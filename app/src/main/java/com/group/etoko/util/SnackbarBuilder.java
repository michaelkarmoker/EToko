package com.group.etoko.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.group.etoko.R;
import com.group.etoko.util.Exception.SnackbarException;

/**
 * Created By Michael Karmoker
 *
 */

public class SnackbarBuilder {
    private View layoutId;
    private String message;
    private View.OnClickListener onClickListener;
    private int length;
    private String buttonName;
    private Snackbar snackbar;
    private Context context;

    public SnackbarBuilder(Builder builder) {
        this.layoutId = builder.layoutId;
        this.message = builder.message;
        this.onClickListener = builder.onClickListener;
        this.length=builder.length;
        this.buttonName=builder.buttonName;
        this.context=builder.context;
    }

    public void show(){
        if (length==0){
            length=Snackbar.LENGTH_SHORT;
        }
        if (onClickListener==null){
            onClickListener= v -> snackbar.dismiss();
            buttonName="ok";
        }
        if (layoutId==null){
            throw new SnackbarException("Layout Id is Null. Solution :Give a root Layout Id");
        }

        if (message==null){
            throw new SnackbarException("No message Set For Snackbar");
        }

        snackbar=Snackbar.make(layoutId,message,length);
        snackbar.setAction(buttonName,onClickListener);
        snackbar.setTextColor(Color.WHITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setBackgroundTint(context.getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    public void hide(){
        if (snackbar != null){
            snackbar.dismiss();
        }else {
            throw new SnackbarException("SnackBar is Null");
        }
    }

    public static class Builder{
        private View layoutId=null;
        private String message=null;
        private View.OnClickListener onClickListener=null;
        private String buttonName=null;
        private int length=0;
        private Context context;


        public Builder(Context context){
            this.context=context;
        }

        public Builder setLayoutId(View view){
            this.layoutId=view;
            return this;
        }

        public Builder setMessage(String message){
            this.message=message;
            return this;
        }

        public Builder shortLength(){
            this.length=Snackbar.LENGTH_SHORT;
            return this;
        }

        public Builder longLength(){
            this.length=Snackbar.LENGTH_LONG;
            return this;
        }

        public Builder indifiniteLength(){
            this.length=Snackbar.LENGTH_INDEFINITE;
            return this;
        }

        public Builder setListener(String buttonName,View.OnClickListener onClickListener){
            this.onClickListener=onClickListener;
            this.buttonName=buttonName;
            return this;
        }

        public SnackbarBuilder build(){
            return new SnackbarBuilder(this);
        }
    }
}
