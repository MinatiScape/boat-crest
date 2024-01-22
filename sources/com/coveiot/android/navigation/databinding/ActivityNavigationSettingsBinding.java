package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ActivityNavigationSettingsBinding extends ViewDataBinding {
    @NonNull
    public final SwitchCompat switchAudio;
    @NonNull
    public final SwitchCompat switchHaptic;
    @NonNull
    public final SwitchCompat switchNavigationAlwaysOnDisplay;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvAudio;
    @NonNull
    public final TextView tvHaptic;
    @NonNull
    public final TextView tvNavigationAlwaysOnDisplay;
    @NonNull
    public final View viewDividerAudio;
    @NonNull
    public final View viewDividerHaptic;

    public ActivityNavigationSettingsBinding(Object obj, View view, int i, SwitchCompat switchCompat, SwitchCompat switchCompat2, SwitchCompat switchCompat3, View view2, TextView textView, TextView textView2, TextView textView3, View view3, View view4) {
        super(obj, view, i);
        this.switchAudio = switchCompat;
        this.switchHaptic = switchCompat2;
        this.switchNavigationAlwaysOnDisplay = switchCompat3;
        this.toolbar = view2;
        this.tvAudio = textView;
        this.tvHaptic = textView2;
        this.tvNavigationAlwaysOnDisplay = textView3;
        this.viewDividerAudio = view3;
        this.viewDividerHaptic = view4;
    }

    public static ActivityNavigationSettingsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNavigationSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNavigationSettingsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityNavigationSettingsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_navigation_settings);
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityNavigationSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_settings, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityNavigationSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityNavigationSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_settings, null, false, obj);
    }
}
