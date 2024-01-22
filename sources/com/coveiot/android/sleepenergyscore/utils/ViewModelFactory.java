package com.coveiot.android.sleepenergyscore.utils;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.ActivityShareViewModel;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterHistoryViewModel;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModel;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew;
import com.coveiot.android.sleepenergyscore.feedback.SleepEnergyScoreFeedbackViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5748a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5748a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(FragmentEnergyMeterViewModel.class)) {
            return new FragmentEnergyMeterViewModel(this.f5748a);
        }
        if (modelClass.isAssignableFrom(FragmentEnergyMeterHistoryViewModel.class)) {
            return new FragmentEnergyMeterHistoryViewModel(this.f5748a);
        }
        if (modelClass.isAssignableFrom(ActivityShareViewModel.class)) {
            return new ActivityShareViewModel(this.f5748a);
        }
        if (modelClass.isAssignableFrom(SleepEnergyScoreFeedbackViewModel.class)) {
            return new SleepEnergyScoreFeedbackViewModel(this.f5748a);
        }
        if (modelClass.isAssignableFrom(FragmentEnergyMeterViewModelNew.class)) {
            return new FragmentEnergyMeterViewModelNew(this.f5748a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f5748a;
    }
}
