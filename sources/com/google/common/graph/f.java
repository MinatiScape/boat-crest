package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.ElementOrder;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class f<N, V> implements n<N, V> {
    public static final Object e = new Object();

    /* renamed from: a  reason: collision with root package name */
    public final Map<N, Object> f10633a;
    @NullableDecl
    public final List<i<N>> b;
    public int c;
    public int d;

    /* loaded from: classes10.dex */
    public class a extends AbstractSet<N> {

        /* renamed from: com.google.common.graph.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0508a extends AbstractIterator<N> {
            public final /* synthetic */ Iterator j;
            public final /* synthetic */ Set k;

            public C0508a(a aVar, Iterator it, Set set) {
                this.j = it;
                this.k = set;
            }

            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (this.j.hasNext()) {
                    i iVar = (i) this.j.next();
                    if (this.k.add(iVar.f10635a)) {
                        return iVar.f10635a;
                    }
                }
                return endOfData();
            }
        }

        public a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        /* renamed from: a */
        public UnmodifiableIterator<N> iterator() {
            return new C0508a(this, f.this.b.iterator(), new HashSet());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return f.this.f10633a.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return f.this.f10633a.size();
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AbstractSet<N> {

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<N> {
            public final /* synthetic */ Iterator j;

            public a(b bVar, Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (this.j.hasNext()) {
                    Map.Entry entry = (Map.Entry) this.j.next();
                    if (f.p(entry.getValue())) {
                        return (N) entry.getKey();
                    }
                }
                return endOfData();
            }
        }

        /* renamed from: com.google.common.graph.f$b$b  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0509b extends AbstractIterator<N> {
            public final /* synthetic */ Iterator j;

            public C0509b(b bVar, Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (this.j.hasNext()) {
                    i iVar = (i) this.j.next();
                    if (iVar instanceof i.a) {
                        return iVar.f10635a;
                    }
                }
                return endOfData();
            }
        }

        public b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        /* renamed from: a */
        public UnmodifiableIterator<N> iterator() {
            if (f.this.b == null) {
                return new a(this, f.this.f10633a.entrySet().iterator());
            }
            return new C0509b(this, f.this.b.iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return f.p(f.this.f10633a.get(obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return f.this.c;
        }
    }

    /* loaded from: classes10.dex */
    public class c extends AbstractSet<N> {

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<N> {
            public final /* synthetic */ Iterator j;

            public a(c cVar, Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (this.j.hasNext()) {
                    Map.Entry entry = (Map.Entry) this.j.next();
                    if (f.q(entry.getValue())) {
                        return (N) entry.getKey();
                    }
                }
                return endOfData();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractIterator<N> {
            public final /* synthetic */ Iterator j;

            public b(c cVar, Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (this.j.hasNext()) {
                    i iVar = (i) this.j.next();
                    if (iVar instanceof i.b) {
                        return iVar.f10635a;
                    }
                }
                return endOfData();
            }
        }

        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        /* renamed from: a */
        public UnmodifiableIterator<N> iterator() {
            if (f.this.b == null) {
                return new a(this, f.this.f10633a.entrySet().iterator());
            }
            return new b(this, f.this.b.iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return f.q(f.this.f10633a.get(obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return f.this.d;
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Function<N, EndpointPair<N>> {
        public final /* synthetic */ Object h;

        public d(f fVar, Object obj) {
            this.h = obj;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public EndpointPair<N> apply(N n) {
            return EndpointPair.ordered(n, this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class e implements Function<N, EndpointPair<N>> {
        public final /* synthetic */ Object h;

        public e(f fVar, Object obj) {
            this.h = obj;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public EndpointPair<N> apply(N n) {
            return EndpointPair.ordered(this.h, n);
        }
    }

    /* renamed from: com.google.common.graph.f$f  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0510f implements Function<i<N>, EndpointPair<N>> {
        public final /* synthetic */ Object h;

        public C0510f(f fVar, Object obj) {
            this.h = obj;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public EndpointPair<N> apply(i<N> iVar) {
            if (iVar instanceof i.b) {
                return EndpointPair.ordered(this.h, iVar.f10635a);
            }
            return EndpointPair.ordered(iVar.f10635a, this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class g extends AbstractIterator<EndpointPair<N>> {
        public final /* synthetic */ Iterator j;
        public final /* synthetic */ AtomicBoolean k;

        public g(f fVar, Iterator it, AtomicBoolean atomicBoolean) {
            this.j = it;
            this.k = atomicBoolean;
        }

        /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.common.graph.EndpointPair<N> computeNext() {
            /*
                r3 = this;
            L0:
                java.util.Iterator r0 = r3.j
                boolean r0 = r0.hasNext()
                if (r0 == 0) goto L28
                java.util.Iterator r0 = r3.j
                java.lang.Object r0 = r0.next()
                com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                java.lang.Object r1 = r0.nodeU()
                java.lang.Object r2 = r0.nodeV()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L27
                java.util.concurrent.atomic.AtomicBoolean r1 = r3.k
                r2 = 1
                boolean r1 = r1.getAndSet(r2)
                if (r1 != 0) goto L0
            L27:
                return r0
            L28:
                java.lang.Object r0 = r3.endOfData()
                com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.f.g.computeNext():com.google.common.graph.EndpointPair");
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class h {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10634a;

        static {
            int[] iArr = new int[ElementOrder.Type.values().length];
            f10634a = iArr;
            try {
                iArr[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10634a[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class i<N> {

        /* renamed from: a  reason: collision with root package name */
        public final N f10635a;

        /* loaded from: classes10.dex */
        public static final class a<N> extends i<N> {
            public a(N n) {
                super(n);
            }

            public boolean equals(Object obj) {
                if (obj instanceof a) {
                    return this.f10635a.equals(((a) obj).f10635a);
                }
                return false;
            }

            public int hashCode() {
                return a.class.hashCode() + this.f10635a.hashCode();
            }
        }

        /* loaded from: classes10.dex */
        public static final class b<N> extends i<N> {
            public b(N n) {
                super(n);
            }

            public boolean equals(Object obj) {
                if (obj instanceof b) {
                    return this.f10635a.equals(((b) obj).f10635a);
                }
                return false;
            }

            public int hashCode() {
                return b.class.hashCode() + this.f10635a.hashCode();
            }
        }

        public i(N n) {
            this.f10635a = (N) Preconditions.checkNotNull(n);
        }
    }

    /* loaded from: classes10.dex */
    public static final class j {

        /* renamed from: a  reason: collision with root package name */
        public final Object f10636a;

        public j(Object obj) {
            this.f10636a = obj;
        }
    }

    public f(Map<N, Object> map, @NullableDecl List<i<N>> list, int i2, int i3) {
        this.f10633a = (Map) Preconditions.checkNotNull(map);
        this.b = list;
        this.c = Graphs.b(i2);
        this.d = Graphs.b(i3);
        Preconditions.checkState(i2 <= map.size() && i3 <= map.size());
    }

    public static boolean p(@NullableDecl Object obj) {
        return obj == e || (obj instanceof j);
    }

    public static boolean q(@NullableDecl Object obj) {
        return (obj == e || obj == null) ? false : true;
    }

    public static <N, V> f<N, V> r(ElementOrder<N> elementOrder) {
        ArrayList arrayList;
        int i2 = h.f10634a[elementOrder.type().ordinal()];
        if (i2 == 1) {
            arrayList = null;
        } else if (i2 == 2) {
            arrayList = new ArrayList();
        } else {
            throw new AssertionError(elementOrder.type());
        }
        return new f<>(new HashMap(4, 1.0f), arrayList, 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> f<N, V> s(N n, Iterable<EndpointPair<N>> iterable, Function<N, V> function) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(function);
        HashMap hashMap = new HashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        int i2 = 0;
        int i3 = 0;
        for (EndpointPair<N> endpointPair : iterable) {
            if (endpointPair.nodeU().equals(n) && endpointPair.nodeV().equals(n)) {
                hashMap.put(n, new j(function.apply(n)));
                builder.add((ImmutableList.Builder) new i.a(n));
                builder.add((ImmutableList.Builder) new i.b(n));
                i2++;
            } else if (endpointPair.nodeV().equals(n)) {
                N nodeU = endpointPair.nodeU();
                Object put = hashMap.put(nodeU, e);
                if (put != null) {
                    hashMap.put(nodeU, new j(put));
                }
                builder.add((ImmutableList.Builder) new i.a(nodeU));
                i2++;
            } else {
                Preconditions.checkArgument(endpointPair.nodeU().equals(n));
                N nodeV = endpointPair.nodeV();
                V apply = function.apply(nodeV);
                Object put2 = hashMap.put(nodeV, apply);
                if (put2 != null) {
                    Preconditions.checkArgument(put2 == e);
                    hashMap.put(nodeV, new j(apply));
                }
                builder.add((ImmutableList.Builder) new i.b(nodeV));
            }
            i3++;
        }
        return new f<>(hashMap, builder.build(), i2, i3);
    }

    @Override // com.google.common.graph.n
    public Set<N> a() {
        return new c();
    }

    @Override // com.google.common.graph.n
    public Set<N> b() {
        return new b();
    }

    @Override // com.google.common.graph.n
    public Set<N> c() {
        if (this.b == null) {
            return Collections.unmodifiableSet(this.f10633a.keySet());
        }
        return new a();
    }

    @Override // com.google.common.graph.n
    public V d(N n) {
        Preconditions.checkNotNull(n);
        V v = (V) this.f10633a.get(n);
        if (v == e) {
            return null;
        }
        return v instanceof j ? (V) ((j) v).f10636a : v;
    }

    @Override // com.google.common.graph.n
    public V e(Object obj) {
        Object obj2;
        Preconditions.checkNotNull(obj);
        V v = (V) this.f10633a.get(obj);
        if (v == null || v == (obj2 = e)) {
            v = null;
        } else if (v instanceof j) {
            this.f10633a.put(obj, obj2);
            v = (V) ((j) v).f10636a;
        } else {
            this.f10633a.remove(obj);
        }
        if (v != null) {
            int i2 = this.d - 1;
            this.d = i2;
            Graphs.b(i2);
            List<i<N>> list = this.b;
            if (list != null) {
                list.remove(new i.b(obj));
            }
        }
        return v;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(N r4) {
        /*
            r3 = this;
            com.google.common.base.Preconditions.checkNotNull(r4)
            java.util.Map<N, java.lang.Object> r0 = r3.f10633a
            java.lang.Object r0 = r0.get(r4)
            java.lang.Object r1 = com.google.common.graph.f.e
            r2 = 1
            if (r0 != r1) goto L15
            java.util.Map<N, java.lang.Object> r0 = r3.f10633a
            r0.remove(r4)
        L13:
            r0 = r2
            goto L26
        L15:
            boolean r1 = r0 instanceof com.google.common.graph.f.j
            if (r1 == 0) goto L25
            java.util.Map<N, java.lang.Object> r1 = r3.f10633a
            com.google.common.graph.f$j r0 = (com.google.common.graph.f.j) r0
            java.lang.Object r0 = com.google.common.graph.f.j.a(r0)
            r1.put(r4, r0)
            goto L13
        L25:
            r0 = 0
        L26:
            if (r0 == 0) goto L3c
            int r0 = r3.c
            int r0 = r0 - r2
            r3.c = r0
            com.google.common.graph.Graphs.b(r0)
            java.util.List<com.google.common.graph.f$i<N>> r0 = r3.b
            if (r0 == 0) goto L3c
            com.google.common.graph.f$i$a r1 = new com.google.common.graph.f$i$a
            r1.<init>(r4)
            r0.remove(r1)
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.f.f(java.lang.Object):void");
    }

    @Override // com.google.common.graph.n
    public Iterator<EndpointPair<N>> g(N n) {
        Iterator transform;
        Preconditions.checkNotNull(n);
        List<i<N>> list = this.b;
        if (list == null) {
            transform = Iterators.concat(Iterators.transform(b().iterator(), new d(this, n)), Iterators.transform(a().iterator(), new e(this, n)));
        } else {
            transform = Iterators.transform(list.iterator(), new C0510f(this, n));
        }
        return new g(this, transform, new AtomicBoolean(false));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    @Override // com.google.common.graph.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public V h(N r4, V r5) {
        /*
            r3 = this;
            java.util.Map<N, java.lang.Object> r0 = r3.f10633a
            java.lang.Object r0 = r0.put(r4, r5)
            r1 = 0
            if (r0 != 0) goto Lb
        L9:
            r0 = r1
            goto L2f
        Lb:
            boolean r2 = r0 instanceof com.google.common.graph.f.j
            if (r2 == 0) goto L20
            java.util.Map<N, java.lang.Object> r1 = r3.f10633a
            com.google.common.graph.f$j r2 = new com.google.common.graph.f$j
            r2.<init>(r5)
            r1.put(r4, r2)
            com.google.common.graph.f$j r0 = (com.google.common.graph.f.j) r0
            java.lang.Object r0 = com.google.common.graph.f.j.a(r0)
            goto L2f
        L20:
            java.lang.Object r2 = com.google.common.graph.f.e
            if (r0 != r2) goto L2f
            java.util.Map<N, java.lang.Object> r0 = r3.f10633a
            com.google.common.graph.f$j r2 = new com.google.common.graph.f$j
            r2.<init>(r5)
            r0.put(r4, r2)
            goto L9
        L2f:
            if (r0 != 0) goto L46
            int r5 = r3.d
            int r5 = r5 + 1
            r3.d = r5
            com.google.common.graph.Graphs.d(r5)
            java.util.List<com.google.common.graph.f$i<N>> r5 = r3.b
            if (r5 == 0) goto L46
            com.google.common.graph.f$i$b r1 = new com.google.common.graph.f$i$b
            r1.<init>(r4)
            r5.add(r1)
        L46:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.f.h(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void i(N r5, V r6) {
        /*
            r4 = this;
            java.util.Map<N, java.lang.Object> r6 = r4.f10633a
            java.lang.Object r0 = com.google.common.graph.f.e
            java.lang.Object r6 = r6.put(r5, r0)
            r1 = 0
            r2 = 1
            if (r6 != 0) goto Le
        Lc:
            r1 = r2
            goto L25
        Le:
            boolean r3 = r6 instanceof com.google.common.graph.f.j
            if (r3 == 0) goto L18
            java.util.Map<N, java.lang.Object> r0 = r4.f10633a
            r0.put(r5, r6)
            goto L25
        L18:
            if (r6 == r0) goto L25
            java.util.Map<N, java.lang.Object> r0 = r4.f10633a
            com.google.common.graph.f$j r1 = new com.google.common.graph.f$j
            r1.<init>(r6)
            r0.put(r5, r1)
            goto Lc
        L25:
            if (r1 == 0) goto L3b
            int r6 = r4.c
            int r6 = r6 + r2
            r4.c = r6
            com.google.common.graph.Graphs.d(r6)
            java.util.List<com.google.common.graph.f$i<N>> r6 = r4.b
            if (r6 == 0) goto L3b
            com.google.common.graph.f$i$a r0 = new com.google.common.graph.f$i$a
            r0.<init>(r5)
            r6.add(r0)
        L3b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.f.i(java.lang.Object, java.lang.Object):void");
    }
}
