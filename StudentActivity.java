package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (((EditText) findViewById(R.id.editTextTextLastName)).getText().toString().equals("") ||
                        ((EditText) findViewById(R.id.editTextTextFirstName)).getText().toString().equals("") ||
                        ((EditText) findViewById(R.id.editTextTextMiddleName)).getText().toString().equals("") ||
                        ((EditText) findViewById(R.id.editTextTextGroup)).getText().toString().equals("")){
                    ((Button) findViewById(R.id.btn_accept)).setVisibility(View.INVISIBLE);
                }
                else {
                    ((Button) findViewById(R.id.btn_accept)).setVisibility(View.VISIBLE);
                }
            }
        };
        EditText ed = (EditText) findViewById(R.id.editTextTextLastName);
        ed.addTextChangedListener(tw);
        ed.setText(getIntent().getStringExtra("last"));

        ed = (EditText) findViewById(R.id.editTextTextMiddleName);
        ed.addTextChangedListener(tw);
        ed.setText(getIntent().getStringExtra("second"));

        ed = (EditText) findViewById(R.id.editTextTextFirstName);
        ed.addTextChangedListener(tw);
        ed.setText(getIntent().getStringExtra("first"));

        ed = (EditText) findViewById(R.id.editTextTextGroup);
        ed.addTextChangedListener(tw);
        Integer i = getIntent().getIntExtra("group", 0);

        if (i!=0){
            ed.setText(i.toString());
        }

    }
    public void studentOk(View view){
        Intent intent = new Intent();
        intent.putExtra("last", ((EditText) findViewById(R.id.editTextTextLastName)).getText().toString());
        intent.putExtra("middle", ((EditText) findViewById(R.id.editTextTextMiddleName)).getText().toString());
        intent.putExtra("first", ((EditText) findViewById(R.id.editTextTextFirstName)).getText().toString());
        intent.putExtra("group", ((EditText) findViewById(R.id.editTextTextGroup)).getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }

    public void studentCancel(View view){
        finish();
    }
}