package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifFrameLoader;
import com.bumptech.glide.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class GifDrawable extends Drawable implements GifFrameLoader.FrameCallback, Animatable, Animatable2Compat {
    public static final int LOOP_FOREVER = -1;
    public static final int LOOP_INTRINSIC = 0;
    public final a h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public int n;
    public boolean o;
    public Paint p;
    public Rect q;
    public List<Animatable2Compat.AnimationCallback> r;

    /* loaded from: classes2.dex */
    public static final class a extends Drawable.ConstantState {
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        public final GifFrameLoader f2492a;

        public a(GifFrameLoader gifFrameLoader) {
            this.f2492a = gifFrameLoader;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new GifDrawable(this);
        }
    }

    @Deprecated
    public GifDrawable(Context context, GifDecoder gifDecoder, BitmapPool bitmapPool, Transformation<Bitmap> transformation, int i, int i2, Bitmap bitmap) {
        this(context, gifDecoder, transformation, i, i2, bitmap);
    }

    public final Drawable.Callback a() {
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        return callback;
    }

    public final Rect b() {
        if (this.q == null) {
            this.q = new Rect();
        }
        return this.q;
    }

    public final Paint c() {
        if (this.p == null) {
            this.p = new Paint(2);
        }
        return this.p;
    }

    @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public void clearAnimationCallbacks() {
        List<Animatable2Compat.AnimationCallback> list = this.r;
        if (list != null) {
            list.clear();
        }
    }

    public final void d() {
        List<Animatable2Compat.AnimationCallback> list = this.r;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this.r.get(i).onAnimationEnd(this);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (this.k) {
            return;
        }
        if (this.o) {
            Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), b());
            this.o = false;
        }
        canvas.drawBitmap(this.h.f2492a.c(), (Rect) null, b(), c());
    }

    public final void e() {
        this.m = 0;
    }

    public final void f() {
        Preconditions.checkArgument(!this.k, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.h.f2492a.f() == 1) {
            invalidateSelf();
        } else if (this.i) {
        } else {
            this.i = true;
            this.h.f2492a.u(this);
            invalidateSelf();
        }
    }

    public final void g() {
        this.i = false;
        this.h.f2492a.v(this);
    }

    public ByteBuffer getBuffer() {
        return this.h.f2492a.b();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.h;
    }

    public Bitmap getFirstFrame() {
        return this.h.f2492a.e();
    }

    public int getFrameCount() {
        return this.h.f2492a.f();
    }

    public int getFrameIndex() {
        return this.h.f2492a.d();
    }

    public Transformation<Bitmap> getFrameTransformation() {
        return this.h.f2492a.h();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.h.f2492a.i();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.h.f2492a.m();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int getSize() {
        return this.h.f2492a.l();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.i;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.o = true;
    }

    @Override // com.bumptech.glide.load.resource.gif.GifFrameLoader.FrameCallback
    public void onFrameReady() {
        if (a() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        if (getFrameIndex() == getFrameCount() - 1) {
            this.m++;
        }
        int i = this.n;
        if (i == -1 || this.m < i) {
            return;
        }
        d();
        stop();
    }

    public void recycle() {
        this.k = true;
        this.h.f2492a.a();
    }

    @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (animationCallback == null) {
            return;
        }
        if (this.r == null) {
            this.r = new ArrayList();
        }
        this.r.add(animationCallback);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        c().setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        c().setColorFilter(colorFilter);
    }

    public void setFrameTransformation(Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.h.f2492a.q(transformation, bitmap);
    }

    public void setLoopCount(int i) {
        if (i <= 0 && i != -1 && i != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        }
        if (i == 0) {
            int j = this.h.f2492a.j();
            this.n = j != 0 ? j : -1;
            return;
        }
        this.n = i;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Preconditions.checkArgument(!this.k, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.l = z;
        if (!z) {
            g();
        } else if (this.j) {
            f();
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.j = true;
        e();
        if (this.l) {
            f();
        }
    }

    public void startFromFirstFrame() {
        Preconditions.checkArgument(!this.i, "You cannot restart a currently running animation.");
        this.h.f2492a.r();
        start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.j = false;
        g();
    }

    @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        List<Animatable2Compat.AnimationCallback> list = this.r;
        if (list == null || animationCallback == null) {
            return false;
        }
        return list.remove(animationCallback);
    }

    public GifDrawable(Context context, GifDecoder gifDecoder, Transformation<Bitmap> transformation, int i, int i2, Bitmap bitmap) {
        this(new a(new GifFrameLoader(Glide.get(context), gifDecoder, i, i2, transformation, bitmap)));
    }

    public GifDrawable(a aVar) {
        this.l = true;
        this.n = -1;
        this.h = (a) Preconditions.checkNotNull(aVar);
    }
}
