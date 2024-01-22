package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    public static final int K = R.style.Widget_Design_CollapsingToolbar;
    public static final int TITLE_COLLAPSE_MODE_FADE = 1;
    public static final int TITLE_COLLAPSE_MODE_SCALE = 0;
    public long A;
    public int B;
    public AppBarLayout.OnOffsetChangedListener C;
    public int D;
    public int E;
    @Nullable
    public WindowInsetsCompat F;
    public int G;
    public boolean H;
    public int I;
    public boolean J;
    public boolean h;
    public int i;
    @Nullable
    public ViewGroup j;
    @Nullable
    public View k;
    public View l;
    public int m;
    public int n;
    public int o;
    public int p;
    public final Rect q;
    @NonNull
    public final CollapsingTextHelper r;
    @NonNull
    public final ElevationOverlayProvider s;
    public boolean t;
    public boolean u;
    @Nullable
    public Drawable v;
    @Nullable
    public Drawable w;
    public int x;
    public boolean y;
    public ValueAnimator z;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface TitleCollapseMode {
    }

    /* loaded from: classes10.dex */
    public class a implements OnApplyWindowInsetsListener {
        public a() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, @NonNull WindowInsetsCompat windowInsetsCompat) {
            return CollapsingToolbarLayout.this.l(windowInsetsCompat);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            CollapsingToolbarLayout.this.setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    /* loaded from: classes10.dex */
    public class c implements AppBarLayout.OnOffsetChangedListener {
        public c() {
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            int height;
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.D = i;
            WindowInsetsCompat windowInsetsCompat = collapsingToolbarLayout.F;
            int systemWindowInsetTop = windowInsetsCompat != null ? windowInsetsCompat.getSystemWindowInsetTop() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                d h = CollapsingToolbarLayout.h(childAt);
                int i3 = layoutParams.f10214a;
                if (i3 == 1) {
                    h.j(MathUtils.clamp(-i, 0, CollapsingToolbarLayout.this.f(childAt)));
                } else if (i3 == 2) {
                    h.j(Math.round((-i) * layoutParams.b));
                }
            }
            CollapsingToolbarLayout.this.r();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.w != null && systemWindowInsetTop > 0) {
                ViewCompat.postInvalidateOnAnimation(collapsingToolbarLayout2);
            }
            int height2 = (CollapsingToolbarLayout.this.getHeight() - ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this)) - systemWindowInsetTop;
            float f = height2;
            CollapsingToolbarLayout.this.r.setFadeModeStartFraction(Math.min(1.0f, (height - CollapsingToolbarLayout.this.getScrimVisibleHeightTrigger()) / f));
            CollapsingToolbarLayout collapsingToolbarLayout3 = CollapsingToolbarLayout.this;
            collapsingToolbarLayout3.r.setCurrentOffsetY(collapsingToolbarLayout3.D + height2);
            CollapsingToolbarLayout.this.r.setExpansionFraction(Math.abs(i) / f);
        }
    }

    public CollapsingToolbarLayout(@NonNull Context context) {
        this(context, null);
    }

    public static int e(@NonNull View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            return view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        }
        return view.getMeasuredHeight();
    }

    public static CharSequence g(View view) {
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getTitle();
        }
        if (Build.VERSION.SDK_INT < 21 || !(view instanceof android.widget.Toolbar)) {
            return null;
        }
        return ((android.widget.Toolbar) view).getTitle();
    }

    @NonNull
    public static d h(@NonNull View view) {
        int i = R.id.view_offset_helper;
        d dVar = (d) view.getTag(i);
        if (dVar == null) {
            d dVar2 = new d(view);
            view.setTag(i, dVar2);
            return dVar2;
        }
        return dVar;
    }

    public static boolean j(View view) {
        return (view instanceof Toolbar) || (Build.VERSION.SDK_INT >= 21 && (view instanceof android.widget.Toolbar));
    }

    public final void a(int i) {
        TimeInterpolator timeInterpolator;
        c();
        ValueAnimator valueAnimator = this.z;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.z = valueAnimator2;
            if (i > this.x) {
                timeInterpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
            } else {
                timeInterpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
            }
            valueAnimator2.setInterpolator(timeInterpolator);
            this.z.addUpdateListener(new b());
        } else if (valueAnimator.isRunning()) {
            this.z.cancel();
        }
        this.z.setDuration(this.A);
        this.z.setIntValues(this.x, i);
        this.z.start();
    }

    public final void b(AppBarLayout appBarLayout) {
        if (i()) {
            appBarLayout.setLiftOnScroll(false);
        }
    }

    public final void c() {
        if (this.h) {
            ViewGroup viewGroup = null;
            this.j = null;
            this.k = null;
            int i = this.i;
            if (i != -1) {
                ViewGroup viewGroup2 = (ViewGroup) findViewById(i);
                this.j = viewGroup2;
                if (viewGroup2 != null) {
                    this.k = d(viewGroup2);
                }
            }
            if (this.j == null) {
                int childCount = getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i2);
                    if (j(childAt)) {
                        viewGroup = (ViewGroup) childAt;
                        break;
                    }
                    i2++;
                }
                this.j = viewGroup;
            }
            q();
            this.h = false;
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @NonNull
    public final View d(@NonNull View view) {
        for (ViewParent parent = view.getParent(); parent != this && parent != null; parent = parent.getParent()) {
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return view;
    }

    @Override // android.view.View
    public void draw(@NonNull Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        c();
        if (this.j == null && (drawable = this.v) != null && this.x > 0) {
            drawable.mutate().setAlpha(this.x);
            this.v.draw(canvas);
        }
        if (this.t && this.u) {
            if (this.j != null && this.v != null && this.x > 0 && i() && this.r.getExpansionFraction() < this.r.getFadeModeThresholdFraction()) {
                int save = canvas.save();
                canvas.clipRect(this.v.getBounds(), Region.Op.DIFFERENCE);
                this.r.draw(canvas);
                canvas.restoreToCount(save);
            } else {
                this.r.draw(canvas);
            }
        }
        if (this.w == null || this.x <= 0) {
            return;
        }
        WindowInsetsCompat windowInsetsCompat = this.F;
        int systemWindowInsetTop = windowInsetsCompat != null ? windowInsetsCompat.getSystemWindowInsetTop() : 0;
        if (systemWindowInsetTop > 0) {
            this.w.setBounds(0, -this.D, getWidth(), systemWindowInsetTop - this.D);
            this.w.mutate().setAlpha(this.x);
            this.w.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        if (this.v == null || this.x <= 0 || !k(view)) {
            z = false;
        } else {
            p(this.v, view, getWidth(), getHeight());
            this.v.mutate().setAlpha(this.x);
            this.v.draw(canvas);
            z = true;
        }
        return super.drawChild(canvas, view, j) || z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.w;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.v;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        CollapsingTextHelper collapsingTextHelper = this.r;
        if (collapsingTextHelper != null) {
            z |= collapsingTextHelper.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    public final int f(@NonNull View view) {
        return ((getHeight() - h(view).b()) - view.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    public int getCollapsedTitleGravity() {
        return this.r.getCollapsedTextGravity();
    }

    @NonNull
    public Typeface getCollapsedTitleTypeface() {
        return this.r.getCollapsedTypeface();
    }

    @Nullable
    public Drawable getContentScrim() {
        return this.v;
    }

    public int getExpandedTitleGravity() {
        return this.r.getExpandedTextGravity();
    }

    public int getExpandedTitleMarginBottom() {
        return this.p;
    }

    public int getExpandedTitleMarginEnd() {
        return this.o;
    }

    public int getExpandedTitleMarginStart() {
        return this.m;
    }

    public int getExpandedTitleMarginTop() {
        return this.n;
    }

    @NonNull
    public Typeface getExpandedTitleTypeface() {
        return this.r.getExpandedTypeface();
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getHyphenationFrequency() {
        return this.r.getHyphenationFrequency();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getLineCount() {
        return this.r.getLineCount();
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getLineSpacingAdd() {
        return this.r.getLineSpacingAdd();
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getLineSpacingMultiplier() {
        return this.r.getLineSpacingMultiplier();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getMaxLines() {
        return this.r.getMaxLines();
    }

    public int getScrimAlpha() {
        return this.x;
    }

    public long getScrimAnimationDuration() {
        return this.A;
    }

    public int getScrimVisibleHeightTrigger() {
        int i = this.B;
        if (i >= 0) {
            return i + this.G + this.I;
        }
        WindowInsetsCompat windowInsetsCompat = this.F;
        int systemWindowInsetTop = windowInsetsCompat != null ? windowInsetsCompat.getSystemWindowInsetTop() : 0;
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (minimumHeight > 0) {
            return Math.min((minimumHeight * 2) + systemWindowInsetTop, getHeight());
        }
        return getHeight() / 3;
    }

    @Nullable
    public Drawable getStatusBarScrim() {
        return this.w;
    }

    @Nullable
    public CharSequence getTitle() {
        if (this.t) {
            return this.r.getText();
        }
        return null;
    }

    public int getTitleCollapseMode() {
        return this.E;
    }

    @Nullable
    public TimeInterpolator getTitlePositionInterpolator() {
        return this.r.getPositionInterpolator();
    }

    public final boolean i() {
        return this.E == 1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isExtraMultilineHeightEnabled() {
        return this.J;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isForceApplySystemWindowInsetTop() {
        return this.H;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.r.isRtlTextDirectionHeuristicsEnabled();
    }

    public boolean isTitleEnabled() {
        return this.t;
    }

    public final boolean k(View view) {
        View view2 = this.k;
        if (view2 == null || view2 == this) {
            if (view == this.j) {
                return true;
            }
        } else if (view == view2) {
            return true;
        }
        return false;
    }

    public WindowInsetsCompat l(@NonNull WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = ViewCompat.getFitsSystemWindows(this) ? windowInsetsCompat : null;
        if (!ObjectsCompat.equals(this.F, windowInsetsCompat2)) {
            this.F = windowInsetsCompat2;
            requestLayout();
        }
        return windowInsetsCompat.consumeSystemWindowInsets();
    }

    public final void m(boolean z) {
        int i;
        int i2;
        int i3;
        View view = this.k;
        if (view == null) {
            view = this.j;
        }
        int f = f(view);
        DescendantOffsetUtils.getDescendantRect(this, this.l, this.q);
        ViewGroup viewGroup = this.j;
        int i4 = 0;
        if (viewGroup instanceof Toolbar) {
            Toolbar toolbar = (Toolbar) viewGroup;
            i4 = toolbar.getTitleMarginStart();
            i2 = toolbar.getTitleMarginEnd();
            i3 = toolbar.getTitleMarginTop();
            i = toolbar.getTitleMarginBottom();
        } else if (Build.VERSION.SDK_INT < 24 || !(viewGroup instanceof android.widget.Toolbar)) {
            i = 0;
            i2 = 0;
            i3 = 0;
        } else {
            android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
            i4 = toolbar2.getTitleMarginStart();
            i2 = toolbar2.getTitleMarginEnd();
            i3 = toolbar2.getTitleMarginTop();
            i = toolbar2.getTitleMarginBottom();
        }
        CollapsingTextHelper collapsingTextHelper = this.r;
        Rect rect = this.q;
        int i5 = rect.left + (z ? i2 : i4);
        int i6 = rect.top + f + i3;
        int i7 = rect.right;
        if (!z) {
            i4 = i2;
        }
        collapsingTextHelper.setCollapsedBounds(i5, i6, i7 - i4, (rect.bottom + f) - i);
    }

    public final void n() {
        setContentDescription(getTitle());
    }

    public final void o(@NonNull Drawable drawable, int i, int i2) {
        p(drawable, this.j, i, i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            b(appBarLayout);
            ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows(appBarLayout));
            if (this.C == null) {
                this.C = new c();
            }
            appBarLayout.addOnOffsetChangedListener(this.C);
            ViewCompat.requestApplyInsets(this);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.r.maybeUpdateFontWeightAdjustment(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = this.C;
        if (onOffsetChangedListener != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(onOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        WindowInsetsCompat windowInsetsCompat = this.F;
        if (windowInsetsCompat != null) {
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (!ViewCompat.getFitsSystemWindows(childAt) && childAt.getTop() < systemWindowInsetTop) {
                    ViewCompat.offsetTopAndBottom(childAt, systemWindowInsetTop);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            h(getChildAt(i6)).g();
        }
        s(i, i2, i3, i4, false);
        t();
        r();
        int childCount3 = getChildCount();
        for (int i7 = 0; i7 < childCount3; i7++) {
            h(getChildAt(i7)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        c();
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        WindowInsetsCompat windowInsetsCompat = this.F;
        int systemWindowInsetTop = windowInsetsCompat != null ? windowInsetsCompat.getSystemWindowInsetTop() : 0;
        if ((mode == 0 || this.H) && systemWindowInsetTop > 0) {
            this.G = systemWindowInsetTop;
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + systemWindowInsetTop, 1073741824));
        }
        if (this.J && this.r.getMaxLines() > 1) {
            t();
            s(0, 0, getMeasuredWidth(), getMeasuredHeight(), true);
            int expandedLineCount = this.r.getExpandedLineCount();
            if (expandedLineCount > 1) {
                this.I = Math.round(this.r.getExpandedTextFullHeight()) * (expandedLineCount - 1);
                super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + this.I, 1073741824));
            }
        }
        ViewGroup viewGroup = this.j;
        if (viewGroup != null) {
            View view = this.k;
            if (view != null && view != this) {
                setMinimumHeight(e(view));
            } else {
                setMinimumHeight(e(viewGroup));
            }
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.v;
        if (drawable != null) {
            o(drawable, i, i2);
        }
    }

    public final void p(@NonNull Drawable drawable, @Nullable View view, int i, int i2) {
        if (i() && view != null && this.t) {
            i2 = view.getBottom();
        }
        drawable.setBounds(0, 0, i, i2);
    }

    public final void q() {
        View view;
        if (!this.t && (view = this.l) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.l);
            }
        }
        if (!this.t || this.j == null) {
            return;
        }
        if (this.l == null) {
            this.l = new View(getContext());
        }
        if (this.l.getParent() == null) {
            this.j.addView(this.l, -1, -1);
        }
    }

    public final void r() {
        if (this.v == null && this.w == null) {
            return;
        }
        setScrimsShown(getHeight() + this.D < getScrimVisibleHeightTrigger());
    }

    public final void s(int i, int i2, int i3, int i4, boolean z) {
        View view;
        if (!this.t || (view = this.l) == null) {
            return;
        }
        boolean z2 = ViewCompat.isAttachedToWindow(view) && this.l.getVisibility() == 0;
        this.u = z2;
        if (z2 || z) {
            boolean z3 = ViewCompat.getLayoutDirection(this) == 1;
            m(z3);
            this.r.setExpandedBounds(z3 ? this.o : this.m, this.q.top + this.n, (i3 - i) - (z3 ? this.m : this.o), (i4 - i2) - this.p);
            this.r.recalculate(z);
        }
    }

    public void setCollapsedTitleGravity(int i) {
        this.r.setCollapsedTextGravity(i);
    }

    public void setCollapsedTitleTextAppearance(@StyleRes int i) {
        this.r.setCollapsedTextAppearance(i);
    }

    public void setCollapsedTitleTextColor(@ColorInt int i) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setCollapsedTitleTypeface(@Nullable Typeface typeface) {
        this.r.setCollapsedTypeface(typeface);
    }

    public void setContentScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = this.v;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.v = mutate;
            if (mutate != null) {
                o(mutate, getWidth(), getHeight());
                this.v.setCallback(this);
                this.v.setAlpha(this.x);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(@ColorInt int i) {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(@DrawableRes int i) {
        setContentScrim(ContextCompat.getDrawable(getContext(), i));
    }

    public void setExpandedTitleColor(@ColorInt int i) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setExpandedTitleGravity(int i) {
        this.r.setExpandedTextGravity(i);
    }

    public void setExpandedTitleMargin(int i, int i2, int i3, int i4) {
        this.m = i;
        this.n = i2;
        this.o = i3;
        this.p = i4;
        requestLayout();
    }

    public void setExpandedTitleMarginBottom(int i) {
        this.p = i;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i) {
        this.o = i;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i) {
        this.m = i;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i) {
        this.n = i;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(@StyleRes int i) {
        this.r.setExpandedTextAppearance(i);
    }

    public void setExpandedTitleTextColor(@NonNull ColorStateList colorStateList) {
        this.r.setExpandedTextColor(colorStateList);
    }

    public void setExpandedTitleTypeface(@Nullable Typeface typeface) {
        this.r.setExpandedTypeface(typeface);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setExtraMultilineHeightEnabled(boolean z) {
        this.J = z;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setForceApplySystemWindowInsetTop(boolean z) {
        this.H = z;
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setHyphenationFrequency(int i) {
        this.r.setHyphenationFrequency(i);
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setLineSpacingAdd(float f) {
        this.r.setLineSpacingAdd(f);
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setLineSpacingMultiplier(@FloatRange(from = 0.0d) float f) {
        this.r.setLineSpacingMultiplier(f);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setMaxLines(int i) {
        this.r.setMaxLines(i);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.r.setRtlTextDirectionHeuristicsEnabled(z);
    }

    public void setScrimAlpha(int i) {
        ViewGroup viewGroup;
        if (i != this.x) {
            if (this.v != null && (viewGroup = this.j) != null) {
                ViewCompat.postInvalidateOnAnimation(viewGroup);
            }
            this.x = i;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setScrimAnimationDuration(@IntRange(from = 0) long j) {
        this.A = j;
    }

    public void setScrimVisibleHeightTrigger(@IntRange(from = 0) int i) {
        if (this.B != i) {
            this.B = i;
            r();
        }
    }

    public void setScrimsShown(boolean z) {
        setScrimsShown(z, ViewCompat.isLaidOut(this) && !isInEditMode());
    }

    public void setStatusBarScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = this.w;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.w = mutate;
            if (mutate != null) {
                if (mutate.isStateful()) {
                    this.w.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.w, ViewCompat.getLayoutDirection(this));
                this.w.setVisible(getVisibility() == 0, false);
                this.w.setCallback(this);
                this.w.setAlpha(this.x);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarScrimColor(@ColorInt int i) {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(@DrawableRes int i) {
        setStatusBarScrim(ContextCompat.getDrawable(getContext(), i));
    }

    public void setTitle(@Nullable CharSequence charSequence) {
        this.r.setText(charSequence);
        n();
    }

    public void setTitleCollapseMode(int i) {
        this.E = i;
        boolean i2 = i();
        this.r.setFadeModeEnabled(i2);
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            b((AppBarLayout) parent);
        }
        if (i2 && this.v == null) {
            setContentScrimColor(this.s.compositeOverlayWithThemeSurfaceColorIfNeeded(getResources().getDimension(R.dimen.design_appbar_elevation)));
        }
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.t) {
            this.t = z;
            n();
            q();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.r.setPositionInterpolator(timeInterpolator);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.w;
        if (drawable != null && drawable.isVisible() != z) {
            this.w.setVisible(z, false);
        }
        Drawable drawable2 = this.v;
        if (drawable2 == null || drawable2.isVisible() == z) {
            return;
        }
        this.v.setVisible(z, false);
    }

    public final void t() {
        if (this.j != null && this.t && TextUtils.isEmpty(this.r.getText())) {
            setTitle(g(this.j));
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(@NonNull Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.v || drawable == this.w;
    }

    public CollapsingToolbarLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.collapsingToolbarLayoutStyle);
    }

    public void setCollapsedTitleTextColor(@NonNull ColorStateList colorStateList) {
        this.r.setCollapsedTextColor(colorStateList);
    }

    public void setScrimsShown(boolean z, boolean z2) {
        if (this.y != z) {
            if (z2) {
                a(z ? 255 : 0);
            } else {
                setScrimAlpha(z ? 255 : 0);
            }
            this.y = z;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CollapsingToolbarLayout(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.Nullable android.util.AttributeSet r12, int r13) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* loaded from: classes10.dex */
    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;

        /* renamed from: a  reason: collision with root package name */
        public int f10214a;
        public float b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f10214a = 0;
            this.b = 0.5f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CollapsingToolbarLayout_Layout);
            this.f10214a = obtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            setParallaxMultiplier(obtainStyledAttributes.getFloat(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            obtainStyledAttributes.recycle();
        }

        public int getCollapseMode() {
            return this.f10214a;
        }

        public float getParallaxMultiplier() {
            return this.b;
        }

        public void setCollapseMode(int i) {
            this.f10214a = i;
        }

        public void setParallaxMultiplier(float f) {
            this.b = f;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f10214a = 0;
            this.b = 0.5f;
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2, i3);
            this.f10214a = 0;
            this.b = 0.5f;
        }

        public LayoutParams(@NonNull ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f10214a = 0;
            this.b = 0.5f;
        }

        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f10214a = 0;
            this.b = 0.5f;
        }

        @RequiresApi(19)
        public LayoutParams(@NonNull FrameLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.f10214a = 0;
            this.b = 0.5f;
        }
    }
}
