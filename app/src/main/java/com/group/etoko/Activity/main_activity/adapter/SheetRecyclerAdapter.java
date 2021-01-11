package com.group.etoko.Activity.main_activity.adapter;

import android.content.Context;
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
import com.group.etoko.model.SubCategory;

import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.SUB_CATEGORY;

public class SheetRecyclerAdapter extends RecyclerView.Adapter<SheetRecyclerAdapter.SheetRecyclerViewHolder>{

    private Context context;
    private List<SubCategory> list;
    private NavController navController;
    private DrawerLayout drawerLayout;

    public SheetRecyclerAdapter(Context context, List<SubCategory> list, NavController navController, DrawerLayout drawerLayout) {
        this.context = context;
        this.list = list;
        this.navController=navController;
        this.drawerLayout=drawerLayout;
    }

    @NonNull
    @Override
    public SheetRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_recycler_item_design,parent,false);
        return new SheetRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SheetRecyclerViewHolder holder, int position) {
        holder.name.setText(list.get(position).getSubCategoryName());
    }

    @Override
    public int getItemCount() {
        if (list.size()<5){
            return list.size();
        }
        return 4;
    }

    class SheetRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        public SheetRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.sub_list_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawerLayout != null && drawerLayout.isOpen()){
                        drawerLayout.close();
                    }
                    Bundle bundle=new Bundle();
                    bundle.putString(CATEGORY,list.get(getAdapterPosition()).getCategoryID());
                    bundle.putString(SUB_CATEGORY,list.get(getAdapterPosition()).getSubCategoryId());
                    bundle.putString(ACTIONBAR_TITLE,list.get(getAdapterPosition()).getSubCategoryName());
                    navController.navigate(R.id.productsGridFragment,bundle);
                }
            });
        }
    }
}
