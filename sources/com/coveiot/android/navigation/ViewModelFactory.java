package com.coveiot.android.navigation;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel;
import com.coveiot.android.navigation.viewModels.ActivityNavigationFTUViewModel;
import com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel;
import com.coveiot.android.navigation.viewModels.ActivityNavigationSettingsViewModel;
import com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel;
import com.coveiot.android.navigation.viewModels.ActivityYourPlacesViewModel;
import com.coveiot.android.navigation.viewModels.NavigationMainViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5473a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5473a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(NavigationMainViewModel.class)) {
            return new NavigationMainViewModel(this.f5473a);
        }
        if (modelClass.isAssignableFrom(ActivityNavigationSearchPlacesViewModel.class)) {
            return new ActivityNavigationSearchPlacesViewModel(this.f5473a);
        }
        if (modelClass.isAssignableFrom(ActivityNavigationShowRoutesViewModel.class)) {
            return new ActivityNavigationShowRoutesViewModel(this.f5473a);
        }
        if (modelClass.isAssignableFrom(ActivityNavigationDirectionsViewmodel.class)) {
            return new ActivityNavigationDirectionsViewmodel(this.f5473a);
        }
        if (modelClass.isAssignableFrom(ActivityNavigationSettingsViewModel.class)) {
            return new ActivityNavigationSettingsViewModel(this.f5473a);
        }
        if (modelClass.isAssignableFrom(ActivityYourPlacesViewModel.class)) {
            return new ActivityYourPlacesViewModel(this.f5473a);
        }
        if (modelClass.isAssignableFrom(ActivityNavigationFTUViewModel.class)) {
            return new ActivityNavigationFTUViewModel(this.f5473a);
        }
        return (T) super.create(modelClass);
    }

    @NotNull
    public final Context getContext() {
        return this.f5473a;
    }
}
