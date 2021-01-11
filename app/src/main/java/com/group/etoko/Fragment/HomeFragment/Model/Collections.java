package com.group.etoko.Fragment.HomeFragment.Model;

import androidx.room.Embedded;
import androidx.room.Relation;


import com.group.etoko.model.Collection;
import com.group.etoko.model.Product;


import java.util.ArrayList;
import java.util.List;

public class Collections {
      @Embedded
      public Collection collection=null;

      @Relation(entity = Product.class ,parentColumn = "collectionId",entityColumn = "collectionId")
      public List<Product> products=new ArrayList<>();

      public List<Product> getProducts(){
          return  products;
      }
}
