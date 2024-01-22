package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.fitnesschallenges.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes2.dex */
public abstract class FragmentMyChallengeBinding extends ViewDataBinding {
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final ViewPager viewPager;

    public FragmentMyChallengeBinding(Object obj, View view, int i, TabLayout tabLayout, ViewPager viewPager) {
        super(obj, view, i);
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
    }

    public static FragmentMyChallengeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentMyChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMyChallengeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentMyChallengeBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_my_challenge);
    }

    @NonNull
    @Deprecated
    public static FragmentMyChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentMyChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_challenge, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentMyChallengeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentMyChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentMyChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_challenge, null, false, obj);
    }
}
