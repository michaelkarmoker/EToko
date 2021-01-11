package com.group.etoko.Activity.main_activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group.etoko.R;
import com.group.etoko.model.Product;

import java.util.List;

public class OfferProductAdapter extends RecyclerView.Adapter<OfferProductAdapter.OfferProductViewHolder>{

    private Context context;
    private List<Product> productLists;

    public OfferProductAdapter(Context context, List<Product> productLists){
        this.context=context;
        this.productLists=productLists;
    }

    @NonNull
    @Override
    public OfferProductAdapter.OfferProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_offer_product_list_design,parent,false);
        return new OfferProductAdapter.OfferProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferProductAdapter.OfferProductViewHolder holder, int position) {
        holder.textView.setText(productLists.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    class OfferProductViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public OfferProductViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.offer_product_name);

        }
    }
}
