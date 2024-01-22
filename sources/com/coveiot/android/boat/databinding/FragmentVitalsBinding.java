package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentVitalsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clGetStarted;
    @NonNull
    public final ConstraintLayout clGuest;
    @NonNull
    public final ConstraintLayout clVitals;
    @NonNull
    public final ImageView ivMobile;
    @Bindable
    public String mVitalName;
    @NonNull
    public final RecyclerView rvVitals;
    @NonNull
    public final TextView tvLoginAndPair;
    @NonNull
    public final FrameLayout vitalContainer;

    public FragmentVitalsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ImageView imageView, RecyclerView recyclerView, TextView textView, FrameLayout frameLayout) {
        super(obj, view, i);
        this.clGetStarted = constraintLayout;
        this.clGuest = constraintLayout2;
        this.clVitals = constraintLayout3;
        this.ivMobile = imageView;
        this.rvVitals = recyclerView;
        this.tvLoginAndPair = textView;
        this.vitalContainer = frameLayout;
    }

    public static FragmentVitalsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getVitalName() {
        return this.mVitalName;
    }

    public abstract void setVitalName(@Nullable String str);

    @Deprecated
    public static FragmentVitalsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vitals);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vitals, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vitals, null, false, obj);
    }
}
