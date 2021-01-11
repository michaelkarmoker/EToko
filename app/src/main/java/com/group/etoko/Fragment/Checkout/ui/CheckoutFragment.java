package com.group.etoko.Fragment.Checkout.ui;

        import android.Manifest;
        import android.app.ProgressDialog;
        import android.content.pm.PackageManager;
        import android.os.Bundle;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProvider;
        import androidx.navigation.Navigation;
        import androidx.recyclerview.widget.GridLayoutManager;

        import android.text.TextUtils;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.group.etoko.Fragment.Cart.model.CartContainer;
        import com.group.etoko.Fragment.Checkout.ClickListener.RecycleItemClickListener;
        import com.group.etoko.Fragment.Checkout.adapter.AdapterDeliveryMethod;
        import com.group.etoko.Fragment.Checkout.adapter.SpacesItemDecoDeliveryMethod;
        import com.group.etoko.Fragment.Checkout.model.Cheakout;
        import com.group.etoko.Fragment.Checkout.model.ConfirmOrderResponse;
        import com.group.etoko.Fragment.Checkout.model.DeliveryMethod;
        import com.group.etoko.Fragment.Checkout.model.ProductVat;
        import com.group.etoko.Fragment.Checkout.model.ShippingArea;
        import com.group.etoko.Fragment.Checkout.model.ShippingZone;
        import com.group.etoko.Fragment.Checkout.viewmodel.CheckoutFragmentViewModel;
        import com.group.etoko.Fragment.LoginSignUp.model.User;
        import com.group.etoko.R;
        import com.group.etoko.databinding.FragmentCheckoutBinding;
        import com.group.etoko.model.NetworkStatusModelForCheckout;
        import com.group.etoko.util.HideKeyBoard;
        import com.group.etoko.util.MyAlertDialog;
        import com.group.etoko.util.SnackbarBuilder;
        import com.group.etoko.util.Constants;
        import com.group.etoko.model.Product;
        import com.jaredrummler.materialspinner.MaterialSpinner;

        import java.util.ArrayList;
        import java.util.List;
        import static com.group.etoko.util.Constants.COUPONAMOUNT;

        import static com.group.etoko.util.Constants.numberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckoutFragment extends Fragment {

    private FragmentCheckoutBinding binding;
    private CheckoutFragmentViewModel viewModel;
    private User currentUser;
    private String PAYMENT_METHOD = "";
    private String DELIVERY_METHOD = "";
    private ProgressDialog progressDialog;
    private int deliveryMethodPosition = -1;
    private static double total, subTotal, discount, vat, deliveryCost, netProductPrice, totalAditonalCost, refBlnc, couponAmount;
    private List<Product> productList = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Cheakout cheakout;


    public CheckoutFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false);
        return binding.getRoot();



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CheckoutFragmentViewModel.class);
        progressDialog = ProgressDialog.show(getContext(), "", "Please Wait...", true);
        Bundle bundle=getArguments();
        if(bundle!=null){
            couponAmount=Double.parseDouble(bundle.getString(COUPONAMOUNT));
        }
        setClickListner();
        setObserber();
        binding.spinnerDeliveryZone.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                viewModel.getshippingareaByZone(position);
                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });


       boolean permission= viewModel.isLocationSatisfied(getActivity());
       if (!permission){
           if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                   ActivityCompat.requestPermissions(requireActivity(),
                           new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                           202);
               }
           }else {
               ActivityCompat.requestPermissions(requireActivity(),
                       new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                       202);
           }
       }
    }


    private void setObserber() {

        viewModel.getUserMobileNo().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null && users.size() > 0) {
                    if (users.get(0).getCustomerMobileNumber() != null) {

                        viewModel.getUserInfoByMobile(users.get(0).getCustomerMobileNumber());
                        binding.editAddress.setText(users.get(0).getCustomerAddress());
                    }

                }
            }
        });

        viewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null && users.size() > 0) {
                    currentUser = users.get(0);
                    if (currentUser.getCustomerRefBalance() != null) {
                        if (Double.parseDouble(currentUser.getCustomerRefBalance()) <= 0) {
                            binding.referralInfoContainer.setVisibility(View.GONE);
                            binding.referralUseBlnTv.setVisibility(View.GONE);
                            binding.referralUseBlnValueTv.setVisibility(View.GONE);
                        } else {
                            binding.referralUseBlnTv.setVisibility(View.GONE);
                            binding.referralUseBlnValueTv.setVisibility(View.GONE);
                            binding.referralInfoContainer.setVisibility(View.VISIBLE);
                            binding.referralBalanceTv.setText("Your Balance is: " + currentUser.getCustomerRefBalance());
                        }
                    }
                    else{
                        binding.referralInfoContainer.setVisibility(View.GONE);
                        binding.referralUseBlnTv.setVisibility(View.GONE);
                        binding.referralUseBlnValueTv.setVisibility(View.GONE);
                    }
                    binding.shippingInfoName.setText(currentUser.getCustomerFullName());
                    binding.shippingInfoMobileNo.setText(currentUser.getCustomerMobileNumber());

                }
            }
        });
        viewModel.getShippingZones().observe(getViewLifecycleOwner(), new Observer<List<ShippingZone>>() {
            @Override
            public void onChanged(List<ShippingZone> shippingZones) {
                binding.spinnerDeliveryZone.setItems(shippingZones);
            }
        });

        viewModel.getShippingAreas().observe(getViewLifecycleOwner(), new Observer<List<ShippingArea>>() {
            @Override
            public void onChanged(List<ShippingArea> shippingAreas) {
                binding.spinnerAddress.setItems(shippingAreas);
            }
        });

        viewModel.getDeliveryMethod().observe(getViewLifecycleOwner(), new Observer<List<DeliveryMethod>>() {
            @Override
            public void onChanged(List<DeliveryMethod> deliveryMethods) {
                binding.deliveryMethodRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
                binding.deliveryMethodRV.setHasFixedSize(true);
                AdapterDeliveryMethod adpter = new AdapterDeliveryMethod(deliveryMethods, new RecycleItemClickListener() {
                    @Override
                    public void position(int position) {
                        deliveryMethodPosition = position;
                        deliveryCost = Double.parseDouble(deliveryMethods.get(position).getMethodRate());
                        binding.deliveryChargeValueTv.setText(Constants.Taka_sign + String.format(numberFormat, deliveryCost+totalAditonalCost));
                        Double tempTotal = (total + deliveryCost )-refBlnc;
                        binding.TotalValueTv.setText(Constants.Taka_sign + String.format(numberFormat, tempTotal));
                    }
                });

                binding.deliveryMethodRV.addItemDecoration(new SpacesItemDecoDeliveryMethod(20));
                binding.deliveryMethodRV.setAdapter(adpter);
                progressDialog.dismiss();
            }
        });



        viewModel.getCartContainer().observe(getViewLifecycleOwner(), new Observer<List<CartContainer>>() {
            @Override
            public void onChanged(List<CartContainer> cartContainers) {
                deliveryCost=0.0;
                total = 0.0;
                subTotal = 0.0;
                discount = 0.0;
                totalAditonalCost = 0.0;
                netProductPrice = 0.0;
                vat = 0.0;
                refBlnc = 0.0;
                for (CartContainer container : cartContainers) {
                    subTotal = subTotal + (Double.parseDouble(container.product.getProductOriginalPrice()) * container.cart.getProductCount());
                    totalAditonalCost = totalAditonalCost+(Double.parseDouble(container.product.getProductShippingCost())*container.cart.getProductCount());
                    productList.add(container.product);
                    if (container.product.getProductDiscountAmount() != null)
                        discount = discount +(Double.parseDouble(container.product.getProductDiscountAmount()) * container.cart.getProductCount());
                }
                netProductPrice = subTotal - discount;
                viewModel.getProductVat().observe(getViewLifecycleOwner(), new Observer<List<ProductVat>>() {
                    @Override
                    public void onChanged(List<ProductVat> productVats) {

                        if (productVats != null && productVats.size() >0) {
                            Double vatPercent = Double.parseDouble(productVats.get(0).getVatPercent());
                            vat = (netProductPrice * vatPercent) / 100;
                            total = (netProductPrice + vat+totalAditonalCost)-couponAmount;
                            discount=discount+couponAmount;
                            binding.subTotalValueTv.setText(Constants.Taka_sign + String.format(numberFormat, subTotal));
                            binding.discountValueTv.setText("-" + Constants.Taka_sign + String.format(numberFormat, discount));
                            binding.deliveryChargeValueTv.setText(Constants.Taka_sign + String.format(numberFormat, deliveryCost+totalAditonalCost));
                            binding.productTaxValueTv.setText(Constants.Taka_sign + String.format(numberFormat, vat));
                            binding.TotalValueTv.setText(Constants.Taka_sign + String.format(numberFormat, total));
                        }


                    }

                });


            }
        });

        viewModel.getConfirmOderResponseLive().observe(getViewLifecycleOwner(), new Observer<ConfirmOrderResponse>() {
            @Override
            public void onChanged(ConfirmOrderResponse response) {
                progressDialog.dismiss();
                if (response != null) {
                    setSnackbarText(response.getMessage());
                    if(response.getStatus()){
                        viewModel.clearCart(cheakout);
                        viewModel.storeDataToFirebase(getActivity(),currentUser,cheakout,response.getData());
                        binding.checkoutLayout.setVisibility(View.GONE);
                        binding.completeLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        viewModel.getNetworkStatus().observe(getViewLifecycleOwner(), new Observer<NetworkStatusModelForCheckout>() {
            @Override
            public void onChanged(NetworkStatusModelForCheckout networkStatusModelForCheckout) {
                if (networkStatusModelForCheckout != null && networkStatusModelForCheckout.getStatus()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void setClickListner() {

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheakoutDataValidation();
            }
        });

        binding.referralApplyTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HideKeyBoard.hideKeyboard(getActivity());

                if (!TextUtils.isEmpty(binding.referralEditTxt.getText().toString())) {
                    Double refBlncT = Double.parseDouble(currentUser.getCustomerRefBalance());
                    Double userRefBlncT = Double.parseDouble(binding.referralEditTxt.getText().toString());
                    if (userRefBlncT >= 0 && userRefBlncT <= refBlncT) {
                        refBlnc = userRefBlncT;
                        Double tempTotal = (total + deliveryCost)- refBlnc;
                        binding.referralUseBlnTv.setVisibility(View.VISIBLE);
                        binding.referralUseBlnValueTv.setVisibility(View.VISIBLE);
                        binding.referralUseBlnValueTv.setText("-" +Constants.Taka_sign + String.format(numberFormat, refBlnc));
                        binding.TotalValueTv.setText(Constants.Taka_sign + String.format(numberFormat, tempTotal));
                        binding.referralBalanceTv.setText("Your Balance is: " + String.format(numberFormat,(refBlncT-refBlnc)));
                        setSnackbarText("Using " + Constants.Taka_sign + refBlnc + " of your Referral Balance");
                    } else {
                        setSnackbarText("Enter valid Balance!");
                    }
                } else {
                    setSnackbarText("Enter Balance!");
                }
            }
        });
        binding.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        binding.spinnerDeliveryZone.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                viewModel.getshippingareaByZone(position);
            }
        });
    }

    private void cheakoutDataValidation() {
        if (!TextUtils.isEmpty(binding.shippingInfoName.getText().toString())) {
            if (!TextUtils.isEmpty(binding.shippingInfoMobileNo.getText().toString())) {
                if (binding.spinnerDeliveryZone.getSelectedIndex() > 0) {
                    int zoneIndex = binding.spinnerDeliveryZone.getSelectedIndex();
                    if (binding.spinnerAddress.getSelectedIndex() > 0) {
                        int areaIndex = binding.spinnerAddress.getSelectedIndex();
                        if (!TextUtils.isEmpty(binding.editAddress.getText().toString())) {
                            currentUser.setCustomerAddress(binding.editAddress.getText().toString());
                            if (deliveryMethodPosition > -1) {
                                MyAlertDialog alertDialog=new MyAlertDialog(getContext());
                                alertDialog.showConfirmDialog("Are You Sure?","Ok","Cancel");
                                alertDialog.okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.cancel();
                                        progressDialog.show();
                                        startCheckout(zoneIndex,areaIndex);
                                    }
                                });

                                alertDialog.cancelButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.cancel();
                                        progressDialog.dismiss();
                                    }
                                });
                                alertDialog.show();
                            } else {
                                setSnackbarText("Select Delivery Method");

                                progressDialog.hide();
                            }
                        } else {
                            binding.editAddress.setError("Enter Your Home Address");
                            binding.editAddress.requestFocus();
                            progressDialog.hide();
                        }
                    } else {
                        setSnackbarText("Select Your Area");
                        progressDialog.hide();
                    }
                } else {
                    binding.spinnerDeliveryZone.requestFocus();
                    setSnackbarText("Select your Zone");
                    progressDialog.hide();
                }
            } else {
                binding.shippingInfoMobileNo.setError("Enter Your Mobile Number");
                binding.shippingInfoMobileNo.requestFocus();
                progressDialog.hide();
            }
        } else {
            binding.shippingInfoName.setError("Enter your Name");
            binding.shippingInfoName.requestFocus();
            progressDialog.hide();
        }


    }

    private void startCheckout(int zoneIndex,int areaIndex) {
        Log.e("Srart","startCheckout");
        cheakout = new Cheakout(currentUser.getCustomerId().toString(),
                "null",
                binding.shippingInfoName.getText().toString(),
                binding.shippingInfoMobileNo.getText().toString(),
                viewModel.getShippingZones().getValue().get(zoneIndex).getZoneName(),
                viewModel.getShippingZones().getValue().get(zoneIndex).getZoneId(),
                viewModel.getShippingAreas().getValue().get(areaIndex).getAreaName(),
                viewModel.getShippingAreas().getValue().get(areaIndex).getAreaId(),
                binding.editAddress.getText().toString(),
                currentUser.getCustomerEmailAddress(),
                "yes",
                String.valueOf((total + deliveryCost) - refBlnc),
                String.valueOf(vat),
                String.valueOf(couponAmount),
                viewModel.getDeliveryMethod().getValue().get(deliveryMethodPosition).getMethodRate(),
                viewModel.getDeliveryMethod().getValue().get(deliveryMethodPosition).getMethodName(),
                String.valueOf(refBlnc),
                String.valueOf(totalAditonalCost),
                String.valueOf(netProductPrice),
                String.valueOf(viewModel.getCartContainer().getValue().size()),
                Constants.COD,
                productList);
        viewModel.sendConfirmOder(cheakout,currentUser);
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