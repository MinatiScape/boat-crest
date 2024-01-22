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
import com.github.anastr.speedviewlib.SpeedView;
/* loaded from: classes7.dex */
public abstract class ItemListFitnessVitalsLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clLevel;
    @NonNull
    public final ConstraintLayout clVitalImage;
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final ImageView ivVitalImage;
    @Bindable
    public Boolean mIsDataAvailable;
    @NonNull
    public final SpeedView speedViewStress;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvVitalDownUpPercentage;
    @NonNull
    public final TextView tvVitalLastUpdatedTime;
    @NonNull
    public final TextView tvVitalLevel;
    @NonNull
    public final TextView tvVitalName;
    @NonNull
    public final TextView tvVitalValue;
    @NonNull
    public final TextView tvVitalValueUnit;
    @NonNull
    public final View view;

    public ItemListFitnessVitalsLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, SpeedView speedView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view2) {
        super(obj, view, i);
        this.clLevel = constraintLayout;
        this.clVitalImage = constraintLayout2;
        this.ivIcon = imageView;
        this.ivVitalImage = imageView2;
        this.speedViewStress = speedView;
        this.tvHeader = textView;
        this.tvInfo = textView2;
        this.tvVitalDownUpPercentage = textView3;
        this.tvVitalLastUpdatedTime = textView4;
        this.tvVitalLevel = textView5;
        this.tvVitalName = textView6;
        this.tvVitalValue = textView7;
        this.tvVitalValueUnit = textView8;
        this.view = view2;
    }

    public static ItemListFitnessVitalsLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemListFitnessVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getIsDataAvailable() {
        return this.mIsDataAvailable;
    }

    public abstract void setIsDataAvailable(@Nullable Boolean bool);

    @Deprecated
    public static ItemListFitnessVitalsLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemListFitnessVitalsLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.item_list_fitness_vitals_layout);
    }

    @NonNull
    @Deprecated
    public static ItemListFitnessVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemListFitnessVitalsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_list_fitness_vitals_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ItemListFitnessVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemListFitnessVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemListFitnessVitalsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_list_fitness_vitals_layout, null, false, obj);
    }
}
