package com.theartofdev.edmodo.cropper;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
/* loaded from: classes12.dex */
public final class CropWindowMoveHandler {

    /* renamed from: a  reason: collision with root package name */
    public final float f13722a;
    public final float b;
    public final float c;
    public final float d;
    public final Type e;
    public final PointF f = new PointF();

    /* loaded from: classes12.dex */
    public enum Type {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        CENTER
    }

    /* loaded from: classes12.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f13723a;

        static {
            int[] iArr = new int[Type.values().length];
            f13723a = iArr;
            try {
                iArr[Type.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13723a[Type.TOP_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13723a[Type.BOTTOM_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13723a[Type.BOTTOM_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13723a[Type.LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13723a[Type.TOP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13723a[Type.RIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f13723a[Type.BOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f13723a[Type.CENTER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    static {
        new Matrix();
    }

    public CropWindowMoveHandler(Type type, d dVar, float f, float f2) {
        this.e = type;
        this.f13722a = dVar.e();
        this.b = dVar.d();
        this.c = dVar.c();
        this.d = dVar.b();
        l(dVar.h(), f, f2);
    }

    public static float k(float f, float f2, float f3, float f4) {
        return (f3 - f) / (f4 - f2);
    }

    public final void a(RectF rectF, float f, RectF rectF2, int i, float f2, float f3, boolean z, boolean z2) {
        float f4 = i;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.f.y -= (f - f4) / 1.1f;
        }
        float f5 = rectF2.bottom;
        if (f > f5) {
            this.f.y -= (f - f5) / 2.0f;
        }
        if (f5 - f < f2) {
            f = f5;
        }
        float f6 = rectF.top;
        float f7 = this.b;
        if (f - f6 < f7) {
            f = f6 + f7;
        }
        float f8 = this.d;
        if (f - f6 > f8) {
            f = f6 + f8;
        }
        if (f5 - f < f2) {
            f = f5;
        }
        if (f3 > 0.0f) {
            float f9 = (f - f6) * f3;
            float f10 = this.f13722a;
            if (f9 < f10) {
                f = Math.min(f5, f6 + (f10 / f3));
                f9 = (f - rectF.top) * f3;
            }
            float f11 = this.c;
            if (f9 > f11) {
                f = Math.min(rectF2.bottom, rectF.top + (f11 / f3));
                f9 = (f - rectF.top) * f3;
            }
            if (z && z2) {
                f = Math.min(f, Math.min(rectF2.bottom, rectF.top + (rectF2.width() / f3)));
            } else {
                if (z) {
                    float f12 = rectF.right;
                    float f13 = rectF2.left;
                    if (f12 - f9 < f13) {
                        f = Math.min(rectF2.bottom, rectF.top + ((f12 - f13) / f3));
                        f9 = (f - rectF.top) * f3;
                    }
                }
                if (z2) {
                    float f14 = rectF.left;
                    float f15 = rectF2.right;
                    if (f9 + f14 > f15) {
                        f = Math.min(f, Math.min(rectF2.bottom, rectF.top + ((f15 - f14) / f3)));
                    }
                }
            }
        }
        rectF.bottom = f;
    }

    public final void b(RectF rectF, float f) {
        rectF.bottom = rectF.top + (rectF.width() / f);
    }

    public final void c(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.f.x -= f / 1.1f;
        }
        float f4 = rectF2.left;
        if (f < f4) {
            this.f.x -= (f - f4) / 2.0f;
        }
        if (f - f4 < f2) {
            f = f4;
        }
        float f5 = rectF.right;
        float f6 = this.f13722a;
        if (f5 - f < f6) {
            f = f5 - f6;
        }
        float f7 = this.c;
        if (f5 - f > f7) {
            f = f5 - f7;
        }
        if (f - f4 < f2) {
            f = f4;
        }
        if (f3 > 0.0f) {
            float f8 = (f5 - f) / f3;
            float f9 = this.b;
            if (f8 < f9) {
                f = Math.max(f4, f5 - (f9 * f3));
                f8 = (rectF.right - f) / f3;
            }
            float f10 = this.d;
            if (f8 > f10) {
                f = Math.max(rectF2.left, rectF.right - (f10 * f3));
                f8 = (rectF.right - f) / f3;
            }
            if (z && z2) {
                f = Math.max(f, Math.max(rectF2.left, rectF.right - (rectF2.height() * f3)));
            } else {
                if (z) {
                    float f11 = rectF.bottom;
                    float f12 = rectF2.top;
                    if (f11 - f8 < f12) {
                        f = Math.max(rectF2.left, rectF.right - ((f11 - f12) * f3));
                        f8 = (rectF.right - f) / f3;
                    }
                }
                if (z2) {
                    float f13 = rectF.top;
                    float f14 = rectF2.bottom;
                    if (f8 + f13 > f14) {
                        f = Math.max(f, Math.max(rectF2.left, rectF.right - ((f14 - f13) * f3)));
                    }
                }
            }
        }
        rectF.left = f;
    }

    public final void d(RectF rectF, float f) {
        rectF.left = rectF.right - (rectF.height() * f);
    }

    public final void e(RectF rectF, RectF rectF2, float f) {
        rectF.inset((rectF.width() - (rectF.height() * f)) / 2.0f, 0.0f);
        float f2 = rectF.left;
        float f3 = rectF2.left;
        if (f2 < f3) {
            rectF.offset(f3 - f2, 0.0f);
        }
        float f4 = rectF.right;
        float f5 = rectF2.right;
        if (f4 > f5) {
            rectF.offset(f5 - f4, 0.0f);
        }
    }

    public final void f(RectF rectF, float f, RectF rectF2, int i, float f2, float f3, boolean z, boolean z2) {
        float f4 = i;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.f.x -= (f - f4) / 1.1f;
        }
        float f5 = rectF2.right;
        if (f > f5) {
            this.f.x -= (f - f5) / 2.0f;
        }
        if (f5 - f < f2) {
            f = f5;
        }
        float f6 = rectF.left;
        float f7 = this.f13722a;
        if (f - f6 < f7) {
            f = f6 + f7;
        }
        float f8 = this.c;
        if (f - f6 > f8) {
            f = f6 + f8;
        }
        if (f5 - f < f2) {
            f = f5;
        }
        if (f3 > 0.0f) {
            float f9 = (f - f6) / f3;
            float f10 = this.b;
            if (f9 < f10) {
                f = Math.min(f5, f6 + (f10 * f3));
                f9 = (f - rectF.left) / f3;
            }
            float f11 = this.d;
            if (f9 > f11) {
                f = Math.min(rectF2.right, rectF.left + (f11 * f3));
                f9 = (f - rectF.left) / f3;
            }
            if (z && z2) {
                f = Math.min(f, Math.min(rectF2.right, rectF.left + (rectF2.height() * f3)));
            } else {
                if (z) {
                    float f12 = rectF.bottom;
                    float f13 = rectF2.top;
                    if (f12 - f9 < f13) {
                        f = Math.min(rectF2.right, rectF.left + ((f12 - f13) * f3));
                        f9 = (f - rectF.left) / f3;
                    }
                }
                if (z2) {
                    float f14 = rectF.top;
                    float f15 = rectF2.bottom;
                    if (f9 + f14 > f15) {
                        f = Math.min(f, Math.min(rectF2.right, rectF.left + ((f15 - f14) * f3)));
                    }
                }
            }
        }
        rectF.right = f;
    }

    public final void g(RectF rectF, float f) {
        rectF.right = rectF.left + (rectF.height() * f);
    }

    public final void h(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.f.y -= f / 1.1f;
        }
        float f4 = rectF2.top;
        if (f < f4) {
            this.f.y -= (f - f4) / 2.0f;
        }
        if (f - f4 < f2) {
            f = f4;
        }
        float f5 = rectF.bottom;
        float f6 = this.b;
        if (f5 - f < f6) {
            f = f5 - f6;
        }
        float f7 = this.d;
        if (f5 - f > f7) {
            f = f5 - f7;
        }
        if (f - f4 < f2) {
            f = f4;
        }
        if (f3 > 0.0f) {
            float f8 = (f5 - f) * f3;
            float f9 = this.f13722a;
            if (f8 < f9) {
                f = Math.max(f4, f5 - (f9 / f3));
                f8 = (rectF.bottom - f) * f3;
            }
            float f10 = this.c;
            if (f8 > f10) {
                f = Math.max(rectF2.top, rectF.bottom - (f10 / f3));
                f8 = (rectF.bottom - f) * f3;
            }
            if (z && z2) {
                f = Math.max(f, Math.max(rectF2.top, rectF.bottom - (rectF2.width() / f3)));
            } else {
                if (z) {
                    float f11 = rectF.right;
                    float f12 = rectF2.left;
                    if (f11 - f8 < f12) {
                        f = Math.max(rectF2.top, rectF.bottom - ((f11 - f12) / f3));
                        f8 = (rectF.bottom - f) * f3;
                    }
                }
                if (z2) {
                    float f13 = rectF.left;
                    float f14 = rectF2.right;
                    if (f8 + f13 > f14) {
                        f = Math.max(f, Math.max(rectF2.top, rectF.bottom - ((f14 - f13) / f3)));
                    }
                }
            }
        }
        rectF.top = f;
    }

    public final void i(RectF rectF, RectF rectF2, float f) {
        rectF.inset(0.0f, (rectF.height() - (rectF.width() / f)) / 2.0f);
        float f2 = rectF.top;
        float f3 = rectF2.top;
        if (f2 < f3) {
            rectF.offset(0.0f, f3 - f2);
        }
        float f4 = rectF.bottom;
        float f5 = rectF2.bottom;
        if (f4 > f5) {
            rectF.offset(0.0f, f5 - f4);
        }
    }

    public final void j(RectF rectF, float f) {
        rectF.top = rectF.bottom - (rectF.width() / f);
    }

    public final void l(RectF rectF, float f, float f2) {
        float f3;
        float f4;
        float f5;
        float f6 = 0.0f;
        switch (a.f13723a[this.e.ordinal()]) {
            case 1:
                f6 = rectF.left - f;
                f3 = rectF.top;
                f5 = f3 - f2;
                break;
            case 2:
                f6 = rectF.right - f;
                f3 = rectF.top;
                f5 = f3 - f2;
                break;
            case 3:
                f6 = rectF.left - f;
                f3 = rectF.bottom;
                f5 = f3 - f2;
                break;
            case 4:
                f6 = rectF.right - f;
                f3 = rectF.bottom;
                f5 = f3 - f2;
                break;
            case 5:
                f4 = rectF.left;
                f6 = f4 - f;
                f5 = 0.0f;
                break;
            case 6:
                f3 = rectF.top;
                f5 = f3 - f2;
                break;
            case 7:
                f4 = rectF.right;
                f6 = f4 - f;
                f5 = 0.0f;
                break;
            case 8:
                f3 = rectF.bottom;
                f5 = f3 - f2;
                break;
            case 9:
                f6 = rectF.centerX() - f;
                f3 = rectF.centerY();
                f5 = f3 - f2;
                break;
            default:
                f5 = 0.0f;
                break;
        }
        PointF pointF = this.f;
        pointF.x = f6;
        pointF.y = f5;
    }

    public void m(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3, boolean z, float f4) {
        PointF pointF = this.f;
        float f5 = f + pointF.x;
        float f6 = f2 + pointF.y;
        if (this.e == Type.CENTER) {
            n(rectF, f5, f6, rectF2, i, i2, f3);
        } else if (z) {
            o(rectF, f5, f6, rectF2, i, i2, f3, f4);
        } else {
            p(rectF, f5, f6, rectF2, i, i2, f3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0056, code lost:
        if ((r0 + r9) <= r10.bottom) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002d, code lost:
        if ((r1 + r8) <= r10.right) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n(android.graphics.RectF r7, float r8, float r9, android.graphics.RectF r10, int r11, int r12, float r13) {
        /*
            r6 = this;
            float r0 = r7.centerX()
            float r8 = r8 - r0
            float r0 = r7.centerY()
            float r9 = r9 - r0
            float r0 = r7.left
            float r1 = r0 + r8
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 1065772646(0x3f866666, float:1.05)
            if (r1 < 0) goto L2f
            float r1 = r7.right
            float r5 = r1 + r8
            float r11 = (float) r11
            int r11 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r11 > 0) goto L2f
            float r0 = r0 + r8
            float r11 = r10.left
            int r11 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r11 < 0) goto L2f
            float r1 = r1 + r8
            float r11 = r10.right
            int r11 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r11 <= 0) goto L39
        L2f:
            float r8 = r8 / r4
            android.graphics.PointF r11 = r6.f
            float r0 = r11.x
            float r1 = r8 / r3
            float r0 = r0 - r1
            r11.x = r0
        L39:
            float r11 = r7.top
            float r0 = r11 + r9
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L58
            float r0 = r7.bottom
            float r1 = r0 + r9
            float r12 = (float) r12
            int r12 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r12 > 0) goto L58
            float r11 = r11 + r9
            float r12 = r10.top
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 < 0) goto L58
            float r0 = r0 + r9
            float r11 = r10.bottom
            int r11 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r11 <= 0) goto L62
        L58:
            float r9 = r9 / r4
            android.graphics.PointF r11 = r6.f
            float r12 = r11.y
            float r0 = r9 / r3
            float r12 = r12 - r0
            r11.y = r12
        L62:
            r7.offset(r8, r9)
            r6.q(r7, r10, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.CropWindowMoveHandler.n(android.graphics.RectF, float, float, android.graphics.RectF, int, int, float):void");
    }

    public final void o(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3, float f4) {
        switch (a.f13723a[this.e.ordinal()]) {
            case 1:
                if (k(f, f2, rectF.right, rectF.bottom) < f4) {
                    h(rectF, f2, rectF2, f3, f4, true, false);
                    d(rectF, f4);
                    return;
                }
                c(rectF, f, rectF2, f3, f4, true, false);
                j(rectF, f4);
                return;
            case 2:
                if (k(rectF.left, f2, f, rectF.bottom) < f4) {
                    h(rectF, f2, rectF2, f3, f4, false, true);
                    g(rectF, f4);
                    return;
                }
                f(rectF, f, rectF2, i, f3, f4, true, false);
                j(rectF, f4);
                return;
            case 3:
                if (k(f, rectF.top, rectF.right, f2) < f4) {
                    a(rectF, f2, rectF2, i2, f3, f4, true, false);
                    d(rectF, f4);
                    return;
                }
                c(rectF, f, rectF2, f3, f4, false, true);
                b(rectF, f4);
                return;
            case 4:
                if (k(rectF.left, rectF.top, f, f2) < f4) {
                    a(rectF, f2, rectF2, i2, f3, f4, false, true);
                    g(rectF, f4);
                    return;
                }
                f(rectF, f, rectF2, i, f3, f4, false, true);
                b(rectF, f4);
                return;
            case 5:
                c(rectF, f, rectF2, f3, f4, true, true);
                i(rectF, rectF2, f4);
                return;
            case 6:
                h(rectF, f2, rectF2, f3, f4, true, true);
                e(rectF, rectF2, f4);
                return;
            case 7:
                f(rectF, f, rectF2, i, f3, f4, true, true);
                i(rectF, rectF2, f4);
                return;
            case 8:
                a(rectF, f2, rectF2, i2, f3, f4, true, true);
                e(rectF, rectF2, f4);
                return;
            default:
                return;
        }
    }

    public final void p(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3) {
        switch (a.f13723a[this.e.ordinal()]) {
            case 1:
                h(rectF, f2, rectF2, f3, 0.0f, false, false);
                c(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 2:
                h(rectF, f2, rectF2, f3, 0.0f, false, false);
                f(rectF, f, rectF2, i, f3, 0.0f, false, false);
                return;
            case 3:
                a(rectF, f2, rectF2, i2, f3, 0.0f, false, false);
                c(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 4:
                a(rectF, f2, rectF2, i2, f3, 0.0f, false, false);
                f(rectF, f, rectF2, i, f3, 0.0f, false, false);
                return;
            case 5:
                c(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 6:
                h(rectF, f2, rectF2, f3, 0.0f, false, false);
                return;
            case 7:
                f(rectF, f, rectF2, i, f3, 0.0f, false, false);
                return;
            case 8:
                a(rectF, f2, rectF2, i2, f3, 0.0f, false, false);
                return;
            default:
                return;
        }
    }

    public final void q(RectF rectF, RectF rectF2, float f) {
        float f2 = rectF.left;
        float f3 = rectF2.left;
        if (f2 < f3 + f) {
            rectF.offset(f3 - f2, 0.0f);
        }
        float f4 = rectF.top;
        float f5 = rectF2.top;
        if (f4 < f5 + f) {
            rectF.offset(0.0f, f5 - f4);
        }
        float f6 = rectF.right;
        float f7 = rectF2.right;
        if (f6 > f7 - f) {
            rectF.offset(f7 - f6, 0.0f);
        }
        float f8 = rectF.bottom;
        float f9 = rectF2.bottom;
        if (f8 > f9 - f) {
            rectF.offset(0.0f, f9 - f8);
        }
    }
}
