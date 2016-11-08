package com.example.paul.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by paul on 12/09/16.
 */
public class TimePickerFragment extends DialogFragment{

    public static final String EXTRA_TIME = "com.example.paul.criminalintent.time";

    private static final String ARG_TIME = "time";

    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date time){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME,time);

        TimePickerFragment fragment  = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time,null);

        Date date = (Date) getArguments().getSerializable(ARG_TIME);
        Time time = new Time(date.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        //int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //int minute = calendar.get(Calendar.MINUTE);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = 0;
                                int minute = 0;
                                if (Build.VERSION.SDK_INT >= 23 ){
                                     hour = mTimePicker.getHour();
                                     //Log.d("hours",""+hour);
                                     minute = mTimePicker.getMinute();
                                }
                                else{
                                     hour = mTimePicker.getCurrentHour();
                                     minute = mTimePicker.getCurrentMinute();
                                }

                                //Time offset
                                Calendar mCalendar = new GregorianCalendar();
                                TimeZone mTimeZone = mCalendar.getTimeZone();
                                int mGMTOffset = mTimeZone.getRawOffset();

                                Time time = new Time((hour*3600000)+(minute*60000)-mGMTOffset);
                                sendResult(Activity.RESULT_OK,time);
                            }
                        })
                .create();
    }


    private void sendResult(int resultCode, Time time){
        if (getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode, intent);
    }
}
