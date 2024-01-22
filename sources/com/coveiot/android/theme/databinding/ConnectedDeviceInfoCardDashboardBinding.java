package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ConnectedDeviceInfoCardDashboardBinding extends ViewDataBinding {
    @NonNull
    public final ProgressBar batteryProgressBar;
    @NonNull
    public final ConstraintLayout clBottom;
    @NonNull
    public final ConstraintLayout clDevice;
    @NonNull
    public final ConstraintLayout clManualSync;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final Guideline guideline2;
    @NonNull
    public final ImageView ivBattery;
    @NonNull
    public final ImageView ivWatch;
    @Bindable
    public String mWatchName;
    @NonNull
    public final View middleView;
    @NonNull
    public final TextView tvBatteryLevel;
    @NonNull
    public final TextView tvDeviceName;
    @NonNull
    public final TextView tvHello;
    @NonNull
    public final TextView tvLastSyncDate;
    @NonNull
    public final TextView tvLastSyncTime;
    @NonNull
    public final TextView tvSyncingStatus;
    @NonNull
    public final TextView tvUserName;

    public ConnectedDeviceInfoCardDashboardBinding(Object obj, View view, int i, ProgressBar progressBar, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        super(obj, view, i);
        this.batteryProgressBar = progressBar;
        this.clBottom = constraintLayout;
        this.clDevice = constraintLayout2;
        this.clManualSync = constraintLayout3;
        this.guideline1 = guideline;
        this.guideline2 = guideline2;
        this.ivBattery = imageView;
        this.ivWatch = imageView2;
        this.middleView = view2;
        this.tvBatteryLevel = textView;
        this.tvDeviceName = textView2;
        this.tvHello = textView3;
        this.tvLastSyncDate = textView4;
        this.tvLastSyncTime = textView5;
        this.tvSyncingStatus = textView6;
        this.tvUserName = textView7;
    }

    public static ConnectedDeviceInfoCardDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ConnectedDeviceInfoCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getWatchName() {
        return this.mWatchName;
    }

    public abstract void setWatchName(@Nullable String str);

    @Deprecated
    public static ConnectedDeviceInfoCardDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ConnectedDeviceInfoCardDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.connected_device_info_card_dashboard);
    }

    @NonNull
    @Deprecated
    public static ConnectedDeviceInfoCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ConnectedDeviceInfoCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.connected_device_info_card_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static ConnectedDeviceInfoCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ConnectedDeviceInfoCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ConnectedDeviceInfoCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.connected_device_info_card_dashboard, null, false, obj);
    }
}
