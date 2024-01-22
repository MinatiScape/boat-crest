package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class DrawerArrowDrawable extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    public static final float m = (float) Math.toRadians(45.0d);

    /* renamed from: a  reason: collision with root package name */
    public final Paint f413a;
    public float b;
    public float c;
    public float d;
    public float e;
    public boolean f;
    public final Path g;
    public final int h;
    public boolean i;
    public float j;
    public float k;
    public int l;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface ArrowDirection {
    }

    public DrawerArrowDrawable(Context context) {
        Paint paint = new Paint();
        this.f413a = paint;
        this.g = new Path();
        this.i = false;
        this.l = 2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(obtainStyledAttributes.getColor(R.styleable.DrawerArrowToggle_color, 0));
        setBarThickness(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(obtainStyledAttributes.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
        setGapSize(Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.c = Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.b = Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.d = obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public static float a(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f;
        Rect bounds = getBounds();
        int i = this.l;
        boolean z = false;
        if (i != 0 && (i == 1 || (i == 3 ? DrawableCompat.getLayoutDirection(this) == 0 : DrawableCompat.getLayoutDirection(this) == 1))) {
            z = true;
        }
        float f2 = this.b;
        float a2 = a(this.c, (float) Math.sqrt(f2 * f2 * 2.0f), this.j);
        float a3 = a(this.c, this.d, this.j);
        float round = Math.round(a(0.0f, this.k, this.j));
        float a4 = a(0.0f, m, this.j);
        float a5 = a(z ? 0.0f : -180.0f, z ? 180.0f : 0.0f, this.j);
        double d = a2;
        double d2 = a4;
        boolean z2 = z;
        float round2 = (float) Math.round(Math.cos(d2) * d);
        float round3 = (float) Math.round(d * Math.sin(d2));
        this.g.rewind();
        float a6 = a(this.e + this.f413a.getStrokeWidth(), -this.k, this.j);
        float f3 = (-a3) / 2.0f;
        this.g.moveTo(f3 + round, 0.0f);
        this.g.rLineTo(a3 - (round * 2.0f), 0.0f);
        this.g.moveTo(f3, a6);
        this.g.rLineTo(round2, round3);
        this.g.moveTo(f3, -a6);
        this.g.rLineTo(round2, -round3);
        this.g.close();
        canvas.save();
        float strokeWidth = this.f413a.getStrokeWidth();
        float height = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), ((((int) (height - (2.0f * f))) / 4) * 2) + (strokeWidth * 1.5f) + this.e);
        if (this.f) {
            canvas.rotate(a5 * (this.i ^ z2 ? -1 : 1));
        } else if (z2) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.g, this.f413a);
        canvas.restore();
    }

    public float getArrowHeadLength() {
        return this.b;
    }

    public float getArrowShaftLength() {
        return this.d;
    }

    public float getBarLength() {
        return this.c;
    }

    public float getBarThickness() {
        return this.f413a.getStrokeWidth();
    }

    @ColorInt
    public int getColor() {
        return this.f413a.getColor();
    }

    public int getDirection() {
        return this.l;
    }

    public float getGapSize() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final Paint getPaint() {
        return this.f413a;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.j;
    }

    public boolean isSpinEnabled() {
        return this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.f413a.getAlpha()) {
            this.f413a.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setArrowHeadLength(float f) {
        if (this.b != f) {
            this.b = f;
            invalidateSelf();
        }
    }

    public void setArrowShaftLength(float f) {
        if (this.d != f) {
            this.d = f;
            invalidateSelf();
        }
    }

    public void setBarLength(float f) {
        if (this.c != f) {
            this.c = f;
            invalidateSelf();
        }
    }

    public void setBarThickness(float f) {
        if (this.f413a.getStrokeWidth() != f) {
            this.f413a.setStrokeWidth(f);
            this.k = (float) ((f / 2.0f) * Math.cos(m));
            invalidateSelf();
        }
    }

    public void setColor(@ColorInt int i) {
        if (i != this.f413a.getColor()) {
            this.f413a.setColor(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f413a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDirection(int i) {
        if (i != this.l) {
            this.l = i;
            invalidateSelf();
        }
    }

    public void setGapSize(float f) {
        if (f != this.e) {
            this.e = f;
            invalidateSelf();
        }
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.j != f) {
            this.j = f;
            invalidateSelf();
        }
    }

    public void setSpinEnabled(boolean z) {
        if (this.f != z) {
            this.f = z;
            invalidateSelf();
        }
    }

    public void setVerticalMirror(boolean z) {
        if (this.i != z) {
            this.i = z;
            invalidateSelf();
        }
    }
}
