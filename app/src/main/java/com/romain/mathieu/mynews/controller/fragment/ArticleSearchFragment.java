/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.controller.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.model.CardData;
import com.romain.mathieu.mynews.model.NYTStreams;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.view.MyAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleSearchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static ArrayList<CardData> list = new ArrayList<>();
    Context context;
    @BindView(R.id.articlesearch_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.articlesearch_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.articlesearch_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager llm;
    private MyAdapter adapter;
    private Disposable disposable;


    public ArticleSearchFragment() {
        // Required empty public constructor
    }

    public static ArticleSearchFragment newInstance() {
        return (new ArticleSearchFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articlesearch, container, false);
        context = container.getContext();

        ButterKnife.bind(this, view);
        Stetho.initializeWithDefaults(context);

        swipeRefreshLayout.setOnRefreshListener(this);

        llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);

        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);

        // 2 - Call the stream
        this.executeHttpRequestWithRetrofit();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    //-----------------------
    // PULL TO REFRESH
    //-----------------------

    @Override
    public void onRefresh() {
        progressBar.setVisibility(View.VISIBLE);
        this.executeHttpRequestWithRetrofit();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }

    //-----------------------
    //  HTTP (RxJAVA)
    //-----------------------

    // 1 - Execute our Stream
    private void executeHttpRequestWithRetrofit() {

        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NYTStreams.streamFetchArticle().subscribeWith(
                new DisposableObserver<NYTAPIArticleSearch>() {
                    @Override
                    public void onNext(NYTAPIArticleSearch section) {
                        Log.e("TAG", "onNext");

                        // 1.3 - Update UI with technology stories
                        updateUIWithListOfArticle(section);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "On Error \n" + Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "On Complete !!");
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUIWithListOfArticle(NYTAPIArticleSearch response) {

        if (list != null) {
            list.clear();
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
            list.add(new CardData(
                    section1 + "Technology",
                    title + "",
                    date + "",
                    "https://www.nytimes.com/" + imageURL,
                    articleURL + ""));
        }
        adapter.notifyDataSetChanged();
    }

}
