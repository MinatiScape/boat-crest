package com.htsmart.wristband2.exceptions;
/* loaded from: classes11.dex */
public class AuthenticatedException extends WristbandException {
    public static final byte REASON_BIND_CANCEL = 2;
    public static final byte REASON_BIND_TIMEOUT = 3;
    public static final byte REASON_FAILED = 1;

    /* renamed from: a  reason: collision with root package name */
    private final byte f12019a;

    public AuthenticatedException() {
        this((byte) 1);
    }

    public AuthenticatedException(byte b) {
        this.f12019a = b;
    }

    public byte getReason() {
        return this.f12019a;
    }
}
