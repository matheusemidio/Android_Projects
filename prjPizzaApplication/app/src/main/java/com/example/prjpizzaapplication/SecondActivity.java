package com.example.prjpizzaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import model.ClientOrder;

public class SecondActivity extends AppCompatActivity {

    TextView tvOrders,tvTotal;

    ArrayList<ClientOrder> listOfOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initalize();
    }

    private void initalize()
    {
        tvOrders = findViewById(R.id.tvOrders);
        tvTotal = findViewById(R.id.tvTotal);
        //Receinving data and deserializing
        listOfOrders = (ArrayList<ClientOrder>) getIntent().getExtras().getSerializable("orders");
        StringBuilder list = new StringBuilder("");
        float total = 0;
        for (ClientOrder oneOrder : listOfOrders)
        {
            list.append(oneOrder + "\n");
            total += oneOrder.getAmount();

        }
        tvOrders.setText(list);
        tvTotal.append(String.valueOf(total));
    }
}