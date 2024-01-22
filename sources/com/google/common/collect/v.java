package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public class v<K, V> extends AbstractMap<K, V> implements Serializable {
    @VisibleForTesting
    public static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    private static final Object NOT_FOUND = new Object();
    @VisibleForTesting
    @NullableDecl
    public transient int[] entries;
    @NullableDecl
    private transient Set<Map.Entry<K, V>> entrySetView;
    @NullableDecl
    private transient Set<K> keySetView;
    @VisibleForTesting
    @NullableDecl
    public transient Object[] keys;
    private transient int metadata;
    private transient int size;
    @NullableDecl
    private transient Object table;
    @VisibleForTesting
    @NullableDecl
    public transient Object[] values;
    @NullableDecl
    private transient Collection<V> valuesView;

    /* loaded from: classes10.dex */
    public class a extends v<K, V>.e<K> {
        public a() {
            super(v.this, null);
        }

        @Override // com.google.common.collect.v.e
        public K b(int i) {
            return (K) v.this.keys[i];
        }
    }

    /* loaded from: classes10.dex */
    public class b extends v<K, V>.e<Map.Entry<K, V>> {
        public b() {
            super(v.this, null);
        }

        @Override // com.google.common.collect.v.e
        /* renamed from: d */
        public Map.Entry<K, V> b(int i) {
            return new g(i);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends v<K, V>.e<V> {
        public c() {
            super(v.this, null);
        }

        @Override // com.google.common.collect.v.e
        public V b(int i) {
            return (V) v.this.values[i];
        }
    }

    /* loaded from: classes10.dex */
    public class d extends AbstractSet<Map.Entry<K, V>> {
        public d() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            v.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            Map<K, V> delegateOrNull = v.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().contains(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                int indexOf = v.this.indexOf(entry.getKey());
                return indexOf != -1 && Objects.equal(v.this.values[indexOf], entry.getValue());
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return v.this.entrySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            Map<K, V> delegateOrNull = v.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().remove(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (v.this.needsAllocArrays()) {
                    return false;
                }
                int hashTableMask = v.this.hashTableMask();
                Object key = entry.getKey();
                Object value = entry.getValue();
                Object obj2 = v.this.table;
                v vVar = v.this;
                int f = x.f(key, value, hashTableMask, obj2, vVar.entries, vVar.keys, vVar.values);
                if (f == -1) {
                    return false;
                }
                v.this.moveLastEntry(f, hashTableMask);
                v.access$710(v.this);
                v.this.incrementModCount();
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return v.this.size();
        }
    }

    /* loaded from: classes10.dex */
    public class f extends AbstractSet<K> {
        public f() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            v.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return v.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return v.this.keySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            Map<K, V> delegateOrNull = v.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.keySet().remove(obj);
            }
            return v.this.removeHelper(obj) != v.NOT_FOUND;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return v.this.size();
        }
    }

    /* loaded from: classes10.dex */
    public final class g extends com.google.common.collect.f<K, V> {
        @NullableDecl
        public final K h;
        public int i;

        public g(int i) {
            this.h = (K) v.this.keys[i];
            this.i = i;
        }

        public final void a() {
            int i = this.i;
            if (i == -1 || i >= v.this.size() || !Objects.equal(this.h, v.this.keys[this.i])) {
                this.i = v.this.indexOf(this.h);
            }
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        @NullableDecl
        public K getKey() {
            return this.h;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        @NullableDecl
        public V getValue() {
            Map<K, V> delegateOrNull = v.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.get(this.h);
            }
            a();
            int i = this.i;
            if (i == -1) {
                return null;
            }
            return (V) v.this.values[i];
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V setValue(V v) {
            Map<K, V> delegateOrNull = v.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.put(this.h, v);
            }
            a();
            int i = this.i;
            if (i == -1) {
                v.this.put(this.h, v);
                return null;
            }
            Object[] objArr = v.this.values;
            V v2 = (V) objArr[i];
            objArr[i] = v;
            return v2;
        }
    }

    /* loaded from: classes10.dex */
    public class h extends AbstractCollection<V> {
        public h() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            v.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return v.this.valuesIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return v.this.size();
        }
    }

    public v() {
        init(3);
    }

    public static /* synthetic */ int access$710(v vVar) {
        int i = vVar.size;
        vVar.size = i - 1;
        return i;
    }

    public static <K, V> v<K, V> create() {
        return new v<>();
    }

    public static <K, V> v<K, V> createWithExpectedSize(int i) {
        return new v<>(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int indexOf(@NullableDecl Object obj) {
        if (needsAllocArrays()) {
            return -1;
        }
        int d2 = w0.d(obj);
        int hashTableMask = hashTableMask();
        int h2 = x.h(this.table, d2 & hashTableMask);
        if (h2 == 0) {
            return -1;
        }
        int b2 = x.b(d2, hashTableMask);
        do {
            int i = h2 - 1;
            int i2 = this.entries[i];
            if (x.b(i2, hashTableMask) == b2 && Objects.equal(obj, this.keys[i])) {
                return i;
            }
            h2 = x.c(i2, hashTableMask);
        } while (h2 != 0);
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i = 0; i < readInt; i++) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            }
            return;
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("Invalid size: ");
        sb.append(readInt);
        throw new InvalidObjectException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public Object removeHelper(@NullableDecl Object obj) {
        if (needsAllocArrays()) {
            return NOT_FOUND;
        }
        int hashTableMask = hashTableMask();
        int f2 = x.f(obj, null, hashTableMask, this.table, this.entries, this.keys, null);
        if (f2 == -1) {
            return NOT_FOUND;
        }
        Object obj2 = this.values[f2];
        moveLastEntry(f2, hashTableMask);
        this.size--;
        incrementModCount();
        return obj2;
    }

    private void resizeMeMaybe(int i) {
        int min;
        int length = this.entries.length;
        if (i <= length || (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) == length) {
            return;
        }
        resizeEntries(min);
    }

    @CanIgnoreReturnValue
    private int resizeTable(int i, int i2, int i3, int i4) {
        Object a2 = x.a(i2);
        int i5 = i2 - 1;
        if (i4 != 0) {
            x.i(a2, i3 & i5, i4 + 1);
        }
        Object obj = this.table;
        int[] iArr = this.entries;
        for (int i6 = 0; i6 <= i; i6++) {
            int h2 = x.h(obj, i6);
            while (h2 != 0) {
                int i7 = h2 - 1;
                int i8 = iArr[i7];
                int b2 = x.b(i8, i) | i6;
                int i9 = b2 & i5;
                int h3 = x.h(a2, i9);
                x.i(a2, i9, h2);
                iArr[i7] = x.d(b2, h3, i5);
                h2 = x.c(i8, i);
            }
        }
        this.table = a2;
        setHashTableMask(i5);
        return i5;
    }

    private void setHashTableMask(int i) {
        this.metadata = x.d(this.metadata, 32 - Integer.numberOfLeadingZeros(i), 31);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator<Map.Entry<K, V>> entrySetIterator = entrySetIterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry<K, V> next = entrySetIterator.next();
            objectOutputStream.writeObject(next.getKey());
            objectOutputStream.writeObject(next.getValue());
        }
    }

    public void accessEntry(int i) {
    }

    public int adjustAfterRemove(int i, int i2) {
        return i - 1;
    }

    @CanIgnoreReturnValue
    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int i = this.metadata;
        int j = x.j(i);
        this.table = x.a(j);
        setHashTableMask(j - 1);
        this.entries = new int[i];
        this.keys = new Object[i];
        this.values = new Object[i];
        return i;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            delegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        x.g(this.table);
        Arrays.fill(this.entries, 0, this.size, 0);
        this.size = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsKey(obj);
        }
        return indexOf(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsValue(obj);
        }
        for (int i = 0; i < this.size; i++) {
            if (Objects.equal(obj, this.values[i])) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.put((K) this.keys[firstEntryIndex], (V) this.values[firstEntryIndex]);
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.keys = null;
        this.values = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    public Set<Map.Entry<K, V>> createEntrySet() {
        return new d();
    }

    public Map<K, V> createHashFloodingResistantDelegate(int i) {
        return new LinkedHashMap(i, 1.0f);
    }

    public Set<K> createKeySet() {
        return new f();
    }

    public Collection<V> createValues() {
        return new h();
    }

    @VisibleForTesting
    @NullableDecl
    public Map<K, V> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set == null) {
            Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
            this.entrySetView = createEntrySet;
            return createEntrySet;
        }
        return set;
    }

    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.entrySet().iterator();
        }
        return new b();
    }

    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.get(obj);
        }
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        accessEntry(indexOf);
        return (V) this.values[indexOf];
    }

    public int getSuccessor(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
    }

    public void incrementModCount() {
        this.metadata += 32;
    }

    public void init(int i) {
        Preconditions.checkArgument(i >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(i, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    public void insertEntry(int i, @NullableDecl K k, @NullableDecl V v, int i2, int i3) {
        this.entries[i] = x.d(i2, 0, i3);
        this.keys[i] = k;
        this.values[i] = v;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set == null) {
            Set<K> createKeySet = createKeySet();
            this.keySetView = createKeySet;
            return createKeySet;
        }
        return set;
    }

    public Iterator<K> keySetIterator() {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.keySet().iterator();
        }
        return new a();
    }

    public void moveLastEntry(int i, int i2) {
        int size = size() - 1;
        if (i < size) {
            Object[] objArr = this.keys;
            Object obj = objArr[size];
            objArr[i] = obj;
            Object[] objArr2 = this.values;
            objArr2[i] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            int[] iArr = this.entries;
            iArr[i] = iArr[size];
            iArr[size] = 0;
            int d2 = w0.d(obj) & i2;
            int h2 = x.h(this.table, d2);
            int i3 = size + 1;
            if (h2 == i3) {
                x.i(this.table, d2, i + 1);
                return;
            }
            while (true) {
                int i4 = h2 - 1;
                int i5 = this.entries[i4];
                int c2 = x.c(i5, i2);
                if (c2 == i3) {
                    this.entries[i4] = x.d(i5, i + 1, i2);
                    return;
                }
                h2 = c2;
            }
        } else {
            this.keys[i] = null;
            this.values[i] = null;
            this.entries[i] = 0;
        }
    }

    @VisibleForTesting
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V put(@NullableDecl K k, @NullableDecl V v) {
        int resizeTable;
        int i;
        if (needsAllocArrays()) {
            allocArrays();
        }
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.put(k, v);
        }
        int[] iArr = this.entries;
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
        int i2 = this.size;
        int i3 = i2 + 1;
        int d2 = w0.d(k);
        int hashTableMask = hashTableMask();
        int i4 = d2 & hashTableMask;
        int h2 = x.h(this.table, i4);
        if (h2 != 0) {
            int b2 = x.b(d2, hashTableMask);
            int i5 = 0;
            while (true) {
                int i6 = h2 - 1;
                int i7 = iArr[i6];
                if (x.b(i7, hashTableMask) == b2 && Objects.equal(k, objArr[i6])) {
                    V v2 = (V) objArr2[i6];
                    objArr2[i6] = v;
                    accessEntry(i6);
                    return v2;
                }
                int c2 = x.c(i7, hashTableMask);
                i5++;
                if (c2 != 0) {
                    h2 = c2;
                } else if (i5 >= 9) {
                    return convertToHashFloodingResistantImplementation().put(k, v);
                } else {
                    if (i3 > hashTableMask) {
                        resizeTable = resizeTable(hashTableMask, x.e(hashTableMask), d2, i2);
                    } else {
                        iArr[i6] = x.d(i7, i3, hashTableMask);
                    }
                }
            }
        } else if (i3 > hashTableMask) {
            resizeTable = resizeTable(hashTableMask, x.e(hashTableMask), d2, i2);
            i = resizeTable;
        } else {
            x.i(this.table, i4, i3);
            i = hashTableMask;
        }
        resizeMeMaybe(i3);
        insertEntry(i2, k, v, d2, i);
        this.size = i3;
        incrementModCount();
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V remove(@NullableDecl Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        V v = (V) removeHelper(obj);
        if (v == NOT_FOUND) {
            return null;
        }
        return v;
    }

    public void resizeEntries(int i) {
        this.entries = Arrays.copyOf(this.entries, i);
        this.keys = Arrays.copyOf(this.keys, i);
        this.values = Arrays.copyOf(this.values, i);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        Map<K, V> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            Map<K, V> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            createHashFloodingResistantDelegate.putAll(delegateOrNull);
            this.table = createHashFloodingResistantDelegate;
            return;
        }
        int i = this.size;
        if (i < this.entries.length) {
            resizeEntries(i);
        }
        int j = x.j(i);
        int hashTableMask = hashTableMask();
        if (j < hashTableMask) {
            resizeTable(hashTableMask, j, 0, 0);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection == null) {
            Collection<V> createValues = createValues();
            this.valuesView = createValues;
            return createValues;
        }
        return collection;
    }

    public Iterator<V> valuesIterator() {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.values().iterator();
        }
        return new c();
    }

    public v(int i) {
        init(i);
    }

    /* loaded from: classes10.dex */
    public abstract class e<T> implements Iterator<T> {
        public int h;
        public int i;
        public int j;

        public e() {
            this.h = v.this.metadata;
            this.i = v.this.firstEntryIndex();
            this.j = -1;
        }

        public final void a() {
            if (v.this.metadata != this.h) {
                throw new ConcurrentModificationException();
            }
        }

        public abstract T b(int i);

        public void c() {
            this.h += 32;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            a();
            if (hasNext()) {
                int i = this.i;
                this.j = i;
                T b = b(i);
                this.i = v.this.getSuccessor(this.i);
                return b;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            a();
            u.e(this.j >= 0);
            c();
            v vVar = v.this;
            vVar.remove(vVar.keys[this.j]);
            this.i = v.this.adjustAfterRemove(this.i, this.j);
            this.j = -1;
        }

        public /* synthetic */ e(v vVar, a aVar) {
            this();
        }
    }
}
