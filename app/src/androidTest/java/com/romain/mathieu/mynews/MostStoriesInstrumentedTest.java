/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.romain.mathieu.mynews.model.API.MostPopular.NYTAPIMostPopular;
import com.romain.mathieu.mynews.model.API.MostPopular.ResultsItem;
import com.romain.mathieu.mynews.model.API.TopStories.NYTAPITopstories;
import com.romain.mathieu.mynews.model.API.TopStories.Result;
import com.romain.mathieu.mynews.model.NYTStreams;

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
public class MostStoriesInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.romain.mathieu.mynews", appContext.getPackageName());
    }

    @Test

    public void MostStoriesReturnResultsTest() throws Exception {
        String section = "magazine";
        String apiKey = "603VoqkXe4T0cL2iwBnuUndaTW7vBz5G";
        //1 - Get the stream
        Observable<NYTAPIMostPopular> observableMostStories = NYTStreams.streamFetchMost(section, apiKey);

        //2 - Create a new TestObserver
        TestObserver<NYTAPIMostPopular> testObserver = new TestObserver<>();

        //3 - Launch observable
        observableMostStories.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

        //4 - Get list of articles
        List<ResultsItem> articles = testObserver.values().get(0).getResults();

        //5 - Verify if TopStories n°0 has a title
        assertThat("Articles are returned", !articles.get(0).getTitle().equals(""));
    }
}
