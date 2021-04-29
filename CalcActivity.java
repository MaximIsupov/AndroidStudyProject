package com.example.myapplication4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalcActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my123);
        EditText calc = (EditText) findViewById(R.id.editText);

        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += (((Button) v).getText());
                calc.setText(s);
            }
        };

        View.OnClickListener eq = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(s.substring(0,s.indexOf(operand)));
                double num2 = Double.parseDouble(s.substring(s.indexOf(operand)+1));
                if (operand.equals("+")) s = String.valueOf(num1+num2);
                if (operand.equals("-")) s =String.valueOf(num1-num2);
                if (operand.equals("/")) s =String.valueOf(num1/num2);
                if (operand.equals("*")) s= String.valueOf(num1*num2);

                if (((String) ((Button) v).getText()).equals("=")){
                    operand = " ";
                }
                else {operand = (String) ((Button) v).getText();s+= (String) ((Button) v).getText();}
                calc.setText(s);
                Intent intent = new Intent();
                intent.putExtra("e1", calc.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        };

        View.OnClickListener toast = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder InfoDialog = new AlertDialog.Builder(CalcActivity.this);
                InfoDialog.setTitle("Дилаон");
                InfoDialog.setMessage("Checkng...");
                InfoDialog.setCancelable(false);
                InfoDialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                InfoDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                InfoDialog.show();

            }
        };

        View.OnClickListener oper = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (operand.equals(" ")) {
                    s += (((Button) v).getText());
                    calc.setText(s);
                    operand = (String) ((Button) v).getText();
                }
                else {
                    double num1 = Double.parseDouble(s.substring(0,s.indexOf(operand)));
                    double num2 = Double.parseDouble(s.substring(s.indexOf(operand)+1));
                    if (operand.equals("+")) s = String.valueOf(num1+num2);
                    if (operand.equals("-")) s =String.valueOf(num1-num2);
                    if (operand.equals("/")) s =String.valueOf(num1/num2);
                    if (operand.equals("*")) s= String.valueOf(num1*num2);

                    if (((String) ((Button) v).getText()).equals("=")){
                        operand = " ";
                    }
                    else {operand = (String) ((Button) v).getText();s+= (String) ((Button) v).getText();}
                    calc.setText(s);
                }

            }
        };

        ((Button) findViewById(R.id.button1)).setOnClickListener(l);
        ((Button) findViewById(R.id.button2)).setOnClickListener(l);
        ((Button) findViewById(R.id.button3)).setOnClickListener(l);
        ((Button) findViewById(R.id.button4)).setOnClickListener(l);
        ((Button) findViewById(R.id.button5)).setOnClickListener(l);
        ((Button) findViewById(R.id.button6)).setOnClickListener(l);
        ((Button) findViewById(R.id.button7)).setOnClickListener(l);
        ((Button) findViewById(R.id.button8)).setOnClickListener(l);
        ((Button) findViewById(R.id.button9)).setOnClickListener(l);
        ((Button) findViewById(R.id.button0)).setOnClickListener(l);
        ((Button) findViewById(R.id.button_point)).setOnClickListener(l);

        ((Button) findViewById(R.id.button_plus)).setOnClickListener(oper);
        ((Button) findViewById(R.id.button_minus)).setOnClickListener(oper);
        ((Button) findViewById(R.id.button_divide)).setOnClickListener(oper);
        ((Button) findViewById(R.id.button_proiz)).setOnClickListener(oper);
        ((Button) findViewById(R.id.button_equals)).setOnClickListener(eq);

        ((Button) findViewById(R.id.button_toast)).setOnClickListener(toast);
    }

    String operand = " ";
    String s = "";

}
