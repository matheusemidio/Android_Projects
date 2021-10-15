package com.example.prjinternetprovider;

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
import java.util.List;

import models.InternetProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    EditText edClientNumber, edMonths;
    TextView tvSubtotal, tvTPS, tvTVB, tvTotal;
    RadioGroup rgInternetProviders;
    RadioButton rbBell, rbVideotron, rbAcanac;
    Button btnSave, btnShowAll, btnQuit;
    String company = "";
    ArrayList<InternetProvider> listOfOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize()
    {
        edClientNumber = findViewById(R.id.edClientNumber);
        edMonths = findViewById(R.id.edMonths);
        tvSubtotal = findViewById(R.id.tvSubtotalResult);
        tvTPS = findViewById(R.id.tvTPSResult);
        tvTVB = findViewById(R.id.tvTVBResult);
        tvTotal = findViewById(R.id.tvTotalResult);
        rgInternetProviders = findViewById(R.id.rgInternetProviders);
        rbBell = findViewById(R.id.rbBell);
        rbVideotron = findViewById(R.id.rbVideotron);
        rbAcanac = findViewById(R.id.rbAcanac);
        btnSave = findViewById(R.id.btnSave);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnQuit = findViewById(R.id.btnQuit);
        listOfOrders = new ArrayList<InternetProvider>();

        btnShowAll.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnQuit.setOnClickListener(this);

        rbBell.setOnClickListener(this);
        rbVideotron.setOnClickListener(this);
        rbAcanac.setOnClickListener(this);

        edMonths.addTextChangedListener(this);

    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        switch (id)
        {
            case R.id.btnSave:
                save(view);
                break;
            case R.id.btnShowAll:
                showAll();
                break;
            case R.id.btnQuit:
                quit();
                break;
            case R.id.rbBell:
                company = rbBell.getText().toString();
                showAmount();
                break;
            case R.id.rbVideotron:
                company = rbVideotron.getText().toString();
                showAmount();
                break;
            case R.id.rbAcanac:
                company = rbAcanac.getText().toString();
                showAmount();
                break;
            default:
                break;

        }
    }


    private float getTVB(float price)
    {
        float TVB = (float) ((price * 9.995) / 100);
        return TVB;
    }

    private float getTPS(float price)
    {
        float TPS = (float) ((price * 6) / 100 );
        return TPS;
    }

    private float getPrice(String company) throws Exception {
        int months = getMonths();
        float price = 0;
        if(company.equals("Bell"))
        {
            if(months >= 1 && months < 12)
            {
                price = (months * 60);
                return price ;
            }
            else if(months == 12)
            {
                price = 600;
                return price;
            }
            else
            {
                throw new Exception("Please, enter a valid number of months (between 1 and 12)");
            }
        }
        else if(company.equals("Videotron"))
        {
            if(months >= 1 && months < 6)
            {
                price = (months * 70);
                return price;
            }
            else if(months >=6 && months < 12)
            {
                price = (350 + ( (months - 6) * 70) );
                return price;
            }
            else if(months == 12)
            {
                price = 12 * 70;
                //30% off
                price = price - ( (price * 30) /100);
                return price;
            }
            else
            {
                throw new Exception("Please, enter a valid number of months (between 1 and 12)");
            }
        }
        else if(company.equals("aCanac"))
        {
            if(months >= 1 && months < 12)
            {
                price = (months * 45);
                return price;
            }
            else if(months == 12)
            {
                price = (11 * 45);
                return price;
            }
            else
            {
                throw new Exception("Please, enter a valid number of months (between 1 and 12)");
            }
        }
        else{
            throw new Exception("Please select an Internet Provider.");
        }
    }

    private int getMonths() throws Exception {
        if(edMonths.getText().toString() != null)
        {
            return (Integer.valueOf(edMonths.getText().toString()));
        }
        else{
            clearTextViews();
            throw new Exception("Please enter the number of months.");
        }
    }

    private void clearTextViews()
    {
        tvSubtotal.setText(null);
        tvTotal.setText(null);
        tvTPS.setText(null);
        tvTVB.setText(null);
    }

    private void quit()
    {
        System.exit(0);
    }

    private void showAmount()
    {
        try{
            float price = getPrice(company);
            float TPS = getTPS(price);
            float TVB = getTVB(price);
            float subtotal = price;
            float total = (price + TPS + TVB);

            tvSubtotal.setText(String.valueOf(subtotal));
            tvTPS.setText(String.valueOf(TPS));
            tvTVB.setText(String.valueOf(TVB));
            tvTotal.setText(String.valueOf(total));

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    private void clearWidgets()
    {
        edClientNumber.setText(null);
        edMonths.setText(null);
        clearTextViews();
        rgInternetProviders.clearCheck();
        edClientNumber.requestFocus();
    }

    private void save(View view)
    {
        try{
            Snackbar.make(view,"Save in progress", Snackbar.LENGTH_LONG).show();
            int clientNumber = Integer.valueOf(edClientNumber.getText().toString());
            int months = Integer.valueOf(edMonths.getText().toString());
            InternetProvider order = new InternetProvider(clientNumber, company, months);
            listOfOrders.add(order);
            Snackbar.make(view,"The order of the client " + clientNumber + "\n has been saved successfully.", Snackbar.LENGTH_LONG).show();
            clearWidgets();

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    private void showAll()
    {
        try
        {
            Intent intent = new Intent(this, SecondActivity.class);
            //Objects take memory, so when you launch another activity, it can not reduce the memory for this
            //activity. So you need to serialize the orders before sending them to the next activity.
            intent.putExtra("orders", listOfOrders);
            //Toast.makeText(this, "Im here", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

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