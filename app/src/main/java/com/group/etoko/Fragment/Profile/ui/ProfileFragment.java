package com.group.etoko.Fragment.Profile.ui;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentProfileBinding;
import com.group.etoko.util.Constants;
import com.group.etoko.Fragment.Profile.viewmodel.ProfileViewModel;
import com.group.etoko.util.InternetConnectivityInfo;
import com.group.etoko.util.MyAlertDialog;
import com.group.etoko.util.SnackbarBuilder;

import java.util.List;

import static com.group.etoko.util.Constants.CUSTOMERID;
import static com.group.etoko.util.Constants.INTERNETERROR;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    ProfileViewModel viewModel;
    User curentUser;
    String homeAddress=" ";
    private ProgressDialog progressDialog;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        observer();

        binding.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_global_fragmentProfileEdit2);
            }
        });

        binding.btnOrdersHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternetConnectivityInfo internetConnectivityInfo= new InternetConnectivityInfo(view.getContext());
                if(internetConnectivityInfo.isConnected()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(CUSTOMERID, curentUser.getCustomerId());
                    Navigation.findNavController(view).navigate(R.id.orderHistoryFragment, bundle);
                }
                else{
                    setSnackbarText(INTERNETERROR);
                }
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAlertDialog alertDialog=new MyAlertDialog(getContext());
               alertDialog.showConfirmDialog("Are You Sure?","Ok","Cancel");
               alertDialog.okButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       alertDialog.cancel();
                       viewModel.clearUser();
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

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginSignUpFragment);
            }
        });

        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        if (curentUser != null && curentUser.getCustomerRefCode()!=null){
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_refferral_id_pre)
                                    +curentUser.getCustomerRefCode()+
                                    getResources().getString(R.string.share_refferral_id_post));
                            sendIntent.setType("text/plain");
                            Intent shareIntent = Intent.createChooser(sendIntent, null);
                            startActivity(shareIntent);
                        }

            }
        });

    }

    private void observer() {
        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null && users.size() > 0) {
                    viewModel.UserInfoByMobile(users.get(0).getCustomerMobileNumber());
                    binding.tvReferral.setVisibility(View.VISIBLE);
                    binding.referral.setVisibility(View.VISIBLE);
                    binding.ivReferral.setVisibility(View.VISIBLE);
                    binding.tvName.setText(users.get(0).getCustomerFullName());
                    binding.tvEmail.setText(users.get(0).getCustomerEmailAddress());
                    binding.tvPhone.setText(users.get(0).getCustomerMobileNumber());
                    binding.tvAddress.setText(users.get(0).getCustomerAddress());
                    binding.tvReferralIdValue.setText((users.get(0).getCustomerRefCode()));
                    binding.tvReferralBalanceValue.setText(Constants.Taka_sign + users.get(0).getCustomerRefBalance());
                    curentUser = users.get(0);

                } else {
                    showGuestData();
                }


                // binding.tvAddress.setText(user.getAddress());

              /*  binding.totalOrderValue.setText(user.getTotal_order());
                binding.pendingValue.setText(user.getPending_order());
                binding.totalOrderInProcessValue.setText(user.getTotal_order_in_process());
                binding.totalOrderShipmentValue.setText(user.getTotal_order_in_shipment());
                binding.totalCompleteOrderValue.setText(user.getTotal_complete_order());
                binding.cancelledOrderValue.setText(user.getTotal_cancelled_order());*/
            }


        });
        viewModel.getUserFromServer().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null && users.size() > 0) {
                    binding.btnLogin.setVisibility(View.GONE);
                    binding.ivAddress.setVisibility(View.VISIBLE);
                    binding.tvReferral.setVisibility(View.VISIBLE);
                    binding.referral.setVisibility(View.VISIBLE);
                    binding.ivReferral.setVisibility(View.VISIBLE);
                    binding.tvEdit.setVisibility(View.VISIBLE);
                    binding.btnOrdersHistory.setVisibility(View.VISIBLE);
                    binding.btnLogout.setVisibility(View.VISIBLE);
                    binding.tvName.setText(users.get(0).getCustomerFullName());
                    binding.tvEmail.setText(users.get(0).getCustomerEmailAddress());
                    binding.tvPhone.setText(users.get(0).getCustomerMobileNumber());
                    binding.tvReferralIdValue.setText((users.get(0).getCustomerRefCode()));
                    binding.tvReferralBalanceValue.setText(Constants.Taka_sign + users.get(0).getCustomerRefBalance());
                    curentUser = users.get(0);
                }
            }
        });

    }

    private void showGuestData() {
        binding.btnLogin.setVisibility(View.VISIBLE);
        binding.tvName.setText("Guest");
        binding.tvEmail.setText("Guest");
        binding.tvPhone.setText("Guest");
        binding.ivAddress.setVisibility(View.INVISIBLE);
        binding.tvAddress.setVisibility(View.INVISIBLE);
        binding.tvReferral.setVisibility(View.INVISIBLE);
        binding.referral.setVisibility(View.INVISIBLE);
        binding.ivReferral.setVisibility(View.INVISIBLE);
        binding.tvEdit.setVisibility(View.INVISIBLE);
        binding.btnOrdersHistory.setVisibility(View.INVISIBLE);
        binding.btnLogout.setVisibility(View.INVISIBLE);
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
