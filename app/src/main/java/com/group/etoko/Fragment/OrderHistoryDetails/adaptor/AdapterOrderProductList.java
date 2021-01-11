package com.group.etoko.Fragment.OrderHistoryDetails.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group.etoko.BuildConfig;
import com.group.etoko.R;
import com.group.etoko.util.Constants;
import com.group.etoko.Fragment.OrderHistoryDetails.model.OrderProduct;

import java.util.ArrayList;
import java.util.List;


public class AdapterOrderProductList extends RecyclerView.Adapter<AdapterOrderProductList.Viewholder> {
        List<OrderProduct> products=new ArrayList<>();

    public AdapterOrderProductList(List<OrderProduct> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitam= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_order_history,parent,false);
        return new AdapterOrderProductList.Viewholder(viewitam);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
           holder.product_name.setText(products.get(position).getProduct_name());
           holder.product_price.setText(Constants.Taka_sign+products.get(position).getProduct_price());
           holder.product_qty.setText("x "+products.get(position).getProduct_qty());
        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL + products.get(position).getProduct_image())
                .placeholder(R.drawable.placeholderimage)
                .into(holder.product_image);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView product_name,product_unit,product_price,product_qty;
        private ImageView product_image;
        public Viewholder( View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            product_unit=itemView.findViewById(R.id.product_unit);
            product_price=itemView.findViewById(R.id.product_price);
            product_qty=itemView.findViewById(R.id.product_qty);
            product_image=itemView.findViewById(R.id.product_image);




        }
    }
}
