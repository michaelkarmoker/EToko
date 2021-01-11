package com.group.etoko.Fragment.CategoryList.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.group.etoko.R;
import com.group.etoko.model.SubCategory;

import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.SUB_CATEGORY;

public class SubCatNameAdapter extends RecyclerView.Adapter<SubCatNameAdapter.SubProductViewHolder>{

    private List<SubCategory> subCategories;

    public SubCatNameAdapter(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @NonNull
    @Override
    public SubProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_groceries_subcat_adptr,parent,false);
        return new SubProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubProductViewHolder holder, int position) {
        holder.subCatName.setText(subCategories.get(position).getSubCategoryName());
    }

    @Override
    public int getItemCount() {
        if (subCategories.size() < 10){
            return subCategories.size();
        }
        return 9;
    }

    class SubProductViewHolder extends RecyclerView.ViewHolder{

        private TextView subCatName;
        private ImageView subCategoryimage;

        public SubProductViewHolder(@NonNull View itemView) {
            super(itemView);

            subCatName =itemView.findViewById(R.id.item_gSubcategories_name_txt);
//             subCategoryimage =itemView.findViewById(R.id.item_gsubcategories_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
// <<<<<<< HEAD
//                     bundle.putString("CATEGORY",subCategories.get(getAdapterPosition()).getCategoryID());
//                     bundle.putString("SUB_CATEGORY",subCategories.get(getAdapterPosition()).getSubCategoryId());
//                     Navigation.findNavController(itemView).navigate(R.id.productsGridFragment,bundle);
//                 }
//             });
// =======

                    //>>>>>>> MichaelNew
                    bundle.putString(CATEGORY,subCategories.get(getAdapterPosition()).getCategoryID());
                    bundle.putString(SUB_CATEGORY,subCategories.get(getAdapterPosition()).getSubCategoryId());
                    bundle.putString(ACTIONBAR_TITLE,subCategories.get(getAdapterPosition()).getSubCategoryName());
                    Navigation.findNavController(itemView).navigate(R.id.action_global_productsGridFragment,bundle);
                }
            });

        }
    }
}
