package com.google.iot.cbor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class i extends CborTextString {

    /* renamed from: a  reason: collision with root package name */
    public final String f11527a;
    public final byte[] b;
    public final int c;

    public i(String str, int i) {
        if (CborTag.isValid(i)) {
            this.c = i;
            this.f11527a = str;
            this.b = str.getBytes(StandardCharsets.UTF_8);
            return;
        }
        throw new IllegalArgumentException("Invalid tag value " + i);
    }

    @Override // com.google.iot.cbor.CborTextString
    public byte[] byteArrayValue() {
        return this.b;
    }

    @Override // com.google.iot.cbor.CborObject
    public int getTag() {
        return this.c;
    }

    @Override // com.google.iot.cbor.CborTextString
    public String stringValue() {
        return this.f11527a;
    }

    public i(byte[] bArr, int i, int i2, int i3) {
        if (CborTag.isValid(i3)) {
            this.c = i3;
            this.f11527a = new String(bArr, i, i2, StandardCharsets.UTF_8);
            this.b = Arrays.copyOfRange(bArr, i, i2 + i);
            return;
        }
        throw new IllegalArgumentException("Invalid tag value " + i3);
    }
}
