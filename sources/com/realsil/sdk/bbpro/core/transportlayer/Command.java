package com.realsil.sdk.bbpro.core.transportlayer;
/* loaded from: classes12.dex */
public class Command {
    public static final int WRITE_TYPE_DEFAULT = 2;
    public static final int WRITE_TYPE_NO_RESPONSE = 1;

    /* renamed from: a  reason: collision with root package name */
    public int f13533a;
    public byte[] b;
    public int c;

    public Command(byte[] bArr) {
        this.c = 2;
        this.b = bArr;
        this.f13533a = 2;
    }

    public byte[] getPayload() {
        return this.b;
    }

    public int getRetransCount() {
        return this.c;
    }

    public int getWriteType() {
        return this.f13533a;
    }

    public Command(int i, byte[] bArr) {
        this.c = 2;
        this.f13533a = i;
        this.b = bArr;
    }

    public Command(int i, byte[] bArr, int i2) {
        this.c = 2;
        this.f13533a = i;
        this.b = bArr;
        this.c = i2;
    }
}
