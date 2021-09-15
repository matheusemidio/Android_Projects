package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Class level declarations
    EditText edNumber1, edNumber2,edResult;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize()
    {
        //Making link between widgets and objects
        edResult = findViewById(R.id.edResult);
        edNumber1 = findViewById(R.id.edNumber1);
        edNumber2 = findViewById(R.id.edNumber2);
        radioGroup = findViewById(R.id.radioGroup);
        //selectedItem = String.valueOf(radioGroup.getCheckedRadioButtonId());

    }

    public void calculate(View view)
    {
        //Extracting the value of the input
        Double number1 = Double.parseDouble(edNumber1.getText().toString());
        Double number2 = Double.parseDouble(edNumber2.getText().toString());
        Double result = 0.0;

        //Getting the id of the selected radiobutton
        int selectedItem = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton =  findViewById(selectedItem);
        String choice = radioButton.getText().toString();

        //Debug
        //Toast.makeText(this,"Selected item: " + choice + "\n",Toast.LENGTH_LONG).show();

        if(choice.equals("Add"))
        {
            result = number1 + number2;
            edResult.setText(String.valueOf(result));
            //Toast.makeText(this, number1 + " + " + number2 + " = " + result + "\n", Toast.LENGTH_LONG).show();

        }
        else if(choice.equals("Multiply"))
        {
            result = number1 * number2;
            edResult.setText(String.valueOf(result));
            //Toast.makeText(this, number1 + " * " + number2 + " = " + result + "\n", Toast.LENGTH_LONG).show();


        }
        else if(choice.equals("Subtract"))
        {
            result = number1 - number2;
            edResult.setText(String.valueOf(result));
            //Toast.makeText(this, number1 + " - " + number2 + " = " + result + "\n", Toast.LENGTH_LONG).show();

        }
        else{
            edResult.setText("Error.");
            //Toast.makeText(this, "Some error occurred. \n", Toast.LENGTH_LONG).show();
        }

    }
    public void clear(View view)
    {
        edNumber1.setText("");
        edNumber2.setText("");
        edResult.setText("");
    }
    public void quit(View view)
    {

        finish();
    }

}