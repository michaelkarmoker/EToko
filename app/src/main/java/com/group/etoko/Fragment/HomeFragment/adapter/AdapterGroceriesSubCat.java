package com.group.etoko.Fragment.HomeFragment.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group.etoko.BuildConfig;
import com.group.etoko.R;
import com.group.etoko.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.SUB_CATEGORY;

public class AdapterGroceriesSubCat extends RecyclerView.Adapter<AdapterGroceriesSubCat.Viewholder> {
    private List<SubCategory> groceriesSubCat=new ArrayList<>();
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitam= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_groceries_subcat_adptr,parent,false);
        return new AdapterGroceriesSubCat.Viewholder(viewitam);
    }
   public AdapterGroceriesSubCat(List<SubCategory> groceriesSubCat){
        this.groceriesSubCat=groceriesSubCat;
   }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        SubCategory gcategory=groceriesSubCat.get(position);
        holder.name.setText(gcategory.getSubCategoryName());
        Glide.with(holder.itemView.getContext()).load(BuildConfig.BASE_URL+gcategory.getSubCategoryImage()).into(holder.gCategoryimage);

    }

    @Override
    public int getItemCount() {
        return groceriesSubCat.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView gCategoryimage;
        public Viewholder( View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_gSubcategories_name_txt);
            gCategoryimage=itemView.findViewById(R.id.item_gsubcategories_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString(CATEGORY,groceriesSubCat.get(getAdapterPosition()).getCategoryID());
                    bundle.putString(SUB_CATEGORY,groceriesSubCat.get(getAdapterPosition()).getSubCategoryId());
                    bundle.putString(ACTIONBAR_TITLE,groceriesSubCat.get(getAdapterPosition()).getSubCategoryName());
                    Navigation.findNavController(itemView).navigate(R.id.action_global_productsGridFragment,bundle);
                }
            });



        }
    }
}
