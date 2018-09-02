package com.khs.www.movietimechecker.DateTimePickerRelated;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.khs.www.movietimechecker.MainBoardRelated.MainBoard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateTimePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    MainBoard mb;

    Calendar mCalendar = Calendar.getInstance(Locale.KOREA);
    Date date = new Date(mCalendar.getTimeInMillis());
    String dateFormat = "yyyyMMdd";
    SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

    String selectedDate, selectedTime;
    TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            selectedTime = String.format(Locale.KOREA, "%02d", hourOfDay) + String.format(Locale.KOREA, "%02d", minute);

            if (selectedDate != null && !selectedDate.equals("") && selectedTime != null && !selectedTime.equals("")) {
                mb.setDate(selectedDate);
                mb.setTime(selectedTime);
//변수용 날짜(20180708) vs 표시용 날짜(2018년 7월 8일)
                try {
                    Date forVariableDate = dateFormatter.parse(selectedDate);
                    String forDisplayDate = new SimpleDateFormat("yyyy년 M월 d일 ").format(forVariableDate);
                    Date forVariableTime = new SimpleDateFormat("HHmm").parse(selectedTime);
                    String forDisplayTime = new SimpleDateFormat("H시 m분 이후 상영영화").format(forVariableTime);

                    mb.getWhentext().setText(forDisplayDate + forDisplayTime);
                } catch (Exception e) {
                    mb.getWhentext().setText("");
                }
            } else {
                mb.getWhentext().setText("");
            }
        }
    };

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        mCalendar.set(year, month, dayOfMonth);
        selectedDate = dateFormatter.format(mCalendar.getTimeInMillis());

        String hourFormat = "HH";
        SimpleDateFormat hourFormatter = new SimpleDateFormat(hourFormat);
        final String hour = hourFormatter.format(date);//현재 시 담기

        String minuteFormat = "mm";
        SimpleDateFormat minuteFormatter = new SimpleDateFormat(minuteFormat);
        final String minute = minuteFormatter.format(date);//현재 분 담기

        TimePickerDialog dialog2 = new TimePickerDialog(getActivity(), listener, Integer.parseInt(hour), Integer.parseInt(minute), true);
        dialog2.show();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mb = (MainBoard) getActivity();
// 오늘 날짜를 디폴트로 해서 달력 다이얼로그 띄우기
        int defaultYear = mCalendar.get(Calendar.YEAR);
        int defaultMonth = mCalendar.get(Calendar.MONTH);
        int defaultDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDialog = new DatePickerDialog(getActivity(), this, defaultYear, defaultMonth, defaultDay);

// 금일부터 최대 3일 후까지 선택 가능
        DatePicker mDatePicker = mDialog.getDatePicker();
        mDatePicker.setMinDate(mCalendar.getTimeInMillis());
        Calendar after3days = Calendar.getInstance(Locale.KOREA);
        after3days.add(Calendar.DATE, 3);
        mDatePicker.setMaxDate(after3days.getTimeInMillis());

        return mDialog;
    }
}
