package com.group.etoko.Fragment.Profile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.Fragment.Profile.repo.ProfileRepository;

import java.util.List;


public class ProfileViewModel extends AndroidViewModel implements ProfileViewModel_View {
    private LiveData<List<User>> userMutableLiveData;
    private MutableLiveData<List<User>> userDataServerLiveData=new MutableLiveData<>();
     private ProfileRepository repository;
     private UserDao userDao;
    public ProfileViewModel(@NonNull Application application) {
        super(application);
        LocalDatabase db = LocalDatabase.getReferences(application);
        userDao=db.userDao();
        repository=new ProfileRepository();
        repository.viewModel=this;
        userMutableLiveData=repository.getuser(userDao);

    }
public void UserInfoByMobile(String mobileNo){
     repository.getUserDataFromServer(mobileNo);
}
    public MutableLiveData<List<User>> getUserFromServer(){return userDataServerLiveData;}
    public LiveData<List<User>> getUserMutableLiveData(){
        return userMutableLiveData;
}

    public void clearUser() {
        repository.clearUserData(userDao);
    }

    @Override
    public void setUserdata(List<User> user) {
        userDataServerLiveData.postValue(user);
    }
}
