package androidx.viewpager2.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final ViewPager2 f1765a;
    public final e b;
    public final RecyclerView c;
    public VelocityTracker d;
    public int e;
    public float f;
    public int g;
    public long h;

    public c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.f1765a = viewPager2;
        this.b = eVar;
        this.c = recyclerView;
    }

    public final void a(long j, int i, float f, float f2) {
        MotionEvent obtain = MotionEvent.obtain(this.h, j, i, f, f2, 0);
        this.d.addMovement(obtain);
        obtain.recycle();
    }

    @UiThread
    public boolean b() {
        if (this.b.g()) {
            return false;
        }
        this.g = 0;
        this.f = 0;
        this.h = SystemClock.uptimeMillis();
        c();
        this.b.k();
        if (!this.b.i()) {
            this.c.stopScroll();
        }
        a(this.h, 0, 0.0f, 0.0f);
        return true;
    }

    public final void c() {
        VelocityTracker velocityTracker = this.d;
        if (velocityTracker == null) {
            this.d = VelocityTracker.obtain();
            this.e = ViewConfiguration.get(this.f1765a.getContext()).getScaledMaximumFlingVelocity();
            return;
        }
        velocityTracker.clear();
    }

    @UiThread
    public boolean d() {
        if (this.b.h()) {
            this.b.m();
            VelocityTracker velocityTracker = this.d;
            velocityTracker.computeCurrentVelocity(1000, this.e);
            if (this.c.fling((int) velocityTracker.getXVelocity(), (int) velocityTracker.getYVelocity())) {
                return true;
            }
            this.f1765a.h();
            return true;
        }
        return false;
    }

    @UiThread
    public boolean e(float f) {
        if (this.b.h()) {
            float f2 = this.f - f;
            this.f = f2;
            int round = Math.round(f2 - this.g);
            this.g += round;
            long uptimeMillis = SystemClock.uptimeMillis();
            boolean z = this.f1765a.getOrientation() == 0;
            int i = z ? round : 0;
            int i2 = z ? 0 : round;
            float f3 = z ? this.f : 0.0f;
            float f4 = z ? 0.0f : this.f;
            this.c.scrollBy(i, i2);
            a(uptimeMillis, 2, f3, f4);
            return true;
        }
        return false;
    }

    public boolean f() {
        return this.b.h();
    }
}
