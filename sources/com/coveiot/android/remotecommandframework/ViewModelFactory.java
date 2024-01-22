package com.coveiot.android.remotecommandframework;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5589a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5589a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(AlexaApiCallHandlerViewModel.class)) {
            return new AlexaApiCallHandlerViewModel(this.f5589a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f5589a;
    }
}
