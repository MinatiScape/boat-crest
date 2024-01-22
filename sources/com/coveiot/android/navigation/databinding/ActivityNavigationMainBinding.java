package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.utils.CustomMapView;
/* loaded from: classes5.dex */
public abstract class ActivityNavigationMainBinding extends ViewDataBinding {
    @NonNull
    public final Button btnGetDirections;
    @NonNull
    public final ConstraintLayout clET;
    @NonNull
    public final LinearLayout clNavigationMainRoot;
    @NonNull
    public final ConstraintLayout clSelectedPlaceLayout;
    @NonNull
    public final ImageView ivPlaceIndicator;
    @NonNull
    public final ImageView ivPlaceLabelCloseIcon;
    @NonNull
    public final LinearLayout llSearchLocationInfo;
    @NonNull
    public final LinearLayout llSearchSourceLayout;
    @NonNull
    public final ConstraintLayout loutSelectedPlaceTitle;
    @NonNull
    public final CustomMapView mapplsMapviewNavigationMain;
    @NonNull
    public final RecyclerView rvTags;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvDistanceToReachSelectedPlace;
    @NonNull
    public final TextView tvPlaceLabel;
    @NonNull
    public final TextView tvReachDurationToSelectedPlace;
    @NonNull
    public final TextView tvSearchLocationInfo;
    @NonNull
    public final TextView tvSelectedPlaceAddress;
    @NonNull
    public final TextView tvSelectedPlaceDisclaimer;
    @NonNull
    public final TextView tvSelectedPlaceName;

    public ActivityNavigationMainBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, LinearLayout linearLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, LinearLayout linearLayout3, ConstraintLayout constraintLayout3, CustomMapView customMapView, RecyclerView recyclerView, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        super(obj, view, i);
        this.btnGetDirections = button;
        this.clET = constraintLayout;
        this.clNavigationMainRoot = linearLayout;
        this.clSelectedPlaceLayout = constraintLayout2;
        this.ivPlaceIndicator = imageView;
        this.ivPlaceLabelCloseIcon = imageView2;
        this.llSearchLocationInfo = linearLayout2;
        this.llSearchSourceLayout = linearLayout3;
        this.loutSelectedPlaceTitle = constraintLayout3;
        this.mapplsMapviewNavigationMain = customMapView;
        this.rvTags = recyclerView;
        this.toolbar = view2;
        this.tvDistanceToReachSelectedPlace = textView;
        this.tvPlaceLabel = textView2;
        this.tvReachDurationToSelectedPlace = textView3;
        this.tvSearchLocationInfo = textView4;
        this.tvSelectedPlaceAddress = textView5;
        this.tvSelectedPlaceDisclaimer = textView6;
        this.tvSelectedPlaceName = textView7;
    }

    public static ActivityNavigationMainBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNavigationMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNavigationMainBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityNavigationMainBinding) ViewDataBinding.bind(obj, view, R.layout.activity_navigation_main);
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityNavigationMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_main, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityNavigationMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityNavigationMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_main, null, false, obj);
    }
}
