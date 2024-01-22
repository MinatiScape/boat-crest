package com.htsmart.wristband2.bean.peripherals;
/* loaded from: classes11.dex */
public class PeripheralsResponse {
    public static final byte RESPONSE_BUSY = 4;
    public static final byte RESPONSE_NOT_EXIST = 1;
    public static final byte RESPONSE_OFFLINE_OR_TIMEOUT = 3;
    public static final byte RESPONSE_SUCCESS = 0;
    public static final byte RESPONSE_UNKNOWN = -1;
    public static final byte RESPONSE_UN_SUPPORT = 2;

    /* renamed from: a  reason: collision with root package name */
    public final Peripherals f11985a;
    public final byte b;

    public PeripheralsResponse(Peripherals peripherals, byte b) {
        this.f11985a = peripherals;
        this.b = b;
    }

    public Peripherals getPeripherals() {
        return this.f11985a;
    }

    public byte getResponse() {
        return this.b;
    }
}
