package com.romain.mathieu.mynews.controller.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;



public class SearchAndNotifyActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.search_editText)
//    EditText search_word;
    @BindView(R.id.begin_date_edittext)
    EditText datedebut;
    @BindView(R.id.end_date_edittext)
    EditText datefin;
//
//    @BindView(R.id.checkBox_Art)
//    CheckBox checkBox_Art;
//    @BindView(R.id.checkBox_business)
//    CheckBox checkBox_business;
//    @BindView(R.id.checkBox_entrepreneur)
//    CheckBox checkBox_entrepreneur;
//
//    @BindView(R.id.checkBox_politics)
//    CheckBox checkBox_politics;
//    @BindView(R.id.checkBox_sports)
//    CheckBox checkBox_sports;
//    @BindView(R.id.checkBox_travel)
//    CheckBox checkBox_travel;

    private Calendar mCalendar;
    private String varDatedebut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(SearchAndNotifyActivity.this);
        Stetho.initializeWithDefaults(this);


        setSupportActionBar(toolbar);
        setTitle("Search Article");

        this.resetBeginDate();
        this.resetEndDate();
        this.OnClickBeginDateListener();
        this.OnClickEndDateListener();
    }

    public void onCheckboxClicked(View view) {
    }



    // ---------------------------------------------------------
    // BEGIN AND END DATE : TextView Listener + DatePicker + Label update
    // ---------------------------------------------------------
    public void OnClickBeginDateListener(){
        mCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateBeginDateLabel();
            }
        };
        datedebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchAndNotifyActivity.this,
                        date,
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void OnClickEndDateListener(){
        mCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndDateLabel();
            }
        };
        datefin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchAndNotifyActivity.this,
                        date,
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void updateBeginDateLabel(){
        String mFormat = "dd/MM/yyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.US);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        datedebut.setText(simpleDateFormat.format(mCalendar.getTime()));
        varDatedebut = simpleDateFormat2.format(mCalendar.getTime());
    }
    public void updateEndDateLabel(){
        String mFormat = "dd/MM/yyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.US);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        datefin.setText(simpleDateFormat.format(mCalendar.getTime()));
        varDatedebut = simpleDateFormat2.format(mCalendar.getTime());
    }
    private void resetBeginDate() {
        varDatedebut = null;
    }
    private void resetEndDate() {
        varDatedebut = null;
    }
}
