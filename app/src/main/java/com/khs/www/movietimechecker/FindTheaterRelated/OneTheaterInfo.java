package com.khs.www.movietimechecker.FindTheaterRelated;

import android.os.Parcel;
import android.os.Parcelable;


public class OneTheaterInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public OneTheaterInfo createFromParcel(Parcel in) {
            return new OneTheaterInfo(in);
        }

        @Override
        public OneTheaterInfo[] newArray(int size) {
            // TODO Auto-generated method stub
            return new OneTheaterInfo[size];
        }

    };
    String code;
    String name;
    boolean isChecked = false;

    OneTheaterInfo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public OneTheaterInfo(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
    }


}
