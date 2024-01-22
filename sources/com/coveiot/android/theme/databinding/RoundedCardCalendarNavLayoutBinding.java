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
public abstract class RoundedCardCalendarNavLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imgArrowLeft;
    @NonNull
    public final ImageView imgArrowRight;
    @NonNull
    public final TextView tvDate;

    public RoundedCardCalendarNavLayoutBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView) {
        super(obj, view, i);
        this.imgArrowLeft = imageView;
        this.imgArrowRight = imageView2;
        this.tvDate = textView;
    }

    public static RoundedCardCalendarNavLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RoundedCardCalendarNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RoundedCardCalendarNavLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RoundedCardCalendarNavLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.rounded_card_calendar_nav_layout);
    }

    @NonNull
    @Deprecated
    public static RoundedCardCalendarNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RoundedCardCalendarNavLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.rounded_card_calendar_nav_layout, viewGroup, z, obj);
    }

    @NonNull
    public static RoundedCardCalendarNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RoundedCardCalendarNavLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RoundedCardCalendarNavLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.rounded_card_calendar_nav_layout, null, false, obj);
    }
}
