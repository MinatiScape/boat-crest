package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionRouteSummaryFragmentBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageViewClose;
    @NonNull
    public final ImageView nearbyReport;
    @NonNull
    public final ViewPager routeSummaryEventViewPager;
    @NonNull
    public final LinearLayout routeSummarySelectorLayout;
    @NonNull
    public final TabLayout routeSummaryTabLayout;
    @NonNull
    public final LinearLayout routeSummaryTitle;

    public MapplsDirectionRouteSummaryFragmentBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ViewPager viewPager, LinearLayout linearLayout, TabLayout tabLayout, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.imageViewClose = imageView;
        this.nearbyReport = imageView2;
        this.routeSummaryEventViewPager = viewPager;
        this.routeSummarySelectorLayout = linearLayout;
        this.routeSummaryTabLayout = tabLayout;
        this.routeSummaryTitle = linearLayout2;
    }

    public static MapplsDirectionRouteSummaryFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionRouteSummaryFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_route_summary_fragment);
    }

    @NonNull
    public static MapplsDirectionRouteSummaryFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionRouteSummaryFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_fragment, null, false, obj);
    }
}
