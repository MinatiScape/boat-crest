package com.mappls.sdk.services.api.geocoding;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes6.dex */
public class GeoCodingCriteria {
    public static final int BIAS_DEFAULT = 0;
    public static final int BIAS_RURAL = -1;
    public static final int BIAS_URBAN = 1;
    public static final String POD_CITY = "city";
    public static final String POD_DISTRICT = "dist";
    public static final String POD_HOUSE_NAME = "hna";
    public static final String POD_HOUSE_NUMBER = "hno";
    public static final String POD_LOCALITY = "loc";
    public static final String POD_PINCODE = "pincode";
    public static final String POD_POINT_OF_INTEREST = "poi";
    public static final String POD_STATE = "state";
    public static final String POD_STREET = "street";
    public static final String POD_SUB_DISTRICT = "sdist";
    public static final String POD_SUB_LOCALITY = "slc";
    public static final String POD_SUB_SUB_LOCALITY = "sslc";
    public static final String POD_VILLAGE = "village";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface BiasCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface PODCriteria {
    }
}
