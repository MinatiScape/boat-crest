package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensAiFilterItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clFilter;
    @NonNull
    public final TextView filterName;
    @NonNull
    public final View selectedDiv;

    public SensAiFilterItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, View view2) {
        super(obj, view, i);
        this.clFilter = constraintLayout;
        this.filterName = textView;
        this.selectedDiv = view2;
    }

    public static SensAiFilterItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiFilterItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiFilterItemBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_filter_item);
    }

    @NonNull
    @Deprecated
    public static SensAiFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_filter_item, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_filter_item, null, false, obj);
    }
}
