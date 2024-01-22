package androidx.viewpager2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.R;
import androidx.viewpager2.adapter.StatefulAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public static boolean B = true;
    public static final int OFFSCREEN_PAGE_LIMIT_DEFAULT = -1;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public e A;
    public final Rect h;
    public final Rect i;
    public androidx.viewpager2.widget.b j;
    public int k;
    public boolean l;
    public RecyclerView.AdapterDataObserver m;
    public LinearLayoutManager n;
    public int o;
    public Parcelable p;
    public RecyclerView q;
    public PagerSnapHelper r;
    public androidx.viewpager2.widget.e s;
    public androidx.viewpager2.widget.b t;
    public androidx.viewpager2.widget.c u;
    public androidx.viewpager2.widget.d v;
    public RecyclerView.ItemAnimator w;
    public boolean x;
    public boolean y;
    public int z;

    @IntRange(from = 1)
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface OffscreenPageLimit {
    }

    /* loaded from: classes.dex */
    public static abstract class OnPageChangeCallback {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, @Px int i2) {
        }

        public void onPageSelected(int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Orientation {
    }

    /* loaded from: classes.dex */
    public interface PageTransformer {
        void transformPage(@NonNull View view, float f);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface ScrollState {
    }

    /* loaded from: classes.dex */
    public class a extends g {
        public a() {
            super(null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.l = true;
            viewPager2.s.l();
        }
    }

    /* loaded from: classes.dex */
    public class b extends OnPageChangeCallback {
        public b() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                ViewPager2.this.j();
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int i) {
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.k != i) {
                viewPager2.k = i;
                viewPager2.A.q();
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends OnPageChangeCallback {
        public c() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int i) {
            ViewPager2.this.clearFocus();
            if (ViewPager2.this.hasFocus()) {
                ViewPager2.this.q.requestFocus(2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements RecyclerView.OnChildAttachStateChangeListener {
        public d(ViewPager2 viewPager2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewAttachedToWindow(@NonNull View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            if (((ViewGroup.MarginLayoutParams) layoutParams).width != -1 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewDetachedFromWindow(@NonNull View view) {
        }
    }

    /* loaded from: classes.dex */
    public abstract class e {
        public e(ViewPager2 viewPager2) {
        }

        public boolean a() {
            return false;
        }

        public boolean b(int i) {
            return false;
        }

        public boolean c(int i, Bundle bundle) {
            return false;
        }

        public boolean d() {
            return false;
        }

        public void e(@Nullable RecyclerView.Adapter<?> adapter) {
        }

        public void f(@Nullable RecyclerView.Adapter<?> adapter) {
        }

        public String g() {
            throw new IllegalStateException("Not implemented.");
        }

        public void h(@NonNull androidx.viewpager2.widget.b bVar, @NonNull RecyclerView recyclerView) {
        }

        public void i(AccessibilityNodeInfo accessibilityNodeInfo) {
        }

        public void j(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        public boolean k(int i) {
            throw new IllegalStateException("Not implemented.");
        }

        public boolean l(int i, Bundle bundle) {
            throw new IllegalStateException("Not implemented.");
        }

        public void m() {
        }

        public CharSequence n() {
            throw new IllegalStateException("Not implemented.");
        }

        public void o(@NonNull AccessibilityEvent accessibilityEvent) {
        }

        public void p() {
        }

        public void q() {
        }

        public void r() {
        }

        public void s() {
        }

        public /* synthetic */ e(ViewPager2 viewPager2, a aVar) {
            this(viewPager2);
        }
    }

    /* loaded from: classes.dex */
    public class f extends e {
        public f() {
            super(ViewPager2.this, null);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean b(int i) {
            return (i == 8192 || i == 4096) && !ViewPager2.this.isUserInputEnabled();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean d() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void j(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (ViewPager2.this.isUserInputEnabled()) {
                return;
            }
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
            accessibilityNodeInfoCompat.setScrollable(false);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean k(int i) {
            if (b(i)) {
                return false;
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public CharSequence n() {
            if (d()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class g extends RecyclerView.AdapterDataObserver {
        public g() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i, int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            onChanged();
        }

        public /* synthetic */ g(a aVar) {
            this();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            onChanged();
        }
    }

    /* loaded from: classes.dex */
    public class h extends LinearLayoutManager {
        public h(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(@NonNull RecyclerView.State state, @NonNull int[] iArr) {
            int offscreenPageLimit = ViewPager2.this.getOffscreenPageLimit();
            if (offscreenPageLimit == -1) {
                super.calculateExtraLayoutSpace(state, iArr);
                return;
            }
            int pageSize = ViewPager2.this.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onInitializeAccessibilityNodeInfo(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
            ViewPager2.this.A.j(accessibilityNodeInfoCompat);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean performAccessibilityAction(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int i, @Nullable Bundle bundle) {
            if (ViewPager2.this.A.b(i)) {
                return ViewPager2.this.A.k(i);
            }
            return super.performAccessibilityAction(recycler, state, i, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z, boolean z2) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class i extends e {

        /* renamed from: a  reason: collision with root package name */
        public final AccessibilityViewCommand f1759a;
        public final AccessibilityViewCommand b;
        public RecyclerView.AdapterDataObserver c;

        /* loaded from: classes.dex */
        public class a implements AccessibilityViewCommand {
            public a() {
            }

            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
                i.this.v(((ViewPager2) view).getCurrentItem() + 1);
                return true;
            }
        }

        /* loaded from: classes.dex */
        public class b implements AccessibilityViewCommand {
            public b() {
            }

            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
                i.this.v(((ViewPager2) view).getCurrentItem() - 1);
                return true;
            }
        }

        /* loaded from: classes.dex */
        public class c extends g {
            public c() {
                super(null);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                i.this.w();
            }
        }

        public i() {
            super(ViewPager2.this, null);
            this.f1759a = new a();
            this.b = new b();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean a() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean c(int i, Bundle bundle) {
            return i == 8192 || i == 4096;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void e(@Nullable RecyclerView.Adapter<?> adapter) {
            w();
            if (adapter != null) {
                adapter.registerAdapterDataObserver(this.c);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void f(@Nullable RecyclerView.Adapter<?> adapter) {
            if (adapter != null) {
                adapter.unregisterAdapterDataObserver(this.c);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public String g() {
            if (a()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void h(@NonNull androidx.viewpager2.widget.b bVar, @NonNull RecyclerView recyclerView) {
            ViewCompat.setImportantForAccessibility(recyclerView, 2);
            this.c = new c();
            if (ViewCompat.getImportantForAccessibility(ViewPager2.this) == 0) {
                ViewCompat.setImportantForAccessibility(ViewPager2.this, 1);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void i(AccessibilityNodeInfo accessibilityNodeInfo) {
            t(accessibilityNodeInfo);
            if (Build.VERSION.SDK_INT >= 16) {
                u(accessibilityNodeInfo);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean l(int i, Bundle bundle) {
            int currentItem;
            if (c(i, bundle)) {
                if (i == 8192) {
                    currentItem = ViewPager2.this.getCurrentItem() - 1;
                } else {
                    currentItem = ViewPager2.this.getCurrentItem() + 1;
                }
                v(currentItem);
                return true;
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void m() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void o(@NonNull AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setSource(ViewPager2.this);
            accessibilityEvent.setClassName(g());
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void p() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void q() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void r() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void s() {
            w();
            if (Build.VERSION.SDK_INT < 21) {
                ViewPager2.this.sendAccessibilityEvent(2048);
            }
        }

        public final void t(AccessibilityNodeInfo accessibilityNodeInfo) {
            int i;
            int i2;
            if (ViewPager2.this.getAdapter() == null) {
                i = 0;
                i2 = 0;
            } else if (ViewPager2.this.getOrientation() == 1) {
                i = ViewPager2.this.getAdapter().getItemCount();
                i2 = 0;
            } else {
                i2 = ViewPager2.this.getAdapter().getItemCount();
                i = 0;
            }
            AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(i, i2, false, 0));
        }

        public final void u(AccessibilityNodeInfo accessibilityNodeInfo) {
            int itemCount;
            RecyclerView.Adapter adapter = ViewPager2.this.getAdapter();
            if (adapter == null || (itemCount = adapter.getItemCount()) == 0 || !ViewPager2.this.isUserInputEnabled()) {
                return;
            }
            if (ViewPager2.this.k > 0) {
                accessibilityNodeInfo.addAction(8192);
            }
            if (ViewPager2.this.k < itemCount - 1) {
                accessibilityNodeInfo.addAction(4096);
            }
            accessibilityNodeInfo.setScrollable(true);
        }

        public void v(int i) {
            if (ViewPager2.this.isUserInputEnabled()) {
                ViewPager2.this.f(i, true);
            }
        }

        public void w() {
            int itemCount;
            ViewPager2 viewPager2 = ViewPager2.this;
            ViewCompat.removeAccessibilityAction(viewPager2, 16908360);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908361);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908358);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908359);
            if (ViewPager2.this.getAdapter() == null || (itemCount = ViewPager2.this.getAdapter().getItemCount()) == 0 || !ViewPager2.this.isUserInputEnabled()) {
                return;
            }
            if (ViewPager2.this.getOrientation() == 0) {
                boolean c2 = ViewPager2.this.c();
                int i = c2 ? 16908360 : 16908361;
                int i2 = c2 ? 16908361 : 16908360;
                if (ViewPager2.this.k < itemCount - 1) {
                    ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i, null), null, this.f1759a);
                }
                if (ViewPager2.this.k > 0) {
                    ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, null), null, this.b);
                    return;
                }
                return;
            }
            if (ViewPager2.this.k < itemCount - 1) {
                ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16908359, null), null, this.f1759a);
            }
            if (ViewPager2.this.k > 0) {
                ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16908358, null), null, this.b);
            }
        }
    }

    /* loaded from: classes.dex */
    public class j extends PagerSnapHelper {
        public j() {
        }

        @Override // androidx.recyclerview.widget.PagerSnapHelper, androidx.recyclerview.widget.SnapHelper
        @Nullable
        public View findSnapView(RecyclerView.LayoutManager layoutManager) {
            if (ViewPager2.this.isFakeDragging()) {
                return null;
            }
            return super.findSnapView(layoutManager);
        }
    }

    /* loaded from: classes.dex */
    public class k extends RecyclerView {
        public k(@NonNull Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        @RequiresApi(23)
        public CharSequence getAccessibilityClassName() {
            if (ViewPager2.this.A.d()) {
                return ViewPager2.this.A.n();
            }
            return super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.k);
            accessibilityEvent.setToIndex(ViewPager2.this.k);
            ViewPager2.this.A.o(accessibilityEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.isUserInputEnabled() && super.onInterceptTouchEvent(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.isUserInputEnabled() && super.onTouchEvent(motionEvent);
        }
    }

    /* loaded from: classes.dex */
    public static class l implements Runnable {
        public final int h;
        public final RecyclerView i;

        public l(int i, RecyclerView recyclerView) {
            this.h = i;
            this.i = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.i.smoothScrollToPosition(this.h);
        }
    }

    public ViewPager2(@NonNull Context context) {
        super(context);
        this.h = new Rect();
        this.i = new Rect();
        this.j = new androidx.viewpager2.widget.b(3);
        this.l = false;
        this.m = new a();
        this.o = -1;
        this.w = null;
        this.x = false;
        this.y = true;
        this.z = -1;
        b(context, null);
    }

    public final RecyclerView.OnChildAttachStateChangeListener a() {
        return new d(this);
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        this.q.addItemDecoration(itemDecoration);
    }

    public final void b(Context context, AttributeSet attributeSet) {
        this.A = B ? new i() : new f();
        k kVar = new k(context);
        this.q = kVar;
        kVar.setId(ViewCompat.generateViewId());
        this.q.setDescendantFocusability(131072);
        h hVar = new h(context);
        this.n = hVar;
        this.q.setLayoutManager(hVar);
        this.q.setScrollingTouchSlop(1);
        g(context, attributeSet);
        this.q.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.q.addOnChildAttachStateChangeListener(a());
        androidx.viewpager2.widget.e eVar = new androidx.viewpager2.widget.e(this);
        this.s = eVar;
        this.u = new androidx.viewpager2.widget.c(this, eVar, this.q);
        j jVar = new j();
        this.r = jVar;
        jVar.attachToRecyclerView(this.q);
        this.q.addOnScrollListener(this.s);
        androidx.viewpager2.widget.b bVar = new androidx.viewpager2.widget.b(3);
        this.t = bVar;
        this.s.p(bVar);
        b bVar2 = new b();
        c cVar = new c();
        this.t.a(bVar2);
        this.t.a(cVar);
        this.A.h(this.t, this.q);
        this.t.a(this.j);
        androidx.viewpager2.widget.d dVar = new androidx.viewpager2.widget.d(this.n);
        this.v = dVar;
        this.t.a(dVar);
        RecyclerView recyclerView = this.q;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    public boolean beginFakeDrag() {
        return this.u.b();
    }

    public boolean c() {
        return this.n.getLayoutDirection() == 1;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        return this.q.canScrollHorizontally(i2);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        return this.q.canScrollVertically(i2);
    }

    public final void d(@Nullable RecyclerView.Adapter<?> adapter) {
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.m);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i2 = ((SavedState) parcelable).h;
            sparseArray.put(this.q.getId(), sparseArray.get(i2));
            sparseArray.remove(i2);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        e();
    }

    public final void e() {
        RecyclerView.Adapter adapter;
        if (this.o == -1 || (adapter = getAdapter()) == null) {
            return;
        }
        Parcelable parcelable = this.p;
        if (parcelable != null) {
            if (adapter instanceof StatefulAdapter) {
                ((StatefulAdapter) adapter).restoreState(parcelable);
            }
            this.p = null;
        }
        int max = Math.max(0, Math.min(this.o, adapter.getItemCount() - 1));
        this.k = max;
        this.o = -1;
        this.q.scrollToPosition(max);
        this.A.m();
    }

    public boolean endFakeDrag() {
        return this.u.d();
    }

    public void f(int i2, boolean z) {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter == null) {
            if (this.o != -1) {
                this.o = Math.max(i2, 0);
            }
        } else if (adapter.getItemCount() <= 0) {
        } else {
            int min = Math.min(Math.max(i2, 0), adapter.getItemCount() - 1);
            if (min == this.k && this.s.i()) {
                return;
            }
            int i3 = this.k;
            if (min == i3 && z) {
                return;
            }
            double d2 = i3;
            this.k = min;
            this.A.q();
            if (!this.s.i()) {
                d2 = this.s.e();
            }
            this.s.n(min, z);
            if (!z) {
                this.q.scrollToPosition(min);
                return;
            }
            double d3 = min;
            if (Math.abs(d3 - d2) > 3.0d) {
                this.q.scrollToPosition(d3 > d2 ? min - 3 : min + 3);
                RecyclerView recyclerView = this.q;
                recyclerView.post(new l(min, recyclerView));
                return;
            }
            this.q.smoothScrollToPosition(min);
        }
    }

    public boolean fakeDragBy(@Px @SuppressLint({"SupportAnnotationUsage"}) float f2) {
        return this.u.e(f2);
    }

    public final void g(Context context, AttributeSet attributeSet) {
        int[] iArr = R.styleable.ViewPager2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, obtainStyledAttributes, 0, 0);
        }
        try {
            setOrientation(obtainStyledAttributes.getInt(R.styleable.ViewPager2_android_orientation, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @RequiresApi(23)
    public CharSequence getAccessibilityClassName() {
        if (this.A.a()) {
            return this.A.g();
        }
        return super.getAccessibilityClassName();
    }

    @Nullable
    public RecyclerView.Adapter getAdapter() {
        return this.q.getAdapter();
    }

    public int getCurrentItem() {
        return this.k;
    }

    @NonNull
    public RecyclerView.ItemDecoration getItemDecorationAt(int i2) {
        return this.q.getItemDecorationAt(i2);
    }

    public int getItemDecorationCount() {
        return this.q.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.z;
    }

    public int getOrientation() {
        return this.n.getOrientation();
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        RecyclerView recyclerView = this.q;
        if (getOrientation() == 0) {
            height = recyclerView.getWidth() - recyclerView.getPaddingLeft();
            paddingBottom = recyclerView.getPaddingRight();
        } else {
            height = recyclerView.getHeight() - recyclerView.getPaddingTop();
            paddingBottom = recyclerView.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.s.f();
    }

    public void h() {
        View findSnapView = this.r.findSnapView(this.n);
        if (findSnapView == null) {
            return;
        }
        int[] calculateDistanceToFinalSnap = this.r.calculateDistanceToFinalSnap(this.n, findSnapView);
        if (calculateDistanceToFinalSnap[0] == 0 && calculateDistanceToFinalSnap[1] == 0) {
            return;
        }
        this.q.smoothScrollBy(calculateDistanceToFinalSnap[0], calculateDistanceToFinalSnap[1]);
    }

    public final void i(@Nullable RecyclerView.Adapter<?> adapter) {
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(this.m);
        }
    }

    public void invalidateItemDecorations() {
        this.q.invalidateItemDecorations();
    }

    public boolean isFakeDragging() {
        return this.u.f();
    }

    public boolean isUserInputEnabled() {
        return this.y;
    }

    public void j() {
        PagerSnapHelper pagerSnapHelper = this.r;
        if (pagerSnapHelper != null) {
            View findSnapView = pagerSnapHelper.findSnapView(this.n);
            if (findSnapView == null) {
                return;
            }
            int position = this.n.getPosition(findSnapView);
            if (position != this.k && getScrollState() == 0) {
                this.t.onPageSelected(position);
            }
            this.l = false;
            return;
        }
        throw new IllegalStateException("Design assumption violated.");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.A.i(accessibilityNodeInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = this.q.getMeasuredWidth();
        int measuredHeight = this.q.getMeasuredHeight();
        this.h.left = getPaddingLeft();
        this.h.right = (i4 - i2) - getPaddingRight();
        this.h.top = getPaddingTop();
        this.h.bottom = (i5 - i3) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.h, this.i);
        RecyclerView recyclerView = this.q;
        Rect rect = this.i;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.l) {
            j();
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        measureChild(this.q, i2, i3);
        int measuredWidth = this.q.getMeasuredWidth();
        int measuredHeight = this.q.getMeasuredHeight();
        int measuredState = this.q.getMeasuredState();
        int paddingLeft = measuredWidth + getPaddingLeft() + getPaddingRight();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i2, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i3, measuredState << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.o = savedState.i;
        this.p = savedState.j;
    }

    @Override // android.view.View
    @Nullable
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.h = this.q.getId();
        int i2 = this.o;
        if (i2 == -1) {
            i2 = this.k;
        }
        savedState.i = i2;
        Parcelable parcelable = this.p;
        if (parcelable != null) {
            savedState.j = parcelable;
        } else {
            RecyclerView.Adapter adapter = this.q.getAdapter();
            if (adapter instanceof StatefulAdapter) {
                savedState.j = ((StatefulAdapter) adapter).saveState();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        throw new IllegalStateException(ViewPager2.class.getSimpleName() + " does not support direct child views");
    }

    @Override // android.view.View
    @RequiresApi(16)
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (this.A.c(i2, bundle)) {
            return this.A.l(i2, bundle);
        }
        return super.performAccessibilityAction(i2, bundle);
    }

    public void registerOnPageChangeCallback(@NonNull OnPageChangeCallback onPageChangeCallback) {
        this.j.a(onPageChangeCallback);
    }

    public void removeItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        this.q.removeItemDecoration(itemDecoration);
    }

    public void removeItemDecorationAt(int i2) {
        this.q.removeItemDecorationAt(i2);
    }

    public void requestTransform() {
        if (this.v.a() == null) {
            return;
        }
        double e2 = this.s.e();
        int i2 = (int) e2;
        float f2 = (float) (e2 - i2);
        this.v.onPageScrolled(i2, f2, Math.round(getPageSize() * f2));
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = this.q.getAdapter();
        this.A.f(adapter2);
        i(adapter2);
        this.q.setAdapter(adapter);
        this.k = 0;
        e();
        this.A.e(adapter);
        d(adapter);
    }

    public void setCurrentItem(int i2) {
        setCurrentItem(i2, true);
    }

    @Override // android.view.View
    @RequiresApi(17)
    public void setLayoutDirection(int i2) {
        super.setLayoutDirection(i2);
        this.A.p();
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1 && i2 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.z = i2;
        this.q.requestLayout();
    }

    public void setOrientation(int i2) {
        this.n.setOrientation(i2);
        this.A.r();
    }

    public void setPageTransformer(@Nullable PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            if (!this.x) {
                this.w = this.q.getItemAnimator();
                this.x = true;
            }
            this.q.setItemAnimator(null);
        } else if (this.x) {
            this.q.setItemAnimator(this.w);
            this.w = null;
            this.x = false;
        }
        if (pageTransformer == this.v.a()) {
            return;
        }
        this.v.b(pageTransformer);
        requestTransform();
    }

    public void setUserInputEnabled(boolean z) {
        this.y = z;
        this.A.s();
    }

    public void unregisterOnPageChangeCallback(@NonNull OnPageChangeCallback onPageChangeCallback) {
        this.j.b(onPageChangeCallback);
    }

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int h;
        public int i;
        public Parcelable j;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return createFromParcel(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return Build.VERSION.SDK_INT >= 24 ? new SavedState(parcel, classLoader) : new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        @RequiresApi(24)
        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            a(parcel, classLoader);
        }

        public final void a(Parcel parcel, ClassLoader classLoader) {
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeParcelable(this.j, i);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            a(parcel, null);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration, int i2) {
        this.q.addItemDecoration(itemDecoration, i2);
    }

    public void setCurrentItem(int i2, boolean z) {
        if (!isFakeDragging()) {
            f(i2, z);
            return;
        }
        throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
    }

    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new Rect();
        this.i = new Rect();
        this.j = new androidx.viewpager2.widget.b(3);
        this.l = false;
        this.m = new a();
        this.o = -1;
        this.w = null;
        this.x = false;
        this.y = true;
        this.z = -1;
        b(context, attributeSet);
    }

    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.h = new Rect();
        this.i = new Rect();
        this.j = new androidx.viewpager2.widget.b(3);
        this.l = false;
        this.m = new a();
        this.o = -1;
        this.w = null;
        this.x = false;
        this.y = true;
        this.z = -1;
        b(context, attributeSet);
    }

    @RequiresApi(21)
    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.h = new Rect();
        this.i = new Rect();
        this.j = new androidx.viewpager2.widget.b(3);
        this.l = false;
        this.m = new a();
        this.o = -1;
        this.w = null;
        this.x = false;
        this.y = true;
        this.z = -1;
        b(context, attributeSet);
    }
}
