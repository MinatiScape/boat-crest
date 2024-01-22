package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public c[] A;
    @NonNull
    public OrientationHelper B;
    @NonNull
    public OrientationHelper C;
    public int D;
    public int E;
    @NonNull
    public final g F;
    public BitSet I;
    public boolean N;
    public boolean O;
    public SavedState P;
    public int Q;
    public int[] V;
    public int z = -1;
    public boolean G = false;
    public boolean H = false;
    public int J = -1;
    public int K = Integer.MIN_VALUE;
    public LazySpanLookup L = new LazySpanLookup();
    public int M = 2;
    public final Rect R = new Rect();
    public final b S = new b();
    public boolean T = false;
    public boolean U = true;
    public final Runnable W = new a();

    /* loaded from: classes.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        public c l;
        public boolean m;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int getSpanIndex() {
            c cVar = this.l;
            if (cVar == null) {
                return -1;
            }
            return cVar.e;
        }

        public boolean isFullSpan() {
            return this.m;
        }

        public void setFullSpan(boolean z) {
            this.m = z;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int h;
        public int i;
        public int j;
        public int[] k;
        public int l;
        public int[] m;
        public List<LazySpanLookup.FullSpanItem> n;
        public boolean o;
        public boolean p;
        public boolean q;

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

        public void a() {
            this.k = null;
            this.j = 0;
            this.h = -1;
            this.i = -1;
        }

        public void b() {
            this.k = null;
            this.j = 0;
            this.l = 0;
            this.m = null;
            this.n = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            if (this.j > 0) {
                parcel.writeIntArray(this.k);
            }
            parcel.writeInt(this.l);
            if (this.l > 0) {
                parcel.writeIntArray(this.m);
            }
            parcel.writeInt(this.o ? 1 : 0);
            parcel.writeInt(this.p ? 1 : 0);
            parcel.writeInt(this.q ? 1 : 0);
            parcel.writeList(this.n);
        }

        public SavedState(Parcel parcel) {
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            int readInt = parcel.readInt();
            this.j = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.k = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.l = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.m = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.o = parcel.readInt() == 1;
            this.p = parcel.readInt() == 1;
            this.q = parcel.readInt() == 1;
            this.n = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.j = savedState.j;
            this.h = savedState.h;
            this.i = savedState.i;
            this.k = savedState.k;
            this.l = savedState.l;
            this.m = savedState.m;
            this.o = savedState.o;
            this.p = savedState.p;
            this.q = savedState.q;
            this.n = savedState.n;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.D();
        }
    }

    /* loaded from: classes.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public int f1623a;
        public int b;
        public boolean c;
        public boolean d;
        public boolean e;
        public int[] f;

        public b() {
            c();
        }

        public void a() {
            this.b = this.c ? StaggeredGridLayoutManager.this.B.getEndAfterPadding() : StaggeredGridLayoutManager.this.B.getStartAfterPadding();
        }

        public void b(int i) {
            if (this.c) {
                this.b = StaggeredGridLayoutManager.this.B.getEndAfterPadding() - i;
            } else {
                this.b = StaggeredGridLayoutManager.this.B.getStartAfterPadding() + i;
            }
        }

        public void c() {
            this.f1623a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
            this.e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void d(c[] cVarArr) {
            int length = cVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.A.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = cVarArr[i].u(Integer.MIN_VALUE);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<View> f1624a = new ArrayList<>();
        public int b = Integer.MIN_VALUE;
        public int c = Integer.MIN_VALUE;
        public int d = 0;
        public final int e;

        public c(int i) {
            this.e = i;
        }

        public void A(int i) {
            this.b = i;
            this.c = i;
        }

        public void a(View view) {
            LayoutParams s = s(view);
            s.l = this;
            this.f1624a.add(view);
            this.c = Integer.MIN_VALUE;
            if (this.f1624a.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.d += StaggeredGridLayoutManager.this.B.getDecoratedMeasurement(view);
            }
        }

        public void b(boolean z, int i) {
            int u;
            if (z) {
                u = q(Integer.MIN_VALUE);
            } else {
                u = u(Integer.MIN_VALUE);
            }
            e();
            if (u == Integer.MIN_VALUE) {
                return;
            }
            if (!z || u >= StaggeredGridLayoutManager.this.B.getEndAfterPadding()) {
                if (z || u <= StaggeredGridLayoutManager.this.B.getStartAfterPadding()) {
                    if (i != Integer.MIN_VALUE) {
                        u += i;
                    }
                    this.c = u;
                    this.b = u;
                }
            }
        }

        public void c() {
            LazySpanLookup.FullSpanItem f;
            ArrayList<View> arrayList = this.f1624a;
            View view = arrayList.get(arrayList.size() - 1);
            LayoutParams s = s(view);
            this.c = StaggeredGridLayoutManager.this.B.getDecoratedEnd(view);
            if (s.m && (f = StaggeredGridLayoutManager.this.L.f(s.getViewLayoutPosition())) != null && f.i == 1) {
                this.c += f.a(this.e);
            }
        }

        public void d() {
            LazySpanLookup.FullSpanItem f;
            View view = this.f1624a.get(0);
            LayoutParams s = s(view);
            this.b = StaggeredGridLayoutManager.this.B.getDecoratedStart(view);
            if (s.m && (f = StaggeredGridLayoutManager.this.L.f(s.getViewLayoutPosition())) != null && f.i == -1) {
                this.b -= f.a(this.e);
            }
        }

        public void e() {
            this.f1624a.clear();
            v();
            this.d = 0;
        }

        public int f() {
            if (StaggeredGridLayoutManager.this.G) {
                return n(this.f1624a.size() - 1, -1, true);
            }
            return n(0, this.f1624a.size(), true);
        }

        public int g() {
            if (StaggeredGridLayoutManager.this.G) {
                return m(this.f1624a.size() - 1, -1, true);
            }
            return m(0, this.f1624a.size(), true);
        }

        public int h() {
            if (StaggeredGridLayoutManager.this.G) {
                return n(this.f1624a.size() - 1, -1, false);
            }
            return n(0, this.f1624a.size(), false);
        }

        public int i() {
            if (StaggeredGridLayoutManager.this.G) {
                return n(0, this.f1624a.size(), true);
            }
            return n(this.f1624a.size() - 1, -1, true);
        }

        public int j() {
            if (StaggeredGridLayoutManager.this.G) {
                return m(0, this.f1624a.size(), true);
            }
            return m(this.f1624a.size() - 1, -1, true);
        }

        public int k() {
            if (StaggeredGridLayoutManager.this.G) {
                return n(0, this.f1624a.size(), false);
            }
            return n(this.f1624a.size() - 1, -1, false);
        }

        public int l(int i, int i2, boolean z, boolean z2, boolean z3) {
            int startAfterPadding = StaggeredGridLayoutManager.this.B.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.B.getEndAfterPadding();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1624a.get(i);
                int decoratedStart = StaggeredGridLayoutManager.this.B.getDecoratedStart(view);
                int decoratedEnd = StaggeredGridLayoutManager.this.B.getDecoratedEnd(view);
                boolean z4 = false;
                boolean z5 = !z3 ? decoratedStart >= endAfterPadding : decoratedStart > endAfterPadding;
                if (!z3 ? decoratedEnd > startAfterPadding : decoratedEnd >= startAfterPadding) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (z && z2) {
                        if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    } else if (z2) {
                        return StaggeredGridLayoutManager.this.getPosition(view);
                    } else {
                        if (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    }
                }
                i += i3;
            }
            return -1;
        }

        public int m(int i, int i2, boolean z) {
            return l(i, i2, false, false, z);
        }

        public int n(int i, int i2, boolean z) {
            return l(i, i2, z, true, false);
        }

        public int o() {
            return this.d;
        }

        public int p() {
            int i = this.c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            c();
            return this.c;
        }

        public int q(int i) {
            int i2 = this.c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1624a.size() == 0) {
                return i;
            }
            c();
            return this.c;
        }

        public View r(int i, int i2) {
            View view = null;
            if (i2 == -1) {
                int size = this.f1624a.size();
                int i3 = 0;
                while (i3 < size) {
                    View view2 = this.f1624a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.G && staggeredGridLayoutManager.getPosition(view2) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.G && staggeredGridLayoutManager2.getPosition(view2) >= i) || !view2.hasFocusable()) {
                        break;
                    }
                    i3++;
                    view = view2;
                }
            } else {
                int size2 = this.f1624a.size() - 1;
                while (size2 >= 0) {
                    View view3 = this.f1624a.get(size2);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.G && staggeredGridLayoutManager3.getPosition(view3) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.G && staggeredGridLayoutManager4.getPosition(view3) <= i) || !view3.hasFocusable()) {
                        break;
                    }
                    size2--;
                    view = view3;
                }
            }
            return view;
        }

        public LayoutParams s(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        public int t() {
            int i = this.b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            d();
            return this.b;
        }

        public int u(int i) {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1624a.size() == 0) {
                return i;
            }
            d();
            return this.b;
        }

        public void v() {
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
        }

        public void w(int i) {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                this.b = i2 + i;
            }
            int i3 = this.c;
            if (i3 != Integer.MIN_VALUE) {
                this.c = i3 + i;
            }
        }

        public void x() {
            int size = this.f1624a.size();
            View remove = this.f1624a.remove(size - 1);
            LayoutParams s = s(remove);
            s.l = null;
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.d -= StaggeredGridLayoutManager.this.B.getDecoratedMeasurement(remove);
            }
            if (size == 1) {
                this.b = Integer.MIN_VALUE;
            }
            this.c = Integer.MIN_VALUE;
        }

        public void y() {
            View remove = this.f1624a.remove(0);
            LayoutParams s = s(remove);
            s.l = null;
            if (this.f1624a.size() == 0) {
                this.c = Integer.MIN_VALUE;
            }
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.d -= StaggeredGridLayoutManager.this.B.getDecoratedMeasurement(remove);
            }
            this.b = Integer.MIN_VALUE;
        }

        public void z(View view) {
            LayoutParams s = s(view);
            s.l = this;
            this.f1624a.add(0, view);
            this.b = Integer.MIN_VALUE;
            if (this.f1624a.size() == 1) {
                this.c = Integer.MIN_VALUE;
            }
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.d += StaggeredGridLayoutManager.this.B.getDecoratedMeasurement(view);
            }
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.F = new g();
        L();
    }

    public boolean A() {
        int u = this.A[0].u(Integer.MIN_VALUE);
        for (int i = 1; i < this.z; i++) {
            if (this.A[i].u(Integer.MIN_VALUE) != u) {
                return false;
            }
        }
        return true;
    }

    public final void B(View view, LayoutParams layoutParams, g gVar) {
        if (gVar.e == 1) {
            if (layoutParams.m) {
                x(view);
            } else {
                layoutParams.l.a(view);
            }
        } else if (layoutParams.m) {
            i0(view);
        } else {
            layoutParams.l.z(view);
        }
    }

    public final int C(int i) {
        if (getChildCount() == 0) {
            return this.H ? 1 : -1;
        }
        return (i < U()) != this.H ? -1 : 1;
    }

    public boolean D() {
        int U;
        int V;
        if (getChildCount() == 0 || this.M == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.H) {
            U = V();
            V = U();
        } else {
            U = U();
            V = V();
        }
        if (U == 0 && c0() != null) {
            this.L.b();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (this.T) {
            int i = this.H ? -1 : 1;
            int i2 = V + 1;
            LazySpanLookup.FullSpanItem e = this.L.e(U, i2, i, true);
            if (e == null) {
                this.T = false;
                this.L.d(i2);
                return false;
            }
            LazySpanLookup.FullSpanItem e2 = this.L.e(U, e.h, i * (-1), true);
            if (e2 == null) {
                this.L.d(e.h);
            } else {
                this.L.d(e2.h + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else {
            return false;
        }
    }

    public final boolean E(c cVar) {
        if (this.H) {
            if (cVar.p() < this.B.getEndAfterPadding()) {
                ArrayList<View> arrayList = cVar.f1624a;
                return !cVar.s(arrayList.get(arrayList.size() - 1)).m;
            }
        } else if (cVar.t() > this.B.getStartAfterPadding()) {
            return !cVar.s(cVar.f1624a.get(0)).m;
        }
        return false;
    }

    public final int F(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return k.a(state, this.B, P(!this.U), O(!this.U), this, this.U);
    }

    public final int G(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return k.b(state, this.B, P(!this.U), O(!this.U), this, this.U, this.H);
    }

    public final int H(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return k.c(state, this.B, P(!this.U), O(!this.U), this, this.U);
    }

    public final int I(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.D == 1) ? 1 : Integer.MIN_VALUE : this.D == 0 ? 1 : Integer.MIN_VALUE : this.D == 1 ? -1 : Integer.MIN_VALUE : this.D == 0 ? -1 : Integer.MIN_VALUE : (this.D != 1 && isLayoutRTL()) ? -1 : 1 : (this.D != 1 && isLayoutRTL()) ? 1 : -1;
    }

    public final LazySpanLookup.FullSpanItem J(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.j = new int[this.z];
        for (int i2 = 0; i2 < this.z; i2++) {
            fullSpanItem.j[i2] = i - this.A[i2].q(i);
        }
        return fullSpanItem;
    }

    public final LazySpanLookup.FullSpanItem K(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.j = new int[this.z];
        for (int i2 = 0; i2 < this.z; i2++) {
            fullSpanItem.j[i2] = this.A[i2].u(i) - i;
        }
        return fullSpanItem;
    }

    public final void L() {
        this.B = OrientationHelper.createOrientationHelper(this, this.D);
        this.C = OrientationHelper.createOrientationHelper(this, 1 - this.D);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    public final int M(RecyclerView.Recycler recycler, g gVar, RecyclerView.State state) {
        int i;
        int startAfterPadding;
        int W;
        c cVar;
        int decoratedMeasurement;
        int i2;
        int i3;
        int decoratedMeasurement2;
        boolean z;
        boolean A;
        ?? r9 = 0;
        this.I.set(0, this.z, true);
        if (this.F.i) {
            i = gVar.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else if (gVar.e == 1) {
            i = gVar.g + gVar.b;
        } else {
            i = gVar.f - gVar.b;
        }
        int i4 = i;
        q0(gVar.e, i4);
        if (this.H) {
            startAfterPadding = this.B.getEndAfterPadding();
        } else {
            startAfterPadding = this.B.getStartAfterPadding();
        }
        int i5 = startAfterPadding;
        boolean z2 = false;
        while (gVar.a(state) && (this.F.i || !this.I.isEmpty())) {
            View b2 = gVar.b(recycler);
            LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int g = this.L.g(viewLayoutPosition);
            boolean z3 = g == -1 ? true : r9;
            if (z3) {
                cVar = layoutParams.m ? this.A[r9] : a0(gVar);
                this.L.n(viewLayoutPosition, cVar);
            } else {
                cVar = this.A[g];
            }
            c cVar2 = cVar;
            layoutParams.l = cVar2;
            if (gVar.e == 1) {
                addView(b2);
            } else {
                addView(b2, r9);
            }
            e0(b2, layoutParams, r9);
            if (gVar.e == 1) {
                int W2 = layoutParams.m ? W(i5) : cVar2.q(i5);
                int decoratedMeasurement3 = this.B.getDecoratedMeasurement(b2) + W2;
                if (z3 && layoutParams.m) {
                    LazySpanLookup.FullSpanItem J = J(W2);
                    J.i = -1;
                    J.h = viewLayoutPosition;
                    this.L.a(J);
                }
                i2 = decoratedMeasurement3;
                decoratedMeasurement = W2;
            } else {
                int Z = layoutParams.m ? Z(i5) : cVar2.u(i5);
                decoratedMeasurement = Z - this.B.getDecoratedMeasurement(b2);
                if (z3 && layoutParams.m) {
                    LazySpanLookup.FullSpanItem K = K(Z);
                    K.i = 1;
                    K.h = viewLayoutPosition;
                    this.L.a(K);
                }
                i2 = Z;
            }
            if (layoutParams.m && gVar.d == -1) {
                if (z3) {
                    this.T = true;
                } else {
                    if (gVar.e == 1) {
                        A = z();
                    } else {
                        A = A();
                    }
                    if (!A) {
                        LazySpanLookup.FullSpanItem f = this.L.f(viewLayoutPosition);
                        if (f != null) {
                            f.k = true;
                        }
                        this.T = true;
                    }
                }
            }
            B(b2, layoutParams, gVar);
            if (isLayoutRTL() && this.D == 1) {
                int endAfterPadding = layoutParams.m ? this.C.getEndAfterPadding() : this.C.getEndAfterPadding() - (((this.z - 1) - cVar2.e) * this.E);
                decoratedMeasurement2 = endAfterPadding;
                i3 = endAfterPadding - this.C.getDecoratedMeasurement(b2);
            } else {
                int startAfterPadding2 = layoutParams.m ? this.C.getStartAfterPadding() : (cVar2.e * this.E) + this.C.getStartAfterPadding();
                i3 = startAfterPadding2;
                decoratedMeasurement2 = this.C.getDecoratedMeasurement(b2) + startAfterPadding2;
            }
            if (this.D == 1) {
                layoutDecoratedWithMargins(b2, i3, decoratedMeasurement, decoratedMeasurement2, i2);
            } else {
                layoutDecoratedWithMargins(b2, decoratedMeasurement, i3, i2, decoratedMeasurement2);
            }
            if (layoutParams.m) {
                q0(this.F.e, i4);
            } else {
                w0(cVar2, this.F.e, i4);
            }
            j0(recycler, this.F);
            if (this.F.h && b2.hasFocusable()) {
                if (layoutParams.m) {
                    this.I.clear();
                } else {
                    z = false;
                    this.I.set(cVar2.e, false);
                    r9 = z;
                    z2 = true;
                }
            }
            z = false;
            r9 = z;
            z2 = true;
        }
        int i6 = r9;
        if (!z2) {
            j0(recycler, this.F);
        }
        if (this.F.e == -1) {
            W = this.B.getStartAfterPadding() - Z(this.B.getStartAfterPadding());
        } else {
            W = W(this.B.getEndAfterPadding()) - this.B.getEndAfterPadding();
        }
        return W > 0 ? Math.min(gVar.b, W) : i6;
    }

    public final int N(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            int position = getPosition(getChildAt(i2));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    public View O(boolean z) {
        int startAfterPadding = this.B.getStartAfterPadding();
        int endAfterPadding = this.B.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.B.getDecoratedStart(childAt);
            int decoratedEnd = this.B.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public View P(boolean z) {
        int startAfterPadding = this.B.getStartAfterPadding();
        int endAfterPadding = this.B.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int decoratedStart = this.B.getDecoratedStart(childAt);
            if (this.B.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public int Q() {
        View O = this.H ? O(true) : P(true);
        if (O == null) {
            return -1;
        }
        return getPosition(O);
    }

    public final int R(int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    public final void S(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int W = W(Integer.MIN_VALUE);
        if (W != Integer.MIN_VALUE && (endAfterPadding = this.B.getEndAfterPadding() - W) > 0) {
            int i = endAfterPadding - (-o0(-endAfterPadding, recycler, state));
            if (!z || i <= 0) {
                return;
            }
            this.B.offsetChildren(i);
        }
    }

    public final void T(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int Z = Z(Integer.MAX_VALUE);
        if (Z != Integer.MAX_VALUE && (startAfterPadding = Z - this.B.getStartAfterPadding()) > 0) {
            int o0 = startAfterPadding - o0(startAfterPadding, recycler, state);
            if (!z || o0 <= 0) {
                return;
            }
            this.B.offsetChildren(-o0);
        }
    }

    public int U() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int V() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public final int W(int i) {
        int q = this.A[0].q(i);
        for (int i2 = 1; i2 < this.z; i2++) {
            int q2 = this.A[i2].q(i);
            if (q2 > q) {
                q = q2;
            }
        }
        return q;
    }

    public final int X(int i) {
        int u = this.A[0].u(i);
        for (int i2 = 1; i2 < this.z; i2++) {
            int u2 = this.A[i2].u(i);
            if (u2 > u) {
                u = u2;
            }
        }
        return u;
    }

    public final int Y(int i) {
        int q = this.A[0].q(i);
        for (int i2 = 1; i2 < this.z; i2++) {
            int q2 = this.A[i2].q(i);
            if (q2 < q) {
                q = q2;
            }
        }
        return q;
    }

    public final int Z(int i) {
        int u = this.A[0].u(i);
        for (int i2 = 1; i2 < this.z; i2++) {
            int u2 = this.A[i2].u(i);
            if (u2 < u) {
                u = u2;
            }
        }
        return u;
    }

    public final c a0(g gVar) {
        int i;
        int i2;
        int i3 = -1;
        if (g0(gVar.e)) {
            i = this.z - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.z;
            i2 = 1;
        }
        c cVar = null;
        if (gVar.e == 1) {
            int i4 = Integer.MAX_VALUE;
            int startAfterPadding = this.B.getStartAfterPadding();
            while (i != i3) {
                c cVar2 = this.A[i];
                int q = cVar2.q(startAfterPadding);
                if (q < i4) {
                    cVar = cVar2;
                    i4 = q;
                }
                i += i2;
            }
            return cVar;
        }
        int i5 = Integer.MIN_VALUE;
        int endAfterPadding = this.B.getEndAfterPadding();
        while (i != i3) {
            c cVar3 = this.A[i];
            int u = cVar3.u(endAfterPadding);
            if (u > i5) {
                cVar = cVar3;
                i5 = u;
            }
            i += i2;
        }
        return cVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.P == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b0(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.H
            if (r0 == 0) goto L9
            int r0 = r6.V()
            goto Ld
        L9:
            int r0 = r6.U()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1a
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1c
        L16:
            int r2 = r7 + 1
            r3 = r8
            goto L1d
        L1a:
            int r2 = r7 + r8
        L1c:
            r3 = r7
        L1d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.L
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.L
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.L
            r7.j(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.L
            r9.k(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.L
            r9.j(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.H
            if (r7 == 0) goto L4d
            int r7 = r6.U()
            goto L51
        L4d:
            int r7 = r6.V()
        L51:
            if (r3 > r7) goto L56
            r6.requestLayout()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.b0(int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008a, code lost:
        r10 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View c0() {
        /*
            r12 = this;
            int r0 = r12.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.z
            r2.<init>(r3)
            int r3 = r12.z
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.D
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.isLayoutRTL()
            if (r3 == 0) goto L20
            r3 = r1
            goto L21
        L20:
            r3 = r5
        L21:
            boolean r6 = r12.H
            if (r6 == 0) goto L27
            r6 = r5
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = r4
        L2b:
            if (r0 >= r6) goto L2e
            r5 = r1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.l
            int r9 = r9.e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.l
            boolean r9 = r12.E(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.l
            int r9 = r9.e
            r2.clear(r9)
        L54:
            boolean r9 = r8.m
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.getChildAt(r9)
            boolean r10 = r12.H
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.OrientationHelper r10 = r12.B
            int r10 = r10.getDecoratedEnd(r7)
            androidx.recyclerview.widget.OrientationHelper r11 = r12.B
            int r11 = r11.getDecoratedEnd(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.OrientationHelper r10 = r12.B
            int r10 = r10.getDecoratedStart(r7)
            androidx.recyclerview.widget.OrientationHelper r11 = r12.B
            int r11 = r11.getDecoratedStart(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = r1
            goto L8b
        L8a:
            r10 = r4
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r9 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r9
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = r8.l
            int r8 = r8.e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r9.l
            int r9 = r9.e
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = r1
            goto La1
        La0:
            r8 = r4
        La1:
            if (r3 >= 0) goto La5
            r9 = r1
            goto La6
        La5:
            r9 = r4
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.c0():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.D == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.D == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int q;
        int i3;
        if (this.D != 0) {
            i = i2;
        }
        if (getChildCount() == 0 || i == 0) {
            return;
        }
        h0(i, state);
        int[] iArr = this.V;
        if (iArr == null || iArr.length < this.z) {
            this.V = new int[this.z];
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.z; i5++) {
            g gVar = this.F;
            if (gVar.d == -1) {
                q = gVar.f;
                i3 = this.A[i5].u(q);
            } else {
                q = this.A[i5].q(gVar.g);
                i3 = this.F.g;
            }
            int i6 = q - i3;
            if (i6 >= 0) {
                this.V[i4] = i6;
                i4++;
            }
        }
        Arrays.sort(this.V, 0, i4);
        for (int i7 = 0; i7 < i4 && this.F.a(state); i7++) {
            layoutPrefetchRegistry.addPosition(this.F.c, this.V[i7]);
            g gVar2 = this.F;
            gVar2.c += gVar2.d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return F(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return G(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return H(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        int C = C(i);
        PointF pointF = new PointF();
        if (C == 0) {
            return null;
        }
        if (this.D == 0) {
            pointF.x = C;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = C;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return F(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return G(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return H(state);
    }

    public final void d0(View view, int i, int i2, boolean z) {
        boolean t;
        calculateItemDecorationsForChild(view, this.R);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        Rect rect = this.R;
        int x0 = x0(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        Rect rect2 = this.R;
        int x02 = x0(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect2.bottom);
        if (z) {
            t = v(view, x0, x02, layoutParams);
        } else {
            t = t(view, x0, x02, layoutParams);
        }
        if (t) {
            view.measure(x0, x02);
        }
    }

    public final void e0(View view, LayoutParams layoutParams, boolean z) {
        if (layoutParams.m) {
            if (this.D == 1) {
                d0(view, this.Q, RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z);
            } else {
                d0(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), this.Q, z);
            }
        } else if (this.D == 1) {
            d0(view, RecyclerView.LayoutManager.getChildMeasureSpec(this.E, getWidthMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).width, false), RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z);
        } else {
            d0(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), RecyclerView.LayoutManager.getChildMeasureSpec(this.E, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).height, false), z);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0157, code lost:
        if (D() != false) goto L83;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f0(androidx.recyclerview.widget.RecyclerView.Recycler r9, androidx.recyclerview.widget.RecyclerView.State r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.f0(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, boolean):void");
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.z];
        } else if (iArr.length < this.z) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.z + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.z; i++) {
            iArr[i] = this.A[i].f();
        }
        return iArr;
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.z];
        } else if (iArr.length < this.z) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.z + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.z; i++) {
            iArr[i] = this.A[i].h();
        }
        return iArr;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.z];
        } else if (iArr.length < this.z) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.z + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.z; i++) {
            iArr[i] = this.A[i].i();
        }
        return iArr;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.z];
        } else if (iArr.length < this.z) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.z + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.z; i++) {
            iArr[i] = this.A[i].k();
        }
        return iArr;
    }

    public final boolean g0(int i) {
        if (this.D == 0) {
            return (i == -1) != this.H;
        }
        return ((i == -1) == this.H) == isLayoutRTL();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.D == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public int getGapStrategy() {
        return this.M;
    }

    public int getOrientation() {
        return this.D;
    }

    public boolean getReverseLayout() {
        return this.G;
    }

    public int getSpanCount() {
        return this.z;
    }

    public void h0(int i, RecyclerView.State state) {
        int i2;
        int U;
        if (i > 0) {
            U = V();
            i2 = 1;
        } else {
            i2 = -1;
            U = U();
        }
        this.F.f1644a = true;
        u0(U, state);
        p0(i2);
        g gVar = this.F;
        gVar.c = U + gVar.d;
        gVar.b = Math.abs(i);
    }

    public final void i0(View view) {
        for (int i = this.z - 1; i >= 0; i--) {
            this.A[i].z(view);
        }
    }

    public void invalidateSpanAssignments() {
        this.L.b();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return this.M != 0;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public final void j0(RecyclerView.Recycler recycler, g gVar) {
        int min;
        int min2;
        if (!gVar.f1644a || gVar.i) {
            return;
        }
        if (gVar.b == 0) {
            if (gVar.e == -1) {
                k0(recycler, gVar.g);
            } else {
                l0(recycler, gVar.f);
            }
        } else if (gVar.e == -1) {
            int i = gVar.f;
            int X = i - X(i);
            if (X < 0) {
                min2 = gVar.g;
            } else {
                min2 = gVar.g - Math.min(X, gVar.b);
            }
            k0(recycler, min2);
        } else {
            int Y = Y(gVar.g) - gVar.g;
            if (Y < 0) {
                min = gVar.f;
            } else {
                min = Math.min(Y, gVar.b) + gVar.f;
            }
            l0(recycler, min);
        }
    }

    public final void k0(RecyclerView.Recycler recycler, int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.B.getDecoratedStart(childAt) < i || this.B.getTransformedStartWithDecoration(childAt) < i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.m) {
                for (int i2 = 0; i2 < this.z; i2++) {
                    if (this.A[i2].f1624a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.z; i3++) {
                    this.A[i3].x();
                }
            } else if (layoutParams.l.f1624a.size() == 1) {
                return;
            } else {
                layoutParams.l.x();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    public final void l0(RecyclerView.Recycler recycler, int i) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.B.getDecoratedEnd(childAt) > i || this.B.getTransformedEndWithDecoration(childAt) > i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.m) {
                for (int i2 = 0; i2 < this.z; i2++) {
                    if (this.A[i2].f1624a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.z; i3++) {
                    this.A[i3].y();
                }
            } else if (layoutParams.l.f1624a.size() == 1) {
                return;
            } else {
                layoutParams.l.y();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    public final void m0() {
        if (this.C.getMode() == 1073741824) {
            return;
        }
        float f = 0.0f;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            float decoratedMeasurement = this.C.getDecoratedMeasurement(childAt);
            if (decoratedMeasurement >= f) {
                if (((LayoutParams) childAt.getLayoutParams()).isFullSpan()) {
                    decoratedMeasurement = (decoratedMeasurement * 1.0f) / this.z;
                }
                f = Math.max(f, decoratedMeasurement);
            }
        }
        int i2 = this.E;
        int round = Math.round(f * this.z);
        if (this.C.getMode() == Integer.MIN_VALUE) {
            round = Math.min(round, this.C.getTotalSpace());
        }
        v0(round);
        if (this.E == i2) {
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (!layoutParams.m) {
                if (isLayoutRTL() && this.D == 1) {
                    int i4 = this.z;
                    int i5 = layoutParams.l.e;
                    childAt2.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.E) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = layoutParams.l.e;
                    int i7 = this.E * i6;
                    int i8 = i6 * i2;
                    if (this.D == 1) {
                        childAt2.offsetLeftAndRight(i7 - i8);
                    } else {
                        childAt2.offsetTopAndBottom(i7 - i8);
                    }
                }
            }
        }
    }

    public final void n0() {
        if (this.D != 1 && isLayoutRTL()) {
            this.H = !this.G;
        } else {
            this.H = this.G;
        }
    }

    public int o0(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        h0(i, state);
        int M = M(recycler, this.F, state);
        if (this.F.b >= M) {
            i = i < 0 ? -M : M;
        }
        this.B.offsetChildren(-i);
        this.N = this.H;
        g gVar = this.F;
        gVar.b = 0;
        j0(recycler, gVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenHorizontal(int i) {
        super.offsetChildrenHorizontal(i);
        for (int i2 = 0; i2 < this.z; i2++) {
            this.A[i2].w(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        for (int i2 = 0; i2 < this.z; i2++) {
            this.A[i2].w(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(@Nullable RecyclerView.Adapter adapter, @Nullable RecyclerView.Adapter adapter2) {
        this.L.b();
        for (int i = 0; i < this.z; i++) {
            this.A[i].e();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        removeCallbacks(this.W);
        for (int i = 0; i < this.z; i++) {
            this.A[i].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @Nullable
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findContainingItemView;
        int U;
        int j;
        int j2;
        int j3;
        View r;
        if (getChildCount() == 0 || (findContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        n0();
        int I = I(i);
        if (I == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        boolean z = layoutParams.m;
        c cVar = layoutParams.l;
        if (I == 1) {
            U = V();
        } else {
            U = U();
        }
        u0(U, state);
        p0(I);
        g gVar = this.F;
        gVar.c = gVar.d + U;
        gVar.b = (int) (this.B.getTotalSpace() * 0.33333334f);
        g gVar2 = this.F;
        gVar2.h = true;
        gVar2.f1644a = false;
        M(recycler, gVar2, state);
        this.N = this.H;
        if (z || (r = cVar.r(U, I)) == null || r == findContainingItemView) {
            if (g0(I)) {
                for (int i2 = this.z - 1; i2 >= 0; i2--) {
                    View r2 = this.A[i2].r(U, I);
                    if (r2 != null && r2 != findContainingItemView) {
                        return r2;
                    }
                }
            } else {
                for (int i3 = 0; i3 < this.z; i3++) {
                    View r3 = this.A[i3].r(U, I);
                    if (r3 != null && r3 != findContainingItemView) {
                        return r3;
                    }
                }
            }
            boolean z2 = (this.G ^ true) == (I == -1);
            if (!z) {
                if (z2) {
                    j3 = cVar.g();
                } else {
                    j3 = cVar.j();
                }
                View findViewByPosition = findViewByPosition(j3);
                if (findViewByPosition != null && findViewByPosition != findContainingItemView) {
                    return findViewByPosition;
                }
            }
            if (g0(I)) {
                for (int i4 = this.z - 1; i4 >= 0; i4--) {
                    if (i4 != cVar.e) {
                        if (z2) {
                            j2 = this.A[i4].g();
                        } else {
                            j2 = this.A[i4].j();
                        }
                        View findViewByPosition2 = findViewByPosition(j2);
                        if (findViewByPosition2 != null && findViewByPosition2 != findContainingItemView) {
                            return findViewByPosition2;
                        }
                    }
                }
            } else {
                for (int i5 = 0; i5 < this.z; i5++) {
                    if (z2) {
                        j = this.A[i5].g();
                    } else {
                        j = this.A[i5].j();
                    }
                    View findViewByPosition3 = findViewByPosition(j);
                    if (findViewByPosition3 != null && findViewByPosition3 != findContainingItemView) {
                        return findViewByPosition3;
                    }
                }
            }
            return null;
        }
        return r;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View P = P(false);
            View O = O(false);
            if (P == null || O == null) {
                return;
            }
            int position = getPosition(P);
            int position2 = getPosition(O);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
                return;
            }
            accessibilityEvent.setFromIndex(position2);
            accessibilityEvent.setToIndex(position);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        b0(i, i2, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.L.b();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        b0(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        b0(i, i2, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        b0(i, i2, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        f0(recycler, state, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.J = -1;
        this.K = Integer.MIN_VALUE;
        this.P = null;
        this.S.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.P = savedState;
            if (this.J != -1) {
                savedState.a();
                this.P.b();
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        int u;
        int startAfterPadding;
        int[] iArr;
        if (this.P != null) {
            return new SavedState(this.P);
        }
        SavedState savedState = new SavedState();
        savedState.o = this.G;
        savedState.p = this.N;
        savedState.q = this.O;
        LazySpanLookup lazySpanLookup = this.L;
        if (lazySpanLookup != null && (iArr = lazySpanLookup.f1622a) != null) {
            savedState.m = iArr;
            savedState.l = iArr.length;
            savedState.n = lazySpanLookup.b;
        } else {
            savedState.l = 0;
        }
        if (getChildCount() > 0) {
            savedState.h = this.N ? V() : U();
            savedState.i = Q();
            int i = this.z;
            savedState.j = i;
            savedState.k = new int[i];
            for (int i2 = 0; i2 < this.z; i2++) {
                if (this.N) {
                    u = this.A[i2].q(Integer.MIN_VALUE);
                    if (u != Integer.MIN_VALUE) {
                        startAfterPadding = this.B.getEndAfterPadding();
                        u -= startAfterPadding;
                        savedState.k[i2] = u;
                    } else {
                        savedState.k[i2] = u;
                    }
                } else {
                    u = this.A[i2].u(Integer.MIN_VALUE);
                    if (u != Integer.MIN_VALUE) {
                        startAfterPadding = this.B.getStartAfterPadding();
                        u -= startAfterPadding;
                        savedState.k[i2] = u;
                    } else {
                        savedState.k[i2] = u;
                    }
                }
            }
        } else {
            savedState.h = -1;
            savedState.i = -1;
            savedState.j = 0;
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        if (i == 0) {
            D();
        }
    }

    public final void p0(int i) {
        g gVar = this.F;
        gVar.e = i;
        gVar.d = this.H != (i == -1) ? -1 : 1;
    }

    public final void q0(int i, int i2) {
        for (int i3 = 0; i3 < this.z; i3++) {
            if (!this.A[i3].f1624a.isEmpty()) {
                w0(this.A[i3], i, i2);
            }
        }
    }

    public final boolean r0(RecyclerView.State state, b bVar) {
        int N;
        if (this.N) {
            N = R(state.getItemCount());
        } else {
            N = N(state.getItemCount());
        }
        bVar.f1623a = N;
        bVar.b = Integer.MIN_VALUE;
        return true;
    }

    public boolean s0(RecyclerView.State state, b bVar) {
        int i;
        int startAfterPadding;
        if (!state.isPreLayout() && (i = this.J) != -1) {
            if (i >= 0 && i < state.getItemCount()) {
                SavedState savedState = this.P;
                if (savedState != null && savedState.h != -1 && savedState.j >= 1) {
                    bVar.b = Integer.MIN_VALUE;
                    bVar.f1623a = this.J;
                } else {
                    View findViewByPosition = findViewByPosition(this.J);
                    if (findViewByPosition != null) {
                        bVar.f1623a = this.H ? V() : U();
                        if (this.K != Integer.MIN_VALUE) {
                            if (bVar.c) {
                                bVar.b = (this.B.getEndAfterPadding() - this.K) - this.B.getDecoratedEnd(findViewByPosition);
                            } else {
                                bVar.b = (this.B.getStartAfterPadding() + this.K) - this.B.getDecoratedStart(findViewByPosition);
                            }
                            return true;
                        } else if (this.B.getDecoratedMeasurement(findViewByPosition) > this.B.getTotalSpace()) {
                            if (bVar.c) {
                                startAfterPadding = this.B.getEndAfterPadding();
                            } else {
                                startAfterPadding = this.B.getStartAfterPadding();
                            }
                            bVar.b = startAfterPadding;
                            return true;
                        } else {
                            int decoratedStart = this.B.getDecoratedStart(findViewByPosition) - this.B.getStartAfterPadding();
                            if (decoratedStart < 0) {
                                bVar.b = -decoratedStart;
                                return true;
                            }
                            int endAfterPadding = this.B.getEndAfterPadding() - this.B.getDecoratedEnd(findViewByPosition);
                            if (endAfterPadding < 0) {
                                bVar.b = endAfterPadding;
                                return true;
                            }
                            bVar.b = Integer.MIN_VALUE;
                        }
                    } else {
                        int i2 = this.J;
                        bVar.f1623a = i2;
                        int i3 = this.K;
                        if (i3 == Integer.MIN_VALUE) {
                            bVar.c = C(i2) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i3);
                        }
                        bVar.d = true;
                    }
                }
                return true;
            }
            this.J = -1;
            this.K = Integer.MIN_VALUE;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return o0(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        SavedState savedState = this.P;
        if (savedState != null && savedState.h != i) {
            savedState.a();
        }
        this.J = i;
        this.K = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        SavedState savedState = this.P;
        if (savedState != null) {
            savedState.a();
        }
        this.J = i;
        this.K = i2;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return o0(i, recycler, state);
    }

    public void setGapStrategy(int i) {
        assertNotInLayoutOrScroll(null);
        if (i == this.M) {
            return;
        }
        if (i != 0 && i != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.M = i;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int chooseSize;
        int chooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.D == 1) {
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i2, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = RecyclerView.LayoutManager.chooseSize(i, (this.E * this.z) + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = RecyclerView.LayoutManager.chooseSize(i, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i2, (this.E * this.z) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i == this.D) {
            return;
        }
        this.D = i;
        OrientationHelper orientationHelper = this.B;
        this.B = this.C;
        this.C = orientationHelper;
        requestLayout();
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.P;
        if (savedState != null && savedState.o != z) {
            savedState.o = z;
        }
        this.G = z;
        requestLayout();
    }

    public void setSpanCount(int i) {
        assertNotInLayoutOrScroll(null);
        if (i != this.z) {
            invalidateSpanAssignments();
            this.z = i;
            this.I = new BitSet(this.z);
            this.A = new c[this.z];
            for (int i2 = 0; i2 < this.z; i2++) {
                this.A[i2] = new c(i2);
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.P == null;
    }

    public void t0(RecyclerView.State state, b bVar) {
        if (s0(state, bVar) || r0(state, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1623a = 0;
    }

    public final void u0(int i, RecyclerView.State state) {
        int i2;
        int i3;
        int targetScrollPosition;
        g gVar = this.F;
        boolean z = false;
        gVar.b = 0;
        gVar.c = i;
        if (!isSmoothScrolling() || (targetScrollPosition = state.getTargetScrollPosition()) == -1) {
            i2 = 0;
            i3 = 0;
        } else {
            if (this.H == (targetScrollPosition < i)) {
                i2 = this.B.getTotalSpace();
                i3 = 0;
            } else {
                i3 = this.B.getTotalSpace();
                i2 = 0;
            }
        }
        if (getClipToPadding()) {
            this.F.f = this.B.getStartAfterPadding() - i3;
            this.F.g = this.B.getEndAfterPadding() + i2;
        } else {
            this.F.g = this.B.getEnd() + i2;
            this.F.f = -i3;
        }
        g gVar2 = this.F;
        gVar2.h = false;
        gVar2.f1644a = true;
        if (this.B.getMode() == 0 && this.B.getEnd() == 0) {
            z = true;
        }
        gVar2.i = z;
    }

    public void v0(int i) {
        this.E = i / this.z;
        this.Q = View.MeasureSpec.makeMeasureSpec(i, this.C.getMode());
    }

    public final void w0(c cVar, int i, int i2) {
        int o = cVar.o();
        if (i == -1) {
            if (cVar.t() + o <= i2) {
                this.I.set(cVar.e, false);
            }
        } else if (cVar.p() - o >= i2) {
            this.I.set(cVar.e, false);
        }
    }

    public final void x(View view) {
        for (int i = this.z - 1; i >= 0; i--) {
            this.A[i].a(view);
        }
    }

    public final int x0(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    public final void y(b bVar) {
        int startAfterPadding;
        SavedState savedState = this.P;
        int i = savedState.j;
        if (i > 0) {
            if (i == this.z) {
                for (int i2 = 0; i2 < this.z; i2++) {
                    this.A[i2].e();
                    SavedState savedState2 = this.P;
                    int i3 = savedState2.k[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        if (savedState2.p) {
                            startAfterPadding = this.B.getEndAfterPadding();
                        } else {
                            startAfterPadding = this.B.getStartAfterPadding();
                        }
                        i3 += startAfterPadding;
                    }
                    this.A[i2].A(i3);
                }
            } else {
                savedState.b();
                SavedState savedState3 = this.P;
                savedState3.h = savedState3.i;
            }
        }
        SavedState savedState4 = this.P;
        this.O = savedState4.q;
        setReverseLayout(savedState4.o);
        n0();
        SavedState savedState5 = this.P;
        int i4 = savedState5.h;
        if (i4 != -1) {
            this.J = i4;
            bVar.c = savedState5.p;
        } else {
            bVar.c = this.H;
        }
        if (savedState5.l > 1) {
            LazySpanLookup lazySpanLookup = this.L;
            lazySpanLookup.f1622a = savedState5.m;
            lazySpanLookup.b = savedState5.n;
        }
    }

    public boolean z() {
        int q = this.A[0].q(Integer.MIN_VALUE);
        for (int i = 1; i < this.z; i++) {
            if (this.A[i].q(Integer.MIN_VALUE) != q) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* loaded from: classes.dex */
    public static class LazySpanLookup {

        /* renamed from: a  reason: collision with root package name */
        public int[] f1622a;
        public List<FullSpanItem> b;

        public void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = this.b.get(i);
                if (fullSpanItem2.h == fullSpanItem.h) {
                    this.b.remove(i);
                }
                if (fullSpanItem2.h >= fullSpanItem.h) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        public void b() {
            int[] iArr = this.f1622a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.b = null;
        }

        public void c(int i) {
            int[] iArr = this.f1622a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.f1622a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[o(i)];
                this.f1622a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1622a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i) {
            List<FullSpanItem> list = this.b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.b.get(size).h >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return h(i);
        }

        public FullSpanItem e(int i, int i2, int i3, boolean z) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = this.b.get(i4);
                int i5 = fullSpanItem.h;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || fullSpanItem.i == i3 || (z && fullSpanItem.k))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                if (fullSpanItem.h == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public int g(int i) {
            int[] iArr = this.f1622a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        public int h(int i) {
            int[] iArr = this.f1622a;
            if (iArr != null && i < iArr.length) {
                int i2 = i(i);
                if (i2 == -1) {
                    int[] iArr2 = this.f1622a;
                    Arrays.fill(iArr2, i, iArr2.length, -1);
                    return this.f1622a.length;
                }
                int min = Math.min(i2 + 1, this.f1622a.length);
                Arrays.fill(this.f1622a, i, min, -1);
                return min;
            }
            return -1;
        }

        public final int i(int i) {
            if (this.b == null) {
                return -1;
            }
            FullSpanItem f = f(i);
            if (f != null) {
                this.b.remove(f);
            }
            int size = this.b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (this.b.get(i2).h >= i) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 != -1) {
                this.b.remove(i2);
                return this.b.get(i2).h;
            }
            return -1;
        }

        public void j(int i, int i2) {
            int[] iArr = this.f1622a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1622a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1622a, i, i3, -1);
            l(i, i2);
        }

        public void k(int i, int i2) {
            int[] iArr = this.f1622a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1622a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1622a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            m(i, i2);
        }

        public final void l(int i, int i2) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i3 = fullSpanItem.h;
                if (i3 >= i) {
                    fullSpanItem.h = i3 + i2;
                }
            }
        }

        public final void m(int i, int i2) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i4 = fullSpanItem.h;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.b.remove(size);
                    } else {
                        fullSpanItem.h = i4 - i2;
                    }
                }
            }
        }

        public void n(int i, c cVar) {
            c(i);
            this.f1622a[i] = cVar.e;
        }

        public int o(int i) {
            int length = this.f1622a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        /* loaded from: classes.dex */
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();
            public int h;
            public int i;
            public int[] j;
            public boolean k;

            /* loaded from: classes.dex */
            public class a implements Parcelable.Creator<FullSpanItem> {
                @Override // android.os.Parcelable.Creator
                /* renamed from: a */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: b */
                public FullSpanItem[] newArray(int i) {
                    return new FullSpanItem[i];
                }
            }

            public FullSpanItem(Parcel parcel) {
                this.h = parcel.readInt();
                this.i = parcel.readInt();
                this.k = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    int[] iArr = new int[readInt];
                    this.j = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            public int a(int i) {
                int[] iArr = this.j;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.h + ", mGapDir=" + this.i + ", mHasUnwantedGapAfter=" + this.k + ", mGapPerSpan=" + Arrays.toString(this.j) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.h);
                parcel.writeInt(this.i);
                parcel.writeInt(this.k ? 1 : 0);
                int[] iArr = this.j;
                if (iArr != null && iArr.length > 0) {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.j);
                    return;
                }
                parcel.writeInt(0);
            }

            public FullSpanItem() {
            }
        }
    }

    public StaggeredGridLayoutManager(int i, int i2) {
        this.D = i2;
        setSpanCount(i);
        this.F = new g();
        L();
    }
}
