package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ItemSensAiFilterBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvFilterTitle;

    public ItemSensAiFilterBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvFilterTitle = textView;
    }

    public static ItemSensAiFilterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSensAiFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSensAiFilterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemSensAiFilterBinding) ViewDataBinding.bind(obj, view, R.layout.item_sens_ai_filter);
    }

    @NonNull
    @Deprecated
    public static ItemSensAiFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemSensAiFilterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_filter, viewGroup, z, obj);
    }

    @NonNull
    public static ItemSensAiFilterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemSensAiFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemSensAiFilterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_filter, null, false, obj);
    }
}
