package com.mappls.sdk.navigation.camera;

import androidx.annotation.Keep;
import com.mappls.sdk.geojson.Point;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes11.dex */
public abstract class Camera {
    @NotNull
    public abstract List<Point> overview(@NotNull RouteInformation routeInformation);

    public abstract double tilt(@NotNull RouteInformation routeInformation);

    public abstract double zoom(@NotNull RouteInformation routeInformation);
}
