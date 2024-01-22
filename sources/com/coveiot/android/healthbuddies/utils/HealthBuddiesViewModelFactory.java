package com.coveiot.android.healthbuddies.utils;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.healthbuddies.viewmodels.AddHealthBuddiesViewModel;
import com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel;
import com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class HealthBuddiesViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4585a;

    public HealthBuddiesViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4585a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(AddHealthBuddiesViewModel.class)) {
            return new AddHealthBuddiesViewModel(this.f4585a);
        }
        if (modelClass.isAssignableFrom(ManageHealthBuddiesViewModel.class)) {
            return new ManageHealthBuddiesViewModel(this.f4585a);
        }
        if (modelClass.isAssignableFrom(ManageDoctorViewModel.class)) {
            return new ManageDoctorViewModel(this.f4585a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f4585a;
    }
}
