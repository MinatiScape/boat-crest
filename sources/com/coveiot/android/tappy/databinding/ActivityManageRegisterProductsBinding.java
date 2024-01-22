package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.tappy.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes7.dex */
public abstract class ActivityManageRegisterProductsBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout fragmentContainer1;
    @NonNull
    public final TabLayout tlStrapManage;
    @NonNull
    public final View toolbar;
    @NonNull
    public final ViewPager viewPager;

    public ActivityManageRegisterProductsBinding(Object obj, View view, int i, FrameLayout frameLayout, TabLayout tabLayout, View view2, ViewPager viewPager) {
        super(obj, view, i);
        this.fragmentContainer1 = frameLayout;
        this.tlStrapManage = tabLayout;
        this.toolbar = view2;
        this.viewPager = viewPager;
    }

    public static ActivityManageRegisterProductsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityManageRegisterProductsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityManageRegisterProductsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityManageRegisterProductsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_manage_register_products);
    }

    @NonNull
    @Deprecated
    public static ActivityManageRegisterProductsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityManageRegisterProductsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_manage_register_products, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityManageRegisterProductsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityManageRegisterProductsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityManageRegisterProductsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_manage_register_products, null, false, obj);
    }
}
