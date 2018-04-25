/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Controller.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.Model.API.MostPopular.NYTAPI;
import com.romain.mathieu.mynews.Model.CardData;
import com.romain.mathieu.mynews.Model.NYTService;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.View.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoriesPageFragment extends Fragment {

    public static List<NYTAPI> responseBody;
    @BindView(R.id.top_stories_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.top_stories_progressBar)
    ProgressBar progressBar;
    Context context;
    private LinearLayoutManager llm;
    private MyAdapter adapter;


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

        getRetrofit();

        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);


        // Inflate the layout for this fragment

        return view;


    }


//    -----------------------
//     HTTP REQUEST RETROFIT
//    -----------------------

    public void getRetrofit() {


        RetrofitRequest.retrofitRequest();

        NYTService service = RetrofitRequest.retrofit.create(NYTService.class);
        //Call<List<NYTAPI>> call = service.getPostInfo(MyConstant.GET_SECTION(5));
        //Call<List<NYTAPI>> call = service.getPostInfo("world");
        Call<List<NYTAPI>> call = service.getPostInfo();


        call.enqueue(new Callback<List<NYTAPI>>() {
            @Override
            public void onResponse(Call<List<NYTAPI>> call, Response<List<NYTAPI>> response) {
                responseBody = response.body();
                progressBar.setVisibility(View.GONE);

                Log.e("RETROFIT", "OK " + responseBody);

//                for (int i = 0; i < mListPost.size(); i++) {
//                    list.add(new CardData(mListPost.get(i).getResults().get(i).getTitle(), mListPost.get(i).getResults().get(i).getAbstract()));
//                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<NYTAPI>> call, @NonNull Throwable t) {
                Toast.makeText(context, R.string.error_network, Toast.LENGTH_LONG).show();
                Log.e("RETROFIT", "Network error");
            }
        });

    }
}
