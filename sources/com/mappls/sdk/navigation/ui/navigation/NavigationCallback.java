package com.mappls.sdk.navigation.ui.navigation;

import androidx.annotation.Keep;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.model.AdviseInfo;
@Keep
/* loaded from: classes11.dex */
public interface NavigationCallback {
    void onNavigationCancelled();

    void onNavigationFinished();

    void onNavigationStarted();

    void onNewRoute(String str);

    void onRouteProgress(AdviseInfo adviseInfo);

    void onWayPointReached(WayPoint wayPoint);
}
