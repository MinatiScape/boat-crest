package com.coveiot.android.permissionsandsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.permissionsandsettings.R;
/* loaded from: classes5.dex */
public abstract class ActivityConfigScreenBinding extends ViewDataBinding {
    @NonNull
    public final View autoView;
    @NonNull
    public final View batteryView;
    @NonNull
    public final Button btnContinueToHomepage;
    @NonNull
    public final ConstraintLayout clAutoStart;
    @NonNull
    public final ConstraintLayout clBattery;
    @NonNull
    public final ConstraintLayout clPowerOptimisation;
    @NonNull
    public final Guideline glBottom;
    @NonNull
    public final ImageView ivAutoStart;
    @NonNull
    public final ImageView ivBattery;
    @NonNull
    public final ImageView ivPowerStart;
    @NonNull
    public final TextView tvAutoStart;
    @NonNull
    public final TextView tvAutoStartInfo;
    @NonNull
    public final TextView tvBatteryHeader;
    @NonNull
    public final TextView tvBatteryInfo;
    @NonNull
    public final TextView tvDisableBatteryOptimisation;
    @NonNull
    public final TextView tvDisablePowerOptimisation;
    @NonNull
    public final TextView tvEnableAutoStart;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvHeaderInfo;
    @NonNull
    public final TextView tvPower;
    @NonNull
    public final TextView tvPowerInfo;
    @NonNull
    public final TextView tvSetting;

    public ActivityConfigScreenBinding(Object obj, View view, int i, View view2, View view3, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, Guideline guideline, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        super(obj, view, i);
        this.autoView = view2;
        this.batteryView = view3;
        this.btnContinueToHomepage = button;
        this.clAutoStart = constraintLayout;
        this.clBattery = constraintLayout2;
        this.clPowerOptimisation = constraintLayout3;
        this.glBottom = guideline;
        this.ivAutoStart = imageView;
        this.ivBattery = imageView2;
        this.ivPowerStart = imageView3;
        this.tvAutoStart = textView;
        this.tvAutoStartInfo = textView2;
        this.tvBatteryHeader = textView3;
        this.tvBatteryInfo = textView4;
        this.tvDisableBatteryOptimisation = textView5;
        this.tvDisablePowerOptimisation = textView6;
        this.tvEnableAutoStart = textView7;
        this.tvHeader = textView8;
        this.tvHeaderInfo = textView9;
        this.tvPower = textView10;
        this.tvPowerInfo = textView11;
        this.tvSetting = textView12;
    }

    public static ActivityConfigScreenBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityConfigScreenBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityConfigScreenBinding) ViewDataBinding.bind(obj, view, R.layout.activity_config_screen);
    }

    @NonNull
    @Deprecated
    public static ActivityConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityConfigScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_config_screen, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityConfigScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityConfigScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_config_screen, null, false, obj);
    }
}
