package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class WatchSettingsBigCardDashboardBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvChangeYourWatchFace;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;

    public WatchSettingsBigCardDashboardBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.tvChangeYourWatchFace = textView;
        this.tvHeader = textView2;
        this.tvInfo = textView3;
    }

    public static WatchSettingsBigCardDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static WatchSettingsBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchSettingsBigCardDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (WatchSettingsBigCardDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.watch_settings_big_card_dashboard);
    }

    @NonNull
    @Deprecated
    public static WatchSettingsBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (WatchSettingsBigCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_settings_big_card_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static WatchSettingsBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static WatchSettingsBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (WatchSettingsBigCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_settings_big_card_dashboard, null, false, obj);
    }
}
