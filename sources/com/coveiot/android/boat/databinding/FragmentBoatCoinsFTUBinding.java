package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentBoatCoinsFTUBinding extends ViewDataBinding {
    @NonNull
    public final ImageView bcImageViewDot1;
    @NonNull
    public final ImageView bcImageViewDot2;
    @NonNull
    public final ConstraintLayout constraintLayoutBottom;
    @NonNull
    public final Guideline guideline26;
    @NonNull
    public final Guideline guidelineBottom;
    @NonNull
    public final ImageView ivBoatCoinsFtu;
    @NonNull
    public final TextView tvBoatCoinsDescription;
    @NonNull
    public final TextView tvBoatCoinsDescription2;

    public FragmentBoatCoinsFTUBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, ImageView imageView3, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.bcImageViewDot1 = imageView;
        this.bcImageViewDot2 = imageView2;
        this.constraintLayoutBottom = constraintLayout;
        this.guideline26 = guideline;
        this.guidelineBottom = guideline2;
        this.ivBoatCoinsFtu = imageView3;
        this.tvBoatCoinsDescription = textView;
        this.tvBoatCoinsDescription2 = textView2;
    }

    public static FragmentBoatCoinsFTUBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentBoatCoinsFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBoatCoinsFTUBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentBoatCoinsFTUBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_boat_coins_f_t_u);
    }

    @NonNull
    @Deprecated
    public static FragmentBoatCoinsFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentBoatCoinsFTUBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_boat_coins_f_t_u, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentBoatCoinsFTUBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentBoatCoinsFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentBoatCoinsFTUBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_boat_coins_f_t_u, null, false, obj);
    }
}
