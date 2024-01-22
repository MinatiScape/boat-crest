package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class MultimapBuilder<K0, V0> {

    /* loaded from: classes10.dex */
    public static abstract class MultimapBuilderWithKeys<K0> {

        /* loaded from: classes10.dex */
        public class a extends ListMultimapBuilder<K0, Object> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ int f10571a;

            public a(int i) {
                this.f10571a = i;
            }

            @Override // com.google.common.collect.MultimapBuilder.ListMultimapBuilder, com.google.common.collect.MultimapBuilder
            public <K extends K0, V> ListMultimap<K, V> build() {
                return Multimaps.newListMultimap(MultimapBuilderWithKeys.this.a(), new e(this.f10571a));
            }
        }

        /* loaded from: classes10.dex */
        public class b extends ListMultimapBuilder<K0, Object> {
            public b() {
            }

            @Override // com.google.common.collect.MultimapBuilder.ListMultimapBuilder, com.google.common.collect.MultimapBuilder
            public <K extends K0, V> ListMultimap<K, V> build() {
                return Multimaps.newListMultimap(MultimapBuilderWithKeys.this.a(), i.instance());
            }
        }

        /* loaded from: classes10.dex */
        public class c extends SetMultimapBuilder<K0, Object> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ int f10573a;

            public c(int i) {
                this.f10573a = i;
            }

            @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
            public <K extends K0, V> SetMultimap<K, V> build() {
                return Multimaps.newSetMultimap(MultimapBuilderWithKeys.this.a(), new g(this.f10573a));
            }
        }

        /* loaded from: classes10.dex */
        public class d extends SetMultimapBuilder<K0, Object> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ int f10574a;

            public d(int i) {
                this.f10574a = i;
            }

            @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
            public <K extends K0, V> SetMultimap<K, V> build() {
                return Multimaps.newSetMultimap(MultimapBuilderWithKeys.this.a(), new h(this.f10574a));
            }
        }

        /* loaded from: classes10.dex */
        public class f extends SetMultimapBuilder<K0, V0> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Class f10576a;

            public f(Class cls) {
                this.f10576a = cls;
            }

            @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
            public <K extends K0, V extends V0> SetMultimap<K, V> build() {
                return Multimaps.newSetMultimap(MultimapBuilderWithKeys.this.a(), new f(this.f10576a));
            }
        }

        public abstract <K extends K0, V> Map<K, Collection<V>> a();

        public ListMultimapBuilder<K0, Object> arrayListValues() {
            return arrayListValues(2);
        }

        public <V0 extends Enum<V0>> SetMultimapBuilder<K0, V0> enumSetValues(Class<V0> cls) {
            Preconditions.checkNotNull(cls, "valueClass");
            return new f(cls);
        }

        public SetMultimapBuilder<K0, Object> hashSetValues() {
            return hashSetValues(2);
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues() {
            return linkedHashSetValues(2);
        }

        public ListMultimapBuilder<K0, Object> linkedListValues() {
            return new b();
        }

        public SortedSetMultimapBuilder<K0, Comparable> treeSetValues() {
            return (SortedSetMultimapBuilder<K0, V0>) treeSetValues(Ordering.natural());
        }

        /* loaded from: classes10.dex */
        public class e extends SortedSetMultimapBuilder<K0, V0> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Comparator f10575a;

            public e(Comparator comparator) {
                this.f10575a = comparator;
            }

            @Override // com.google.common.collect.MultimapBuilder.SortedSetMultimapBuilder, com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
            public <K extends K0, V extends V0> SortedSetMultimap<K, V> build() {
                return Multimaps.newSortedSetMultimap(MultimapBuilderWithKeys.this.a(), new j(this.f10575a));
            }
        }

        public ListMultimapBuilder<K0, Object> arrayListValues(int i) {
            u.b(i, "expectedValuesPerKey");
            return new a(i);
        }

        public SetMultimapBuilder<K0, Object> hashSetValues(int i) {
            u.b(i, "expectedValuesPerKey");
            return new c(i);
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues(int i) {
            u.b(i, "expectedValuesPerKey");
            return new d(i);
        }

        public <V0> SortedSetMultimapBuilder<K0, V0> treeSetValues(Comparator<V0> comparator) {
            Preconditions.checkNotNull(comparator, "comparator");
            return new e(comparator);
        }
    }

    /* loaded from: classes10.dex */
    public class a extends MultimapBuilderWithKeys<Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f10577a;

        public a(int i) {
            this.f10577a = i;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K, V> Map<K, Collection<V>> a() {
            return r1.c(this.f10577a);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends MultimapBuilderWithKeys<Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f10578a;

        public b(int i) {
            this.f10578a = i;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K, V> Map<K, Collection<V>> a() {
            return r1.e(this.f10578a);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends MultimapBuilderWithKeys<K0> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Comparator f10579a;

        public c(Comparator comparator) {
            this.f10579a = comparator;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K extends K0, V> Map<K, Collection<V>> a() {
            return new TreeMap(this.f10579a);
        }
    }

    /* loaded from: classes10.dex */
    public class d extends MultimapBuilderWithKeys<K0> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Class f10580a;

        public d(Class cls) {
            this.f10580a = cls;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K extends K0, V> Map<K, Collection<V>> a() {
            return new EnumMap(this.f10580a);
        }
    }

    /* loaded from: classes10.dex */
    public static final class e<V> implements Supplier<List<V>>, Serializable {
        private final int expectedValuesPerKey;

        public e(int i) {
            this.expectedValuesPerKey = u.b(i, "expectedValuesPerKey");
        }

        @Override // com.google.common.base.Supplier
        public List<V> get() {
            return new ArrayList(this.expectedValuesPerKey);
        }
    }

    /* loaded from: classes10.dex */
    public static final class f<V extends Enum<V>> implements Supplier<Set<V>>, Serializable {
        private final Class<V> clazz;

        public f(Class<V> cls) {
            this.clazz = (Class) Preconditions.checkNotNull(cls);
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return EnumSet.noneOf(this.clazz);
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<V> implements Supplier<Set<V>>, Serializable {
        private final int expectedValuesPerKey;

        public g(int i) {
            this.expectedValuesPerKey = u.b(i, "expectedValuesPerKey");
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return r1.d(this.expectedValuesPerKey);
        }
    }

    /* loaded from: classes10.dex */
    public static final class h<V> implements Supplier<Set<V>>, Serializable {
        private final int expectedValuesPerKey;

        public h(int i) {
            this.expectedValuesPerKey = u.b(i, "expectedValuesPerKey");
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return r1.f(this.expectedValuesPerKey);
        }
    }

    /* loaded from: classes10.dex */
    public enum i implements Supplier<List<Object>> {
        INSTANCE;

        public static <V> Supplier<List<V>> instance() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Supplier
        public List<Object> get() {
            return new LinkedList();
        }
    }

    /* loaded from: classes10.dex */
    public static final class j<V> implements Supplier<SortedSet<V>>, Serializable {
        private final Comparator<? super V> comparator;

        public j(Comparator<? super V> comparator) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        @Override // com.google.common.base.Supplier
        public SortedSet<V> get() {
            return new TreeSet(this.comparator);
        }
    }

    public /* synthetic */ MultimapBuilder(a aVar) {
        this();
    }

    public static <K0 extends Enum<K0>> MultimapBuilderWithKeys<K0> enumKeys(Class<K0> cls) {
        Preconditions.checkNotNull(cls);
        return new d(cls);
    }

    public static MultimapBuilderWithKeys<Object> hashKeys() {
        return hashKeys(8);
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys() {
        return linkedHashKeys(8);
    }

    public static MultimapBuilderWithKeys<Comparable> treeKeys() {
        return treeKeys(Ordering.natural());
    }

    public abstract <K extends K0, V extends V0> Multimap<K, V> build();

    public <K extends K0, V extends V0> Multimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
        Multimap<K, V> build = build();
        build.putAll(multimap);
        return build;
    }

    /* loaded from: classes10.dex */
    public static abstract class ListMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        public ListMultimapBuilder() {
            super(null);
        }

        @Override // com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> ListMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> ListMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (ListMultimap) super.build((Multimap) multimap);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class SetMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        public SetMultimapBuilder() {
            super(null);
        }

        @Override // com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> SetMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> SetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (SetMultimap) super.build((Multimap) multimap);
        }
    }

    public MultimapBuilder() {
    }

    public static MultimapBuilderWithKeys<Object> hashKeys(int i2) {
        u.b(i2, "expectedKeys");
        return new a(i2);
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys(int i2) {
        u.b(i2, "expectedKeys");
        return new b(i2);
    }

    public static <K0> MultimapBuilderWithKeys<K0> treeKeys(Comparator<K0> comparator) {
        Preconditions.checkNotNull(comparator);
        return new c(comparator);
    }

    /* loaded from: classes10.dex */
    public static abstract class SortedSetMultimapBuilder<K0, V0> extends SetMultimapBuilder<K0, V0> {
        @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> SortedSetMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> SortedSetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (SortedSetMultimap) super.build((Multimap) multimap);
        }
    }
}
