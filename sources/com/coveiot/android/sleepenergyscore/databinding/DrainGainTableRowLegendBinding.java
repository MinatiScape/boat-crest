package com.coveiot.android.sleepenergyscore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sleepenergyscore.R;
/* loaded from: classes6.dex */
public abstract class DrainGainTableRowLegendBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageView;
    @NonNull
    public final TextView textView;

    public DrainGainTableRowLegendBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.imageView = imageView;
        this.textView = textView;
    }

    public static DrainGainTableRowLegendBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DrainGainTableRowLegendBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DrainGainTableRowLegendBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DrainGainTableRowLegendBinding) ViewDataBinding.bind(obj, view, R.layout.drain_gain_table_row_legend);
    }

    @NonNull
    @Deprecated
    public static DrainGainTableRowLegendBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DrainGainTableRowLegendBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.drain_gain_table_row_legend, viewGroup, z, obj);
    }

    @NonNull
    public static DrainGainTableRowLegendBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DrainGainTableRowLegendBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DrainGainTableRowLegendBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.drain_gain_table_row_legend, null, false, obj);
    }
}
