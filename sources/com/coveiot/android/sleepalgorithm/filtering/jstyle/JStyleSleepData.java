package com.coveiot.android.sleepalgorithm.filtering.jstyle;

import java.util.Arrays;
/* loaded from: classes6.dex */
public class JStyleSleepData {
    public static final int HOURS_IN_A_DAY = 24;
    public static final int JSTYLE_VALUE_AWAKE = -1;
    public static int JSTYLE_VALUE_AWAKE_END_WITH_REM = 200;
    public static int JSTYLE_VALUE_AWAKE_START_WITH_REM = 105;
    public static final int JSTYLE_VALUE_DEEPSLEEP_END = 14;
    public static int JSTYLE_VALUE_DEEPSLEEP_END_WITH_REM = 14;
    public static final int JSTYLE_VALUE_DEEPSLEEP_START = 0;
    public static int JSTYLE_VALUE_DEEPSLEEP_START_WITH_REM = 0;
    public static final int JSTYLE_VALUE_INVALID = 255;
    public static final int JSTYLE_VALUE_INVALID_WITH_REM = 255;
    public static final int JSTYLE_VALUE_LIGHTSLEEP_END = 200;
    public static int JSTYLE_VALUE_LIGHTSLEEP_END_WITH_REM = 44;
    public static final int JSTYLE_VALUE_LIGHTSLEEP_START = 15;
    public static int JSTYLE_VALUE_LIGHTSLEEP_START_WITH_REM = 15;
    public static final Integer JSTYLE_VALUE_NO_DATA = null;
    public static int JSTYLE_VALUE_REMSLEEP_END_WITH_REM = 104;
    public static int JSTYLE_VALUE_REMSLEEP_START_WITH_REM = 45;
    public static final int MAX_UNITS_IN_SLEEP_HOUR = 60;

    /* renamed from: a  reason: collision with root package name */
    public byte[] f5686a;
    public SUPPORTED_ENCODING b;

    /* loaded from: classes6.dex */
    public enum SUPPORTED_ENCODING {
        ENCODING_NONE
    }

    public JStyleSleepData(byte[] bArr, SUPPORTED_ENCODING supported_encoding) {
        this.b = supported_encoding;
        byte[] copyOf = Arrays.copyOf(bArr, 1440);
        this.f5686a = copyOf;
        int length = bArr.length;
        if (length < 1440) {
            Arrays.fill(copyOf, length, copyOf.length, (byte) -1);
        }
        a();
    }

    public final void a() {
        SUPPORTED_ENCODING supported_encoding = SUPPORTED_ENCODING.ENCODING_NONE;
    }

    public byte[] getSleepRawData() {
        return this.f5686a;
    }
}
