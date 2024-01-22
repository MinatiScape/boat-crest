package com.mappls.sdk.navigation.iface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.navigation.AlternateRoute;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.util.List;
/* loaded from: classes11.dex */
public interface INavigationListener {
    default void onAlternateRoutesUpdate(@Nullable List<AlternateRoute> list) {
    }

    default void onBetterRouteAvailable(List<DirectionsRoute> list) {
    }

    void onETARefreshed(String str);

    void onEvent(@Nullable NavEvent navEvent);

    void onNavigationCancelled();

    void onNavigationFinished();

    void onNavigationStarted();

    void onNewRoute(String str);

    void onReRoutingRequested();

    void onRouteProgress(@NonNull AdviseInfo adviseInfo);

    void onWayPointReached(WayPoint wayPoint);
}
