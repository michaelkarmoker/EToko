package com.group.etoko.Fragment.Checkout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.group.etoko.Fragment.Checkout.ClickListener.RecycleItemClickListener;
import com.group.etoko.Fragment.Checkout.model.DeliveryMethod;

import com.group.etoko.R;
import com.group.etoko.util.Constants;

import java.util.ArrayList;
import java.util.List;


public class AdapterDeliveryMethod extends RecyclerView.Adapter<AdapterDeliveryMethod.MethodViewHolder> {
private List<DeliveryMethod> deliveryMethods=new ArrayList<>();
int selectedPosition=-1;
RecycleItemClickListener recycleItemClickListener;
    public AdapterDeliveryMethod(List<DeliveryMethod> deliveryMethods , RecycleItemClickListener recycleItemClickListener) {
        this.deliveryMethods = deliveryMethods;
        this.recycleItemClickListener=recycleItemClickListener;

    }

    @NonNull
    @Override
    public MethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitam= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_delivery_method,parent,false);
        return new MethodViewHolder(viewitam);
    }

    @Override
    public void onBindViewHolder(@NonNull MethodViewHolder holder, int position) {
            DeliveryMethod deliveryMethod=deliveryMethods.get(position);
        holder.methodrate.setText(Constants.Taka_sign + deliveryMethod.getMethodRate());
        holder.methodName.setText(deliveryMethod.getMethodName());
            if(selectedPosition==position){
                holder.methodBg.setBackground(holder.itemView.getResources().getDrawable(R.drawable.cupon_code_back));
                holder.methodName.setTextColor(holder.itemView.getResources().getColor(R.color.colorPrimary));
                holder.methodrate.setTextColor(holder.itemView.getResources().getColor(R.color.colorPrimary));
            }
            else{
                holder.methodBg.setBackground(null);
                holder.methodName.setTextColor(holder.itemView.getResources().getColor(R.color.white_off2));
                holder.methodrate.setTextColor(holder.itemView.getResources().getColor(R.color.white_off2));
            }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=position;
                recycleItemClickListener.position(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryMethods.size();
    }

    public class MethodViewHolder extends RecyclerView.ViewHolder{
        TextView methodrate,methodName;
        ConstraintLayout methodBg;

        public MethodViewHolder(View itemView) {
            super(itemView);
           methodName=itemView.findViewById(R.id.method_name);
           methodrate=itemView.findViewById(R.id.method_rate);
            methodBg=itemView.findViewById(R.id.methodCardView);
        }
    }
}
