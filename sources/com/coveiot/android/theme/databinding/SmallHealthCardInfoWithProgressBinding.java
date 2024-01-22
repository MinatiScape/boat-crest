package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.model.SmallHealthCardInfo;
/* loaded from: classes7.dex */
public abstract class SmallHealthCardInfoWithProgressBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final LinearLayout llData;
    @Bindable
    public SmallHealthCardInfo mHealthInfo;
    @Bindable
    public Integer mProgress;
    @NonNull
    public final ProgressBar progressBar1;
    @NonNull
    public final TextView tvBottom;
    @NonNull
    public final TextView tvValue1;
    @NonNull
    public final TextView tvValue2;
    @NonNull
    public final TextView tvValue3;
    @NonNull
    public final TextView tvValue4;

    public SmallHealthCardInfoWithProgressBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.ivProfile = imageView;
        this.llData = linearLayout;
        this.progressBar1 = progressBar;
        this.tvBottom = textView;
        this.tvValue1 = textView2;
        this.tvValue2 = textView3;
        this.tvValue3 = textView4;
        this.tvValue4 = textView5;
    }

    public static SmallHealthCardInfoWithProgressBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SmallHealthCardInfoWithProgressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public SmallHealthCardInfo getHealthInfo() {
        return this.mHealthInfo;
    }

    @Nullable
    public Integer getProgress() {
        return this.mProgress;
    }

    public abstract void setHealthInfo(@Nullable SmallHealthCardInfo smallHealthCardInfo);

    public abstract void setProgress(@Nullable Integer num);

    @Deprecated
    public static SmallHealthCardInfoWithProgressBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SmallHealthCardInfoWithProgressBinding) ViewDataBinding.bind(obj, view, R.layout.small_health_card_info_with_progress);
    }

    @NonNull
    @Deprecated
    public static SmallHealthCardInfoWithProgressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SmallHealthCardInfoWithProgressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.small_health_card_info_with_progress, viewGroup, z, obj);
    }

    @NonNull
    public static SmallHealthCardInfoWithProgressBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SmallHealthCardInfoWithProgressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SmallHealthCardInfoWithProgressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.small_health_card_info_with_progress, null, false, obj);
    }
}
