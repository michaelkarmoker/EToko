package com.group.etoko.Fragment.ProductsGridFragment.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.util.Constants;
import com.group.etoko.model.Product;
import com.group.etoko.Fragment.ProductsGridFragment.repo.ProductsGridFragmentRepository;

import java.util.List;

import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.COLLECTION;
import static com.group.etoko.util.Constants.SEARCH_WORD;
import static com.group.etoko.util.Constants.SEASONAL_CATEGORY;
import static com.group.etoko.util.Constants.SUB_CATEGORY;

public class ProductsGridFragmentViewModel extends AndroidViewModel {

    private LiveData<List<Product>> productMutableLiveData=new MutableLiveData<>();
    private ProductsGridFragmentRepository repository;

    public ProductsGridFragmentViewModel(@NonNull Application application) {
        super(application);

        repository = new ProductsGridFragmentRepository(application);

    }


    public LiveData<List<Product>> getProductByCategoryAndSubCategoryId(Bundle bundle) {
        if (bundle != null) {
            String Cat_ID = bundle.getString(CATEGORY, null);
            String SUB_Cat_ID = bundle.getString(SUB_CATEGORY, null);
            String Seasonal_ID = bundle.getString(SEASONAL_CATEGORY, null);
            String Collection_ID = bundle.getString(COLLECTION, null);
            String Search_WORD = bundle.getString(SEARCH_WORD, null);
            String OFFER_PRODUCTID=bundle.getString(Constants.OFFER_PRODUCT,null);
            String Featured_Product=bundle.getString(Constants.FEATURED_PRODUCT,null);
            if (Cat_ID != null && SUB_Cat_ID != null){
                productMutableLiveData= repository.getProducts(Cat_ID,SUB_Cat_ID);
            }
            else if(Cat_ID!=null){
                productMutableLiveData= repository.getProductsByCatId(Cat_ID);
            }
            else if(Seasonal_ID!=null){
                productMutableLiveData= repository.getProductBySeasonalId(Seasonal_ID);
            }
            else if(Search_WORD!=null){
                productMutableLiveData= repository.getSearchedProductByName(Search_WORD);
            }
            else if(Collection_ID!=null){
                productMutableLiveData= repository.getProductByCollectionId(Collection_ID);
            }
            else if(OFFER_PRODUCTID!=null){
                productMutableLiveData= repository.getExclusiveOfferProducts(OFFER_PRODUCTID);
            }
            else if(Featured_Product!=null){
                productMutableLiveData= repository.getFeaturedProduct(Featured_Product);
            }

        }
        return productMutableLiveData;
    }


}
