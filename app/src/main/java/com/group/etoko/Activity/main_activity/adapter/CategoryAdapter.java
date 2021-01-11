package com.group.etoko.Activity.main_activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.group.etoko.Activity.main_activity.model.CategoryAndSubCategory;
import com.group.etoko.BuildConfig;
import com.group.etoko.R;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.NormalProductViewHolder>{

    private Context context;
    private List<CategoryAndSubCategory> dataList;
    private NavController navController;
    private DrawerLayout drawerLayout;

    public CategoryAdapter(Context context, List<CategoryAndSubCategory> dataList, NavController navController, DrawerLayout navDrawerLayout){
        this.context=context;
        this.dataList=dataList;
        this.navController=navController;
        this.drawerLayout=navDrawerLayout;
    }

    @NonNull
    @Override
    public NormalProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_normal_product_list_design,parent,false);
        return new NormalProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NormalProductViewHolder holder, int position) {
        holder.categoryName.setText(dataList.get(position).getCategory().getCategoryName());
        Glide.with(holder.itemView.getContext()).load(BuildConfig.BASE_URL+dataList.get(position).getCategory().getCategoryIcon()).into(holder.categoryIcon);
    }

    @Override
    public int getItemCount() {
        if (dataList.size()<10){
            return dataList.size();
        }
        return 9;
    }

    class NormalProductViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        ImageView categoryIcon,category_arrow;

        public NormalProductViewHolder(@NonNull View itemView ) {
            super(itemView);

            categoryName=itemView.findViewById(R.id.product_name);
            categoryIcon=itemView.findViewById(R.id.product_logo);
            category_arrow=itemView.findViewById(R.id.product_arrow);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConstraintLayout constraintLayout=itemView.findViewById(R.id.bottom_sheet);
                    BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(constraintLayout);
                    RecyclerView recyclerView=itemView.findViewById(R.id.sheet_re);
                    recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
//                    recyclerView.setHasFixedSize(true);
                    SheetRecyclerAdapter sheetRecyclerAdapter=new SheetRecyclerAdapter(itemView.getContext(),dataList.get(getAdapterPosition()).getSubCategories(),navController,drawerLayout);
                    recyclerView.setAdapter(sheetRecyclerAdapter);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        category_arrow.setRotation(0);
                        recyclerView.setAdapter(null);
                    }else {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                    //for arrow change
                    bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                        @Override
                        public void onStateChanged(@NonNull View bottomSheet, int newState) {

                        }

                        @Override
                        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                            category_arrow.setRotation(slideOffset*90);
                        }
                    });

                }
            });
        }
    }
}
