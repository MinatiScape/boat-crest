package com.mappls.sdk.services.utils;

import androidx.annotation.Keep;
import com.mappls.sdk.services.BuildConfig;
import java.util.Locale;
@Keep
/* loaded from: classes8.dex */
public final class Constants {
    public static final String ADVANCE_MAP_BASE_URL = "https://apis.mappls.com/";
    public static final String ANCHOR_BASE_URL = "https://anchor.mappls.com/";
    public static final String ATLAS_BASE_URL = "https://atlas.mappls.com/";
    public static final String EXPLORE_BASE_URL = "https://explore.mappls.com/";
    public static final String HEADER_USER_AGENT = String.format(Locale.US, "MapplsJava(%s)", BuildConfig.VERSION_NAME);
    public static final String INTOUCH_BASE_URL = "https://intouch.mappls.com/";
    public static final String MGIS_APIS_BASE_URL = "https://mgis-api.mappls.com/";
    public static final String MGIS_BASE_URL = "https://mgis.mappls.com/";
    public static final String OUTPOST_BASE_URL = "https://outpost.mappls.com/";
    public static final int PRECISION_5 = 5;
    public static final int PRECISION_6 = 6;
    public static final String TRAFFIC_BASE_URL = "https://traffic.mappls.com/";

    private Constants() {
    }
}
