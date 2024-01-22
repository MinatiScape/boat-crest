package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentStrapManagementBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddStrap;
    @NonNull
    public final ImageView emptyStrapView;
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final RecyclerView strapRv;

    public FragmentStrapManagementBinding(Object obj, View view, int i, Button button, ImageView imageView, FrameLayout frameLayout, RecyclerView recyclerView) {
        super(obj, view, i);
        this.btnAddStrap = button;
        this.emptyStrapView = imageView;
        this.fragmentContainer = frameLayout;
        this.strapRv = recyclerView;
    }

    public static FragmentStrapManagementBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentStrapManagementBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentStrapManagementBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentStrapManagementBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_strap_management);
    }

    @NonNull
    @Deprecated
    public static FragmentStrapManagementBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentStrapManagementBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_strap_management, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentStrapManagementBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentStrapManagementBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentStrapManagementBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_strap_management, null, false, obj);
    }
}
