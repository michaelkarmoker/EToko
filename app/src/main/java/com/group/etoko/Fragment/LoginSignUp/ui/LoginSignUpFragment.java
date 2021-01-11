package com.group.etoko.Fragment.LoginSignUp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.group.etoko.Fragment.LoginSignUp.model.CheckUserRegisterModel;
import com.group.etoko.Fragment.LoginSignUp.model.UserContainer;
import com.group.etoko.Fragment.LoginSignUp.viewmodel.LoginSignUPViewModel;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentLoginSignUpBinding;
import com.group.etoko.util.MySharedPreference;
import com.group.etoko.util.SnackbarBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.group.etoko.util.Constants.IS_USER_ALREADY_REGISTER;
import static com.group.etoko.util.Constants.OTP_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginSignUpFragment extends Fragment {

    private FragmentLoginSignUpBinding binding;
    private LoginSignUPViewModel viewModel;

    public LoginSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentLoginSignUpBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModel=new ViewModelProvider(this).get(LoginSignUPViewModel.class);
        observer();

        binding.phoneNumberTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    binding.phoneNumberTxt.setHint("0177");
                }
            }
        });

        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.continueButton.setEnabled(false);
                String number= Objects.requireNonNull(binding.phoneNumberTxt.getText()).toString();
                viewModel.isUserAlreadyRegister(number);
            }
        });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.registerButton.setEnabled(false);
                String number= Objects.requireNonNull(binding.phoneNumberTxt.getText()).toString();
                String otp= Objects.requireNonNull(binding.OTPNumberTxt.getText()).toString();
                String fullName= Objects.requireNonNull(binding.FullNameTxt.getText()).toString();
                String email= Objects.requireNonNull(binding.emailTxt.getText()).toString();
                String referralCode= Objects.requireNonNull(binding.referralTxt.getText()).toString();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = new Date();
                String currentDate=formatter.format(date);
                viewModel.registerUser(getContext(),number,otp,fullName,email,currentDate,referralCode);
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loginButton.setEnabled(false);
                String number= Objects.requireNonNull(binding.phoneNumberTxt.getText()).toString();
                String otp= Objects.requireNonNull(binding.OTPNumberTxt.getText()).toString();
                viewModel.loginUser(getContext(),otp,number);
            }
        });

        Glide.with(requireActivity())
                .load(R.drawable.login)
                .placeholder(R.drawable.login)
                .into(binding.loginImage);

        binding.resendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.resendButton.setVisibility(View.GONE);
                String number= Objects.requireNonNull(binding.phoneNumberTxt.getText()).toString();
                viewModel.isUserAlreadyRegister(number);
            }
        });

    }

    private void observer() {

        viewModel.getIsNumberValid().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.continueButton.setEnabled(true);
                binding.registerButton.setEnabled(true);
                binding.loginButton.setEnabled(true);
                SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(getContext())
                        .setLayoutId(binding.rootLayout)
                        .setMessage(s)
                        .build();
                snackbarBuilder.show();
            }
        });
        viewModel.getUserRegisterStatus().observe(getViewLifecycleOwner(), new Observer<CheckUserRegisterModel>() {
            @Override
            public void onChanged(CheckUserRegisterModel checkUserRegisterModel) {
                if (checkUserRegisterModel.getFailedCode().equals("2")){
                    SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(getContext())
                            .setLayoutId(binding.rootLayout)
                            .setMessage(checkUserRegisterModel.getMessage())
                            .build();
                    snackbarBuilder.show();
                    binding.continueButton.setEnabled(true);
                }else {
                    binding.phoneNumberLayout.setEnabled(false);
                    String number= Objects.requireNonNull(binding.phoneNumberTxt.getText()).toString();
                    viewModel.sendOTP(number);
                    MySharedPreference.getInstance(getContext())
                            .edit().putBoolean(IS_USER_ALREADY_REGISTER,checkUserRegisterModel.getStatus()).apply();
                }

            }
        });

        viewModel.getIsOTPSent().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.resendButton.setVisibility(View.VISIBLE);
                MySharedPreference.getInstance(getContext())
                        .edit().putInt(OTP_CODE,integer).apply();
                boolean isUserAlreadyRegister=MySharedPreference.getInstance(getContext()).getBoolean(IS_USER_ALREADY_REGISTER,false);
                viewModel.setLayout(binding,isUserAlreadyRegister);
            }
        });


        viewModel.getRegisterUser().observe(getViewLifecycleOwner(), new Observer<UserContainer>() {
            @Override
            public void onChanged(UserContainer userContainer) {
                MySharedPreference.getInstance(getContext())
                        .edit().putInt(OTP_CODE,-1).apply();
                if (userContainer.getStatus()){
                    Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).popBackStack();
                }
            }
        });

        viewModel.getLoginUser().observe(getViewLifecycleOwner(), new Observer<UserContainer>() {
            @Override
            public void onChanged(UserContainer userContainer) {
                MySharedPreference.getInstance(getContext())
                        .edit().putInt(OTP_CODE,-1).apply();
                if (userContainer.getStatus()){
                    Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).popBackStack();
                }
            }
        });
    }
}
