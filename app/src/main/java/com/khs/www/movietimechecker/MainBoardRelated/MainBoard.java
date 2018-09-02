package com.khs.www.movietimechecker.MainBoardRelated;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.khs.www.movietimechecker.DateTimePickerRelated.DateTimePicker;
import com.khs.www.movietimechecker.ETC.Util;
import com.khs.www.movietimechecker.FinalSelectionRelated.Finalselection;
import com.khs.www.movietimechecker.FindTheaterRelated.OneTheaterInfo;
import com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard;
import com.khs.www.movietimechecker.HotMovieRelated.HotMovie;
import com.khs.www.movietimechecker.HotMovieRelated.HotMovieItem;
import com.khs.www.movietimechecker.R;
import com.khs.www.movietimechecker.databinding.MainboardLayoutBinding;

import java.util.ArrayList;


public class MainBoard extends AppCompatActivity {

    public static final int REQ_TITLE = 0; // 영화 제목 선택 요청
    public static final int REQ_THEATER = 1; // 영화관 선택 요청

    ArrayList<String> selectedMovieTitleArr = new ArrayList<>();//선택된 영화 제목들(화면 표시용)
    ArrayList<HotMovieItem> selectedMovieTitleArrForFinalSelection = new ArrayList<>();//선택된 영화 제목들(조회결과 화면용)

    ArrayList<String> selectedTheaterArr = new ArrayList<>();//선택된 영화관 정보들(화면 표시용)
    ArrayList<OneTheaterInfo> selectedTheaterArrForFinalSelection = new ArrayList<>();//선택된 영화 제목들(조회결과 화면용)

    String date, time;//관람 날짜, 관람 시간

    MainboardLayoutBinding binding;

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        Typeface typeface = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName);
        textView.setTypeface(typeface);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TextView getWhentext() {
        return binding.whentext;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.mainboard_layout);
        binding.setActivity(this);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.search.setHeight(binding.toolbar.getHeight());

        if (!Util.isNetworkOn(this)) {
            Toast.makeText(this, "네트워크에 먼저 연결해주세요", Toast.LENGTH_LONG).show();
        }
    }

    public void onWhatClicked(View v) {
        Intent intent = new Intent(this, HotMovie.class);
        startActivityForResult(intent, REQ_TITLE);
    }

    public void onWhenClicked(View v) {
        DialogFragment newFragment = new DateTimePicker();
        newFragment.show(getSupportFragmentManager(), "myDatePicker");
    }

    public void onWhereClicked(View v) {
        Intent intent = new Intent(this, TheaterSelectionTabBoard.class);
        startActivityForResult(intent, REQ_THEATER);
    }

    public void onSearchClicked(View v) {
        if (selectedMovieTitleArr.size() != 0 && !date.equals("") && !time.equals("") && selectedTheaterArr.size() != 0) {
            Intent intent = new Intent(MainBoard.this, Finalselection.class);
            intent.putParcelableArrayListExtra("selMovieTitleArr", selectedMovieTitleArrForFinalSelection);
            intent.putParcelableArrayListExtra("selTheaterArr", selectedTheaterArrForFinalSelection);
            intent.putExtra("selDate", date);
            intent.putExtra("selTime", time);
            intent.putExtra("selDateAndTime", binding.whentext.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "모든 정보를 채워주세요", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_TITLE:
                if (resultCode == RESULT_OK) {
                    ArrayList<HotMovieItem> selectedArray = data.getParcelableArrayListExtra("selTitle");
                    selectedMovieTitleArrForFinalSelection = selectedArray;
                    selectedMovieTitleArr.clear();
                    for (HotMovieItem a : selectedArray) {
                        selectedMovieTitleArr.add(a.getTitle());
                    }
                    binding.whattext.setText(Util.arrayToString(selectedMovieTitleArr, ", "));
                }
                break;

            case REQ_THEATER:
                if (resultCode == RESULT_OK) {
                    ArrayList<OneTheaterInfo> selectedArray = data.getParcelableArrayListExtra("selTheater");
                    selectedTheaterArrForFinalSelection = selectedArray;
                    selectedTheaterArr.clear();
                    for (OneTheaterInfo a : selectedArray) {
                        selectedTheaterArr.add(a.getName());
                    }
                    binding.wheretext.setText(Util.arrayToString(selectedTheaterArr, ", "));
                }
                break;

            default:
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_board, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String text = "http://play.google.com/store/apps/details?id=" + getPackageName();
                intent.putExtra(Intent.EXTRA_TEXT, text);
                Intent chooser = Intent.createChooser(intent, "친구에게 공유하기");
                startActivity(chooser);
                return true;

            case R.id.question:
                Intent ShareIntent = new Intent(Intent.ACTION_VIEW);
                ShareIntent.setData(Uri.parse("market://details?id=" + getPackageName()));
                startActivity(ShareIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
