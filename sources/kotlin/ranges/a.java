package kotlin.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class a implements ClosedFloatingPointRange<Double> {
    public final double h;
    public final double i;

    public a(double d, double d2) {
        this.h = d;
        this.i = d2;
    }

    public boolean a(double d) {
        return d >= this.h && d <= this.i;
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    /* renamed from: b */
    public Double getEndInclusive() {
        return Double.valueOf(this.i);
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    /* renamed from: c */
    public Double getStart() {
        return Double.valueOf(this.h);
    }

    @Override // kotlin.ranges.ClosedFloatingPointRange, kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return a(((Number) comparable).doubleValue());
    }

    public boolean d(double d, double d2) {
        return d <= d2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof a) {
            if (isEmpty() && ((a) obj).isEmpty()) {
                return true;
            }
            a aVar = (a) obj;
            if (this.h == aVar.h) {
                if (this.i == aVar.i) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (Double.hashCode(this.h) * 31) + Double.hashCode(this.i);
    }

    @Override // kotlin.ranges.ClosedFloatingPointRange, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return this.h > this.i;
    }

    @Override // kotlin.ranges.ClosedFloatingPointRange
    public /* bridge */ /* synthetic */ boolean lessThanOrEquals(Double d, Double d2) {
        return d(d.doubleValue(), d2.doubleValue());
    }

    @NotNull
    public String toString() {
        return this.h + ".." + this.i;
    }
}
