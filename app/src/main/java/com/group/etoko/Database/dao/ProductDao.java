package com.group.etoko.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group.etoko.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Product> product);

    @Query("DELETE FROM Product")
    void clear();

    @Query("SELECT COUNT(productId) FROM Product")
    LiveData<Integer> getProductCount();

    @Query("SELECT * FROM Product WHERE productId LIKE :productId")
    LiveData<Product> getProduct(String productId);

    @Query("SELECT * FROM Product WHERE categoryID LIKE :categoryId")
    LiveData<List<Product>> getProductByCategoryId(String categoryId);

    @Query("SELECT * FROM Product WHERE subCategoryId LIKE :subCategoryId")
    LiveData<List<Product>> getProductBySubCategoryId(String subCategoryId);

    @Query("SELECT * FROM Product WHERE seasonalCategoryId LIKE :seasonalCategoryId")
    LiveData<List<Product>> getProductBySeasonalCategoryId(String seasonalCategoryId);

    @Query("SELECT * FROM Product WHERE collectionId LIKE :collectionId")
    LiveData<List<Product>> getProductByCollectionId(String collectionId);

    @Query("SELECT * FROM Product WHERE productIsDiscount LIKE :productIsDiscount")
    LiveData<List<Product>> getOfferProduct(String productIsDiscount);

    @Query("SELECT * FROM Product WHERE productName LIKE '% ' || :searchWord || ' %' " +
            "OR productName LIKE '%' || :searchWord || ' %' "+
            "OR productName LIKE '% ' || :searchWord || '%' "+
            "OR productName LIKE '%' || :searchWord || '%' "+
            "OR productSearchTag LIKE '%' || :searchWord || '%' "+
            "OR productCode Like '%' || :searchWord || '%'"
    )
    LiveData<List<Product>> getSearchedProduct(String searchWord);


    @Query("SELECT * FROM Product WHERE categoryID LIKE :categoryId AND subCategoryId LIKE :subCategoryId")
    LiveData<List<Product>> getProductByCategoryAndSubCategoryId(String categoryId,String subCategoryId);

    @Query("UPDATE Product SET productQty=:qty WHERE productId = :productId")
    void updateQtyById(String qty, String productId);


    @Query("SELECT * FROM Product WHERE productIsFeatured LIKE :productIsFeatured")
    LiveData<List<Product>> getFeaturedProducts(String productIsFeatured);
}


////-------for specific word-----
  /*"SELECT * FROM Product WHERE productName LIKE '% ' || :searchWord || ' %' " +
            "OR productName LIKE '' || :searchWord || ' %' "+
            "OR productName LIKE '% ' || :searchWord || '' "+
            "OR productSearchTag LIKE '' || :searchWord || ',%' "+
            "OR productSearchTag LIKE '%,' || :searchWord || '' "+
            "OR productSearchTag LIKE '' || :searchWord || '' "+
            "OR productSearchTag LIKE '' || :searchWord || ' %' "+
            "OR productSearchTag LIKE '%,' || :searchWord || ',%' ")
    */