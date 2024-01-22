package com.crrepa.p0;

import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public final class d implements y, Cloneable {
    public static final d h = new d();
    public boolean l;
    public double i = -1.0d;
    public int j = 136;
    public boolean k = true;
    public List<com.crrepa.n0.b> m = Collections.emptyList();
    public List<com.crrepa.n0.b> n = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class a<T> extends x<T> {

        /* renamed from: a  reason: collision with root package name */
        public x<T> f7802a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ boolean c;
        public final /* synthetic */ com.crrepa.n0.f d;
        public final /* synthetic */ com.crrepa.s0.a e;

        public a(boolean z, boolean z2, com.crrepa.n0.f fVar, com.crrepa.s0.a aVar) {
            this.b = z;
            this.c = z2;
            this.d = fVar;
            this.e = aVar;
        }

        @Override // com.crrepa.n0.x
        public T a(com.crrepa.t0.a aVar) throws IOException {
            if (this.b) {
                aVar.z();
                return null;
            }
            return c().a(aVar);
        }

        @Override // com.crrepa.n0.x
        public void a(com.crrepa.t0.d dVar, T t) throws IOException {
            if (this.c) {
                dVar.k();
            } else {
                c().a(dVar, (com.crrepa.t0.d) t);
            }
        }

        public final x<T> c() {
            x<T> xVar = this.f7802a;
            if (xVar != null) {
                return xVar;
            }
            x<T> a2 = this.d.a(d.this, this.e);
            this.f7802a = a2;
            return a2;
        }
    }

    @Override // com.crrepa.n0.y
    public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
        Class<? super T> a2 = aVar.a();
        boolean a3 = a((Class<?>) a2, true);
        boolean a4 = a((Class<?>) a2, false);
        if (a3 || a4) {
            return new a(a4, a3, fVar, aVar);
        }
        return null;
    }

    /* renamed from: a */
    public d clone() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public d a(double d) {
        d clone = clone();
        clone.i = d;
        return clone;
    }

    public d a(com.crrepa.n0.b bVar, boolean z, boolean z2) {
        d clone = clone();
        if (z) {
            ArrayList arrayList = new ArrayList(this.m);
            clone.m = arrayList;
            arrayList.add(bVar);
        }
        if (z2) {
            ArrayList arrayList2 = new ArrayList(this.n);
            clone.n = arrayList2;
            arrayList2.add(bVar);
        }
        return clone;
    }

    public d a(int... iArr) {
        d clone = clone();
        clone.j = 0;
        for (int i : iArr) {
            clone.j = i | clone.j;
        }
        return clone;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.i == -1.0d || c((com.crrepa.o0.d) cls.getAnnotation(com.crrepa.o0.d.class), (com.crrepa.o0.e) cls.getAnnotation(com.crrepa.o0.e.class))) {
            if ((this.k || !f(cls)) && !e(cls)) {
                for (com.crrepa.n0.b bVar : z ? this.m : this.n) {
                    if (bVar.a(cls)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean a(Field field, boolean z) {
        com.crrepa.o0.a aVar;
        if ((this.j & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.i == -1.0d || c((com.crrepa.o0.d) field.getAnnotation(com.crrepa.o0.d.class), (com.crrepa.o0.e) field.getAnnotation(com.crrepa.o0.e.class))) && !field.isSynthetic()) {
            if (!this.l || ((aVar = (com.crrepa.o0.a) field.getAnnotation(com.crrepa.o0.a.class)) != null && (!z ? !aVar.deserialize() : !aVar.serialize()))) {
                if ((this.k || !f(field.getType())) && !e(field.getType())) {
                    List<com.crrepa.n0.b> list = z ? this.m : this.n;
                    if (list.isEmpty()) {
                        return false;
                    }
                    com.crrepa.n0.c cVar = new com.crrepa.n0.c(field);
                    for (com.crrepa.n0.b bVar : list) {
                        if (bVar.a(cVar)) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    public d b() {
        d clone = clone();
        clone.k = false;
        return clone;
    }

    public final boolean b(com.crrepa.o0.d dVar) {
        return dVar == null || dVar.value() <= this.i;
    }

    public d c() {
        d clone = clone();
        clone.l = true;
        return clone;
    }

    public final boolean c(com.crrepa.o0.d dVar, com.crrepa.o0.e eVar) {
        return b(dVar) && d(eVar);
    }

    public final boolean d(com.crrepa.o0.e eVar) {
        return eVar == null || eVar.value() > this.i;
    }

    public final boolean e(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public final boolean f(Class<?> cls) {
        return cls.isMemberClass() && !g(cls);
    }

    public final boolean g(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }
}
