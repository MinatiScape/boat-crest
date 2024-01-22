package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes10.dex */
public class LinkedListMultimap<K, V> extends com.google.common.collect.g<K, V> implements ListMultimap<K, V>, Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    @NullableDecl
    private transient g<K, V> head;
    private transient Map<K, f<K, V>> keyToKeyList;
    private transient int modCount;
    private transient int size;
    @NullableDecl
    private transient g<K, V> tail;

    /* loaded from: classes10.dex */
    public class a extends AbstractSequentialList<V> {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<V> listIterator(int i) {
            return new i(this.h, i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            f fVar = (f) LinkedListMultimap.this.keyToKeyList.get(this.h);
            if (fVar == null) {
                return 0;
            }
            return fVar.c;
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AbstractSequentialList<Map.Entry<K, V>> {
        public b() {
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<Map.Entry<K, V>> listIterator(int i) {
            return new h(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return LinkedListMultimap.this.size;
        }
    }

    /* loaded from: classes10.dex */
    public class c extends Sets.k<K> {
        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedListMultimap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new e(LinkedListMultimap.this, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return !LinkedListMultimap.this.removeAll(obj).isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedListMultimap.this.keyToKeyList.size();
        }
    }

    /* loaded from: classes10.dex */
    public class d extends AbstractSequentialList<V> {

        /* loaded from: classes10.dex */
        public class a extends r2<Map.Entry<K, V>, V> {
            public final /* synthetic */ h i;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(d dVar, ListIterator listIterator, h hVar) {
                super(listIterator);
                this.i = hVar;
            }

            @Override // com.google.common.collect.q2
            /* renamed from: c */
            public V a(Map.Entry<K, V> entry) {
                return entry.getValue();
            }

            @Override // com.google.common.collect.r2, java.util.ListIterator
            public void set(V v) {
                this.i.f(v);
            }
        }

        public d() {
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<V> listIterator(int i) {
            h hVar = new h(i);
            return new a(this, hVar, hVar);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return LinkedListMultimap.this.size;
        }
    }

    /* loaded from: classes10.dex */
    public static class f<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public g<K, V> f10563a;
        public g<K, V> b;
        public int c;

        public f(g<K, V> gVar) {
            this.f10563a = gVar;
            this.b = gVar;
            gVar.m = null;
            gVar.l = null;
            this.c = 1;
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<K, V> extends com.google.common.collect.f<K, V> {
        @NullableDecl
        public final K h;
        @NullableDecl
        public V i;
        @NullableDecl
        public g<K, V> j;
        @NullableDecl
        public g<K, V> k;
        @NullableDecl
        public g<K, V> l;
        @NullableDecl
        public g<K, V> m;

        public g(@NullableDecl K k, @NullableDecl V v) {
            this.h = k;
            this.i = v;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public K getKey() {
            return this.h;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V getValue() {
            return this.i;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V setValue(@NullableDecl V v) {
            V v2 = this.i;
            this.i = v;
            return v2;
        }
    }

    /* loaded from: classes10.dex */
    public class h implements ListIterator<Map.Entry<K, V>> {
        public int h;
        @NullableDecl
        public g<K, V> i;
        @NullableDecl
        public g<K, V> j;
        @NullableDecl
        public g<K, V> k;
        public int l;

        public h(int i) {
            this.l = LinkedListMultimap.this.modCount;
            int size = LinkedListMultimap.this.size();
            Preconditions.checkPositionIndex(i, size);
            if (i >= size / 2) {
                this.k = LinkedListMultimap.this.tail;
                this.h = size;
                while (true) {
                    int i2 = i + 1;
                    if (i >= size) {
                        break;
                    }
                    previous();
                    i = i2;
                }
            } else {
                this.i = LinkedListMultimap.this.head;
                while (true) {
                    int i3 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    next();
                    i = i3;
                }
            }
            this.j = null;
        }

        @Override // java.util.ListIterator
        /* renamed from: a */
        public void add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public final void b() {
            if (LinkedListMultimap.this.modCount != this.l) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        @CanIgnoreReturnValue
        /* renamed from: c */
        public g<K, V> next() {
            b();
            LinkedListMultimap.checkElement(this.i);
            g<K, V> gVar = this.i;
            this.j = gVar;
            this.k = gVar;
            this.i = gVar.j;
            this.h++;
            return gVar;
        }

        @Override // java.util.ListIterator
        @CanIgnoreReturnValue
        /* renamed from: d */
        public g<K, V> previous() {
            b();
            LinkedListMultimap.checkElement(this.k);
            g<K, V> gVar = this.k;
            this.j = gVar;
            this.i = gVar;
            this.k = gVar.k;
            this.h--;
            return gVar;
        }

        @Override // java.util.ListIterator
        /* renamed from: e */
        public void set(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public void f(V v) {
            Preconditions.checkState(this.j != null);
            this.j.i = v;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            b();
            return this.i != null;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            b();
            return this.k != null;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.h;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.h - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            b();
            u.e(this.j != null);
            g<K, V> gVar = this.j;
            if (gVar != this.i) {
                this.k = gVar.k;
                this.h--;
            } else {
                this.i = gVar.j;
            }
            LinkedListMultimap.this.removeNode(gVar);
            this.j = null;
            this.l = LinkedListMultimap.this.modCount;
        }
    }

    public LinkedListMultimap() {
        this(12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CanIgnoreReturnValue
    public g<K, V> addNode(@NullableDecl K k, @NullableDecl V v, @NullableDecl g<K, V> gVar) {
        g<K, V> gVar2 = new g<>(k, v);
        if (this.head == null) {
            this.tail = gVar2;
            this.head = gVar2;
            this.keyToKeyList.put(k, new f<>(gVar2));
            this.modCount++;
        } else if (gVar == null) {
            g<K, V> gVar3 = this.tail;
            gVar3.j = gVar2;
            gVar2.k = gVar3;
            this.tail = gVar2;
            f<K, V> fVar = this.keyToKeyList.get(k);
            if (fVar == null) {
                this.keyToKeyList.put(k, new f<>(gVar2));
                this.modCount++;
            } else {
                fVar.c++;
                g<K, V> gVar4 = fVar.b;
                gVar4.l = gVar2;
                gVar2.m = gVar4;
                fVar.b = gVar2;
            }
        } else {
            this.keyToKeyList.get(k).c++;
            gVar2.k = gVar.k;
            gVar2.m = gVar.m;
            gVar2.j = gVar;
            gVar2.l = gVar;
            g<K, V> gVar5 = gVar.m;
            if (gVar5 == null) {
                this.keyToKeyList.get(k).f10563a = gVar2;
            } else {
                gVar5.l = gVar2;
            }
            g<K, V> gVar6 = gVar.k;
            if (gVar6 == null) {
                this.head = gVar2;
            } else {
                gVar6.j = gVar2;
            }
            gVar.k = gVar2;
            gVar.m = gVar2;
        }
        this.size++;
        return gVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkElement(@NullableDecl Object obj) {
        if (obj == null) {
            throw new NoSuchElementException();
        }
    }

    public static <K, V> LinkedListMultimap<K, V> create() {
        return new LinkedListMultimap<>();
    }

    private List<V> getCopy(@NullableDecl Object obj) {
        return Collections.unmodifiableList(Lists.newArrayList(new i(obj)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.keyToKeyList = y.create();
        int readInt = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAllNodes(@NullableDecl Object obj) {
        Iterators.c(new i(obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeNode(g<K, V> gVar) {
        g<K, V> gVar2 = gVar.k;
        if (gVar2 != null) {
            gVar2.j = gVar.j;
        } else {
            this.head = gVar.j;
        }
        g<K, V> gVar3 = gVar.j;
        if (gVar3 != null) {
            gVar3.k = gVar2;
        } else {
            this.tail = gVar2;
        }
        if (gVar.m == null && gVar.l == null) {
            this.keyToKeyList.remove(gVar.h).c = 0;
            this.modCount++;
        } else {
            f<K, V> fVar = this.keyToKeyList.get(gVar.h);
            fVar.c--;
            g<K, V> gVar4 = gVar.m;
            if (gVar4 == null) {
                fVar.f10563a = gVar.l;
            } else {
                gVar4.l = gVar.l;
            }
            g<K, V> gVar5 = gVar.l;
            if (gVar5 == null) {
                fVar.b = gVar4;
            } else {
                gVar5.m = gVar4;
            }
        }
        this.size--;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        this.head = null;
        this.tail = null;
        this.keyToKeyList.clear();
        this.size = 0;
        this.modCount++;
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return this.keyToKeyList.containsKey(obj);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public boolean containsValue(@NullableDecl Object obj) {
        return values().contains(obj);
    }

    @Override // com.google.common.collect.g
    public Map<K, Collection<V>> createAsMap() {
        return new Multimaps.a(this);
    }

    @Override // com.google.common.collect.g
    public Set<K> createKeySet() {
        return new c();
    }

    @Override // com.google.common.collect.g
    public Multiset<K> createKeys() {
        return new Multimaps.g(this);
    }

    @Override // com.google.common.collect.g
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Collection get(@NullableDecl Object obj) {
        return get((LinkedListMultimap<K, V>) obj);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean put(@NullableDecl K k, @NullableDecl V v) {
        addNode(k, v, null);
        return true;
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean putAll(Multimap multimap) {
        return super.putAll(multimap);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.remove(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Collection replaceValues(@NullableDecl Object obj, Iterable iterable) {
        return replaceValues((LinkedListMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.g
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    private LinkedListMultimap(int i2) {
        this.keyToKeyList = r1.c(i2);
    }

    public static <K, V> LinkedListMultimap<K, V> create(int i2) {
        return new LinkedListMultimap<>(i2);
    }

    @Override // com.google.common.collect.g
    public List<Map.Entry<K, V>> createEntries() {
        return new b();
    }

    @Override // com.google.common.collect.g
    public List<V> createValues() {
        return new d();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public List<Map.Entry<K, V>> entries() {
        return (List) super.entries();
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> get(@NullableDecl K k) {
        return new a(k);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean putAll(@NullableDecl Object obj, Iterable iterable) {
        return super.putAll(obj, iterable);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public List<V> removeAll(@NullableDecl Object obj) {
        List<V> copy = getCopy(obj);
        removeAllNodes(obj);
        return copy;
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public List<V> replaceValues(@NullableDecl K k, Iterable<? extends V> iterable) {
        List<V> copy = getCopy(k);
        i iVar = new i(k);
        Iterator<? extends V> it = iterable.iterator();
        while (iVar.hasNext() && it.hasNext()) {
            iVar.next();
            iVar.set(it.next());
        }
        while (iVar.hasNext()) {
            iVar.next();
            iVar.remove();
        }
        while (it.hasNext()) {
            iVar.add(it.next());
        }
        return copy;
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public List<V> values() {
        return (List) super.values();
    }

    public static <K, V> LinkedListMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new LinkedListMultimap<>(multimap);
    }

    /* loaded from: classes10.dex */
    public class e implements Iterator<K> {
        public final Set<K> h;
        public g<K, V> i;
        @NullableDecl
        public g<K, V> j;
        public int k;

        public e() {
            this.h = Sets.newHashSetWithExpectedSize(LinkedListMultimap.this.keySet().size());
            this.i = LinkedListMultimap.this.head;
            this.k = LinkedListMultimap.this.modCount;
        }

        public final void a() {
            if (LinkedListMultimap.this.modCount != this.k) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            return this.i != null;
        }

        @Override // java.util.Iterator
        public K next() {
            g<K, V> gVar;
            a();
            LinkedListMultimap.checkElement(this.i);
            g<K, V> gVar2 = this.i;
            this.j = gVar2;
            this.h.add(gVar2.h);
            do {
                gVar = this.i.j;
                this.i = gVar;
                if (gVar == null) {
                    break;
                }
            } while (!this.h.add(gVar.h));
            return this.j.h;
        }

        @Override // java.util.Iterator
        public void remove() {
            a();
            u.e(this.j != null);
            LinkedListMultimap.this.removeAllNodes(this.j.h);
            this.j = null;
            this.k = LinkedListMultimap.this.modCount;
        }

        public /* synthetic */ e(LinkedListMultimap linkedListMultimap, a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public class i implements ListIterator<V> {
        @NullableDecl
        public final Object h;
        public int i;
        @NullableDecl
        public g<K, V> j;
        @NullableDecl
        public g<K, V> k;
        @NullableDecl
        public g<K, V> l;

        public i(@NullableDecl Object obj) {
            this.h = obj;
            f fVar = (f) LinkedListMultimap.this.keyToKeyList.get(obj);
            this.j = fVar == null ? null : fVar.f10563a;
        }

        @Override // java.util.ListIterator
        public void add(V v) {
            this.l = LinkedListMultimap.this.addNode(this.h, v, this.j);
            this.i++;
            this.k = null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.j != null;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.l != null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        @CanIgnoreReturnValue
        public V next() {
            LinkedListMultimap.checkElement(this.j);
            g<K, V> gVar = this.j;
            this.k = gVar;
            this.l = gVar;
            this.j = gVar.l;
            this.i++;
            return gVar.i;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.i;
        }

        @Override // java.util.ListIterator
        @CanIgnoreReturnValue
        public V previous() {
            LinkedListMultimap.checkElement(this.l);
            g<K, V> gVar = this.l;
            this.k = gVar;
            this.j = gVar;
            this.l = gVar.m;
            this.i--;
            return gVar.i;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.i - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            u.e(this.k != null);
            g<K, V> gVar = this.k;
            if (gVar != this.j) {
                this.l = gVar.m;
                this.i--;
            } else {
                this.j = gVar.l;
            }
            LinkedListMultimap.this.removeNode(gVar);
            this.k = null;
        }

        @Override // java.util.ListIterator
        public void set(V v) {
            Preconditions.checkState(this.k != null);
            this.k.i = v;
        }

        public i(@NullableDecl Object obj, int i) {
            f fVar = (f) LinkedListMultimap.this.keyToKeyList.get(obj);
            int i2 = fVar == null ? 0 : fVar.c;
            Preconditions.checkPositionIndex(i, i2);
            if (i >= i2 / 2) {
                this.l = fVar == null ? null : fVar.b;
                this.i = i2;
                while (true) {
                    int i3 = i + 1;
                    if (i >= i2) {
                        break;
                    }
                    previous();
                    i = i3;
                }
            } else {
                this.j = fVar == null ? null : fVar.f10563a;
                while (true) {
                    int i4 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    next();
                    i = i4;
                }
            }
            this.h = obj;
            this.k = null;
        }
    }

    private LinkedListMultimap(Multimap<? extends K, ? extends V> multimap) {
        this(multimap.keySet().size());
        putAll(multimap);
    }
}
