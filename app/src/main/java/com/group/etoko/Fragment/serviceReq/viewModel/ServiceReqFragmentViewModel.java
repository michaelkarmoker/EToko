package com.group.etoko.Fragment.serviceReq.viewModel;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.serviceReq.Repo.ServiceReqFragmentRepository;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.model.ReqModel;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ServiceReqFragmentViewModel extends AndroidViewModel {

    private ServiceReqFragmentRepository repository;
    private UserDao userDao;

    public ServiceReqFragmentViewModel(@NonNull Application application) {
        super(application);
        repository=new ServiceReqFragmentRepository();
        userDao= LocalDatabase.getReferences(application).userDao();
    }

    public MutableLiveData<ReqModel> uploadFile(Bitmap bitmap, String serviceName, String userName, String userPhone, String userId) {
        Bitmap immagex=bitmap;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
        Log.e("Image Base64",imageEncoded);
        return repository.getRequestService(serviceName,imageEncoded,userName,userPhone,userId);
    }

    public LiveData<List<User>> getLoginUser(){
        return repository.getUser(userDao);
    }
}
