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
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.FitnessBuddiesSubModel;
/* loaded from: classes3.dex */
public abstract class ProfileFitnessSubheaderItemBinding extends ViewDataBinding {
    @NonNull
    public final View listItemDivider;
    @Bindable
    public FitnessBuddiesSubModel mFitnessData;
    @NonNull
    public final TextView tvLable;

    public ProfileFitnessSubheaderItemBinding(Object obj, View view, int i, View view2, TextView textView) {
        super(obj, view, i);
        this.listItemDivider = view2;
        this.tvLable = textView;
    }

    public static ProfileFitnessSubheaderItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileFitnessSubheaderItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public FitnessBuddiesSubModel getFitnessData() {
        return this.mFitnessData;
    }

    public abstract void setFitnessData(@Nullable FitnessBuddiesSubModel fitnessBuddiesSubModel);

    @Deprecated
    public static ProfileFitnessSubheaderItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileFitnessSubheaderItemBinding) ViewDataBinding.bind(obj, view, R.layout.profile_fitness_subheader_item);
    }

    @NonNull
    @Deprecated
    public static ProfileFitnessSubheaderItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileFitnessSubheaderItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_fitness_subheader_item, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileFitnessSubheaderItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileFitnessSubheaderItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileFitnessSubheaderItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_fitness_subheader_item, null, false, obj);
    }
}
