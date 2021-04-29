package com.example.myapplication4;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student implements Parcelable {

    String first_name, last_name, middle_name;
    Integer group;
    ArrayList<Subject> subjects = new ArrayList<>();

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student(Parcel in) {
        group = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        middle_name = in.readString();
        subjects = in.createTypedArrayList(Subject.CREATOR);
    }

    public Student(String first_name, String last_name, String middle_name, Integer group, ArrayList<Subject> subjects){
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.group = group;
        this.subjects = subjects;
    }

    public Student(){

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.group);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.middle_name);
        dest.writeTypedList(subjects);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
