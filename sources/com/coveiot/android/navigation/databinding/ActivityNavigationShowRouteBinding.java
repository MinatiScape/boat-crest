package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.utils.CustomMapView;
/* loaded from: classes5.dex */
public abstract class ActivityNavigationShowRouteBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrierBs1;
    @NonNull
    public final Barrier barrierShowRoutes1;
    @NonNull
    public final Button btnNavigateOnWatch;
    @NonNull
    public final Button btnPreview;
    @NonNull
    public final ConstraintLayout clDriving;
    @NonNull
    public final ConstraintLayout clRouteMapviewBottomLayout;
    @NonNull
    public final ConstraintLayout clRouteMapviewTopLayout;
    @NonNull
    public final ConstraintLayout clWalking;
    @NonNull
    public final FrameLayout frameLayout;
    @NonNull
    public final ImageView ivBsInfoIcon;
    @NonNull
    public final ImageView ivViceVersa;
    @NonNull
    public final LinearLayout llRouteDetailButtons;
    @NonNull
    public final ConstraintLayout llSearchDestinationLayout;
    @NonNull
    public final LinearLayout llSearchSourceLayout;
    @NonNull
    public final CustomMapView mapviewShowRoute;
    @NonNull
    public final NestedScrollView scrollView;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvBsArrival;
    @NonNull
    public final TextView tvBsDistanceToReachDestination;
    @NonNull
    public final TextView tvBsReachDurationToDestination;
    @NonNull
    public final TextView tvDrivingMode;
    @NonNull
    public final TextView tvMostOptimalRouteSelected;
    @NonNull
    public final TextView tvNavigateToWatchDisclaimer;
    @NonNull
    public final TextView tvNavigateToWatchInstructions;
    @NonNull
    public final TextView tvShowRouteDestinationLabel;
    @NonNull
    public final TextView tvShowRouteSourceLabel;
    @NonNull
    public final TextView tvTapToEnter;
    @NonNull
    public final TextView tvWalkingMode;

    public ActivityNavigationShowRouteBinding(Object obj, View view, int i, Barrier barrier, Barrier barrier2, Button button, Button button2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, ConstraintLayout constraintLayout5, LinearLayout linearLayout2, CustomMapView customMapView, NestedScrollView nestedScrollView, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        super(obj, view, i);
        this.barrierBs1 = barrier;
        this.barrierShowRoutes1 = barrier2;
        this.btnNavigateOnWatch = button;
        this.btnPreview = button2;
        this.clDriving = constraintLayout;
        this.clRouteMapviewBottomLayout = constraintLayout2;
        this.clRouteMapviewTopLayout = constraintLayout3;
        this.clWalking = constraintLayout4;
        this.frameLayout = frameLayout;
        this.ivBsInfoIcon = imageView;
        this.ivViceVersa = imageView2;
        this.llRouteDetailButtons = linearLayout;
        this.llSearchDestinationLayout = constraintLayout5;
        this.llSearchSourceLayout = linearLayout2;
        this.mapviewShowRoute = customMapView;
        this.scrollView = nestedScrollView;
        this.toolbar = view2;
        this.tvBsArrival = textView;
        this.tvBsDistanceToReachDestination = textView2;
        this.tvBsReachDurationToDestination = textView3;
        this.tvDrivingMode = textView4;
        this.tvMostOptimalRouteSelected = textView5;
        this.tvNavigateToWatchDisclaimer = textView6;
        this.tvNavigateToWatchInstructions = textView7;
        this.tvShowRouteDestinationLabel = textView8;
        this.tvShowRouteSourceLabel = textView9;
        this.tvTapToEnter = textView10;
        this.tvWalkingMode = textView11;
    }

    public static ActivityNavigationShowRouteBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNavigationShowRouteBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNavigationShowRouteBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityNavigationShowRouteBinding) ViewDataBinding.bind(obj, view, R.layout.activity_navigation_show_route);
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationShowRouteBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityNavigationShowRouteBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_show_route, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityNavigationShowRouteBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationShowRouteBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityNavigationShowRouteBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_show_route, null, false, obj);
    }
}
