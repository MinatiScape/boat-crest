package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class ShapePath {
    public static final float ANGLE_LEFT = 180.0f;

    /* renamed from: a  reason: collision with root package name */
    public final List<PathOperation> f10361a = new ArrayList();
    public final List<d> b = new ArrayList();
    public boolean c;
    @Deprecated
    public float currentShadowAngle;
    @Deprecated
    public float endShadowAngle;
    @Deprecated
    public float endX;
    @Deprecated
    public float endY;
    @Deprecated
    public float startX;
    @Deprecated
    public float startY;

    /* loaded from: classes10.dex */
    public static class PathArcOperation extends PathOperation {

        /* renamed from: a  reason: collision with root package name */
        public static final RectF f10362a = new RectF();
        @Deprecated
        public float bottom;
        @Deprecated
        public float left;
        @Deprecated
        public float right;
        @Deprecated
        public float startAngle;
        @Deprecated
        public float sweepAngle;
        @Deprecated
        public float top;

        public PathArcOperation(float f, float f2, float f3, float f4) {
            p(f);
            t(f2);
            q(f3);
            o(f4);
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f10362a;
            rectF.set(j(), n(), k(), i());
            path.arcTo(rectF, l(), m(), false);
            path.transform(matrix);
        }

        public final float i() {
            return this.bottom;
        }

        public final float j() {
            return this.left;
        }

        public final float k() {
            return this.right;
        }

        public final float l() {
            return this.startAngle;
        }

        public final float m() {
            return this.sweepAngle;
        }

        public final float n() {
            return this.top;
        }

        public final void o(float f) {
            this.bottom = f;
        }

        public final void p(float f) {
            this.left = f;
        }

        public final void q(float f) {
            this.right = f;
        }

        public final void r(float f) {
            this.startAngle = f;
        }

        public final void s(float f) {
            this.sweepAngle = f;
        }

        public final void t(float f) {
            this.top = f;
        }
    }

    /* loaded from: classes10.dex */
    public static class PathCubicOperation extends PathOperation {

        /* renamed from: a  reason: collision with root package name */
        public float f10363a;
        public float b;
        public float c;
        public float d;
        public float e;
        public float f;

        public PathCubicOperation(float f, float f2, float f3, float f4, float f5, float f6) {
            a(f);
            c(f2);
            b(f3);
            d(f4);
            e(f5);
            f(f6);
        }

        public final void a(float f) {
            this.f10363a = f;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.cubicTo(this.f10363a, this.b, this.c, this.d, this.e, this.f);
            path.transform(matrix);
        }

        public final void b(float f) {
            this.c = f;
        }

        public final void c(float f) {
            this.b = f;
        }

        public final void d(float f) {
            this.d = f;
        }

        public final void e(float f) {
            this.e = f;
        }

        public final void f(float f) {
            this.f = f;
        }
    }

    /* loaded from: classes10.dex */
    public static class PathLineOperation extends PathOperation {

        /* renamed from: a  reason: collision with root package name */
        public float f10364a;
        public float b;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f10364a, this.b);
            path.transform(matrix);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class PathOperation {
        public final Matrix matrix = new Matrix();

        public abstract void applyToPath(Matrix matrix, Path path);
    }

    /* loaded from: classes10.dex */
    public static class PathQuadOperation extends PathOperation {
        @Deprecated
        public float controlX;
        @Deprecated
        public float controlY;
        @Deprecated
        public float endX;
        @Deprecated
        public float endY;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(e(), f(), g(), h());
            path.transform(matrix);
        }

        public final float e() {
            return this.controlX;
        }

        public final float f() {
            return this.controlY;
        }

        public final float g() {
            return this.endX;
        }

        public final float h() {
            return this.endY;
        }

        public final void i(float f) {
            this.controlX = f;
        }

        public final void j(float f) {
            this.controlY = f;
        }

        public final void k(float f) {
            this.endX = f;
        }

        public final void l(float f) {
            this.endY = f;
        }
    }

    /* loaded from: classes10.dex */
    public class a extends d {
        public final /* synthetic */ List b;
        public final /* synthetic */ Matrix c;

        public a(ShapePath shapePath, List list, Matrix matrix) {
            this.b = list;
            this.c = matrix;
        }

        @Override // com.google.android.material.shape.ShapePath.d
        public void a(Matrix matrix, ShadowRenderer shadowRenderer, int i, Canvas canvas) {
            for (d dVar : this.b) {
                dVar.a(this.c, shadowRenderer, i, canvas);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class b extends d {
        public final PathArcOperation b;

        public b(PathArcOperation pathArcOperation) {
            this.b = pathArcOperation;
        }

        @Override // com.google.android.material.shape.ShapePath.d
        public void a(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i, @NonNull Canvas canvas) {
            shadowRenderer.drawCornerShadow(canvas, matrix, new RectF(this.b.j(), this.b.n(), this.b.k(), this.b.i()), i, this.b.l(), this.b.m());
        }
    }

    /* loaded from: classes10.dex */
    public static class c extends d {
        public final PathLineOperation b;
        public final float c;
        public final float d;

        public c(PathLineOperation pathLineOperation, float f, float f2) {
            this.b = pathLineOperation;
            this.c = f;
            this.d = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.d
        public void a(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i, @NonNull Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.b.b - this.d, this.b.f10364a - this.c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.c, this.d);
            matrix2.preRotate(c());
            shadowRenderer.drawEdgeShadow(canvas, matrix2, rectF, i);
        }

        public float c() {
            return (float) Math.toDegrees(Math.atan((this.b.b - this.d) / (this.b.f10364a - this.c)));
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class d {

        /* renamed from: a  reason: collision with root package name */
        public static final Matrix f10365a = new Matrix();

        public abstract void a(Matrix matrix, ShadowRenderer shadowRenderer, int i, Canvas canvas);

        public final void b(ShadowRenderer shadowRenderer, int i, Canvas canvas) {
            a(f10365a, shadowRenderer, i, canvas);
        }
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    public final void a(float f) {
        if (e() == f) {
            return;
        }
        float e = ((f - e()) + 360.0f) % 360.0f;
        if (e > 180.0f) {
            return;
        }
        PathArcOperation pathArcOperation = new PathArcOperation(g(), h(), g(), h());
        pathArcOperation.r(e());
        pathArcOperation.s(e);
        this.b.add(new b(pathArcOperation));
        k(f);
    }

    public void addArc(float f, float f2, float f3, float f4, float f5, float f6) {
        PathArcOperation pathArcOperation = new PathArcOperation(f, f2, f3, f4);
        pathArcOperation.r(f5);
        pathArcOperation.s(f6);
        this.f10361a.add(pathArcOperation);
        b bVar = new b(pathArcOperation);
        float f7 = f5 + f6;
        boolean z = f6 < 0.0f;
        if (z) {
            f5 = (f5 + 180.0f) % 360.0f;
        }
        b(bVar, f5, z ? (180.0f + f7) % 360.0f : f7);
        double d2 = f7;
        m(((f + f3) * 0.5f) + (((f3 - f) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        n(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public void applyToPath(Matrix matrix, Path path) {
        int size = this.f10361a.size();
        for (int i = 0; i < size; i++) {
            this.f10361a.get(i).applyToPath(matrix, path);
        }
    }

    public final void b(d dVar, float f, float f2) {
        a(f);
        this.b.add(dVar);
        k(f2);
    }

    public boolean c() {
        return this.c;
    }

    @RequiresApi(21)
    public void cubicToPoint(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f10361a.add(new PathCubicOperation(f, f2, f3, f4, f5, f6));
        this.c = true;
        m(f5);
        n(f6);
    }

    @NonNull
    public d d(Matrix matrix) {
        a(f());
        return new a(this, new ArrayList(this.b), new Matrix(matrix));
    }

    public final float e() {
        return this.currentShadowAngle;
    }

    public final float f() {
        return this.endShadowAngle;
    }

    public float g() {
        return this.endX;
    }

    public float h() {
        return this.endY;
    }

    public float i() {
        return this.startX;
    }

    public float j() {
        return this.startY;
    }

    public final void k(float f) {
        this.currentShadowAngle = f;
    }

    public final void l(float f) {
        this.endShadowAngle = f;
    }

    public void lineTo(float f, float f2) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.f10364a = f;
        pathLineOperation.b = f2;
        this.f10361a.add(pathLineOperation);
        c cVar = new c(pathLineOperation, g(), h());
        b(cVar, cVar.c() + 270.0f, cVar.c() + 270.0f);
        m(f);
        n(f2);
    }

    public final void m(float f) {
        this.endX = f;
    }

    public final void n(float f) {
        this.endY = f;
    }

    public final void o(float f) {
        this.startX = f;
    }

    public final void p(float f) {
        this.startY = f;
    }

    @RequiresApi(21)
    public void quadToPoint(float f, float f2, float f3, float f4) {
        PathQuadOperation pathQuadOperation = new PathQuadOperation();
        pathQuadOperation.i(f);
        pathQuadOperation.j(f2);
        pathQuadOperation.k(f3);
        pathQuadOperation.l(f4);
        this.f10361a.add(pathQuadOperation);
        this.c = true;
        m(f3);
        n(f4);
    }

    public void reset(float f, float f2) {
        reset(f, f2, 270.0f, 0.0f);
    }

    public void reset(float f, float f2, float f3, float f4) {
        o(f);
        p(f2);
        m(f);
        n(f2);
        k(f3);
        l((f3 + f4) % 360.0f);
        this.f10361a.clear();
        this.b.clear();
        this.c = false;
    }

    public ShapePath(float f, float f2) {
        reset(f, f2);
    }
}
