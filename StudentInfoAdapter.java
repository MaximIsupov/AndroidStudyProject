package com.example.myapplication4;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentInfoAdapter extends BaseAdapter {
    ArrayList<Subject> subjects = new ArrayList<>();
    Context ctx;
    LayoutInflater lInflater;

    StudentInfoAdapter(Context context, ArrayList<Subject> subjects){
        ctx = context;
        this.subjects = subjects;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = lInflater.inflate(R.layout.subjects_list_layout, parent, false);

        if(subjects.isEmpty()) return view;

        ((TextView) view.findViewById(R.id.tvListSubject)).setText(subjects.get(position).getName());
        if (Integer.parseInt(subjects.get(position).getMark().toString()) == 2){
            ((TextView) view.findViewById(R.id.tvListMark)).setText("Н/А");
            ((TextView) view.findViewById(R.id.tvListMark)).setTextColor(Color.RED);
        }
        else{
            ((TextView) view.findViewById(R.id.tvListMark)).setText(subjects.get(position).getMark().toString());
        }
        return view;
    }
}

