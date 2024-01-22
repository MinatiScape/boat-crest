package com.mappls.sdk.navigation.camera;

import androidx.annotation.Keep;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfMisc;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes11.dex */
public class SimpleCamera extends Camera {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final double DEFAULT_TILT = 40.0d;
    public static final double DEFAULT_ZOOM = 15.0d;
    private double defaultZoom = 15.0d;

    /* loaded from: classes11.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final List<Point> generateRouteCoordinates(DirectionsRoute directionsRoute) {
        LineString lineString;
        List<Point> list = null;
        if (directionsRoute != null && (lineString = getLineString(directionsRoute)) != null) {
            list = lineString.coordinates();
        }
        return list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
    }

    private final List<Point> getCoordinates(DirectionsRoute directionsRoute, AdviseInfo adviseInfo) {
        LineString lineString;
        Double distance;
        List<Point> list = null;
        if (adviseInfo != null && (lineString = getLineString(directionsRoute)) != null && (distance = directionsRoute.distance()) != null) {
            Double distance2 = directionsRoute.distance();
            Intrinsics.checkNotNull(distance2);
            Intrinsics.checkNotNullExpressionValue(distance2, "route.distance()!!");
            list = TurfMisc.lineSliceAlong(lineString, distance.doubleValue() - adviseInfo.getLeftDistance(), distance2.doubleValue(), TurfConstants.UNIT_METERS).coordinates();
        }
        return list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
    }

    private final LineString getLineString(DirectionsRoute directionsRoute) {
        String geometry = directionsRoute.geometry();
        if (geometry != null) {
            RouteOptions routeOptions = directionsRoute.routeOptions();
            return LineString.fromPolyline(geometry, Intrinsics.areEqual(routeOptions != null ? routeOptions.geometries() : null, "polyline") ? 5 : 6);
        }
        return null;
    }

    private final DirectionsRoute getRoute(RouteInformation routeInformation) {
        if (routeInformation.getRoute() == null) {
            return MapplsNavigationHelper.getInstance().getCurrentRoute();
        }
        return routeInformation.getRoute();
    }

    public final double getDefaultZoom() {
        return this.defaultZoom;
    }

    @Override // com.mappls.sdk.navigation.camera.Camera
    @NotNull
    public List<Point> overview(@NotNull RouteInformation routeInformation) {
        List<Point> coordinates;
        Intrinsics.checkNotNullParameter(routeInformation, "routeInformation");
        DirectionsRoute route = getRoute(routeInformation);
        if (route == null) {
            coordinates = null;
        } else if (routeInformation.getAdviseInfo() == null) {
            coordinates = generateRouteCoordinates(route);
        } else {
            coordinates = getCoordinates(route, routeInformation.getAdviseInfo());
        }
        return coordinates == null ? CollectionsKt__CollectionsKt.emptyList() : coordinates;
    }

    public final void setDefaultZoom(double d) {
        this.defaultZoom = d;
    }

    @Override // com.mappls.sdk.navigation.camera.Camera
    public double tilt(@NotNull RouteInformation routeInformation) {
        Intrinsics.checkNotNullParameter(routeInformation, "routeInformation");
        return 40.0d;
    }

    @Override // com.mappls.sdk.navigation.camera.Camera
    public double zoom(@NotNull RouteInformation routeInformation) {
        Intrinsics.checkNotNullParameter(routeInformation, "routeInformation");
        return this.defaultZoom;
    }
}
