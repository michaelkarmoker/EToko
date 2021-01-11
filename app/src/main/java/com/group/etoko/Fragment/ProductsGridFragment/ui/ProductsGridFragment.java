package com.group.etoko.Fragment.ProductsGridFragment.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.group.etoko.Activity.main_activity.ui.MainActivity;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentProductsGridBinding;
import com.group.etoko.model.Product;
import com.group.etoko.Fragment.ProductsGridFragment.adapter.AdapterProductView;
import com.group.etoko.Fragment.ProductsGridFragment.adapter.SpacesItemDecoProductGrid;
import com.group.etoko.Fragment.ProductsGridFragment.viewmodel.ProductsGridFragmentViewModel;
import com.group.etoko.util.HideKeyBoard;

import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;

public class ProductsGridFragment extends Fragment {
    private FragmentProductsGridBinding binding;
    private ProductsGridFragmentViewModel viewModel;
    private int observeCount;
    public ProductsGridFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProductsGridBinding.inflate(inflater, container, false);
        HideKeyBoard.hideKeyboard(requireActivity());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProductsGridFragmentViewModel.class);
        observeCount=0;
        observer();

        String title= requireArguments().getString(ACTIONBAR_TITLE,null);
        if(title!=null){
           // Navigation.findNavController(view).getCurrentDestination().setLabel(title);
             ((MainActivity) requireActivity()).updateToolbar(title);

        }



    }


    private void observer() {
        viewModel.getProductByCategoryAndSubCategoryId(getArguments()).observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if(products.size()>0 && products!=null) {
                    if(observeCount==0) {
                        //  binding.productsRv.setVisibility(View.INVISIBLE);
                        binding.productsRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        binding.productsRv.setHasFixedSize(true);
                        AdapterProductView adapter = new AdapterProductView(products, getContext());
                        binding.productsRv.addItemDecoration(new SpacesItemDecoProductGrid(20));
                        binding.productsRv.setAdapter(adapter);
                        observeCount++;
                    }
                }
                else{
                    Glide.with(requireActivity())
                            .load(R.drawable.product_not_found)
                            .placeholder(R.drawable.product_not_found)
                            .into(binding.productNotFoundImage);
                    binding.notFoundViewContianer.setVisibility(View.VISIBLE);
                }
            }
        });
    }




}
