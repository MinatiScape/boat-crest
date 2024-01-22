package com.google.android.material.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
@Deprecated
/* loaded from: classes10.dex */
public class ShadowDrawableWrapper extends DrawableWrapper {
    public static final double p = Math.cos(Math.toRadians(45.0d));
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Paint f10343a;
    @NonNull
    public final Paint b;
    @NonNull
    public final RectF c;
    public float d;
    public Path e;
    public float f;
    public float g;
    public float h;
    public boolean i;
    public final int j;
    public final int k;
    public final int l;
    public boolean m;
    public float n;
    public boolean o;

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f, float f2, float f3) {
        super(drawable);
        this.i = true;
        this.m = true;
        this.o = false;
        this.j = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.k = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.l = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        Paint paint = new Paint(5);
        this.f10343a = paint;
        paint.setStyle(Paint.Style.FILL);
        this.d = Math.round(f);
        this.c = new RectF();
        Paint paint2 = new Paint(paint);
        this.b = paint2;
        paint2.setAntiAlias(false);
        setShadowSize(f2, f3);
    }

    public static float calculateHorizontalPadding(float f, float f2, boolean z) {
        return z ? (float) (f + ((1.0d - p) * f2)) : f;
    }

    public static float calculateVerticalPadding(float f, float f2, boolean z) {
        return z ? (float) ((f * 1.5f) + ((1.0d - p) * f2)) : f * 1.5f;
    }

    public static int d(float f) {
        int round = Math.round(f);
        return round % 2 == 1 ? round - 1 : round;
    }

    public final void a(@NonNull Rect rect) {
        float f = this.f;
        float f2 = 1.5f * f;
        this.c.set(rect.left + f, rect.top + f2, rect.right - f, rect.bottom - f2);
        Drawable wrappedDrawable = getWrappedDrawable();
        RectF rectF = this.c;
        wrappedDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        b();
    }

    public final void b() {
        float f = this.d;
        RectF rectF = new RectF(-f, -f, f, f);
        RectF rectF2 = new RectF(rectF);
        float f2 = this.g;
        rectF2.inset(-f2, -f2);
        Path path = this.e;
        if (path == null) {
            this.e = new Path();
        } else {
            path.reset();
        }
        this.e.setFillType(Path.FillType.EVEN_ODD);
        this.e.moveTo(-this.d, 0.0f);
        this.e.rLineTo(-this.g, 0.0f);
        this.e.arcTo(rectF2, 180.0f, 90.0f, false);
        this.e.arcTo(rectF, 270.0f, -90.0f, false);
        this.e.close();
        float f3 = -rectF2.top;
        if (f3 > 0.0f) {
            float f4 = this.d / f3;
            this.f10343a.setShader(new RadialGradient(0.0f, 0.0f, f3, new int[]{0, this.j, this.k, this.l}, new float[]{0.0f, f4, ((1.0f - f4) / 2.0f) + f4, 1.0f}, Shader.TileMode.CLAMP));
        }
        this.b.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.j, this.k, this.l}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.b.setAntiAlias(false);
    }

    public final void c(@NonNull Canvas canvas) {
        int i;
        float f;
        int i2;
        float f2;
        float f3;
        float f4;
        int save = canvas.save();
        canvas.rotate(this.n, this.c.centerX(), this.c.centerY());
        float f5 = this.d;
        float f6 = (-f5) - this.g;
        float f7 = f5 * 2.0f;
        boolean z = this.c.width() - f7 > 0.0f;
        boolean z2 = this.c.height() - f7 > 0.0f;
        float f8 = this.h;
        float f9 = f5 / ((f8 - (0.5f * f8)) + f5);
        float f10 = f5 / ((f8 - (0.25f * f8)) + f5);
        float f11 = f5 / ((f8 - (f8 * 1.0f)) + f5);
        int save2 = canvas.save();
        RectF rectF = this.c;
        canvas.translate(rectF.left + f5, rectF.top + f5);
        canvas.scale(f9, f10);
        canvas.drawPath(this.e, this.f10343a);
        if (z) {
            canvas.scale(1.0f / f9, 1.0f);
            i = save2;
            f = f11;
            i2 = save;
            f2 = f10;
            canvas.drawRect(0.0f, f6, this.c.width() - f7, -this.d, this.b);
        } else {
            i = save2;
            f = f11;
            i2 = save;
            f2 = f10;
        }
        canvas.restoreToCount(i);
        int save3 = canvas.save();
        RectF rectF2 = this.c;
        canvas.translate(rectF2.right - f5, rectF2.bottom - f5);
        float f12 = f;
        canvas.scale(f9, f12);
        canvas.rotate(180.0f);
        canvas.drawPath(this.e, this.f10343a);
        if (z) {
            canvas.scale(1.0f / f9, 1.0f);
            f3 = f2;
            f4 = f12;
            canvas.drawRect(0.0f, f6, this.c.width() - f7, (-this.d) + this.g, this.b);
        } else {
            f3 = f2;
            f4 = f12;
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        RectF rectF3 = this.c;
        canvas.translate(rectF3.left + f5, rectF3.bottom - f5);
        canvas.scale(f9, f4);
        canvas.rotate(270.0f);
        canvas.drawPath(this.e, this.f10343a);
        if (z2) {
            canvas.scale(1.0f / f4, 1.0f);
            canvas.drawRect(0.0f, f6, this.c.height() - f7, -this.d, this.b);
        }
        canvas.restoreToCount(save4);
        int save5 = canvas.save();
        RectF rectF4 = this.c;
        canvas.translate(rectF4.right - f5, rectF4.top + f5);
        float f13 = f3;
        canvas.scale(f9, f13);
        canvas.rotate(90.0f);
        canvas.drawPath(this.e, this.f10343a);
        if (z2) {
            canvas.scale(1.0f / f13, 1.0f);
            canvas.drawRect(0.0f, f6, this.c.height() - f7, -this.d, this.b);
        }
        canvas.restoreToCount(save5);
        canvas.restoreToCount(i2);
    }

    public void draw(@NonNull Canvas canvas) {
        if (this.i) {
            a(getBounds());
            this.i = false;
        }
        c(canvas);
        super.draw(canvas);
    }

    public float getCornerRadius() {
        return this.d;
    }

    public float getMaxShadowSize() {
        return this.f;
    }

    public float getMinHeight() {
        float f = this.f;
        return (Math.max(f, this.d + ((f * 1.5f) / 2.0f)) * 2.0f) + (this.f * 1.5f * 2.0f);
    }

    public float getMinWidth() {
        float f = this.f;
        return (Math.max(f, this.d + (f / 2.0f)) * 2.0f) + (this.f * 2.0f);
    }

    public int getOpacity() {
        return -3;
    }

    public boolean getPadding(@NonNull Rect rect) {
        int ceil = (int) Math.ceil(calculateVerticalPadding(this.f, this.d, this.m));
        int ceil2 = (int) Math.ceil(calculateHorizontalPadding(this.f, this.d, this.m));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    public float getShadowSize() {
        return this.h;
    }

    public void onBoundsChange(Rect rect) {
        this.i = true;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.m = z;
        invalidateSelf();
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        this.f10343a.setAlpha(i);
        this.b.setAlpha(i);
    }

    public void setCornerRadius(float f) {
        float round = Math.round(f);
        if (this.d == round) {
            return;
        }
        this.d = round;
        this.i = true;
        invalidateSelf();
    }

    public void setMaxShadowSize(float f) {
        setShadowSize(this.h, f);
    }

    public final void setRotation(float f) {
        if (this.n != f) {
            this.n = f;
            invalidateSelf();
        }
    }

    public void setShadowSize(float f, float f2) {
        if (f >= 0.0f && f2 >= 0.0f) {
            float d = d(f);
            float d2 = d(f2);
            if (d > d2) {
                if (!this.o) {
                    this.o = true;
                }
                d = d2;
            }
            if (this.h == d && this.f == d2) {
                return;
            }
            this.h = d;
            this.f = d2;
            this.g = Math.round(d * 1.5f);
            this.i = true;
            invalidateSelf();
            return;
        }
        throw new IllegalArgumentException("invalid shadow size");
    }

    public void setShadowSize(float f) {
        setShadowSize(f, this.f);
    }
}
