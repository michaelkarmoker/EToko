package com.group.etoko.util;

import android.content.Context;
import android.content.DialogInterface;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.group.etoko.util.Exception.SnackbarException;

public class MetarialDialog {

    private MaterialAlertDialogBuilder alertDialogBuilder;
    private Context context;
    private String title;
    private String message;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;
    private String positiveButton;
    private String negativeButton;

    public MetarialDialog(Builder builder) {
        this.context=builder.context;
      this.title=builder.title;
      this.message=builder.message;
      this.positiveListener=builder.positiveListener;
      this.negativeListener=builder.negativeListener;
      this.positiveButton=builder.positiveButton;
      this.negativeButton=builder.negativeButton;
    }

    public void show(){

        if (context==null){
            throw new SnackbarException("No Context Set For AlertDialog");
        }

        if (title==null){
            throw new SnackbarException("No Title Set For AlertDialog");
        }

        if (message==null){
            throw new SnackbarException("No message Set For AlertDialog");
        }

        if (positiveListener==null){
            throw new SnackbarException("No Positive Button Set For AlertDialog");
        }

        alertDialogBuilder=new MaterialAlertDialogBuilder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(positiveButton,positiveListener);
        if (negativeListener != null){
            alertDialogBuilder.setNeutralButton(negativeButton,negativeListener);
        }
        alertDialogBuilder.show();
    }

    public static class Builder{
        private Context context=null;
        private String title=null;
        private String message=null;
        private DialogInterface.OnClickListener positiveListener=null;
        private DialogInterface.OnClickListener negativeListener=null;
        private String positiveButton=null;
        private String negativeButton=null;

        public Builder(Context context){
            this.context=context;
        }

        public Builder setTitle(String title){
            this.title=title;
            return this;
        }

        public Builder setMessage(String message){
            this.message=message;
            return this;
        }

        public Builder setPositiveListener(String buttonName,DialogInterface.OnClickListener positiveListener){
            this.positiveListener=positiveListener;
            this.positiveButton=buttonName;
            return this;
        }

        public Builder setNegativeListener(String buttonName,DialogInterface.OnClickListener negativeListener){
            this.negativeListener=negativeListener;
            this.negativeButton=buttonName;
            return this;
        }

        public MetarialDialog build(){
            return new MetarialDialog(this);
        }
    }
}
