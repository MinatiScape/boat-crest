package com.mappls.sdk.maps.location.modes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public final class CameraMode {
    public static final int NONE = 8;
    public static final int NONE_COMPASS = 16;
    public static final int NONE_GPS = 22;
    public static final int TRACKING = 24;
    public static final int TRACKING_COMPASS = 32;
    public static final int TRACKING_GPS = 34;
    public static final int TRACKING_GPS_NORTH = 36;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface Mode {
    }
}
