package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class ActivityAddressBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout container;
    @NonNull
    public final View toolbar;

    public ActivityAddressBinding(Object obj, View view, int i, FrameLayout frameLayout, View view2) {
        super(obj, view, i);
        this.container = frameLayout;
        this.toolbar = view2;
    }

    public static ActivityAddressBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddressBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddressBinding) ViewDataBinding.bind(obj, view, R.layout.activity_address);
    }

    @NonNull
    @Deprecated
    public static ActivityAddressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_address, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddressBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_address, null, false, obj);
    }
}
