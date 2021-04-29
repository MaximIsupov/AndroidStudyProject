package com.example.myapplication4;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject implements Parcelable {
    String name;
    Integer mark;



    public Subject(Parcel in) {
        mark = in.readInt();
        name = in.readString();
    }

    public Subject(String name, Integer mark) {
        this.mark = mark;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mark);
        dest.writeString(this.name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };
}
