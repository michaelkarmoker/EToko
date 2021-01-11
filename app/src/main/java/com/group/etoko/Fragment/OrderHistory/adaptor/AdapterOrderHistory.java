package com.group.etoko.Fragment.OrderHistory.adaptor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.group.etoko.Fragment.OrderHistory.model.Order;
import com.group.etoko.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterOrderHistory extends RecyclerView.Adapter<AdapterOrderHistory.Viewholder> {
        List<Order> orders=new ArrayList<>();

    public AdapterOrderHistory(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitam= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_details_order_history,parent,false);
        return new AdapterOrderHistory.Viewholder(viewitam);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
           Order order=orders.get(position);
           holder.orderId.setText("Order: "+order.getCustom_order_id());
           holder.orderPlaceTime.setText("Placed on "+order.getProduct_order_date_time());
           holder.orderStatus.setText(orders.get(position).getProduct_order_status());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView orderId,orderPlaceTime,orderStatus;
        private ConstraintLayout expandablelayout;
        public Viewholder( View itemView) {
            super(itemView);
            orderId=itemView.findViewById(R.id.order_id_tv);
            orderPlaceTime=itemView.findViewById(R.id.order_placed_time_tv);
            orderStatus=itemView.findViewById(R.id.order_status_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Order order=orders.get(getAdapterPosition());
                     Bundle bundle=new Bundle();
                     bundle.putString("orderId",order.getProduct_order_id());
                     bundle.putString("customOrderId",order.getCustom_order_id());
                     bundle.putString("orderPlaceTime",order.getProduct_order_date_time());
                     bundle.putString("orderstatus",order.getProduct_order_status());
                     bundle.putString("subtotal",order.getProduct_order_net());
                     bundle.putString("delivery",order.getProduct_order_shipping_rate());
                     bundle.putString("additionalCost",order.getProduct_order_additional_shipping_cost());
                    bundle.putString("couponDiscount",order.getProduct_order_coupon_amount());
                    bundle.putString("referralBlnc",order.getProduct_order_customer_ref_balance());
                     bundle.putString("tax",order.getProduct_total_vat());
                     bundle.putString("total",order.getProduct_order_total());
                     Navigation.findNavController(view).navigate(R.id.orderHistoryDetailsFragment,bundle);


                }
            });



        }
    }
}
