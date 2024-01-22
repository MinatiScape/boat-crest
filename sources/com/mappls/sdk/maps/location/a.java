package com.mappls.sdk.maps.location;

import com.mappls.sdk.maps.location.o;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final int f12749a;
    public final o.b b;

    public a(int i, o.b bVar) {
        this.f12749a = i;
        this.b = bVar;
    }

    public int a() {
        return this.f12749a;
    }

    public o.b b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f12749a != aVar.f12749a) {
            return false;
        }
        o.b bVar = this.b;
        o.b bVar2 = aVar.b;
        return bVar != null ? bVar.equals(bVar2) : bVar2 == null;
    }

    public int hashCode() {
        int i = this.f12749a * 31;
        o.b bVar = this.b;
        return i + (bVar != null ? bVar.hashCode() : 0);
    }
}
