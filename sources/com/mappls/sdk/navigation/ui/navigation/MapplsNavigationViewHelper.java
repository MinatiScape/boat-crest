package com.mappls.sdk.navigation.ui.navigation;

import androidx.annotation.Keep;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import java.util.ArrayList;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class MapplsNavigationViewHelper {
    private static MapplsNavigationViewHelper mapplsNavigationViewHelper = new MapplsNavigationViewHelper();
    private NavigationApplication application;
    private ELocation destination;
    private NavLocation startLocation;
    private List<WayPoint> wayPoints;

    public static MapplsNavigationViewHelper getInstance() {
        return mapplsNavigationViewHelper;
    }

    public void addWayPoint(ELocation eLocation) {
        Double d = eLocation.latitude;
        if (d == null || eLocation.longitude == null) {
            String mapplsPin = eLocation.getMapplsPin();
            String str = eLocation.placeName;
            this.wayPoints.add(0, new WayPoint(mapplsPin, str, str));
            return;
        }
        double doubleValue = d.doubleValue();
        double doubleValue2 = eLocation.longitude.doubleValue();
        String str2 = eLocation.placeName;
        this.wayPoints.add(0, new WayPoint(doubleValue, doubleValue2, str2, str2));
    }

    public void clearWayPoints() {
        this.wayPoints = new ArrayList();
    }

    public NavigationApplication getApplication() {
        return this.application;
    }

    public ELocation getDestination() {
        return this.destination;
    }

    public NavLocation getStartLocation() {
        return this.startLocation;
    }

    public List<WayPoint> getWayPoints() {
        return this.wayPoints;
    }

    public void init(NavigationApplication navigationApplication) {
        MapplsNavigationHelper.getInstance().init(navigationApplication);
        this.application = navigationApplication;
    }

    public void setDestination(ELocation eLocation) {
        this.destination = eLocation;
    }

    public void setStartLocation(NavLocation navLocation) {
        this.startLocation = navLocation;
    }

    public void setWayPoints(List<WayPoint> list) {
        this.wayPoints = list;
    }
}
