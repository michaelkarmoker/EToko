package com.group.etoko.Fragment.Checkout.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoDeliveryMethod extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoDeliveryMethod(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 2;
      //  outRect.right = space;
         outRect.bottom = space;
        //outRect.top = space;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildAdapterPosition(view) <= 1) {
            outRect.top = space;
        }
    }
}