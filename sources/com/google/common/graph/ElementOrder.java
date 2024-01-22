package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.Immutable;
import java.util.Comparator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Immutable
@Beta
/* loaded from: classes10.dex */
public final class ElementOrder<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Type f10619a;
    @NullableDecl
    public final Comparator<T> b;

    /* loaded from: classes10.dex */
    public enum Type {
        UNORDERED,
        STABLE,
        INSERTION,
        SORTED
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10620a;

        static {
            int[] iArr = new int[Type.values().length];
            f10620a = iArr;
            try {
                iArr[Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10620a[Type.INSERTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10620a[Type.STABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10620a[Type.SORTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public ElementOrder(Type type, @NullableDecl Comparator<T> comparator) {
        this.f10619a = (Type) Preconditions.checkNotNull(type);
        this.b = comparator;
        Preconditions.checkState((type == Type.SORTED) == (comparator != null));
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, Ordering.natural());
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator) {
        return new ElementOrder<>(Type.SORTED, (Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <S> ElementOrder<S> stable() {
        return new ElementOrder<>(Type.STABLE, null);
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T1 extends T> ElementOrder<T1> a() {
        return this;
    }

    public <K extends T, V> Map<K, V> b(int i) {
        int i2 = a.f10620a[this.f10619a.ordinal()];
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                return Maps.newLinkedHashMapWithExpectedSize(i);
            }
            if (i2 == 4) {
                return Maps.newTreeMap(comparator());
            }
            throw new AssertionError();
        }
        return Maps.newHashMapWithExpectedSize(i);
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator = this.b;
        if (comparator != null) {
            return comparator;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ElementOrder) {
            ElementOrder elementOrder = (ElementOrder) obj;
            return this.f10619a == elementOrder.f10619a && Objects.equal(this.b, elementOrder.b);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f10619a, this.b);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper(this).add("type", this.f10619a);
        Comparator<T> comparator = this.b;
        if (comparator != null) {
            add.add("comparator", comparator);
        }
        return add.toString();
    }

    public Type type() {
        return this.f10619a;
    }
}
