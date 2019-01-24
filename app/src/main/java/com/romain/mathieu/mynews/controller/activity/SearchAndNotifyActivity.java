package com.romain.mathieu.mynews.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;



public class SearchAndNotifyActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_editText)
    EditText search_word;
    @BindView(R.id.begin_date_edittext)
    EditText datedebut;
    @BindView(R.id.end_date_edittext)
    EditText datefin;

    @BindView(R.id.checkBox_Art)
    CheckBox checkBox_Art;
    @BindView(R.id.checkBox_business)
    CheckBox checkBox_business;
    @BindView(R.id.checkBox_entrepreneur)
    CheckBox checkBox_entrepreneur;

    @BindView(R.id.checkBox_politics)
    CheckBox checkBox_politics;
    @BindView(R.id.checkBox_sports)
    CheckBox checkBox_sports;
    @BindView(R.id.checkBox_travel)
    CheckBox checkBox_travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);


        setSupportActionBar(toolbar);
        setTitle("Search Article");
    }

    public void onCheckboxClicked(View view) {
    }
}
