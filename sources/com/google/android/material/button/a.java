package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class a {
    @ChecksSdkIntAtLeast(api = 21)
    public static final boolean t;
    public static final boolean u;

    /* renamed from: a  reason: collision with root package name */
    public final MaterialButton f10241a;
    @NonNull
    public ShapeAppearanceModel b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    @Nullable
    public PorterDuff.Mode i;
    @Nullable
    public ColorStateList j;
    @Nullable
    public ColorStateList k;
    @Nullable
    public ColorStateList l;
    @Nullable
    public Drawable m;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q;
    public LayerDrawable r;
    public int s;

    static {
        int i = Build.VERSION.SDK_INT;
        boolean z = true;
        t = i >= 21;
        if (i < 21 || i > 22) {
            z = false;
        }
        u = z;
    }

    public a(MaterialButton materialButton, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.f10241a = materialButton;
        this.b = shapeAppearanceModel;
    }

    public void A(@Nullable ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            I();
        }
    }

    public void B(int i) {
        if (this.h != i) {
            this.h = i;
            I();
        }
    }

    public void C(@Nullable ColorStateList colorStateList) {
        if (this.j != colorStateList) {
            this.j = colorStateList;
            if (f() != null) {
                DrawableCompat.setTintList(f(), this.j);
            }
        }
    }

    public void D(@Nullable PorterDuff.Mode mode) {
        if (this.i != mode) {
            this.i = mode;
            if (f() == null || this.i == null) {
                return;
            }
            DrawableCompat.setTintMode(f(), this.i);
        }
    }

    public final void E(@Dimension int i, @Dimension int i2) {
        int paddingStart = ViewCompat.getPaddingStart(this.f10241a);
        int paddingTop = this.f10241a.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.f10241a);
        int paddingBottom = this.f10241a.getPaddingBottom();
        int i3 = this.e;
        int i4 = this.f;
        this.f = i2;
        this.e = i;
        if (!this.o) {
            F();
        }
        ViewCompat.setPaddingRelative(this.f10241a, paddingStart, (paddingTop + i) - i3, paddingEnd, (paddingBottom + i2) - i4);
    }

    public final void F() {
        this.f10241a.setInternalBackground(a());
        MaterialShapeDrawable f = f();
        if (f != null) {
            f.setElevation(this.s);
        }
    }

    public final void G(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (u && !this.o) {
            int paddingStart = ViewCompat.getPaddingStart(this.f10241a);
            int paddingTop = this.f10241a.getPaddingTop();
            int paddingEnd = ViewCompat.getPaddingEnd(this.f10241a);
            int paddingBottom = this.f10241a.getPaddingBottom();
            F();
            ViewCompat.setPaddingRelative(this.f10241a, paddingStart, paddingTop, paddingEnd, paddingBottom);
            return;
        }
        if (f() != null) {
            f().setShapeAppearanceModel(shapeAppearanceModel);
        }
        if (n() != null) {
            n().setShapeAppearanceModel(shapeAppearanceModel);
        }
        if (e() != null) {
            e().setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    public void H(int i, int i2) {
        Drawable drawable = this.m;
        if (drawable != null) {
            drawable.setBounds(this.c, this.e, i2 - this.d, i - this.f);
        }
    }

    public final void I() {
        MaterialShapeDrawable f = f();
        MaterialShapeDrawable n = n();
        if (f != null) {
            f.setStroke(this.h, this.k);
            if (n != null) {
                n.setStroke(this.h, this.n ? MaterialColors.getColor(this.f10241a, R.attr.colorSurface) : 0);
            }
        }
    }

    @NonNull
    public final InsetDrawable J(Drawable drawable) {
        return new InsetDrawable(drawable, this.c, this.e, this.d, this.f);
    }

    public final Drawable a() {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.b);
        materialShapeDrawable.initializeElevationOverlay(this.f10241a.getContext());
        DrawableCompat.setTintList(materialShapeDrawable, this.j);
        PorterDuff.Mode mode = this.i;
        if (mode != null) {
            DrawableCompat.setTintMode(materialShapeDrawable, mode);
        }
        materialShapeDrawable.setStroke(this.h, this.k);
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(this.b);
        materialShapeDrawable2.setTint(0);
        materialShapeDrawable2.setStroke(this.h, this.n ? MaterialColors.getColor(this.f10241a, R.attr.colorSurface) : 0);
        if (t) {
            MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(this.b);
            this.m = materialShapeDrawable3;
            DrawableCompat.setTint(materialShapeDrawable3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.l), J(new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable})), this.m);
            this.r = rippleDrawable;
            return rippleDrawable;
        }
        RippleDrawableCompat rippleDrawableCompat = new RippleDrawableCompat(this.b);
        this.m = rippleDrawableCompat;
        DrawableCompat.setTintList(rippleDrawableCompat, RippleUtils.sanitizeRippleDrawableColor(this.l));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable, this.m});
        this.r = layerDrawable;
        return J(layerDrawable);
    }

    public int b() {
        return this.g;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.e;
    }

    @Nullable
    public Shapeable e() {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        if (this.r.getNumberOfLayers() > 2) {
            return (Shapeable) this.r.getDrawable(2);
        }
        return (Shapeable) this.r.getDrawable(1);
    }

    @Nullable
    public MaterialShapeDrawable f() {
        return g(false);
    }

    @Nullable
    public final MaterialShapeDrawable g(boolean z) {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        if (t) {
            return (MaterialShapeDrawable) ((LayerDrawable) ((InsetDrawable) this.r.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
        }
        return (MaterialShapeDrawable) this.r.getDrawable(!z ? 1 : 0);
    }

    @Nullable
    public ColorStateList h() {
        return this.l;
    }

    @NonNull
    public ShapeAppearanceModel i() {
        return this.b;
    }

    @Nullable
    public ColorStateList j() {
        return this.k;
    }

    public int k() {
        return this.h;
    }

    public ColorStateList l() {
        return this.j;
    }

    public PorterDuff.Mode m() {
        return this.i;
    }

    @Nullable
    public final MaterialShapeDrawable n() {
        return g(true);
    }

    public boolean o() {
        return this.o;
    }

    public boolean p() {
        return this.q;
    }

    public void q(@NonNull TypedArray typedArray) {
        this.c = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        this.d = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        this.e = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        this.f = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        int i = R.styleable.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i, -1);
            this.g = dimensionPixelSize;
            y(this.b.withCornerSize(dimensionPixelSize));
            this.p = true;
        }
        this.h = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        this.i = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.j = MaterialResources.getColorStateList(this.f10241a.getContext(), typedArray, R.styleable.MaterialButton_backgroundTint);
        this.k = MaterialResources.getColorStateList(this.f10241a.getContext(), typedArray, R.styleable.MaterialButton_strokeColor);
        this.l = MaterialResources.getColorStateList(this.f10241a.getContext(), typedArray, R.styleable.MaterialButton_rippleColor);
        this.q = typedArray.getBoolean(R.styleable.MaterialButton_android_checkable, false);
        this.s = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_elevation, 0);
        int paddingStart = ViewCompat.getPaddingStart(this.f10241a);
        int paddingTop = this.f10241a.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.f10241a);
        int paddingBottom = this.f10241a.getPaddingBottom();
        if (typedArray.hasValue(R.styleable.MaterialButton_android_background)) {
            s();
        } else {
            F();
        }
        ViewCompat.setPaddingRelative(this.f10241a, paddingStart + this.c, paddingTop + this.e, paddingEnd + this.d, paddingBottom + this.f);
    }

    public void r(int i) {
        if (f() != null) {
            f().setTint(i);
        }
    }

    public void s() {
        this.o = true;
        this.f10241a.setSupportBackgroundTintList(this.j);
        this.f10241a.setSupportBackgroundTintMode(this.i);
    }

    public void t(boolean z) {
        this.q = z;
    }

    public void u(int i) {
        if (this.p && this.g == i) {
            return;
        }
        this.g = i;
        this.p = true;
        y(this.b.withCornerSize(i));
    }

    public void v(@Dimension int i) {
        E(this.e, i);
    }

    public void w(@Dimension int i) {
        E(i, this.f);
    }

    public void x(@Nullable ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            boolean z = t;
            if (z && (this.f10241a.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f10241a.getBackground()).setColor(RippleUtils.sanitizeRippleDrawableColor(colorStateList));
            } else if (z || !(this.f10241a.getBackground() instanceof RippleDrawableCompat)) {
            } else {
                ((RippleDrawableCompat) this.f10241a.getBackground()).setTintList(RippleUtils.sanitizeRippleDrawableColor(colorStateList));
            }
        }
    }

    public void y(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.b = shapeAppearanceModel;
        G(shapeAppearanceModel);
    }

    public void z(boolean z) {
        this.n = z;
        I();
    }
}
