package com.crrepa.n0;

import java.util.Map;
import java.util.Set;
/* loaded from: classes9.dex */
public final class o extends l {

    /* renamed from: a  reason: collision with root package name */
    public final com.crrepa.p0.h<String, l> f7781a = new com.crrepa.p0.h<>();

    public final l a(Object obj) {
        return obj == null ? n.f7780a : new r(obj);
    }

    public l a(String str) {
        return this.f7781a.get(str);
    }

    public void a(String str, l lVar) {
        if (lVar == null) {
            lVar = n.f7780a;
        }
        this.f7781a.put(str, lVar);
    }

    public void a(String str, Boolean bool) {
        a(str, a(bool));
    }

    public void a(String str, Character ch) {
        a(str, a(ch));
    }

    public void a(String str, Number number) {
        a(str, a(number));
    }

    public void a(String str, String str2) {
        a(str, a((Object) str2));
    }

    public i b(String str) {
        return (i) this.f7781a.get(str);
    }

    public o c(String str) {
        return (o) this.f7781a.get(str);
    }

    public r d(String str) {
        return (r) this.f7781a.get(str);
    }

    public boolean e(String str) {
        return this.f7781a.containsKey(str);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof o) && ((o) obj).f7781a.equals(this.f7781a));
    }

    public l f(String str) {
        return this.f7781a.remove(str);
    }

    public int hashCode() {
        return this.f7781a.hashCode();
    }

    public int size() {
        return this.f7781a.size();
    }

    public Set<Map.Entry<String, l>> x() {
        return this.f7781a.entrySet();
    }
}
