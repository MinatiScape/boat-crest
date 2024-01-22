package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.graphics.Canvas;
import android.widget.EdgeEffect;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public abstract class b extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView f11910a;
    public EdgeEffect b;
    public EdgeEffect c;
    public boolean d;
    public int e;
    public int f;

    public b(@NonNull RecyclerView recyclerView) {
        this.f11910a = recyclerView;
    }

    public static boolean a(Canvas canvas, RecyclerView recyclerView, int i, EdgeEffect edgeEffect) {
        if (edgeEffect.isFinished()) {
            return false;
        }
        int save = canvas.save();
        boolean e = e(recyclerView);
        if (i == 0) {
            canvas.rotate(-90.0f);
            if (e) {
                canvas.translate((-recyclerView.getHeight()) + recyclerView.getPaddingTop(), recyclerView.getPaddingLeft());
            } else {
                canvas.translate(-recyclerView.getHeight(), 0.0f);
            }
        } else if (i != 1) {
            if (i == 2) {
                canvas.rotate(90.0f);
                if (e) {
                    canvas.translate(recyclerView.getPaddingTop(), (-recyclerView.getWidth()) + recyclerView.getPaddingRight());
                } else {
                    canvas.translate(0.0f, -recyclerView.getWidth());
                }
            } else if (i == 3) {
                canvas.rotate(180.0f);
                if (e) {
                    canvas.translate((-recyclerView.getWidth()) + recyclerView.getPaddingRight(), (-recyclerView.getHeight()) + recyclerView.getPaddingBottom());
                } else {
                    canvas.translate(-recyclerView.getWidth(), -recyclerView.getHeight());
                }
            }
        } else if (e) {
            canvas.translate(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop());
        }
        boolean draw = edgeEffect.draw(canvas);
        canvas.restoreToCount(save);
        return draw;
    }

    public static boolean e(RecyclerView recyclerView) {
        return recyclerView.getLayoutManager().getClipToPadding();
    }

    public static void l(RecyclerView recyclerView, EdgeEffect edgeEffect, int i) {
        int measuredWidth = recyclerView.getMeasuredWidth();
        int measuredHeight = recyclerView.getMeasuredHeight();
        if (e(recyclerView)) {
            measuredWidth -= recyclerView.getPaddingLeft() + recyclerView.getPaddingRight();
            measuredHeight -= recyclerView.getPaddingTop() + recyclerView.getPaddingBottom();
        }
        int max = Math.max(0, measuredWidth);
        int max2 = Math.max(0, measuredHeight);
        if (i == 0 || i == 2) {
            max = max2;
            max2 = max;
        }
        edgeEffect.setSize(max, max2);
    }

    public final void b(RecyclerView recyclerView) {
        if (this.b == null) {
            this.b = new EdgeEffect(recyclerView.getContext());
        }
        l(recyclerView, this.b, this.e);
    }

    public final void c(RecyclerView recyclerView) {
        if (this.c == null) {
            this.c = new EdgeEffect(recyclerView.getContext());
        }
        l(recyclerView, this.c, this.f);
    }

    public void d() {
        if (this.d) {
            this.f11910a.removeItemDecoration(this);
        }
        i();
        this.f11910a = null;
        this.d = false;
    }

    public abstract int f(int i);

    public void g(float f) {
        b(this.f11910a);
        EdgeEffectCompat.onPull(this.b, f, 0.5f);
        ViewCompat.postInvalidateOnAnimation(this.f11910a);
    }

    public void h(float f) {
        c(this.f11910a);
        EdgeEffectCompat.onPull(this.c, f, 0.5f);
        ViewCompat.postInvalidateOnAnimation(this.f11910a);
    }

    public void i() {
        EdgeEffect edgeEffect = this.b;
        boolean z = false;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = false | this.b.isFinished();
        }
        EdgeEffect edgeEffect2 = this.c;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.c.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this.f11910a);
        }
    }

    public void j() {
        if (this.d) {
            this.f11910a.removeItemDecoration(this);
            this.f11910a.addItemDecoration(this);
        }
    }

    public void k() {
        if (this.d) {
            return;
        }
        this.e = f(0);
        this.f = f(1);
        this.f11910a.addItemDecoration(this);
        this.d = true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        EdgeEffect edgeEffect = this.b;
        boolean a2 = edgeEffect != null ? false | a(canvas, recyclerView, this.e, edgeEffect) : false;
        EdgeEffect edgeEffect2 = this.c;
        if (edgeEffect2 != null) {
            a2 |= a(canvas, recyclerView, this.f, edgeEffect2);
        }
        if (a2) {
            ViewCompat.postInvalidateOnAnimation(recyclerView);
        }
    }
}
