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
import com.romain.mathieu.mynews.model.API.TopStories.NYTAPITopstories;
import com.romain.mathieu.mynews.model.CardData;
import com.romain.mathieu.mynews.model.MyConstant;
import com.romain.mathieu.mynews.model.NYTStreams;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.view.MyAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class TopStoriesPageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    public static ArrayList<CardData> list = new ArrayList<>();
    Context context;
    @BindView(R.id.top_stories_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.top_stories_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager llm;
    private MyAdapter adapter;
    private Disposable disposable;
    MyConstant constant = new MyConstant();

    public TopStoriesPageFragment() {
        // Required empty public constructor
    }

    public static TopStoriesPageFragment newInstance() {
        return (new TopStoriesPageFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_stories_page, container, false);
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
            // To change the section, change the constant value => GET_SECTION_TOP(n) (0 = world [...] 25 = home)
        this.disposable = NYTStreams.streamFetchTop("home").subscribeWith(
        //this.disposable = NYTStreams.streamFetchTop(constant.GET_SECTION_TOP(0)).subscribeWith(
                new DisposableObserver<NYTAPITopstories>() {
                    @Override
                    public void onNext(NYTAPITopstories section) {
                        Log.e("TAG", "onNext");
                        // 1.3 - Update UI with topstories
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
    private void updateUIWithListOfArticle(NYTAPITopstories response) {

        if (list != null) {
            list.clear();
        }

        int num_results = response.getNumResults();
        for (int i = 0; i < num_results; i++) {
            String section1 = response.getSection();
            String section2 = response.getResults().get(i).getSection();
            String subTitle = response.getResults().get(i).getAbstract();
            String imageURL;
            if (response.getResults().get(i).getMultimedia().isEmpty()) {
                imageURL = "https://image.noelshack.com/fichiers/2018/17/7/1524955130-empty-image-thumb2.png";
            } else {
                imageURL = response.getResults().get(i).getMultimedia().get(4).getUrl();
            }
            String articleURL = response.getResults().get(i).getUrl();
            String date = response.getResults().get(i).getCreatedDate();
            date = date.replace("T", " - ");

            list.add(new CardData(
                    section1 + " > " + section2,
                    subTitle + "",
                    date + "",
                    imageURL + "",
                    articleURL + ""));
        }
        adapter.notifyDataSetChanged();
    }
}