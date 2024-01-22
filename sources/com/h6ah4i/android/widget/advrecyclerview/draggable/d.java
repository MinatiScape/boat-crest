package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
/* loaded from: classes11.dex */
public class d extends a {
    public float A;
    public float B;
    public float C;
    public Interpolator D;
    public Interpolator E;
    public Interpolator F;
    public float G;
    public float H;
    public float I;
    public float J;
    public int e;
    public int f;
    public Bitmap g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public NinePatchDrawable n;
    public final Rect o;
    public boolean p;
    public boolean q;
    public ItemDraggableRange r;
    public int s;
    public int t;
    public DraggingItemInfo u;
    public Paint v;
    public long w;
    public long x;
    public float y;
    public float z;

    public d(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange) {
        super(recyclerView, viewHolder);
        this.o = new Rect();
        this.x = 0L;
        this.y = 1.0f;
        this.z = 0.0f;
        this.A = 1.0f;
        this.D = null;
        this.E = null;
        this.F = null;
        this.r = itemDraggableRange;
        this.v = new Paint();
    }

    public static int g(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public static View i(RecyclerView recyclerView, ItemDraggableRange itemDraggableRange, int i, int i2) {
        int layoutPosition;
        if (i == -1 || i2 == -1) {
            return null;
        }
        int childCount = recyclerView.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = recyclerView.getChildAt(i3);
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
            if (childViewHolder != null && (layoutPosition = childViewHolder.getLayoutPosition()) >= i && layoutPosition <= i2 && itemDraggableRange.checkInRange(layoutPosition)) {
                return childAt;
            }
        }
        return null;
    }

    public static View j(RecyclerView recyclerView, ItemDraggableRange itemDraggableRange, int i, int i2) {
        int layoutPosition;
        if (i == -1 || i2 == -1) {
            return null;
        }
        for (int childCount = recyclerView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = recyclerView.getChildAt(childCount);
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
            if (childViewHolder != null && (layoutPosition = childViewHolder.getLayoutPosition()) >= i && layoutPosition <= i2 && itemDraggableRange.checkInRange(layoutPosition)) {
                return childAt;
            }
        }
        return null;
    }

    public static float p(Interpolator interpolator, float f) {
        return interpolator != null ? interpolator.getInterpolation(f) : f;
    }

    public void A(RecyclerView.ViewHolder viewHolder) {
        if (this.d == null) {
            this.d = viewHolder;
            viewHolder.itemView.setVisibility(4);
            return;
        }
        throw new IllegalStateException("A new view holder is attempt to be assigned before invalidating the older one");
    }

    public void B(boolean z) {
        if (this.q == z) {
            return;
        }
        this.q = z;
    }

    public void C(NinePatchDrawable ninePatchDrawable) {
        this.n = ninePatchDrawable;
        if (ninePatchDrawable != null) {
            ninePatchDrawable.getPadding(this.o);
        }
    }

    public void D(e eVar) {
        this.x = eVar.f11911a;
        this.y = eVar.b;
        this.D = eVar.e;
        this.z = eVar.c;
        this.E = eVar.f;
        this.A = eVar.d;
        this.F = eVar.g;
    }

    public void E(DraggingItemInfo draggingItemInfo, int i, int i2) {
        if (this.p) {
            return;
        }
        View view = this.d.itemView;
        this.u = draggingItemInfo;
        this.g = h(view, this.n);
        this.h = this.c.getPaddingLeft();
        this.j = this.c.getPaddingTop();
        this.s = CustomRecyclerViewUtils.getOrientation(this.c);
        this.t = CustomRecyclerViewUtils.getLayoutType(this.c);
        this.B = view.getScaleX();
        this.C = view.getScaleY();
        this.G = 1.0f;
        this.H = 1.0f;
        this.I = 0.0f;
        this.J = 1.0f;
        view.setVisibility(4);
        F(i, i2, true);
        this.c.addItemDecoration(this);
        this.w = System.currentTimeMillis();
        this.p = true;
    }

