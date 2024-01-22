package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    public static final int d0 = R.style.Widget_Design_BottomSheet_Modal;
    @Nullable
    public ValueAnimator A;
    public int B;
    public int C;
    public int D;
    public float E;
    public int F;
    public float G;
    public boolean H;
    public boolean I;
    public boolean J;
    public int K;
    public int L;
    @Nullable
    public ViewDragHelper M;
    public boolean N;
    public int O;
    public boolean P;
    public int Q;
    public int R;
    public int S;
    @Nullable
    public WeakReference<V> T;
    @Nullable
    public WeakReference<View> U;
    @NonNull
    public final ArrayList<BottomSheetCallback> V;
    @Nullable
    public VelocityTracker W;
    public int X;
    public int Y;
    public boolean Z;

    /* renamed from: a  reason: collision with root package name */
    public int f10230a;
    @Nullable
    public Map<View, Integer> a0;
    public boolean b;
    public int b0;
    public boolean c;
    public final ViewDragHelper.Callback c0;
    public float d;
    public int e;
    public boolean f;
    public int g;
    public int h;
    public MaterialShapeDrawable i;
    @Nullable
    public ColorStateList j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;
    public int w;
    public ShapeAppearanceModel x;
    public boolean y;
    public final BottomSheetBehavior<V>.f z;

    /* loaded from: classes10.dex */
    public static abstract class BottomSheetCallback {
        public void a(@NonNull View view) {
        }

        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface SaveFlags {
    }

    /* loaded from: classes10.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int i;
        public int j;
        public boolean k;
        public boolean l;
        public boolean m;

        /* loaded from: classes10.dex */
        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            @Nullable
            /* renamed from: a */
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            @NonNull
            /* renamed from: b */
            public SavedState createFromParcel(@NonNull Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: c */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(@NonNull Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeInt(this.l ? 1 : 0);
            parcel.writeInt(this.m ? 1 : 0);
        }

        public SavedState(@NonNull Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt() == 1;
            this.l = parcel.readInt() == 1;
            this.m = parcel.readInt() == 1;
        }

        public SavedState(Parcelable parcelable, @NonNull BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.i = bottomSheetBehavior.K;
            this.j = bottomSheetBehavior.e;
            this.k = bottomSheetBehavior.b;
            this.l = bottomSheetBehavior.H;
            this.m = bottomSheetBehavior.I;
        }

        @Deprecated
        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.i = i;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface StableState {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface State {
    }

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ View h;
        public final /* synthetic */ int i;

        public a(View view, int i) {
            this.h = view;
            this.i = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.N(this.h, this.i, false);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.i != null) {
                BottomSheetBehavior.this.i.setInterpolation(floatValue);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c implements ViewUtils.OnApplyWindowInsetsListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f10231a;

        public c(boolean z) {
            this.f10231a = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00c3  */
        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.core.view.WindowInsetsCompat onApplyWindowInsets(android.view.View r11, androidx.core.view.WindowInsetsCompat r12, com.google.android.material.internal.ViewUtils.RelativePadding r13) {
            /*
                r10 = this;
                int r0 = androidx.core.view.WindowInsetsCompat.Type.systemBars()
                androidx.core.graphics.Insets r0 = r12.getInsets(r0)
                int r1 = androidx.core.view.WindowInsetsCompat.Type.mandatorySystemGestures()
                androidx.core.graphics.Insets r1 = r12.getInsets(r1)
                com.google.android.material.bottomsheet.BottomSheetBehavior r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                int r3 = r0.top
                com.google.android.material.bottomsheet.BottomSheetBehavior.j(r2, r3)
                boolean r2 = com.google.android.material.internal.ViewUtils.isLayoutRtl(r11)
                int r3 = r11.getPaddingBottom()
                int r4 = r11.getPaddingLeft()
                int r5 = r11.getPaddingRight()
                com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.k(r6)
                if (r6 == 0) goto L41
                com.google.android.material.bottomsheet.BottomSheetBehavior r3 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                int r6 = r12.getSystemWindowInsetBottom()
                com.google.android.material.bottomsheet.BottomSheetBehavior.m(r3, r6)
                int r3 = r13.bottom
                com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                int r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.l(r6)
                int r3 = r3 + r6
            L41:
                com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.n(r6)
                if (r6 == 0) goto L53
                if (r2 == 0) goto L4e
                int r4 = r13.end
                goto L50
            L4e:
                int r4 = r13.start
            L50:
                int r6 = r0.left
                int r4 = r4 + r6
            L53:
                com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.o(r6)
                if (r6 == 0) goto L66
                if (r2 == 0) goto L60
                int r13 = r13.start
                goto L62
            L60:
                int r13 = r13.end
            L62:
                int r2 = r0.right
                int r5 = r13 + r2
            L66:
                android.view.ViewGroup$LayoutParams r13 = r11.getLayoutParams()
                android.view.ViewGroup$MarginLayoutParams r13 = (android.view.ViewGroup.MarginLayoutParams) r13
                com.google.android.material.bottomsheet.BottomSheetBehavior r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                boolean r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.p(r2)
                r6 = 0
                r7 = 1
                if (r2 == 0) goto L80
                int r2 = r13.leftMargin
                int r8 = r0.left
                if (r2 == r8) goto L80
                r13.leftMargin = r8
                r2 = r7
                goto L81
            L80:
                r2 = r6
            L81:
                com.google.android.material.bottomsheet.BottomSheetBehavior r8 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                boolean r8 = com.google.android.material.bottomsheet.BottomSheetBehavior.q(r8)
                if (r8 == 0) goto L92
                int r8 = r13.rightMargin
                int r9 = r0.right
                if (r8 == r9) goto L92
                r13.rightMargin = r9
                r2 = r7
            L92:
                com.google.android.material.bottomsheet.BottomSheetBehavior r8 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                boolean r8 = com.google.android.material.bottomsheet.BottomSheetBehavior.b(r8)
                if (r8 == 0) goto La3
                int r8 = r13.topMargin
                int r0 = r0.top
                if (r8 == r0) goto La3
                r13.topMargin = r0
                goto La4
            La3:
                r7 = r2
            La4:
                if (r7 == 0) goto La9
                r11.setLayoutParams(r13)
            La9:
                int r13 = r11.getPaddingTop()
                r11.setPadding(r4, r13, r5, r3)
                boolean r11 = r10.f10231a
                if (r11 == 0) goto Lbb
                com.google.android.material.bottomsheet.BottomSheetBehavior r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                int r13 = r1.bottom
                com.google.android.material.bottomsheet.BottomSheetBehavior.c(r11, r13)
            Lbb:
                com.google.android.material.bottomsheet.BottomSheetBehavior r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                boolean r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.k(r11)
                if (r11 != 0) goto Lc7
                boolean r11 = r10.f10231a
                if (r11 == 0) goto Lcc
            Lc7:
                com.google.android.material.bottomsheet.BottomSheetBehavior r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                com.google.android.material.bottomsheet.BottomSheetBehavior.d(r11, r6)
            Lcc:
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.c.onApplyWindowInsets(android.view.View, androidx.core.view.WindowInsetsCompat, com.google.android.material.internal.ViewUtils$RelativePadding):androidx.core.view.WindowInsetsCompat");
        }
    }

    /* loaded from: classes10.dex */
    public class d extends ViewDragHelper.Callback {

        /* renamed from: a  reason: collision with root package name */
        public long f10232a;

        public d() {
        }

        public final boolean a(@NonNull View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.S + bottomSheetBehavior.getExpandedOffset()) / 2;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(@NonNull View view, int i, int i2) {
            return view.getLeft();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(@NonNull View view, int i, int i2) {
            int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return MathUtils.clamp(i, expandedOffset, bottomSheetBehavior.H ? bottomSheetBehavior.S : bottomSheetBehavior.F);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(@NonNull View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            if (bottomSheetBehavior.H) {
                return bottomSheetBehavior.S;
            }
            return bottomSheetBehavior.F;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            if (i == 1 && BottomSheetBehavior.this.J) {
                BottomSheetBehavior.this.J(1);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(@NonNull View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.y(i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0034, code lost:
            if (r7.b.shouldExpandOnUpwardDrag(r0, (r9 * 100.0f) / r10.S) != false) goto L5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x003b, code lost:
            if (r9 > r7.b.D) goto L6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x008b, code lost:
            if (java.lang.Math.abs(r8.getTop() - r7.b.getExpandedOffset()) < java.lang.Math.abs(r8.getTop() - r7.b.D)) goto L5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00c9, code lost:
            if (r7.b.shouldSkipHalfExpandedStateWhenDragging() == false) goto L6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00eb, code lost:
            if (java.lang.Math.abs(r9 - r7.b.C) < java.lang.Math.abs(r9 - r7.b.F)) goto L5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0107, code lost:
            if (r7.b.shouldSkipHalfExpandedStateWhenDragging() != false) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0121, code lost:
            if (r7.b.shouldSkipHalfExpandedStateWhenDragging() == false) goto L6;
         */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onViewReleased(@androidx.annotation.NonNull android.view.View r8, float r9, float r10) {
            /*
                Method dump skipped, instructions count: 302
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.d.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(@NonNull View view, int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.K;
            if (i2 == 1 || bottomSheetBehavior.Z) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.X == i) {
                WeakReference<View> weakReference = bottomSheetBehavior.U;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            this.f10232a = System.currentTimeMillis();
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.T;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    /* loaded from: classes10.dex */
    public class e implements AccessibilityViewCommand {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f10233a;

        public e(int i) {
            this.f10233a = i;
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
            BottomSheetBehavior.this.setState(this.f10233a);
            return true;
        }
    }

    public BottomSheetBehavior() {
        this.f10230a = 0;
        this.b = true;
        this.c = false;
        this.k = -1;
        this.l = -1;
        this.z = new f(this, null);
        this.E = 0.5f;
        this.G = -1.0f;
        this.J = true;
        this.K = 4;
        this.L = 4;
        this.V = new ArrayList<>();
        this.b0 = -1;
        this.c0 = new d();
    }

    @NonNull
    public static <V extends View> BottomSheetBehavior<V> from(@NonNull V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    public final int A(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode != 1073741824) {
            if (size != 0) {
                i3 = Math.min(size, i3);
            }
            return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
    }

    public MaterialShapeDrawable B() {
        return this.i;
    }

    public final int C(int i) {
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i == 6) {
                        return this.D;
                    }
                    throw new IllegalArgumentException("Invalid state to get top offset: " + i);
                }
                return this.S;
            }
            return this.F;
        }
        return getExpandedOffset();
    }

    public final float D() {
        VelocityTracker velocityTracker = this.W;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.d);
        return this.W.getYVelocity(this.X);
    }

    public final boolean E(V v) {
        ViewParent parent = v.getParent();
        return parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(v);
    }

    public final void F(V v, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i) {
        ViewCompat.replaceAccessibilityAction(v, accessibilityActionCompat, null, v(i));
    }

    public final void G() {
        this.X = -1;
        VelocityTracker velocityTracker = this.W;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.W = null;
        }
    }

    public final void H(@NonNull SavedState savedState) {
        int i = this.f10230a;
        if (i == 0) {
            return;
        }
        if (i == -1 || (i & 1) == 1) {
            this.e = savedState.j;
        }
        if (i == -1 || (i & 2) == 2) {
            this.b = savedState.k;
        }
        if (i == -1 || (i & 4) == 4) {
            this.H = savedState.l;
        }
        if (i == -1 || (i & 8) == 8) {
            this.I = savedState.m;
        }
    }

    public final void I(V v, Runnable runnable) {
        if (E(v)) {
            v.post(runnable);
        } else {
            runnable.run();
        }
    }

    public void J(int i) {
        V v;
        if (this.K == i) {
            return;
        }
        this.K = i;
        if (i == 4 || i == 3 || i == 6 || (this.H && i == 5)) {
            this.L = i;
        }
        WeakReference<V> weakReference = this.T;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        if (i == 3) {
            Q(true);
        } else if (i == 6 || i == 5 || i == 4) {
            Q(false);
        }
        P(i);
        for (int i2 = 0; i2 < this.V.size(); i2++) {
            this.V.get(i2).onStateChanged(v, i);
        }
        O();
    }

    public final void K(@NonNull View view) {
        boolean z = (Build.VERSION.SDK_INT < 29 || isGestureInsetBottomIgnored() || this.f) ? false : true;
        if (this.o || this.p || this.q || this.s || this.t || this.u || z) {
            ViewUtils.doOnApplyWindowInsets(view, new c(z));
        }
    }

    public final boolean L() {
        return this.M != null && (this.J || this.K == 1);
    }

    public boolean M(@NonNull View view, float f2) {
        if (this.I) {
            return true;
        }
        if (view.getTop() < this.F) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f2 * 0.1f)) - ((float) this.F)) / ((float) u()) > 0.5f;
    }

    public final void N(View view, int i, boolean z) {
        int C = C(i);
        ViewDragHelper viewDragHelper = this.M;
        if (viewDragHelper != null && (!z ? !viewDragHelper.smoothSlideViewTo(view, view.getLeft(), C) : !viewDragHelper.settleCapturedViewAt(view.getLeft(), C))) {
            J(2);
            P(i);
            this.z.c(i);
            return;
        }
        J(i);
    }

    public final void O() {
        V v;
        WeakReference<V> weakReference = this.T;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        ViewCompat.removeAccessibilityAction(v, 524288);
        ViewCompat.removeAccessibilityAction(v, 262144);
        ViewCompat.removeAccessibilityAction(v, 1048576);
        int i = this.b0;
        if (i != -1) {
            ViewCompat.removeAccessibilityAction(v, i);
        }
        if (!this.b && this.K != 6) {
            this.b0 = r(v, R.string.bottomsheet_action_expand_halfway, 6);
        }
        if (this.H && this.K != 5) {
            F(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
        }
        int i2 = this.K;
        if (i2 == 3) {
            F(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, this.b ? 4 : 6);
        } else if (i2 == 4) {
            F(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, this.b ? 3 : 6);
        } else if (i2 != 6) {
        } else {
            F(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
            F(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
        }
    }

    public final void P(int i) {
        ValueAnimator valueAnimator;
        if (i == 2) {
            return;
        }
        boolean z = i == 3;
        if (this.y != z) {
            this.y = z;
            if (this.i == null || (valueAnimator = this.A) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.A.reverse();
                return;
            }
            float f2 = z ? 0.0f : 1.0f;
            this.A.setFloatValues(1.0f - f2, f2);
            this.A.start();
        }
    }

    public final void Q(boolean z) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.T;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z) {
                if (this.a0 != null) {
                    return;
                }
                this.a0 = new HashMap(childCount);
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.T.get()) {
                    if (z) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.a0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.c) {
                            ViewCompat.setImportantForAccessibility(childAt, 4);
                        }
                    } else if (this.c && (map = this.a0) != null && map.containsKey(childAt)) {
                        ViewCompat.setImportantForAccessibility(childAt, this.a0.get(childAt).intValue());
                    }
                }
            }
            if (!z) {
                this.a0 = null;
            } else if (this.c) {
                this.T.get().sendAccessibilityEvent(8);
            }
        }
    }

    public final void R(boolean z) {
        V v;
        if (this.T != null) {
            s();
            if (this.K != 4 || (v = this.T.get()) == null) {
                return;
            }
            if (z) {
                setState(4);
            } else {
                v.requestLayout();
            }
        }
    }

    public void addBottomSheetCallback(@NonNull BottomSheetCallback bottomSheetCallback) {
        if (this.V.contains(bottomSheetCallback)) {
            return;
        }
        this.V.add(bottomSheetCallback);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public void disableShapeAnimations() {
        this.A = null;
    }

    public int getExpandedOffset() {
        if (this.b) {
            return this.C;
        }
        return Math.max(this.B, this.r ? 0 : this.w);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getHalfExpandedRatio() {
        return this.E;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getLastStableState() {
        return this.L;
    }

    @Px
    public int getMaxHeight() {
        return this.l;
    }

    @Px
    public int getMaxWidth() {
        return this.k;
    }

    public int getPeekHeight() {
        if (this.f) {
            return -1;
        }
        return this.e;
    }

    public int getSaveFlags() {
        return this.f10230a;
    }

    public boolean getSkipCollapsed() {
        return this.I;
    }

    public int getState() {
        return this.K;
    }

    public boolean isDraggable() {
        return this.J;
    }

    public boolean isFitToContents() {
        return this.b;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.n;
    }

    public boolean isHideable() {
        return this.H;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isNestedScrollingCheckEnabled() {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.T = null;
        this.M = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.T = null;
        this.M = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        if (v.isShown() && this.J) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                G();
            }
            if (this.W == null) {
                this.W = VelocityTracker.obtain();
            }
            this.W.addMovement(motionEvent);
            if (actionMasked == 0) {
                int x = (int) motionEvent.getX();
                this.Y = (int) motionEvent.getY();
                if (this.K != 2) {
                    WeakReference<View> weakReference = this.U;
                    View view = weakReference != null ? weakReference.get() : null;
                    if (view != null && coordinatorLayout.isPointInChildBounds(view, x, this.Y)) {
                        this.X = motionEvent.getPointerId(motionEvent.getActionIndex());
                        this.Z = true;
                    }
                }
                this.N = this.X == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.Y);
            } else if (actionMasked == 1 || actionMasked == 3) {
                this.Z = false;
                this.X = -1;
                if (this.N) {
                    this.N = false;
                    return false;
                }
            }
            if (this.N || (viewDragHelper = this.M) == null || !viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
                WeakReference<View> weakReference2 = this.U;
                View view2 = weakReference2 != null ? weakReference2.get() : null;
                return (actionMasked != 2 || view2 == null || this.N || this.K == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.M == null || Math.abs(((float) this.Y) - motionEvent.getY()) <= ((float) this.M.getTouchSlop())) ? false : true;
            }
            return true;
        }
        this.N = true;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.T == null) {
            this.g = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            K(v);
            this.T = new WeakReference<>(v);
            MaterialShapeDrawable materialShapeDrawable = this.i;
            if (materialShapeDrawable != null) {
                ViewCompat.setBackground(v, materialShapeDrawable);
                MaterialShapeDrawable materialShapeDrawable2 = this.i;
                float f2 = this.G;
                if (f2 == -1.0f) {
                    f2 = ViewCompat.getElevation(v);
                }
                materialShapeDrawable2.setElevation(f2);
                boolean z = this.K == 3;
                this.y = z;
                this.i.setInterpolation(z ? 0.0f : 1.0f);
            } else {
                ColorStateList colorStateList = this.j;
                if (colorStateList != null) {
                    ViewCompat.setBackgroundTintList(v, colorStateList);
                }
            }
            O();
            if (ViewCompat.getImportantForAccessibility(v) == 0) {
                ViewCompat.setImportantForAccessibility(v, 1);
            }
        }
        if (this.M == null) {
            this.M = ViewDragHelper.create(coordinatorLayout, this.c0);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.R = coordinatorLayout.getWidth();
        this.S = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.Q = height;
        int i2 = this.S;
        int i3 = i2 - height;
        int i4 = this.w;
        if (i3 < i4) {
            if (this.r) {
                this.Q = i2;
            } else {
                this.Q = i2 - i4;
            }
        }
        this.C = Math.max(0, i2 - this.Q);
        t();
        s();
        int i5 = this.K;
        if (i5 == 3) {
            ViewCompat.offsetTopAndBottom(v, getExpandedOffset());
        } else if (i5 == 6) {
            ViewCompat.offsetTopAndBottom(v, this.D);
        } else if (this.H && i5 == 5) {
            ViewCompat.offsetTopAndBottom(v, this.S);
        } else if (i5 == 4) {
            ViewCompat.offsetTopAndBottom(v, this.F);
        } else if (i5 == 1 || i5 == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        this.U = new WeakReference<>(z(v));
        for (int i6 = 0; i6 < this.V.size(); i6++) {
            this.V.get(i6).a(v);
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        v.measure(A(i, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, this.k, marginLayoutParams.width), A(i3, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, this.l, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, float f2, float f3) {
        WeakReference<View> weakReference;
        if (isNestedScrollingCheckEnabled() && (weakReference = this.U) != null && view == weakReference.get()) {
            return this.K != 3 || super.onNestedPreFling(coordinatorLayout, v, view, f2, f3);
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i, int i2, @NonNull int[] iArr, int i3) {
        if (i3 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.U;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (!isNestedScrollingCheckEnabled() || view == view2) {
            int top = v.getTop();
            int i4 = top - i2;
            if (i2 > 0) {
                if (i4 < getExpandedOffset()) {
                    iArr[1] = top - getExpandedOffset();
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    J(3);
                } else if (!this.J) {
                    return;
                } else {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    J(1);
                }
            } else if (i2 < 0 && !view.canScrollVertically(-1)) {
                int i5 = this.F;
                if (i4 > i5 && !this.H) {
                    iArr[1] = top - i5;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    J(4);
                } else if (!this.J) {
                    return;
                } else {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    J(1);
                }
            }
            y(v.getTop());
            this.O = i2;
            this.P = true;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i, int i2, int i3, int i4, int i5, @NonNull int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        H(savedState);
        int i = savedState.i;
        if (i != 1 && i != 2) {
            this.K = i;
            this.L = i;
            return;
        }
        this.K = 4;
        this.L = 4;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    @NonNull
    public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int i, int i2) {
        this.O = 0;
        this.P = false;
        return (i & 2) != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0036, code lost:
        if (r4.getTop() <= r2.D) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
        if (java.lang.Math.abs(r3 - r2.C) < java.lang.Math.abs(r3 - r2.F)) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007b, code lost:
        if (shouldSkipHalfExpandedStateWhenDragging() != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008b, code lost:
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.F)) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a7, code lost:
        if (java.lang.Math.abs(r3 - r2.D) < java.lang.Math.abs(r3 - r2.F)) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a9, code lost:
        r0 = 6;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onStopNestedScroll(@androidx.annotation.NonNull androidx.coordinatorlayout.widget.CoordinatorLayout r3, @androidx.annotation.NonNull V r4, @androidx.annotation.NonNull android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.getExpandedOffset()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.J(r0)
            return
        Lf:
            boolean r3 = r2.isNestedScrollingCheckEnabled()
            if (r3 == 0) goto L24
            java.lang.ref.WeakReference<android.view.View> r3 = r2.U
            if (r3 == 0) goto L23
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto L23
            boolean r3 = r2.P
            if (r3 != 0) goto L24
        L23:
            return
        L24:
            int r3 = r2.O
            r5 = 6
            r6 = 4
            if (r3 <= 0) goto L3a
            boolean r3 = r2.b
            if (r3 == 0) goto L30
            goto Laa
        L30:
            int r3 = r4.getTop()
            int r6 = r2.D
            if (r3 <= r6) goto Laa
            goto La9
        L3a:
            boolean r3 = r2.H
            if (r3 == 0) goto L4a
            float r3 = r2.D()
            boolean r3 = r2.M(r4, r3)
            if (r3 == 0) goto L4a
            r0 = 5
            goto Laa
        L4a:
            int r3 = r2.O
            if (r3 != 0) goto L8e
            int r3 = r4.getTop()
            boolean r1 = r2.b
            if (r1 == 0) goto L68
            int r5 = r2.C
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.F
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L92
            goto Laa
        L68:
            int r1 = r2.D
            if (r3 >= r1) goto L7e
            int r1 = r2.F
            int r1 = r3 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r3 >= r1) goto L77
            goto Laa
        L77:
            boolean r3 = r2.shouldSkipHalfExpandedStateWhenDragging()
            if (r3 == 0) goto La9
            goto L92
        L7e:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.F
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
            goto La9
        L8e:
            boolean r3 = r2.b
            if (r3 == 0) goto L94
        L92:
            r0 = r6
            goto Laa
        L94:
            int r3 = r4.getTop()
            int r0 = r2.D
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.F
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
        La9:
            r0 = r5
        Laa:
            r3 = 0
            r2.N(r4, r0, r3)
            r2.P = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        if (v.isShown()) {
            int actionMasked = motionEvent.getActionMasked();
            if (this.K == 1 && actionMasked == 0) {
                return true;
            }
            if (L()) {
                this.M.processTouchEvent(motionEvent);
            }
            if (actionMasked == 0) {
                G();
            }
            if (this.W == null) {
                this.W = VelocityTracker.obtain();
            }
            this.W.addMovement(motionEvent);
            if (L() && actionMasked == 2 && !this.N && Math.abs(this.Y - motionEvent.getY()) > this.M.getTouchSlop()) {
                this.M.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
            return !this.N;
        }
        return false;
    }

    public final int r(V v, @StringRes int i, int i2) {
        return ViewCompat.addAccessibilityAction(v, v.getResources().getString(i), v(i2));
    }

    public void removeBottomSheetCallback(@NonNull BottomSheetCallback bottomSheetCallback) {
        this.V.remove(bottomSheetCallback);
    }

    public final void s() {
        int u = u();
        if (this.b) {
            this.F = Math.max(this.S - u, this.C);
        } else {
            this.F = this.S - u;
        }
    }

    @Deprecated
    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        Log.w("BottomSheetBehavior", "BottomSheetBehavior now supports multiple callbacks. `setBottomSheetCallback()` removes all existing callbacks, including ones set internally by library authors, which may result in unintended behavior. This may change in the future. Please use `addBottomSheetCallback()` and `removeBottomSheetCallback()` instead to set your own callbacks.");
        this.V.clear();
        if (bottomSheetCallback != null) {
            this.V.add(bottomSheetCallback);
        }
    }

    public void setDraggable(boolean z) {
        this.J = z;
    }

    public void setExpandedOffset(int i) {
        if (i >= 0) {
            this.B = i;
            return;
        }
        throw new IllegalArgumentException("offset must be greater than or equal to 0");
    }

    public void setFitToContents(boolean z) {
        if (this.b == z) {
            return;
        }
        this.b = z;
        if (this.T != null) {
            s();
        }
        J((this.b && this.K == 6) ? 3 : this.K);
        O();
    }

    public void setGestureInsetBottomIgnored(boolean z) {
        this.n = z;
    }

    public void setHalfExpandedRatio(@FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d, toInclusive = false) float f2) {
        if (f2 > 0.0f && f2 < 1.0f) {
            this.E = f2;
            if (this.T != null) {
                t();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
    }

    public void setHideable(boolean z) {
        if (this.H != z) {
            this.H = z;
            if (!z && this.K == 5) {
                setState(4);
            }
            O();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setHideableInternal(boolean z) {
        this.H = z;
    }

    public void setMaxHeight(@Px int i) {
        this.l = i;
    }

    public void setMaxWidth(@Px int i) {
        this.k = i;
    }

    public void setPeekHeight(int i) {
        setPeekHeight(i, false);
    }

    public void setSaveFlags(int i) {
        this.f10230a = i;
    }

    public void setSkipCollapsed(boolean z) {
        this.I = z;
    }

    public void setState(int i) {
        if (i != 1 && i != 2) {
            if (!this.H && i == 5) {
                Log.w("BottomSheetBehavior", "Cannot set state: " + i);
                return;
            }
            int i2 = (i == 6 && this.b && C(i) <= this.C) ? 3 : i;
            WeakReference<V> weakReference = this.T;
            if (weakReference != null && weakReference.get() != null) {
                V v = this.T.get();
                I(v, new a(v, i2));
                return;
            }
            J(i);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("STATE_");
        sb.append(i == 1 ? "DRAGGING" : "SETTLING");
        sb.append(" should not be set externally.");
        throw new IllegalArgumentException(sb.toString());
    }

    public void setUpdateImportantForAccessibilityOnSiblings(boolean z) {
        this.c = z;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean shouldExpandOnUpwardDrag(long j, @FloatRange(from = 0.0d, to = 100.0d) float f2) {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean shouldSkipHalfExpandedStateWhenDragging() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean shouldSkipSmoothAnimation() {
        return true;
    }

    public final void t() {
        this.D = (int) (this.S * (1.0f - this.E));
    }

    public final int u() {
        int i;
        if (this.f) {
            return Math.min(Math.max(this.g, this.S - ((this.R * 9) / 16)), this.Q) + this.v;
        }
        if (!this.n && !this.o && (i = this.m) > 0) {
            return Math.max(this.e, i + this.h);
        }
        return this.e + this.v;
    }

    public final AccessibilityViewCommand v(int i) {
        return new e(i);
    }

    public final void w(@NonNull Context context) {
        if (this.x == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.x);
        this.i = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(context);
        ColorStateList colorStateList = this.j;
        if (colorStateList != null) {
            this.i.setFillColor(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842801, typedValue, true);
        this.i.setTint(typedValue.data);
    }

    public final void x() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.A = ofFloat;
        ofFloat.setDuration(500L);
        this.A.addUpdateListener(new b());
    }

    public void y(int i) {
        float f2;
        float f3;
        V v = this.T.get();
        if (v == null || this.V.isEmpty()) {
            return;
        }
        int i2 = this.F;
        if (i <= i2 && i2 != getExpandedOffset()) {
            int i3 = this.F;
            f2 = i3 - i;
            f3 = i3 - getExpandedOffset();
        } else {
            int i4 = this.F;
            f2 = i4 - i;
            f3 = this.S - i4;
        }
        float f4 = f2 / f3;
        for (int i5 = 0; i5 < this.V.size(); i5++) {
            this.V.get(i5).onSlide(v, f4);
        }
    }

    @Nullable
    @VisibleForTesting
    public View z(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View z = z(viewGroup.getChildAt(i));
                if (z != null) {
                    return z;
                }
            }
            return null;
        }
        return null;
    }

    /* loaded from: classes10.dex */
    public class f {

        /* renamed from: a  reason: collision with root package name */
        public int f10234a;
        public boolean b;
        public final Runnable c;

        /* loaded from: classes10.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.b = false;
                ViewDragHelper viewDragHelper = BottomSheetBehavior.this.M;
                if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                    f fVar = f.this;
                    fVar.c(fVar.f10234a);
                    return;
                }
                f fVar2 = f.this;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.K == 2) {
                    bottomSheetBehavior.J(fVar2.f10234a);
                }
            }
        }

        public f() {
            this.c = new a();
        }

        public void c(int i) {
            WeakReference<V> weakReference = BottomSheetBehavior.this.T;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f10234a = i;
            if (this.b) {
                return;
            }
            ViewCompat.postOnAnimation(BottomSheetBehavior.this.T.get(), this.c);
            this.b = true;
        }

        public /* synthetic */ f(BottomSheetBehavior bottomSheetBehavior, a aVar) {
            this();
        }
    }

    public final void setPeekHeight(int i, boolean z) {
        boolean z2 = true;
        if (i == -1) {
            if (!this.f) {
                this.f = true;
            }
            z2 = false;
        } else {
            if (this.f || this.e != i) {
                this.f = false;
                this.e = Math.max(0, i);
            }
            z2 = false;
        }
        if (z2) {
            R(z);
        }
    }

    public BottomSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        this.f10230a = 0;
        this.b = true;
        this.c = false;
        this.k = -1;
        this.l = -1;
        this.z = new f(this, null);
        this.E = 0.5f;
        this.G = -1.0f;
        this.J = true;
        this.K = 4;
        this.L = 4;
        this.V = new ArrayList<>();
        this.b0 = -1;
        this.c0 = new d();
        this.h = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        int i2 = R.styleable.BottomSheetBehavior_Layout_backgroundTint;
        if (obtainStyledAttributes.hasValue(i2)) {
            this.j = MaterialResources.getColorStateList(context, obtainStyledAttributes, i2);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.x = ShapeAppearanceModel.builder(context, attributeSet, R.attr.bottomSheetStyle, d0).build();
        }
        w(context);
        x();
        if (Build.VERSION.SDK_INT >= 21) {
            this.G = obtainStyledAttributes.getDimension(R.styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        int i3 = R.styleable.BottomSheetBehavior_Layout_android_maxWidth;
        if (obtainStyledAttributes.hasValue(i3)) {
            setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(i3, -1));
        }
        int i4 = R.styleable.BottomSheetBehavior_Layout_android_maxHeight;
        if (obtainStyledAttributes.hasValue(i4)) {
            setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(i4, -1));
        }
        int i5 = R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue peekValue = obtainStyledAttributes.peekValue(i5);
        if (peekValue != null && (i = peekValue.data) == -1) {
            setPeekHeight(i);
        } else {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(i5, -1));
        }
        setHideable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setGestureInsetBottomIgnored(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        setFitToContents(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        setDraggable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        setSaveFlags(obtainStyledAttributes.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        setHalfExpandedRatio(obtainStyledAttributes.getFloat(R.styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i6 = R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(i6);
        if (peekValue2 != null && peekValue2.type == 16) {
            setExpandedOffset(peekValue2.data);
        } else {
            setExpandedOffset(obtainStyledAttributes.getDimensionPixelOffset(i6, 0));
        }
        this.o = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.p = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.q = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.r = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.s = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.t = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.u = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        obtainStyledAttributes.recycle();
        this.d = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
