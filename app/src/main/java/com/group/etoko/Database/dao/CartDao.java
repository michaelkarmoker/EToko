package com.group.etoko.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import com.group.etoko.Fragment.Cart.model.CartContainer;
import com.group.etoko.model.Cart;

import java.util.List;

@Dao
public interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cart cart);

    @Query("DELETE FROM Cart")
    void clear();

    @Transaction
    @Query("SELECT * FROM Cart")
    LiveData<List<CartContainer>> getCartList();

    @Query("SELECT * FROM Cart")
    List<Cart> getCartListforQty();

    @Query("SELECT COUNT(id) FROM Cart")
    LiveData<Integer> getCartCount();

    @Update
    void update(Cart cart);

    @Delete
    void deleteCart(Cart cart);

    //---for Rv adapter-------------
    @Query("UPDATE Cart SET productCount =:productCount WHERE productId=:productId ")
    void updateByProductId(int productCount,String productId);

    @Query("DELETE FROM Cart WHERE productId=:productId")
    void DeleteByProductId(String productId);


}
