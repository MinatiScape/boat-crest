package com.mappls.sdk.plugin.directions.view;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.Stack;
/* loaded from: classes11.dex */
public class c {

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f13094a;

        static {
            int[] iArr = new int[m.values().length];
            f13094a = iArr;
            try {
                iArr[m.AspectFit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13094a[m.AspectFill.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13094a[m.Center.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13095a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
        public static RectF i = new RectF();
        public static Path j = new Path();
        public static RectF k = new RectF();
        public static Path l = new Path();
    }

    /* renamed from: com.mappls.sdk.plugin.directions.view.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0658c {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13096a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
        public static RectF i = new RectF();
        public static Path j = new Path();
        public static RectF k = new RectF();
        public static Path l = new Path();
    }

    /* loaded from: classes11.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13097a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
    }

    /* loaded from: classes11.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13098a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
    }

    /* loaded from: classes11.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13099a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
    }

    /* loaded from: classes11.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13100a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
        public static RectF i = new RectF();
    }

    /* loaded from: classes11.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13101a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
    }

    /* loaded from: classes11.dex */
    public static class i {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13102a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
        public static RectF i = new RectF();
        public static Path j = new Path();
    }

    /* loaded from: classes11.dex */
    public static class j {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13103a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
        public static RectF i = new RectF();
        public static Path j = new Path();
    }

    /* loaded from: classes11.dex */
    public static class k {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13104a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
        public static RectF i = new RectF();
        public static Path j = new Path();
    }

    /* loaded from: classes11.dex */
    public static class l {

        /* renamed from: a  reason: collision with root package name */
        public static Paint f13105a = new Paint();
        public static RectF b = new RectF(0.0f, 0.0f, 32.0f, 32.0f);
        public static RectF c = new RectF();
        public static RectF d = new RectF();
        public static RectF e = new RectF();
        public static Path f = new Path();
        public static RectF g = new RectF();
        public static Path h = new Path();
        public static float[] i = new float[8];
        public static RectF j = new RectF();
        public static Path k = new Path();
        public static RectF l = new RectF();
        public static Path m = new Path();
        public static x n = new x();
    }

    /* loaded from: classes11.dex */
    public enum m {
        AspectFit,
        AspectFill,
        Stretch,
        Center
    }

    public static void a(Canvas canvas, int i2, int i3, PointF pointF) {
        a(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, i3, pointF);
    }

    public static void a(Canvas canvas, int i2, int i3, PointF pointF, float f2) {
        a(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, i3, pointF, f2, 6.5f);
    }

    public static void a(Canvas canvas, int i2, PointF pointF) {
        a(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void a(Canvas canvas, RectF rectF, m mVar, int i2, int i3, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = i.f13102a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        float f4 = pointF.y / 2.0f;
        canvas.save();
        RectF rectF2 = i.c;
        a(mVar, i.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        i.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f5 = f3 + 2.99f;
        canvas.translate(f5, f4);
        ((Matrix) stack.peek()).postTranslate(f5, f4);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        i.e.set(-12.57f, -12.73f, -3.99f, 16.0f);
        Path path = i.f;
        path.reset();
        path.moveTo(-3.99f, 16.0f);
        path.lineTo(-3.99f, 9.0f);
        path.cubicTo(-3.99f, 7.06f, -4.74f, 5.17f, -5.9f, 3.62f);
        path.lineTo(-10.32f, -2.29f);
        path.cubicTo(-11.48f, -3.84f, -12.57f, -5.73f, -12.57f, -7.67f);
        path.lineTo(-12.57f, -12.73f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i3);
        canvas.drawPath(path, paint);
        canvas.restore();
        i.g.set(-3.99f, -2.2f, 2.23f, 16.0f);
        Path path2 = i.h;
        path2.reset();
        path2.moveTo(2.23f, -2.2f);
        path2.lineTo(-2.2f, 3.7f);
        path2.cubicTo(-3.36f, 5.25f, -3.99f, 7.06f, -3.99f, 9.0f);
        path2.lineTo(-3.99f, 16.0f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        canvas.restore();
        i.i.set(-2.99f, -13.42f, 10.58f, 0.0f);
        Path path3 = i.j;
        path3.reset();
        path3.moveTo(-2.61f, -9.17f);
        path3.lineTo(9.54f, -13.42f);
        path3.lineTo(10.57f, -0.59f);
        path3.cubicTo(10.59f, -0.47f, 10.57f, -0.34f, 10.5f, -0.23f);
        path3.cubicTo(10.35f, 0.0f, 10.04f, 0.07f, 9.81f, -0.08f);
        path3.cubicTo(9.77f, -0.1f, 9.72f, -0.15f, 9.72f, -0.15f);
        path3.lineTo(6.27f, -3.55f);
        path3.cubicTo(6.24f, -3.59f, 6.2f, -3.62f, 6.16f, -3.65f);
        path3.cubicTo(5.94f, -3.8f, 5.49f, -3.53f, 5.35f, -3.31f);
        path3.cubicTo(5.15f, -3.01f, 3.85f, -1.0f, 3.85f, -1.0f);
        path3.lineTo(2.17f, -2.09f);
        path3.lineTo(0.49f, -3.19f);
        path3.cubicTo(0.49f, -3.19f, 1.8f, -5.19f, 1.99f, -5.49f);
        path3.cubicTo(2.14f, -5.71f, 2.13f, -6.13f, 1.91f, -6.28f);
        path3.cubicTo(1.87f, -6.3f, 1.85f, -6.37f, 1.81f, -6.39f);
        path3.lineTo(-2.68f, -8.19f);
        path3.cubicTo(-2.68f, -8.19f, -2.74f, -8.23f, -2.77f, -8.26f);
        path3.cubicTo(-3.0f, -8.41f, -3.06f, -8.72f, -2.91f, -8.95f);
        path3.cubicTo(-2.84f, -9.06f, -2.73f, -9.14f, -2.61f, -9.17f);
        path3.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path3, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void a(Canvas canvas, RectF rectF, m mVar, int i2, int i3, PointF pointF, float f2, float f3) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = l.f13105a;
        float min = Math.min(pointF.x / 32.0f, pointF.y / 32.0f);
        double d2 = ((f2 - 180.0f) * 3.1415927f) / 180.0f;
        float cos = (pointF.y - (((f3 * 2.0f) + 4.0f) * min)) + 1.0f + (((((float) Math.cos(d2)) * min) * 20.0f) / 4.0f);
        float sin = (pointF.x / 2.0f) + ((((0.75f * min) * ((float) Math.sin(d2))) * 16.0f) / 2.0f);
        canvas.save();
        RectF rectF2 = l.c;
        a(mVar, l.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        l.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f4 = sin - 0.0f;
        float f5 = cos - 1.0f;
        canvas.translate(f4, f5);
        ((Matrix) stack.peek()).postTranslate(f4, f5);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        l.e.set(-6.5f, -5.5f, 6.5f, 7.5f);
        Path path = l.f;
        path.reset();
        path.moveTo(6.5f, 1.0f);
        path.cubicTo(6.5f, 2.8f, 5.78f, 4.42f, 4.6f, 5.59f);
        path.cubicTo(3.43f, 6.77f, 1.8f, 7.5f, 0.0f, 7.5f);
        path.cubicTo(-1.79f, 7.5f, -3.41f, 6.78f, -4.59f, 5.61f);
        path.cubicTo(-5.77f, 4.43f, -6.5f, 2.8f, -6.5f, 1.0f);
        path.cubicTo(-6.5f, -0.79f, -5.77f, -2.42f, -4.6f, -3.59f);
        path.cubicTo(-3.42f, -4.77f, -1.79f, -5.5f, 0.0f, -5.5f);
        path.cubicTo(1.79f, -5.5f, 3.42f, -4.77f, 4.6f, -3.59f);
        path.cubicTo(5.77f, -2.42f, 6.5f, -0.79f, 6.5f, 1.0f);
        path.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i3);
        canvas.drawPath(path, paint);
        canvas.restore();
        RectF rectF3 = l.g;
        rectF3.set(-1.97f, 5.5f, 2.03f, 17.5f);
        Path path2 = l.h;
        path2.reset();
        float min2 = Math.min(Math.min(rectF3.width(), rectF3.height()) / 2.0f, 1.0f);
        float[] fArr = l.i;
        fArr[1] = min2;
        fArr[0] = min2;
        fArr[3] = min2;
        fArr[2] = min2;
        fArr[5] = 0.0f;
        fArr[4] = 0.0f;
        fArr[7] = 0.0f;
        fArr[6] = 0.0f;
        path2.addRoundRect(rectF3, fArr, Path.Direction.CW);
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        canvas.save();
        canvas.translate(0.0f, 1.0f);
        ((Matrix) stack.peek()).postTranslate(0.0f, 1.0f);
        float f6 = -(f2 + 90.0f);
        canvas.rotate(f6);
        ((Matrix) stack.peek()).postRotate(f6);
        l.j.set(-20.5f, -7.99f, -4.5f, 8.01f);
        Path path3 = l.k;
        path3.reset();
        path3.moveTo(-9.47f, -7.49f);
        path3.cubicTo(-9.47f, -7.45f, -9.49f, -7.38f, -9.49f, -7.38f);
        path3.cubicTo(-9.57f, -6.97f, -10.45f, -2.64f, -10.45f, -2.64f);
        path3.cubicTo(-10.47f, -2.59f, -10.47f, -2.54f, -10.47f, -2.49f);
        path3.cubicTo(-10.47f, -2.25f, -10.09f, -2.03f, -9.82f, -2.0f);
        path3.lineTo(-5.5f, -2.0f);
        path3.cubicTo(-4.95f, -2.0f, -4.5f, -1.55f, -4.5f, -1.0f);
        path3.lineTo(-4.5f, 1.0f);
        path3.cubicTo(-4.5f, 1.55f, -4.95f, 2.0f, -5.5f, 2.0f);
        path3.cubicTo(-5.5f, 2.0f, -9.38f, 2.01f, -9.75f, 2.01f);
        path3.cubicTo(-10.01f, 2.01f, -10.35f, 2.24f, -10.35f, 2.51f);
        path3.cubicTo(-10.35f, 2.56f, -10.41f, 2.6f, -10.39f, 2.65f);
        path3.lineTo(-9.46f, 7.39f);
        path3.cubicTo(-9.46f, 7.39f, -9.46f, 7.47f, -9.46f, 7.51f);
        path3.cubicTo(-9.46f, 7.78f, -9.69f, 8.01f, -9.97f, 8.01f);
        path3.cubicTo(-10.1f, 8.01f, -10.22f, 7.95f, -10.31f, 7.87f);
        path3.lineTo(-20.5f, 0.01f);
        path3.lineTo(-10.31f, -7.86f);
        path3.cubicTo(-10.22f, -7.94f, -10.11f, -7.99f, -9.97f, -7.99f);
        path3.cubicTo(-9.7f, -7.99f, -9.47f, -7.77f, -9.47f, -7.49f);
        path3.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path3, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(1.0f, 2.0f);
        ((Matrix) stack.peek()).postTranslate(1.0f, 2.0f);
        canvas.rotate(-90.0f);
        ((Matrix) stack.peek()).postRotate(-90.0f);
        canvas.scale(-1.0f, 1.0f);
        ((Matrix) stack.peek()).postScale(-1.0f, 1.0f);
        l.l.set(-7.5f, -7.5f, 5.5f, 5.5f);
        Path path4 = l.m;
        path4.reset();
        path4.moveTo(5.5f, -1.0f);
        path4.cubicTo(5.5f, 0.79f, 4.78f, 2.41f, 3.6f, 3.59f);
        path4.cubicTo(2.43f, 4.77f, 0.8f, 5.5f, -1.0f, 5.5f);
        path4.cubicTo(-2.79f, 5.5f, -4.41f, 4.78f, -5.59f, 3.61f);
        path4.cubicTo(-6.77f, 2.43f, -7.5f, 0.8f, -7.5f, -1.0f);
        path4.cubicTo(-7.5f, -2.79f, -6.77f, -4.42f, -5.6f, -5.6f);
        path4.cubicTo(-4.42f, -6.77f, -2.79f, -7.5f, -1.0f, -7.5f);
        path4.cubicTo(0.79f, -7.5f, 2.42f, -6.77f, 3.6f, -5.6f);
        path4.cubicTo(4.77f, -4.42f, 5.5f, -2.79f, 5.5f, -1.0f);
        path4.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        paint.setPathEffect(l.n.a((f2 / 360.0f) * 2.0f * 3.1415927f * f3, 1000.0f, 0.0f));
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path4, paint);
        canvas.restore();
        canvas.restore();
        canvas.restore();
        canvas.restore();
    }

    public static void a(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = b.f13095a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        canvas.save();
        RectF rectF2 = b.c;
        a(mVar, b.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        b.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f4 = (pointF.y / 2.0f) + 1.0f;
        canvas.translate(f3, f4);
        ((Matrix) stack.peek()).postTranslate(f3, f4);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        b.e.set(0.06f, 6.6f, 0.06f, 15.0f);
        Path path = b.f;
        path.reset();
        path.moveTo(0.06f, 6.6f);
        path.lineTo(0.06f, 15.0f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        canvas.restore();
        b.g.set(-8.0f, -9.03f, 8.0f, 2.48f);
        Path path2 = b.h;
        path2.reset();
        path2.moveTo(-2.0f, 2.48f);
        path2.cubicTo(-2.0f, 2.48f, -2.0f, 2.09f, -2.0f, 1.73f);
        path2.cubicTo(-2.0f, 1.46f, -2.23f, 1.12f, -2.5f, 1.12f);
        path2.cubicTo(-2.55f, 1.12f, -2.6f, 1.07f, -2.64f, 1.08f);
        path2.lineTo(-7.39f, 2.02f);
        path2.cubicTo(-7.39f, 2.02f, -7.46f, 2.01f, -7.5f, 2.01f);
        path2.cubicTo(-7.78f, 2.01f, -8.0f, 1.78f, -8.0f, 1.51f);
        path2.cubicTo(-8.0f, 1.37f, -7.95f, 1.25f, -7.86f, 1.16f);
        path2.lineTo(0.0f, -9.03f);
        path2.lineTo(7.86f, 1.16f);
        path2.cubicTo(7.95f, 1.25f, 8.0f, 1.37f, 8.0f, 1.5f);
        path2.cubicTo(8.0f, 1.77f, 7.78f, 2.0f, 7.5f, 2.0f);
        path2.cubicTo(7.46f, 2.0f, 7.39f, 1.99f, 7.39f, 1.99f);
        path2.lineTo(2.64f, 1.02f);
        path2.cubicTo(2.6f, 1.01f, 2.55f, 1.0f, 2.5f, 1.0f);
        path2.cubicTo(2.23f, 1.0f, 2.0f, 1.46f, 2.0f, 1.73f);
        path2.cubicTo(2.0f, 2.09f, 2.0f, 2.48f, 2.0f, 2.48f);
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        RectF rectF3 = b.i;
        rectF3.set(-3.0f, -16.6f, 3.1f, -10.5f);
        Path path3 = b.j;
        path3.reset();
        path3.addOval(rectF3, Path.Direction.CW);
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path3, paint);
        RectF rectF4 = b.k;
        rectF4.set(-2.0f, 3.63f, 2.0f, 5.58f);
        Path path4 = b.l;
        path4.reset();
        path4.moveTo(rectF4.left, rectF4.top);
        path4.lineTo(rectF4.right, rectF4.top);
        path4.lineTo(rectF4.right, rectF4.bottom);
        path4.lineTo(rectF4.left, rectF4.bottom);
        path4.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path4, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void a(m mVar, RectF rectF, RectF rectF2, RectF rectF3) {
        if (rectF.equals(rectF2) || rectF2 == null) {
            rectF3.set(rectF);
        } else if (mVar == m.Stretch) {
            rectF3.set(rectF2);
        } else {
            float abs = Math.abs(rectF2.width() / rectF.width());
            float abs2 = Math.abs(rectF2.height() / rectF.height());
            float f2 = 0.0f;
            int i2 = a.f13094a[mVar.ordinal()];
            if (i2 == 1) {
                f2 = Math.min(abs, abs2);
            } else if (i2 == 2) {
                f2 = Math.max(abs, abs2);
            } else if (i2 == 3) {
                f2 = 1.0f;
            }
            float abs3 = Math.abs(rectF.width() * f2);
            float f3 = abs3 / 2.0f;
            float abs4 = Math.abs(rectF.height() * f2) / 2.0f;
            rectF3.set(rectF2.centerX() - f3, rectF2.centerY() - abs4, rectF2.centerX() + f3, rectF2.centerY() + abs4);
        }
    }

    public static void b(Canvas canvas, int i2, int i3, PointF pointF) {
        b(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, i3, pointF);
    }

    public static void b(Canvas canvas, int i2, PointF pointF) {
        b(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void b(Canvas canvas, RectF rectF, m mVar, int i2, int i3, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = j.f13103a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        canvas.save();
        RectF rectF2 = j.c;
        a(mVar, j.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        j.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f4 = (pointF.y / 2.0f) + 1.0f;
        canvas.translate(f3, f4);
        ((Matrix) stack.peek()).postTranslate(f3, f4);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        j.e.set(0.07f, -10.51f, 8.07f, 15.0f);
        Path path = j.f;
        path.reset();
        path.moveTo(8.07f, 15.0f);
        path.lineTo(8.07f, 12.47f);
        path.cubicTo(8.07f, 10.53f, 7.44f, 8.65f, 6.28f, 7.1f);
        path.lineTo(1.86f, 1.19f);
        path.cubicTo(0.69f, -0.36f, 0.07f, -2.25f, 0.07f, -4.19f);
        path.lineTo(0.07f, -10.51f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i3);
        canvas.drawPath(path, paint);
        canvas.restore();
        j.g.set(-8.06f, -10.6f, -0.06f, 15.0f);
        Path path2 = j.h;
        path2.reset();
        path2.moveTo(-8.06f, 15.0f);
        path2.lineTo(-8.06f, 12.39f);
        path2.cubicTo(-8.06f, 10.45f, -7.43f, 8.56f, -6.27f, 7.01f);
        path2.lineTo(-1.85f, 1.11f);
        path2.cubicTo(-0.69f, -0.45f, -0.06f, -2.33f, -0.06f, -4.27f);
        path2.lineTo(-0.06f, -10.6f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        canvas.restore();
        j.i.set(-8.07f, -16.48f, 7.93f, -2.96f);
        Path path3 = j.j;
        path3.reset();
        path3.moveTo(7.8f, -6.29f);
        path3.lineTo(-0.07f, -16.48f);
        path3.lineTo(-7.93f, -6.29f);
        path3.cubicTo(-8.01f, -6.2f, -8.07f, -6.08f, -8.07f, -5.95f);
        path3.cubicTo(-8.07f, -5.67f, -7.84f, -5.45f, -7.57f, -5.45f);
        path3.cubicTo(-7.53f, -5.45f, -7.45f, -5.46f, -7.45f, -5.46f);
        path3.lineTo(-2.71f, -6.43f);
        path3.cubicTo(-2.66f, -6.44f, -2.62f, -6.45f, -2.57f, -6.45f);
        path3.cubicTo(-2.3f, -6.45f, -2.07f, -5.98f, -2.07f, -5.72f);
        path3.cubicTo(-2.07f, -5.36f, -2.07f, -2.96f, -2.07f, -2.96f);
        path3.lineTo(-0.07f, -2.96f);
        path3.lineTo(1.94f, -2.96f);
        path3.cubicTo(1.94f, -2.96f, 1.93f, -5.36f, 1.93f, -5.72f);
        path3.cubicTo(1.93f, -5.98f, 2.16f, -6.33f, 2.43f, -6.33f);
        path3.cubicTo(2.48f, -6.33f, 2.53f, -6.38f, 2.58f, -6.37f);
        path3.lineTo(7.32f, -5.43f);
        path3.cubicTo(7.32f, -5.43f, 7.4f, -5.43f, 7.43f, -5.43f);
        path3.cubicTo(7.71f, -5.43f, 7.93f, -5.66f, 7.93f, -5.94f);
        path3.cubicTo(7.93f, -6.07f, 7.88f, -6.2f, 7.8f, -6.29f);
        path3.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path3, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void b(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = C0658c.f13096a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        float f4 = pointF.y / 2.0f;
        canvas.save();
        RectF rectF2 = C0658c.c;
        a(mVar, C0658c.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        C0658c.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        canvas.translate(f3, f4);
        ((Matrix) stack.peek()).postTranslate(f3, f4);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        C0658c.e.set(-0.99f, 5.6f, -0.99f, 16.0f);
        Path path = C0658c.f;
        path.reset();
        path.moveTo(-0.99f, 5.6f);
        path.lineTo(-0.99f, 16.0f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        canvas.restore();
        C0658c.g.set(-9.05f, -10.03f, 6.95f, 1.48f);
        Path path2 = C0658c.h;
        path2.reset();
        path2.moveTo(-3.05f, 1.48f);
        path2.cubicTo(-3.05f, 1.48f, -3.05f, 1.09f, -3.05f, 0.73f);
        path2.cubicTo(-3.05f, 0.46f, -3.28f, 0.12f, -3.55f, 0.12f);
        path2.cubicTo(-3.6f, 0.12f, -3.65f, 0.07f, -3.69f, 0.08f);
        path2.lineTo(-8.44f, 1.02f);
        path2.cubicTo(-8.44f, 1.02f, -8.51f, 1.01f, -8.55f, 1.01f);
        path2.cubicTo(-8.83f, 1.01f, -9.05f, 0.78f, -9.05f, 0.51f);
        path2.cubicTo(-9.05f, 0.37f, -9.0f, 0.25f, -8.91f, 0.16f);
        path2.lineTo(-1.05f, -10.03f);
        path2.lineTo(6.81f, 0.16f);
        path2.cubicTo(6.9f, 0.25f, 6.95f, 0.37f, 6.95f, 0.5f);
        path2.cubicTo(6.95f, 0.77f, 6.73f, 1.0f, 6.45f, 1.0f);
        path2.cubicTo(6.41f, 1.0f, 6.34f, 0.99f, 6.34f, 0.99f);
        path2.lineTo(1.59f, 0.02f);
        path2.cubicTo(1.55f, 0.01f, 1.5f, -0.0f, 1.45f, -0.0f);
        path2.cubicTo(1.18f, -0.0f, 0.95f, 0.46f, 0.95f, 0.73f);
        path2.cubicTo(0.95f, 1.09f, 0.96f, 1.48f, 0.96f, 1.48f);
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        RectF rectF3 = C0658c.i;
        rectF3.set(2.95f, -15.6f, 9.05f, -9.5f);
        Path path3 = C0658c.j;
        path3.reset();
        path3.addOval(rectF3, Path.Direction.CW);
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path3, paint);
        RectF rectF4 = C0658c.k;
        rectF4.set(-3.05f, 2.63f, 0.95f, 4.58f);
        Path path4 = C0658c.l;
        path4.reset();
        path4.moveTo(rectF4.left, rectF4.top);
        path4.lineTo(rectF4.right, rectF4.top);
        path4.lineTo(rectF4.right, rectF4.bottom);
        path4.lineTo(rectF4.left, rectF4.bottom);
        path4.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path4, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void c(Canvas canvas, int i2, int i3, PointF pointF) {
        c(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, i3, pointF);
    }

    public static void c(Canvas canvas, int i2, PointF pointF) {
        c(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void c(Canvas canvas, RectF rectF, m mVar, int i2, int i3, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = k.f13104a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        float f4 = pointF.y / 2.0f;
        canvas.save();
        RectF rectF2 = k.c;
        a(mVar, k.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        k.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f5 = f3 + 3.38f;
        canvas.translate(f5, f4);
        ((Matrix) stack.peek()).postTranslate(f5, f4);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        k.e.set(-10.38f, -13.0f, -10.38f, 16.0f);
        Path path = k.f;
        path.reset();
        path.moveTo(-10.38f, 16.0f);
        path.lineTo(-10.38f, 7.51f);
        path.cubicTo(-10.38f, 5.7f, -10.38f, -13.0f, -10.38f, -13.0f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i3);
        canvas.drawPath(path, paint);
        canvas.restore();
        k.g.set(-10.38f, -4.4f, -0.81f, 16.0f);
        Path path2 = k.h;
        path2.reset();
        path2.moveTo(-0.81f, -4.4f);
        path2.lineTo(-8.35f, 1.79f);
        path2.cubicTo(-9.51f, 3.34f, -10.38f, 5.23f, -10.38f, 7.17f);
        path2.lineTo(-10.38f, 16.0f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        canvas.restore();
        k.i.set(-5.38f, -12.07f, 8.0f, 0.99f);
        Path path3 = k.j;
        path3.reset();
        path3.moveTo(-4.85f, -11.35f);
        path3.lineTo(8.0f, -12.07f);
        path3.lineTo(5.43f, 0.55f);
        path3.cubicTo(5.42f, 0.67f, 5.36f, 0.79f, 5.27f, 0.87f);
        path3.cubicTo(5.06f, 1.05f, 4.74f, 1.03f, 4.56f, 0.82f);
        path3.cubicTo(4.53f, 0.79f, 4.5f, 0.73f, 4.5f, 0.73f);
        path3.lineTo(2.12f, -3.49f);
        path3.cubicTo(2.11f, -3.54f, 2.08f, -3.58f, 2.05f, -3.62f);
        path3.cubicTo(1.87f, -3.82f, 1.37f, -3.69f, 1.17f, -3.52f);
        path3.cubicTo(0.9f, -3.28f, -0.91f, -1.71f, -0.91f, -1.71f);
        path3.lineTo(-2.23f, -3.23f);
        path3.lineTo(-3.54f, -4.74f);
        path3.cubicTo(-3.54f, -4.74f, -1.72f, -6.31f, -1.45f, -6.54f);
        path3.cubicTo(-1.25f, -6.72f, -1.14f, -7.11f, -1.32f, -7.32f);
        path3.cubicTo(-1.35f, -7.36f, -1.34f, -7.43f, -1.38f, -7.45f);
        path3.lineTo(-5.19f, -10.43f);
        path3.cubicTo(-5.19f, -10.43f, -5.24f, -10.49f, -5.26f, -10.52f);
        path3.cubicTo(-5.44f, -10.73f, -5.41f, -11.05f, -5.2f, -11.23f);
        path3.cubicTo(-5.1f, -11.31f, -4.97f, -11.36f, -4.85f, -11.35f);
        path3.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path3, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void c(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = d.f13097a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        canvas.save();
        RectF rectF2 = d.c;
        a(mVar, d.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        d.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f4 = (pointF.y / 2.0f) + 1.0f;
        canvas.translate(f3, f4);
        ((Matrix) stack.peek()).postTranslate(f3, f4);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        d.e.set(-3.49f, 1.0f, 14.51f, 14.99f);
        Path path = d.f;
        path.reset();
        path.moveTo(-3.36f, 2.8f);
        path.lineTo(5.51f, 14.99f);
        path.lineTo(14.37f, 2.8f);
        path.cubicTo(14.45f, 2.71f, 14.51f, 2.59f, 14.51f, 2.46f);
        path.cubicTo(14.51f, 2.19f, 14.28f, 1.96f, 14.01f, 1.96f);
        path.cubicTo(13.97f, 1.96f, 13.89f, 1.97f, 13.89f, 1.97f);
        path.lineTo(8.15f, 3.94f);
        path.cubicTo(8.11f, 3.95f, 8.06f, 3.96f, 8.01f, 3.96f);
        path.cubicTo(7.74f, 3.96f, 7.51f, 3.76f, 7.51f, 3.5f);
        path.cubicTo(7.51f, 3.13f, 7.51f, 1.0f, 7.51f, 1.0f);
        path.lineTo(5.51f, 1.0f);
        path.lineTo(3.5f, 1.0f);
        path.cubicTo(3.5f, 1.0f, 3.51f, 3.13f, 3.51f, 3.5f);
        path.cubicTo(3.51f, 3.76f, 3.28f, 3.97f, 3.01f, 3.97f);
        path.cubicTo(2.96f, 3.97f, 2.91f, 3.96f, 2.86f, 3.95f);
        path.lineTo(-2.88f, 1.98f);
        path.cubicTo(-2.88f, 1.98f, -2.95f, 1.96f, -2.99f, 1.96f);
        path.cubicTo(-3.27f, 1.96f, -3.49f, 2.19f, -3.49f, 2.46f);
        path.cubicTo(-3.49f, 2.59f, -3.44f, 2.71f, -3.36f, 2.8f);
        path.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        d.g.set(-7.5f, -12.0f, 5.5f, 15.0f);
        Path path2 = d.h;
        path2.reset();
        path2.moveTo(-7.5f, 15.0f);
        path2.lineTo(-7.5f, -5.16f);
        path2.cubicTo(-7.5f, -8.91f, -4.55f, -12.0f, -1.0f, -12.0f);
        path2.cubicTo(2.59f, -12.0f, 5.5f, -8.72f, 5.5f, -5.15f);
        path2.lineTo(5.5f, 4.0f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        canvas.restore();
        canvas.restore();
        canvas.restore();
    }

    public static void d(Canvas canvas, int i2, PointF pointF) {
        d(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void d(Canvas canvas, RectF rectF, m mVar, int i2, int i3, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = h.f13101a;
        float min = Math.min(pointF.x / 32.0f, pointF.y / 32.0f);
        canvas.save();
        RectF rectF2 = h.c;
        a(mVar, h.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        h.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        canvas.translate(0.0f, 1.0f);
        ((Matrix) stack.peek()).postTranslate(0.0f, 1.0f);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        new RectF().set(0.0f, 0.0f, 32.0f, 32.0f);
        Path path = new Path();
        path.reset();
        path.moveTo(7.39f, 15.63f);
        path.cubicTo(7.37f, 15.72f, 7.37f, 15.81f, 7.41f, 15.89f);
        path.cubicTo(7.44f, 15.97f, 7.49f, 16.04f, 7.57f, 16.09f);
        path.cubicTo(7.64f, 16.14f, 7.72f, 16.16f, 7.81f, 16.17f);
        path.cubicTo(7.9f, 16.17f, 7.98f, 16.14f, 8.05f, 16.1f);
        path.lineTo(11.69f, 14.45f);
        path.cubicTo(11.78f, 14.42f, 11.89f, 14.42f, 11.99f, 14.45f);
        path.cubicTo(12.09f, 14.49f, 12.17f, 14.55f, 12.23f, 14.64f);
        path.cubicTo(12.29f, 14.73f, 12.32f, 14.83f, 12.31f, 14.93f);
        path.cubicTo(12.3f, 15.04f, 12.26f, 15.14f, 12.19f, 15.21f);
        path.lineTo(6.15f, 22.99f);
        path.lineTo(0.12f, 15.21f);
        path.cubicTo(0.05f, 15.14f, 0.01f, 15.04f, 0.0f, 14.93f);
        path.cubicTo(-0.01f, 14.83f, 0.02f, 14.73f, 0.08f, 14.64f);
        path.cubicTo(0.14f, 14.55f, 0.22f, 14.49f, 0.32f, 14.45f);
        path.cubicTo(0.42f, 14.42f, 0.53f, 14.42f, 0.62f, 14.45f);
        path.lineTo(4.26f, 16.1f);
        path.cubicTo(4.36f, 16.16f, 4.47f, 16.18f, 4.59f, 16.16f);
        path.cubicTo(4.7f, 16.13f, 4.8f, 16.06f, 4.87f, 15.96f);
        path.cubicTo(4.93f, 15.87f, 4.95f, 15.75f, 4.92f, 15.63f);
        path.lineTo(4.92f, 15.63f);
        path.cubicTo(4.93f, 13.26f, 5.56f, 10.93f, 6.75f, 8.88f);
        path.cubicTo(7.93f, 6.83f, 9.64f, 5.12f, 11.7f, 3.94f);
        path.lineTo(12.93f, 6.06f);
        path.cubicTo(11.25f, 7.03f, 9.85f, 8.43f, 8.88f, 10.11f);
        path.cubicTo(7.9f, 11.78f, 7.39f, 13.69f, 7.39f, 15.63f);
        path.lineTo(7.39f, 15.63f);
        path.close();
        path.moveTo(24.0f, 6.07f);
        path.cubicTo(25.68f, 7.04f, 27.08f, 8.44f, 28.05f, 10.11f);
        path.cubicTo(29.02f, 11.79f, 29.53f, 13.69f, 29.54f, 15.63f);
        path.lineTo(32.0f, 15.63f);
        path.cubicTo(32.0f, 13.26f, 31.37f, 10.94f, 30.18f, 8.89f);
        path.cubicTo(28.99f, 6.84f, 27.29f, 5.13f, 25.24f, 3.94f);
        path.lineTo(25.23f, 3.94f);
        path.cubicTo(25.15f, 3.92f, 25.08f, 3.87f, 25.02f, 3.8f);
        path.cubicTo(24.97f, 3.73f, 24.94f, 3.65f, 24.93f, 3.56f);
        path.cubicTo(24.92f, 3.48f, 24.94f, 3.39f, 24.98f, 3.31f);
        path.cubicTo(25.02f, 3.24f, 25.09f, 3.18f, 25.16f, 3.14f);
        path.lineTo(28.41f, 0.83f);
        path.cubicTo(28.49f, 0.76f, 28.54f, 0.66f, 28.56f, 0.56f);
        path.cubicTo(28.58f, 0.46f, 28.57f, 0.35f, 28.52f, 0.26f);
        path.cubicTo(28.47f, 0.17f, 28.4f, 0.09f, 28.3f, 0.05f);
        path.cubicTo(28.21f, 0.0f, 28.1f, -0.01f, 28.0f, 0.01f);
        path.lineTo(18.22f, 1.33f);
        path.lineTo(21.97f, 10.43f);
        path.cubicTo(22.0f, 10.53f, 22.06f, 10.61f, 22.15f, 10.67f);
        path.cubicTo(22.24f, 10.73f, 22.34f, 10.75f, 22.44f, 10.75f);
        path.cubicTo(22.55f, 10.74f, 22.65f, 10.7f, 22.73f, 10.63f);
        path.cubicTo(22.81f, 10.57f, 22.86f, 10.47f, 22.88f, 10.37f);
        path.lineTo(23.27f, 6.41f);
        path.cubicTo(23.26f, 6.3f, 23.3f, 6.18f, 23.38f, 6.1f);
        path.cubicTo(23.46f, 6.01f, 23.57f, 5.96f, 23.69f, 5.96f);
        path.cubicTo(23.8f, 5.95f, 23.92f, 5.99f, 24.0f, 6.07f);
        path.close();
        path.moveTo(25.22f, 27.32f);
        path.cubicTo(25.29f, 27.26f, 25.37f, 27.22f, 25.46f, 27.21f);
        path.cubicTo(25.54f, 27.2f, 25.63f, 27.21f, 25.71f, 27.25f);
        path.cubicTo(25.78f, 27.28f, 25.85f, 27.34f, 25.9f, 27.42f);
        path.cubicTo(25.94f, 27.49f, 25.96f, 27.58f, 25.96f, 27.67f);
        path.lineTo(26.34f, 31.62f);
        path.cubicTo(26.37f, 31.72f, 26.42f, 31.82f, 26.5f, 31.88f);
        path.cubicTo(26.58f, 31.95f, 26.68f, 31.99f, 26.78f, 32.0f);
        path.cubicTo(26.89f, 32.0f, 26.99f, 31.97f, 27.07f, 31.92f);
        path.cubicTo(27.16f, 31.86f, 27.23f, 31.77f, 27.26f, 31.68f);
        path.lineTo(31.0f, 22.58f);
        path.lineTo(21.23f, 21.26f);
        path.cubicTo(21.12f, 21.24f, 21.02f, 21.25f, 20.92f, 21.3f);
        path.cubicTo(20.83f, 21.34f, 20.75f, 21.42f, 20.71f, 21.51f);
        path.cubicTo(20.66f, 21.6f, 20.64f, 21.71f, 20.66f, 21.81f);
        path.cubicTo(20.68f, 21.91f, 20.73f, 22.01f, 20.81f, 22.08f);
        path.lineTo(24.06f, 24.39f);
        path.cubicTo(24.14f, 24.43f, 24.2f, 24.49f, 24.24f, 24.56f);
        path.cubicTo(24.29f, 24.64f, 24.31f, 24.73f, 24.3f, 24.81f);
        path.cubicTo(24.29f, 24.9f, 24.26f, 24.98f, 24.2f, 25.05f);
        path.cubicTo(24.15f, 25.12f, 24.08f, 25.17f, 23.99f, 25.19f);
        path.lineTo(24.0f, 25.19f);
        path.cubicTo(22.31f, 26.14f, 20.4f, 26.64f, 18.46f, 26.64f);
        path.cubicTo(16.52f, 26.64f, 14.61f, 26.14f, 12.92f, 25.19f);
        path.lineTo(11.69f, 27.31f);
        path.cubicTo(13.75f, 28.5f, 16.08f, 29.12f, 18.46f, 29.12f);
        path.cubicTo(20.84f, 29.12f, 23.18f, 28.5f, 25.23f, 27.31f);
        path.lineTo(25.22f, 27.32f);
        path.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void d(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = e.f13098a;
        float f2 = pointF.y;
        float f3 = f2 / 2.0f;
        float min = Math.min(pointF.x / 32.0f, f2 / 32.0f);
        float f4 = pointF.x / 2.0f;
        canvas.save();
        RectF rectF2 = e.c;
        a(mVar, e.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        e.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f5 = f3 + 1.0f;
        canvas.translate(f4, f5);
        ((Matrix) stack.peek()).postTranslate(f4, f5);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        e.e.set(-14.01f, -15.01f, 14.01f, 15.01f);
        Path path = e.f;
        path.reset();
        path.moveTo(-10.01f, 15.01f);
        path.lineTo(-10.01f, 1.0f);
        path.cubicTo(-10.0f, 0.49f, -9.84f, -3.99f, -5.05f, -3.99f);
        path.lineTo(2.57f, -3.99f);
        path.cubicTo(2.8f, -3.95f, 2.98f, -3.75f, 2.98f, -3.51f);
        path.cubicTo(2.98f, -3.46f, 2.97f, -3.41f, 2.96f, -3.36f);
        path.lineTo(0.99f, 2.38f);
        path.cubicTo(0.99f, 2.38f, 0.98f, 2.45f, 0.98f, 2.49f);
        path.cubicTo(0.98f, 2.77f, 1.2f, 2.99f, 1.48f, 2.99f);
        path.cubicTo(1.61f, 2.99f, 1.73f, 2.94f, 1.82f, 2.86f);
        path.lineTo(14.01f, -6.01f);
        path.lineTo(1.82f, -14.87f);
        path.cubicTo(1.73f, -14.95f, 1.61f, -15.01f, 1.48f, -15.01f);
        path.cubicTo(1.2f, -15.01f, 0.98f, -14.78f, 0.98f, -14.51f);
        path.cubicTo(0.98f, -14.47f, 0.99f, -14.4f, 0.99f, -14.4f);
        path.lineTo(2.96f, -8.65f);
        path.cubicTo(2.97f, -8.61f, 2.98f, -8.56f, 2.98f, -8.51f);
        path.cubicTo(2.98f, -8.24f, 2.76f, -8.01f, 2.5f, -8.01f);
        path.cubicTo(2.14f, -8.01f, -5.05f, -7.99f, -5.05f, -7.99f);
        path.cubicTo(-11.58f, -7.99f, -13.99f, -2.63f, -14.01f, 0.99f);
        path.lineTo(-14.01f, 15.01f);
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void e(Canvas canvas, int i2, PointF pointF) {
        e(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void e(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = f.f13099a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        float f4 = pointF.y / 2.0f;
        canvas.save();
        RectF rectF2 = f.c;
        a(mVar, f.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        f.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        canvas.translate(f3, f4);
        ((Matrix) stack.peek()).postTranslate(f3, f4);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        f.e.set(-2.12f, -7.97f, 13.13f, 7.5f);
        Path path = f.f;
        path.reset();
        path.moveTo(-1.66f, 4.57f);
        path.lineTo(13.13f, 7.5f);
        path.lineTo(11.36f, -7.47f);
        path.cubicTo(11.36f, -7.59f, 11.32f, -7.71f, 11.23f, -7.81f);
        path.cubicTo(11.04f, -8.01f, 10.73f, -8.03f, 10.52f, -7.84f);
        path.cubicTo(10.49f, -7.81f, 10.45f, -7.75f, 10.45f, -7.75f);
        path.lineTo(7.57f, -2.41f);
        path.cubicTo(7.54f, -2.37f, 7.51f, -2.33f, 7.48f, -2.3f);
        path.cubicTo(7.28f, -2.11f, 6.96f, -2.12f, 6.78f, -2.31f);
        path.cubicTo(6.54f, -2.57f, 5.09f, -4.15f, 5.09f, -4.15f);
        path.lineTo(3.61f, -2.79f);
        path.lineTo(2.14f, -1.43f);
        path.cubicTo(2.14f, -1.43f, 3.6f, 0.14f, 3.85f, 0.41f);
        path.cubicTo(4.02f, 0.6f, 4.01f, 0.92f, 3.81f, 1.1f);
        path.cubicTo(3.77f, 1.14f, 3.73f, 1.16f, 3.69f, 1.18f);
        path.lineTo(-1.87f, 3.64f);
        path.cubicTo(-1.87f, 3.64f, -1.93f, 3.68f, -1.96f, 3.7f);
        path.cubicTo(-2.16f, 3.89f, -2.17f, 4.21f, -1.99f, 4.41f);
        path.cubicTo(-1.9f, 4.51f, -1.78f, 4.56f, -1.66f, 4.57f);
        path.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        f.g.set(-11.12f, -8.5f, 4.88f, 16.0f);
        Path path2 = f.h;
        path2.reset();
        path2.moveTo(-11.12f, -4.0f);
        path2.cubicTo(-11.12f, -4.0f, -11.33f, -8.5f, -6.62f, -8.5f);
        path2.cubicTo(-1.91f, -8.5f, 1.88f, -4.5f, 1.88f, -4.5f);
        path2.lineTo(4.88f, -1.5f);
        path2.moveTo(-11.12f, -4.5f);
        path2.lineTo(-11.12f, 1.5f);
        path2.moveTo(-11.12f, 1.5f);
        path2.lineTo(-11.12f, 3.5f);
        path2.moveTo(-11.12f, 3.5f);
        path2.lineTo(-11.12f, 12.98f);
        path2.lineTo(-11.12f, 16.0f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        canvas.restore();
        canvas.restore();
        canvas.restore();
    }

    public static void f(Canvas canvas, int i2, PointF pointF) {
        f(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void f(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = g.f13100a;
        float f2 = pointF.x;
        float f3 = f2 / 2.0f;
        float min = Math.min(f2 / 32.0f, pointF.y / 32.0f);
        canvas.save();
        RectF rectF2 = g.c;
        a(mVar, g.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        g.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f4 = f3 + 1.0f;
        float f5 = (pointF.y / 2.0f) + 1.0f;
        canvas.translate(f4, f5);
        ((Matrix) stack.peek()).postTranslate(f4, f5);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        g.e.set(-8.34f, -5.09f, 0.99f, 15.06f);
        Path path = g.f;
        path.reset();
        path.moveTo(0.99f, -5.09f);
        path.lineTo(-6.55f, 1.88f);
        path.cubicTo(-7.71f, 3.63f, -8.34f, 5.75f, -8.34f, 7.93f);
        path.lineTo(-8.34f, 15.06f);
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        canvas.restore();
        g.g.set(-3.5f, -12.76f, 9.88f, 0.3f);
        Path path2 = g.h;
        path2.reset();
        path2.moveTo(-2.97f, -12.04f);
        path2.lineTo(9.88f, -12.76f);
        path2.lineTo(7.31f, -0.15f);
        path2.cubicTo(7.3f, -0.02f, 7.24f, 0.09f, 7.15f, 0.18f);
        path2.cubicTo(6.94f, 0.36f, 6.62f, 0.34f, 6.44f, 0.13f);
        path2.cubicTo(6.41f, 0.1f, 6.38f, 0.03f, 6.38f, 0.03f);
        path2.lineTo(4.0f, -4.19f);
        path2.cubicTo(3.99f, -4.23f, 3.96f, -4.27f, 3.93f, -4.31f);
        path2.cubicTo(3.75f, -4.51f, 3.25f, -4.38f, 3.05f, -4.21f);
        path2.cubicTo(2.78f, -3.98f, 0.97f, -2.41f, 0.97f, -2.41f);
        path2.lineTo(-0.34f, -3.92f);
        path2.lineTo(-1.65f, -5.44f);
        path2.cubicTo(-1.65f, -5.44f, 0.16f, -7.0f, 0.43f, -7.24f);
        path2.cubicTo(0.63f, -7.41f, 0.74f, -7.81f, 0.57f, -8.01f);
        path2.cubicTo(0.53f, -8.05f, 0.54f, -8.12f, 0.5f, -8.15f);
        path2.lineTo(-3.31f, -11.12f);
        path2.cubicTo(-3.31f, -11.12f, -3.36f, -11.18f, -3.38f, -11.21f);
        path2.cubicTo(-3.56f, -11.42f, -3.53f, -11.74f, -3.32f, -11.92f);
        path2.cubicTo(-3.22f, -12.01f, -3.09f, -12.05f, -2.97f, -12.04f);
        path2.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        g.i.set(-10.2f, -13.7f, 10.2f, 13.05f);
        canvas.restore();
        canvas.restore();
    }

    public static void g(Canvas canvas, int i2, PointF pointF) {
        g(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void g(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = h.f13101a;
        float f2 = pointF.y;
        float f3 = f2 / 2.0f;
        float min = Math.min(pointF.x / 32.0f, f2 / 32.0f);
        float f4 = pointF.x / 2.0f;
        canvas.save();
        RectF rectF2 = h.c;
        a(mVar, h.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        h.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f5 = f3 + 1.0f;
        canvas.translate(f4, f5);
        ((Matrix) stack.peek()).postTranslate(f4, f5);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        h.e.set(-9.0f, -15.02f, 9.0f, 15.02f);
        Path path = h.f;
        path.reset();
        path.moveTo(8.86f, -2.82f);
        path.lineTo(0.0f, -15.02f);
        path.lineTo(-8.86f, -2.82f);
        path.cubicTo(-8.95f, -2.73f, -9.0f, -2.62f, -9.0f, -2.48f);
        path.cubicTo(-9.0f, -2.21f, -8.78f, -1.98f, -8.5f, -1.98f);
        path.cubicTo(-8.46f, -1.98f, -8.39f, -2.0f, -8.39f, -2.0f);
        path.lineTo(-2.64f, -3.96f);
        path.cubicTo(-2.6f, -3.98f, -2.55f, -3.98f, -2.5f, -3.98f);
        path.cubicTo(-2.23f, -3.98f, -2.0f, -3.76f, -2.0f, -3.5f);
        path.lineTo(-2.0f, 15.02f);
        path.lineTo(2.0f, 15.02f);
        path.cubicTo(2.0f, 15.02f, 2.0f, -3.14f, 2.0f, -3.5f);
        path.cubicTo(2.0f, -3.76f, 2.23f, -3.98f, 2.5f, -3.98f);
        path.cubicTo(2.55f, -3.98f, 2.6f, -3.98f, 2.64f, -3.96f);
        path.lineTo(8.39f, -2.0f);
        path.cubicTo(8.39f, -2.0f, 8.46f, -1.98f, 8.5f, -1.98f);
        path.cubicTo(8.78f, -1.98f, 9.0f, -2.21f, 9.0f, -2.48f);
        path.cubicTo(9.0f, -2.62f, 8.95f, -2.73f, 8.86f, -2.82f);
        path.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void h(Canvas canvas, int i2, PointF pointF) {
        h(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, pointF);
    }

    public static void h(Canvas canvas, RectF rectF, m mVar, int i2, PointF pointF) {
        Stack stack = new Stack();
        stack.push(new Matrix());
        Paint paint = h.f13101a;
        float f2 = pointF.y;
        float f3 = f2 / 2.0f;
        float min = Math.min(pointF.x / 32.0f, f2 / 32.0f);
        float f4 = pointF.x / 2.0f;
        canvas.save();
        RectF rectF2 = h.c;
        a(mVar, h.b, rectF, rectF2);
        canvas.translate(rectF2.left, rectF2.top);
        canvas.scale(rectF2.width() / 32.0f, rectF2.height() / 32.0f);
        h.d.set(0.0f, 0.0f, pointF.x, pointF.y);
        canvas.save();
        float f5 = f3 + 1.0f;
        canvas.translate(f4, f5);
        ((Matrix) stack.peek()).postTranslate(f4, f5);
        canvas.scale(min, min);
        ((Matrix) stack.peek()).postScale(min, min);
        Path path = h.f;
        path.reset();
        path.moveTo(-9.0f, 4.0f);
        path.lineTo(9.0f, 4.0f);
        path.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStrokeWidth(3.0f);
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        canvas.drawPath(path, paint);
        canvas.restore();
        h.e.set(-9.0f, -15.02f, 9.0f, 15.02f);
        Path path2 = h.f;
        path2.reset();
        path2.moveTo(8.86f, -2.82f);
        path2.lineTo(0.0f, -15.02f);
        path2.lineTo(-8.86f, -2.82f);
        path2.cubicTo(-8.95f, -2.73f, -9.0f, -2.62f, -9.0f, -2.48f);
        path2.cubicTo(-9.0f, -2.21f, -8.78f, -1.98f, -8.5f, -1.98f);
        path2.cubicTo(-8.46f, -1.98f, -8.39f, -2.0f, -8.39f, -2.0f);
        path2.lineTo(-2.64f, -3.96f);
        path2.cubicTo(-2.6f, -3.98f, -2.55f, -3.98f, -2.5f, -3.98f);
        path2.cubicTo(-2.23f, -3.98f, -2.0f, -3.76f, -2.0f, -3.5f);
        path2.lineTo(-2.0f, 15.02f);
        path2.lineTo(2.0f, 15.02f);
        path2.cubicTo(2.0f, 15.02f, 2.0f, -3.14f, 2.0f, -3.5f);
        path2.cubicTo(2.0f, -3.76f, 2.23f, -3.98f, 2.5f, -3.98f);
        path2.cubicTo(2.55f, -3.98f, 2.6f, -3.98f, 2.64f, -3.96f);
        path2.lineTo(8.39f, -2.0f);
        path2.cubicTo(8.39f, -2.0f, 8.46f, -1.98f, 8.5f, -1.98f);
        path2.cubicTo(8.78f, -1.98f, 9.0f, -2.21f, 9.0f, -2.48f);
        path2.cubicTo(9.0f, -2.62f, 8.95f, -2.73f, 8.86f, -2.82f);
        path2.close();
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        canvas.drawPath(path2, paint);
        canvas.restore();
        canvas.restore();
    }

    public static void i(Canvas canvas, int i2, PointF pointF) {
        d(canvas, new RectF(0.0f, 0.0f, 32.0f, 32.0f), m.AspectFit, i2, i2, pointF);
    }
}
