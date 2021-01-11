package com.group.etoko.Fragment.CategoryList.Model;

import com.group.etoko.model.Category;
import com.group.etoko.model.Product;
import com.group.etoko.model.SubCategory;

import java.util.HashMap;
import java.util.List;

public class DataModel {

    private List<Category> categories=null;
    private HashMap<String, List<SubCategory>> subList=null;
    private HashMap<String,List<Product>> productList=null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setSubList(HashMap<String, List<SubCategory>> subList) {
        this.subList = subList;
    }

    public void setProductList(HashMap<String, List<Product>> productList) {
        this.productList = productList;
    }

    public boolean status(){
        return subList!=null && subList.size()>0 && productList!=null && productList.size()>0;
    }

    public HashMap<String, List<SubCategory>> getSubList() {
        return subList;
    }

    public HashMap<String, List<Product>> getProductList() {
        return productList;
    }
}
