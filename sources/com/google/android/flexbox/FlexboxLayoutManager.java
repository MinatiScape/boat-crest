package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.b;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements com.google.android.flexbox.a, RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final Rect Z = new Rect();
    public int A;
    public int B;
    public int C;
    public int D;
    public boolean E;
    public boolean F;
    public List<FlexLine> G;
    public final com.google.android.flexbox.b H;
    public RecyclerView.Recycler I;
    public RecyclerView.State J;
    public c K;
    public b L;
    public OrientationHelper M;
    public OrientationHelper N;
    public SavedState O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public boolean T;
    public SparseArray<View> U;
    public final Context V;
    public View W;
    public int X;
    public b.C0372b Y;
    public int z;

    /* loaded from: classes6.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public int f8169a;
        public int b;
        public int c;
        public int d;
        public boolean e;
        public boolean f;
        public boolean g;

        public b() {
            this.d = 0;
        }

        public static /* synthetic */ int l(b bVar, int i) {
            int i2 = bVar.d + i;
            bVar.d = i2;
            return i2;
        }

        public final void r() {
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.E) {
                this.c = this.e ? FlexboxLayoutManager.this.M.getEndAfterPadding() : FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.M.getStartAfterPadding();
            } else {
                this.c = this.e ? FlexboxLayoutManager.this.M.getEndAfterPadding() : FlexboxLayoutManager.this.M.getStartAfterPadding();
            }
        }

        public final void s(View view) {
            OrientationHelper orientationHelper = FlexboxLayoutManager.this.A == 0 ? FlexboxLayoutManager.this.N : FlexboxLayoutManager.this.M;
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.E) {
                if (this.e) {
                    this.c = orientationHelper.getDecoratedStart(view) + orientationHelper.getTotalSpaceChange();
                } else {
                    this.c = orientationHelper.getDecoratedEnd(view);
                }
            } else if (this.e) {
                this.c = orientationHelper.getDecoratedEnd(view) + orientationHelper.getTotalSpaceChange();
            } else {
                this.c = orientationHelper.getDecoratedStart(view);
            }
            this.f8169a = FlexboxLayoutManager.this.getPosition(view);
            this.g = false;
            int[] iArr = FlexboxLayoutManager.this.H.c;
            int i = this.f8169a;
            if (i == -1) {
                i = 0;
            }
            int i2 = iArr[i];
            this.b = i2 != -1 ? i2 : 0;
            if (FlexboxLayoutManager.this.G.size() > this.b) {
                this.f8169a = ((FlexLine) FlexboxLayoutManager.this.G.get(this.b)).o;
            }
        }

        public final void t() {
            this.f8169a = -1;
            this.b = -1;
            this.c = Integer.MIN_VALUE;
            this.f = false;
            this.g = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.A == 0) {
                    this.e = FlexboxLayoutManager.this.z == 1;
                } else {
                    this.e = FlexboxLayoutManager.this.A == 2;
                }
            } else if (FlexboxLayoutManager.this.A == 0) {
                this.e = FlexboxLayoutManager.this.z == 3;
            } else {
                this.e = FlexboxLayoutManager.this.A == 2;
            }
        }

        @NonNull
        public String toString() {
            return "AnchorInfo{mPosition=" + this.f8169a + ", mFlexLinePosition=" + this.b + ", mCoordinate=" + this.c + ", mPerpendicularCoordinate=" + this.d + ", mLayoutFromEnd=" + this.e + ", mValid=" + this.f + ", mAssignedFromSavedState=" + this.g + '}';
        }
    }

    /* loaded from: classes6.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public int f8170a;
        public boolean b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public boolean j;

        public c() {
            this.h = 1;
            this.i = 1;
        }

        public static /* synthetic */ int c(c cVar, int i) {
            int i2 = cVar.e + i;
            cVar.e = i2;
            return i2;
        }

        public static /* synthetic */ int d(c cVar, int i) {
            int i2 = cVar.e - i;
            cVar.e = i2;
            return i2;
        }

        public static /* synthetic */ int i(c cVar, int i) {
            int i2 = cVar.f8170a - i;
            cVar.f8170a = i2;
            return i2;
        }

        public static /* synthetic */ int l(c cVar) {
            int i = cVar.c;
            cVar.c = i + 1;
            return i;
        }

        public static /* synthetic */ int m(c cVar) {
            int i = cVar.c;
            cVar.c = i - 1;
            return i;
        }

        public static /* synthetic */ int n(c cVar, int i) {
            int i2 = cVar.c + i;
            cVar.c = i2;
            return i2;
        }

        public static /* synthetic */ int q(c cVar, int i) {
            int i2 = cVar.f + i;
            cVar.f = i2;
            return i2;
        }

        public static /* synthetic */ int u(c cVar, int i) {
            int i2 = cVar.d + i;
            cVar.d = i2;
            return i2;
        }

        public static /* synthetic */ int v(c cVar, int i) {
            int i2 = cVar.d - i;
            cVar.d = i2;
            return i2;
        }

        public final boolean D(RecyclerView.State state, List<FlexLine> list) {
            int i;
            int i2 = this.d;
            return i2 >= 0 && i2 < state.getItemCount() && (i = this.c) >= 0 && i < list.size();
        }

        @NonNull
        public String toString() {
            return "LayoutState{mAvailable=" + this.f8170a + ", mFlexLinePosition=" + this.c + ", mPosition=" + this.d + ", mOffset=" + this.e + ", mScrollingOffset=" + this.f + ", mLastScrollDelta=" + this.g + ", mItemDirection=" + this.h + ", mLayoutDirection=" + this.i + '}';
        }
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
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

    private boolean t(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && h(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && h(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
    }

    public final boolean E(View view, int i) {
        return (isMainAxisDirectionHorizontal() || !this.E) ? this.M.getDecoratedStart(view) >= this.M.getEnd() - i : this.M.getDecoratedEnd(view) <= i;
    }

    public final boolean F(View view, int i) {
        return (isMainAxisDirectionHorizontal() || !this.E) ? this.M.getDecoratedEnd(view) <= i : this.M.getEnd() - this.M.getDecoratedStart(view) <= i;
    }

    public final void G() {
        this.G.clear();
        this.L.t();
        this.L.d = 0;
    }

    public final int H(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        L();
        View N = N(itemCount);
        View P = P(itemCount);
        if (state.getItemCount() == 0 || N == null || P == null) {
            return 0;
        }
        return Math.min(this.M.getTotalSpace(), this.M.getDecoratedEnd(P) - this.M.getDecoratedStart(N));
    }

    public final int I(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View N = N(itemCount);
        View P = P(itemCount);
        if (state.getItemCount() != 0 && N != null && P != null) {
            int position = getPosition(N);
            int position2 = getPosition(P);
            int abs = Math.abs(this.M.getDecoratedEnd(P) - this.M.getDecoratedStart(N));
            int[] iArr = this.H.c;
            int i = iArr[position];
            if (i != 0 && i != -1) {
                return Math.round((i * (abs / ((iArr[position2] - i) + 1))) + (this.M.getStartAfterPadding() - this.M.getDecoratedStart(N)));
            }
        }
        return 0;
    }

    public final int J(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View N = N(itemCount);
        View P = P(itemCount);
        if (state.getItemCount() == 0 || N == null || P == null) {
            return 0;
        }
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((Math.abs(this.M.getDecoratedEnd(P) - this.M.getDecoratedStart(N)) / ((findLastVisibleItemPosition() - findFirstVisibleItemPosition) + 1)) * state.getItemCount());
    }

    public final void K() {
        if (this.K == null) {
            this.K = new c();
        }
    }

    public final void L() {
        if (this.M != null) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            if (this.A == 0) {
                this.M = OrientationHelper.createHorizontalHelper(this);
                this.N = OrientationHelper.createVerticalHelper(this);
                return;
            }
            this.M = OrientationHelper.createVerticalHelper(this);
            this.N = OrientationHelper.createHorizontalHelper(this);
        } else if (this.A == 0) {
            this.M = OrientationHelper.createVerticalHelper(this);
            this.N = OrientationHelper.createHorizontalHelper(this);
        } else {
            this.M = OrientationHelper.createHorizontalHelper(this);
            this.N = OrientationHelper.createVerticalHelper(this);
        }
    }

    public final int M(RecyclerView.Recycler recycler, RecyclerView.State state, c cVar) {
        if (cVar.f != Integer.MIN_VALUE) {
            if (cVar.f8170a < 0) {
                c.q(cVar, cVar.f8170a);
            }
            i0(recycler, cVar);
        }
        int i = cVar.f8170a;
        int i2 = cVar.f8170a;
        int i3 = 0;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        while (true) {
            if ((i2 > 0 || this.K.b) && cVar.D(state, this.G)) {
                FlexLine flexLine = this.G.get(cVar.c);
                cVar.d = flexLine.o;
                i3 += f0(flexLine, cVar);
                if (isMainAxisDirectionHorizontal || !this.E) {
                    c.c(cVar, flexLine.getCrossSize() * cVar.i);
                } else {
                    c.d(cVar, flexLine.getCrossSize() * cVar.i);
                }
                i2 -= flexLine.getCrossSize();
            }
        }
        c.i(cVar, i3);
        if (cVar.f != Integer.MIN_VALUE) {
            c.q(cVar, i3);
            if (cVar.f8170a < 0) {
                c.q(cVar, cVar.f8170a);
            }
            i0(recycler, cVar);
        }
        return i - cVar.f8170a;
    }

    public final View N(int i) {
        View S = S(0, getChildCount(), i);
        if (S == null) {
            return null;
        }
        int i2 = this.H.c[getPosition(S)];
        if (i2 == -1) {
            return null;
        }
        return O(S, this.G.get(i2));
    }

    public final View O(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int i = flexLine.h;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (this.E && !isMainAxisDirectionHorizontal) {
                    if (this.M.getDecoratedEnd(view) >= this.M.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.M.getDecoratedStart(view) <= this.M.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    public final View P(int i) {
        View S = S(getChildCount() - 1, -1, i);
        if (S == null) {
            return null;
        }
        return Q(S, this.G.get(this.H.c[getPosition(S)]));
    }

    public final View Q(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int childCount = (getChildCount() - flexLine.h) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (this.E && !isMainAxisDirectionHorizontal) {
                    if (this.M.getDecoratedStart(view) <= this.M.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.M.getDecoratedEnd(view) >= this.M.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    public final View R(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (e0(childAt, z)) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }

    public final View S(int i, int i2, int i3) {
        int position;
        L();
        K();
        int startAfterPadding = this.M.getStartAfterPadding();
        int endAfterPadding = this.M.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (childAt != null && (position = getPosition(childAt)) >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.M.getDecoratedStart(childAt) >= startAfterPadding && this.M.getDecoratedEnd(childAt) <= endAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    public final int T(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int endAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.E) {
            int startAfterPadding = i - this.M.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = b0(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding2 = this.M.getEndAfterPadding() - i;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -b0(-endAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (!z || (endAfterPadding = this.M.getEndAfterPadding() - i3) <= 0) {
            return i2;
        }
        this.M.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    public final int U(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int startAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.E) {
            int endAfterPadding = this.M.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = b0(-endAfterPadding, recycler, state);
        } else {
            int startAfterPadding2 = i - this.M.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -b0(startAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (!z || (startAfterPadding = i3 - this.M.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.M.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    public final int V(View view) {
        return getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    public final View W() {
        return getChildAt(0);
    }

    public final int X(View view) {
        return getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
    }

    public final int Y(View view) {
        return getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
    }

    public final int Z(View view) {
        return getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
    }

    public int a0(int i) {
        return this.H.c[i];
    }

    public final int b0(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        L();
        int i2 = 1;
        this.K.j = true;
        boolean z = !isMainAxisDirectionHorizontal() && this.E;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int abs = Math.abs(i);
        t0(i2, abs);
        int M = this.K.f + M(recycler, state, this.K);
        if (M < 0) {
            return 0;
        }
        if (z) {
            if (abs > M) {
                i = (-i2) * M;
            }
        } else if (abs > M) {
            i = i2 * M;
        }
        this.M.offsetChildren(-i);
        this.K.g = i;
        return i;
    }

    public final int c0(int i) {
        int i2;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        L();
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        View view = this.W;
        int width = isMainAxisDirectionHorizontal ? view.getWidth() : view.getHeight();
        int width2 = isMainAxisDirectionHorizontal ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            int abs = Math.abs(i);
            if (i >= 0) {
                if (this.L.d + i <= 0) {
                    return i;
                }
                i2 = this.L.d;
            } else {
                i2 = Math.min((width2 + this.L.d) - width, abs);
            }
        } else if (i <= 0) {
            if (this.L.d + i >= 0) {
                return i;
            }
            i2 = this.L.d;
        } else {
            return Math.min((width2 - this.L.d) - width, i);
        }
        return -i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        if (this.A == 0) {
            return isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            int width = getWidth();
            View view = this.W;
            if (width <= (view != null ? view.getWidth() : 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        if (this.A == 0) {
            return !isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            return true;
        }
        int height = getHeight();
        View view = this.W;
        return height > (view != null ? view.getHeight() : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(@NonNull RecyclerView.State state) {
        return H(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(@NonNull RecyclerView.State state) {
        return I(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(@NonNull RecyclerView.State state) {
        return J(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        View childAt;
        if (getChildCount() == 0 || (childAt = getChildAt(0)) == null) {
            return null;
        }
        int i2 = i < getPosition(childAt) ? -1 : 1;
        if (isMainAxisDirectionHorizontal()) {
            return new PointF(0.0f, i2);
        }
        return new PointF(i2, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(@NonNull RecyclerView.State state) {
        return H(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(@NonNull RecyclerView.State state) {
        return I(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(@NonNull RecyclerView.State state) {
        return J(state);
    }

    public boolean d0() {
        return this.E;
    }

    public final boolean e0(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int X = X(view);
        int Z2 = Z(view);
        int Y = Y(view);
        int V = V(view);
        return z ? (paddingLeft <= X && width >= Y) && (paddingTop <= Z2 && height >= V) : (X >= width || Y >= paddingLeft) && (Z2 >= height || V >= paddingTop);
    }

    public final int f0(FlexLine flexLine, c cVar) {
        if (isMainAxisDirectionHorizontal()) {
            return g0(flexLine, cVar);
        }
        return h0(flexLine, cVar);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View R = R(0, getChildCount(), true);
        if (R == null) {
            return -1;
        }
        return getPosition(R);
    }

    public int findFirstVisibleItemPosition() {
        View R = R(0, getChildCount(), false);
        if (R == null) {
            return -1;
        }
        return getPosition(R);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View R = R(getChildCount() - 1, -1, true);
        if (R == null) {
            return -1;
        }
        return getPosition(R);
    }

    public int findLastVisibleItemPosition() {
        View R = R(getChildCount() - 1, -1, false);
        if (R == null) {
            return -1;
        }
        return getPosition(R);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int g0(com.google.android.flexbox.FlexLine r22, com.google.android.flexbox.FlexboxLayoutManager.c r23) {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.g0(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$c):int");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.C;
    }

    @Override // com.google.android.flexbox.a
    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    @Override // com.google.android.flexbox.a
    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthCrossAxis(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (isMainAxisDirectionHorizontal()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return leftDecorationWidth + rightDecorationWidth;
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (isMainAxisDirectionHorizontal()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return topDecorationHeight + bottomDecorationHeight;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.z;
    }

    @Override // com.google.android.flexbox.a
    public View getFlexItemAt(int i) {
        View view = this.U.get(i);
        return view != null ? view : this.I.getViewForPosition(i);
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return this.J.getItemCount();
    }

    @NonNull
    public List<FlexLine> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.G.size());
        int size = this.G.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.G.get(i);
            if (flexLine.getItemCount() != 0) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.a
    public List<FlexLine> getFlexLinesInternal() {
        return this.G;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.A;
    }

    public int getJustifyContent() {
        return this.B;
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        if (this.G.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.G.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, this.G.get(i2).e);
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int getMaxLine() {
        return this.D;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.T;
    }

    @Override // com.google.android.flexbox.a
    public View getReorderedFlexItemAt(int i) {
        return getFlexItemAt(i);
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int size = this.G.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.G.get(i2).g;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int h0(com.google.android.flexbox.FlexLine r26, com.google.android.flexbox.FlexboxLayoutManager.c r27) {
        /*
            Method dump skipped, instructions count: 541
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.h0(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$c):int");
    }

    public final void i0(RecyclerView.Recycler recycler, c cVar) {
        if (cVar.j) {
            if (cVar.i == -1) {
                k0(recycler, cVar);
            } else {
                l0(recycler, cVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override // com.google.android.flexbox.a
    public boolean isMainAxisDirectionHorizontal() {
        int i = this.z;
        return i == 0 || i == 1;
    }

    public final void j0(RecyclerView.Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    public final void k0(RecyclerView.Recycler recycler, c cVar) {
        int childCount;
        int i;
        View childAt;
        int i2;
        if (cVar.f < 0 || (childCount = getChildCount()) == 0 || (childAt = getChildAt(childCount - 1)) == null || (i2 = this.H.c[getPosition(childAt)]) == -1) {
            return;
        }
        FlexLine flexLine = this.G.get(i2);
        int i3 = i;
        while (true) {
            if (i3 < 0) {
                break;
            }
            View childAt2 = getChildAt(i3);
            if (childAt2 != null) {
                if (!E(childAt2, cVar.f)) {
                    break;
                } else if (flexLine.o != getPosition(childAt2)) {
                    continue;
                } else if (i2 <= 0) {
                    childCount = i3;
                    break;
                } else {
                    i2 += cVar.i;
                    flexLine = this.G.get(i2);
                    childCount = i3;
                }
            }
            i3--;
        }
        j0(recycler, childCount, i);
    }

    public final void l0(RecyclerView.Recycler recycler, c cVar) {
        int childCount;
        View childAt;
        if (cVar.f < 0 || (childCount = getChildCount()) == 0 || (childAt = getChildAt(0)) == null) {
            return;
        }
        int i = this.H.c[getPosition(childAt)];
        int i2 = -1;
        if (i == -1) {
            return;
        }
        FlexLine flexLine = this.G.get(i);
        int i3 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt2 = getChildAt(i3);
            if (childAt2 != null) {
                if (!F(childAt2, cVar.f)) {
                    break;
                } else if (flexLine.p != getPosition(childAt2)) {
                    continue;
                } else if (i >= this.G.size() - 1) {
                    i2 = i3;
                    break;
                } else {
                    i += cVar.i;
                    flexLine = this.G.get(i);
                    i2 = i3;
                }
            }
            i3++;
        }
        j0(recycler, 0, i2);
    }

    public final void m0() {
        int widthMode;
        if (isMainAxisDirectionHorizontal()) {
            widthMode = getHeightMode();
        } else {
            widthMode = getWidthMode();
        }
        this.K.b = widthMode == 0 || widthMode == Integer.MIN_VALUE;
    }

    public final void n0() {
        int layoutDirection = getLayoutDirection();
        int i = this.z;
        if (i == 0) {
            this.E = layoutDirection == 1;
            this.F = this.A == 2;
        } else if (i == 1) {
            this.E = layoutDirection != 1;
            this.F = this.A == 2;
        } else if (i == 2) {
            boolean z = layoutDirection == 1;
            this.E = z;
            if (this.A == 2) {
                this.E = !z;
            }
            this.F = false;
        } else if (i != 3) {
            this.E = false;
            this.F = false;
        } else {
            boolean z2 = layoutDirection == 1;
            this.E = z2;
            if (this.A == 2) {
                this.E = !z2;
            }
            this.F = true;
        }
    }

    public final boolean o0(RecyclerView.State state, b bVar) {
        View N;
        int startAfterPadding;
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        if (bVar.e) {
            N = P(state.getItemCount());
        } else {
            N = N(state.getItemCount());
        }
        if (N != null) {
            bVar.s(N);
            if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
                if (this.M.getDecoratedStart(N) >= this.M.getEndAfterPadding() || this.M.getDecoratedEnd(N) < this.M.getStartAfterPadding()) {
                    z = true;
                }
                if (z) {
                    if (bVar.e) {
                        startAfterPadding = this.M.getEndAfterPadding();
                    } else {
                        startAfterPadding = this.M.getStartAfterPadding();
                    }
                    bVar.c = startAfterPadding;
                }
            }
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.W = (View) recyclerView.getParent();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.T) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        r0(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(@NonNull RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        r0(Math.min(i, i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        r0(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        r0(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        this.I = recycler;
        this.J = state;
        int itemCount = state.getItemCount();
        if (itemCount == 0 && state.isPreLayout()) {
            return;
        }
        n0();
        L();
        K();
        this.H.t(itemCount);
        this.H.u(itemCount);
        this.H.s(itemCount);
        this.K.j = false;
        SavedState savedState = this.O;
        if (savedState != null && savedState.g(itemCount)) {
            this.P = this.O.h;
        }
        if (!this.L.f || this.P != -1 || this.O != null) {
            this.L.t();
            q0(state, this.L);
            this.L.f = true;
        }
        detachAndScrapAttachedViews(recycler);
        if (this.L.e) {
            v0(this.L, false, true);
        } else {
            u0(this.L, false, true);
        }
        s0(itemCount);
        M(recycler, state, this.K);
        if (this.L.e) {
            i2 = this.K.e;
            u0(this.L, true, false);
            M(recycler, state, this.K);
            i = this.K.e;
        } else {
            i = this.K.e;
            v0(this.L, true, false);
            M(recycler, state, this.K);
            i2 = this.K.e;
        }
        if (getChildCount() > 0) {
            if (this.L.e) {
                U(i2 + T(i, recycler, state, true), recycler, state, false);
            } else {
                T(i + U(i2, recycler, state, true), recycler, state, false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.O = null;
        this.P = -1;
        this.Q = Integer.MIN_VALUE;
        this.X = -1;
        this.L.t();
        this.U.clear();
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine) {
        calculateItemDecorationsForChild(view, Z);
        if (isMainAxisDirectionHorizontal()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            flexLine.e += leftDecorationWidth;
            flexLine.f += leftDecorationWidth;
            return;
        }
        int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        flexLine.e += topDecorationHeight;
        flexLine.f += topDecorationHeight;
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexLineAdded(FlexLine flexLine) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.O = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.O != null) {
            return new SavedState(this.O);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() <= 0) {
            savedState.h();
        } else {
            View W = W();
            savedState.h = getPosition(W);
            savedState.i = this.M.getDecoratedStart(W) - this.M.getStartAfterPadding();
        }
        return savedState;
    }

    public final boolean p0(RecyclerView.State state, b bVar, SavedState savedState) {
        int i;
        View childAt;
        int decoratedStart;
        if (!state.isPreLayout() && (i = this.P) != -1) {
            if (i >= 0 && i < state.getItemCount()) {
                bVar.f8169a = this.P;
                bVar.b = this.H.c[bVar.f8169a];
                SavedState savedState2 = this.O;
                if (savedState2 != null && savedState2.g(state.getItemCount())) {
                    bVar.c = this.M.getStartAfterPadding() + savedState.i;
                    bVar.g = true;
                    bVar.b = -1;
                    return true;
                } else if (this.Q == Integer.MIN_VALUE) {
                    View findViewByPosition = findViewByPosition(this.P);
                    if (findViewByPosition != null) {
                        if (this.M.getDecoratedMeasurement(findViewByPosition) > this.M.getTotalSpace()) {
                            bVar.r();
                            return true;
                        } else if (this.M.getDecoratedStart(findViewByPosition) - this.M.getStartAfterPadding() < 0) {
                            bVar.c = this.M.getStartAfterPadding();
                            bVar.e = false;
                            return true;
                        } else if (this.M.getEndAfterPadding() - this.M.getDecoratedEnd(findViewByPosition) < 0) {
                            bVar.c = this.M.getEndAfterPadding();
                            bVar.e = true;
                            return true;
                        } else {
                            if (bVar.e) {
                                decoratedStart = this.M.getDecoratedEnd(findViewByPosition) + this.M.getTotalSpaceChange();
                            } else {
                                decoratedStart = this.M.getDecoratedStart(findViewByPosition);
                            }
                            bVar.c = decoratedStart;
                        }
                    } else {
                        if (getChildCount() > 0 && (childAt = getChildAt(0)) != null) {
                            bVar.e = this.P < getPosition(childAt);
                        }
                        bVar.r();
                    }
                    return true;
                } else {
                    if (isMainAxisDirectionHorizontal() || !this.E) {
                        bVar.c = this.M.getStartAfterPadding() + this.Q;
                    } else {
                        bVar.c = this.Q - this.M.getEndPadding();
                    }
                    return true;
                }
            }
            this.P = -1;
            this.Q = Integer.MIN_VALUE;
        }
        return false;
    }

    public final void q0(RecyclerView.State state, b bVar) {
        if (p0(state, bVar, this.O) || o0(state, bVar)) {
            return;
        }
        bVar.r();
        bVar.f8169a = 0;
        bVar.b = 0;
    }

    public final void r0(int i) {
        if (i >= findLastVisibleItemPosition()) {
            return;
        }
        int childCount = getChildCount();
        this.H.t(childCount);
        this.H.u(childCount);
        this.H.s(childCount);
        if (i >= this.H.c.length) {
            return;
        }
        this.X = i;
        View W = W();
        if (W == null) {
            return;
        }
        this.P = getPosition(W);
        if (!isMainAxisDirectionHorizontal() && this.E) {
            this.Q = this.M.getDecoratedEnd(W) + this.M.getEndPadding();
        } else {
            this.Q = this.M.getDecoratedStart(W) - this.M.getStartAfterPadding();
        }
    }

    public final void s0(int i) {
        int i2;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        boolean z = true;
        if (isMainAxisDirectionHorizontal()) {
            int i3 = this.R;
            if (i3 == Integer.MIN_VALUE || i3 == width) {
                z = false;
            }
            i2 = this.K.b ? this.V.getResources().getDisplayMetrics().heightPixels : this.K.f8170a;
        } else {
            int i4 = this.S;
            if (i4 == Integer.MIN_VALUE || i4 == height) {
                z = false;
            }
            if (!this.K.b) {
                i2 = this.K.f8170a;
            } else {
                i2 = this.V.getResources().getDisplayMetrics().widthPixels;
            }
        }
        int i5 = i2;
        this.R = width;
        this.S = height;
        int i6 = this.X;
        if (i6 != -1 || (this.P == -1 && !z)) {
            int min = i6 != -1 ? Math.min(i6, this.L.f8169a) : this.L.f8169a;
            this.Y.a();
            if (isMainAxisDirectionHorizontal()) {
                if (this.G.size() > 0) {
                    this.H.j(this.G, min);
                    this.H.b(this.Y, makeMeasureSpec, makeMeasureSpec2, i5, min, this.L.f8169a, this.G);
                } else {
                    this.H.s(i);
                    this.H.d(this.Y, makeMeasureSpec, makeMeasureSpec2, i5, 0, this.G);
                }
            } else if (this.G.size() > 0) {
                this.H.j(this.G, min);
                this.H.b(this.Y, makeMeasureSpec2, makeMeasureSpec, i5, min, this.L.f8169a, this.G);
            } else {
                this.H.s(i);
                this.H.g(this.Y, makeMeasureSpec, makeMeasureSpec2, i5, 0, this.G);
            }
            this.G = this.Y.f8172a;
            this.H.q(makeMeasureSpec, makeMeasureSpec2, min);
            this.H.Y(min);
        } else if (this.L.e) {
        } else {
            this.G.clear();
            this.Y.a();
            if (isMainAxisDirectionHorizontal()) {
                this.H.e(this.Y, makeMeasureSpec, makeMeasureSpec2, i5, this.L.f8169a, this.G);
            } else {
                this.H.h(this.Y, makeMeasureSpec, makeMeasureSpec2, i5, this.L.f8169a, this.G);
            }
            this.G = this.Y.f8172a;
            this.H.p(makeMeasureSpec, makeMeasureSpec2);
            this.H.X();
            b bVar = this.L;
            bVar.b = this.H.c[bVar.f8169a];
            this.K.c = this.L.b;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isMainAxisDirectionHorizontal() && this.A != 0) {
            int c0 = c0(i);
            b.l(this.L, c0);
            this.N.offsetChildren(-c0);
            return c0;
        }
        int b0 = b0(i, recycler, state);
        this.U.clear();
        return b0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.P = i;
        this.Q = Integer.MIN_VALUE;
        SavedState savedState = this.O;
        if (savedState != null) {
            savedState.h();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isMainAxisDirectionHorizontal() && (this.A != 0 || isMainAxisDirectionHorizontal())) {
            int c0 = c0(i);
            b.l(this.L, c0);
            this.N.offsetChildren(-c0);
            return c0;
        }
        int b0 = b0(i, recycler, state);
        this.U.clear();
        return b0;
    }

    public void setAlignContent(int i) {
        throw new UnsupportedOperationException("Setting the alignContent in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to use this attribute.");
    }

    public void setAlignItems(int i) {
        int i2 = this.C;
        if (i2 != i) {
            if (i2 == 4 || i == 4) {
                removeAllViews();
                G();
            }
            this.C = i;
            requestLayout();
        }
    }

    public void setFlexDirection(int i) {
        if (this.z != i) {
            removeAllViews();
            this.z = i;
            this.M = null;
            this.N = null;
            G();
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<FlexLine> list) {
        this.G = list;
    }

    public void setFlexWrap(int i) {
        if (i != 2) {
            int i2 = this.A;
            if (i2 != i) {
                if (i2 == 0 || i == 0) {
                    removeAllViews();
                    G();
                }
                this.A = i;
                this.M = null;
                this.N = null;
                requestLayout();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
    }

    public void setJustifyContent(int i) {
        if (this.B != i) {
            this.B = i;
            requestLayout();
        }
    }

    public void setMaxLine(int i) {
        if (this.D != i) {
            this.D = i;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.T = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public final void t0(int i, int i2) {
        this.K.i = i;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        boolean z = !isMainAxisDirectionHorizontal && this.E;
        if (i == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            if (childAt == null) {
                return;
            }
            this.K.e = this.M.getDecoratedEnd(childAt);
            int position = getPosition(childAt);
            View Q = Q(childAt, this.G.get(this.H.c[position]));
            this.K.h = 1;
            c cVar = this.K;
            cVar.d = position + cVar.h;
            if (this.H.c.length <= this.K.d) {
                this.K.c = -1;
            } else {
                c cVar2 = this.K;
                cVar2.c = this.H.c[cVar2.d];
            }
            if (z) {
                this.K.e = this.M.getDecoratedStart(Q);
                this.K.f = (-this.M.getDecoratedStart(Q)) + this.M.getStartAfterPadding();
                c cVar3 = this.K;
                cVar3.f = Math.max(cVar3.f, 0);
            } else {
                this.K.e = this.M.getDecoratedEnd(Q);
                this.K.f = this.M.getDecoratedEnd(Q) - this.M.getEndAfterPadding();
            }
            if ((this.K.c == -1 || this.K.c > this.G.size() - 1) && this.K.d <= getFlexItemCount()) {
                int i3 = i2 - this.K.f;
                this.Y.a();
                if (i3 > 0) {
                    if (isMainAxisDirectionHorizontal) {
                        this.H.d(this.Y, makeMeasureSpec, makeMeasureSpec2, i3, this.K.d, this.G);
                    } else {
                        this.H.g(this.Y, makeMeasureSpec, makeMeasureSpec2, i3, this.K.d, this.G);
                    }
                    this.H.q(makeMeasureSpec, makeMeasureSpec2, this.K.d);
                    this.H.Y(this.K.d);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            if (childAt2 == null) {
                return;
            }
            this.K.e = this.M.getDecoratedStart(childAt2);
            int position2 = getPosition(childAt2);
            View O = O(childAt2, this.G.get(this.H.c[position2]));
            this.K.h = 1;
            int i4 = this.H.c[position2];
            if (i4 == -1) {
                i4 = 0;
            }
            if (i4 > 0) {
                this.K.d = position2 - this.G.get(i4 - 1).getItemCount();
            } else {
                this.K.d = -1;
            }
            this.K.c = i4 > 0 ? i4 - 1 : 0;
            if (z) {
                this.K.e = this.M.getDecoratedEnd(O);
                this.K.f = this.M.getDecoratedEnd(O) - this.M.getEndAfterPadding();
                c cVar4 = this.K;
                cVar4.f = Math.max(cVar4.f, 0);
            } else {
                this.K.e = this.M.getDecoratedStart(O);
                this.K.f = (-this.M.getDecoratedStart(O)) + this.M.getStartAfterPadding();
            }
        }
        c cVar5 = this.K;
        cVar5.f8170a = i2 - cVar5.f;
    }

    public final void u0(b bVar, boolean z, boolean z2) {
        if (z2) {
            m0();
        } else {
            this.K.b = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.E) {
            this.K.f8170a = bVar.c - getPaddingRight();
        } else {
            this.K.f8170a = this.M.getEndAfterPadding() - bVar.c;
        }
        this.K.d = bVar.f8169a;
        this.K.h = 1;
        this.K.i = 1;
        this.K.e = bVar.c;
        this.K.f = Integer.MIN_VALUE;
        this.K.c = bVar.b;
        if (!z || this.G.size() <= 1 || bVar.b < 0 || bVar.b >= this.G.size() - 1) {
            return;
        }
        c.l(this.K);
        c.u(this.K, this.G.get(bVar.b).getItemCount());
    }

    @Override // com.google.android.flexbox.a
    public void updateViewCache(int i, View view) {
        this.U.put(i, view);
    }

    public final void v0(b bVar, boolean z, boolean z2) {
        if (z2) {
            m0();
        } else {
            this.K.b = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.E) {
            this.K.f8170a = (this.W.getWidth() - bVar.c) - this.M.getStartAfterPadding();
        } else {
            this.K.f8170a = bVar.c - this.M.getStartAfterPadding();
        }
        this.K.d = bVar.f8169a;
        this.K.h = 1;
        this.K.i = -1;
        this.K.e = bVar.c;
        this.K.f = Integer.MIN_VALUE;
        this.K.c = bVar.b;
        if (!z || bVar.b <= 0 || this.G.size() <= bVar.b) {
            return;
        }
        c.m(this.K);
        c.v(this.K, this.G.get(bVar.b).getItemCount());
    }

    /* loaded from: classes6.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int h;
        public int i;

        /* loaded from: classes6.dex */
        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public final boolean g(int i) {
            int i2 = this.h;
            return i2 >= 0 && i2 < i;
        }

        public final void h() {
            this.h = -1;
        }

        @NonNull
        public String toString() {
            return "SavedState{mAnchorPosition=" + this.h + ", mAnchorOffset=" + this.i + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
        }

        public SavedState() {
        }

        public SavedState(Parcel parcel) {
            this.h = parcel.readInt();
            this.i = parcel.readInt();
        }

        public SavedState(SavedState savedState) {
            this.h = savedState.h;
            this.i = savedState.i;
        }
    }

    public FlexboxLayoutManager(Context context, int i) {
        this(context, i, 1);
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.D = -1;
        this.G = new ArrayList();
        this.H = new com.google.android.flexbox.b(this);
        this.L = new b();
        this.P = -1;
        this.Q = Integer.MIN_VALUE;
        this.R = Integer.MIN_VALUE;
        this.S = Integer.MIN_VALUE;
        this.U = new SparseArray<>();
        this.X = -1;
        this.Y = new b.C0372b();
        setFlexDirection(i);
        setFlexWrap(i2);
        setAlignItems(4);
        this.V = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        r0(i);
    }

    /* loaded from: classes6.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new a();
        public float l;
        public float m;
        public int n;
        public float o;
        public int p;
        public int q;
        public int r;
        public int s;
        public boolean t;

        /* loaded from: classes6.dex */
        public class a implements Parcelable.Creator<LayoutParams> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.l = 0.0f;
            this.m = 1.0f;
            this.n = -1;
            this.o = -1.0f;
            this.r = 16777215;
            this.s = 16777215;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getAlignSelf() {
            return this.n;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexBasisPercent() {
            return this.o;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexGrow() {
            return this.l;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexShrink() {
            return this.m;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginBottom() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginLeft() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginRight() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginTop() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxHeight() {
            return this.s;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxWidth() {
            return this.r;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinHeight() {
            return this.q;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinWidth() {
            return this.p;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return 1;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean isWrapBefore() {
            return this.t;
        }

        public void setAlignSelf(int i) {
            this.n = i;
        }

        public void setFlexBasisPercent(float f) {
            this.o = f;
        }

        public void setFlexGrow(float f) {
            this.l = f;
        }

        public void setFlexShrink(float f) {
            this.m = f;
        }

        public void setHeight(int i) {
            ((ViewGroup.MarginLayoutParams) this).height = i;
        }

        public void setMaxHeight(int i) {
            this.s = i;
        }

        public void setMaxWidth(int i) {
            this.r = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinHeight(int i) {
            this.q = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinWidth(int i) {
            this.p = i;
        }

        public void setOrder(int i) {
            throw new UnsupportedOperationException("Setting the order in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to reorder using the attribute.");
        }

        public void setWidth(int i) {
            ((ViewGroup.MarginLayoutParams) this).width = i;
        }

        public void setWrapBefore(boolean z) {
            this.t = z;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.l);
            parcel.writeFloat(this.m);
            parcel.writeInt(this.n);
            parcel.writeFloat(this.o);
            parcel.writeInt(this.p);
            parcel.writeInt(this.q);
            parcel.writeInt(this.r);
            parcel.writeInt(this.s);
            parcel.writeByte(this.t ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.l = 0.0f;
            this.m = 1.0f;
            this.n = -1;
            this.o = -1.0f;
            this.r = 16777215;
            this.s = 16777215;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.l = 0.0f;
            this.m = 1.0f;
            this.n = -1;
            this.o = -1.0f;
            this.r = 16777215;
            this.s = 16777215;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.l = 0.0f;
            this.m = 1.0f;
            this.n = -1;
            this.o = -1.0f;
            this.r = 16777215;
            this.s = 16777215;
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.l = 0.0f;
            this.m = 1.0f;
            this.n = -1;
            this.o = -1.0f;
            this.r = 16777215;
            this.s = 16777215;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((RecyclerView.LayoutParams) layoutParams);
            this.l = 0.0f;
            this.m = 1.0f;
            this.n = -1;
            this.o = -1.0f;
            this.r = 16777215;
            this.s = 16777215;
            this.l = layoutParams.l;
            this.m = layoutParams.m;
            this.n = layoutParams.n;
            this.o = layoutParams.o;
            this.p = layoutParams.p;
            this.q = layoutParams.q;
            this.r = layoutParams.r;
            this.s = layoutParams.s;
            this.t = layoutParams.t;
        }

        public LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.l = 0.0f;
            this.m = 1.0f;
            this.n = -1;
            this.o = -1.0f;
            this.r = 16777215;
            this.s = 16777215;
            this.l = parcel.readFloat();
            this.m = parcel.readFloat();
            this.n = parcel.readInt();
            this.o = parcel.readFloat();
            this.p = parcel.readInt();
            this.q = parcel.readInt();
            this.r = parcel.readInt();
            this.s = parcel.readInt();
            this.t = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.D = -1;
        this.G = new ArrayList();
        this.H = new com.google.android.flexbox.b(this);
        this.L = new b();
        this.P = -1;
        this.Q = Integer.MIN_VALUE;
        this.R = Integer.MIN_VALUE;
        this.S = Integer.MIN_VALUE;
        this.U = new SparseArray<>();
        this.X = -1;
        this.Y = new b.C0372b();
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0) {
            if (i3 == 1) {
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                } else {
                    setFlexDirection(2);
                }
            }
        } else if (properties.reverseLayout) {
            setFlexDirection(1);
        } else {
            setFlexDirection(0);
        }
        setFlexWrap(1);
        setAlignItems(4);
        this.V = context;
    }
}
