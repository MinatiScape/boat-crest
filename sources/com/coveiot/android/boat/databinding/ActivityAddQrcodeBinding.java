package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityAddQrcodeBinding extends ViewDataBinding {
    @NonNull
    public final FragmentContainerView fragmentContainerAddQrCode;

    public ActivityAddQrcodeBinding(Object obj, View view, int i, FragmentContainerView fragmentContainerView) {
        super(obj, view, i);
        this.fragmentContainerAddQrCode = fragmentContainerView;
    }

    public static ActivityAddQrcodeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddQrcodeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddQrcodeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddQrcodeBinding) ViewDataBinding.bind(obj, view, R.layout.activity_add_qrcode);
    }

    @NonNull
    @Deprecated
    public static ActivityAddQrcodeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddQrcodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_qrcode, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddQrcodeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddQrcodeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddQrcodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_qrcode, null, false, obj);
    }
}
