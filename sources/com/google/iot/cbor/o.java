package com.google.iot.cbor;
/* loaded from: classes10.dex */
public class o implements m {

    /* renamed from: a  reason: collision with root package name */
    public int f11531a = 0;

    @Override // com.google.iot.cbor.m
    public int length() {
        return this.f11531a;
    }

    @Override // com.google.iot.cbor.m
    public m put(byte b) {
        this.f11531a++;
        return this;
    }

    @Override // com.google.iot.cbor.m
    public m putInt(int i) {
        this.f11531a += 4;
        return this;
    }

    @Override // com.google.iot.cbor.m
    public m putLong(long j) {
        this.f11531a += 8;
        return this;
    }

    @Override // com.google.iot.cbor.m
    public m putShort(short s) {
        this.f11531a += 2;
        return this;
    }
}
