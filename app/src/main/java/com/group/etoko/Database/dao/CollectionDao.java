package com.group.etoko.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.group.etoko.model.Collection;
import com.group.etoko.Fragment.HomeFragment.Model.Collections;

import java.util.List;

@Dao
public interface CollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Collection> collection);

    @Query("DELETE FROM Collection")
    void clear();

    @Query("SELECT * FROM Collection")
    LiveData<List<Collection>> getCollection();

    @Query("SELECT * FROM Collection WHERE collectionId LIKE :collectionId")
    LiveData<List<Collection>> getCollectionById(String collectionId);

    @Transaction
    @Query("SELECT * FROM Collection")
    LiveData<List<Collections>> getcollections();



}
