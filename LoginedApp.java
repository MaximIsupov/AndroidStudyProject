package com.example.myapplication4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginedApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
    }

    public void onClickFinish(View view){
        if (view.getId() == R.id.btnDark){
            Intent intent = new Intent();
            intent.putExtra("e1", "dark");
            setResult(RESULT_OK, intent);
            finish();
        }
        else{
            Intent intent = new Intent();
            intent.putExtra("e1", "light");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}