package com.group.etoko.Fragment.ProductsGridFragment.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.model.Product;
import com.group.etoko.Fragment.ProductsGridFragment.viewmodel.ProductsGridFragmentViewModel;

import java.util.List;

public class ProductsGridFragmentRepository {
    public ProductsGridFragmentViewModel viewModel;
    public ProductDao productDao;

    public ProductsGridFragmentRepository(Context context) {
        LocalDatabase db=LocalDatabase.getReferences(context.getApplicationContext());
        productDao=db.productDao();
    }


    public LiveData<List<Product>> getProducts(String cat_id,String sub_cat_id){
       return productDao.getProductByCategoryAndSubCategoryId(cat_id,sub_cat_id);

     }
    public LiveData<List<Product>> getProductsByCatId(String cat_id){
        return productDao.getProductByCategoryId(cat_id);
    }
    public LiveData<List<Product>> getProductBySeasonalId(String Seasonal_id) {
        return productDao.getProductBySeasonalCategoryId(Seasonal_id);

    }
    public LiveData<List<Product>> getSearchedProductByName(String word) {
        return productDao.getSearchedProduct(word);

    }
    public LiveData<List<Product>> getProductByCollectionId(String collectionId) {
        return productDao.getProductByCollectionId(collectionId);

    }
    public LiveData<List<Product>> getFeaturedProduct(String IsFeatured) {
        return productDao.getFeaturedProducts(IsFeatured);

    }

    public LiveData<List<Product>> getExclusiveOfferProducts(String OfferId) {
        return productDao.getOfferProduct(OfferId);

    }

}
