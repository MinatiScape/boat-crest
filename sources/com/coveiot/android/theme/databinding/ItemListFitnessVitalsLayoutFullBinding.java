package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
import com.github.anastr.speedviewlib.SpeedView;
/* loaded from: classes7.dex */
public abstract class ItemListFitnessVitalsLayoutFullBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clLevel;
    @NonNull
    public final ConstraintLayout clUnit;
    @NonNull
    public final ConstraintLayout clVitalImage;
    @NonNull
    public final ImageView ivVitalImage;
    @NonNull
    public final SpeedView speedViewStress;
    @NonNull
    public final TextView tvAttention;
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
    @NonNull
    public final View view1;

    public ItemListFitnessVitalsLayoutFullBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ImageView imageView, SpeedView speedView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, View view2, View view3) {
        super(obj, view, i);
        this.clLevel = constraintLayout;
        this.clUnit = constraintLayout2;
        this.clVitalImage = constraintLayout3;
        this.ivVitalImage = imageView;
        this.speedViewStress = speedView;
        this.tvAttention = textView;
        this.tvVitalDownUpPercentage = textView2;
        this.tvVitalLastUpdatedTime = textView3;
        this.tvVitalLevel = textView4;
        this.tvVitalName = textView5;
        this.tvVitalValue = textView6;
        this.tvVitalValueUnit = textView7;
        this.view = view2;
        this.view1 = view3;
    }

    public static ItemListFitnessVitalsLayoutFullBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemListFitnessVitalsLayoutFullBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemListFitnessVitalsLayoutFullBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemListFitnessVitalsLayoutFullBinding) ViewDataBinding.bind(obj, view, R.layout.item_list_fitness_vitals_layout_full);
    }

    @NonNull
    @Deprecated
    public static ItemListFitnessVitalsLayoutFullBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemListFitnessVitalsLayoutFullBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_list_fitness_vitals_layout_full, viewGroup, z, obj);
    }

    @NonNull
    public static ItemListFitnessVitalsLayoutFullBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemListFitnessVitalsLayoutFullBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemListFitnessVitalsLayoutFullBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_list_fitness_vitals_layout_full, null, false, obj);
    }
}
