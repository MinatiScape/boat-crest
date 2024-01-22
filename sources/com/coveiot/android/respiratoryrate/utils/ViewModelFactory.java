package com.coveiot.android.respiratoryrate.utils;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateSettingsViewModel;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5678a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5678a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(RespiratoryRateViewModel.class)) {
            return new RespiratoryRateViewModel(this.f5678a);
        }
        if (modelClass.isAssignableFrom(RespiratoryRateHistoryViewModel.class)) {
            return new RespiratoryRateHistoryViewModel(this.f5678a);
        }
        if (modelClass.isAssignableFrom(RespiratoryRateSettingsViewModel.class)) {
            return new RespiratoryRateSettingsViewModel(this.f5678a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f5678a;
    }
}
