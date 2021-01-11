package com.group.etoko.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group.etoko.model.SeasonalCategory;

import java.util.List;
@Dao
public interface SeasonalCatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SeasonalCategory> seasonalCategories);

    @Query("DELETE FROM SeasonalCategory")
    void clear();

    @Query("SELECT * FROM SeasonalCategory")
    LiveData<List<SeasonalCategory>> getSeasonalCategory();

/*    @Query("SELECT * FROM SeasonalCategory WHERE collectionId LIKE :collectionId")
    LiveData<List<SeasonalCategory>> getCollectionById(String collectionId);

    @Transaction
    @Query("SELECT * FROM SeasonalCategory")
    LiveData<List<SeasonalCategory>> getcollections();*/
}
