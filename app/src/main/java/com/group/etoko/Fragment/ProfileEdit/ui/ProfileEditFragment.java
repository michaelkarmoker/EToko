package com.group.etoko.Fragment.ProfileEdit.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.ProfileEdit.model.ProfileUpdateResponse;
import com.group.etoko.Fragment.ProfileEdit.viewmodel.ProfileEditViewModel;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentProfileEditBinding;
import com.group.etoko.util.AlertDialogBox;
import com.group.etoko.util.MyAlertDialog;
import com.group.etoko.util.SnackbarBuilder;

import java.util.List;


public class ProfileEditFragment extends Fragment {
    public ProfileEditViewModel viewModel;
    public User user;
    public FragmentProfileEditBinding binding;
    NavController navController;
    ProgressDialog progressDialog;

    public ProfileEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileEditBinding.inflate(inflater, container, false);

//update button=========
        binding.btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAlertDialog alertDialog=new MyAlertDialog(getContext());
                alertDialog.showConfirmDialog("Are You Sure?","Ok","Cancel");
                alertDialog.okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        gatdata();
                    }
                });

                alertDialog.cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileEditViewModel.class);
        observer();
        progressDialog =new ProgressDialog(getContext(), R.style.CustomAlertDialogStyle);
        progressDialog.setMessage("Please Wait...");
    }

    private void observer() {

        viewModel.getuser().observe(getViewLifecycleOwner(), new Observer<List<com.group.etoko.Fragment.LoginSignUp.model.User>>() {
            @Override
            public void onChanged(List<com.group.etoko.Fragment.LoginSignUp.model.User> users) {
                if (users != null && users.size()> 0) {
                    user = users.get(0);
                    binding.editUsername.setText(user.getCustomerFullName());
                    binding.editEmail.setText(user.getCustomerEmailAddress());
                    binding.editPhone.setText(user.getCustomerMobileNumber());
                    binding.editAddress.setText(user.getCustomerAddress());
                }
            }
        });


        viewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<ProfileUpdateResponse>() {
            @Override
            public void onChanged(ProfileUpdateResponse profileUpdateResponse) {
                progressDialog.dismiss();
                if (profileUpdateResponse != null) {
                    if (profileUpdateResponse.getStatus()) {
                        AlertDialogBox.Box(getContext(), "Profile Update", profileUpdateResponse.getMessage());
                        navController = Navigation.findNavController(getView());
                        navController.popBackStack();
                    } else {
                        setSnackbarText(profileUpdateResponse.getMessage());
                        navController = Navigation.findNavController(getView());
                        navController.popBackStack();
                    }
                }
            }
        });


    }

    private void gatdata() {

        if (!TextUtils.isEmpty(binding.editUsername.getText().toString())) {
            if (!TextUtils.isEmpty(binding.editEmail.getText().toString())) {
                if (Patterns.EMAIL_ADDRESS.matcher(binding.editEmail.getText().toString()).matches()) {
                    user.setCustomerFullName(binding.editUsername.getText().toString());
                    user.setCustomerEmailAddress(binding.editEmail.getText().toString());
                    if(!TextUtils.isEmpty(binding.editAddress.getText().toString())){
                        user.setCustomerAddress(binding.editAddress.getText().toString());

                    }
                    viewModel.setuserdata(user);
                    progressDialog.show();
                   /* if (!TextUtils.isEmpty(binding.editAddress.getText().toString())) {
                        user.setCustomerFullName(binding.editUsername.getText().toString());
                        user.setCustomerEmailAddress(binding.editEmail.getText().toString());
                       // user.setAddress(binding.editAddress.getText().toString());
                        viewModel.setuserdata(user);
                    } else {
                        binding.editAddress.setError("This is required field");
                        binding.editAddress.requestFocus();
                    }*/
                } else {
                    binding.editEmail.setError("Enter valid email!");
                    binding.editEmail.requestFocus();
                }
            } else {
                binding.editEmail.setError("This is required field");
                binding.editEmail.requestFocus();
            }
        } else {
            binding.editUsername.setError("This is required field");
            binding.editUsername.requestFocus();
        }



    }
    private void setSnackbarText(String text) {
        SnackbarBuilder snackbarBuilder = new SnackbarBuilder.Builder(getContext())
                .setLayoutId(binding.getRoot())
                .setMessage(text)
                .build();
        snackbarBuilder.show();
        ;
    }
}
