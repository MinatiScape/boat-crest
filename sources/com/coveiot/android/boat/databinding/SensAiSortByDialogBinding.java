package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensAiSortByDialogBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rvSortByList;
    @NonNull
    public final TextView tvSortBy;

    public SensAiSortByDialogBinding(Object obj, View view, int i, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.rvSortByList = recyclerView;
        this.tvSortBy = textView;
    }

    public static SensAiSortByDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiSortByDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiSortByDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiSortByDialogBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_sort_by_dialog);
    }

    @NonNull
    @Deprecated
    public static SensAiSortByDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiSortByDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_sort_by_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiSortByDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiSortByDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiSortByDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_sort_by_dialog, null, false, obj);
    }
}
