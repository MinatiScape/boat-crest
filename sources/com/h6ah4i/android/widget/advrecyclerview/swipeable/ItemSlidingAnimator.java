package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class ItemSlidingAnimator {
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 0;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_UP = 1;

    /* renamed from: a  reason: collision with root package name */
    public final e<RecyclerView.ViewHolder> f11918a;
    public int i;
    public final Interpolator b = new AccelerateDecelerateInterpolator();
    public final Interpolator c = new DecelerateInterpolator();
    public final Interpolator d = new AccelerateInterpolator(0.8f);
    public final int[] g = new int[2];
    public final Rect h = new Rect();
    public final List<RecyclerView.ViewHolder> e = new ArrayList();
    public final List<WeakReference<d>> f = new ArrayList();

    /* loaded from: classes11.dex */
    public static final class a extends d {
        public final float i;
        public final boolean j;

        public a(RecyclerView.ViewHolder viewHolder, float f, boolean z) {
            super(viewHolder);
            this.i = f;
            this.j = z;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.ItemSlidingAnimator.d
        public void c(RecyclerView.ViewHolder viewHolder) {
            View a2 = f.a(viewHolder);
            if (this.j) {
                ItemSlidingAnimator.f(viewHolder, true, (int) ((a2.getWidth() * this.i) + 0.5f), 0);
            } else {
                ItemSlidingAnimator.f(viewHolder, false, 0, (int) ((a2.getHeight() * this.i) + 0.5f));
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener {

        /* renamed from: a  reason: collision with root package name */
        public e<RecyclerView.ViewHolder> f11919a;
        public List<RecyclerView.ViewHolder> b;
        public RecyclerView.ViewHolder c;
        public ViewPropertyAnimatorCompat d;
        public final int e;
        public final int f;
        public final long g;
        public final boolean h;
        public final c i;
        public final Interpolator j;
        public float k;

        public b(e<RecyclerView.ViewHolder> eVar, List<RecyclerView.ViewHolder> list, RecyclerView.ViewHolder viewHolder, int i, int i2, long j, boolean z, Interpolator interpolator, c cVar) {
            this.f11919a = eVar;
            this.b = list;
            this.c = viewHolder;
            this.e = i;
            this.f = i2;
            this.h = z;
            this.i = cVar;
            this.g = j;
            this.j = interpolator;
        }

        public void a() {
            View a2 = f.a(this.c);
            this.k = 1.0f / Math.max(1.0f, this.h ? a2.getWidth() : a2.getHeight());
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(a2);
            this.d = animate;
            animate.setDuration(this.g);
            this.d.translationX(this.e);
            this.d.translationY(this.f);
            Interpolator interpolator = this.j;
            if (interpolator != null) {
                this.d.setInterpolator(interpolator);
            }
            this.d.setListener(this);
            this.d.setUpdateListener(this);
            this.b.add(this.c);
            this.d.start();
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(View view) {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            this.d.setListener(null);
            if (Build.VERSION.SDK_INT >= 19) {
                com.h6ah4i.android.widget.advrecyclerview.swipeable.a.a(view);
            } else {
                this.d.setUpdateListener(null);
            }
            view.setTranslationX(this.e);
            view.setTranslationY(this.f);
            this.b.remove(this.c);
            ViewParent parent = this.c.itemView.getParent();
            if (parent != null) {
                ViewCompat.postInvalidateOnAnimation((View) parent);
            }
            c cVar = this.i;
            if (cVar != null) {
                cVar.f11920a.slideAnimationEnd();
            }
            this.b = null;
            this.d = null;
            this.c = null;
            this.f11919a = null;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorUpdateListener
        public void onAnimationUpdate(View view) {
            float translationX = this.h ? view.getTranslationX() : view.getTranslationY();
            e<RecyclerView.ViewHolder> eVar = this.f11919a;
            RecyclerView.ViewHolder viewHolder = this.c;
            eVar.k(viewHolder, viewHolder.getLayoutPosition(), translationX * this.k, true, this.h, false);
        }
    }

    /* loaded from: classes11.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public SwipeResultAction f11920a;

        public c(int i, SwipeResultAction swipeResultAction) {
            this.f11920a = swipeResultAction;
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class d implements Runnable {
        public final WeakReference<RecyclerView.ViewHolder> h;

        public d(RecyclerView.ViewHolder viewHolder) {
            this.h = new WeakReference<>(viewHolder);
        }

        public boolean a(RecyclerView.ViewHolder viewHolder) {
            return this.h.get() == viewHolder;
        }

        public boolean b(RecyclerView.ViewHolder viewHolder) {
            return this.h.get() == null;
        }

        public abstract void c(RecyclerView.ViewHolder viewHolder);

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView.ViewHolder viewHolder = this.h.get();
            if (viewHolder != null) {
                c(viewHolder);
            }
        }
    }

    public ItemSlidingAnimator(e<RecyclerView.ViewHolder> eVar) {
        this.f11918a = eVar;
    }

    public static void e(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            View a2 = f.a(viewHolder);
            ViewCompat.animate(a2).cancel();
            a2.setTranslationX(i);
            a2.setTranslationY(i2);
        }
    }

    public static void f(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2) {
        e(viewHolder, z, i, i2);
    }

    public final boolean a(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2, long j, Interpolator interpolator, c cVar) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            View a2 = f.a(viewHolder);
            int translationX = (int) (a2.getTranslationX() + 0.5f);
            int translationY = (int) (a2.getTranslationY() + 0.5f);
            endAnimation(viewHolder);
            int translationX2 = (int) (a2.getTranslationX() + 0.5f);
            int translationY2 = (int) (a2.getTranslationY() + 0.5f);
            if (j != 0 && ((translationX2 != i || translationY2 != i2) && Math.max(Math.abs(i - translationX), Math.abs(i2 - translationY)) > this.i)) {
                a2.setTranslationX(translationX);
                a2.setTranslationY(translationY);
                new b(this.f11918a, this.e, viewHolder, i, i2, j, z, interpolator, cVar).a();
                return true;
            }
            a2.setTranslationX(i);
            a2.setTranslationY(i2);
            return false;
        }
        return false;
    }

    public final boolean b(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2, long j, Interpolator interpolator, c cVar) {
        return a(viewHolder, z, i, i2, j, interpolator, cVar);
    }

    public final void c(RecyclerView.ViewHolder viewHolder) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            d dVar = this.f.get(size).get();
            if (dVar != null && dVar.a(viewHolder)) {
                viewHolder.itemView.removeCallbacks(dVar);
                this.f.remove(size);
            } else if (dVar == null || dVar.b(viewHolder)) {
                this.f.remove(size);
            }
        }
    }

    public final void d(RecyclerView.ViewHolder viewHolder, d dVar) {
        this.f.add(new WeakReference<>(dVar));
        viewHolder.itemView.post(dVar);
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            c(viewHolder);
            ViewCompat.animate(f.a(viewHolder)).cancel();
            if (this.e.remove(viewHolder)) {
                throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [slide]");
            }
        }
    }

    public void endAnimations() {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            endAnimation(this.e.get(size));
        }
    }

    public boolean finishSwipeSlideToDefaultPosition(RecyclerView.ViewHolder viewHolder, boolean z, boolean z2, long j, int i, SwipeResultAction swipeResultAction) {
        c(viewHolder);
        return h(viewHolder, 0.0f, false, z, z2, this.b, j, new c(i, swipeResultAction));
    }

    public boolean finishSwipeSlideToOutsideOfWindow(RecyclerView.ViewHolder viewHolder, int i, boolean z, long j, int i2, SwipeResultAction swipeResultAction) {
        c(viewHolder);
        return g(viewHolder, i, z, j, new c(i2, swipeResultAction));
    }

    public final boolean g(RecyclerView.ViewHolder viewHolder, int i, boolean z, long j, c cVar) {
        boolean z2;
        if (viewHolder instanceof SwipeableItemViewHolder) {
            View a2 = f.a(viewHolder);
            ViewGroup viewGroup = (ViewGroup) a2.getParent();
            if (viewGroup == null) {
                return false;
            }
            int left = a2.getLeft();
            int right = a2.getRight();
            int top = a2.getTop();
            int i2 = right - left;
            int bottom = a2.getBottom() - top;
            viewGroup.getWindowVisibleDisplayFrame(this.h);
            int width = this.h.width();
            int height = this.h.height();
            if (i2 == 0 || bottom == 0) {
                if (i != 0) {
                    if (i == 1) {
                        height = -height;
                    } else if (i != 2) {
                        if (i != 3) {
                            width = 0;
                            height = 0;
                            z2 = false;
                        }
                    }
                    width = 0;
                    z2 = false;
                } else {
                    width = -width;
                }
                height = 0;
                z2 = false;
            } else {
                viewGroup.getLocationInWindow(this.g);
                int[] iArr = this.g;
                int i3 = iArr[0];
                int i4 = iArr[1];
                if (i == 0) {
                    height = 0;
                    width = -(i3 + i2);
                } else if (i == 1) {
                    width = 0;
                    height = -(i4 + bottom);
                } else if (i == 2) {
                    width -= i3 - left;
                    z2 = z;
                    height = 0;
                } else if (i != 3) {
                    z2 = z;
                    width = 0;
                    height = 0;
                } else {
                    height -= i4 - top;
                    z2 = z;
                    width = 0;
                }
                z2 = z;
            }
            if (z2) {
                z2 = ViewCompat.isAttachedToWindow(a2) && a2.getVisibility() == 0;
            }
            return b(viewHolder, i == 0 || i == 2, width, height, z2 ? j : 0L, this.d, cVar);
        }
        return false;
    }

    public int getImmediatelySetTranslationThreshold() {
        return this.i;
    }

    public int getSwipeContainerViewTranslationX(RecyclerView.ViewHolder viewHolder) {
        return (int) (f.a(viewHolder).getTranslationX() + 0.5f);
    }

    public int getSwipeContainerViewTranslationY(RecyclerView.ViewHolder viewHolder) {
        return (int) (f.a(viewHolder).getTranslationY() + 0.5f);
    }

    public final boolean h(RecyclerView.ViewHolder viewHolder, float f, boolean z, boolean z2, boolean z3, Interpolator interpolator, long j, c cVar) {
        boolean z4;
        float f2 = f;
        View a2 = f.a(viewHolder);
        if (z3) {
            z4 = ViewCompat.isAttachedToWindow(a2) && a2.getVisibility() == 0;
        } else {
            z4 = z3;
        }
        long j2 = z4 ? j : 0L;
        if (f2 != 0.0f) {
            int width = a2.getWidth();
            int height = a2.getHeight();
            if (z2 && (!z || width != 0)) {
                if (z) {
                    f2 *= width;
                }
                return b(viewHolder, true, (int) (f2 + 0.5f), 0, j2, interpolator, cVar);
            } else if (!z2 && (!z || height != 0)) {
                if (z) {
                    f2 *= height;
                }
                return b(viewHolder, false, 0, (int) (f2 + 0.5f), j2, interpolator, cVar);
            } else if (cVar == null) {
                d(viewHolder, new a(viewHolder, f, z2));
                return false;
            } else {
                throw new IllegalStateException("Unexpected state in slideToSpecifiedPositionInternal (swipeFinish == null)");
            }
        }
        return b(viewHolder, z2, 0, 0, j2, interpolator, cVar);
    }

    public boolean isRunning(RecyclerView.ViewHolder viewHolder) {
        return this.e.contains(viewHolder);
    }

    public void setImmediatelySetTranslationThreshold(int i) {
        this.i = i;
    }

    public void slideToDefaultPosition(RecyclerView.ViewHolder viewHolder, boolean z, boolean z2, long j) {
        c(viewHolder);
        h(viewHolder, 0.0f, false, z, z2, this.b, j, null);
    }

    public void slideToOutsideOfWindow(RecyclerView.ViewHolder viewHolder, int i, boolean z, long j) {
        c(viewHolder);
        g(viewHolder, i, z, j, null);
    }

    public void slideToSpecifiedPosition(RecyclerView.ViewHolder viewHolder, float f, boolean z, boolean z2, boolean z3, long j) {
        c(viewHolder);
        h(viewHolder, f, z, z2, z3, this.c, j, null);
    }

    public boolean isRunning() {
        return !this.e.isEmpty();
    }
}
