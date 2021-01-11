package com.group.etoko.Database.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Database.dao.CarouselDao;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.CategoryDao;
import com.group.etoko.Database.dao.CollectionDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.SeasonalCatDao;
import com.group.etoko.Database.dao.SubCategoryDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.model.Cart;
import com.group.etoko.model.Category;
import com.group.etoko.model.Collection;
import com.group.etoko.model.Product;
import com.group.etoko.model.SeasonalCategory;
import com.group.etoko.model.SubCategory;
import com.group.etoko.Fragment.HomeFragment.Model.CarouselItem;

@Database(entities = {Category.class, SubCategory.class, Product.class, User.class,Collection.class, SeasonalCategory.class,Cart.class, CarouselItem.class},version =1,exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase databaseRef;
    public abstract CategoryDao categoryDao();
    public abstract SubCategoryDao subCategoryDao();
    public abstract SeasonalCatDao seasonalCatDao();
    public abstract CollectionDao collectionDao();
    public abstract ProductDao productDao();
    public abstract UserDao userDao();
    public abstract CartDao cartDao();
    public abstract CarouselDao carouselDao();



    public static LocalDatabase getReferences(@NonNull Context context){
        if (databaseRef==null){
            synchronized (LocalDatabase.class){
                if (databaseRef == null ){
                    databaseRef= Room.databaseBuilder(context,LocalDatabase.class,"CholoV2Database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseRef;
    }
}
