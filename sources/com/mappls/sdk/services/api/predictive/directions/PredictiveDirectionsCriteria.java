package com.mappls.sdk.services.api.predictive.directions;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDirectionsCriteria {
    public static final String PREFERRED_SIDE_EITHER = "either";
    public static final String PREFERRED_SIDE_OPPOSITE = "opposite";
    public static final String PREFERRED_SIDE_SAME = "same";
    public static final String PROFILE_BIKING = "biking";
    public static final String PROFILE_DRIVING = "driving";
    public static final String PROFILE_TRUCKING = "trucking";
    public static final String PROFILE_WALKING = "walking";
    public static final int SPECIFIED_ARRIVAL = 2;
    public static final int SPECIFIED_DEPARTURE = 1;

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface PreferredSideCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface ProfileCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface SpecifiedTypeCriteria {
    }
}
