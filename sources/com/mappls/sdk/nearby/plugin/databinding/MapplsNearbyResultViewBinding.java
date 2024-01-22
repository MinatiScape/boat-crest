package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyResultViewBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout mapplsNearbyMapContainer;
    @NonNull
    public final CardView mapplsNearbyNext;
    @NonNull
    public final CardView mapplsNearbyPageCurrent;
    @NonNull
    public final TextView mapplsNearbyPageCurrentTv;
    @NonNull
    public final CardView mapplsNearbyPageNext;
    @NonNull
    public final TextView mapplsNearbyPageNextTv;
    @Nullable
    public final View mapplsNearbyPageShadow;
    @NonNull
    public final CardView mapplsNearbyPrev;
    @NonNull
    public final ConstraintLayout mapplsNearbyResultCategoryBg;
    @NonNull
    public final RecyclerView mapplsNearbyResultCategoryRv;
    @NonNull
    public final ConstraintLayout mapplsNearbyResultPage;
    @NonNull
    public final RelativeLayout mapplsNearbyResultProgressBar;
    @NonNull
    public final ConstraintLayout mapplsNearbyResultToolbar;
    @NonNull
    public final TextView mapplsNearbyResultToolbarTextView;
    @NonNull
    public final TabLayout mapplsNearbyTabLayout;
    @Nullable
    public final View mapplsNearbyTabShadow;
    @NonNull
    public final ImageView mapplsNearbyToolbarBackButton;
    @NonNull
    public final ViewPager2 mapplsNearbyViewPager;

    public MapplsNearbyResultViewBinding(Object obj, View view, int i, FrameLayout frameLayout, CardView cardView, CardView cardView2, TextView textView, CardView cardView3, TextView textView2, View view2, CardView cardView4, ConstraintLayout constraintLayout, RecyclerView recyclerView, ConstraintLayout constraintLayout2, RelativeLayout relativeLayout, ConstraintLayout constraintLayout3, TextView textView3, TabLayout tabLayout, View view3, ImageView imageView, ViewPager2 viewPager2) {
        super(obj, view, i);
        this.mapplsNearbyMapContainer = frameLayout;
        this.mapplsNearbyNext = cardView;
        this.mapplsNearbyPageCurrent = cardView2;
        this.mapplsNearbyPageCurrentTv = textView;
        this.mapplsNearbyPageNext = cardView3;
        this.mapplsNearbyPageNextTv = textView2;
        this.mapplsNearbyPageShadow = view2;
        this.mapplsNearbyPrev = cardView4;
        this.mapplsNearbyResultCategoryBg = constraintLayout;
        this.mapplsNearbyResultCategoryRv = recyclerView;
        this.mapplsNearbyResultPage = constraintLayout2;
        this.mapplsNearbyResultProgressBar = relativeLayout;
        this.mapplsNearbyResultToolbar = constraintLayout3;
        this.mapplsNearbyResultToolbarTextView = textView3;
        this.mapplsNearbyTabLayout = tabLayout;
        this.mapplsNearbyTabShadow = view3;
        this.mapplsNearbyToolbarBackButton = imageView;
        this.mapplsNearbyViewPager = viewPager2;
    }

    public static MapplsNearbyResultViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyResultViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyResultViewBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_result_view);
    }

    @NonNull
    public static MapplsNearbyResultViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyResultViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyResultViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyResultViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_view, null, false, obj);
    }
}
