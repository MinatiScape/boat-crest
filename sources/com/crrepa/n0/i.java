package com.crrepa.n0;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes9.dex */
public final class i extends l implements Iterable<l> {
    public final List<l> h = new ArrayList();

    public l a(int i) {
        return this.h.get(i);
    }

    public l a(int i, l lVar) {
        return this.h.set(i, lVar);
    }

    public void a(i iVar) {
        this.h.addAll(iVar.h);
    }

    public void a(l lVar) {
        if (lVar == null) {
            lVar = n.f7780a;
        }
        this.h.add(lVar);
    }

    public void a(Boolean bool) {
        this.h.add(bool == null ? n.f7780a : new r(bool));
    }

    public void a(Character ch) {
        this.h.add(ch == null ? n.f7780a : new r(ch));
    }

    public void a(Number number) {
        this.h.add(number == null ? n.f7780a : new r(number));
    }

    public void a(String str) {
        this.h.add(str == null ? n.f7780a : new r(str));
    }

    public l b(int i) {
        return this.h.remove(i);
    }

    @Override // com.crrepa.n0.l
    public BigDecimal b() {
        if (this.h.size() == 1) {
            return this.h.get(0).b();
        }
        throw new IllegalStateException();
    }

    public boolean b(l lVar) {
        return this.h.contains(lVar);
    }

    @Override // com.crrepa.n0.l
    public BigInteger c() {
        if (this.h.size() == 1) {
            return this.h.get(0).c();
        }
        throw new IllegalStateException();
    }

    public boolean c(l lVar) {
        return this.h.remove(lVar);
    }

    @Override // com.crrepa.n0.l
    public boolean d() {
        if (this.h.size() == 1) {
            return this.h.get(0).d();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof i) && ((i) obj).h.equals(this.h));
    }

    @Override // com.crrepa.n0.l
    public byte f() {
        if (this.h.size() == 1) {
            return this.h.get(0).f();
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.n0.l
    public char g() {
        if (this.h.size() == 1) {
            return this.h.get(0).g();
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.n0.l
    public double h() {
        if (this.h.size() == 1) {
            return this.h.get(0).h();
        }
        throw new IllegalStateException();
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @Override // com.crrepa.n0.l
    public float i() {
        if (this.h.size() == 1) {
            return this.h.get(0).i();
        }
        throw new IllegalStateException();
    }

    @Override // java.lang.Iterable
    public Iterator<l> iterator() {
        return this.h.iterator();
    }

    @Override // com.crrepa.n0.l
    public int j() {
        if (this.h.size() == 1) {
            return this.h.get(0).j();
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.n0.l
    public long o() {
        if (this.h.size() == 1) {
            return this.h.get(0).o();
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.n0.l
    public Number p() {
        if (this.h.size() == 1) {
            return this.h.get(0).p();
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.n0.l
    public short q() {
        if (this.h.size() == 1) {
            return this.h.get(0).q();
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.n0.l
    public String r() {
        if (this.h.size() == 1) {
            return this.h.get(0).r();
        }
        throw new IllegalStateException();
    }

    public int size() {
        return this.h.size();
    }
}
