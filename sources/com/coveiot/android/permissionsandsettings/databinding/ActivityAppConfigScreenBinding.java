package com.coveiot.android.permissionsandsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.permissionsandsettings.R;
/* loaded from: classes5.dex */
public abstract class ActivityAppConfigScreenBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clAutoStart;
    @NonNull
    public final ConstraintLayout clBattery;
    @NonNull
    public final ConstraintLayout clPowerOptimisation;
    @NonNull
    public final View listItemDivider;
    @NonNull
    public final View listItemDivider2;
    @NonNull
    public final TextView settingsName;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvSetting;

    public ActivityAppConfigScreenBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, View view2, View view3, TextView textView, View view4, TextView textView2) {
        super(obj, view, i);
        this.clAutoStart = constraintLayout;
        this.clBattery = constraintLayout2;
        this.clPowerOptimisation = constraintLayout3;
        this.listItemDivider = view2;
        this.listItemDivider2 = view3;
        this.settingsName = textView;
        this.toolbar = view4;
        this.tvSetting = textView2;
    }

    public static ActivityAppConfigScreenBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAppConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAppConfigScreenBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAppConfigScreenBinding) ViewDataBinding.bind(obj, view, R.layout.activity_app_config_screen);
    }

    @NonNull
    @Deprecated
    public static ActivityAppConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAppConfigScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_app_config_screen, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAppConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAppConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAppConfigScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_app_config_screen, null, false, obj);
    }
}
