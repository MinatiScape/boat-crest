package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class DoMoreWithYourWatchCardContainerDashboardBinding extends ViewDataBinding {
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding alexaConnect;
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding buildFitnessPlan;
    @Bindable
    public Boolean mShowAlexaConnect;
    @Bindable
    public Boolean mShowFitnessPlan;
    @Bindable
    public Boolean mShowSOSSettings;
    @Bindable
    public Boolean mShowSportScores;
    @Bindable
    public Boolean mShowTapAndPay;
    @Bindable
    public Boolean mShowWellnessCrew;
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding sosSettings;
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding sportScores;
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding tapAndPay;
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding wellnessCrewSetup;

    public DoMoreWithYourWatchCardContainerDashboardBinding(Object obj, View view, int i, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding2, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding3, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding4, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding5, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding6) {
        super(obj, view, i);
        this.alexaConnect = layoutDashboardDoMoreWithYourWatchGridItemBinding;
        this.buildFitnessPlan = layoutDashboardDoMoreWithYourWatchGridItemBinding2;
        this.sosSettings = layoutDashboardDoMoreWithYourWatchGridItemBinding3;
        this.sportScores = layoutDashboardDoMoreWithYourWatchGridItemBinding4;
        this.tapAndPay = layoutDashboardDoMoreWithYourWatchGridItemBinding5;
        this.wellnessCrewSetup = layoutDashboardDoMoreWithYourWatchGridItemBinding6;
    }

    public static DoMoreWithYourWatchCardContainerDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DoMoreWithYourWatchCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getShowAlexaConnect() {
        return this.mShowAlexaConnect;
    }

    @Nullable
    public Boolean getShowFitnessPlan() {
        return this.mShowFitnessPlan;
    }

    @Nullable
    public Boolean getShowSOSSettings() {
        return this.mShowSOSSettings;
    }

    @Nullable
    public Boolean getShowSportScores() {
        return this.mShowSportScores;
    }

    @Nullable
    public Boolean getShowTapAndPay() {
        return this.mShowTapAndPay;
    }

    @Nullable
    public Boolean getShowWellnessCrew() {
        return this.mShowWellnessCrew;
    }

    public abstract void setShowAlexaConnect(@Nullable Boolean bool);

    public abstract void setShowFitnessPlan(@Nullable Boolean bool);

    public abstract void setShowSOSSettings(@Nullable Boolean bool);

    public abstract void setShowSportScores(@Nullable Boolean bool);

    public abstract void setShowTapAndPay(@Nullable Boolean bool);

    public abstract void setShowWellnessCrew(@Nullable Boolean bool);

    @Deprecated
    public static DoMoreWithYourWatchCardContainerDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DoMoreWithYourWatchCardContainerDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.do_more_with_your_watch_card_container_dashboard);
    }

    @NonNull
    @Deprecated
    public static DoMoreWithYourWatchCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DoMoreWithYourWatchCardContainerDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.do_more_with_your_watch_card_container_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static DoMoreWithYourWatchCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DoMoreWithYourWatchCardContainerDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DoMoreWithYourWatchCardContainerDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.do_more_with_your_watch_card_container_dashboard, null, false, obj);
    }
}
