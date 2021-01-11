package com.group.etoko.Activity.splashactivity.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.group.etoko.Activity.splashactivity.repository.SplashActivityRepository;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.model.AppUpdateModel;
import com.group.etoko.model.NetworkStatusModel;
import com.group.etoko.util.InternetConnectivityInfo;

public class SplashActivityViewModel extends AndroidViewModel implements SplashViewModel_View{

    private SplashActivityRepository repository;
    private InternetConnectivityInfo internetConnectivityInfo;
    private MutableLiveData<Boolean> isInternetOn=new MutableLiveData<>();
    private MutableLiveData<AppUpdateModel>isUpdateAvailable=new MutableLiveData<>();
    private MutableLiveData<NetworkStatusModel>networkStatusModelLiveData=new MutableLiveData<>();
    private ProductDao productDao;
    private CartDao cartDao;

    private NetworkStatusModel networkStatusModel;

    public SplashActivityViewModel(@NonNull Application application) {
        super(application);

        LocalDatabase database=LocalDatabase.getReferences(application);
        productDao=database.productDao();
        cartDao=database.cartDao();


        networkStatusModel=new NetworkStatusModel();
        networkStatusModel.setProductStore(false);
        networkStatusModel.setProductQtyUpdated(false);

        repository=new SplashActivityRepository(application);
        repository.viewModel=this;
        repository.getProduct(productDao);


    }


    public MutableLiveData<AppUpdateModel> isUpdateAvailable(){
        return isUpdateAvailable;
    }

    public MutableLiveData<NetworkStatusModel> getNetworkStatusModelLiveData() {
        return networkStatusModelLiveData;
    }

    //this Method check Internet Connection
    public void checkInternetConnectivity(Context application) {
        if (internetConnectivityInfo==null){
            internetConnectivityInfo= new InternetConnectivityInfo(application);
        }
        if (internetConnectivityInfo.isConnected()){
            isInternetOn.postValue(true);
        }else {
            isInternetOn.postValue(false);
        }
    }

    //observe internet Connection
    public LiveData<Boolean> getInternetConnectivityInfo(){
        return isInternetOn;
    }

    public void checkAppVersion(Context context){
        AppUpdateManager updateManager = AppUpdateManagerFactory.create(context);
        Task<AppUpdateInfo> appUpdateInfoTask = updateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                isUpdateAvailable.postValue(new AppUpdateModel(true,updateManager,appUpdateInfo));
            }
        });
    }

    @Override
    public void productStatus(boolean productStatus) {
        networkStatusModel.setProductStore(productStatus);
        networkStatusModelLiveData.postValue(networkStatusModel);
        if(productStatus){
            repository.setProductQtyByCart(cartDao,productDao);
        }
    }

    @Override
    public void productQtystatus(boolean qtyStatus) {
        networkStatusModel.setProductQtyUpdated(qtyStatus);
        networkStatusModelLiveData.postValue(networkStatusModel);
    }



}