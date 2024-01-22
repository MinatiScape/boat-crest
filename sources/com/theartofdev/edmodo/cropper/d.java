package com.theartofdev.edmodo.cropper;

import android.graphics.RectF;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.theartofdev.edmodo.cropper.CropWindowMoveHandler;
/* loaded from: classes12.dex */
public final class d {
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;

    /* renamed from: a  reason: collision with root package name */
    public final RectF f13729a = new RectF();
    public final RectF b = new RectF();
    public float k = 1.0f;
    public float l = 1.0f;

    public static boolean l(float f, float f2, float f3, float f4, float f5, float f6) {
        return f > f3 && f < f5 && f2 > f4 && f2 < f6;
    }

    public static boolean m(float f, float f2, float f3, float f4, float f5) {
        return Math.abs(f - f3) <= f5 && Math.abs(f2 - f4) <= f5;
    }

    public static boolean n(float f, float f2, float f3, float f4, float f5, float f6) {
        return f > f3 && f < f4 && Math.abs(f2 - f5) <= f6;
    }

    public static boolean o(float f, float f2, float f3, float f4, float f5, float f6) {
        return Math.abs(f - f3) <= f6 && f2 > f4 && f2 < f5;
    }

    public final boolean a() {
        return !u();
    }

    public float b() {
        return Math.min(this.f, this.j / this.l);
    }

    public float c() {
        return Math.min(this.e, this.i / this.k);
    }

    public float d() {
        return Math.max(this.d, this.h / this.l);
    }

    public float e() {
        return Math.max(this.c, this.g / this.k);
    }

    public CropWindowMoveHandler f(float f, float f2, float f3, CropImageView.CropShape cropShape) {
        CropWindowMoveHandler.Type i;
        if (cropShape == CropImageView.CropShape.OVAL) {
            i = g(f, f2);
        } else {
            i = i(f, f2, f3);
        }
        if (i != null) {
            return new CropWindowMoveHandler(i, this, f, f2);
        }
        return null;
    }

    public final CropWindowMoveHandler.Type g(float f, float f2) {
        float width = this.f13729a.width() / 6.0f;
        RectF rectF = this.f13729a;
        float f3 = rectF.left;
        float f4 = f3 + width;
        float f5 = f3 + (width * 5.0f);
        float height = rectF.height() / 6.0f;
        float f6 = this.f13729a.top;
        float f7 = f6 + height;
        float f8 = f6 + (height * 5.0f);
        if (f < f4) {
            if (f2 < f7) {
                return CropWindowMoveHandler.Type.TOP_LEFT;
            }
            if (f2 < f8) {
                return CropWindowMoveHandler.Type.LEFT;
            }
            return CropWindowMoveHandler.Type.BOTTOM_LEFT;
        } else if (f < f5) {
            if (f2 < f7) {
                return CropWindowMoveHandler.Type.TOP;
            }
            if (f2 < f8) {
                return CropWindowMoveHandler.Type.CENTER;
            }
            return CropWindowMoveHandler.Type.BOTTOM;
        } else if (f2 < f7) {
            return CropWindowMoveHandler.Type.TOP_RIGHT;
        } else {
            if (f2 < f8) {
                return CropWindowMoveHandler.Type.RIGHT;
            }
            return CropWindowMoveHandler.Type.BOTTOM_RIGHT;
        }
    }

    public RectF h() {
        this.b.set(this.f13729a);
        return this.b;
    }

    public final CropWindowMoveHandler.Type i(float f, float f2, float f3) {
        RectF rectF = this.f13729a;
        if (m(f, f2, rectF.left, rectF.top, f3)) {
            return CropWindowMoveHandler.Type.TOP_LEFT;
        }
        RectF rectF2 = this.f13729a;
        if (m(f, f2, rectF2.right, rectF2.top, f3)) {
            return CropWindowMoveHandler.Type.TOP_RIGHT;
        }
        RectF rectF3 = this.f13729a;
        if (m(f, f2, rectF3.left, rectF3.bottom, f3)) {
            return CropWindowMoveHandler.Type.BOTTOM_LEFT;
        }
        RectF rectF4 = this.f13729a;
        if (m(f, f2, rectF4.right, rectF4.bottom, f3)) {
            return CropWindowMoveHandler.Type.BOTTOM_RIGHT;
        }
        RectF rectF5 = this.f13729a;
        if (l(f, f2, rectF5.left, rectF5.top, rectF5.right, rectF5.bottom) && a()) {
            return CropWindowMoveHandler.Type.CENTER;
        }
        RectF rectF6 = this.f13729a;
        if (n(f, f2, rectF6.left, rectF6.right, rectF6.top, f3)) {
            return CropWindowMoveHandler.Type.TOP;
        }
        RectF rectF7 = this.f13729a;
        if (n(f, f2, rectF7.left, rectF7.right, rectF7.bottom, f3)) {
            return CropWindowMoveHandler.Type.BOTTOM;
        }
        RectF rectF8 = this.f13729a;
        if (o(f, f2, rectF8.left, rectF8.top, rectF8.bottom, f3)) {
            return CropWindowMoveHandler.Type.LEFT;
        }
        RectF rectF9 = this.f13729a;
        if (o(f, f2, rectF9.right, rectF9.top, rectF9.bottom, f3)) {
            return CropWindowMoveHandler.Type.RIGHT;
        }
        RectF rectF10 = this.f13729a;
        if (!l(f, f2, rectF10.left, rectF10.top, rectF10.right, rectF10.bottom) || a()) {
            return null;
        }
        return CropWindowMoveHandler.Type.CENTER;
    }

    public float j() {
        return this.l;
    }

    public float k() {
        return this.k;
    }

    public void p(float f, float f2, float f3, float f4) {
        this.e = f;
        this.f = f2;
        this.k = f3;
        this.l = f4;
    }

    public void q(CropImageOptions cropImageOptions) {
        this.c = cropImageOptions.minCropWindowWidth;
        this.d = cropImageOptions.minCropWindowHeight;
        this.g = cropImageOptions.minCropResultWidth;
        this.h = cropImageOptions.minCropResultHeight;
        this.i = cropImageOptions.maxCropResultWidth;
        this.j = cropImageOptions.maxCropResultHeight;
    }

    public void r(int i, int i2) {
        this.i = i;
        this.j = i2;
    }

    public void s(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    public void t(RectF rectF) {
        this.f13729a.set(rectF);
    }

    public boolean u() {
        return this.f13729a.width() >= 100.0f && this.f13729a.height() >= 100.0f;
    }
}
