package com.google.iot.cbor;

import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public final class f extends CborMap {

    /* renamed from: a  reason: collision with root package name */
    public final Map<CborObject, CborObject> f11524a;
    public int b;

    public f(int i) {
        if (CborTag.isValid(i)) {
            this.f11524a = new LinkedHashMap();
            this.b = i;
            return;
        }
        throw new IllegalArgumentException("Invalid tag value " + i);
    }

    @Override // com.google.iot.cbor.CborObject
    public int getTag() {
        return this.b;
    }

    @Override // com.google.iot.cbor.CborMap
    public Map<CborObject, CborObject> mapValue() {
        return this.f11524a;
    }

    public f(Map<CborObject, CborObject> map, int i) {
        this(i);
        this.f11524a.putAll(map);
    }
}
