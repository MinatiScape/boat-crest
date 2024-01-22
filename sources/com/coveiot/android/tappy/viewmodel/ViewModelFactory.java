package com.coveiot.android.tappy.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6050a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6050a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(NfcStrapViewModel.class)) {
            return new NfcStrapViewModel(this.f6050a);
        }
        if (modelClass.isAssignableFrom(ManageViewModel.class)) {
            return new ManageViewModel(this.f6050a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f6050a;
    }
}
