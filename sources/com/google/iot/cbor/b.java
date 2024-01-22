package com.google.iot.cbor;

import java.util.LinkedList;
import java.util.List;
/* loaded from: classes10.dex */
public final class b extends CborArray {
    public final LinkedList<CborObject> h;
    public int i;

    public b(Iterable<CborObject> iterable, int i) {
        this.h = new LinkedList<>();
        this.i = -1;
        this.i = i;
        if (iterable != null) {
            for (CborObject cborObject : iterable) {
                add(cborObject.copy());
            }
        }
    }

    @Override // com.google.iot.cbor.CborObject
    public int getTag() {
        return this.i;
    }

    @Override // com.google.iot.cbor.CborArray
    public List<CborObject> listValue() {
        return this.h;
    }

    public b() {
        this.h = new LinkedList<>();
        this.i = -1;
    }
}
