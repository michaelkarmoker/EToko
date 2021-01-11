package com.group.etoko.Fragment.ProductReq.viewModel;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.ProductReq.repo.ProductReqFragmentRepository;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.model.ReqModel;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ProductReqFragmentViewModel extends AndroidViewModel {

    private ProductReqFragmentRepository repository;
    private UserDao userDao;

    public ProductReqFragmentViewModel(@NonNull Application application) {
        super(application);
        repository=new ProductReqFragmentRepository();
        userDao= LocalDatabase.getReferences(application).userDao();
    }

    public MutableLiveData<ReqModel> uploadFile(Bitmap bitmap, String apple, String productPrice, String userName, String userPhone, String userId) {
        Bitmap immagex=bitmap;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
        Log.e("Image Base64",imageEncoded);
        return repository.getRequestProduct(apple,productPrice,imageEncoded,userName,userPhone,userId);
    }

    public LiveData<List<User>> getLoginUser(){
        return repository.getUser(userDao);
    }
}
