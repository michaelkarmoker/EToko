package com.group.etoko.Fragment.CategoryList.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.Fragment.CategoryList.Model.CategorySubcategoryAndProduct;
import com.group.etoko.Fragment.CategoryList.Model.DataModel;
import com.group.etoko.Fragment.CategoryList.repository.AllProductFragmentRepository;
import com.group.etoko.Database.dao.CategoryDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.SubCategoryDao;
import com.group.etoko.Database.db.LocalDatabase;
import java.util.List;

public class AllProductFragmentViewModel extends AndroidViewModel {

    private AllProductFragmentRepository repository;
    private MutableLiveData<Boolean> progressStatusLiveData=new MutableLiveData<>();
    private CategoryDao categoryDao;
    private SubCategoryDao subCategoryDao;
    private ProductDao productDao;
    private DataModel dataModel;

    public AllProductFragmentViewModel(@NonNull Application application) {
        super(application);

        LocalDatabase database=LocalDatabase.getReferences(application);
        categoryDao=database.categoryDao();
        subCategoryDao=database.subCategoryDao();
        productDao=database.productDao();

        repository=new AllProductFragmentRepository();
        dataModel=new DataModel();
    }

    public LiveData<List<CategorySubcategoryAndProduct>> getAllData(){
        return repository.getAllData(categoryDao);
    }


}
