package com.mappls.sdk.services.api.directions;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes11.dex */
public final class DirectionsCriteria {
    public static final String ANNOTATION_BASE_DURATION = "baseDuration";
    public static final String ANNOTATION_CONGESTION = "congestion";
    public static final String ANNOTATION_DISTANCE = "distance";
    public static final String ANNOTATION_DURATION = "duration";
    public static final String ANNOTATION_MAXSPEED = "maxspeed";
    public static final String ANNOTATION_NODES = "nodes";
    public static final String ANNOTATION_SPEED = "speed";
    public static final String ANNOTATION_SPEED_LIMIT = "spdlmt";
    public static final String ANNOTATION_TOLL_ROAD = "toll_road";
    public static final String APPROACH_CURB = "curb";
    public static final String APPROACH_UNRESTRICTED = "unrestricted";
    public static final String DESTINATION_ANY = "any";
    public static final String DESTINATION_LAST = "last";
    public static final int DISTANCE_ROUTE_TYPE_OPTIMAL = 0;
    public static final int DISTANCE_ROUTE_TYPE_SHORTEST = 1;
    public static final String EXCLUDE_FERRY = "ferry";
    public static final String EXCLUDE_MOTORWAY = "motorway";
    public static final String EXCLUDE_RESTRICTED = "restricted";
    public static final String EXCLUDE_TOLL = "toll";
    public static final String EXCLUDE_TUNNEL = "tunnel";
    public static final String GEOMETRY_COORDINATES = "geojson";
    public static final String GEOMETRY_POLYLINE = "polyline";
    public static final String GEOMETRY_POLYLINE6 = "polyline6";
    public static final String IMPERIAL = "imperial";
    public static final String METRIC = "metric";
    public static final String OVERVIEW_FALSE = "false";
    public static final String OVERVIEW_FULL = "full";
    public static final String OVERVIEW_SIMPLIFIED = "simplified";
    public static final String PROFILE_BIKING = "biking";
    public static final String PROFILE_DEFAULT_USER = "mappls";
    public static final String PROFILE_DRIVING = "driving";
    public static final String PROFILE_TRUCKING = "trucking";
    public static final String PROFILE_WALKING = "walking";
    public static final String RESOURCE_DISTANCE = "distance_matrix";
    public static final String RESOURCE_DISTANCE_ETA = "distance_matrix_eta";
    public static final String RESOURCE_DISTANCE_TRAFFIC = "distance_matrix_traffic";
    public static final String RESOURCE_ROUTE = "route_adv";
    public static final String RESOURCE_ROUTE_ETA = "route_eta";
    public static final String RESOURCE_ROUTE_TRAFFIC = "route_traffic";
    public static final int ROUTE_TYPE_OPTIMAL = 0;
    public static final int ROUTE_TYPE_SHORTEST = 1;
    public static final int ROUTE_TYPE_WITHOUT_CONTAINMENT_ZONE = 2;
    public static final String SOURCE_ANY = "any";
    public static final String SOURCE_FIRST = "first";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface AnnotationCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ApproachesCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface DestinationCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface DistanceProfileCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface DistanceResourceCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface DistanceRouteType {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ExcludeCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface GeometriesCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface OverviewCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ProfileCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ResourceCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface RouteType {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface SourceCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface VoiceUnitCriteria {
    }

    private DirectionsCriteria() {
    }
}
