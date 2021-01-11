package com.group.etoko.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.group.etoko.util.Exception.SnackbarException;

/**
 * Created By Michael Karmoker
 *
 */

public class MySharedPreference {

    private static SharedPreferences sharedPreferences=null;
    private MySharedPreference(){

    }

    public static synchronized SharedPreferences getInstance(Context context){
        if (sharedPreferences==null){
            return sharedPreferences=context.getSharedPreferences("com.group.choloshop.v2.SharedPreference",Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static SharedPreferences.Editor editor(){
        if (sharedPreferences != null){
            return sharedPreferences.edit();
        }else {
            throw new SnackbarException("SharedPreferences Should Not be Null");
        }
    }
}
