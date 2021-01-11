package com.group.etoko.Fragment.HomeFragment.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.group.etoko.R;
import com.group.etoko.model.SeasonalCategory;

import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.SEASONAL_CATEGORY;

public class AdapterSeasonalCat extends RecyclerView.Adapter<AdapterSeasonalCat.Viewholder> {
 List<SeasonalCategory> seasonalCategories;

    public AdapterSeasonalCat(List<SeasonalCategory> seasonalCategories) {
        this.seasonalCategories = seasonalCategories;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitam= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_seasonal_adptr,parent,false);
        return new AdapterSeasonalCat.Viewholder(viewitam);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        if(position%2==0) {
            holder.bg.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorOfferTagYellowishBackground));
            holder.name.setTextColor(holder.itemView.getResources().getColor(R.color.colorOfferRedText));
            holder.name.setText(seasonalCategories.get(position).getSeasonalCatName());
        }
        else{

            holder.name.setText(seasonalCategories.get(position).getSeasonalCatName());
        }
    }

    @Override
    public int getItemCount() {
        return seasonalCategories.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView name;
        private ConstraintLayout bg;
        public Viewholder( View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.seasonal_cat_name_tv);
            bg=(ConstraintLayout)itemView.findViewById(R.id.seasonal_view_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString(SEASONAL_CATEGORY,seasonalCategories.get(getAdapterPosition()).getSeasonalCatId());
                    bundle.putString(ACTIONBAR_TITLE,seasonalCategories.get(getAdapterPosition()).getSeasonalCatName());
                    Navigation.findNavController(itemView).navigate(R.id.action_global_productsGridFragment,bundle);
                }
            });



        }
    }
}
