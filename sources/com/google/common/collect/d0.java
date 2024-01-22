package com.google.common.collect;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.eclipse.paho.client.mqttv3.MqttTopic;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class d0<C extends Comparable> implements Comparable<d0<C>>, Serializable {
    private static final long serialVersionUID = 0;
    @NullableDecl
    public final C endpoint;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10590a;

        static {
            int[] iArr = new int[BoundType.values().length];
            f10590a = iArr;
            try {
                iArr[BoundType.CLOSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10590a[BoundType.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class b extends d0<Comparable<?>> {
        private static final b INSTANCE = new b();
        private static final long serialVersionUID = 0;

        private b() {
            super(null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.collect.d0, java.lang.Comparable
        public int compareTo(d0<Comparable<?>> d0Var) {
            return d0Var == this ? 0 : 1;
        }

        @Override // com.google.common.collect.d0
        public void describeAsLowerBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.d0
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append("+∞)");
        }

        @Override // com.google.common.collect.d0
        public Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override // com.google.common.collect.d0
        public Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.maxValue();
        }

        @Override // com.google.common.collect.d0
        public int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.d0
        public boolean isLessThan(Comparable<?> comparable) {
            return false;
        }

        @Override // com.google.common.collect.d0
        public Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        public String toString() {
            return "+∞";
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsLowerBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsUpperBound() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.d0
        public d0<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.d0
        public d0<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }
    }

    /* loaded from: classes10.dex */
    public static final class c<C extends Comparable> extends d0<C> {
        private static final long serialVersionUID = 0;

        public c(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        @Override // com.google.common.collect.d0
        public d0<C> canonical(DiscreteDomain<C> discreteDomain) {
            C leastValueAbove = leastValueAbove(discreteDomain);
            return leastValueAbove != null ? d0.belowValue(leastValueAbove) : d0.aboveAll();
        }

        @Override // com.google.common.collect.d0, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return super.compareTo((d0) ((d0) obj));
        }

        @Override // com.google.common.collect.d0
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append(HexStringBuilder.COMMENT_BEGIN_CHAR);
            sb.append(this.endpoint);
        }

        @Override // com.google.common.collect.d0
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(']');
        }

        @Override // com.google.common.collect.d0
        public C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        @Override // com.google.common.collect.d0
        public int hashCode() {
            return ~this.endpoint.hashCode();
        }

        @Override // com.google.common.collect.d0
        public boolean isLessThan(C c) {
            return Range.compareOrThrow(this.endpoint, c) < 0;
        }

        @Override // com.google.common.collect.d0
        public C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.next(this.endpoint);
        }

        public String toString() {
            String valueOf = String.valueOf(this.endpoint);
            StringBuilder sb = new StringBuilder(valueOf.length() + 2);
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(valueOf);
            sb.append("\\");
            return sb.toString();
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsLowerBound() {
            return BoundType.OPEN;
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsUpperBound() {
            return BoundType.CLOSED;
        }

        @Override // com.google.common.collect.d0
        public d0<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            int i = a.f10590a[boundType.ordinal()];
            if (i == 1) {
                C next = discreteDomain.next(this.endpoint);
                return next == null ? d0.belowAll() : d0.belowValue(next);
            } else if (i == 2) {
                return this;
            } else {
                throw new AssertionError();
            }
        }

        @Override // com.google.common.collect.d0
        public d0<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            int i = a.f10590a[boundType.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    C next = discreteDomain.next(this.endpoint);
                    return next == null ? d0.aboveAll() : d0.belowValue(next);
                }
                throw new AssertionError();
            }
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends d0<Comparable<?>> {
        private static final d INSTANCE = new d();
        private static final long serialVersionUID = 0;

        private d() {
            super(null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.collect.d0
        public d0<Comparable<?>> canonical(DiscreteDomain<Comparable<?>> discreteDomain) {
            try {
                return d0.belowValue(discreteDomain.minValue());
            } catch (NoSuchElementException unused) {
                return this;
            }
        }

        @Override // com.google.common.collect.d0, java.lang.Comparable
        public int compareTo(d0<Comparable<?>> d0Var) {
            return d0Var == this ? 0 : -1;
        }

        @Override // com.google.common.collect.d0
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append("(-∞");
        }

        @Override // com.google.common.collect.d0
        public void describeAsUpperBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.d0
        public Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override // com.google.common.collect.d0
        public Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.d0
        public int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.d0
        public boolean isLessThan(Comparable<?> comparable) {
            return true;
        }

        @Override // com.google.common.collect.d0
        public Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.minValue();
        }

        public String toString() {
            return "-∞";
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsLowerBound() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsUpperBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.d0
        public d0<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.d0
        public d0<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }
    }

    /* loaded from: classes10.dex */
    public static final class e<C extends Comparable> extends d0<C> {
        private static final long serialVersionUID = 0;

        public e(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        @Override // com.google.common.collect.d0, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return super.compareTo((d0) ((d0) obj));
        }

        @Override // com.google.common.collect.d0
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append('[');
            sb.append(this.endpoint);
        }

        @Override // com.google.common.collect.d0
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
        }

        @Override // com.google.common.collect.d0
        public C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.previous(this.endpoint);
        }

        @Override // com.google.common.collect.d0
        public int hashCode() {
            return this.endpoint.hashCode();
        }

        @Override // com.google.common.collect.d0
        public boolean isLessThan(C c) {
            return Range.compareOrThrow(this.endpoint, c) <= 0;
        }

        @Override // com.google.common.collect.d0
        public C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        public String toString() {
            String valueOf = String.valueOf(this.endpoint);
            StringBuilder sb = new StringBuilder(valueOf.length() + 2);
            sb.append("\\");
            sb.append(valueOf);
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            return sb.toString();
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsLowerBound() {
            return BoundType.CLOSED;
        }

        @Override // com.google.common.collect.d0
        public BoundType typeAsUpperBound() {
            return BoundType.OPEN;
        }

        @Override // com.google.common.collect.d0
        public d0<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            int i = a.f10590a[boundType.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    C previous = discreteDomain.previous(this.endpoint);
                    return previous == null ? d0.belowAll() : new c(previous);
                }
                throw new AssertionError();
            }
            return this;
        }

        @Override // com.google.common.collect.d0
        public d0<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            int i = a.f10590a[boundType.ordinal()];
            if (i == 1) {
                C previous = discreteDomain.previous(this.endpoint);
                return previous == null ? d0.aboveAll() : new c(previous);
            } else if (i == 2) {
                return this;
            } else {
                throw new AssertionError();
            }
        }
    }

    public d0(@NullableDecl C c2) {
        this.endpoint = c2;
    }

    public static <C extends Comparable> d0<C> aboveAll() {
        return b.INSTANCE;
    }

    public static <C extends Comparable> d0<C> aboveValue(C c2) {
        return new c(c2);
    }

    public static <C extends Comparable> d0<C> belowAll() {
        return d.INSTANCE;
    }

    public static <C extends Comparable> d0<C> belowValue(C c2) {
        return new e(c2);
    }

    public d0<C> canonical(DiscreteDomain<C> discreteDomain) {
        return this;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((d0) ((d0) obj));
    }

    public abstract void describeAsLowerBound(StringBuilder sb);

    public abstract void describeAsUpperBound(StringBuilder sb);

    public C endpoint() {
        return this.endpoint;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d0) {
            try {
                return compareTo((d0) ((d0) obj)) == 0;
            } catch (ClassCastException unused) {
                return false;
            }
        }
        return false;
    }

    public abstract C greatestValueBelow(DiscreteDomain<C> discreteDomain);

    public abstract int hashCode();

    public abstract boolean isLessThan(C c2);

    public abstract C leastValueAbove(DiscreteDomain<C> discreteDomain);

    public abstract BoundType typeAsLowerBound();

    public abstract BoundType typeAsUpperBound();

    public abstract d0<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    public abstract d0<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    public int compareTo(d0<C> d0Var) {
        if (d0Var == belowAll()) {
            return 1;
        }
        if (d0Var == aboveAll()) {
            return -1;
        }
        int compareOrThrow = Range.compareOrThrow(this.endpoint, d0Var.endpoint);
        return compareOrThrow != 0 ? compareOrThrow : Booleans.compare(this instanceof c, d0Var instanceof c);
    }
}
