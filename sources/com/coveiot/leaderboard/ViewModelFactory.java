package com.coveiot.leaderboard;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.leaderboard.rankshare.viewmodel.ActivityRankViewModel;
import com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7207a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7207a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(ActivityRankViewModel.class)) {
            return new ActivityRankViewModel(this.f7207a);
        }
        if (modelClass.isAssignableFrom(LeaderboardViewModel.class)) {
            return new LeaderboardViewModel(this.f7207a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f7207a;
    }
}
