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
import com.romain.mathieu.mynews.utils.SharedPreferencesUtils;
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
    @BindView(R.id.checkBox_entrepreneur)
    CheckBox checkBox_entrepreneur;

    @BindView(R.id.checkBox_politics)
    CheckBox checkBox_politics;
    @BindView(R.id.checkBox_sports)
    CheckBox checkBox_sports;
    @BindView(R.id.checkBox_travel)
    CheckBox checkBox_travel;

    private Calendar mCalendar;
    public static ArrayList<CardData> listSearch = new ArrayList<>();
    private MyAdapter adapter;
    private Disposable disposable;
    private String query, newsDesk;

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

        boolean checked = ((CheckBox) view).isChecked();

        int checkBoxArt;
        int checkBoxBusiness;
        int checkBoxEntrepreneur;

        int checkBoxPolitics;
        int checkBoxSports;
        int checkBoxTravel;
        switch (view.getId()) {
            case R.id.checkBox_Art:
                if (checked) {
                    checkBoxArt = 1;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxArt);
                } else {
                    checkBoxArt = 0;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxArt);
                }
                break;
            case R.id.checkBox_business:
                if (checked) {
                    checkBoxBusiness = 1;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxBusiness);
                } else {
                    checkBoxBusiness = 0;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxBusiness);
                }
                break;
            case R.id.checkBox_entrepreneur:
                if (checked) {
                    checkBoxEntrepreneur = 1;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxEntrepreneur);
                } else {
                    checkBoxEntrepreneur = 0;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxEntrepreneur);
                }
                break;
            case R.id.checkBox_politics:
                if (checked) {
                    checkBoxPolitics = 1;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxPolitics);
                } else {
                    checkBoxPolitics = 0;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxPolitics);
                }
                break;
            case R.id.checkBox_sports:
                if (checked) {
                    checkBoxSports = 1;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxSports);
                } else {
                    checkBoxSports = 0;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxSports);
                }
                break;
            case R.id.checkBox_travel:
                if (checked) {
                    checkBoxTravel = 1;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxTravel);
                } else {
                    checkBoxTravel = 0;
                    SharedPreferencesUtils.SaveNotification(SearchAndNotifyActivity.this, checkBoxTravel);
                }
                break;
        }
    }

    private boolean isCheckBoxChecked() {
        return checkBox_Art.isChecked() ||
                checkBox_business.isChecked() ||
                checkBox_entrepreneur.isChecked() ||
                checkBox_politics.isChecked() ||
                checkBox_sports.isChecked() ||
                checkBox_travel.isChecked();
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
                if (isQueryTermEditTextEmpty()) {
//                    if (isQueryTermEditTextEmpty() && isCheckBoxChecked() && isDatesCorrect()) {
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
        myIntent.putExtra("QUERY_TERM", query); //Optional parameters
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
        String mFormat = "dd/MM/yyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.US);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        datedebut.setText(simpleDateFormat.format(mCalendar.getTime()));
    }

    public void updateEndDateLabel() {
        String mFormat = "dd/MM/yyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.US);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        datefin.setText(simpleDateFormat.format(mCalendar.getTime()));
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
