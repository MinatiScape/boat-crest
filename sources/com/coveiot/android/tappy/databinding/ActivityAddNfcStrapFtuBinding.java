package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class ActivityAddNfcStrapFtuBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout fragmentContainer;

    public ActivityAddNfcStrapFtuBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.fragmentContainer = frameLayout;
    }

    public static ActivityAddNfcStrapFtuBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddNfcStrapFtuBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddNfcStrapFtuBinding) ViewDataBinding.bind(obj, view, R.layout.activity_add_nfc_strap_ftu);
    }

    @NonNull
    @Deprecated
    public static ActivityAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddNfcStrapFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_nfc_strap_ftu, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddNfcStrapFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_nfc_strap_ftu, null, false, obj);
    }
}
