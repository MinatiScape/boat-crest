package com.mappls.sdk.services.api.predictive.distance;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDistanceCriteria {
    public static final String PROFILE_BIKING = "biking";
    public static final String PROFILE_DRIVING = "driving";
    public static final String PROFILE_TRUCKING = "trucking";
    public static final String PROFILE_WALKING = "walking";
    public static final String SPEED_TYPES_OPTIMAL = "optimal";
    public static final String SPEED_TYPES_PREDICTIVE = "predictive";
    public static final String SPEED_TYPES_TRAFFIC = "traffic";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface ProfileCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface SpeedTypeCriteria {
    }
}
