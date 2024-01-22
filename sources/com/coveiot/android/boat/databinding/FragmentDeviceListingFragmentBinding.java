package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentDeviceListingFragmentBinding extends ViewDataBinding {
    @NonNull
    public final Button btnPairDevice;
    @NonNull
    public final ConstraintLayout clMiddle;
    @NonNull
    public final ConstraintLayout clNoDeviceFound;
    @NonNull
    public final ConstraintLayout clPairDevice;
    @NonNull
    public final ConstraintLayout clPairViaQR;
    @NonNull
    public final ConstraintLayout clPairViaQR1;
    @NonNull
    public final ConstraintLayout clQRCode;
    @NonNull
    public final ConstraintLayout clSearchDevice;
    @NonNull
    public final ConstraintLayout constraintLayout4;
    @NonNull
    public final Guideline glBottom;
    @NonNull
    public final Guideline glBottom1;
    @NonNull
    public final ImageView ivConnectionLoader;
    @NonNull
    public final ImageView ivConnectionLoader1;
    @NonNull
    public final LottieAnimationView lottieView;
    @NonNull
    public final RecyclerView rvBleDevices;
    @NonNull
    public final TextView scanAllDevicesTv;
    @NonNull
    public final View tvBack;
    @NonNull
    public final View tvBack1;
    @NonNull
    public final TextView tvDeviceName;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvInfo1;
    @NonNull
    public final TextView tvNoDevice;
    @NonNull
    public final TextView tvOr;
    @NonNull
    public final TextView tvQRCode;
    @NonNull
    public final TextView tvQRCode1;
    @NonNull
    public final TextView tvSearchAgain;
    @NonNull
    public final TextView tvSearching;
    @NonNull
    public final TextView tvTrouble;
    @NonNull
    public final TextView tvUnableToFindDevice;

    public FragmentDeviceListingFragmentBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, LottieAnimationView lottieAnimationView, RecyclerView recyclerView, TextView textView, View view2, View view3, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        super(obj, view, i);
        this.btnPairDevice = button;
        this.clMiddle = constraintLayout;
        this.clNoDeviceFound = constraintLayout2;
        this.clPairDevice = constraintLayout3;
        this.clPairViaQR = constraintLayout4;
        this.clPairViaQR1 = constraintLayout5;
        this.clQRCode = constraintLayout6;
        this.clSearchDevice = constraintLayout7;
        this.constraintLayout4 = constraintLayout8;
        this.glBottom = guideline;
        this.glBottom1 = guideline2;
        this.ivConnectionLoader = imageView;
        this.ivConnectionLoader1 = imageView2;
        this.lottieView = lottieAnimationView;
        this.rvBleDevices = recyclerView;
        this.scanAllDevicesTv = textView;
        this.tvBack = view2;
        this.tvBack1 = view3;
        this.tvDeviceName = textView2;
        this.tvInfo = textView3;
        this.tvInfo1 = textView4;
        this.tvNoDevice = textView5;
        this.tvOr = textView6;
        this.tvQRCode = textView7;
        this.tvQRCode1 = textView8;
        this.tvSearchAgain = textView9;
        this.tvSearching = textView10;
        this.tvTrouble = textView11;
        this.tvUnableToFindDevice = textView12;
    }

    public static FragmentDeviceListingFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentDeviceListingFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDeviceListingFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentDeviceListingFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_device_listing_fragment);
    }

    @NonNull
    @Deprecated
    public static FragmentDeviceListingFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentDeviceListingFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_device_listing_fragment, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentDeviceListingFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentDeviceListingFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentDeviceListingFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_device_listing_fragment, null, false, obj);
    }
}
