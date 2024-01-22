package com.coveiot.android.customreminders.utils;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.customreminders.viewmodel.AddCustomReminderViewModel;
import com.coveiot.android.customreminders.viewmodel.HandWashReminderViewModel;
import com.coveiot.android.customreminders.viewmodel.MedicineReminderViewModel;
import com.coveiot.android.customreminders.viewmodel.RemindersListViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4187a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4187a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(AddCustomReminderViewModel.class)) {
            return new AddCustomReminderViewModel(this.f4187a);
        }
        if (modelClass.isAssignableFrom(HandWashReminderViewModel.class)) {
            return new HandWashReminderViewModel(this.f4187a);
        }
        if (modelClass.isAssignableFrom(MedicineReminderViewModel.class)) {
            return new MedicineReminderViewModel(this.f4187a);
        }
        if (modelClass.isAssignableFrom(RemindersListViewModel.class)) {
            return new RemindersListViewModel(this.f4187a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f4187a;
    }
}
