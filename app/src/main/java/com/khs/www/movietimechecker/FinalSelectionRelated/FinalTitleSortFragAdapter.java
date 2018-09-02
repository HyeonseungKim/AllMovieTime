package com.khs.www.movietimechecker.FinalSelectionRelated;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khs.www.movietimechecker.ETC.Util;
import com.khs.www.movietimechecker.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

public class FinalTitleSortFragAdapter extends RecyclerView.Adapter<FinalTitleSortFragAdapter.ItemViewHolder> {

    ArrayList<OneFinalItem> mItems;
    Context mContext;
    Hashtable<String, ArrayList> HashTableKeyedByMovieName = new Hashtable<>();
    ArrayList<Hashtable<String, ArrayList<OneFinalItem>>> ArrayListOfHashTableKeyedByTheaterName = new ArrayList<>();
    Object[] movieNameKeySetArr, theaterNameKeySetArr;

    int dependson30;
    int dependson20;
    int dependson15;
    int dependson5;

    public FinalTitleSortFragAdapter(ArrayList<OneFinalItem> items, Context context) {
        mItems = items;
        mContext = context;

        dependson30 = Util.dpToPixel(mContext, 30);
        dependson20 = Util.dpToPixel(mContext, 20);
        dependson15 = Util.dpToPixel(mContext, 15);
        dependson5 = Util.dpToPixel(mContext, 5);

        for (int i = 0; i < mItems.size(); i++) {

            OneFinalItem one = mItems.get(i);
            String movieTitle = one.moviename;
            String theaterName = one.theatername;
//1. 먼저 영화제목을 키로 해서 각 oneFinalItem들의 배열을 나눔
            if (HashTableKeyedByMovieName.containsKey(movieTitle)) {

                ArrayListOfHashTableKeyedByTheaterName = HashTableKeyedByMovieName.get(movieTitle);
                for (int j = 0; j < ArrayListOfHashTableKeyedByTheaterName.size(); j++) {
                    Hashtable<String, ArrayList<OneFinalItem>> oneHashtable = ArrayListOfHashTableKeyedByTheaterName.get(j);
                    if (oneHashtable.containsKey(theaterName)) {
                        ArrayList<OneFinalItem> AL = oneHashtable.get(theaterName);
                        AL.add(one);
                    } else {
                        ArrayList<OneFinalItem> AL = new ArrayList<>();
                        AL.add(one);
                        oneHashtable.put(theaterName, AL);
                    }
                }
            } else {
                ArrayList<Hashtable<String, ArrayList<OneFinalItem>>> newone = new ArrayList<Hashtable<String, ArrayList<OneFinalItem>>>();
                Hashtable<String, ArrayList<OneFinalItem>> newone2 = new Hashtable<>();
                ArrayList<OneFinalItem> newone3 = new ArrayList<>();
                newone3.add(one);
                newone2.put(theaterName, newone3);
                newone.add(newone2);
                HashTableKeyedByMovieName.put(movieTitle, newone);
            }
        }

        movieNameKeySetArr = HashTableKeyedByMovieName.keySet().toArray();
        Arrays.sort(movieNameKeySetArr);

    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.onefinallistitemcenteredtitle_layout, parent, false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        LinearLayout mLinear = new LinearLayout(mContext);
        mLinear.setOrientation(LinearLayout.VERTICAL);
        mLinear.setPadding(0, 0, 0, 0);

//-----영화 제목 뷰 설정 Start--
        TextView movieName = new TextView(mContext);
        final int ViewID = View.generateViewId();
        movieName.setId(ViewID);
        movieName.setText((String) movieNameKeySetArr[position]);
        movieName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.customloadingicon, 0, 0, 0);
        movieName.setTextColor(Color.BLACK);
        movieName.setTextSize(Util.dpToPixel(mContext, 8));
        movieName.setGravity(Gravity.LEFT);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            movieName.setTextAppearance(mContext, R.style.titleCenteredfinalListTheater);
        } else {
            movieName.setTextAppearance(R.style.titleCenteredfinalListTheater);
        }

        LinearLayout.LayoutParams LL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (position == 0) {
            LL.setMargins(0, 0, 0, 0);
        } else {
            LL.setMargins(0, dependson20, 0, 0);
        }
        mLinear.addView(movieName, LL);
