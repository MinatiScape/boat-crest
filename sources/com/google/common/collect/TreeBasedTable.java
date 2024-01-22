package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(serializable = true)
/* loaded from: classes10.dex */
public class TreeBasedTable<R, C, V> extends m2<R, C, V> {
    private static final long serialVersionUID = 0;
    private final Comparator<? super C> columnComparator;

    /* loaded from: classes10.dex */
    public class a implements Function<Map<C, V>, Iterator<C>> {
        public a(TreeBasedTable treeBasedTable) {
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public Iterator<C> apply(Map<C, V> map) {
            return map.keySet().iterator();
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AbstractIterator<C> {
        @NullableDecl
        public C j;
        public final /* synthetic */ Iterator k;
        public final /* synthetic */ Comparator l;

        public b(TreeBasedTable treeBasedTable, Iterator it, Comparator comparator) {
            this.k = it;
            this.l = comparator;
        }

        @Override // com.google.common.collect.AbstractIterator
        public C computeNext() {
            boolean z;
            while (this.k.hasNext()) {
                C c = (C) this.k.next();
                C c2 = this.j;
                if (c2 == null || this.l.compare(c, c2) != 0) {
                    z = false;
                    continue;
                } else {
                    z = true;
                    continue;
                }
                if (!z) {
                    this.j = c;
                    return c;
                }
            }
            this.j = null;
            return endOfData();
        }
    }

    /* loaded from: classes10.dex */
    public static class c<C, V> implements Supplier<TreeMap<C, V>>, Serializable {
        private static final long serialVersionUID = 0;
        public final Comparator<? super C> comparator;

        public c(Comparator<? super C> comparator) {
            this.comparator = comparator;
        }

        @Override // com.google.common.base.Supplier
        public TreeMap<C, V> get() {
            return new TreeMap<>(this.comparator);
        }
    }

    /* loaded from: classes10.dex */
    public class d extends n2<R, C, V>.g implements SortedMap<C, V> {
        @NullableDecl
        public final C k;
        @NullableDecl
        public final C l;
        @NullableDecl
        public transient SortedMap<C, V> m;

        public d(TreeBasedTable treeBasedTable, R r) {
            this(r, null, null);
        }

        @Override // java.util.SortedMap
        public Comparator<? super C> comparator() {
            return TreeBasedTable.this.columnComparator();
        }

        @Override // com.google.common.collect.n2.g, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return k(obj) && super.containsKey(obj);
        }

        @Override // com.google.common.collect.n2.g
        public void e() {
            if (l() == null || !this.m.isEmpty()) {
                return;
            }
            TreeBasedTable.this.backingMap.remove(this.h);
            this.m = null;
            this.i = null;
        }

        @Override // java.util.SortedMap
        public C firstKey() {
            if (b() != null) {
                return b().firstKey();
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.n2.g
        /* renamed from: g */
        public SortedMap<C, V> b() {
            return (SortedMap) super.b();
        }

        public int h(Object obj, Object obj2) {
            return comparator().compare(obj, obj2);
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> headMap(C c) {
            Preconditions.checkArgument(k(Preconditions.checkNotNull(c)));
            return new d(this.h, this.k, c);
        }

        @Override // com.google.common.collect.n2.g
        /* renamed from: i */
        public SortedMap<C, V> d() {
            SortedMap<C, V> l = l();
            if (l != null) {
                C c = this.k;
                if (c != null) {
                    l = l.tailMap(c);
                }
                C c2 = this.l;
                return c2 != null ? l.headMap(c2) : l;
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        /* renamed from: j */
        public SortedSet<C> keySet() {
            return new Maps.f0(this);
        }

        public boolean k(@NullableDecl Object obj) {
            C c;
            C c2;
            return obj != null && ((c = this.k) == null || h(c, obj) <= 0) && ((c2 = this.l) == null || h(c2, obj) > 0);
        }

        public SortedMap<C, V> l() {
            SortedMap<C, V> sortedMap = this.m;
            if (sortedMap == null || (sortedMap.isEmpty() && TreeBasedTable.this.backingMap.containsKey(this.h))) {
                this.m = (SortedMap) TreeBasedTable.this.backingMap.get(this.h);
            }
            return this.m;
        }

        @Override // java.util.SortedMap
        public C lastKey() {
            if (b() != null) {
                return b().lastKey();
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.n2.g, java.util.AbstractMap, java.util.Map
        public V put(C c, V v) {
            Preconditions.checkArgument(k(Preconditions.checkNotNull(c)));
            return (V) super.put(c, v);
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> subMap(C c, C c2) {
            Preconditions.checkArgument(k(Preconditions.checkNotNull(c)) && k(Preconditions.checkNotNull(c2)));
            return new d(this.h, c, c2);
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> tailMap(C c) {
            Preconditions.checkArgument(k(Preconditions.checkNotNull(c)));
            return new d(this.h, c, this.l);
        }

        public d(R r, @NullableDecl C c, @NullableDecl C c2) {
            super(r);
            this.k = c;
            this.l = c2;
            Preconditions.checkArgument(c == null || c2 == null || h(c, c2) <= 0);
        }
    }

    public TreeBasedTable(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        super(new TreeMap(comparator), new c(comparator2));
        this.columnComparator = comparator2;
    }

    public static <R extends Comparable, C extends Comparable, V> TreeBasedTable<R, C, V> create() {
        return new TreeBasedTable<>(Ordering.natural(), Ordering.natural());
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Set cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.n2, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Map column(Object obj) {
        return super.column(obj);
    }

    @Deprecated
    public Comparator<? super C> columnComparator() {
        return this.columnComparator;
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Set columnKeySet() {
        return super.columnKeySet();
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Map columnMap() {
        return super.columnMap();
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.contains(obj, obj2);
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsColumn(@NullableDecl Object obj) {
        return super.containsColumn(obj);
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsRow(@NullableDecl Object obj) {
        return super.containsRow(obj);
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsValue(@NullableDecl Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.google.common.collect.n2
    public Iterator<C> createColumnKeyIterator() {
        Comparator<? super C> columnComparator = columnComparator();
        return new b(this, Iterators.mergeSorted(Iterables.transform(this.backingMap.values(), new a(this)), columnComparator), columnComparator);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Object get(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.get(obj, obj2);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2, Object obj3) {
        return super.put(obj, obj2, obj3);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ void putAll(Table table) {
        super.putAll(table);
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.remove(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.n2, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Map row(Object obj) {
        return row((TreeBasedTable<R, C, V>) obj);
    }

    @Deprecated
    public Comparator<? super R> rowComparator() {
        return rowKeySet().comparator();
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.google.common.collect.o
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(comparator2);
        return new TreeBasedTable<>(comparator, comparator2);
    }

    @Override // com.google.common.collect.n2, com.google.common.collect.Table
    public SortedMap<C, V> row(R r) {
        return new d(this, r);
    }

    @Override // com.google.common.collect.m2, com.google.common.collect.n2, com.google.common.collect.o, com.google.common.collect.Table
    public SortedSet<R> rowKeySet() {
        return super.rowKeySet();
    }

    @Override // com.google.common.collect.m2, com.google.common.collect.n2, com.google.common.collect.Table
    public SortedMap<R, Map<C, V>> rowMap() {
        return super.rowMap();
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(TreeBasedTable<R, C, ? extends V> treeBasedTable) {
        TreeBasedTable<R, C, V> treeBasedTable2 = new TreeBasedTable<>(treeBasedTable.rowComparator(), treeBasedTable.columnComparator());
        treeBasedTable2.putAll(treeBasedTable);
        return treeBasedTable2;
    }
}
