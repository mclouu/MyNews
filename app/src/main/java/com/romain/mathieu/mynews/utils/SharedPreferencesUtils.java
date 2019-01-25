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


    public static void SaveNotification(Context context, int MyVariableInt) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MyConstant.KEY_INT, MyVariableInt);
        editor.apply();
    }

    public static int getNotification(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(MyConstant.KEY_INT, 0);
    }

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
