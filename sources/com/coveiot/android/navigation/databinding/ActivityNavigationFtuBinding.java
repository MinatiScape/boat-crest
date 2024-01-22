package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ActivityNavigationFtuBinding extends ViewDataBinding {
    @NonNull
    public final FragmentContainerView fragmentContainerNavigationFtu;
    @NonNull
    public final View toolbar;

    public ActivityNavigationFtuBinding(Object obj, View view, int i, FragmentContainerView fragmentContainerView, View view2) {
        super(obj, view, i);
        this.fragmentContainerNavigationFtu = fragmentContainerView;
        this.toolbar = view2;
    }

    public static ActivityNavigationFtuBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNavigationFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNavigationFtuBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityNavigationFtuBinding) ViewDataBinding.bind(obj, view, R.layout.activity_navigation_ftu);
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityNavigationFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_ftu, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityNavigationFtuBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityNavigationFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_ftu, null, false, obj);
    }
}
