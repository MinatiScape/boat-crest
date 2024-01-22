package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class InfoDetailsBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivDot1;
    @NonNull
    public final ImageView ivDot2;
    @NonNull
    public final ImageView ivInfo;
    @NonNull
    public final TextView tvInfoText;
    @NonNull
    public final TextView tvInfoText2;

    public InfoDetailsBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivDot1 = imageView;
        this.ivDot2 = imageView2;
        this.ivInfo = imageView3;
        this.tvInfoText = textView;
        this.tvInfoText2 = textView2;
    }

    public static InfoDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static InfoDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static InfoDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (InfoDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.info_details);
    }

    @NonNull
    @Deprecated
    public static InfoDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (InfoDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.info_details, viewGroup, z, obj);
    }

    @NonNull
    public static InfoDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static InfoDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (InfoDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.info_details, null, false, obj);
    }
}
