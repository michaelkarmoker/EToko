package com.group.etoko.Activity.splashactivity.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import com.google.android.play.core.install.model.AppUpdateType;
import com.group.etoko.Activity.main_activity.ui.MainActivity;
import com.group.etoko.Activity.splashactivity.viewmodel.SplashActivityViewModel;
import com.group.etoko.databinding.ActivitySplashBinding;
import com.group.etoko.model.AppUpdateModel;
import com.group.etoko.model.NetworkStatusModel;
import com.group.etoko.util.SnackbarBuilder;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private SplashActivityViewModel  viewModel;
    private int APP_UPDATE_RESULT=505;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider(this).get(SplashActivityViewModel.class);
        viewModel.checkInternetConnectivity(this);
        viewModel.checkAppVersion(this);

        binding.progress.startProgress();


        observer();
    }

    private void gotoHomeScreen() {
        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void observer() {
        //observe Network Connectivity
        viewModel.getInternetConnectivityInfo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean connected) {
                if (!connected){
                    SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(SplashActivity.this)
                            .setLayoutId(binding.rootLayout)
                            .setMessage("No Internet Connection")
                            .indifiniteLength()
                            .build();
                    snackbarBuilder.show();
                    gotoHomeScreen();
                }
            }
        });

        //observe App Update
        viewModel.isUpdateAvailable().observe(this, new Observer<AppUpdateModel>() {
            @Override
            public void onChanged(AppUpdateModel appUpdateModel) {
                if (appUpdateModel.getUpdate()){
                    try {
                        appUpdateModel.getAppUpdateManager().startUpdateFlowForResult(appUpdateModel.getAppUpdateInfo(),
                                AppUpdateType.IMMEDIATE,SplashActivity.this,APP_UPDATE_RESULT);
                    } catch (IntentSender.SendIntentException e) {
                        SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(SplashActivity.this)
                                .setLayoutId(binding.rootLayout)
                                .setMessage("Error:"+e.getMessage())
                                .indifiniteLength()
                                .build();
                        snackbarBuilder.show();
                    }
                }
            }
        });

        //Observe NetworkCall Status
        viewModel.getNetworkStatusModelLiveData().observe(this, new Observer<NetworkStatusModel>() {
            @Override
            public void onChanged(NetworkStatusModel networkStatusModel) {
                if (networkStatusModel.getStatus()){
                    gotoHomeScreen();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_UPDATE_RESULT) {
            if (resultCode != RESULT_OK) {
                SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(SplashActivity.this)
                        .setLayoutId(binding.rootLayout)
                        .setMessage("Update Not Complete")
                        .indifiniteLength()
                        .build();
                snackbarBuilder.show();
            }
        }
    }
}