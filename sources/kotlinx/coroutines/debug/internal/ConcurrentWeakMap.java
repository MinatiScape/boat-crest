package kotlinx.coroutines.debug.internal;

import com.clevertap.android.sdk.Constants;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.h;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ConcurrentWeakMap<K, V> extends AbstractMutableMap<K, V> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater i = AtomicIntegerFieldUpdater.newUpdater(ConcurrentWeakMap.class, "_size");
    @NotNull
    private volatile /* synthetic */ int _size;
    @NotNull
    public volatile /* synthetic */ Object core;
    @Nullable
    public final ReferenceQueue<K> h;

    /* loaded from: classes12.dex */
    public final class a {
        public static final /* synthetic */ AtomicIntegerFieldUpdater g = AtomicIntegerFieldUpdater.newUpdater(a.class, "load");

        /* renamed from: a  reason: collision with root package name */
        public final int f14152a;
        public final int b;
        public final int c;
        @NotNull
        public /* synthetic */ AtomicReferenceArray d;
        @NotNull
        public /* synthetic */ AtomicReferenceArray e;
        @NotNull
        private volatile /* synthetic */ int load = 0;

        /* renamed from: kotlinx.coroutines.debug.internal.ConcurrentWeakMap$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0875a<E> implements Iterator<E>, KMutableIterator {
            @NotNull
            public final Function2<K, V, E> h;
            public int i = -1;
            public K j;
            public V k;

            /* JADX WARN: Multi-variable type inference failed */
            public C0875a(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
                this.h = function2;
                a();
            }

            public final void a() {
                while (true) {
                    int i = this.i + 1;
                    this.i = i;
                    if (i >= a.this.f14152a) {
                        return;
                    }
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) a.this.d.get(this.i);
                    K k = hashedWeakRef == null ? null : (K) hashedWeakRef.get();
                    if (k != null) {
                        this.j = k;
                        V v = (V) a.this.e.get(this.i);
                        if (v instanceof kotlinx.coroutines.debug.internal.b) {
                            v = (V) ((kotlinx.coroutines.debug.internal.b) v).f14157a;
                        }
                        if (v != null) {
                            this.k = v;
                            return;
                        }
                    }
                }
            }

            @Override // java.util.Iterator
            @NotNull
            /* renamed from: b */
            public Void remove() {
                ConcurrentWeakMapKt.b();
                throw new KotlinNothingValueException();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < a.this.f14152a;
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.i < a.this.f14152a) {
                    Function2<K, V, E> function2 = this.h;
                    K k = this.j;
                    if (k == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(Constants.KEY_KEY);
                        k = (K) Unit.INSTANCE;
                    }
                    V v = this.k;
                    if (v == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("value");
                        v = (V) Unit.INSTANCE;
                    }
                    E invoke = function2.invoke(k, v);
                    a();
                    return invoke;
                }
                throw new NoSuchElementException();
            }
        }

        public a(int i) {
            this.f14152a = i;
            this.b = Integer.numberOfLeadingZeros(i) + 1;
            this.c = (i * 2) / 3;
            this.d = new AtomicReferenceArray(i);
            this.e = new AtomicReferenceArray(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object g(a aVar, Object obj, Object obj2, HashedWeakRef hashedWeakRef, int i, Object obj3) {
            if ((i & 4) != 0) {
                hashedWeakRef = null;
            }
            return aVar.f(obj, obj2, hashedWeakRef);
        }

        public final void b(@NotNull HashedWeakRef<?> hashedWeakRef) {
            int d = d(hashedWeakRef.hash);
            while (true) {
                HashedWeakRef<?> hashedWeakRef2 = (HashedWeakRef) this.d.get(d);
                if (hashedWeakRef2 == null) {
                    return;
                }
                if (hashedWeakRef2 == hashedWeakRef) {
                    i(d);
                    return;
                }
                if (d == 0) {
                    d = this.f14152a;
                }
                d--;
            }
        }

        @Nullable
        public final V c(@NotNull K k) {
            int d = d(k.hashCode());
            while (true) {
                HashedWeakRef hashedWeakRef = (HashedWeakRef) this.d.get(d);
                if (hashedWeakRef == null) {
                    return null;
                }
                T t = hashedWeakRef.get();
                if (Intrinsics.areEqual(k, t)) {
                    V v = (V) this.e.get(d);
                    return v instanceof kotlinx.coroutines.debug.internal.b ? (V) ((kotlinx.coroutines.debug.internal.b) v).f14157a : v;
                }
                if (t == 0) {
                    i(d);
                }
                if (d == 0) {
                    d = this.f14152a;
                }
                d--;
            }
        }

        public final int d(int i) {
            return (i * (-1640531527)) >>> this.b;
        }

        @NotNull
        public final <E> Iterator<E> e(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
            return new C0875a(function2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
            r6 = r5.e.get(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
            if ((r6 instanceof kotlinx.coroutines.debug.internal.b) == false) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0060, code lost:
            r6 = kotlinx.coroutines.debug.internal.ConcurrentWeakMapKt.f14153a;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
            return r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x006b, code lost:
            if (r5.e.compareAndSet(r0, r6, r7) == false) goto L18;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x006d, code lost:
            return r6;
         */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object f(@org.jetbrains.annotations.NotNull K r6, @org.jetbrains.annotations.Nullable V r7, @org.jetbrains.annotations.Nullable kotlinx.coroutines.debug.internal.HashedWeakRef<K> r8) {
            /*
                r5 = this;
                int r0 = r6.hashCode()
                int r0 = r5.d(r0)
                r1 = 0
            L9:
                java.util.concurrent.atomic.AtomicReferenceArray r2 = r5.d
                java.lang.Object r2 = r2.get(r0)
                kotlinx.coroutines.debug.internal.HashedWeakRef r2 = (kotlinx.coroutines.debug.internal.HashedWeakRef) r2
                if (r2 != 0) goto L45
                r2 = 0
                if (r7 != 0) goto L17
                return r2
            L17:
                if (r1 != 0) goto L2f
            L19:
                int r1 = r5.load
                int r3 = r5.c
                if (r1 < r3) goto L24
                kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.debug.internal.ConcurrentWeakMapKt.access$getREHASH$p()
                return r6
            L24:
                int r3 = r1 + 1
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.a.g
                boolean r1 = r4.compareAndSet(r5, r1, r3)
                if (r1 == 0) goto L19
                r1 = 1
            L2f:
                if (r8 != 0) goto L3c
                kotlinx.coroutines.debug.internal.HashedWeakRef r8 = new kotlinx.coroutines.debug.internal.HashedWeakRef
                kotlinx.coroutines.debug.internal.ConcurrentWeakMap<K, V> r3 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.this
                java.lang.ref.ReferenceQueue r3 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.access$getWeakRefQueue$p(r3)
                r8.<init>(r6, r3)
            L3c:
                java.util.concurrent.atomic.AtomicReferenceArray r3 = r5.d
                boolean r2 = r3.compareAndSet(r0, r2, r8)
                if (r2 == 0) goto L9
                goto L56
            L45:
                java.lang.Object r2 = r2.get()
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r2)
                if (r3 == 0) goto L6e
                if (r1 == 0) goto L56
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r6 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.a.g
                r6.decrementAndGet(r5)
            L56:
                java.util.concurrent.atomic.AtomicReferenceArray r6 = r5.e
                java.lang.Object r6 = r6.get(r0)
                boolean r8 = r6 instanceof kotlinx.coroutines.debug.internal.b
                if (r8 == 0) goto L65
                kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.debug.internal.ConcurrentWeakMapKt.access$getREHASH$p()
                return r6
            L65:
                java.util.concurrent.atomic.AtomicReferenceArray r8 = r5.e
                boolean r8 = r8.compareAndSet(r0, r6, r7)
                if (r8 == 0) goto L56
                return r6
            L6e:
                if (r2 != 0) goto L73
                r5.i(r0)
            L73:
                if (r0 != 0) goto L77
                int r0 = r5.f14152a
            L77:
                int r0 = r0 + (-1)
                goto L9
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.debug.internal.ConcurrentWeakMap.a.f(java.lang.Object, java.lang.Object, kotlinx.coroutines.debug.internal.HashedWeakRef):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final ConcurrentWeakMap<K, V>.a h() {
            Object obj;
            Symbol symbol;
            kotlinx.coroutines.debug.internal.b a2;
            while (true) {
                ConcurrentWeakMap<K, V>.a aVar = (ConcurrentWeakMap<K, V>.a) new a(Integer.highestOneBit(h.coerceAtLeast(ConcurrentWeakMap.this.size(), 4)) * 4);
                int i = 0;
                int i2 = this.f14152a;
                while (i < i2) {
                    int i3 = i + 1;
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) this.d.get(i);
                    Object obj2 = hashedWeakRef == null ? null : hashedWeakRef.get();
                    if (hashedWeakRef != null && obj2 == null) {
                        i(i);
                    }
                    while (true) {
                        obj = this.e.get(i);
                        if (obj instanceof kotlinx.coroutines.debug.internal.b) {
                            obj = ((kotlinx.coroutines.debug.internal.b) obj).f14157a;
                            break;
                        }
                        AtomicReferenceArray atomicReferenceArray = this.e;
                        a2 = ConcurrentWeakMapKt.a(obj);
                        if (atomicReferenceArray.compareAndSet(i, obj, a2)) {
                            break;
                        }
                    }
                    if (obj2 != null && obj != null) {
                        Object f = aVar.f(obj2, obj, hashedWeakRef);
                        symbol = ConcurrentWeakMapKt.f14153a;
                        if (f == symbol) {
                            break;
                        }
                    }
                    i = i3;
                }
                return aVar;
            }
        }

        public final void i(int i) {
            Object obj;
            do {
                obj = this.e.get(i);
                if (obj == null || (obj instanceof kotlinx.coroutines.debug.internal.b)) {
                    return;
                }
            } while (!this.e.compareAndSet(i, obj, null));
            ConcurrentWeakMap.this.b();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
        public final K h;
        public final V i;

        public b(K k, V v) {
            this.h = k;
            this.i = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.h;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.i;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            ConcurrentWeakMapKt.b();
            throw new KotlinNothingValueException();
        }
    }

    /* loaded from: classes12.dex */
    public final class c<E> extends AbstractMutableSet<E> {
        @NotNull
        public final Function2<K, V, E> h;

        /* JADX WARN: Multi-variable type inference failed */
        public c(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
            this.h = function2;
        }

        @Override // kotlin.collections.AbstractMutableSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(E e) {
            ConcurrentWeakMapKt.b();
            throw new KotlinNothingValueException();
        }

        @Override // kotlin.collections.AbstractMutableSet
        public int getSize() {
            return ConcurrentWeakMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        @NotNull
        public Iterator<E> iterator() {
            return ((a) ConcurrentWeakMap.this.core).e(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d extends Lambda implements Function2<K, V, Map.Entry<K, V>> {
        public static final d INSTANCE = new d();

        public d() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((d) obj, obj2);
        }

        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final Map.Entry<K, V> invoke(@NotNull K k, @NotNull V v) {
            return new b(k, v);
        }
    }

    /* loaded from: classes12.dex */
    public static final class e extends Lambda implements Function2<K, V, K> {
        public static final e INSTANCE = new e();

        public e() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final K invoke(@NotNull K k, @NotNull V v) {
            return k;
        }
    }

    public ConcurrentWeakMap() {
        this(false, 1, null);
    }

    public /* synthetic */ ConcurrentWeakMap(boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z);
    }

    public final void a(HashedWeakRef<?> hashedWeakRef) {
        ((a) this.core).b(hashedWeakRef);
    }

    public final void b() {
        i.decrementAndGet(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (K k : keySet()) {
            remove(k);
        }
    }

    public final synchronized V d(K k, V v) {
        V v2;
        Symbol symbol;
        a aVar = (a) this.core;
        while (true) {
            v2 = (V) a.g(aVar, k, v, null, 4, null);
            symbol = ConcurrentWeakMapKt.f14153a;
            if (v2 == symbol) {
                aVar = aVar.h();
                this.core = aVar;
            }
        }
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        return (V) ((a) this.core).c(obj);
    }

    @Override // kotlin.collections.AbstractMutableMap
    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        return new c(d.INSTANCE);
    }

    @Override // kotlin.collections.AbstractMutableMap
    @NotNull
    public Set<K> getKeys() {
        return new c(e.INSTANCE);
    }

    @Override // kotlin.collections.AbstractMutableMap
    public int getSize() {
        return this._size;
    }

    @Override // kotlin.collections.AbstractMutableMap, java.util.AbstractMap, java.util.Map
    @Nullable
    public V put(@NotNull K k, @NotNull V v) {
        Symbol symbol;
        V v2 = (V) a.g((a) this.core, k, v, null, 4, null);
        symbol = ConcurrentWeakMapKt.f14153a;
        if (v2 == symbol) {
            v2 = d(k, v);
        }
        if (v2 == null) {
            i.incrementAndGet(this);
        }
        return v2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V remove(@Nullable Object obj) {
        Symbol symbol;
        if (obj == 0) {
            return null;
        }
        V v = (V) a.g((a) this.core, obj, null, null, 4, null);
        symbol = ConcurrentWeakMapKt.f14153a;
        if (v == symbol) {
            v = d(obj, null);
        }
        if (v != null) {
            i.decrementAndGet(this);
        }
        return v;
    }

    public final void runWeakRefQueueCleaningLoopUntilInterrupted() {
        if (!(this.h != null)) {
            throw new IllegalStateException("Must be created with weakRefQueue = true".toString());
        }
        while (true) {
            try {
                Reference<? extends K> remove = this.h.remove();
                if (remove == null) {
                    break;
                }
                a((HashedWeakRef) remove);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.debug.internal.HashedWeakRef<*>");
    }

    public ConcurrentWeakMap(boolean z) {
        this._size = 0;
        this.core = new a(16);
        this.h = z ? new ReferenceQueue<>() : null;
    }
}
