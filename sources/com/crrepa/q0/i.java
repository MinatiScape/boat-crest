package com.crrepa.q0;

import com.crrepa.n0.v;
import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public final class i implements y {
    public final com.crrepa.p0.c h;
    public final com.crrepa.n0.e i;
    public final com.crrepa.p0.d j;
    public final d k;

    /* loaded from: classes9.dex */
    public class a extends c {
        public final /* synthetic */ Field d;
        public final /* synthetic */ boolean e;
        public final /* synthetic */ x f;
        public final /* synthetic */ com.crrepa.n0.f g;
        public final /* synthetic */ com.crrepa.s0.a h;
        public final /* synthetic */ boolean i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(i iVar, String str, boolean z, boolean z2, Field field, boolean z3, x xVar, com.crrepa.n0.f fVar, com.crrepa.s0.a aVar, boolean z4) {
            super(str, z, z2);
            this.d = field;
            this.e = z3;
            this.f = xVar;
            this.g = fVar;
            this.h = aVar;
            this.i = z4;
        }

        @Override // com.crrepa.q0.i.c
        public void a(com.crrepa.t0.a aVar, Object obj) throws IOException, IllegalAccessException {
            Object a2 = this.f.a(aVar);
            if (a2 == null && this.i) {
                return;
            }
            this.d.set(obj, a2);
        }

        @Override // com.crrepa.q0.i.c
        public void b(com.crrepa.t0.d dVar, Object obj) throws IOException, IllegalAccessException {
            (this.e ? this.f : new m(this.g, this.f, this.h.b())).a(dVar, (com.crrepa.t0.d) this.d.get(obj));
        }

        @Override // com.crrepa.q0.i.c
        public boolean c(Object obj) throws IOException, IllegalAccessException {
            return this.b && this.d.get(obj) != obj;
        }
    }

    /* loaded from: classes9.dex */
    public static final class b<T> extends x<T> {

        /* renamed from: a  reason: collision with root package name */
        public final com.crrepa.p0.i<T> f7822a;
        public final Map<String, c> b;

        public b(com.crrepa.p0.i<T> iVar, Map<String, c> map) {
            this.f7822a = iVar;
            this.b = map;
        }

        @Override // com.crrepa.n0.x
        public T a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            T a2 = this.f7822a.a();
            try {
                aVar.b();
                while (aVar.i()) {
                    c cVar = this.b.get(aVar.p());
                    if (cVar != null && cVar.c) {
                        cVar.a(aVar, a2);
                    }
                    aVar.z();
                }
                aVar.g();
                return a2;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new v(e2);
            }
        }

        @Override // com.crrepa.n0.x
        public void a(com.crrepa.t0.d dVar, T t) throws IOException {
            if (t == null) {
                dVar.k();
                return;
            }
            dVar.d();
            try {
                for (c cVar : this.b.values()) {
                    if (cVar.c(t)) {
                        dVar.b(cVar.f7823a);
                        cVar.b(dVar, t);
                    }
                }
                dVar.f();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class c {

        /* renamed from: a  reason: collision with root package name */
        public final String f7823a;
        public final boolean b;
        public final boolean c;

        public c(String str, boolean z, boolean z2) {
            this.f7823a = str;
            this.b = z;
            this.c = z2;
        }

        public abstract void a(com.crrepa.t0.a aVar, Object obj) throws IOException, IllegalAccessException;

        public abstract void b(com.crrepa.t0.d dVar, Object obj) throws IOException, IllegalAccessException;

        public abstract boolean c(Object obj) throws IOException, IllegalAccessException;
    }

    public i(com.crrepa.p0.c cVar, com.crrepa.n0.e eVar, com.crrepa.p0.d dVar, d dVar2) {
        this.h = cVar;
        this.i = eVar;
        this.j = dVar;
        this.k = dVar2;
    }

    public static boolean e(Field field, boolean z, com.crrepa.p0.d dVar) {
        return (dVar.a(field.getType(), z) || dVar.a(field, z)) ? false : true;
    }

    @Override // com.crrepa.n0.y
    public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
        Class<? super T> a2 = aVar.a();
        if (Object.class.isAssignableFrom(a2)) {
            return new b(this.h.a(aVar), d(fVar, aVar, a2));
        }
        return null;
    }

    public boolean a(Field field, boolean z) {
        return e(field, z, this.j);
    }

    public final c b(com.crrepa.n0.f fVar, Field field, String str, com.crrepa.s0.a<?> aVar, boolean z, boolean z2) {
        boolean a2 = com.crrepa.p0.j.a((Type) aVar.a());
        com.crrepa.o0.b bVar = (com.crrepa.o0.b) field.getAnnotation(com.crrepa.o0.b.class);
        x<?> b2 = bVar != null ? this.k.b(this.h, fVar, aVar, bVar) : null;
        boolean z3 = b2 != null;
        if (b2 == null) {
            b2 = fVar.a((com.crrepa.s0.a) aVar);
        }
        return new a(this, str, z, z2, field, z3, b2, fVar, aVar, a2);
    }

    public final List<String> c(Field field) {
        com.crrepa.o0.c cVar = (com.crrepa.o0.c) field.getAnnotation(com.crrepa.o0.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.i.a(field));
        }
        String value = cVar.value();
        String[] alternate = cVar.alternate();
        if (alternate.length == 0) {
            return Collections.singletonList(value);
        }
        ArrayList arrayList = new ArrayList(alternate.length + 1);
        arrayList.add(value);
        for (String str : alternate) {
            arrayList.add(str);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    public final Map<String, c> d(com.crrepa.n0.f fVar, com.crrepa.s0.a<?> aVar, Class<?> cls) {
        c cVar;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b2 = aVar.b();
        com.crrepa.s0.a<?> aVar2 = aVar;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a2 = a(field, true);
                boolean a3 = a(field, z);
                if (a2 || a3) {
                    field.setAccessible(true);
                    Type a4 = com.crrepa.p0.b.a(aVar2.b(), cls2, field.getGenericType());
                    List<String> c2 = c(field);
                    c cVar2 = null;
                    int i2 = z;
                    while (i2 < c2.size()) {
                        String str = c2.get(i2);
                        boolean z2 = i2 != 0 ? z : a2;
                        int i3 = i2;
                        c cVar3 = cVar2;
                        List<String> list = c2;
                        Field field2 = field;
                        cVar2 = cVar3 == null ? (c) linkedHashMap.put(str, b(fVar, field, str, com.crrepa.s0.a.a(a4), z2, a3)) : cVar3;
                        a2 = z2;
                        c2 = list;
                        field = field2;
                        z = false;
                        i2 = i3 + 1;
                    }
                    if (cVar2 != null) {
                        throw new IllegalArgumentException(b2 + " declares multiple JSON fields named " + cVar.f7823a);
                    }
                }
                i++;
                z = false;
            }
            aVar2 = com.crrepa.s0.a.a(com.crrepa.p0.b.a(aVar2.b(), cls2, cls2.getGenericSuperclass()));
            cls2 = aVar2.a();
        }
        return linkedHashMap;
    }
}
