package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class FitnessVitalCardContainerDashboardBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clFirstFitnessVitalsCard;
    @NonNull
    public final FitnessVitalsCardRectangleBinding firstFitnessVitalsCard;
    @NonNull
    public final ItemListFitnessVitalsLayoutFullBinding firstFitnessVitalsCardWithValue;
    @Bindable
    public String mFirstCardType;
    @Bindable
    public Boolean mIsFirstCardDataAvailable;
    @NonNull
    public final RecyclerView rvVitals;

    public FitnessVitalCardContainerDashboardBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, FitnessVitalsCardRectangleBinding fitnessVitalsCardRectangleBinding, ItemListFitnessVitalsLayoutFullBinding itemListFitnessVitalsLayoutFullBinding, RecyclerView recyclerView) {
        super(obj, view, i);
        this.clFirstFitnessVitalsCard = constraintLayout;
        this.firstFitnessVitalsCard = fitnessVitalsCardRectangleBinding;
        this.firstFitnessVitalsCardWithValue = itemListFitnessVitalsLayoutFullBinding;
        this.rvVitals = recyclerView;
    }

    public static FitnessVitalCardContainerDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FitnessVitalCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getFirstCardType() {
        return this.mFirstCardType;
    }

    @Nullable
    public Boolean getIsFirstCardDataAvailable() {
        return this.mIsFirstCardDataAvailable;
    }

    public abstract void setFirstCardType(@Nullable String str);

    public abstract void setIsFirstCardDataAvailable(@Nullable Boolean bool);

    @Deprecated
    public static FitnessVitalCardContainerDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FitnessVitalCardContainerDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.fitness_vital_card_container_dashboard);
    }

    @NonNull
    @Deprecated
    public static FitnessVitalCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FitnessVitalCardContainerDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_vital_card_container_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static FitnessVitalCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FitnessVitalCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FitnessVitalCardContainerDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_vital_card_container_dashboard, null, false, obj);
    }
}
