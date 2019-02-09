/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews;

import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.model.API.MostPopular.NYTAPIMostPopular;
import com.romain.mathieu.mynews.model.API.TopStories.NYTAPITopstories;
import com.romain.mathieu.mynews.model.NYTStreams;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.MatcherAssert.assertThat;

public class StreamFetchUnitTest {

    private String apiKey = "603VoqkXe4T0cL2iwBnuUndaTW7vBz5G";

    @BeforeClass
    public static void setupClass() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                new Function<Callable<Scheduler>, Scheduler>() {
                    @Override
                    public Scheduler apply(Callable<Scheduler> __) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
    }

    @Test
    public void streamFetchTopTest() {
        // 1 - Get the stream
        Observable<NYTAPITopstories> observableTopStories = NYTStreams.streamFetchTop("home", apiKey);
        // 2 - Create new TestObserver
        TestObserver<NYTAPITopstories> testObserver = new TestObserver<>();
        // 3 - Launch Observable
        observableTopStories.subscribeWith(testObserver)
                .assertNoErrors()       // 3.1 - Check if no errors
                .assertNoTimeout()      // 3.2 - Check if no Timeout
                .awaitTerminalEvent();  // 3.3 - Await the stream terminated before continue
        // 4 - Get list of user fetched
        NYTAPITopstories articlesFetched = testObserver.values().get(0);
        // 5 - Verify if
        assertThat("The number of articles returned is ", articlesFetched.getResults().size() != 0);
    }

    @Test
    public void streamFetchMostTest() {
        // 1 - Get the stream
        Observable<NYTAPIMostPopular> observableMostPopular = NYTStreams.streamFetchMost("food", apiKey);
        // 2 - Create new TestObserver
        TestObserver<NYTAPIMostPopular> testObserver = new TestObserver<>();
        // 3 - Launch Observable
        observableMostPopular.subscribeWith(testObserver)
                .assertNoErrors()       // 3.1 - Check if no errors
                .assertNoTimeout()      // 3.2 - Check if no Timeout
                .awaitTerminalEvent();  // 3.3 - Await the stream terminated before continue
        // 4 - Get list of user fetched
        NYTAPIMostPopular articlesFetched = testObserver.values().get(0);
        // 5 - Verify if
        assertThat("The number of articles returned is ", articlesFetched.getResults().size() != 0);
    }

    @Test
    public void streamFetchArticleTest() {
        // 1 - Get the stream
        Observable<NYTAPIArticleSearch> observableArticleSearch = NYTStreams.streamFetchArticle(apiKey, "home", "newest");
        // 2 - Create new TestObserver
        TestObserver<NYTAPIArticleSearch> testObserver = new TestObserver<>();
        // 3 - Launch Observable
        observableArticleSearch.subscribeWith(testObserver)
                .assertNoErrors()       // 3.1 - Check if no errors
                .assertNoTimeout()      // 3.2 - Check if no Timeout
                .awaitTerminalEvent();  // 3.3 - Await the stream terminated before continue
        // 4 - Get list of user fetched
        NYTAPIArticleSearch articlesFetched = testObserver.values().get(0);
        // 5 - Verify if
        assertThat("The number of articles returned is ", articlesFetched.getResponse().getDocs().size() != 0);
    }

}
