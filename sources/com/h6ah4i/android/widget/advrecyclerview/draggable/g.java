package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
/* loaded from: classes11.dex */
public class g extends com.h6ah4i.android.widget.advrecyclerview.draggable.a {
    public static final ViewPropertyAnimatorListener q = new a();
    public RecyclerView.ViewHolder e;
    public Interpolator f;
    public int g;
    public int h;
    public final Rect i;
    public final Rect j;
    public final Rect k;
    public boolean l;
    public float m;
    public float n;
    public DraggingItemInfo o;
    public boolean p;

    /* loaded from: classes11.dex */
    public static class a implements ViewPropertyAnimatorListener {
        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(View view) {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            ViewCompat.animate(view).setListener(null);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
        }
    }

    public g(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo) {
        super(recyclerView, viewHolder);
        this.i = new Rect();
        this.j = new Rect();
        Rect rect = new Rect();
        this.k = rect;
        this.o = draggingItemInfo;
        CustomRecyclerViewUtils.getDecorationOffsets(this.c.getLayoutManager(), this.d.itemView, rect);
    }

    public static float g(float f, float f2) {
        float f3 = (f * 0.7f) + (0.3f * f2);
        return Math.abs(f3 - f2) < 0.01f ? f2 : f3;
    }

    public final float h(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        View view = viewHolder2.itemView;
        int layoutPosition = viewHolder.getLayoutPosition();
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        CustomRecyclerViewUtils.getDecorationOffsets(this.c.getLayoutManager(), view, this.i);
        CustomRecyclerViewUtils.getLayoutMargins(view, this.j);
        Rect rect = this.j;
        Rect rect2 = this.i;
        int height = view.getHeight() + rect.top + rect.bottom + rect2.top + rect2.bottom;
        int width = view.getWidth() + rect.left + rect.right + rect2.left + rect2.right;
        float left = width != 0 ? (viewHolder.itemView.getLeft() - this.g) / width : 0.0f;
        float top = height != 0 ? (viewHolder.itemView.getTop() - this.h) / height : 0.0f;
        int orientation = CustomRecyclerViewUtils.getOrientation(this.c);
        if (orientation == 1) {
            left = layoutPosition > layoutPosition2 ? top : top + 1.0f;
        } else if (orientation != 0) {
            left = 0.0f;
        } else if (layoutPosition <= layoutPosition2) {
            left += 1.0f;
        }
        return Math.min(Math.max(left, 0.0f), 1.0f);
    }

    public void i(boolean z) {
        if (this.l) {
            this.c.removeItemDecoration(this);
        }
        RecyclerView.ItemAnimator itemAnimator = this.c.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        this.c.stopScroll();
        RecyclerView.ViewHolder viewHolder = this.e;
        if (viewHolder != null) {
            o(this.d, viewHolder, this.n);
            b(this.e.itemView, 1.0f, 1.0f, 0.0f, 1.0f, z);
            this.e = null;
        }
        this.d = null;
        this.g = 0;
        this.h = 0;
        this.n = 0.0f;
        this.m = 0.0f;
        this.l = false;
        this.o = null;
    }

    public void j(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == this.e) {
            k(null);
        }
    }

    public void k(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = this.e;
        if (viewHolder2 == viewHolder) {
            return;
        }
        if (viewHolder2 != null) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(viewHolder2.itemView);
            animate.cancel();
            animate.setDuration(10L).translationX(0.0f).translationY(0.0f).setListener(q).start();
        }
        this.e = viewHolder;
        if (viewHolder != null) {
            ViewCompat.animate(viewHolder.itemView).cancel();
        }
        this.p = true;
    }

    public void l(Interpolator interpolator) {
        this.f = interpolator;
    }

    public void m() {
        if (this.l) {
            return;
        }
        this.c.addItemDecoration(this, 0);
        this.l = true;
    }

    public void n(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    public final void o(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, float f) {
        View view = viewHolder2.itemView;
        int layoutPosition = viewHolder.getLayoutPosition();
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        DraggingItemInfo draggingItemInfo = this.o;
        Rect rect = draggingItemInfo.margins;
        Rect rect2 = this.k;
        int i = draggingItemInfo.height + rect.top + rect.bottom + rect2.top + rect2.bottom;
        int i2 = draggingItemInfo.width + rect.left + rect.right + rect2.left + rect2.right;
        Interpolator interpolator = this.f;
        if (interpolator != null) {
            f = interpolator.getInterpolation(f);
        }
        int orientation = CustomRecyclerViewUtils.getOrientation(this.c);
        if (orientation == 0) {
            if (layoutPosition > layoutPosition2) {
                view.setTranslationX(f * i2);
            } else {
                view.setTranslationX((f - 1.0f) * i2);
            }
        } else if (orientation != 1) {
        } else {
            if (layoutPosition > layoutPosition2) {
                view.setTranslationY(f * i);
            } else {
                view.setTranslationY((f - 1.0f) * i);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        RecyclerView.ViewHolder viewHolder = this.d;
        RecyclerView.ViewHolder viewHolder2 = this.e;
        if (viewHolder == null || viewHolder2 == null || viewHolder.getItemId() != this.o.id) {
            return;
        }
        float h = h(viewHolder, viewHolder2);
        this.m = h;
        if (this.p) {
            this.p = false;
            this.n = h;
        } else {
            this.n = g(this.n, h);
        }
        o(viewHolder, viewHolder2, this.n);
    }
}
