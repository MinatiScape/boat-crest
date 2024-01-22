package com.mappls.sdk.navigation.camera;

import android.location.Location;
import androidx.annotation.Keep;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes11.dex */
public final class RouteInformation {
    @Nullable
    private final AdviseInfo adviseInfo;
    @Nullable
    private final Location location;
    @Nullable
    private DirectionsRoute route;

    public RouteInformation(@Nullable DirectionsRoute directionsRoute, @Nullable Location location, @Nullable AdviseInfo adviseInfo) {
        this.route = directionsRoute;
        this.location = location;
        this.adviseInfo = adviseInfo;
    }

    public static /* synthetic */ RouteInformation copy$default(RouteInformation routeInformation, DirectionsRoute directionsRoute, Location location, AdviseInfo adviseInfo, int i, Object obj) {
        if ((i & 1) != 0) {
            directionsRoute = routeInformation.route;
        }
        if ((i & 2) != 0) {
            location = routeInformation.location;
        }
        if ((i & 4) != 0) {
            adviseInfo = routeInformation.adviseInfo;
        }
        return routeInformation.copy(directionsRoute, location, adviseInfo);
    }

    @Nullable
    public final DirectionsRoute component1() {
        return this.route;
    }

    @Nullable
    public final Location component2() {
        return this.location;
    }

    @Nullable
    public final AdviseInfo component3() {
        return this.adviseInfo;
    }

    @NotNull
    public final RouteInformation copy(@Nullable DirectionsRoute directionsRoute, @Nullable Location location, @Nullable AdviseInfo adviseInfo) {
        return new RouteInformation(directionsRoute, location, adviseInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RouteInformation) {
            RouteInformation routeInformation = (RouteInformation) obj;
            return Intrinsics.areEqual(this.route, routeInformation.route) && Intrinsics.areEqual(this.location, routeInformation.location) && Intrinsics.areEqual(this.adviseInfo, routeInformation.adviseInfo);
        }
        return false;
    }

    @Nullable
    public final AdviseInfo getAdviseInfo() {
        return this.adviseInfo;
    }

    @Nullable
    public final Location getLocation() {
        return this.location;
    }

    @Nullable
    public final DirectionsRoute getRoute() {
        return this.route;
    }

    public int hashCode() {
        DirectionsRoute directionsRoute = this.route;
        int hashCode = (directionsRoute == null ? 0 : directionsRoute.hashCode()) * 31;
        Location location = this.location;
        int hashCode2 = (hashCode + (location == null ? 0 : location.hashCode())) * 31;
        AdviseInfo adviseInfo = this.adviseInfo;
        return hashCode2 + (adviseInfo != null ? adviseInfo.hashCode() : 0);
    }

    public final void setRoute(@Nullable DirectionsRoute directionsRoute) {
        this.route = directionsRoute;
    }

    @NotNull
    public String toString() {
        return "RouteInformation(route=" + this.route + ", location=" + this.location + ", adviseInfo=" + this.adviseInfo + HexStringBuilder.COMMENT_END_CHAR;
    }
}
