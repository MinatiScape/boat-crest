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
public abstract class ActivityAddPaymentCardBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final View toolbar;

    public ActivityAddPaymentCardBinding(Object obj, View view, int i, FrameLayout frameLayout, View view2) {
        super(obj, view, i);
        this.fragmentContainer = frameLayout;
        this.toolbar = view2;
    }

    public static ActivityAddPaymentCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddPaymentCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddPaymentCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddPaymentCardBinding) ViewDataBinding.bind(obj, view, R.layout.activity_add_payment_card);
    }

    @NonNull
    @Deprecated
    public static ActivityAddPaymentCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddPaymentCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_payment_card, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddPaymentCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddPaymentCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddPaymentCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_payment_card, null, false, obj);
    }
}
