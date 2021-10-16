package com.example.prjmidtermreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prjmidtermreview.model.ClientOrder;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edClientName;
    TextView tvClientNumberDisplay;
    Button btnReturn;
    int clientNumberData = 0;
    String clientNameData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initialize();
    }

    private void initialize()
    {
        tvClientNumberDisplay = findViewById(R.id.tvClientNumberDisplay);
        edClientName = findViewById(R.id.edClientName);
        btnReturn = findViewById(R.id.btnReturn);
        clientNumberData = getIntent().getIntExtra("clientNumber", 0);
        clientNameData = getIntent().getStringExtra("clientName");
        tvClientNumberDisplay.setText(String.valueOf(clientNumberData));
        edClientName.setText(clientNameData);
        btnReturn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        try
        {
            Intent intent = new Intent();
            int id = view.getId();
            String newName = edClientName.getText().toString();
            if(id == R.id.btnReturn)
            {
                if(newName.equals(clientNameData))
                {
                    setResult(RESULT_CANCELED, intent);
                }
                else
                {
                    intent.putExtra("newNumber", clientNumberData);
                    intent.putExtra("newName", newName);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}