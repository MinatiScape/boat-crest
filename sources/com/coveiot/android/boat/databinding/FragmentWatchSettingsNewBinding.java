package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentWatchSettingsNewBinding extends ViewDataBinding {
    @NonNull
    public final View aboutWatchLayout;
    @NonNull
    public final RecyclerView rcvSettings;
    @NonNull
    public final View watchStatusLayout;

    public FragmentWatchSettingsNewBinding(Object obj, View view, int i, View view2, RecyclerView recyclerView, View view3) {
        super(obj, view, i);
        this.aboutWatchLayout = view2;
        this.rcvSettings = recyclerView;
        this.watchStatusLayout = view3;
    }

    public static FragmentWatchSettingsNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWatchSettingsNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchSettingsNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentWatchSettingsNewBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_watch_settings_new);
    }

    @NonNull
    @Deprecated
    public static FragmentWatchSettingsNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentWatchSettingsNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_settings_new, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentWatchSettingsNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentWatchSettingsNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentWatchSettingsNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_settings_new, null, false, obj);
    }
}
