package com.google.common.collect;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.Immutable;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.lang.reflect.Array;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Immutable(containerOf = {"R", WeatherCriteria.UNIT_CELSIUS, ExifInterface.GPS_MEASUREMENT_INTERRUPTED})
@GwtCompatible
/* loaded from: classes10.dex */
public final class e0<R, C, V> extends b2<R, C, V> {
    private final int[] cellColumnIndices;
    private final int[] cellRowIndices;
    private final int[] columnCounts;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    private final int[] rowCounts;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
    private final V[][] values;

    /* loaded from: classes10.dex */
    public final class b extends d<R, V> {
        private final int columnIndex;

        public b(int i) {
            super(e0.this.columnCounts[i]);
            this.columnIndex = i;
        }

        @Override // com.google.common.collect.e0.d
        public V getValue(int i) {
            return (V) e0.this.values[i][this.columnIndex];
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.e0.d
        public ImmutableMap<R, Integer> keyToIndex() {
            return e0.this.rowKeyToIndex;
        }
    }

    /* loaded from: classes10.dex */
    public final class c extends d<C, ImmutableMap<R, V>> {
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        @Override // com.google.common.collect.e0.d
        public ImmutableMap<C, Integer> keyToIndex() {
            return e0.this.columnKeyToIndex;
        }

        private c() {
            super(e0.this.columnCounts.length);
        }

        @Override // com.google.common.collect.e0.d
        public ImmutableMap<R, V> getValue(int i) {
            return new b(i);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class d<K, V> extends ImmutableMap.b<K, V> {
        private final int size;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<Map.Entry<K, V>> {
            public int j = -1;
            public final int k;

            public a() {
                this.k = d.this.keyToIndex().size();
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<K, V> computeNext() {
                int i = this.j;
                while (true) {
                    this.j = i + 1;
                    int i2 = this.j;
                    if (i2 < this.k) {
                        Object value = d.this.getValue(i2);
                        if (value != null) {
                            return Maps.immutableEntry(d.this.getKey(this.j), value);
                        }
                        i = this.j;
                    } else {
                        return endOfData();
                    }
                }
            }
        }

        public d(int i) {
            this.size = i;
        }

        private boolean isFull() {
            return this.size == keyToIndex().size();
        }

        @Override // com.google.common.collect.ImmutableMap.b, com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return isFull() ? keyToIndex().keySet() : super.createKeySet();
        }

        @Override // com.google.common.collect.ImmutableMap.b
        public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
            return new a();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public V get(@NullableDecl Object obj) {
            Integer num = keyToIndex().get(obj);
            if (num == null) {
                return null;
            }
            return getValue(num.intValue());
        }

        public K getKey(int i) {
            return keyToIndex().keySet().asList().get(i);
        }

        @NullableDecl
        public abstract V getValue(int i);

        public abstract ImmutableMap<K, Integer> keyToIndex();

        @Override // java.util.Map
        public int size() {
            return this.size;
        }
    }

    /* loaded from: classes10.dex */
    public final class e extends d<C, V> {
        private final int rowIndex;

        public e(int i) {
            super(e0.this.rowCounts[i]);
            this.rowIndex = i;
        }

        @Override // com.google.common.collect.e0.d
        public V getValue(int i) {
            return (V) e0.this.values[this.rowIndex][i];
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.e0.d
        public ImmutableMap<C, Integer> keyToIndex() {
            return e0.this.columnKeyToIndex;
        }
    }

    /* loaded from: classes10.dex */
    public final class f extends d<R, ImmutableMap<C, V>> {
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        @Override // com.google.common.collect.e0.d
        public ImmutableMap<R, Integer> keyToIndex() {
            return e0.this.rowKeyToIndex;
        }

        private f() {
            super(e0.this.rowCounts.length);
        }

        @Override // com.google.common.collect.e0.d
        public ImmutableMap<C, V> getValue(int i) {
            return new e(i);
        }
    }

    public e0(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        this.values = (V[][]) ((Object[][]) Array.newInstance(Object.class, immutableSet.size(), immutableSet2.size()));
        ImmutableMap<R, Integer> u = Maps.u(immutableSet);
        this.rowKeyToIndex = u;
        ImmutableMap<C, Integer> u2 = Maps.u(immutableSet2);
        this.columnKeyToIndex = u2;
        this.rowCounts = new int[u.size()];
        this.columnCounts = new int[u2.size()];
        int[] iArr = new int[immutableList.size()];
        int[] iArr2 = new int[immutableList.size()];
        for (int i = 0; i < immutableList.size(); i++) {
            Table.Cell<R, C, V> cell = immutableList.get(i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            int intValue = this.rowKeyToIndex.get(rowKey).intValue();
            int intValue2 = this.columnKeyToIndex.get(columnKey).intValue();
            checkNoDuplicate(rowKey, columnKey, this.values[intValue][intValue2], cell.getValue());
            this.values[intValue][intValue2] = cell.getValue();
            int[] iArr3 = this.rowCounts;
            iArr3[intValue] = iArr3[intValue] + 1;
            int[] iArr4 = this.columnCounts;
            iArr4[intValue2] = iArr4[intValue2] + 1;
            iArr[i] = intValue;
            iArr2[i] = intValue2;
        }
        this.cellRowIndices = iArr;
        this.cellColumnIndices = iArr2;
        this.rowMap = new f();
        this.columnMap = new c();
    }

    @Override // com.google.common.collect.ImmutableTable
    public ImmutableTable.a createSerializedForm() {
        return ImmutableTable.a.create(this, this.cellRowIndices, this.cellColumnIndices);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.o, com.google.common.collect.Table
    public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return this.values[num.intValue()][num2.intValue()];
    }

    @Override // com.google.common.collect.b2
    public Table.Cell<R, C, V> getCell(int i) {
        int i2 = this.cellRowIndices[i];
        int i3 = this.cellColumnIndices[i];
        return ImmutableTable.cellOf(rowKeySet().asList().get(i2), columnKeySet().asList().get(i3), this.values[i2][i3]);
    }

    @Override // com.google.common.collect.b2
    public V getValue(int i) {
        return this.values[this.cellRowIndices[i]][this.cellColumnIndices[i]];
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.cellRowIndices.length;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.copyOf((Map) this.columnMap);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.copyOf((Map) this.rowMap);
    }
}
