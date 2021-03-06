package com.example.myapplication4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentInfo extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        student = getIntent().getParcelableExtra("student");
        if (student == null){
            studentEdit(null);
        }
        else update();
        ListView listView = findViewById(R.id.lvSubjects);
        adapter = new ArrayAdapter<>(this, R.layout.list_item, SubjectsNames);
        listView.setAdapter(adapter);
    }

    final ArrayList<String> SubjectsNames = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Student student;
    final int REQUEST_CODE_INFO = 0;

    public void studentEdit(View view){
        Intent intent = new Intent(StudentInfo.this, StudentActivity.class);
        if (student != null){
            intent.putExtra("first", student.getFirst_name());
            intent.putExtra("second", student.getMiddle_name());
            intent.putExtra("last", student.getLast_name());
            intent.putExtra("group", student.getGroup());
        }
        startActivityForResult(intent, REQUEST_CODE_INFO);
    }

    StudentInfoAdapter olAdapter;

    private void showPopupMenu(View v, int position) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popup_marks);
       popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
               Integer mark;
               switch (item.getItemId()) {
                   case R.id.popup5: {
                       mark = 5;
                       break;
                   }
                   case R.id.popup4: {
                       mark = 4;
                       break;
                   }
                   case R.id.popup3: {
                       mark = 3;
                       break;
                   }
                   case R.id.popup2: {
                       mark = 2;
                       break;
                   }
                   default:
                       return false;
               }
               if ((student != null) && (position<student.getSubjects().size())){
                    student.getSubjects().get(position).setMark(mark);
                    olAdapter.notifyDataSetChanged();
               }
               return true;
           }

       });
       popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
           @Override
           public void onDismiss(PopupMenu menu) {

           }
       });
       popupMenu.show();
    }

    public void update(){
        if (student == null) return;
        ((TextView) findViewById(R.id.student_surname)).setText((student.getMiddle_name()));
        ((TextView) findViewById(R.id.student_name)).setText(((student.getFirst_name())));
        ((TextView) findViewById(R.id.student_middle_name)).setText(student.getLast_name());
        ((TextView) findViewById(R.id.student_group)).setText((student.getGroup()).toString());

        olAdapter = new StudentInfoAdapter(StudentInfo.this, student.getSubjects());
        ListView lvMain = (ListView) findViewById(R.id.lvSubjects);
        lvMain.setAdapter(olAdapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view.findViewById(R.id.tvListMark), position);
            }
        });

        }




    public void addSubject(View view){
        AlertDialog.Builder infoDialog = new AlertDialog.Builder(StudentInfo.this);
        infoDialog.setTitle("???????????????? ?????????? ??????????????");
        infoDialog.setCancelable(false);
        final View v = (LinearLayout) getLayoutInflater().inflate(R.layout.subject_layout, null);
        infoDialog.setView(v);
        infoDialog.setPositiveButton("????????????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Spinner spinner = (Spinner) v.findViewById(R.id.spMarks);
                EditText subject_name = v.findViewById(R.id.etSubject);
                Subject sub = new Subject(subject_name.getText().toString(), 5-spinner.getSelectedItemPosition());
                student.getSubjects().add(sub);
//                SubjectsNames.add(sub.name + " " + sub.mark);
//                adapter.notifyDataSetChanged();
                update();
            }
        });
        infoDialog.setNegativeButton("??????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        infoDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_INFO:
                    if (student == null) student = new Student();
                    student.setFirst_name(data.getStringExtra("first"));
                    student.setLast_name(data.getStringExtra("last"));
                    student.setMiddle_name(data.getStringExtra("middle"));
                    student.setGroup(Integer.parseInt(data.getStringExtra("group")));
                    update();
                    break;
            }
        }
        else{
            //Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }
}
