package com.coveiot.android.sleepalgorithm.filtering;

import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes6.dex */
public class SleepData {
    public static final int HOURS_IN_A_DAY = 24;
    public static final int MAX_UNITS_IN_SLEEP_HOUR = 60;
    public static int VALUE_AWAKE = 0;
    public static int VALUE_DEEPSLEEP = 2;
    public static int VALUE_INVALID = 255;
    public static int VALUE_LIGHTSLEEP = 1;
    public static int VALUE_REM = 3;

    /* renamed from: a  reason: collision with root package name */
    public byte[] f5683a;
    public SUPPORTED_ENCODING b;
    public HashMap<Integer, SleepHour> c = new HashMap<>();

    /* loaded from: classes6.dex */
    public enum SUPPORTED_ENCODING {
        ENCODING_NONE
    }

    public SleepData(byte[] bArr, SUPPORTED_ENCODING supported_encoding) {
        this.b = supported_encoding;
        byte[] copyOf = Arrays.copyOf(bArr, 1440);
        this.f5683a = copyOf;
        int length = bArr.length;
        if (length < 1440) {
            Arrays.fill(copyOf, length, copyOf.length, (byte) 0);
        }
        a();
        b();
    }

    public final void a() {
        SUPPORTED_ENCODING supported_encoding = SUPPORTED_ENCODING.ENCODING_NONE;
    }

    public final void b() {
        for (int i = 0; i < 24; i++) {
            try {
                int i2 = i * 60;
                this.c.put(Integer.valueOf((i + 12) % 24), new SleepHour(Arrays.copyOfRange(this.f5683a, i2, i2 + 60)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public SleepHour getSleepHour(int i) {
        return this.c.get(Integer.valueOf(i));
    }

    public byte[] getSleepRawData() {
        return this.f5683a;
    }
}
