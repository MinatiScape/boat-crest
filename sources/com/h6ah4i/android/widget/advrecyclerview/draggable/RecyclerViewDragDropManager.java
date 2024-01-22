package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.adapter.AdapterPath;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemIdComposer;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
public class RecyclerViewDragDropManager implements DraggableItemConstants {
    public static final int ITEM_MOVE_MODE_DEFAULT = 0;
    public static final int ITEM_MOVE_MODE_SWAP = 1;
    public com.h6ah4i.android.widget.advrecyclerview.draggable.c A;
    public RecyclerView.ViewHolder B;
    public DraggingItemInfo C;
    public com.h6ah4i.android.widget.advrecyclerview.draggable.d D;
    public com.h6ah4i.android.widget.advrecyclerview.draggable.g E;
    public NestedScrollView F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int T;
    public ItemDraggableRange U;
    public ItemDraggableRange V;
    public e W;
    public OnItemDragEventListener X;
    public boolean Y;
    public boolean Z;

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView f11902a;
    public Object c0;
    public com.h6ah4i.android.widget.advrecyclerview.draggable.b f;
    public NinePatchDrawable g;
    public float h;
    public int i;
    public int j;
    public int k;
    public int l;
    public boolean n;
    public boolean o;
    public boolean r;
    public boolean s;
    public int t;
    public int u;
    public static final Interpolator DEFAULT_SWAP_TARGET_TRANSITION_INTERPOLATOR = new BasicSwapTargetTranslationInterpolator();
    public static final Interpolator DEFAULT_ITEM_SETTLE_BACK_INTO_PLACE_ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    public Interpolator b = DEFAULT_SWAP_TARGET_TRANSITION_INTERPOLATOR;
    public long m = -1;
    public boolean p = true;
    public final Rect v = new Rect();
    public int w = 200;
    public Interpolator x = DEFAULT_ITEM_SETTLE_BACK_INTO_PLACE_ANIMATION_INTERPOLATOR;
    public int y = 0;
    public com.h6ah4i.android.widget.advrecyclerview.draggable.e z = new com.h6ah4i.android.widget.advrecyclerview.draggable.e();
    public int S = 0;
    public float a0 = 1.0f;
    public int b0 = 0;
    public g d0 = new g();
    public d e0 = new d();
    public final Runnable f0 = new c();
    public RecyclerView.OnItemTouchListener d = new a();
    public RecyclerView.OnScrollListener e = new b();
    public f c = new f(this);
    public int q = ViewConfiguration.getLongPressTimeout();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ItemMoveMode {
    }

    /* loaded from: classes11.dex */
    public interface OnItemDragEventListener {
        void onItemDragFinished(int i, int i2, boolean z);

        void onItemDragMoveDistanceUpdated(int i, int i2);

        void onItemDragPositionChanged(int i, int i2);

        void onItemDragStarted(int i);
    }

    /* loaded from: classes11.dex */
    public class a implements RecyclerView.OnItemTouchListener {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            return RecyclerViewDragDropManager.this.F(recyclerView, motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            RecyclerViewDragDropManager.this.J(z);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            RecyclerViewDragDropManager.this.M(recyclerView, motionEvent);
        }
    }

