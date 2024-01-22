package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensAiFilterOptionListItemBinding extends ViewDataBinding {
    @NonNull
    public final CheckBox cbFilter;

    public SensAiFilterOptionListItemBinding(Object obj, View view, int i, CheckBox checkBox) {
        super(obj, view, i);
        this.cbFilter = checkBox;
    }

    public static SensAiFilterOptionListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiFilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiFilterOptionListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiFilterOptionListItemBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_filter_option_list_item);
    }

    @NonNull
    @Deprecated
    public static SensAiFilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiFilterOptionListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_filter_option_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiFilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiFilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiFilterOptionListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_filter_option_list_item, null, false, obj);
    }
}
