package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.FitnessBuddiesModel;
/* loaded from: classes3.dex */
public abstract class ProfileFitnessItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivArrow;
    @NonNull
    public final ImageView ivFitness;
    @NonNull
    public final View listItemDivider;
    @Bindable
    public FitnessBuddiesModel mFitnessData;
    @NonNull
    public final RecyclerView rvFitnessSubheaders;
    @NonNull
    public final TextView tvFitness;

    public ProfileFitnessItemBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, View view2, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.ivArrow = imageView;
        this.ivFitness = imageView2;
        this.listItemDivider = view2;
        this.rvFitnessSubheaders = recyclerView;
        this.tvFitness = textView;
    }

    public static ProfileFitnessItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileFitnessItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public FitnessBuddiesModel getFitnessData() {
        return this.mFitnessData;
    }

    public abstract void setFitnessData(@Nullable FitnessBuddiesModel fitnessBuddiesModel);

    @Deprecated
    public static ProfileFitnessItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileFitnessItemBinding) ViewDataBinding.bind(obj, view, R.layout.profile_fitness_item);
    }

    @NonNull
    @Deprecated
    public static ProfileFitnessItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileFitnessItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_fitness_item, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileFitnessItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileFitnessItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileFitnessItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_fitness_item, null, false, obj);
    }
}
