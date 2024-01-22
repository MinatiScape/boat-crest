package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ListItemSleepStagesLayoutBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvSleepStage;
    @NonNull
    public final TextView tvSleepStageInfo;

    public ListItemSleepStagesLayoutBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.tvSleepStage = textView;
        this.tvSleepStageInfo = textView2;
    }

    public static ListItemSleepStagesLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemSleepStagesLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemSleepStagesLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemSleepStagesLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_sleep_stages_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemSleepStagesLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemSleepStagesLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_sleep_stages_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemSleepStagesLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemSleepStagesLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemSleepStagesLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_sleep_stages_layout, null, false, obj);
    }
}
