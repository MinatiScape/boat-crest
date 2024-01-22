package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.PointF;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class PointKt {
    public static final int component1(@NotNull Point point) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        return point.x;
    }

    public static final int component2(@NotNull Point point) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        return point.y;
    }

    @NotNull
    public static final Point minus(@NotNull Point point, @NotNull Point p) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        Point point2 = new Point(point.x, point.y);
        point2.offset(-p.x, -p.y);
        return point2;
    }

    @NotNull
    public static final Point plus(@NotNull Point point, @NotNull Point p) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        Point point2 = new Point(point.x, point.y);
        point2.offset(p.x, p.y);
        return point2;
    }

    @NotNull
    public static final Point toPoint(@NotNull PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        return new Point((int) pointF.x, (int) pointF.y);
    }

    @NotNull
    public static final PointF toPointF(@NotNull Point point) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        return new PointF(point);
    }

    @NotNull
    public static final Point unaryMinus(@NotNull Point point) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        return new Point(-point.x, -point.y);
    }

    public static final float component1(@NotNull PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        return pointF.x;
    }

    public static final float component2(@NotNull PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        return pointF.y;
    }

    @NotNull
    public static final PointF unaryMinus(@NotNull PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        return new PointF(-pointF.x, -pointF.y);
    }

    @NotNull
    public static final PointF minus(@NotNull PointF pointF, @NotNull PointF p) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        pointF2.offset(-p.x, -p.y);
        return pointF2;
    }

    @NotNull
    public static final PointF plus(@NotNull PointF pointF, @NotNull PointF p) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        pointF2.offset(p.x, p.y);
        return pointF2;
    }

    @NotNull
    public static final Point minus(@NotNull Point point, int i) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        Point point2 = new Point(point.x, point.y);
        int i2 = -i;
        point2.offset(i2, i2);
        return point2;
    }

    @NotNull
    public static final Point plus(@NotNull Point point, int i) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        Point point2 = new Point(point.x, point.y);
        point2.offset(i, i);
        return point2;
    }

    @NotNull
    public static final PointF minus(@NotNull PointF pointF, float f) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        float f2 = -f;
        pointF2.offset(f2, f2);
        return pointF2;
    }

    @NotNull
    public static final PointF plus(@NotNull PointF pointF, float f) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        pointF2.offset(f, f);
        return pointF2;
    }
}
