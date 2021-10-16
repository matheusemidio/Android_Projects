package com.example.prjmidtermreview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prjmidtermreview.model.ClientOrder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    EditText edClientNumber, edClientName, edNbSlices;
    TextView tvAmountResult;
    Button btnSave, btnShowAll, btnQuit, btnUpdate;
    RadioGroup rgPizza;
    RadioButton rbCheese, rbVegetarian, rbMexican;
    String pizzaType = "";
    ArrayList<ClientOrder> listOfOrders;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize()
    {
        edClientNumber = findViewById(R.id.edClientNumber);
        edClientName = findViewById(R.id.edClientName);
        edNbSlices = findViewById(R.id.edNbSlices);
        tvAmountResult = findViewById(R.id.tvAmountResult);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuit = findViewById(R.id.btnQuit);
        btnShowAll = findViewById(R.id.btnShowAll);
        rgPizza = findViewById(R.id.rgPizza);
        rbCheese = findViewById(R.id.rbCheese);
        rbVegetarian = findViewById(R.id.rbVegetarian);
        rbMexican = findViewById(R.id.rbMexican);
        listOfOrders = new ArrayList<ClientOrder>();

        btnSave.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnQuit.setOnClickListener(this);

        rbCheese.setOnClickListener(this);
        rbMexican.setOnClickListener(this);
        rbVegetarian.setOnClickListener(this);

        edNbSlices.addTextChangedListener(this);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK && result.getData() != null)
                        {
                            try
                            {
                                int newNumber = result.getData().getIntExtra("newNumber",0);
                                String newName = result.getData().getStringExtra("newName");

                                for (ClientOrder oneOrder : listOfOrders)
                                {
                                    if(oneOrder.getClientNumber() == newNumber)
                                    {
                                        oneOrder.setClientNumber(newNumber);
                                        oneOrder.setClientName(newName);
                                        edClientNumber.setText(String.valueOf(newNumber));
                                        edClientName.setText(oneOrder.getClientName());
                                    }
                                }
                            }
                            catch(Exception e)
                            {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }


                        }
                        else
                        {
                            if(result.getResultCode() == RESULT_CANCELED)
                            {
                                Toast.makeText(MainActivity.this,"No change in the Client", Toast.LENGTH_LONG).show();
                            }
                        }


                    }
                }
        );
    }




    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        showAmount();
    }
    

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id)
        {
            case R.id.btnSave:
                saveOrder();
                break;
            case R.id.btnUpdate:
                update();
                break;
            case R.id.btnShowAll:
                showAll();
                break;
            case R.id.btnQuit:
                quit();
                break;
            case R.id.rbCheese:
                pizzaType = rbCheese.getText().toString();
                showAmount();
                break;
            case R.id.rbVegetarian:
                pizzaType = rbVegetarian.getText().toString();
                showAmount();
                break;
            case R.id.rbMexican:
                pizzaType = rbMexican.getText().toString();
                showAmount();
                break;
            default:
                break;
        }
    }

    private void update()
    {
        try
        {
            ClientOrder order;
            int clientNumber = Integer.valueOf(edClientNumber.getText().toString());
            String clientName = "";
            Intent intent = new Intent(this, ThirdActivity.class);
            for (ClientOrder oneOrder : listOfOrders)
            {
                if(oneOrder.getClientNumber() == clientNumber)
                {

                    intent.putExtra("clientNumber", clientNumber);
                    intent.putExtra("clientName", oneOrder.getClientName());
                    activityResultLauncher.launch(intent);
                }
                else
                {
                    Toast.makeText(this, "Please enter a client number.", Toast.LENGTH_LONG).show();
                }

            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void quit()
    {
        System.exit(0);
    }

    private void showAll()
    {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("orders", listOfOrders);
        startActivity(intent);

    }


    private void saveOrder() 
    {
        try
        {
            int clientNumber = Integer.valueOf(edClientNumber.getText().toString());
            String clientName = edClientName.getText().toString();
            int nbSlices = Integer.valueOf(edNbSlices.getText().toString());
            ClientOrder order = new ClientOrder(clientNumber,clientName,pizzaType,nbSlices);
            listOfOrders.add(order);
            clearWidgets();
            Toast.makeText(this, "Order was saved.", Toast.LENGTH_LONG).show();

        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void clearWidgets()
    {
        edClientNumber.setText(null);
        edClientName.setText(null);
        edNbSlices.setText(null);
        tvAmountResult.setText(null);
        rgPizza.clearCheck();
        edClientNumber.requestFocus();
    }

    private void showAmount()
    {
        try
        {
            float amount = 0;
            int nbSlices = Integer.valueOf(edNbSlices.getText().toString());
            switch (pizzaType)
            {
                case "Cheese":
                    amount = nbSlices * 1f;
                    break;
                case "Vegetarian":
                    amount = nbSlices * 5f;
                    break;
                case "Mexican":
                    amount = nbSlices * 10f;
                    break;
                default:
                    break;

            }
            tvAmountResult.setText(String.valueOf(amount));
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}