    public boolean F(int i, int i2, boolean z) {
        this.l = i;
        this.m = i2;
        return z(z);
    }

    public final void G(float f, int i) {
        RecyclerView.ViewHolder viewHolder = this.d;
        if (viewHolder != null) {
            a.d(this.c, viewHolder, f - viewHolder.itemView.getLeft(), i - this.d.itemView.getTop());
        }
    }

    public void H(DraggingItemInfo draggingItemInfo, RecyclerView.ViewHolder viewHolder) {
        if (this.p) {
            if (this.d != viewHolder) {
                u();
                this.d = viewHolder;
            }
            this.g = h(viewHolder.itemView, this.n);
            this.u = draggingItemInfo;
            z(true);
        }
    }

    public final void I() {
        RecyclerView recyclerView = this.c;
        if (recyclerView.getChildCount() > 0) {
            this.h = 0;
            this.i = recyclerView.getWidth() - this.u.width;
            this.j = 0;
            int height = recyclerView.getHeight();
            int i = this.u.height;
            this.k = height - i;
            int i2 = this.s;
            if (i2 == 0) {
                this.j += recyclerView.getPaddingTop();
                this.k -= recyclerView.getPaddingBottom();
                this.h = -this.u.width;
                this.i = recyclerView.getWidth();
            } else if (i2 == 1) {
                this.j = -i;
                this.k = recyclerView.getHeight();
                this.h += recyclerView.getPaddingLeft();
                this.i -= recyclerView.getPaddingRight();
            }
            this.i = Math.max(this.h, this.i);
            this.k = Math.max(this.j, this.k);
            if (!this.q) {
                int findFirstVisibleItemPosition = CustomRecyclerViewUtils.findFirstVisibleItemPosition(recyclerView, true);
                int findLastVisibleItemPosition = CustomRecyclerViewUtils.findLastVisibleItemPosition(recyclerView, true);
                View i3 = i(recyclerView, this.r, findFirstVisibleItemPosition, findLastVisibleItemPosition);
                View j = j(recyclerView, this.r, findFirstVisibleItemPosition, findLastVisibleItemPosition);
                int i4 = this.s;
                if (i4 == 0) {
                    if (i3 != null) {
                        this.h = Math.min(this.h, i3.getLeft());
                    }
                    if (j != null) {
                        this.i = Math.min(this.i, Math.max(0, j.getRight() - this.u.width));
                    }
                } else if (i4 == 1) {
                    if (i3 != null) {
                        this.j = Math.min(this.k, i3.getTop());
                    }
                    if (j != null) {
                        this.k = Math.min(this.k, Math.max(0, j.getBottom() - this.u.height));
                    }
                }
            }
        } else {
            int paddingLeft = recyclerView.getPaddingLeft();
            this.h = paddingLeft;
            this.i = paddingLeft;
            int paddingTop = recyclerView.getPaddingTop();
            this.j = paddingTop;
            this.k = paddingTop;
        }
        int i5 = this.l;
        DraggingItemInfo draggingItemInfo = this.u;
        this.e = i5 - draggingItemInfo.grabbedPositionX;
        this.f = this.m - draggingItemInfo.grabbedPositionY;
        if (CustomRecyclerViewUtils.isLinearLayout(this.t)) {
            this.e = g(this.e, this.h, this.i);
            this.f = g(this.f, this.j, this.k);
        }
    }

