package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ListViewCompat;
/* loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingParent3, NestedScrollingParent2, NestedScrollingChild3, NestedScrollingChild2, NestedScrollingParent, NestedScrollingChild {
    public static final int DEFAULT = 1;
    public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
    public static final int LARGE = 0;
    public static final String V = SwipeRefreshLayout.class.getSimpleName();
    public static final int[] W = {16842766};
    public boolean A;
    public final DecelerateInterpolator B;
    public androidx.swiperefreshlayout.widget.a C;
    public int D;
    public float E;
    public int F;
    public int G;
    public CircularProgressDrawable H;
    public Animation I;
    public Animation J;
    public Animation K;
    public Animation L;
    public Animation M;
    public boolean N;
    public int O;
    public boolean P;
    public OnChildScrollUpCallback Q;
    public boolean R;
    public Animation.AnimationListener S;
    public final Animation T;
    public final Animation U;
    public View h;
    public OnRefreshListener i;
    public boolean j;
    public int k;
    public float l;
    public float m;
    public int mFrom;
    public int mOriginalOffsetTop;
    public final NestedScrollingParentHelper n;
    public final NestedScrollingChildHelper o;
    public final int[] p;
    public final int[] q;
    public final int[] r;
    public boolean s;
    public int t;
    public int u;
    public float v;
    public float w;
    public boolean x;
    public int y;
    public boolean z;

    /* loaded from: classes.dex */
    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(@NonNull SwipeRefreshLayout swipeRefreshLayout, @Nullable View view);
    }

    /* loaded from: classes.dex */
    public interface OnRefreshListener {
        void onRefresh();
    }

    /* loaded from: classes.dex */
    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            OnRefreshListener onRefreshListener;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.j) {
                swipeRefreshLayout.H.setAlpha(255);
                SwipeRefreshLayout.this.H.start();
                SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
                if (swipeRefreshLayout2.N && (onRefreshListener = swipeRefreshLayout2.i) != null) {
                    onRefreshListener.onRefresh();
                }
                SwipeRefreshLayout swipeRefreshLayout3 = SwipeRefreshLayout.this;
                swipeRefreshLayout3.u = swipeRefreshLayout3.C.getTop();
                return;
            }
            swipeRefreshLayout.j();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes.dex */
    public class b extends Animation {
        public b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f);
        }
    }

    /* loaded from: classes.dex */
    public class c extends Animation {
        public c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
        }
    }

    /* loaded from: classes.dex */
    public class d extends Animation {
        public final /* synthetic */ int h;
        public final /* synthetic */ int i;

        public d(int i, int i2) {
            this.h = i;
            this.i = i2;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            CircularProgressDrawable circularProgressDrawable = SwipeRefreshLayout.this.H;
            int i = this.h;
            circularProgressDrawable.setAlpha((int) (i + ((this.i - i) * f)));
        }
    }

    /* loaded from: classes.dex */
    public class e implements Animation.AnimationListener {
        public e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.z) {
                return;
            }
            swipeRefreshLayout.p(null);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes.dex */
    public class f extends Animation {
        public f() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            int i;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (!swipeRefreshLayout.P) {
                i = swipeRefreshLayout.F - Math.abs(swipeRefreshLayout.mOriginalOffsetTop);
            } else {
                i = swipeRefreshLayout.F;
            }
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            int i2 = swipeRefreshLayout2.mFrom;
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((i2 + ((int) ((i - i2) * f))) - swipeRefreshLayout2.C.getTop());
            SwipeRefreshLayout.this.H.setArrowScale(1.0f - f);
        }
    }

    /* loaded from: classes.dex */
    public class g extends Animation {
        public g() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.h(f);
        }
    }

    /* loaded from: classes.dex */
    public class h extends Animation {
        public h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            float f2 = swipeRefreshLayout.E;
            swipeRefreshLayout.setAnimationProgress(f2 + ((-f2) * f));
            SwipeRefreshLayout.this.h(f);
        }
    }

    public SwipeRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    private void setColorViewAlpha(int i) {
        this.C.getBackground().setAlpha(i);
        this.H.setAlpha(i);
    }

    public final void a(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.T.reset();
        this.T.setDuration(200L);
        this.T.setInterpolator(this.B);
        if (animationListener != null) {
            this.C.b(animationListener);
        }
        this.C.clearAnimation();
        this.C.startAnimation(this.T);
    }

    public final void b(int i, Animation.AnimationListener animationListener) {
        if (this.z) {
            q(i, animationListener);
            return;
        }
        this.mFrom = i;
        this.U.reset();
        this.U.setDuration(200L);
        this.U.setInterpolator(this.B);
        if (animationListener != null) {
            this.C.b(animationListener);
        }
        this.C.clearAnimation();
        this.C.startAnimation(this.U);
    }

    public final void c() {
        this.C = new androidx.swiperefreshlayout.widget.a(getContext());
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getContext());
        this.H = circularProgressDrawable;
        circularProgressDrawable.setStyle(1);
        this.C.setImageDrawable(this.H);
        this.C.setVisibility(8);
        addView(this.C);
    }

    public boolean canChildScrollUp() {
        OnChildScrollUpCallback onChildScrollUpCallback = this.Q;
        if (onChildScrollUpCallback != null) {
            return onChildScrollUpCallback.canChildScrollUp(this, this.h);
        }
        View view = this.h;
        if (view instanceof ListView) {
            return ListViewCompat.canScrollList((ListView) view, -1);
        }
        return view.canScrollVertically(-1);
    }

    public final void d() {
        if (this.h == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.C)) {
                    this.h = childAt;
                    return;
                }
            }
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.o.dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.o.dispatchNestedPreFling(f2, f3);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return i3 == 0 && dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // androidx.core.view.NestedScrollingChild3
    public void dispatchNestedScroll(int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5, @NonNull int[] iArr2) {
        if (i5 == 0) {
            this.o.dispatchNestedScroll(i, i2, i3, i4, iArr, i5, iArr2);
        }
    }

    public final void e(float f2) {
        if (f2 > this.l) {
            k(true, true);
            return;
        }
        this.j = false;
        this.H.setStartEndTrim(0.0f, 0.0f);
        b(this.u, this.z ? null : new e());
        this.H.setArrowEnabled(false);
    }

    public final boolean f(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    public final void g(float f2) {
        this.H.setArrowEnabled(true);
        float min = Math.min(1.0f, Math.abs(f2 / this.l));
        float max = (((float) Math.max(min - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f2) - this.l;
        int i = this.G;
        if (i <= 0) {
            if (this.P) {
                i = this.F - this.mOriginalOffsetTop;
            } else {
                i = this.F;
            }
        }
        float f3 = i;
        double max2 = Math.max(0.0f, Math.min(abs, f3 * 2.0f) / f3) / 4.0f;
        float pow = ((float) (max2 - Math.pow(max2, 2.0d))) * 2.0f;
        int i2 = this.mOriginalOffsetTop + ((int) ((f3 * min) + (f3 * pow * 2.0f)));
        if (this.C.getVisibility() != 0) {
            this.C.setVisibility(0);
        }
        if (!this.z) {
            this.C.setScaleX(1.0f);
            this.C.setScaleY(1.0f);
        }
        if (this.z) {
            setAnimationProgress(Math.min(1.0f, f2 / this.l));
        }
        if (f2 < this.l) {
            if (this.H.getAlpha() > 76 && !f(this.K)) {
                o();
            }
        } else if (this.H.getAlpha() < 255 && !f(this.L)) {
            n();
        }
        this.H.setStartEndTrim(0.0f, Math.min(0.8f, max * 0.8f));
        this.H.setArrowScale(Math.min(1.0f, max));
        this.H.setProgressRotation((((max * 0.4f) - 0.25f) + (pow * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i2 - this.u);
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        int i3 = this.D;
        return i3 < 0 ? i2 : i2 == i + (-1) ? i3 : i2 >= i3 ? i2 + 1 : i2;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.n.getNestedScrollAxes();
    }

    public int getProgressCircleDiameter() {
        return this.O;
    }

    public int getProgressViewEndOffset() {
        return this.F;
    }

    public int getProgressViewStartOffset() {
        return this.mOriginalOffsetTop;
    }

    public void h(float f2) {
        int i = this.mFrom;
        setTargetOffsetTopAndBottom((i + ((int) ((this.mOriginalOffsetTop - i) * f2))) - this.C.getTop());
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i) {
        return i == 0 && hasNestedScrollingParent();
    }

    public final void i(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.y) {
            this.y = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.o.isNestedScrollingEnabled();
    }

    public boolean isRefreshing() {
        return this.j;
    }

    public void j() {
        this.C.clearAnimation();
        this.H.stop();
        this.C.setVisibility(8);
        setColorViewAlpha(255);
        if (this.z) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.u);
        }
        this.u = this.C.getTop();
    }

    public final void k(boolean z, boolean z2) {
        if (this.j != z) {
            this.N = z2;
            d();
            this.j = z;
            if (z) {
                a(this.u, this.S);
            } else {
                p(this.S);
            }
        }
    }

    public final Animation l(int i, int i2) {
        d dVar = new d(i, i2);
        dVar.setDuration(300L);
        this.C.b(null);
        this.C.clearAnimation();
        this.C.startAnimation(dVar);
        return dVar;
    }

    public final void m(float f2) {
        float f3 = this.w;
        int i = this.k;
        if (f2 - f3 <= i || this.x) {
            return;
        }
        this.v = f3 + i;
        this.x = true;
        this.H.setAlpha(76);
    }

    public final void n() {
        this.L = l(this.H.getAlpha(), 255);
    }

    public final void o() {
        this.K = l(this.H.getAlpha(), 76);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        j();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        d();
        int actionMasked = motionEvent.getActionMasked();
        if (this.A && actionMasked == 0) {
            this.A = false;
        }
        if (!isEnabled() || this.A || canChildScrollUp() || this.j || this.s) {
            return false;
        }
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i = this.y;
                    if (i == -1) {
                        Log.e(V, "Got ACTION_MOVE event but don't have an active pointer id.");
                        return false;
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(i);
                    if (findPointerIndex < 0) {
                        return false;
                    }
                    m(motionEvent.getY(findPointerIndex));
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        i(motionEvent);
                    }
                }
            }
            this.x = false;
            this.y = -1;
        } else {
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.C.getTop());
            int pointerId = motionEvent.getPointerId(0);
            this.y = pointerId;
            this.x = false;
            int findPointerIndex2 = motionEvent.findPointerIndex(pointerId);
            if (findPointerIndex2 < 0) {
                return false;
            }
            this.w = motionEvent.getY(findPointerIndex2);
        }
        return this.x;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.h == null) {
            d();
        }
        View view = this.h;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.C.getMeasuredWidth();
        int measuredHeight2 = this.C.getMeasuredHeight();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = this.u;
        this.C.layout(i5 - i6, i7, i5 + i6, measuredHeight2 + i7);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.h == null) {
            d();
        }
        View view = this.h;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.C.measure(View.MeasureSpec.makeMeasureSpec(this.O, 1073741824), View.MeasureSpec.makeMeasureSpec(this.O, 1073741824));
        this.D = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.C) {
                this.D = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, int i5, @NonNull int[] iArr) {
        if (i5 != 0) {
            return;
        }
        int i6 = iArr[1];
        dispatchNestedScroll(i, i2, i3, i4, this.q, i5, iArr);
        int i7 = i4 - (iArr[1] - i6);
        int i8 = i7 == 0 ? i4 + this.q[1] : i7;
        if (i8 >= 0 || canChildScrollUp()) {
            return;
        }
        float abs = this.m + Math.abs(i8);
        this.m = abs;
        g(abs);
        iArr[1] = iArr[1] + i7;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setRefreshing(savedState.h);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.j);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            return onStartNestedScroll(view, view2, i);
        }
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.A && actionMasked == 0) {
            this.A = false;
        }
        if (!isEnabled() || this.A || canChildScrollUp() || this.j || this.s) {
            return false;
        }
        if (actionMasked == 0) {
            this.y = motionEvent.getPointerId(0);
            this.x = false;
        } else if (actionMasked == 1) {
            int findPointerIndex = motionEvent.findPointerIndex(this.y);
            if (findPointerIndex < 0) {
                Log.e(V, "Got ACTION_UP event but don't have an active pointer id.");
                return false;
            }
            if (this.x) {
                this.x = false;
                e((motionEvent.getY(findPointerIndex) - this.v) * 0.5f);
            }
            this.y = -1;
            return false;
        } else if (actionMasked == 2) {
            int findPointerIndex2 = motionEvent.findPointerIndex(this.y);
            if (findPointerIndex2 < 0) {
                Log.e(V, "Got ACTION_MOVE event but have an invalid active pointer id.");
                return false;
            }
            float y = motionEvent.getY(findPointerIndex2);
            m(y);
            if (this.x) {
                float f2 = (y - this.v) * 0.5f;
                if (f2 <= 0.0f) {
                    return false;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                g(f2);
            }
        } else if (actionMasked == 3) {
            return false;
        } else {
            if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                if (actionIndex < 0) {
                    Log.e(V, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                this.y = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                i(motionEvent);
            }
        }
        return true;
    }

    public void p(Animation.AnimationListener animationListener) {
        c cVar = new c();
        this.J = cVar;
        cVar.setDuration(150L);
        this.C.b(animationListener);
        this.C.clearAnimation();
        this.C.startAnimation(this.J);
    }

    public final void q(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.E = this.C.getScaleX();
        h hVar = new h();
        this.M = hVar;
        hVar.setDuration(150L);
        if (animationListener != null) {
            this.C.b(animationListener);
        }
        this.C.clearAnimation();
        this.C.startAnimation(this.M);
    }

    public final void r(Animation.AnimationListener animationListener) {
        this.C.setVisibility(0);
        this.H.setAlpha(255);
        b bVar = new b();
        this.I = bVar;
        bVar.setDuration(this.t);
        if (animationListener != null) {
            this.C.b(animationListener);
        }
        this.C.clearAnimation();
        this.C.startAnimation(this.I);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        View view;
        ViewParent parent;
        if ((Build.VERSION.SDK_INT < 21 && (this.h instanceof AbsListView)) || ((view = this.h) != null && !ViewCompat.isNestedScrollingEnabled(view))) {
            if (this.R || (parent = getParent()) == null) {
                return;
            }
            parent.requestDisallowInterceptTouchEvent(z);
            return;
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void setAnimationProgress(float f2) {
        this.C.setScaleX(f2);
        this.C.setScaleY(f2);
    }

    @Deprecated
    public void setColorScheme(@ColorRes int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(@ColorInt int... iArr) {
        d();
        this.H.setColorSchemeColors(iArr);
    }

    public void setColorSchemeResources(@ColorRes int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = ContextCompat.getColor(context, iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i) {
        this.l = i;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        j();
    }

    @Deprecated
    public void setLegacyRequestDisallowInterceptTouchEventEnabled(boolean z) {
        this.R = z;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.o.setNestedScrollingEnabled(z);
    }

    public void setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback onChildScrollUpCallback) {
        this.Q = onChildScrollUpCallback;
    }

    public void setOnRefreshListener(@Nullable OnRefreshListener onRefreshListener) {
        this.i = onRefreshListener;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int i) {
        this.C.setBackgroundColor(i);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int i) {
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), i));
    }

    public void setProgressViewEndTarget(boolean z, int i) {
        this.F = i;
        this.z = z;
        this.C.invalidate();
    }

    public void setProgressViewOffset(boolean z, int i, int i2) {
        this.z = z;
        this.mOriginalOffsetTop = i;
        this.F = i2;
        this.P = true;
        j();
        this.j = false;
    }

    public void setRefreshing(boolean z) {
        int i;
        if (z && this.j != z) {
            this.j = z;
            if (!this.P) {
                i = this.F + this.mOriginalOffsetTop;
            } else {
                i = this.F;
            }
            setTargetOffsetTopAndBottom(i - this.u);
            this.N = false;
            r(this.S);
            return;
        }
        k(z, false);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                this.O = (int) (displayMetrics.density * 56.0f);
            } else {
                this.O = (int) (displayMetrics.density * 40.0f);
            }
            this.C.setImageDrawable(null);
            this.H.setStyle(i);
            this.C.setImageDrawable(this.H);
        }
    }

    public void setSlingshotDistance(@Px int i) {
        this.G = i;
    }

    public void setTargetOffsetTopAndBottom(int i) {
        this.C.bringToFront();
        ViewCompat.offsetTopAndBottom(this.C, i);
        this.u = this.C.getTop();
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i, int i2) {
        return i2 == 0 && startNestedScroll(i);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int i) {
        if (i == 0) {
            stopNestedScroll();
        }
    }

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final boolean h;

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

        public SavedState(Parcelable parcelable, boolean z) {
            super(parcelable);
            this.h = z;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.h = parcel.readByte() != 0;
        }
    }

    public SwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = false;
        this.l = -1.0f;
        this.p = new int[2];
        this.q = new int[2];
        this.r = new int[2];
        this.y = -1;
        this.D = -1;
        this.S = new a();
        this.T = new f();
        this.U = new g();
        this.k = ViewConfiguration.get(context).getScaledTouchSlop();
        this.t = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.B = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.O = (int) (displayMetrics.density * 40.0f);
        c();
        setChildrenDrawingOrderEnabled(true);
        int i = (int) (displayMetrics.density * 64.0f);
        this.F = i;
        this.l = i;
        this.n = new NestedScrollingParentHelper(this);
        this.o = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.O;
        this.u = i2;
        this.mOriginalOffsetTop = i2;
        h(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, W);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.o.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return i5 == 0 && this.o.dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.o.hasNestedScrollingParent();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            float f2 = this.m;
            if (f2 > 0.0f) {
                float f3 = i2;
                if (f3 > f2) {
                    iArr[1] = (int) f2;
                    this.m = 0.0f;
                } else {
                    this.m = f2 - f3;
                    iArr[1] = i2;
                }
                g(this.m);
            }
        }
        if (this.P && i2 > 0 && this.m == 0.0f && Math.abs(i2 - iArr[1]) > 0) {
            this.C.setVisibility(8);
        }
        int[] iArr2 = this.p;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.n.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(i & 2);
        this.m = 0.0f;
        this.s = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.A || this.j || (i & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
        this.n.onStopNestedScroll(view);
        this.s = false;
        float f2 = this.m;
        if (f2 > 0.0f) {
            e(f2);
            this.m = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.o.startNestedScroll(i);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.o.stopNestedScroll();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.o.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        onNestedScroll(view, i, i2, i3, i4, i5, this.r);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        onNestedScroll(view, i, i2, i3, i4, 0, this.r);
    }
}
