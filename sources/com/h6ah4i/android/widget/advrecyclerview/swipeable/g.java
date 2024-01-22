package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public class g {
    public static final Interpolator r = new c(0.15f);

    /* renamed from: a  reason: collision with root package name */
    public RecyclerViewSwipeManager f11928a;
    public RecyclerView.ViewHolder b;
    public View c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public final int i;
    public float j;
    public float k;
    public int l;
    public int m;
    public float n;
    public int o;
    public int p;
    public final boolean q;

    public g(RecyclerViewSwipeManager recyclerViewSwipeManager, RecyclerView.ViewHolder viewHolder, int i, boolean z) {
        this.f11928a = recyclerViewSwipeManager;
        this.b = viewHolder;
        this.d = d.f(i);
        this.e = d.h(i);
        this.f = d.g(i);
        this.g = d.e(i);
        this.q = z;
        View a2 = f.a(viewHolder);
        this.c = a2;
        this.h = a2.getWidth();
        int height = this.c.getHeight();
        this.i = height;
        this.j = a(this.h);
        this.k = a(height);
    }

    public static float a(int i) {
        if (i != 0) {
            return 1.0f / i;
        }
        return 0.0f;
    }

    public static int b(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public void c() {
        this.f11928a = null;
        this.b = null;
        this.l = 0;
        this.m = 0;
        this.h = 0;
        this.j = 0.0f;
        this.k = 0.0f;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.n = 0.0f;
        this.o = 0;
        this.p = 0;
        this.c = null;
    }

    public void d() {
        int i = (int) (this.b.itemView.getResources().getDisplayMetrics().density * 48.0f);
        int max = Math.max(0, this.h - i);
        int max2 = Math.max(0, this.i - i);
        this.o = b(this.f11928a.i(this.b), -max, max);
        this.p = b(this.f11928a.j(this.b), -max2, max2);
    }

    public void e(int i, int i2, int i3) {
        int i4;
        if (this.l == i2 && this.m == i3) {
            return;
        }
        this.l = i2;
        this.m = i3;
        boolean z = this.q;
        int i5 = z ? i2 + this.o : this.p + i3;
        int i6 = z ? this.h : this.i;
        float f = z ? this.j : this.k;
        if (z) {
            i4 = i5 > 0 ? this.f : this.d;
        } else {
            i4 = i5 > 0 ? this.g : this.e;
        }
        float f2 = 0.0f;
        if (i4 == 1) {
            f2 = Math.signum(i5) * r.getInterpolation(Math.min(Math.abs(i5), i6) * f);
        } else if (i4 == 2) {
            f2 = Math.min(Math.max(i5 * f, -1.0f), 1.0f);
        }
        this.f11928a.b(this.b, i, this.n, f2, true, this.q, false, true);
        this.n = f2;
    }
}
