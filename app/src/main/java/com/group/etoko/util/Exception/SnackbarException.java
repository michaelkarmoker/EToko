package com.group.etoko.util.Exception;

import androidx.annotation.Nullable;

/**
 * Created By Abdullah Jubayer
 *
 */

public class SnackbarException extends RuntimeException{
    private String message;

    public SnackbarException(String message){
        this.message=message;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}
