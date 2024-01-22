package com.polidea.rxandroidble2.internal.cache;

import com.polidea.rxandroidble2.internal.DeviceComponent;
import java.util.Map;
/* loaded from: classes9.dex */
public class a implements Map.Entry<String, DeviceComponent> {
    public final String h;
    public final DeviceComponentWeakReference i;

    public a(String str, DeviceComponentWeakReference deviceComponentWeakReference) {
        this.h = str;
        this.i = deviceComponentWeakReference;
    }

    @Override // java.util.Map.Entry
    /* renamed from: a */
    public String getKey() {
        return this.h;
    }

    @Override // java.util.Map.Entry
    /* renamed from: b */
    public DeviceComponent getValue() {
        return this.i.get();
    }

    @Override // java.util.Map.Entry
    /* renamed from: c */
    public DeviceComponent setValue(DeviceComponent deviceComponent) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            return this.h.equals(aVar.getKey()) && this.i.equals(aVar.i);
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return (this.h.hashCode() * 31) + this.i.hashCode();
    }
}
