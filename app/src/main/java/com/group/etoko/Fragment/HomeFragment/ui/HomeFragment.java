package com.group.etoko.Fragment.HomeFragment.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.facebook.shimmer.ShimmerFrameLayout;

import com.github.islamkhsh.viewpager2.ViewPager2;
import com.group.etoko.Fragment.HomeFragment.adapter.CarouselAdapter;
import com.group.etoko.Fragment.HomeFragment.Model.CarouselItem;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentHomeBinding;
import com.group.etoko.model.SeasonalCategory;
import com.group.etoko.Fragment.HomeFragment.Model.Collections;
import com.group.etoko.model.Category;
import com.group.etoko.model.Product;
import com.group.etoko.model.SubCategory;
import com.group.etoko.Fragment.HomeFragment.adapter.AdapterCategory;
import com.group.etoko.Fragment.HomeFragment.adapter.AdapterCollectionHome;
import com.group.etoko.Fragment.HomeFragment.adapter.AdapterExclusiveOffer;
import com.group.etoko.Fragment.HomeFragment.adapter.AdapterGroceriesSubCat;
import com.group.etoko.Fragment.HomeFragment.adapter.AdapterSeasonalCat;
import com.group.etoko.Fragment.HomeFragment.adapter.SpacesItemDecoration;
import com.group.etoko.Fragment.HomeFragment.viewmodel.HomeFragmentViewModel;
import com.group.etoko.util.HideKeyBoard;
import com.group.etoko.util.MyAlertDialog;
import com.group.etoko.util.MySharedPreference;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

import static com.group.etoko.util.Constants.ACTIONBAR_TITLE;
import static com.group.etoko.util.Constants.CATEGORY;
import static com.group.etoko.util.Constants.FEATURED_PRODUCT;
import static com.group.etoko.util.Constants.ITEMSPACE;
import static com.group.etoko.util.Constants.OFFER_PRODUCT;
import static com.group.etoko.util.Constants.RECYCLER_ITEM_POSITION;
import static com.group.etoko.util.Constants.SEARCH_WORD;

public class HomeFragment extends Fragment {

    public FragmentHomeBinding binding;
    private List<CarouselItem> items;
    private ShimmerFrameLayout shimmerLayout;
    private HomeFragmentViewModel viewModel;
    private ViewPager2.OnPageChangeCallback onPageChange;
    private int isExclusiveAdapterAlreadySet = 0;
    private int isFeturedProductAlreadySet = 0;
    private int isGroceriesAdapterAlreadySet = 0;
    private int isCollectionAdapterAlreadySet = 0;
    private AdapterExclusiveOffer exclusiveOffer;
    private AdapterExclusiveOffer featuredAdapter;
    private AdapterExclusiveOffer groceriesAdapter;
    private AdapterCollectionHome collectionAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        viewModel.callDataFromNetwork();
        viewModel.ObserveUpdate();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        observer();

