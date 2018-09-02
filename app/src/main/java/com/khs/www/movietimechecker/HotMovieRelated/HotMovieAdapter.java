package com.khs.www.movietimechecker.HotMovieRelated;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khs.www.movietimechecker.ETC.CheckableLinearLayout;
import com.khs.www.movietimechecker.R;

import java.util.ArrayList;

public class HotMovieAdapter extends RecyclerView.Adapter<HotMovieAdapter.ItemViewHolder> {
    ArrayList<HotMovieItem> mItems;
    Context mContext;

    public HotMovieAdapter(ArrayList<HotMovieItem> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckableLinearLayout view = (CheckableLinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.hotmovie_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        final CheckBox mCheckBox = holder.mCheckBox;

        final int mPosition = position;

        Glide.with(mContext).load(mItems.get(position).getPosterlink()).into(holder.moviePoster);
        holder.movieTitle.setText(mItems.get(position).getTitle());


        if (mItems.get(position).isChecked) {
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }

        holder.moviePoster.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String mPosterLink = mItems.get(mPosition).getPosterlink();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPosterLink.substring(0, mPosterLink.indexOf("type"))));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                return true;
            }
        });

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckBox.toggle();
                mItems.get(mPosition).setChecked(mCheckBox.isChecked());
            }
        });

        holder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckBox.toggle();
                mItems.get(mPosition).setChecked(mCheckBox.isChecked());
            }
        });


        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.get(mPosition).setChecked(mCheckBox.isChecked());
            }
        });

        holder.mLinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String mTitle = mItems.get(mPosition).getTitle();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?query=" + mTitle));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CheckableLinearLayout mLinearLayout;
        private ImageView moviePoster;
        private TextView movieTitle;
        private CheckBox mCheckBox;


        public ItemViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.mLinear);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            mCheckBox = itemView.findViewById(R.id.movieTitlecheckbox);
        }

    }
}