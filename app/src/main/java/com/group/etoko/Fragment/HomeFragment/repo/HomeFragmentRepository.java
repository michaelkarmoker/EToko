package com.group.etoko.Fragment.HomeFragment.repo;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.group.etoko.util.Constants;
import com.group.etoko.Database.dao.CarouselDao;
import com.group.etoko.Database.dao.CategoryDao;
import com.group.etoko.Database.dao.CollectionDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.SeasonalCatDao;
import com.group.etoko.Database.dao.SubCategoryDao;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.model.Category;
import com.group.etoko.model.CategoryContainer;
import com.group.etoko.model.Collection;
import com.group.etoko.model.CollectionContainer;
import com.group.etoko.model.Product;
import com.group.etoko.model.SeasonalCategory;
import com.group.etoko.model.SeasonalCategoryContainer;
import com.group.etoko.model.SubCategory;
import com.group.etoko.model.SubCategoryContainer;
import com.group.etoko.Fragment.HomeFragment.Model.CarouselItem;
import com.group.etoko.Fragment.HomeFragment.Model.CarouselItemContainer;
import com.group.etoko.Fragment.HomeFragment.Model.Collections;
import com.group.etoko.Fragment.HomeFragment.viewmodel.HomeFragmentViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentRepository {

    private Api api;
    public HomeFragmentViewModel viewModel;

    public HomeFragmentRepository(Context context) {
        api= RetrofitClient.get().create(Api.class);
    }


    public LiveData<List<Collections>> getCollections(CollectionDao collectionDao) {
        return collectionDao.getcollections();
    }

    public LiveData<List<Category>> getCategory(CategoryDao categoryDao) {
        return categoryDao.getCategory();
    }
    public LiveData<List<SeasonalCategory>> getSeasonalCategory(SeasonalCatDao seasonalCatDao) {

        return seasonalCatDao.getSeasonalCategory();
    }

    public LiveData<List<Product>> getGroceriesProduct(ProductDao productDao,String id) {
        return productDao.getProductByCategoryId(id);
    }

    public LiveData<List<Product>> getExclusiveOfferProduct(ProductDao productDao) {

        return productDao.getOfferProduct(Constants.IS_Discount);
    }

    public LiveData<List<SubCategory>> getGroceriesSubCat(SubCategoryDao subCategoryDao,String id) {

        return subCategoryDao.getSubCategoryByCategoryId(id);
    }
    public LiveData<List<Product>> getSearchedProduct(ProductDao productDao,String word) {

        return productDao.getSearchedProduct(word);
    }

public LiveData<List<Product>> getFeaturedProduct(ProductDao productDao){
       return productDao.getFeaturedProducts("1");
}
    public void getCategoryFromNetwork(CategoryDao categoryDao){
        api.getCategory().enqueue(new Callback<CategoryContainer>() {
            @Override
            public void onResponse(@NonNull Call<CategoryContainer> call, @NonNull Response<CategoryContainer> response) {
                if (response.isSuccessful() && response.body() != null){
                    new InsertCategoryList(categoryDao).execute(response.body().getData());
                    Log.e("DBBBBBBBBBB  Call 1","getCategory  "+"Success");
                }else {
                    Log.e("DBBBBBBBBBB  Call 1","getCategory  "+"Failed");
                }
            }

            @Override
            public void onFailure(Call<CategoryContainer> call, Throwable t) {
                Log.e("DBBBBBBBBBB Call 1",t.toString());
            }
        });
    }


    public void getSubCategory(SubCategoryDao subCategoryDao){
        api.getSubCategory().enqueue(new Callback<SubCategoryContainer>() {
            @Override
            public void onResponse(@NonNull Call<SubCategoryContainer> call, @NonNull Response<SubCategoryContainer> response) {
                if (response.isSuccessful() && response.body() != null){
                    new InsertSubCategoryList(subCategoryDao).execute(response.body().getData());
                    Log.e("DBBBBBBBBBB Call 2","getSubCategory  "+"Success");
                }else {
                    Log.e("DBBBBBBBBBB  Call 2","getSubCategory  "+"Failed");
                }
            }

            @Override
            public void onFailure(Call<SubCategoryContainer> call, Throwable t) {
            }
        });
    }


    public void getCollectionFromNetwork(CollectionDao collectionDao){

        api.getCollection().enqueue(new Callback<CollectionContainer>() {
            @Override
            public void onResponse(Call<CollectionContainer> call, Response<CollectionContainer> response) {
                if(response.isSuccessful() && response.body()!=null ) {
                    if (response.body().getData() != null) {
                        new InsertCollectionList(collectionDao).execute(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<CollectionContainer> call, Throwable t) {
            }
        });
    }

    public void getSliderImageData(CarouselDao carouselDao){
        api.getSliderImageData().enqueue(new Callback<CarouselItemContainer>() {
            @Override
            public void onResponse(Call<CarouselItemContainer> call, @NonNull Response<CarouselItemContainer> response) {
                if (response.isSuccessful() && response.body() != null){
                    new InsertImageSliderList(carouselDao).execute(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CarouselItemContainer> call, Throwable t) {
            }
        });

    }


    public  void insertSeasonalCategory(SeasonalCatDao seasonalCatDao){
        api.getSeasonalCategory().enqueue(new Callback<SeasonalCategoryContainer>() {
            @Override
            public void onResponse(Call<SeasonalCategoryContainer> call, Response<SeasonalCategoryContainer> response) {
                if (response.isSuccessful() && response.body() != null ){
                    if( response.body().getData()!=null) {
                        new InsertSeasonalCatList(seasonalCatDao).execute(response.body().getData());
                        Log.e("DBBBBBBBBBB Call 5", "getseasonal  " + "Success");
                    }

                }else {
                    Log.e("DBBBBBBBBBB  Call 5","getseasonal  "+"Failed");
                }
            }

            @Override
            public void onFailure(Call<SeasonalCategoryContainer> call, Throwable t) {

            }
        });
    }


    public LiveData<List<CarouselItem>> getImageData(CarouselDao carouselDao) {
        return carouselDao.getSliderImage();
    }

    private class InsertSeasonalCatList extends AsyncTask< List<SeasonalCategory>,Void,Void >{

        private SeasonalCatDao seasonalCatDao;

        public InsertSeasonalCatList(SeasonalCatDao seasonalCatDao){
            this.seasonalCatDao=seasonalCatDao;
        }

        @Override
        protected Void doInBackground(List<SeasonalCategory>... lists) {
            seasonalCatDao.clear();
            seasonalCatDao.insert(lists[0]);
            return null;

        }
    }


    private class InsertCategoryList extends AsyncTask< List<Category>,Void,Void >{

        private CategoryDao categoryDao;

        public InsertCategoryList(CategoryDao categoryDao){
            this.categoryDao=categoryDao;
        }

        @Override
        protected Void doInBackground(List<Category>... lists) {
            categoryDao.clear();
            categoryDao.insert(lists[0]);
            return null;
        }
    }

    private class InsertSubCategoryList extends AsyncTask< List<SubCategory>,Void,Void >{

        private SubCategoryDao subCategoryDao;

        public InsertSubCategoryList(SubCategoryDao subCategoryDao){
            this.subCategoryDao=subCategoryDao;
        }

        @Override
        protected Void doInBackground(List<SubCategory>... lists) {
            subCategoryDao.clear();
            subCategoryDao.insert(lists[0]);
            return null;
        }
    }

    private class InsertImageSliderList extends AsyncTask< List<CarouselItem>,Void,Void > {

        private CarouselDao carouselDao;

        public InsertImageSliderList(CarouselDao carouselDao){
            this.carouselDao=carouselDao;
        }

        @Override
        protected Void doInBackground(List<CarouselItem>... lists) {
            carouselDao.clear();
            carouselDao.insert(lists[0]);
            return null;
        }
    }

    private class InsertCollectionList extends AsyncTask< List<Collection>,Void,Void >{

        private CollectionDao collectionDao;

        public InsertCollectionList(CollectionDao collectionDao){
            this.collectionDao=collectionDao;
        }

        @Override
        protected Void doInBackground(List<Collection>... lists) {
            collectionDao.clear();
            collectionDao.insert(lists[0]);
            return null;
        }
    }

}
