package com.group.etoko.Fragment.HomeFragment.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.AsyncTask;
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
import com.group.etoko.Activity.main_activity.ClickListner.MyRecyclerItemClickListner;
import com.group.etoko.BuildConfig;
import com.group.etoko.R;
import com.group.etoko.util.Constants;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.model.Cart;
import com.group.etoko.model.Product;
import com.group.etoko.util.MySharedPreference;

import java.util.List;

import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.PRODUCT_ID;
import static com.group.etoko.util.Constants.RECYCLER_ITEM_POSITION;

public class AdapterExclusiveOffer extends RecyclerView.Adapter<AdapterExclusiveOffer.ExclusiveViewHolder> {
    private List<Product> products;
    private MyRecyclerItemClickListner itemClickListner;

    CartDao cartDao;
    ProductDao productDao;
    Cart cart=new Cart();
    static int c=0;
    LocalDatabase db;
    private Context context;

    public AdapterExclusiveOffer(List<Product> products,Context context) {
        this.products = products;
      db =LocalDatabase.getReferences(context);
      cartDao=db.cartDao();
        productDao= db.productDao();
        this.context=context;
    }

    public AdapterExclusiveOffer(List<Product> products, MyRecyclerItemClickListner itemClickListner) {
        this.products = products;
        this.itemClickListner = itemClickListner;
    }

