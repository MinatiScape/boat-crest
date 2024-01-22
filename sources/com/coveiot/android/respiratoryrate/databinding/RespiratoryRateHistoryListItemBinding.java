package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public abstract class RespiratoryRateHistoryListItemBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvHistoryItemDate;
    @NonNull
    public final TextView tvHistoryItemMax;
    @NonNull
    public final TextView tvHistoryItemMin;
    @NonNull
    public final View vSeparator;

    public RespiratoryRateHistoryListItemBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.tvHistoryItemDate = textView;
        this.tvHistoryItemMax = textView2;
        this.tvHistoryItemMin = textView3;
        this.vSeparator = view2;
    }

    public static RespiratoryRateHistoryListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RespiratoryRateHistoryListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RespiratoryRateHistoryListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RespiratoryRateHistoryListItemBinding) ViewDataBinding.bind(obj, view, R.layout.respiratory_rate_history_list_item);
    }

    @NonNull
    @Deprecated
    public static RespiratoryRateHistoryListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RespiratoryRateHistoryListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.respiratory_rate_history_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static RespiratoryRateHistoryListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RespiratoryRateHistoryListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RespiratoryRateHistoryListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.respiratory_rate_history_list_item, null, false, obj);
    }
}
