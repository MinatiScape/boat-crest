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
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public class w<E> extends AbstractSet<E> implements Serializable {
    @VisibleForTesting
    public static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    @VisibleForTesting
    @NullableDecl
    public transient Object[] elements;
    @NullableDecl
    private transient int[] entries;
    private transient int metadata;
    private transient int size;
    @NullableDecl
    private transient Object table;

    /* loaded from: classes10.dex */
    public class a implements Iterator<E> {
        public int h;
        public int i;
        public int j = -1;

        public a() {
            this.h = w.this.metadata;
            this.i = w.this.firstEntryIndex();
        }

        public final void a() {
            if (w.this.metadata != this.h) {
                throw new ConcurrentModificationException();
            }
        }

        public void b() {
            this.h += 32;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i >= 0;
        }

        @Override // java.util.Iterator
        public E next() {
            a();
            if (hasNext()) {
                int i = this.i;
                this.j = i;
                w wVar = w.this;
                E e = (E) wVar.elements[i];
                this.i = wVar.getSuccessor(i);
                return e;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            a();
            u.e(this.j >= 0);
            b();
            w wVar = w.this;
            wVar.remove(wVar.elements[this.j]);
            this.i = w.this.adjustAfterRemove(this.i, this.j);
            this.j = -1;
        }
    }

    public w() {
        init(3);
    }

    public static <E> w<E> create() {
        return new w<>();
    }

    private Set<E> createHashFloodingResistantDelegate(int i) {
        return new LinkedHashSet(i, 1.0f);
    }

    public static <E> w<E> createWithExpectedSize(int i) {
        return new w<>(i);
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i = 0; i < readInt; i++) {
                add(objectInputStream.readObject());
            }
            return;
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("Invalid size: ");
        sb.append(readInt);
        throw new InvalidObjectException(sb.toString());
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
            int h = x.h(obj, i6);
            while (h != 0) {
                int i7 = h - 1;
                int i8 = iArr[i7];
                int b = x.b(i8, i) | i6;
                int i9 = b & i5;
                int h2 = x.h(a2, i9);
                x.i(a2, i9, h);
                iArr[i7] = x.d(b, h2, i5);
                h = x.c(i8, i);
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
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean add(@NullableDecl E e) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.add(e);
        }
        int[] iArr = this.entries;
        Object[] objArr = this.elements;
        int i = this.size;
        int i2 = i + 1;
        int d = w0.d(e);
        int hashTableMask = hashTableMask();
        int i3 = d & hashTableMask;
        int h = x.h(this.table, i3);
        if (h != 0) {
            int b = x.b(d, hashTableMask);
            int i4 = 0;
            while (true) {
                int i5 = h - 1;
                int i6 = iArr[i5];
                if (x.b(i6, hashTableMask) == b && Objects.equal(e, objArr[i5])) {
                    return false;
                }
                int c = x.c(i6, hashTableMask);
                i4++;
                if (c != 0) {
                    h = c;
                } else if (i4 >= 9) {
                    return convertToHashFloodingResistantImplementation().add(e);
                } else {
                    if (i2 > hashTableMask) {
                        hashTableMask = resizeTable(hashTableMask, x.e(hashTableMask), d, i);
                    } else {
                        iArr[i5] = x.d(i6, i2, hashTableMask);
                    }
                }
            }
        } else if (i2 > hashTableMask) {
            hashTableMask = resizeTable(hashTableMask, x.e(hashTableMask), d, i);
        } else {
            x.i(this.table, i3, i2);
        }
        resizeMeMaybe(i2);
        insertEntry(i, e, d, hashTableMask);
        this.size = i2;
        incrementModCount();
        return true;
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
        this.elements = new Object[i];
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            delegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(this.elements, 0, this.size, (Object) null);
        x.g(this.table);
        Arrays.fill(this.entries, 0, this.size, 0);
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.contains(obj);
        }
        int d = w0.d(obj);
        int hashTableMask = hashTableMask();
        int h = x.h(this.table, d & hashTableMask);
        if (h == 0) {
            return false;
        }
        int b = x.b(d, hashTableMask);
        do {
            int i = h - 1;
            int i2 = this.entries[i];
            if (x.b(i2, hashTableMask) == b && Objects.equal(obj, this.elements[i])) {
                return true;
            }
            h = x.c(i2, hashTableMask);
        } while (h != 0);
        return false;
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.add((E) this.elements[firstEntryIndex]);
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.elements = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    @VisibleForTesting
    @NullableDecl
    public Set<E> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
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

    public void insertEntry(int i, @NullableDecl E e, int i2, int i3) {
        this.entries[i] = x.d(i2, 0, i3);
        this.elements[i] = e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return size() == 0;
    }

    @VisibleForTesting
    public boolean isUsingHashFloodingResistance() {
        return delegateOrNull() != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.iterator();
        }
        return new a();
    }

    public void moveLastEntry(int i, int i2) {
        int size = size() - 1;
        if (i < size) {
            Object[] objArr = this.elements;
            Object obj = objArr[size];
            objArr[i] = obj;
            objArr[size] = null;
            int[] iArr = this.entries;
            iArr[i] = iArr[size];
            iArr[size] = 0;
            int d = w0.d(obj) & i2;
            int h = x.h(this.table, d);
            int i3 = size + 1;
            if (h == i3) {
                x.i(this.table, d, i + 1);
                return;
            }
            while (true) {
                int i4 = h - 1;
                int i5 = this.entries[i4];
                int c = x.c(i5, i2);
                if (c == i3) {
                    this.entries[i4] = x.d(i5, i + 1, i2);
                    return;
                }
                h = c;
            }
        } else {
            this.elements[i] = null;
            this.entries[i] = 0;
        }
    }

    @VisibleForTesting
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        int hashTableMask = hashTableMask();
        int f = x.f(obj, null, hashTableMask, this.table, this.entries, this.elements, null);
        if (f == -1) {
            return false;
        }
        moveLastEntry(f, hashTableMask);
        this.size--;
        incrementModCount();
        return true;
    }

    public void resizeEntries(int i) {
        this.entries = Arrays.copyOf(this.entries, i);
        this.elements = Arrays.copyOf(this.elements, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        Set<E> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        Set<E> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.toArray() : Arrays.copyOf(this.elements, this.size);
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            createHashFloodingResistantDelegate.addAll(delegateOrNull);
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

    public static <E> w<E> create(Collection<? extends E> collection) {
        w<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    public w(int i) {
        init(i);
    }

    @SafeVarargs
    public static <E> w<E> create(E... eArr) {
        w<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        if (needsAllocArrays()) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return (T[]) delegateOrNull.toArray(tArr);
        }
        return (T[]) ObjectArrays.g(this.elements, 0, this.size, tArr);
    }
}
