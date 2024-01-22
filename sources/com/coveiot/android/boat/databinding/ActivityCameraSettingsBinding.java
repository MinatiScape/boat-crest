package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityCameraSettingsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout autoStressLay;
    @NonNull
    public final Button btnOk;
    @NonNull
    public final SwitchCompat switchCamera;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvCameraInstructionsText;

    public ActivityCameraSettingsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Button button, SwitchCompat switchCompat, View view2, TextView textView) {
        super(obj, view, i);
        this.autoStressLay = constraintLayout;
        this.btnOk = button;
        this.switchCamera = switchCompat;
        this.toolbar = view2;
        this.tvCameraInstructionsText = textView;
    }

    public static ActivityCameraSettingsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityCameraSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCameraSettingsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityCameraSettingsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_camera_settings);
    }

    @NonNull
    @Deprecated
    public static ActivityCameraSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityCameraSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_camera_settings, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityCameraSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityCameraSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityCameraSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_camera_settings, null, false, obj);
    }
}
