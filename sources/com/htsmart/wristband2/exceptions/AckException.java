package com.htsmart.wristband2.exceptions;
/* loaded from: classes11.dex */
public class AckException extends WristbandException {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f12018a;

    public AckException(byte[] bArr) {
        this.f12018a = bArr;
    }

    public byte[] getPacket() {
        return this.f12018a;
    }
}
