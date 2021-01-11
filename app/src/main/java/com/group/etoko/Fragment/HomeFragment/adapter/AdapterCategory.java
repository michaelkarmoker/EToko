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
import com.group.etoko.model.Category;

import java.util.ArrayList;
import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;

public class AdapterCategory  extends RecyclerView.Adapter<AdapterCategory.Viewholder> {
    private List<Category> categories=new ArrayList<>();
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitam= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_cat_adptr,parent,false);
        return new AdapterCategory.Viewholder(viewitam);
    }
   public AdapterCategory(List<Category> categories){
        this.categories=categories;
   }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Category category=categories.get(position);
        holder.name.setText(category.getCategoryName());
        Glide.with(holder.itemView.getContext()).load(BuildConfig.BASE_URL+category.getCategoryIcon()).into(holder.categoryimage);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView categoryimage;
        public Viewholder( View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_category_name_tv);
            categoryimage=itemView.findViewById(R.id.item_category_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString("CATEGORY",categories.get(getAdapterPosition()).getCategoryId());
                    bundle.putString(ACTIONBAR_TITLE,categories.get(getAdapterPosition()).getCategoryName());
                    Navigation.findNavController(itemView).navigate(R.id.action_global_productsGridFragment,bundle);
                }
            });
        }

    }
}
