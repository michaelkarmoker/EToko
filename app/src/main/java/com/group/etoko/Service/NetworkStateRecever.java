package com.group.etoko.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.group.etoko.util.SnackbarBuilder;

public class NetworkStateRecever extends BroadcastReceiver {

    private Context context;
    private View view;
    public NetworkStateRecever(Context context, View view) {
        this.context=context;
        this.view=view;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {

        boolean isConnected=NetworkUtils.getConnectivityStatus(context);

        if (!isConnected){
            SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(this.context)
                    .setLayoutId(view)
                    .setMessage("You Don't have an Internet Connection! Turn On Internet Connection for Batter Experience")
                    .indifiniteLength()
                    .build();
            snackbarBuilder.show();
        }
    }
}
