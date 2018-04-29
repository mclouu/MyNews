/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romain.mathieu.mynews.Controller.Activity.WebViewActivity;
import com.romain.mathieu.mynews.Model.CardData;
import com.romain.mathieu.mynews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ArticleViewHolder> {

    private Context context;
    private ArrayList<CardData> mdatas;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public MyAdapter(ArrayList<CardData> mlist) {
        this.mdatas = mlist;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        final CardData object = mdatas.get(position);


        holder.title.setText(Html.fromHtml(object.getTitle()));
        holder.subtitle.setText(Html.fromHtml(object.getSubtitle()));
        holder.date.setText(Html.fromHtml(object.getDate()));

        String url = object.getImageURL();

        Picasso.get()
                .load(url)
                .centerCrop()
                .resize(100, 100)
                .placeholder(R.drawable.imagedownloading)
                .error(R.drawable.imageempty)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mdatas != null) {
            return mdatas.size();
        }
        return 0;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subTitle)
        TextView subtitle;
        @BindView(R.id.date)
        TextView date;


        ArticleViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        @OnClick(R.id.relativeLayout)
        void submit(View view) {
            int position = getAdapterPosition();

            Intent intent = new Intent(context, WebViewActivity.class);
            final CardData object = mdatas.get(position);
            intent.putExtra("urlArticle", object.getArticleURL());
            context.startActivity(intent);
        }
    }
}
