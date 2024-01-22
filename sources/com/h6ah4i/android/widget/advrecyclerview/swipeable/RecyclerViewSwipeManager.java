package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemIdComposer;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
/* loaded from: classes11.dex */
public class RecyclerViewSwipeManager implements SwipeableItemConstants {
    public OnItemSwipeEventListener A;
    public b B;
    public RecyclerView b;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public boolean m;
    public ItemSlidingAnimator n;
    public e<RecyclerView.ViewHolder> o;
    public RecyclerView.ViewHolder p;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public g z;
    public long c = 300;
    public long d = 200;
    public long e = 200;
    public long l = -1;
    public int q = -1;
    public long r = -1;
    public final Rect s = new Rect();

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.OnItemTouchListener f11921a = new a();
    public VelocityTracker y = VelocityTracker.obtain();
    public int C = ViewConfiguration.getLongPressTimeout();

    /* loaded from: classes11.dex */
    public interface OnItemSwipeEventListener {
        void onItemSwipeFinished(int i, int i2, int i3);

        void onItemSwipeStarted(int i);
    }

    /* loaded from: classes11.dex */
    public class a implements RecyclerView.OnItemTouchListener {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            return RecyclerViewSwipeManager.this.v(recyclerView, motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            RecyclerViewSwipeManager.this.w(z);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            RecyclerViewSwipeManager.this.x(recyclerView, motionEvent);
        }
    }

    /* loaded from: classes11.dex */
    public static class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public RecyclerViewSwipeManager f11923a;
        public MotionEvent b;

        public b(RecyclerViewSwipeManager recyclerViewSwipeManager) {
            this.f11923a = recyclerViewSwipeManager;
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
            this.f11923a = null;
        }

        public void d() {
            removeMessages(2);
        }

        public void e() {
            if (b()) {
                return;
            }
            sendEmptyMessage(2);
        }

