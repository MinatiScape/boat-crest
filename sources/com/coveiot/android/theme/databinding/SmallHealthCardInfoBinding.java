package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.model.SmallHealthCardInfo;
/* loaded from: classes7.dex */
public abstract class SmallHealthCardInfoBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final LinearLayout llData;
    @Bindable
    public SmallHealthCardInfo mHealthInfo;
    @NonNull
    public final TextView tvBottom;
    @NonNull
    public final TextView tvValue1;
    @NonNull
    public final TextView tvValue2;

    public SmallHealthCardInfoBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivIcon = imageView;
        this.llData = linearLayout;
        this.tvBottom = textView;
        this.tvValue1 = textView2;
        this.tvValue2 = textView3;
    }

    public static SmallHealthCardInfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SmallHealthCardInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public SmallHealthCardInfo getHealthInfo() {
        return this.mHealthInfo;
    }

    public abstract void setHealthInfo(@Nullable SmallHealthCardInfo smallHealthCardInfo);

    @Deprecated
    public static SmallHealthCardInfoBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SmallHealthCardInfoBinding) ViewDataBinding.bind(obj, view, R.layout.small_health_card_info);
    }

    @NonNull
    @Deprecated
    public static SmallHealthCardInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SmallHealthCardInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.small_health_card_info, viewGroup, z, obj);
    }

    @NonNull
    public static SmallHealthCardInfoBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SmallHealthCardInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SmallHealthCardInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.small_health_card_info, null, false, obj);
    }
}
