package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyViewBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout mapplsNearbyBgLayout;
    @NonNull
    public final CardView mapplsNearbyBottomLayout;
    @NonNull
    public final TextView mapplsNearbyChangeLocationBtn;
    @NonNull
    public final TextView mapplsNearbyCurrentLocationBtn;
    @NonNull
    public final LinearLayout mapplsNearbyLayoutTitle;
    @NonNull
    public final CardView mapplsNearbyLocationLayout;
    @NonNull
    public final TextView mapplsNearbyLocationText;
    @NonNull
    public final TextView mapplsNearbyNextBtn;
    @NonNull
    public final RelativeLayout mapplsNearbyProgressBar;
    @NonNull
    public final RecyclerView mapplsNearbyRvCategory;
    @NonNull
    public final ImageView mapplsNearbyToolbarIcon;
    @NonNull
    public final TextView mapplsNearbyToolbarText;
    @NonNull
    public final TextView mapplsNearbyTvAddress;

    public MapplsNearbyViewBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, CardView cardView, TextView textView, TextView textView2, LinearLayout linearLayout, CardView cardView2, TextView textView3, TextView textView4, RelativeLayout relativeLayout, RecyclerView recyclerView, ImageView imageView, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.mapplsNearbyBgLayout = constraintLayout;
        this.mapplsNearbyBottomLayout = cardView;
        this.mapplsNearbyChangeLocationBtn = textView;
        this.mapplsNearbyCurrentLocationBtn = textView2;
        this.mapplsNearbyLayoutTitle = linearLayout;
        this.mapplsNearbyLocationLayout = cardView2;
        this.mapplsNearbyLocationText = textView3;
        this.mapplsNearbyNextBtn = textView4;
        this.mapplsNearbyProgressBar = relativeLayout;
        this.mapplsNearbyRvCategory = recyclerView;
        this.mapplsNearbyToolbarIcon = imageView;
        this.mapplsNearbyToolbarText = textView5;
        this.mapplsNearbyTvAddress = textView6;
    }

    public static MapplsNearbyViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyViewBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_view);
    }

    @NonNull
    public static MapplsNearbyViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_view, null, false, obj);
    }
}
