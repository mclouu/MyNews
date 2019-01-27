package com.romain.mathieu.mynews.controller.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.model.CardData;
import com.romain.mathieu.mynews.model.NYTStreams;
import com.romain.mathieu.mynews.utils.MyConstant;
import com.romain.mathieu.mynews.utils.SharedPreferencesUtils;
import com.romain.mathieu.mynews.view.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.romain.mathieu.mynews.utils.MyConstant.BUNDLED_EXTRA;
import static com.romain.mathieu.mynews.utils.MyConstant.NOTIF_ID;
import static com.romain.mathieu.mynews.utils.MyConstant.SEARCH_ID;


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
                setTitle("Recherche");
                this.resetBeginDate();
                this.resetEndDate();
                this.OnClickBeginDateListener();
                this.OnClickEndDateListener();
                switchNotif.setVisibility(View.GONE);
                break;
            case NOTIF_ID:
                setTitle("Notifications");
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
                    this.notifyEmptySearchResultsError();
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

    // ---------------------------------------------------------
    // ON CLICK BUTTON
    // ---------------------------------------------------------

    public void onClickButton(View view) {
        Toast.makeText(this, "tdb", Toast.LENGTH_SHORT).show();
//        this.executeHttpReques();
        Intent myIntent = new Intent(SearchAndNotifyActivity.this, ResultSearch.class);
        myIntent.putExtra(BUNDLED_EXTRA, SEARCH_ID); //Optional parameters
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

    private void notifyEmptySearchResultsError() {

        // Build an AlertDialog for the Help section
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchAndNotifyActivity.this);
        // Set Title and Message content
        builder.setTitle("Error");
        builder.setMessage("No search results found, try again");
        // Neutral button
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    // ---------------------------------------------------------
    // HTTP (RxJAVA & Retrofit)
    // ---------------------------------------------------------

    private void executeHttpReques() {
        Disposable disposable = NYTStreams
                .streamFetchSearch(MyConstant.API_KEY, "google", "technology", "newest", "20180101", "20190101")
                .subscribeWith(new DisposableObserver<NYTAPIArticleSearch>() {
            @Override
            public void onNext(NYTAPIArticleSearch section) {
                Toast.makeText(SearchAndNotifyActivity.this, "onNext", Toast.LENGTH_SHORT).show();
                updateUIWithListOfArticle(section);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(SearchAndNotifyActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(SearchAndNotifyActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUIWithListOfArticle(NYTAPIArticleSearch response) {
        if (listSearch != null) {
            listSearch.clear();
        }

        int num_results = 10;
        for (int i = 0; i < num_results; i++) {
            //String section = response.getResponse().getDocs().get(i).getNewDesk();
            String section1 = "";
            String title = response.getResponse().getDocs().get(i).getSnippet();
            String imageURL;
            if (response.getResponse().getDocs().get(i).getMultimedia().isEmpty()) {
                imageURL = "https://image.noelshack.com/fichiers/2018/17/7/1524955130-empty-image-thumb2.png";
            } else {
                imageURL = response.getResponse().getDocs().get(i).getMultimedia().get(0).getUrl();
            }
            String articleURL = response.getResponse().getDocs().get(i).getWebUrl();
            String date = response.getResponse().getDocs().get(i).getPubDate();
            date = date.replace("T", " - ");
            listSearch.add(new CardData(
                    section1 + "",
                    title + "",
                    date + "",
                    "https://www.nytimes.com/" + imageURL,
                    articleURL + ""));
        }
//        adapter.notifyDataSetChanged();
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
