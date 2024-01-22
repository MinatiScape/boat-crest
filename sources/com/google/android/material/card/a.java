package com.google.android.material.card;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class a {
    public static final double u = Math.cos(Math.toRadians(45.0d));
    public static final Drawable v;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final MaterialCardView f10242a;
    @NonNull
    public final MaterialShapeDrawable c;
    @NonNull
    public final MaterialShapeDrawable d;
    @Dimension
    public int e;
    @Dimension
    public int f;
    public int g;
    @Dimension
    public int h;
    @Nullable
    public Drawable i;
    @Nullable
    public Drawable j;
    @Nullable
    public ColorStateList k;
    @Nullable
    public ColorStateList l;
    @Nullable
    public ShapeAppearanceModel m;
    @Nullable
    public ColorStateList n;
    @Nullable
    public Drawable o;
    @Nullable
    public LayerDrawable p;
    @Nullable
    public MaterialShapeDrawable q;
    @Nullable
    public MaterialShapeDrawable r;
    public boolean t;
    @NonNull
    public final Rect b = new Rect();
    public boolean s = false;

    /* renamed from: com.google.android.material.card.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0427a extends InsetDrawable {
        public C0427a(a aVar, Drawable drawable, int i, int i2, int i3, int i4) {
            super(drawable, i, i2, i3, i4);
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return -1;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return -1;
        }

        @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            return false;
        }
    }

    static {
        v = Build.VERSION.SDK_INT <= 28 ? new ColorDrawable() : null;
    }

    public a(@NonNull MaterialCardView materialCardView, AttributeSet attributeSet, int i, @StyleRes int i2) {
        this.f10242a = materialCardView;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(materialCardView.getContext(), attributeSet, i, i2);
        this.c = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(materialCardView.getContext());
        materialShapeDrawable.setShadowColor(-12303292);
        ShapeAppearanceModel.Builder builder = materialShapeDrawable.getShapeAppearanceModel().toBuilder();
        TypedArray obtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R.styleable.CardView, i, R.style.CardView);
        int i3 = R.styleable.CardView_cardCornerRadius;
        if (obtainStyledAttributes.hasValue(i3)) {
            builder.setAllCornerSizes(obtainStyledAttributes.getDimension(i3, 0.0f));
        }
        this.d = new MaterialShapeDrawable();
        V(builder.build());
        obtainStyledAttributes.recycle();
    }

    @NonNull
    public Rect A() {
        return this.b;
    }

    @NonNull
    public final Drawable B(Drawable drawable) {
        int ceil;
        int i;
        if ((Build.VERSION.SDK_INT < 21) || this.f10242a.getUseCompatPadding()) {
            int ceil2 = (int) Math.ceil(d());
            ceil = (int) Math.ceil(c());
            i = ceil2;
        } else {
            ceil = 0;
            i = 0;
        }
        return new C0427a(this, drawable, ceil, i, ceil, i);
    }

    public boolean C() {
        return this.s;
    }

    public boolean D() {
        return this.t;
    }

    public final boolean E() {
        return (this.g & 80) == 80;
    }

    public final boolean F() {
        return (this.g & GravityCompat.END) == 8388613;
    }

    public void G(@NonNull TypedArray typedArray) {
        ColorStateList colorStateList = MaterialResources.getColorStateList(this.f10242a.getContext(), typedArray, R.styleable.MaterialCardView_strokeColor);
        this.n = colorStateList;
        if (colorStateList == null) {
            this.n = ColorStateList.valueOf(-1);
        }
        this.h = typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArray.getBoolean(R.styleable.MaterialCardView_android_checkable, false);
        this.t = z;
        this.f10242a.setLongClickable(z);
        this.l = MaterialResources.getColorStateList(this.f10242a.getContext(), typedArray, R.styleable.MaterialCardView_checkedIconTint);
        N(MaterialResources.getDrawable(this.f10242a.getContext(), typedArray, R.styleable.MaterialCardView_checkedIcon));
        Q(typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_checkedIconSize, 0));
        P(typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_checkedIconMargin, 0));
        this.g = typedArray.getInteger(R.styleable.MaterialCardView_checkedIconGravity, 8388661);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(this.f10242a.getContext(), typedArray, R.styleable.MaterialCardView_rippleColor);
        this.k = colorStateList2;
        if (colorStateList2 == null) {
            this.k = ColorStateList.valueOf(MaterialColors.getColor(this.f10242a, R.attr.colorControlHighlight));
        }
        K(MaterialResources.getColorStateList(this.f10242a.getContext(), typedArray, R.styleable.MaterialCardView_cardForegroundColor));
        g0();
        d0();
        h0();
        this.f10242a.setBackgroundInternal(B(this.c));
        Drawable r = this.f10242a.isClickable() ? r() : this.d;
        this.i = r;
        this.f10242a.setForeground(B(r));
    }

    public void H(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (this.p != null) {
            int i10 = 0;
            if ((Build.VERSION.SDK_INT < 21) || this.f10242a.getUseCompatPadding()) {
                int ceil = (int) Math.ceil(d() * 2.0f);
                i10 = (int) Math.ceil(c() * 2.0f);
                i3 = ceil;
            } else {
                i3 = 0;
            }
            if (F()) {
                i4 = ((i - this.e) - this.f) - i10;
            } else {
                i4 = this.e;
            }
            if (E()) {
                i5 = this.e;
            } else {
                i5 = ((i2 - this.e) - this.f) - i3;
            }
            int i11 = i5;
            if (F()) {
                i6 = this.e;
            } else {
                i6 = ((i - this.e) - this.f) - i10;
            }
            if (E()) {
                i7 = ((i2 - this.e) - this.f) - i3;
            } else {
                i7 = this.e;
            }
            int i12 = i7;
            if (ViewCompat.getLayoutDirection(this.f10242a) == 1) {
                i9 = i6;
                i8 = i4;
            } else {
                i8 = i6;
                i9 = i4;
            }
            this.p.setLayerInset(2, i9, i12, i8, i11);
        }
    }

    public void I(boolean z) {
        this.s = z;
    }

    public void J(ColorStateList colorStateList) {
        this.c.setFillColor(colorStateList);
    }

    public void K(@Nullable ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.d;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        materialShapeDrawable.setFillColor(colorStateList);
    }

    public void L(boolean z) {
        this.t = z;
    }

    public void M(boolean z) {
        Drawable drawable = this.j;
        if (drawable != null) {
            drawable.setAlpha(z ? 255 : 0);
        }
    }

    public void N(@Nullable Drawable drawable) {
        if (drawable != null) {
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            this.j = mutate;
            DrawableCompat.setTintList(mutate, this.l);
            M(this.f10242a.isChecked());
        } else {
            this.j = v;
        }
        LayerDrawable layerDrawable = this.p;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(R.id.mtrl_card_checked_layer_id, this.j);
        }
    }

    public void O(int i) {
        this.g = i;
        H(this.f10242a.getMeasuredWidth(), this.f10242a.getMeasuredHeight());
    }

    public void P(@Dimension int i) {
        this.e = i;
    }

    public void Q(@Dimension int i) {
        this.f = i;
    }

    public void R(@Nullable ColorStateList colorStateList) {
        this.l = colorStateList;
        Drawable drawable = this.j;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, colorStateList);
        }
    }

    public void S(float f) {
        V(this.m.withCornerSize(f));
        this.i.invalidateSelf();
        if (a0() || Z()) {
            c0();
        }
        if (a0()) {
            f0();
        }
    }

    public void T(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.c.setInterpolation(f);
        MaterialShapeDrawable materialShapeDrawable = this.d;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setInterpolation(f);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.r;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setInterpolation(f);
        }
    }

    public void U(@Nullable ColorStateList colorStateList) {
        this.k = colorStateList;
        g0();
    }

    public void V(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.m = shapeAppearanceModel;
        this.c.setShapeAppearanceModel(shapeAppearanceModel);
        MaterialShapeDrawable materialShapeDrawable = this.c;
        materialShapeDrawable.setShadowBitmapDrawingEnable(!materialShapeDrawable.isRoundRect());
        MaterialShapeDrawable materialShapeDrawable2 = this.d;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel);
        }
        MaterialShapeDrawable materialShapeDrawable3 = this.r;
        if (materialShapeDrawable3 != null) {
            materialShapeDrawable3.setShapeAppearanceModel(shapeAppearanceModel);
        }
        MaterialShapeDrawable materialShapeDrawable4 = this.q;
        if (materialShapeDrawable4 != null) {
            materialShapeDrawable4.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    public void W(ColorStateList colorStateList) {
        if (this.n == colorStateList) {
            return;
        }
        this.n = colorStateList;
        h0();
    }

    public void X(@Dimension int i) {
        if (i == this.h) {
            return;
        }
        this.h = i;
        h0();
    }

    public void Y(int i, int i2, int i3, int i4) {
        this.b.set(i, i2, i3, i4);
        c0();
    }

    public final boolean Z() {
        return this.f10242a.getPreventCornerOverlap() && !e();
    }

    public final float a() {
        return Math.max(Math.max(b(this.m.getTopLeftCorner(), this.c.getTopLeftCornerResolvedSize()), b(this.m.getTopRightCorner(), this.c.getTopRightCornerResolvedSize())), Math.max(b(this.m.getBottomRightCorner(), this.c.getBottomRightCornerResolvedSize()), b(this.m.getBottomLeftCorner(), this.c.getBottomLeftCornerResolvedSize())));
    }

    public final boolean a0() {
        return this.f10242a.getPreventCornerOverlap() && e() && this.f10242a.getUseCompatPadding();
    }

    public final float b(CornerTreatment cornerTreatment, float f) {
        if (cornerTreatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - u) * f);
        }
        if (cornerTreatment instanceof CutCornerTreatment) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public void b0() {
        Drawable drawable = this.i;
        Drawable r = this.f10242a.isClickable() ? r() : this.d;
        this.i = r;
        if (drawable != r) {
            e0(r);
        }
    }

    public final float c() {
        return this.f10242a.getMaxCardElevation() + (a0() ? a() : 0.0f);
    }

    public void c0() {
        int a2 = (int) ((Z() || a0() ? a() : 0.0f) - t());
        MaterialCardView materialCardView = this.f10242a;
        Rect rect = this.b;
        materialCardView.c(rect.left + a2, rect.top + a2, rect.right + a2, rect.bottom + a2);
    }

    public final float d() {
        return (this.f10242a.getMaxCardElevation() * 1.5f) + (a0() ? a() : 0.0f);
    }

    public void d0() {
        this.c.setElevation(this.f10242a.getCardElevation());
    }

    public final boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.c.isRoundRect();
    }

    public final void e0(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 23 && (this.f10242a.getForeground() instanceof InsetDrawable)) {
            ((InsetDrawable) this.f10242a.getForeground()).setDrawable(drawable);
        } else {
            this.f10242a.setForeground(B(drawable));
        }
    }

    @NonNull
    public final Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        MaterialShapeDrawable h = h();
        this.q = h;
        h.setFillColor(this.k);
        stateListDrawable.addState(new int[]{16842919}, this.q);
        return stateListDrawable;
    }

    public void f0() {
        if (!C()) {
            this.f10242a.setBackgroundInternal(B(this.c));
        }
        this.f10242a.setForeground(B(this.i));
    }

    @NonNull
    public final Drawable g() {
        if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
            this.r = h();
            return new RippleDrawable(this.k, null, this.r);
        }
        return f();
    }

    public final void g0() {
        Drawable drawable;
        if (RippleUtils.USE_FRAMEWORK_RIPPLE && (drawable = this.o) != null) {
            ((RippleDrawable) drawable).setColor(this.k);
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = this.q;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setFillColor(this.k);
        }
    }

    @NonNull
    public final MaterialShapeDrawable h() {
        return new MaterialShapeDrawable(this.m);
    }

    public void h0() {
        this.d.setStroke(this.h, this.n);
    }

    @RequiresApi(api = 23)
    public void i() {
        Drawable drawable = this.o;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i = bounds.bottom;
            this.o.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
            this.o.setBounds(bounds.left, bounds.top, bounds.right, i);
        }
    }

    @NonNull
    public MaterialShapeDrawable j() {
        return this.c;
    }

    public ColorStateList k() {
        return this.c.getFillColor();
    }

    public ColorStateList l() {
        return this.d.getFillColor();
    }

    @Nullable
    public Drawable m() {
        return this.j;
    }

    public int n() {
        return this.g;
    }

    @Dimension
    public int o() {
        return this.e;
    }

    @Dimension
    public int p() {
        return this.f;
    }

    @Nullable
    public ColorStateList q() {
        return this.l;
    }

    @NonNull
    public final Drawable r() {
        if (this.o == null) {
            this.o = g();
        }
        if (this.p == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.o, this.d, this.j});
            this.p = layerDrawable;
            layerDrawable.setId(2, R.id.mtrl_card_checked_layer_id);
        }
        return this.p;
    }

    public float s() {
        return this.c.getTopLeftCornerResolvedSize();
    }

    public final float t() {
        if (this.f10242a.getPreventCornerOverlap()) {
            if (Build.VERSION.SDK_INT < 21 || this.f10242a.getUseCompatPadding()) {
                return (float) ((1.0d - u) * this.f10242a.getCardViewRadius());
            }
            return 0.0f;
        }
        return 0.0f;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float u() {
        return this.c.getInterpolation();
    }

    @Nullable
    public ColorStateList v() {
        return this.k;
    }

    public ShapeAppearanceModel w() {
        return this.m;
    }

    @ColorInt
    public int x() {
        ColorStateList colorStateList = this.n;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    @Nullable
    public ColorStateList y() {
        return this.n;
    }

    @Dimension
    public int z() {
        return this.h;
    }
}
