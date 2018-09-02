package com.khs.www.movietimechecker.FinalSelectionRelated;

import java.util.Comparator;

public class OneFinalItem {//최종 선택 리스트에서의 한 아이템


    String moviename;
    String theatername;
    String movietime;
    String whichroom;//같은 영화관 내 다른 관

    OneFinalItem(String moviename, String theatername, String movietime, String whichroom) {
        this.moviename = moviename;
        this.theatername = theatername;
        this.movietime = movietime;
        this.whichroom = whichroom;
    }

    public String getMoviename() {
        return moviename;
    }

    public static class MovieNameCompare implements Comparator<OneFinalItem> {
        @Override
        public int compare(OneFinalItem first, OneFinalItem second) {
//맨 앞 시간 우선 순 그다음 기준은 영화명
            String firstmovieName = first.moviename;
            String secondmovieName = second.moviename;

            String firstmovietime = first.movietime.split(" | ")[0];
            String secondmovietime = second.movietime.split(" | ")[0];

            if (firstmovietime.compareTo(secondmovietime) < 0) {
                return -1;
            } else if (firstmovietime.compareTo(secondmovietime) == 0) {
                if (firstmovieName.compareTo(secondmovieName) < 0) {
                    return -1;
                } else if (firstmovieName.compareTo(secondmovieName) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }


    }


    public static class TheaterNameCompare implements Comparator<OneFinalItem> {
        @Override
        public int compare(OneFinalItem first, OneFinalItem second) {
//맨 앞 시간 우선 순 그다음 기준은 ?관
            String firstTheaterName = first.theatername + first.whichroom;
            String secondTheaterName = second.theatername + second.whichroom;

            String firstmovietime = first.movietime.split(" | ")[0];
            String secondmovietime = second.movietime.split(" | ")[0];

            if (firstmovietime.compareTo(secondmovietime) < 0) {
                return -1;
            } else if (firstmovietime.compareTo(secondmovietime) == 0) {
                if (firstTheaterName.compareTo(secondTheaterName) < 0) {
                    return -1;
                } else if (firstTheaterName.compareTo(secondTheaterName) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }


    }

}
