package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ListItemOtpServiceTypeLayoutBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvServiceType;

    public ListItemOtpServiceTypeLayoutBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvServiceType = textView;
    }

    public static ListItemOtpServiceTypeLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemOtpServiceTypeLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemOtpServiceTypeLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemOtpServiceTypeLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_otp_service_type_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemOtpServiceTypeLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemOtpServiceTypeLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_otp_service_type_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemOtpServiceTypeLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemOtpServiceTypeLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemOtpServiceTypeLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_otp_service_type_layout, null, false, obj);
    }
}
