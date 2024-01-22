package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes10.dex */
public final class LinkedHashMultimap<K, V> extends k1<K, V> {
    private static final int DEFAULT_KEY_CAPACITY = 16;
    private static final int DEFAULT_VALUE_SET_CAPACITY = 2;
    @VisibleForTesting
    public static final double VALUE_SET_LOAD_FACTOR = 1.0d;
    @GwtIncompatible
    private static final long serialVersionUID = 1;
    private transient b<K, V> multimapHeaderEntry;
    @VisibleForTesting
    public transient int valueSetCapacity;

    /* loaded from: classes10.dex */
    public class a implements Iterator<Map.Entry<K, V>> {
        public b<K, V> h;
        @NullableDecl
        public b<K, V> i;

        public a() {
            this.h = LinkedHashMultimap.this.multimapHeaderEntry.successorInMultimap;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (hasNext()) {
                b<K, V> bVar = this.h;
                this.i = bVar;
                this.h = bVar.successorInMultimap;
                return bVar;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h != LinkedHashMultimap.this.multimapHeaderEntry;
        }

        @Override // java.util.Iterator
        public void remove() {
            u.e(this.i != null);
            LinkedHashMultimap.this.remove(this.i.getKey(), this.i.getValue());
            this.i = null;
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static final class b<K, V> extends y0<K, V> implements d<K, V> {
        @NullableDecl
        public b<K, V> nextInValueBucket;
        @NullableDecl
        public b<K, V> predecessorInMultimap;
        @NullableDecl
        public d<K, V> predecessorInValueSet;
        public final int smearedValueHash;
        @NullableDecl
        public b<K, V> successorInMultimap;
        @NullableDecl
        public d<K, V> successorInValueSet;

        public b(@NullableDecl K k, @NullableDecl V v, int i, @NullableDecl b<K, V> bVar) {
            super(k, v);
            this.smearedValueHash = i;
            this.nextInValueBucket = bVar;
        }

        public b<K, V> getPredecessorInMultimap() {
            return this.predecessorInMultimap;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public d<K, V> getPredecessorInValueSet() {
            return this.predecessorInValueSet;
        }

        public b<K, V> getSuccessorInMultimap() {
            return this.successorInMultimap;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public d<K, V> getSuccessorInValueSet() {
            return this.successorInValueSet;
        }

        public boolean matchesValue(@NullableDecl Object obj, int i) {
            return this.smearedValueHash == i && Objects.equal(getValue(), obj);
        }

        public void setPredecessorInMultimap(b<K, V> bVar) {
            this.predecessorInMultimap = bVar;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public void setPredecessorInValueSet(d<K, V> dVar) {
            this.predecessorInValueSet = dVar;
        }

        public void setSuccessorInMultimap(b<K, V> bVar) {
            this.successorInMultimap = bVar;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public void setSuccessorInValueSet(d<K, V> dVar) {
            this.successorInValueSet = dVar;
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public final class c extends Sets.k<V> implements d<K, V> {
        public final K h;
        @VisibleForTesting
        public b<K, V>[] i;
        public int j = 0;
        public int k = 0;
        public d<K, V> l = this;
        public d<K, V> m = this;

        /* loaded from: classes10.dex */
        public class a implements Iterator<V> {
            public d<K, V> h;
            @NullableDecl
            public b<K, V> i;
            public int j;

            public a() {
                this.h = c.this.l;
                this.j = c.this.k;
            }

            public final void a() {
                if (c.this.k != this.j) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                a();
                return this.h != c.this;
            }

            @Override // java.util.Iterator
            public V next() {
                if (hasNext()) {
                    b<K, V> bVar = (b) this.h;
                    V value = bVar.getValue();
                    this.i = bVar;
                    this.h = bVar.getSuccessorInValueSet();
                    return value;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                a();
                u.e(this.i != null);
                c.this.remove(this.i.getValue());
                this.j = c.this.k;
                this.i = null;
            }
        }

        public c(K k, int i) {
            this.h = k;
            this.i = new b[w0.a(i, 1.0d)];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(@NullableDecl V v) {
            int d = w0.d(v);
            int c = c() & d;
            b<K, V> bVar = this.i[c];
            for (b<K, V> bVar2 = bVar; bVar2 != null; bVar2 = bVar2.nextInValueBucket) {
                if (bVar2.matchesValue(v, d)) {
                    return false;
                }
            }
            b<K, V> bVar3 = new b<>(this.h, v, d, bVar);
            LinkedHashMultimap.succeedsInValueSet(this.m, bVar3);
            LinkedHashMultimap.succeedsInValueSet(bVar3, this);
            LinkedHashMultimap.succeedsInMultimap(LinkedHashMultimap.this.multimapHeaderEntry.getPredecessorInMultimap(), bVar3);
            LinkedHashMultimap.succeedsInMultimap(bVar3, LinkedHashMultimap.this.multimapHeaderEntry);
            this.i[c] = bVar3;
            this.j++;
            this.k++;
            d();
            return true;
        }

        public final int c() {
            return this.i.length - 1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Arrays.fill(this.i, (Object) null);
            this.j = 0;
            for (d<K, V> dVar = this.l; dVar != this; dVar = dVar.getSuccessorInValueSet()) {
                LinkedHashMultimap.deleteFromMultimap((b) dVar);
            }
            LinkedHashMultimap.succeedsInValueSet(this, this);
            this.k++;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            int d = w0.d(obj);
            for (b<K, V> bVar = this.i[c() & d]; bVar != null; bVar = bVar.nextInValueBucket) {
                if (bVar.matchesValue(obj, d)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void d() {
            if (w0.b(this.j, this.i.length, 1.0d)) {
                int length = this.i.length * 2;
                b<K, V>[] bVarArr = new b[length];
                this.i = bVarArr;
                int i = length - 1;
                for (d dVar = (d<K, V>) this.l; dVar != this; dVar = (d<K, V>) dVar.getSuccessorInValueSet()) {
                    b<K, V> bVar = (b) dVar;
                    int i2 = bVar.smearedValueHash & i;
                    bVar.nextInValueBucket = bVarArr[i2];
                    bVarArr[i2] = bVar;
                }
            }
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public d<K, V> getPredecessorInValueSet() {
            return this.m;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public d<K, V> getSuccessorInValueSet() {
            return this.l;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        public boolean remove(@NullableDecl Object obj) {
            int d = w0.d(obj);
            int c = c() & d;
            b<K, V> bVar = null;
            for (b<K, V> bVar2 = this.i[c]; bVar2 != null; bVar2 = bVar2.nextInValueBucket) {
                if (bVar2.matchesValue(obj, d)) {
                    if (bVar == null) {
                        this.i[c] = bVar2.nextInValueBucket;
                    } else {
                        bVar.nextInValueBucket = bVar2.nextInValueBucket;
                    }
                    LinkedHashMultimap.deleteFromValueSet(bVar2);
                    LinkedHashMultimap.deleteFromMultimap(bVar2);
                    this.j--;
                    this.k++;
                    return true;
                }
                bVar = bVar2;
            }
            return false;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public void setPredecessorInValueSet(d<K, V> dVar) {
            this.m = dVar;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.d
        public void setSuccessorInValueSet(d<K, V> dVar) {
            this.l = dVar;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.j;
        }
    }

    /* loaded from: classes10.dex */
    public interface d<K, V> {
        d<K, V> getPredecessorInValueSet();

        d<K, V> getSuccessorInValueSet();

        void setPredecessorInValueSet(d<K, V> dVar);

        void setSuccessorInValueSet(d<K, V> dVar);
    }

    private LinkedHashMultimap(int i, int i2) {
        super(r1.e(i));
        this.valueSetCapacity = 2;
        u.b(i2, "expectedValuesPerKey");
        this.valueSetCapacity = i2;
        b<K, V> bVar = new b<>(null, null, 0, null);
        this.multimapHeaderEntry = bVar;
        succeedsInMultimap(bVar, bVar);
    }

    public static <K, V> LinkedHashMultimap<K, V> create() {
        return new LinkedHashMultimap<>(16, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> void deleteFromMultimap(b<K, V> bVar) {
        succeedsInMultimap(bVar.getPredecessorInMultimap(), bVar.getSuccessorInMultimap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> void deleteFromValueSet(d<K, V> dVar) {
        succeedsInValueSet(dVar.getPredecessorInValueSet(), dVar.getSuccessorInValueSet());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        b<K, V> bVar = new b<>(null, null, 0, null);
        this.multimapHeaderEntry = bVar;
        succeedsInMultimap(bVar, bVar);
        this.valueSetCapacity = 2;
        int readInt = objectInputStream.readInt();
        Map e = r1.e(12);
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            e.put(readObject, createCollection(readObject));
        }
        int readInt2 = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            ((Collection) e.get(objectInputStream.readObject())).add(objectInputStream.readObject());
        }
        setMap(e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> void succeedsInMultimap(b<K, V> bVar, b<K, V> bVar2) {
        bVar.setSuccessorInMultimap(bVar2);
        bVar2.setPredecessorInMultimap(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> void succeedsInValueSet(d<K, V> dVar, d<K, V> dVar2) {
        dVar.setSuccessorInValueSet(dVar2);
        dVar2.setPredecessorInValueSet(dVar);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(keySet().size());
        for (K k : keySet()) {
            objectOutputStream.writeObject(k);
        }
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @Override // com.google.common.collect.k, com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.d, com.google.common.collect.Multimap
    public void clear() {
        super.clear();
        b<K, V> bVar = this.multimapHeaderEntry;
        succeedsInMultimap(bVar, bVar);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsKey(@NullableDecl Object obj) {
        return super.containsKey(obj);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsValue(@NullableDecl Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.g
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new a();
    }

    @Override // com.google.common.collect.k, com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.k, com.google.common.collect.d, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Set get(@NullableDecl Object obj) {
        return super.get((LinkedHashMultimap<K, V>) obj);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public Set<K> keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.k, com.google.common.collect.d, com.google.common.collect.g, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean put(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.put(obj, obj2);
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

    @Override // com.google.common.collect.k, com.google.common.collect.d, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Set removeAll(@NullableDecl Object obj) {
        return super.removeAll(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.k, com.google.common.collect.d, com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Collection replaceValues(@NullableDecl Object obj, Iterable iterable) {
        return replaceValues((LinkedHashMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.google.common.collect.g
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.d, com.google.common.collect.g
    public Iterator<V> valueIterator() {
        return Maps.Q(entryIterator());
    }

    @Override // com.google.common.collect.d, com.google.common.collect.g, com.google.common.collect.Multimap
    public Collection<V> values() {
        return super.values();
    }

    public static <K, V> LinkedHashMultimap<K, V> create(int i, int i2) {
        return new LinkedHashMultimap<>(Maps.k(i), Maps.k(i2));
    }

    @Override // com.google.common.collect.k, com.google.common.collect.d
    public Set<V> createCollection() {
        return r1.f(this.valueSetCapacity);
    }

    @Override // com.google.common.collect.k, com.google.common.collect.d, com.google.common.collect.g, com.google.common.collect.Multimap
    public Set<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean putAll(@NullableDecl Object obj, Iterable iterable) {
        return super.putAll(obj, iterable);
    }

    @Override // com.google.common.collect.k, com.google.common.collect.d, com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public Set<V> replaceValues(@NullableDecl K k, Iterable<? extends V> iterable) {
        return super.replaceValues((LinkedHashMultimap<K, V>) k, (Iterable) iterable);
    }

    @Override // com.google.common.collect.d
    public Collection<V> createCollection(K k) {
        return new c(k, this.valueSetCapacity);
    }

    public static <K, V> LinkedHashMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        LinkedHashMultimap<K, V> create = create(multimap.keySet().size(), 2);
        create.putAll(multimap);
        return create;
    }
}
