package com.google.iot.cbor;

import java.util.Arrays;
/* loaded from: classes10.dex */
public final class c extends CborByteString {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11523a;
    public final int b;

    public c(byte[] bArr, int i, int i2, int i3) {
        this.b = i3;
        this.f11523a = Arrays.copyOfRange(bArr, i, i2 + i);
    }

    @Override // com.google.iot.cbor.CborByteString
    public byte[] byteArrayValue() {
        return this.f11523a;
    }

    @Override // com.google.iot.cbor.CborObject
    public int getTag() {
        return this.b;
    }
}
