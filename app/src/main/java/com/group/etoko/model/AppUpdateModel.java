package com.group.etoko.model;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;

/**
 * Created By Michael Karmoker
 *
 */

public class AppUpdateModel {
    private Boolean isUpdate;
    private AppUpdateInfo appUpdateInfo;
    private AppUpdateManager appUpdateManager;

    public AppUpdateModel(Boolean isUpdate,AppUpdateManager appUpdateManager, AppUpdateInfo appUpdateInfo) {
        this.isUpdate = isUpdate;
        this.appUpdateInfo = appUpdateInfo;
        this.appUpdateManager=appUpdateManager;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public AppUpdateInfo getAppUpdateInfo() {
        return appUpdateInfo;
    }

    public AppUpdateManager getAppUpdateManager() {
        return appUpdateManager;
    }
}
