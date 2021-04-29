package com.example.myapplication4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE_1 = 1;
    final int REQUEST_CODE_2 = 2;

    final String[] names = new String[] {
            "Павел", "Иван", "Мария", "Максим", "Наталья", "Василий"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_main);
        sPref = getPreferences(MODE_PRIVATE);
        final EditText login = (EditText) findViewById(R.id.editTextTextPersonName3);
        final EditText password = (EditText) findViewById(R.id.editTextTextPersonName4);
        AlertDialog.Builder infoDialog = new AlertDialog.Builder(MainActivity.this);
        infoDialog.setTitle("Вход в аккаунт");
        infoDialog.setCancelable(false);
        if (!sPref.getString("login", "").equals("")) {
            infoDialog.setMessage("Вы хотите зайти как пользователь " + sPref.getString("login", "") + "?");
            infoDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    login.setText(sPref.getString("login", ""));
                    password.setText(sPref.getString("password", ""));
                }
            });
            infoDialog.setNegativeButton("Выход", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString("login", "");
                    ed.putString("password", "");
                    ed.commit();
                }
            });
            infoDialog.show();
        }

        ListView listView = findViewById(R.id.lvSpisok);
        adapter = new ArrayAdapter<>(this, R.layout.list_item, alNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    SharedPreferences sPref;
    ArrayAdapter <String> adapter;
    final ArrayList<String> alNames = new ArrayList<>();

    public void addItem(View view){
        EditText et = (EditText) findViewById(R.id.editTextTextPersonName3);
        alNames.add(0, et.getText().toString());
        adapter.notifyDataSetChanged();
    }

    public void registerOnClick(View view){
        AlertDialog.Builder infoDialog = new AlertDialog.Builder(MainActivity.this);
        infoDialog.setTitle("Регистрация");
        infoDialog.setCancelable(false);
        final EditText login = (EditText) findViewById(R.id.editTextTextPersonName3);
        final EditText password = (EditText) findViewById(R.id.editTextTextPersonName4);
        final View v = (LinearLayout) getLayoutInflater().inflate(R.layout.registration_window, null);
        infoDialog.setView(v);
        infoDialog.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sPref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                EditText log =(EditText) v.findViewById(R.id.editTextTextPersonName);
                EditText pass = (EditText) v.findViewById(R.id.editTextTextPersonName2);
                ed.putString("login", log.getText().toString());
                ed.putString("password", pass.getText().toString());
                ed.commit();
                login.setText(sPref.getString("login", ""));
                password.setText(sPref.getString("password", ""));
                Toast.makeText(MainActivity.this, "Ваши данные были сохранены", Toast.LENGTH_SHORT).show();
            }
        });
        infoDialog.setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        infoDialog.show();
    }

    public void deleteOnClick(View view){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("login", "");
        ed.putString("password", "");
        Toast.makeText(MainActivity.this, "Данные были успешно удалены", Toast.LENGTH_SHORT).show();
        final EditText login = (EditText) findViewById(R.id.editTextTextPersonName3);
        final EditText password = (EditText) findViewById(R.id.editTextTextPersonName4);
        login.setText("");
        password.setText("");
    }

    public void onClick(View view){
        final EditText login = (EditText) findViewById(R.id.editTextTextPersonName3);
        final EditText password = (EditText) findViewById(R.id.editTextTextPersonName4);
        if (login.getText().toString().equals(sPref.getString("login","")) && (password.getText().toString().equals(sPref.getString("password", ""))) && (!sPref.getString("password", "").equals(""))) {
            Intent intent = new Intent(this, CalcActivity.class);
            intent.putExtra("login", sPref.getString("login", ""));
            startActivityForResult(intent, REQUEST_CODE_1);
        }
        else{
            Toast.makeText(MainActivity.this, "Неверно введённый логин или пароль.", Toast.LENGTH_SHORT).show();
        }
    }

    public void changePreferences(View view){
        final EditText login = (EditText) findViewById(R.id.editTextTextPersonName3);
        final EditText password = (EditText) findViewById(R.id.editTextTextPersonName4);
        if (login.getText().toString().equals(sPref.getString("login","")) && (password.getText().toString().equals(sPref.getString("password", ""))) && (!sPref.getString("password", "").equals(""))) {
            Intent intent = new Intent(this, LoginedApp.class);
            intent.putExtra("login", sPref.getString("login", ""));
            startActivityForResult(intent, REQUEST_CODE_2);
        }
        else{
            Toast.makeText(MainActivity.this, "Неверно введённый логин или пароль.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView calcRes = (TextView) findViewById(R.id.calcResult);
        if (resultCode == RESULT_OK){
            switch(requestCode) {
                case REQUEST_CODE_1:
                    String s = data.getStringExtra("e1");
                    calcRes.setText(s);
                    break;
                case REQUEST_CODE_2:
                    String theme = data.getStringExtra("e1");
                    if (theme.equals("dark")){
                        LinearLayout ll = (LinearLayout) findViewById(R.id.main_window);
                        ll.setBackgroundColor(Color.DKGRAY);
                        calcRes.setText("Выбрана тёмная тема");
                    }
                    else {
                        LinearLayout ll = (LinearLayout) findViewById(R.id.main_window);
                        ll.setBackgroundColor(Color.WHITE);
                        calcRes.setText("Выбрана светлая тема");
                    }
                    break;
            }
        }
        else Toast.makeText(MainActivity.this, "Неудача", Toast.LENGTH_SHORT).show();
    }
    Student student = null;
    public void  editStudent(View view){
        Intent intent = new Intent(MainActivity.this, StudentInfo.class);
        if (student != null) intent.putExtra("student", student);
        startActivityForResult(intent, REQUEST_CODE_2);
    }
}