package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
/* loaded from: classes10.dex */
public class ShapeAppearancePathProvider {

    /* renamed from: a  reason: collision with root package name */
    public final ShapePath[] f10358a = new ShapePath[4];
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final ShapePath g = new ShapePath();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public final Path j = new Path();
    public final Path k = new Path();
    public boolean l = true;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public interface PathListener {
        void onCornerPathCreated(ShapePath shapePath, Matrix matrix, int i);

        void onEdgePathCreated(ShapePath shapePath, Matrix matrix, int i);
    }

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ShapeAppearancePathProvider f10359a = new ShapeAppearancePathProvider();
    }

    /* loaded from: classes10.dex */
    public static final class b {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final ShapeAppearanceModel f10360a;
        @NonNull
        public final Path b;
        @NonNull
        public final RectF c;
        @Nullable
        public final PathListener d;
        public final float e;

        public b(@NonNull ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, @Nullable PathListener pathListener, Path path) {
            this.d = pathListener;
            this.f10360a = shapeAppearanceModel;
            this.e = f;
            this.c = rectF;
            this.b = path;
        }
    }

    public ShapeAppearancePathProvider() {
        for (int i = 0; i < 4; i++) {
            this.f10358a[i] = new ShapePath();
            this.b[i] = new Matrix();
            this.c[i] = new Matrix();
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public static ShapeAppearancePathProvider getInstance() {
        return a.f10359a;
    }

    public final float a(int i) {
        return (i + 1) * 90;
    }

    public final void b(@NonNull b bVar, int i) {
        this.h[0] = this.f10358a[i].i();
        this.h[1] = this.f10358a[i].j();
        this.b[i].mapPoints(this.h);
        if (i == 0) {
            Path path = bVar.b;
            float[] fArr = this.h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.b;
            float[] fArr2 = this.h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f10358a[i].applyToPath(this.b[i], bVar.b);
        PathListener pathListener = bVar.d;
        if (pathListener != null) {
            pathListener.onCornerPathCreated(this.f10358a[i], this.b[i], i);
        }
    }

    public final void c(@NonNull b bVar, int i) {
        int i2 = (i + 1) % 4;
        this.h[0] = this.f10358a[i].g();
        this.h[1] = this.f10358a[i].h();
        this.b[i].mapPoints(this.h);
        this.i[0] = this.f10358a[i2].i();
        this.i[1] = this.f10358a[i2].j();
        this.b[i2].mapPoints(this.i);
        float[] fArr = this.h;
        float f = fArr[0];
        float[] fArr2 = this.i;
        float max = Math.max(((float) Math.hypot(f - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, 0.0f);
        float g = g(bVar.c, i);
        this.g.reset(0.0f, 0.0f);
        EdgeTreatment h = h(i, bVar.f10360a);
        h.getEdgePath(max, g, bVar.e, this.g);
        this.j.reset();
        this.g.applyToPath(this.c[i], this.j);
        if (this.l && Build.VERSION.SDK_INT >= 19 && (h.a() || i(this.j, i) || i(this.j, i2))) {
            Path path = this.j;
            path.op(path, this.f, Path.Op.DIFFERENCE);
            this.h[0] = this.g.i();
            this.h[1] = this.g.j();
            this.c[i].mapPoints(this.h);
            Path path2 = this.e;
            float[] fArr3 = this.h;
            path2.moveTo(fArr3[0], fArr3[1]);
            this.g.applyToPath(this.c[i], this.e);
        } else {
            this.g.applyToPath(this.c[i], bVar.b);
        }
        PathListener pathListener = bVar.d;
        if (pathListener != null) {
            pathListener.onEdgePathCreated(this.g, this.c[i], i);
        }
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, @NonNull Path path) {
        calculatePath(shapeAppearanceModel, f, rectF, null, path);
    }

    public final void d(int i, @NonNull RectF rectF, @NonNull PointF pointF) {
        if (i == 1) {
            pointF.set(rectF.right, rectF.bottom);
        } else if (i == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    public final CornerSize e(int i, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return shapeAppearanceModel.getTopRightCornerSize();
                }
                return shapeAppearanceModel.getTopLeftCornerSize();
            }
            return shapeAppearanceModel.getBottomLeftCornerSize();
        }
        return shapeAppearanceModel.getBottomRightCornerSize();
    }

    public final CornerTreatment f(int i, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return shapeAppearanceModel.getTopRightCorner();
                }
                return shapeAppearanceModel.getTopLeftCorner();
            }
            return shapeAppearanceModel.getBottomLeftCorner();
        }
        return shapeAppearanceModel.getBottomRightCorner();
    }

    public final float g(@NonNull RectF rectF, int i) {
        float[] fArr = this.h;
        ShapePath[] shapePathArr = this.f10358a;
        fArr[0] = shapePathArr[i].endX;
        fArr[1] = shapePathArr[i].endY;
        this.b[i].mapPoints(fArr);
        if (i != 1 && i != 3) {
            return Math.abs(rectF.centerY() - this.h[1]);
        }
        return Math.abs(rectF.centerX() - this.h[0]);
    }

    public final EdgeTreatment h(int i, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return shapeAppearanceModel.getRightEdge();
                }
                return shapeAppearanceModel.getTopEdge();
            }
            return shapeAppearanceModel.getLeftEdge();
        }
        return shapeAppearanceModel.getBottomEdge();
    }

    @RequiresApi(19)
    public final boolean i(Path path, int i) {
        this.k.reset();
        this.f10358a[i].applyToPath(this.b[i], this.k);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.k.computeBounds(rectF, true);
        path.op(this.k, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    public final void j(@NonNull b bVar, int i) {
        f(i, bVar.f10360a).getCornerPath(this.f10358a[i], 90.0f, bVar.e, bVar.c, e(i, bVar.f10360a));
        float a2 = a(i);
        this.b[i].reset();
        d(i, bVar.c, this.d);
        Matrix matrix = this.b[i];
        PointF pointF = this.d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.b[i].preRotate(a2);
    }

    public void k(boolean z) {
        this.l = z;
    }

    public final void l(int i) {
        this.h[0] = this.f10358a[i].g();
        this.h[1] = this.f10358a[i].h();
        this.b[i].mapPoints(this.h);
        float a2 = a(i);
        this.c[i].reset();
        Matrix matrix = this.c[i];
        float[] fArr = this.h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.c[i].preRotate(a2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, PathListener pathListener, @NonNull Path path) {
        path.rewind();
        this.e.rewind();
        this.f.rewind();
        this.f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(shapeAppearanceModel, f, rectF, pathListener, path);
        for (int i = 0; i < 4; i++) {
            j(bVar, i);
            l(i);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            b(bVar, i2);
            c(bVar, i2);
        }
        path.close();
        this.e.close();
        if (Build.VERSION.SDK_INT < 19 || this.e.isEmpty()) {
            return;
        }
        path.op(this.e, Path.Op.UNION);
    }
}
