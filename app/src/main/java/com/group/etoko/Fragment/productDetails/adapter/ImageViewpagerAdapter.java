package com.group.etoko.Fragment.productDetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.group.etoko.R;

import java.util.List;

public class ImageViewpagerAdapter extends PagerAdapter {


    private List<String> images;
    public ImageViewpagerAdapter(List<String> images){
        this.images=images;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v=LayoutInflater.from(container.getContext()).inflate(R.layout.carousel_item_design,null);

        ImageView imgSlide = v.findViewById(R.id.product_details_view_pager_image);
        Glide.with(container.getContext())
                .load(images.get(position))
                .centerCrop()
                //.placeholder(R.drawable.)
                .into(imgSlide);
        container.addView(v);

        return v;
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
