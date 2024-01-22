package com.crrepa.q0;

import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
/* loaded from: classes9.dex */
public final class b implements y {
    public final com.crrepa.p0.c h;

    /* loaded from: classes9.dex */
    public static final class a<E> extends x<Collection<E>> {

        /* renamed from: a  reason: collision with root package name */
        public final x<E> f7817a;
        public final com.crrepa.p0.i<? extends Collection<E>> b;

        public a(com.crrepa.n0.f fVar, Type type, x<E> xVar, com.crrepa.p0.i<? extends Collection<E>> iVar) {
            this.f7817a = new m(fVar, xVar, type);
            this.b = iVar;
        }

        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                dVar.k();
                return;
            }
            dVar.c();
            for (E e : collection) {
                this.f7817a.a(dVar, (com.crrepa.t0.d) e);
            }
            dVar.e();
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Collection<E> a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            Collection<E> a2 = this.b.a();
            aVar.a();
            while (aVar.i()) {
                a2.add(this.f7817a.a(aVar));
            }
            aVar.f();
            return a2;
        }
    }

    public b(com.crrepa.p0.c cVar) {
        this.h = cVar;
    }

    @Override // com.crrepa.n0.y
    public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
        Type b = aVar.b();
        Class<? super T> a2 = aVar.a();
        if (Collection.class.isAssignableFrom(a2)) {
            Type a3 = com.crrepa.p0.b.a(b, (Class<?>) a2);
            return new a(fVar, a3, fVar.a((com.crrepa.s0.a) com.crrepa.s0.a.a(a3)), this.h.a(aVar));
        }
        return null;
    }
}
