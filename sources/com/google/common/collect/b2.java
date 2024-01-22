package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class b2<R, C, V> extends ImmutableTable<R, C, V> {

    /* loaded from: classes10.dex */
    public class a implements Comparator<Table.Cell<R, C, V>> {
        public final /* synthetic */ Comparator h;
        public final /* synthetic */ Comparator i;

        public a(Comparator comparator, Comparator comparator2) {
            this.h = comparator;
            this.i = comparator2;
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Table.Cell<R, C, V> cell, Table.Cell<R, C, V> cell2) {
            Comparator comparator = this.h;
            int compare = comparator == null ? 0 : comparator.compare(cell.getRowKey(), cell2.getRowKey());
            if (compare != 0) {
                return compare;
            }
            Comparator comparator2 = this.i;
            if (comparator2 == null) {
                return 0;
            }
            return comparator2.compare(cell.getColumnKey(), cell2.getColumnKey());
        }
    }

    /* loaded from: classes10.dex */
    public final class b extends i1<Table.Cell<R, C, V>> {
        private b() {
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell) obj;
                Object obj2 = b2.this.get(cell.getRowKey(), cell.getColumnKey());
                return obj2 != null && obj2.equals(cell.getValue());
            }
            return false;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return b2.this.size();
        }

        public /* synthetic */ b(b2 b2Var, a aVar) {
            this();
        }

        @Override // com.google.common.collect.i1
        public Table.Cell<R, C, V> get(int i) {
            return b2.this.getCell(i);
        }
    }

    /* loaded from: classes10.dex */
    public final class c extends ImmutableList<V> {
        private c() {
        }

        @Override // java.util.List
        public V get(int i) {
            return (V) b2.this.getValue(i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return b2.this.size();
        }

        public /* synthetic */ c(b2 b2Var, a aVar) {
            this();
        }
    }

    public static <R, C, V> b2<R, C, V> forCells(List<Table.Cell<R, C, V>> list, @NullableDecl Comparator<? super R> comparator, @NullableDecl Comparator<? super C> comparator2) {
        Preconditions.checkNotNull(list);
        if (comparator != null || comparator2 != null) {
            Collections.sort(list, new a(comparator, comparator2));
        }
        return forCellsInternal(list, comparator, comparator2);
    }

    private static <R, C, V> b2<R, C, V> forCellsInternal(Iterable<Table.Cell<R, C, V>> iterable, @NullableDecl Comparator<? super R> comparator, @NullableDecl Comparator<? super C> comparator2) {
        ImmutableSet copyOf;
        ImmutableSet copyOf2;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        ImmutableList copyOf3 = ImmutableList.copyOf(iterable);
        for (Table.Cell<R, C, V> cell : iterable) {
            linkedHashSet.add(cell.getRowKey());
            linkedHashSet2.add(cell.getColumnKey());
        }
        if (comparator == null) {
            copyOf = ImmutableSet.copyOf((Collection) linkedHashSet);
        } else {
            copyOf = ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(comparator, linkedHashSet));
        }
        if (comparator2 == null) {
            copyOf2 = ImmutableSet.copyOf((Collection) linkedHashSet2);
        } else {
            copyOf2 = ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(comparator2, linkedHashSet2));
        }
        return forOrderedComponents(copyOf3, copyOf, copyOf2);
    }

    public static <R, C, V> b2<R, C, V> forOrderedComponents(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        if (immutableList.size() > (immutableSet.size() * immutableSet2.size()) / 2) {
            return new e0(immutableList, immutableSet, immutableSet2);
        }
        return new l2(immutableList, immutableSet, immutableSet2);
    }

    public final void checkNoDuplicate(R r, C c2, V v, V v2) {
        Preconditions.checkArgument(v == null, "Duplicate key: (row=%s, column=%s), values: [%s, %s].", r, c2, v2, v);
    }

    public abstract Table.Cell<R, C, V> getCell(int i);

    public abstract V getValue(int i);

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.o
    public final ImmutableSet<Table.Cell<R, C, V>> createCellSet() {
        return isEmpty() ? ImmutableSet.of() : new b(this, null);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.o
    public final ImmutableCollection<V> createValues() {
        return isEmpty() ? ImmutableList.of() : new c(this, null);
    }

    public static <R, C, V> b2<R, C, V> forCells(Iterable<Table.Cell<R, C, V>> iterable) {
        return forCellsInternal(iterable, null, null);
    }
}
