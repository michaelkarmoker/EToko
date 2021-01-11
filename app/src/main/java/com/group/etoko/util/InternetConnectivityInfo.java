package com.group.etoko.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created By Michael Karmoker
 *
 */

public class InternetConnectivityInfo {
    private Context context;

    public InternetConnectivityInfo(Context context){
        this.context=context;
    }
    private NetworkInfo getNetworkInfo(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm != null) {
            return cm.getActiveNetworkInfo();
        }else {
            return null;
        }
    }

    public boolean isConnected(){
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected());
    }
}
