package com.example.sportspreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvName, tvSport;
    Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize()
    {
        tvName = findViewById(R.id.tvName);
        tvSport = findViewById(R.id.tvSport);
        btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(this);

        //Receiving data from previous activity
        String name = getIntent().getStringExtra("name");
        tvName.setText(name);
        //Dont put a value that correspond to some radio button. With this you can know if no value was passed.
        int sp = getIntent().getIntExtra("sport", -100);
        String sport = "No sport";
        if(sp == R.id.rbsoccer)
        {
            sport = "Soccer";
        }
        if(sp == R.id.rbHandball)
        {
            sport = "Handball";
        }
        if(sp == R.id.rbHockey)
        {
            sport = "Hockey";
        }
        tvSport.setText(sport);
    }

    @Override
    public void onClick(View view) {
        //This will destroy the activity
        finish();
    }
}