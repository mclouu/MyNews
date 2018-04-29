/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Controller.Fragment;


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
import com.romain.mathieu.mynews.Model.API.MostPopular.NYTAPIMostPopular;
import com.romain.mathieu.mynews.Model.CardData;
import com.romain.mathieu.mynews.Model.NYTStreams;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.View.MyAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static ArrayList<CardData> list = new ArrayList<>();
    Context context;
    @BindView(R.id.most_popular_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.most_popular_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.top_stories_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager llm;
    private MyAdapter adapter;
    private Disposable disposable;

    public MostPopularFragment() {
        // Required empty public constructor
    }

    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);

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
        // 1.1 - Update UI
        this.updateUIWhenStartingHTTPRequest();

        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NYTStreams.streamFetchMost("all-sections").subscribeWith(
                new DisposableObserver<NYTAPIMostPopular>() {
                    @Override
                    public void onNext(NYTAPIMostPopular section) {
                        Log.e("TAG", "onNext");
                        // 1.3 - Update UI with topstories
                        updateUIWithListOfArticle(section);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        Log.e("tdb", "On Error \n" + Log.getStackTraceString(e));
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

    private void updateUIWhenStartingHTTPRequest() {
        //this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response) {
        //this.textView.setText(response);
    }

    private void updateUIWithListOfArticle(NYTAPIMostPopular response) {

        if (list != null) {
            list.clear();
        }

        int num_results = 19;
        for (int i = 0; i < num_results; i++) {
            String section1 = response.getResults().get(i).getSection();
            String title = response.getResults().get(i).getTitle();
            String imageURL;
            if (response.getResults().get(i).getMedia().isEmpty()) {
                imageURL = "https://image.noelshack.com/fichiers/2018/17/7/1524955130-empty-image-thumb2.png";
            } else {
                imageURL = response.getResults().get(i).getMedia().get(0).getMediaMetadata().get(0).getUrl();
            }
            String articleURL = response.getResults().get(i).getUrl();
            String date = response.getResults().get(i).getPublishedDate();

            list.add(new CardData(
                    section1 + "",
                    title + "",
                    date + "",
                    imageURL + "",
                    articleURL + ""));
        }
        adapter.notifyDataSetChanged();
    }
}
