package com.google.common.collect;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(serializable = true)
/* loaded from: classes10.dex */
public final class u0<T> implements Serializable {
    private final Comparator<? super T> comparator;
    private final boolean hasLowerBound;
    private final boolean hasUpperBound;
    private final BoundType lowerBoundType;
    @NullableDecl
    private final T lowerEndpoint;
    @NullableDecl
    private transient u0<T> reverse;
    private final BoundType upperBoundType;
    @NullableDecl
    private final T upperEndpoint;

    /* JADX WARN: Multi-variable type inference failed */
    private u0(Comparator<? super T> comparator, boolean z, @NullableDecl T t, BoundType boundType, boolean z2, @NullableDecl T t2, BoundType boundType2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        this.hasLowerBound = z;
        this.hasUpperBound = z2;
        this.lowerEndpoint = t;
        this.lowerBoundType = (BoundType) Preconditions.checkNotNull(boundType);
        this.upperEndpoint = t2;
        this.upperBoundType = (BoundType) Preconditions.checkNotNull(boundType2);
        if (z) {
            comparator.compare(t, t);
        }
        if (z2) {
            comparator.compare(t2, t2);
        }
        if (z && z2) {
            int compare = comparator.compare(t, t2);
            Preconditions.checkArgument(compare <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", t, t2);
            if (compare == 0) {
                BoundType boundType3 = BoundType.OPEN;
                Preconditions.checkArgument((boundType != boundType3) | (boundType2 != boundType3));
            }
        }
    }

    public static <T> u0<T> all(Comparator<? super T> comparator) {
        BoundType boundType = BoundType.OPEN;
        return new u0<>(comparator, false, null, boundType, false, null, boundType);
    }

    public static <T> u0<T> downTo(Comparator<? super T> comparator, @NullableDecl T t, BoundType boundType) {
        return new u0<>(comparator, true, t, boundType, false, null, BoundType.OPEN);
    }

    public static <T extends Comparable> u0<T> from(Range<T> range) {
        return new u0<>(Ordering.natural(), range.hasLowerBound(), range.hasLowerBound() ? range.lowerEndpoint() : null, range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN, range.hasUpperBound(), range.hasUpperBound() ? range.upperEndpoint() : null, range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN);
    }

    public static <T> u0<T> range(Comparator<? super T> comparator, @NullableDecl T t, BoundType boundType, @NullableDecl T t2, BoundType boundType2) {
        return new u0<>(comparator, true, t, boundType, true, t2, boundType2);
    }

    public static <T> u0<T> upTo(Comparator<? super T> comparator, @NullableDecl T t, BoundType boundType) {
        return new u0<>(comparator, false, null, BoundType.OPEN, true, t, boundType);
    }

    public Comparator<? super T> comparator() {
        return this.comparator;
    }

    public boolean contains(@NullableDecl T t) {
        return (tooLow(t) || tooHigh(t)) ? false : true;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof u0) {
            u0 u0Var = (u0) obj;
            return this.comparator.equals(u0Var.comparator) && this.hasLowerBound == u0Var.hasLowerBound && this.hasUpperBound == u0Var.hasUpperBound && getLowerBoundType().equals(u0Var.getLowerBoundType()) && getUpperBoundType().equals(u0Var.getUpperBoundType()) && Objects.equal(getLowerEndpoint(), u0Var.getLowerEndpoint()) && Objects.equal(getUpperEndpoint(), u0Var.getUpperEndpoint());
        }
        return false;
    }

    public BoundType getLowerBoundType() {
        return this.lowerBoundType;
    }

    public T getLowerEndpoint() {
        return this.lowerEndpoint;
    }

    public BoundType getUpperBoundType() {
        return this.upperBoundType;
    }

    public T getUpperEndpoint() {
        return this.upperEndpoint;
    }

    public boolean hasLowerBound() {
        return this.hasLowerBound;
    }

    public boolean hasUpperBound() {
        return this.hasUpperBound;
    }

    public int hashCode() {
        return Objects.hashCode(this.comparator, getLowerEndpoint(), getLowerBoundType(), getUpperEndpoint(), getUpperBoundType());
    }

