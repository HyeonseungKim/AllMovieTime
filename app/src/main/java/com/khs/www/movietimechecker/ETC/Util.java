package com.khs.www.movietimechecker.ETC;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.khs.www.movietimechecker.R;

import java.util.ArrayList;
import java.util.List;

public class Util {

    //dp TO px
    public static int dpToPixel(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale);
    }

    //배열 To 문자열
    public static String arrayToString(ArrayList<String> array, String delimiter) {
        StringBuilder arTostr = new StringBuilder();
        if (array.size() > 0) {
            arTostr.append(array.get(0));
            for (int i = 1; i < array.size(); i++) {
                arTostr.append(delimiter);
                arTostr.append(array.get(i));
            }
        }
        return arTostr.toString();
    }

    //네트워크 상태 확인
    public static boolean isNetworkOn(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            return true;
        } else {
            return false;
        }
    }

    //영화관 조회 화면에서 각 프래그먼트마다 다른 기능의 스낵바 생성
    public static Snackbar getCustomizedSnackView(Context context, Snackbar mSnackBar, View.OnClickListener allCheck, View.OnClickListener checkComplete) {

        LinearLayout.LayoutParams objLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) mSnackBar.getView();
        CoordinatorLayout.LayoutParams parentParams = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();
        parentParams.setMargins(0, 0, 0, 0);
        layout.setLayoutParams(parentParams);
        layout.setPadding(0, 0, 0, 0);
        layout.setLayoutParams(parentParams);

        View snackView = ((Activity) context).getLayoutInflater().inflate(R.layout.my_snackbar, null);

        TextView textViewOne = (TextView) snackView.findViewById(R.id.first_text_view);
        textViewOne.setText("모두 선택");
        textViewOne.setOnClickListener(allCheck);

        TextView textViewTwo = (TextView) snackView.findViewById(R.id.second_text_view);
        textViewTwo.setText("선택 완료");
        textViewTwo.setOnClickListener(checkComplete);

        layout.addView(snackView, objLayoutParams);


        return mSnackBar;


    }

    //즐겨찾기한 영화관이 없는 경우 표시
    public static void makeCoachMark(Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.theaterselectiontab_coachmark_layout);
        dialog.setCanceledOnTouchOutside(true);
        View masterView = dialog.findViewById(R.id.coachmark);
        masterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        final TextView title = new TextView(context);
        title.setText("즐겨찾기된 영화관이 없습니다\n영화관을 즐겨찾기에 \n추가해서 편하게 찾으세요");
