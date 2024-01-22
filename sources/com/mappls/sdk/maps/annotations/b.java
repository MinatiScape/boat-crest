package com.mappls.sdk.maps.annotations;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
@Deprecated
/* loaded from: classes11.dex */
public class b extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public RectF f12672a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float g;
    public Paint h;
    public Path i;
    @NonNull
    public Paint f = new Paint(1);
    @NonNull
    public Path j = new Path();

    public b(@NonNull RectF rectF, @NonNull a aVar, float f, float f2, float f3, float f4, int i, float f5, int i2) {
        this.f12672a = rectF;
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f.setColor(i);
        this.g = f5;
        if (f5 > 0.0f) {
            Paint paint = new Paint(1);
            this.h = paint;
            paint.setColor(i2);
            this.i = new Path();
            e(aVar, this.j, f5);
            e(aVar, this.i, 0.0f);
            return;
        }
        e(aVar, this.j, 0.0f);
    }

    public final void a(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(rectF.left + this.e + f, rectF.top + f);
        path.lineTo((rectF.width() - this.e) - f, rectF.top + f);
        float f2 = rectF.right;
        float f3 = this.e;
        float f4 = rectF.top;
        path.arcTo(new RectF(f2 - f3, f4 + f, f2 - f, f3 + f4), 270.0f, 90.0f);
        path.lineTo(rectF.right - f, ((rectF.bottom - this.c) - this.e) - f);
        float f5 = rectF.right;
        float f6 = this.e;
        float f7 = rectF.bottom;
        float f8 = this.c;
        path.arcTo(new RectF(f5 - f6, (f7 - f6) - f8, f5 - f, (f7 - f8) - f), 0.0f, 90.0f);
        float f9 = f / 2.0f;
        path.lineTo(((rectF.left + this.b) + this.d) - f9, (rectF.bottom - this.c) - f);
        path.lineTo(rectF.left + this.d + (this.b / 2.0f), (rectF.bottom - f) - f);
        path.lineTo(rectF.left + this.d + f9, (rectF.bottom - this.c) - f);
        path.lineTo(rectF.left + Math.min(this.e, this.d) + f, (rectF.bottom - this.c) - f);
        float f10 = rectF.left;
        float f11 = rectF.bottom;
        float f12 = this.e;
        float f13 = this.c;
        path.arcTo(new RectF(f10 + f, (f11 - f12) - f13, f12 + f10, (f11 - f13) - f), 90.0f, 90.0f);
        path.lineTo(rectF.left + f, rectF.top + this.e + f);
        float f14 = rectF.left;
        float f15 = rectF.top;
        float f16 = this.e;
        path.arcTo(new RectF(f14 + f, f + f15, f14 + f16, f16 + f15), 180.0f, 90.0f);
        path.close();
    }

    public final void b(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(rectF.left + f, rectF.top + f);
        path.lineTo(rectF.right - f, rectF.top + f);
        path.lineTo(rectF.right - f, (rectF.bottom - this.c) - f);
        float f2 = f / 2.0f;
        path.lineTo(((rectF.left + this.b) + this.d) - f2, (rectF.bottom - this.c) - f);
        path.lineTo(rectF.left + this.d + (this.b / 2.0f), (rectF.bottom - f) - f);
        path.lineTo(rectF.left + this.d + f2, (rectF.bottom - this.c) - f);
        path.lineTo(rectF.left + this.d + f, (rectF.bottom - this.c) - f);
        path.lineTo(rectF.left + f, (rectF.bottom - this.c) - f);
        path.lineTo(rectF.left + f, rectF.top + f);
        path.close();
    }

    public final void c(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(this.b + rectF.left + this.e + f, rectF.top + f);
        path.lineTo((rectF.width() - this.e) - f, rectF.top + f);
        float f2 = rectF.right;
        float f3 = this.e;
        float f4 = rectF.top;
        path.arcTo(new RectF(f2 - f3, f4 + f, f2 - f, f3 + f4), 270.0f, 90.0f);
        path.lineTo(rectF.right - f, (rectF.bottom - this.e) - f);
        float f5 = rectF.right;
        float f6 = this.e;
        float f7 = rectF.bottom;
        path.arcTo(new RectF(f5 - f6, f7 - f6, f5 - f, f7 - f), 0.0f, 90.0f);
        path.lineTo(rectF.left + this.b + this.e + f, rectF.bottom - f);
        float f8 = rectF.left;
        float f9 = this.b;
        float f10 = rectF.bottom;
        float f11 = this.e;
        path.arcTo(new RectF(f8 + f9 + f, f10 - f11, f11 + f8 + f9, f10 - f), 90.0f, 90.0f);
        float f12 = f / 2.0f;
        path.lineTo(rectF.left + this.b + f, (this.c + this.d) - f12);
        path.lineTo(rectF.left + f + f, this.d + (this.c / 2.0f));
        path.lineTo(rectF.left + this.b + f, this.d + f12);
        path.lineTo(rectF.left + this.b + f, rectF.top + this.e + f);
        float f13 = rectF.left;
        float f14 = this.b;
        float f15 = rectF.top;
        float f16 = this.e;
        path.arcTo(new RectF(f13 + f14 + f, f + f15, f13 + f16 + f14, f16 + f15), 180.0f, 90.0f);
        path.close();
    }

    public final void d(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(this.b + rectF.left + f, rectF.top + f);
        path.lineTo(rectF.width() - f, rectF.top + f);
        path.lineTo(rectF.right - f, rectF.bottom - f);
        path.lineTo(rectF.left + this.b + f, rectF.bottom - f);
        float f2 = f / 2.0f;
        path.lineTo(rectF.left + this.b + f, (this.c + this.d) - f2);
        path.lineTo(rectF.left + f + f, this.d + (this.c / 2.0f));
        path.lineTo(rectF.left + this.b + f, this.d + f2);
        path.lineTo(rectF.left + this.b + f, rectF.top + f);
        path.close();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (this.g > 0.0f) {
            canvas.drawPath(this.i, this.h);
        }
        canvas.drawPath(this.j, this.f);
    }

    public final void e(@NonNull a aVar, @NonNull Path path, float f) {
        int a2 = aVar.a();
        if (a2 == 0) {
            float f2 = this.e;
            if (f2 <= 0.0f) {
                d(this.f12672a, path, f);
            } else if (f > 0.0f && f > f2) {
                d(this.f12672a, path, f);
            } else {
                c(this.f12672a, path, f);
            }
        } else if (a2 == 1) {
            float f3 = this.e;
            if (f3 <= 0.0f) {
                g(this.f12672a, path, f);
            } else if (f > 0.0f && f > f3) {
                g(this.f12672a, path, f);
            } else {
                f(this.f12672a, path, f);
            }
        } else if (a2 == 2) {
            float f4 = this.e;
            if (f4 <= 0.0f) {
                i(this.f12672a, path, f);
            } else if (f > 0.0f && f > f4) {
                i(this.f12672a, path, f);
            } else {
                h(this.f12672a, path, f);
            }
        } else if (a2 != 3) {
        } else {
            float f5 = this.e;
            if (f5 <= 0.0f) {
                b(this.f12672a, path, f);
            } else if (f > 0.0f && f > f5) {
                b(this.f12672a, path, f);
            } else {
                a(this.f12672a, path, f);
            }
        }
    }

    public final void f(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(rectF.left + this.e + f, rectF.top + f);
        path.lineTo(((rectF.width() - this.e) - this.b) - f, rectF.top + f);
        float f2 = rectF.right;
        float f3 = this.e;
        float f4 = this.b;
        float f5 = rectF.top;
        path.arcTo(new RectF((f2 - f3) - f4, f5 + f, (f2 - f4) - f, f3 + f5), 270.0f, 90.0f);
        float f6 = f / 2.0f;
        path.lineTo((rectF.right - this.b) - f, this.d + f6);
        path.lineTo((rectF.right - f) - f, this.d + (this.c / 2.0f));
        path.lineTo((rectF.right - this.b) - f, (this.d + this.c) - f6);
        path.lineTo((rectF.right - this.b) - f, (rectF.bottom - this.e) - f);
        float f7 = rectF.right;
        float f8 = this.e;
        float f9 = this.b;
        float f10 = rectF.bottom;
        path.arcTo(new RectF((f7 - f8) - f9, f10 - f8, (f7 - f9) - f, f10 - f), 0.0f, 90.0f);
        path.lineTo(rectF.left + this.b + f, rectF.bottom - f);
        float f11 = rectF.left;
        float f12 = rectF.bottom;
        float f13 = this.e;
        path.arcTo(new RectF(f11 + f, f12 - f13, f13 + f11, f12 - f), 90.0f, 90.0f);
        float f14 = rectF.left;
        float f15 = rectF.top;
        float f16 = this.e;
        path.arcTo(new RectF(f14 + f, f + f15, f14 + f16, f16 + f15), 180.0f, 90.0f);
        path.close();
    }

    public final void g(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(rectF.left + f, rectF.top + f);
        path.lineTo((rectF.width() - this.b) - f, rectF.top + f);
        float f2 = f / 2.0f;
        path.lineTo((rectF.right - this.b) - f, this.d + f2);
        path.lineTo((rectF.right - f) - f, this.d + (this.c / 2.0f));
        path.lineTo((rectF.right - this.b) - f, (this.d + this.c) - f2);
        path.lineTo((rectF.right - this.b) - f, rectF.bottom - f);
        path.lineTo(rectF.left + f, rectF.bottom - f);
        path.lineTo(rectF.left + f, rectF.top + f);
        path.close();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.f12672a.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) this.f12672a.width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final void h(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(rectF.left + Math.min(this.d, this.e) + f, rectF.top + this.c + f);
        float f2 = f / 2.0f;
        path.lineTo(rectF.left + this.d + f2, rectF.top + this.c + f);
        path.lineTo(rectF.left + (this.b / 2.0f) + this.d, rectF.top + f + f);
        path.lineTo(((rectF.left + this.b) + this.d) - f2, rectF.top + this.c + f);
        path.lineTo((rectF.right - this.e) - f, rectF.top + this.c + f);
        float f3 = rectF.right;
        float f4 = this.e;
        float f5 = rectF.top;
        float f6 = this.c;
        path.arcTo(new RectF(f3 - f4, f5 + f6 + f, f3 - f, f4 + f5 + f6), 270.0f, 90.0f);
        path.lineTo(rectF.right - f, (rectF.bottom - this.e) - f);
        float f7 = rectF.right;
        float f8 = this.e;
        float f9 = rectF.bottom;
        path.arcTo(new RectF(f7 - f8, f9 - f8, f7 - f, f9 - f), 0.0f, 90.0f);
        path.lineTo(rectF.left + this.e + f, rectF.bottom - f);
        float f10 = rectF.left;
        float f11 = rectF.bottom;
        float f12 = this.e;
        path.arcTo(new RectF(f10 + f, f11 - f12, f12 + f10, f11 - f), 90.0f, 90.0f);
        path.lineTo(rectF.left + f, rectF.top + this.c + this.e + f);
        float f13 = rectF.left;
        float f14 = f13 + f;
        float f15 = rectF.top;
        float f16 = this.c;
        float f17 = f15 + f16 + f;
        float f18 = this.e;
        path.arcTo(new RectF(f14, f17, f13 + f18, f18 + f15 + f16), 180.0f, 90.0f);
        path.close();
    }

    public final void i(@NonNull RectF rectF, @NonNull Path path, float f) {
        path.moveTo(rectF.left + this.d + f, rectF.top + this.c + f);
        float f2 = f / 2.0f;
        path.lineTo(rectF.left + this.d + f2, rectF.top + this.c + f);
        path.lineTo(rectF.left + (this.b / 2.0f) + this.d, rectF.top + f + f);
        path.lineTo(((rectF.left + this.b) + this.d) - f2, rectF.top + this.c + f);
        path.lineTo(rectF.right - f, rectF.top + this.c + f);
        path.lineTo(rectF.right - f, rectF.bottom - f);
        path.lineTo(rectF.left + f, rectF.bottom - f);
        path.lineTo(rectF.left + f, rectF.top + this.c + f);
        path.lineTo(rectF.left + this.d + f, rectF.top + this.c + f);
        path.close();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f.setColorFilter(colorFilter);
    }
}
