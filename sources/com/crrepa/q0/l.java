package com.crrepa.q0;

import com.crrepa.n0.p;
import com.crrepa.n0.s;
import com.crrepa.n0.t;
import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.lang.reflect.Type;
/* loaded from: classes9.dex */
public final class l<T> extends x<T> {

    /* renamed from: a  reason: collision with root package name */
    public final t<T> f7826a;
    public final com.crrepa.n0.k<T> b;
    public final com.crrepa.n0.f c;
    public final com.crrepa.s0.a<T> d;
    public final y e;
    public final l<T>.b f = new b();
    public x<T> g;

    /* loaded from: classes9.dex */
    public final class b implements s, com.crrepa.n0.j {
        public b() {
        }

        @Override // com.crrepa.n0.s
        public com.crrepa.n0.l a(Object obj) {
            return l.this.c.b(obj);
        }

        @Override // com.crrepa.n0.s
        public com.crrepa.n0.l a(Object obj, Type type) {
            return l.this.c.b(obj, type);
        }

        @Override // com.crrepa.n0.j
        public <R> R a(com.crrepa.n0.l lVar, Type type) throws p {
            return (R) l.this.c.a(lVar, type);
        }
    }

    /* loaded from: classes9.dex */
    public static final class c implements y {
        public final com.crrepa.s0.a<?> h;
        public final boolean i;
        public final Class<?> j;
        public final t<?> k;
        public final com.crrepa.n0.k<?> l;

        public c(Object obj, com.crrepa.s0.a<?> aVar, boolean z, Class<?> cls) {
            t<?> tVar = obj instanceof t ? (t) obj : null;
            this.k = tVar;
            com.crrepa.n0.k<?> kVar = obj instanceof com.crrepa.n0.k ? (com.crrepa.n0.k) obj : null;
            this.l = kVar;
            com.crrepa.p0.a.a((tVar == null && kVar == null) ? false : true);
            this.h = aVar;
            this.i = z;
            this.j = cls;
        }

        @Override // com.crrepa.n0.y
        public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            com.crrepa.s0.a<?> aVar2 = this.h;
            if (aVar2 != null ? aVar2.equals(aVar) || (this.i && this.h.b() == aVar.a()) : this.j.isAssignableFrom(aVar.a())) {
                return new l(this.k, this.l, fVar, aVar, this);
            }
            return null;
        }
    }

    public l(t<T> tVar, com.crrepa.n0.k<T> kVar, com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar, y yVar) {
        this.f7826a = tVar;
        this.b = kVar;
        this.c = fVar;
        this.d = aVar;
        this.e = yVar;
    }

    public static y a(com.crrepa.s0.a<?> aVar, Object obj) {
        return new c(obj, aVar, false, null);
    }

    public static y a(Class<?> cls, Object obj) {
        return new c(obj, null, false, cls);
    }

    public static y b(com.crrepa.s0.a<?> aVar, Object obj) {
        return new c(obj, aVar, aVar.b() == aVar.a(), null);
    }

    @Override // com.crrepa.n0.x
    public T a(com.crrepa.t0.a aVar) throws IOException {
        if (this.b == null) {
            return d().a(aVar);
        }
        com.crrepa.n0.l a2 = com.crrepa.p0.k.a(aVar);
        if (a2.t()) {
            return null;
        }
        return this.b.a(a2, this.d.b(), this.f);
    }

    @Override // com.crrepa.n0.x
    public void a(com.crrepa.t0.d dVar, T t) throws IOException {
        t<T> tVar = this.f7826a;
        if (tVar == null) {
            d().a(dVar, (com.crrepa.t0.d) t);
        } else if (t == null) {
            dVar.k();
        } else {
            com.crrepa.p0.k.a(tVar.a(t, this.d.b(), this.f), dVar);
        }
    }

    public final x<T> d() {
        x<T> xVar = this.g;
        if (xVar != null) {
            return xVar;
        }
        x<T> a2 = this.c.a(this.e, this.d);
        this.g = a2;
        return a2;
    }
}
