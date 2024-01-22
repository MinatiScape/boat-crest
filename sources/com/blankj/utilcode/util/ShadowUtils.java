package com.blankj.utilcode.util;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import android.view.View;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class ShadowUtils {

    /* loaded from: classes.dex */
    public static class Config {
        public static final int i = b.w(8.0f);

        /* renamed from: a  reason: collision with root package name */
        public float f2286a = -1.0f;
        public float b = -1.0f;
        public float c = -1.0f;
        public float d = -1.0f;
        public float e = -1.0f;
        public int f = 1140850688;
        public int g = 1140850688;
        public boolean h = false;

        public Drawable a(Drawable drawable) {
            if (drawable == null) {
                drawable = new ColorDrawable(0);
            }
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawable2 = drawable;
            stateListDrawable.addState(new int[]{16842919}, new ShadowDrawable(drawable2, d(), e(), b(), this.g, this.h));
            stateListDrawable.addState(StateSet.WILD_CARD, new ShadowDrawable(drawable2, d(), f(), c(), this.f, this.h));
            return stateListDrawable;
        }

        public final float b() {
            if (this.d == -1.0f) {
                this.d = e();
            }
            return this.d;
        }

        public final float c() {
            if (this.e == -1.0f) {
                this.e = f();
            }
            return this.e;
        }

        public final float d() {
            if (this.f2286a < 0.0f) {
                this.f2286a = 0.0f;
            }
            return this.f2286a;
        }

        public final float e() {
            if (this.b == -1.0f) {
                this.b = i;
            }
            return this.b;
        }

        public final float f() {
            if (this.c == -1.0f) {
                this.c = e();
            }
            return this.c;
        }

        public Config setCircle() {
            this.h = true;
            if (this.f2286a == -1.0f) {
                return this;
            }
            throw new IllegalArgumentException("Set circle needn't set radius.");
        }

        public Config setShadowColor(int i2) {
            return setShadowColor(i2, i2);
        }

        public Config setShadowMaxSize(int i2) {
            return setShadowMaxSize(i2, i2);
        }

        public Config setShadowRadius(float f) {
            this.f2286a = f;
            if (this.h) {
                throw new IllegalArgumentException("Set circle needn't set radius.");
            }
            return this;
        }

        public Config setShadowSize(int i2) {
            return setShadowSize(i2, i2);
        }

        public Config setShadowColor(int i2, int i3) {
            this.f = i2;
            this.g = i3;
            return this;
        }

        public Config setShadowMaxSize(int i2, int i3) {
            this.d = i2;
            this.e = i3;
            return this;
        }

        public Config setShadowSize(int i2, int i3) {
            this.b = i2;
            this.c = i3;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class ShadowDrawable extends a {
        public static final double A = Math.cos(Math.toRadians(45.0d));
        public float i;
        public float j;
        public float k;
        public float l;
        public Paint m;
        public Paint n;
        public RectF o;
        public float p;
        public Path q;
        public float r;
        public float s;
        public float t;
        public boolean u;
        public final int v;
        public final int w;
        public boolean x;
        public float y;
        public boolean z;

        public ShadowDrawable(Drawable drawable, float f, float f2, float f3, int i, boolean z) {
            super(drawable);
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 1.0f;
            this.l = 1.0f;
            this.u = true;
            this.x = false;
            this.v = i;
            this.w = i & 16777215;
            this.z = z;
            if (z) {
                this.i = 1.0f;
                this.j = 1.0f;
                this.k = 1.0f;
                this.l = 1.0f;
            }
            Paint paint = new Paint(5);
            this.m = paint;
            paint.setStyle(Paint.Style.FILL);
            this.p = Math.round(f);
            this.o = new RectF();
            Paint paint2 = new Paint(this.m);
            this.n = paint2;
            paint2.setAntiAlias(false);
            f(f2, f3);
        }

        public static float c(float f, float f2, boolean z) {
            return z ? (float) (f + ((1.0d - A) * f2)) : f;
        }

        public static int g(float f) {
            int round = Math.round(f);
            return round % 2 == 1 ? round - 1 : round;
        }

        public final void a(Rect rect) {
            if (this.z) {
                this.p = rect.width() / 2;
            }
            float f = this.r;
            float f2 = this.i * f;
            this.o.set(rect.left + f, rect.top + f2, rect.right - f, rect.bottom - f2);
            Drawable wrappedDrawable = getWrappedDrawable();
            RectF rectF = this.o;
            wrappedDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            b();
        }

        public final void b() {
            if (this.z) {
                float width = (this.o.width() / 2.0f) - 1.0f;
                float f = -width;
                RectF rectF = new RectF(f, f, width, width);
                RectF rectF2 = new RectF(rectF);
                float f2 = this.s;
                rectF2.inset(-f2, -f2);
                Path path = this.q;
                if (path == null) {
                    this.q = new Path();
                } else {
                    path.reset();
                }
                this.q.setFillType(Path.FillType.EVEN_ODD);
                this.q.moveTo(f, 0.0f);
                this.q.rLineTo(-this.s, 0.0f);
                this.q.arcTo(rectF2, 180.0f, 180.0f, false);
                this.q.arcTo(rectF2, 0.0f, 180.0f, false);
                this.q.arcTo(rectF, 180.0f, 180.0f, false);
                this.q.arcTo(rectF, 0.0f, 180.0f, false);
                this.q.close();
                float f3 = -rectF2.top;
                if (f3 > 0.0f) {
                    this.m.setShader(new RadialGradient(0.0f, 0.0f, f3, new int[]{0, this.v, this.w}, new float[]{0.0f, width / f3, 1.0f}, Shader.TileMode.CLAMP));
                    return;
                }
                return;
            }
            float f4 = this.p;
            RectF rectF3 = new RectF(-f4, -f4, f4, f4);
            RectF rectF4 = new RectF(rectF3);
            float f5 = this.s;
            rectF4.inset(-f5, -f5);
            Path path2 = this.q;
            if (path2 == null) {
                this.q = new Path();
            } else {
                path2.reset();
            }
            this.q.setFillType(Path.FillType.EVEN_ODD);
            this.q.moveTo(-this.p, 0.0f);
            this.q.rLineTo(-this.s, 0.0f);
            this.q.arcTo(rectF4, 180.0f, 90.0f, false);
            this.q.arcTo(rectF3, 270.0f, -90.0f, false);
            this.q.close();
            float f6 = -rectF4.top;
            if (f6 > 0.0f) {
                this.m.setShader(new RadialGradient(0.0f, 0.0f, f6, new int[]{0, this.v, this.w}, new float[]{0.0f, this.p / f6, 1.0f}, Shader.TileMode.CLAMP));
            }
            this.n.setShader(new LinearGradient(0.0f, rectF3.top, 0.0f, rectF4.top, this.v, this.w, Shader.TileMode.CLAMP));
            this.n.setAntiAlias(false);
        }

        public final float d(float f, float f2, boolean z) {
            if (z) {
                return (float) ((f * this.i) + ((1.0d - A) * f2));
            }
            return f * this.i;
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.u) {
                a(getBounds());
                this.u = false;
            }
            e(canvas);
            super.draw(canvas);
        }

        public final void e(Canvas canvas) {
            int i;
            float f;
            int i2;
            float f2;
            float f3;
            float f4;
            if (this.z) {
                int save = canvas.save();
                canvas.translate(this.o.centerX(), this.o.centerY());
                canvas.drawPath(this.q, this.m);
                canvas.restoreToCount(save);
                return;
            }
            int save2 = canvas.save();
            canvas.rotate(this.y, this.o.centerX(), this.o.centerY());
            float f5 = this.p;
            float f6 = (-f5) - this.s;
            float f7 = f5 * 2.0f;
            boolean z = this.o.width() - f7 > 0.0f;
            boolean z2 = this.o.height() - f7 > 0.0f;
            float f8 = this.t;
            float f9 = f8 - (this.j * f8);
            float f10 = f8 - (this.k * f8);
            float f11 = f8 - (this.l * f8);
            int i3 = (f5 > 0.0f ? 1 : (f5 == 0.0f ? 0 : -1));
            float f12 = i3 == 0 ? 1.0f : f5 / (f10 + f5);
            float f13 = i3 == 0 ? 1.0f : f5 / (f9 + f5);
            float f14 = i3 == 0 ? 1.0f : f5 / (f11 + f5);
            int save3 = canvas.save();
            RectF rectF = this.o;
            canvas.translate(rectF.left + f5, rectF.top + f5);
            canvas.scale(f12, f13);
            canvas.drawPath(this.q, this.m);
            if (z) {
                canvas.scale(1.0f / f12, 1.0f);
                i = save3;
                f = f14;
                i2 = save2;
                f2 = f13;
                canvas.drawRect(0.0f, f6, this.o.width() - f7, -this.p, this.n);
            } else {
                i = save3;
                f = f14;
                i2 = save2;
                f2 = f13;
            }
            canvas.restoreToCount(i);
            int save4 = canvas.save();
            RectF rectF2 = this.o;
            canvas.translate(rectF2.right - f5, rectF2.bottom - f5);
            float f15 = f;
            canvas.scale(f12, f15);
            canvas.rotate(180.0f);
            canvas.drawPath(this.q, this.m);
            if (z) {
                canvas.scale(1.0f / f12, 1.0f);
                f3 = f2;
                f4 = f15;
                canvas.drawRect(0.0f, f6, this.o.width() - f7, -this.p, this.n);
            } else {
                f3 = f2;
                f4 = f15;
            }
            canvas.restoreToCount(save4);
            int save5 = canvas.save();
            RectF rectF3 = this.o;
            canvas.translate(rectF3.left + f5, rectF3.bottom - f5);
            canvas.scale(f12, f4);
            canvas.rotate(270.0f);
            canvas.drawPath(this.q, this.m);
            if (z2) {
                canvas.scale(1.0f / f4, 1.0f);
                canvas.drawRect(0.0f, f6, this.o.height() - f7, -this.p, this.n);
            }
            canvas.restoreToCount(save5);
            int save6 = canvas.save();
            RectF rectF4 = this.o;
            canvas.translate(rectF4.right - f5, rectF4.top + f5);
            float f16 = f3;
            canvas.scale(f12, f16);
            canvas.rotate(90.0f);
            canvas.drawPath(this.q, this.m);
            if (z2) {
                canvas.scale(1.0f / f16, 1.0f);
                canvas.drawRect(0.0f, f6, this.o.height() - f7, -this.p, this.n);
            }
            canvas.restoreToCount(save6);
            canvas.restoreToCount(i2);
        }

        public void f(float f, float f2) {
            if (f >= 0.0f && f2 >= 0.0f) {
                float g = g(f);
                float g2 = g(f2);
                if (g > g2) {
                    g = g2;
                }
                if (this.t == g && this.r == g2) {
                    return;
                }
                this.t = g;
                this.r = g2;
                this.s = Math.round(g * this.i);
                this.u = true;
                invalidateSelf();
                return;
            }
            throw new IllegalArgumentException("invalid shadow size");
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ int getChangingConfigurations() {
            return super.getChangingConfigurations();
        }

        public float getCornerRadius() {
            return this.p;
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ Drawable getCurrent() {
            return super.getCurrent();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
            return super.getIntrinsicHeight();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
            return super.getIntrinsicWidth();
        }

        public float getMaxShadowSize() {
            return this.r;
        }

        public float getMinHeight() {
            float f = this.r;
            return (Math.max(f, this.p + ((this.i * f) / 2.0f)) * 2.0f) + (this.r * this.i * 2.0f);
        }

        public float getMinWidth() {
            float f = this.r;
            return (Math.max(f, this.p + (f / 2.0f)) * 2.0f) + (this.r * 2.0f);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ int getMinimumHeight() {
            return super.getMinimumHeight();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ int getMinimumWidth() {
            return super.getMinimumWidth();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            int ceil = (int) Math.ceil(d(this.r, this.p, this.x));
            int ceil2 = (int) Math.ceil(c(this.r, this.p, this.x));
            rect.set(ceil2, ceil, ceil2, ceil);
            return true;
        }

        public float getShadowSize() {
            return this.t;
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ int[] getState() {
            return super.getState();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ Region getTransparentRegion() {
            return super.getTransparentRegion();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a
        public /* bridge */ /* synthetic */ Drawable getWrappedDrawable() {
            return super.getWrappedDrawable();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable.Callback
        public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
            super.invalidateDrawable(drawable);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
            return super.isAutoMirrored();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ boolean isStateful() {
            return super.isStateful();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void jumpToCurrentState() {
            super.jumpToCurrentState();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public void onBoundsChange(Rect rect) {
            this.u = true;
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable.Callback
        public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            super.scheduleDrawable(drawable, runnable, j);
        }

        public void setAddPaddingForCorners(boolean z) {
            this.x = z;
            invalidateSelf();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            super.setAlpha(i);
            this.m.setAlpha(i);
            this.n.setAlpha(i);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
            super.setAutoMirrored(z);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
            super.setChangingConfigurations(i);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
            super.setColorFilter(colorFilter);
        }

        public void setCornerRadius(float f) {
            float round = Math.round(f);
            if (this.p == round) {
                return;
            }
            this.p = round;
            this.u = true;
            invalidateSelf();
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setDither(boolean z) {
            super.setDither(z);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
            super.setFilterBitmap(z);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
            super.setHotspot(f, f2);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
            super.setHotspotBounds(i, i2, i3, i4);
        }

        public void setMaxShadowSize(float f) {
            f(this.t, f);
        }

        public void setShadowSize(float f) {
            f(f, this.r);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
            return super.setState(iArr);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setTint(int i) {
            super.setTint(i);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
            super.setTintList(colorStateList);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
            super.setTintMode(mode);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2) {
            return super.setVisible(z, z2);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a
        public /* bridge */ /* synthetic */ void setWrappedDrawable(Drawable drawable) {
            super.setWrappedDrawable(drawable);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable.Callback
        public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            super.unscheduleDrawable(drawable, runnable);
        }
    }

    /* loaded from: classes.dex */
    public static class a extends Drawable implements Drawable.Callback {
        public Drawable h;

        public a(Drawable drawable) {
            setWrappedDrawable(drawable);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            this.h.draw(canvas);
        }

        @Override // android.graphics.drawable.Drawable
        public int getChangingConfigurations() {
            return this.h.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable getCurrent() {
            return this.h.getCurrent();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.h.getIntrinsicHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.h.getIntrinsicWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return this.h.getMinimumHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return this.h.getMinimumWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.h.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            return this.h.getPadding(rect);
        }

        @Override // android.graphics.drawable.Drawable
        public int[] getState() {
            return this.h.getState();
        }

        @Override // android.graphics.drawable.Drawable
        public Region getTransparentRegion() {
            return this.h.getTransparentRegion();
        }

        public Drawable getWrappedDrawable() {
            return this.h;
        }

        public void invalidateDrawable(Drawable drawable) {
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public boolean isAutoMirrored() {
            return DrawableCompat.isAutoMirrored(this.h);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean isStateful() {
            return this.h.isStateful();
        }

        @Override // android.graphics.drawable.Drawable
        public void jumpToCurrentState() {
            DrawableCompat.jumpToCurrentState(this.h);
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect rect) {
            this.h.setBounds(rect);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean onLevelChange(int i) {
            return this.h.setLevel(i);
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.h.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAutoMirrored(boolean z) {
            DrawableCompat.setAutoMirrored(this.h, z);
        }

        @Override // android.graphics.drawable.Drawable
        public void setChangingConfigurations(int i) {
            this.h.setChangingConfigurations(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.h.setColorFilter(colorFilter);
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean z) {
            this.h.setDither(z);
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean z) {
            this.h.setFilterBitmap(z);
        }

        @Override // android.graphics.drawable.Drawable
        public void setHotspot(float f, float f2) {
            DrawableCompat.setHotspot(this.h, f, f2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            DrawableCompat.setHotspotBounds(this.h, i, i2, i3, i4);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            return this.h.setState(iArr);
        }

        @Override // android.graphics.drawable.Drawable
        public void setTint(int i) {
            DrawableCompat.setTint(this.h, i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setTintList(ColorStateList colorStateList) {
            DrawableCompat.setTintList(this.h, colorStateList);
        }

        @Override // android.graphics.drawable.Drawable
        public void setTintMode(PorterDuff.Mode mode) {
            DrawableCompat.setTintMode(this.h, mode);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            return super.setVisible(z, z2) || this.h.setVisible(z, z2);
        }

        public void setWrappedDrawable(Drawable drawable) {
            Drawable drawable2 = this.h;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.h = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
            }
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            unscheduleSelf(runnable);
        }
    }

    public static void apply(View... viewArr) {
        if (viewArr == null) {
            return;
        }
        for (View view : viewArr) {
            apply(view, new Config());
        }
    }

    public static void apply(View view, Config config) {
        if (view == null || config == null) {
            return;
        }
        Drawable background = view.getBackground();
        Object tag = view.getTag(-16);
        if (tag instanceof Drawable) {
            ViewCompat.setBackground(view, (Drawable) tag);
            return;
        }
        Drawable a2 = config.a(background);
        ViewCompat.setBackground(view, a2);
        view.setTag(-16, a2);
    }
}
