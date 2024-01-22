package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class CurrentLocationDetailsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clCurrentLocation;
    @NonNull
    public final ImageView ivArrow;
    @NonNull
    public final ImageView ivLocation;
    @NonNull
    public final TextView tvCurrentLocation;
    @NonNull
    public final TextView tvCurrentLocationData;

    public CurrentLocationDetailsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clCurrentLocation = constraintLayout;
        this.ivArrow = imageView;
        this.ivLocation = imageView2;
        this.tvCurrentLocation = textView;
        this.tvCurrentLocationData = textView2;
    }

    public static CurrentLocationDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CurrentLocationDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CurrentLocationDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CurrentLocationDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.current_location_details);
    }

    @NonNull
    @Deprecated
    public static CurrentLocationDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CurrentLocationDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.current_location_details, viewGroup, z, obj);
    }

    @NonNull
    public static CurrentLocationDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CurrentLocationDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CurrentLocationDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.current_location_details, null, false, obj);
    }
}