        public void f(MotionEvent motionEvent, int i) {
            a();
            this.b = MotionEvent.obtain(motionEvent);
            sendEmptyMessageAtTime(1, motionEvent.getDownTime() + i);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                this.f11923a.s(this.b);
            } else if (i != 2) {
            } else {
                this.f11923a.d(true);
            }
        }
    }

    public static void E(int i, int i2) {
        if ((i2 != 2 && i2 != 1) || i == 2 || i == 3 || i == 4 || i == 5) {
            return;
        }
        throw new IllegalStateException("Unexpected after reaction has been requested: result = " + i + ", afterReaction = " + i2);
    }

    public static float a(SwipeableItemViewHolder swipeableItemViewHolder, boolean z, float f, boolean z2, boolean z3) {
        if (z2 ^ z3) {
            if (f == 0.0f || u(f)) {
                return f;
            }
            View b2 = f.b(swipeableItemViewHolder);
            float width = z ? b2.getWidth() : b2.getHeight();
            if (z3) {
                width = width != 0.0f ? 1.0f / width : 0.0f;
            }
            return f * width;
        }
        return f;
    }

    public static int f(float f, boolean z) {
        return z ? f < 0.0f ? 1 : 3 : f < 0.0f ? 2 : 4;
    }

    public static int h(@Nullable RecyclerView.Adapter adapter, long j, int i) {
        if (adapter == null) {
            return -1;
        }
        int itemCount = adapter.getItemCount();
        if (i < 0 || i >= itemCount || adapter.getItemId(i) != j) {
            for (int i2 = 0; i2 < itemCount; i2++) {
                if (adapter.getItemId(i2) == j) {
                    return i2;
                }
            }
            return -1;
        }
        return i;
    }

    public static boolean u(float f) {
        return f == -65536.0f || f == 65536.0f || f == -65537.0f || f == 65537.0f;
    }

    public static int y(int i) {
        if (i != 3) {
            if (i != 4) {
                return i != 5 ? 0 : 3;
            }
            return 2;
        }
        return 1;
    }

    public final void A(MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder, int i) {
        this.B.a();
        this.p = viewHolder;
        this.q = i;
        this.r = this.o.getItemId(i);
        this.v = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        this.w = y;
        this.t = this.v;
        this.u = y;
        this.l = -1L;
        CustomRecyclerViewUtils.getLayoutMargins(viewHolder.itemView, this.s);
        g gVar = new g(this, this.p, this.x, this.m);
        this.z = gVar;
        gVar.d();
        this.y.clear();
        this.y.addMovement(motionEvent);
        this.b.getParent().requestDisallowInterceptTouchEvent(true);
        OnItemSwipeEventListener onItemSwipeEventListener = this.A;
        if (onItemSwipeEventListener != null) {
            onItemSwipeEventListener.onItemSwipeStarted(i);
        }
        this.o.j(this, viewHolder, i, this.r);
    }

    public boolean B() {
        return this.m;
    }

    public int C() {
        return D(this.q);
    }

    public int D(int i) {
        int h = h(this.o, this.r, i);
        this.q = h;
        return h;
    }

    public void attachRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!isReleased()) {
            if (this.b == null) {
                int orientation = CustomRecyclerViewUtils.getOrientation(recyclerView);
                if (orientation != -1) {
                    this.b = recyclerView;
                    recyclerView.addOnItemTouchListener(this.f11921a);
                    ViewConfiguration viewConfiguration = ViewConfiguration.get(recyclerView.getContext());
                    this.f = viewConfiguration.getScaledTouchSlop();
                    this.g = viewConfiguration.getScaledMinimumFlingVelocity();
                    this.h = viewConfiguration.getScaledMaximumFlingVelocity();
                    this.i = this.f * 5;
                    ItemSlidingAnimator itemSlidingAnimator = new ItemSlidingAnimator(this.o);
                    this.n = itemSlidingAnimator;
                    itemSlidingAnimator.setImmediatelySetTranslationThreshold((int) ((recyclerView.getResources().getDisplayMetrics().density * 8.0f) + 0.5f));
                    this.m = orientation == 1;
                    this.B = new b(this);
                    return;
                }
                throw new IllegalStateException("failed to determine layout orientation");
            }
            throw new IllegalStateException("RecyclerView instance has already been set");
        }
        throw new IllegalStateException("Accessing released object");
    }

    public void b(RecyclerView.ViewHolder viewHolder, int i, float f, float f2, boolean z, boolean z2, boolean z3, boolean z4) {
        int f3;
        float f4;
        SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
        if (f.b(swipeableItemViewHolder) == null) {
            return;
        }
        int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
        if (i2 == 0) {
            f3 = f == 0.0f ? 0 : f(f, z2);
        } else {
            f3 = f(f2, z2);
        }
        int i3 = f3;
        if (i2 != 0) {
            boolean isProportionalSwipeAmountModeEnabled = swipeableItemViewHolder.isProportionalSwipeAmountModeEnabled();
            float maxLeftSwipeAmount = z2 ? swipeableItemViewHolder.getMaxLeftSwipeAmount() : swipeableItemViewHolder.getMaxUpSwipeAmount();
            float maxRightSwipeAmount = z2 ? swipeableItemViewHolder.getMaxRightSwipeAmount() : swipeableItemViewHolder.getMaxDownSwipeAmount();
            f4 = Math.min(Math.max(f2, a(swipeableItemViewHolder, z2, maxLeftSwipeAmount, isProportionalSwipeAmountModeEnabled, z)), a(swipeableItemViewHolder, z2, maxRightSwipeAmount, isProportionalSwipeAmountModeEnabled, z));
        } else {
            f4 = f2;
        }
        z(viewHolder, f4, z, z2, z3);
        this.o.l(viewHolder, i, f2, z, z2, z4, i3);
    }

    public void c(RecyclerView.ViewHolder viewHolder) {
        ItemSlidingAnimator itemSlidingAnimator = this.n;
        if (itemSlidingAnimator != null) {
            itemSlidingAnimator.endAnimation(viewHolder);
        }
    }

    public void cancelSwipe() {
        d(false);
    }

    @NonNull
    public RecyclerView.Adapter createWrappedAdapter(@NonNull RecyclerView.Adapter adapter) {
        if (adapter.hasStableIds()) {
            if (this.o == null) {
                e<RecyclerView.ViewHolder> eVar = new e<>(this, adapter);
                this.o = eVar;
                return eVar;
            }
            throw new IllegalStateException("already have a wrapped adapter");
        }
        throw new IllegalArgumentException("The passed adapter does not support stable IDs");
    }

    public void d(boolean z) {
        p(null, false);
        if (z) {
            g(1);
        } else if (isSwiping()) {
            this.B.e();
        }
    }

    public final boolean e(MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder) {
        int l = l(viewHolder);
        if (l == -1) {
            return false;
        }
        A(motionEvent, viewHolder, l);
        return true;
    }

    public final void g(int i) {
        RecyclerView.ViewHolder viewHolder = this.p;
        if (viewHolder == null) {
            return;
        }
        this.B.d();
        this.B.a();
        RecyclerView recyclerView = this.b;
        boolean z = false;
        if (recyclerView != null && recyclerView.getParent() != null) {
            this.b.getParent().requestDisallowInterceptTouchEvent(false);
        }
        int k = k();
        this.y.clear();
        this.p = null;
        this.q = -1;
        this.r = -1L;
        this.v = 0;
        this.w = 0;
        this.j = 0;
        this.t = 0;
        this.u = 0;
        this.l = -1L;
        this.x = 0;
        g gVar = this.z;
        if (gVar != null) {
            gVar.c();
            this.z = null;
        }
        int y = y(i);
        e<RecyclerView.ViewHolder> eVar = this.o;
        SwipeResultAction h = eVar != null ? eVar.h(viewHolder, k, i) : null;
        if (h == null) {
            h = new SwipeResultActionDefault();
        }
        SwipeResultAction swipeResultAction = h;
        int resultActionType = swipeResultAction.getResultActionType();
        E(i, resultActionType);
        if (resultActionType == 0) {
            z = this.n.finishSwipeSlideToDefaultPosition(viewHolder, this.m, true, this.c, k, swipeResultAction);
        } else if (resultActionType == 1) {
            RecyclerView.ItemAnimator itemAnimator = this.b.getItemAnimator();
            long removeDuration = itemAnimator != null ? itemAnimator.getRemoveDuration() : 0L;
            com.h6ah4i.android.widget.advrecyclerview.swipeable.b bVar = new com.h6ah4i.android.widget.advrecyclerview.swipeable.b(this.b, viewHolder, i, removeDuration, itemAnimator != null ? itemAnimator.getMoveDuration() : 0L);
            bVar.i(SwipeDismissItemAnimator.MOVE_INTERPOLATOR);
            bVar.j();
            z = this.n.finishSwipeSlideToOutsideOfWindow(viewHolder, y, true, removeDuration, k, swipeResultAction);
        } else if (resultActionType == 2) {
            z = this.n.finishSwipeSlideToOutsideOfWindow(viewHolder, y, true, this.e, k, swipeResultAction);
        } else if (resultActionType != 3) {
            throw new IllegalStateException("Unknown after reaction type: " + resultActionType);
        }
        boolean z2 = z;
        e<RecyclerView.ViewHolder> eVar2 = this.o;
        if (eVar2 != null) {
            eVar2.i(viewHolder, k, i, resultActionType, swipeResultAction);
        }
        OnItemSwipeEventListener onItemSwipeEventListener = this.A;
        if (onItemSwipeEventListener != null) {
            onItemSwipeEventListener.onItemSwipeFinished(k, i, resultActionType);
        }
        if (z2) {
            return;
        }
        swipeResultAction.slideAnimationEnd();
    }

    public long getMoveToOutsideWindowAnimationDuration() {
        return this.e;
    }

    public long getMoveToSpecifiedPositionAnimationDuration() {
        return this.d;
    }

    @Nullable
    public OnItemSwipeEventListener getOnItemSwipeEventListener() {
        return this.A;
    }

    public long getReturnToDefaultPositionAnimationDuration() {
        return this.c;
    }

    public int getSwipeThresholdDistance() {
        return this.i;
    }

    public int i(RecyclerView.ViewHolder viewHolder) {
        return this.n.getSwipeContainerViewTranslationX(viewHolder);
    }

    public boolean isReleased() {
        return this.f11921a == null;
    }

    public boolean isSwiping() {
        return (this.p == null || this.B.b()) ? false : true;
    }

    public int j(RecyclerView.ViewHolder viewHolder) {
        return this.n.getSwipeContainerViewTranslationY(viewHolder);
    }

    public int k() {
        return this.q;
    }

    public final int l(RecyclerView.ViewHolder viewHolder) {
        return WrapperAdapterUtils.unwrapPosition(this.b.getAdapter(), this.o, CustomRecyclerViewUtils.getSynchronizedPosition(viewHolder));
    }

    public final boolean m(RecyclerView recyclerView, MotionEvent motionEvent) {
        int l;
        RecyclerView.ViewHolder findChildViewHolderUnderWithTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithTranslation(recyclerView, motionEvent.getX(), motionEvent.getY());
        if ((findChildViewHolderUnderWithTranslation instanceof SwipeableItemViewHolder) && (l = l(findChildViewHolderUnderWithTranslation)) >= 0 && l < this.o.getItemCount()) {
            if (ItemIdComposer.extractWrappedIdPart(findChildViewHolderUnderWithTranslation.getItemId()) != ItemIdComposer.extractWrappedIdPart(this.o.getItemId(l))) {
                return false;
            }
            int x = (int) (motionEvent.getX() + 0.5f);
            int y = (int) (motionEvent.getY() + 0.5f);
            View view = findChildViewHolderUnderWithTranslation.itemView;
            int translationY = (int) (view.getTranslationY() + 0.5f);
            int left = view.getLeft();
            int f = this.o.f(findChildViewHolderUnderWithTranslation, l, x - (left + ((int) (view.getTranslationX() + 0.5f))), y - (view.getTop() + translationY));
            if (f == 0) {
                return false;
            }
            this.j = x;
            this.k = y;
            this.l = findChildViewHolderUnderWithTranslation.getItemId();
            this.x = f;
            if ((16777216 & f) != 0) {
                this.B.f(motionEvent, this.C);
                return true;
            }
            return true;
        }
        return false;
    }

    public final boolean n(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.l == -1) {
            return false;
        }
        int x = ((int) (motionEvent.getX() + 0.5f)) - this.j;
        int y = ((int) (motionEvent.getY() + 0.5f)) - this.k;
        if (this.m) {
            y = x;
            x = y;
        }
        if (Math.abs(x) > this.f) {
            this.l = -1L;
            return false;
        } else if (Math.abs(y) <= this.f) {
            return false;
        } else {
            boolean z = true;
            if (!this.m ? y >= 0 ? (this.x & 2097152) == 0 : (this.x & 512) == 0 : y >= 0 ? (this.x & 32768) == 0 : (this.x & 8) == 0) {
                z = false;
            }
            if (z) {
                this.l = -1L;
                return false;
            }
            RecyclerView.ViewHolder findChildViewHolderUnderWithTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithTranslation(recyclerView, motionEvent.getX(), motionEvent.getY());
            if (findChildViewHolderUnderWithTranslation != null && findChildViewHolderUnderWithTranslation.getItemId() == this.l) {
                return e(motionEvent, findChildViewHolderUnderWithTranslation);
            }
            this.l = -1L;
            return false;
        }
    }

    public final void o(MotionEvent motionEvent) {
        this.v = (int) (motionEvent.getX() + 0.5f);
        this.w = (int) (motionEvent.getY() + 0.5f);
        this.y.addMovement(motionEvent);
        int i = this.v - this.t;
        int i2 = this.w - this.u;
        this.z.e(k(), i, i2);
    }

    public final boolean p(MotionEvent motionEvent, boolean z) {
        int i;
        if (motionEvent != null) {
            i = motionEvent.getActionMasked();
            this.v = (int) (motionEvent.getX() + 0.5f);
            this.w = (int) (motionEvent.getY() + 0.5f);
        } else {
            i = 3;
        }
        if (!isSwiping()) {
            q();
            return false;
        } else if (z) {
            r(i);
            return true;
        } else {
            return true;
        }
    }

    public boolean performFakeSwipe(RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = 0;
        if ((viewHolder instanceof SwipeableItemViewHolder) && !isSwiping()) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return false;
                            }
                        }
                    }
                    if (this.m) {
                        return false;
                    }
                }
                if (!this.m) {
                    return false;
                }
            }
            int l = l(viewHolder);
            if (l == -1) {
                return false;
            }
            MotionEvent obtain = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0);
            A(obtain, viewHolder, l);
            obtain.recycle();
            if (i == 2 || i == 3) {
                i2 = -1;
            } else if (i == 4 || i == 5) {
                i2 = 1;
            }
            b(viewHolder, l, 0.0f, i2, false, this.m, false, true);
            g(i);
            return true;
        }
        return false;
    }

    public final void q() {
        b bVar = this.B;
        if (bVar != null) {
            bVar.a();
        }
        this.l = -1L;
        this.x = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void r(int r13) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager.r(int):void");
    }

    public void release() {
        RecyclerView.OnItemTouchListener onItemTouchListener;
        d(true);
        b bVar = this.B;
        if (bVar != null) {
            bVar.c();
            this.B = null;
        }
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && (onItemTouchListener = this.f11921a) != null) {
            recyclerView.removeOnItemTouchListener(onItemTouchListener);
        }
        this.f11921a = null;
        VelocityTracker velocityTracker = this.y;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.y = null;
        }
        ItemSlidingAnimator itemSlidingAnimator = this.n;
        if (itemSlidingAnimator != null) {
            itemSlidingAnimator.endAnimations();
            this.n = null;
        }
        this.o = null;
        this.b = null;
    }

    public void s(MotionEvent motionEvent) {
        RecyclerView.ViewHolder findViewHolderForItemId = this.b.findViewHolderForItemId(this.l);
        if (findViewHolderForItemId != null) {
            e(motionEvent, findViewHolderForItemId);
        }
    }

    public void setLongPressTimeout(int i) {
        this.C = i;
    }

    public void setMoveToOutsideWindowAnimationDuration(long j) {
        this.e = j;
    }

    public void setMoveToSpecifiedPositionAnimationDuration(long j) {
        this.d = j;
    }

    public void setOnItemSwipeEventListener(@Nullable OnItemSwipeEventListener onItemSwipeEventListener) {
        this.A = onItemSwipeEventListener;
    }

    public void setReturnToDefaultPositionAnimationDuration(long j) {
        this.c = j;
    }

    public void setSwipeThresholdDistance(int i) {
        this.i = Math.max(i, this.f);
    }

    public boolean t(RecyclerView.ViewHolder viewHolder) {
        ItemSlidingAnimator itemSlidingAnimator = this.n;
        return itemSlidingAnimator != null && itemSlidingAnimator.isRunning(viewHolder);
    }

    public boolean v(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            if (isSwiping()) {
                return false;
            }
            m(recyclerView, motionEvent);
            return false;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (!isSwiping()) {
                    return n(recyclerView, motionEvent);
                }
                o(motionEvent);
                return true;
            } else if (actionMasked != 3) {
                return false;
            }
        }
        return p(motionEvent, true);
    }

    public void w(boolean z) {
        if (z) {
            d(true);
        }
    }

    public void x(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (isSwiping()) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    o(motionEvent);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            p(motionEvent, true);
        }
    }

    public final void z(RecyclerView.ViewHolder viewHolder, float f, boolean z, boolean z2, boolean z3) {
        if (f == -65536.0f) {
            this.n.slideToOutsideOfWindow(viewHolder, 0, z3, this.e);
        } else if (f == -65537.0f) {
            this.n.slideToOutsideOfWindow(viewHolder, 1, z3, this.e);
        } else if (f == 65536.0f) {
            this.n.slideToOutsideOfWindow(viewHolder, 2, z3, this.e);
        } else if (f == 65537.0f) {
            this.n.slideToOutsideOfWindow(viewHolder, 3, z3, this.e);
        } else if (f == 0.0f) {
            this.n.slideToDefaultPosition(viewHolder, z2, z3, this.c);
        } else {
            this.n.slideToSpecifiedPosition(viewHolder, f, z, z2, z3, this.d);
        }
    }
}
