package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.XmlRes;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.Locale;
/* loaded from: classes10.dex */
public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    public static final int BOTTOM_END = 8388693;
    public static final int BOTTOM_START = 8388691;
    public static final int TOP_END = 8388661;
    public static final int TOP_START = 8388659;
    @StyleRes
    public static final int u = R.style.Widget_MaterialComponents_Badge;
    @AttrRes
    public static final int v = R.attr.badgeStyle;
    @NonNull
    public final WeakReference<Context> h;
    @NonNull
    public final MaterialShapeDrawable i;
    @NonNull
    public final TextDrawableHelper j;
    @NonNull
    public final Rect k;
    @NonNull
    public final BadgeState l;
    public float m;
    public float n;
    public int o;
    public float p;
    public float q;
    public float r;
    @Nullable
    public WeakReference<View> s;
    @Nullable
    public WeakReference<FrameLayout> t;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface BadgeGravity {
    }

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ View h;
        public final /* synthetic */ FrameLayout i;

        public a(View view, FrameLayout frameLayout) {
            this.h = view;
            this.i = frameLayout;
        }

        @Override // java.lang.Runnable
        public void run() {
            BadgeDrawable.this.updateBadgeCoordinates(this.h, this.i);
        }
    }

    public BadgeDrawable(@NonNull Context context, @XmlRes int i, @AttrRes int i2, @StyleRes int i3, @Nullable BadgeState.State state) {
        this.h = new WeakReference<>(context);
        ThemeEnforcement.checkMaterialTheme(context);
        this.k = new Rect();
        this.i = new MaterialShapeDrawable();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.j = textDrawableHelper;
        textDrawableHelper.getTextPaint().setTextAlign(Paint.Align.CENTER);
        s(R.style.TextAppearance_MaterialComponents_Badge);
        this.l = new BadgeState(context, i, i2, i3, state);
        o();
    }

    @NonNull
    public static BadgeDrawable b(@NonNull Context context, @NonNull BadgeState.State state) {
        return new BadgeDrawable(context, 0, v, u, state);
    }

    @NonNull
    public static BadgeDrawable create(@NonNull Context context) {
        return new BadgeDrawable(context, 0, v, u, null);
    }

    @NonNull
    public static BadgeDrawable createFromResource(@NonNull Context context, @XmlRes int i) {
        return new BadgeDrawable(context, i, v, u, null);
    }

    public static void u(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
    }

    public final void a(@NonNull Context context, @NonNull Rect rect, @NonNull View view) {
        int i;
        float f;
        float f2;
        int g = g();
        int g2 = this.l.g();
        if (g2 != 8388691 && g2 != 8388693) {
            this.n = rect.top + g;
        } else {
            this.n = rect.bottom - g;
        }
        if (getNumber() <= 9) {
            float f3 = !hasNumber() ? this.l.c : this.l.d;
            this.p = f3;
            this.r = f3;
            this.q = f3;
        } else {
            float f4 = this.l.d;
            this.p = f4;
            this.r = f4;
            this.q = (this.j.getTextWidth(d()) / 2.0f) + this.l.e;
        }
        Resources resources = context.getResources();
        if (hasNumber()) {
            i = R.dimen.mtrl_badge_text_horizontal_edge_offset;
        } else {
            i = R.dimen.mtrl_badge_horizontal_edge_offset;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(i);
        int f5 = f();
        int g3 = this.l.g();
        if (g3 != 8388659 && g3 != 8388691) {
            if (ViewCompat.getLayoutDirection(view) == 0) {
                f2 = ((rect.right + this.q) - dimensionPixelSize) - f5;
            } else {
                f2 = (rect.left - this.q) + dimensionPixelSize + f5;
            }
            this.m = f2;
            return;
        }
        if (ViewCompat.getLayoutDirection(view) == 0) {
            f = (rect.left - this.q) + dimensionPixelSize + f5;
        } else {
            f = ((rect.right + this.q) - dimensionPixelSize) - f5;
        }
        this.m = f;
    }

    public final void c(Canvas canvas) {
        Rect rect = new Rect();
        String d = d();
        this.j.getTextPaint().getTextBounds(d, 0, d.length(), rect);
        canvas.drawText(d, this.m, this.n + (rect.height() / 2), this.j.getTextPaint());
    }

    public void clearNumber() {
        if (hasNumber()) {
            this.l.a();
            m();
        }
    }

    @NonNull
    public final String d() {
        if (getNumber() <= this.o) {
            return NumberFormat.getInstance(this.l.p()).format(getNumber());
        }
        Context context = this.h.get();
        return context == null ? "" : String.format(this.l.p(), context.getString(R.string.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.o), "+");
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.i.draw(canvas);
        if (hasNumber()) {
            c(canvas);
        }
    }

    @NonNull
    public BadgeState.State e() {
        return this.l.q();
    }

    public final int f() {
        return (hasNumber() ? this.l.l() : this.l.m()) + this.l.c();
    }

    public final int g() {
        return (hasNumber() ? this.l.r() : this.l.s()) + this.l.d();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.l.e();
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.i.getFillColor().getDefaultColor();
    }

    public int getBadgeGravity() {
        return this.l.g();
    }

    @NonNull
    public Locale getBadgeNumberLocale() {
        return this.l.p();
    }

    @ColorInt
    public int getBadgeTextColor() {
        return this.j.getTextPaint().getColor();
    }

    @Nullable
    public CharSequence getContentDescription() {
        Context context;
        if (isVisible()) {
            if (hasNumber()) {
                if (this.l.k() == 0 || (context = this.h.get()) == null) {
                    return null;
                }
                if (getNumber() <= this.o) {
                    return context.getResources().getQuantityString(this.l.k(), getNumber(), Integer.valueOf(getNumber()));
                }
                return context.getString(this.l.i(), Integer.valueOf(this.o));
            }
            return this.l.j();
        }
        return null;
    }

    @Nullable
    public FrameLayout getCustomBadgeParent() {
        WeakReference<FrameLayout> weakReference = this.t;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getHorizontalOffset() {
        return this.l.m();
    }

    @Px
    public int getHorizontalOffsetWithText() {
        return this.l.l();
    }

    @Px
    public int getHorizontalOffsetWithoutText() {
        return this.l.m();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.k.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.k.width();
    }

    public int getMaxCharacterCount() {
        return this.l.n();
    }

    public int getNumber() {
        if (hasNumber()) {
            return this.l.o();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public int getVerticalOffset() {
        return this.l.s();
    }

    @Px
    public int getVerticalOffsetWithText() {
        return this.l.r();
    }

    @Px
    public int getVerticalOffsetWithoutText() {
        return this.l.s();
    }

    public final void h() {
        this.j.getTextPaint().setAlpha(getAlpha());
        invalidateSelf();
    }

    public boolean hasNumber() {
        return this.l.t();
    }

    public final void i() {
        ColorStateList valueOf = ColorStateList.valueOf(this.l.f());
        if (this.i.getFillColor() != valueOf) {
            this.i.setFillColor(valueOf);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public final void j() {
        WeakReference<View> weakReference = this.s;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = this.s.get();
        WeakReference<FrameLayout> weakReference2 = this.t;
        updateBadgeCoordinates(view, weakReference2 != null ? weakReference2.get() : null);
    }

    public final void k() {
        this.j.getTextPaint().setColor(this.l.h());
        invalidateSelf();
    }

    public final void l() {
        w();
        this.j.setTextWidthDirty(true);
        v();
        invalidateSelf();
    }

    public final void m() {
        this.j.setTextWidthDirty(true);
        v();
        invalidateSelf();
    }

    public final void n() {
        boolean u2 = this.l.u();
        setVisible(u2, false);
        if (!BadgeUtils.USE_COMPAT_PARENT || getCustomBadgeParent() == null || u2) {
            return;
        }
        ((ViewGroup) getCustomBadgeParent().getParent()).invalidate();
    }

    public final void o() {
        l();
        m();
        h();
        i();
        k();
        j();
        v();
        n();
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onTextSizeChange() {
        invalidateSelf();
    }

    public void p(int i) {
        this.l.w(i);
        v();
    }

    public void q(@Px int i) {
        this.l.x(i);
        v();
    }

    public final void r(@Nullable TextAppearance textAppearance) {
        Context context;
        if (this.j.getTextAppearance() == textAppearance || (context = this.h.get()) == null) {
            return;
        }
        this.j.setTextAppearance(textAppearance, context);
        v();
    }

    public final void s(@StyleRes int i) {
        Context context = this.h.get();
        if (context == null) {
            return;
        }
        r(new TextAppearance(context, i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.l.y(i);
        h();
    }

    public void setBackgroundColor(@ColorInt int i) {
        this.l.z(i);
        i();
    }

    public void setBadgeGravity(int i) {
        if (this.l.g() != i) {
            this.l.A(i);
            j();
        }
    }

    public void setBadgeNumberLocale(@NonNull Locale locale) {
        if (locale.equals(this.l.p())) {
            return;
        }
        this.l.J(locale);
        invalidateSelf();
    }

    public void setBadgeTextColor(@ColorInt int i) {
        if (this.j.getTextPaint().getColor() != i) {
            this.l.B(i);
            k();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(@StringRes int i) {
        this.l.C(i);
    }

    public void setContentDescriptionNumberless(CharSequence charSequence) {
        this.l.D(charSequence);
    }

    public void setContentDescriptionQuantityStringsResource(@PluralsRes int i) {
        this.l.E(i);
    }

    public void setHorizontalOffset(int i) {
        setHorizontalOffsetWithoutText(i);
        setHorizontalOffsetWithText(i);
    }

    public void setHorizontalOffsetWithText(@Px int i) {
        this.l.F(i);
        v();
    }

    public void setHorizontalOffsetWithoutText(@Px int i) {
        this.l.G(i);
        v();
    }

    public void setMaxCharacterCount(int i) {
        if (this.l.n() != i) {
            this.l.H(i);
            l();
        }
    }

    public void setNumber(int i) {
        int max = Math.max(0, i);
        if (this.l.o() != max) {
            this.l.I(max);
            m();
        }
    }

    public void setVerticalOffset(int i) {
        setVerticalOffsetWithoutText(i);
        setVerticalOffsetWithText(i);
    }

    public void setVerticalOffsetWithText(@Px int i) {
        this.l.K(i);
        v();
    }

    public void setVerticalOffsetWithoutText(@Px int i) {
        this.l.L(i);
        v();
    }

    public void setVisible(boolean z) {
        this.l.M(z);
        n();
    }

    public final void t(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null || viewGroup.getId() != R.id.mtrl_anchor_parent) {
            WeakReference<FrameLayout> weakReference = this.t;
            if (weakReference == null || weakReference.get() != viewGroup) {
                u(view);
                FrameLayout frameLayout = new FrameLayout(view.getContext());
                frameLayout.setId(R.id.mtrl_anchor_parent);
                frameLayout.setClipChildren(false);
                frameLayout.setClipToPadding(false);
                frameLayout.setLayoutParams(view.getLayoutParams());
                frameLayout.setMinimumWidth(view.getWidth());
                frameLayout.setMinimumHeight(view.getHeight());
                int indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeViewAt(indexOfChild);
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.addView(view);
                viewGroup.addView(frameLayout, indexOfChild);
                this.t = new WeakReference<>(frameLayout);
                frameLayout.post(new a(view, frameLayout));
            }
        }
    }

    @Deprecated
    public void updateBadgeCoordinates(@NonNull View view, @Nullable ViewGroup viewGroup) {
        if (viewGroup instanceof FrameLayout) {
            updateBadgeCoordinates(view, (FrameLayout) viewGroup);
            return;
        }
        throw new IllegalArgumentException("customBadgeParent must be a FrameLayout");
    }

    public final void v() {
        Context context = this.h.get();
        WeakReference<View> weakReference = this.s;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.k);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<FrameLayout> weakReference2 = this.t;
        FrameLayout frameLayout = weakReference2 != null ? weakReference2.get() : null;
        if (frameLayout != null || BadgeUtils.USE_COMPAT_PARENT) {
            if (frameLayout == null) {
                frameLayout = (ViewGroup) view.getParent();
            }
            frameLayout.offsetDescendantRectToMyCoords(view, rect2);
        }
        a(context, rect2, view);
        BadgeUtils.updateBadgeBounds(this.k, this.m, this.n, this.q, this.r);
        this.i.setCornerSize(this.p);
        if (rect.equals(this.k)) {
            return;
        }
        this.i.setBounds(this.k);
    }

    public final void w() {
        this.o = ((int) Math.pow(10.0d, getMaxCharacterCount() - 1.0d)) - 1;
    }

    public void updateBadgeCoordinates(@NonNull View view) {
        updateBadgeCoordinates(view, (FrameLayout) null);
    }

    public void updateBadgeCoordinates(@NonNull View view, @Nullable FrameLayout frameLayout) {
        this.s = new WeakReference<>(view);
        boolean z = BadgeUtils.USE_COMPAT_PARENT;
        if (z && frameLayout == null) {
            t(view);
        } else {
            this.t = new WeakReference<>(frameLayout);
        }
        if (!z) {
            u(view);
        }
        v();
        invalidateSelf();
    }
}
