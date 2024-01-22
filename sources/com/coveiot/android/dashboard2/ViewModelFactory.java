package com.coveiot.android.dashboard2;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.dashboard2.viewmodel.EditDashboardVitalsViewModel;
import com.coveiot.android.dashboard2.viewmodel.EnergyMeterDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel;
import com.coveiot.android.dashboard2.viewmodel.HRVDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.HeartRateDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel;
import com.coveiot.android.dashboard2.viewmodel.RespiratoryRateDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.SPO2DataViewModel;
import com.coveiot.android.dashboard2.viewmodel.SleepDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.StepsDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.StressDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.TemperatureDataViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4210a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4210a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(FragmentHomeViewModel.class)) {
            return new FragmentHomeViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(DataSyncViewModel.class)) {
            return new DataSyncViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(StepsDataViewModel.class)) {
            return new StepsDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(SleepDataViewModel.class)) {
            return new SleepDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(HeartRateDataViewModel.class)) {
            return new HeartRateDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(SPO2DataViewModel.class)) {
            return new SPO2DataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(HRVDataViewModel.class)) {
            return new HRVDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(StressDataViewModel.class)) {
            return new StressDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(EnergyMeterDataViewModel.class)) {
            return new EnergyMeterDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(RespiratoryRateDataViewModel.class)) {
            return new RespiratoryRateDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(TemperatureDataViewModel.class)) {
            return new TemperatureDataViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(EditDashboardVitalsViewModel.class)) {
            return new EditDashboardVitalsViewModel(this.f4210a);
        }
        if (modelClass.isAssignableFrom(PromotionsViewModel.class)) {
            return new PromotionsViewModel(this.f4210a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f4210a;
    }
}
