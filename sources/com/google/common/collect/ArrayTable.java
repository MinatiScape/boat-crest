package com.google.common.collect;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class ArrayTable<R, C, V> extends o<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    private final V[][] array;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableList<C> columnList;
    @NullableDecl
    private transient ArrayTable<R, C, V>.f columnMap;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableList<R> rowList;
    @NullableDecl
    private transient ArrayTable<R, C, V>.h rowMap;

    /* loaded from: classes10.dex */
    public class a extends com.google.common.collect.b<Table.Cell<R, C, V>> {
        public a(int i) {
            super(i);
        }

        @Override // com.google.common.collect.b
        /* renamed from: b */
        public Table.Cell<R, C, V> a(int i) {
            return ArrayTable.this.getCell(i);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends Tables.b<R, C, V> {
        public final int h;
        public final int i;
        public final /* synthetic */ int j;

        public b(int i) {
            this.j = i;
            this.h = i / ArrayTable.this.columnList.size();
            this.i = i % ArrayTable.this.columnList.size();
        }

        @Override // com.google.common.collect.Table.Cell
        public C getColumnKey() {
            return (C) ArrayTable.this.columnList.get(this.i);
        }

        @Override // com.google.common.collect.Table.Cell
        public R getRowKey() {
            return (R) ArrayTable.this.rowList.get(this.h);
        }

        @Override // com.google.common.collect.Table.Cell
        public V getValue() {
            return (V) ArrayTable.this.at(this.h, this.i);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends com.google.common.collect.b<V> {
        public c(int i) {
            super(i);
        }

        @Override // com.google.common.collect.b
        public V a(int i) {
            return (V) ArrayTable.this.getValue(i);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class d<K, V> extends Maps.z<K, V> {
        public final ImmutableMap<K, Integer> h;

        /* loaded from: classes10.dex */
        public class a extends com.google.common.collect.f<K, V> {
            public final /* synthetic */ int h;

            public a(int i) {
                this.h = i;
            }

            @Override // com.google.common.collect.f, java.util.Map.Entry
            public K getKey() {
                return (K) d.this.d(this.h);
            }

            @Override // com.google.common.collect.f, java.util.Map.Entry
            public V getValue() {
                return (V) d.this.f(this.h);
            }

            @Override // com.google.common.collect.f, java.util.Map.Entry
            public V setValue(V v) {
                return (V) d.this.g(this.h, v);
            }
        }

        /* loaded from: classes10.dex */
        public class b extends com.google.common.collect.b<Map.Entry<K, V>> {
            public b(int i) {
                super(i);
            }

            @Override // com.google.common.collect.b
            /* renamed from: b */
            public Map.Entry<K, V> a(int i) {
                return d.this.b(i);
            }
        }

        public /* synthetic */ d(ImmutableMap immutableMap, a aVar) {
            this(immutableMap);
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<K, V>> a() {
            return new b(size());
        }

        public Map.Entry<K, V> b(int i) {
            Preconditions.checkElementIndex(i, size());
            return new a(i);
        }

        @Override // com.google.common.collect.Maps.z, java.util.AbstractMap, java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return this.h.containsKey(obj);
        }

        public K d(int i) {
            return this.h.keySet().asList().get(i);
        }

        public abstract String e();

        @NullableDecl
        public abstract V f(int i);

        @NullableDecl
        public abstract V g(int i, V v);

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@NullableDecl Object obj) {
            Integer num = this.h.get(obj);
            if (num == null) {
                return null;
            }
            return f(num.intValue());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.h.isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.h.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            Integer num = this.h.get(k);
            if (num != null) {
                return g(num.intValue(), v);
            }
            String e = e();
            String valueOf = String.valueOf(k);
            String valueOf2 = String.valueOf(this.h.keySet());
            StringBuilder sb = new StringBuilder(String.valueOf(e).length() + 9 + valueOf.length() + valueOf2.length());
            sb.append(e);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(valueOf);
            sb.append(" not in ");
            sb.append(valueOf2);
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.h.size();
        }

        public d(ImmutableMap<K, Integer> immutableMap) {
            this.h = immutableMap;
        }
    }

    /* loaded from: classes10.dex */
    public class e extends d<R, V> {
        public final int i;

        public e(int i) {
            super(ArrayTable.this.rowKeyToIndex, null);
            this.i = i;
        }

        @Override // com.google.common.collect.ArrayTable.d
        public String e() {
            return "Row";
        }

        @Override // com.google.common.collect.ArrayTable.d
        public V f(int i) {
            return (V) ArrayTable.this.at(i, this.i);
        }

        @Override // com.google.common.collect.ArrayTable.d
        public V g(int i, V v) {
            return (V) ArrayTable.this.set(i, this.i, v);
        }
    }

    /* loaded from: classes10.dex */
    public class f extends d<C, Map<R, V>> {
        public /* synthetic */ f(ArrayTable arrayTable, a aVar) {
            this();
        }

        @Override // com.google.common.collect.ArrayTable.d
        public String e() {
            return "Column";
        }

        @Override // com.google.common.collect.ArrayTable.d
        /* renamed from: h */
        public Map<R, V> f(int i) {
            return new e(i);
        }

        @Override // com.google.common.collect.ArrayTable.d, java.util.AbstractMap, java.util.Map
        /* renamed from: i */
        public Map<R, V> put(C c, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ArrayTable.d
        /* renamed from: j */
        public Map<R, V> g(int i, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }

        public f() {
            super(ArrayTable.this.columnKeyToIndex, null);
        }
    }

    /* loaded from: classes10.dex */
    public class g extends d<C, V> {
        public final int i;

        public g(int i) {
            super(ArrayTable.this.columnKeyToIndex, null);
            this.i = i;
        }

        @Override // com.google.common.collect.ArrayTable.d
        public String e() {
            return "Column";
        }

        @Override // com.google.common.collect.ArrayTable.d
        public V f(int i) {
            return (V) ArrayTable.this.at(this.i, i);
        }

        @Override // com.google.common.collect.ArrayTable.d
        public V g(int i, V v) {
            return (V) ArrayTable.this.set(this.i, i, v);
        }
    }

    /* loaded from: classes10.dex */
    public class h extends d<R, Map<C, V>> {
        public /* synthetic */ h(ArrayTable arrayTable, a aVar) {
            this();
        }

        @Override // com.google.common.collect.ArrayTable.d
        public String e() {
            return "Row";
        }

        @Override // com.google.common.collect.ArrayTable.d
        /* renamed from: h */
        public Map<C, V> f(int i) {
            return new g(i);
        }

        @Override // com.google.common.collect.ArrayTable.d, java.util.AbstractMap, java.util.Map
        /* renamed from: i */
        public Map<C, V> put(R r, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ArrayTable.d
        /* renamed from: j */
        public Map<C, V> g(int i, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        public h() {
            super(ArrayTable.this.rowKeyToIndex, null);
        }
    }

    private ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        ImmutableList<R> copyOf = ImmutableList.copyOf(iterable);
        this.rowList = copyOf;
        ImmutableList<C> copyOf2 = ImmutableList.copyOf(iterable2);
        this.columnList = copyOf2;
        Preconditions.checkArgument(copyOf.isEmpty() == copyOf2.isEmpty());
        this.rowKeyToIndex = Maps.u(copyOf);
        this.columnKeyToIndex = Maps.u(copyOf2);
        this.array = (V[][]) ((Object[][]) Array.newInstance(Object.class, copyOf.size(), copyOf2.size()));
        eraseAll();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        return new ArrayTable<>(iterable, iterable2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Table.Cell<R, C, V> getCell(int i) {
        return new b(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V getValue(int i) {
        return at(i / this.columnList.size(), i % this.columnList.size());
    }

    public V at(int i, int i2) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        return this.array[i][i2];
    }

    @Override // com.google.common.collect.o
    public Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new a(size());
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    public Map<R, V> column(C c2) {
        Preconditions.checkNotNull(c2);
        Integer num = this.columnKeyToIndex.get(c2);
        return num == null ? ImmutableMap.of() : new e(num.intValue());
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    @Override // com.google.common.collect.Table
    public Map<C, Map<R, V>> columnMap() {
        ArrayTable<R, C, V>.f fVar = this.columnMap;
        if (fVar == null) {
            ArrayTable<R, C, V>.f fVar2 = new f(this, null);
            this.columnMap = fVar2;
            return fVar2;
        }
        return fVar;
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return containsRow(obj) && containsColumn(obj2);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean containsColumn(@NullableDecl Object obj) {
        return this.columnKeyToIndex.containsKey(obj);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean containsRow(@NullableDecl Object obj) {
        return this.rowKeyToIndex.containsKey(obj);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean containsValue(@NullableDecl Object obj) {
        V[][] vArr;
        for (V[] vArr2 : this.array) {
            for (V v : vArr2) {
                if (Objects.equal(obj, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    public V erase(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return set(num.intValue(), num2.intValue(), null);
    }

    public void eraseAll() {
        for (V[] vArr : this.array) {
            Arrays.fill(vArr, (Object) null);
        }
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return at(num.intValue(), num2.intValue());
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean isEmpty() {
        return this.rowList.isEmpty() || this.columnList.isEmpty();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V put(R r, C c2, @NullableDecl V v) {
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c2);
        Integer num = this.rowKeyToIndex.get(r);
        Preconditions.checkArgument(num != null, "Row %s not in %s", r, this.rowList);
        Integer num2 = this.columnKeyToIndex.get(c2);
        Preconditions.checkArgument(num2 != null, "Column %s not in %s", c2, this.columnList);
        return set(num.intValue(), num2.intValue(), v);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public V remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    public Map<C, V> row(R r) {
        Preconditions.checkNotNull(r);
        Integer num = this.rowKeyToIndex.get(r);
        return num == null ? ImmutableMap.of() : new g(num.intValue());
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    @Override // com.google.common.collect.Table
    public Map<R, Map<C, V>> rowMap() {
        ArrayTable<R, C, V>.h hVar = this.rowMap;
        if (hVar == null) {
            ArrayTable<R, C, V>.h hVar2 = new h(this, null);
            this.rowMap = hVar2;
            return hVar2;
        }
        return hVar;
    }

    @CanIgnoreReturnValue
    public V set(int i, int i2, @NullableDecl V v) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        V[][] vArr = this.array;
        V v2 = vArr[i][i2];
        vArr[i][i2] = v;
        return v2;
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.rowList.size() * this.columnList.size();
    }

    @GwtIncompatible
    public V[][] toArray(Class<V> cls) {
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance((Class<?>) cls, this.rowList.size(), this.columnList.size()));
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = this.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
        return vArr;
    }

    @Override // com.google.common.collect.o
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public Collection<V> values() {
        return super.values();
    }

    @Override // com.google.common.collect.o
    public Iterator<V> valuesIterator() {
        return new c(size());
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, V> table) {
        if (table instanceof ArrayTable) {
            return new ArrayTable<>((ArrayTable) table);
        }
        return new ArrayTable<>(table);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ArrayTable(Table<R, C, V> table) {
        this(table.rowKeySet(), table.columnKeySet());
        putAll(table);
    }

    private ArrayTable(ArrayTable<R, C, V> arrayTable) {
        ImmutableList<R> immutableList = arrayTable.rowList;
        this.rowList = immutableList;
        ImmutableList<C> immutableList2 = arrayTable.columnList;
        this.columnList = immutableList2;
        this.rowKeyToIndex = arrayTable.rowKeyToIndex;
        this.columnKeyToIndex = arrayTable.columnKeyToIndex;
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance(Object.class, immutableList.size(), immutableList2.size()));
        this.array = vArr;
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = arrayTable.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
    }
}
