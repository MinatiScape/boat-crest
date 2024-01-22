package com.htsmart.wristband2.exceptions;
/* loaded from: classes11.dex */
public class TemperatureRealTimeException extends WristbandException {
    public static final int REASON_HYPERTHERMIA = 3;
    public static final int REASON_HYPOTHERMIA = 4;
    public static final int REASON_NOT_WORN = 2;

    /* renamed from: a  reason: collision with root package name */
    private int f12024a;

    public TemperatureRealTimeException(int i) {
        this.f12024a = i;
    }

    public int getReason() {
        return this.f12024a;
    }
}
