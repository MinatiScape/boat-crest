package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.sensai.model.SensAICompareListItem;
/* loaded from: classes3.dex */
public abstract class ItemSensAiCompareListBinding extends ViewDataBinding {
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final Guideline guideline2;
    @NonNull
    public final Guideline guideline3;
    @NonNull
    public final Guideline guideline4;
    @NonNull
    public final ImageView ivCompareImage;
    @Bindable
    public SensAICompareListItem mCompareData;
    @NonNull
    public final TextView tvCompareTitle;
    @NonNull
    public final TextView tvCompareValue1;
    @NonNull
    public final TextView tvCompareValue2;

    public ItemSensAiCompareListBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.guideline1 = guideline;
        this.guideline2 = guideline2;
        this.guideline3 = guideline3;
        this.guideline4 = guideline4;
        this.ivCompareImage = imageView;
        this.tvCompareTitle = textView;
        this.tvCompareValue1 = textView2;
        this.tvCompareValue2 = textView3;
    }

    public static ItemSensAiCompareListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSensAiCompareListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public SensAICompareListItem getCompareData() {
        return this.mCompareData;
    }

    public abstract void setCompareData(@Nullable SensAICompareListItem sensAICompareListItem);

    @Deprecated
    public static ItemSensAiCompareListBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemSensAiCompareListBinding) ViewDataBinding.bind(obj, view, R.layout.item_sens_ai_compare_list);
    }

    @NonNull
    @Deprecated
    public static ItemSensAiCompareListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemSensAiCompareListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_compare_list, viewGroup, z, obj);
    }

    @NonNull
    public static ItemSensAiCompareListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemSensAiCompareListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemSensAiCompareListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sens_ai_compare_list, null, false, obj);
    }
}
