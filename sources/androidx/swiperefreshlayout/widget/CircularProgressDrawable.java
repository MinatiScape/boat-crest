package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class CircularProgressDrawable extends Drawable implements Animatable {
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;
    public static final Interpolator n = new LinearInterpolator();
    public static final Interpolator o = new FastOutSlowInInterpolator();
    public static final int[] p = {ViewCompat.MEASURED_STATE_MASK};
    public final c h;
    public float i;
    public Resources j;
    public Animator k;
    public float l;
    public boolean m;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface ProgressDrawableSize {
    }

    /* loaded from: classes.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ c h;

        public a(c cVar) {
            this.h = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            CircularProgressDrawable.this.g(floatValue, this.h);
            CircularProgressDrawable.this.b(floatValue, this.h, false);
            CircularProgressDrawable.this.invalidateSelf();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Animator.AnimatorListener {
        public final /* synthetic */ c h;

        public b(c cVar) {
            this.h = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            CircularProgressDrawable.this.b(1.0f, this.h, true);
            this.h.M();
            this.h.v();
            CircularProgressDrawable circularProgressDrawable = CircularProgressDrawable.this;
            if (circularProgressDrawable.m) {
                circularProgressDrawable.m = false;
                animator.cancel();
                animator.setDuration(1332L);
                animator.start();
                this.h.I(false);
                return;
            }
            circularProgressDrawable.l += 1.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            CircularProgressDrawable.this.l = 0.0f;
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final RectF f1689a = new RectF();
        public final Paint b;
        public final Paint c;
        public final Paint d;
        public float e;
        public float f;
        public float g;
        public float h;
        public int[] i;
        public int j;
        public float k;
        public float l;
        public float m;
        public boolean n;
        public Path o;
        public float p;
        public float q;
        public int r;
        public int s;
        public int t;
        public int u;

        public c() {
            Paint paint = new Paint();
            this.b = paint;
            Paint paint2 = new Paint();
            this.c = paint2;
            Paint paint3 = new Paint();
            this.d = paint3;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 5.0f;
            this.p = 1.0f;
            this.t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        public void A(int i) {
            this.d.setColor(i);
        }

        public void B(float f) {
            this.q = f;
        }

        public void C(int i) {
            this.u = i;
        }

        public void D(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        public void E(int i) {
            this.j = i;
            this.u = this.i[i];
        }

        public void F(@NonNull int[] iArr) {
            this.i = iArr;
            E(0);
        }

        public void G(float f) {
            this.f = f;
        }

        public void H(float f) {
            this.g = f;
        }

        public void I(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        public void J(float f) {
            this.e = f;
        }

        public void K(Paint.Cap cap) {
            this.b.setStrokeCap(cap);
        }

        public void L(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
        }

        public void M() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f1689a;
            float f = this.q;
            float f2 = (this.h / 2.0f) + f;
            if (f <= 0.0f) {
                f2 = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(rect.centerX() - f2, rect.centerY() - f2, rect.centerX() + f2, rect.centerY() + f2);
            float f3 = this.e;
            float f4 = this.g;
            float f5 = (f3 + f4) * 360.0f;
            float f6 = ((this.f + f4) * 360.0f) - f5;
            this.b.setColor(this.u);
            this.b.setAlpha(this.t);
            float f7 = this.h / 2.0f;
            rectF.inset(f7, f7);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.d);
            float f8 = -f7;
            rectF.inset(f8, f8);
            canvas.drawArc(rectF, f5, f6, false, this.b);
            b(canvas, f5, f6, rectF);
        }

        public void b(Canvas canvas, float f, float f2, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    Path path2 = new Path();
                    this.o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path3 = this.o;
                float f3 = this.p;
                path3.lineTo((this.r * f3) / 2.0f, this.s * f3);
                this.o.offset(((Math.min(rectF.width(), rectF.height()) / 2.0f) + rectF.centerX()) - ((this.r * this.p) / 2.0f), rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.c.setColor(this.u);
                this.c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.c);
                canvas.restore();
            }
        }

        public int c() {
            return this.t;
        }

        public float d() {
            return this.s;
        }

        public float e() {
            return this.p;
        }

        public float f() {
            return this.r;
        }

        public int g() {
            return this.d.getColor();
        }

        public float h() {
            return this.q;
        }

        public int[] i() {
            return this.i;
        }

        public float j() {
            return this.f;
        }

        public int k() {
            return this.i[l()];
        }

        public int l() {
            return (this.j + 1) % this.i.length;
        }

        public float m() {
            return this.g;
        }

        public boolean n() {
            return this.n;
        }

        public float o() {
            return this.e;
        }

        public int p() {
            return this.i[this.j];
        }

        public float q() {
            return this.l;
        }

        public float r() {
            return this.m;
        }

        public float s() {
            return this.k;
        }

        public Paint.Cap t() {
            return this.b.getStrokeCap();
        }

        public float u() {
            return this.h;
        }

        public void v() {
            E(l());
        }

        public void w() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            J(0.0f);
            G(0.0f);
            H(0.0f);
        }

        public void x(int i) {
            this.t = i;
        }

        public void y(float f, float f2) {
            this.r = (int) f;
            this.s = (int) f2;
        }

        public void z(float f) {
            if (f != this.p) {
                this.p = f;
            }
        }
    }

    public CircularProgressDrawable(@NonNull Context context) {
        this.j = ((Context) Preconditions.checkNotNull(context)).getResources();
        c cVar = new c();
        this.h = cVar;
        cVar.F(p);
        setStrokeWidth(2.5f);
        f();
    }

    public final void a(float f, c cVar) {
        g(f, cVar);
        cVar.J(cVar.s() + (((cVar.q() - 0.01f) - cVar.s()) * f));
        cVar.G(cVar.q());
        cVar.H(cVar.r() + ((((float) (Math.floor(cVar.r() / 0.8f) + 1.0d)) - cVar.r()) * f));
    }

    public void b(float f, c cVar, boolean z) {
        float interpolation;
        float f2;
        if (this.m) {
            a(f, cVar);
        } else if (f != 1.0f || z) {
            float r = cVar.r();
            if (f < 0.5f) {
                interpolation = cVar.s();
                f2 = (o.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float s = cVar.s() + 0.79f;
                interpolation = s - (((1.0f - o.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                f2 = s;
            }
            cVar.J(interpolation);
            cVar.G(f2);
            cVar.H(r + (0.20999998f * f));
            d((f + this.l) * 216.0f);
        }
    }

    public final int c(float f, int i, int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        int i6 = i & 255;
        return ((i3 + ((int) ((((i2 >> 24) & 255) - i3) * f))) << 24) | ((i4 + ((int) ((((i2 >> 16) & 255) - i4) * f))) << 16) | ((i5 + ((int) ((((i2 >> 8) & 255) - i5) * f))) << 8) | (i6 + ((int) (f * ((i2 & 255) - i6))));
    }

    public final void d(float f) {
        this.i = f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.i, bounds.exactCenterX(), bounds.exactCenterY());
        this.h.a(canvas, bounds);
        canvas.restore();
    }

    public final void e(float f, float f2, float f3, float f4) {
        c cVar = this.h;
        float f5 = this.j.getDisplayMetrics().density;
        cVar.L(f2 * f5);
        cVar.B(f * f5);
        cVar.E(0);
        cVar.y(f3 * f5, f4 * f5);
    }

    public final void f() {
        c cVar = this.h;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new a(cVar));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(n);
        ofFloat.addListener(new b(cVar));
        this.k = ofFloat;
    }

    public void g(float f, c cVar) {
        if (f > 0.75f) {
            cVar.C(c((f - 0.75f) / 0.25f, cVar.p(), cVar.k()));
        } else {
            cVar.C(cVar.p());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.h.c();
    }

    public boolean getArrowEnabled() {
        return this.h.n();
    }

    public float getArrowHeight() {
        return this.h.d();
    }

    public float getArrowScale() {
        return this.h.e();
    }

    public float getArrowWidth() {
        return this.h.f();
    }

    public int getBackgroundColor() {
        return this.h.g();
    }

    public float getCenterRadius() {
        return this.h.h();
    }

    @NonNull
    public int[] getColorSchemeColors() {
        return this.h.i();
    }

    public float getEndTrim() {
        return this.h.j();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getProgressRotation() {
        return this.h.m();
    }

    public float getStartTrim() {
        return this.h.o();
    }

    @NonNull
    public Paint.Cap getStrokeCap() {
        return this.h.t();
    }

    public float getStrokeWidth() {
        return this.h.u();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.k.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.h.x(i);
        invalidateSelf();
    }

    public void setArrowDimensions(float f, float f2) {
        this.h.y(f, f2);
        invalidateSelf();
    }

    public void setArrowEnabled(boolean z) {
        this.h.I(z);
        invalidateSelf();
    }

    public void setArrowScale(float f) {
        this.h.z(f);
        invalidateSelf();
    }

    public void setBackgroundColor(int i) {
        this.h.A(i);
        invalidateSelf();
    }

    public void setCenterRadius(float f) {
        this.h.B(f);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.h.D(colorFilter);
        invalidateSelf();
    }

    public void setColorSchemeColors(@NonNull int... iArr) {
        this.h.F(iArr);
        this.h.E(0);
        invalidateSelf();
    }

    public void setProgressRotation(float f) {
        this.h.H(f);
        invalidateSelf();
    }

    public void setStartEndTrim(float f, float f2) {
        this.h.J(f);
        this.h.G(f2);
        invalidateSelf();
    }

    public void setStrokeCap(@NonNull Paint.Cap cap) {
        this.h.K(cap);
        invalidateSelf();
    }

    public void setStrokeWidth(float f) {
        this.h.L(f);
        invalidateSelf();
    }

    public void setStyle(int i) {
        if (i == 0) {
            e(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            e(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.k.cancel();
        this.h.M();
        if (this.h.j() != this.h.o()) {
            this.m = true;
            this.k.setDuration(666L);
            this.k.start();
            return;
        }
        this.h.E(0);
        this.h.w();
        this.k.setDuration(1332L);
        this.k.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.k.cancel();
        d(0.0f);
        this.h.I(false);
        this.h.E(0);
        this.h.w();
        invalidateSelf();
    }
}
