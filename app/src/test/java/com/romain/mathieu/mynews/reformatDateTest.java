package com.romain.mathieu.mynews;

import com.romain.mathieu.mynews.model.ReformatDate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class reformatDateTest {

    private ReformatDate reformatDate;


    @Before
    public void setUp() {
        reformatDate = new ReformatDate();
    }

    @Test
    public void returnBetterDateTopTest() {

        assertEquals("2019-02-26 - 20:50:45", reformatDate.returnBetterDateTop("2019-02-26T20:50:45-05:00"));
    }


    @Test
    public void returnBetterDateSearchTest() {

        assertEquals("2019-02-27 - 17:21:57", reformatDate.returnBetterDateSearch("2019-02-27T17:21:57+0000"));
    }
}