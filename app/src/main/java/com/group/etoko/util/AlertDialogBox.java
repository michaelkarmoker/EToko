package com.group.etoko.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created By Michael Karmoker
 *
 */
public class AlertDialogBox {
    public static void Box(Context context,String titleText,String messText){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(titleText);
        alertDialog.setMessage(messText);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