    @NonNull
    @Override
    public ExclusiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_product_adptr, parent, false);
         return itemClickListner != null ? new ExclusiveViewHolder(view,itemClickListner):new ExclusiveViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ExclusiveViewHolder holder, int position) {
        Product product = products.get(position);
        holder.Name.setText(product.getProductName());
        holder.weight.setText(product.getProductMeasurement()+" "+product.getProductUnit());
        holder.unitprice.setText(Constants.Taka_sign+product.getProductUnitPrice());
        holder.BtnDecrease.setVisibility(View.INVISIBLE);
        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL + product.getProductPrimaryImage())
                .placeholder(R.drawable.placeholderimage)
                .into(holder.productImage);

        if(product.getProductIsFeatured().equals("1")){
            holder.ItemFeaturedIv.setVisibility(View.VISIBLE);
        }

        // if have discount-----------
        if (product.getProductIsDiscount().equals("1")) {
            holder.orginalprice.setVisibility(View.VISIBLE);
            holder.orginalprice.setText(Constants.Taka_sign +product.getProductOriginalPrice());
            holder.orginalprice.setPaintFlags(holder.orginalprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //discount amount------
            holder.DiscountAmount.setVisibility(View.VISIBLE);
            holder.DiscountAmount.setText(Constants.Taka_sign+product.getProductDiscountAmount()+" off");
        }
        else{
            holder.orginalprice.setVisibility(View.INVISIBLE);
            holder.DiscountAmount.setVisibility(View.INVISIBLE);
        }



        if (Integer.parseInt(product.getProductIsAvailable())==1){

            //if product is in cart-----------
            if(Integer.parseInt(product.getProductQty())>0 ){
                holder.AddToBag.setVisibility(View.INVISIBLE);
                holder.QTY.setVisibility(View.VISIBLE);
                holder.QTY.setText(product.getProductQty());
                holder.BtnIncrease.setVisibility(View.VISIBLE);
                holder.BtnDecrease.setVisibility(View.VISIBLE);
            }
            else{
                holder.AddToBag.setClickable(true);
                holder.AddToBag.setVisibility(View.VISIBLE);
                holder.QTY.setVisibility(View.INVISIBLE);
                holder.AddToBag.setText("Add to Bag");
                holder.BtnIncrease.setVisibility(View.VISIBLE);
            }
        }
        else{
            holder.AddToBag.setVisibility(View.VISIBLE);
            holder.AddToBag.setClickable(false);
            holder.AddToBag.setText("Out of Stock");
            holder.QTY.setVisibility(View.GONE);
            holder.BtnIncrease.setVisibility(View.GONE);
            holder.BtnDecrease.setVisibility(View.GONE);
        }

        holder.BtnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=0;
                count=Integer.parseInt(product.getProductQty());
                holder.AddToBag.setVisibility(View.INVISIBLE);
                holder.QTY.setVisibility(View.VISIBLE);
                holder.BtnDecrease.setVisibility(View.VISIBLE);
                count++;
                cart.setProductCount(count);
                cart.setProductId(product.getProductId());
                if(count==1){
                    new InsertToCart(cartDao,productDao).execute(cart);
                }
                else {
                    new UpdateDeleteToCart(cartDao, productDao).execute(cart);
                }

                MySharedPreference.getInstance(context)
                        .edit()
                        .putInt(RECYCLER_ITEM_POSITION,position)
                        .apply();

                product.setProductQty(String.valueOf(count));
                holder.QTY.setText(String.valueOf(count));
            }
        });

        holder.BtnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=0;
                count=Integer.parseInt(product.getProductQty());
                if(count>1){
                    count--;
                    cart.setProductCount(count);
                    cart.setProductId(product.getProductId());
                    new UpdateDeleteToCart(cartDao,productDao).execute(cart);
                    product.setProductQty(String.valueOf(count));
                    holder.QTY.setText(String.valueOf(count));
                }

                else if(count==1){
                    count--;
                    cart.setProductCount(count);
                    cart.setProductId(product.getProductId());
                    new UpdateDeleteToCart(cartDao,productDao).execute(cart);
                    product.setProductQty(String.valueOf(count));
                    holder.BtnDecrease.setVisibility(View.INVISIBLE);
                    holder.QTY.setVisibility(View.INVISIBLE);
                    holder.AddToBag.setVisibility(View.VISIBLE);
                    holder.AddToBag.setText("Add to Bag");

                }
                MySharedPreference.getInstance(context)
                        .edit()
                        .putInt(RECYCLER_ITEM_POSITION,position)
                        .apply();

            }
        });


    }

    @Override
    public int getItemCount() {
        if (products.size() < 20) {
            return products.size();
        }
        return 20 - 1;
    }


    public class ExclusiveViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;
        private TextView unitprice;
        private TextView orginalprice;
        private TextView weight;
        private ImageView productImage;
        private ImageView BtnIncrease;
        private ImageView BtnDecrease,ItemFeaturedIv;
        private TextView DiscountAmount;
        private TextView AddToBag;
        private TextView QTY;
        private MyRecyclerItemClickListner itemClickListner;

        public ExclusiveViewHolder(View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.item_product_name_tv);
            unitprice = itemView.findViewById(R.id.item_current_price_tv);
            orginalprice = itemView.findViewById(R.id.item_prev_price_tv);
            weight = itemView.findViewById(R.id.item_weight_tv);
            productImage = itemView.findViewById(R.id.item_product_image);
            DiscountAmount = itemView.findViewById(R.id.item_discount_tv);
            ItemFeaturedIv=itemView.findViewById(R.id.item_featured_image);
            BtnIncrease=itemView.findViewById(R.id.item_bt_increase);
            BtnDecrease=itemView.findViewById(R.id.item_bt_decrease);
            AddToBag=itemView.findViewById(R.id.item_add_to_cart_tv);
            QTY=itemView.findViewById(R.id.item_in_cart_quantity_tv);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString(PRODUCT_ID,products.get(getAdapterPosition()).getProductId());
                    bundle.putString(CATEGORY,products.get(getAdapterPosition()).getCategoryID());
                    Navigation.findNavController(itemView).navigate(R.id.action_global_productDetailsFragment,bundle);
                }
            });

        }

        public ExclusiveViewHolder(View itemView, MyRecyclerItemClickListner itemClickListner) {
            super(itemView);

            Name = itemView.findViewById(R.id.item_product_name_tv);
            unitprice = itemView.findViewById(R.id.item_current_price_tv);
            orginalprice = itemView.findViewById(R.id.item_prev_price_tv);
            weight = itemView.findViewById(R.id.item_weight_tv);
            productImage = itemView.findViewById(R.id.item_product_image);
            DiscountAmount = itemView.findViewById(R.id.item_discount_tv);
            this.itemClickListner=itemClickListner;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListner.item(getAdapterPosition(),v);
                }
            });

        }

    }

    public class UpdateDeleteToCart extends AsyncTask<Cart,Void,Void > {

        private CartDao cartDao;
        private ProductDao productDao;

        public UpdateDeleteToCart(CartDao cartDao, ProductDao productDao){
            this.cartDao=cartDao;
            this.productDao=productDao;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            productDao.updateQtyById(String.valueOf(carts[0].getProductCount()),String.valueOf(carts[0].getProductId()));
            if(cart.getProductCount()<1){
                cartDao.DeleteByProductId(carts[0].getProductId());
            }
            else{
                cartDao.updateByProductId(carts[0].getProductCount(),carts[0].getProductId());
            }


            return null;
    }

    }
    public class InsertToCart extends AsyncTask<Cart,Void,Void> {

        private CartDao cartDao;
        private ProductDao productDao;

        public InsertToCart(CartDao cartDao,ProductDao productDao){
            this.cartDao=cartDao;
            this.productDao=productDao;
        }


        @Override
        protected Void doInBackground(Cart... carts) {
            productDao.updateQtyById(String.valueOf(carts[0].getProductCount()),String.valueOf(carts[0].getProductId()));
            cartDao.insert(carts[0]);
            return null;
        }
    }
}


