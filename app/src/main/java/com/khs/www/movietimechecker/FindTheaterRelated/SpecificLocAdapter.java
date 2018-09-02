package com.khs.www.movietimechecker.FindTheaterRelated;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khs.www.movietimechecker.R;

import java.util.ArrayList;

public class SpecificLocAdapter extends RecyclerView.Adapter<SpecificLocAdapter.ItemViewHolder> {
    ArrayList<OneSpecificLoc> mItems;
    Context mContext;


    public SpecificLocAdapter(ArrayList<OneSpecificLoc> items, Context context) {
        mItems = items;
        mContext = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.onespecificloc_layout, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {


        final CheckBox mCheckBox = holder.mCheckBox;
        final int mPosition = position;
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

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView specificLoc;
        private CheckBox mCheckBox;


        public ItemViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.mLinear);
            specificLoc = itemView.findViewById(R.id.specificLoc);
            mCheckBox = itemView.findViewById(R.id.checkBox);
        }

    }
}
