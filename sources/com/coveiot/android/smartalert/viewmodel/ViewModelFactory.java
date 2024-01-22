package com.coveiot.android.smartalert.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5763a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5763a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(SmartAlertViewModel.class)) {
            return new SmartAlertViewModel(this.f5763a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f5763a;
    }
}
