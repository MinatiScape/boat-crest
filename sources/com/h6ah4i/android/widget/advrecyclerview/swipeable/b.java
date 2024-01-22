package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
public class b extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView f11926a;
    public RecyclerView.ViewHolder b;
    public final long c;
    public final Rect d;
    public int e;
    public int f;
    public long g;
    public final long h;
    public final long i;
    public Interpolator j;
    public Drawable k;
    public final boolean l;
    public int m;

    /* loaded from: classes11.dex */
    public static class a implements Runnable {
        public WeakReference<b> h;
        public final int i;

        public a(b bVar, int i) {
            this.h = new WeakReference<>(bVar);
            this.i = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = this.h.get();
            this.h.clear();
            this.h = null;
            if (bVar != null) {
                bVar.f(this.i);
            }
        }
    }

    public b(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, long j, long j2) {
        Rect rect = new Rect();
        this.d = rect;
        boolean z = false;
        this.m = 0;
        this.f11926a = recyclerView;
        this.b = viewHolder;
        this.c = viewHolder.getItemId();
        this.l = (i == 2 || i == 4) ? true : true;
        this.h = j + 50;
        this.i = j2;
        this.e = (int) (viewHolder.itemView.getTranslationX() + 0.5f);
        this.f = (int) (viewHolder.itemView.getTranslationY() + 0.5f);
        CustomRecyclerViewUtils.getViewBounds(this.b.itemView, rect);
    }

    public static long d(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= j) {
            return currentTimeMillis - j;
        }
        return Long.MAX_VALUE;
    }

    public final float a(long j) {
        long j2 = this.h;
        if (j < j2) {
            return 1.0f;
        }
        long j3 = this.i;
        if (j >= j2 + j3 || j3 == 0) {
            return 0.0f;
        }
        float f = 1.0f - (((float) (j - j2)) / ((float) j3));
        Interpolator interpolator = this.j;
        return interpolator != null ? interpolator.getInterpolation(f) : f;
    }

    public final void b(Canvas canvas, Drawable drawable, float f) {
        Rect rect = this.d;
        int i = this.e;
        int i2 = this.f;
        boolean z = this.l;
        float f2 = z ? 1.0f : f;
        if (!z) {
            f = 1.0f;
        }
        int width = (int) ((f2 * rect.width()) + 0.5f);
        int height = (int) ((f * rect.height()) + 0.5f);
        if (height == 0 || width == 0 || drawable == null) {
            return;
        }
        int save = canvas.save();
        int i3 = rect.left;
        int i4 = rect.top;
        canvas.clipRect(i3 + i, i4 + i2, i3 + i + width, i4 + i2 + height);
        canvas.translate((rect.left + i) - ((rect.width() - width) / 2), (rect.top + i2) - ((rect.height() - height) / 2));
        drawable.setBounds(0, 0, rect.width(), rect.height());
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    public final void c() {
        this.f11926a.removeItemDecoration(this);
        g();
        this.f11926a = null;
        this.b = null;
        this.f = 0;
        this.j = null;
    }

    public final void e(int i, long j) {
        int i2 = 1 << i;
        int i3 = this.m;
        if ((i3 & i2) != 0) {
            return;
        }
        this.m = i2 | i3;
        ViewCompat.postOnAnimationDelayed(this.f11926a, new a(this, i), j);
    }

    public void f(int i) {
        long d = d(this.g);
        this.m = (~(1 << i)) & this.m;
        if (i != 0) {
            if (i != 1) {
                return;
            }
            c();
            return;
        }
        long j = this.h;
        if (d < j) {
            e(0, j - d);
            return;
        }
        g();
        e(1, this.i);
    }

    public final void g() {
        ViewCompat.postInvalidateOnAnimation(this.f11926a);
    }

    public final boolean h(long j) {
        long j2 = this.h;
        return j >= j2 && j < j2 + this.i;
    }

    public void i(Interpolator interpolator) {
        this.j = interpolator;
    }

    public void j() {
        ViewCompat.animate(f.a(this.b)).cancel();
        this.f11926a.addItemDecoration(this);
        this.g = System.currentTimeMillis();
        this.f = (int) (this.b.itemView.getTranslationY() + 0.5f);
        this.k = this.b.itemView.getBackground();
        g();
        e(0, this.h);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        long d = d(this.g);
        b(canvas, this.k, a(d));
        if (this.c == this.b.getItemId()) {
            this.e = (int) (this.b.itemView.getTranslationX() + 0.5f);
            this.f = (int) (this.b.itemView.getTranslationY() + 0.5f);
        }
        if (h(d)) {
            g();
        }
    }
}
