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
public abstract class ItemSensAiSortByBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvSortByTitle;
    @NonNull
    public final View view1;

    public ItemSensAiSortByBinding(Object obj, View view, int i, TextView textView, View view2) {
        super(obj, view, i);
        this.tvSortByTitle = textView;
        this.view1 = view2;
    }

    public static ItemSensAiSortByBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSensAiSortByBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSensAiSortByBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemSensAiSortByBinding) ViewDataBinding.bind(obj, view, R.layout.item_sens_ai_sort_by);
    }

    @NonNull
    @Deprecated
    public static ItemSensAiSortByBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemSensAiSortByBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_sort_by, viewGroup, z, obj);
    }

    @NonNull
    public static ItemSensAiSortByBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemSensAiSortByBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemSensAiSortByBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_sort_by, null, false, obj);
    }
}
