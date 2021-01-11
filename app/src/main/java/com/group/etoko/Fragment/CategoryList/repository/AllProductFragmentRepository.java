package com.group.etoko.Fragment.CategoryList.repository;

import androidx.lifecycle.LiveData;
import com.group.etoko.Fragment.CategoryList.Model.CategorySubcategoryAndProduct;
import com.group.etoko.Database.dao.CategoryDao;

import java.util.List;

public class AllProductFragmentRepository {

    public LiveData<List<CategorySubcategoryAndProduct>> getAllData(CategoryDao categoryDao){
        return categoryDao.getCategorySubCategoryAndProduct();

    }

}
