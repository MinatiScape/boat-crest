package com.google.iot.cbor;
/* loaded from: classes10.dex */
public final class d extends CborFloat {
    public final double h;
    public final int i;
    public int j;

    public d(float f, int i) {
        if (CborTag.isValid(i)) {
            this.i = i;
            this.h = f;
            this.j = 26;
            return;
        }
        throw new IllegalArgumentException("Invalid tag value " + i);
    }

    public static CborFloat createHalf(float f, int i) {
        d dVar = new d(f, i);
        dVar.j = 25;
        return dVar;
    }

    @Override // com.google.iot.cbor.CborFloat, com.google.iot.cbor.CborNumber
    public double doubleValue() {
        return this.h;
    }

    @Override // com.google.iot.cbor.CborFloat, com.google.iot.cbor.CborNumber
    public float floatValue() {
        return (float) this.h;
    }

    @Override // com.google.iot.cbor.CborObject
    public int getAdditionalInformation() {
        return this.j;
    }

    @Override // com.google.iot.cbor.CborObject
    public int getTag() {
        return this.i;
    }

    public d(double d, int i) {
        if (CborTag.isValid(i)) {
            this.i = i;
            this.h = d;
            this.j = 27;
            return;
        }
        throw new IllegalArgumentException("Invalid tag value " + i);
    }
}
