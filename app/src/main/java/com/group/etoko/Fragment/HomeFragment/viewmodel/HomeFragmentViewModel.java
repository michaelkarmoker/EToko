package com.group.etoko.Fragment.HomeFragment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.etoko.Database.dao.CarouselDao;
import com.group.etoko.Database.dao.CategoryDao;
import com.group.etoko.Database.dao.CollectionDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.SeasonalCatDao;
import com.group.etoko.Database.dao.SubCategoryDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.model.SeasonalCategory;
import com.group.etoko.Fragment.HomeFragment.Model.CarouselItem;
import com.group.etoko.Fragment.HomeFragment.Model.Collections;
import com.group.etoko.model.Category;
import com.group.etoko.model.Product;
import com.group.etoko.model.SubCategory;
import com.group.etoko.Fragment.HomeFragment.repo.HomeFragmentRepository;

import java.util.List;


public class HomeFragmentViewModel extends AndroidViewModel{

    private HomeFragmentRepository repository;
    private CarouselDao carouselDao;
    private CollectionDao collectionDao;
    private ProductDao productDao;
    private SeasonalCatDao seasonalCatDao;
    private CategoryDao categorydao;
    private SubCategoryDao subCategoryDao;
    private String groceriesCatId="1";
    private CategoryDao categoryDao;
    private static boolean isNetworkCall=false;
    private MutableLiveData<Boolean> shimmerOnOff=new MutableLiveData<>();
    private MutableLiveData<Boolean> isUpdateAvailable=new MutableLiveData<>();
    private DatabaseReference ref;



    //constructor==========
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        repository=new HomeFragmentRepository(application);
        LocalDatabase database=LocalDatabase.getReferences(application);
        categoryDao=database.categoryDao();
        carouselDao=database.carouselDao();
        collectionDao=database.collectionDao();
        productDao=database.productDao();
        seasonalCatDao=database.seasonalCatDao();
        categorydao = database.categoryDao();
        subCategoryDao = database.subCategoryDao();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
         ref= firebaseDatabase.getReference();
    }

    public void callDataFromNetwork(){
        if (!isNetworkCall){
            shimmerOnOff.postValue(true);
            isNetworkCall=true;
            repository.getCollectionFromNetwork(collectionDao);
            repository.getSliderImageData(carouselDao);
            repository.insertSeasonalCategory(seasonalCatDao);
            repository.getCategoryFromNetwork(categoryDao);
            repository.getSubCategory(subCategoryDao);
        }
    }


    public void ObserveUpdate(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer oldSdk = dataSnapshot.child("AppSdkVersion").child("currentVersion").getValue(Integer.class);
                Integer newSdk = dataSnapshot.child("AppSdkVersion").child("newVersion").getValue(Integer.class);
                if ( oldSdk != null && newSdk != null && !oldSdk.equals(newSdk)){
                    isUpdateAvailable.postValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//getters-------------------------

    public MutableLiveData<Boolean> getShimmerOnOff() {
        return shimmerOnOff;
    }

    public MutableLiveData<Boolean> getIsUpdateAvailable() {
        return isUpdateAvailable;
    }

    public LiveData<List<SubCategory>> getGroceriesSubCat(){ return repository.getGroceriesSubCat(subCategoryDao,groceriesCatId);}
    public LiveData<List<Product>> getGroceriesProducts(){return repository.getGroceriesProduct(productDao,groceriesCatId);}
    public LiveData<List<Product>> getExcluciveOfferProduct(){ return repository.getExclusiveOfferProduct(productDao);}
    public LiveData<List<Product>> getFeaturedProducts(){return repository.getFeaturedProduct(productDao);}
    public LiveData<List<Collections>> getCollections(){return repository.getCollections(collectionDao);}
    public LiveData<List<Category>> getCategorie(){return repository.getCategory(categorydao);}
    public LiveData<List<SeasonalCategory>> getSeasonalCategory(){return repository.getSeasonalCategory(seasonalCatDao);}

    public LiveData<Integer> getProduct() {
        return productDao.getProductCount();
    }

    public LiveData<List<CarouselItem>> getImageSliderData() {
        return repository.getImageData(carouselDao);
    }
}
