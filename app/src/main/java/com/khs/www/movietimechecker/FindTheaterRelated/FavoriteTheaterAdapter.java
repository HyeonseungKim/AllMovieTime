package com.khs.www.movietimechecker.FindTheaterRelated;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.khs.www.movietimechecker.R;

import java.util.ArrayList;

public class FavoriteTheaterAdapter extends RecyclerView.Adapter<FavoriteTheaterAdapter.ItemViewHolder> {

    Context mContext;
    ArrayList<OneTheaterInfo> mData;
    SharedPreferences mPref = FavoriteTheater.mPref;
    SharedPreferences.Editor prefEditor = mPref.edit();


    public FavoriteTheaterAdapter(Context context, ArrayList data) {
        mContext = context;
        mData = data;
    }

    @Override
    public FavoriteTheaterAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onefavoritetheater_layout, parent, false);
        return new FavoriteTheaterAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteTheaterAdapter.ItemViewHolder holder, int position) {
        try {
            holder.movieName.setText(mData.get(position).name);
            if (mData.get(position).isChecked == true) {
                holder.setChecked(true);
            } else {
                holder.setChecked(false);
            }
        } catch (Exception e) {

        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements Checkable {
        private TextView movieName;
        private CheckBox cb;
        private ImageView delBtn;

        public ItemViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            cb = itemView.findViewById(R.id.checkBox);
            delBtn = itemView.findViewById(R.id.delBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setChecked(!mData.get(getAdapterPosition()).isChecked);
                    mData.get(getAdapterPosition()).isChecked = !mData.get(getAdapterPosition()).isChecked;
                    notifyDataSetChanged();
                }
            });

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setChecked(!mData.get(getAdapterPosition()).isChecked);
                    mData.get(getAdapterPosition()).isChecked = !mData.get(getAdapterPosition()).isChecked;
                    notifyDataSetChanged();
                }
            });

            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String code = mData.get(getAdapterPosition()).code;
                    if (mPref.contains(code)) {
                        prefEditor.remove(code);
                        prefEditor.apply();
                    }
                    mData.remove(getAdapterPosition());
                    Snackbar.make(((Activity) mContext).findViewById(R.id.coordinatorlayout), "영화관이 즐겨찾기에서 삭제되었습니다", Snackbar.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });


        }

        @Override
        public boolean isChecked() {
            return cb.isChecked();
        }

        //한 행을 클릭하면 해당 행 내의 체크박스가 체크되도록 함
        @Override
        public void setChecked(boolean checked) {
            if (cb.isChecked() != checked) {
                cb.setChecked(checked);
            }
        }

        @Override
        public void toggle() {
            setChecked(cb.isChecked() ? false : true);
        }
    }
}
