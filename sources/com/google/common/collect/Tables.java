package com.google.common.collect;

import com.clevertap.android.sdk.Constants;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public final class Tables {

    /* renamed from: a  reason: collision with root package name */
    public static final Function<? extends Map<?, ?>, ? extends Map<?, ?>> f10584a = new a();

    /* loaded from: classes10.dex */
    public class a implements Function<Map<Object, Object>, Map<Object, Object>> {
        @Override // com.google.common.base.Function
        /* renamed from: a */
        public Map<Object, Object> apply(Map<Object, Object> map) {
            return Collections.unmodifiableMap(map);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class b<R, C, V> implements Table.Cell<R, C, V> {
        @Override // com.google.common.collect.Table.Cell
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell) obj;
                return Objects.equal(getRowKey(), cell.getRowKey()) && Objects.equal(getColumnKey(), cell.getColumnKey()) && Objects.equal(getValue(), cell.getValue());
            }
            return false;
        }

        @Override // com.google.common.collect.Table.Cell
        public int hashCode() {
            return Objects.hashCode(getRowKey(), getColumnKey(), getValue());
        }

        public String toString() {
            String valueOf = String.valueOf(getRowKey());
            String valueOf2 = String.valueOf(getColumnKey());
            String valueOf3 = String.valueOf(getValue());
            StringBuilder sb = new StringBuilder(valueOf.length() + 4 + valueOf2.length() + valueOf3.length());
            sb.append("(");
            sb.append(valueOf);
            sb.append(Constants.SEPARATOR_COMMA);
            sb.append(valueOf2);
            sb.append(")=");
            sb.append(valueOf3);
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class c<R, C, V> extends b<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        @NullableDecl
        private final C columnKey;
        @NullableDecl
        private final R rowKey;
        @NullableDecl
        private final V value;

        public c(@NullableDecl R r, @NullableDecl C c, @NullableDecl V v) {
            this.rowKey = r;
            this.columnKey = c;
            this.value = v;
        }

        @Override // com.google.common.collect.Table.Cell
        public C getColumnKey() {
            return this.columnKey;
        }

        @Override // com.google.common.collect.Table.Cell
        public R getRowKey() {
            return this.rowKey;
        }

        @Override // com.google.common.collect.Table.Cell
        public V getValue() {
            return this.value;
        }
    }

    /* loaded from: classes10.dex */
    public static class d<R, C, V1, V2> extends o<R, C, V2> {
        public final Table<R, C, V1> h;
        public final Function<? super V1, V2> i;

        /* loaded from: classes10.dex */
        public class a implements Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>> {
            public a() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Table.Cell<R, C, V2> apply(Table.Cell<R, C, V1> cell) {
                return Tables.immutableCell(cell.getRowKey(), cell.getColumnKey(), d.this.i.apply(cell.getValue()));
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Function<Map<C, V1>, Map<C, V2>> {
            public b() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Map<C, V2> apply(Map<C, V1> map) {
                return Maps.transformValues(map, d.this.i);
            }
        }

        /* loaded from: classes10.dex */
        public class c implements Function<Map<R, V1>, Map<R, V2>> {
            public c() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Map<R, V2> apply(Map<R, V1> map) {
                return Maps.transformValues(map, d.this.i);
            }
        }

        public d(Table<R, C, V1> table, Function<? super V1, V2> function) {
            this.h = (Table) Preconditions.checkNotNull(table);
            this.i = (Function) Preconditions.checkNotNull(function);
        }

        public Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>> a() {
            return new a();
        }

        @Override // com.google.common.collect.o
        public Iterator<Table.Cell<R, C, V2>> cellIterator() {
            return Iterators.transform(this.h.cellSet().iterator(), a());
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public void clear() {
            this.h.clear();
        }

        @Override // com.google.common.collect.Table
        public Map<R, V2> column(C c2) {
            return Maps.transformValues(this.h.column(c2), this.i);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public Set<C> columnKeySet() {
            return this.h.columnKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V2>> columnMap() {
            return Maps.transformValues(this.h.columnMap(), new c());
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public boolean contains(Object obj, Object obj2) {
            return this.h.contains(obj, obj2);
        }

        @Override // com.google.common.collect.o
        public Collection<V2> createValues() {
            return Collections2.transform(this.h.values(), this.i);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public V2 get(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.i.apply((V1) this.h.get(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public V2 put(R r, C c2, V2 v2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V2> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public V2 remove(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.i.apply((V1) this.h.remove(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.Table
        public Map<C, V2> row(R r) {
            return Maps.transformValues(this.h.row(r), this.i);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public Set<R> rowKeySet() {
            return this.h.rowKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V2>> rowMap() {
            return Maps.transformValues(this.h.rowMap(), new b());
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.h.size();
        }
    }

    /* loaded from: classes10.dex */
    public static class e<C, R, V> extends o<C, R, V> {
        public static final Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>> i = new a();
        public final Table<R, C, V> h;

        /* loaded from: classes10.dex */
        public class a implements Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>> {
            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Table.Cell<?, ?, ?> apply(Table.Cell<?, ?, ?> cell) {
                return Tables.immutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
            }
        }

        public e(Table<R, C, V> table) {
            this.h = (Table) Preconditions.checkNotNull(table);
        }

        @Override // com.google.common.collect.o
        public Iterator<Table.Cell<C, R, V>> cellIterator() {
            return Iterators.transform(this.h.cellSet().iterator(), i);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public void clear() {
            this.h.clear();
        }

        @Override // com.google.common.collect.Table
        public Map<C, V> column(R r) {
            return this.h.row(r);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public Set<R> columnKeySet() {
            return this.h.rowKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V>> columnMap() {
            return this.h.rowMap();
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
            return this.h.contains(obj2, obj);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public boolean containsColumn(@NullableDecl Object obj) {
            return this.h.containsRow(obj);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public boolean containsRow(@NullableDecl Object obj) {
            return this.h.containsColumn(obj);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public boolean containsValue(@NullableDecl Object obj) {
            return this.h.containsValue(obj);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
            return this.h.get(obj2, obj);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public V put(C c, R r, V v) {
            return this.h.put(r, c, v);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public void putAll(Table<? extends C, ? extends R, ? extends V> table) {
            this.h.putAll(Tables.transpose(table));
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
            return this.h.remove(obj2, obj);
        }

        @Override // com.google.common.collect.Table
        public Map<R, V> row(C c) {
            return this.h.column(c);
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public Set<C> rowKeySet() {
            return this.h.columnKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V>> rowMap() {
            return this.h.columnMap();
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.h.size();
        }

        @Override // com.google.common.collect.o, com.google.common.collect.Table
        public Collection<V> values() {
            return this.h.values();
        }
    }

    /* loaded from: classes10.dex */
    public static final class f<R, C, V> extends g<R, C, V> implements RowSortedTable<R, C, V> {
        private static final long serialVersionUID = 0;

        public f(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
            super(rowSortedTable);
        }

        @Override // com.google.common.collect.Tables.g, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public SortedSet<R> rowKeySet() {
            return Collections.unmodifiableSortedSet(delegate().rowKeySet());
        }

        @Override // com.google.common.collect.Tables.g, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public SortedMap<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableSortedMap(Maps.transformValues((SortedMap) delegate().rowMap(), Tables.a()));
        }

        @Override // com.google.common.collect.Tables.g, com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public RowSortedTable<R, C, V> delegate() {
            return (RowSortedTable) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class g<R, C, V> extends ForwardingTable<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        public final Table<? extends R, ? extends C, ? extends V> delegate;

        public g(Table<? extends R, ? extends C, ? extends V> table) {
            this.delegate = (Table) Preconditions.checkNotNull(table);
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<Table.Cell<R, C, V>> cellSet() {
            return Collections.unmodifiableSet(super.cellSet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<R, V> column(@NullableDecl C c) {
            return Collections.unmodifiableMap(super.column(c));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<C> columnKeySet() {
            return Collections.unmodifiableSet(super.columnKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<C, Map<R, V>> columnMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.columnMap(), Tables.a()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V put(@NullableDecl R r, @NullableDecl C c, @NullableDecl V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<C, V> row(@NullableDecl R r) {
            return Collections.unmodifiableMap(super.row(r));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<R> rowKeySet() {
            return Collections.unmodifiableSet(super.rowKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.rowMap(), Tables.a()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Collection<V> values() {
            return Collections.unmodifiableCollection(super.values());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public Table<R, C, V> delegate() {
            return (Table<? extends R, ? extends C, ? extends V>) this.delegate;
        }
    }

    public static /* synthetic */ Function a() {
        return c();
    }

    public static boolean b(Table<?, ?, ?> table, @NullableDecl Object obj) {
        if (obj == table) {
            return true;
        }
        if (obj instanceof Table) {
            return table.cellSet().equals(((Table) obj).cellSet());
        }
        return false;
    }

    public static <K, V> Function<Map<K, V>, Map<K, V>> c() {
        return (Function<Map<K, V>, Map<K, V>>) f10584a;
    }

    public static <R, C, V> Table.Cell<R, C, V> immutableCell(@NullableDecl R r, @NullableDecl C c2, @NullableDecl V v) {
        return new c(r, c2, v);
    }

    @Beta
    public static <R, C, V> Table<R, C, V> newCustomTable(Map<R, Map<C, V>> map, Supplier<? extends Map<C, V>> supplier) {
        Preconditions.checkArgument(map.isEmpty());
        Preconditions.checkNotNull(supplier);
        return new n2(map, supplier);
    }

    public static <R, C, V> Table<R, C, V> synchronizedTable(Table<R, C, V> table) {
        return o2.z(table, null);
    }

    @Beta
    public static <R, C, V1, V2> Table<R, C, V2> transformValues(Table<R, C, V1> table, Function<? super V1, V2> function) {
        return new d(table, function);
    }

    public static <R, C, V> Table<C, R, V> transpose(Table<R, C, V> table) {
        if (table instanceof e) {
            return (Table<R, C, V>) ((e) table).h;
        }
        return new e(table);
    }

    @Beta
    public static <R, C, V> RowSortedTable<R, C, V> unmodifiableRowSortedTable(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
        return new f(rowSortedTable);
    }

    public static <R, C, V> Table<R, C, V> unmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
        return new g(table);
    }
}
