package com.google.iot.cbor;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes10.dex */
public class p implements m {

    /* renamed from: a  reason: collision with root package name */
    public final OutputStream f11532a;
    public int b = 0;

    public p(OutputStream outputStream) {
        this.f11532a = outputStream;
    }

    @Override // com.google.iot.cbor.m
    public int length() {
        return this.b;
    }

    @Override // com.google.iot.cbor.m
    public m put(byte b) throws IOException {
        this.f11532a.write(b & 255);
        this.b++;
        return this;
    }

    @Override // com.google.iot.cbor.m
    public m putInt(int i) throws IOException {
        this.f11532a.write((i >> 24) & 255);
        this.f11532a.write((i >> 16) & 255);
        this.f11532a.write((i >> 8) & 255);
        this.f11532a.write(i & 255);
        this.b += 4;
        return this;
    }

    @Override // com.google.iot.cbor.m
    public m putLong(long j) throws IOException {
        this.f11532a.write(((int) (j >> 56)) & 255);
        this.f11532a.write(((int) (j >> 48)) & 255);
        this.f11532a.write(((int) (j >> 40)) & 255);
        this.f11532a.write(((int) (j >> 32)) & 255);
        this.f11532a.write(((int) (j >> 24)) & 255);
        this.f11532a.write(((int) (j >> 16)) & 255);
        this.f11532a.write(((int) (j >> 8)) & 255);
        this.f11532a.write(((int) j) & 255);
        this.b += 8;
        return this;
    }

    @Override // com.google.iot.cbor.m
    public m putShort(short s) throws IOException {
        this.f11532a.write((s >> 8) & 255);
        this.f11532a.write(s & 255);
        this.b += 2;
        return this;
    }
}
