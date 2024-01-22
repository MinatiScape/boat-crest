package com.htsmart.wristband2.bean.peripherals;
/* loaded from: classes11.dex */
public class PeripheralsRequest {
    public static final byte REQUEST_PAUSE = 2;
    public static final byte REQUEST_RESUME = 3;
    public static final byte REQUEST_START = 1;
    public static final byte REQUEST_STOP = 0;

    /* renamed from: a  reason: collision with root package name */
    public final Peripherals f11984a;
    public final byte b;

    public PeripheralsRequest(Peripherals peripherals, byte b) {
        this.f11984a = peripherals;
        this.b = b;
    }

    public Peripherals getPeripherals() {
        return this.f11984a;
    }

    public byte getRequest() {
        return this.b;
    }
}
