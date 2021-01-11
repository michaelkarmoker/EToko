package com.group.etoko.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.group.etoko.Fragment.CategoryList.Model.CategorySubcategoryAndProduct;
import com.group.etoko.Activity.main_activity.model.CategoryAndSubCategory;
import com.group.etoko.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Category> category);

    @Query("DELETE FROM Category")
    void clear();

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getCategory();

    @Query("SELECT * FROM Category WHERE categoryId LIKE :categoryId")
    LiveData<List<Category>> getCategoryById(String categoryId);

   /* @Transaction
    @Query("SELECT * FROM category")
    LiveData<List<Collections>> getcollection();*/
  
    @Transaction
    @Query("SELECT * FROM Category")
    LiveData<List<CategorySubcategoryAndProduct>> getCategorySubCategoryAndProduct();

    @Transaction
    @Query("SELECT * FROM Category")
    LiveData<List<CategoryAndSubCategory>> getCategorySubCategory();
}
