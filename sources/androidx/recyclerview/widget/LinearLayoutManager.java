package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.util.List;
/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    public b A;
    public OrientationHelper B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public int H;
    public int I;
    public boolean J;
    public SavedState K;
    public final a L;
    public final LayoutChunkResult M;
    public int N;
    public int[] O;
    public int z;

    /* loaded from: classes.dex */
    public static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        public void a() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int h;
        public int i;
        public boolean j;

        /* loaded from: classes.dex */
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

        public SavedState() {
        }

        public boolean a() {
            return this.h >= 0;
        }

        public void b() {
            this.h = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public OrientationHelper f1597a;
        public int b;
        public int c;
        public boolean d;
        public boolean e;

        public a() {
            e();
        }

        public void a() {
            int startAfterPadding;
            if (this.d) {
                startAfterPadding = this.f1597a.getEndAfterPadding();
            } else {
                startAfterPadding = this.f1597a.getStartAfterPadding();
            }
            this.c = startAfterPadding;
        }

        public void b(View view, int i) {
            if (this.d) {
                this.c = this.f1597a.getDecoratedEnd(view) + this.f1597a.getTotalSpaceChange();
            } else {
                this.c = this.f1597a.getDecoratedStart(view);
            }
            this.b = i;
        }

        public void c(View view, int i) {
            int totalSpaceChange = this.f1597a.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                b(view, i);
                return;
            }
            this.b = i;
            if (this.d) {
                int endAfterPadding = (this.f1597a.getEndAfterPadding() - totalSpaceChange) - this.f1597a.getDecoratedEnd(view);
                this.c = this.f1597a.getEndAfterPadding() - endAfterPadding;
                if (endAfterPadding > 0) {
                    int decoratedMeasurement = this.c - this.f1597a.getDecoratedMeasurement(view);
                    int startAfterPadding = this.f1597a.getStartAfterPadding();
                    int min = decoratedMeasurement - (startAfterPadding + Math.min(this.f1597a.getDecoratedStart(view) - startAfterPadding, 0));
                    if (min < 0) {
                        this.c += Math.min(endAfterPadding, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int decoratedStart = this.f1597a.getDecoratedStart(view);
            int startAfterPadding2 = decoratedStart - this.f1597a.getStartAfterPadding();
            this.c = decoratedStart;
            if (startAfterPadding2 > 0) {
                int endAfterPadding2 = (this.f1597a.getEndAfterPadding() - Math.min(0, (this.f1597a.getEndAfterPadding() - totalSpaceChange) - this.f1597a.getDecoratedEnd(view))) - (decoratedStart + this.f1597a.getDecoratedMeasurement(view));
                if (endAfterPadding2 < 0) {
                    this.c -= Math.min(startAfterPadding2, -endAfterPadding2);
                }
            }
        }

        public boolean d(View view, RecyclerView.State state) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
        }

        public void e() {
            this.b = -1;
            this.c = Integer.MIN_VALUE;
            this.d = false;
            this.e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public boolean j;
        public int k;
        public boolean m;

        /* renamed from: a  reason: collision with root package name */
        public boolean f1598a = true;
        public int h = 0;
        public int i = 0;
        public List<RecyclerView.ViewHolder> l = null;

        public void a() {
            b(null);
        }

        public void b(View view) {
            View f = f(view);
            if (f == null) {
                this.d = -1;
            } else {
                this.d = ((RecyclerView.LayoutParams) f.getLayoutParams()).getViewLayoutPosition();
            }
        }

        public boolean c(RecyclerView.State state) {
            int i = this.d;
            return i >= 0 && i < state.getItemCount();
        }

        public View d(RecyclerView.Recycler recycler) {
            if (this.l != null) {
                return e();
            }
            View viewForPosition = recycler.getViewForPosition(this.d);
            this.d += this.e;
            return viewForPosition;
        }

        public final View e() {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                View view = this.l.get(i).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (!layoutParams.isItemRemoved() && this.d == layoutParams.getViewLayoutPosition()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public View f(View view) {
            int viewLayoutPosition;
            int size = this.l.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.l.get(i2).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.isItemRemoved() && (viewLayoutPosition = (layoutParams.getViewLayoutPosition() - this.d) * this.e) >= 0 && viewLayoutPosition < i) {
                    view2 = view3;
                    if (viewLayoutPosition == 0) {
                        break;
                    }
                    i = viewLayoutPosition;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public final int A(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        D();
        return k.c(state, this.B, H(!this.G, true), G(!this.G, true), this, this.G);
    }

    public int B(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.z == 1) ? 1 : Integer.MIN_VALUE : this.z == 0 ? 1 : Integer.MIN_VALUE : this.z == 1 ? -1 : Integer.MIN_VALUE : this.z == 0 ? -1 : Integer.MIN_VALUE : (this.z != 1 && isLayoutRTL()) ? -1 : 1 : (this.z != 1 && isLayoutRTL()) ? 1 : -1;
    }

    public b C() {
        return new b();
    }

    public void D() {
        if (this.A == null) {
            this.A = C();
        }
    }

    public int E(RecyclerView.Recycler recycler, b bVar, RecyclerView.State state, boolean z) {
        int i = bVar.c;
        int i2 = bVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                bVar.g = i2 + i;
            }
            V(recycler, bVar);
        }
        int i3 = bVar.c + bVar.h;
        LayoutChunkResult layoutChunkResult = this.M;
        while (true) {
            if ((!bVar.m && i3 <= 0) || !bVar.c(state)) {
                break;
            }
            layoutChunkResult.a();
            S(recycler, state, bVar, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                bVar.b += layoutChunkResult.mConsumed * bVar.f;
                if (!layoutChunkResult.mIgnoreConsumed || bVar.l != null || !state.isPreLayout()) {
                    int i4 = bVar.c;
                    int i5 = layoutChunkResult.mConsumed;
                    bVar.c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = bVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + layoutChunkResult.mConsumed;
                    bVar.g = i7;
                    int i8 = bVar.c;
                    if (i8 < 0) {
                        bVar.g = i7 + i8;
                    }
                    V(recycler, bVar);
                }
                if (z && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - bVar.c;
    }

    public final View F() {
        return J(0, getChildCount());
    }

    public View G(boolean z, boolean z2) {
        if (this.E) {
            return K(0, getChildCount(), z, z2);
        }
        return K(getChildCount() - 1, -1, z, z2);
    }

    public View H(boolean z, boolean z2) {
        if (this.E) {
            return K(getChildCount() - 1, -1, z, z2);
        }
        return K(0, getChildCount(), z, z2);
    }

    public final View I() {
        return J(getChildCount() - 1, -1);
    }

    public View J(int i, int i2) {
        int i3;
        int i4;
        D();
        if ((i2 > i ? (char) 1 : i2 < i ? (char) 65535 : (char) 0) == 0) {
            return getChildAt(i);
        }
        if (this.B.getDecoratedStart(getChildAt(i)) < this.B.getStartAfterPadding()) {
            i3 = 16644;
            i4 = ErrorCode.SUB_ERR_OFFSET_OVER;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        if (this.z == 0) {
            return this.l.a(i, i2, i3, i4);
        }
        return this.m.a(i, i2, i3, i4);
    }

    public View K(int i, int i2, boolean z, boolean z2) {
        D();
        int i3 = z ? 24579 : 320;
        int i4 = z2 ? 320 : 0;
        if (this.z == 0) {
            return this.l.a(i, i2, i3, i4);
        }
        return this.m.a(i, i2, i3, i4);
    }

    public final View L() {
        return this.E ? F() : I();
    }

    public final View M() {
        return this.E ? I() : F();
    }

    public View N(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z2) {
        int i;
        int i2;
        D();
        int childCount = getChildCount();
        int i3 = -1;
        if (z2) {
            i = getChildCount() - 1;
            i2 = -1;
        } else {
            i3 = childCount;
            i = 0;
            i2 = 1;
        }
        int itemCount = state.getItemCount();
        int startAfterPadding = this.B.getStartAfterPadding();
        int endAfterPadding = this.B.getEndAfterPadding();
        View view = null;
        View view2 = null;
        View view3 = null;
        while (i != i3) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            int decoratedStart = this.B.getDecoratedStart(childAt);
            int decoratedEnd = this.B.getDecoratedEnd(childAt);
            if (position >= 0 && position < itemCount) {
                if (!((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    boolean z3 = decoratedEnd <= startAfterPadding && decoratedStart < startAfterPadding;
                    boolean z4 = decoratedStart >= endAfterPadding && decoratedEnd > endAfterPadding;
                    if (!z3 && !z4) {
                        return childAt;
                    }
                    if (z) {
                        if (!z4) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    } else {
                        if (!z3) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    }
                } else if (view3 == null) {
                    view3 = childAt;
                }
            }
            i += i2;
        }
        return view != null ? view : view2 != null ? view2 : view3;
    }

    public final int O(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.B.getEndAfterPadding() - i;
        if (endAfterPadding2 > 0) {
            int i2 = -b0(-endAfterPadding2, recycler, state);
            int i3 = i + i2;
            if (!z || (endAfterPadding = this.B.getEndAfterPadding() - i3) <= 0) {
                return i2;
            }
            this.B.offsetChildren(endAfterPadding);
            return endAfterPadding + i2;
        }
        return 0;
    }

    public final int P(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i - this.B.getStartAfterPadding();
        if (startAfterPadding2 > 0) {
            int i2 = -b0(startAfterPadding2, recycler, state);
            int i3 = i + i2;
            if (!z || (startAfterPadding = i3 - this.B.getStartAfterPadding()) <= 0) {
                return i2;
            }
            this.B.offsetChildren(-startAfterPadding);
            return i2 - startAfterPadding;
        }
        return 0;
    }

    public final View Q() {
        return getChildAt(this.E ? 0 : getChildCount() - 1);
    }

    public final View R() {
        return getChildAt(this.E ? getChildCount() - 1 : 0);
    }

    public void S(RecyclerView.Recycler recycler, RecyclerView.State state, b bVar, LayoutChunkResult layoutChunkResult) {
        int i;
        int i2;
        int i3;
        int i4;
        int decoratedMeasurementInOther;
        View d = bVar.d(recycler);
        if (d == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) d.getLayoutParams();
        if (bVar.l == null) {
            if (this.E == (bVar.f == -1)) {
                addView(d);
            } else {
                addView(d, 0);
            }
        } else {
            if (this.E == (bVar.f == -1)) {
                addDisappearingView(d);
            } else {
                addDisappearingView(d, 0);
            }
        }
        measureChildWithMargins(d, 0, 0);
        layoutChunkResult.mConsumed = this.B.getDecoratedMeasurement(d);
        if (this.z == 1) {
            if (isLayoutRTL()) {
                decoratedMeasurementInOther = getWidth() - getPaddingRight();
                i4 = decoratedMeasurementInOther - this.B.getDecoratedMeasurementInOther(d);
            } else {
                i4 = getPaddingLeft();
                decoratedMeasurementInOther = this.B.getDecoratedMeasurementInOther(d) + i4;
            }
            if (bVar.f == -1) {
                int i5 = bVar.b;
                i3 = i5;
                i2 = decoratedMeasurementInOther;
                i = i5 - layoutChunkResult.mConsumed;
            } else {
                int i6 = bVar.b;
                i = i6;
                i2 = decoratedMeasurementInOther;
                i3 = layoutChunkResult.mConsumed + i6;
            }
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther2 = this.B.getDecoratedMeasurementInOther(d) + paddingTop;
            if (bVar.f == -1) {
                int i7 = bVar.b;
                i2 = i7;
                i = paddingTop;
                i3 = decoratedMeasurementInOther2;
                i4 = i7 - layoutChunkResult.mConsumed;
            } else {
                int i8 = bVar.b;
                i = paddingTop;
                i2 = layoutChunkResult.mConsumed + i8;
                i3 = decoratedMeasurementInOther2;
                i4 = i8;
            }
        }
        layoutDecoratedWithMargins(d, i4, i, i2, i3);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = d.hasFocusable();
    }

    public final void T(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        if (!state.willRunPredictiveAnimations() || getChildCount() == 0 || state.isPreLayout() || !supportsPredictiveItemAnimations()) {
            return;
        }
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        int size = scrapList.size();
        int position = getPosition(getChildAt(0));
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView.ViewHolder viewHolder = scrapList.get(i5);
            if (!viewHolder.isRemoved()) {
                if ((viewHolder.getLayoutPosition() < position) != this.E ? true : true) {
                    i3 += this.B.getDecoratedMeasurement(viewHolder.itemView);
                } else {
                    i4 += this.B.getDecoratedMeasurement(viewHolder.itemView);
                }
            }
        }
        this.A.l = scrapList;
        if (i3 > 0) {
            i0(getPosition(R()), i);
            b bVar = this.A;
            bVar.h = i3;
            bVar.c = 0;
            bVar.a();
            E(recycler, this.A, state, false);
        }
        if (i4 > 0) {
            g0(getPosition(Q()), i2);
            b bVar2 = this.A;
            bVar2.h = i4;
            bVar2.c = 0;
            bVar2.a();
            E(recycler, this.A, state, false);
        }
        this.A.l = null;
    }

    public void U(RecyclerView.Recycler recycler, RecyclerView.State state, a aVar, int i) {
    }

    public final void V(RecyclerView.Recycler recycler, b bVar) {
        if (!bVar.f1598a || bVar.m) {
            return;
        }
        int i = bVar.g;
        int i2 = bVar.i;
        if (bVar.f == -1) {
            X(recycler, i, i2);
        } else {
            Y(recycler, i, i2);
        }
    }

    public final void W(RecyclerView.Recycler recycler, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                removeAndRecycleViewAt(i, recycler);
                i--;
            }
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            removeAndRecycleViewAt(i3, recycler);
        }
    }

    public final void X(RecyclerView.Recycler recycler, int i, int i2) {
        int childCount = getChildCount();
        if (i < 0) {
            return;
        }
        int end = (this.B.getEnd() - i) + i2;
        if (this.E) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (this.B.getDecoratedStart(childAt) < end || this.B.getTransformedStartWithDecoration(childAt) < end) {
                    W(recycler, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = childCount - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View childAt2 = getChildAt(i5);
            if (this.B.getDecoratedStart(childAt2) < end || this.B.getTransformedStartWithDecoration(childAt2) < end) {
                W(recycler, i4, i5);
                return;
            }
        }
    }

    public final void Y(RecyclerView.Recycler recycler, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int childCount = getChildCount();
        if (!this.E) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (this.B.getDecoratedEnd(childAt) > i3 || this.B.getTransformedEndWithDecoration(childAt) > i3) {
                    W(recycler, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = childCount - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View childAt2 = getChildAt(i6);
            if (this.B.getDecoratedEnd(childAt2) > i3 || this.B.getTransformedEndWithDecoration(childAt2) > i3) {
                W(recycler, i5, i6);
                return;
            }
        }
    }

    public boolean Z() {
        return this.B.getMode() == 0 && this.B.getEnd() == 0;
    }

    public final void a0() {
        if (this.z != 1 && isLayoutRTL()) {
            this.E = !this.D;
        } else {
            this.E = this.D;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.K == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public int b0(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        D();
        this.A.f1598a = true;
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        f0(i2, abs, true, state);
        b bVar = this.A;
        int E = bVar.g + E(recycler, bVar, state, false);
        if (E < 0) {
            return 0;
        }
        if (abs > E) {
            i = i2 * E;
        }
        this.B.offsetChildren(-i);
        this.A.k = i;
        return i;
    }

    public final boolean c0(RecyclerView.Recycler recycler, RecyclerView.State state, a aVar) {
        View N;
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null && aVar.d(focusedChild, state)) {
            aVar.c(focusedChild, getPosition(focusedChild));
            return true;
        }
        boolean z2 = this.C;
        boolean z3 = this.F;
        if (z2 == z3 && (N = N(recycler, state, aVar.d, z3)) != null) {
            aVar.b(N, getPosition(N));
            if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
                int decoratedStart = this.B.getDecoratedStart(N);
                int decoratedEnd = this.B.getDecoratedEnd(N);
                int startAfterPadding = this.B.getStartAfterPadding();
                int endAfterPadding = this.B.getEndAfterPadding();
                boolean z4 = decoratedEnd <= startAfterPadding && decoratedStart < startAfterPadding;
                if (decoratedStart >= endAfterPadding && decoratedEnd > endAfterPadding) {
                    z = true;
                }
                if (z4 || z) {
                    if (aVar.d) {
                        startAfterPadding = endAfterPadding;
                    }
                    aVar.c = startAfterPadding;
                }
            }
            return true;
        }
        return false;
    }

    public void calculateExtraLayoutSpace(@NonNull RecyclerView.State state, @NonNull int[] iArr) {
        int i;
        int extraLayoutSpace = getExtraLayoutSpace(state);
        if (this.A.f == -1) {
            i = 0;
        } else {
            i = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        iArr[0] = extraLayoutSpace;
        iArr[1] = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.z == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.z == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        if (this.z != 0) {
            i = i2;
        }
        if (getChildCount() == 0 || i == 0) {
            return;
        }
        D();
        f0(i > 0 ? 1 : -1, Math.abs(i), true, state);
        x(state, this.A, layoutPrefetchRegistry);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        boolean z;
        int i2;
        SavedState savedState = this.K;
        if (savedState != null && savedState.a()) {
            SavedState savedState2 = this.K;
            z = savedState2.j;
            i2 = savedState2.h;
        } else {
            a0();
            z = this.E;
            i2 = this.H;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.N && i2 >= 0 && i2 < i; i4++) {
            layoutPrefetchRegistry.addPosition(i2, 0);
            i2 += i3;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return y(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return z(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return A(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = (i < getPosition(getChildAt(0))) != this.E ? -1 : 1;
        if (this.z == 0) {
            return new PointF(i2, 0.0f);
        }
        return new PointF(0.0f, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return y(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return z(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return A(state);
    }

    public final boolean d0(RecyclerView.State state, a aVar) {
        int i;
        int decoratedStart;
        if (!state.isPreLayout() && (i = this.H) != -1) {
            if (i >= 0 && i < state.getItemCount()) {
                aVar.b = this.H;
                SavedState savedState = this.K;
                if (savedState != null && savedState.a()) {
                    boolean z = this.K.j;
                    aVar.d = z;
                    if (z) {
                        aVar.c = this.B.getEndAfterPadding() - this.K.i;
                    } else {
                        aVar.c = this.B.getStartAfterPadding() + this.K.i;
                    }
                    return true;
                } else if (this.I == Integer.MIN_VALUE) {
                    View findViewByPosition = findViewByPosition(this.H);
                    if (findViewByPosition != null) {
                        if (this.B.getDecoratedMeasurement(findViewByPosition) > this.B.getTotalSpace()) {
                            aVar.a();
                            return true;
                        } else if (this.B.getDecoratedStart(findViewByPosition) - this.B.getStartAfterPadding() < 0) {
                            aVar.c = this.B.getStartAfterPadding();
                            aVar.d = false;
                            return true;
                        } else if (this.B.getEndAfterPadding() - this.B.getDecoratedEnd(findViewByPosition) < 0) {
                            aVar.c = this.B.getEndAfterPadding();
                            aVar.d = true;
                            return true;
                        } else {
                            if (aVar.d) {
                                decoratedStart = this.B.getDecoratedEnd(findViewByPosition) + this.B.getTotalSpaceChange();
                            } else {
                                decoratedStart = this.B.getDecoratedStart(findViewByPosition);
                            }
                            aVar.c = decoratedStart;
                        }
                    } else {
                        if (getChildCount() > 0) {
                            aVar.d = (this.H < getPosition(getChildAt(0))) == this.E;
                        }
                        aVar.a();
                    }
                    return true;
                } else {
                    boolean z2 = this.E;
                    aVar.d = z2;
                    if (z2) {
                        aVar.c = this.B.getEndAfterPadding() - this.I;
                    } else {
                        aVar.c = this.B.getStartAfterPadding() + this.I;
                    }
                    return true;
                }
            }
            this.H = -1;
            this.I = Integer.MIN_VALUE;
        }
        return false;
    }

    public final void e0(RecyclerView.Recycler recycler, RecyclerView.State state, a aVar) {
        if (d0(state, aVar) || c0(recycler, state, aVar)) {
            return;
        }
        aVar.a();
        aVar.b = this.F ? state.getItemCount() - 1 : 0;
    }

    public final void f0(int i, int i2, boolean z, RecyclerView.State state) {
        int startAfterPadding;
        this.A.m = Z();
        this.A.f = i;
        int[] iArr = this.O;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(state, iArr);
        int max = Math.max(0, this.O[0]);
        int max2 = Math.max(0, this.O[1]);
        boolean z2 = i == 1;
        b bVar = this.A;
        int i3 = z2 ? max2 : max;
        bVar.h = i3;
        if (!z2) {
            max = max2;
        }
        bVar.i = max;
        if (z2) {
            bVar.h = i3 + this.B.getEndPadding();
            View Q = Q();
            b bVar2 = this.A;
            bVar2.e = this.E ? -1 : 1;
            int position = getPosition(Q);
            b bVar3 = this.A;
            bVar2.d = position + bVar3.e;
            bVar3.b = this.B.getDecoratedEnd(Q);
            startAfterPadding = this.B.getDecoratedEnd(Q) - this.B.getEndAfterPadding();
        } else {
            View R = R();
            this.A.h += this.B.getStartAfterPadding();
            b bVar4 = this.A;
            bVar4.e = this.E ? 1 : -1;
            int position2 = getPosition(R);
            b bVar5 = this.A;
            bVar4.d = position2 + bVar5.e;
            bVar5.b = this.B.getDecoratedStart(R);
            startAfterPadding = (-this.B.getDecoratedStart(R)) + this.B.getStartAfterPadding();
        }
        b bVar6 = this.A;
        bVar6.c = i2;
        if (z) {
            bVar6.c = i2 - startAfterPadding;
        }
        bVar6.g = startAfterPadding;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View K = K(0, getChildCount(), true, false);
        if (K == null) {
            return -1;
        }
        return getPosition(K);
    }

    public int findFirstVisibleItemPosition() {
        View K = K(0, getChildCount(), false, true);
        if (K == null) {
            return -1;
        }
        return getPosition(K);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View K = K(getChildCount() - 1, -1, true, false);
        if (K == null) {
            return -1;
        }
        return getPosition(K);
    }

    public int findLastVisibleItemPosition() {
        View K = K(getChildCount() - 1, -1, false, true);
        if (K == null) {
            return -1;
        }
        return getPosition(K);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    public final void g0(int i, int i2) {
        this.A.c = this.B.getEndAfterPadding() - i2;
        b bVar = this.A;
        bVar.e = this.E ? -1 : 1;
        bVar.d = i;
        bVar.f = 1;
        bVar.b = i2;
        bVar.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Deprecated
    public int getExtraLayoutSpace(RecyclerView.State state) {
        if (state.hasTargetScrollPosition()) {
            return this.B.getTotalSpace();
        }
        return 0;
    }

    public int getInitialPrefetchItemCount() {
        return this.N;
    }

    public int getOrientation() {
        return this.z;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.J;
    }

    public boolean getReverseLayout() {
        return this.D;
    }

    public boolean getStackFromEnd() {
        return this.F;
    }

    public final void h0(a aVar) {
        g0(aVar.b, aVar.c);
    }

    public final void i0(int i, int i2) {
        this.A.c = i2 - this.B.getStartAfterPadding();
        b bVar = this.A;
        bVar.d = i;
        bVar.e = this.E ? 1 : -1;
        bVar.f = -1;
        bVar.b = i2;
        bVar.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.G;
    }

    public final void j0(a aVar) {
        i0(aVar.b, aVar.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.J) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int B;
        View L;
        View Q;
        a0();
        if (getChildCount() == 0 || (B = B(i)) == Integer.MIN_VALUE) {
            return null;
        }
        D();
        f0(B, (int) (this.B.getTotalSpace() * 0.33333334f), false, state);
        b bVar = this.A;
        bVar.g = Integer.MIN_VALUE;
        bVar.f1598a = false;
        E(recycler, bVar, state, true);
        if (B == -1) {
            L = M();
        } else {
            L = L();
        }
        if (B == -1) {
            Q = R();
        } else {
            Q = Q();
        }
        if (Q.hasFocusable()) {
            if (L == null) {
                return null;
            }
            return Q;
        }
        return L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        int i3;
        int i4;
        int O;
        int i5;
        View findViewByPosition;
        int decoratedStart;
        int i6;
        int i7 = -1;
        if ((this.K != null || this.H != -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        SavedState savedState = this.K;
        if (savedState != null && savedState.a()) {
            this.H = this.K.h;
        }
        D();
        this.A.f1598a = false;
        a0();
        View focusedChild = getFocusedChild();
        a aVar = this.L;
        if (aVar.e && this.H == -1 && this.K == null) {
            if (focusedChild != null && (this.B.getDecoratedStart(focusedChild) >= this.B.getEndAfterPadding() || this.B.getDecoratedEnd(focusedChild) <= this.B.getStartAfterPadding())) {
                this.L.c(focusedChild, getPosition(focusedChild));
            }
        } else {
            aVar.e();
            a aVar2 = this.L;
            aVar2.d = this.E ^ this.F;
            e0(recycler, state, aVar2);
            this.L.e = true;
        }
        b bVar = this.A;
        bVar.f = bVar.k >= 0 ? 1 : -1;
        int[] iArr = this.O;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(state, iArr);
        int max = Math.max(0, this.O[0]) + this.B.getStartAfterPadding();
        int max2 = Math.max(0, this.O[1]) + this.B.getEndPadding();
        if (state.isPreLayout() && (i5 = this.H) != -1 && this.I != Integer.MIN_VALUE && (findViewByPosition = findViewByPosition(i5)) != null) {
            if (this.E) {
                i6 = this.B.getEndAfterPadding() - this.B.getDecoratedEnd(findViewByPosition);
                decoratedStart = this.I;
            } else {
                decoratedStart = this.B.getDecoratedStart(findViewByPosition) - this.B.getStartAfterPadding();
                i6 = this.I;
            }
            int i8 = i6 - decoratedStart;
            if (i8 > 0) {
                max += i8;
            } else {
                max2 -= i8;
            }
        }
        a aVar3 = this.L;
        if (!aVar3.d ? !this.E : this.E) {
            i7 = 1;
        }
        U(recycler, state, aVar3, i7);
        detachAndScrapAttachedViews(recycler);
        this.A.m = Z();
        this.A.j = state.isPreLayout();
        this.A.i = 0;
        a aVar4 = this.L;
        if (aVar4.d) {
            j0(aVar4);
            b bVar2 = this.A;
            bVar2.h = max;
            E(recycler, bVar2, state, false);
            b bVar3 = this.A;
            i2 = bVar3.b;
            int i9 = bVar3.d;
            int i10 = bVar3.c;
            if (i10 > 0) {
                max2 += i10;
            }
            h0(this.L);
            b bVar4 = this.A;
            bVar4.h = max2;
            bVar4.d += bVar4.e;
            E(recycler, bVar4, state, false);
            b bVar5 = this.A;
            i = bVar5.b;
            int i11 = bVar5.c;
            if (i11 > 0) {
                i0(i9, i2);
                b bVar6 = this.A;
                bVar6.h = i11;
                E(recycler, bVar6, state, false);
                i2 = this.A.b;
            }
        } else {
            h0(aVar4);
            b bVar7 = this.A;
            bVar7.h = max2;
            E(recycler, bVar7, state, false);
            b bVar8 = this.A;
            i = bVar8.b;
            int i12 = bVar8.d;
            int i13 = bVar8.c;
            if (i13 > 0) {
                max += i13;
            }
            j0(this.L);
            b bVar9 = this.A;
            bVar9.h = max;
            bVar9.d += bVar9.e;
            E(recycler, bVar9, state, false);
            b bVar10 = this.A;
            i2 = bVar10.b;
            int i14 = bVar10.c;
            if (i14 > 0) {
                g0(i12, i);
                b bVar11 = this.A;
                bVar11.h = i14;
                E(recycler, bVar11, state, false);
                i = this.A.b;
            }
        }
        if (getChildCount() > 0) {
            if (this.E ^ this.F) {
                int O2 = O(i, recycler, state, true);
                i3 = i2 + O2;
                i4 = i + O2;
                O = P(i3, recycler, state, false);
            } else {
                int P = P(i2, recycler, state, true);
                i3 = i2 + P;
                i4 = i + P;
                O = O(i4, recycler, state, false);
            }
            i2 = i3 + O;
            i = i4 + O;
        }
        T(recycler, state, i2, i);
        if (!state.isPreLayout()) {
            this.B.onLayoutComplete();
        } else {
            this.L.e();
        }
        this.C = this.F;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.K = null;
        this.H = -1;
        this.I = Integer.MIN_VALUE;
        this.L.e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.K = savedState;
            if (this.H != -1) {
                savedState.b();
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.K != null) {
            return new SavedState(this.K);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            D();
            boolean z = this.C ^ this.E;
            savedState.j = z;
            if (z) {
                View Q = Q();
                savedState.i = this.B.getEndAfterPadding() - this.B.getDecoratedEnd(Q);
                savedState.h = getPosition(Q);
            } else {
                View R = R();
                savedState.h = getPosition(R);
                savedState.i = this.B.getDecoratedStart(R) - this.B.getStartAfterPadding();
            }
        } else {
            savedState.b();
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.ViewDropHandler
    public void prepareForDrop(@NonNull View view, @NonNull View view2, int i, int i2) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        D();
        a0();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        boolean z = position < position2 ? true : true;
        if (this.E) {
            if (z) {
                scrollToPositionWithOffset(position2, this.B.getEndAfterPadding() - (this.B.getDecoratedStart(view2) + this.B.getDecoratedMeasurement(view)));
            } else {
                scrollToPositionWithOffset(position2, this.B.getEndAfterPadding() - this.B.getDecoratedEnd(view2));
            }
        } else if (z) {
            scrollToPositionWithOffset(position2, this.B.getDecoratedStart(view2));
        } else {
            scrollToPositionWithOffset(position2, this.B.getDecoratedEnd(view2) - this.B.getDecoratedMeasurement(view));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.z == 1) {
            return 0;
        }
        return b0(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.H = i;
        this.I = Integer.MIN_VALUE;
        SavedState savedState = this.K;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        this.H = i;
        this.I = i2;
        SavedState savedState = this.K;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.z == 0) {
            return 0;
        }
        return b0(i, recycler, state);
    }

    public void setInitialPrefetchItemCount(int i) {
        this.N = i;
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        assertNotInLayoutOrScroll(null);
        if (i != this.z || this.B == null) {
            OrientationHelper createOrientationHelper = OrientationHelper.createOrientationHelper(this, i);
            this.B = createOrientationHelper;
            this.L.f1597a = createOrientationHelper;
            this.z = i;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.J = z;
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.D) {
            return;
        }
        this.D = z;
        requestLayout();
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.G = z;
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.F == z) {
            return;
        }
        this.F = z;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.K == null && this.C == this.F;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean u() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !f()) ? false : true;
    }

    public void x(RecyclerView.State state, b bVar, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i = bVar.d;
        if (i < 0 || i >= state.getItemCount()) {
            return;
        }
        layoutPrefetchRegistry.addPosition(i, Math.max(0, bVar.g));
    }

    public final int y(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        D();
        return k.a(state, this.B, H(!this.G, true), G(!this.G, true), this, this.G);
    }

    public final int z(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        D();
        return k.b(state, this.B, H(!this.G, true), G(!this.G, true), this, this.G, this.E);
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.z = 1;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = true;
        this.H = -1;
        this.I = Integer.MIN_VALUE;
        this.K = null;
        this.L = new a();
        this.M = new LayoutChunkResult();
        this.N = 2;
        this.O = new int[2];
        setOrientation(i);
        setReverseLayout(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.z = 1;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = true;
        this.H = -1;
        this.I = Integer.MIN_VALUE;
        this.K = null;
        this.L = new a();
        this.M = new LayoutChunkResult();
        this.N = 2;
        this.O = new int[2];
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
    }
}
