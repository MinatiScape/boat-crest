package kotlin.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class b implements ClosedFloatingPointRange<Float> {
    public final float h;
    public final float i;

    public b(float f, float f2) {
        this.h = f;
        this.i = f2;
    }

    public boolean a(float f) {
        return f >= this.h && f <= this.i;
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    /* renamed from: b */
    public Float getEndInclusive() {
        return Float.valueOf(this.i);
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    /* renamed from: c */
    public Float getStart() {
        return Float.valueOf(this.h);
    }

    @Override // kotlin.ranges.ClosedFloatingPointRange, kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return a(((Number) comparable).floatValue());
    }

    public boolean d(float f, float f2) {
        return f <= f2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof b) {
            if (isEmpty() && ((b) obj).isEmpty()) {
                return true;
            }
            b bVar = (b) obj;
            if (this.h == bVar.h) {
                if (this.i == bVar.i) {
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
        return (Float.hashCode(this.h) * 31) + Float.hashCode(this.i);
    }

    @Override // kotlin.ranges.ClosedFloatingPointRange, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return this.h > this.i;
    }

    @Override // kotlin.ranges.ClosedFloatingPointRange
    public /* bridge */ /* synthetic */ boolean lessThanOrEquals(Float f, Float f2) {
        return d(f.floatValue(), f2.floatValue());
    }

    @NotNull
    public String toString() {
        return this.h + ".." + this.i;
    }
}
