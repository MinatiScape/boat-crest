package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final List<b> f11836a;
    public final int b;
    public final boolean c;

    public c(List<b> list, int i, boolean z) {
        this.f11836a = new ArrayList(list);
        this.b = i;
        this.c = z;
    }

    public List<b> a() {
        return this.f11836a;
    }

    public int b() {
        return this.b;
    }

    public boolean c(List<b> list) {
        return this.f11836a.equals(list);
    }

    public boolean equals(Object obj) {
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f11836a.equals(cVar.f11836a) && this.c == cVar.c;
        }
        return false;
    }

    public int hashCode() {
        return this.f11836a.hashCode() ^ Boolean.valueOf(this.c).hashCode();
    }

    public String toString() {
        return "{ " + this.f11836a + " }";
    }
}
