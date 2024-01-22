package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class SuccessPopUpBinding extends ViewDataBinding {
    @NonNull
    public final Button btOk;
    @NonNull
    public final ImageView imgvCross;
    @NonNull
    public final TextView tvSuccessDesc;
    @NonNull
    public final TextView tvSuccessTitle;

    public SuccessPopUpBinding(Object obj, View view, int i, Button button, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btOk = button;
        this.imgvCross = imageView;
        this.tvSuccessDesc = textView;
        this.tvSuccessTitle = textView2;
    }

    public static SuccessPopUpBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SuccessPopUpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SuccessPopUpBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SuccessPopUpBinding) ViewDataBinding.bind(obj, view, R.layout.success_pop_up);
    }

    @NonNull
    @Deprecated
    public static SuccessPopUpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SuccessPopUpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.success_pop_up, viewGroup, z, obj);
    }

    @NonNull
    public static SuccessPopUpBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SuccessPopUpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SuccessPopUpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.success_pop_up, null, false, obj);
    }
}
