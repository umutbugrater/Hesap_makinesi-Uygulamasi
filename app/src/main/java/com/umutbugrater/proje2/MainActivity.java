package com.umutbugrater.proje2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private EditText newNumber;
    private TextView operandDisplay;
    private String pendingOperation = "+";

    private Double deger1 = null;
    private Double deger2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.text_Result);
        newNumber = (EditText) findViewById(R.id.etNewNumber);
        operandDisplay = (TextView) findViewById(R.id.text_operand);

        Button button0 = (Button) findViewById(R.id.button_0);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button button4 = (Button) findViewById(R.id.button_4);
        Button button5 = (Button) findViewById(R.id.button_5);
        Button button6 = (Button) findViewById(R.id.button_6);
        Button button7 = (Button) findViewById(R.id.button_7);
        Button button8 = (Button) findViewById(R.id.button_8);
        Button button9 = (Button) findViewById(R.id.button_9);
        Button buttonDot = (Button) findViewById(R.id.button_dot);
        Button buttonDivide = (Button) findViewById(R.id.button_div);
        Button buttonEquals = (Button) findViewById(R.id.button_equals);
        Button buttonPlus = (Button) findViewById(R.id.button_plus);
        Button buttonMinus = (Button) findViewById(R.id.button_minus);
        Button buttonMulti = (Button) findViewById(R.id.button_multi);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                newNumber.append(button.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String value = newNumber.getText().toString();
                Double dValue = Double.parseDouble(value);
                Hesapla(dValue,op);
                newNumber.setText("");
                pendingOperation = op;
                operandDisplay.setText(pendingOperation);
            }
        };
        buttonDivide.setOnClickListener(operationListener);
        buttonPlus.setOnClickListener(operationListener);
        buttonMinus.setOnClickListener(operationListener);
        buttonMulti.setOnClickListener(operationListener);
        buttonEquals.setOnClickListener(operationListener);
    }
    public void Hesapla(Double value,String op){
        if(deger1==null)
            deger1 = value;
        else{
            deger2 = value;
            if(pendingOperation.equals("=")){
                pendingOperation = op;
            }

            switch(pendingOperation){
                case "=":
                    deger1 = deger2;
                    break;
                case "/":
                    if(deger2==0)
                        deger1 = 0.0;
                    else{
                        deger1 = deger1/deger2;
                    }
                    break;
                case "+":
                    deger1 += deger2;
                    break;
                case "-":
                    deger1 -= deger2;
                    break;
                case "*":
                    deger1 *= deger2;
                    break;
            }
        }
        result.setText(deger1.toString());
        newNumber.setText("");
        operandDisplay.setText(op);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putString("PENDING",pendingOperation);
        if (deger1 != null) {
            outState.putDouble("VALUE",deger1);
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString("PENDING");
        deger1 = savedInstanceState.getDouble("VALUE");
        result.setText(String.valueOf(deger1));
        operandDisplay.setText(pendingOperation);
    }
}