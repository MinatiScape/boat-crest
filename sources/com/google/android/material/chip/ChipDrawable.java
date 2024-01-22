package com.google.android.material.chip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.AnimatorRes;
import androidx.annotation.AttrRes;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.XmlRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.ref.WeakReference;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class ChipDrawable extends MaterialShapeDrawable implements Drawable.Callback, TextDrawableHelper.TextDrawableDelegate {
    public static final int[] Q0 = {16842910};
    public static final ShapeDrawable R0 = new ShapeDrawable(new OvalShape());
    @ColorInt
    public int A0;
    public boolean B0;
    @ColorInt
    public int C0;
    public int D0;
    @Nullable
    public ColorFilter E0;
    @Nullable
    public PorterDuffColorFilter F0;
    @Nullable
    public ColorStateList G;
    @Nullable
    public ColorStateList G0;
    @Nullable
    public ColorStateList H;
    @Nullable
    public PorterDuff.Mode H0;
    public float I;
    public int[] I0;
    public float J;
    public boolean J0;
    @Nullable
    public ColorStateList K;
    @Nullable
    public ColorStateList K0;
    public float L;
    @NonNull
    public WeakReference<Delegate> L0;
    @Nullable
    public ColorStateList M;
    public TextUtils.TruncateAt M0;
    @Nullable
    public CharSequence N;
    public boolean N0;
    public boolean O;
    public int O0;
    @Nullable
    public Drawable P;
    public boolean P0;
    @Nullable
    public ColorStateList Q;
    public float R;
    public boolean S;
    public boolean T;
    @Nullable
    public Drawable U;
    @Nullable
    public Drawable V;
    @Nullable
    public ColorStateList W;
    public float X;
    @Nullable
    public CharSequence Y;
    public boolean Z;
    public boolean a0;
    @Nullable
    public Drawable b0;
    @Nullable
    public ColorStateList c0;
    @Nullable
    public MotionSpec d0;
    @Nullable
    public MotionSpec e0;
    public float f0;
    public float g0;
    public float h0;
    public float i0;
    public float j0;
    public float k0;
    public float l0;
    public float m0;
    @NonNull
    public final Context n0;
    public final Paint o0;
    @Nullable
    public final Paint p0;
    public final Paint.FontMetrics q0;
    public final RectF r0;
    public final PointF s0;
    public final Path t0;
    @NonNull
    public final TextDrawableHelper u0;
    @ColorInt
    public int v0;
    @ColorInt
    public int w0;
    @ColorInt
    public int x0;
    @ColorInt
    public int y0;
    @ColorInt
    public int z0;

    /* loaded from: classes10.dex */
    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    public ChipDrawable(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
        this.J = -1.0f;
        this.o0 = new Paint(1);
        this.q0 = new Paint.FontMetrics();
        this.r0 = new RectF();
        this.s0 = new PointF();
        this.t0 = new Path();
        this.D0 = 255;
        this.H0 = PorterDuff.Mode.SRC_IN;
        this.L0 = new WeakReference<>(null);
        initializeElevationOverlay(context);
        this.n0 = context;
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.u0 = textDrawableHelper;
        this.N = "";
        textDrawableHelper.getTextPaint().density = context.getResources().getDisplayMetrics().density;
        this.p0 = null;
        int[] iArr = Q0;
        setState(iArr);
        setCloseIconState(iArr);
        this.N0 = true;
        if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
            R0.setTint(-1);
        }
    }

    public static boolean V(@Nullable int[] iArr, @AttrRes int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean W(@Nullable ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean X(@Nullable Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public static boolean Y(@Nullable TextAppearance textAppearance) {
        return (textAppearance == null || textAppearance.getTextColor() == null || !textAppearance.getTextColor().isStateful()) ? false : true;
    }

    @NonNull
    public static ChipDrawable createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        ChipDrawable chipDrawable = new ChipDrawable(context, attributeSet, i, i2);
        chipDrawable.Z(attributeSet, i, i2);
        return chipDrawable;
    }

    @NonNull
    public static ChipDrawable createFromResource(@NonNull Context context, @XmlRes int i) {
        AttributeSet parseDrawableXml = DrawableUtils.parseDrawableXml(context, i, "chip");
        int styleAttribute = parseDrawableXml.getStyleAttribute();
        if (styleAttribute == 0) {
            styleAttribute = R.style.Widget_MaterialComponents_Chip_Entry;
        }
        return createFromAttributes(context, parseDrawableXml, R.attr.chipStandaloneStyle, styleAttribute);
    }

    public float A() {
        if (f0() || e0()) {
            return this.g0 + T() + this.h0;
        }
        return 0.0f;
    }

    public final void B(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.set(rect);
        if (g0()) {
            float f = this.m0 + this.l0 + this.X + this.k0 + this.j0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = rect.right - f;
            } else {
                rectF.left = rect.left + f;
            }
        }
    }

    public final void C(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (g0()) {
            float f = this.m0 + this.l0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.right - f;
                rectF.right = f2;
                rectF.left = f2 - this.X;
            } else {
                float f3 = rect.left + f;
                rectF.left = f3;
                rectF.right = f3 + this.X;
            }
            float exactCenterY = rect.exactCenterY();
            float f4 = this.X;
            float f5 = exactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    public final void D(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (g0()) {
            float f = this.m0 + this.l0 + this.X + this.k0 + this.j0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.right;
                rectF.right = f2;
                rectF.left = f2 - f;
            } else {
                int i = rect.left;
                rectF.left = i;
                rectF.right = i + f;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public float E() {
        if (g0()) {
            return this.k0 + this.X + this.l0;
        }
        return 0.0f;
    }

    public final void F(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (this.N != null) {
            float A = this.f0 + A() + this.i0;
            float E = this.m0 + E() + this.j0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = rect.left + A;
                rectF.right = rect.right - E;
            } else {
                rectF.left = rect.left + E;
                rectF.right = rect.right - A;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public final float G() {
        this.u0.getTextPaint().getFontMetrics(this.q0);
        Paint.FontMetrics fontMetrics = this.q0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    @NonNull
    public Paint.Align H(@NonNull Rect rect, @NonNull PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.N != null) {
            float A = this.f0 + A() + this.i0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                pointF.x = rect.left + A;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - A;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - G();
        }
        return align;
    }

    public final boolean I() {
        return this.a0 && this.b0 != null && this.Z;
    }

    public final void J(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (e0()) {
            z(rect, this.r0);
            RectF rectF = this.r0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.b0.setBounds(0, 0, (int) this.r0.width(), (int) this.r0.height());
            this.b0.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    public final void K(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.P0) {
            return;
        }
        this.o0.setColor(this.w0);
        this.o0.setStyle(Paint.Style.FILL);
        this.o0.setColorFilter(U());
        this.r0.set(rect);
        canvas.drawRoundRect(this.r0, getChipCornerRadius(), getChipCornerRadius(), this.o0);
    }

    public final void L(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (f0()) {
            z(rect, this.r0);
            RectF rectF = this.r0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.P.setBounds(0, 0, (int) this.r0.width(), (int) this.r0.height());
            this.P.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    public final void M(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.L <= 0.0f || this.P0) {
            return;
        }
        this.o0.setColor(this.y0);
        this.o0.setStyle(Paint.Style.STROKE);
        if (!this.P0) {
            this.o0.setColorFilter(U());
        }
        RectF rectF = this.r0;
        float f = this.L;
        rectF.set(rect.left + (f / 2.0f), rect.top + (f / 2.0f), rect.right - (f / 2.0f), rect.bottom - (f / 2.0f));
        float f2 = this.J - (this.L / 2.0f);
        canvas.drawRoundRect(this.r0, f2, f2, this.o0);
    }

    public final void N(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.P0) {
            return;
        }
        this.o0.setColor(this.v0);
        this.o0.setStyle(Paint.Style.FILL);
        this.r0.set(rect);
        canvas.drawRoundRect(this.r0, getChipCornerRadius(), getChipCornerRadius(), this.o0);
    }

    public final void O(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (g0()) {
            C(rect, this.r0);
            RectF rectF = this.r0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.U.setBounds(0, 0, (int) this.r0.width(), (int) this.r0.height());
            if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
                this.V.setBounds(this.U.getBounds());
                this.V.jumpToCurrentState();
                this.V.draw(canvas);
            } else {
                this.U.draw(canvas);
            }
            canvas.translate(-f, -f2);
        }
    }

    public final void P(@NonNull Canvas canvas, @NonNull Rect rect) {
        this.o0.setColor(this.z0);
        this.o0.setStyle(Paint.Style.FILL);
        this.r0.set(rect);
        if (!this.P0) {
            canvas.drawRoundRect(this.r0, getChipCornerRadius(), getChipCornerRadius(), this.o0);
            return;
        }
        calculatePathForSize(new RectF(rect), this.t0);
        super.drawShape(canvas, this.o0, this.t0, getBoundsAsRectF());
    }

    public final void Q(@NonNull Canvas canvas, @NonNull Rect rect) {
        Paint paint = this.p0;
        if (paint != null) {
            paint.setColor(ColorUtils.setAlphaComponent(ViewCompat.MEASURED_STATE_MASK, 127));
            canvas.drawRect(rect, this.p0);
            if (f0() || e0()) {
                z(rect, this.r0);
                canvas.drawRect(this.r0, this.p0);
            }
            if (this.N != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.p0);
            }
            if (g0()) {
                C(rect, this.r0);
                canvas.drawRect(this.r0, this.p0);
            }
            this.p0.setColor(ColorUtils.setAlphaComponent(SupportMenu.CATEGORY_MASK, 127));
            B(rect, this.r0);
            canvas.drawRect(this.r0, this.p0);
            this.p0.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
            D(rect, this.r0);
            canvas.drawRect(this.r0, this.p0);
        }
    }

    public final void R(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.N != null) {
            Paint.Align H = H(rect, this.s0);
            F(rect, this.r0);
            if (this.u0.getTextAppearance() != null) {
                this.u0.getTextPaint().drawableState = getState();
                this.u0.updateTextPaintDrawState(this.n0);
            }
            this.u0.getTextPaint().setTextAlign(H);
            int i = 0;
            boolean z = Math.round(this.u0.getTextWidth(getText().toString())) > Math.round(this.r0.width());
            if (z) {
                i = canvas.save();
                canvas.clipRect(this.r0);
            }
            CharSequence charSequence = this.N;
            if (z && this.M0 != null) {
                charSequence = TextUtils.ellipsize(charSequence, this.u0.getTextPaint(), this.r0.width(), this.M0);
            }
            CharSequence charSequence2 = charSequence;
            int length = charSequence2.length();
            PointF pointF = this.s0;
            canvas.drawText(charSequence2, 0, length, pointF.x, pointF.y, this.u0.getTextPaint());
            if (z) {
                canvas.restoreToCount(i);
            }
        }
    }

    public final float S() {
        Drawable drawable = this.B0 ? this.b0 : this.P;
        float f = this.R;
        if (f <= 0.0f && drawable != null) {
            f = (float) Math.ceil(ViewUtils.dpToPx(this.n0, 24));
            if (drawable.getIntrinsicHeight() <= f) {
                return drawable.getIntrinsicHeight();
            }
        }
        return f;
    }

    public final float T() {
        Drawable drawable = this.B0 ? this.b0 : this.P;
        float f = this.R;
        return (f > 0.0f || drawable == null) ? f : drawable.getIntrinsicWidth();
    }

    @Nullable
    public final ColorFilter U() {
        ColorFilter colorFilter = this.E0;
        return colorFilter != null ? colorFilter : this.F0;
    }

    public final void Z(@Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(this.n0, attributeSet, R.styleable.Chip, i, i2, new int[0]);
        this.P0 = obtainStyledAttributes.hasValue(R.styleable.Chip_shapeAppearance);
        b0(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, R.styleable.Chip_chipSurfaceColor));
        setChipBackgroundColor(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, R.styleable.Chip_chipBackgroundColor));
        setChipMinHeight(obtainStyledAttributes.getDimension(R.styleable.Chip_chipMinHeight, 0.0f));
        int i3 = R.styleable.Chip_chipCornerRadius;
        if (obtainStyledAttributes.hasValue(i3)) {
            setChipCornerRadius(obtainStyledAttributes.getDimension(i3, 0.0f));
        }
        setChipStrokeColor(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, R.styleable.Chip_chipStrokeColor));
        setChipStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.Chip_chipStrokeWidth, 0.0f));
        setRippleColor(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, R.styleable.Chip_rippleColor));
        setText(obtainStyledAttributes.getText(R.styleable.Chip_android_text));
        TextAppearance textAppearance = MaterialResources.getTextAppearance(this.n0, obtainStyledAttributes, R.styleable.Chip_android_textAppearance);
        textAppearance.setTextSize(obtainStyledAttributes.getDimension(R.styleable.Chip_android_textSize, textAppearance.getTextSize()));
        if (Build.VERSION.SDK_INT < 23) {
            textAppearance.setTextColor(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, R.styleable.Chip_android_textColor));
        }
        setTextAppearance(textAppearance);
        int i4 = obtainStyledAttributes.getInt(R.styleable.Chip_android_ellipsize, 0);
        if (i4 == 1) {
            setEllipsize(TextUtils.TruncateAt.START);
        } else if (i4 == 2) {
            setEllipsize(TextUtils.TruncateAt.MIDDLE);
        } else if (i4 == 3) {
            setEllipsize(TextUtils.TruncateAt.END);
        }
        setChipIconVisible(obtainStyledAttributes.getBoolean(R.styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            setChipIconVisible(obtainStyledAttributes.getBoolean(R.styleable.Chip_chipIconEnabled, false));
        }
        setChipIcon(MaterialResources.getDrawable(this.n0, obtainStyledAttributes, R.styleable.Chip_chipIcon));
        int i5 = R.styleable.Chip_chipIconTint;
        if (obtainStyledAttributes.hasValue(i5)) {
            setChipIconTint(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, i5));
        }
        setChipIconSize(obtainStyledAttributes.getDimension(R.styleable.Chip_chipIconSize, -1.0f));
        setCloseIconVisible(obtainStyledAttributes.getBoolean(R.styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            setCloseIconVisible(obtainStyledAttributes.getBoolean(R.styleable.Chip_closeIconEnabled, false));
        }
        setCloseIcon(MaterialResources.getDrawable(this.n0, obtainStyledAttributes, R.styleable.Chip_closeIcon));
        setCloseIconTint(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, R.styleable.Chip_closeIconTint));
        setCloseIconSize(obtainStyledAttributes.getDimension(R.styleable.Chip_closeIconSize, 0.0f));
        setCheckable(obtainStyledAttributes.getBoolean(R.styleable.Chip_android_checkable, false));
        setCheckedIconVisible(obtainStyledAttributes.getBoolean(R.styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            setCheckedIconVisible(obtainStyledAttributes.getBoolean(R.styleable.Chip_checkedIconEnabled, false));
        }
        setCheckedIcon(MaterialResources.getDrawable(this.n0, obtainStyledAttributes, R.styleable.Chip_checkedIcon));
        int i6 = R.styleable.Chip_checkedIconTint;
        if (obtainStyledAttributes.hasValue(i6)) {
            setCheckedIconTint(MaterialResources.getColorStateList(this.n0, obtainStyledAttributes, i6));
        }
        setShowMotionSpec(MotionSpec.createFromAttribute(this.n0, obtainStyledAttributes, R.styleable.Chip_showMotionSpec));
        setHideMotionSpec(MotionSpec.createFromAttribute(this.n0, obtainStyledAttributes, R.styleable.Chip_hideMotionSpec));
        setChipStartPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_chipStartPadding, 0.0f));
        setIconStartPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_iconStartPadding, 0.0f));
        setIconEndPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_iconEndPadding, 0.0f));
        setTextStartPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_textStartPadding, 0.0f));
        setTextEndPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_textEndPadding, 0.0f));
        setCloseIconStartPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_closeIconStartPadding, 0.0f));
        setCloseIconEndPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_closeIconEndPadding, 0.0f));
        setChipEndPadding(obtainStyledAttributes.getDimension(R.styleable.Chip_chipEndPadding, 0.0f));
        setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
        obtainStyledAttributes.recycle();
    }

    public final boolean a0(@NonNull int[] iArr, @NonNull int[] iArr2) {
        boolean z;
        boolean onStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.G;
        int compositeElevationOverlayIfNeeded = compositeElevationOverlayIfNeeded(colorStateList != null ? colorStateList.getColorForState(iArr, this.v0) : 0);
        boolean z2 = true;
        if (this.v0 != compositeElevationOverlayIfNeeded) {
            this.v0 = compositeElevationOverlayIfNeeded;
            onStateChange = true;
        }
        ColorStateList colorStateList2 = this.H;
        int compositeElevationOverlayIfNeeded2 = compositeElevationOverlayIfNeeded(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.w0) : 0);
        if (this.w0 != compositeElevationOverlayIfNeeded2) {
            this.w0 = compositeElevationOverlayIfNeeded2;
            onStateChange = true;
        }
        int layer = MaterialColors.layer(compositeElevationOverlayIfNeeded, compositeElevationOverlayIfNeeded2);
        if ((this.x0 != layer) | (getFillColor() == null)) {
            this.x0 = layer;
            setFillColor(ColorStateList.valueOf(layer));
            onStateChange = true;
        }
        ColorStateList colorStateList3 = this.K;
        int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.y0) : 0;
        if (this.y0 != colorForState) {
            this.y0 = colorForState;
            onStateChange = true;
        }
        int colorForState2 = (this.K0 == null || !RippleUtils.shouldDrawRippleCompat(iArr)) ? 0 : this.K0.getColorForState(iArr, this.z0);
        if (this.z0 != colorForState2) {
            this.z0 = colorForState2;
            if (this.J0) {
                onStateChange = true;
            }
        }
        int colorForState3 = (this.u0.getTextAppearance() == null || this.u0.getTextAppearance().getTextColor() == null) ? 0 : this.u0.getTextAppearance().getTextColor().getColorForState(iArr, this.A0);
        if (this.A0 != colorForState3) {
            this.A0 = colorForState3;
            onStateChange = true;
        }
        boolean z3 = V(getState(), 16842912) && this.Z;
        if (this.B0 == z3 || this.b0 == null) {
            z = false;
        } else {
            float A = A();
            this.B0 = z3;
            if (A != A()) {
                onStateChange = true;
                z = true;
            } else {
                z = false;
                onStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.G0;
        int colorForState4 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.C0) : 0;
        if (this.C0 != colorForState4) {
            this.C0 = colorForState4;
            this.F0 = DrawableUtils.updateTintFilter(this, this.G0, this.H0);
        } else {
            z2 = onStateChange;
        }
        if (X(this.P)) {
            z2 |= this.P.setState(iArr);
        }
        if (X(this.b0)) {
            z2 |= this.b0.setState(iArr);
        }
        if (X(this.U)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            z2 |= this.U.setState(iArr3);
        }
        if (RippleUtils.USE_FRAMEWORK_RIPPLE && X(this.V)) {
            z2 |= this.V.setState(iArr2);
        }
        if (z2) {
            invalidateSelf();
        }
        if (z) {
            onSizeChange();
        }
        return z2;
    }

    public final void b0(@Nullable ColorStateList colorStateList) {
        if (this.G != colorStateList) {
            this.G = colorStateList;
            onStateChange(getState());
        }
    }

    public void c0(boolean z) {
        this.N0 = z;
    }

    public boolean d0() {
        return this.N0;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i = this.D0;
        int saveLayerAlpha = i < 255 ? CanvasCompat.saveLayerAlpha(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        N(canvas, bounds);
        K(canvas, bounds);
        if (this.P0) {
            super.draw(canvas);
        }
        M(canvas, bounds);
        P(canvas, bounds);
        L(canvas, bounds);
        J(canvas, bounds);
        if (this.N0) {
            R(canvas, bounds);
        }
        O(canvas, bounds);
        Q(canvas, bounds);
        if (this.D0 < 255) {
            canvas.restoreToCount(saveLayerAlpha);
        }
    }

    public final boolean e0() {
        return this.a0 && this.b0 != null && this.B0;
    }

    public final boolean f0() {
        return this.O && this.P != null;
    }

    public final boolean g0() {
        return this.T && this.U != null;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.D0;
    }

    @Nullable
    public Drawable getCheckedIcon() {
        return this.b0;
    }

    @Nullable
    public ColorStateList getCheckedIconTint() {
        return this.c0;
    }

    @Nullable
    public ColorStateList getChipBackgroundColor() {
        return this.H;
    }

    public float getChipCornerRadius() {
        return this.P0 ? getTopLeftCornerResolvedSize() : this.J;
    }

    public float getChipEndPadding() {
        return this.m0;
    }

    @Nullable
    public Drawable getChipIcon() {
        Drawable drawable = this.P;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    public float getChipIconSize() {
        return this.R;
    }

    @Nullable
    public ColorStateList getChipIconTint() {
        return this.Q;
    }

    public float getChipMinHeight() {
        return this.I;
    }

    public float getChipStartPadding() {
        return this.f0;
    }

    @Nullable
    public ColorStateList getChipStrokeColor() {
        return this.K;
    }

    public float getChipStrokeWidth() {
        return this.L;
    }

    public void getChipTouchBounds(@NonNull RectF rectF) {
        B(getBounds(), rectF);
    }

    @Nullable
    public Drawable getCloseIcon() {
        Drawable drawable = this.U;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    @Nullable
    public CharSequence getCloseIconContentDescription() {
        return this.Y;
    }

    public float getCloseIconEndPadding() {
        return this.l0;
    }

    public float getCloseIconSize() {
        return this.X;
    }

    public float getCloseIconStartPadding() {
        return this.k0;
    }

    @NonNull
    public int[] getCloseIconState() {
        return this.I0;
    }

    @Nullable
    public ColorStateList getCloseIconTint() {
        return this.W;
    }

    public void getCloseIconTouchBounds(@NonNull RectF rectF) {
        D(getBounds(), rectF);
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public ColorFilter getColorFilter() {
        return this.E0;
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.M0;
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.e0;
    }

    public float getIconEndPadding() {
        return this.h0;
    }

    public float getIconStartPadding() {
        return this.g0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.I;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.f0 + A() + this.i0 + this.u0.getTextWidth(getText().toString()) + this.j0 + E() + this.m0), this.O0);
    }

    @Px
    public int getMaxWidth() {
        return this.O0;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        if (this.P0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.J);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.J);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    @Nullable
    public ColorStateList getRippleColor() {
        return this.M;
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.d0;
    }

    @Nullable
    public CharSequence getText() {
        return this.N;
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.u0.getTextAppearance();
    }

    public float getTextEndPadding() {
        return this.j0;
    }

    public float getTextStartPadding() {
        return this.i0;
    }

    public boolean getUseCompatRipple() {
        return this.J0;
    }

    public final void h0(@Nullable Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public final void i0() {
        this.K0 = this.J0 ? RippleUtils.sanitizeRippleDrawableColor(this.M) : null;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public boolean isCheckable() {
        return this.Z;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        return this.a0;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        return this.O;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public boolean isCloseIconStateful() {
        return X(this.U);
    }

    public boolean isCloseIconVisible() {
        return this.T;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return W(this.G) || W(this.H) || W(this.K) || (this.J0 && W(this.K0)) || Y(this.u0.getTextAppearance()) || I() || X(this.P) || X(this.b0) || W(this.G0);
    }

    @TargetApi(21)
    public final void j0() {
        this.V = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(getRippleColor()), this.U, R0);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (f0()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.P, i);
        }
        if (e0()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.b0, i);
        }
        if (g0()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.U, i);
        }
        if (onLayoutDirectionChanged) {
            invalidateSelf();
            return true;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (f0()) {
            onLevelChange |= this.P.setLevel(i);
        }
        if (e0()) {
            onLevelChange |= this.b0.setLevel(i);
        }
        if (g0()) {
            onLevelChange |= this.U.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    public void onSizeChange() {
        Delegate delegate = this.L0.get();
        if (delegate != null) {
            delegate.onChipDrawableSizeChange();
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(@NonNull int[] iArr) {
        if (this.P0) {
            super.onStateChange(iArr);
        }
        return a0(iArr, getCloseIconState());
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        onSizeChange();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.D0 != i) {
            this.D0 = i;
            invalidateSelf();
        }
    }

    public void setCheckable(boolean z) {
        if (this.Z != z) {
            this.Z = z;
            float A = A();
            if (!z && this.B0) {
                this.B0 = false;
            }
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                onSizeChange();
            }
        }
    }

    public void setCheckableResource(@BoolRes int i) {
        setCheckable(this.n0.getResources().getBoolean(i));
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        if (this.b0 != drawable) {
            float A = A();
            this.b0 = drawable;
            float A2 = A();
            h0(this.b0);
            y(this.b0);
            invalidateSelf();
            if (A != A2) {
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(@BoolRes int i) {
        setCheckedIconVisible(this.n0.getResources().getBoolean(i));
    }

    public void setCheckedIconResource(@DrawableRes int i) {
        setCheckedIcon(AppCompatResources.getDrawable(this.n0, i));
    }

    public void setCheckedIconTint(@Nullable ColorStateList colorStateList) {
        if (this.c0 != colorStateList) {
            this.c0 = colorStateList;
            if (I()) {
                DrawableCompat.setTintList(this.b0, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setCheckedIconTintResource(@ColorRes int i) {
        setCheckedIconTint(AppCompatResources.getColorStateList(this.n0, i));
    }

    public void setCheckedIconVisible(@BoolRes int i) {
        setCheckedIconVisible(this.n0.getResources().getBoolean(i));
    }

    public void setChipBackgroundColor(@Nullable ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            onStateChange(getState());
        }
    }

    public void setChipBackgroundColorResource(@ColorRes int i) {
        setChipBackgroundColor(AppCompatResources.getColorStateList(this.n0, i));
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        if (this.J != f) {
            this.J = f;
            setShapeAppearanceModel(getShapeAppearanceModel().withCornerSize(f));
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(@DimenRes int i) {
        setChipCornerRadius(this.n0.getResources().getDimension(i));
    }

    public void setChipEndPadding(float f) {
        if (this.m0 != f) {
            this.m0 = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipEndPaddingResource(@DimenRes int i) {
        setChipEndPadding(this.n0.getResources().getDimension(i));
    }

    public void setChipIcon(@Nullable Drawable drawable) {
        Drawable chipIcon = getChipIcon();
        if (chipIcon != drawable) {
            float A = A();
            this.P = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            float A2 = A();
            h0(chipIcon);
            if (f0()) {
                y(this.P);
            }
            invalidateSelf();
            if (A != A2) {
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(@BoolRes int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(@DrawableRes int i) {
        setChipIcon(AppCompatResources.getDrawable(this.n0, i));
    }

    public void setChipIconSize(float f) {
        if (this.R != f) {
            float A = A();
            this.R = f;
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                onSizeChange();
            }
        }
    }

    public void setChipIconSizeResource(@DimenRes int i) {
        setChipIconSize(this.n0.getResources().getDimension(i));
    }

    public void setChipIconTint(@Nullable ColorStateList colorStateList) {
        this.S = true;
        if (this.Q != colorStateList) {
            this.Q = colorStateList;
            if (f0()) {
                DrawableCompat.setTintList(this.P, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setChipIconTintResource(@ColorRes int i) {
        setChipIconTint(AppCompatResources.getColorStateList(this.n0, i));
    }

    public void setChipIconVisible(@BoolRes int i) {
        setChipIconVisible(this.n0.getResources().getBoolean(i));
    }

    public void setChipMinHeight(float f) {
        if (this.I != f) {
            this.I = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipMinHeightResource(@DimenRes int i) {
        setChipMinHeight(this.n0.getResources().getDimension(i));
    }

    public void setChipStartPadding(float f) {
        if (this.f0 != f) {
            this.f0 = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipStartPaddingResource(@DimenRes int i) {
        setChipStartPadding(this.n0.getResources().getDimension(i));
    }

    public void setChipStrokeColor(@Nullable ColorStateList colorStateList) {
        if (this.K != colorStateList) {
            this.K = colorStateList;
            if (this.P0) {
                setStrokeColor(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setChipStrokeColorResource(@ColorRes int i) {
        setChipStrokeColor(AppCompatResources.getColorStateList(this.n0, i));
    }

    public void setChipStrokeWidth(float f) {
        if (this.L != f) {
            this.L = f;
            this.o0.setStrokeWidth(f);
            if (this.P0) {
                super.setStrokeWidth(f);
            }
            invalidateSelf();
        }
    }

    public void setChipStrokeWidthResource(@DimenRes int i) {
        setChipStrokeWidth(this.n0.getResources().getDimension(i));
    }

    public void setCloseIcon(@Nullable Drawable drawable) {
        Drawable closeIcon = getCloseIcon();
        if (closeIcon != drawable) {
            float E = E();
            this.U = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
                j0();
            }
            float E2 = E();
            h0(closeIcon);
            if (g0()) {
                y(this.U);
            }
            invalidateSelf();
            if (E != E2) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconContentDescription(@Nullable CharSequence charSequence) {
        if (this.Y != charSequence) {
            this.Y = BidiFormatter.getInstance().unicodeWrap(charSequence);
            invalidateSelf();
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(@BoolRes int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f) {
        if (this.l0 != f) {
            this.l0 = f;
            invalidateSelf();
            if (g0()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconEndPaddingResource(@DimenRes int i) {
        setCloseIconEndPadding(this.n0.getResources().getDimension(i));
    }

    public void setCloseIconResource(@DrawableRes int i) {
        setCloseIcon(AppCompatResources.getDrawable(this.n0, i));
    }

    public void setCloseIconSize(float f) {
        if (this.X != f) {
            this.X = f;
            invalidateSelf();
            if (g0()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconSizeResource(@DimenRes int i) {
        setCloseIconSize(this.n0.getResources().getDimension(i));
    }

    public void setCloseIconStartPadding(float f) {
        if (this.k0 != f) {
            this.k0 = f;
            invalidateSelf();
            if (g0()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconStartPaddingResource(@DimenRes int i) {
        setCloseIconStartPadding(this.n0.getResources().getDimension(i));
    }

    public boolean setCloseIconState(@NonNull int[] iArr) {
        if (Arrays.equals(this.I0, iArr)) {
            return false;
        }
        this.I0 = iArr;
        if (g0()) {
            return a0(getState(), iArr);
        }
        return false;
    }

    public void setCloseIconTint(@Nullable ColorStateList colorStateList) {
        if (this.W != colorStateList) {
            this.W = colorStateList;
            if (g0()) {
                DrawableCompat.setTintList(this.U, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setCloseIconTintResource(@ColorRes int i) {
        setCloseIconTint(AppCompatResources.getColorStateList(this.n0, i));
    }

    public void setCloseIconVisible(@BoolRes int i) {
        setCloseIconVisible(this.n0.getResources().getBoolean(i));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (this.E0 != colorFilter) {
            this.E0 = colorFilter;
            invalidateSelf();
        }
    }

    public void setDelegate(@Nullable Delegate delegate) {
        this.L0 = new WeakReference<>(delegate);
    }

    public void setEllipsize(@Nullable TextUtils.TruncateAt truncateAt) {
        this.M0 = truncateAt;
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        this.e0 = motionSpec;
    }

    public void setHideMotionSpecResource(@AnimatorRes int i) {
        setHideMotionSpec(MotionSpec.createFromResource(this.n0, i));
    }

    public void setIconEndPadding(float f) {
        if (this.h0 != f) {
            float A = A();
            this.h0 = f;
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                onSizeChange();
            }
        }
    }

    public void setIconEndPaddingResource(@DimenRes int i) {
        setIconEndPadding(this.n0.getResources().getDimension(i));
    }

    public void setIconStartPadding(float f) {
        if (this.g0 != f) {
            float A = A();
            this.g0 = f;
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                onSizeChange();
            }
        }
    }

    public void setIconStartPaddingResource(@DimenRes int i) {
        setIconStartPadding(this.n0.getResources().getDimension(i));
    }

    public void setMaxWidth(@Px int i) {
        this.O0 = i;
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.M != colorStateList) {
            this.M = colorStateList;
            i0();
            onStateChange(getState());
        }
    }

    public void setRippleColorResource(@ColorRes int i) {
        setRippleColor(AppCompatResources.getColorStateList(this.n0, i));
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        this.d0 = motionSpec;
    }

    public void setShowMotionSpecResource(@AnimatorRes int i) {
        setShowMotionSpec(MotionSpec.createFromResource(this.n0, i));
    }

    public void setText(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.N, charSequence)) {
            return;
        }
        this.N = charSequence;
        this.u0.setTextWidthDirty(true);
        invalidateSelf();
        onSizeChange();
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance) {
        this.u0.setTextAppearance(textAppearance, this.n0);
    }

    public void setTextAppearanceResource(@StyleRes int i) {
        setTextAppearance(new TextAppearance(this.n0, i));
    }

    public void setTextColor(@ColorInt int i) {
        setTextColor(ColorStateList.valueOf(i));
    }

    public void setTextEndPadding(float f) {
        if (this.j0 != f) {
            this.j0 = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setTextEndPaddingResource(@DimenRes int i) {
        setTextEndPadding(this.n0.getResources().getDimension(i));
    }

    public void setTextResource(@StringRes int i) {
        setText(this.n0.getResources().getString(i));
    }

    public void setTextSize(@Dimension float f) {
        TextAppearance textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.setTextSize(f);
            this.u0.getTextPaint().setTextSize(f);
            onTextSizeChange();
        }
    }

    public void setTextStartPadding(float f) {
        if (this.i0 != f) {
            this.i0 = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setTextStartPaddingResource(@DimenRes int i) {
        setTextStartPadding(this.n0.getResources().getDimension(i));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(@Nullable ColorStateList colorStateList) {
        if (this.G0 != colorStateList) {
            this.G0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        if (this.H0 != mode) {
            this.H0 = mode;
            this.F0 = DrawableUtils.updateTintFilter(this, this.G0, mode);
            invalidateSelf();
        }
    }

    public void setUseCompatRipple(boolean z) {
        if (this.J0 != z) {
            this.J0 = z;
            i0();
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (f0()) {
            visible |= this.P.setVisible(z, z2);
        }
        if (e0()) {
            visible |= this.b0.setVisible(z, z2);
        }
        if (g0()) {
            visible |= this.U.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void y(@Nullable Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.U) {
            if (drawable.isStateful()) {
                drawable.setState(getCloseIconState());
            }
            DrawableCompat.setTintList(drawable, this.W);
            return;
        }
        Drawable drawable2 = this.P;
        if (drawable == drawable2 && this.S) {
            DrawableCompat.setTintList(drawable2, this.Q);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    public final void z(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (f0() || e0()) {
            float f = this.f0 + this.g0;
            float T = T();
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + T;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - T;
            }
            float S = S();
            float exactCenterY = rect.exactCenterY() - (S / 2.0f);
            rectF.top = exactCenterY;
            rectF.bottom = exactCenterY + S;
        }
    }

    public void setCheckedIconVisible(boolean z) {
        if (this.a0 != z) {
            boolean e0 = e0();
            this.a0 = z;
            boolean e02 = e0();
            if (e0 != e02) {
                if (e02) {
                    y(this.b0);
                } else {
                    h0(this.b0);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public void setChipIconVisible(boolean z) {
        if (this.O != z) {
            boolean f0 = f0();
            this.O = z;
            boolean f02 = f0();
            if (f0 != f02) {
                if (f02) {
                    y(this.P);
                } else {
                    h0(this.P);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public void setCloseIconVisible(boolean z) {
        if (this.T != z) {
            boolean g0 = g0();
            this.T = z;
            boolean g02 = g0();
            if (g0 != g02) {
                if (g02) {
                    y(this.U);
                } else {
                    h0(this.U);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public void setTextColor(@Nullable ColorStateList colorStateList) {
        TextAppearance textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.setTextColor(colorStateList);
            invalidateSelf();
        }
    }
}
