package com.romain.mathieu.mynews.controller.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.model.CardData;
import com.romain.mathieu.mynews.model.MyBroadcastReceiver;
import com.romain.mathieu.mynews.utils.MyConstant;
import com.romain.mathieu.mynews.utils.SharedPreferencesUtils;
import com.romain.mathieu.mynews.view.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    @BindView(R.id.notification_switch)
    Switch switchNotif;

    @BindView(R.id.search_editText)
    EditText search_query;

    @BindView(R.id.begin_date_textview_label)
    TextView textViewBeginDate;
    @BindView(R.id.begin_date_edittext)
    EditText datedebut;
    @BindView(R.id.end_date_textview_label)
    TextView textViewEndDate;
    @BindView(R.id.end_date_edittext)
    EditText datefin;


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
    public static List<Boolean> listBooleanBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(SearchAndNotifyActivity.this);
        Stetho.initializeWithDefaults(this);

        setSupportActionBar(toolbar);


        this.configureLayout();

    }


    //---------------------------------------||
    // CONFIGURE LAYOUT IF IT'S NOTIFICATION ||
    //                  OR                   ||
    //    SEARCH WITH EXTRA AND SWITCH       ||
    //---------------------------------------||


    private void configureLayout() {


        String extraValue = getIntent().getStringExtra(BUNDLED_EXTRA);
        switch (extraValue) {
            case SEARCH_ID:
                setTitle(R.string.Search_title);
                this.OnClickBeginDateListener();
                this.OnClickEndDateListener();
                this.addListenerEditTextSearch();


                switchNotif.setVisibility(View.GONE);
                break;
            case NOTIF_ID:
                setTitle(R.string.notif);
                this.onCheckChangeListenerSwitch();
                this.addListenerEditTextSearch();
                this.getSharedPref();


                this.addListenerOnCheckBoxNotifActivity(checkBox_Art);
                this.addListenerOnCheckBoxNotifActivity(checkBox_business);
                this.addListenerOnCheckBoxNotifActivity(checkBox_culture);
                this.addListenerOnCheckBoxNotifActivity(checkBox_world);

                this.addListenerOnCheckBoxNotifActivity(checkBox_Politics);
                this.addListenerOnCheckBoxNotifActivity(checkBox_science);
                this.addListenerOnCheckBoxNotifActivity(checkBox_technology);
                this.addListenerOnCheckBoxNotifActivity(checkBox_movies);

                textViewBeginDate.setVisibility(View.GONE);
                textViewEndDate.setVisibility(View.GONE);
                buttonSearch.setVisibility(View.GONE);
                datedebut.setVisibility(View.GONE);
                datefin.setVisibility(View.GONE);
                break;
        }
    }

    //---------------------------------------||
    //               CHECKBOX                ||
    //                  IS                   ||
    //                CHECKED                ||
    //---------------------------------------||

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

    private boolean checkBoxChecked() {
        return checkBox_Art.isChecked() ||
                checkBox_business.isChecked() ||
                checkBox_culture.isChecked() ||
                checkBox_world.isChecked() ||
                checkBox_Politics.isChecked() ||
                checkBox_science.isChecked() ||
                checkBox_technology.isChecked() ||
                checkBox_movies.isChecked();
    }

    private boolean editTextIsEmpty() {

        String sUsername = search_query.getText().toString();

        return !sUsername.matches("");
    }

    private void addListenerOnCheckBoxNotifActivity(final CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                listBooleanBox = new ArrayList<>();
                listBooleanBox.add(checkBox_Art.isChecked());
                listBooleanBox.add(checkBox_business.isChecked());
                listBooleanBox.add(checkBox_culture.isChecked());
                listBooleanBox.add(checkBox_world.isChecked());

                listBooleanBox.add(checkBox_Politics.isChecked());
                listBooleanBox.add(checkBox_science.isChecked());
                listBooleanBox.add(checkBox_technology.isChecked());
                listBooleanBox.add(checkBox_movies.isChecked());

                SharedPreferencesUtils.saveArrayList(SearchAndNotifyActivity.this);
            }
        });
    }

    //---------------------------------------||
    //             TEXT WATCHER              ||
    //                  ON                   ||
    //               EDIT TEXT               ||
    //---------------------------------------||


    private void addListenerEditTextSearch() {
        search_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query = s.toString().trim();
                if (getIntent().getStringExtra(BUNDLED_EXTRA).equals(NOTIF_ID)) {
                    SharedPreferencesUtils.SaveNotificationQuery(SearchAndNotifyActivity.this, query);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    //------------------------------||
    //            ONCLICK           ||
    //            BUTTON            ||
    //                              ||
    //------------------------------||
    public void onClickButton(View view) {
        if (checkBoxChecked() & editTextIsEmpty()) {
            Intent myIntent = new Intent(SearchAndNotifyActivity.this, ResultSearch.class);
            myIntent.putExtra("QUERY", query); //Optional parameters
            myIntent.putExtra("FQUERY", fquery);
            myIntent.putExtra("DATE_DEBUT", mBeginDate); //Optional parameters
            myIntent.putExtra("DATE_END", mEndDate); //Optional parameters
            this.startActivity(myIntent);
        } else {
            Toast.makeText(this, getString(R.string.msg_categorie_is_not_checked), Toast.LENGTH_SHORT).show();
        }
        if (editTextIsEmpty()) {
            Log.e("TDB", "true");
        } else {
            Log.e("TDB", "false");
        }


    }


    //-----------------------------------||
    //         BEGIN AND END DATE        ||
    //      TextView Listener DatePicker ||
    //              Label update         ||
    //-----------------------------------||

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
        String mFormatDisplay = "dd/MM/yyyy";
        String mFormatRequest = "yyyyMMdd";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormatDisplay, Locale.US);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(mFormatRequest);

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

    //------------------------------||
    //         SWITCH VIEW          ||
    //           SAVE IN            ||
    //               +              ||
    //       SHAREDPREFERENCES      ||
    //------------------------------||

    public void onCheckChangeListenerSwitch() {
        switchNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alarmReceiver(SearchAndNotifyActivity.this);
                    SharedPreferencesUtils.saveNotificationSwitch(SearchAndNotifyActivity.this, true);
                    Toast.makeText(SearchAndNotifyActivity.this, getString(R.string.notif_on), Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferencesUtils.saveNotificationSwitch(SearchAndNotifyActivity.this, false);
                    Toast.makeText(SearchAndNotifyActivity.this, getString(R.string.notif_off), Toast.LENGTH_SHORT).show();
                    checkBox_Art.setChecked(false);
                    checkBox_business.setChecked(false);
                    checkBox_culture.setChecked(false);
                    checkBox_world.setChecked(false);

                    checkBox_Politics.setChecked(false);
                    checkBox_science.setChecked(false);
                    checkBox_technology.setChecked(false);
                    checkBox_movies.setChecked(false);
                    search_query.setText("");
                }
            }
        });
    }


    private void getSharedPref() {
        switchNotif.setChecked(SharedPreferencesUtils.getNotificationSwitch(SearchAndNotifyActivity.this));

        search_query.setText(SharedPreferencesUtils.getNotificationQuery(SearchAndNotifyActivity.this));


        // If in my sharedPreferences there is an arrayList of saved, then I recover the values
        if (SharedPreferencesUtils.containsArrayList(this)) {

            checkBox_Art.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(0));
            checkBox_business.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(1));
            checkBox_culture.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(2));
            checkBox_world.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(3));

            checkBox_Politics.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(4));
            checkBox_science.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(5));
            checkBox_technology.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(6));
            checkBox_movies.setChecked(SharedPreferencesUtils.getArrayList(SearchAndNotifyActivity.this).get(7));
        }

    }
    //------------------------------||
    //            ALARM             ||
    //           RECEIVER           ||
    //                              ||
    //------------------------------||

    private void alarmReceiver(Context context) {

        AlarmManager alarmManager;
        PendingIntent pendingIntent;
        MyConstant myConstant = new MyConstant();
        Calendar calendar = Calendar.getInstance();


        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, MyConstant.hour);
//        calendar.set(Calendar.MINUTE, myConstant.minutes);
        calendar.set(Calendar.SECOND, myConstant.secondes);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }
    //------------------------------||
    //            END               ||
    //          ACTIVITY            ||
    //                              ||
    //------------------------------||

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}
