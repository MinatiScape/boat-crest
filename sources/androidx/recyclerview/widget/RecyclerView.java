package androidx.recyclerview.widget;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.ViewBoundsCheck;
import androidx.recyclerview.widget.a;
import androidx.recyclerview.widget.b;
import androidx.recyclerview.widget.e;
import androidx.recyclerview.widget.l;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.ido.ble.protocol.model.V3MessageNotice;
import com.realsil.sdk.dfu.DfuConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2, NestedScrollingChild3 {
    public static final int HORIZONTAL = 0;
    public static final int INVALID_TYPE = -1;
    public static final int[] J0 = {16843830};
    public static final boolean K0;
    public static final boolean L0;
    public static final boolean M0;
    public static final boolean N0;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final boolean O0;
    public static final boolean P0;
    public static final Class<?>[] Q0;
    public static final Interpolator R0;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    public boolean A;
    public final int[] A0;
    public boolean B;
    public final int[] B0;
    @VisibleForTesting
    public boolean C;
    public final int[] C0;
    public int D;
    @VisibleForTesting
    public final List<ViewHolder> D0;
    public boolean E;
    public Runnable E0;
    public boolean F;
    public boolean F0;
    public boolean G;
    public int G0;
    public int H;
    public int H0;
    public boolean I;
    public final l.b I0;
    public final AccessibilityManager J;
    public List<OnChildAttachStateChangeListener> K;
    public boolean L;
    public boolean M;
    public int N;
    public int O;
    @NonNull
    public EdgeEffectFactory P;
    public EdgeEffect Q;
    public EdgeEffect R;
    public EdgeEffect S;
    public EdgeEffect T;
    public ItemAnimator U;
    public int V;
    public int W;
    public VelocityTracker a0;
    public int b0;
    public int c0;
    public int d0;
    public int e0;
    public int f0;
    public OnFlingListener g0;
    public final j h;
    public final int h0;
    public final Recycler i;
    public final int i0;
    public SavedState j;
    public float j0;
    public androidx.recyclerview.widget.a k;
    public float k0;
    public androidx.recyclerview.widget.b l;
    public boolean l0;
    public final l m;
    public final k m0;
    public boolean n;
    public androidx.recyclerview.widget.e n0;
    public final Runnable o;
    public e.b o0;
    public final Rect p;
    public final State p0;
    public final Rect q;
    public OnScrollListener q0;
    public final RectF r;
    public List<OnScrollListener> r0;
    public Adapter s;
    public boolean s0;
    @VisibleForTesting
    public LayoutManager t;
    public boolean t0;
    public RecyclerListener u;
    public ItemAnimator.a u0;
    public final List<RecyclerListener> v;
    public boolean v0;
    public final ArrayList<ItemDecoration> w;
    public RecyclerViewAccessibilityDelegate w0;
    public final ArrayList<OnItemTouchListener> x;
    public ChildDrawingOrderCallback x0;
    public OnItemTouchListener y;
    public final int[] y0;
    public boolean z;
    public NestedScrollingChildHelper z0;

    /* loaded from: classes.dex */
    public static abstract class Adapter<VH extends ViewHolder> {
        private final h mObservable = new h();
        private boolean mHasStableIds = false;
        private StateRestorationPolicy mStateRestorationPolicy = StateRestorationPolicy.ALLOW;

        /* loaded from: classes.dex */
        public enum StateRestorationPolicy {
            ALLOW,
            PREVENT_WHEN_EMPTY,
            PREVENT
        }

        public final void bindViewHolder(@NonNull VH vh, int i) {
            boolean z = vh.mBindingAdapter == null;
            if (z) {
                vh.mPosition = i;
                if (hasStableIds()) {
                    vh.mItemId = getItemId(i);
                }
                vh.setFlags(1, 519);
                TraceCompat.beginSection("RV OnBindView");
            }
            vh.mBindingAdapter = this;
            onBindViewHolder(vh, i, vh.getUnmodifiedPayloads());
            if (z) {
                vh.clearPayload();
                ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
                if (layoutParams instanceof LayoutParams) {
                    ((LayoutParams) layoutParams).j = true;
                }
                TraceCompat.endSection();
            }
        }

        public boolean canRestoreState() {
            int i = g.f1612a[this.mStateRestorationPolicy.ordinal()];
            if (i != 1) {
                return i != 2 || getItemCount() > 0;
            }
            return false;
        }

        @NonNull
        public final VH createViewHolder(@NonNull ViewGroup viewGroup, int i) {
            try {
                TraceCompat.beginSection("RV CreateView");
                VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
                if (onCreateViewHolder.itemView.getParent() == null) {
                    onCreateViewHolder.mItemViewType = i;
                    return onCreateViewHolder;
                }
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            } finally {
                TraceCompat.endSection();
            }
        }

        public int findRelativeAdapterPositionIn(@NonNull Adapter<? extends ViewHolder> adapter, @NonNull ViewHolder viewHolder, int i) {
            if (adapter == this) {
                return i;
            }
            return -1;
        }

        public abstract int getItemCount();

        public long getItemId(int i) {
            return -1L;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        @NonNull
        public final StateRestorationPolicy getStateRestorationPolicy() {
            return this.mStateRestorationPolicy;
        }

        public final boolean hasObservers() {
            return this.mObservable.a();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.b();
        }

        public final void notifyItemChanged(int i) {
            this.mObservable.d(i, 1);
        }

        public final void notifyItemInserted(int i) {
            this.mObservable.f(i, 1);
        }

        public final void notifyItemMoved(int i, int i2) {
            this.mObservable.c(i, i2);
        }

        public final void notifyItemRangeChanged(int i, int i2) {
            this.mObservable.d(i, i2);
        }

        public final void notifyItemRangeInserted(int i, int i2) {
            this.mObservable.f(i, i2);
        }

        public final void notifyItemRangeRemoved(int i, int i2) {
            this.mObservable.g(i, i2);
        }

        public final void notifyItemRemoved(int i) {
            this.mObservable.g(i, 1);
        }

        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public abstract void onBindViewHolder(@NonNull VH vh, int i);

        public void onBindViewHolder(@NonNull VH vh, int i, @NonNull List<Object> list) {
            onBindViewHolder(vh, i);
        }

        @NonNull
        public abstract VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public boolean onFailedToRecycleView(@NonNull VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(@NonNull VH vh) {
        }

        public void onViewDetachedFromWindow(@NonNull VH vh) {
        }

        public void onViewRecycled(@NonNull VH vh) {
        }

        public void registerAdapterDataObserver(@NonNull AdapterDataObserver adapterDataObserver) {
            this.mObservable.registerObserver(adapterDataObserver);
        }

        public void setHasStableIds(boolean z) {
            if (!hasObservers()) {
                this.mHasStableIds = z;
                return;
            }
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }

        public void setStateRestorationPolicy(@NonNull StateRestorationPolicy stateRestorationPolicy) {
            this.mStateRestorationPolicy = stateRestorationPolicy;
            this.mObservable.h();
        }

        public void unregisterAdapterDataObserver(@NonNull AdapterDataObserver adapterDataObserver) {
            this.mObservable.unregisterObserver(adapterDataObserver);
        }

        public final void notifyItemChanged(int i, @Nullable Object obj) {
            this.mObservable.e(i, 1, obj);
        }

        public final void notifyItemRangeChanged(int i, int i2, @Nullable Object obj) {
            this.mObservable.e(i, i2, obj);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class AdapterDataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int i, int i2) {
        }

        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            onItemRangeChanged(i, i2);
        }

        public void onItemRangeInserted(int i, int i2) {
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
        }

        public void onItemRangeRemoved(int i, int i2) {
        }

        public void onStateRestorationPolicyChanged() {
        }
    }

    /* loaded from: classes.dex */
    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i, int i2);
    }

    /* loaded from: classes.dex */
    public static class EdgeEffectFactory {
        public static final int DIRECTION_BOTTOM = 3;
        public static final int DIRECTION_LEFT = 0;
        public static final int DIRECTION_RIGHT = 2;
        public static final int DIRECTION_TOP = 1;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface EdgeDirection {
        }

        @NonNull
        public EdgeEffect createEdgeEffect(@NonNull RecyclerView recyclerView, int i) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemAnimator {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private a mListener = null;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<>();
        private long mAddDuration = 120;
        private long mRemoveDuration = 120;
        private long mMoveDuration = 250;
        private long mChangeDuration = 250;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface AdapterChanges {
        }

        /* loaded from: classes.dex */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        /* loaded from: classes.dex */
        public static class ItemHolderInfo {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder viewHolder) {
                return setFrom(viewHolder, 0);
            }

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder viewHolder, int i) {
                View view = viewHolder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                this.right = view.getRight();
                this.bottom = view.getBottom();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public interface a {
            void a(@NonNull ViewHolder viewHolder);
        }

        public static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            int i = viewHolder.mFlags & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            if ((i & 4) == 0) {
                int oldPosition = viewHolder.getOldPosition();
                int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                return (oldPosition == -1 || absoluteAdapterPosition == -1 || oldPosition == absoluteAdapterPosition) ? i : i | 2048;
            }
            return i;
        }

        public abstract boolean animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2);

        public abstract boolean animatePersistence(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder) {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder, @NonNull List<Object> list) {
            return canReuseUpdatedViewHolder(viewHolder);
        }

        public final void dispatchAnimationFinished(@NonNull ViewHolder viewHolder) {
            onAnimationFinished(viewHolder);
            a aVar = this.mListener;
            if (aVar != null) {
                aVar.a(viewHolder);
            }
        }

        public final void dispatchAnimationStarted(@NonNull ViewHolder viewHolder) {
            onAnimationStarted(viewHolder);
        }

        public final void dispatchAnimationsFinished() {
            int size = this.mFinishedListeners.size();
            for (int i = 0; i < size; i++) {
                this.mFinishedListeners.get(i).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        public abstract void endAnimation(@NonNull ViewHolder viewHolder);

        public abstract void endAnimations();

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public long getChangeDuration() {
            return this.mChangeDuration;
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(@Nullable ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            boolean isRunning = isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (!isRunning) {
                    itemAnimatorFinishedListener.onAnimationsFinished();
                } else {
                    this.mFinishedListeners.add(itemAnimatorFinishedListener);
                }
            }
            return isRunning;
        }

        @NonNull
        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }

        public void onAnimationFinished(@NonNull ViewHolder viewHolder) {
        }

        public void onAnimationStarted(@NonNull ViewHolder viewHolder) {
        }

        @NonNull
        public ItemHolderInfo recordPostLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        @NonNull
        public ItemHolderInfo recordPreLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder, int i, @NonNull List<Object> list) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        public abstract void runPendingAnimations();

        public void setAddDuration(long j) {
            this.mAddDuration = j;
        }

        public void setChangeDuration(long j) {
            this.mChangeDuration = j;
        }

        public void setListener(a aVar) {
            this.mListener = aVar;
        }

        public void setMoveDuration(long j) {
            this.mMoveDuration = j;
        }

        public void setRemoveDuration(long j) {
            this.mRemoveDuration = j;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemDecoration {
        @Deprecated
        public void getItemOffsets(@NonNull Rect rect, int i, @NonNull RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        @Deprecated
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull State state) {
            onDraw(canvas, recyclerView);
        }

        @Deprecated
        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull State state) {
            onDrawOver(canvas, recyclerView);
        }

        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull State state) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class LayoutManager {
        public androidx.recyclerview.widget.b h;
        public RecyclerView i;
        public final ViewBoundsCheck.b j;
        public final ViewBoundsCheck.b k;
        public ViewBoundsCheck l;
        public ViewBoundsCheck m;
        @Nullable
        public SmoothScroller n;
        public boolean o;
        public boolean p;
        public boolean q;
        public boolean r;
        public boolean s;
        public int t;
        public boolean u;
        public int v;
        public int w;
        public int x;
        public int y;

        /* loaded from: classes.dex */
        public interface LayoutPrefetchRegistry {
            void addPosition(int i, int i2);
        }

        /* loaded from: classes.dex */
        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        /* loaded from: classes.dex */
        public class a implements ViewBoundsCheck.b {
            public a() {
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public View a(int i) {
                return LayoutManager.this.getChildAt(i);
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int b(View view) {
                return LayoutManager.this.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).leftMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int c() {
                return LayoutManager.this.getPaddingLeft();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int d() {
                return LayoutManager.this.getWidth() - LayoutManager.this.getPaddingRight();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int e(View view) {
                return LayoutManager.this.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).rightMargin;
            }
        }

        /* loaded from: classes.dex */
        public class b implements ViewBoundsCheck.b {
            public b() {
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public View a(int i) {
                return LayoutManager.this.getChildAt(i);
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int b(View view) {
                return LayoutManager.this.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).topMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int c() {
                return LayoutManager.this.getPaddingTop();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int d() {
                return LayoutManager.this.getHeight() - LayoutManager.this.getPaddingBottom();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int e(View view) {
                return LayoutManager.this.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
            }
        }

        public LayoutManager() {
            a aVar = new a();
            this.j = aVar;
            b bVar = new b();
            this.k = bVar;
            this.l = new ViewBoundsCheck(aVar);
            this.m = new ViewBoundsCheck(bVar);
            this.o = false;
            this.p = false;
            this.q = false;
            this.r = true;
            this.s = true;
        }

        public static int chooseSize(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode != Integer.MIN_VALUE) {
                return mode != 1073741824 ? Math.max(i2, i3) : size;
            }
            return Math.min(size, Math.max(i2, i3));
        }

        /* JADX WARN: Code restructure failed: missing block: B:4:0x000a, code lost:
            if (r3 >= 0) goto L8;
         */
        @java.lang.Deprecated
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static int getChildMeasureSpec(int r1, int r2, int r3, boolean r4) {
            /*
                int r1 = r1 - r2
                r2 = 0
                int r1 = java.lang.Math.max(r2, r1)
                r0 = 1073741824(0x40000000, float:2.0)
                if (r4 == 0) goto Lf
                if (r3 < 0) goto Ld
                goto L11
            Ld:
                r3 = r2
                goto L1e
            Lf:
                if (r3 < 0) goto L13
            L11:
                r2 = r0
                goto L1e
            L13:
                r4 = -1
                if (r3 != r4) goto L18
                r3 = r1
                goto L11
            L18:
                r4 = -2
                if (r3 != r4) goto Ld
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1
            L1e:
                int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.getChildMeasureSpec(int, int, int, boolean):int");
        }

        public static Properties getProperties(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i, i2);
            properties.orientation = obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = obtainStyledAttributes.getInt(R.styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        public static boolean h(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (i3 <= 0 || i == i3) {
                if (mode == Integer.MIN_VALUE) {
                    return size >= i;
                } else if (mode != 0) {
                    return mode == 1073741824 && size == i;
                } else {
                    return true;
                }
            }
            return false;
        }

        public final void a(View view, int i, boolean z) {
            ViewHolder S = RecyclerView.S(view);
            if (!z && !S.isRemoved()) {
                this.i.m.p(S);
            } else {
                this.i.m.b(S);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (!S.wasReturnedFromScrap() && !S.isScrap()) {
                if (view.getParent() == this.i) {
                    int m = this.h.m(view);
                    if (i == -1) {
                        i = this.h.g();
                    }
                    if (m == -1) {
                        throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.i.indexOfChild(view) + this.i.J());
                    } else if (m != i) {
                        this.i.t.moveView(m, i);
                    }
                } else {
                    this.h.a(view, i, false);
                    layoutParams.j = true;
                    SmoothScroller smoothScroller = this.n;
                    if (smoothScroller != null && smoothScroller.isRunning()) {
                        this.n.onChildAttachedToWindow(view);
                    }
                }
            } else {
                if (S.isScrap()) {
                    S.unScrap();
                } else {
                    S.clearReturnedFromScrapFlag();
                }
                this.h.c(view, i, view.getLayoutParams(), false);
            }
            if (layoutParams.k) {
                S.itemView.invalidate();
                layoutParams.k = false;
            }
        }

        public void addDisappearingView(View view) {
            addDisappearingView(view, -1);
        }

        public void addView(View view) {
            addView(view, -1);
        }

        public void assertInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                recyclerView.j(str);
            }
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                recyclerView.k(str);
            }
        }

        public void attachView(@NonNull View view, int i, LayoutParams layoutParams) {
            ViewHolder S = RecyclerView.S(view);
            if (S.isRemoved()) {
                this.i.m.b(S);
            } else {
                this.i.m.p(S);
            }
            this.h.c(view, i, layoutParams, S.isRemoved());
        }

        public final void b(int i, @NonNull View view) {
            this.h.d(i);
        }

        public void c(RecyclerView recyclerView) {
            this.p = true;
            onAttachedToWindow(recyclerView);
        }

        public void calculateItemDecorationsForChild(@NonNull View view, @NonNull Rect rect) {
            RecyclerView recyclerView = this.i;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.W(view));
            }
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public void collectAdjacentPrefetchPositions(int i, int i2, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public int computeHorizontalScrollExtent(@NonNull State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(@NonNull State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollRange(@NonNull State state) {
            return 0;
        }

        public void d(RecyclerView recyclerView, Recycler recycler) {
            this.p = false;
            onDetachedFromWindow(recyclerView, recycler);
        }

        public void detachAndScrapAttachedViews(@NonNull Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                o(recycler, childCount, getChildAt(childCount));
            }
        }

        public void detachAndScrapView(@NonNull View view, @NonNull Recycler recycler) {
            o(recycler, this.h.m(view), view);
        }

        public void detachAndScrapViewAt(int i, @NonNull Recycler recycler) {
            o(recycler, i, getChildAt(i));
        }

        public void detachView(@NonNull View view) {
            int m = this.h.m(view);
            if (m >= 0) {
                b(m, view);
            }
        }

        public void detachViewAt(int i) {
            b(i, getChildAt(i));
        }

        public final int[] e(View view, Rect rect) {
            int[] iArr = new int[2];
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width2 = rect.width() + left;
            int height2 = rect.height() + top;
            int i = left - paddingLeft;
            int min = Math.min(0, i);
            int i2 = top - paddingTop;
            int min2 = Math.min(0, i2);
            int i3 = width2 - width;
            int max = Math.max(0, i3);
            int max2 = Math.max(0, height2 - height);
            if (getLayoutDirection() != 1) {
                if (min == 0) {
                    min = Math.min(i, max);
                }
                max = min;
            } else if (max == 0) {
                max = Math.max(min, i3);
            }
            if (min2 == 0) {
                min2 = Math.min(i2, max2);
            }
            iArr[0] = max;
            iArr[1] = min2;
            return iArr;
        }

        public void endAnimation(View view) {
            ItemAnimator itemAnimator = this.i.U;
            if (itemAnimator != null) {
                itemAnimator.endAnimation(RecyclerView.S(view));
            }
        }

        public boolean f() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        @Nullable
        public View findContainingItemView(@NonNull View view) {
            View findContainingItemView;
            RecyclerView recyclerView = this.i;
            if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.h.n(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        @Nullable
        public View findViewByPosition(int i) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                ViewHolder S = RecyclerView.S(childAt);
                if (S != null && S.getLayoutPosition() == i && !S.shouldIgnore() && (this.i.p0.isPreLayout() || !S.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        public final boolean g(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            Rect rect = this.i.p;
            getDecoratedBoundsWithMargins(focusedChild, rect);
            return rect.left - i < width && rect.right - i > paddingLeft && rect.top - i2 < height && rect.bottom - i2 > paddingTop;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public int getBaseline() {
            return -1;
        }

        public int getBottomDecorationHeight(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).i.bottom;
        }

        @Nullable
        public View getChildAt(int i) {
            androidx.recyclerview.widget.b bVar = this.h;
            if (bVar != null) {
                return bVar.f(i);
            }
            return null;
        }

        public int getChildCount() {
            androidx.recyclerview.widget.b bVar = this.h;
            if (bVar != null) {
                return bVar.g();
            }
            return 0;
        }

        public boolean getClipToPadding() {
            RecyclerView recyclerView = this.i;
            return recyclerView != null && recyclerView.n;
        }

        public int getColumnCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            return -1;
        }

        public int getDecoratedBottom(@NonNull View view) {
            return view.getBottom() + getBottomDecorationHeight(view);
        }

        public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
            RecyclerView.T(view, rect);
        }

        public int getDecoratedLeft(@NonNull View view) {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedMeasuredHeight(@NonNull View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).i;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(@NonNull View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).i;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(@NonNull View view) {
            return view.getRight() + getRightDecorationWidth(view);
        }

        public int getDecoratedTop(@NonNull View view) {
            return view.getTop() - getTopDecorationHeight(view);
        }

        @Nullable
        public View getFocusedChild() {
            View focusedChild;
            RecyclerView recyclerView = this.i;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.h.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        @Px
        public int getHeight() {
            return this.y;
        }

        public int getHeightMode() {
            return this.w;
        }

        public int getItemCount() {
            RecyclerView recyclerView = this.i;
            Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public int getItemViewType(@NonNull View view) {
            return RecyclerView.S(view).getItemViewType();
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.i);
        }

        public int getLeftDecorationWidth(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).i.left;
        }

        @Px
        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.i);
        }

        @Px
        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.i);
        }

        @Px
        public int getPaddingBottom() {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        @Px
        public int getPaddingEnd() {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                return ViewCompat.getPaddingEnd(recyclerView);
            }
            return 0;
        }

        @Px
        public int getPaddingLeft() {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        @Px
        public int getPaddingRight() {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        @Px
        public int getPaddingStart() {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                return ViewCompat.getPaddingStart(recyclerView);
            }
            return 0;
        }

        @Px
        public int getPaddingTop() {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getPosition(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRightDecorationWidth(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).i.right;
        }

        public int getRowCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            return -1;
        }

        public int getSelectionModeForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            return 0;
        }

        public int getTopDecorationHeight(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).i.top;
        }

        public void getTransformedBoundingBox(@NonNull View view, boolean z, @NonNull Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).i;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.i != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.i.r;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        @Px
        public int getWidth() {
            return this.x;
        }

        public int getWidthMode() {
            return this.v;
        }

        public boolean hasFocus() {
            RecyclerView recyclerView = this.i;
            return recyclerView != null && recyclerView.hasFocus();
        }

        public void i(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            RecyclerView recyclerView = this.i;
            onInitializeAccessibilityNodeInfo(recyclerView.i, recyclerView.p0, accessibilityNodeInfoCompat);
        }

        public void ignoreView(@NonNull View view) {
            ViewParent parent = view.getParent();
            RecyclerView recyclerView = this.i;
            if (parent == recyclerView && recyclerView.indexOfChild(view) != -1) {
                ViewHolder S = RecyclerView.S(view);
                S.addFlags(128);
                this.i.m.q(S);
                return;
            }
            throw new IllegalArgumentException("View should be fully attached to be ignored" + this.i.J());
        }

        public boolean isAttachedToWindow() {
            return this.p;
        }

        public boolean isAutoMeasureEnabled() {
            return this.q;
        }

        public boolean isFocused() {
            RecyclerView recyclerView = this.i;
            return recyclerView != null && recyclerView.isFocused();
        }

        public final boolean isItemPrefetchEnabled() {
            return this.s;
        }

        public boolean isLayoutHierarchical(@NonNull Recycler recycler, @NonNull State state) {
            return false;
        }

        public boolean isMeasurementCacheEnabled() {
            return this.r;
        }

        public boolean isSmoothScrolling() {
            SmoothScroller smoothScroller = this.n;
            return smoothScroller != null && smoothScroller.isRunning();
        }

        public boolean isViewPartiallyVisible(@NonNull View view, boolean z, boolean z2) {
            boolean z3 = this.l.b(view, 24579) && this.m.b(view, 24579);
            return z ? z3 : !z3;
        }

        public void j(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder S = RecyclerView.S(view);
            if (S == null || S.isRemoved() || this.h.n(S.itemView)) {
                return;
            }
            RecyclerView recyclerView = this.i;
            onInitializeAccessibilityNodeInfoForItem(recyclerView.i, recyclerView.p0, view, accessibilityNodeInfoCompat);
        }

        public void k(SmoothScroller smoothScroller) {
            if (this.n == smoothScroller) {
                this.n = null;
            }
        }

        public boolean l(int i, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.i;
            return performAccessibilityAction(recyclerView.i, recyclerView.p0, i, bundle);
        }

        public void layoutDecorated(@NonNull View view, int i, int i2, int i3, int i4) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).i;
            view.layout(i + rect.left, i2 + rect.top, i3 - rect.right, i4 - rect.bottom);
        }

        public void layoutDecoratedWithMargins(@NonNull View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.i;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        public boolean m(@NonNull View view, int i, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.i;
            return performAccessibilityActionForItem(recyclerView.i, recyclerView.p0, view, i, bundle);
        }

        public void measureChild(@NonNull View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect W = this.i.W(view);
            int i3 = i + W.left + W.right;
            int i4 = i2 + W.top + W.bottom;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + i3, ((ViewGroup.MarginLayoutParams) layoutParams).width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + i4, ((ViewGroup.MarginLayoutParams) layoutParams).height, canScrollVertically());
            if (t(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void measureChildWithMargins(@NonNull View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect W = this.i.W(view);
            int i3 = i + W.left + W.right;
            int i4 = i2 + W.top + W.bottom;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + i3, ((ViewGroup.MarginLayoutParams) layoutParams).width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) layoutParams).height, canScrollVertically());
            if (t(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void moveView(int i, int i2) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                detachViewAt(i);
                attachView(childAt, i2);
                return;
            }
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.i.toString());
        }

        public void n(Recycler recycler) {
            int h = recycler.h();
            for (int i = h - 1; i >= 0; i--) {
                View k = recycler.k(i);
                ViewHolder S = RecyclerView.S(k);
                if (!S.shouldIgnore()) {
                    S.setIsRecyclable(false);
                    if (S.isTmpDetached()) {
                        this.i.removeDetachedView(k, false);
                    }
                    ItemAnimator itemAnimator = this.i.U;
                    if (itemAnimator != null) {
                        itemAnimator.endAnimation(S);
                    }
                    S.setIsRecyclable(true);
                    recycler.u(k);
                }
            }
            recycler.d();
            if (h > 0) {
                this.i.invalidate();
            }
        }

        public final void o(Recycler recycler, int i, View view) {
            ViewHolder S = RecyclerView.S(view);
            if (S.shouldIgnore()) {
                return;
            }
            if (S.isInvalid() && !S.isRemoved() && !this.i.s.hasStableIds()) {
                removeViewAt(i);
                recycler.x(S);
                return;
            }
            detachViewAt(i);
            recycler.y(view);
            this.i.m.k(S);
        }

        public void offsetChildrenHorizontal(@Px int i) {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                recyclerView.offsetChildrenHorizontal(i);
            }
        }

        public void offsetChildrenVertical(@Px int i) {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                recyclerView.offsetChildrenVertical(i);
            }
        }

        public void onAdapterChanged(@Nullable Adapter adapter, @Nullable Adapter adapter2) {
        }

        public boolean onAddFocusables(@NonNull RecyclerView recyclerView, @NonNull ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        @CallSuper
        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView recyclerView) {
        }

        @CallSuper
        public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            onDetachedFromWindow(recyclerView);
        }

        @Nullable
        public View onFocusSearchFailed(@NonNull View view, int i, @NonNull Recycler recycler, @NonNull State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.i;
            onInitializeAccessibilityEvent(recyclerView.i, recyclerView.p0, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.i.canScrollVertically(-1) || this.i.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (this.i.canScrollVertically(1) || this.i.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        public void onInitializeAccessibilityNodeInfoForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        @Nullable
        public View onInterceptFocusSearch(@NonNull View view, int i) {
            return null;
        }

        public void onItemsAdded(@NonNull RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsChanged(@NonNull RecyclerView recyclerView) {
        }

        public void onItemsMoved(@NonNull RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2, @Nullable Object obj) {
            onItemsUpdated(recyclerView, i, i2);
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state) {
        }

        public void onMeasure(@NonNull Recycler recycler, @NonNull State state, int i, int i2) {
            this.i.s(i, i2);
        }

        @Deprecated
        public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull View view, @Nullable View view2) {
            return isSmoothScrolling() || recyclerView.isComputingLayout();
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        @Nullable
        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onScrollStateChanged(int i) {
        }

        public void p(RecyclerView recyclerView) {
            q(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public boolean performAccessibilityAction(@NonNull Recycler recycler, @NonNull State state, int i, @Nullable Bundle bundle) {
            int height;
            int width;
            int i2;
            int i3;
            RecyclerView recyclerView = this.i;
            if (recyclerView == null) {
                return false;
            }
            if (i == 4096) {
                height = recyclerView.canScrollVertically(1) ? (getHeight() - getPaddingTop()) - getPaddingBottom() : 0;
                if (this.i.canScrollHorizontally(1)) {
                    width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                    i2 = height;
                    i3 = width;
                }
                i2 = height;
                i3 = 0;
            } else if (i != 8192) {
                i3 = 0;
                i2 = 0;
            } else {
                height = recyclerView.canScrollVertically(-1) ? -((getHeight() - getPaddingTop()) - getPaddingBottom()) : 0;
                if (this.i.canScrollHorizontally(-1)) {
                    width = -((getWidth() - getPaddingLeft()) - getPaddingRight());
                    i2 = height;
                    i3 = width;
                }
                i2 = height;
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            this.i.M0(i3, i2, null, Integer.MIN_VALUE, true);
            return true;
        }

        public boolean performAccessibilityActionForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, int i, @Nullable Bundle bundle) {
            return false;
        }

        public void postOnAnimation(Runnable runnable) {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                ViewCompat.postOnAnimation(recyclerView, runnable);
            }
        }

        public void q(int i, int i2) {
            this.x = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.v = mode;
            if (mode == 0 && !RecyclerView.L0) {
                this.x = 0;
            }
            this.y = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.w = mode2;
            if (mode2 != 0 || RecyclerView.L0) {
                return;
            }
            this.y = 0;
        }

        public void r(int i, int i2) {
            int childCount = getChildCount();
            if (childCount == 0) {
                this.i.s(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MIN_VALUE;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                Rect rect = this.i.p;
                getDecoratedBoundsWithMargins(childAt, rect);
                int i8 = rect.left;
                if (i8 < i4) {
                    i4 = i8;
                }
                int i9 = rect.right;
                if (i9 > i3) {
                    i3 = i9;
                }
                int i10 = rect.top;
                if (i10 < i5) {
                    i5 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i6) {
                    i6 = i11;
                }
            }
            this.i.p.set(i4, i5, i3, i6);
            setMeasuredDimension(this.i.p, i, i2);
        }

        public void removeAllViews() {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                this.h.q(childCount);
            }
        }

        public void removeAndRecycleAllViews(@NonNull Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.S(getChildAt(childCount)).shouldIgnore()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        public void removeAndRecycleView(@NonNull View view, @NonNull Recycler recycler) {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i, @NonNull Recycler recycler) {
            View childAt = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(childAt);
        }

        public boolean removeCallbacks(Runnable runnable) {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void removeDetachedView(@NonNull View view) {
            this.i.removeDetachedView(view, false);
        }

        public void removeView(View view) {
            this.h.p(view);
        }

        public void removeViewAt(int i) {
            if (getChildAt(i) != null) {
                this.h.q(i);
            }
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z) {
            return requestChildRectangleOnScreen(recyclerView, view, rect, z, false);
        }

        public void requestLayout() {
            RecyclerView recyclerView = this.i;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.o = true;
        }

        public void s(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.i = null;
                this.h = null;
                this.x = 0;
                this.y = 0;
            } else {
                this.i = recyclerView;
                this.h = recyclerView.l;
                this.x = recyclerView.getWidth();
                this.y = recyclerView.getHeight();
            }
            this.v = 1073741824;
            this.w = 1073741824;
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        public void scrollToPosition(int i) {
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        @Deprecated
        public void setAutoMeasureEnabled(boolean z) {
            this.q = z;
        }

        public final void setItemPrefetchEnabled(boolean z) {
            if (z != this.s) {
                this.s = z;
                this.t = 0;
                RecyclerView recyclerView = this.i;
                if (recyclerView != null) {
                    recyclerView.i.E();
                }
            }
        }

        public void setMeasuredDimension(Rect rect, int i, int i2) {
            setMeasuredDimension(chooseSize(i, rect.width() + getPaddingLeft() + getPaddingRight(), getMinimumWidth()), chooseSize(i2, rect.height() + getPaddingTop() + getPaddingBottom(), getMinimumHeight()));
        }

        public void setMeasurementCacheEnabled(boolean z) {
            this.r = z;
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.n;
            if (smoothScroller2 != null && smoothScroller != smoothScroller2 && smoothScroller2.isRunning()) {
                this.n.stop();
            }
            this.n = smoothScroller;
            smoothScroller.b(this.i, this);
        }

        public void stopIgnoringView(@NonNull View view) {
            ViewHolder S = RecyclerView.S(view);
            S.stopIgnoring();
            S.resetInternal();
            S.addFlags(4);
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public boolean t(View view, int i, int i2, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.r && h(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && h(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public boolean u() {
            return false;
        }

        public boolean v(View view, int i, int i2, LayoutParams layoutParams) {
            return (this.r && h(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && h(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public void w() {
            SmoothScroller smoothScroller = this.n;
            if (smoothScroller != null) {
                smoothScroller.stop();
            }
        }

        public void addDisappearingView(View view, int i) {
            a(view, i, true);
        }

        public void addView(View view, int i) {
            a(view, i, false);
        }

        public void onInitializeAccessibilityEvent(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.i;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.i.canScrollVertically(-1) && !this.i.canScrollHorizontally(-1) && !this.i.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            Adapter adapter = this.i.s;
            if (adapter != null) {
                accessibilityEvent.setItemCount(adapter.getItemCount());
            }
        }

        public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull State state, @NonNull View view, @Nullable View view2) {
            return onRequestChildFocus(recyclerView, view, view2);
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z, boolean z2) {
            int[] e = e(view, rect);
            int i = e[0];
            int i2 = e[1];
            if ((!z2 || g(recyclerView, i, i2)) && !(i == 0 && i2 == 0)) {
                if (z) {
                    recyclerView.scrollBy(i, i2);
                } else {
                    recyclerView.smoothScrollBy(i, i2);
                }
                return true;
            }
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
            if (r5 == 1073741824) goto L8;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static int getChildMeasureSpec(int r4, int r5, int r6, int r7, boolean r8) {
            /*
                int r4 = r4 - r6
                r6 = 0
                int r4 = java.lang.Math.max(r6, r4)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r8 == 0) goto L1a
                if (r7 < 0) goto L11
                goto L1c
            L11:
                if (r7 != r1) goto L2f
                if (r5 == r2) goto L20
                if (r5 == 0) goto L2f
                if (r5 == r3) goto L20
                goto L2f
            L1a:
                if (r7 < 0) goto L1e
            L1c:
                r5 = r3
                goto L31
            L1e:
                if (r7 != r1) goto L22
            L20:
                r7 = r4
                goto L31
            L22:
                if (r7 != r0) goto L2f
                if (r5 == r2) goto L2c
                if (r5 != r3) goto L29
                goto L2c
            L29:
                r7 = r4
                r5 = r6
                goto L31
            L2c:
                r7 = r4
                r5 = r2
                goto L31
            L2f:
                r5 = r6
                r7 = r5
            L31:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.getChildMeasureSpec(int, int, int, int, boolean):int");
        }

        public void attachView(@NonNull View view, int i) {
            attachView(view, i, (LayoutParams) view.getLayoutParams());
        }

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public void setMeasuredDimension(int i, int i2) {
            this.i.setMeasuredDimension(i, i2);
        }

        public void attachView(@NonNull View view) {
            attachView(view, -1);
        }
    }

    /* loaded from: classes.dex */
    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(@NonNull View view);

        void onChildViewDetachedFromWindow(@NonNull View view);
    }

    /* loaded from: classes.dex */
    public static abstract class OnFlingListener {
        public abstract boolean onFling(int i, int i2);
    }

    /* loaded from: classes.dex */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);
    }

    /* loaded from: classes.dex */
    public static abstract class OnScrollListener {
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
        }

        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Orientation {
    }

    /* loaded from: classes.dex */
    public static class RecycledViewPool {

        /* renamed from: a  reason: collision with root package name */
        public SparseArray<a> f1603a = new SparseArray<>();
        public int b = 0;

        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            public final ArrayList<ViewHolder> f1604a = new ArrayList<>();
            public int b = 5;
            public long c = 0;
            public long d = 0;
        }

        public void a() {
            this.b++;
        }

        public void b() {
            this.b--;
        }

        public void c(int i, long j) {
            a e = e(i);
            e.d = g(e.d, j);
        }

        public void clear() {
            for (int i = 0; i < this.f1603a.size(); i++) {
                this.f1603a.valueAt(i).f1604a.clear();
            }
        }

        public void d(int i, long j) {
            a e = e(i);
            e.c = g(e.c, j);
        }

        public final a e(int i) {
            a aVar = this.f1603a.get(i);
            if (aVar == null) {
                a aVar2 = new a();
                this.f1603a.put(i, aVar2);
                return aVar2;
            }
            return aVar;
        }

        public void f(Adapter adapter, Adapter adapter2, boolean z) {
            if (adapter != null) {
                b();
            }
            if (!z && this.b == 0) {
                clear();
            }
            if (adapter2 != null) {
                a();
            }
        }

        public long g(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        @Nullable
        public ViewHolder getRecycledView(int i) {
            a aVar = this.f1603a.get(i);
            if (aVar == null || aVar.f1604a.isEmpty()) {
                return null;
            }
            ArrayList<ViewHolder> arrayList = aVar.f1604a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).isAttachedToTransitionOverlay()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        public int getRecycledViewCount(int i) {
            return e(i).f1604a.size();
        }

        public boolean h(int i, long j, long j2) {
            long j3 = e(i).d;
            return j3 == 0 || j + j3 < j2;
        }

        public boolean i(int i, long j, long j2) {
            long j3 = e(i).c;
            return j3 == 0 || j + j3 < j2;
        }

        public void putRecycledView(ViewHolder viewHolder) {
            int itemViewType = viewHolder.getItemViewType();
            ArrayList<ViewHolder> arrayList = e(itemViewType).f1604a;
            if (this.f1603a.get(itemViewType).b <= arrayList.size()) {
                return;
            }
            viewHolder.resetInternal();
            arrayList.add(viewHolder);
        }

        public void setMaxRecycledViews(int i, int i2) {
            a e = e(i);
            e.b = i2;
            ArrayList<ViewHolder> arrayList = e.f1604a;
            while (arrayList.size() > i2) {
                arrayList.remove(arrayList.size() - 1);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class Recycler {

        /* renamed from: a  reason: collision with root package name */
        public final ArrayList<ViewHolder> f1605a;
        public ArrayList<ViewHolder> b;
        public final ArrayList<ViewHolder> c;
        public final List<ViewHolder> d;
        public int e;
        public int f;
        public RecycledViewPool g;
        public ViewCacheExtension h;

        public Recycler() {
            ArrayList<ViewHolder> arrayList = new ArrayList<>();
            this.f1605a = arrayList;
            this.b = null;
            this.c = new ArrayList<>();
            this.d = Collections.unmodifiableList(arrayList);
            this.e = 2;
            this.f = 2;
        }

        public void A(ViewCacheExtension viewCacheExtension) {
            this.h = viewCacheExtension;
        }

        public final boolean B(@NonNull ViewHolder viewHolder, int i, int i2, long j) {
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = RecyclerView.this;
            int itemViewType = viewHolder.getItemViewType();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j == Long.MAX_VALUE || this.g.h(itemViewType, nanoTime, j)) {
                RecyclerView.this.s.bindViewHolder(viewHolder, i);
                this.g.c(viewHolder.getItemViewType(), RecyclerView.this.getNanoTime() - nanoTime);
                b(viewHolder);
                if (RecyclerView.this.p0.isPreLayout()) {
                    viewHolder.mPreLayoutPosition = i2;
                    return true;
                }
                return true;
            }
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x020c  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0228 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0185  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x01a2  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x01c5  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01fe  */
        @androidx.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.recyclerview.widget.RecyclerView.ViewHolder C(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instructions count: 615
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.C(int, boolean, long):androidx.recyclerview.widget.RecyclerView$ViewHolder");
        }

        public void D(ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.b.remove(viewHolder);
            } else {
                this.f1605a.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }

        public void E() {
            LayoutManager layoutManager = RecyclerView.this.t;
            this.f = this.e + (layoutManager != null ? layoutManager.t : 0);
            for (int size = this.c.size() - 1; size >= 0 && this.c.size() > this.f; size--) {
                w(size);
            }
        }

        public boolean F(ViewHolder viewHolder) {
            if (viewHolder.isRemoved()) {
                return RecyclerView.this.p0.isPreLayout();
            }
            int i = viewHolder.mPosition;
            if (i >= 0 && i < RecyclerView.this.s.getItemCount()) {
                if (RecyclerView.this.p0.isPreLayout() || RecyclerView.this.s.getItemViewType(viewHolder.mPosition) == viewHolder.getItemViewType()) {
                    return !RecyclerView.this.s.hasStableIds() || viewHolder.getItemId() == RecyclerView.this.s.getItemId(viewHolder.mPosition);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + viewHolder + RecyclerView.this.J());
        }

        public void G(int i, int i2) {
            int i3;
            int i4 = i2 + i;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.c.get(size);
                if (viewHolder != null && (i3 = viewHolder.mPosition) >= i && i3 < i4) {
                    viewHolder.addFlags(2);
                    w(size);
                }
            }
        }

        public void a(@NonNull ViewHolder viewHolder, boolean z) {
            RecyclerView.n(viewHolder);
            View view = viewHolder.itemView;
            RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.w0;
            if (recyclerViewAccessibilityDelegate != null) {
                AccessibilityDelegateCompat itemDelegate = recyclerViewAccessibilityDelegate.getItemDelegate();
                ViewCompat.setAccessibilityDelegate(view, itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate ? ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).e(view) : null);
            }
            if (z) {
                e(viewHolder);
            }
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = null;
            g().putRecycledView(viewHolder);
        }

        public final void b(ViewHolder viewHolder) {
            if (RecyclerView.this.e0()) {
                View view = viewHolder.itemView;
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.w0;
                if (recyclerViewAccessibilityDelegate == null) {
                    return;
                }
                AccessibilityDelegateCompat itemDelegate = recyclerViewAccessibilityDelegate.getItemDelegate();
                if (itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
                    ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).f(view);
                }
                ViewCompat.setAccessibilityDelegate(view, itemDelegate);
            }
        }

        public void bindViewToPosition(@NonNull View view, int i) {
            LayoutParams layoutParams;
            ViewHolder S = RecyclerView.S(view);
            if (S != null) {
                int m = RecyclerView.this.k.m(i);
                if (m >= 0 && m < RecyclerView.this.s.getItemCount()) {
                    B(S, m, i, Long.MAX_VALUE);
                    ViewGroup.LayoutParams layoutParams2 = S.itemView.getLayoutParams();
                    if (layoutParams2 == null) {
                        layoutParams = (LayoutParams) RecyclerView.this.generateDefaultLayoutParams();
                        S.itemView.setLayoutParams(layoutParams);
                    } else if (!RecyclerView.this.checkLayoutParams(layoutParams2)) {
                        layoutParams = (LayoutParams) RecyclerView.this.generateLayoutParams(layoutParams2);
                        S.itemView.setLayoutParams(layoutParams);
                    } else {
                        layoutParams = (LayoutParams) layoutParams2;
                    }
                    layoutParams.j = true;
                    layoutParams.h = S;
                    layoutParams.k = S.itemView.getParent() == null;
                    return;
                }
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + m + ").state:" + RecyclerView.this.p0.getItemCount() + RecyclerView.this.J());
            }
            throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter" + RecyclerView.this.J());
        }

        public void c() {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                this.c.get(i).clearOldPosition();
            }
            int size2 = this.f1605a.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.f1605a.get(i2).clearOldPosition();
            }
            ArrayList<ViewHolder> arrayList = this.b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    this.b.get(i3).clearOldPosition();
                }
            }
        }

        public void clear() {
            this.f1605a.clear();
            v();
        }

        public int convertPreLayoutPositionToPostLayout(int i) {
            if (i >= 0 && i < RecyclerView.this.p0.getItemCount()) {
                return !RecyclerView.this.p0.isPreLayout() ? i : RecyclerView.this.k.m(i);
            }
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + RecyclerView.this.p0.getItemCount() + RecyclerView.this.J());
        }

        public void d() {
            this.f1605a.clear();
            ArrayList<ViewHolder> arrayList = this.b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public void e(@NonNull ViewHolder viewHolder) {
            RecyclerListener recyclerListener = RecyclerView.this.u;
            if (recyclerListener != null) {
                recyclerListener.onViewRecycled(viewHolder);
            }
            int size = RecyclerView.this.v.size();
            for (int i = 0; i < size; i++) {
                RecyclerView.this.v.get(i).onViewRecycled(viewHolder);
            }
            Adapter adapter = RecyclerView.this.s;
            if (adapter != null) {
                adapter.onViewRecycled(viewHolder);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.p0 != null) {
                recyclerView.m.q(viewHolder);
            }
        }

        public ViewHolder f(int i) {
            int size;
            int m;
            ArrayList<ViewHolder> arrayList = this.b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    ViewHolder viewHolder = this.b.get(i2);
                    if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == i) {
                        viewHolder.addFlags(32);
                        return viewHolder;
                    }
                }
                if (RecyclerView.this.s.hasStableIds() && (m = RecyclerView.this.k.m(i)) > 0 && m < RecyclerView.this.s.getItemCount()) {
                    long itemId = RecyclerView.this.s.getItemId(m);
                    for (int i3 = 0; i3 < size; i3++) {
                        ViewHolder viewHolder2 = this.b.get(i3);
                        if (!viewHolder2.wasReturnedFromScrap() && viewHolder2.getItemId() == itemId) {
                            viewHolder2.addFlags(32);
                            return viewHolder2;
                        }
                    }
                }
            }
            return null;
        }

        public RecycledViewPool g() {
            if (this.g == null) {
                this.g = new RecycledViewPool();
            }
            return this.g;
        }

        @NonNull
        public List<ViewHolder> getScrapList() {
            return this.d;
        }

        @NonNull
        public View getViewForPosition(int i) {
            return l(i, false);
        }

        public int h() {
            return this.f1605a.size();
        }

        public ViewHolder i(long j, int i, boolean z) {
            for (int size = this.f1605a.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.f1605a.get(size);
                if (viewHolder.getItemId() == j && !viewHolder.wasReturnedFromScrap()) {
                    if (i == viewHolder.getItemViewType()) {
                        viewHolder.addFlags(32);
                        if (viewHolder.isRemoved() && !RecyclerView.this.p0.isPreLayout()) {
                            viewHolder.setFlags(2, 14);
                        }
                        return viewHolder;
                    } else if (!z) {
                        this.f1605a.remove(size);
                        RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                        u(viewHolder.itemView);
                    }
                }
            }
            int size2 = this.c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                ViewHolder viewHolder2 = this.c.get(size2);
                if (viewHolder2.getItemId() == j && !viewHolder2.isAttachedToTransitionOverlay()) {
                    if (i == viewHolder2.getItemViewType()) {
                        if (!z) {
                            this.c.remove(size2);
                        }
                        return viewHolder2;
                    } else if (!z) {
                        w(size2);
                        return null;
                    }
                }
            }
        }

        public ViewHolder j(int i, boolean z) {
            View e;
            int size = this.f1605a.size();
            for (int i2 = 0; i2 < size; i2++) {
                ViewHolder viewHolder = this.f1605a.get(i2);
                if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == i && !viewHolder.isInvalid() && (RecyclerView.this.p0.h || !viewHolder.isRemoved())) {
                    viewHolder.addFlags(32);
                    return viewHolder;
                }
            }
            if (!z && (e = RecyclerView.this.l.e(i)) != null) {
                ViewHolder S = RecyclerView.S(e);
                RecyclerView.this.l.s(e);
                int m = RecyclerView.this.l.m(e);
                if (m != -1) {
                    RecyclerView.this.l.d(m);
                    y(e);
                    S.addFlags(V3MessageNotice.TYPE_CHATWORK);
                    return S;
                }
                throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + S + RecyclerView.this.J());
            }
            int size2 = this.c.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ViewHolder viewHolder2 = this.c.get(i3);
                if (!viewHolder2.isInvalid() && viewHolder2.getLayoutPosition() == i && !viewHolder2.isAttachedToTransitionOverlay()) {
                    if (!z) {
                        this.c.remove(i3);
                    }
                    return viewHolder2;
                }
            }
            return null;
        }

        public View k(int i) {
            return this.f1605a.get(i).itemView;
        }

        public View l(int i, boolean z) {
            return C(i, z, Long.MAX_VALUE).itemView;
        }

        public final void m(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    m((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                    return;
                }
                int visibility = viewGroup.getVisibility();
                viewGroup.setVisibility(4);
                viewGroup.setVisibility(visibility);
            }
        }

        public final void n(ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (view instanceof ViewGroup) {
                m((ViewGroup) view, false);
            }
        }

        public void o() {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                LayoutParams layoutParams = (LayoutParams) this.c.get(i).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.j = true;
                }
            }
        }

        public void p() {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                ViewHolder viewHolder = this.c.get(i);
                if (viewHolder != null) {
                    viewHolder.addFlags(6);
                    viewHolder.addChangePayload(null);
                }
            }
            Adapter adapter = RecyclerView.this.s;
            if (adapter == null || !adapter.hasStableIds()) {
                v();
            }
        }

        public void q(int i, int i2) {
            int size = this.c.size();
            for (int i3 = 0; i3 < size; i3++) {
                ViewHolder viewHolder = this.c.get(i3);
                if (viewHolder != null && viewHolder.mPosition >= i) {
                    viewHolder.offsetPosition(i2, false);
                }
            }
        }

        public void r(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            if (i < i2) {
                i3 = -1;
                i5 = i;
                i4 = i2;
            } else {
                i3 = 1;
                i4 = i;
                i5 = i2;
            }
            int size = this.c.size();
            for (int i7 = 0; i7 < size; i7++) {
                ViewHolder viewHolder = this.c.get(i7);
                if (viewHolder != null && (i6 = viewHolder.mPosition) >= i5 && i6 <= i4) {
                    if (i6 == i) {
                        viewHolder.offsetPosition(i2 - i, false);
                    } else {
                        viewHolder.offsetPosition(i3, false);
                    }
                }
            }
        }

        public void recycleView(@NonNull View view) {
            ViewHolder S = RecyclerView.S(view);
            if (S.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (S.isScrap()) {
                S.unScrap();
            } else if (S.wasReturnedFromScrap()) {
                S.clearReturnedFromScrapFlag();
            }
            x(S);
            if (RecyclerView.this.U == null || S.isRecyclable()) {
                return;
            }
            RecyclerView.this.U.endAnimation(S);
        }

        public void s(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.c.get(size);
                if (viewHolder != null) {
                    int i4 = viewHolder.mPosition;
                    if (i4 >= i3) {
                        viewHolder.offsetPosition(-i2, z);
                    } else if (i4 >= i) {
                        viewHolder.addFlags(8);
                        w(size);
                    }
                }
            }
        }

        public void setViewCacheSize(int i) {
            this.e = i;
            E();
        }

        public void t(Adapter adapter, Adapter adapter2, boolean z) {
            clear();
            g().f(adapter, adapter2, z);
        }

        public void u(View view) {
            ViewHolder S = RecyclerView.S(view);
            S.mScrapContainer = null;
            S.mInChangeScrap = false;
            S.clearReturnedFromScrapFlag();
            x(S);
        }

        public void v() {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                w(size);
            }
            this.c.clear();
            if (RecyclerView.N0) {
                RecyclerView.this.o0.a();
            }
        }

        public void w(int i) {
            a(this.c.get(i), true);
            this.c.remove(i);
        }

        public void x(ViewHolder viewHolder) {
            boolean z;
            boolean z2 = true;
            if (!viewHolder.isScrap() && viewHolder.itemView.getParent() == null) {
                if (!viewHolder.isTmpDetached()) {
                    if (!viewHolder.shouldIgnore()) {
                        boolean doesTransientStatePreventRecycling = viewHolder.doesTransientStatePreventRecycling();
                        Adapter adapter = RecyclerView.this.s;
                        if ((adapter != null && doesTransientStatePreventRecycling && adapter.onFailedToRecycleView(viewHolder)) || viewHolder.isRecyclable()) {
                            if (this.f <= 0 || viewHolder.hasAnyOfTheFlags(DfuConstants.PROGRESS_DOWNLOAD_FIRMWARE)) {
                                z = false;
                            } else {
                                int size = this.c.size();
                                if (size >= this.f && size > 0) {
                                    w(0);
                                    size--;
                                }
                                if (RecyclerView.N0 && size > 0 && !RecyclerView.this.o0.c(viewHolder.mPosition)) {
                                    int i = size - 1;
                                    while (i >= 0) {
                                        if (!RecyclerView.this.o0.c(this.c.get(i).mPosition)) {
                                            break;
                                        }
                                        i--;
                                    }
                                    size = i + 1;
                                }
                                this.c.add(size, viewHolder);
                                z = true;
                            }
                            if (z) {
                                z2 = false;
                            } else {
                                a(viewHolder, true);
                            }
                            r1 = z;
                        } else {
                            z2 = false;
                        }
                        RecyclerView.this.m.q(viewHolder);
                        if (r1 || z2 || !doesTransientStatePreventRecycling) {
                            return;
                        }
                        viewHolder.mBindingAdapter = null;
                        viewHolder.mOwnerRecyclerView = null;
                        return;
                    }
                    throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.J());
                }
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder + RecyclerView.this.J());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Scrapped or attached views may not be recycled. isScrap:");
            sb.append(viewHolder.isScrap());
            sb.append(" isAttached:");
            sb.append(viewHolder.itemView.getParent() != null);
            sb.append(RecyclerView.this.J());
            throw new IllegalArgumentException(sb.toString());
        }

        public void y(View view) {
            ViewHolder S = RecyclerView.S(view);
            if (!S.hasAnyOfTheFlags(12) && S.isUpdated() && !RecyclerView.this.l(S)) {
                if (this.b == null) {
                    this.b = new ArrayList<>();
                }
                S.setScrapContainer(this, true);
                this.b.add(S);
            } else if (S.isInvalid() && !S.isRemoved() && !RecyclerView.this.s.hasStableIds()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.J());
            } else {
                S.setScrapContainer(this, false);
                this.f1605a.add(S);
            }
        }

        public void z(RecycledViewPool recycledViewPool) {
            RecycledViewPool recycledViewPool2 = this.g;
            if (recycledViewPool2 != null) {
                recycledViewPool2.b();
            }
            this.g = recycledViewPool;
            if (recycledViewPool == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.g.a();
        }
    }

    /* loaded from: classes.dex */
    public interface RecyclerListener {
        void onViewRecycled(@NonNull ViewHolder viewHolder);
    }

    /* loaded from: classes.dex */
    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SmoothScroller {
        public RecyclerView b;
        public LayoutManager c;
        public boolean d;
        public boolean e;
        public View f;
        public boolean h;

        /* renamed from: a  reason: collision with root package name */
        public int f1606a = -1;
        public final Action g = new Action(0, 0);

        /* loaded from: classes.dex */
        public static class Action {
            public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;

            /* renamed from: a  reason: collision with root package name */
            public int f1607a;
            public int b;
            public int c;
            public int d;
            public Interpolator e;
            public boolean f;
            public int g;

            public Action(@Px int i, @Px int i2) {
                this(i, i2, Integer.MIN_VALUE, null);
            }

            public boolean a() {
                return this.d >= 0;
            }

            public void b(RecyclerView recyclerView) {
                int i = this.d;
                if (i >= 0) {
                    this.d = -1;
                    recyclerView.g0(i);
                    this.f = false;
                } else if (this.f) {
                    c();
                    recyclerView.m0.e(this.f1607a, this.b, this.c, this.e);
                    int i2 = this.g + 1;
                    this.g = i2;
                    if (i2 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f = false;
                } else {
                    this.g = 0;
                }
            }

            public final void c() {
                if (this.e != null && this.c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public int getDuration() {
                return this.c;
            }

            @Px
            public int getDx() {
                return this.f1607a;
            }

            @Px
            public int getDy() {
                return this.b;
            }

            @Nullable
            public Interpolator getInterpolator() {
                return this.e;
            }

            public void jumpTo(int i) {
                this.d = i;
            }

            public void setDuration(int i) {
                this.f = true;
                this.c = i;
            }

            public void setDx(@Px int i) {
                this.f = true;
                this.f1607a = i;
            }

            public void setDy(@Px int i) {
                this.f = true;
                this.b = i;
            }

            public void setInterpolator(@Nullable Interpolator interpolator) {
                this.f = true;
                this.e = interpolator;
            }

            public void update(@Px int i, @Px int i2, int i3, @Nullable Interpolator interpolator) {
                this.f1607a = i;
                this.b = i2;
                this.c = i3;
                this.e = interpolator;
                this.f = true;
            }

            public Action(@Px int i, @Px int i2, int i3) {
                this(i, i2, i3, null);
            }

            public Action(@Px int i, @Px int i2, int i3, @Nullable Interpolator interpolator) {
                this.d = -1;
                this.f = false;
                this.g = 0;
                this.f1607a = i;
                this.b = i2;
                this.c = i3;
                this.e = interpolator;
            }
        }

        /* loaded from: classes.dex */
        public interface ScrollVectorProvider {
            @Nullable
            PointF computeScrollVectorForPosition(int i);
        }

        public void a(int i, int i2) {
            PointF computeScrollVectorForPosition;
            RecyclerView recyclerView = this.b;
            if (this.f1606a == -1 || recyclerView == null) {
                stop();
            }
            if (this.d && this.f == null && this.c != null && (computeScrollVectorForPosition = computeScrollVectorForPosition(this.f1606a)) != null) {
                float f = computeScrollVectorForPosition.x;
                if (f != 0.0f || computeScrollVectorForPosition.y != 0.0f) {
                    recyclerView.I0((int) Math.signum(f), (int) Math.signum(computeScrollVectorForPosition.y), null);
                }
            }
            this.d = false;
            View view = this.f;
            if (view != null) {
                if (getChildPosition(view) == this.f1606a) {
                    onTargetFound(this.f, recyclerView.p0, this.g);
                    this.g.b(recyclerView);
                    stop();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f = null;
                }
            }
            if (this.e) {
                onSeekTargetStep(i, i2, recyclerView.p0, this.g);
                boolean a2 = this.g.a();
                this.g.b(recyclerView);
                if (a2 && this.e) {
                    this.d = true;
                    recyclerView.m0.d();
                }
            }
        }

        public void b(RecyclerView recyclerView, LayoutManager layoutManager) {
            recyclerView.m0.f();
            if (this.h) {
                Log.w("RecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.b = recyclerView;
            this.c = layoutManager;
            int i = this.f1606a;
            if (i != -1) {
                recyclerView.p0.f1608a = i;
                this.e = true;
                this.d = true;
                this.f = findViewByPosition(getTargetPosition());
                onStart();
                this.b.m0.d();
                this.h = true;
                return;
            }
            throw new IllegalArgumentException("Invalid target position");
        }

        @Nullable
        public PointF computeScrollVectorForPosition(int i) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(i);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
            return null;
        }

        public View findViewByPosition(int i) {
            return this.b.t.findViewByPosition(i);
        }

        public int getChildCount() {
            return this.b.t.getChildCount();
        }

        public int getChildPosition(View view) {
            return this.b.getChildLayoutPosition(view);
        }

        @Nullable
        public LayoutManager getLayoutManager() {
            return this.c;
        }

        public int getTargetPosition() {
            return this.f1606a;
        }

        @Deprecated
        public void instantScrollToPosition(int i) {
            this.b.scrollToPosition(i);
        }

        public boolean isPendingInitialRun() {
            return this.d;
        }

        public boolean isRunning() {
            return this.e;
        }

        public void normalize(@NonNull PointF pointF) {
            float f = pointF.x;
            float f2 = pointF.y;
            float sqrt = (float) Math.sqrt((f * f) + (f2 * f2));
            pointF.x /= sqrt;
            pointF.y /= sqrt;
        }

        public void onChildAttachedToWindow(View view) {
            if (getChildPosition(view) == getTargetPosition()) {
                this.f = view;
            }
        }

        public abstract void onSeekTargetStep(@Px int i, @Px int i2, @NonNull State state, @NonNull Action action);

        public abstract void onStart();

        public abstract void onStop();

        public abstract void onTargetFound(@NonNull View view, @NonNull State state, @NonNull Action action);

        public void setTargetPosition(int i) {
            this.f1606a = i;
        }

        public final void stop() {
            if (this.e) {
                this.e = false;
                onStop();
                this.b.p0.f1608a = -1;
                this.f = null;
                this.f1606a = -1;
                this.d = false;
                this.c.k(this);
                this.c = null;
                this.b = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class State {
        public SparseArray<Object> b;
        public int m;
        public long n;
        public int o;
        public int p;
        public int q;

        /* renamed from: a  reason: collision with root package name */
        public int f1608a = -1;
        public int c = 0;
        public int d = 0;
        public int e = 1;
        public int f = 0;
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;
        public boolean j = false;
        public boolean k = false;
        public boolean l = false;

        public void a(int i) {
            if ((this.e & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.e));
        }

        public void b(Adapter adapter) {
            this.e = 1;
            this.f = adapter.getItemCount();
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public boolean didStructureChange() {
            return this.g;
        }

        public <T> T get(int i) {
            SparseArray<Object> sparseArray = this.b;
            if (sparseArray == null) {
                return null;
            }
            return (T) sparseArray.get(i);
        }

        public int getItemCount() {
            if (this.h) {
                return this.c - this.d;
            }
            return this.f;
        }

        public int getRemainingScrollHorizontal() {
            return this.p;
        }

        public int getRemainingScrollVertical() {
            return this.q;
        }

        public int getTargetScrollPosition() {
            return this.f1608a;
        }

        public boolean hasTargetScrollPosition() {
            return this.f1608a != -1;
        }

        public boolean isMeasuring() {
            return this.j;
        }

        public boolean isPreLayout() {
            return this.h;
        }

        public void put(int i, Object obj) {
            if (this.b == null) {
                this.b = new SparseArray<>();
            }
            this.b.put(i, obj);
        }

        public void remove(int i) {
            SparseArray<Object> sparseArray = this.b;
            if (sparseArray == null) {
                return;
            }
            sparseArray.remove(i);
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1608a + ", mData=" + this.b + ", mItemCount=" + this.f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.d + ", mStructureChanged=" + this.g + ", mInPreLayout=" + this.h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }

        public boolean willRunPredictiveAnimations() {
            return this.l;
        }

        public boolean willRunSimpleAnimations() {
            return this.k;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ViewCacheExtension {
        @Nullable
        public abstract View getViewForPositionAndType(@NonNull Recycler recycler, int i, int i2);
    }

    /* loaded from: classes.dex */
    public static abstract class ViewHolder {
        public static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        public static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        public static final int FLAG_BOUND = 1;
        public static final int FLAG_IGNORE = 128;
        public static final int FLAG_INVALID = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_NOT_RECYCLABLE = 16;
        public static final int FLAG_REMOVED = 8;
        public static final int FLAG_RETURNED_FROM_SCRAP = 32;
        public static final int FLAG_TMP_DETACHED = 256;
        public static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        public static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        @NonNull
        public final View itemView;
        public Adapter<? extends ViewHolder> mBindingAdapter;
        public int mFlags;
        public WeakReference<RecyclerView> mNestedRecyclerView;
        public RecyclerView mOwnerRecyclerView;
        public int mPosition = -1;
        public int mOldPosition = -1;
        public long mItemId = -1;
        public int mItemViewType = -1;
        public int mPreLayoutPosition = -1;
        public ViewHolder mShadowedHolder = null;
        public ViewHolder mShadowingHolder = null;
        public List<Object> mPayloads = null;
        public List<Object> mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        public Recycler mScrapContainer = null;
        public boolean mInChangeScrap = false;
        private int mWasImportantForAccessibilityBeforeHidden = 0;
        @VisibleForTesting
        public int mPendingAccessibilityState = -1;

        public ViewHolder(@NonNull View view) {
            if (view != null) {
                this.itemView = view;
                return;
            }
            throw new IllegalArgumentException("itemView may not be null");
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
        }

        public void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        public void addFlags(int i) {
            this.mFlags = i | this.mFlags;
        }

        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        public void clearPayload() {
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        public boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        public void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
            addFlags(8);
            offsetPosition(i2, z);
            this.mPosition = i;
        }

        public final int getAbsoluteAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.Q(this);
        }

        @Deprecated
        public final int getAdapterPosition() {
            return getBindingAdapterPosition();
        }

        @Nullable
        public final Adapter<? extends ViewHolder> getBindingAdapter() {
            return this.mBindingAdapter;
        }

        public final int getBindingAdapterPosition() {
            RecyclerView recyclerView;
            Adapter adapter;
            int Q;
            if (this.mBindingAdapter == null || (recyclerView = this.mOwnerRecyclerView) == null || (adapter = recyclerView.getAdapter()) == null || (Q = this.mOwnerRecyclerView.Q(this)) == -1) {
                return -1;
            }
            return adapter.findRelativeAdapterPositionIn(this.mBindingAdapter, this, Q);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int i = this.mPreLayoutPosition;
            return i == -1 ? this.mPosition : i;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        @Deprecated
        public final int getPosition() {
            int i = this.mPreLayoutPosition;
            return i == -1 ? this.mPosition : i;
        }

        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) == 0) {
                List<Object> list = this.mPayloads;
                if (list != null && list.size() != 0) {
                    return this.mUnmodifiedPayloads;
                }
                return FULLUPDATE_PAYLOADS;
            }
            return FULLUPDATE_PAYLOADS;
        }

        public boolean hasAnyOfTheFlags(int i) {
            return (i & this.mFlags) != 0;
        }

        public boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || isInvalid();
        }

        public boolean isAttachedToTransitionOverlay() {
            return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        public boolean isScrap() {
            return this.mScrapContainer != null;
        }

        public boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        public boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        public boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        public void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).j = true;
            }
        }

        public void onEnteredHiddenState(RecyclerView recyclerView) {
            int i = this.mPendingAccessibilityState;
            if (i != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = i;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            }
            recyclerView.K0(this, 4);
        }

        public void onLeftHiddenState(RecyclerView recyclerView) {
            recyclerView.K0(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.n(this);
        }

        public void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        public void setFlags(int i, int i2) {
            this.mFlags = (i & i2) | (this.mFlags & (~i2));
        }

        public final void setIsRecyclable(boolean z) {
            int i = this.mIsRecyclableCount;
            int i2 = z ? i - 1 : i + 1;
            this.mIsRecyclableCount = i2;
            if (i2 < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && i2 == 1) {
                this.mFlags |= 16;
            } else if (z && i2 == 0) {
                this.mFlags &= -17;
            }
        }

        public void setScrapContainer(Recycler recycler, boolean z) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = z;
        }

        public boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        public void stopIgnoring() {
            this.mFlags &= -129;
        }

        public String toString() {
            String simpleName = getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName();
            StringBuilder sb = new StringBuilder(simpleName + "{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public void unScrap() {
            this.mScrapContainer.D(this);
        }

        public boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.C || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.z) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.F) {
                recyclerView2.E = true;
            } else {
                recyclerView2.q();
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ItemAnimator itemAnimator = RecyclerView.this.U;
            if (itemAnimator != null) {
                itemAnimator.runPendingAnimations();
            }
            RecyclerView.this.v0 = false;
        }
    }

    /* loaded from: classes.dex */
    public class c implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* loaded from: classes.dex */
    public class d implements l.b {
        public d() {
        }

        @Override // androidx.recyclerview.widget.l.b
        public void a(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
            RecyclerView.this.g(viewHolder, itemHolderInfo, itemHolderInfo2);
        }

        @Override // androidx.recyclerview.widget.l.b
        public void b(ViewHolder viewHolder) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.t.removeAndRecycleView(viewHolder.itemView, recyclerView.i);
        }

        @Override // androidx.recyclerview.widget.l.b
        public void c(ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo2) {
            RecyclerView.this.i.D(viewHolder);
            RecyclerView.this.i(viewHolder, itemHolderInfo, itemHolderInfo2);
        }

        @Override // androidx.recyclerview.widget.l.b
        public void d(ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2) {
            viewHolder.setIsRecyclable(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.L) {
                if (recyclerView.U.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    RecyclerView.this.r0();
                }
            } else if (recyclerView.U.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                RecyclerView.this.r0();
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements b.InterfaceC0175b {
        public e() {
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public View a(int i) {
            return RecyclerView.this.getChildAt(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public void b(View view) {
            ViewHolder S = RecyclerView.S(view);
            if (S != null) {
                S.onEnteredHiddenState(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public int c() {
            return RecyclerView.this.getChildCount();
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public ViewHolder d(View view) {
            return RecyclerView.S(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public void e(int i) {
            ViewHolder S;
            View a2 = a(i);
            if (a2 != null && (S = RecyclerView.S(a2)) != null) {
                if (S.isTmpDetached() && !S.shouldIgnore()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + S + RecyclerView.this.J());
                }
                S.addFlags(256);
            }
            RecyclerView.this.detachViewFromParent(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public void f(View view, int i) {
            RecyclerView.this.addView(view, i);
            RecyclerView.this.u(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public void g() {
            int c = c();
            for (int i = 0; i < c; i++) {
                View a2 = a(i);
                RecyclerView.this.v(a2);
                a2.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public int h(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public void i(View view) {
            ViewHolder S = RecyclerView.S(view);
            if (S != null) {
                S.onLeftHiddenState(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public void j(int i) {
            View childAt = RecyclerView.this.getChildAt(i);
            if (childAt != null) {
                RecyclerView.this.v(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0175b
        public void k(View view, int i, ViewGroup.LayoutParams layoutParams) {
            ViewHolder S = RecyclerView.S(view);
            if (S != null) {
                if (!S.isTmpDetached() && !S.shouldIgnore()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + S + RecyclerView.this.J());
                }
                S.clearTmpDetachFlag();
            }
            RecyclerView.this.attachViewToParent(view, i, layoutParams);
        }
    }

    /* loaded from: classes.dex */
    public class f implements a.InterfaceC0174a {
        public f() {
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public void a(int i, int i2) {
            RecyclerView.this.l0(i, i2);
            RecyclerView.this.s0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public void c(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public void d(int i, int i2) {
            RecyclerView.this.m0(i, i2, false);
            RecyclerView.this.s0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public void e(int i, int i2, Object obj) {
            RecyclerView.this.Q0(i, i2, obj);
            RecyclerView.this.t0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public ViewHolder f(int i) {
            ViewHolder P = RecyclerView.this.P(i, true);
            if (P == null || RecyclerView.this.l.n(P.itemView)) {
                return null;
            }
            return P;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public void g(int i, int i2) {
            RecyclerView.this.k0(i, i2);
            RecyclerView.this.s0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0174a
        public void h(int i, int i2) {
            RecyclerView.this.m0(i, i2, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.s0 = true;
            recyclerView.p0.d += i2;
        }

        public void i(a.b bVar) {
            int i = bVar.f1634a;
            if (i == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.t.onItemsAdded(recyclerView, bVar.b, bVar.d);
            } else if (i == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.t.onItemsRemoved(recyclerView2, bVar.b, bVar.d);
            } else if (i == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.t.onItemsUpdated(recyclerView3, bVar.b, bVar.d, bVar.c);
            } else if (i != 8) {
            } else {
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.t.onItemsMoved(recyclerView4, bVar.b, bVar.d, 1);
            }
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class g {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1612a;

        static {
            int[] iArr = new int[Adapter.StateRestorationPolicy.values().length];
            f1612a = iArr;
            try {
                iArr[Adapter.StateRestorationPolicy.PREVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1612a[Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class h extends Observable<AdapterDataObserver> {
        public boolean a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onChanged();
            }
        }

        public void c(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeMoved(i, i2, 1);
            }
        }

        public void d(int i, int i2) {
            e(i, i2, null);
        }

        public void e(int i, int i2, @Nullable Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeChanged(i, i2, obj);
            }
        }

        public void f(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeInserted(i, i2);
            }
        }

        public void g(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeRemoved(i, i2);
            }
        }

        public void h() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onStateRestorationPolicyChanged();
            }
        }
    }

    /* loaded from: classes.dex */
    public class i implements ItemAnimator.a {
        public i() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.a
        public void a(ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            if (viewHolder.shouldBeKeptAsChild() || RecyclerView.this.A0(viewHolder.itemView) || !viewHolder.isTmpDetached()) {
                return;
            }
            RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
        }
    }

    /* loaded from: classes.dex */
    public class j extends AdapterDataObserver {
        public j() {
        }

        public void a() {
            if (RecyclerView.M0) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.A && recyclerView.z) {
                    ViewCompat.postOnAnimation(recyclerView, recyclerView.o);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.I = true;
            recyclerView2.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            RecyclerView.this.k(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.p0.g = true;
            recyclerView.u0(true);
            if (RecyclerView.this.k.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            RecyclerView.this.k(null);
            if (RecyclerView.this.k.r(i, i2, obj)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            RecyclerView.this.k(null);
            if (RecyclerView.this.k.s(i, i2)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            RecyclerView.this.k(null);
            if (RecyclerView.this.k.t(i, i2, i3)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            RecyclerView.this.k(null);
            if (RecyclerView.this.k.u(i, i2)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onStateRestorationPolicyChanged() {
            Adapter adapter;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.j == null || (adapter = recyclerView.s) == null || !adapter.canRestoreState()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    public class k implements Runnable {
        public int h;
        public int i;
        public OverScroller j;
        public Interpolator k;
        public boolean l;
        public boolean m;

        public k() {
            Interpolator interpolator = RecyclerView.R0;
            this.k = interpolator;
            this.l = false;
            this.m = false;
            this.j = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        public final int a(int i, int i2) {
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = abs > abs2;
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            if (!z) {
                abs = abs2;
            }
            return Math.min((int) (((abs / width) + 1.0f) * 300.0f), 2000);
        }

        public void b(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.i = 0;
            this.h = 0;
            Interpolator interpolator = this.k;
            Interpolator interpolator2 = RecyclerView.R0;
            if (interpolator != interpolator2) {
                this.k = interpolator2;
                this.j = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.j.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            d();
        }

        public final void c() {
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
        }

        public void d() {
            if (this.l) {
                this.m = true;
            } else {
                c();
            }
        }

        public void e(int i, int i2, int i3, @Nullable Interpolator interpolator) {
            if (i3 == Integer.MIN_VALUE) {
                i3 = a(i, i2);
            }
            int i4 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.R0;
            }
            if (this.k != interpolator) {
                this.k = interpolator;
                this.j = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.i = 0;
            this.h = 0;
            RecyclerView.this.setScrollState(2);
            this.j.startScroll(0, 0, i, i2, i4);
            if (Build.VERSION.SDK_INT < 23) {
                this.j.computeScrollOffset();
            }
            d();
        }

        public void f() {
            RecyclerView.this.removeCallbacks(this);
            this.j.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.t == null) {
                f();
                return;
            }
            this.m = false;
            this.l = true;
            recyclerView.q();
            OverScroller overScroller = this.j;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i3 = currX - this.h;
                int i4 = currY - this.i;
                this.h = currX;
                this.i = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.C0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.dispatchNestedPreScroll(i3, i4, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.C0;
                    i3 -= iArr2[0];
                    i4 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.p(i3, i4);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.s != null) {
                    int[] iArr3 = recyclerView3.C0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.I0(i3, i4, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.C0;
                    i2 = iArr4[0];
                    i = iArr4[1];
                    i3 -= i2;
                    i4 -= i;
                    SmoothScroller smoothScroller = recyclerView4.t.n;
                    if (smoothScroller != null && !smoothScroller.isPendingInitialRun() && smoothScroller.isRunning()) {
                        int itemCount = RecyclerView.this.p0.getItemCount();
                        if (itemCount == 0) {
                            smoothScroller.stop();
                        } else if (smoothScroller.getTargetPosition() >= itemCount) {
                            smoothScroller.setTargetPosition(itemCount - 1);
                            smoothScroller.a(i2, i);
                        } else {
                            smoothScroller.a(i2, i);
                        }
                    }
                } else {
                    i = 0;
                    i2 = 0;
                }
                if (!RecyclerView.this.w.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.C0;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.dispatchNestedScroll(i2, i, i3, i4, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.C0;
                int i5 = i3 - iArr6[0];
                int i6 = i4 - iArr6[1];
                if (i2 != 0 || i != 0) {
                    recyclerView6.C(i2, i);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
                SmoothScroller smoothScroller2 = RecyclerView.this.t.n;
                if (!(smoothScroller2 != null && smoothScroller2.isPendingInitialRun()) && z) {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i7 = i5 < 0 ? -currVelocity : i5 > 0 ? currVelocity : 0;
                        if (i6 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i6 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i7, currVelocity);
                    }
                    if (RecyclerView.N0) {
                        RecyclerView.this.o0.a();
                    }
                } else {
                    d();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    androidx.recyclerview.widget.e eVar = recyclerView7.n0;
                    if (eVar != null) {
                        eVar.f(recyclerView7, i2, i);
                    }
                }
            }
            SmoothScroller smoothScroller3 = RecyclerView.this.t.n;
            if (smoothScroller3 != null && smoothScroller3.isPendingInitialRun()) {
                smoothScroller3.a(0, 0);
            }
            this.l = false;
            if (this.m) {
                c();
                return;
            }
            RecyclerView.this.setScrollState(0);
            RecyclerView.this.stopNestedScroll(1);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        K0 = i2 == 18 || i2 == 19 || i2 == 20;
        L0 = i2 >= 23;
        M0 = i2 >= 16;
        N0 = i2 >= 21;
        O0 = i2 <= 15;
        P0 = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        Q0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        R0 = new c();
    }

    public RecyclerView(@NonNull Context context) {
        this(context, null);
    }

    @Nullable
    public static RecyclerView N(@NonNull View view) {
        if (view instanceof ViewGroup) {
            if (view instanceof RecyclerView) {
                return (RecyclerView) view;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                RecyclerView N = N(viewGroup.getChildAt(i2));
                if (N != null) {
                    return N;
                }
            }
            return null;
        }
        return null;
    }

    public static ViewHolder S(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).h;
    }

    public static void T(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.i;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.z0 == null) {
            this.z0 = new NestedScrollingChildHelper(this);
        }
        return this.z0;
    }

    public static void n(@NonNull ViewHolder viewHolder) {
        WeakReference<RecyclerView> weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == viewHolder.itemView) {
                    return;
                }
                ViewParent parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            viewHolder.mNestedRecyclerView = null;
        }
    }

    public final void A() {
        this.p0.a(4);
        N0();
        n0();
        State state = this.p0;
        state.e = 1;
        if (state.k) {
            for (int g2 = this.l.g() - 1; g2 >= 0; g2--) {
                ViewHolder S = S(this.l.f(g2));
                if (!S.shouldIgnore()) {
                    long R = R(S);
                    ItemAnimator.ItemHolderInfo recordPostLayoutInformation = this.U.recordPostLayoutInformation(this.p0, S);
                    ViewHolder g3 = this.m.g(R);
                    if (g3 != null && !g3.shouldIgnore()) {
                        boolean h2 = this.m.h(g3);
                        boolean h3 = this.m.h(S);
                        if (h2 && g3 == S) {
                            this.m.d(S, recordPostLayoutInformation);
                        } else {
                            ItemAnimator.ItemHolderInfo n = this.m.n(g3);
                            this.m.d(S, recordPostLayoutInformation);
                            ItemAnimator.ItemHolderInfo m = this.m.m(S);
                            if (n == null) {
                                X(R, S, g3);
                            } else {
                                h(g3, S, n, m, h2, h3);
                            }
                        }
                    } else {
                        this.m.d(S, recordPostLayoutInformation);
                    }
                }
            }
            this.m.o(this.I0);
        }
        this.t.n(this.i);
        State state2 = this.p0;
        state2.c = state2.f;
        this.L = false;
        this.M = false;
        state2.k = false;
        state2.l = false;
        this.t.o = false;
        ArrayList<ViewHolder> arrayList = this.i.b;
        if (arrayList != null) {
            arrayList.clear();
        }
        LayoutManager layoutManager = this.t;
        if (layoutManager.u) {
            layoutManager.t = 0;
            layoutManager.u = false;
            this.i.E();
        }
        this.t.onLayoutCompleted(this.p0);
        o0();
        O0(false);
        this.m.f();
        int[] iArr = this.y0;
        if (t(iArr[0], iArr[1])) {
            C(0, 0);
        }
        x0();
        D0();
    }

    public boolean A0(View view) {
        N0();
        boolean r = this.l.r(view);
        if (r) {
            ViewHolder S = S(view);
            this.i.D(S);
            this.i.x(S);
        }
        O0(!r);
        return r;
    }

    public void B(int i2) {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.onScrollStateChanged(i2);
        }
        onScrollStateChanged(i2);
        OnScrollListener onScrollListener = this.q0;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(this, i2);
        }
        List<OnScrollListener> list = this.r0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.r0.get(size).onScrollStateChanged(this, i2);
            }
        }
    }

    public void B0() {
        ViewHolder viewHolder;
        int g2 = this.l.g();
        for (int i2 = 0; i2 < g2; i2++) {
            View f2 = this.l.f(i2);
            ViewHolder childViewHolder = getChildViewHolder(f2);
            if (childViewHolder != null && (viewHolder = childViewHolder.mShadowingHolder) != null) {
                View view = viewHolder.itemView;
                int left = f2.getLeft();
                int top = f2.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void C(int i2, int i3) {
        this.O++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        onScrolled(i2, i3);
        OnScrollListener onScrollListener = this.q0;
        if (onScrollListener != null) {
            onScrollListener.onScrolled(this, i2, i3);
        }
        List<OnScrollListener> list = this.r0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.r0.get(size).onScrolled(this, i2, i3);
            }
        }
        this.O--;
    }

    public final void C0(@NonNull View view, @Nullable View view2) {
        View view3 = view2 != null ? view2 : view;
        this.p.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.j) {
                Rect rect = layoutParams2.i;
                Rect rect2 = this.p;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.p);
            offsetRectIntoDescendantCoords(view, this.p);
        }
        this.t.requestChildRectangleOnScreen(this, view, this.p, !this.C, view2 == null);
    }

    public void D() {
        int i2;
        for (int size = this.D0.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = this.D0.get(size);
            if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i2 = viewHolder.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(viewHolder.itemView, i2);
                viewHolder.mPendingAccessibilityState = -1;
            }
        }
        this.D0.clear();
    }

    public final void D0() {
        State state = this.p0;
        state.n = -1L;
        state.m = -1;
        state.o = -1;
    }

    public final boolean E(MotionEvent motionEvent) {
        OnItemTouchListener onItemTouchListener = this.y;
        if (onItemTouchListener == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return L(motionEvent);
        }
        onItemTouchListener.onTouchEvent(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.y = null;
        }
        return true;
    }

    public final void E0() {
        VelocityTracker velocityTracker = this.a0;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        y0();
    }

    public void F() {
        if (this.T != null) {
            return;
        }
        EdgeEffect createEdgeEffect = this.P.createEdgeEffect(this, 3);
        this.T = createEdgeEffect;
        if (this.n) {
            createEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            createEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void F0() {
        int absoluteAdapterPosition;
        View focusedChild = (this.l0 && hasFocus() && this.s != null) ? getFocusedChild() : null;
        ViewHolder findContainingViewHolder = focusedChild != null ? findContainingViewHolder(focusedChild) : null;
        if (findContainingViewHolder == null) {
            D0();
            return;
        }
        this.p0.n = this.s.hasStableIds() ? findContainingViewHolder.getItemId() : -1L;
        State state = this.p0;
        if (this.L) {
            absoluteAdapterPosition = -1;
        } else {
            absoluteAdapterPosition = findContainingViewHolder.isRemoved() ? findContainingViewHolder.mOldPosition : findContainingViewHolder.getAbsoluteAdapterPosition();
        }
        state.m = absoluteAdapterPosition;
        this.p0.o = U(findContainingViewHolder.itemView);
    }

    public void G() {
        if (this.Q != null) {
            return;
        }
        EdgeEffect createEdgeEffect = this.P.createEdgeEffect(this, 0);
        this.Q = createEdgeEffect;
        if (this.n) {
            createEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            createEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void G0() {
        int j2 = this.l.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ViewHolder S = S(this.l.i(i2));
            if (!S.shouldIgnore()) {
                S.saveOldPosition();
            }
        }
    }

    public void H() {
        if (this.S != null) {
            return;
        }
        EdgeEffect createEdgeEffect = this.P.createEdgeEffect(this, 2);
        this.S = createEdgeEffect;
        if (this.n) {
            createEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            createEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public boolean H0(int i2, int i3, MotionEvent motionEvent, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        q();
        if (this.s != null) {
            int[] iArr = this.C0;
            iArr[0] = 0;
            iArr[1] = 0;
            I0(i2, i3, iArr);
            int[] iArr2 = this.C0;
            int i9 = iArr2[0];
            int i10 = iArr2[1];
            i5 = i10;
            i6 = i9;
            i7 = i2 - i9;
            i8 = i3 - i10;
        } else {
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
        }
        if (!this.w.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.C0;
        iArr3[0] = 0;
        iArr3[1] = 0;
        dispatchNestedScroll(i6, i5, i7, i8, this.A0, i4, iArr3);
        int[] iArr4 = this.C0;
        int i11 = i7 - iArr4[0];
        int i12 = i8 - iArr4[1];
        boolean z = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i13 = this.d0;
        int[] iArr5 = this.A0;
        this.d0 = i13 - iArr5[0];
        this.e0 -= iArr5[1];
        int[] iArr6 = this.B0;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !MotionEventCompat.isFromSource(motionEvent, 8194)) {
                v0(motionEvent.getX(), i11, motionEvent.getY(), i12);
            }
            p(i2, i3);
        }
        if (i6 != 0 || i5 != 0) {
            C(i6, i5);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z && i6 == 0 && i5 == 0) ? false : true;
    }

    public void I() {
        if (this.R != null) {
            return;
        }
        EdgeEffect createEdgeEffect = this.P.createEdgeEffect(this, 1);
        this.R = createEdgeEffect;
        if (this.n) {
            createEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            createEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void I0(int i2, int i3, @Nullable int[] iArr) {
        N0();
        n0();
        TraceCompat.beginSection("RV Scroll");
        K(this.p0);
        int scrollHorizontallyBy = i2 != 0 ? this.t.scrollHorizontallyBy(i2, this.i, this.p0) : 0;
        int scrollVerticallyBy = i3 != 0 ? this.t.scrollVerticallyBy(i3, this.i, this.p0) : 0;
        TraceCompat.endSection();
        B0();
        o0();
        O0(false);
        if (iArr != null) {
            iArr[0] = scrollHorizontallyBy;
            iArr[1] = scrollVerticallyBy;
        }
    }

    public String J() {
        return HexStringBuilder.DEFAULT_SEPARATOR + super.toString() + ", adapter:" + this.s + ", layout:" + this.t + ", context:" + getContext();
    }

    public final void J0(@Nullable Adapter adapter, boolean z, boolean z2) {
        Adapter adapter2 = this.s;
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.h);
            this.s.onDetachedFromRecyclerView(this);
        }
        if (!z || z2) {
            z0();
        }
        this.k.y();
        Adapter adapter3 = this.s;
        this.s = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.h);
            adapter.onAttachedToRecyclerView(this);
        }
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged(adapter3, this.s);
        }
        this.i.t(adapter3, this.s, z);
        this.p0.g = true;
    }

    public final void K(State state) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.m0.j;
            state.p = overScroller.getFinalX() - overScroller.getCurrX();
            state.q = overScroller.getFinalY() - overScroller.getCurrY();
            return;
        }
        state.p = 0;
        state.q = 0;
    }

    @VisibleForTesting
    public boolean K0(ViewHolder viewHolder, int i2) {
        if (isComputingLayout()) {
            viewHolder.mPendingAccessibilityState = i2;
            this.D0.add(viewHolder);
            return false;
        }
        ViewCompat.setImportantForAccessibility(viewHolder.itemView, i2);
        return true;
    }

    public final boolean L(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.x.size();
        for (int i2 = 0; i2 < size; i2++) {
            OnItemTouchListener onItemTouchListener = this.x.get(i2);
            if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.y = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    public boolean L0(AccessibilityEvent accessibilityEvent) {
        if (isComputingLayout()) {
            int contentChangeTypes = accessibilityEvent != null ? AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent) : 0;
            this.H |= contentChangeTypes != 0 ? contentChangeTypes : 0;
            return true;
        }
        return false;
    }

    public final void M(int[] iArr) {
        int g2 = this.l.g();
        if (g2 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < g2; i4++) {
            ViewHolder S = S(this.l.f(i4));
            if (!S.shouldIgnore()) {
                int layoutPosition = S.getLayoutPosition();
                if (layoutPosition < i2) {
                    i2 = layoutPosition;
                }
                if (layoutPosition > i3) {
                    i3 = layoutPosition;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    public void M0(@Px int i2, @Px int i3, @Nullable Interpolator interpolator, int i4, boolean z) {
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.F) {
        } else {
            if (!layoutManager.canScrollHorizontally()) {
                i2 = 0;
            }
            if (!this.t.canScrollVertically()) {
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return;
            }
            if (i4 == Integer.MIN_VALUE || i4 > 0) {
                if (z) {
                    int i5 = i2 != 0 ? 1 : 0;
                    if (i3 != 0) {
                        i5 |= 2;
                    }
                    startNestedScroll(i5, 1);
                }
                this.m0.e(i2, i3, i4, interpolator);
                return;
            }
            scrollBy(i2, i3);
        }
    }

    public void N0() {
        int i2 = this.D + 1;
        this.D = i2;
        if (i2 != 1 || this.F) {
            return;
        }
        this.E = false;
    }

    @Nullable
    public final View O() {
        ViewHolder findViewHolderForAdapterPosition;
        State state = this.p0;
        int i2 = state.m;
        if (i2 == -1) {
            i2 = 0;
        }
        int itemCount = state.getItemCount();
        for (int i3 = i2; i3 < itemCount; i3++) {
            ViewHolder findViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i3);
            if (findViewHolderForAdapterPosition2 == null) {
                break;
            } else if (findViewHolderForAdapterPosition2.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition2.itemView;
            }
        }
        int min = Math.min(itemCount, i2);
        while (true) {
            min--;
            if (min < 0 || (findViewHolderForAdapterPosition = findViewHolderForAdapterPosition(min)) == null) {
                return null;
            }
            if (findViewHolderForAdapterPosition.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition.itemView;
            }
        }
    }

    public void O0(boolean z) {
        if (this.D < 1) {
            this.D = 1;
        }
        if (!z && !this.F) {
            this.E = false;
        }
        if (this.D == 1) {
            if (z && this.E && !this.F && this.t != null && this.s != null) {
                x();
            }
            if (!this.F) {
                this.E = false;
            }
        }
        this.D--;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0036 A[SYNTHETIC] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.recyclerview.widget.RecyclerView.ViewHolder P(int r6, boolean r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.b r0 = r5.l
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            androidx.recyclerview.widget.b r3 = r5.l
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$ViewHolder r3 = S(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.isRemoved()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.mPosition
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.getLayoutPosition()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.b r1 = r5.l
            android.view.View r4 = r3.itemView
            boolean r1 = r1.n(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.P(int, boolean):androidx.recyclerview.widget.RecyclerView$ViewHolder");
    }

    public final void P0() {
        this.m0.f();
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.w();
        }
    }

    public int Q(ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET) || !viewHolder.isBound()) {
            return -1;
        }
        return this.k.e(viewHolder.mPosition);
    }

    public void Q0(int i2, int i3, Object obj) {
        int i4;
        int j2 = this.l.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < j2; i6++) {
            View i7 = this.l.i(i6);
            ViewHolder S = S(i7);
            if (S != null && !S.shouldIgnore() && (i4 = S.mPosition) >= i2 && i4 < i5) {
                S.addFlags(2);
                S.addChangePayload(obj);
                ((LayoutParams) i7.getLayoutParams()).j = true;
            }
        }
        this.i.G(i2, i3);
    }

    public long R(ViewHolder viewHolder) {
        return this.s.hasStableIds() ? viewHolder.getItemId() : viewHolder.mPosition;
    }

    public final int U(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    public final String V(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        } else if (str.contains(".")) {
            return str;
        } else {
            return RecyclerView.class.getPackage().getName() + '.' + str;
        }
    }

    public Rect W(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.j) {
            return layoutParams.i;
        }
        if (this.p0.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid())) {
            return layoutParams.i;
        }
        Rect rect = layoutParams.i;
        rect.set(0, 0, 0, 0);
        int size = this.w.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.p.set(0, 0, 0, 0);
            this.w.get(i2).getItemOffsets(this.p, view, this, this.p0);
            int i3 = rect.left;
            Rect rect2 = this.p;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.j = false;
        return rect;
    }

    public final void X(long j2, ViewHolder viewHolder, ViewHolder viewHolder2) {
        int g2 = this.l.g();
        for (int i2 = 0; i2 < g2; i2++) {
            ViewHolder S = S(this.l.f(i2));
            if (S != viewHolder && R(S) == j2) {
                Adapter adapter = this.s;
                if (adapter != null && adapter.hasStableIds()) {
                    throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + S + " \n View Holder 2:" + viewHolder + J());
                }
                throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + S + " \n View Holder 2:" + viewHolder + J());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + viewHolder2 + " cannot be found but it is necessary for " + viewHolder + J());
    }

    public final boolean Y() {
        int g2 = this.l.g();
        for (int i2 = 0; i2 < g2; i2++) {
            ViewHolder S = S(this.l.f(i2));
            if (S != null && !S.shouldIgnore() && S.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    public void Z() {
        this.k = new androidx.recyclerview.widget.a(new f());
    }

    public void a(int i2, int i3) {
        if (i2 < 0) {
            G();
            if (this.Q.isFinished()) {
                this.Q.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            H();
            if (this.S.isFinished()) {
                this.S.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            I();
            if (this.R.isFinished()) {
                this.R.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            F();
            if (this.T.isFinished()) {
                this.T.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @SuppressLint({"InlinedApi"})
    public final void a0() {
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        LayoutManager layoutManager = this.t;
        if (layoutManager == null || !layoutManager.onAddFocusables(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration, int i2) {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.w.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.w.add(itemDecoration);
        } else {
            this.w.add(i2, itemDecoration);
        }
        h0();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.K == null) {
            this.K = new ArrayList();
        }
        this.K.add(onChildAttachStateChangeListener);
    }

    public void addOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.x.add(onItemTouchListener);
    }

    public void addOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        if (this.r0 == null) {
            this.r0 = new ArrayList();
        }
        this.r0.add(onScrollListener);
    }

    public void addRecyclerListener(@NonNull RecyclerListener recyclerListener) {
        Preconditions.checkArgument(recyclerListener != null, "'listener' arg cannot be null.");
        this.v.add(recyclerListener);
    }

    public final void b0() {
        this.l = new androidx.recyclerview.widget.b(new e());
    }

    @VisibleForTesting
    public void c0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new androidx.recyclerview.widget.d(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
            return;
        }
        throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + J());
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.t.checkLayoutParams((LayoutParams) layoutParams);
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<OnChildAttachStateChangeListener> list = this.K;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List<OnScrollListener> list = this.r0;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.t.computeHorizontalScrollExtent(this.p0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.t.computeHorizontalScrollOffset(this.p0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.t.computeHorizontalScrollRange(this.p0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.t.computeVerticalScrollExtent(this.p0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.t.computeVerticalScrollOffset(this.p0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.t.computeVerticalScrollRange(this.p0);
        }
        return 0;
    }

    public void d0() {
        this.T = null;
        this.R = null;
        this.S = null;
        this.Q = null;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        int size = this.w.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            this.w.get(i2).onDrawOver(canvas, this, this.p0);
        }
        EdgeEffect edgeEffect = this.Q;
        boolean z3 = true;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.n ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.Q;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.R;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.n) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.R;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.S;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.n ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.S;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.T;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.n) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.T;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(save4);
        }
        if (z || this.U == null || this.w.size() <= 0 || !this.U.isRunning()) {
            z3 = z;
        }
        if (z3) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public boolean e0() {
        AccessibilityManager accessibilityManager = this.J;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public final void f(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean z = view.getParent() == this;
        this.i.D(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.l.c(view, -1, view.getLayoutParams(), true);
        } else if (!z) {
            this.l.b(view, true);
        } else {
            this.l.k(view);
        }
    }

    public final boolean f0(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || view2 == view || findContainingItemView(view2) == null) {
            return false;
        }
        if (view == null || findContainingItemView(view) == null) {
            return true;
        }
        this.p.set(0, 0, view.getWidth(), view.getHeight());
        this.q.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.p);
        offsetDescendantRectToMyCoords(view2, this.q);
        char c2 = 65535;
        int i4 = this.t.getLayoutDirection() == 1 ? -1 : 1;
        Rect rect = this.p;
        int i5 = rect.left;
        Rect rect2 = this.q;
        int i6 = rect2.left;
        if ((i5 < i6 || rect.right <= i6) && rect.right < rect2.right) {
            i3 = 1;
        } else {
            int i7 = rect.right;
            int i8 = rect2.right;
            i3 = ((i7 > i8 || i5 >= i8) && i5 > i6) ? -1 : 0;
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        if ((i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom) {
            c2 = 1;
        } else {
            int i11 = rect.bottom;
            int i12 = rect2.bottom;
            if ((i11 <= i12 && i9 < i12) || i9 <= i10) {
                c2 = 0;
            }
        }
        if (i2 == 1) {
            return c2 < 0 || (c2 == 0 && i3 * i4 < 0);
        } else if (i2 == 2) {
            return c2 > 0 || (c2 == 0 && i3 * i4 > 0);
        } else if (i2 == 17) {
            return i3 < 0;
        } else if (i2 == 33) {
            return c2 < 0;
        } else if (i2 == 66) {
            return i3 > 0;
        } else if (i2 == 130) {
            return c2 > 0;
        } else {
            throw new IllegalArgumentException("Invalid direction: " + i2 + J());
        }
    }

    @Nullable
    public View findChildViewUnder(float f2, float f3) {
        for (int g2 = this.l.g() - 1; g2 >= 0; g2--) {
            View f4 = this.l.f(g2);
            float translationX = f4.getTranslationX();
            float translationY = f4.getTranslationY();
            if (f2 >= f4.getLeft() + translationX && f2 <= f4.getRight() + translationX && f3 >= f4.getTop() + translationY && f3 <= f4.getBottom() + translationY) {
                return f4;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
        return r3;
     */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View findContainingItemView(@androidx.annotation.NonNull android.view.View r3) {
        /*
            r2 = this;
            android.view.ViewParent r0 = r3.getParent()
        L4:
            if (r0 == 0) goto L14
            if (r0 == r2) goto L14
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L14
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r0 = r3.getParent()
            goto L4
        L14:
            if (r0 != r2) goto L17
            goto L18
        L17:
            r3 = 0
        L18:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findContainingItemView(android.view.View):android.view.View");
    }

    @Nullable
    public ViewHolder findContainingViewHolder(@NonNull View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(findContainingItemView);
    }

    @Nullable
    public ViewHolder findViewHolderForAdapterPosition(int i2) {
        ViewHolder viewHolder = null;
        if (this.L) {
            return null;
        }
        int j2 = this.l.j();
        for (int i3 = 0; i3 < j2; i3++) {
            ViewHolder S = S(this.l.i(i3));
            if (S != null && !S.isRemoved() && Q(S) == i2) {
                if (!this.l.n(S.itemView)) {
                    return S;
                }
                viewHolder = S;
            }
        }
        return viewHolder;
    }

    public ViewHolder findViewHolderForItemId(long j2) {
        Adapter adapter = this.s;
        ViewHolder viewHolder = null;
        if (adapter != null && adapter.hasStableIds()) {
            int j3 = this.l.j();
            for (int i2 = 0; i2 < j3; i2++) {
                ViewHolder S = S(this.l.i(i2));
                if (S != null && !S.isRemoved() && S.getItemId() == j2) {
                    if (!this.l.n(S.itemView)) {
                        return S;
                    }
                    viewHolder = S;
                }
            }
        }
        return viewHolder;
    }

    @Nullable
    public ViewHolder findViewHolderForLayoutPosition(int i2) {
        return P(i2, false);
    }

    @Nullable
    @Deprecated
    public ViewHolder findViewHolderForPosition(int i2) {
        return P(i2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public boolean fling(int i2, int i3) {
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.F) {
            return false;
        } else {
            int canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.t.canScrollVertically();
            if (canScrollHorizontally == 0 || Math.abs(i2) < this.h0) {
                i2 = 0;
            }
            if (!canScrollVertically || Math.abs(i3) < this.h0) {
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            float f2 = i2;
            float f3 = i3;
            if (!dispatchNestedPreFling(f2, f3)) {
                boolean z = canScrollHorizontally != 0 || canScrollVertically;
                dispatchNestedFling(f2, f3, z);
                OnFlingListener onFlingListener = this.g0;
                if (onFlingListener != null && onFlingListener.onFling(i2, i3)) {
                    return true;
                }
                if (z) {
                    if (canScrollVertically) {
                        canScrollHorizontally = (canScrollHorizontally == true ? 1 : 0) | 2;
                    }
                    startNestedScroll(canScrollHorizontally, 1);
                    int i4 = this.i0;
                    int max = Math.max(-i4, Math.min(i2, i4));
                    int i5 = this.i0;
                    this.m0.b(max, Math.max(-i5, Math.min(i3, i5)));
                    return true;
                }
            }
            return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View view2;
        boolean z;
        View onInterceptFocusSearch = this.t.onInterceptFocusSearch(view, i2);
        if (onInterceptFocusSearch != null) {
            return onInterceptFocusSearch;
        }
        boolean z2 = (this.s == null || this.t == null || isComputingLayout() || this.F) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z2 && (i2 == 2 || i2 == 1)) {
            if (this.t.canScrollVertically()) {
                int i3 = i2 == 2 ? 130 : 33;
                z = focusFinder.findNextFocus(this, view, i3) == null;
                if (O0) {
                    i2 = i3;
                }
            } else {
                z = false;
            }
            if (!z && this.t.canScrollHorizontally()) {
                int i4 = (this.t.getLayoutDirection() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z3 = focusFinder.findNextFocus(this, view, i4) == null;
                if (O0) {
                    i2 = i4;
                }
                z = z3;
            }
            if (z) {
                q();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                N0();
                this.t.onFocusSearchFailed(view, i2, this.i, this.p0);
                O0(false);
            }
            view2 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View findNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (findNextFocus == null && z2) {
                q();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                N0();
                view2 = this.t.onFocusSearchFailed(view, i2, this.i, this.p0);
                O0(false);
            } else {
                view2 = findNextFocus;
            }
        }
        if (view2 == null || view2.hasFocusable()) {
            return f0(view, view2, i2) ? view2 : super.focusSearch(view, i2);
        } else if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        } else {
            C0(view2, null);
            return view;
        }
    }

    public void g(@NonNull ViewHolder viewHolder, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.U.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            r0();
        }
    }

    public void g0(int i2) {
        if (this.t == null) {
            return;
        }
        setScrollState(2);
        this.t.scrollToPosition(i2);
        awakenScrollBars();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + J());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + J());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    @Nullable
    public Adapter getAdapter() {
        return this.s;
    }

    @Override // android.view.View
    public int getBaseline() {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            return layoutManager.getBaseline();
        }
        return super.getBaseline();
    }

    public int getChildAdapterPosition(@NonNull View view) {
        ViewHolder S = S(view);
        if (S != null) {
            return S.getAbsoluteAdapterPosition();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        ChildDrawingOrderCallback childDrawingOrderCallback = this.x0;
        if (childDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(i2, i3);
        }
        return childDrawingOrderCallback.onGetChildDrawingOrder(i2, i3);
    }

    public long getChildItemId(@NonNull View view) {
        ViewHolder S;
        Adapter adapter = this.s;
        if (adapter == null || !adapter.hasStableIds() || (S = S(view)) == null) {
            return -1L;
        }
        return S.getItemId();
    }

    public int getChildLayoutPosition(@NonNull View view) {
        ViewHolder S = S(view);
        if (S != null) {
            return S.getLayoutPosition();
        }
        return -1;
    }

    @Deprecated
    public int getChildPosition(@NonNull View view) {
        return getChildAdapterPosition(view);
    }

    public ViewHolder getChildViewHolder(@NonNull View view) {
        ViewParent parent = view.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
        }
        return S(view);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.n;
    }

    @Nullable
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.w0;
    }

    public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
        T(view, rect);
    }

    @NonNull
    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.P;
    }

    @Nullable
    public ItemAnimator getItemAnimator() {
        return this.U;
    }

    @NonNull
    public ItemDecoration getItemDecorationAt(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 >= 0 && i2 < itemDecorationCount) {
            return this.w.get(i2);
        }
        throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
    }

    public int getItemDecorationCount() {
        return this.w.size();
    }

    @Nullable
    public LayoutManager getLayoutManager() {
        return this.t;
    }

    public int getMaxFlingVelocity() {
        return this.i0;
    }

    public int getMinFlingVelocity() {
        return this.h0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNanoTime() {
        if (N0) {
            return System.nanoTime();
        }
        return 0L;
    }

    @Nullable
    public OnFlingListener getOnFlingListener() {
        return this.g0;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.l0;
    }

    @NonNull
    public RecycledViewPool getRecycledViewPool() {
        return this.i.g();
    }

    public int getScrollState() {
        return this.V;
    }

    public final void h(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2, boolean z, boolean z2) {
        viewHolder.setIsRecyclable(false);
        if (z) {
            f(viewHolder);
        }
        if (viewHolder != viewHolder2) {
            if (z2) {
                f(viewHolder2);
            }
            viewHolder.mShadowedHolder = viewHolder2;
            f(viewHolder);
            this.i.D(viewHolder);
            viewHolder2.setIsRecyclable(false);
            viewHolder2.mShadowingHolder = viewHolder;
        }
        if (this.U.animateChange(viewHolder, viewHolder2, itemHolderInfo, itemHolderInfo2)) {
            r0();
        }
    }

    public void h0() {
        int j2 = this.l.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ((LayoutParams) this.l.i(i2).getLayoutParams()).j = true;
        }
        this.i.o();
    }

    public boolean hasFixedSize() {
        return this.A;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.C || this.L || this.k.p();
    }

    public void i(@NonNull ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        f(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.U.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            r0();
        }
    }

    public void i0() {
        int j2 = this.l.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ViewHolder S = S(this.l.i(i2));
            if (S != null && !S.shouldIgnore()) {
                S.addFlags(6);
            }
        }
        h0();
        this.i.p();
    }

    public void invalidateItemDecorations() {
        if (this.w.size() == 0) {
            return;
        }
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        h0();
        requestLayout();
    }

    public boolean isAnimating() {
        ItemAnimator itemAnimator = this.U;
        return itemAnimator != null && itemAnimator.isRunning();
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.z;
    }

    public boolean isComputingLayout() {
        return this.N > 0;
    }

    @Deprecated
    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.F;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public void j(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + J());
        }
        throw new IllegalStateException(str + J());
    }

    public final void j0(int i2, int i3, @Nullable MotionEvent motionEvent, int i4) {
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.F) {
        } else {
            int[] iArr = this.C0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.t.canScrollVertically();
            startNestedScroll(canScrollVertically ? canScrollHorizontally | 2 : canScrollHorizontally, i4);
            if (dispatchNestedPreScroll(canScrollHorizontally != 0 ? i2 : 0, canScrollVertically ? i3 : 0, this.C0, this.A0, i4)) {
                int[] iArr2 = this.C0;
                i2 -= iArr2[0];
                i3 -= iArr2[1];
            }
            H0(canScrollHorizontally != 0 ? i2 : 0, canScrollVertically ? i3 : 0, motionEvent, i4);
            androidx.recyclerview.widget.e eVar = this.n0;
            if (eVar != null && (i2 != 0 || i3 != 0)) {
                eVar.f(this, i2, i3);
            }
            stopNestedScroll(i4);
        }
    }

    public void k(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + J());
            }
            throw new IllegalStateException(str);
        } else if (this.O > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + J()));
        }
    }

    public void k0(int i2, int i3) {
        int j2 = this.l.j();
        for (int i4 = 0; i4 < j2; i4++) {
            ViewHolder S = S(this.l.i(i4));
            if (S != null && !S.shouldIgnore() && S.mPosition >= i2) {
                S.offsetPosition(i3, false);
                this.p0.g = true;
            }
        }
        this.i.q(i2, i3);
        requestLayout();
    }

    public boolean l(ViewHolder viewHolder) {
        ItemAnimator itemAnimator = this.U;
        return itemAnimator == null || itemAnimator.canReuseUpdatedViewHolder(viewHolder, viewHolder.getUnmodifiedPayloads());
    }

    public void l0(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int j2 = this.l.j();
        if (i2 < i3) {
            i6 = -1;
            i5 = i2;
            i4 = i3;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i8 = 0; i8 < j2; i8++) {
            ViewHolder S = S(this.l.i(i8));
            if (S != null && (i7 = S.mPosition) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    S.offsetPosition(i3 - i2, false);
                } else {
                    S.offsetPosition(i6, false);
                }
                this.p0.g = true;
            }
        }
        this.i.r(i2, i3);
        requestLayout();
    }

    public final void m() {
        E0();
        setScrollState(0);
    }

    public void m0(int i2, int i3, boolean z) {
        int i4 = i2 + i3;
        int j2 = this.l.j();
        for (int i5 = 0; i5 < j2; i5++) {
            ViewHolder S = S(this.l.i(i5));
            if (S != null && !S.shouldIgnore()) {
                int i6 = S.mPosition;
                if (i6 >= i4) {
                    S.offsetPosition(-i3, z);
                    this.p0.g = true;
                } else if (i6 >= i2) {
                    S.flagRemovedAndOffsetPosition(i2 - 1, -i3, z);
                    this.p0.g = true;
                }
            }
        }
        this.i.s(i2, i3, z);
        requestLayout();
    }

    public void n0() {
        this.N++;
    }

    public void nestedScrollBy(int i2, int i3) {
        j0(i2, i3, null, 1);
    }

    public void o() {
        int j2 = this.l.j();
        for (int i2 = 0; i2 < j2; i2++) {
            ViewHolder S = S(this.l.i(i2));
            if (!S.shouldIgnore()) {
                S.clearOldPosition();
            }
        }
        this.i.c();
    }

    public void o0() {
        p0(true);
    }

    public void offsetChildrenHorizontal(@Px int i2) {
        int g2 = this.l.g();
        for (int i3 = 0; i3 < g2; i3++) {
            this.l.f(i3).offsetLeftAndRight(i2);
        }
    }

    public void offsetChildrenVertical(@Px int i2) {
        int g2 = this.l.g();
        for (int i3 = 0; i3 < g2; i3++) {
            this.l.f(i3).offsetTopAndBottom(i2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.N = 0;
        boolean z = true;
        this.z = true;
        if (!this.C || isLayoutRequested()) {
            z = false;
        }
        this.C = z;
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.c(this);
        }
        this.v0 = false;
        if (N0) {
            ThreadLocal<androidx.recyclerview.widget.e> threadLocal = androidx.recyclerview.widget.e.l;
            androidx.recyclerview.widget.e eVar = threadLocal.get();
            this.n0 = eVar;
            if (eVar == null) {
                this.n0 = new androidx.recyclerview.widget.e();
                Display display = ViewCompat.getDisplay(this);
                float f2 = 60.0f;
                if (!isInEditMode() && display != null) {
                    float refreshRate = display.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f2 = refreshRate;
                    }
                }
                androidx.recyclerview.widget.e eVar2 = this.n0;
                eVar2.j = 1.0E9f / f2;
                threadLocal.set(eVar2);
            }
            this.n0.a(this);
        }
    }

    public void onChildAttachedToWindow(@NonNull View view) {
    }

    public void onChildDetachedFromWindow(@NonNull View view) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        androidx.recyclerview.widget.e eVar;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.U;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        stopScroll();
        this.z = false;
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.d(this, this.i);
        }
        this.D0.clear();
        removeCallbacks(this.E0);
        this.m.j();
        if (!N0 || (eVar = this.n0) == null) {
            return;
        }
        eVar.j(this);
        this.n0 = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.w.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.w.get(i2).onDraw(canvas, this, this.p0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r5.t
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.F
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L78
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3e
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r5.t
            boolean r0 = r0.canScrollVertically()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r5.t
            boolean r3 = r3.canScrollHorizontally()
            if (r3 == 0) goto L3c
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L64
        L3c:
            r3 = r2
            goto L64
        L3e:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L62
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r5.t
            boolean r3 = r3.canScrollVertically()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r5.t
            boolean r3 = r3.canScrollHorizontally()
            if (r3 == 0) goto L62
            r3 = r0
            r0 = r2
            goto L64
        L62:
            r0 = r2
            r3 = r0
        L64:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6c
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L78
        L6c:
            float r2 = r5.j0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.k0
            float r0 = r0 * r3
            int r0 = (int) r0
            r3 = 1
            r5.j0(r2, r0, r6, r3)
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.F) {
            return false;
        }
        this.y = null;
        if (L(motionEvent)) {
            m();
            return true;
        }
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            return false;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = this.t.canScrollVertically();
        if (this.a0 == null) {
            this.a0 = VelocityTracker.obtain();
        }
        this.a0.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.G) {
                this.G = false;
            }
            this.W = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.d0 = x;
            this.b0 = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.e0 = y;
            this.c0 = y;
            if (this.V == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                stopNestedScroll(1);
            }
            int[] iArr = this.B0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = canScrollHorizontally;
            if (canScrollVertically) {
                i2 = (canScrollHorizontally ? 1 : 0) | 2;
            }
            startNestedScroll(i2, 0);
        } else if (actionMasked == 1) {
            this.a0.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.W);
            if (findPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.W + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.V != 1) {
                int i3 = x2 - this.b0;
                int i4 = y2 - this.c0;
                if (!canScrollHorizontally || Math.abs(i3) <= this.f0) {
                    z = false;
                } else {
                    this.d0 = x2;
                    z = true;
                }
                if (canScrollVertically && Math.abs(i4) > this.f0) {
                    this.e0 = y2;
                    z = true;
                }
                if (z) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            m();
        } else if (actionMasked == 5) {
            this.W = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.d0 = x3;
            this.b0 = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.e0 = y3;
            this.c0 = y3;
        } else if (actionMasked == 6) {
            q0(motionEvent);
        }
        return this.V == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        TraceCompat.beginSection("RV OnLayout");
        x();
        TraceCompat.endSection();
        this.C = true;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            s(i2, i3);
            return;
        }
        boolean z = false;
        if (layoutManager.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.t.onMeasure(this.i, this.p0, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.F0 = z;
            if (z || this.s == null) {
                return;
            }
            if (this.p0.e == 1) {
                y();
            }
            this.t.q(i2, i3);
            this.p0.j = true;
            z();
            this.t.r(i2, i3);
            if (this.t.u()) {
                this.t.q(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.p0.j = true;
                z();
                this.t.r(i2, i3);
            }
            this.G0 = getMeasuredWidth();
            this.H0 = getMeasuredHeight();
        } else if (this.A) {
            this.t.onMeasure(this.i, this.p0, i2, i3);
        } else {
            if (this.I) {
                N0();
                n0();
                t0();
                o0();
                State state = this.p0;
                if (state.l) {
                    state.h = true;
                } else {
                    this.k.j();
                    this.p0.h = false;
                }
                this.I = false;
                O0(false);
            } else if (this.p0.l) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            Adapter adapter = this.s;
            if (adapter != null) {
                this.p0.f = adapter.getItemCount();
            } else {
                this.p0.f = 0;
            }
            N0();
            this.t.onMeasure(this.i, this.p0, i2, i3);
            O0(false);
            this.p0.h = false;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.j = savedState;
        super.onRestoreInstanceState(savedState.getSuperState());
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.j;
        if (savedState2 != null) {
            savedState.a(savedState2);
        } else {
            LayoutManager layoutManager = this.t;
            if (layoutManager != null) {
                savedState.i = layoutManager.onSaveInstanceState();
            } else {
                savedState.i = null;
            }
        }
        return savedState;
    }

    public void onScrollStateChanged(int i2) {
    }

    public void onScrolled(@Px int i2, @Px int i3) {
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        d0();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instructions count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(int i2, int i3) {
        boolean z;
        EdgeEffect edgeEffect = this.Q;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            z = false;
        } else {
            this.Q.onRelease();
            z = this.Q.isFinished();
        }
        EdgeEffect edgeEffect2 = this.S;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.S.onRelease();
            z |= this.S.isFinished();
        }
        EdgeEffect edgeEffect3 = this.R;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.R.onRelease();
            z |= this.R.isFinished();
        }
        EdgeEffect edgeEffect4 = this.T;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.T.onRelease();
            z |= this.T.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void p0(boolean z) {
        int i2 = this.N - 1;
        this.N = i2;
        if (i2 < 1) {
            this.N = 0;
            if (z) {
                w();
                D();
            }
        }
    }

    public void q() {
        if (this.C && !this.L) {
            if (this.k.p()) {
                if (this.k.o(4) && !this.k.o(11)) {
                    TraceCompat.beginSection("RV PartialInvalidate");
                    N0();
                    n0();
                    this.k.w();
                    if (!this.E) {
                        if (Y()) {
                            x();
                        } else {
                            this.k.i();
                        }
                    }
                    O0(true);
                    o0();
                    TraceCompat.endSection();
                    return;
                } else if (this.k.p()) {
                    TraceCompat.beginSection("RV FullInvalidate");
                    x();
                    TraceCompat.endSection();
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        TraceCompat.beginSection("RV FullInvalidate");
        x();
        TraceCompat.endSection();
    }

    public final void q0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.W) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.W = motionEvent.getPointerId(i2);
            int x = (int) (motionEvent.getX(i2) + 0.5f);
            this.d0 = x;
            this.b0 = x;
            int y = (int) (motionEvent.getY(i2) + 0.5f);
            this.e0 = y;
            this.c0 = y;
        }
    }

    public final void r(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        ClassLoader classLoader;
        Constructor constructor;
        if (str != null) {
            String trim = str.trim();
            if (trim.isEmpty()) {
                return;
            }
            String V = V(context, trim);
            try {
                if (isInEditMode()) {
                    classLoader = getClass().getClassLoader();
                } else {
                    classLoader = context.getClassLoader();
                }
                Class<? extends U> asSubclass = Class.forName(V, false, classLoader).asSubclass(LayoutManager.class);
                Object[] objArr = null;
                try {
                    constructor = asSubclass.getConstructor(Q0);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = asSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + V, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((LayoutManager) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + V, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + V, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + V, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + V, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + V, e8);
            }
        }
    }

    public void r0() {
        if (this.v0 || !this.z) {
            return;
        }
        ViewCompat.postOnAnimation(this, this.E0);
        this.v0 = true;
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z) {
        ViewHolder S = S(view);
        if (S != null) {
            if (S.isTmpDetached()) {
                S.clearTmpDetachFlag();
            } else if (!S.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + S + J());
            }
        }
        view.clearAnimation();
        v(view);
        super.removeDetachedView(view, z);
    }

    public void removeItemDecoration(@NonNull ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.w.remove(itemDecoration);
        if (this.w.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        h0();
        requestLayout();
    }

    public void removeItemDecorationAt(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 >= 0 && i2 < itemDecorationCount) {
            removeItemDecoration(getItemDecorationAt(i2));
            return;
        }
        throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
    }

    public void removeOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        List<OnChildAttachStateChangeListener> list = this.K;
        if (list == null) {
            return;
        }
        list.remove(onChildAttachStateChangeListener);
    }

    public void removeOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.x.remove(onItemTouchListener);
        if (this.y == onItemTouchListener) {
            this.y = null;
        }
    }

    public void removeOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        List<OnScrollListener> list = this.r0;
        if (list != null) {
            list.remove(onScrollListener);
        }
    }

    public void removeRecyclerListener(@NonNull RecyclerListener recyclerListener) {
        this.v.remove(recyclerListener);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.t.onRequestChildFocus(this, this.p0, view, view2) && view2 != null) {
            C0(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.t.requestChildRectangleOnScreen(this, view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.x.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.x.get(i2).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.D == 0 && !this.F) {
            super.requestLayout();
        } else {
            this.E = true;
        }
    }

    public void s(int i2, int i3) {
        setMeasuredDimension(LayoutManager.chooseSize(i2, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(i3, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    public final boolean s0() {
        return this.U != null && this.t.supportsPredictiveItemAnimations();
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.F) {
        } else {
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.t.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    i2 = 0;
                }
                if (!canScrollVertically) {
                    i3 = 0;
                }
                H0(i2, i3, null, 0);
            }
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i2) {
        if (this.F) {
            return;
        }
        stopScroll();
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        layoutManager.scrollToPosition(i2);
        awakenScrollBars();
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (L0(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(@Nullable RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.w0 = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate);
    }

    public void setAdapter(@Nullable Adapter adapter) {
        setLayoutFrozen(false);
        J0(adapter, false, true);
        u0(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(@Nullable ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback == this.x0) {
            return;
        }
        this.x0 = childDrawingOrderCallback;
        setChildrenDrawingOrderEnabled(childDrawingOrderCallback != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.n) {
            d0();
        }
        this.n = z;
        super.setClipToPadding(z);
        if (this.C) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(@NonNull EdgeEffectFactory edgeEffectFactory) {
        Preconditions.checkNotNull(edgeEffectFactory);
        this.P = edgeEffectFactory;
        d0();
    }

    public void setHasFixedSize(boolean z) {
        this.A = z;
    }

    public void setItemAnimator(@Nullable ItemAnimator itemAnimator) {
        ItemAnimator itemAnimator2 = this.U;
        if (itemAnimator2 != null) {
            itemAnimator2.endAnimations();
            this.U.setListener(null);
        }
        this.U = itemAnimator;
        if (itemAnimator != null) {
            itemAnimator.setListener(this.u0);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.i.setViewCacheSize(i2);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(@Nullable LayoutManager layoutManager) {
        if (layoutManager == this.t) {
            return;
        }
        stopScroll();
        if (this.t != null) {
            ItemAnimator itemAnimator = this.U;
            if (itemAnimator != null) {
                itemAnimator.endAnimations();
            }
            this.t.removeAndRecycleAllViews(this.i);
            this.t.n(this.i);
            this.i.clear();
            if (this.z) {
                this.t.d(this, this.i);
            }
            this.t.s(null);
            this.t = null;
        } else {
            this.i.clear();
        }
        this.l.o();
        this.t = layoutManager;
        if (layoutManager != null) {
            if (layoutManager.i == null) {
                layoutManager.s(this);
                if (this.z) {
                    this.t.c(this);
                }
            } else {
                throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView:" + layoutManager.i.J());
            }
        }
        this.i.E();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (Build.VERSION.SDK_INT < 18) {
            if (layoutTransition == null) {
                suppressLayout(false);
                return;
            } else if (layoutTransition.getAnimator(0) == null && layoutTransition.getAnimator(1) == null && layoutTransition.getAnimator(2) == null && layoutTransition.getAnimator(3) == null && layoutTransition.getAnimator(4) == null) {
                suppressLayout(true);
                return;
            }
        }
        if (layoutTransition == null) {
            super.setLayoutTransition(null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    public void setOnFlingListener(@Nullable OnFlingListener onFlingListener) {
        this.g0 = onFlingListener;
    }

    @Deprecated
    public void setOnScrollListener(@Nullable OnScrollListener onScrollListener) {
        this.q0 = onScrollListener;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.l0 = z;
    }

    public void setRecycledViewPool(@Nullable RecycledViewPool recycledViewPool) {
        this.i.z(recycledViewPool);
    }

    @Deprecated
    public void setRecyclerListener(@Nullable RecyclerListener recyclerListener) {
        this.u = recyclerListener;
    }

    void setScrollState(int i2) {
        if (i2 == this.V) {
            return;
        }
        this.V = i2;
        if (i2 != 2) {
            P0();
        }
        B(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 != 1) {
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
            } else {
                this.f0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
        }
        this.f0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(@Nullable ViewCacheExtension viewCacheExtension) {
        this.i.A(viewCacheExtension);
    }

    public void smoothScrollBy(@Px int i2, @Px int i3) {
        smoothScrollBy(i2, i3, null);
    }

    public void smoothScrollToPosition(int i2) {
        if (this.F) {
            return;
        }
        LayoutManager layoutManager = this.t;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.smoothScrollToPosition(this, this.p0, i2);
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().startNestedScroll(i2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopScroll() {
        setScrollState(0);
        P0();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if (z != this.F) {
            k("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.F = false;
                if (this.E && this.t != null && this.s != null) {
                    requestLayout();
                }
                this.E = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.F = true;
            this.G = true;
            stopScroll();
        }
    }

    public void swapAdapter(@Nullable Adapter adapter, boolean z) {
        setLayoutFrozen(false);
        J0(adapter, true, z);
        u0(true);
        requestLayout();
    }

    public final boolean t(int i2, int i3) {
        M(this.y0);
        int[] iArr = this.y0;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    public final void t0() {
        boolean z;
        if (this.L) {
            this.k.y();
            if (this.M) {
                this.t.onItemsChanged(this);
            }
        }
        if (s0()) {
            this.k.w();
        } else {
            this.k.j();
        }
        boolean z2 = false;
        boolean z3 = this.s0 || this.t0;
        this.p0.k = this.C && this.U != null && ((z = this.L) || z3 || this.t.o) && (!z || this.s.hasStableIds());
        State state = this.p0;
        if (state.k && z3 && !this.L && s0()) {
            z2 = true;
        }
        state.l = z2;
    }

    public void u(View view) {
        ViewHolder S = S(view);
        onChildAttachedToWindow(view);
        Adapter adapter = this.s;
        if (adapter != null && S != null) {
            adapter.onViewAttachedToWindow(S);
        }
        List<OnChildAttachStateChangeListener> list = this.K;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.K.get(size).onChildViewAttachedToWindow(view);
            }
        }
    }

    public void u0(boolean z) {
        this.M = z | this.M;
        this.L = true;
        i0();
    }

    public void v(View view) {
        ViewHolder S = S(view);
        onChildDetachedFromWindow(view);
        Adapter adapter = this.s;
        if (adapter != null && S != null) {
            adapter.onViewDetachedFromWindow(S);
        }
        List<OnChildAttachStateChangeListener> list = this.K;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.K.get(size).onChildViewDetachedFromWindow(view);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void v0(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 1
            if (r1 >= 0) goto L21
            r6.G()
            android.widget.EdgeEffect r1 = r6.Q
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r2 - r9
            androidx.core.widget.EdgeEffectCompat.onPull(r1, r4, r9)
        L1f:
            r9 = r3
            goto L3c
        L21:
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r1 <= 0) goto L3b
            r6.H()
            android.widget.EdgeEffect r1 = r6.S
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            androidx.core.widget.EdgeEffectCompat.onPull(r1, r4, r9)
            goto L1f
        L3b:
            r9 = 0
        L3c:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L56
            r6.I()
            android.widget.EdgeEffect r9 = r6.R
            float r1 = -r10
            int r2 = r6.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            int r2 = r6.getWidth()
            float r2 = (float) r2
            float r7 = r7 / r2
            androidx.core.widget.EdgeEffectCompat.onPull(r9, r1, r7)
            goto L72
        L56:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 <= 0) goto L71
            r6.F()
            android.widget.EdgeEffect r9 = r6.T
            int r1 = r6.getHeight()
            float r1 = (float) r1
            float r1 = r10 / r1
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r2 = r2 - r7
            androidx.core.widget.EdgeEffectCompat.onPull(r9, r1, r2)
            goto L72
        L71:
            r3 = r9
        L72:
            if (r3 != 0) goto L7c
            int r7 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r7 != 0) goto L7c
            int r7 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r7 == 0) goto L7f
        L7c:
            androidx.core.view.ViewCompat.postInvalidateOnAnimation(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.v0(float, float, float, float):void");
    }

    public final void w() {
        int i2 = this.H;
        this.H = 0;
        if (i2 == 0 || !e0()) {
            return;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        obtain.setEventType(2048);
        AccessibilityEventCompat.setContentChangeTypes(obtain, i2);
        sendAccessibilityEventUnchecked(obtain);
    }

    public void w0(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.p0.i && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.m.c(R(viewHolder), viewHolder);
        }
        this.m.e(viewHolder, itemHolderInfo);
    }

    public void x() {
        if (this.s == null) {
            Log.w("RecyclerView", "No adapter attached; skipping layout");
        } else if (this.t == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        } else {
            this.p0.j = false;
            boolean z = this.F0 && !(this.G0 == getWidth() && this.H0 == getHeight());
            this.G0 = 0;
            this.H0 = 0;
            this.F0 = false;
            if (this.p0.e == 1) {
                y();
                this.t.p(this);
                z();
            } else if (!this.k.q() && !z && this.t.getWidth() == getWidth() && this.t.getHeight() == getHeight()) {
                this.t.p(this);
            } else {
                this.t.p(this);
                z();
            }
            A();
        }
    }

    public final void x0() {
        View findViewById;
        if (!this.l0 || this.s == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (P0 && (focusedChild.getParent() == null || !focusedChild.hasFocus())) {
                if (this.l.g() == 0) {
                    requestFocus();
                    return;
                }
            } else if (!this.l.n(focusedChild)) {
                return;
            }
        }
        View view = null;
        ViewHolder findViewHolderForItemId = (this.p0.n == -1 || !this.s.hasStableIds()) ? null : findViewHolderForItemId(this.p0.n);
        if (findViewHolderForItemId != null && !this.l.n(findViewHolderForItemId.itemView) && findViewHolderForItemId.itemView.hasFocusable()) {
            view = findViewHolderForItemId.itemView;
        } else if (this.l.g() > 0) {
            view = O();
        }
        if (view != null) {
            int i2 = this.p0.o;
            if (i2 != -1 && (findViewById = view.findViewById(i2)) != null && findViewById.isFocusable()) {
                view = findViewById;
            }
            view.requestFocus();
        }
    }

    public final void y() {
        boolean z = true;
        this.p0.a(1);
        K(this.p0);
        this.p0.j = false;
        N0();
        this.m.f();
        n0();
        t0();
        F0();
        State state = this.p0;
        state.i = (state.k && this.t0) ? false : false;
        this.t0 = false;
        this.s0 = false;
        state.h = state.l;
        state.f = this.s.getItemCount();
        M(this.y0);
        if (this.p0.k) {
            int g2 = this.l.g();
            for (int i2 = 0; i2 < g2; i2++) {
                ViewHolder S = S(this.l.f(i2));
                if (!S.shouldIgnore() && (!S.isInvalid() || this.s.hasStableIds())) {
                    this.m.e(S, this.U.recordPreLayoutInformation(this.p0, S, ItemAnimator.buildAdapterChangeFlagsForAnimations(S), S.getUnmodifiedPayloads()));
                    if (this.p0.i && S.isUpdated() && !S.isRemoved() && !S.shouldIgnore() && !S.isInvalid()) {
                        this.m.c(R(S), S);
                    }
                }
            }
        }
        if (this.p0.l) {
            G0();
            State state2 = this.p0;
            boolean z2 = state2.g;
            state2.g = false;
            this.t.onLayoutChildren(this.i, state2);
            this.p0.g = z2;
            for (int i3 = 0; i3 < this.l.g(); i3++) {
                ViewHolder S2 = S(this.l.f(i3));
                if (!S2.shouldIgnore() && !this.m.i(S2)) {
                    int buildAdapterChangeFlagsForAnimations = ItemAnimator.buildAdapterChangeFlagsForAnimations(S2);
                    boolean hasAnyOfTheFlags = S2.hasAnyOfTheFlags(8192);
                    if (!hasAnyOfTheFlags) {
                        buildAdapterChangeFlagsForAnimations |= 4096;
                    }
                    ItemAnimator.ItemHolderInfo recordPreLayoutInformation = this.U.recordPreLayoutInformation(this.p0, S2, buildAdapterChangeFlagsForAnimations, S2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        w0(S2, recordPreLayoutInformation);
                    } else {
                        this.m.a(S2, recordPreLayoutInformation);
                    }
                }
            }
            o();
        } else {
            o();
        }
        o0();
        O0(false);
        this.p0.e = 2;
    }

    public final void y0() {
        boolean z;
        EdgeEffect edgeEffect = this.Q;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.Q.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.R;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.R.isFinished();
        }
        EdgeEffect edgeEffect3 = this.S;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.S.isFinished();
        }
        EdgeEffect edgeEffect4 = this.T;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.T.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public final void z() {
        N0();
        n0();
        this.p0.a(6);
        this.k.j();
        this.p0.f = this.s.getItemCount();
        this.p0.d = 0;
        if (this.j != null && this.s.canRestoreState()) {
            Parcelable parcelable = this.j.i;
            if (parcelable != null) {
                this.t.onRestoreInstanceState(parcelable);
            }
            this.j = null;
        }
        State state = this.p0;
        state.h = false;
        this.t.onLayoutChildren(this.i, state);
        State state2 = this.p0;
        state2.g = false;
        state2.k = state2.k && this.U != null;
        state2.e = 4;
        o0();
        O0(false);
    }

    public void z0() {
        ItemAnimator itemAnimator = this.U;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.i);
            this.t.n(this.i);
        }
        this.i.clear();
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i2) {
        return getScrollingChildHelper().hasNestedScrollingParent(i2);
    }

    public void smoothScrollBy(@Px int i2, @Px int i3, @Nullable Interpolator interpolator) {
        smoothScrollBy(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i2, int i3) {
        return getScrollingChildHelper().startNestedScroll(i2, i3);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int i2) {
        getScrollingChildHelper().stopNestedScroll(i2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public Parcelable i;

        /* loaded from: classes.dex */
        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.i = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        public void a(SavedState savedState) {
            this.i = savedState.i;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.i, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.h = new j();
        this.i = new Recycler();
        this.m = new l();
        this.o = new a();
        this.p = new Rect();
        this.q = new Rect();
        this.r = new RectF();
        this.v = new ArrayList();
        this.w = new ArrayList<>();
        this.x = new ArrayList<>();
        this.D = 0;
        this.L = false;
        this.M = false;
        this.N = 0;
        this.O = 0;
        this.P = new EdgeEffectFactory();
        this.U = new DefaultItemAnimator();
        this.V = 0;
        this.W = -1;
        this.j0 = Float.MIN_VALUE;
        this.k0 = Float.MIN_VALUE;
        boolean z = true;
        this.l0 = true;
        this.m0 = new k();
        this.o0 = N0 ? new e.b() : null;
        this.p0 = new State();
        this.s0 = false;
        this.t0 = false;
        this.u0 = new i();
        this.v0 = false;
        this.y0 = new int[2];
        this.A0 = new int[2];
        this.B0 = new int[2];
        this.C0 = new int[2];
        this.D0 = new ArrayList();
        this.E0 = new b();
        this.G0 = 0;
        this.H0 = 0;
        this.I0 = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f0 = viewConfiguration.getScaledTouchSlop();
        this.j0 = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        this.k0 = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        this.h0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.i0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.U.setListener(this.u0);
        Z();
        b0();
        a0();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        this.J = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        int[] iArr = R.styleable.RecyclerView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, i2, 0);
        String string = obtainStyledAttributes.getString(R.styleable.RecyclerView_layoutManager);
        if (obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.n = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_android_clipToPadding, true);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
        this.B = z2;
        if (z2) {
            c0((StateListDrawable) obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        obtainStyledAttributes.recycle();
        r(context, string, attributeSet, i2, 0);
        if (Build.VERSION.SDK_INT >= 21) {
            int[] iArr2 = J0;
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i2, 0);
            ViewCompat.saveAttributeDataForStyleable(this, context, iArr2, attributeSet, obtainStyledAttributes2, i2, 0);
            z = obtainStyledAttributes2.getBoolean(0, true);
            obtainStyledAttributes2.recycle();
        }
        setNestedScrollingEnabled(z);
    }

    @Override // androidx.core.view.NestedScrollingChild3
    public final void dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6, @NonNull int[] iArr2) {
        getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public void smoothScrollBy(@Px int i2, @Px int i3, @Nullable Interpolator interpolator, int i4) {
        M0(i2, i3, interpolator, i4, false);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public ViewHolder h;
        public final Rect i;
        public boolean j;
        public boolean k;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.i = new Rect();
            this.j = true;
            this.k = false;
        }

        public int getAbsoluteAdapterPosition() {
            return this.h.getAbsoluteAdapterPosition();
        }

        public int getBindingAdapterPosition() {
            return this.h.getBindingAdapterPosition();
        }

        @Deprecated
        public int getViewAdapterPosition() {
            return this.h.getBindingAdapterPosition();
        }

        public int getViewLayoutPosition() {
            return this.h.getLayoutPosition();
        }

        @Deprecated
        public int getViewPosition() {
            return this.h.getPosition();
        }

        public boolean isItemChanged() {
            return this.h.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.h.isRemoved();
        }

        public boolean isViewInvalid() {
            return this.h.isInvalid();
        }

        public boolean viewNeedsUpdate() {
            return this.h.needsUpdate();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.i = new Rect();
            this.j = true;
            this.k = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.i = new Rect();
            this.j = true;
            this.k = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.i = new Rect();
            this.j = true;
            this.k = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.i = new Rect();
            this.j = true;
            this.k = false;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.t;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + J());
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration) {
        addItemDecoration(itemDecoration, -1);
    }
}
