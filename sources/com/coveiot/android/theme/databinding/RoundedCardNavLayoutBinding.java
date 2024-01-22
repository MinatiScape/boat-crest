package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class RoundedCardNavLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clRoundedTitle;
    @Bindable
    public String mTitle;
    @NonNull
    public final TextView tvTitle;

    public RoundedCardNavLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.clRoundedTitle = constraintLayout;
        this.tvTitle = textView;
    }

    public static RoundedCardNavLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RoundedCardNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    public abstract void setTitle(@Nullable String str);

    @Deprecated
    public static RoundedCardNavLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RoundedCardNavLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.rounded_card_nav_layout);
    }

    @NonNull
    @Deprecated
    public static RoundedCardNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RoundedCardNavLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.rounded_card_nav_layout, viewGroup, z, obj);
    }

    @NonNull
    public static RoundedCardNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RoundedCardNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RoundedCardNavLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.rounded_card_nav_layout, null, false, obj);
    }
}
