package com.coveiot.android.weeklyreport.utils;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.weeklyreport.viewmodel.ActivityReportIssueViewModel;
import com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6243a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6243a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(WeeklyReportViewModel.class)) {
            return new WeeklyReportViewModel(this.f6243a);
        }
        if (modelClass.isAssignableFrom(ActivityReportIssueViewModel.class)) {
            return new ActivityReportIssueViewModel(this.f6243a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f6243a;
    }
}
