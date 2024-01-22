package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.a;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueueKt;
/* loaded from: classes10.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    public static final int ANIMATION_MODE_FADE = 1;
    public static final int ANIMATION_MODE_SLIDE = 0;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    @NonNull
    public static final Handler s;
    public static final boolean t;
    public static final int[] u;
    public static final String v;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f10368a;
    public final Context b;
    @NonNull
    public final com.google.android.material.snackbar.ContentViewCallback c;
    public int d;
    public boolean e;
    @Nullable
    public q f;
    public boolean g;
    @RequiresApi(29)
    public final Runnable h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public List<BaseCallback<B>> o;
    public Behavior p;
    @Nullable
    public final AccessibilityManager q;
    @NonNull
    public a.b r;
    @NonNull
    public final SnackbarBaseLayout view;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface AnimationMode {
    }

    /* loaded from: classes10.dex */
    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes10.dex */
        public @interface DismissEvent {
        }

        public void onDismissed(B b, int i) {
        }

        public void onShown(B b) {
        }
    }

    /* loaded from: classes10.dex */
    public static class Behavior extends SwipeDismissBehavior<View> {
        @NonNull
        public final BehaviorDelegate k = new BehaviorDelegate(this);

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View view) {
            return this.k.canSwipeDismissView(view);
        }

        public final void g(@NonNull BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.k.setBaseTransientBottomBar(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull MotionEvent motionEvent) {
            this.k.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public static class BehaviorDelegate {

        /* renamed from: a  reason: collision with root package name */
        public a.b f10369a;

        public BehaviorDelegate(@NonNull SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.setStartAlphaSwipeDistance(0.1f);
            swipeDismissBehavior.setEndAlphaSwipeDistance(0.6f);
            swipeDismissBehavior.setSwipeDirection(0);
        }

        public boolean canSwipeDismissView(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    com.google.android.material.snackbar.a.c().k(this.f10369a);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                com.google.android.material.snackbar.a.c().l(this.f10369a);
            }
        }

        public void setBaseTransientBottomBar(@NonNull BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.f10369a = baseTransientBottomBar.r;
        }
    }

    @Deprecated
    /* loaded from: classes10.dex */
    public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
    }

    @IntRange(from = WorkQueueKt.NOTHING_TO_STEAL)
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface Duration {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public static class SnackbarBaseLayout extends FrameLayout {
        public static final View.OnTouchListener r = new a();
        @Nullable
        public BaseTransientBottomBar<?> h;
        public int i;
        public final float j;
        public final float k;
        public final int l;
        public final int m;
        public ColorStateList n;
        public PorterDuff.Mode o;
        @Nullable
        public Rect p;
        public boolean q;

        /* loaded from: classes10.dex */
        public class a implements View.OnTouchListener {
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        public SnackbarBaseLayout(@NonNull Context context) {
            this(context, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.h = baseTransientBottomBar;
        }

        public void c(ViewGroup viewGroup) {
            this.q = true;
            viewGroup.addView(this);
            this.q = false;
        }

        @NonNull
        public final Drawable d() {
            float dimension = getResources().getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(MaterialColors.layer(this, R.attr.colorSurface, R.attr.colorOnSurface, getBackgroundOverlayColorAlpha()));
            if (this.n != null) {
                Drawable wrap = DrawableCompat.wrap(gradientDrawable);
                DrawableCompat.setTintList(wrap, this.n);
                return wrap;
            }
            return DrawableCompat.wrap(gradientDrawable);
        }

        public final void e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.p = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }

        public float getActionTextColorAlpha() {
            return this.k;
        }

        public int getAnimationMode() {
            return this.i;
        }

        public float getBackgroundOverlayColorAlpha() {
            return this.j;
        }

        public int getMaxInlineActionWidth() {
            return this.m;
        }

        public int getMaxWidth() {
            return this.l;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.h;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.z();
            }
            ViewCompat.requestApplyInsets(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.h;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.A();
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.h;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.B();
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.l > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = this.l;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
                }
            }
        }

        public void setAnimationMode(int i) {
            this.i = i;
        }

        @Override // android.view.View
        public void setBackground(@Nullable Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(@Nullable Drawable drawable) {
            if (drawable != null && this.n != null) {
                drawable = DrawableCompat.wrap(drawable.mutate());
                DrawableCompat.setTintList(drawable, this.n);
                DrawableCompat.setTintMode(drawable, this.o);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
            this.n = colorStateList;
            if (getBackground() != null) {
                Drawable wrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintList(wrap, colorStateList);
                DrawableCompat.setTintMode(wrap, this.o);
                if (wrap != getBackground()) {
                    super.setBackgroundDrawable(wrap);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
            this.o = mode;
            if (getBackground() != null) {
                Drawable wrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintMode(wrap, mode);
                if (wrap != getBackground()) {
                    super.setBackgroundDrawable(wrap);
                }
            }
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (this.q || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                return;
            }
            e((ViewGroup.MarginLayoutParams) layoutParams);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.h;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.O();
            }
        }

        @Override // android.view.View
        public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : r);
            super.setOnClickListener(onClickListener);
        }

        public SnackbarBaseLayout(@NonNull Context context, AttributeSet attributeSet) {
            super(MaterialThemeOverlay.wrap(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            int i = R.styleable.SnackbarLayout_elevation;
            if (obtainStyledAttributes.hasValue(i)) {
                ViewCompat.setElevation(this, obtainStyledAttributes.getDimensionPixelSize(i, 0));
            }
            this.i = obtainStyledAttributes.getInt(R.styleable.SnackbarLayout_animationMode, 0);
            this.j = obtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(MaterialResources.getColorStateList(context2, obtainStyledAttributes, R.styleable.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(ViewUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.k = obtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            this.l = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
            this.m = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
            obtainStyledAttributes.recycle();
            setOnTouchListener(r);
            setFocusable(true);
            if (getBackground() == null) {
                ViewCompat.setBackground(this, d());
            }
        }
    }

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ int h;

        public a(int i) {
            this.h = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.C(this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            BaseTransientBottomBar.this.view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* loaded from: classes10.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        public c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            BaseTransientBottomBar.this.view.setScaleX(floatValue);
            BaseTransientBottomBar.this.view.setScaleY(floatValue);
        }
    }

    /* loaded from: classes10.dex */
    public class d extends AnimatorListenerAdapter {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.D();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.c.animateContentIn(70, 180);
        }
    }

    /* loaded from: classes10.dex */
    public class e implements ValueAnimator.AnimatorUpdateListener {
        public int h;
        public final /* synthetic */ int i;

        public e(int i) {
            this.i = i;
            this.h = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.t) {
                ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, intValue - this.h);
            } else {
                BaseTransientBottomBar.this.view.setTranslationY(intValue);
            }
            this.h = intValue;
        }
    }

    /* loaded from: classes10.dex */
    public class f extends AnimatorListenerAdapter {
        public final /* synthetic */ int h;

        public f(int i) {
            this.h = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.C(this.h);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.c.animateContentOut(0, 180);
        }
    }

    /* loaded from: classes10.dex */
    public class g implements ValueAnimator.AnimatorUpdateListener {
        public int h = 0;

        public g() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.t) {
                ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, intValue - this.h);
            } else {
                BaseTransientBottomBar.this.view.setTranslationY(intValue);
            }
            this.h = intValue;
        }
    }

    /* loaded from: classes10.dex */
    public class h implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i == 0) {
                ((BaseTransientBottomBar) message.obj).I();
                return true;
            } else if (i != 1) {
                return false;
            } else {
                ((BaseTransientBottomBar) message.obj).x(message.arg1);
                return true;
            }
        }
    }

    /* loaded from: classes10.dex */
    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int u;
            BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
            if (baseTransientBottomBar.view == null || baseTransientBottomBar.b == null || (u = (BaseTransientBottomBar.this.u() - BaseTransientBottomBar.this.w()) + ((int) BaseTransientBottomBar.this.view.getTranslationY())) >= BaseTransientBottomBar.this.l) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = BaseTransientBottomBar.this.view.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                Log.w(BaseTransientBottomBar.v, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                return;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += BaseTransientBottomBar.this.l - u;
            BaseTransientBottomBar.this.view.requestLayout();
        }
    }

    /* loaded from: classes10.dex */
    public class j implements OnApplyWindowInsetsListener {
        public j() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        @NonNull
        public WindowInsetsCompat onApplyWindowInsets(View view, @NonNull WindowInsetsCompat windowInsetsCompat) {
            BaseTransientBottomBar.this.i = windowInsetsCompat.getSystemWindowInsetBottom();
            BaseTransientBottomBar.this.j = windowInsetsCompat.getSystemWindowInsetLeft();
            BaseTransientBottomBar.this.k = windowInsetsCompat.getSystemWindowInsetRight();
            BaseTransientBottomBar.this.O();
            return windowInsetsCompat;
        }
    }

    /* loaded from: classes10.dex */
    public class k extends AccessibilityDelegateCompat {
        public k() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.addAction(1048576);
            accessibilityNodeInfoCompat.setDismissable(true);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i == 1048576) {
                BaseTransientBottomBar.this.dismiss();
                return true;
            }
            return super.performAccessibilityAction(view, i, bundle);
        }
    }

    /* loaded from: classes10.dex */
    public class l implements a.b {
        public l() {
        }

        @Override // com.google.android.material.snackbar.a.b
        public void a(int i) {
            Handler handler = BaseTransientBottomBar.s;
            handler.sendMessage(handler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.a.b
        public void show() {
            Handler handler = BaseTransientBottomBar.s;
            handler.sendMessage(handler.obtainMessage(0, BaseTransientBottomBar.this));
        }
    }

    /* loaded from: classes10.dex */
    public class m implements Runnable {
        public m() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseTransientBottomBar.this.C(3);
        }
    }

    /* loaded from: classes10.dex */
    public class n implements SwipeDismissBehavior.OnDismissListener {
        public n() {
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
        public void onDismiss(@NonNull View view) {
            if (view.getParent() != null) {
                view.setVisibility(8);
            }
            BaseTransientBottomBar.this.dispatchDismiss(0);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
        public void onDragStateChanged(int i) {
            if (i == 0) {
                com.google.android.material.snackbar.a.c().l(BaseTransientBottomBar.this.r);
            } else if (i == 1 || i == 2) {
                com.google.android.material.snackbar.a.c().k(BaseTransientBottomBar.this.r);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class o implements Runnable {
        public o() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SnackbarBaseLayout snackbarBaseLayout = BaseTransientBottomBar.this.view;
            if (snackbarBaseLayout == null) {
                return;
            }
            if (snackbarBaseLayout.getParent() != null) {
                BaseTransientBottomBar.this.view.setVisibility(0);
            }
            if (BaseTransientBottomBar.this.view.getAnimationMode() == 1) {
                BaseTransientBottomBar.this.K();
            } else {
                BaseTransientBottomBar.this.M();
            }
        }
    }

    /* loaded from: classes10.dex */
    public class p extends AnimatorListenerAdapter {
        public p() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.D();
        }
    }

    /* loaded from: classes10.dex */
    public static class q implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
        @NonNull
        public final WeakReference<BaseTransientBottomBar> h;
        @NonNull
        public final WeakReference<View> i;

        public q(@NonNull BaseTransientBottomBar baseTransientBottomBar, @NonNull View view) {
            this.h = new WeakReference<>(baseTransientBottomBar);
            this.i = new WeakReference<>(view);
        }

        public static q a(@NonNull BaseTransientBottomBar baseTransientBottomBar, @NonNull View view) {
            q qVar = new q(baseTransientBottomBar, view);
            if (ViewCompat.isAttachedToWindow(view)) {
                ViewUtils.addOnGlobalLayoutListener(view, qVar);
            }
            view.addOnAttachStateChangeListener(qVar);
            return qVar;
        }

        @Nullable
        public View b() {
            return this.i.get();
        }

        public void c() {
            if (this.i.get() != null) {
                this.i.get().removeOnAttachStateChangeListener(this);
                ViewUtils.removeOnGlobalLayoutListener(this.i.get(), this);
            }
            this.i.clear();
            this.h.clear();
        }

        public final boolean d() {
            if (this.h.get() == null) {
                c();
                return true;
            }
            return false;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (d() || !this.h.get().g) {
                return;
            }
            this.h.get().E();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (d()) {
                return;
            }
            ViewUtils.addOnGlobalLayoutListener(view, this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (d()) {
                return;
            }
            ViewUtils.removeOnGlobalLayoutListener(view, this);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        t = i2 >= 16 && i2 <= 19;
        u = new int[]{R.attr.snackbarStyle};
        v = BaseTransientBottomBar.class.getSimpleName();
        s = new Handler(Looper.getMainLooper(), new h());
    }

    public BaseTransientBottomBar(@NonNull ViewGroup viewGroup, @NonNull View view, @NonNull com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        this(viewGroup.getContext(), viewGroup, view, contentViewCallback);
    }

    public void A() {
        if (isShownOrQueued()) {
            s.post(new m());
        }
    }

    public void B() {
        if (this.n) {
            J();
            this.n = false;
        }
    }

    public void C(int i2) {
        com.google.android.material.snackbar.a.c().i(this.r);
        List<BaseCallback<B>> list = this.o;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.o.get(size).onDismissed(this, i2);
            }
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    public void D() {
        com.google.android.material.snackbar.a.c().j(this.r);
        List<BaseCallback<B>> list = this.o;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.o.get(size).onShown(this);
            }
        }
    }

    public final void E() {
        this.m = r();
        O();
    }

    public final void F(CoordinatorLayout.LayoutParams layoutParams) {
        SwipeDismissBehavior<? extends View> swipeDismissBehavior = this.p;
        if (swipeDismissBehavior == null) {
            swipeDismissBehavior = getNewBehavior();
        }
        if (swipeDismissBehavior instanceof Behavior) {
            ((Behavior) swipeDismissBehavior).g(this);
        }
        swipeDismissBehavior.setListener(new n());
        layoutParams.setBehavior(swipeDismissBehavior);
        if (getAnchorView() == null) {
            layoutParams.insetEdge = 80;
        }
    }

    public boolean G() {
        AccessibilityManager accessibilityManager = this.q;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    public final boolean H() {
        return this.l > 0 && !this.e && y();
    }

    public final void I() {
        if (this.view.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                F((CoordinatorLayout.LayoutParams) layoutParams);
            }
            this.view.c(this.f10368a);
            E();
            this.view.setVisibility(4);
        }
        if (ViewCompat.isLaidOut(this.view)) {
            J();
        } else {
            this.n = true;
        }
    }

    public final void J() {
        if (G()) {
            p();
            return;
        }
        if (this.view.getParent() != null) {
            this.view.setVisibility(0);
        }
        D();
    }

    public final void K() {
        ValueAnimator s2 = s(0.0f, 1.0f);
        ValueAnimator t2 = t(0.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(s2, t2);
        animatorSet.setDuration(150L);
        animatorSet.addListener(new p());
        animatorSet.start();
    }

    public final void L(int i2) {
        ValueAnimator s2 = s(1.0f, 0.0f);
        s2.setDuration(75L);
        s2.addListener(new a(i2));
        s2.start();
    }

    public final void M() {
        int v2 = v();
        if (t) {
            ViewCompat.offsetTopAndBottom(this.view, v2);
        } else {
            this.view.setTranslationY(v2);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(v2, 0);
        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new d());
        valueAnimator.addUpdateListener(new e(v2));
        valueAnimator.start();
    }

    public final void N(int i2) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, v());
        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new f(i2));
        valueAnimator.addUpdateListener(new g());
        valueAnimator.start();
    }

    public final void O() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if ((layoutParams instanceof ViewGroup.MarginLayoutParams) && this.view.p != null) {
            if (this.view.getParent() == null) {
                return;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.bottomMargin = this.view.p.bottom + (getAnchorView() != null ? this.m : this.i);
            marginLayoutParams.leftMargin = this.view.p.left + this.j;
            marginLayoutParams.rightMargin = this.view.p.right + this.k;
            marginLayoutParams.topMargin = this.view.p.top;
            this.view.requestLayout();
            if (Build.VERSION.SDK_INT < 29 || !H()) {
                return;
            }
            this.view.removeCallbacks(this.h);
            this.view.post(this.h);
            return;
        }
        Log.w(v, "Unable to update margins because layout params are not MarginLayoutParams");
    }

    @NonNull
    public B addCallback(@Nullable BaseCallback<B> baseCallback) {
        if (baseCallback == null) {
            return this;
        }
        if (this.o == null) {
            this.o = new ArrayList();
        }
        this.o.add(baseCallback);
        return this;
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    public void dispatchDismiss(int i2) {
        com.google.android.material.snackbar.a.c().b(this.r, i2);
    }

    @Nullable
    public View getAnchorView() {
        q qVar = this.f;
        if (qVar == null) {
            return null;
        }
        return qVar.b();
    }

    public int getAnimationMode() {
        return this.view.getAnimationMode();
    }

    public Behavior getBehavior() {
        return this.p;
    }

    @NonNull
    public Context getContext() {
        return this.b;
    }

    public int getDuration() {
        return this.d;
    }

    @NonNull
    public SwipeDismissBehavior<? extends View> getNewBehavior() {
        return new Behavior();
    }

    @LayoutRes
    public int getSnackbarBaseLayoutResId() {
        return hasSnackbarStyleAttr() ? R.layout.mtrl_layout_snackbar : R.layout.design_layout_snackbar;
    }

    @NonNull
    public View getView() {
        return this.view;
    }

    public boolean hasSnackbarStyleAttr() {
        TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(u);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    public boolean isAnchorViewLayoutListenerEnabled() {
        return this.g;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.e;
    }

    public boolean isShown() {
        return com.google.android.material.snackbar.a.c().e(this.r);
    }

    public boolean isShownOrQueued() {
        return com.google.android.material.snackbar.a.c().f(this.r);
    }

    public void p() {
        this.view.post(new o());
    }

    public final void q(int i2) {
        if (this.view.getAnimationMode() == 1) {
            L(i2);
        } else {
            N(i2);
        }
    }

    public final int r() {
        if (getAnchorView() == null) {
            return 0;
        }
        int[] iArr = new int[2];
        getAnchorView().getLocationOnScreen(iArr);
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        this.f10368a.getLocationOnScreen(iArr2);
        return (iArr2[1] + this.f10368a.getHeight()) - i2;
    }

    @NonNull
    public B removeCallback(@Nullable BaseCallback<B> baseCallback) {
        List<BaseCallback<B>> list;
        if (baseCallback == null || (list = this.o) == null) {
            return this;
        }
        list.remove(baseCallback);
        return this;
    }

    public final ValueAnimator s(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ofFloat.addUpdateListener(new b());
        return ofFloat;
    }

    @NonNull
    public B setAnchorView(@Nullable View view) {
        q qVar = this.f;
        if (qVar != null) {
            qVar.c();
        }
        this.f = view == null ? null : q.a(this, view);
        return this;
    }

    public void setAnchorViewLayoutListenerEnabled(boolean z) {
        this.g = z;
    }

    @NonNull
    public B setAnimationMode(int i2) {
        this.view.setAnimationMode(i2);
        return this;
    }

    @NonNull
    public B setBehavior(Behavior behavior) {
        this.p = behavior;
        return this;
    }

    @NonNull
    public B setDuration(int i2) {
        this.d = i2;
        return this;
    }

    @NonNull
    public B setGestureInsetBottomIgnored(boolean z) {
        this.e = z;
        return this;
    }

    public void show() {
        com.google.android.material.snackbar.a.c().n(getDuration(), this.r);
    }

    public final ValueAnimator t(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        ofFloat.addUpdateListener(new c());
        return ofFloat;
    }

    @RequiresApi(17)
    public final int u() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public final int v() {
        int height = this.view.getHeight();
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    public final int w() {
        int[] iArr = new int[2];
        this.view.getLocationOnScreen(iArr);
        return iArr[1] + this.view.getHeight();
    }

    public final void x(int i2) {
        if (G() && this.view.getVisibility() == 0) {
            q(i2);
        } else {
            C(i2);
        }
    }

    public final boolean y() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return (layoutParams instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof SwipeDismissBehavior);
    }

    public void z() {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 29 || (rootWindowInsets = this.view.getRootWindowInsets()) == null) {
            return;
        }
        this.l = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
        O();
    }

    public BaseTransientBottomBar(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull View view, @NonNull com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        this.g = false;
        this.h = new i();
        this.r = new l();
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (contentViewCallback != null) {
            this.f10368a = viewGroup;
            this.c = contentViewCallback;
            this.b = context;
            ThemeEnforcement.checkAppCompatTheme(context);
            SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) LayoutInflater.from(context).inflate(getSnackbarBaseLayoutResId(), viewGroup, false);
            this.view = snackbarBaseLayout;
            snackbarBaseLayout.setBaseTransientBottomBar(this);
            if (view instanceof SnackbarContentLayout) {
                SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) view;
                snackbarContentLayout.a(snackbarBaseLayout.getActionTextColorAlpha());
                snackbarContentLayout.setMaxInlineActionWidth(snackbarBaseLayout.getMaxInlineActionWidth());
            }
            snackbarBaseLayout.addView(view);
            ViewCompat.setAccessibilityLiveRegion(snackbarBaseLayout, 1);
            ViewCompat.setImportantForAccessibility(snackbarBaseLayout, 1);
            ViewCompat.setFitsSystemWindows(snackbarBaseLayout, true);
            ViewCompat.setOnApplyWindowInsetsListener(snackbarBaseLayout, new j());
            ViewCompat.setAccessibilityDelegate(snackbarBaseLayout, new k());
            this.q = (AccessibilityManager) context.getSystemService("accessibility");
            return;
        }
        throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
    }

    @NonNull
    public B setAnchorView(@IdRes int i2) {
        View findViewById = this.f10368a.findViewById(i2);
        if (findViewById != null) {
            return setAnchorView(findViewById);
        }
        throw new IllegalArgumentException("Unable to find anchor view with id: " + i2);
    }
}
