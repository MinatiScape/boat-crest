package com.google.android.material.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import com.google.android.material.R;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class MaterialCardView extends CardView implements Checkable, Shapeable {
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_END = 8388693;
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_START = 8388691;
    public static final int CHECKED_ICON_GRAVITY_TOP_END = 8388661;
    public static final int CHECKED_ICON_GRAVITY_TOP_START = 8388659;
    public static final int[] m = {16842911};
    public static final int[] n = {16842912};
    public static final int[] o = {R.attr.state_dragged};
    public static final int p = R.style.Widget_MaterialComponents_CardView;
    @NonNull
    public final a h;
    public boolean i;
    public boolean j;
    public boolean k;
    public OnCheckedChangeListener l;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface CheckedIconGravity {
    }

    /* loaded from: classes10.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialCardView materialCardView, boolean z);
    }

    public MaterialCardView(Context context) {
        this(context, null);
    }

    @NonNull
    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.h.j().getBounds());
        return rectF;
    }

    public final void b() {
        if (Build.VERSION.SDK_INT > 26) {
            this.h.i();
        }
    }

    public void c(int i, int i2, int i3, int i4) {
        super.setContentPadding(i, i2, i3, i4);
    }

    @Override // androidx.cardview.widget.CardView
    @NonNull
    public ColorStateList getCardBackgroundColor() {
        return this.h.k();
    }

    @NonNull
    public ColorStateList getCardForegroundColor() {
        return this.h.l();
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    @Nullable
    public Drawable getCheckedIcon() {
        return this.h.m();
    }

    public int getCheckedIconGravity() {
        return this.h.n();
    }

    @Dimension
    public int getCheckedIconMargin() {
        return this.h.o();
    }

    @Dimension
    public int getCheckedIconSize() {
        return this.h.p();
    }

    @Nullable
    public ColorStateList getCheckedIconTint() {
        return this.h.q();
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.h.A().bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.h.A().left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.h.A().right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.h.A().top;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.h.u();
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.h.s();
    }

    public ColorStateList getRippleColor() {
        return this.h.v();
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.h.w();
    }

    @ColorInt
    @Deprecated
    public int getStrokeColor() {
        return this.h.x();
    }

    @Nullable
    public ColorStateList getStrokeColorStateList() {
        return this.h.y();
    }

    @Dimension
    public int getStrokeWidth() {
        return this.h.z();
    }

    public boolean isCheckable() {
        a aVar = this.h;
        return aVar != null && aVar.D();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.j;
    }

    public boolean isDragged() {
        return this.k;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.h.j());
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (isCheckable()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, m);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, n);
        }
        if (isDragged()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, o);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.h.H(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.i) {
            if (!this.h.C()) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.h.I(true);
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(@ColorInt int i) {
        this.h.J(ColorStateList.valueOf(i));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f) {
        super.setCardElevation(f);
        this.h.d0();
    }

    public void setCardForegroundColor(@Nullable ColorStateList colorStateList) {
        this.h.K(colorStateList);
    }

    public void setCheckable(boolean z) {
        this.h.L(z);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.j != z) {
            toggle();
        }
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        this.h.N(drawable);
    }

    public void setCheckedIconGravity(int i) {
        if (this.h.n() != i) {
            this.h.O(i);
        }
    }

    public void setCheckedIconMargin(@Dimension int i) {
        this.h.P(i);
    }

    public void setCheckedIconMarginResource(@DimenRes int i) {
        if (i != -1) {
            this.h.P(getResources().getDimensionPixelSize(i));
        }
    }

    public void setCheckedIconResource(@DrawableRes int i) {
        this.h.N(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setCheckedIconSize(@Dimension int i) {
        this.h.Q(i);
    }

    public void setCheckedIconSizeResource(@DimenRes int i) {
        if (i != 0) {
            this.h.Q(getResources().getDimensionPixelSize(i));
        }
    }

    public void setCheckedIconTint(@Nullable ColorStateList colorStateList) {
        this.h.R(colorStateList);
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        a aVar = this.h;
        if (aVar != null) {
            aVar.b0();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.h.Y(i, i2, i3, i4);
    }

    public void setDragged(boolean z) {
        if (this.k != z) {
            this.k = z;
            refreshDrawableState();
            b();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.h.f0();
    }

    public void setOnCheckedChangeListener(@Nullable OnCheckedChangeListener onCheckedChangeListener) {
        this.l = onCheckedChangeListener;
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        this.h.f0();
        this.h.c0();
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.h.T(f);
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f) {
        super.setRadius(f);
        this.h.S(f);
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        this.h.U(colorStateList);
    }

    public void setRippleColorResource(@ColorRes int i) {
        this.h.U(AppCompatResources.getColorStateList(getContext(), i));
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(shapeAppearanceModel.isRoundRect(getBoundsAsRectF()));
        }
        this.h.V(shapeAppearanceModel);
    }

    public void setStrokeColor(@ColorInt int i) {
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setStrokeWidth(@Dimension int i) {
        this.h.X(i);
        invalidate();
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        this.h.f0();
        this.h.c0();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (isCheckable() && isEnabled()) {
            this.j = !this.j;
            refreshDrawableState();
            b();
            this.h.M(this.j);
            OnCheckedChangeListener onCheckedChangeListener = this.l;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, this.j);
            }
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialCardViewStyle);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(@Nullable ColorStateList colorStateList) {
        this.h.J(colorStateList);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.h.W(colorStateList);
        invalidate();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialCardView(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r6 = com.google.android.material.card.MaterialCardView.p
            android.content.Context r8 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r8, r9, r10, r6)
            r7.<init>(r8, r9, r10)
            r8 = 0
            r7.j = r8
            r7.k = r8
            r0 = 1
            r7.i = r0
            android.content.Context r0 = r7.getContext()
            int[] r2 = com.google.android.material.R.styleable.MaterialCardView
            int[] r5 = new int[r8]
            r1 = r9
            r3 = r10
            r4 = r6
            android.content.res.TypedArray r8 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            com.google.android.material.card.a r0 = new com.google.android.material.card.a
            r0.<init>(r7, r9, r10, r6)
            r7.h = r0
            android.content.res.ColorStateList r9 = super.getCardBackgroundColor()
            r0.J(r9)
            int r9 = super.getContentPaddingLeft()
            int r10 = super.getContentPaddingTop()
            int r1 = super.getContentPaddingRight()
            int r2 = super.getContentPaddingBottom()
            r0.Y(r9, r10, r1, r2)
            r0.G(r8)
            r8.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.card.MaterialCardView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
