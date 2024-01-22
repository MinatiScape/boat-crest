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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class DeviceNotPairedBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clCompleteProfile;
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final TextView tvCompleteProfile;
    @NonNull
    public final TextView tvContent;
    @NonNull
    public final TextView tvHeader;

    public DeviceNotPairedBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ImageView imageView, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clCompleteProfile = constraintLayout;
        this.clMain = constraintLayout2;
        this.clProgress = constraintLayout3;
        this.ivProfile = imageView;
        this.progressBar = progressBar;
        this.tvCompleteProfile = textView;
        this.tvContent = textView2;
        this.tvHeader = textView3;
    }

    public static DeviceNotPairedBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DeviceNotPairedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DeviceNotPairedBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DeviceNotPairedBinding) ViewDataBinding.bind(obj, view, R.layout.device_not_paired);
    }

    @NonNull
    @Deprecated
    public static DeviceNotPairedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DeviceNotPairedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.device_not_paired, viewGroup, z, obj);
    }

    @NonNull
    public static DeviceNotPairedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DeviceNotPairedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DeviceNotPairedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.device_not_paired, null, false, obj);
    }
}
