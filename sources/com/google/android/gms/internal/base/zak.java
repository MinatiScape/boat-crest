package com.google.android.gms.internal.base;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
/* loaded from: classes6.dex */
public final class zak extends Drawable implements Drawable.Callback {
    public int h;
    public long i;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;
    public c p;
    public Drawable q;
    public Drawable r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;

    public zak(@Nullable Drawable drawable, @Nullable Drawable drawable2) {
        this(null);
        drawable = drawable == null ? b.f8564a : drawable;
        this.q = drawable;
        drawable.setCallback(this);
        c cVar = this.p;
        cVar.b = drawable.getChangingConfigurations() | cVar.b;
        drawable2 = drawable2 == null ? b.f8564a : drawable2;
        this.r = drawable2;
        drawable2.setCallback(this);
        c cVar2 = this.p;
        cVar2.b = drawable2.getChangingConfigurations() | cVar2.b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
        if (r0 == 0) goto L22;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void draw(android.graphics.Canvas r7) {
        /*
            r6 = this;
            int r0 = r6.h
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == r3) goto L38
            if (r0 == r1) goto La
            goto L41
        La:
            long r0 = r6.i
            r4 = 0
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 < 0) goto L41
            long r0 = android.os.SystemClock.uptimeMillis()
            long r4 = r6.i
            long r0 = r0 - r4
            float r0 = (float) r0
            int r1 = r6.l
            float r1 = (float) r1
            float r0 = r0 / r1
            r1 = 1065353216(0x3f800000, float:1.0)
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 < 0) goto L25
            goto L26
        L25:
            r3 = r2
        L26:
            if (r3 == 0) goto L2a
            r6.h = r2
        L2a:
            float r0 = java.lang.Math.min(r0, r1)
            int r1 = r6.j
            float r1 = (float) r1
            float r1 = r1 * r0
            r0 = 0
            float r1 = r1 + r0
            int r0 = (int) r1
            r6.m = r0
            goto L41
        L38:
            long r3 = android.os.SystemClock.uptimeMillis()
            r6.i = r3
            r6.h = r1
            r3 = r2
        L41:
            int r0 = r6.m
            boolean r1 = r6.n
            android.graphics.drawable.Drawable r4 = r6.q
            android.graphics.drawable.Drawable r5 = r6.r
            if (r3 == 0) goto L60
            if (r1 == 0) goto L50
            if (r0 != 0) goto L55
            goto L51
        L50:
            r2 = r0
        L51:
            r4.draw(r7)
            r0 = r2
        L55:
            int r1 = r6.k
            if (r0 != r1) goto L5f
            r5.setAlpha(r1)
            r5.draw(r7)
        L5f:
            return
        L60:
            if (r1 == 0) goto L68
            int r2 = r6.k
            int r2 = r2 - r0
            r4.setAlpha(r2)
        L68:
            r4.draw(r7)
            if (r1 == 0) goto L72
            int r1 = r6.k
            r4.setAlpha(r1)
        L72:
            if (r0 <= 0) goto L7f
            r5.setAlpha(r0)
            r5.draw(r7)
            int r7 = r6.k
            r5.setAlpha(r7)
        L7f:
            r6.invalidateSelf()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.base.zak.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        c cVar = this.p;
        return changingConfigurations | cVar.f8565a | cVar.b;
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public final Drawable.ConstantState getConstantState() {
        if (zac()) {
            this.p.f8565a = getChangingConfigurations();
            return this.p;
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.q.getIntrinsicHeight(), this.r.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.q.getIntrinsicWidth(), this.r.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.u) {
            this.v = Drawable.resolveOpacity(this.q.getOpacity(), this.r.getOpacity());
            this.u = true;
        }
        return this.v;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    @CanIgnoreReturnValue
    public final Drawable mutate() {
        if (!this.o && super.mutate() == this) {
            if (zac()) {
                this.q.mutate();
                this.r.mutate();
                this.o = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.q.setBounds(rect);
        this.r.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.m == this.k) {
            this.m = i;
        }
        this.k = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.q.setColorFilter(colorFilter);
        this.r.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zaa() {
        return this.r;
    }

    public final void zab(int i) {
        this.j = this.k;
        this.m = 0;
        this.l = 250;
        this.h = 1;
        invalidateSelf();
    }

    public final boolean zac() {
        if (!this.s) {
            boolean z = false;
            if (this.q.getConstantState() != null && this.r.getConstantState() != null) {
                z = true;
            }
            this.t = z;
            this.s = true;
        }
        return this.t;
    }

    public zak(@Nullable c cVar) {
        this.h = 0;
        this.k = 255;
        this.m = 0;
        this.n = true;
        this.p = new c(cVar);
    }
}
