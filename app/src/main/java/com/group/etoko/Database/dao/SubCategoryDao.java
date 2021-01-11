package com.group.etoko.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group.etoko.model.SubCategory;
import java.util.List;

@Dao
public interface SubCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SubCategory> subCategory);

    @Query("DELETE FROM SubCategory")
    void clear();

    @Query("SELECT * FROM SubCategory WHERE categoryID LIKE :categoryId")
    LiveData<List<SubCategory>> getSubCategoryByCategoryId(String categoryId);

    @Query("SELECT * FROM SubCategory WHERE categoryId LIKE :categoryName")
    LiveData<List<SubCategory>> getSubCategoryByCategoryName(String categoryName);

}
