package kotlin.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class f implements OpenEndRange<Float> {
    public final float h;
    public final float i;

    public f(float f, float f2) {
        this.h = f;
        this.i = f2;
    }

    public boolean a(float f) {
        return f >= this.h && f < this.i;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    /* renamed from: b */
    public Float getEndExclusive() {
        return Float.valueOf(this.i);
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    /* renamed from: c */
    public Float getStart() {
        return Float.valueOf(this.h);
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ boolean contains(Float f) {
        return a(f.floatValue());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof f) {
            if (isEmpty() && ((f) obj).isEmpty()) {
                return true;
            }
            f fVar = (f) obj;
            if (this.h == fVar.h) {
                if (this.i == fVar.i) {
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

    @Override // kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return this.h >= this.i;
    }

    @NotNull
    public String toString() {
        return this.h + "..<" + this.i;
    }
}
