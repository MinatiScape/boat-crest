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
public abstract class ErrorPopUpBinding extends ViewDataBinding {
    @NonNull
    public final Button btOk;
    @NonNull
    public final ImageView imgvCross;
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final TextView tvErrorMsg;
    @NonNull
    public final TextView tvErrorTitle;

    public ErrorPopUpBinding(Object obj, View view, int i, Button button, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btOk = button;
        this.imgvCross = imageView;
        this.ivClose = imageView2;
        this.tvErrorMsg = textView;
        this.tvErrorTitle = textView2;
    }

    public static ErrorPopUpBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ErrorPopUpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ErrorPopUpBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ErrorPopUpBinding) ViewDataBinding.bind(obj, view, R.layout.error_pop_up);
    }

    @NonNull
    @Deprecated
    public static ErrorPopUpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ErrorPopUpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.error_pop_up, viewGroup, z, obj);
    }

    @NonNull
    public static ErrorPopUpBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ErrorPopUpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ErrorPopUpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.error_pop_up, null, false, obj);
    }
}
