package com.coveiot.android.dashboard2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.dashboard2.R;
/* loaded from: classes4.dex */
public abstract class ItemEditHomeDashboardVitalsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clItem;
    @NonNull
    public final ImageView ivHamburger;
    @NonNull
    public final ImageView ivVitalImage;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvVitalName;

    public ItemEditHomeDashboardVitalsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clItem = constraintLayout;
        this.ivHamburger = imageView;
        this.ivVitalImage = imageView2;
        this.tvInfo = textView;
        this.tvVitalName = textView2;
    }

    public static ItemEditHomeDashboardVitalsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemEditHomeDashboardVitalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemEditHomeDashboardVitalsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemEditHomeDashboardVitalsBinding) ViewDataBinding.bind(obj, view, R.layout.item_edit_home_dashboard_vitals);
    }

    @NonNull
    @Deprecated
    public static ItemEditHomeDashboardVitalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemEditHomeDashboardVitalsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_edit_home_dashboard_vitals, viewGroup, z, obj);
    }

    @NonNull
    public static ItemEditHomeDashboardVitalsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemEditHomeDashboardVitalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemEditHomeDashboardVitalsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_edit_home_dashboard_vitals, null, false, obj);
    }
}
