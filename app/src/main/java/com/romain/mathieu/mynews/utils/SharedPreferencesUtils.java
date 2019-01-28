package com.romain.mathieu.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.romain.mathieu.mynews.utils.MyConstant.MY_FILE;

/**
 * Created by Romain on 06/02/2018.
 */

public class SharedPreferencesUtils {


    // ------------------------------
    // - SAVE AN PRIMITIVE VARIABLE -
    // ------------------------------

    static void removeNotification(Context context, String prefsName, String key) {
        SharedPreferences preferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static boolean containsNotification(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.contains(MyConstant.KEY_INT);
    }

    public static void SaveNotificationSwitch(Context context, boolean MyVariableBool) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_SWITCH, MyVariableBool);
        editor.apply();
    }

    public static boolean getNotificationSwitch(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_SWITCH, false);
    }

    public static void SaveNotificationQuery(Context context, String MyVariableString) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MyConstant.KEY_STRING, MyVariableString);
        editor.apply();
    }

    public static String getNotificationQuery(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MyConstant.KEY_STRING, "");
    }

    public static void SaveNotificationBoxArts(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_ARTS, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxArts(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_ARTS, false);
    }

    public static void SaveNotificationBoxBusiness(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_BUSINESS, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxBusiness(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_BUSINESS, false);
    }

    public static void SaveNotificationBoxCulture(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_CULTURE, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxCulture(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_CULTURE, false);
    }

    public static void SaveNotificationBoxWorld(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_WORLD, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxWorlde(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_WORLD, false);
    }


    public static void SaveNotificationBoxPolitic(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_POLITIC, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxPolitic(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_POLITIC, false);
    }

    public static void SaveNotificationBoxScience(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_SCIENCE, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxScience(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_SCIENCE, false);
    }

    public static void SaveNotificationBoxTechnologie(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_TECHNOLOGIE, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxTechnologie(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_TECHNOLOGIE, false);
    }

    public static void SaveNotificationBoxMovies(Context context, Boolean MyVariableBoolen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.KEY_BOOL_MOVIES, MyVariableBoolen);
        editor.apply();
    }

    public static boolean getNotificationBoxMovies(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.KEY_BOOL_MOVIES, false);
    }

    // ----------------------------
    // - SAVE AN OBJECT WITH GSON -
    // ----------------------------


//    static void saveArrayList(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(MyList);
//        editor.putString(KEY_LIST, json);
//        editor.apply();
//    }
//
//    public static void getArrayList(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString(KEY_LIST, null);
//        Type type = new TypeToken<ArrayList<MyClassList>>() {
//        }.getType();
//        MyList = gson.fromJson(json, type);
//    }


}