    public final Bitmap h(View view, NinePatchDrawable ninePatchDrawable) {
        int top = view.getTop();
        int left = view.getLeft();
        int width = view.getWidth();
        int height = view.getHeight();
        Rect rect = this.o;
        int i = rect.left + width + rect.right;
        int i2 = rect.top + height + rect.bottom;
        view.measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(height, 1073741824));
        view.layout(left, top, width + left, height + top);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        if (ninePatchDrawable != null) {
            ninePatchDrawable.setBounds(0, 0, i, i2);
            ninePatchDrawable.draw(canvas);
        }
        int save = canvas.save();
        Rect rect2 = this.o;
        canvas.clipRect(rect2.left, rect2.top, i - rect2.right, i2 - rect2.bottom);
        Rect rect3 = this.o;
        canvas.translate(rect3.left, rect3.top);
        view.draw(canvas);
        canvas.restoreToCount(save);
        return createBitmap;
    }

    public void k(boolean z) {
        if (this.p) {
            this.c.removeItemDecoration(this);
        }
        RecyclerView.ItemAnimator itemAnimator = this.c.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        this.c.stopScroll();
        G(this.e, this.f);
        RecyclerView.ViewHolder viewHolder = this.d;
        if (viewHolder != null) {
            b(viewHolder.itemView, this.G, this.H, this.I, this.J, z);
        }
        RecyclerView.ViewHolder viewHolder2 = this.d;
        if (viewHolder2 != null) {
            viewHolder2.itemView.setVisibility(0);
        }
        this.d = null;
        Bitmap bitmap = this.g;
        if (bitmap != null) {
            bitmap.recycle();
            this.g = null;
        }
        this.r = null;
        this.e = 0;
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.p = false;
    }

    public int l() {
        return this.e - this.u.initialItemLeft;
    }

    public int m() {
        return this.f - this.u.initialItemTop;
    }

    public int n() {
        return this.e;
    }

    public int o() {
        return this.f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        if (this.g == null) {
            return;
        }
        int min = (int) Math.min(System.currentTimeMillis() - this.w, this.x);
        long j = this.x;
        float f = j > 0 ? min / ((float) j) : 1.0f;
        float p = p(this.D, f);
        float f2 = this.y;
        float f3 = this.B;
        float f4 = ((f2 - f3) * p) + f3;
        float f5 = this.C;
        float f6 = (p * (f2 - f5)) + f5;
        float p2 = (p(this.F, f) * (this.A - 1.0f)) + 1.0f;
        float p3 = p(this.E, f) * this.z;
        if (f4 > 0.0f && f6 > 0.0f && p2 > 0.0f) {
            this.v.setAlpha((int) (255.0f * p2));
            int save = canvas.save();
            int i = this.e;
            DraggingItemInfo draggingItemInfo = this.u;
            canvas.translate(i + draggingItemInfo.grabbedPositionX, this.f + draggingItemInfo.grabbedPositionY);
            canvas.scale(f4, f6);
            canvas.rotate(p3);
            Rect rect = this.o;
            int i2 = rect.left;
            DraggingItemInfo draggingItemInfo2 = this.u;
            canvas.translate(-(i2 + draggingItemInfo2.grabbedPositionX), -(rect.top + draggingItemInfo2.grabbedPositionY));
            canvas.drawBitmap(this.g, 0.0f, 0.0f, this.v);
            canvas.restoreToCount(save);
        }
        if (f < 1.0f) {
            ViewCompat.postInvalidateOnAnimation(this.c);
        }
        this.G = f4;
        this.H = f6;
        this.I = p3;
        this.J = p2;
    }

    public int q() {
        return this.f + this.u.height;
    }

    public int r() {
        return this.e;
    }

    public int s() {
        return this.e + this.u.width;
    }

    public int t() {
        return this.f;
    }

    public void u() {
        RecyclerView.ViewHolder viewHolder = this.d;
        if (viewHolder != null) {
            viewHolder.itemView.setTranslationX(0.0f);
            this.d.itemView.setTranslationY(0.0f);
            this.d.itemView.setVisibility(0);
        }
        this.d = null;
    }

    public boolean v() {
        return this.f == this.k;
    }

    public boolean w() {
        return this.e == this.h;
    }

    public boolean x() {
        return this.e == this.i;
    }

    public boolean y() {
        return this.f == this.j;
    }

    public boolean z(boolean z) {
        int i = this.e;
        int i2 = this.f;
        I();
        int i3 = this.e;
        boolean z2 = (i == i3 && i2 == this.f) ? false : true;
        if (z2 || z) {
            G(i3, this.f);
            ViewCompat.postInvalidateOnAnimation(this.c);
        }
        return z2;
    }
}
