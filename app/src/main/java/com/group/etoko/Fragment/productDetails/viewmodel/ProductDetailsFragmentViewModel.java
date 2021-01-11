package com.group.etoko.Fragment.productDetails.viewmodel;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.productDetails.Model.ModelShareData;
import com.group.etoko.Fragment.productDetails.repo.ProductDetailsFragmentRepository;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.R;
import com.group.etoko.model.Cart;
import com.group.etoko.model.Product;

import java.util.List;

import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.PRODUCT_ID;

public class ProductDetailsFragmentViewModel extends AndroidViewModel {

    private ProductDetailsFragmentRepository repository;
    private MutableLiveData<String> status = new MutableLiveData<>();
    private LiveData<Product> product = new MutableLiveData<>();
    private LiveData<List<Product>> reletedProduct = new MutableLiveData<>();
    private ProductDao productDao;
    private CartDao cartDao;
    private UserDao userDao;

    public ProductDetailsFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductDetailsFragmentRepository();
        LocalDatabase database = LocalDatabase.getReferences(application);
        productDao = database.productDao();
        cartDao=database.cartDao();
        userDao=database.userDao();
    }

    public MutableLiveData<String> getStatus() {
        return status;
    }

    public LiveData<Product> getProduct() {
        return product;
    }

    private LiveData<List<User>> userLogin(){
        return repository.getLoginUser(userDao);
    }

    public void navigateForward(View view, LifecycleOwner viewLifecycleOwner){
        userLogin().observe(viewLifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null&& users.size()!=0){
                    Navigation.findNavController(view).navigate(R.id.action_global_cartFragment);
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_global_loginSignUpFragment);
                }
            }
        });
    }

    public void addToCart(Bundle bundle) {
        if (bundle != null) {
            String productId = bundle.getString(PRODUCT_ID, null);
            if (productId != null) {
                Cart cart=new Cart();
                cart.setProductId(productId);
                cart.setProductCount(1);
                    repository.addCartList(cartDao,productDao,cart);
                    status.postValue("added");
            }else {
                status.postValue("Error");
            }
        } else {
            status.postValue("Error");
        }
    }

    public void getProductInfo(Bundle bundle) {

        if (bundle != null) {
            String productId = bundle.getString(PRODUCT_ID, null);
            String categoryID = bundle.getString(CATEGORY, null);
            if (productId != null) {
                this.product = repository.getProduct(productDao, productId);
            } else {
                status.postValue("No Product Found");
            }

            if (categoryID != null) {
                this.reletedProduct = repository.getRelatedProduct(productDao, categoryID);
            } else {
                status.postValue("No Related Product Found");
            }
        } else {
            status.postValue("Error");
        }

    }

    public LiveData<List<Product>> getReletedProduct() {
        return reletedProduct;
    }

    public MutableLiveData<ModelShareData> getProductUrl(Bundle bundle){
        if (bundle != null) {
            String productId = bundle.getString(PRODUCT_ID, null);
            if (productId != null) {
                return repository.getProductUrlById(productId);
            }else {
                status.postValue("Error");
                return null;
            }
        }else {
            status.postValue("Error");
            return null;
        }
    }


}
