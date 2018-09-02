package com.khs.www.movietimechecker.ETC;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.khs.www.movietimechecker.R;

public class CustomLoadingIcon extends ProgressDialog {

    private Context c;
    private ImageView imgLogo;

    public CustomLoadingIcon(Context context) {
        super(context);
        c = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customloadingicon_layout);
        imgLogo = (ImageView) findViewById(R.id.img_android);

        //회전 애니메이션
        Animation anim = AnimationUtils.loadAnimation(c, R.anim.loading);
        imgLogo.setAnimation(anim);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

