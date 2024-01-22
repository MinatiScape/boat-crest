package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public abstract class ActivityRespiratoryRateSettingsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final ConstraintLayout clReminder;
    @NonNull
    public final ImageView imgvRespiratoryRateSettings;
    @NonNull
    public final TextView respiratoryRateSwitchTitle;
    @NonNull
    public final SwitchCompat switchRespiratoryRate;
    @NonNull
    public final View toolbar;

    public ActivityRespiratoryRateSettingsBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, SwitchCompat switchCompat, View view2) {
        super(obj, view, i);
        this.btnSave = button;
        this.clReminder = constraintLayout;
        this.imgvRespiratoryRateSettings = imageView;
        this.respiratoryRateSwitchTitle = textView;
        this.switchRespiratoryRate = switchCompat;
        this.toolbar = view2;
    }

    public static ActivityRespiratoryRateSettingsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRespiratoryRateSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRespiratoryRateSettingsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRespiratoryRateSettingsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_respiratory_rate_settings);
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRespiratoryRateSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_settings, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRespiratoryRateSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRespiratoryRateSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_settings, null, false, obj);
    }
}
