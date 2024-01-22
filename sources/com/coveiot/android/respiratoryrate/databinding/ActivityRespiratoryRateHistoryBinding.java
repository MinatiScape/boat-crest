package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public abstract class ActivityRespiratoryRateHistoryBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout fragmentContainer;

    public ActivityRespiratoryRateHistoryBinding(Object obj, View view, int i, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.fragmentContainer = relativeLayout;
    }

    public static ActivityRespiratoryRateHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRespiratoryRateHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRespiratoryRateHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.activity_respiratory_rate_history);
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRespiratoryRateHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_history, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRespiratoryRateHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_respiratory_rate_history, null, false, obj);
    }
}
