package kotlin.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class e implements OpenEndRange<Double> {
    public final double h;
    public final double i;

    public e(double d, double d2) {
        this.h = d;
        this.i = d2;
    }

    public boolean a(double d) {
        return d >= this.h && d < this.i;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    /* renamed from: b */
    public Double getEndExclusive() {
        return Double.valueOf(this.i);
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    /* renamed from: c */
    public Double getStart() {
        return Double.valueOf(this.h);
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ boolean contains(Double d) {
        return a(d.doubleValue());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof e) {
            if (isEmpty() && ((e) obj).isEmpty()) {
                return true;
            }
            e eVar = (e) obj;
            if (this.h == eVar.h) {
                if (this.i == eVar.i) {
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

    @Override // kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return this.h >= this.i;
    }

    @NotNull
    public String toString() {
        return this.h + "..<" + this.i;
    }
}
