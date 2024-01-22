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
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public abstract class ActivityRespiratoryRateShareBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout containerLayout;
    @NonNull
    public final Guideline glButton;
    @NonNull
    public final TextView shareButton;
    @NonNull
    public final ImageView shareCloseImage;
    @NonNull
    public final LinearLayout shareCloseLayout;

    public ActivityRespiratoryRateShareBinding(Object obj, View view, int i, RelativeLayout relativeLayout, Guideline guideline, TextView textView, ImageView imageView, LinearLayout linearLayout) {
        super(obj, view, i);
        this.containerLayout = relativeLayout;
        this.glButton = guideline;
        this.shareButton = textView;
        this.shareCloseImage = imageView;
        this.shareCloseLayout = linearLayout;
    }

    public static ActivityRespiratoryRateShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRespiratoryRateShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRespiratoryRateShareBinding) ViewDataBinding.bind(obj, view, R.layout.activity_respiratory_rate_share);
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRespiratoryRateShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_share, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRespiratoryRateShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_share, null, false, obj);
    }
}
