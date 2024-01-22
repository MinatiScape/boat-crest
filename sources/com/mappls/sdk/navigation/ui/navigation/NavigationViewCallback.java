package com.mappls.sdk.navigation.ui.navigation;

import androidx.annotation.Keep;
import com.mappls.sdk.maps.MapplsMap;
@Keep
/* loaded from: classes11.dex */
public interface NavigationViewCallback {
    void onNavigationEnd();

    void onNavigationMapReady(MapplsMap mapplsMap);

    void searchAlongRoute();
}
