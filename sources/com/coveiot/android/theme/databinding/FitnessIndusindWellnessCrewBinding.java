package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class FitnessIndusindWellnessCrewBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;

    public FitnessIndusindWellnessCrewBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivIcon = imageView;
        this.tvHeader = textView;
        this.tvInfo = textView2;
    }

    public static FitnessIndusindWellnessCrewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FitnessIndusindWellnessCrewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FitnessIndusindWellnessCrewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FitnessIndusindWellnessCrewBinding) ViewDataBinding.bind(obj, view, R.layout.fitness_indusind_wellness_crew);
    }

    @NonNull
    @Deprecated
    public static FitnessIndusindWellnessCrewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FitnessIndusindWellnessCrewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_indusind_wellness_crew, viewGroup, z, obj);
    }

    @NonNull
    public static FitnessIndusindWellnessCrewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FitnessIndusindWellnessCrewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FitnessIndusindWellnessCrewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_indusind_wellness_crew, null, false, obj);
    }
}
