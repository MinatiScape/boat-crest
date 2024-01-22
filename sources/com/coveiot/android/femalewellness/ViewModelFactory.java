package com.coveiot.android.femalewellness;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel;
import com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4372a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4372a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(WomenWellnessViewModel.class)) {
            return new WomenWellnessViewModel(this.f4372a);
        }
        if (modelClass.isAssignableFrom(WomenWellnessRecordSymptomsViewModel.class)) {
            return new WomenWellnessRecordSymptomsViewModel(this.f4372a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f4372a;
    }
}