// 버전에 따라 사용가능한 api 다름
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            title.setTextAppearance(context, R.style.mainBoardtitlestyle);
        } else {
            title.setTextAppearance(R.style.mainBoardtitlestyle);
        }
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/NanumBarunGothic.ttf");
        title.setTypeface(typeFace);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        title.setGravity(Gravity.LEFT | Gravity.CENTER);
        dialog.addContentView(title, lp);
    }

    //특정 앱 존재여부 확인
    public static boolean getPackageList(Context mContext, String apppackagename) {
        boolean isExist = false;
        PackageManager pkgMgr = mContext.getPackageManager();
        List<ResolveInfo> mApps;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0);
        try {
            for (int i = 0; i < mApps.size(); i++) {
                if (mApps.get(i).activityInfo.packageName.contains(apppackagename)) {
                    isExist = true;
                    break;
                }
            }
        } catch (Exception e) {
            isExist = false;
        }
        return isExist;
    }

    //한글 초성 매칭 여부 확인
    public static class hangulmatcher//초성 검색용 클래스
    {
        private static final char HANGUL_BEGIN_UNICODE = 44032; //유니코드 한글 첫글자
        private static final char HANGUL_LAST_UNICODE = 55203; //유니코드 한글 마지막 글자
        private static final char HANGUL_BASE_UNIT = 588; //한 자음 당 작성가능한 총 글자 수
        private static final char[] INITIAL_SOUND = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};//모든 초성


        //초성 여부 확인
        private static boolean isInitialSound(char searchar) {
            for (char c : INITIAL_SOUND) {
                if (c == searchar) {
                    return true;
                }
            }
            return false;
        }

        //해당 문자 자음 얻기
        private static char getInitialSound(char c) {
            int hanBegin = (c - HANGUL_BEGIN_UNICODE);
            int index = hanBegin / HANGUL_BASE_UNIT;
            return INITIAL_SOUND[index];
        }

        //해당 문자 한글 여부 확인
        private static boolean isHangul(char c) {
            return HANGUL_BEGIN_UNICODE <= c && c <= HANGUL_LAST_UNICODE;
        }

        //해당되는 초성이나 문자가 있는지를 확인하는 함수
        public static boolean matchString(String value, String stringToSearch) {
            int t = 0;
            int seof = value.length() - stringToSearch.length();//검색어와 대상어의 길이 차이
            int slen = stringToSearch.length();//검색어의 길이
            if (seof < 0)
                return false; //검색어가 더 긴 경우 바로 false 리턴

            for (int i = 0; i <= seof; i++) {//문자열 일치 여부 확인 시 대상어에서의 기준 인덱스 _ 계속 기준 인덱스를 한 칸씩 뒤로 가면서 끝까지 확인
                t = 0;//문자열 일치 여부 확인시 검색어에서의 인덱스
                while (t < slen) {
                    if (isInitialSound(stringToSearch.charAt(t)) == true && isHangul(value.charAt(i + t))) {
                        //만약 현재 검색어의 문자가 초성이고 대상어의 문자가 한글이면
                        if (getInitialSound(value.charAt(i + t)) == stringToSearch.charAt(t))
                            //각각의 초성끼리 같은지 비교한다
                            t++;  //검색어 내에서 다음 인덱스로 이동
                        else
                            break;
                    } else {
                        //만약 현재 검색어의 문자가 초성이 아니라면
                        if (value.charAt(i + t) == stringToSearch.charAt(t))
                            //문자 자체가 같은지 비교한다
                            t++;  //검색어 내에서 다음 인덱스로 이동
                        else
                            break;
                    }
                }
                if (t == slen)  //만약 t가 가리키는 인덱스가 검색어의 길이와 같아지면 이는 해당하는 초성열이나 문자열이 있다는 뜻이므로 true 리턴
                    return true;
            }
            return false; //일치하는 부분을 찾지 못했으면 false 리턴
        }
    }

    //CGV, 롯데시네마, 메가박스가 포함된 영화관 이름 클릭 시 설치된 앱 또는 영화사 웹페이지 열기
    public static class goToTheaterSiteOnClickListener implements View.OnClickListener {

        int viewId;
        Context mContext;


        public goToTheaterSiteOnClickListener(int _viewId, Context context) {
            viewId = _viewId;
            mContext = context;
        }

        @Override
        public void onClick(View v) {
            String whichtheater = ((TextView) v.findViewById(viewId)).getText().toString();
            if (whichtheater.contains("CGV")) {
                whichtheater = "cgv";
                if (getPackageList(mContext, whichtheater)) {
                    Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.cgv.android.movieapp");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cgv.co.kr/reserve/show-times/"));
                    mContext.startActivity(i);
                }
            } else if (whichtheater.contains("메가박스")) {
                whichtheater = "megabox";
                if (getPackageList(mContext, whichtheater)) {
                    Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.megabox.mop");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.megabox.co.kr/?menuId=timetable-movie"));
                    mContext.startActivity(i);
                }

            } else if (whichtheater.contains("롯데시네마")) {
                whichtheater = "lottecinema";
                if (getPackageList(mContext, whichtheater)) {
                    Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("kr.co.lottecinema.lcm");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lottecinema.co.kr/LCHS/Contents/ticketing/ticketing.aspx"));
                    mContext.startActivity(i);
                }
            } else {
                Toast.makeText(mContext, "해당 영화관 예약은 따로 알아보셔야 합니다", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
