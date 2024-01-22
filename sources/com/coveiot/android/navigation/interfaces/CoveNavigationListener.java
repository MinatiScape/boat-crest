package com.coveiot.android.navigation.interfaces;

import com.coveiot.android.navigation.utils.CoveNavigationCallBacks;
import com.mappls.sdk.navigation.camera.ProgressChangeListener;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.routing.NavigationStep;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public interface CoveNavigationListener {
    void onCommonEvents(@NotNull CoveNavigationCallBacks coveNavigationCallBacks);

    void onETARefreshed(@Nullable String str);

    void onEvent(@Nullable NavEvent navEvent);

    void onNewRoute(@NotNull List<NavigationStep> list);

    void onRouteProgress(@NotNull AdviseInfo adviseInfo);

    void onWayPointReached(@Nullable WayPoint wayPoint);

    void removeProgressChangeListener(@Nullable ProgressChangeListener progressChangeListener);

    void setProgressChangeListener(@Nullable ProgressChangeListener progressChangeListener);

    void startingNavigationOnBandFailed();
}
