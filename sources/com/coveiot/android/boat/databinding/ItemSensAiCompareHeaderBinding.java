package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.sensai.model.SensAICompareData;
/* loaded from: classes3.dex */
public abstract class ItemSensAiCompareHeaderBinding extends ViewDataBinding {
    @NonNull
    public final View listItemDivider;
    @Bindable
    public SensAICompareData mCompareData;
    @NonNull
    public final RecyclerView rvCompare;
    @NonNull
    public final TextView tvCompareTitle;

    public ItemSensAiCompareHeaderBinding(Object obj, View view, int i, View view2, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.listItemDivider = view2;
        this.rvCompare = recyclerView;
        this.tvCompareTitle = textView;
    }

    public static ItemSensAiCompareHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSensAiCompareHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public SensAICompareData getCompareData() {
        return this.mCompareData;
    }

    public abstract void setCompareData(@Nullable SensAICompareData sensAICompareData);

    @Deprecated
    public static ItemSensAiCompareHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemSensAiCompareHeaderBinding) ViewDataBinding.bind(obj, view, R.layout.item_sens_ai_compare_header);
    }

    @NonNull
    @Deprecated
    public static ItemSensAiCompareHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemSensAiCompareHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_compare_header, viewGroup, z, obj);
    }

    @NonNull
    public static ItemSensAiCompareHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemSensAiCompareHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemSensAiCompareHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_compare_header, null, false, obj);
    }
}
