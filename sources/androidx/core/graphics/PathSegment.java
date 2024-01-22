package androidx.core.graphics;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
/* loaded from: classes.dex */
public final class PathSegment {

    /* renamed from: a  reason: collision with root package name */
    public final PointF f1042a;
    public final float b;
    public final PointF c;
    public final float d;

    public PathSegment(@NonNull PointF pointF, float f, @NonNull PointF pointF2, float f2) {
        this.f1042a = (PointF) Preconditions.checkNotNull(pointF, "start == null");
        this.b = f;
        this.c = (PointF) Preconditions.checkNotNull(pointF2, "end == null");
        this.d = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PathSegment) {
            PathSegment pathSegment = (PathSegment) obj;
            return Float.compare(this.b, pathSegment.b) == 0 && Float.compare(this.d, pathSegment.d) == 0 && this.f1042a.equals(pathSegment.f1042a) && this.c.equals(pathSegment.c);
        }
        return false;
    }

    @NonNull
    public PointF getEnd() {
        return this.c;
    }

    public float getEndFraction() {
        return this.d;
    }

    @NonNull
    public PointF getStart() {
        return this.f1042a;
    }

    public float getStartFraction() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = this.f1042a.hashCode() * 31;
        float f = this.b;
        int floatToIntBits = (((hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + this.c.hashCode()) * 31;
        float f2 = this.d;
        return floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public String toString() {
        return "PathSegment{start=" + this.f1042a + ", startFraction=" + this.b + ", end=" + this.c + ", endFraction=" + this.d + '}';
    }
}
