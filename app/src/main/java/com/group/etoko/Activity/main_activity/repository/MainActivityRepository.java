package com.group.etoko.Activity.main_activity.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Activity.main_activity.model.CategoryAndSubCategory;
import com.group.etoko.Database.dao.CategoryDao;
import com.group.etoko.Database.dao.SeasonalCatDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.model.SeasonalCategory;

import java.util.List;

public class MainActivityRepository {

    public MainActivityRepository(){
    }

    public LiveData<List<User>> getUser(UserDao userDao){
        return userDao.getUser();
    }


    public LiveData<List<CategoryAndSubCategory>> getCategoryAndSubCategory(CategoryDao categoryDao){
        return categoryDao.getCategorySubCategory();
    }

    public boolean clearUser(UserDao userDao){
        try{
            new DeleteUser(userDao).execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static class DeleteUser extends AsyncTask<Void,Void,Boolean > {

        private UserDao userDao;

        public DeleteUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            userDao.clear();
            return null;
        }
    }

    public LiveData<List<SeasonalCategory>> getSeasonalCategory(SeasonalCatDao seasonalCatDao) {
        return seasonalCatDao.getSeasonalCategory();
    }

}
