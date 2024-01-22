package com.mappls.sdk.services.api.alongroute;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes11.dex */
public final class POICriteria {
    public static final String GEOMETRY_BASE64 = "base64";
    public static final String GEOMETRY_COORDINATES = "geojson";
    public static final String GEOMETRY_POLYLINE5 = "polyline5";
    public static final String GEOMETRY_POLYLINE6 = "polyline6";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface GeometriesCriteria {
    }

    private POICriteria() {
    }
}
