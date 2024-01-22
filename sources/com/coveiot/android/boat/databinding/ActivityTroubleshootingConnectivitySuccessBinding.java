package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityTroubleshootingConnectivitySuccessBinding extends ViewDataBinding {
    @NonNull
    public final Button btnOk;
    @NonNull
    public final ImageView ivTroubleshootInprogress;
    @Bindable
    public Boolean mIsShowingInProgress;
    @NonNull
    public final View toolbar;
    @NonNull
    public final ConstraintLayout troubleshootInProgressLayout;
    @NonNull
    public final ConstraintLayout troubleshootInSuccessLayout;
    @NonNull
    public final TextView tvTroubleshootingHeader;
    @NonNull
    public final TextView tvYourTroubleshootInProgress;

    public ActivityTroubleshootingConnectivitySuccessBinding(Object obj, View view, int i, Button button, ImageView imageView, View view2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnOk = button;
        this.ivTroubleshootInprogress = imageView;
        this.toolbar = view2;
        this.troubleshootInProgressLayout = constraintLayout;
        this.troubleshootInSuccessLayout = constraintLayout2;
        this.tvTroubleshootingHeader = textView;
        this.tvYourTroubleshootInProgress = textView2;
    }

    public static ActivityTroubleshootingConnectivitySuccessBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityTroubleshootingConnectivitySuccessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getIsShowingInProgress() {
        return this.mIsShowingInProgress;
    }

    public abstract void setIsShowingInProgress(@Nullable Boolean bool);

    @Deprecated
    public static ActivityTroubleshootingConnectivitySuccessBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityTroubleshootingConnectivitySuccessBinding) ViewDataBinding.bind(obj, view, R.layout.activity_troubleshooting_connectivity_success);
    }

    @NonNull
    @Deprecated
    public static ActivityTroubleshootingConnectivitySuccessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityTroubleshootingConnectivitySuccessBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_troubleshooting_connectivity_success, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityTroubleshootingConnectivitySuccessBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityTroubleshootingConnectivitySuccessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityTroubleshootingConnectivitySuccessBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_troubleshooting_connectivity_success, null, false, obj);
    }
}
