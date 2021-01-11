package com.group.etoko.Fragment.ProfileEdit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.group.etoko.Fragment.LoginSignUp.model.User;

import com.group.etoko.Fragment.ProfileEdit.model.ProfileUpdateResponse;
import com.group.etoko.Fragment.ProfileEdit.repo.ProfileEditRepository;

import java.util.List;

public class ProfileEditViewModel extends AndroidViewModel implements ProfileEditViewModel_View {
    private LiveData<List<User>> userMutableLiveData;
    private MutableLiveData<ProfileUpdateResponse> UpdateResponse=new MutableLiveData<>();
    private ProfileEditRepository repository;
    public ProfileEditViewModel(@NonNull Application application) {
        super(application);
       repository=new ProfileEditRepository(application);
       repository.viewModel=this;
       userMutableLiveData=repository.getuser();
    }

public LiveData<List<User>> getuser(){
        return userMutableLiveData;
}
public MutableLiveData<ProfileUpdateResponse> getResponse(){
        return UpdateResponse;
}
public void setuserdata(User user){
        repository.edituser(user);
}

    @Override
    public void setResponse(ProfileUpdateResponse response) {

        UpdateResponse.postValue(response);
    }
}
