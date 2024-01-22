package com.theartofdev.edmodo.cropper;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.util.Arrays;
/* loaded from: classes12.dex */
public class CropOverlayView extends View {
    public CropWindowMoveHandler A;
    public boolean B;
    public int C;
    public int D;
    public float E;
    public CropImageView.Guidelines F;
    public CropImageView.CropShape G;
    public final Rect H;
    public boolean I;
    public Integer J;
    public ScaleGestureDetector h;
    public boolean i;
    public final d j;
    public CropWindowChangeListener k;
    public final RectF l;
    public Paint m;
    public Paint n;
    public Paint o;
    public Paint p;
    public Path q;
    public final float[] r;
    public final RectF s;
    public int t;
    public int u;
    public float v;
    public float w;
    public float x;
    public float y;
    public float z;

    /* loaded from: classes12.dex */
    public interface CropWindowChangeListener {
        void onCropWindowChanged(boolean z);
    }

    /* loaded from: classes12.dex */
    public class b extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public b() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        @TargetApi(11)
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            RectF h = CropOverlayView.this.j.h();
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            float currentSpanY = scaleGestureDetector.getCurrentSpanY() / 2.0f;
            float currentSpanX = scaleGestureDetector.getCurrentSpanX() / 2.0f;
            float f = focusY - currentSpanY;
            float f2 = focusX - currentSpanX;
            float f3 = focusX + currentSpanX;
            float f4 = focusY + currentSpanY;
            if (f2 >= f3 || f > f4 || f2 < 0.0f || f3 > CropOverlayView.this.j.c() || f < 0.0f || f4 > CropOverlayView.this.j.b()) {
                return true;
            }
            h.set(f2, f, f3, f4);
            CropOverlayView.this.j.t(h);
            CropOverlayView.this.invalidate();
            return true;
        }
    }

    public CropOverlayView(Context context) {
        this(context, null);
    }

    public static Paint i(int i) {
        Paint paint = new Paint();
        paint.setColor(i);
        return paint;
    }

    public static Paint j(float f, int i) {
        if (f > 0.0f) {
            Paint paint = new Paint();
            paint.setColor(i);
            paint.setStrokeWidth(f);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            return paint;
        }
        return null;
    }

    public final boolean b(RectF rectF) {
        float u = com.theartofdev.edmodo.cropper.b.u(this.r);
        float w = com.theartofdev.edmodo.cropper.b.w(this.r);
        float v = com.theartofdev.edmodo.cropper.b.v(this.r);
        float p = com.theartofdev.edmodo.cropper.b.p(this.r);
        if (!l()) {
            this.s.set(u, w, v, p);
            return false;
        }
        float[] fArr = this.r;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[4];
        float f4 = fArr[5];
        float f5 = fArr[6];
        float f6 = fArr[7];
        if (fArr[7] < fArr[1]) {
            if (fArr[1] < fArr[3]) {
                f = fArr[6];
                f2 = fArr[7];
                f3 = fArr[2];
                f4 = fArr[3];
                f5 = fArr[4];
                f6 = fArr[5];
            } else {
                f = fArr[4];
                f2 = fArr[5];
                f3 = fArr[0];
                f4 = fArr[1];
                f5 = fArr[2];
                f6 = fArr[3];
            }
        } else if (fArr[1] > fArr[3]) {
            f = fArr[2];
            f2 = fArr[3];
            f3 = fArr[6];
            f4 = fArr[7];
            f5 = fArr[0];
            f6 = fArr[1];
        }
        float f7 = (f6 - f2) / (f5 - f);
        float f8 = (-1.0f) / f7;
        float f9 = f2 - (f7 * f);
        float f10 = f2 - (f * f8);
        float f11 = f4 - (f7 * f3);
        float f12 = f4 - (f3 * f8);
        float centerY = rectF.centerY() - rectF.top;
        float centerX = rectF.centerX();
        float f13 = rectF.left;
        float f14 = centerY / (centerX - f13);
        float f15 = -f14;
        float f16 = rectF.top;
        float f17 = f16 - (f13 * f14);
        float f18 = rectF.right;
        float f19 = f16 - (f15 * f18);
        float f20 = f7 - f14;
        float f21 = (f17 - f9) / f20;
        float max = Math.max(u, f21 < f18 ? f21 : u);
        float f22 = (f17 - f10) / (f8 - f14);
        if (f22 >= rectF.right) {
            f22 = max;
        }
        float max2 = Math.max(max, f22);
        float f23 = f8 - f15;
        float f24 = (f19 - f12) / f23;
        if (f24 >= rectF.right) {
            f24 = max2;
        }
        float max3 = Math.max(max2, f24);
        float f25 = (f19 - f10) / f23;
        if (f25 <= rectF.left) {
            f25 = v;
        }
        float min = Math.min(v, f25);
        float f26 = (f19 - f11) / (f7 - f15);
        if (f26 <= rectF.left) {
            f26 = min;
        }
        float min2 = Math.min(min, f26);
        float f27 = (f17 - f11) / f20;
        if (f27 <= rectF.left) {
            f27 = min2;
        }
        float min3 = Math.min(min2, f27);
        float max4 = Math.max(w, Math.max((f7 * max3) + f9, (f8 * min3) + f10));
        float min4 = Math.min(p, Math.min((f8 * max3) + f12, (f7 * min3) + f11));
        RectF rectF2 = this.s;
        rectF2.left = max3;
        rectF2.top = max4;
        rectF2.right = min3;
        rectF2.bottom = min4;
        return true;
    }

    public final void c(boolean z) {
        try {
            CropWindowChangeListener cropWindowChangeListener = this.k;
            if (cropWindowChangeListener != null) {
                cropWindowChangeListener.onCropWindowChanged(z);
            }
        } catch (Exception e) {
            Log.e("AIC", "Exception in crop window changed", e);
        }
    }

    public final void d(Canvas canvas) {
        int i;
        RectF h = this.j.h();
        float max = Math.max(com.theartofdev.edmodo.cropper.b.u(this.r), 0.0f);
        float max2 = Math.max(com.theartofdev.edmodo.cropper.b.w(this.r), 0.0f);
        float min = Math.min(com.theartofdev.edmodo.cropper.b.v(this.r), getWidth());
        float min2 = Math.min(com.theartofdev.edmodo.cropper.b.p(this.r), getHeight());
        if (this.G == CropImageView.CropShape.RECTANGLE) {
            if (l() && (i = Build.VERSION.SDK_INT) > 17) {
                this.q.reset();
                Path path = this.q;
                float[] fArr = this.r;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.q;
                float[] fArr2 = this.r;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.q;
                float[] fArr3 = this.r;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.q;
                float[] fArr4 = this.r;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.q.close();
                canvas.save();
                if (i >= 26) {
                    canvas.clipOutPath(this.q);
                } else {
                    canvas.clipPath(this.q, Region.Op.INTERSECT);
                }
                canvas.clipRect(h, Region.Op.XOR);
                canvas.drawRect(max, max2, min, min2, this.p);
                canvas.restore();
                return;
            }
            canvas.drawRect(max, max2, min, h.top, this.p);
            canvas.drawRect(max, h.bottom, min, min2, this.p);
            canvas.drawRect(max, h.top, h.left, h.bottom, this.p);
            canvas.drawRect(h.right, h.top, min, h.bottom, this.p);
            return;
        }
        this.q.reset();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 17 && this.G == CropImageView.CropShape.OVAL) {
            this.l.set(h.left + 2.0f, h.top + 2.0f, h.right - 2.0f, h.bottom - 2.0f);
        } else {
            this.l.set(h.left, h.top, h.right, h.bottom);
        }
        this.q.addOval(this.l, Path.Direction.CW);
        canvas.save();
        if (i2 >= 26) {
            canvas.clipOutPath(this.q);
        } else {
            canvas.clipPath(this.q, Region.Op.XOR);
        }
        canvas.drawRect(max, max2, min, min2, this.p);
        canvas.restore();
    }

    public final void e(Canvas canvas) {
        Paint paint = this.m;
        if (paint != null) {
            float strokeWidth = paint.getStrokeWidth();
            RectF h = this.j.h();
            float f = strokeWidth / 2.0f;
            h.inset(f, f);
            if (this.G == CropImageView.CropShape.RECTANGLE) {
                canvas.drawRect(h, this.m);
            } else {
                canvas.drawOval(h, this.m);
            }
        }
    }

    public final void f(Canvas canvas) {
        if (this.n != null) {
            Paint paint = this.m;
            float strokeWidth = paint != null ? paint.getStrokeWidth() : 0.0f;
            float strokeWidth2 = this.n.getStrokeWidth();
            float f = strokeWidth2 / 2.0f;
            float f2 = (this.G == CropImageView.CropShape.RECTANGLE ? this.v : 0.0f) + f;
            RectF h = this.j.h();
            h.inset(f2, f2);
            float f3 = (strokeWidth2 - strokeWidth) / 2.0f;
            float f4 = f + f3;
            float f5 = h.left;
            float f6 = h.top;
            canvas.drawLine(f5 - f3, f6 - f4, f5 - f3, f6 + this.w, this.n);
            float f7 = h.left;
            float f8 = h.top;
            canvas.drawLine(f7 - f4, f8 - f3, f7 + this.w, f8 - f3, this.n);
            float f9 = h.right;
            float f10 = h.top;
            canvas.drawLine(f9 + f3, f10 - f4, f9 + f3, f10 + this.w, this.n);
            float f11 = h.right;
            float f12 = h.top;
            canvas.drawLine(f11 + f4, f12 - f3, f11 - this.w, f12 - f3, this.n);
            float f13 = h.left;
            float f14 = h.bottom;
            canvas.drawLine(f13 - f3, f14 + f4, f13 - f3, f14 - this.w, this.n);
            float f15 = h.left;
            float f16 = h.bottom;
            canvas.drawLine(f15 - f4, f16 + f3, f15 + this.w, f16 + f3, this.n);
            float f17 = h.right;
            float f18 = h.bottom;
            canvas.drawLine(f17 + f3, f18 + f4, f17 + f3, f18 - this.w, this.n);
            float f19 = h.right;
            float f20 = h.bottom;
            canvas.drawLine(f19 + f4, f20 + f3, f19 - this.w, f20 + f3, this.n);
        }
    }

    public void fixCurrentCropWindowRect() {
        RectF cropWindowRect = getCropWindowRect();
        h(cropWindowRect);
        this.j.t(cropWindowRect);
    }

    public final void g(Canvas canvas) {
        if (this.o != null) {
            Paint paint = this.m;
            float strokeWidth = paint != null ? paint.getStrokeWidth() : 0.0f;
            RectF h = this.j.h();
            h.inset(strokeWidth, strokeWidth);
            float width = h.width() / 3.0f;
            float height = h.height() / 3.0f;
            if (this.G == CropImageView.CropShape.OVAL) {
                float width2 = (h.width() / 2.0f) - strokeWidth;
                float height2 = (h.height() / 2.0f) - strokeWidth;
                float f = h.left + width;
                float f2 = h.right - width;
                float sin = (float) (height2 * Math.sin(Math.acos((width2 - width) / width2)));
                canvas.drawLine(f, (h.top + height2) - sin, f, (h.bottom - height2) + sin, this.o);
                canvas.drawLine(f2, (h.top + height2) - sin, f2, (h.bottom - height2) + sin, this.o);
                float f3 = h.top + height;
                float f4 = h.bottom - height;
                float cos = (float) (width2 * Math.cos(Math.asin((height2 - height) / height2)));
                canvas.drawLine((h.left + width2) - cos, f3, (h.right - width2) + cos, f3, this.o);
                canvas.drawLine((h.left + width2) - cos, f4, (h.right - width2) + cos, f4, this.o);
                return;
            }
            float f5 = h.left + width;
            float f6 = h.right - width;
            canvas.drawLine(f5, h.top, f5, h.bottom, this.o);
            canvas.drawLine(f6, h.top, f6, h.bottom, this.o);
            float f7 = h.top + height;
            float f8 = h.bottom - height;
            canvas.drawLine(h.left, f7, h.right, f7, this.o);
            canvas.drawLine(h.left, f8, h.right, f8, this.o);
        }
    }

    public int getAspectRatioX() {
        return this.C;
    }

    public int getAspectRatioY() {
        return this.D;
    }

    public CropImageView.CropShape getCropShape() {
        return this.G;
    }

    public RectF getCropWindowRect() {
        return this.j.h();
    }

    public CropImageView.Guidelines getGuidelines() {
        return this.F;
    }

    public Rect getInitialCropWindowRect() {
        return this.H;
    }

    public final void h(RectF rectF) {
        if (rectF.width() < this.j.e()) {
            float e = (this.j.e() - rectF.width()) / 2.0f;
            rectF.left -= e;
            rectF.right += e;
        }
        if (rectF.height() < this.j.d()) {
            float d = (this.j.d() - rectF.height()) / 2.0f;
            rectF.top -= d;
            rectF.bottom += d;
        }
        if (rectF.width() > this.j.c()) {
            float width = (rectF.width() - this.j.c()) / 2.0f;
            rectF.left += width;
            rectF.right -= width;
        }
        if (rectF.height() > this.j.b()) {
            float height = (rectF.height() - this.j.b()) / 2.0f;
            rectF.top += height;
            rectF.bottom -= height;
        }
        b(rectF);
        if (this.s.width() > 0.0f && this.s.height() > 0.0f) {
            float max = Math.max(this.s.left, 0.0f);
            float max2 = Math.max(this.s.top, 0.0f);
            float min = Math.min(this.s.right, getWidth());
            float min2 = Math.min(this.s.bottom, getHeight());
            if (rectF.left < max) {
                rectF.left = max;
            }
            if (rectF.top < max2) {
                rectF.top = max2;
            }
            if (rectF.right > min) {
                rectF.right = min;
            }
            if (rectF.bottom > min2) {
                rectF.bottom = min2;
            }
        }
        if (!this.B || Math.abs(rectF.width() - (rectF.height() * this.E)) <= 0.1d) {
            return;
        }
        if (rectF.width() > rectF.height() * this.E) {
            float abs = Math.abs((rectF.height() * this.E) - rectF.width()) / 2.0f;
            rectF.left += abs;
            rectF.right -= abs;
            return;
        }
        float abs2 = Math.abs((rectF.width() / this.E) - rectF.height()) / 2.0f;
        rectF.top += abs2;
        rectF.bottom -= abs2;
    }

    public boolean isFixAspectRatio() {
        return this.B;
    }

    public final void k() {
        float max = Math.max(com.theartofdev.edmodo.cropper.b.u(this.r), 0.0f);
        float max2 = Math.max(com.theartofdev.edmodo.cropper.b.w(this.r), 0.0f);
        float min = Math.min(com.theartofdev.edmodo.cropper.b.v(this.r), getWidth());
        float min2 = Math.min(com.theartofdev.edmodo.cropper.b.p(this.r), getHeight());
        if (min <= max || min2 <= max2) {
            return;
        }
        RectF rectF = new RectF();
        this.I = true;
        float f = this.x;
        float f2 = min - max;
        float f3 = f * f2;
        float f4 = min2 - max2;
        float f5 = f * f4;
        if (this.H.width() > 0 && this.H.height() > 0) {
            rectF.left = (this.H.left / this.j.k()) + max;
            rectF.top = (this.H.top / this.j.j()) + max2;
            rectF.right = rectF.left + (this.H.width() / this.j.k());
            rectF.bottom = rectF.top + (this.H.height() / this.j.j());
            rectF.left = Math.max(max, rectF.left);
            rectF.top = Math.max(max2, rectF.top);
            rectF.right = Math.min(min, rectF.right);
            rectF.bottom = Math.min(min2, rectF.bottom);
        } else if (this.B && min > max && min2 > max2) {
            if (f2 / f4 > this.E) {
                rectF.top = max2 + f5;
                rectF.bottom = min2 - f5;
                float width = getWidth() / 2.0f;
                this.E = this.C / this.D;
                float max3 = Math.max(this.j.e(), rectF.height() * this.E) / 2.0f;
                rectF.left = width - max3;
                rectF.right = width + max3;
            } else {
                rectF.left = max + f3;
                rectF.right = min - f3;
                float height = getHeight() / 2.0f;
                float max4 = Math.max(this.j.d(), rectF.width() / this.E) / 2.0f;
                rectF.top = height - max4;
                rectF.bottom = height + max4;
            }
        } else {
            rectF.left = max + f3;
            rectF.top = max2 + f5;
            rectF.right = min - f3;
            rectF.bottom = min2 - f5;
        }
        h(rectF);
        this.j.t(rectF);
    }

    public final boolean l() {
        float[] fArr = this.r;
        return (fArr[0] == fArr[6] || fArr[1] == fArr[7]) ? false : true;
    }

    public final void m(float f, float f2) {
        CropWindowMoveHandler f3 = this.j.f(f, f2, this.y, this.G);
        this.A = f3;
        if (f3 != null) {
            invalidate();
        }
    }

    public final void n(float f, float f2) {
        if (this.A != null) {
            float f3 = this.z;
            RectF h = this.j.h();
            if (b(h)) {
                f3 = 0.0f;
            }
            this.A.m(h, f, f2, this.s, this.t, this.u, f3, this.B, this.E);
            this.j.t(h);
            c(true);
            invalidate();
        }
    }

    public final void o() {
        if (this.A != null) {
            this.A = null;
            c(false);
            invalidate();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        d(canvas);
        if (this.j.u()) {
            CropImageView.Guidelines guidelines = this.F;
            if (guidelines == CropImageView.Guidelines.ON) {
                g(canvas);
            } else if (guidelines == CropImageView.Guidelines.ON_TOUCH && this.A != null) {
                g(canvas);
            }
        }
        e(canvas);
        f(canvas);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            if (this.i) {
                this.h.onTouchEvent(motionEvent);
            }
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        n(motionEvent.getX(), motionEvent.getY());
                        getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    } else if (action != 3) {
                        return false;
                    }
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                o();
                return true;
            }
            m(motionEvent.getX(), motionEvent.getY());
            return true;
        }
        return false;
    }

    public void resetCropOverlayView() {
        if (this.I) {
            setCropWindowRect(com.theartofdev.edmodo.cropper.b.b);
            k();
            invalidate();
        }
    }

    public void resetCropWindowRect() {
        if (this.I) {
            k();
            invalidate();
            c(false);
        }
    }

    public void setAspectRatioX(int i) {
        if (i > 0) {
            if (this.C != i) {
                this.C = i;
                this.E = i / this.D;
                if (this.I) {
                    k();
                    invalidate();
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
    }

    public void setAspectRatioY(int i) {
        if (i > 0) {
            if (this.D != i) {
                this.D = i;
                this.E = this.C / i;
                if (this.I) {
                    k();
                    invalidate();
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
    }

    public void setBounds(float[] fArr, int i, int i2) {
        if (fArr == null || !Arrays.equals(this.r, fArr)) {
            if (fArr == null) {
                Arrays.fill(this.r, 0.0f);
            } else {
                System.arraycopy(fArr, 0, this.r, 0, fArr.length);
            }
            this.t = i;
            this.u = i2;
            RectF h = this.j.h();
            if (h.width() == 0.0f || h.height() == 0.0f) {
                k();
            }
        }
    }

    public void setCropShape(CropImageView.CropShape cropShape) {
        if (this.G != cropShape) {
            this.G = cropShape;
            if (Build.VERSION.SDK_INT <= 17) {
                if (cropShape == CropImageView.CropShape.OVAL) {
                    Integer valueOf = Integer.valueOf(getLayerType());
                    this.J = valueOf;
                    if (valueOf.intValue() != 1) {
                        setLayerType(1, null);
                    } else {
                        this.J = null;
                    }
                } else {
                    Integer num = this.J;
                    if (num != null) {
                        setLayerType(num.intValue(), null);
                        this.J = null;
                    }
                }
            }
            invalidate();
        }
    }

    public void setCropWindowChangeListener(CropWindowChangeListener cropWindowChangeListener) {
        this.k = cropWindowChangeListener;
    }

    public void setCropWindowLimits(float f, float f2, float f3, float f4) {
        this.j.p(f, f2, f3, f4);
    }

    public void setCropWindowRect(RectF rectF) {
        this.j.t(rectF);
    }

    public void setFixedAspectRatio(boolean z) {
        if (this.B != z) {
            this.B = z;
            if (this.I) {
                k();
                invalidate();
            }
        }
    }

    public void setGuidelines(CropImageView.Guidelines guidelines) {
        if (this.F != guidelines) {
            this.F = guidelines;
            if (this.I) {
                invalidate();
            }
        }
    }

    public void setInitialAttributeValues(CropImageOptions cropImageOptions) {
        this.j.q(cropImageOptions);
        setCropShape(cropImageOptions.cropShape);
        setSnapRadius(cropImageOptions.snapRadius);
        setGuidelines(cropImageOptions.guidelines);
        setFixedAspectRatio(cropImageOptions.fixAspectRatio);
        setAspectRatioX(cropImageOptions.aspectRatioX);
        setAspectRatioY(cropImageOptions.aspectRatioY);
        setMultiTouchEnabled(cropImageOptions.multiTouchEnabled);
        this.y = cropImageOptions.touchRadius;
        this.x = cropImageOptions.initialCropWindowPaddingRatio;
        this.m = j(cropImageOptions.borderLineThickness, cropImageOptions.borderLineColor);
        this.v = cropImageOptions.borderCornerOffset;
        this.w = cropImageOptions.borderCornerLength;
        this.n = j(cropImageOptions.borderCornerThickness, cropImageOptions.borderCornerColor);
        this.o = j(cropImageOptions.guidelinesThickness, cropImageOptions.guidelinesColor);
        this.p = i(cropImageOptions.backgroundColor);
    }

    public void setInitialCropWindowRect(Rect rect) {
        Rect rect2 = this.H;
        if (rect == null) {
            rect = com.theartofdev.edmodo.cropper.b.f13726a;
        }
        rect2.set(rect);
        if (this.I) {
            k();
            invalidate();
            c(false);
        }
    }

    public void setMaxCropResultSize(int i, int i2) {
        this.j.r(i, i2);
    }

    public void setMinCropResultSize(int i, int i2) {
        this.j.s(i, i2);
    }

    public boolean setMultiTouchEnabled(boolean z) {
        if (this.i != z) {
            this.i = z;
            if (z && this.h == null) {
                this.h = new ScaleGestureDetector(getContext(), new b());
                return true;
            }
            return true;
        }
        return false;
    }

    public void setSnapRadius(float f) {
        this.z = f;
    }

    public CropOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = new d();
        this.l = new RectF();
        this.q = new Path();
        this.r = new float[8];
        this.s = new RectF();
        this.E = this.C / this.D;
        this.H = new Rect();
    }
}
