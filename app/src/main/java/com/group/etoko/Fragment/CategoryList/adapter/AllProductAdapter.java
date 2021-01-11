package com.group.etoko.Fragment.CategoryList.adapter;
import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.group.etoko.Fragment.CategoryList.Model.CategorySubcategoryAndProduct;
import com.group.etoko.Fragment.HomeFragment.adapter.AdapterExclusiveOffer;
import com.group.etoko.R;
import com.group.etoko.util.MySharedPreference;

import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.RECYCLER_ITEM_POSITION;


public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder>{

    private List<CategorySubcategoryAndProduct> categorySubcategoryAndProducts;
    Context context;
    public AllProductAdapter(List<CategorySubcategoryAndProduct> categorySubcategoryAndProducts,Context context) {
        this.categorySubcategoryAndProducts=categorySubcategoryAndProducts;
        this.context=context;
    }

    @NonNull
    @Override
    public AllProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product_recycler_item_design,parent,false);
        return new AllProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductViewHolder holder, int position) {

        //Category Name
        holder.categoryTxt.setText(categorySubcategoryAndProducts.get(position).getCategory().getCategoryName());

//        Sub Category Name List
            SubCatNameAdapter subCatNameListAdapter =new SubCatNameAdapter(categorySubcategoryAndProducts.get(position).getSubCategories());
//            holder.subCatRecyclerView.setHasFixedSize(true);
            holder.subCatRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            holder.subCatRecyclerView.setAdapter(subCatNameListAdapter);

//        Sub Category Item
// <<<<<<< HEAD
//             AdapterExclusiveOffer subCatItemListAdapter =new AdapterExclusiveOffer(categorySubcategoryAndProducts.get(position).getProducts(), new MyRecyclerItemClickListner() {
//                 @Override
//                 public void item(int position, View view) {
//                     Bundle bundle=new Bundle();
//                     bundle.putString(PRODUCT_ID,categorySubcategoryAndProducts.get(position).getProducts().get(position).getProductId());
//                     bundle.putString(CATEGORY,categorySubcategoryAndProducts.get(position).getProducts().get(position).getCategoryID());
//                     Navigation.findNavController(view).navigate(R.id.productDetailsFragment,bundle);
//                 }
//             });
// =======
            AdapterExclusiveOffer subCatItemListAdapter =new AdapterExclusiveOffer(categorySubcategoryAndProducts.get(position).getProducts(),context);
//>>>>>>> MichaelNew
//            holder.subCatItemRecyclerView.setHasFixedSize(true);
            holder.subCatItemRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            holder.subCatItemRecyclerView.setAdapter(subCatItemListAdapter);
        if (getNotifyItemChange(context) != -1){
            subCatItemListAdapter.notifyItemChanged(getNotifyItemChange(context));
            notifyItemChange(context,-1);
        }
    }

    @Override
    public int getItemCount() {
        return categorySubcategoryAndProducts.size();
    }


    public class AllProductViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTxt,seeAllTxt;
        RecyclerView subCatRecyclerView;
        RecyclerView subCatItemRecyclerView;

        public AllProductViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTxt =itemView.findViewById(R.id.category_title);
            seeAllTxt =itemView.findViewById(R.id.txt_see_all);
            subCatRecyclerView=itemView.findViewById(R.id.sub_category_recycler_view);
            subCatItemRecyclerView=itemView.findViewById(R.id.sub_category_item_recycler_view);

            seeAllTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString("CATEGORY",categorySubcategoryAndProducts.get(getAdapterPosition()).category.categoryId);
                    bundle.putString(ACTIONBAR_TITLE,categorySubcategoryAndProducts.get(getAdapterPosition()).category.getCategoryName());
                    Navigation.findNavController(v).navigate(R.id.action_global_productsGridFragment,bundle);
                }
            });
        }
    }

    private void notifyItemChange(Context context,int position){
        MySharedPreference.getInstance(context)
                .edit()
                .putInt(RECYCLER_ITEM_POSITION,position)
                .apply();
    }

    private int getNotifyItemChange(Context context){
        return MySharedPreference.getInstance(context)
                .getInt(RECYCLER_ITEM_POSITION,-1);
    }
}
