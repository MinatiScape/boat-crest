package com.google.iot.cbor;
/* loaded from: classes10.dex */
public final class e extends CborInteger {
    public final long h;
    public final int i;

    public e(long j, int i) {
        if (CborTag.isValid(i)) {
            this.i = i;
            this.h = j;
            return;
        }
        throw new IllegalArgumentException("Invalid tag value " + i);
    }

    @Override // com.google.iot.cbor.CborObject
    public int getTag() {
        return this.i;
    }

    @Override // com.google.iot.cbor.CborInteger, com.google.iot.cbor.CborNumber
    public long longValue() {
        return this.h;
    }
}
