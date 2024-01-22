package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutBottomInfobarBinding extends ViewDataBinding {
    @NonNull
    public final ImageView carSpeedLightImageview;
    @NonNull
    public final CardView cardView;
    @NonNull
    public final TextView destinationText;
    @NonNull
    public final ImageView imageRouteOverview;
    @NonNull
    public final ImageView ivBottomSheetArrow;
    @NonNull
    public final ImageView navDestinationImageview;
    @NonNull
    public final ProgressBar navProgress;
    @NonNull
    public final RelativeLayout optionsRecyclerViewContainer;
    @NonNull
    public final LinearLayout progressBarLayout;
    @NonNull
    public final TextView remainingDistanceTextView;
    @NonNull
    public final RecyclerView rvBottomItem;
    @Nullable
    public final RelativeLayout seperatorView;
    @NonNull
    public final ImageView stopNavigation;
    @NonNull
    public final TextView textViewReachEta;
    @NonNull
    public final TextView textViewTotalDistanceLeft;
    @NonNull
    public final TextView textViewTotalTimeLeft;
    @NonNull
    public final View topSeparatorViewDayMode;
    @NonNull
    public final View topSeparatorViewNightMode;
    @NonNull
    public final View verticalSeperatorView;

    public LayoutBottomInfobarBinding(Object obj, View view, int i, ImageView imageView, CardView cardView, TextView textView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ProgressBar progressBar, RelativeLayout relativeLayout, LinearLayout linearLayout, TextView textView2, RecyclerView recyclerView, RelativeLayout relativeLayout2, ImageView imageView5, TextView textView3, TextView textView4, TextView textView5, View view2, View view3, View view4) {
        super(obj, view, i);
        this.carSpeedLightImageview = imageView;
        this.cardView = cardView;
        this.destinationText = textView;
        this.imageRouteOverview = imageView2;
        this.ivBottomSheetArrow = imageView3;
        this.navDestinationImageview = imageView4;
        this.navProgress = progressBar;
        this.optionsRecyclerViewContainer = relativeLayout;
        this.progressBarLayout = linearLayout;
        this.remainingDistanceTextView = textView2;
        this.rvBottomItem = recyclerView;
        this.seperatorView = relativeLayout2;
        this.stopNavigation = imageView5;
        this.textViewReachEta = textView3;
        this.textViewTotalDistanceLeft = textView4;
        this.textViewTotalTimeLeft = textView5;
        this.topSeparatorViewDayMode = view2;
        this.topSeparatorViewNightMode = view3;
        this.verticalSeperatorView = view4;
    }

    public static LayoutBottomInfobarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBottomInfobarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutBottomInfobarBinding) ViewDataBinding.bind(obj, view, R.layout.layout_bottom_infobar);
    }

    @NonNull
    public static LayoutBottomInfobarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutBottomInfobarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutBottomInfobarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutBottomInfobarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_bottom_infobar, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutBottomInfobarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutBottomInfobarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_bottom_infobar, null, false, obj);
    }
}
