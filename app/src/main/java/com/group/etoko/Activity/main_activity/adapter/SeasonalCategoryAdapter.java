package com.group.etoko.Activity.main_activity.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.group.etoko.R;
import com.group.etoko.model.SeasonalCategory;

import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.SEASONAL_CATEGORY;

public class SeasonalCategoryAdapter extends RecyclerView.Adapter<SeasonalCategoryAdapter.ViewHolder>{

    private List<SeasonalCategory> seasonalCategories;
    private NavController navController;
    private DrawerLayout navDrawerLayout;

    public SeasonalCategoryAdapter(List<SeasonalCategory> seasonalCategories, NavController navController, DrawerLayout navDrawerLayout) {
        this.seasonalCategories = seasonalCategories;
        this.navController=navController;
        this.navDrawerLayout=navDrawerLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.seasonal_category_item_design,parent,false);
        return new ViewHolder(view,navController,navDrawerLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(seasonalCategories.get(position).seasonalCatName);
    }

    @Override
    public int getItemCount() {
        return seasonalCategories.size() > 3 ? 3 : seasonalCategories.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        private NavController nController;
        private DrawerLayout drawer;

        public ViewHolder(@NonNull View itemView, NavController navController, DrawerLayout navDrawerLayout) {
            super(itemView);

            name=itemView.findViewById(R.id.seasonal_product_name);
            this.nController=navController;
            this.drawer=navDrawerLayout;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawer != null && drawer.isOpen()){
                        drawer.close();
                    }
                    Bundle bundle=new Bundle();
                    bundle.putString(SEASONAL_CATEGORY,seasonalCategories.get(getAdapterPosition()).getSeasonalCatId());
                    bundle.putString(ACTIONBAR_TITLE,seasonalCategories.get(getAdapterPosition()).getSeasonalCatName());
                    nController.navigate(R.id.productsGridFragment,bundle);
                }
            });
        }
    }
}
