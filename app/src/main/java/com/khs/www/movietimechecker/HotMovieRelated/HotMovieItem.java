package com.khs.www.movietimechecker.HotMovieRelated;

import android.os.Parcel;
import android.os.Parcelable;

public class HotMovieItem implements Parcelable {

    String title;
    String posterlink;
    boolean isChecked=false;

    public HotMovieItem(String title, String posterlink) {
        this.title = title;
        this.posterlink = posterlink;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPosterlink() {
        return posterlink;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public HotMovieItem(Parcel in) {
        this.title = in.readString();
        this.posterlink = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.posterlink);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public HotMovieItem createFromParcel(Parcel in) {
            return new HotMovieItem(in);
        }

        @Override
        public HotMovieItem[] newArray(int size) {
            // TODO Auto-generated method stub
            return new HotMovieItem[size];
        }

    };
}
