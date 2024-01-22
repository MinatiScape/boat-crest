package com.coveiot.android.watchfaceui.utils;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWebViewerViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDefaultViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6144a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6144a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(ActivityWatchFaceViewModel.class)) {
            return new ActivityWatchFaceViewModel(this.f6144a);
        }
        if (modelClass.isAssignableFrom(WatchFaceCloudViewModel.class)) {
            return new WatchFaceCloudViewModel(this.f6144a);
        }
        if (modelClass.isAssignableFrom(WatchFaceDefaultViewModel.class)) {
            return new WatchFaceDefaultViewModel(this.f6144a);
        }
        if (modelClass.isAssignableFrom(WatchFaceBackgroundViewModel.class)) {
            return new WatchFaceBackgroundViewModel(this.f6144a);
        }
        if (modelClass.isAssignableFrom(WatchFaceLayoutViewModel.class)) {
            return new WatchFaceLayoutViewModel(this.f6144a);
        }
        if (modelClass.isAssignableFrom(WatchFaceDiyViewModel.class)) {
            return new WatchFaceDiyViewModel(this.f6144a);
        }
        if (modelClass.isAssignableFrom(ActivityWebViewerViewModel.class)) {
            return new ActivityWebViewerViewModel(this.f6144a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f6144a;
    }
}