//-----영화 제목 뷰 설정 End--

        ArrayList<Hashtable<String, ArrayList<OneFinalItem>>> ArrOfHashtable = HashTableKeyedByMovieName.get(movieNameKeySetArr[position]);

        for (int i = 0; i < ArrOfHashtable.size(); i++) {
//----- 영화관 이름 뷰 설정 Start--
            Hashtable<String, ArrayList<OneFinalItem>> oneHashtable = ArrOfHashtable.get(i);

            theaterNameKeySetArr = oneHashtable.keySet().toArray();
            Arrays.sort(theaterNameKeySetArr);

            for (int j = 0; j < theaterNameKeySetArr.length; j++) {

                TextView theaterName = new TextView(mContext);
                final int ViewID2 = View.generateViewId();
                theaterName.setId(ViewID2);
                theaterName.setText((String) theaterNameKeySetArr[j]);
                theaterName.setTextSize(Util.dpToPixel(mContext, 6));
                theaterName.setTextColor(Color.BLACK);
                theaterName.setGravity(Gravity.LEFT);
                /*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    theaterName.setTextAppearance(mContext, R.style.titleCenteredfinalListTheater);
                } else {
                    theaterName.setTextAppearance(R.style.titleCenteredfinalListTheater);
                }*/

                LinearLayout.LayoutParams LL2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LL2.setMargins(0, dependson15, 0, dependson5);

                theaterName.setOnClickListener(new Util.goToTheaterSiteOnClickListener(ViewID2, mContext));

                mLinear.addView(theaterName, LL2);
//----- 영화관 이름 뷰 설정 End--
                ArrayList<OneFinalItem> finallist = oneHashtable.get(theaterNameKeySetArr[j]);
// ?관과 시작 시간을 기준으로 각 항목(?관 + 시간정보)정렬 Start--
                OneFinalItem.TheaterNameCompare tNC = new OneFinalItem.TheaterNameCompare();
                Collections.sort(finallist, tNC);

                for (int z = 0; z < finallist.size(); z++) {

                    TextView whichroom = new TextView(mContext);
                    int ViewID3 = View.generateViewId();
                    whichroom.setId(ViewID3);
                    whichroom.setText(finallist.get(z).whichroom);

                    whichroom.setTextSize(Util.dpToPixel(mContext, 5));
                    whichroom.setGravity(Gravity.RIGHT);

                    LinearLayout.LayoutParams LL3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    LL3.setMargins(0, dependson5, 0, 0);


                    mLinear.addView(whichroom, LL3);


                    TextView timetable = new TextView(mContext);
                    int ViewID4 = View.generateViewId();
                    timetable.setId(ViewID4);
                    timetable.setText(finallist.get(z).movietime);
                    timetable.setTextSize(Util.dpToPixel(mContext, 5));
                    timetable.setGravity(Gravity.LEFT);

                    LinearLayout.LayoutParams LL4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    LL4.setMargins(0, dependson5, 0, dependson30);


                    mLinear.addView(timetable, LL4);
// ?관과 시작 시간을 기준으로 각 항목(?관 + 시간정보)정렬 End--
                }
            }
        }

        LinearLayout.LayoutParams LL5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LL5.setMargins(0, dependson15, 0, dependson5);
        holder.titleLinearLayout.addView(mLinear, LL5);

    }


    @Override
    public int getItemCount() {
//일반적인 경우와 달리 이 경우는 영화 제목을 키로 하여 같은 영화제목이면 밸류인 배열에 oneFinalitem이 들어가기 때문에 이렇게 표현
        return HashTableKeyedByMovieName.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout titleLinearLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleLinearLayout = itemView.findViewById(R.id.titleLinLayoutLayout);
        }

    }


}

