package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes3.dex */
public abstract class SensAiHomeActivityBinding extends ViewDataBinding {
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final ViewPager viewPager;

    public SensAiHomeActivityBinding(Object obj, View view, int i, TabLayout tabLayout, View view2, ViewPager viewPager) {
        super(obj, view, i);
        this.tabLayout = tabLayout;
        this.toolbar = view2;
        this.viewPager = viewPager;
    }

    public static SensAiHomeActivityBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiHomeActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiHomeActivityBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiHomeActivityBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_home_activity);
    }

    @NonNull
    @Deprecated
    public static SensAiHomeActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiHomeActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_home_activity, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiHomeActivityBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiHomeActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiHomeActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_home_activity, null, false, obj);
    }
}
