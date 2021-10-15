package com.example.prjinternetprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import models.InternetProvider;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvOrders,tvTotal;
    Button btnReturn;
    ArrayList<InternetProvider> listOfOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize()
    {
        //Toast.makeText(this, "Im here", Toast.LENGTH_LONG).show();
        tvOrders = findViewById(R.id.tvOrders);
        tvTotal = findViewById(R.id.tvTotalResult);
        btnReturn = findViewById(R.id.btnReturn);
        tvTotal.setText(null);

        btnReturn.setOnClickListener(this);

       //Receiving data and deserializing
        listOfOrders = (ArrayList<InternetProvider>) getIntent().getExtras().getSerializable("orders");

        StringBuilder list = new StringBuilder("");
        float total = 0;
        for (InternetProvider oneOrder : listOfOrders)
        {
            list.append(oneOrder + "\n");
            total += oneOrder.getAmount();

        }

        tvOrders.setText(list);
        tvTotal.append(String.valueOf(total));

    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        if(id == R.id.btnReturn)
        {
            finish();
        }

    }
}