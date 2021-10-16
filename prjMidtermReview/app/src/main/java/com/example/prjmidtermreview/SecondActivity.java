package com.example.prjmidtermreview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prjmidtermreview.model.ClientOrder;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTotalResult, tvOrders;
    Button btnReturn;
    ArrayList<ClientOrder> listOfOrdersData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();

    }

    private void initialize()
    {
        tvTotalResult = findViewById(R.id.tvTotalResult);
        tvOrders = findViewById(R.id.tvOrders);
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        listOfOrdersData = (ArrayList<ClientOrder>) getIntent().getExtras().getSerializable("orders");
        StringBuilder list = new StringBuilder("");
        float total = 0;
        for (ClientOrder order : listOfOrdersData)
        {
            list.append(order + "\n");
            total += order.getAmount();
        }
        tvOrders.setText(list);
        tvTotalResult.setText(String.valueOf(total));
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