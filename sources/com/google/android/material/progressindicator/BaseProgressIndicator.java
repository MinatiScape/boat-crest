package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.ProgressBar;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
/* loaded from: classes10.dex */
public abstract class BaseProgressIndicator<S extends BaseProgressIndicatorSpec> extends ProgressBar {
    public static final int HIDE_INWARD = 2;
    public static final int HIDE_NONE = 0;
    public static final int HIDE_OUTWARD = 1;
    public static final int SHOW_INWARD = 2;
    public static final int SHOW_NONE = 0;
    public static final int SHOW_OUTWARD = 1;
    public static final int v = R.style.Widget_MaterialComponents_ProgressIndicator;
    public S h;
    public int i;
    public boolean j;
    public boolean k;
    public final int l;
    public final int m;
    public long n;
    public AnimatorDurationScaleProvider o;
    public boolean p;
    public int q;
    public final Runnable r;
    public final Runnable s;
    public final Animatable2Compat.AnimationCallback t;
    public final Animatable2Compat.AnimationCallback u;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface HideAnimationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface ShowAnimationBehavior {
    }

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator.this.j();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator.this.i();
            BaseProgressIndicator.this.n = -1L;
        }
    }

    /* loaded from: classes10.dex */
    public class c extends Animatable2Compat.AnimationCallback {
        public c() {
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            BaseProgressIndicator.this.setIndeterminate(false);
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            baseProgressIndicator.setProgressCompat(baseProgressIndicator.i, BaseProgressIndicator.this.j);
        }
    }

    /* loaded from: classes10.dex */
    public class d extends Animatable2Compat.AnimationCallback {
        public d() {
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (BaseProgressIndicator.this.p) {
                return;
            }
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            baseProgressIndicator.setVisibility(baseProgressIndicator.q);
        }
    }

    public BaseProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, v), attributeSet, i);
        this.n = -1L;
        this.p = false;
        this.q = 4;
        this.r = new a();
        this.s = new b();
        this.t = new c();
        this.u = new d();
        Context context2 = getContext();
        this.h = h(context2, attributeSet);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R.styleable.BaseProgressIndicator, i, i2, new int[0]);
        this.l = obtainStyledAttributes.getInt(R.styleable.BaseProgressIndicator_showDelay, -1);
        this.m = Math.min(obtainStyledAttributes.getInt(R.styleable.BaseProgressIndicator_minHideDelay, -1), 1000);
        obtainStyledAttributes.recycle();
        this.o = new AnimatorDurationScaleProvider();
        this.k = true;
    }

    @Nullable
    private com.google.android.material.progressindicator.d<S> getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().n();
        } else if (getProgressDrawable() == null) {
            return null;
        } else {
            return getProgressDrawable().o();
        }
    }

    public void applyNewVisibility(boolean z) {
        if (this.k) {
            ((com.google.android.material.progressindicator.c) getCurrentDrawable()).setVisible(o(), false, z);
        }
    }

    @Override // android.widget.ProgressBar
    @Nullable
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.h.hideAnimationBehavior;
    }

    @NonNull
    public int[] getIndicatorColor() {
        return this.h.indicatorColors;
    }

    public int getShowAnimationBehavior() {
        return this.h.showAnimationBehavior;
    }

    @ColorInt
    public int getTrackColor() {
        return this.h.trackColor;
    }

    @Px
    public int getTrackCornerRadius() {
        return this.h.trackCornerRadius;
    }

    @Px
    public int getTrackThickness() {
        return this.h.trackThickness;
    }

    public abstract S h(@NonNull Context context, @NonNull AttributeSet attributeSet);

    public void hide() {
        if (getVisibility() != 0) {
            removeCallbacks(this.r);
            return;
        }
        removeCallbacks(this.s);
        long uptimeMillis = SystemClock.uptimeMillis() - this.n;
        int i = this.m;
        if (uptimeMillis >= ((long) i)) {
            this.s.run();
        } else {
            postDelayed(this.s, i - uptimeMillis);
        }
    }

    public final void i() {
        ((com.google.android.material.progressindicator.c) getCurrentDrawable()).setVisible(false, false, true);
        if (l()) {
            setVisibility(4);
        }
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    public final void j() {
        if (this.m > 0) {
            this.n = SystemClock.uptimeMillis();
        }
        setVisibility(0);
    }

    public boolean k() {
        View view = this;
        while (view.getVisibility() == 0) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                return getWindowVisibility() == 0;
            } else if (!(parent instanceof View)) {
                return true;
            } else {
                view = (View) parent;
            }
        }
        return false;
    }

    public final boolean l() {
        return (getProgressDrawable() == null || !getProgressDrawable().isVisible()) && (getIndeterminateDrawable() == null || !getIndeterminateDrawable().isVisible());
    }

    public final void m() {
        if (getProgressDrawable() != null && getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().m().d(this.t);
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().registerAnimationCallback(this.u);
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().registerAnimationCallback(this.u);
        }
    }

    public final void n() {
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().unregisterAnimationCallback(this.u);
            getIndeterminateDrawable().m().h();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().unregisterAnimationCallback(this.u);
        }
    }

    public boolean o() {
        return ViewCompat.isAttachedToWindow(this) && getWindowVisibility() == 0 && k();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        m();
        if (o()) {
            j();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.s);
        removeCallbacks(this.r);
        ((com.google.android.material.progressindicator.c) getCurrentDrawable()).hideNow();
        n();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(@NonNull Canvas canvas) {
        int save = canvas.save();
        if (getPaddingLeft() != 0 || getPaddingTop() != 0) {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        if (getPaddingRight() != 0 || getPaddingBottom() != 0) {
            canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
        }
        getCurrentDrawable().draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        int paddingLeft;
        int paddingTop;
        super.onMeasure(i, i2);
        com.google.android.material.progressindicator.d<S> currentDrawingDelegate = getCurrentDrawingDelegate();
        if (currentDrawingDelegate == null) {
            return;
        }
        int e = currentDrawingDelegate.e();
        int d2 = currentDrawingDelegate.d();
        if (e < 0) {
            paddingLeft = getMeasuredWidth();
        } else {
            paddingLeft = e + getPaddingLeft() + getPaddingRight();
        }
        if (d2 < 0) {
            paddingTop = getMeasuredHeight();
        } else {
            paddingTop = d2 + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(paddingLeft, paddingTop);
    }

    @Override // android.view.View
    public void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        applyNewVisibility(i == 0);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        applyNewVisibility(false);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public void setAnimatorDurationScaleProvider(@NonNull AnimatorDurationScaleProvider animatorDurationScaleProvider) {
        this.o = animatorDurationScaleProvider;
        if (getProgressDrawable() != null) {
            getProgressDrawable().j = animatorDurationScaleProvider;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().j = animatorDurationScaleProvider;
        }
    }

    public void setHideAnimationBehavior(int i) {
        this.h.hideAnimationBehavior = i;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        if (z == isIndeterminate()) {
            return;
        }
        com.google.android.material.progressindicator.c cVar = (com.google.android.material.progressindicator.c) getCurrentDrawable();
        if (cVar != null) {
            cVar.hideNow();
        }
        super.setIndeterminate(z);
        com.google.android.material.progressindicator.c cVar2 = (com.google.android.material.progressindicator.c) getCurrentDrawable();
        if (cVar2 != null) {
            cVar2.setVisible(o(), false, false);
        }
        if ((cVar2 instanceof IndeterminateDrawable) && o()) {
            ((IndeterminateDrawable) cVar2).m().g();
        }
        this.p = false;
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(@Nullable Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable(null);
        } else if (drawable instanceof IndeterminateDrawable) {
            ((com.google.android.material.progressindicator.c) drawable).hideNow();
            super.setIndeterminateDrawable(drawable);
        } else {
            throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
        }
    }

    public void setIndicatorColor(@ColorInt int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{MaterialColors.getColor(getContext(), R.attr.colorPrimary, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.h.indicatorColors = iArr;
        getIndeterminateDrawable().m().c();
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (isIndeterminate()) {
            return;
        }
        setProgressCompat(i, false);
    }

    public void setProgressCompat(int i, boolean z) {
        if (isIndeterminate()) {
            if (getProgressDrawable() != null) {
                this.i = i;
                this.j = z;
                this.p = true;
                if (getIndeterminateDrawable().isVisible() && this.o.getSystemAnimatorDurationScale(getContext().getContentResolver()) != 0.0f) {
                    getIndeterminateDrawable().m().f();
                    return;
                } else {
                    this.t.onAnimationEnd(getIndeterminateDrawable());
                    return;
                }
            }
            return;
        }
        super.setProgress(i);
        if (getProgressDrawable() == null || z) {
            return;
        }
        getProgressDrawable().jumpToCurrentState();
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(@Nullable Drawable drawable) {
        if (drawable == null) {
            super.setProgressDrawable(null);
        } else if (drawable instanceof DeterminateDrawable) {
            DeterminateDrawable determinateDrawable = (DeterminateDrawable) drawable;
            determinateDrawable.hideNow();
            super.setProgressDrawable(determinateDrawable);
            determinateDrawable.s(getProgress() / getMax());
        } else {
            throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
        }
    }

    public void setShowAnimationBehavior(int i) {
        this.h.showAnimationBehavior = i;
        invalidate();
    }

    public void setTrackColor(@ColorInt int i) {
        S s = this.h;
        if (s.trackColor != i) {
            s.trackColor = i;
            invalidate();
        }
    }

    public void setTrackCornerRadius(@Px int i) {
        S s = this.h;
        if (s.trackCornerRadius != i) {
            s.trackCornerRadius = Math.min(i, s.trackThickness / 2);
        }
    }

    public void setTrackThickness(@Px int i) {
        S s = this.h;
        if (s.trackThickness != i) {
            s.trackThickness = i;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i) {
        if (i != 0 && i != 4 && i != 8) {
            throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.q = i;
    }

    public void show() {
        if (this.l > 0) {
            removeCallbacks(this.r);
            postDelayed(this.r, this.l);
            return;
        }
        this.r.run();
    }

    @Override // android.widget.ProgressBar
    @Nullable
    public IndeterminateDrawable<S> getIndeterminateDrawable() {
        return (IndeterminateDrawable) super.getIndeterminateDrawable();
    }

    @Override // android.widget.ProgressBar
    @Nullable
    public DeterminateDrawable<S> getProgressDrawable() {
        return (DeterminateDrawable) super.getProgressDrawable();
    }
}
