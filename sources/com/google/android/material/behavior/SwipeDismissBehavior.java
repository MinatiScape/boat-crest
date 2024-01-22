package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;
/* loaded from: classes10.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;

    /* renamed from: a  reason: collision with root package name */
    public ViewDragHelper f10222a;
    public OnDismissListener b;
    public boolean c;
    public boolean e;
    public float d = 0.0f;
    public int f = 2;
    public float g = 0.5f;
    public float h = 0.0f;
    public float i = 0.5f;
    public final ViewDragHelper.Callback j = new a();

    /* loaded from: classes10.dex */
    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i);
    }

    /* loaded from: classes10.dex */
    public class a extends ViewDragHelper.Callback {

        /* renamed from: a  reason: collision with root package name */
        public int f10223a;
        public int b = -1;

        public a() {
        }

        public final boolean a(@NonNull View view, float f) {
            int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
            if (i == 0) {
                return Math.abs(view.getLeft() - this.f10223a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.g);
            }
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            int i2 = SwipeDismissBehavior.this.f;
            if (i2 == 2) {
                return true;
            }
            if (i2 == 0) {
                if (z) {
                    if (f >= 0.0f) {
                        return false;
                    }
                } else if (i <= 0) {
                    return false;
                }
                return true;
            } else if (i2 == 1) {
                if (z) {
                    if (i <= 0) {
                        return false;
                    }
                } else if (f >= 0.0f) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(@NonNull View view, int i, int i2) {
            int width;
            int width2;
            int width3;
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            int i3 = SwipeDismissBehavior.this.f;
            if (i3 == 0) {
                if (z) {
                    width = this.f10223a - view.getWidth();
                    width2 = this.f10223a;
                } else {
                    width = this.f10223a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i3 != 1) {
                width = this.f10223a - view.getWidth();
                width2 = view.getWidth() + this.f10223a;
            } else if (z) {
                width = this.f10223a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f10223a - view.getWidth();
                width2 = this.f10223a;
            }
            return SwipeDismissBehavior.b(width, i, width2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(@NonNull View view, int i, int i2) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(@NonNull View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(@NonNull View view, int i) {
            this.b = i;
            this.f10223a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.b;
            if (onDismissListener != null) {
                onDismissListener.onDragStateChanged(i);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(@NonNull View view, int i, int i2, int i3, int i4) {
            float width = this.f10223a + (view.getWidth() * SwipeDismissBehavior.this.h);
            float width2 = this.f10223a + (view.getWidth() * SwipeDismissBehavior.this.i);
            float f = i;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.a(0.0f, 1.0f - SwipeDismissBehavior.d(width, width2, f), 1.0f));
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(@NonNull View view, float f, float f2) {
            int i;
            boolean z;
            OnDismissListener onDismissListener;
            this.b = -1;
            int width = view.getWidth();
            if (a(view, f)) {
                int left = view.getLeft();
                int i2 = this.f10223a;
                i = left < i2 ? i2 - width : i2 + width;
                z = true;
            } else {
                i = this.f10223a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f10222a.settleCapturedViewAt(i, view.getTop())) {
                ViewCompat.postOnAnimation(view, new c(view, z));
            } else if (!z || (onDismissListener = SwipeDismissBehavior.this.b) == null) {
            } else {
                onDismissListener.onDismiss(view);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            int i2 = this.b;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements AccessibilityViewCommand {
        public b() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
            boolean z = false;
            if (SwipeDismissBehavior.this.canSwipeDismissView(view)) {
                boolean z2 = ViewCompat.getLayoutDirection(view) == 1;
                int i = SwipeDismissBehavior.this.f;
                if ((i == 0 && z2) || (i == 1 && !z2)) {
                    z = true;
                }
                int width = view.getWidth();
                if (z) {
                    width = -width;
                }
                ViewCompat.offsetLeftAndRight(view, width);
                view.setAlpha(0.0f);
                OnDismissListener onDismissListener = SwipeDismissBehavior.this.b;
                if (onDismissListener != null) {
                    onDismissListener.onDismiss(view);
                }
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Runnable {
        public final View h;
        public final boolean i;

        public c(View view, boolean z) {
            this.h = view;
            this.i = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            OnDismissListener onDismissListener;
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.f10222a;
            if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.h, this);
            } else if (!this.i || (onDismissListener = SwipeDismissBehavior.this.b) == null) {
            } else {
                onDismissListener.onDismiss(this.h);
            }
        }
    }

    public static float a(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    public static int b(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public static float d(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public final void c(ViewGroup viewGroup) {
        ViewDragHelper create;
        if (this.f10222a == null) {
            if (this.e) {
                create = ViewDragHelper.create(viewGroup, this.d, this.j);
            } else {
                create = ViewDragHelper.create(viewGroup, this.j);
            }
            this.f10222a = create;
        }
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    public final void e(View view) {
        ViewCompat.removeAccessibilityAction(view, 1048576);
        if (canSwipeDismissView(view)) {
            ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, null, new b());
        }
    }

    public int getDragState() {
        ViewDragHelper viewDragHelper = this.f10222a;
        if (viewDragHelper != null) {
            return viewDragHelper.getViewDragState();
        }
        return 0;
    }

    @Nullable
    @VisibleForTesting
    public OnDismissListener getListener() {
        return this.b;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        boolean z = this.c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.c = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.c = false;
        }
        if (z) {
            c(coordinatorLayout);
            return this.f10222a.shouldInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i) {
        boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, v, i);
        if (ViewCompat.getImportantForAccessibility(v) == 0) {
            ViewCompat.setImportantForAccessibility(v, 1);
            e(v);
        }
        return onLayoutChild;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper = this.f10222a;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(motionEvent);
            return true;
        }
        return false;
    }

    public void setDragDismissDistance(float f) {
        this.g = a(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.i = a(0.0f, f, 1.0f);
    }

    public void setListener(@Nullable OnDismissListener onDismissListener) {
        this.b = onDismissListener;
    }

    public void setSensitivity(float f) {
        this.d = f;
        this.e = true;
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.h = a(0.0f, f, 1.0f);
    }

    public void setSwipeDirection(int i) {
        this.f = i;
    }
}
