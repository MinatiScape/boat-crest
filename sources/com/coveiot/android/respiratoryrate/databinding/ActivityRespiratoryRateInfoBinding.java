package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public abstract class ActivityRespiratoryRateInfoBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final LinearLayout llTv34;
    @NonNull
    public final TextView titleText;
    @NonNull
    public final RelativeLayout toolbar;
    @NonNull
    public final TextView tv1;
    @NonNull
    public final TextView tv2;
    @NonNull
    public final TextView tv3;
    @NonNull
    public final TextView tv4;
    @NonNull
    public final TextView tv5;

    public ActivityRespiratoryRateInfoBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, TextView textView, RelativeLayout relativeLayout, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.ivClose = imageView;
        this.llTv34 = linearLayout;
        this.titleText = textView;
        this.toolbar = relativeLayout;
        this.tv1 = textView2;
        this.tv2 = textView3;
        this.tv3 = textView4;
        this.tv4 = textView5;
        this.tv5 = textView6;
    }

    public static ActivityRespiratoryRateInfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRespiratoryRateInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRespiratoryRateInfoBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRespiratoryRateInfoBinding) ViewDataBinding.bind(obj, view, R.layout.activity_respiratory_rate_info);
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRespiratoryRateInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_info, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRespiratoryRateInfoBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRespiratoryRateInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_info, null, false, obj);
    }
}
