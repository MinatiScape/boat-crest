package com.crrepa.n0;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
/* loaded from: classes9.dex */
public final class f {
    public static final com.crrepa.s0.a<?> m = new a();

    /* renamed from: a  reason: collision with root package name */
    public final ThreadLocal<Map<com.crrepa.s0.a<?>, g<?>>> f7775a;
    public final Map<com.crrepa.s0.a<?>, x<?>> b;
    public final List<y> c;
    public final com.crrepa.p0.c d;
    public final com.crrepa.p0.d e;
    public final com.crrepa.n0.e f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final com.crrepa.q0.d l;

    /* loaded from: classes9.dex */
    public static class a extends com.crrepa.s0.a<Object> {
    }

    /* loaded from: classes9.dex */
    public class b extends x<Number> {
        public b(f fVar) {
        }

        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            if (number == null) {
                dVar.k();
                return;
            }
            f.d(number.doubleValue());
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Double a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return Double.valueOf(aVar.m());
        }
    }

    /* loaded from: classes9.dex */
    public class c extends x<Number> {
        public c(f fVar) {
        }

        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            if (number == null) {
                dVar.k();
                return;
            }
            f.d(number.floatValue());
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Float a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return Float.valueOf((float) aVar.m());
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            if (number == null) {
                dVar.k();
            } else {
                dVar.e(number.toString());
            }
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return Long.valueOf(aVar.o());
        }
    }

    /* loaded from: classes9.dex */
    public static class e extends x<AtomicLong> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ x f7776a;

        public e(x xVar) {
            this.f7776a = xVar;
        }

        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, AtomicLong atomicLong) throws IOException {
            this.f7776a.a(dVar, (com.crrepa.t0.d) Long.valueOf(atomicLong.get()));
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public AtomicLong a(com.crrepa.t0.a aVar) throws IOException {
            return new AtomicLong(((Number) this.f7776a.a(aVar)).longValue());
        }
    }

    /* renamed from: com.crrepa.n0.f$f  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0347f extends x<AtomicLongArray> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ x f7777a;

        public C0347f(x xVar) {
            this.f7777a = xVar;
        }

        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, AtomicLongArray atomicLongArray) throws IOException {
            dVar.c();
            int length = atomicLongArray.length();
            for (int i = 0; i < length; i++) {
                this.f7777a.a(dVar, (com.crrepa.t0.d) Long.valueOf(atomicLongArray.get(i)));
            }
            dVar.e();
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public AtomicLongArray a(com.crrepa.t0.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.i()) {
                arrayList.add(Long.valueOf(((Number) this.f7777a.a(aVar)).longValue()));
            }
            aVar.f();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i = 0; i < size; i++) {
                atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
            }
            return atomicLongArray;
        }
    }

    /* loaded from: classes9.dex */
    public static class g<T> extends x<T> {

        /* renamed from: a  reason: collision with root package name */
        public x<T> f7778a;

        @Override // com.crrepa.n0.x
        public T a(com.crrepa.t0.a aVar) throws IOException {
            x<T> xVar = this.f7778a;
            if (xVar != null) {
                return xVar.a(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // com.crrepa.n0.x
        public void a(com.crrepa.t0.d dVar, T t) throws IOException {
            x<T> xVar = this.f7778a;
            if (xVar == null) {
                throw new IllegalStateException();
            }
            xVar.a(dVar, (com.crrepa.t0.d) t);
        }

        public void c(x<T> xVar) {
            if (this.f7778a != null) {
                throw new AssertionError();
            }
            this.f7778a = xVar;
        }
    }

    public f() {
        this(com.crrepa.p0.d.h, com.crrepa.n0.d.f7774a, Collections.emptyMap(), false, false, false, true, false, false, false, w.f7784a, Collections.emptyList());
    }

    public f(com.crrepa.p0.d dVar, com.crrepa.n0.e eVar, Map<Type, h<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, w wVar, List<y> list) {
        this.f7775a = new ThreadLocal<>();
        this.b = new ConcurrentHashMap();
        com.crrepa.p0.c cVar = new com.crrepa.p0.c(map);
        this.d = cVar;
        this.e = dVar;
        this.f = eVar;
        this.g = z;
        this.i = z3;
        this.h = z4;
        this.j = z5;
        this.k = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(com.crrepa.q0.n.Y);
        arrayList.add(com.crrepa.q0.h.b);
        arrayList.add(dVar);
        arrayList.addAll(list);
        arrayList.add(com.crrepa.q0.n.D);
        arrayList.add(com.crrepa.q0.n.m);
        arrayList.add(com.crrepa.q0.n.g);
        arrayList.add(com.crrepa.q0.n.i);
        arrayList.add(com.crrepa.q0.n.k);
        x<Number> a2 = a(wVar);
        arrayList.add(com.crrepa.q0.n.a(Long.TYPE, Long.class, a2));
        arrayList.add(com.crrepa.q0.n.a(Double.TYPE, Double.class, c(z7)));
        arrayList.add(com.crrepa.q0.n.a(Float.TYPE, Float.class, g(z7)));
        arrayList.add(com.crrepa.q0.n.x);
        arrayList.add(com.crrepa.q0.n.o);
        arrayList.add(com.crrepa.q0.n.q);
        arrayList.add(com.crrepa.q0.n.a(AtomicLong.class, b(a2)));
        arrayList.add(com.crrepa.q0.n.a(AtomicLongArray.class, f(a2)));
        arrayList.add(com.crrepa.q0.n.s);
        arrayList.add(com.crrepa.q0.n.z);
        arrayList.add(com.crrepa.q0.n.F);
        arrayList.add(com.crrepa.q0.n.H);
        arrayList.add(com.crrepa.q0.n.a(BigDecimal.class, com.crrepa.q0.n.B));
        arrayList.add(com.crrepa.q0.n.a(BigInteger.class, com.crrepa.q0.n.C));
        arrayList.add(com.crrepa.q0.n.J);
        arrayList.add(com.crrepa.q0.n.L);
        arrayList.add(com.crrepa.q0.n.P);
        arrayList.add(com.crrepa.q0.n.R);
        arrayList.add(com.crrepa.q0.n.W);
        arrayList.add(com.crrepa.q0.n.N);
        arrayList.add(com.crrepa.q0.n.d);
        arrayList.add(com.crrepa.q0.c.c);
        arrayList.add(com.crrepa.q0.n.U);
        arrayList.add(com.crrepa.q0.k.b);
        arrayList.add(com.crrepa.q0.j.b);
        arrayList.add(com.crrepa.q0.n.S);
        arrayList.add(com.crrepa.q0.a.c);
        arrayList.add(com.crrepa.q0.n.b);
        arrayList.add(new com.crrepa.q0.b(cVar));
        arrayList.add(new com.crrepa.q0.g(cVar, z2));
        com.crrepa.q0.d dVar2 = new com.crrepa.q0.d(cVar);
        this.l = dVar2;
        arrayList.add(dVar2);
        arrayList.add(com.crrepa.q0.n.Z);
        arrayList.add(new com.crrepa.q0.i(cVar, eVar, dVar, dVar2));
        this.c = Collections.unmodifiableList(arrayList);
    }

    public static x<Number> a(w wVar) {
        return wVar == w.f7784a ? com.crrepa.q0.n.t : new d();
    }

    public static x<AtomicLong> b(x<Number> xVar) {
        return new e(xVar).a();
    }

    public static void d(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public static void e(Object obj, com.crrepa.t0.a aVar) {
        if (obj != null) {
            try {
                if (aVar.t() == com.crrepa.t0.c.END_DOCUMENT) {
                    return;
                }
                throw new m("JSON document was not fully consumed.");
            } catch (com.crrepa.t0.e e2) {
                throw new v(e2);
            } catch (IOException e3) {
                throw new m(e3);
            }
        }
    }

    public static x<AtomicLongArray> f(x<Number> xVar) {
        return new C0347f(xVar).a();
    }

    public <T> x<T> a(y yVar, com.crrepa.s0.a<T> aVar) {
        if (!this.c.contains(yVar)) {
            yVar = this.l;
        }
        boolean z = false;
        for (y yVar2 : this.c) {
            if (z) {
                x<T> a2 = yVar2.a(this, aVar);
                if (a2 != null) {
                    return a2;
                }
            } else if (yVar2 == yVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> x<T> a(com.crrepa.s0.a<T> aVar) {
        x<T> xVar = (x<T>) this.b.get(aVar == null ? m : aVar);
        if (xVar != null) {
            return xVar;
        }
        Map<com.crrepa.s0.a<?>, g<?>> map = this.f7775a.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f7775a.set(map);
            z = true;
        }
        g<?> gVar = map.get(aVar);
        if (gVar != null) {
            return gVar;
        }
        try {
            g<?> gVar2 = new g<>();
            map.put(aVar, gVar2);
            for (y yVar : this.c) {
                x xVar2 = (x<T>) yVar.a(this, aVar);
                if (xVar2 != null) {
                    gVar2.c(xVar2);
                    this.b.put(aVar, xVar2);
                    return xVar2;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f7775a.remove();
            }
        }
    }

    public <T> x<T> a(Class<T> cls) {
        return a((com.crrepa.s0.a) com.crrepa.s0.a.a((Class) cls));
    }

    public com.crrepa.p0.d a() {
        return this.e;
    }

    public com.crrepa.t0.a a(Reader reader) {
        com.crrepa.t0.a aVar = new com.crrepa.t0.a(reader);
        aVar.b(this.k);
        return aVar;
    }

    public com.crrepa.t0.d a(Writer writer) throws IOException {
        if (this.i) {
            writer.write(")]}'\n");
        }
        com.crrepa.t0.d dVar = new com.crrepa.t0.d(writer);
        if (this.j) {
            dVar.c("  ");
        }
        dVar.c(this.g);
        return dVar;
    }

    public <T> T a(l lVar, Class<T> cls) throws v {
        return (T) com.crrepa.p0.j.b((Class) cls).cast(a(lVar, (Type) cls));
    }

    public <T> T a(l lVar, Type type) throws v {
        if (lVar == null) {
            return null;
        }
        return (T) a((com.crrepa.t0.a) new com.crrepa.q0.e(lVar), type);
    }

    public <T> T a(com.crrepa.t0.a aVar, Type type) throws m, v {
        boolean j = aVar.j();
        boolean z = true;
        aVar.b(true);
        try {
            try {
                try {
                    aVar.t();
                    z = false;
                    return a((com.crrepa.s0.a) com.crrepa.s0.a.a(type)).a(aVar);
                } catch (IOException e2) {
                    throw new v(e2);
                }
            } catch (EOFException e3) {
                if (z) {
                    aVar.b(j);
                    return null;
                }
                throw new v(e3);
            } catch (IllegalStateException e4) {
                throw new v(e4);
            }
        } finally {
            aVar.b(j);
        }
    }

    public <T> T a(Reader reader, Class<T> cls) throws v, m {
        com.crrepa.t0.a a2 = a(reader);
        Object a3 = a(a2, (Type) cls);
        e(a3, a2);
        return (T) com.crrepa.p0.j.b((Class) cls).cast(a3);
    }

    public <T> T a(Reader reader, Type type) throws m, v {
        com.crrepa.t0.a a2 = a(reader);
        T t = (T) a(a2, type);
        e(t, a2);
        return t;
    }

    public <T> T a(String str, Class<T> cls) throws v {
        return (T) com.crrepa.p0.j.b((Class) cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) throws v {
        if (str == null) {
            return null;
        }
        return (T) a((Reader) new StringReader(str), type);
    }

    public String a(l lVar) {
        StringWriter stringWriter = new StringWriter();
        a(lVar, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public String a(Object obj) {
        return obj == null ? a((l) n.f7780a) : a(obj, obj.getClass());
    }

    public String a(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(l lVar, com.crrepa.t0.d dVar) throws m {
        boolean i = dVar.i();
        dVar.b(true);
        boolean h = dVar.h();
        dVar.a(this.h);
        boolean g2 = dVar.g();
        dVar.c(this.g);
        try {
            try {
                com.crrepa.p0.k.a(lVar, dVar);
            } catch (IOException e2) {
                throw new m(e2);
            }
        } finally {
            dVar.b(i);
            dVar.a(h);
            dVar.c(g2);
        }
    }

    public void a(l lVar, Appendable appendable) throws m {
        try {
            a(lVar, a(com.crrepa.p0.k.a(appendable)));
        } catch (IOException e2) {
            throw new m(e2);
        }
    }

    public void a(Object obj, Appendable appendable) throws m {
        if (obj != null) {
            a(obj, obj.getClass(), appendable);
        } else {
            a((l) n.f7780a, appendable);
        }
    }

    public void a(Object obj, Type type, com.crrepa.t0.d dVar) throws m {
        x a2 = a((com.crrepa.s0.a) com.crrepa.s0.a.a(type));
        boolean i = dVar.i();
        dVar.b(true);
        boolean h = dVar.h();
        dVar.a(this.h);
        boolean g2 = dVar.g();
        dVar.c(this.g);
        try {
            try {
                a2.a(dVar, (com.crrepa.t0.d) obj);
            } catch (IOException e2) {
                throw new m(e2);
            }
        } finally {
            dVar.b(i);
            dVar.a(h);
            dVar.c(g2);
        }
    }

    public void a(Object obj, Type type, Appendable appendable) throws m {
        try {
            a(obj, type, a(com.crrepa.p0.k.a(appendable)));
        } catch (IOException e2) {
            throw new m(e2);
        }
    }

    public com.crrepa.n0.e b() {
        return this.f;
    }

    public l b(Object obj) {
        return obj == null ? n.f7780a : b(obj, obj.getClass());
    }

    public l b(Object obj, Type type) {
        com.crrepa.q0.f fVar = new com.crrepa.q0.f();
        a(obj, type, fVar);
        return fVar.n();
    }

    public final x<Number> c(boolean z) {
        return z ? com.crrepa.q0.n.v : new b(this);
    }

    public boolean c() {
        return this.h;
    }

    public boolean d() {
        return this.g;
    }

    public final x<Number> g(boolean z) {
        return z ? com.crrepa.q0.n.u : new c(this);
    }

    public String toString() {
        return "{serializeNulls:" + this.g + "factories:" + this.c + ",instanceCreators:" + this.d + "}";
    }
}
