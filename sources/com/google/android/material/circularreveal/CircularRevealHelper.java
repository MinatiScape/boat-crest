package com.google.android.material.circularreveal;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.math.MathUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class CircularRevealHelper {
    public static final int BITMAP_SHADER = 0;
    public static final int CLIP_PATH = 1;
    public static final int REVEAL_ANIMATOR = 2;
    public static final int STRATEGY;

    /* renamed from: a  reason: collision with root package name */
    public final Delegate f10248a;
    @NonNull
    public final View b;
    @NonNull
    public final Path c;
    @NonNull
    public final Paint d;
    @NonNull
    public final Paint e;
    @Nullable
    public CircularRevealWidget.RevealInfo f;
    @Nullable
    public Drawable g;
    public boolean h;
    public boolean i;

    /* loaded from: classes10.dex */
    public interface Delegate {
        void actualDraw(Canvas canvas);

        boolean actualIsOpaque();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface Strategy {
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            STRATEGY = 2;
        } else if (i >= 18) {
            STRATEGY = 1;
        } else {
            STRATEGY = 0;
        }
    }

    public CircularRevealHelper(Delegate delegate) {
        this.f10248a = delegate;
        View view = (View) delegate;
        this.b = view;
        view.setWillNotDraw(false);
        this.c = new Path();
        this.d = new Paint(7);
        Paint paint = new Paint(1);
        this.e = paint;
        paint.setColor(0);
    }

    public final void a(@NonNull Canvas canvas) {
        if (e()) {
            Rect bounds = this.g.getBounds();
            float width = this.f.centerX - (bounds.width() / 2.0f);
            float height = this.f.centerY - (bounds.height() / 2.0f);
            canvas.translate(width, height);
            this.g.draw(canvas);
            canvas.translate(-width, -height);
        }
    }

    public final float b(@NonNull CircularRevealWidget.RevealInfo revealInfo) {
        return MathUtils.distanceToFurthestCorner(revealInfo.centerX, revealInfo.centerY, 0.0f, 0.0f, this.b.getWidth(), this.b.getHeight());
    }

    public void buildCircularRevealCache() {
        if (STRATEGY == 0) {
            this.h = true;
            this.i = false;
            this.b.buildDrawingCache();
            Bitmap drawingCache = this.b.getDrawingCache();
            if (drawingCache == null && this.b.getWidth() != 0 && this.b.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.b.getWidth(), this.b.getHeight(), Bitmap.Config.ARGB_8888);
                this.b.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.d;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.h = false;
            this.i = true;
        }
    }

    public final void c() {
        if (STRATEGY == 1) {
            this.c.rewind();
            CircularRevealWidget.RevealInfo revealInfo = this.f;
            if (revealInfo != null) {
                this.c.addCircle(revealInfo.centerX, revealInfo.centerY, revealInfo.radius, Path.Direction.CW);
            }
        }
        this.b.invalidate();
    }

    public final boolean d() {
        CircularRevealWidget.RevealInfo revealInfo = this.f;
        boolean z = revealInfo == null || revealInfo.isInvalid();
        return STRATEGY == 0 ? !z && this.i : !z;
    }

    public void destroyCircularRevealCache() {
        if (STRATEGY == 0) {
            this.i = false;
            this.b.destroyDrawingCache();
            this.d.setShader(null);
            this.b.invalidate();
        }
    }

    public void draw(@NonNull Canvas canvas) {
        if (d()) {
            int i = STRATEGY;
            if (i == 0) {
                CircularRevealWidget.RevealInfo revealInfo = this.f;
                canvas.drawCircle(revealInfo.centerX, revealInfo.centerY, revealInfo.radius, this.d);
                if (f()) {
                    CircularRevealWidget.RevealInfo revealInfo2 = this.f;
                    canvas.drawCircle(revealInfo2.centerX, revealInfo2.centerY, revealInfo2.radius, this.e);
                }
            } else if (i == 1) {
                int save = canvas.save();
                canvas.clipPath(this.c);
                this.f10248a.actualDraw(canvas);
                if (f()) {
                    canvas.drawRect(0.0f, 0.0f, this.b.getWidth(), this.b.getHeight(), this.e);
                }
                canvas.restoreToCount(save);
            } else if (i == 2) {
                this.f10248a.actualDraw(canvas);
                if (f()) {
                    canvas.drawRect(0.0f, 0.0f, this.b.getWidth(), this.b.getHeight(), this.e);
                }
            } else {
                throw new IllegalStateException("Unsupported strategy " + i);
            }
        } else {
            this.f10248a.actualDraw(canvas);
            if (f()) {
                canvas.drawRect(0.0f, 0.0f, this.b.getWidth(), this.b.getHeight(), this.e);
            }
        }
        a(canvas);
    }

    public final boolean e() {
        return (this.h || this.g == null || this.f == null) ? false : true;
    }

    public final boolean f() {
        return (this.h || Color.alpha(this.e.getColor()) == 0) ? false : true;
    }

    @Nullable
    public Drawable getCircularRevealOverlayDrawable() {
        return this.g;
    }

    @ColorInt
    public int getCircularRevealScrimColor() {
        return this.e.getColor();
    }

    @Nullable
    public CircularRevealWidget.RevealInfo getRevealInfo() {
        CircularRevealWidget.RevealInfo revealInfo = this.f;
        if (revealInfo == null) {
            return null;
        }
        CircularRevealWidget.RevealInfo revealInfo2 = new CircularRevealWidget.RevealInfo(revealInfo);
        if (revealInfo2.isInvalid()) {
            revealInfo2.radius = b(revealInfo2);
        }
        return revealInfo2;
    }

    public boolean isOpaque() {
        return this.f10248a.actualIsOpaque() && !d();
    }

    public void setCircularRevealOverlayDrawable(@Nullable Drawable drawable) {
        this.g = drawable;
        this.b.invalidate();
    }

    public void setCircularRevealScrimColor(@ColorInt int i) {
        this.e.setColor(i);
        this.b.invalidate();
    }

    public void setRevealInfo(@Nullable CircularRevealWidget.RevealInfo revealInfo) {
        if (revealInfo == null) {
            this.f = null;
        } else {
            CircularRevealWidget.RevealInfo revealInfo2 = this.f;
            if (revealInfo2 == null) {
                this.f = new CircularRevealWidget.RevealInfo(revealInfo);
            } else {
                revealInfo2.set(revealInfo);
            }
            if (MathUtils.geq(revealInfo.radius, b(revealInfo), 1.0E-4f)) {
                this.f.radius = Float.MAX_VALUE;
            }
        }
        c();
    }
}
