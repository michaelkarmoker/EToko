package com.group.etoko.Fragment.ProductReq.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.ProductReq.viewModel.ProductReqFragmentViewModel;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentProductReqBinding;
import com.group.etoko.model.ReqModel;
import com.group.etoko.util.SnackbarBuilder;

import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductReqFragment extends Fragment {

    private FragmentProductReqBinding binding;
    private ProductReqFragmentViewModel viewModel;
    private int PICK_PHOTO=101;
    private Bitmap bitmap;
    private User user;
    private ProgressDialog progressDialog;

    public ProductReqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProductReqBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel=new ViewModelProvider(this).get(ProductReqFragmentViewModel.class);
        obserber();

        binding.selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 202);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PICK_PHOTO);
                }
            }
        });

        binding.requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.requestBtn.setEnabled(false);
                progressDialog = ProgressDialog.show(getContext(), "", "Please Wait...", true);
                requestProduct();
            }
        });
    }

    private void obserber() {
        viewModel.getLoginUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null && users.size()>0){
                    user=users.get(0);
                }
            }
        });
    }


    private void requestProduct(){
        String productName=binding.reqProductName.getText().toString();
        String productPrice=binding.productReqPrice.getText().toString();
        if (productName.isEmpty()){
            showMessage("Product Name Empty");
            progressDialog.hide();
            return;
        }
        else if (productPrice.isEmpty()){
            showMessage("Product Price Empty");
            progressDialog.hide();
            return;
        }
        else if (user==null){
            SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(getContext())
                    .setLayoutId(binding.rootLayout)
                    .setMessage("Please Login First")
                    .longLength()
                    .setListener("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.action_productReqFragment_to_loginSignUpFragment);
                        }
                    })
                    .build();
            snackbarBuilder.show();
            progressDialog.hide();
            return;
        }

        viewModel.uploadFile(bitmap,productName,productPrice,user.getCustomerFullName(),user.getCustomerMobileNumber(),user.getCustomerId()).observe(getViewLifecycleOwner(), new Observer<ReqModel>() {
            @Override
            public void onChanged(ReqModel reqModel) {
                progressDialog.hide();
                if (reqModel != null){
                    showMessage(reqModel.getMessage());
                }else {
                    showMessage("Error !");
                }
                binding.requestBtn.setEnabled(true);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO&& data!= null) {
            Uri imageUri = data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(),imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap != null){
                binding.requestBtn.setEnabled(true);
                binding.productImage.setImageBitmap(bitmap);
            }
        }
    }

    private void showMessage(String message){
        SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(getContext())
                .setLayoutId(binding.rootLayout)
                .setMessage(message)
                .build();
        snackbarBuilder.show();
    }

}
