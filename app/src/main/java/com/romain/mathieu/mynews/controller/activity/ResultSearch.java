package com.romain.mathieu.mynews.controller.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.model.CardData;
import com.romain.mathieu.mynews.model.NYTStreams;
import com.romain.mathieu.mynews.model.ReformatDate;
import com.romain.mathieu.mynews.utils.MyConstant;
import com.romain.mathieu.mynews.view.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ResultSearch extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    public static ArrayList<CardData> listResut = new ArrayList<>();
    MyAdapter adapter;
    LinearLayoutManager llm;
    String queryResult = "", fqueryResult = "", dateDebut = "", dateFin = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_viewpager);
        ButterKnife.bind(ResultSearch.this);

        swipeRefreshLayout.setOnRefreshListener(this);

        llm = new LinearLayoutManager(ResultSearch.this);
        recyclerView.setLayoutManager(llm);

        adapter = new MyAdapter(listResut);
        recyclerView.setAdapter(adapter);

        queryResult = getIntent().getStringExtra("QUERY");

        fqueryResult = getIntent().getStringExtra("FQUERY");
        fqueryResult = fqueryResult.trim();

        dateDebut = getIntent().getStringExtra("DATE_DEBUT");
        dateFin = getIntent().getStringExtra("DATE_END");

        this.executeHttpRequest();

    }

    @Override
    public void onRefresh() {
        progressBar.setVisibility(View.VISIBLE);
        this.executeHttpRequest();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }

    // ---------------------------------------------------------
    // HTTP (RxJAVA & Retrofit)
    // ---------------------------------------------------------

    private void executeHttpRequest() {
        String query = queryResult;
        String fQuery = fqueryResult;
        String sort = "newest";
        String beginDate = dateDebut;
        String endDate = dateFin;
        Disposable disposable = NYTStreams
                .streamFetchSearch(MyConstant.API_KEY, query, fQuery, sort, beginDate, endDate)
                .subscribeWith(new DisposableObserver<NYTAPIArticleSearch>() {
                    @Override
                    public void onNext(NYTAPIArticleSearch section) {
                        List articles = section.getResponse().getDocs();
                        if (articles.size() == 0) {
                            emptySearchResultsError();
                        } else {
                            updateUIWithListOfArticle(section);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ResultSearch.this, getString(R.string.error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void updateUIWithListOfArticle(NYTAPIArticleSearch response) {
        if (listResut != null) {
            listResut.clear();
        }

        List articles = response.getResponse().getDocs();
        for (int i = 0; i < articles.size(); i++) {
            String section = response.getResponse().getDocs().get(i).getNewsDesk();
            String title = response.getResponse().getDocs().get(i).getSnippet();
            String imageURL;
            if (response.getResponse().getDocs().get(i).getMultimedia().isEmpty()) {
                imageURL = "https://image.noelshack.com/fichiers/2018/17/7/1524955130-empty-image-thumb2.png";
            } else {
                imageURL = response.getResponse().getDocs().get(i).getMultimedia().get(0).getUrl();
            }
            String articleURL = response.getResponse().getDocs().get(i).getWebUrl();
            String date = response.getResponse().getDocs().get(i).getPubDate();
            listResut.add(new CardData(
                    section + "",
                    title + "",
                    ReformatDate.returnBetterDateSearch(date) + "",
                    "https://www.nytimes.com/" + imageURL,
                    articleURL + ""));
        }

        adapter.notifyDataSetChanged();
    }

    private void emptySearchResultsError() {

        // Build an AlertDialog for the Help section
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultSearch.this);
        // Set Title and Message content
        builder.setTitle(R.string.Error);
        builder.setMessage(R.string.noResults);
        // Neutral button
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });

        builder.show();

    }
}
