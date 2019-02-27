package com.romain.mathieu.mynews.model;

public class ReformatDate {

    public static String returnBetterDateTop(String betterDate) {

        betterDate = betterDate.replace("T", " - ");
        betterDate = betterDate.replace("-05:00", "");

        return betterDate;
    }

    public static String returnBetterDateSearch(String betterDate) {

        betterDate = betterDate.replace("T", " - ");
        betterDate = betterDate.replace("+0000", "");

        return betterDate;
    }
}
