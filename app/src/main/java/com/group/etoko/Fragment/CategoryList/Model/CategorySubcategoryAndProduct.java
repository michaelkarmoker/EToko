package com.group.etoko.Fragment.CategoryList.Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.group.etoko.model.Category;
import com.group.etoko.model.Product;
import com.group.etoko.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class CategorySubcategoryAndProduct {

    @Embedded
    public Category category=null;

    @Relation(parentColumn = "categoryId",entityColumn = "categoryID")
    public List<SubCategory> subCategories=new ArrayList<>();

    @Relation(parentColumn = "categoryId",entityColumn = "categoryID")
    public List<Product> products=new ArrayList<>();


    public Category getCategory() {
        return category;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public List<Product> getProducts() {
        return products;
    }

}
