package com.group.etoko.Fragment.HomeFragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.islamkhsh.CardSliderAdapter;
import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.HomeFragment.Model.CarouselItem;
import com.group.etoko.R;

import java.util.List;

public class CarouselAdapter extends CardSliderAdapter<CarouselAdapter.ViewHolder> {

    private List<CarouselItem> data;

    public CarouselAdapter(List<CarouselItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_item_design,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        if (data.size() ==0){
            return 0;
        }else {
            return data.size();
        }
    }

    @Override
    public void bindVH(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL+data.get(position).getImageName())
                .into(holder.image);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.product_details_view_pager_image);
        }
    }
}
