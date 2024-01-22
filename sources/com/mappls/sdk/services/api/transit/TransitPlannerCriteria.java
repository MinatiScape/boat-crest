package com.mappls.sdk.services.api.transit;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes8.dex */
public final class TransitPlannerCriteria {
    public static final String MODE_BUS = "BUS";
    public static final String MODE_RAIL = "RAIL";
    public static final String MODE_SUBWAY = "SUBWAY";
    public static final String MODE_TRANSIT = "TRANSIT";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface ModeCriteria {
    }
}
