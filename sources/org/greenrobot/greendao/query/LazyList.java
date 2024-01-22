package org.greenrobot.greendao.query;

import android.database.Cursor;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.InternalQueryDaoAccess;
/* loaded from: classes13.dex */
public class LazyList<E> implements List<E>, Closeable {
    public final InternalQueryDaoAccess<E> h;
    public final Cursor i;
    public final List<E> j;
    public final int k;
    public final ReentrantLock l;
    public volatile int m;

    /* loaded from: classes13.dex */
    public class LazyIterator implements CloseableListIterator<E> {
        public int h;
        public final boolean i;

        public LazyIterator(int i, boolean z) {
            this.h = i;
            this.i = z;
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            LazyList.this.close();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.h < LazyList.this.k;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.h > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            if (this.h < LazyList.this.k) {
                E e = (E) LazyList.this.get(this.h);
                int i = this.h + 1;
                this.h = i;
                if (i == LazyList.this.k && this.i) {
                    close();
                }
                return e;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.h;
        }

        @Override // java.util.ListIterator
        public E previous() {
            int i = this.h;
            if (i > 0) {
                int i2 = i - 1;
                this.h = i2;
                return (E) LazyList.this.get(i2);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.h - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }

    public LazyList(InternalQueryDaoAccess<E> internalQueryDaoAccess, Cursor cursor, boolean z) {
        this.i = cursor;
        this.h = internalQueryDaoAccess;
        int count = cursor.getCount();
        this.k = count;
        if (z) {
            this.j = new ArrayList(count);
            for (int i = 0; i < this.k; i++) {
                this.j.add(null);
            }
        } else {
            this.j = null;
        }
        if (this.k == 0) {
            cursor.close();
        }
        this.l = new ReentrantLock();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public void checkCached() {
        if (this.j == null) {
            throw new DaoException("This operation only works with cached lazy lists");
        }
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.i.close();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        loadRemaining();
        return this.j.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        loadRemaining();
        return this.j.containsAll(collection);
    }

    @Override // java.util.List
    public E get(int i) {
        List<E> list = this.j;
        if (list != null) {
            E e = list.get(i);
            if (e == null) {
                this.l.lock();
                try {
                    e = this.j.get(i);
                    if (e == null) {
                        e = loadEntity(i);
                        this.j.set(i, e);
                        this.m++;
                        if (this.m == this.k) {
                            this.i.close();
                        }
                    }
                } finally {
                }
            }
            return e;
        }
        this.l.lock();
        try {
            return loadEntity(i);
        } finally {
        }
    }

    public int getLoadedCount() {
        return this.m;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        loadRemaining();
        return this.j.indexOf(obj);
    }

    public boolean isClosed() {
        return this.i.isClosed();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.k == 0;
    }

    public boolean isLoadedCompletely() {
        return this.m == this.k;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new LazyIterator(0, false);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        loadRemaining();
        return this.j.lastIndexOf(obj);
    }

    public CloseableListIterator<E> listIteratorAutoClose() {
        return new LazyIterator(0, true);
    }

    public E loadEntity(int i) {
        if (this.i.moveToPosition(i)) {
            E loadCurrent = this.h.loadCurrent(this.i, 0, true);
            if (loadCurrent != null) {
                return loadCurrent;
            }
            throw new DaoException("Loading of entity failed (null) at position " + i);
        }
        throw new DaoException("Could not move to cursor location " + i);
    }

    public void loadRemaining() {
        checkCached();
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            get(i);
        }
    }

    public E peek(int i) {
        List<E> list = this.j;
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    @Override // java.util.List
    public E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.k;
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        checkCached();
        for (int i3 = i; i3 < i2; i3++) {
            get(i3);
        }
        return this.j.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        loadRemaining();
        return this.j.toArray();
    }

    @Override // java.util.List
    public void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public CloseableListIterator<E> listIterator() {
        return new LazyIterator(0, false);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return new LazyIterator(i, false);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        loadRemaining();
        return (T[]) this.j.toArray(tArr);
    }
}
