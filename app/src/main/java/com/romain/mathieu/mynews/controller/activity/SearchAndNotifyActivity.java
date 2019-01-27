package com.romain.mathieu.mynews.controller.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.model.CardData;
import com.romain.mathieu.mynews.view.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

import static com.romain.mathieu.mynews.utils.MyConstant.BUNDLED_EXTRA;
import static com.romain.mathieu.mynews.utils.MyConstant.NOTIF_ID;
import static com.romain.mathieu.mynews.utils.MyConstant.SEARCH_ID;


public class SearchAndNotifyActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.button_search)
    Button buttonSearch;
    @BindView(R.id.search_editText)
    EditText search_query;
    @BindView(R.id.begin_date_edittext)
    EditText datedebut;
    @BindView(R.id.end_date_edittext)
    EditText datefin;

    @BindView(R.id.notification_switch)
    Switch switchNotif;

    @BindView(R.id.checkBox_Art)
    CheckBox checkBox_Art;
    @BindView(R.id.checkBox_business)
    CheckBox checkBox_business;
    @BindView(R.id.checkBox_culture)
    CheckBox checkBox_culture;
    @BindView(R.id.checkBox_world)
    CheckBox checkBox_world;

    @BindView(R.id.checkBox_politics)
    CheckBox checkBox_Politics;
    @BindView(R.id.checkBox_science)
    CheckBox checkBox_science;
    @BindView(R.id.checkBox_technology)
    CheckBox checkBox_technology;
    @BindView(R.id.checkBox_movies)
    CheckBox checkBox_movies;

    private Calendar mCalendar;
    public static ArrayList<CardData> listSearch = new ArrayList<>();
    private MyAdapter adapter;
    private Disposable disposable;
    private String query, fquery;
    private String mBeginDate, mEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(SearchAndNotifyActivity.this);
        Stetho.initializeWithDefaults(this);

        setSupportActionBar(toolbar);


        this.configureLayout();

    }

    // ---------------------------------------------------------
    // CONFIGURE LAYOUT IF IT'S NOTIFICATION OR SEARCH WITH EXTRA AND SWITCH
    // ---------------------------------------------------------
    private void configureLayout() {
        String extraValue = getIntent().getStringExtra(BUNDLED_EXTRA);
        switch (extraValue) {
            case SEARCH_ID:
                setTitle(R.string.Search_title);
                this.editTextQueryTerm();
                this.resetBeginDate();
                this.resetEndDate();
                this.OnClickBeginDateListener();
                this.OnClickEndDateListener();
                switchNotif.setVisibility(View.GONE);
                break;
            case NOTIF_ID:
                setTitle(R.string.notif);
                buttonSearch.setVisibility(View.GONE);
                datedebut.setVisibility(View.GONE);
                datefin.setVisibility(View.GONE);
                break;
        }
    }

    // ---------------------------------------------------------
    // CHECKBOX IS CHECKED
    // ---------------------------------------------------------
    public void onCheckboxClicked(View view) {

        fquery = "news_desk:(";

        if (checkBox_Art.isChecked()) fquery = fquery + "\"Arts\"";
        if (checkBox_business.isChecked()) fquery = fquery + "\"Business\"";
        if (checkBox_culture.isChecked()) fquery = fquery + "\"Culture\"";
        if (checkBox_world.isChecked()) fquery = fquery + "\"World\"";
        if (checkBox_Politics.isChecked()) fquery = fquery + "\"Politics\"";
        if (checkBox_science.isChecked()) fquery = fquery + "\"Science\"";
        if (checkBox_technology.isChecked()) fquery = fquery + "\"Technology\"";
        if (checkBox_movies.isChecked()) fquery = fquery + "\"Movies\"";
        if (fquery.contains("\"\"")) fquery = fquery.replace("\"\"", "\" \"");
        fquery = fquery + ")";
    }

    private boolean isCheckBoxChecked() {
        return checkBox_Art.isChecked() ||
                checkBox_business.isChecked() ||
                checkBox_culture.isChecked() ||
                checkBox_world.isChecked() ||
                checkBox_Politics.isChecked() ||
                checkBox_science.isChecked() ||
                checkBox_technology.isChecked() ||
                checkBox_movies.isChecked();
    }

    // ---------------------------------------------------------
    // TEXT WATCHER ON THE EDIT TEXT
    // ---------------------------------------------------------
    private void editTextQueryTerm() {
        search_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query = s.toString().trim();
                // if all the conditions return true then the button becomes enable
                if (isQueryTermEditTextEmpty() && isCheckBoxChecked()) {
                    buttonSearch.setEnabled(true);
                } else buttonSearch.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private boolean isQueryTermEditTextEmpty() {
        return !query.isEmpty();
    }

    // ---------------------------------------------------------
    // ON CLICK BUTTON
    // ---------------------------------------------------------

    public void onClickButton(View view) {
        Intent myIntent = new Intent(SearchAndNotifyActivity.this, ResultSearch.class);
        myIntent.putExtra("QUERY", query); //Optional parameters
        myIntent.putExtra("FQUERY", fquery);
        myIntent.putExtra("DATE_DEBUT", mBeginDate);
        myIntent.putExtra("DATE_END", mEndDate);
        this.startActivity(myIntent);
    }

    // ---------------------------------------------------------
    // BEGIN AND END DATE : TextView Listener + DatePicker + Label update
    // ---------------------------------------------------------
    public void OnClickBeginDateListener() {
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

    public void OnClickEndDateListener() {
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

    public void updateBeginDateLabel() {
        String mFormat = "dd/MM/yyyy";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.US);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");

        datedebut.setText(simpleDateFormat.format(mCalendar.getTime()));
        mBeginDate = simpleDateFormat2.format(mCalendar.getTime());
    }

    public void updateEndDateLabel() {
        String mFormat = "dd/MM/yyyy";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.US);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");

        datefin.setText(simpleDateFormat.format(mCalendar.getTime()));
        mEndDate = simpleDateFormat2.format(mCalendar.getTime());
    }

    private void resetBeginDate() {
    }

    private void resetEndDate() {

    }

    // ---------------------------------------------------------
    // DESTROY
    // ---------------------------------------------------------

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}
