package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutSearchLongRouteBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout addStopConstraintLayout;
    @NonNull
    public final TextView addWayPointTextView;
    @NonNull
    public final TextView addressTextView;
    @NonNull
    public final LinearLayout detailsLayout;
    @NonNull
    public final TextView startNewRouteTextView;
    @NonNull
    public final TextView tvPlaceTitle;

    public LayoutSearchLongRouteBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2, LinearLayout linearLayout, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.addStopConstraintLayout = constraintLayout;
        this.addWayPointTextView = textView;
        this.addressTextView = textView2;
        this.detailsLayout = linearLayout;
        this.startNewRouteTextView = textView3;
        this.tvPlaceTitle = textView4;
    }

    public static LayoutSearchLongRouteBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSearchLongRouteBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutSearchLongRouteBinding) ViewDataBinding.bind(obj, view, R.layout.layout_search_long_route);
    }

    @NonNull
    public static LayoutSearchLongRouteBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutSearchLongRouteBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutSearchLongRouteBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutSearchLongRouteBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_search_long_route, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutSearchLongRouteBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutSearchLongRouteBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_search_long_route, null, false, obj);
    }
}