        onPageChange = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.e("Position", position + "");
                binding.viewpagerIndicator.setSelection(position);
                binding.viewpagerIndicator.setAnimationType(AnimationType.WORM);
            }
        };

        binding.groceriesSeeAllTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(CATEGORY, "1");
                bundle.putString(ACTIONBAR_TITLE, "Groceries");
                Navigation.findNavController(view).navigate(R.id.action_global_productsGridFragment, bundle);
            }
        });

        binding.exclusiveOffersSeeAllTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString(OFFER_PRODUCT, "1");
                bundle.putString(ACTIONBAR_TITLE, "Exclusive Offer");
                Navigation.findNavController(view).navigate(R.id.action_global_productsGridFragment, bundle);
            }
        });

        binding.featuredProductSeeAllTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(FEATURED_PRODUCT, "1");
                bundle.putString(ACTIONBAR_TITLE, "Featured Products");
                Navigation.findNavController(view).navigate(R.id.action_global_productsGridFragment, bundle);
            }
        });

        //search product======================
        binding.searchview.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String word = binding.searchview.getText().toString();
                    if (!word.isEmpty()) {
                        Bundle bundle = new Bundle();
                        bundle.putString(SEARCH_WORD, word);
                        HideKeyBoard.hideKeyboard(requireActivity());
                        binding.searchview.setText("");
                        Navigation.findNavController(view).navigate(R.id.action_global_productsGridFragment, bundle);
                        return true;
                    }

                }
                return true;
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                MyAlertDialog alertDialog = new MyAlertDialog(getContext());
                alertDialog.showConfirmDialog("Are You Want to Exit?", "Exit", "Cancel");
                alertDialog.okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        requireActivity().finish();
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
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    private void observer() {
        viewModel.getCategorie().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if (categories != null && categories.size() > 0) {
                    binding.categoryViewRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    binding.categoryViewRv.setHasFixedSize(true);
                    AdapterCategory adapter = new AdapterCategory(categories);
                    binding.categoryViewRv.addItemDecoration(new SpacesItemDecoration(ITEMSPACE));
                    binding.categoryViewRv.setAdapter(adapter);
                    shimmerOff();
                }

            }
        });

        viewModel.getSeasonalCategory().observe(getViewLifecycleOwner(), new Observer<List<SeasonalCategory>>() {
            @Override
            public void onChanged(List<SeasonalCategory> seasonalCategories) {
                if (seasonalCategories != null && seasonalCategories.size() > 0) {
                    binding.seasonalCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    binding.seasonalCategory.setHasFixedSize(true);
                    AdapterSeasonalCat adapter = new AdapterSeasonalCat(seasonalCategories);
                    binding.seasonalCategory.addItemDecoration(new SpacesItemDecoration(ITEMSPACE));
                    binding.seasonalCategory.setAdapter(adapter);
                    shimmerOff();
                }
            }
        });

        viewModel.getGroceriesSubCat().observe(getViewLifecycleOwner(), new Observer<List<SubCategory>>() {
            @Override
            public void onChanged(List<SubCategory> groceriesSubCats) {
                if (groceriesSubCats != null && groceriesSubCats.size() > 0) {
                    binding.groceriesSubcatRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    binding.groceriesSubcatRv.setHasFixedSize(true);
                    AdapterGroceriesSubCat adapter = new AdapterGroceriesSubCat(groceriesSubCats);
                    binding.groceriesSubcatRv.setAdapter(adapter);
                    shimmerOff();
                }

            }
        });

        viewModel.getExcluciveOfferProduct().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (products != null && products.size() > 0) {
                    if (isExclusiveAdapterAlreadySet == 0) {
                        isExclusiveAdapterAlreadySet++;
                        binding.exclusiveOffersLayout.setVisibility(View.VISIBLE);
                        binding.exclusiveOffersRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        binding.exclusiveOffersRv.setHasFixedSize(true);
                        exclusiveOffer = new AdapterExclusiveOffer(new ArrayList<>(products), getContext());
                        binding.exclusiveOffersRv.setAdapter(exclusiveOffer);
                        shimmerOff();
                    }

                } else {
                    binding.exclusiveOffersLayout.setVisibility(View.GONE);
                }
            }


        });

        viewModel.getFeaturedProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (products != null && products.size() > 0) {
                    if (isFeturedProductAlreadySet == 0) {
                        isFeturedProductAlreadySet++;
                        binding.featuredProductLayout.setVisibility(View.VISIBLE);
                        binding.featuredProductRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        binding.featuredProductRv.setHasFixedSize(true);
                        featuredAdapter = new AdapterExclusiveOffer(new ArrayList<>(products), getContext());
                        binding.featuredProductRv.setAdapter(featuredAdapter);
                    }

                } else {
                    binding.featuredProductLayout.setVisibility(View.GONE);
                }
            }
        });
        viewModel.getGroceriesProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> gProducts) {
                if (gProducts != null && gProducts.size() > 0) {
                    if (isGroceriesAdapterAlreadySet == 0) {
                        isGroceriesAdapterAlreadySet++;
                        binding.groceriesProductsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        binding.groceriesProductsRv.setHasFixedSize(true);
                        groceriesAdapter = new AdapterExclusiveOffer(new ArrayList<>(gProducts), getContext());
                        binding.groceriesProductsRv.setAdapter(groceriesAdapter);
                        shimmerOff();
                    }

                }

            }
        });
        viewModel.getCollections().observe(getViewLifecycleOwner(), new Observer<List<Collections>>() {
            @Override
            public void onChanged(List<Collections> collections) {
                if (collections != null && collections.size() > 0) {
                    if (isCollectionAdapterAlreadySet == 0) {
                        isCollectionAdapterAlreadySet++;
                        binding.collectionHomeRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        collectionAdapter = new AdapterCollectionHome(collections, getContext(), ITEMSPACE);
                        binding.collectionHomeRv.setAdapter(collectionAdapter);
                        binding.collectionHomeRv.setNestedScrollingEnabled(false);
                        shimmerOff();
                    }

                }

            }
        });

        viewModel.getImageSliderData().observe(getViewLifecycleOwner(), new Observer<List<CarouselItem>>() {
            @Override
            public void onChanged(List<CarouselItem> carouselItems) {
                if (carouselItems != null && carouselItems.size() > 0) {
                    items = carouselItems;
                    CarouselAdapter adapter = new CarouselAdapter(items);
                    binding.carouselBannerRvHome.setAdapter(adapter);
                    binding.carouselBannerRvHome.setAutoSlideTime(5);
                    binding.viewpagerIndicator.setCount(items.size());
                }
            }
        });

        viewModel.getShimmerOnOff().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean start) {
                if (start != null) {
                    if (start) {
                        shimmerOn();
                    }
                }
            }
        });

        viewModel.getIsUpdateAvailable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null && aBoolean){
                    AlertDialog dialog=new AlertDialog.Builder(getContext())
                            .setTitle("Update Available!")
                            .setMessage("A Newer Version Of Choloshop App is Live on Play Store .Please Update The Latest Version")
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    final String appPackageName =requireContext().getPackageName();
                                    try {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                    } catch (android.content.ActivityNotFoundException anfe) {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                    }
                                }
                            })
                            .setCancelable(false)
                            .create();
                    dialog.show();
                }
            }
        });

    }

    private void shimmerOn() {
        shimmerLayout = binding.shimmerPlaceholderView;
        binding.shimmerPlaceholderView.setVisibility(View.VISIBLE);
        binding.homeContent.setVisibility(View.GONE);
        shimmerLayout.startShimmer();
    }

    private void shimmerOff() {
        shimmerLayout = binding.shimmerPlaceholderView;
        shimmerLayout.stopShimmer();
        shimmerLayout.setVisibility(View.GONE);
        binding.homeContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.carouselBannerRvHome.unregisterOnPageChangeCallback(onPageChange);
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.carouselBannerRvHome.registerOnPageChangeCallback(onPageChange);
    }

    @Override
    public void onStop() {
        super.onStop();
        isExclusiveAdapterAlreadySet = 0;
        isGroceriesAdapterAlreadySet = 0;
        isCollectionAdapterAlreadySet = 0;
        isFeturedProductAlreadySet=0;
    }


    private void notifyItemChange(Context context, int position) {
        MySharedPreference.getInstance(context)
                .edit()
                .putInt(RECYCLER_ITEM_POSITION, position)
                .apply();
    }

    private int getNotifyItemChange(Context context) {
        return MySharedPreference.getInstance(context)
                .getInt(RECYCLER_ITEM_POSITION, -1);
    }

}