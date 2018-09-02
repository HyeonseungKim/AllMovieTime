package com.khs.www.movietimechecker.ETC;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.khs.www.movietimechecker.R;

//리스트 뷰에서 각 한 행(뷰)_해당 행 클릭만으로 체크가 작동하도록 함
public class CheckableLinearLayout extends LinearLayout implements Checkable {

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isChecked() {
        CheckBox cb = findViewById(R.id.movieTitlecheckbox);
        return cb.isChecked();
    }

    //한 행을 클릭하면 해당 행 내의 체크박스가 체크되도록 함
    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = findViewById(R.id.movieTitlecheckbox);
        if (cb.isChecked() != checked) {
            cb.setChecked(checked);
        }
    }

    @Override
    public void toggle() {
        CheckBox cb = findViewById(R.id.movieTitlecheckbox);
        setChecked(cb.isChecked() ? false : true);
    }
}



