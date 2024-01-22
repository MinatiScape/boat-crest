package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.model.TipsModel;
/* loaded from: classes7.dex */
public abstract class ListItemVitalTipsLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout cvImage;
    @NonNull
    public final ImageView ivStress;
    @Bindable
    public TipsModel mTipsData;
    @NonNull
    public final TextView tvStressName;

    public ListItemVitalTipsLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.cvImage = constraintLayout;
        this.ivStress = imageView;
        this.tvStressName = textView;
    }

    public static ListItemVitalTipsLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemVitalTipsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public TipsModel getTipsData() {
        return this.mTipsData;
    }

    public abstract void setTipsData(@Nullable TipsModel tipsModel);

    @Deprecated
    public static ListItemVitalTipsLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemVitalTipsLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_vital_tips_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemVitalTipsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemVitalTipsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_vital_tips_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemVitalTipsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemVitalTipsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemVitalTipsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_vital_tips_layout, null, false, obj);
    }
}
