/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Controller.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.Model.CardData;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.View.MyAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class TopStoriesPageFragment extends Fragment {

    @BindView(R.id.top_stories_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.top_stories_progressBar)
    ProgressBar progressBar;
    Context context;
    private LinearLayoutManager llm;
    private MyAdapter adapter;
    private Disposable disposable;

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


        llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);

        ArrayList<CardData> list = new ArrayList<>();

        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);


        streamShowString();

        return view;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    //-----------------------
    // RX JAVA
    //-----------------------

    // 1 - Create Observable
    private Observable<String> getObservable() {
        return Observable.just("Cool !");
    }

    // 2 - Create Subscriber
    private DisposableObserver<String> getSubcriber() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    // 3 - Create Stream and execute it
    private void streamShowString() {
        this.disposable = this.getObservable()
                .subscribeWith(getSubcriber());
    }

    // 5 - Dispose subscription
    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

//    -----------------------
//     HTTP REQUEST RETROFIT
//    -----------------------
}
