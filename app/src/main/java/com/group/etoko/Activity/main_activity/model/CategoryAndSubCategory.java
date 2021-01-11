package com.group.etoko.Activity.main_activity.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.group.etoko.model.Category;
import com.group.etoko.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryAndSubCategory {

    @Embedded
    public Category category=null;

    @Relation(parentColumn = "categoryId",entityColumn = "categoryID")
    public List<SubCategory> subCategories=new ArrayList<>();

    public Category getCategory() {
        return category;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }


}
