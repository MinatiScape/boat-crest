package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityAutoSpo2SettingsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout autoStressLay;
    @NonNull
    public final Button btnSave;
    @NonNull
    public final SwitchCompat switchReminder;
    @NonNull
    public final View toolbar;

    public ActivityAutoSpo2SettingsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Button button, SwitchCompat switchCompat, View view2) {
        super(obj, view, i);
        this.autoStressLay = constraintLayout;
        this.btnSave = button;
        this.switchReminder = switchCompat;
        this.toolbar = view2;
    }

    public static ActivityAutoSpo2SettingsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAutoSpo2SettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAutoSpo2SettingsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAutoSpo2SettingsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_auto_spo2_settings);
    }

    @NonNull
    @Deprecated
    public static ActivityAutoSpo2SettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAutoSpo2SettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_auto_spo2_settings, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAutoSpo2SettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAutoSpo2SettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAutoSpo2SettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_auto_spo2_settings, null, false, obj);
    }
}
