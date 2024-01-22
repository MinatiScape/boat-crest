package com.crrepa.p0;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
/* loaded from: classes9.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Type, com.crrepa.n0.h<?>> f7796a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class a<T> implements com.crrepa.p0.i<T> {
        public a(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class b<T> implements com.crrepa.p0.i<T> {
        public b(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.crrepa.p0.c$c  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class C0350c<T> implements com.crrepa.p0.i<T> {
        public C0350c(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class d<T> implements com.crrepa.p0.i<T> {
        public d(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new com.crrepa.p0.h();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class e<T> implements com.crrepa.p0.i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final com.crrepa.p0.l f7797a = com.crrepa.p0.l.a();
        public final /* synthetic */ Class b;
        public final /* synthetic */ Type c;

        public e(c cVar, Class cls, Type type) {
            this.b = cls;
            this.c = type;
        }

        @Override // com.crrepa.p0.i
        public T a() {
            try {
                return (T) this.f7797a.c(this.b);
            } catch (Exception e) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.c + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class f<T> implements com.crrepa.p0.i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ com.crrepa.n0.h f7798a;
        public final /* synthetic */ Type b;

        public f(c cVar, com.crrepa.n0.h hVar, Type type) {
            this.f7798a = hVar;
            this.b = type;
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) this.f7798a.a(this.b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class g<T> implements com.crrepa.p0.i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ com.crrepa.n0.h f7799a;
        public final /* synthetic */ Type b;

        public g(c cVar, com.crrepa.n0.h hVar, Type type) {
            this.f7799a = hVar;
            this.b = type;
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) this.f7799a.a(this.b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class h<T> implements com.crrepa.p0.i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Constructor f7800a;

        public h(c cVar, Constructor constructor) {
            this.f7800a = constructor;
        }

        @Override // com.crrepa.p0.i
        public T a() {
            try {
                return (T) this.f7800a.newInstance(null);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Failed to invoke " + this.f7800a + " with no args", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to invoke " + this.f7800a + " with no args", e3.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class i<T> implements com.crrepa.p0.i<T> {
        public i(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class j<T> implements com.crrepa.p0.i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Type f7801a;

        public j(c cVar, Type type) {
            this.f7801a = type;
        }

        @Override // com.crrepa.p0.i
        public T a() {
            Type type = this.f7801a;
            if (!(type instanceof ParameterizedType)) {
                throw new com.crrepa.n0.m("Invalid EnumSet type: " + this.f7801a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new com.crrepa.n0.m("Invalid EnumSet type: " + this.f7801a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class k<T> implements com.crrepa.p0.i<T> {
        public k(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class l<T> implements com.crrepa.p0.i<T> {
        public l(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class m<T> implements com.crrepa.p0.i<T> {
        public m(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public class n<T> implements com.crrepa.p0.i<T> {
        public n(c cVar) {
        }

        @Override // com.crrepa.p0.i
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public c(Map<Type, com.crrepa.n0.h<?>> map) {
        this.f7796a = map;
    }

    public <T> com.crrepa.p0.i<T> a(com.crrepa.s0.a<T> aVar) {
        Type b2 = aVar.b();
        Class<? super T> a2 = aVar.a();
        com.crrepa.n0.h<?> hVar = this.f7796a.get(b2);
        if (hVar != null) {
            return new f(this, hVar, b2);
        }
        com.crrepa.n0.h<?> hVar2 = this.f7796a.get(a2);
        if (hVar2 != null) {
            return new g(this, hVar2, b2);
        }
        com.crrepa.p0.i<T> b3 = b(a2);
        if (b3 != null) {
            return b3;
        }
        com.crrepa.p0.i<T> c = c(b2, a2);
        return c != null ? c : d(b2, a2);
    }

    public final <T> com.crrepa.p0.i<T> b(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new h(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final <T> com.crrepa.p0.i<T> c(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new i(this) : EnumSet.class.isAssignableFrom(cls) ? new j(this, type) : Set.class.isAssignableFrom(cls) ? new k(this) : Queue.class.isAssignableFrom(cls) ? new l(this) : new m(this);
        } else if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new n(this) : ConcurrentMap.class.isAssignableFrom(cls) ? new a(this) : SortedMap.class.isAssignableFrom(cls) ? new b(this) : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(com.crrepa.s0.a.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) ? new d(this) : new C0350c(this);
        } else {
            return null;
        }
    }

    public final <T> com.crrepa.p0.i<T> d(Type type, Class<? super T> cls) {
        return new e(this, cls, type);
    }

    public String toString() {
        return this.f7796a.toString();
    }
}
