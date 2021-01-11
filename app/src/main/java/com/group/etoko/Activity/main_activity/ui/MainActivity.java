package com.group.etoko.Activity.main_activity.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.badge.BadgeDrawable;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Activity.main_activity.adapter.CategoryAdapter;
import com.group.etoko.Activity.main_activity.adapter.OfferProductAdapter;
import com.group.etoko.Activity.main_activity.adapter.SeasonalCategoryAdapter;
import com.group.etoko.Activity.main_activity.model.CategoryAndSubCategory;
import com.group.etoko.Activity.main_activity.viewmodel.MainActivityViewModel;
import com.group.etoko.R;
import com.group.etoko.Service.NetworkStateRecever;
import com.group.etoko.databinding.ActivityMainBinding;
import com.group.etoko.model.SeasonalCategory;
import com.group.etoko.util.MyAlertDialog;
import com.group.etoko.util.SnackbarBuilder;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  {

    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private CategoryAdapter normalProductListAdapter;
    private OfferProductAdapter offerProductAdapter;
    private SeasonalCategoryAdapter adapter;
    private BadgeDrawable badge ;
    private ShimmerFrameLayout shimmerLayout;
    private NetworkStateRecever networkStateRecever;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);
        observer();

        badge= binding.bottomNavigation.getOrCreateBadge(R.id.cartFragment);

        navController= Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration=new AppBarConfiguration.Builder(new int[]{R.id.homeFragment,R.id.profileFragment,R.id.cartFragment,R.id.categoryFragment}).setOpenableLayout(binding.navDrawerLayout).build();
        NavigationUI.setupWithNavController(binding.bottomNavigation,navController);
        NavigationUI.setupWithNavController(binding.drawerNavigation,navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.productDetailsFragment || destination.getId() == R.id.checkoutFragment
                        || destination.getId()==R.id.productsGridFragment||destination.getId()==R.id.loginSignUpFragment
                        || destination.getId()==R.id.productReqFragment|| destination.getId()==R.id.serviceRequest
                        ||destination.getId()== R.id.aboutFragment
                        || destination.getId()==R.id.orderHistoryFragment||destination.getId()==R.id.orderHistoryDetailsFragment
                        || destination.getId()==R.id.fragmentProfileEdit
                )
                {
                    binding.bottomNavigation.setVisibility(View.GONE);
                    Objects.requireNonNull(binding.toolbar.getNavigationIcon()).setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                    binding.toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                else if (destination.getId()==R.id.action_homeFragment_to_aboutFragment){
                    binding.toolbar.setBackgroundColor(Color.TRANSPARENT);
                }
                else {
                    binding.bottomNavigation.setVisibility(View.VISIBLE);
                    Objects.requireNonNull(binding.toolbar.getNavigationIcon()).setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                    binding.toolbar.setTitleTextColor(getResources().getColor(R.color.black));
                    binding.toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });


        binding.drawerItem.loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.navDrawerLayout.isOpen()){
                    binding.navDrawerLayout.close();
                }
               navController.navigate(R.id.action_global_loginSignUpFragment);
            }
        });

        binding.drawerItem.logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.navDrawerLayout.isOpen()){
                    binding.navDrawerLayout.close();
                }
                MyAlertDialog alertDialog=new MyAlertDialog(MainActivity.this);
                alertDialog.showConfirmDialog("Are You Sure?","Ok","Cancel");
                alertDialog.okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        viewModel.clearUser();
                        binding.drawerItem.navigationGuestUserLayout.setVisibility(View.VISIBLE);
                        binding.drawerItem.navigationLoginUserLayout.setVisibility(View.GONE);
                        binding.drawerItem.logoutLayout.setVisibility(View.GONE);
                        binding.drawerItem.loginLayout.setVisibility(View.VISIBLE);
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

        binding.drawerItem.navigationReqProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.navDrawerLayout.isOpen()){
                    binding.navDrawerLayout.close();
                }
                navController.navigate(R.id.action_global_productReqFragment);
            }
        });

        binding.drawerItem.navigationReqServiceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.navDrawerLayout.isOpen()){
                    binding.navDrawerLayout.close();
                }
                navController.navigate(R.id.action_global_serviceRequest);
            }
        });

        binding.drawerItem.navigationAboutCholoshopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.navDrawerLayout.isOpen()){
                    binding.navDrawerLayout.close();
                }
                navController.navigate(R.id.action_global_aboutFragment2);
            }
        });

        binding.drawerItem.navigationCllNowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCallPermissionGranted()){
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:01888052999"));
                    startActivity(callIntent);
                }
            }
        });

    }

    private void observer() {

        //Observe User Status
        viewModel.getCurrentUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null && users.size()>0){
                    User user=users.get(0);
                    binding.drawerItem.navigationGuestUserLayout.setVisibility(View.GONE);
                    binding.drawerItem.navigationLoginUserLayout.setVisibility(View.VISIBLE);
                    binding.drawerItem.logoutLayout.setVisibility(View.VISIBLE);
                    binding.drawerItem.navigationLoginUserName.setText(user.getCustomerFullName());
                    binding.drawerItem.navigationLoginUserEmail.setText(user.getCustomerEmailAddress());
                    binding.drawerItem.navigationLoginUserPhone.setText(user.getCustomerMobileNumber());
                }
            }
        });

        viewModel.getCategoryAndSubCategory().observe(this, new Observer<List<CategoryAndSubCategory>>() {
            @Override
            public void onChanged(List<CategoryAndSubCategory> categoryAndSubCategories) {
                if (categoryAndSubCategories != null){
                    normalProductListAdapter=new CategoryAdapter(MainActivity.this,categoryAndSubCategories,navController,binding.navDrawerLayout);
                    binding.drawerItem.navigationNormalProductList.setHasFixedSize(true);
                    binding.drawerItem.navigationNormalProductList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.drawerItem.navigationNormalProductList.setAdapter(normalProductListAdapter);
                }
            }
        });

        viewModel.getLogoutStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean logout) {
                if (logout){
                    SnackbarBuilder snackbarBuilder=new SnackbarBuilder.Builder(MainActivity.this)
                            .setLayoutId(binding.navDrawerLayout)
                            .setMessage("Success")
                            .build();
                    snackbarBuilder.show();
                }
            }
        });

        viewModel.getSeasonalCategory().observe(this, new Observer<List<SeasonalCategory>>() {
            @Override
            public void onChanged(List<SeasonalCategory> seasonalCategories) {
                if (seasonalCategories != null && seasonalCategories.size()>0)
                    adapter=new SeasonalCategoryAdapter(seasonalCategories,navController,binding.navDrawerLayout);
                    binding.drawerItem.navigationSeasonalProductList.setHasFixedSize(true);
                    binding.drawerItem.navigationSeasonalProductList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.drawerItem.navigationSeasonalProductList.setAdapter(adapter);
            }
        });

        viewModel.getCartCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer != null && integer>0){
                    badge.setVisible(true);
                    badge.setNumber(integer);
                }else {
                    badge.setVisible(false);
                    badge.clearNumber();
                }
            }
        });
      
        viewModel.subscribeToGlobalTopic();
    }

    public void updateToolbar(String title) {
        binding.toolbar.setTitle(title);
    }

    public  boolean isCallPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        networkStateRecever=new NetworkStateRecever(MainActivity.this,binding.navDrawerLayout);
        registerReceiver(networkStateRecever,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkStateRecever);
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
        ActivityNavigator.applyPopAnimationsToPendingTransition(this);
    }
}


