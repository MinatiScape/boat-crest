package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public abstract class AutoScrollHelper implements View.OnTouchListener {
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    public static final float NO_MAX = Float.MAX_VALUE;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    public static final int y = ViewConfiguration.getTapTimeout();
    public final View j;
    public Runnable k;
    public int n;
    public int o;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public final a h = new a();
    public final Interpolator i = new AccelerateInterpolator();
    public float[] l = {0.0f, 0.0f};
    public float[] m = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] p = {0.0f, 0.0f};
    public float[] q = {0.0f, 0.0f};
    public float[] r = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f1201a;
        public int b;
        public float c;
        public float d;
        public float j;
        public int k;
        public long e = Long.MIN_VALUE;
        public long i = -1;
        public long f = 0;
        public int g = 0;
        public int h = 0;

        public void a() {
            if (this.f != 0) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float g = g(e(currentAnimationTimeMillis));
                this.f = currentAnimationTimeMillis;
                float f = ((float) (currentAnimationTimeMillis - this.f)) * g;
                this.g = (int) (this.c * f);
                this.h = (int) (f * this.d);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public int b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            float f = this.c;
            return (int) (f / Math.abs(f));
        }

        public final float e(long j) {
            long j2 = this.e;
            if (j < j2) {
                return 0.0f;
            }
            long j3 = this.i;
            if (j3 >= 0 && j >= j3) {
                float f = this.j;
                return (1.0f - f) + (f * AutoScrollHelper.c(((float) (j - j3)) / this.k, 0.0f, 1.0f));
            }
            return AutoScrollHelper.c(((float) (j - j2)) / this.f1201a, 0.0f, 1.0f) * 0.5f;
        }

        public int f() {
            float f = this.d;
            return (int) (f / Math.abs(f));
        }

        public final float g(float f) {
            return ((-4.0f) * f * f) + (f * 4.0f);
        }

        public boolean h() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        public void i() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = AutoScrollHelper.d((int) (currentAnimationTimeMillis - this.e), 0, this.b);
            this.j = e(currentAnimationTimeMillis);
            this.i = currentAnimationTimeMillis;
        }

        public void j(int i) {
            this.b = i;
        }

        public void k(int i) {
            this.f1201a = i;
        }

        public void l(float f, float f2) {
            this.c = f;
            this.d = f2;
        }

        public void m() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.e = currentAnimationTimeMillis;
            this.i = -1L;
            this.f = currentAnimationTimeMillis;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AutoScrollHelper autoScrollHelper = AutoScrollHelper.this;
            if (autoScrollHelper.v) {
                if (autoScrollHelper.t) {
                    autoScrollHelper.t = false;
                    autoScrollHelper.h.m();
                }
                a aVar = AutoScrollHelper.this.h;
                if (!aVar.h() && AutoScrollHelper.this.h()) {
                    AutoScrollHelper autoScrollHelper2 = AutoScrollHelper.this;
                    if (autoScrollHelper2.u) {
                        autoScrollHelper2.u = false;
                        autoScrollHelper2.a();
                    }
                    aVar.a();
                    AutoScrollHelper.this.scrollTargetBy(aVar.b(), aVar.c());
                    ViewCompat.postOnAnimation(AutoScrollHelper.this.j, this);
                    return;
                }
                AutoScrollHelper.this.v = false;
            }
        }
    }

    public AutoScrollHelper(@NonNull View view) {
        this.j = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f2 = (int) ((1575.0f * f) + 0.5f);
        setMaximumVelocity(f2, f2);
        float f3 = (int) ((f * 315.0f) + 0.5f);
        setMinimumVelocity(f3, f3);
        setEdgeType(1);
        setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        setRelativeEdges(0.2f, 0.2f);
        setRelativeVelocity(1.0f, 1.0f);
        setActivationDelay(y);
        setRampUpDuration(500);
        setRampDownDuration(500);
    }

    public static float c(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    public static int d(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public void a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.j.onTouchEvent(obtain);
        obtain.recycle();
    }

    public final float b(int i, float f, float f2, float f3) {
        float f4 = f(this.l[i], f2, this.m[i], f);
        int i2 = (f4 > 0.0f ? 1 : (f4 == 0.0f ? 0 : -1));
        if (i2 == 0) {
            return 0.0f;
        }
        float f5 = this.p[i];
        float f6 = this.q[i];
        float f7 = this.r[i];
        float f8 = f5 * f3;
        if (i2 > 0) {
            return c(f4 * f8, f6, f7);
        }
        return -c((-f4) * f8, f6, f7);
    }

    public abstract boolean canTargetScrollHorizontally(int i);

    public abstract boolean canTargetScrollVertically(int i);

    public final float e(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.n;
        if (i == 0 || i == 1) {
            if (f < f2) {
                if (f >= 0.0f) {
                    return 1.0f - (f / f2);
                }
                if (this.v && i == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f < 0.0f) {
            return f / (-f2);
        }
        return 0.0f;
    }

    public final float f(float f, float f2, float f3, float f4) {
        float interpolation;
        float c = c(f * f2, 0.0f, f3);
        float e = e(f2 - f4, c) - e(f4, c);
        if (e < 0.0f) {
            interpolation = -this.i.getInterpolation(-e);
        } else if (e <= 0.0f) {
            return 0.0f;
        } else {
            interpolation = this.i.getInterpolation(e);
        }
        return c(interpolation, -1.0f, 1.0f);
    }

    public final void g() {
        if (this.t) {
            this.v = false;
        } else {
            this.h.i();
        }
    }

    public boolean h() {
        a aVar = this.h;
        int f = aVar.f();
        int d = aVar.d();
        return (f != 0 && canTargetScrollVertically(f)) || (d != 0 && canTargetScrollHorizontally(d));
    }

    public final void i() {
        int i;
        if (this.k == null) {
            this.k = new b();
        }
        this.v = true;
        this.t = true;
        if (!this.s && (i = this.o) > 0) {
            ViewCompat.postOnAnimationDelayed(this.j, this.k, i);
        } else {
            this.k.run();
        }
        this.s = true;
    }

    public boolean isEnabled() {
        return this.w;
    }

    public boolean isExclusive() {
        return this.x;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
        if (r0 != 3) goto L12;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.w
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.g()
            goto L58
        L1a:
            r5.u = r2
            r5.s = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.j
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.b(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.j
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.b(r2, r7, r6, r3)
            androidx.core.widget.AutoScrollHelper$a r7 = r5.h
            r7.l(r0, r6)
            boolean r6 = r5.v
            if (r6 != 0) goto L58
            boolean r6 = r5.h()
            if (r6 == 0) goto L58
            r5.i()
        L58:
            boolean r6 = r5.x
            if (r6 == 0) goto L61
            boolean r6 = r5.v
            if (r6 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.AutoScrollHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract void scrollTargetBy(int i, int i2);

    @NonNull
    public AutoScrollHelper setActivationDelay(int i) {
        this.o = i;
        return this;
    }

    @NonNull
    public AutoScrollHelper setEdgeType(int i) {
        this.n = i;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean z) {
        if (this.w && !z) {
            g();
        }
        this.w = z;
        return this;
    }

    public AutoScrollHelper setExclusive(boolean z) {
        this.x = z;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumEdges(float f, float f2) {
        float[] fArr = this.m;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumVelocity(float f, float f2) {
        float[] fArr = this.r;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMinimumVelocity(float f, float f2) {
        float[] fArr = this.q;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampDownDuration(int i) {
        this.h.j(i);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampUpDuration(int i) {
        this.h.k(i);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeEdges(float f, float f2) {
        float[] fArr = this.l;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeVelocity(float f, float f2) {
        float[] fArr = this.p;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }
}
