package com.polidea.rxandroidble2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public final class LogConstants {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int MAC_ADDRESS_FULL = 2;
    public static final int MAC_ADDRESS_TRUNCATED = 3;
    public static final int NONE = Integer.MAX_VALUE;
    public static final int UUIDS_FULL = 2;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface LogLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface MacAddressLogSetting {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface UuidLogSetting {
    }
}
