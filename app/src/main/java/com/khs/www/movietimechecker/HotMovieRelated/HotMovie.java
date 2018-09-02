package com.khs.www.movietimechecker.HotMovieRelated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.khs.www.movietimechecker.ETC.Util;
import com.khs.www.movietimechecker.R;
import com.khs.www.movietimechecker.databinding.HotmovieLayoutBinding;

import java.util.ArrayList;

//MVVM 적용
public class HotMovie extends AppCompatActivity {

    HotmovieLayoutBinding binding;

    Snackbar mSnackBar;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<HotMovieItem> movieArraySource;//인기영화 리스트 목록 원본
    ArrayList<HotMovieItem> movieArraySearch;//초성검색 시 필터링 결과 리스트


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.hotmovie_layout);
        binding.setActivity(this);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("영화 목록(예매순)");

//커스텀 스낵바
        mSnackBar = Snackbar.make(binding.mCoordinator, null, Snackbar.LENGTH_INDEFINITE);

//영화 리스트 다운로드
        HotMovieListDownloader hm = new HotMovieListDownloader(this);
        hm.getHotMovieList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("영화 검색");
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                mSnackBar.dismiss();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {//검색창이 사라지고 다시 아이콘이 나타나는 것
                return true;
            }
        });

// 텍스트 입력 시 초성 검색 시작
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//일반적인 경우 검색 수행, 이 앱에서는 영화선택 완료로 처리
                titleSelected();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieArraySearch.clear();
                for (int i = 0; i < movieArraySource.size(); i++) {
                    HotMovieItem oneHotMovie = movieArraySource.get(i);
                    if (Util.hangulmatcher.matchString(oneHotMovie.getTitle(), newText)) {
                        movieArraySearch.add(oneHotMovie);
                    }
                }
                binding.mRecyclerView.getAdapter().notifyDataSetChanged();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //인기영화 리스트 다운 후 화면 구성
    public void showMovieTitle(final ArrayList<HotMovieItem> hotmovielist) {

        movieArraySearch = hotmovielist;
        movieArraySource = (ArrayList<HotMovieItem>) hotmovielist.clone();

        binding.mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        binding.mRecyclerView.setLayoutManager(layoutManager);
        Adapter = new HotMovieAdapter(movieArraySearch, this);
        binding.mRecyclerView.setAdapter(Adapter);

// 앱 사용 두번째까지만 스낵바 표시
        SharedPreferences mPref = getSharedPreferences("isitthirdopening", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = mPref.edit();
        if (mPref.getInt("opentime", 0) < 3) {
            Snackbar.make(binding.mCoordinator, "영화정보는 꾹 눌러서 확인~!", Snackbar.LENGTH_LONG).show();
            prefEditor.putInt("opentime", mPref.getInt("opentime", 0) + 1);
            prefEditor.apply();
        }

//모든 영화 선택 혹은 모든 선택 해제
        final View.OnClickListener allCheck = new View.OnClickListener() {//전체 선택 or 선택 해제
            public void onClick(View v) {
                int count = Adapter.getItemCount();
                for (int i = 0; i < count; i++) {
                    movieArraySearch.get(i).setChecked(true);
                }
                binding.mRecyclerView.getAdapter().notifyDataSetChanged();
                titleSelected();
            }
        };


        final View.OnClickListener checkComplete = new View.OnClickListener() {
            public void onClick(View v) {
                titleSelected();
            }
        };
//FAB의 이벤트 리스너는 프래그먼트에 따라 동적으로 변하므로 데이터바인딩 불가
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSnackBar.isShown()) {
                    mSnackBar.dismiss();
                } else {
                    mSnackBar = Util.getCustomizedSnackView(HotMovie.this, mSnackBar, allCheck, checkComplete);
                    mSnackBar.show();
                }
            }
        });

    }

    public void titleSelected() {
        ArrayList<HotMovieItem> selectedArray = new ArrayList<HotMovieItem>();
        for (HotMovieItem a : movieArraySource) {
            if (a.isChecked()) {
                selectedArray.add(a);
            }
        }
        if (selectedArray.size() != 0) {
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("selTitle", selectedArray);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "영화 제목을 선택해주세요", Toast.LENGTH_SHORT).show();
        }
    }

}