    /* loaded from: classes11.dex */
    public class b extends RecyclerView.OnScrollListener {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            RecyclerViewDragDropManager.this.K(recyclerView, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            RecyclerViewDragDropManager.this.L(recyclerView, i, i2);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerViewDragDropManager recyclerViewDragDropManager = RecyclerViewDragDropManager.this;
            if (recyclerViewDragDropManager.B != null) {
                recyclerViewDragDropManager.d(recyclerViewDragDropManager.s());
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public RecyclerView f11905a;
        public DraggingItemInfo b;
        public RecyclerView.ViewHolder c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public boolean i;
        public ItemDraggableRange j;
        public ItemDraggableRange k;
        public boolean l;

        public void a() {
            this.f11905a = null;
            this.b = null;
            this.c = null;
        }

        public void b(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i, int i2, ItemDraggableRange itemDraggableRange, ItemDraggableRange itemDraggableRange2, boolean z) {
            this.f11905a = recyclerView;
            this.b = draggingItemInfo;
            this.c = viewHolder;
            this.d = i;
            this.e = i2;
            this.j = itemDraggableRange;
            this.k = itemDraggableRange2;
            this.l = z;
            int layoutType = CustomRecyclerViewUtils.getLayoutType(recyclerView);
            this.h = layoutType;
            boolean z2 = CustomRecyclerViewUtils.extractOrientation(layoutType) == 1;
            this.i = z2;
            int i3 = i - draggingItemInfo.grabbedPositionX;
            this.f = i3;
            int i4 = i2 - draggingItemInfo.grabbedPositionY;
            this.g = i4;
            if (z2) {
                int max = Math.max(i3, recyclerView.getPaddingLeft());
                this.f = max;
                this.f = Math.min(max, Math.max(0, (recyclerView.getWidth() - recyclerView.getPaddingRight()) - this.b.width));
                return;
            }
            int max2 = Math.max(i4, recyclerView.getPaddingTop());
            this.g = max2;
            this.g = Math.min(max2, Math.max(0, (recyclerView.getHeight() - recyclerView.getPaddingBottom()) - this.b.height));
        }
    }

    /* loaded from: classes11.dex */
    public static class e extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public RecyclerViewDragDropManager f11906a;
        public MotionEvent b;

        public e(RecyclerViewDragDropManager recyclerViewDragDropManager) {
            this.f11906a = recyclerViewDragDropManager;
        }

        public void a() {
            removeMessages(1);
            MotionEvent motionEvent = this.b;
            if (motionEvent != null) {
                motionEvent.recycle();
                this.b = null;
            }
        }

        public boolean b() {
            return hasMessages(2);
        }

        public void c() {
            removeCallbacksAndMessages(null);
            this.f11906a = null;
        }

        public void d() {
            removeMessages(2);
        }

        public void e() {
            removeMessages(3);
        }

        public void f() {
            if (b()) {
                return;
            }
            sendEmptyMessage(2);
        }

        public void g() {
            sendEmptyMessage(3);
        }

        public void h(MotionEvent motionEvent, int i) {
            a();
            this.b = MotionEvent.obtain(motionEvent);
            sendEmptyMessageAtTime(1, motionEvent.getDownTime() + i);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                this.f11906a.z(this.b);
            } else if (i == 2) {
                this.f11906a.b(true);
            } else if (i != 3) {
            } else {
                this.f11906a.y();
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class f implements Runnable {
        public final WeakReference<RecyclerViewDragDropManager> h;
        public boolean i;

        public f(RecyclerViewDragDropManager recyclerViewDragDropManager) {
            this.h = new WeakReference<>(recyclerViewDragDropManager);
        }

        public void a() {
            this.h.clear();
            this.i = false;
        }

        public void b() {
            RecyclerViewDragDropManager recyclerViewDragDropManager;
            RecyclerView s;
            if (this.i || (recyclerViewDragDropManager = this.h.get()) == null || (s = recyclerViewDragDropManager.s()) == null) {
                return;
            }
            ViewCompat.postOnAnimation(s, this);
            this.i = true;
        }

        public void c() {
            if (this.i) {
                this.i = false;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerViewDragDropManager recyclerViewDragDropManager = this.h.get();
            if (recyclerViewDragDropManager != null && this.i) {
                recyclerViewDragDropManager.A();
                RecyclerView s = recyclerViewDragDropManager.s();
                if (s != null && this.i) {
                    ViewCompat.postOnAnimation(s, this);
                } else {
                    this.i = false;
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        public RecyclerView.ViewHolder f11907a;
        public int b;

        public void a() {
            this.f11907a = null;
            this.b = -1;
        }
    }

    public static boolean D(View view, View view2, Rect rect) {
        ViewParent parent;
        do {
            parent = view.getParent();
            if (!(parent instanceof ViewGroup)) {
                return false;
            }
            ((ViewGroup) parent).offsetDescendantRectToMyCoords(view, rect);
            view = (View) parent;
        } while (parent != view2);
        return true;
    }

    public static void O(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimation(viewHolder);
        }
    }

    public static void P(RecyclerView recyclerView) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
    }

    public static void R(RecyclerView recyclerView, int i, boolean z) {
        if (z) {
            recyclerView.scrollBy(0, i);
        } else {
            recyclerView.scrollBy(i, 0);
        }
    }

    public static boolean X() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static NestedScrollView g(View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof NestedScrollView) {
                return (NestedScrollView) parent;
            }
        }
        return null;
    }

    public static RecyclerView.ViewHolder i(d dVar, boolean z) {
        if (z) {
            return null;
        }
        RecyclerView.ViewHolder j = j(dVar);
        return j == null ? k(dVar) : j;
    }

    public static RecyclerView.ViewHolder j(d dVar) {
        return CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, dVar.d, dVar.e);
    }

    public static RecyclerView.ViewHolder k(d dVar) {
        float f2;
        float f3;
        int spanCount = CustomRecyclerViewUtils.getSpanCount(dVar.f11905a);
        int height = dVar.f11905a.getHeight();
        int width = dVar.f11905a.getWidth();
        int paddingLeft = dVar.i ? dVar.f11905a.getPaddingLeft() : 0;
        int paddingTop = !dVar.i ? dVar.f11905a.getPaddingTop() : 0;
        int paddingRight = ((width - paddingLeft) - (dVar.i ? dVar.f11905a.getPaddingRight() : 0)) / spanCount;
        int paddingBottom = ((height - paddingTop) - (!dVar.i ? dVar.f11905a.getPaddingBottom() : 0)) / spanCount;
        int i = dVar.d;
        int i2 = dVar.e;
        int start = dVar.k.getStart();
        int end = dVar.k.getEnd();
        if (dVar.i) {
            f2 = i - paddingLeft;
            f3 = paddingRight;
        } else {
            f2 = i2 - paddingTop;
            f3 = paddingBottom;
        }
        for (int min = Math.min(Math.max((int) (f2 / f3), 0), spanCount - 1); min >= 0; min--) {
            boolean z = dVar.i;
            RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, z ? (paddingRight * min) + paddingLeft + (paddingRight / 2) : i, !z ? (paddingBottom * min) + paddingTop + (paddingBottom / 2) : i2);
            if (findChildViewHolderUnderWithoutTranslation != null) {
                int adapterPosition = findChildViewHolderUnderWithoutTranslation.getAdapterPosition();
                if (adapterPosition == -1 || adapterPosition < start || adapterPosition > end) {
                    return null;
                }
                return findChildViewHolderUnderWithoutTranslation;
            }
        }
        return null;
    }

