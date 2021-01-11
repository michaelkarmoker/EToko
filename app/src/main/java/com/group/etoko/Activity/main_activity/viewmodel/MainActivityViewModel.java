package com.group.etoko.Activity.main_activity.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Activity.main_activity.model.CategoryAndSubCategory;
import com.group.etoko.Activity.main_activity.repository.MainActivityRepository;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.CategoryDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.SeasonalCatDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.model.SeasonalCategory;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> logoutStatus=new MutableLiveData<>();
    private MainActivityRepository repository;
    private CategoryDao categoryDao;
    private UserDao userDao;
    private SeasonalCatDao seasonalCatDao;
    private ProductDao productDao;
    private CartDao cartDao;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository=new MainActivityRepository();


        LocalDatabase database=LocalDatabase.getReferences(application);
        categoryDao=database.categoryDao();
        seasonalCatDao=database.seasonalCatDao();
        userDao=database.userDao();
        productDao=database.productDao();
        cartDao=database.cartDao();

    }

    public MutableLiveData<Boolean> getLogoutStatus() {
        return logoutStatus;
    }

    public LiveData<List<CategoryAndSubCategory>> getCategoryAndSubCategory(){
       return repository.getCategoryAndSubCategory(categoryDao);
    }

    public LiveData<List<User>> getCurrentUser() {
        return repository.getUser(userDao);
    }

    public void clearUser(){
        logoutStatus.postValue(repository.clearUser(userDao));
    }

    public LiveData<List<SeasonalCategory>> getSeasonalCategory(){
       return repository.getSeasonalCategory(seasonalCatDao);
    }


    public LiveData<Integer> getCartCount(){
        return cartDao.getCartCount();
    }


    public void subscribeToGlobalTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("Global")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribe Success";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe Failed";
                        }
                        Log.e("MainActivityViewModel", msg);
                    }
                });
    }

}
