package com.coveiot.android.sportsnotification.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sportsnotification.R;
/* loaded from: classes7.dex */
public abstract class FilterOptionListItemBinding extends ViewDataBinding {
    @NonNull
    public final CheckBox cbFilter;

    public FilterOptionListItemBinding(Object obj, View view, int i, CheckBox checkBox) {
        super(obj, view, i);
        this.cbFilter = checkBox;
    }

    public static FilterOptionListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FilterOptionListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FilterOptionListItemBinding) ViewDataBinding.bind(obj, view, R.layout.filter_option_list_item);
    }

    @NonNull
    @Deprecated
    public static FilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FilterOptionListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.filter_option_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static FilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilterOptionListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FilterOptionListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.filter_option_list_item, null, false, obj);
    }
}
