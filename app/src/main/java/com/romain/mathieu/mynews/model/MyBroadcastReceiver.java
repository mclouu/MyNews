package com.romain.mathieu.mynews.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.utils.MyConstant;
import com.romain.mathieu.mynews.utils.NotificationsUtils;
import com.romain.mathieu.mynews.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "NOTIFICATION_CHANNEL";
    String fqueryResult = "";
    String queryResult = "";
    int numberArticles = 0;
    private String messageNotif = "";
    private Disposable disposable;
    //    This method is executed at midnight every day

    @Override
    public void onReceive(Context context, Intent intent) {


        Log.e("TDBALARM", "Coucou");

        queryResult = SharedPreferencesUtils.getNotificationQuery(context);

        fqueryResult = "news_desk:(";

        if (SharedPreferencesUtils.getNotificationBoxArts(context))
            fqueryResult = fqueryResult + "\"Arts\"";
        if (SharedPreferencesUtils.getNotificationBoxBusiness(context))
            fqueryResult = fqueryResult + "\"Business\"";
        if (SharedPreferencesUtils.getNotificationBoxCulture(context))
            fqueryResult = fqueryResult + "\"Culture\"";
        if (SharedPreferencesUtils.getNotificationBoxWorlde(context))
            fqueryResult = fqueryResult + "\"World\"";
        if (SharedPreferencesUtils.getNotificationBoxPolitic(context))
            fqueryResult = fqueryResult + "\"Politics\"";
        if (SharedPreferencesUtils.getNotificationBoxScience(context))
            fqueryResult = fqueryResult + "\"Science\"";
        if (SharedPreferencesUtils.getNotificationBoxTechnologie(context))
            fqueryResult = fqueryResult + "\"Technology\"";
        if (SharedPreferencesUtils.getNotificationBoxMovies(context))
            fqueryResult = fqueryResult + "\"Movies\"";
        if (fqueryResult.contains("\"\"")) fqueryResult = fqueryResult.replace("\"\"", "\" \"");
        fqueryResult = fqueryResult + ")";

        Log.e("TDB query", queryResult);
        Log.e("TDB fquery", fqueryResult);

        this.executeHttpRequest(context);
//        createNotification(context, CHANNEL_ID);


    }

    private void executeHttpRequest(final Context context) {
        String query = queryResult;
        String fQuery = fqueryResult;
        String sort = "newest";
        this.disposable = NYTStreams
                .streamFetchNotif(MyConstant.API_KEY, query, fQuery, sort)
                .subscribeWith(new DisposableObserver<NYTAPIArticleSearch>() {
                    @Override
                    public void onNext(NYTAPIArticleSearch section) {
                        updateUIWithListOfArticle(section);
                        messageNotif = "Vous avez " + String.valueOf(section.getResponse().getDocs().size() + " articles correspondants à vos derniers critères. ");
                        send(context);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TDB", "error :(");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TDB", "Requete ok ;)");
                    }
                });
    }

    private void updateUIWithListOfArticle(NYTAPIArticleSearch response) {
        List articles = response.getResponse().getDocs();
        int nombreArticle = articles.size();
    }

    private void send(Context context) {
        NotificationsUtils notificationHelper = new NotificationsUtils(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("MyNews", messageNotif);
        notificationHelper.getManager().notify(1, nb.build());
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}
