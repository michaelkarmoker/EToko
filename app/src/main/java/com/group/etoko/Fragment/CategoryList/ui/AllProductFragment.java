package com.group.etoko.Fragment.CategoryList.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group.etoko.Fragment.CategoryList.Model.CategorySubcategoryAndProduct;
import com.group.etoko.Fragment.CategoryList.adapter.AllProductAdapter;
import com.group.etoko.Fragment.CategoryList.viewmodel.AllProductFragmentViewModel;
import com.group.etoko.databinding.FragmentAllProductBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductFragment extends Fragment {

    private FragmentAllProductBinding binding;
    private AllProductFragmentViewModel viewModel;
    private AllProductAdapter allProductAdapter;
    private int isAdapterAlreadySet=0;


    public AllProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AllProductFragmentViewModel.class);
        obserber();
    }

    private void obserber() {
        viewModel.getAllData().observe(getViewLifecycleOwner(), new Observer<List<CategorySubcategoryAndProduct>>() {
            @Override
            public void onChanged(List<CategorySubcategoryAndProduct> categorySubcategoryAndProducts) {
                if (categorySubcategoryAndProducts != null) {
                    if (isAdapterAlreadySet==0) {
                        isAdapterAlreadySet++;
                        allProductAdapter = new AllProductAdapter(categorySubcategoryAndProducts, getContext());
                        binding.allProductRecyclerView.setHasFixedSize(true);
                        binding.allProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        binding.allProductRecyclerView.setAdapter(allProductAdapter);
                    }
                }

            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        isAdapterAlreadySet=0;
    }
}
