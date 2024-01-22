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
public abstract class LayoutCultFitFtuCardBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final TextView tvActionButton;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo1;
    @NonNull
    public final TextView tvInfo2;

    public LayoutCultFitFtuCardBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.ivIcon = imageView;
        this.tvActionButton = textView;
        this.tvHeader = textView2;
        this.tvInfo1 = textView3;
        this.tvInfo2 = textView4;
    }

    public static LayoutCultFitFtuCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutCultFitFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCultFitFtuCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutCultFitFtuCardBinding) ViewDataBinding.bind(obj, view, R.layout.layout_cult_fit_ftu_card);
    }

    @NonNull
    @Deprecated
    public static LayoutCultFitFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutCultFitFtuCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_cult_fit_ftu_card, viewGroup, z, obj);
    }

    @NonNull
    public static LayoutCultFitFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutCultFitFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutCultFitFtuCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_cult_fit_ftu_card, null, false, obj);
    }
}
