/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.romain.mathieu.mynews.model.API.ArticleSearch.DocsItem;
import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.model.NYTStreams;
import com.romain.mathieu.mynews.utils.MyConstant;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ArticleSearchInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.romain.mathieu.mynews", appContext.getPackageName());
    }

    @Test

    public void ArticleSearchReturnResultsTest() throws Exception {
        String apiKey = "603VoqkXe4T0cL2iwBnuUndaTW7vBz5G";
        int fQuery = 5;
        String sort = "newest";
        //1 - Get the stream
        Observable<NYTAPIArticleSearch> observableArticleSearch = NYTStreams.streamFetchArticle(apiKey, MyConstant.getSectionTop(fQuery), sort);

        //2 - Create a new TestObserver
        TestObserver<NYTAPIArticleSearch> testObserver = new TestObserver<>();

        //3 - Launch observable
        observableArticleSearch.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

        //4 - Get list of articles
        List<DocsItem> articles = testObserver.values().get(0).getResponse().getDocs();

        //5 - Verify if TopStories n°0 has a title
        assertThat("Articles are returned", !articles.get(0).getSnippet().equals(""));
    }
}