    public static RecyclerView.ViewHolder l(d dVar, boolean z) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        RecyclerView.ViewHolder viewHolder = dVar.c;
        if (viewHolder == null) {
            return null;
        }
        if (!dVar.l && !z) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int top = dVar.i ? dVar.c.itemView.getTop() : dVar.c.itemView.getLeft();
            int i = dVar.i ? dVar.g : dVar.f;
            if (i < top) {
                if (adapterPosition <= 0) {
                    return null;
                }
                findViewHolderForAdapterPosition = dVar.f11905a.findViewHolderForAdapterPosition(adapterPosition - 1);
            } else if (i <= top || adapterPosition >= dVar.f11905a.getAdapter().getItemCount() - 1) {
                return null;
            } else {
                findViewHolderForAdapterPosition = dVar.f11905a.findViewHolderForAdapterPosition(adapterPosition + 1);
            }
            return findViewHolderForAdapterPosition;
        }
        float f2 = viewHolder.itemView.getResources().getDisplayMetrics().density * 8.0f;
        float min = Math.min(dVar.b.width * 0.2f, f2);
        float min2 = Math.min(dVar.b.height * 0.2f, f2);
        DraggingItemInfo draggingItemInfo = dVar.b;
        float f3 = dVar.f + (draggingItemInfo.width * 0.5f);
        float f4 = dVar.g + (draggingItemInfo.height * 0.5f);
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, f3 - min, f4 - min2);
        if (findChildViewHolderUnderWithoutTranslation == CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, f3 + min, f4 + min2)) {
            return findChildViewHolderUnderWithoutTranslation;
        }
        return null;
    }

    public static RecyclerView.ViewHolder m(d dVar, boolean z) {
        RecyclerView.ViewHolder viewHolder;
        RecyclerView.ViewHolder viewHolder2;
        RecyclerView.ViewHolder viewHolder3;
        if (z || dVar.c == null) {
            return null;
        }
        int i = dVar.f;
        int i2 = i + 1;
        DraggingItemInfo draggingItemInfo = dVar.b;
        int i3 = draggingItemInfo.width;
        int i4 = ((i3 / 2) + i) - 1;
        int i5 = (i + i3) - 2;
        int i6 = dVar.g;
        int i7 = i6 + 1;
        int i8 = draggingItemInfo.height;
        int i9 = ((i8 / 2) + i6) - 1;
        int i10 = (i6 + i8) - 2;
        if (dVar.i) {
            float f2 = i9;
            viewHolder = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, i2, f2);
            viewHolder2 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, i5, f2);
            viewHolder3 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, i4, f2);
        } else {
            float f3 = i4;
            RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, f3, i7);
            RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation2 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, f3, i9);
            RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation3 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(dVar.f11905a, f3, i10);
            viewHolder = findChildViewHolderUnderWithoutTranslation;
            viewHolder2 = findChildViewHolderUnderWithoutTranslation2;
            viewHolder3 = findChildViewHolderUnderWithoutTranslation3;
        }
        if (viewHolder3 != dVar.c) {
            if (viewHolder3 == viewHolder || viewHolder3 == viewHolder2) {
                return viewHolder3;
            }
            return null;
        }
        return null;
    }

    public static Integer p(View view, boolean z) {
        if (view != null) {
            return Integer.valueOf(z ? view.getTop() : view.getLeft());
        }
        return null;
    }

    public void A() {
        RecyclerView recyclerView = this.f11902a;
        int orientation = CustomRecyclerViewUtils.getOrientation(recyclerView);
        boolean z = true;
        if (orientation != 0) {
            if (orientation != 1) {
                return;
            }
            z = false;
        }
        if (this.F != null) {
            B(recyclerView, z);
        } else {
            C(recyclerView, z);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0072, code lost:
        if (((r9 ? 8 : 2) & r4) == 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0074, code lost:
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
        if (((r9 ? 4 : 1) & r4) == 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void B(androidx.recyclerview.widget.RecyclerView r8, boolean r9) {
        /*
            r7 = this;
            androidx.core.widget.NestedScrollView r0 = r7.F
            int r1 = r0.getScrollX()
            int r2 = r0.getScrollY()
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            int r4 = r7.q()
            r3.right = r4
            r3.left = r4
            int r4 = r7.r()
            r3.bottom = r4
            r3.top = r4
            androidx.recyclerview.widget.RecyclerView r4 = r7.f11902a
            D(r4, r0, r3)
            int r4 = r3.left
            int r4 = r4 - r1
            int r1 = r3.top
            int r1 = r1 - r2
            if (r9 == 0) goto L31
            int r2 = r0.getWidth()
            goto L35
        L31:
            int r2 = r0.getHeight()
        L35:
            r3 = 1065353216(0x3f800000, float:1.0)
            float r2 = (float) r2
            float r3 = r3 / r2
            if (r9 == 0) goto L3c
            goto L3d
        L3c:
            r4 = r1
        L3d:
            float r1 = (float) r4
            float r1 = r1 * r3
            r2 = 1056964608(0x3f000000, float:0.5)
            float r1 = r1 - r2
            float r3 = java.lang.Math.abs(r1)
            r4 = 0
            r5 = 1050253722(0x3e99999a, float:0.3)
            float r3 = r2 - r3
            float r5 = r5 - r3
            float r3 = java.lang.Math.max(r4, r5)
            r4 = 1079334229(0x40555555, float:3.3333333)
            float r3 = r3 * r4
            int r4 = r7.S
            float r1 = java.lang.Math.signum(r1)
            int r1 = (int) r1
            r5 = 1103626240(0x41c80000, float:25.0)
            float r6 = r7.a0
            float r6 = r6 * r5
            float r5 = r7.h
            float r6 = r6 * r5
            float r6 = r6 * r3
            float r6 = r6 + r2
            int r2 = (int) r6
            int r1 = r1 * r2
            r2 = 0
            if (r1 <= 0) goto L76
            if (r9 == 0) goto L70
            r3 = 8
            goto L71
        L70:
            r3 = 2
        L71:
            r3 = r3 & r4
            if (r3 != 0) goto L81
        L74:
            r1 = r2
            goto L81
        L76:
            if (r1 >= 0) goto L81
            if (r9 == 0) goto L7c
            r3 = 4
            goto L7d
        L7c:
            r3 = 1
        L7d:
            r3 = r3 & r4
            if (r3 != 0) goto L81
            goto L74
        L81:
            if (r1 == 0) goto L8f
            r7.Q(r8)
            if (r9 == 0) goto L8c
            r0.scrollBy(r1, r2)
            goto L8f
        L8c:
            r0.scrollBy(r2, r1)
        L8f:
            com.h6ah4i.android.widget.advrecyclerview.draggable.d r9 = r7.D
            int r0 = r7.q()
            int r1 = r7.r()
            boolean r9 = r9.F(r0, r1, r2)
            if (r9 == 0) goto Lb8
            com.h6ah4i.android.widget.advrecyclerview.draggable.g r9 = r7.E
            if (r9 == 0) goto Lb2
            com.h6ah4i.android.widget.advrecyclerview.draggable.d r0 = r7.D
            int r0 = r0.n()
            com.h6ah4i.android.widget.advrecyclerview.draggable.d r1 = r7.D
            int r1 = r1.o()
            r9.n(r0, r1)
        Lb2:
            r7.d(r8)
            r7.G()
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.B(androidx.recyclerview.widget.RecyclerView, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0151, code lost:
        r1 = r17.h;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0153, code lost:
        r5 = r1 * 0.005f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0099, code lost:
        if ((r7 & (r19 ? 8 : 2)) == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009b, code lost:
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
        if ((r7 & (r19 ? 4 : 1)) == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x013e, code lost:
        r1 = -r17.h;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void C(androidx.recyclerview.widget.RecyclerView r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.C(androidx.recyclerview.widget.RecyclerView, boolean):void");
    }

    public final void E() {
        Log.i("ARVDragDropManager", "a view holder object which is bound to currently dragging item is recycled");
        this.B = null;
        this.D.u();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000d, code lost:
        if (r0 != 3) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean F(androidx.recyclerview.widget.RecyclerView r4, android.view.MotionEvent r5) {
        /*
            r3 = this;
            int r0 = r5.getActionMasked()
            r1 = 1
            if (r0 == 0) goto L26
            if (r0 == r1) goto L21
            r2 = 2
            if (r0 == r2) goto L10
            r4 = 3
            if (r0 == r4) goto L21
            goto L31
        L10:
            boolean r0 = r3.isDragging()
            if (r0 == 0) goto L1a
            r3.v(r4, r5)
            goto L32
        L1a:
            boolean r4 = r3.w(r4, r5)
            if (r4 == 0) goto L31
            goto L32
        L21:
            boolean r1 = r3.x(r0, r1)
            goto L32
        L26:
            boolean r0 = r3.isDragging()
            if (r0 != 0) goto L31
            boolean r1 = r3.u(r4, r5)
            goto L32
        L31:
            r1 = 0
        L32:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.F(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    public final void G() {
        if (this.X == null) {
            return;
        }
        this.X.onItemDragMoveDistanceUpdated(this.Q + this.D.l(), this.R + this.D.m());
    }

    public void H(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == this.B) {
            E();
            return;
        }
        com.h6ah4i.android.widget.advrecyclerview.draggable.g gVar = this.E;
        if (gVar != null) {
            gVar.j(viewHolder);
        }
    }

    public void I(RecyclerView.ViewHolder viewHolder) {
        if (this.B != null) {
            E();
        }
        this.B = viewHolder;
        this.D.A(viewHolder);
    }

    public void J(boolean z) {
        if (z) {
            b(true);
        }
    }

    public void K(RecyclerView recyclerView, int i) {
        if (i == 1) {
            b(true);
        }
    }

    public void L(RecyclerView recyclerView, int i, int i2) {
        if (this.s) {
            this.t = i;
            this.u = i2;
        } else if (isDragging()) {
            ViewCompat.postOnAnimationDelayed(this.f11902a, this.f0, 500L);
        }
    }

    public void M(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (isDragging()) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    v(recyclerView, motionEvent);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            x(actionMasked, true);
        }
    }

    public final void N(RecyclerView recyclerView, @Nullable RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2, Rect rect, int i, int i2) {
        int decoratedMeasuredWidth;
        int i3;
        OnItemDragEventListener onItemDragEventListener = this.X;
        if (onItemDragEventListener != null) {
            onItemDragEventListener.onItemDragPositionChanged(i, i2);
        }
        RecyclerView.LayoutManager layoutManager = this.f11902a.getLayoutManager();
        int layoutType = CustomRecyclerViewUtils.getLayoutType(this.f11902a);
        boolean z = CustomRecyclerViewUtils.extractOrientation(layoutType) == 1;
        int findFirstVisibleItemPosition = CustomRecyclerViewUtils.findFirstVisibleItemPosition(this.f11902a, false);
        View view = viewHolder != null ? viewHolder.itemView : null;
        View view2 = viewHolder2.itemView;
        View findViewByPosition = CustomRecyclerViewUtils.findViewByPosition(layoutManager, findFirstVisibleItemPosition);
        int layoutPosition = viewHolder != null ? viewHolder.getLayoutPosition() : -1;
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        Integer p = p(view, z);
        Integer p2 = p(view2, z);
        Integer p3 = p(findViewByPosition, z);
        this.A.j(i, i2, layoutType);
        if (findFirstVisibleItemPosition == layoutPosition && p3 != null && p2 != null) {
            R(recyclerView, -(p2.intValue() - p3.intValue()), z);
            P(recyclerView);
        } else if (findFirstVisibleItemPosition != layoutPosition2 || view == null || p == null || p.equals(p2)) {
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (z) {
                decoratedMeasuredWidth = layoutManager.getDecoratedMeasuredHeight(view) + marginLayoutParams.topMargin;
                i3 = marginLayoutParams.bottomMargin;
            } else {
                decoratedMeasuredWidth = layoutManager.getDecoratedMeasuredWidth(view) + marginLayoutParams.leftMargin;
                i3 = marginLayoutParams.rightMargin;
            }
            R(recyclerView, -(decoratedMeasuredWidth + i3), z);
            P(recyclerView);
        }
    }

    public final void Q(RecyclerView recyclerView) {
        if (this.E != null) {
            P(recyclerView);
        }
    }

    public final int S(int i) {
        this.t = 0;
        this.s = true;
        this.f11902a.scrollBy(i, 0);
        this.s = false;
        return this.t;
    }

    public final int T(int i) {
        this.u = 0;
        this.s = true;
        this.f11902a.scrollBy(0, i);
        this.s = false;
        return this.u;
    }

    public final void U(RecyclerView recyclerView, MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange, AdapterPath adapterPath, int i, Object obj) {
        O(recyclerView, viewHolder);
        this.W.a();
        this.C = new DraggingItemInfo(recyclerView, viewHolder, this.I, this.J);
        this.B = viewHolder;
        this.U = itemDraggableRange;
        this.V = f(adapterPath, itemDraggableRange);
        NestedScrollView g2 = g(this.f11902a);
        if (g2 != null && !this.f11902a.isNestedScrollingEnabled()) {
            this.F = g2;
        } else {
            this.F = null;
        }
        this.T = recyclerView.getOverScrollMode();
        recyclerView.setOverScrollMode(2);
        this.I = (int) (motionEvent.getX() + 0.5f);
        this.J = (int) (motionEvent.getY() + 0.5f);
        NestedScrollView nestedScrollView = this.F;
        this.G = nestedScrollView != null ? nestedScrollView.getScrollX() : 0;
        NestedScrollView nestedScrollView2 = this.F;
        this.H = nestedScrollView2 != null ? nestedScrollView2.getScrollY() : 0;
        int i2 = this.J;
        this.P = i2;
        this.N = i2;
        this.L = i2;
        int i3 = this.I;
        this.O = i3;
        this.M = i3;
        this.K = i3;
        this.S = 0;
        this.b0 = this.y;
        this.c0 = obj;
        this.f11902a.getParent().requestDisallowInterceptTouchEvent(true);
        V();
        this.A.o(this.C, viewHolder, this.U, i, this.b0);
        this.A.onBindViewHolder(viewHolder, i);
        com.h6ah4i.android.widget.advrecyclerview.draggable.d dVar = new com.h6ah4i.android.widget.advrecyclerview.draggable.d(this.f11902a, viewHolder, this.V);
        this.D = dVar;
        dVar.C(this.g);
        this.D.D(this.z);
        this.D.E(this.C, this.I, this.J);
        int layoutType = CustomRecyclerViewUtils.getLayoutType(this.f11902a);
        if (!this.r && CustomRecyclerViewUtils.isLinearLayout(layoutType)) {
            com.h6ah4i.android.widget.advrecyclerview.draggable.g gVar = new com.h6ah4i.android.widget.advrecyclerview.draggable.g(this.f11902a, viewHolder, this.C);
            this.E = gVar;
            gVar.l(this.b);
            this.E.m();
            this.E.n(this.D.n(), this.D.o());
        }
        com.h6ah4i.android.widget.advrecyclerview.draggable.b bVar = this.f;
        if (bVar != null) {
            bVar.j();
        }
        this.A.l();
        OnItemDragEventListener onItemDragEventListener = this.X;
        if (onItemDragEventListener != null) {
            onItemDragEventListener.onItemDragStarted(this.A.f());
            this.X.onItemDragMoveDistanceUpdated(0, 0);
        }
    }

    public final void V() {
        this.c.b();
    }

    public final void W() {
        f fVar = this.c;
        if (fVar != null) {
            fVar.c();
        }
    }

    public final void Y(RecyclerView recyclerView, int i, @Nullable RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
        int min;
        int min2;
        Rect layoutMargins = CustomRecyclerViewUtils.getLayoutMargins(viewHolder2.itemView, this.v);
        int t = t(viewHolder2);
        int abs = Math.abs(i - t);
        if (i == -1 || t == -1 || ItemIdComposer.extractWrappedIdPart(this.A.getItemId(i)) != ItemIdComposer.extractWrappedIdPart(this.C.id)) {
            return;
        }
        boolean z = false;
        boolean z2 = CustomRecyclerViewUtils.isLinearLayout(CustomRecyclerViewUtils.getLayoutType(recyclerView)) && !this.r;
        if (abs != 0) {
            if (abs == 1 && viewHolder != null && z2) {
                View view = viewHolder.itemView;
                View view2 = viewHolder2.itemView;
                Rect rect = this.C.margins;
                if (this.Y) {
                    float min3 = Math.min(view.getLeft() - rect.left, view2.getLeft() - layoutMargins.left) + ((Math.max(view.getRight() + rect.right, view2.getRight() + layoutMargins.right) - min2) * 0.5f);
                    int q = q();
                    DraggingItemInfo draggingItemInfo = this.C;
                    float f2 = (q - draggingItemInfo.grabbedPositionX) + (draggingItemInfo.width * 0.5f);
                    if (t >= i ? f2 > min3 : f2 < min3) {
                        z = true;
                    }
                }
                if (!z && this.Z) {
                    float min4 = Math.min(view.getTop() - rect.top, view2.getTop() - layoutMargins.top) + ((Math.max(view.getBottom() + rect.bottom, view2.getBottom() + layoutMargins.bottom) - min) * 0.5f);
                    int r = r();
                    DraggingItemInfo draggingItemInfo2 = this.C;
                    float f3 = (r - draggingItemInfo2.grabbedPositionY) + (draggingItemInfo2.height * 0.5f);
                    if (t >= i) {
                    }
                }
            }
            z = true;
        }
        if (z) {
            N(recyclerView, viewHolder, viewHolder2, layoutMargins, i, t);
        }
    }

    public final void Z() {
        int orientation = CustomRecyclerViewUtils.getOrientation(this.f11902a);
        if (orientation == 0) {
            int q = q();
            int i = this.K;
            int i2 = this.M;
            int i3 = i - i2;
            int i4 = this.j;
            if (i3 > i4 || this.O - q > i4) {
                this.S |= 4;
            }
            if (this.O - i > i4 || q - i2 > i4) {
                this.S |= 8;
            }
        } else if (orientation != 1) {
        } else {
            int r = r();
            int i5 = this.L;
            int i6 = this.N;
            int i7 = i5 - i6;
            int i8 = this.j;
            if (i7 > i8 || this.P - r > i8) {
                this.S = 1 | this.S;
            }
            if (this.P - i5 > i8 || r - i6 > i8) {
                this.S |= 2;
            }
        }
    }

    public final boolean a(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        int adapterPosition = viewHolder.getAdapterPosition();
        int unwrapPosition = WrapperAdapterUtils.unwrapPosition(this.f11902a.getAdapter(), this.A, (Object) null, adapterPosition);
        if (unwrapPosition == -1) {
            return false;
        }
        View view = viewHolder.itemView;
        int translationY = (int) (view.getTranslationY() + 0.5f);
        return this.A.b(viewHolder, unwrapPosition, i - (view.getLeft() + ((int) (view.getTranslationX() + 0.5f))), i2 - (view.getTop() + translationY)) && viewHolder.getAdapterPosition() == adapterPosition;
    }

    public final void a0(float f2) {
        if (f2 == 0.0f) {
            this.f.i();
        } else if (f2 < 0.0f) {
            this.f.g(f2);
        } else {
            this.f.h(f2);
        }
    }

    public void attachRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!isReleased()) {
            if (this.f11902a == null) {
                this.f11902a = recyclerView;
                recyclerView.addOnScrollListener(this.e);
                this.f11902a.addOnItemTouchListener(this.d);
                this.h = this.f11902a.getResources().getDisplayMetrics().density;
                int scaledTouchSlop = ViewConfiguration.get(this.f11902a.getContext()).getScaledTouchSlop();
                this.i = scaledTouchSlop;
                this.j = (int) ((scaledTouchSlop * 1.5f) + 0.5f);
                this.W = new e(this);
                if (X()) {
                    int orientation = CustomRecyclerViewUtils.getOrientation(this.f11902a);
                    if (orientation == 0) {
                        this.f = new com.h6ah4i.android.widget.advrecyclerview.draggable.f(this.f11902a);
                    } else if (orientation == 1) {
                        this.f = new h(this.f11902a);
                    }
                    com.h6ah4i.android.widget.advrecyclerview.draggable.b bVar = this.f;
                    if (bVar != null) {
                        bVar.k();
                        return;
                    }
                    return;
                }
                return;
            }
            throw new IllegalStateException("RecyclerView instance has already been set");
        }
        throw new IllegalStateException("Accessing released object");
    }

    public void b(boolean z) {
        x(3, false);
        if (z) {
            n(false);
        } else if (isDragging()) {
            this.W.f();
        }
    }

    public final void b0(ItemDraggableRange itemDraggableRange, int i) {
        int max = Math.max(0, this.A.getItemCount() - 1);
        if (itemDraggableRange.getStart() <= itemDraggableRange.getEnd()) {
            if (itemDraggableRange.getStart() >= 0) {
                if (itemDraggableRange.getEnd() <= max) {
                    if (itemDraggableRange.checkInRange(i)) {
                        return;
                    }
                    throw new IllegalStateException("Invalid wrappedAdapterRange specified --- does not contain drag target item (wrappedAdapterRange = " + itemDraggableRange + ", position = " + i + ")");
                }
                throw new IllegalStateException("Invalid wrappedAdapterRange specified --- end >= count (wrappedAdapterRange = " + itemDraggableRange + ")");
            }
            throw new IllegalStateException("Invalid wrappedAdapterRange specified --- start < 0 (wrappedAdapterRange = " + itemDraggableRange + ")");
        }
        throw new IllegalStateException("Invalid wrappedAdapterRange specified --- start > wrappedAdapterRange (wrappedAdapterRange = " + itemDraggableRange + ")");
    }

    public final boolean c(RecyclerView recyclerView, MotionEvent motionEvent, boolean z) {
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation;
        if (this.C != null) {
            return false;
        }
        int x = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        this.I = x;
        this.J = y;
        if (this.m == -1) {
            return false;
        }
        if ((!z || ((this.Y && Math.abs(x - this.k) > this.i) || (this.Z && Math.abs(y - this.l) > this.i))) && (findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(recyclerView, this.k, this.l)) != null && a(findChildViewHolderUnderWithoutTranslation, x, y)) {
            RecyclerView.Adapter adapter = this.f11902a.getAdapter();
            AdapterPath adapterPath = new AdapterPath();
            int unwrapPosition = WrapperAdapterUtils.unwrapPosition(adapter, this.A, null, findChildViewHolderUnderWithoutTranslation.getAdapterPosition(), adapterPath);
            ItemDraggableRange g2 = this.A.g(findChildViewHolderUnderWithoutTranslation, unwrapPosition);
            if (g2 == null) {
                g2 = new ItemDraggableRange(0, Math.max(0, this.A.getItemCount() - 1));
            }
            ItemDraggableRange itemDraggableRange = g2;
            b0(itemDraggableRange, unwrapPosition);
            U(recyclerView, motionEvent, findChildViewHolderUnderWithoutTranslation, itemDraggableRange, adapterPath, unwrapPosition, adapterPath.lastSegment().tag);
            return true;
        }
        return false;
    }

    public void cancelDrag() {
        b(false);
    }

    @NonNull
    public RecyclerView.Adapter createWrappedAdapter(@NonNull RecyclerView.Adapter adapter) {
        if (adapter.hasStableIds()) {
            if (this.A == null) {
                com.h6ah4i.android.widget.advrecyclerview.draggable.c cVar = new com.h6ah4i.android.widget.advrecyclerview.draggable.c(this, adapter);
                this.A = cVar;
                return cVar;
            }
            throw new IllegalStateException("already have a wrapped adapter");
        }
        throw new IllegalArgumentException("The passed adapter does not support stable IDs");
    }

    public void d(RecyclerView recyclerView) {
        int i;
        RecyclerView.ViewHolder viewHolder = this.B;
        d dVar = this.e0;
        dVar.b(recyclerView, viewHolder, this.C, q(), r(), this.U, this.V, this.r);
        int f2 = this.A.f();
        int e2 = this.A.e();
        boolean z = false;
        g h = h(this.d0, dVar, false);
        int i2 = h.b;
        if (i2 != -1) {
            z = !this.r;
            if (!z) {
                z = this.A.a(f2, i2);
            }
            if (!z && (i = (h = h(this.d0, dVar, true)).b) != -1) {
                z = this.A.a(f2, i);
            }
        }
        if (z && h.f11907a == null) {
            throw new IllegalStateException("bug check");
        }
        if (z) {
            Y(recyclerView, e2, viewHolder, h.f11907a);
        }
        com.h6ah4i.android.widget.advrecyclerview.draggable.g gVar = this.E;
        if (gVar != null) {
            gVar.k(z ? h.f11907a : null);
        }
        if (z) {
            this.W.g();
        }
        h.a();
        dVar.a();
    }

    public final boolean e(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof DraggableItemViewHolder) {
            int t = t(viewHolder);
            return t >= 0 && t < this.A.getItemCount();
        }
        return false;
    }

    public final ItemDraggableRange f(AdapterPath adapterPath, ItemDraggableRange itemDraggableRange) {
        RecyclerView.Adapter adapter = this.f11902a.getAdapter();
        return new ItemDraggableRange(WrapperAdapterUtils.wrapPosition(adapterPath, this.A, adapter, itemDraggableRange.getStart()), WrapperAdapterUtils.wrapPosition(adapterPath, this.A, adapter, itemDraggableRange.getEnd()));
    }

    public float getDragEdgeScrollSpeed() {
        return this.a0;
    }

    @Nullable
    public Interpolator getDragStartItemAlphaAnimationInterpolator() {
        return this.z.g;
    }

    public int getDragStartItemAnimationDuration() {
        return this.z.f11911a;
    }

    @Nullable
    public Interpolator getDragStartItemRotationAnimationInterpolator() {
        return this.z.f;
    }

    @Nullable
    public Interpolator getDragStartItemScaleAnimationInterpolator() {
        return this.z.e;
    }

    public float getDraggingItemAlpha() {
        return this.z.d;
    }

    public float getDraggingItemRotation() {
        return this.z.c;
    }

    public float getDraggingItemScale() {
        return this.z.b;
    }

    public int getItemMoveMode() {
        return this.y;
    }

    public int getItemSettleBackIntoPlaceAnimationDuration() {
        return this.w;
    }

    @Nullable
    public Interpolator getItemSettleBackIntoPlaceAnimationInterpolator() {
        return this.x;
    }

    @Nullable
    public OnItemDragEventListener getOnItemDragEventListener() {
        return this.X;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.g h(com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.g r8, com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.d r9, boolean r10) {
        /*
            r7 = this;
            r8.a()
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r9.c
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L1d
            int r0 = r7.t(r0)
            if (r0 == r1) goto L30
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r9.c
            long r3 = r0.getItemId()
            com.h6ah4i.android.widget.advrecyclerview.draggable.DraggingItemInfo r0 = r9.b
            long r5 = r0.id
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L30
        L1d:
            int r0 = r9.h
            if (r0 == 0) goto L3c
            r3 = 1
            if (r0 == r3) goto L3c
            r3 = 2
            if (r0 == r3) goto L37
            r3 = 3
            if (r0 == r3) goto L37
            r3 = 4
            if (r0 == r3) goto L32
            r3 = 5
            if (r0 == r3) goto L32
        L30:
            r10 = r2
            goto L40
        L32:
            androidx.recyclerview.widget.RecyclerView$ViewHolder r10 = m(r9, r10)
            goto L40
        L37:
            androidx.recyclerview.widget.RecyclerView$ViewHolder r10 = i(r9, r10)
            goto L40
        L3c:
            androidx.recyclerview.widget.RecyclerView$ViewHolder r10 = l(r9, r10)
        L40:
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r9.c
            if (r10 != r0) goto L45
            r10 = r2
        L45:
            int r0 = r7.t(r10)
            if (r10 == 0) goto L56
            com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange r9 = r9.j
            if (r9 == 0) goto L56
            boolean r9 = r9.checkInRange(r0)
            if (r9 != 0) goto L56
            goto L57
        L56:
            r2 = r10
        L57:
            r8.f11907a = r2
            if (r2 == 0) goto L5c
            r1 = r0
        L5c:
            r8.b = r1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.h(com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager$g, com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager$d, boolean):com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager$g");
    }

    public boolean isCheckCanDropEnabled() {
        return this.r;
    }

    public boolean isDragging() {
        return (this.C == null || this.W.b()) ? false : true;
    }

    public boolean isInitiateOnLongPressEnabled() {
        return this.n;
    }

    public boolean isInitiateOnMoveEnabled() {
        return this.p;
    }

    public boolean isInitiateOnTouchEnabled() {
        return this.o;
    }

    public boolean isReleased() {
        return this.d == null;
    }

    public final void n(boolean z) {
        int i;
        if (isDragging()) {
            e eVar = this.W;
            if (eVar != null) {
                eVar.d();
                this.W.e();
            }
            RecyclerView recyclerView = this.f11902a;
            if (recyclerView != null && this.B != null) {
                recyclerView.setOverScrollMode(this.T);
            }
            com.h6ah4i.android.widget.advrecyclerview.draggable.d dVar = this.D;
            if (dVar != null) {
                dVar.e(this.w);
                this.D.f(this.x);
                this.D.k(true);
            }
            com.h6ah4i.android.widget.advrecyclerview.draggable.g gVar = this.E;
            if (gVar != null) {
                gVar.e(this.w);
                this.D.f(this.x);
                this.E.i(true);
            }
            com.h6ah4i.android.widget.advrecyclerview.draggable.b bVar = this.f;
            if (bVar != null) {
                bVar.i();
            }
            W();
            RecyclerView recyclerView2 = this.f11902a;
            if (recyclerView2 != null && recyclerView2.getParent() != null) {
                this.f11902a.getParent().requestDisallowInterceptTouchEvent(false);
            }
            RecyclerView recyclerView3 = this.f11902a;
            if (recyclerView3 != null) {
                recyclerView3.invalidate();
            }
            this.U = null;
            this.V = null;
            this.D = null;
            this.E = null;
            this.B = null;
            this.C = null;
            this.c0 = null;
            this.F = null;
            this.I = 0;
            this.J = 0;
            this.G = 0;
            this.H = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.Y = false;
            this.Z = false;
            com.h6ah4i.android.widget.advrecyclerview.draggable.c cVar = this.A;
            int i2 = -1;
            if (cVar != null) {
                i2 = cVar.f();
                i = this.A.e();
                this.A.k(i2, i, z);
            } else {
                i = -1;
            }
            OnItemDragEventListener onItemDragEventListener = this.X;
            if (onItemDragEventListener != null) {
                onItemDragEventListener.onItemDragFinished(i2, i, z);
            }
        }
    }

    public RecyclerView.ViewHolder o() {
        return this.B;
    }

    public final int q() {
        int i = this.I;
        NestedScrollView nestedScrollView = this.F;
        return nestedScrollView != null ? i + (nestedScrollView.getScrollX() - this.G) : i;
    }

    public final int r() {
        int i = this.J;
        NestedScrollView nestedScrollView = this.F;
        return nestedScrollView != null ? i + (nestedScrollView.getScrollY() - this.H) : i;
    }

    public void release() {
        RecyclerView.OnScrollListener onScrollListener;
        RecyclerView.OnItemTouchListener onItemTouchListener;
        b(true);
        e eVar = this.W;
        if (eVar != null) {
            eVar.c();
            this.W = null;
        }
        com.h6ah4i.android.widget.advrecyclerview.draggable.b bVar = this.f;
        if (bVar != null) {
            bVar.d();
            this.f = null;
        }
        RecyclerView recyclerView = this.f11902a;
        if (recyclerView != null && (onItemTouchListener = this.d) != null) {
            recyclerView.removeOnItemTouchListener(onItemTouchListener);
        }
        this.d = null;
        RecyclerView recyclerView2 = this.f11902a;
        if (recyclerView2 != null && (onScrollListener = this.e) != null) {
            recyclerView2.removeOnScrollListener(onScrollListener);
        }
        this.e = null;
        f fVar = this.c;
        if (fVar != null) {
            fVar.a();
            this.c = null;
        }
        this.A = null;
        this.f11902a = null;
        this.b = null;
    }

    public RecyclerView s() {
        return this.f11902a;
    }

    public void setCheckCanDropEnabled(boolean z) {
        this.r = z;
    }

    public void setDragEdgeScrollSpeed(float f2) {
        this.a0 = Math.min(Math.max(f2, 0.0f), 2.0f);
    }

    public void setDragStartItemAlphaAnimationInterpolator(Interpolator interpolator) {
        this.z.g = interpolator;
    }

    public void setDragStartItemAnimationDuration(int i) {
        this.z.f11911a = i;
    }

    public void setDragStartItemRotationAnimationInterpolator(Interpolator interpolator) {
        this.z.f = interpolator;
    }

    public void setDragStartItemScaleAnimationInterpolator(Interpolator interpolator) {
        this.z.e = interpolator;
    }

    public void setDraggingItemAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.z.d = f2;
    }

    public void setDraggingItemRotation(float f2) {
        this.z.c = f2;
    }

    public void setDraggingItemScale(float f2) {
        this.z.b = f2;
    }

    public void setDraggingItemShadowDrawable(@Nullable NinePatchDrawable ninePatchDrawable) {
        this.g = ninePatchDrawable;
    }

    public void setInitiateOnLongPress(boolean z) {
        this.n = z;
    }

    public void setInitiateOnMove(boolean z) {
        this.p = z;
    }

    public void setInitiateOnTouch(boolean z) {
        this.o = z;
    }

    public void setItemMoveMode(int i) {
        this.y = i;
    }

    public void setItemSettleBackIntoPlaceAnimationDuration(int i) {
        this.w = i;
    }

    public void setItemSettleBackIntoPlaceAnimationInterpolator(@Nullable Interpolator interpolator) {
        this.x = interpolator;
    }

    public void setLongPressTimeout(int i) {
        this.q = i;
    }

    public void setOnItemDragEventListener(@Nullable OnItemDragEventListener onItemDragEventListener) {
        this.X = onItemDragEventListener;
    }

    public void setSwapTargetTranslationInterpolator(@Nullable Interpolator interpolator) {
        this.b = interpolator;
    }

    public final int t(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            return -1;
        }
        return WrapperAdapterUtils.unwrapPosition(this.f11902a.getAdapter(), this.A, this.c0, viewHolder.getAdapterPosition());
    }

    public final boolean u(RecyclerView recyclerView, MotionEvent motionEvent) {
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(recyclerView, motionEvent.getX(), motionEvent.getY());
        if (e(recyclerView, findChildViewHolderUnderWithoutTranslation)) {
            int x = (int) (motionEvent.getX() + 0.5f);
            int y = (int) (motionEvent.getY() + 0.5f);
            if (a(findChildViewHolderUnderWithoutTranslation, x, y)) {
                int orientation = CustomRecyclerViewUtils.getOrientation(this.f11902a);
                int spanCount = CustomRecyclerViewUtils.getSpanCount(this.f11902a);
                this.I = x;
                this.k = x;
                this.J = y;
                this.l = y;
                this.m = findChildViewHolderUnderWithoutTranslation.getItemId();
                boolean z = true;
                this.Y = orientation == 0 || (orientation == 1 && spanCount > 1);
                if (orientation != 1 && (orientation != 0 || spanCount <= 1)) {
                    z = false;
                }
                this.Z = z;
                if (this.o) {
                    return c(recyclerView, motionEvent, false);
                }
                if (this.n) {
                    this.W.h(motionEvent, this.q);
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final void v(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.I = (int) (motionEvent.getX() + 0.5f);
        this.J = (int) (motionEvent.getY() + 0.5f);
        NestedScrollView nestedScrollView = this.F;
        this.G = nestedScrollView != null ? nestedScrollView.getScrollX() : 0;
        NestedScrollView nestedScrollView2 = this.F;
        this.H = nestedScrollView2 != null ? nestedScrollView2.getScrollY() : 0;
        this.M = Math.min(this.M, this.I);
        this.N = Math.min(this.N, this.J);
        this.O = Math.max(this.O, this.I);
        this.P = Math.max(this.P, this.J);
        Z();
        if (this.D.F(q(), r(), false)) {
            com.h6ah4i.android.widget.advrecyclerview.draggable.g gVar = this.E;
            if (gVar != null) {
                gVar.n(this.D.n(), this.D.o());
            }
            d(recyclerView);
            G();
        }
    }

    public final boolean w(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.p) {
            return c(recyclerView, motionEvent, true);
        }
        return false;
    }

    public final boolean x(int i, boolean z) {
        boolean z2 = i == 1;
        boolean isDragging = isDragging();
        e eVar = this.W;
        if (eVar != null) {
            eVar.a();
        }
        this.k = 0;
        this.l = 0;
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.m = -1L;
        this.Y = false;
        this.Z = false;
        if (z && isDragging()) {
            n(z2);
        }
        return isDragging;
    }

    public void y() {
        RecyclerView.ViewHolder findViewHolderForItemId = this.f11902a.findViewHolderForItemId(this.C.id);
        if (findViewHolderForItemId == null) {
            return;
        }
        int width = findViewHolderForItemId.itemView.getWidth();
        int height = findViewHolderForItemId.itemView.getHeight();
        DraggingItemInfo draggingItemInfo = this.C;
        if (width == draggingItemInfo.width && height == draggingItemInfo.height) {
            return;
        }
        DraggingItemInfo createWithNewView = DraggingItemInfo.createWithNewView(draggingItemInfo, findViewHolderForItemId);
        this.C = createWithNewView;
        this.D.H(createWithNewView, findViewHolderForItemId);
    }

    public void z(MotionEvent motionEvent) {
        if (this.n) {
            c(this.f11902a, motionEvent, false);
        }
    }

    public Interpolator setSwapTargetTranslationInterpolator() {
        return this.b;
    }
}