    public u0<T> intersect(u0<T> u0Var) {
        int compare;
        int compare2;
        T t;
        BoundType boundType;
        BoundType boundType2;
        int compare3;
        BoundType boundType3;
        Preconditions.checkNotNull(u0Var);
        Preconditions.checkArgument(this.comparator.equals(u0Var.comparator));
        boolean z = this.hasLowerBound;
        T lowerEndpoint = getLowerEndpoint();
        BoundType lowerBoundType = getLowerBoundType();
        if (!hasLowerBound()) {
            z = u0Var.hasLowerBound;
            lowerEndpoint = u0Var.getLowerEndpoint();
            lowerBoundType = u0Var.getLowerBoundType();
        } else if (u0Var.hasLowerBound() && ((compare = this.comparator.compare(getLowerEndpoint(), u0Var.getLowerEndpoint())) < 0 || (compare == 0 && u0Var.getLowerBoundType() == BoundType.OPEN))) {
            lowerEndpoint = u0Var.getLowerEndpoint();
            lowerBoundType = u0Var.getLowerBoundType();
        }
        boolean z2 = z;
        boolean z3 = this.hasUpperBound;
        T upperEndpoint = getUpperEndpoint();
        BoundType upperBoundType = getUpperBoundType();
        if (!hasUpperBound()) {
            z3 = u0Var.hasUpperBound;
            upperEndpoint = u0Var.getUpperEndpoint();
            upperBoundType = u0Var.getUpperBoundType();
        } else if (u0Var.hasUpperBound() && ((compare2 = this.comparator.compare(getUpperEndpoint(), u0Var.getUpperEndpoint())) > 0 || (compare2 == 0 && u0Var.getUpperBoundType() == BoundType.OPEN))) {
            upperEndpoint = u0Var.getUpperEndpoint();
            upperBoundType = u0Var.getUpperBoundType();
        }
        boolean z4 = z3;
        T t2 = upperEndpoint;
        if (z2 && z4 && ((compare3 = this.comparator.compare(lowerEndpoint, t2)) > 0 || (compare3 == 0 && lowerBoundType == (boundType3 = BoundType.OPEN) && upperBoundType == boundType3))) {
            boundType = BoundType.OPEN;
            boundType2 = BoundType.CLOSED;
            t = t2;
        } else {
            t = lowerEndpoint;
            boundType = lowerBoundType;
            boundType2 = upperBoundType;
        }
        return new u0<>(this.comparator, z2, t, boundType, z4, t2, boundType2);
    }

    public boolean isEmpty() {
        return (hasUpperBound() && tooLow(getUpperEndpoint())) || (hasLowerBound() && tooHigh(getLowerEndpoint()));
    }

    public u0<T> reverse() {
        u0<T> u0Var = this.reverse;
        if (u0Var == null) {
            u0<T> u0Var2 = new u0<>(Ordering.from(this.comparator).reverse(), this.hasUpperBound, getUpperEndpoint(), getUpperBoundType(), this.hasLowerBound, getLowerEndpoint(), getLowerBoundType());
            u0Var2.reverse = this;
            this.reverse = u0Var2;
            return u0Var2;
        }
        return u0Var;
    }

    public String toString() {
        String valueOf = String.valueOf(this.comparator);
        BoundType boundType = this.lowerBoundType;
        BoundType boundType2 = BoundType.CLOSED;
        char c = boundType == boundType2 ? '[' : HexStringBuilder.COMMENT_BEGIN_CHAR;
        String valueOf2 = String.valueOf(this.hasLowerBound ? this.lowerEndpoint : "-∞");
        String valueOf3 = String.valueOf(this.hasUpperBound ? this.upperEndpoint : "∞");
        char c2 = this.upperBoundType == boundType2 ? ']' : HexStringBuilder.COMMENT_END_CHAR;
        StringBuilder sb = new StringBuilder(valueOf.length() + 4 + valueOf2.length() + valueOf3.length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(c);
        sb.append(valueOf2);
        sb.append(',');
        sb.append(valueOf3);
        sb.append(c2);
        return sb.toString();
    }

    public boolean tooHigh(@NullableDecl T t) {
        if (hasUpperBound()) {
            int compare = this.comparator.compare(t, getUpperEndpoint());
            return ((compare == 0) & (getUpperBoundType() == BoundType.OPEN)) | (compare > 0);
        }
        return false;
    }

    public boolean tooLow(@NullableDecl T t) {
        if (hasLowerBound()) {
            int compare = this.comparator.compare(t, getLowerEndpoint());
            return ((compare == 0) & (getLowerBoundType() == BoundType.OPEN)) | (compare < 0);
        }
        return false;
    }
}
