package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutRecenterBtnBinding extends ViewDataBinding {
    @NonNull
    public final ImageView recenterImageView;
    @NonNull
    public final LinearLayout recenterLinearLayout;
    @NonNull
    public final TextView recenterTextView;

    public LayoutRecenterBtnBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, TextView textView) {
        super(obj, view, i);
        this.recenterImageView = imageView;
        this.recenterLinearLayout = linearLayout;
        this.recenterTextView = textView;
    }

    public static LayoutRecenterBtnBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutRecenterBtnBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutRecenterBtnBinding) ViewDataBinding.bind(obj, view, R.layout.layout_recenter_btn);
    }

    @NonNull
    public static LayoutRecenterBtnBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutRecenterBtnBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutRecenterBtnBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutRecenterBtnBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_recenter_btn, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutRecenterBtnBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutRecenterBtnBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_recenter_btn, null, false, obj);
    }
}
