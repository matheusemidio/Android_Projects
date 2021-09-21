package com.example.sportspreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Class level declarations
    EditText edName;
    RadioGroup rgSports;
    Button btnNext, btnClear, btnQuit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize()
    {
        //Making the link between the widget and the object
        edName = findViewById(R.id.edName);
        rgSports = findViewById(R.id.rgSports);
        btnClear = findViewById(R.id.btnClear);
        btnNext = findViewById(R.id.btnNext);
        btnQuit = findViewById(R.id.btnQuit);

        btnClear.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        switch (id)
        {
            case R.id.btnClear:
                clearWidgets();
                break;
            case R.id.btnNext:
                goToSecondActivity();
                break;
            case R.id.btnQuit:
                quitApp();
                break;
            default:
        }

    }

    private void goToSecondActivity()
    {
        //Before we send the activity, we send the data as well
        String name = edName.getText().toString();
        int rbId = rgSports.getCheckedRadioButtonId();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("sport", rbId);
        startActivity(intent);

    }

    private void quitApp()
    {
        //The 0 on status means that you finished the app in a normal way. Other status message
        //can mean an error status message that you want to send
        System.exit(0);
    }

    private void clearWidgets()
    {
        edName.setText(null);
        rgSports.clearCheck();
    }
}