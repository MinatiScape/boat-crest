package com.bumptech.glide.load.engine.bitmap_recycle;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
/* loaded from: classes2.dex */
public final class LruArrayPool implements ArrayPool {

    /* renamed from: a  reason: collision with root package name */
    public final d<a, Object> f2352a;
    public final b b;
    public final Map<Class<?>, NavigableMap<Integer, Integer>> c;
    public final Map<Class<?>, com.bumptech.glide.load.engine.bitmap_recycle.a<?>> d;
    public final int e;
    public int f;

    /* loaded from: classes2.dex */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        public final b f2353a;
        public int b;
        public Class<?> c;

        public a(b bVar) {
            this.f2353a = bVar;
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.f
        public void a() {
            this.f2353a.c(this);
        }

        public void b(int i, Class<?> cls) {
            this.b = i;
            this.c = cls;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.b == aVar.b && this.c == aVar.c;
            }
            return false;
        }

        public int hashCode() {
            int i = this.b * 31;
            Class<?> cls = this.c;
            return i + (cls != null ? cls.hashCode() : 0);
        }

        public String toString() {
            return "Key{size=" + this.b + "array=" + this.c + '}';
        }
    }

    /* loaded from: classes2.dex */
    public static final class b extends c<a> {
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.c
        /* renamed from: d */
        public a a() {
            return new a(this);
        }

        public a e(int i, Class<?> cls) {
            a b = b();
            b.b(i, cls);
            return b;
        }
    }

    @VisibleForTesting
    public LruArrayPool() {
        this.f2352a = new d<>();
        this.b = new b();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = 4194304;
    }

    public final void a(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> h = h(cls);
        Integer num = (Integer) h.get(Integer.valueOf(i));
        if (num != null) {
            if (num.intValue() == 1) {
                h.remove(Integer.valueOf(i));
                return;
            } else {
                h.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i + ", this: " + this);
    }

    public final void b() {
        c(this.e);
    }

    public final void c(int i) {
        while (this.f > i) {
            Object f = this.f2352a.f();
            Preconditions.checkNotNull(f);
            com.bumptech.glide.load.engine.bitmap_recycle.a d = d(f);
            this.f -= d.getArrayLength(f) * d.getElementSizeInBytes();
            a(d.getArrayLength(f), f.getClass());
            if (Log.isLoggable(d.getTag(), 2)) {
                Log.v(d.getTag(), "evicted: " + d.getArrayLength(f));
            }
        }
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void clearMemory() {
        c(0);
    }

    public final <T> com.bumptech.glide.load.engine.bitmap_recycle.a<T> d(T t) {
        return e(t.getClass());
    }

    public final <T> com.bumptech.glide.load.engine.bitmap_recycle.a<T> e(Class<T> cls) {
        IntegerArrayAdapter integerArrayAdapter = (com.bumptech.glide.load.engine.bitmap_recycle.a<T>) this.d.get(cls);
        if (integerArrayAdapter == null) {
            if (cls.equals(int[].class)) {
                integerArrayAdapter = new IntegerArrayAdapter();
            } else if (cls.equals(byte[].class)) {
                integerArrayAdapter = new ByteArrayAdapter();
            } else {
                throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
            }
            this.d.put(cls, integerArrayAdapter);
        }
        return integerArrayAdapter;
    }

    @Nullable
    public final <T> T f(a aVar) {
        return (T) this.f2352a.a(aVar);
    }

    public final <T> T g(a aVar, Class<T> cls) {
        com.bumptech.glide.load.engine.bitmap_recycle.a<T> e = e(cls);
        T t = (T) f(aVar);
        if (t != null) {
            this.f -= e.getArrayLength(t) * e.getElementSizeInBytes();
            a(e.getArrayLength(t), cls);
        }
        if (t == null) {
            if (Log.isLoggable(e.getTag(), 2)) {
                Log.v(e.getTag(), "Allocated " + aVar.b + " bytes");
            }
            return e.newArray(aVar.b);
        }
        return t;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T get(int i, Class<T> cls) {
        a e;
        Integer ceilingKey = h(cls).ceilingKey(Integer.valueOf(i));
        if (k(i, ceilingKey)) {
            e = this.b.e(ceilingKey.intValue(), cls);
        } else {
            e = this.b.e(i, cls);
        }
        return (T) g(e, cls);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T getExact(int i, Class<T> cls) {
        return (T) g(this.b.e(i, cls), cls);
    }

    public final NavigableMap<Integer, Integer> h(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.c.get(cls);
        if (navigableMap == null) {
            TreeMap treeMap = new TreeMap();
            this.c.put(cls, treeMap);
            return treeMap;
        }
        return navigableMap;
    }

    public final boolean i() {
        int i = this.f;
        return i == 0 || this.e / i >= 2;
    }

    public final boolean j(int i) {
        return i <= this.e / 2;
    }

    public final boolean k(int i, Integer num) {
        return num != null && (i() || num.intValue() <= i * 8);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    @Deprecated
    public <T> void put(T t, Class<T> cls) {
        put(t);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void trimMemory(int i) {
        try {
            if (i >= 40) {
                clearMemory();
            } else if (i >= 20 || i == 15) {
                c(this.e / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> void put(T t) {
        Class<?> cls = t.getClass();
        com.bumptech.glide.load.engine.bitmap_recycle.a<T> e = e(cls);
        int arrayLength = e.getArrayLength(t);
        int elementSizeInBytes = e.getElementSizeInBytes() * arrayLength;
        if (j(elementSizeInBytes)) {
            a e2 = this.b.e(arrayLength, cls);
            this.f2352a.d(e2, t);
            NavigableMap<Integer, Integer> h = h(cls);
            Integer num = (Integer) h.get(Integer.valueOf(e2.b));
            Integer valueOf = Integer.valueOf(e2.b);
            int i = 1;
            if (num != null) {
                i = 1 + num.intValue();
            }
            h.put(valueOf, Integer.valueOf(i));
            this.f += elementSizeInBytes;
            b();
        }
    }

    public LruArrayPool(int i) {
        this.f2352a = new d<>();
        this.b = new b();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = i;
    }
}
