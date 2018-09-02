package com.khs.www.movietimechecker.FindTheaterRelated;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.khs.www.movietimechecker.R;

import java.util.ArrayList;

public class TheaterListAdapter extends RecyclerView.Adapter<TheaterListAdapter.ItemViewHolder> {

    ArrayList<OneTheaterInfo> mItems;
    Context mContext;
    SharedPreferences mPref;
    SharedPreferences.Editor prefEditor;

    public TheaterListAdapter(ArrayList<OneTheaterInfo> items, Context context) {
        mItems = items;
        mContext = context;
        mPref = mContext.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        prefEditor = mPref.edit();
    }


    @Override
    public TheaterListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.onefavoritetheater_forselection_layout, parent, false);
        return new TheaterListAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TheaterListAdapter.ItemViewHolder holder, int position) {


        final CheckBox mCheckBox = holder.mCheckBox;

        final int mPosition = position;

        final String code = mItems.get(mPosition).code;
        final String name = mItems.get(mPosition).name;

        holder.specificLoc.setText(mItems.get(position).name);


        if (mItems.get(position).isChecked) {
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckBox.toggle();
                mItems.get(mPosition).isChecked = mCheckBox.isChecked();
            }
        });

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.get(mPosition).isChecked = mCheckBox.isChecked();
            }
        });


        if (mPref.contains(code)) {//이미 즐겨찾기 되어 있는 영화관인 경우 별표시
            holder.star.setBackgroundResource(R.drawable.staron);
        } else {
            holder.star.setBackgroundResource(R.drawable.staroff);
        }


        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPref.contains(code)) {
                    prefEditor.remove(code);
                    v.setBackgroundResource(R.drawable.staroff);
                    prefEditor.apply();
                    Toast.makeText(mContext, "영화관이 즐겨찾기에서 제거되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    prefEditor.putString(code, name);
                    v.setBackgroundResource(R.drawable.staron);
                    prefEditor.apply();
                    Toast.makeText(mContext, "영화관이 즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public TextView specificLoc;
        public CheckBox mCheckBox;
        public ImageView star;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.mLinear);
            specificLoc = itemView.findViewById(R.id.specificLoc);
            mCheckBox = itemView.findViewById(R.id.checkBox);
            star = itemView.findViewById(R.id.setfavorite);
        }

    }

}
