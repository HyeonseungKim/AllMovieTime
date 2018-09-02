package com.khs.www.movietimechecker.FindTheaterRelated;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khs.www.movietimechecker.R;

import org.json.JSONArray;


//주요지역 배열 어댑터
public class FindTheaterAdapter extends RecyclerView.Adapter<FindTheaterAdapter.ItemViewHolder> {

    Context mContext;
    JSONArray mData;
    Typeface typeFace;


    public FindTheaterAdapter(Context context, JSONArray data) {
        mContext = context;
        mData = data;
        typeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/NanumBarunGothicLight.ttf");
    }

    @Override
    public FindTheaterAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onemainlocation_layout, parent, false);
        return new FindTheaterAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindTheaterAdapter.ItemViewHolder holder, int position) {
        try {
            holder.mainLoc.setText(mData.getJSONObject(position).get("name").toString());
            holder.mainLoc.setTypeface(typeFace);
        } catch (Exception e) {

        }
    }


    @Override
    public int getItemCount() {
        return mData.length();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mainLoc;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mainLoc = itemView.findViewById(R.id.mainLocName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        SpecificLoc spl = new SpecificLoc();
                        spl.setArgs(mData.getJSONObject(getAdapterPosition()));
                        FragmentTransaction trans = ((TheaterSelectionTabBoard) mContext).getSupportFragmentManager().beginTransaction();
                        trans.replace(R.id.rootfragment, spl, "specificloc");
                        // Back button Press 시에 차이 발생 - trans.addToBackStack(null);
                        trans.commit();
                    } catch (Exception e) {

                    }
                }
            });
        }
    }
}


