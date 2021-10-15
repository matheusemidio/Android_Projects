package com.example.prjpizzaapplication;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import model.ClientOrder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    TextView tvAmount;
    EditText edClientNumber, edNumberSlices;
    RadioGroup rgPizza;
    RadioButton rbCheese, rbVegetarian, rbMexican;
    Button btnSave, btnQuit, btnShowAll;
    ArrayList<ClientOrder> listOfOrders;
    String pizzaType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize()
    {
        tvAmount = (TextView) findViewById(R.id.tvAmountResult);
        edClientNumber = (EditText) findViewById(R.id.edClientNumber);
        edNumberSlices = (EditText) findViewById(R.id.edNumberSlices);
        rgPizza = (RadioGroup) findViewById(R.id.rgPizza);
        rbCheese = (RadioButton) findViewById(R.id.rbCheese);
        rbVegetarian = (RadioButton) findViewById(R.id.rbVegetarian);
        rbMexican = (RadioButton) findViewById(R.id.rbMexican);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnShowAll = (Button) findViewById(R.id.btnShowAll);
        listOfOrders = new ArrayList<ClientOrder>();

        btnSave.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);

        rbCheese.setOnClickListener(this);
        rbVegetarian.setOnClickListener(this);
        rbMexican.setOnClickListener(this);

        edNumberSlices.addTextChangedListener(this);

    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        switch (id)
        {
            case R.id.btnSave:
                saveOrder(view);
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

    private void showAmount()
    {
        try
        {
            float price = getPrice(pizzaType);
            int nbSlices = Integer.valueOf(edNumberSlices.getText().toString());
            float amount = price * nbSlices;
            tvAmount.setText(String.valueOf(amount));
        } catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private float getPrice(String pizzaType) throws Exception {
        float price;
        if(pizzaType.equals("Cheese"))
        {
            price = 2.0f;
        }
        else if(pizzaType.equals("Vegetarian"))
        {
            price = 2.5f;
        }
        else if(pizzaType.equals("Mexican"))
        {
            price = 3.0f;
        }
        else {
            throw new Exception("Please select a pizza type.");
        }

        return price;
    }

    private void saveOrder(View view)
    {
        try
        {
            Snackbar.make(view,"Save in progress", Snackbar.LENGTH_LONG).show();
            int clientNumber = Integer.valueOf(edClientNumber.getText().toString());
            int numberSlices = Integer.valueOf(edNumberSlices.getText().toString());
            ClientOrder order = new ClientOrder(clientNumber,pizzaType,numberSlices);
            listOfOrders.add(order);
            Snackbar.make(view,"The order of the client " + clientNumber + "\n has been saved successfully.", Snackbar.LENGTH_LONG).show();
            clearWidgets();

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void clearWidgets()
    {
        edClientNumber.setText(null);
        edNumberSlices.setText(null);
        rgPizza.clearCheck();
        tvAmount.setText(null);
        edClientNumber.requestFocus();
    }

    private void showAll()
    {
        Intent intent = new Intent(this, SecondActivity.class);
        //Objects take memory, so when you launch another activity, it can not reduce the memory for this
        //activity. So you need to serialize the orders before sending them to the next activity.
        intent.putExtra("orders", listOfOrders);
        startActivity(intent);

    }

    private void quit()
    {
        System.exit(0);
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
}