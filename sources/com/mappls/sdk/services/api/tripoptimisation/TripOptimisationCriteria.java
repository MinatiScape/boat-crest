package com.mappls.sdk.services.api.tripoptimisation;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes8.dex */
public final class TripOptimisationCriteria {
    public static final String GEOMETRY_COORDINATES = "geojson";
    public static final String GEOMETRY_POLYLINE = "polyline";
    public static final String GEOMETRY_POLYLINE6 = "polyline6";
    public static final String LOCATION_INDEX_ANY = "any";
    public static final String LOCATION_INDEX_FIRST = "first";
    public static final String LOCATION_INDEX_LAST = "last";
    public static final String OVERVIEW_FALSE = "false";
    public static final String OVERVIEW_FULL = "full";
    public static final String OVERVIEW_SIMPLIFIED = "simplified";
    public static final String PROFILE_BIKING = "biking";
    public static final String PROFILE_DRIVING = "driving";
    public static final String PROFILE_TRUCKING = "trucking";
    public static final String PROFILE_WALKING = "walking";
    public static final String RESOURCE_TRIP_OPTIMISATION = "trip_optimization";
    public static final String RESOURCE_TRIP_OPTIMISATION_ETA = "trip_optimization_eta";
    public static final String RESOURCE_TRIP_OPTIMISATION_TRAFFIC = "trip_optimization_traffic";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface GeometryCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface LocationIndexCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface OverviewCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface ProfileCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface ResourceCriteria {
    }
}
