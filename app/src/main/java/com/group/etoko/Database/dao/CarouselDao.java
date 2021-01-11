package com.group.etoko.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group.etoko.Fragment.HomeFragment.Model.CarouselItem;

import java.util.List;

@Dao
public interface CarouselDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CarouselItem> carouselItems);

    @Query("DELETE FROM CarouselItem")
    void clear();

    @Query("SELECT * FROM CarouselItem")
    LiveData<List<CarouselItem>> getSliderImage();

}
