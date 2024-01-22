package com.google.android.material.tooltip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MarkerEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.OffsetEdgeTreatment;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class TooltipDrawable extends MaterialShapeDrawable implements TextDrawableHelper.TextDrawableDelegate {
    @StyleRes
    public static final int W = R.style.Widget_MaterialComponents_Tooltip;
    @AttrRes
    public static final int X = R.attr.tooltipStyle;
    @Nullable
    public CharSequence G;
    @NonNull
    public final Context H;
    @Nullable
    public final Paint.FontMetrics I;
    @NonNull
    public final TextDrawableHelper J;
    @NonNull
    public final View.OnLayoutChangeListener K;
    @NonNull
    public final Rect L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public float S;
    public float T;
    public float U;
    public float V;

    /* loaded from: classes10.dex */
    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            TooltipDrawable.this.G(view);
        }
    }

    public TooltipDrawable(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
        this.I = new Paint.FontMetrics();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.J = textDrawableHelper;
        this.K = new a();
        this.L = new Rect();
        this.S = 1.0f;
        this.T = 1.0f;
        this.U = 0.5f;
        this.V = 1.0f;
        this.H = context;
        textDrawableHelper.getTextPaint().density = context.getResources().getDisplayMetrics().density;
        textDrawableHelper.getTextPaint().setTextAlign(Paint.Align.CENTER);
    }

    @NonNull
    public static TooltipDrawable create(@NonNull Context context) {
        return createFromAttributes(context, null, X, W);
    }

    @NonNull
    public static TooltipDrawable createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        TooltipDrawable tooltipDrawable = new TooltipDrawable(context, attributeSet, i, i2);
        tooltipDrawable.F(attributeSet, i, i2);
        return tooltipDrawable;
    }

    public final float A() {
        this.J.getTextPaint().getFontMetrics(this.I);
        Paint.FontMetrics fontMetrics = this.I;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public final float B(@NonNull Rect rect) {
        return rect.centerY() - A();
    }

    public final EdgeTreatment C() {
        float width = ((float) (getBounds().width() - (this.Q * Math.sqrt(2.0d)))) / 2.0f;
        return new OffsetEdgeTreatment(new MarkerEdgeTreatment(this.Q), Math.min(Math.max(-z(), -width), width));
    }

    public final void D(@NonNull Canvas canvas) {
        if (this.G == null) {
            return;
        }
        Rect bounds = getBounds();
        int B = (int) B(bounds);
        if (this.J.getTextAppearance() != null) {
            this.J.getTextPaint().drawableState = getState();
            this.J.updateTextPaintDrawState(this.H);
            this.J.getTextPaint().setAlpha((int) (this.V * 255.0f));
        }
        CharSequence charSequence = this.G;
        canvas.drawText(charSequence, 0, charSequence.length(), bounds.centerX(), B, this.J.getTextPaint());
    }

    public final float E() {
        CharSequence charSequence = this.G;
        if (charSequence == null) {
            return 0.0f;
        }
        return this.J.getTextWidth(charSequence.toString());
    }

    public final void F(@Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(this.H, attributeSet, R.styleable.Tooltip, i, i2, new int[0]);
        this.Q = this.H.getResources().getDimensionPixelSize(R.dimen.mtrl_tooltip_arrowSize);
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setBottomEdge(C()).build());
        setText(obtainStyledAttributes.getText(R.styleable.Tooltip_android_text));
        TextAppearance textAppearance = MaterialResources.getTextAppearance(this.H, obtainStyledAttributes, R.styleable.Tooltip_android_textAppearance);
        if (textAppearance != null) {
            int i3 = R.styleable.Tooltip_android_textColor;
            if (obtainStyledAttributes.hasValue(i3)) {
                textAppearance.setTextColor(MaterialResources.getColorStateList(this.H, obtainStyledAttributes, i3));
            }
        }
        setTextAppearance(textAppearance);
        setFillColor(ColorStateList.valueOf(obtainStyledAttributes.getColor(R.styleable.Tooltip_backgroundTint, MaterialColors.layer(ColorUtils.setAlphaComponent(MaterialColors.getColor(this.H, 16842801, TooltipDrawable.class.getCanonicalName()), 229), ColorUtils.setAlphaComponent(MaterialColors.getColor(this.H, R.attr.colorOnBackground, TooltipDrawable.class.getCanonicalName()), 153)))));
        setStrokeColor(ColorStateList.valueOf(MaterialColors.getColor(this.H, R.attr.colorSurface, TooltipDrawable.class.getCanonicalName())));
        this.M = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_padding, 0);
        this.N = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_minWidth, 0);
        this.O = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_minHeight, 0);
        this.P = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_layout_margin, 0);
        obtainStyledAttributes.recycle();
    }

    public final void G(@NonNull View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.R = iArr[0];
        view.getWindowVisibleDisplayFrame(this.L);
    }

    public void detachView(@Nullable View view) {
        if (view == null) {
            return;
        }
        view.removeOnLayoutChangeListener(this.K);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.scale(this.S, this.T, getBounds().left + (getBounds().width() * 0.5f), getBounds().top + (getBounds().height() * this.U));
        canvas.translate(z(), (float) (-((this.Q * Math.sqrt(2.0d)) - this.Q)));
        super.draw(canvas);
        D(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) Math.max(this.J.getTextPaint().getTextSize(), this.O);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) Math.max((this.M * 2) + E(), this.N);
    }

    public int getLayoutMargin() {
        return this.P;
    }

    public int getMinHeight() {
        return this.O;
    }

    public int getMinWidth() {
        return this.N;
    }

    @Nullable
    public CharSequence getText() {
        return this.G;
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.J.getTextAppearance();
    }

    public int getTextPadding() {
        return this.M;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setBottomEdge(C()).build());
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        invalidateSelf();
    }

    public void setLayoutMargin(@Px int i) {
        this.P = i;
        invalidateSelf();
    }

    public void setMinHeight(@Px int i) {
        this.O = i;
        invalidateSelf();
    }

    public void setMinWidth(@Px int i) {
        this.N = i;
        invalidateSelf();
    }

    public void setRelativeToView(@Nullable View view) {
        if (view == null) {
            return;
        }
        G(view);
        view.addOnLayoutChangeListener(this.K);
    }

    public void setRevealFraction(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.U = 1.2f;
        this.S = f;
        this.T = f;
        this.V = AnimationUtils.lerp(0.0f, 1.0f, 0.19f, 1.0f, f);
        invalidateSelf();
    }

    public void setText(@Nullable CharSequence charSequence) {
        if (TextUtils.equals(this.G, charSequence)) {
            return;
        }
        this.G = charSequence;
        this.J.setTextWidthDirty(true);
        invalidateSelf();
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance) {
        this.J.setTextAppearance(textAppearance, this.H);
    }

    public void setTextAppearanceResource(@StyleRes int i) {
        setTextAppearance(new TextAppearance(this.H, i));
    }

    public void setTextPadding(@Px int i) {
        this.M = i;
        invalidateSelf();
    }

    public void setTextResource(@StringRes int i) {
        setText(this.H.getResources().getString(i));
    }

    public final float z() {
        int i;
        if (((this.L.right - getBounds().right) - this.R) - this.P < 0) {
            i = ((this.L.right - getBounds().right) - this.R) - this.P;
        } else if (((this.L.left - getBounds().left) - this.R) + this.P <= 0) {
            return 0.0f;
        } else {
            i = ((this.L.left - getBounds().left) - this.R) + this.P;
        }
        return i;
    }

    @NonNull
    public static TooltipDrawable createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        return createFromAttributes(context, attributeSet, X, W);
    }
}
