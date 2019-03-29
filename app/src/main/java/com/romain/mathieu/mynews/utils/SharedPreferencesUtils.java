package com.romain.mathieu.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.romain.mathieu.mynews.controller.activity.SearchAndNotifyActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.romain.mathieu.mynews.utils.MyConstant.KEY_LIST;
import static com.romain.mathieu.mynews.utils.MyConstant.MY_FILE;

/**
 * Created by Romain on 06/02/2018.
 */

public class SharedPreferencesUtils {


    // ------------------------------
    // - SAVE AN PRIMITIVE VARIABLE -
    // ------------------------------

    public static void saveNotificationSwitch(Context context, boolean MyVariableBool) {
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

    // ----------------------------
    // - SAVE AN OBJECT WITH GSON -
    // ----------------------------


    public static void saveArrayList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(SearchAndNotifyActivity.listBooleanBox);
        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    public static ArrayList<Boolean> getArrayList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, null);
        Type type = new TypeToken<ArrayList<Boolean>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static boolean containsArrayList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_LIST);
    }


}
