package com.group.etoko.Fragment.productDetails.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.productDetails.Model.ModelShareData;
import com.group.etoko.Fragment.productDetails.viewmodel.ProductDetailsFragmentViewModel;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentProductDetailsBinding;
import com.group.etoko.util.Constants;
import com.group.etoko.model.Product;
import com.group.etoko.Fragment.HomeFragment.adapter.AdapterExclusiveOffer;
import com.group.etoko.util.MySharedPreference;
import com.group.etoko.util.RelatedProductSpacesItemDecoration;
import com.group.etoko.util.SnackbarBuilder;

import java.util.List;
import java.util.Objects;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.RECYCLER_ITEM_POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    private FragmentProductDetailsBinding binding;
    private ProductDetailsFragmentViewModel viewModel;
    private AdapterExclusiveOffer reletedAdapter;
    private ProgressDialog progressDialog;
    private int isAdapterAlreadySet=0;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentProductDetailsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel=new ViewModelProvider(this).get(ProductDetailsFragmentViewModel.class);
        viewModel.getProductInfo(getArguments());

        //Observe all ui data
        observer();



        binding.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(Objects.requireNonNull(viewModel.getProduct().getValue()).getProductIsAvailable())!=0){
                    if(Integer.parseInt(Objects.requireNonNull(viewModel.getProduct().getValue()).getProductQty())>0){
                        showMessage("Product already in cart!");
                    }
                    else {
                        viewModel.addToCart(getArguments());
                    }
                }else {
                    showMessage("Product Out Of Stoke!");
                }

            }
        });

        binding.checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(Objects.requireNonNull(viewModel.getProduct().getValue()).getProductIsAvailable())!=0){

                    if(Integer.parseInt(Objects.requireNonNull(viewModel.getProduct().getValue()).getProductQty())>0){
                        showMessage("Product already in cart!");
                        navigteForwar(v);
                    }
                    else {
                        viewModel.addToCart(getArguments());
                        navigteForwar(v);
                    }
                }else {
                    showMessage("Product Out Of Stoke!");
                }
            }
        });

        binding.detailUpDownImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.expandableLayout.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(binding.detailsLayout,new AutoTransition());
                    binding.expandableLayout.setVisibility(View.VISIBLE);
                    binding.detailUpDownImageView.setImageResource(R.drawable.ic_arrow_down);
                }else {
                    TransitionManager.beginDelayedTransition(binding.detailsLayout,new AutoTransition());
                    binding.expandableLayout.setVisibility(View.GONE);
                    binding.detailUpDownImageView.setImageResource(R.drawable.ic_arrow_up);
                }
            }
        });

        binding.seeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments() != null){
                    String categoryID = getArguments().getString(CATEGORY, null);
                    Bundle bundle=new Bundle();
                    bundle.putString(CATEGORY,categoryID);
                    bundle.putString(ACTIONBAR_TITLE,"Product");
                    Navigation.findNavController(v).navigate(R.id.productsGridFragment,bundle);
                }
            }
        });

        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(getContext(), "", "Please Wait...", true);

                viewModel.getProductUrl(getArguments()).observe(getViewLifecycleOwner(), new Observer<ModelShareData>() {
                    @Override
                    public void onChanged(ModelShareData modelShareData) {
                        progressDialog.cancel();
                        if (modelShareData != null && modelShareData.getStatus()){
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, BuildConfig.BASE_URL+"Product-Details/"+modelShareData.getData());
                            sendIntent.setType("text/plain");
                            Intent shareIntent = Intent.createChooser(sendIntent, null);
                            startActivity(shareIntent);
                        }
                    }
                });
            }
        });
    }

    private void navigteForwar(View v) {
        viewModel.navigateForward(v,getViewLifecycleOwner());
    }

    private void observer() {

        viewModel.getStatus().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showMessage(s);
            }
        });

        //Observe Related Product
        viewModel.getProduct().observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                if (product != null){
                    Glide.with(ProductDetailsFragment.this).load(BuildConfig.BASE_URL +product.getProductPrimaryImage()).into(binding.imageViewpager);
                    binding.productNameValueTextView.setText(product.getProductName());
                    binding.productCodeValueTextView.setText(product.getProductCode());
                    binding.productUnitValueTextView.setText(product.getProductMeasurement()+" "+product.getProductUnit());
                    binding.productPriceValueTextView.setText(product.getProductUnitPrice()+" "+ Constants.Taka_sign);
                    binding.productSortDetailsValueTextView.setText(product.getProductShortDescription());
                }
            }
        });

        //Observe Related Product
        viewModel.getReletedProduct().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (products != null){
                    if (isAdapterAlreadySet==0){
                        isAdapterAlreadySet++;
                        binding.reletedProductRecyclerView.addItemDecoration(new RelatedProductSpacesItemDecoration(0));
                        binding.reletedProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                        binding.reletedProductRecyclerView.setHasFixedSize(true);
                        reletedAdapter=new AdapterExclusiveOffer(products,getContext());
                        binding.reletedProductRecyclerView.setAdapter(reletedAdapter);
                    }else {
                        if (getNotifyItemChange(getContext()) != -1){
                            reletedAdapter.notifyItemChanged(getNotifyItemChange(getContext()));
                            notifyItemChange(getContext(),-1);
                        }
                    }
                }
            }
        });
    }

    private void showMessage(String s){
        SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(getContext())
                .setLayoutId(binding.rootLayout)
                .setMessage(s)
                .build();
        snackbarBuilder.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        isAdapterAlreadySet=0;
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
