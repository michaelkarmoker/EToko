package com.group.etoko.Fragment.HomeFragment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group.etoko.Fragment.HomeFragment.Model.Collections;
import com.group.etoko.R;
import com.group.etoko.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.COLLECTION;

public class AdapterCollectionHome extends RecyclerView.Adapter<AdapterCollectionHome.CollectionsHolder> {
    private List<Collections> collections;
    Context context;
    int itemSpace;

    public AdapterCollectionHome(List<Collections> collections,Context context,int itemSpace) {
        this.collections = collections;
        this.context=context;
        this.itemSpace=itemSpace;
    }

    @NonNull
    @Override
    public CollectionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitam = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_collection_adptr, parent, false);
        return new CollectionsHolder(viewitam);

    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsHolder holder, int position) {
        Collections collection = collections.get(position);
        holder.title.setText(collection.collection.getCollectionName());
        AdapterExclusiveOffer adapterExclusiveOffer = new AdapterExclusiveOffer((ArrayList<Product>) collection.products,context);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //holder.recyclerView.addItemDecoration(new SpacesItemDecoration(itemSpace));
//        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapterExclusiveOffer);
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public class CollectionsHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView seeAll;
        private RecyclerView recyclerView;
        private ImageView productImage;

        public CollectionsHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.collection_txt);
            seeAll = itemView.findViewById(R.id.collections_see_all_btn);
            recyclerView = itemView.findViewById(R.id.collection_recycler);

            seeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (collections.get(getAdapterPosition()).getProducts()!= null && collections.get(getAdapterPosition()).getProducts().size()>0){
                        Bundle bundle=new Bundle();
                        bundle.putString(COLLECTION,collections.get(getAdapterPosition()).products.get(0).getCollectionId());
                        bundle.putString(ACTIONBAR_TITLE,collections.get(getAdapterPosition()).collection.getCollectionName());
                        Navigation.findNavController(itemView).navigate(R.id.action_global_productsGridFragment,bundle);
                    }else {
                        Toast.makeText(context,"Please Wait...!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
