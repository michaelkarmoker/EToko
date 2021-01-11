package com.group.etoko.Fragment.Cart.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.Cart.model.CartContainer;
import com.group.etoko.Fragment.Cart.viewmodel.CartFragmentViewModel;
import com.group.etoko.R;
import com.group.etoko.model.Cart;

import java.util.List;

import static com.group.etoko.util.Constants.Taka_sign;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private List<CartContainer> containers;
    private CartFragmentViewModel viewModel;

    public CartAdapter(List<CartContainer> containers, CartFragmentViewModel viewModel){
        this.containers=containers;
        this.viewModel=viewModel;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_recycler_item_design,parent,false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {


        holder.product_name.setText(containers.get(position).product.getProductName());
        holder.product_unit.setText(containers.get(position).product.getProductMeasurement()+" "+containers.get(position).product.getProductUnit());
        holder.product_price.setText(Taka_sign+" "+String.valueOf(containers.get(position).product.getProductOriginalPrice()));
        Glide.with(holder.itemView.getContext()).load(BuildConfig.BASE_URL+containers.get(position).product.getProductPrimaryImage()).into(holder.product_image);
        holder.total_product.setText(containers.get(position).cart.getProductCount()+"");

        holder.increment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(containers.get(position).product.getProductIsAvailable())!=0)
                {
                    Cart cart=containers.get(position).cart;
                    cart.setProductCount(cart.getProductCount()+1);
                    viewModel.updateCart(cart);
                    viewModel.notifyCartItemChange(holder.itemView.getContext(),position);
                }else {
                    Toast.makeText(holder.itemView.getContext(),"Out Of Stoke",Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.decrement_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart=containers.get(position).cart;
                if (cart.getProductCount()>1){
                    cart.setProductCount(cart.getProductCount()-1);
                    viewModel.updateCart(cart);
                    viewModel.notifyCartItemChange(holder.itemView.getContext(),position);
                }else {

                }
                Log.e("cLLLLL","click");
            }
        });

        holder.exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteCart(containers.get(position).cart);
                containers.remove(position);
                viewModel.notifyCartItemRemove(holder.itemView.getContext(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return containers.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        TextView product_name,product_unit,product_price,total_product;
        ImageView product_image,exit_btn,increment_btn,decrement_btn;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            product_price=itemView.findViewById(R.id.product_price);
            product_unit=itemView.findViewById(R.id.product_unit);
            total_product=itemView.findViewById(R.id.count_txt);


            product_image=itemView.findViewById(R.id.product_image);
            exit_btn=itemView.findViewById(R.id.exit_button);
            increment_btn=itemView.findViewById(R.id.plus_button);
            decrement_btn=itemView.findViewById(R.id.minus_button);

        }
    }
}
