package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public class TreeRangeSet<C extends Comparable<?>> extends j<C> implements Serializable {
    @NullableDecl
    private transient Set<Range<C>> asDescendingSetOfRanges;
    @NullableDecl
    private transient Set<Range<C>> asRanges;
    @NullableDecl
    private transient RangeSet<C> complement;
    @VisibleForTesting
    public final NavigableMap<d0<C>, Range<C>> rangesByLowerBound;

    /* loaded from: classes10.dex */
    public final class b extends ForwardingCollection<Range<C>> implements Set<Range<C>> {
        public final Collection<Range<C>> h;

        public b(TreeRangeSet treeRangeSet, Collection<Range<C>> collection) {
            this.h = collection;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            return Sets.a(this, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.b(this);
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<Range<C>> delegate() {
            return this.h;
        }
    }

    /* loaded from: classes10.dex */
    public final class c extends TreeRangeSet<C> {
        public c() {
            super(new d(TreeRangeSet.this.rangesByLowerBound));
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public void add(Range<C> range) {
            TreeRangeSet.this.remove(range);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.RangeSet
        public RangeSet<C> complement() {
            return TreeRangeSet.this;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public boolean contains(C c) {
            return !TreeRangeSet.this.contains(c);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public void remove(Range<C> range) {
            TreeRangeSet.this.add(range);
        }
    }

    /* loaded from: classes10.dex */
    public static final class d<C extends Comparable<?>> extends i<d0<C>, Range<C>> {
        public final NavigableMap<d0<C>, Range<C>> h;
        public final NavigableMap<d0<C>, Range<C>> i;
        public final Range<d0<C>> j;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<Map.Entry<d0<C>, Range<C>>> {
            public d0<C> j;
            public final /* synthetic */ d0 k;
            public final /* synthetic */ PeekingIterator l;

            public a(d0 d0Var, PeekingIterator peekingIterator) {
                this.k = d0Var;
                this.l = peekingIterator;
                this.j = d0Var;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<d0<C>, Range<C>> computeNext() {
                Range create;
                if (!d.this.j.upperBound.isLessThan(this.j) && this.j != d0.aboveAll()) {
                    if (this.l.hasNext()) {
                        Range range = (Range) this.l.next();
                        create = Range.create(this.j, range.lowerBound);
                        this.j = range.upperBound;
                    } else {
                        create = Range.create(this.j, d0.aboveAll());
                        this.j = d0.aboveAll();
                    }
                    return Maps.immutableEntry(create.lowerBound, create);
                }
                return (Map.Entry) endOfData();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractIterator<Map.Entry<d0<C>, Range<C>>> {
            public d0<C> j;
            public final /* synthetic */ d0 k;
            public final /* synthetic */ PeekingIterator l;

            public b(d0 d0Var, PeekingIterator peekingIterator) {
                this.k = d0Var;
                this.l = peekingIterator;
                this.j = d0Var;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<d0<C>, Range<C>> computeNext() {
                if (this.j == d0.belowAll()) {
                    return (Map.Entry) endOfData();
                }
                if (!this.l.hasNext()) {
                    if (d.this.j.lowerBound.isLessThan(d0.belowAll())) {
                        Range create = Range.create(d0.belowAll(), this.j);
                        this.j = d0.belowAll();
                        return Maps.immutableEntry(d0.belowAll(), create);
                    }
                } else {
                    Range range = (Range) this.l.next();
                    Range create2 = Range.create(range.upperBound, this.j);
                    this.j = range.lowerBound;
                    if (d.this.j.lowerBound.isLessThan(create2.lowerBound)) {
                        return Maps.immutableEntry(create2.lowerBound, create2);
                    }
                }
                return (Map.Entry) endOfData();
            }
        }

        public d(NavigableMap<d0<C>, Range<C>> navigableMap) {
            this(navigableMap, Range.all());
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<d0<C>, Range<C>>> a() {
            Collection<Range<C>> values;
            d0 d0Var;
            if (this.j.hasLowerBound()) {
                values = this.i.tailMap(this.j.lowerEndpoint(), this.j.lowerBoundType() == BoundType.CLOSED).values();
            } else {
                values = this.i.values();
            }
            PeekingIterator peekingIterator = Iterators.peekingIterator(values.iterator());
            if (this.j.contains(d0.belowAll()) && (!peekingIterator.hasNext() || ((Range) peekingIterator.peek()).lowerBound != d0.belowAll())) {
                d0Var = d0.belowAll();
            } else if (peekingIterator.hasNext()) {
                d0Var = ((Range) peekingIterator.next()).upperBound;
            } else {
                return Iterators.f();
            }
            return new a(d0Var, peekingIterator);
        }

        @Override // com.google.common.collect.i
        public Iterator<Map.Entry<d0<C>, Range<C>>> b() {
            d0<C> aboveAll;
            d0<C> higherKey;
            if (this.j.hasUpperBound()) {
                aboveAll = this.j.upperEndpoint();
            } else {
                aboveAll = d0.aboveAll();
            }
            PeekingIterator peekingIterator = Iterators.peekingIterator(this.i.headMap(aboveAll, this.j.hasUpperBound() && this.j.upperBoundType() == BoundType.CLOSED).descendingMap().values().iterator());
            if (peekingIterator.hasNext()) {
                if (((Range) peekingIterator.peek()).upperBound == d0.aboveAll()) {
                    higherKey = ((Range) peekingIterator.next()).lowerBound;
                } else {
                    higherKey = this.h.higherKey(((Range) peekingIterator.peek()).upperBound);
                }
            } else if (this.j.contains(d0.belowAll()) && !this.h.containsKey(d0.belowAll())) {
                higherKey = this.h.higherKey(d0.belowAll());
            } else {
                return Iterators.f();
            }
            return new b((d0) MoreObjects.firstNonNull(higherKey, d0.aboveAll()), peekingIterator);
        }

        @Override // java.util.SortedMap
        public Comparator<? super d0<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        /* renamed from: e */
        public Range<C> get(Object obj) {
            if (obj instanceof d0) {
                try {
                    d0<C> d0Var = (d0) obj;
                    Map.Entry<d0<C>, Range<C>> firstEntry = tailMap(d0Var, true).firstEntry();
                    if (firstEntry != null && firstEntry.getKey().equals(d0Var)) {
                        return firstEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        @Override // java.util.NavigableMap
        /* renamed from: f */
        public NavigableMap<d0<C>, Range<C>> headMap(d0<C> d0Var, boolean z) {
            return h(Range.upTo(d0Var, BoundType.forBoolean(z)));
        }

        @Override // java.util.NavigableMap
        /* renamed from: g */
        public NavigableMap<d0<C>, Range<C>> subMap(d0<C> d0Var, boolean z, d0<C> d0Var2, boolean z2) {
            return h(Range.range(d0Var, BoundType.forBoolean(z), d0Var2, BoundType.forBoolean(z2)));
        }

        public final NavigableMap<d0<C>, Range<C>> h(Range<d0<C>> range) {
            if (!this.j.isConnected(range)) {
                return ImmutableSortedMap.of();
            }
            return new d(this.h, range.intersection(this.j));
        }

        @Override // java.util.NavigableMap
        /* renamed from: i */
        public NavigableMap<d0<C>, Range<C>> tailMap(d0<C> d0Var, boolean z) {
            return h(Range.downTo(d0Var, BoundType.forBoolean(z)));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.size(a());
        }

        public d(NavigableMap<d0<C>, Range<C>> navigableMap, Range<d0<C>> range) {
            this.h = navigableMap;
            this.i = new e(navigableMap);
            this.j = range;
        }
    }

    /* loaded from: classes10.dex */
    public final class f extends TreeRangeSet<C> {
        private final Range<C> restriction;

        public f(Range<C> range) {
            super(new g(Range.all(), range, TreeRangeSet.this.rangesByLowerBound));
            this.restriction = range;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public void add(Range<C> range) {
            Preconditions.checkArgument(this.restriction.encloses(range), "Cannot add range %s to subRangeSet(%s)", range, this.restriction);
            TreeRangeSet.this.add(range);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public void clear() {
            TreeRangeSet.this.remove(this.restriction);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public boolean contains(C c) {
            return this.restriction.contains(c) && TreeRangeSet.this.contains(c);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public boolean encloses(Range<C> range) {
            Range rangeEnclosing;
            return (this.restriction.isEmpty() || !this.restriction.encloses(range) || (rangeEnclosing = TreeRangeSet.this.rangeEnclosing(range)) == null || rangeEnclosing.intersection(this.restriction).isEmpty()) ? false : true;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        @NullableDecl
        public Range<C> rangeContaining(C c) {
            Range<C> rangeContaining;
            if (this.restriction.contains(c) && (rangeContaining = TreeRangeSet.this.rangeContaining(c)) != null) {
                return rangeContaining.intersection(this.restriction);
            }
            return null;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.j, com.google.common.collect.RangeSet
        public void remove(Range<C> range) {
            if (range.isConnected(this.restriction)) {
                TreeRangeSet.this.remove(range.intersection(this.restriction));
            }
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.RangeSet
        public RangeSet<C> subRangeSet(Range<C> range) {
            if (range.encloses(this.restriction)) {
                return this;
            }
            if (range.isConnected(this.restriction)) {
                return new f(this.restriction.intersection(range));
            }
            return ImmutableRangeSet.of();
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<C extends Comparable<?>> extends i<d0<C>, Range<C>> {
        public final Range<d0<C>> h;
        public final Range<C> i;
        public final NavigableMap<d0<C>, Range<C>> j;
        public final NavigableMap<d0<C>, Range<C>> k;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<Map.Entry<d0<C>, Range<C>>> {
            public final /* synthetic */ Iterator j;
            public final /* synthetic */ d0 k;

            public a(Iterator it, d0 d0Var) {
                this.j = it;
                this.k = d0Var;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<d0<C>, Range<C>> computeNext() {
                if (!this.j.hasNext()) {
                    return (Map.Entry) endOfData();
                }
                Range range = (Range) this.j.next();
                if (!this.k.isLessThan(range.lowerBound)) {
                    Range intersection = range.intersection(g.this.i);
                    return Maps.immutableEntry(intersection.lowerBound, intersection);
                }
                return (Map.Entry) endOfData();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractIterator<Map.Entry<d0<C>, Range<C>>> {
            public final /* synthetic */ Iterator j;

            public b(Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<d0<C>, Range<C>> computeNext() {
                if (!this.j.hasNext()) {
                    return (Map.Entry) endOfData();
                }
                Range range = (Range) this.j.next();
                if (g.this.i.lowerBound.compareTo((d0) range.upperBound) < 0) {
                    Range intersection = range.intersection(g.this.i);
                    if (g.this.h.contains(intersection.lowerBound)) {
                        return Maps.immutableEntry(intersection.lowerBound, intersection);
                    }
                    return (Map.Entry) endOfData();
                }
                return (Map.Entry) endOfData();
            }
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<d0<C>, Range<C>>> a() {
            Iterator<Range<C>> it;
            if (this.i.isEmpty()) {
                return Iterators.f();
            }
            if (this.h.upperBound.isLessThan(this.i.lowerBound)) {
                return Iterators.f();
            }
            if (this.h.lowerBound.isLessThan(this.i.lowerBound)) {
                it = this.k.tailMap(this.i.lowerBound, false).values().iterator();
            } else {
                it = this.j.tailMap(this.h.lowerBound.endpoint(), this.h.lowerBoundType() == BoundType.CLOSED).values().iterator();
            }
            return new a(it, (d0) Ordering.natural().min(this.h.upperBound, d0.belowValue(this.i.upperBound)));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.i
        public Iterator<Map.Entry<d0<C>, Range<C>>> b() {
            if (this.i.isEmpty()) {
                return Iterators.f();
            }
            d0 d0Var = (d0) Ordering.natural().min(this.h.upperBound, d0.belowValue(this.i.upperBound));
            return new b(this.j.headMap(d0Var.endpoint(), d0Var.typeAsUpperBound() == BoundType.CLOSED).descendingMap().values().iterator());
        }

        @Override // java.util.SortedMap
        public Comparator<? super d0<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        /* renamed from: f */
        public Range<C> get(@NullableDecl Object obj) {
            if (obj instanceof d0) {
                try {
                    d0<C> d0Var = (d0) obj;
                    if (this.h.contains(d0Var) && d0Var.compareTo(this.i.lowerBound) >= 0 && d0Var.compareTo(this.i.upperBound) < 0) {
                        if (d0Var.equals(this.i.lowerBound)) {
                            Range range = (Range) Maps.R(this.j.floorEntry(d0Var));
                            if (range != null && range.upperBound.compareTo((d0) this.i.lowerBound) > 0) {
                                return range.intersection(this.i);
                            }
                        } else {
                            Range range2 = (Range) this.j.get(d0Var);
                            if (range2 != null) {
                                return range2.intersection(this.i);
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        @Override // java.util.NavigableMap
        /* renamed from: g */
        public NavigableMap<d0<C>, Range<C>> headMap(d0<C> d0Var, boolean z) {
            return i(Range.upTo(d0Var, BoundType.forBoolean(z)));
        }

        @Override // java.util.NavigableMap
        /* renamed from: h */
        public NavigableMap<d0<C>, Range<C>> subMap(d0<C> d0Var, boolean z, d0<C> d0Var2, boolean z2) {
            return i(Range.range(d0Var, BoundType.forBoolean(z), d0Var2, BoundType.forBoolean(z2)));
        }

        public final NavigableMap<d0<C>, Range<C>> i(Range<d0<C>> range) {
            if (!range.isConnected(this.h)) {
                return ImmutableSortedMap.of();
            }
            return new g(this.h.intersection(range), this.i, this.j);
        }

        @Override // java.util.NavigableMap
        /* renamed from: j */
        public NavigableMap<d0<C>, Range<C>> tailMap(d0<C> d0Var, boolean z) {
            return i(Range.downTo(d0Var, BoundType.forBoolean(z)));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.size(a());
        }

        public g(Range<d0<C>> range, Range<C> range2, NavigableMap<d0<C>, Range<C>> navigableMap) {
            this.h = (Range) Preconditions.checkNotNull(range);
            this.i = (Range) Preconditions.checkNotNull(range2);
            this.j = (NavigableMap) Preconditions.checkNotNull(navigableMap);
            this.k = new e(navigableMap);
        }
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create() {
        return new TreeRangeSet<>(new TreeMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public Range<C> rangeEnclosing(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<d0<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        if (floorEntry == null || !floorEntry.getValue().encloses(range)) {
            return null;
        }
        return floorEntry.getValue();
    }

    private void replaceRangeWithSameLowerBound(Range<C> range) {
        if (range.isEmpty()) {
            this.rangesByLowerBound.remove(range.lowerBound);
        } else {
            this.rangesByLowerBound.put(range.lowerBound, range);
        }
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public void add(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (range.isEmpty()) {
            return;
        }
        d0<C> d0Var = range.lowerBound;
        d0<C> d0Var2 = range.upperBound;
        Map.Entry<d0<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(d0Var);
        if (lowerEntry != null) {
            Range<C> value = lowerEntry.getValue();
            if (value.upperBound.compareTo(d0Var) >= 0) {
                if (value.upperBound.compareTo(d0Var2) >= 0) {
                    d0Var2 = value.upperBound;
                }
                d0Var = value.lowerBound;
            }
        }
        Map.Entry<d0<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(d0Var2);
        if (floorEntry != null) {
            Range<C> value2 = floorEntry.getValue();
            if (value2.upperBound.compareTo(d0Var2) >= 0) {
                d0Var2 = value2.upperBound;
            }
        }
        this.rangesByLowerBound.subMap(d0Var, d0Var2).clear();
        replaceRangeWithSameLowerBound(Range.create(d0Var, d0Var2));
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void addAll(RangeSet rangeSet) {
        super.addAll(rangeSet);
    }

    @Override // com.google.common.collect.RangeSet
    public Set<Range<C>> asDescendingSetOfRanges() {
        Set<Range<C>> set = this.asDescendingSetOfRanges;
        if (set == null) {
            b bVar = new b(this, this.rangesByLowerBound.descendingMap().values());
            this.asDescendingSetOfRanges = bVar;
            return bVar;
        }
        return set;
    }

    @Override // com.google.common.collect.RangeSet
    public Set<Range<C>> asRanges() {
        Set<Range<C>> set = this.asRanges;
        if (set == null) {
            b bVar = new b(this, this.rangesByLowerBound.values());
            this.asRanges = bVar;
            return bVar;
        }
        return set;
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.RangeSet
    public RangeSet<C> complement() {
        RangeSet<C> rangeSet = this.complement;
        if (rangeSet == null) {
            c cVar = new c();
            this.complement = cVar;
            return cVar;
        }
        return rangeSet;
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public boolean encloses(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<d0<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        return floorEntry != null && floorEntry.getValue().encloses(range);
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet rangeSet) {
        return super.enclosesAll(rangeSet);
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public boolean intersects(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<d0<C>, Range<C>> ceilingEntry = this.rangesByLowerBound.ceilingEntry(range.lowerBound);
        if (ceilingEntry == null || !ceilingEntry.getValue().isConnected(range) || ceilingEntry.getValue().intersection(range).isEmpty()) {
            Map.Entry<d0<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(range.lowerBound);
            return (lowerEntry == null || !lowerEntry.getValue().isConnected(range) || lowerEntry.getValue().intersection(range).isEmpty()) ? false : true;
        }
        return true;
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    @NullableDecl
    public Range<C> rangeContaining(C c2) {
        Preconditions.checkNotNull(c2);
        Map.Entry<d0<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(d0.belowValue(c2));
        if (floorEntry == null || !floorEntry.getValue().contains(c2)) {
            return null;
        }
        return floorEntry.getValue();
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public void remove(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (range.isEmpty()) {
            return;
        }
        Map.Entry<d0<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(range.lowerBound);
        if (lowerEntry != null) {
            Range<C> value = lowerEntry.getValue();
            if (value.upperBound.compareTo(range.lowerBound) >= 0) {
                if (range.hasUpperBound() && value.upperBound.compareTo(range.upperBound) >= 0) {
                    replaceRangeWithSameLowerBound(Range.create(range.upperBound, value.upperBound));
                }
                replaceRangeWithSameLowerBound(Range.create(value.lowerBound, range.lowerBound));
            }
        }
        Map.Entry<d0<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.upperBound);
        if (floorEntry != null) {
            Range<C> value2 = floorEntry.getValue();
            if (range.hasUpperBound() && value2.upperBound.compareTo(range.upperBound) >= 0) {
                replaceRangeWithSameLowerBound(Range.create(range.upperBound, value2.upperBound));
            }
        }
        this.rangesByLowerBound.subMap(range.lowerBound, range.upperBound).clear();
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void removeAll(RangeSet rangeSet) {
        super.removeAll(rangeSet);
    }

    @Override // com.google.common.collect.RangeSet
    public Range<C> span() {
        Map.Entry<d0<C>, Range<C>> firstEntry = this.rangesByLowerBound.firstEntry();
        Map.Entry<d0<C>, Range<C>> lastEntry = this.rangesByLowerBound.lastEntry();
        if (firstEntry != null) {
            return Range.create(firstEntry.getValue().lowerBound, lastEntry.getValue().upperBound);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.RangeSet
    public RangeSet<C> subRangeSet(Range<C> range) {
        return range.equals(Range.all()) ? this : new f(range);
    }

    private TreeRangeSet(NavigableMap<d0<C>, Range<C>> navigableMap) {
        this.rangesByLowerBound = navigableMap;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(RangeSet<C> rangeSet) {
        TreeRangeSet<C> create = create();
        create.addAll(rangeSet);
        return create;
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void addAll(Iterable iterable) {
        super.addAll(iterable);
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(Iterable iterable) {
        return super.enclosesAll(iterable);
    }

    @Override // com.google.common.collect.j, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void removeAll(Iterable iterable) {
        super.removeAll(iterable);
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static final class e<C extends Comparable<?>> extends i<d0<C>, Range<C>> {
        public final NavigableMap<d0<C>, Range<C>> h;
        public final Range<d0<C>> i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<Map.Entry<d0<C>, Range<C>>> {
            public final /* synthetic */ Iterator j;

            public a(Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<d0<C>, Range<C>> computeNext() {
                if (!this.j.hasNext()) {
                    return (Map.Entry) endOfData();
                }
                Range range = (Range) this.j.next();
                if (e.this.i.upperBound.isLessThan(range.upperBound)) {
                    return (Map.Entry) endOfData();
                }
                return Maps.immutableEntry(range.upperBound, range);
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractIterator<Map.Entry<d0<C>, Range<C>>> {
            public final /* synthetic */ PeekingIterator j;

            public b(PeekingIterator peekingIterator) {
                this.j = peekingIterator;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<d0<C>, Range<C>> computeNext() {
                if (!this.j.hasNext()) {
                    return (Map.Entry) endOfData();
                }
                Range range = (Range) this.j.next();
                if (e.this.i.lowerBound.isLessThan(range.upperBound)) {
                    return Maps.immutableEntry(range.upperBound, range);
                }
                return (Map.Entry) endOfData();
            }
        }

        public e(NavigableMap<d0<C>, Range<C>> navigableMap) {
            this.h = navigableMap;
            this.i = Range.all();
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<d0<C>, Range<C>>> a() {
            Iterator<Range<C>> it;
            if (!this.i.hasLowerBound()) {
                it = this.h.values().iterator();
            } else {
                Map.Entry<d0<C>, Range<C>> lowerEntry = this.h.lowerEntry(this.i.lowerEndpoint());
                if (lowerEntry == null) {
                    it = this.h.values().iterator();
                } else if (this.i.lowerBound.isLessThan(lowerEntry.getValue().upperBound)) {
                    it = this.h.tailMap(lowerEntry.getKey(), true).values().iterator();
                } else {
                    it = this.h.tailMap(this.i.lowerEndpoint(), true).values().iterator();
                }
            }
            return new a(it);
        }

        @Override // com.google.common.collect.i
        public Iterator<Map.Entry<d0<C>, Range<C>>> b() {
            Collection<Range<C>> values;
            if (this.i.hasUpperBound()) {
                values = this.h.headMap(this.i.upperEndpoint(), false).descendingMap().values();
            } else {
                values = this.h.descendingMap().values();
            }
            PeekingIterator peekingIterator = Iterators.peekingIterator(values.iterator());
            if (peekingIterator.hasNext() && this.i.upperBound.isLessThan(((Range) peekingIterator.peek()).upperBound)) {
                peekingIterator.next();
            }
            return new b(peekingIterator);
        }

        @Override // java.util.SortedMap
        public Comparator<? super d0<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: e */
        public Range<C> get(@NullableDecl Object obj) {
            Map.Entry<d0<C>, Range<C>> lowerEntry;
            if (obj instanceof d0) {
                try {
                    d0<C> d0Var = (d0) obj;
                    if (this.i.contains(d0Var) && (lowerEntry = this.h.lowerEntry(d0Var)) != null && lowerEntry.getValue().upperBound.equals(d0Var)) {
                        return lowerEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        @Override // java.util.NavigableMap
        /* renamed from: f */
        public NavigableMap<d0<C>, Range<C>> headMap(d0<C> d0Var, boolean z) {
            return h(Range.upTo(d0Var, BoundType.forBoolean(z)));
        }

        @Override // java.util.NavigableMap
        /* renamed from: g */
        public NavigableMap<d0<C>, Range<C>> subMap(d0<C> d0Var, boolean z, d0<C> d0Var2, boolean z2) {
            return h(Range.range(d0Var, BoundType.forBoolean(z), d0Var2, BoundType.forBoolean(z2)));
        }

        public final NavigableMap<d0<C>, Range<C>> h(Range<d0<C>> range) {
            if (range.isConnected(this.i)) {
                return new e(this.h, range.intersection(this.i));
            }
            return ImmutableSortedMap.of();
        }

        @Override // java.util.NavigableMap
        /* renamed from: i */
        public NavigableMap<d0<C>, Range<C>> tailMap(d0<C> d0Var, boolean z) {
            return h(Range.downTo(d0Var, BoundType.forBoolean(z)));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            if (this.i.equals(Range.all())) {
                return this.h.isEmpty();
            }
            return !a().hasNext();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            if (this.i.equals(Range.all())) {
                return this.h.size();
            }
            return Iterators.size(a());
        }

        public e(NavigableMap<d0<C>, Range<C>> navigableMap, Range<d0<C>> range) {
            this.h = navigableMap;
            this.i = range;
        }
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(Iterable<Range<C>> iterable) {
        TreeRangeSet<C> create = create();
        create.addAll(iterable);
        return create;
    }
}
