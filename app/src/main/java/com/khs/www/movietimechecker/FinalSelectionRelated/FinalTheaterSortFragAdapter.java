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

public class FinalTheaterSortFragAdapter extends RecyclerView.Adapter<FinalTheaterSortFragAdapter.ItemViewHolder> {

    ArrayList<OneFinalItem> mItems;
    Context mContext;
    Hashtable<String, ArrayList> HashTableKeyedByTheaterName = new Hashtable<>();
    ArrayList<Hashtable<String, ArrayList<OneFinalItem>>> ArrayListOfHashTableKeyedByMovieName = new ArrayList<>();
    Object[] theaterNameKeySetArr, movieNameKeySetArr;

    int dependson30;
    int dependson20;
    int dependson15;
    int dependson5;

    public FinalTheaterSortFragAdapter(ArrayList<OneFinalItem> items, Context context) {
        mItems = items;
        mContext = context;

        dependson30 = Util.dpToPixel(mContext, 30);
        dependson20 = Util.dpToPixel(mContext, 20);
        dependson15 = Util.dpToPixel(mContext, 15);
        dependson5 = Util.dpToPixel(mContext, 5);

        for (int i = 0; i < mItems.size(); i++) {

            OneFinalItem one = mItems.get(i);
            String theaterName = one.theatername;
            String movieTitle = one.moviename;

//1. 먼저 영화관을 키로 해서 각 oneFinalItem들의 배열을 나눔
            if (HashTableKeyedByTheaterName.containsKey(theaterName)) {

                ArrayListOfHashTableKeyedByMovieName = HashTableKeyedByTheaterName.get(theaterName);
                for (int j = 0; j < ArrayListOfHashTableKeyedByMovieName.size(); j++) {
                    Hashtable<String, ArrayList<OneFinalItem>> oneHashtable = ArrayListOfHashTableKeyedByMovieName.get(j);
                    if (oneHashtable.containsKey(movieTitle)) {
                        ArrayList<OneFinalItem> AL = oneHashtable.get(movieTitle);
                        AL.add(one);
                    } else {
                        ArrayList<OneFinalItem> AL = new ArrayList<>();
                        AL.add(one);
                        oneHashtable.put(movieTitle, AL);
                    }
                }
            } else {
                ArrayList<Hashtable<String, ArrayList<OneFinalItem>>> newone = new ArrayList<Hashtable<String, ArrayList<OneFinalItem>>>();
                Hashtable<String, ArrayList<OneFinalItem>> newone2 = new Hashtable<>();
                ArrayList<OneFinalItem> newone3 = new ArrayList<>();
                newone3.add(one);
                newone2.put(movieTitle, newone3);
                newone.add(newone2);
                HashTableKeyedByTheaterName.put(theaterName, newone);
            }
        }

        theaterNameKeySetArr = HashTableKeyedByTheaterName.keySet().toArray();
        Arrays.sort(theaterNameKeySetArr);

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.onefinallistitemcenteredtitle_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        LinearLayout mLinear = new LinearLayout(mContext);
        mLinear.setOrientation(LinearLayout.VERTICAL);
        mLinear.setPadding(0, 0, 0, 0);

//-----영화관 뷰 설정 Start--
        TextView theaterName = new TextView(mContext);
        final int ViewID = View.generateViewId();
        theaterName.setId(ViewID);
        theaterName.setText((String) theaterNameKeySetArr[position]);
        theaterName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.small_where, 0, 0, 0);
        theaterName.setTextColor(Color.BLACK);
        theaterName.setTextSize(Util.dpToPixel(mContext, 8));
        theaterName.setGravity(Gravity.LEFT);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            theaterName.setTextAppearance(mContext, R.style.titleCenteredfinalListTheater);
        } else {
            theaterName.setTextAppearance(R.style.titleCenteredfinalListTheater);
        }

        LinearLayout.LayoutParams LL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (position == 0) {
            LL.setMargins(0, 0, 0, 0);
        } else {
            LL.setMargins(0, dependson20, 0, 0);
        }

        theaterName.setOnClickListener(new Util.goToTheaterSiteOnClickListener(ViewID, mContext));

        mLinear.addView(theaterName, LL);
//-----영화관 뷰 설정 End--

        ArrayList<Hashtable<String, ArrayList<OneFinalItem>>> ArrOfHashtable = HashTableKeyedByTheaterName.get(theaterNameKeySetArr[position]);

        for (int i = 0; i < ArrOfHashtable.size(); i++) {
//----- 영화명 설정 Start--
            Hashtable<String, ArrayList<OneFinalItem>> oneHashtable = ArrOfHashtable.get(i);

            movieNameKeySetArr = oneHashtable.keySet().toArray();
            Arrays.sort(movieNameKeySetArr);

            for (int j = 0; j < movieNameKeySetArr.length; j++) {

                TextView movieName = new TextView(mContext);
                int ViewID2 = View.generateViewId();
                movieName.setId(ViewID2);
                movieName.setText((String) movieNameKeySetArr[j]);
                movieName.setTextSize(Util.dpToPixel(mContext, 6));
                movieName.setTextColor(Color.BLACK);
                movieName.setGravity(Gravity.LEFT);

                LinearLayout.LayoutParams LL2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LL2.setMargins(0, dependson15, 0, dependson5);

                mLinear.addView(movieName, LL2);
//----- 영화명 설정 End--
                ArrayList<OneFinalItem> finallist = oneHashtable.get((String) movieNameKeySetArr[j]);
// ?관과 시작 시간을 기준으로 각 항목(?관 + 시간정보)정렬 Start--
                OneFinalItem.MovieNameCompare mNC = new OneFinalItem.MovieNameCompare();
                Collections.sort(finallist, mNC);

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
//일반적인 경우와 달리 이 경우는 영화관 이름을 키로 하여 같은 영화제목이면 밸류인 배열에 oneFinalitem이 들어가기 때문에 이렇게 표현
        return HashTableKeyedByTheaterName.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout titleLinearLayout;

        public ItemViewHolder(View itemView) {

            super(itemView);
            titleLinearLayout = itemView.findViewById(R.id.titleLinLayoutLayout);
        }

    }

}

