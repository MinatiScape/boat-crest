package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.model.BindingDataModel1;
/* loaded from: classes7.dex */
public abstract class SmallRoundedCardIconHeaderStatusBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ImageView ivIcon;
    @Bindable
    public BindingDataModel1 mBindingDataModel1;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvStatus;

    public SmallRoundedCardIconHeaderStatusBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clMain = constraintLayout;
        this.ivIcon = imageView;
        this.tvHeader = textView;
        this.tvStatus = textView2;
    }

    public static SmallRoundedCardIconHeaderStatusBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SmallRoundedCardIconHeaderStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public BindingDataModel1 getBindingDataModel1() {
        return this.mBindingDataModel1;
    }

    public abstract void setBindingDataModel1(@Nullable BindingDataModel1 bindingDataModel1);

    @Deprecated
    public static SmallRoundedCardIconHeaderStatusBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SmallRoundedCardIconHeaderStatusBinding) ViewDataBinding.bind(obj, view, R.layout.small_rounded_card_icon_header_status);
    }

    @NonNull
    @Deprecated
    public static SmallRoundedCardIconHeaderStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SmallRoundedCardIconHeaderStatusBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.small_rounded_card_icon_header_status, viewGroup, z, obj);
    }

    @NonNull
    public static SmallRoundedCardIconHeaderStatusBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SmallRoundedCardIconHeaderStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SmallRoundedCardIconHeaderStatusBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.small_rounded_card_icon_header_status, null, false, obj);
    }
}
