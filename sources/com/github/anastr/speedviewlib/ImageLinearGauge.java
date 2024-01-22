package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.github.anastr.speedviewlib.Gauge;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0003\u001a\u00020\u0002H\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0014J\b\u0010\b\u001a\u00020\u0002H\u0014¨\u0006\u0010"}, d2 = {"Lcom/github/anastr/speedviewlib/ImageLinearGauge;", "Lcom/github/anastr/speedviewlib/LinearGauge;", "", "defaultGaugeValues", "", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "updateFrontAndBackBitmaps", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class ImageLinearGauge extends LinearGauge {
    @Nullable
    public Drawable f0;
    public int g0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ImageLinearGauge(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ImageLinearGauge(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ ImageLinearGauge(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ImageLinearGauge, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…e.ImageLinearGauge, 0, 0)");
        this.g0 = obtainStyledAttributes.getColor(R.styleable.ImageLinearGauge_sv_speedometerBackColor, this.g0);
        this.f0 = obtainStyledAttributes.getDrawable(R.styleable.ImageLinearGauge_sv_image);
        obtainStyledAttributes.recycle();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
        super.setSpeedTextPosition(Gauge.Position.CENTER);
        super.setUnitUnderSpeedText(true);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        Drawable drawable = this.f0;
        if (drawable == null || measuredWidth == 0 || measuredHeight == 0) {
            return;
        }
        Intrinsics.checkNotNull(drawable);
        if (drawable.getIntrinsicWidth() > 0) {
            Drawable drawable2 = this.f0;
            Intrinsics.checkNotNull(drawable2);
            if (drawable2.getIntrinsicHeight() <= 0) {
                return;
            }
            Drawable drawable3 = this.f0;
            Intrinsics.checkNotNull(drawable3);
            float intrinsicWidth = drawable3.getIntrinsicWidth();
            Drawable drawable4 = this.f0;
            Intrinsics.checkNotNull(drawable4);
            float intrinsicHeight = drawable4.getIntrinsicHeight();
            if (intrinsicWidth / intrinsicHeight > measuredWidth / measuredHeight) {
                setMeasuredDimension(measuredWidth, (int) ((measuredWidth * intrinsicHeight) / intrinsicWidth));
            } else {
                setMeasuredDimension((int) ((measuredHeight * intrinsicWidth) / intrinsicHeight), measuredHeight);
            }
        }
    }

    @Override // com.github.anastr.speedviewlib.LinearGauge
    public void updateFrontAndBackBitmaps() {
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        Canvas createForegroundBitmapCanvas = createForegroundBitmapCanvas();
        Drawable drawable = this.f0;
        if (drawable != null) {
            Intrinsics.checkNotNull(drawable);
            drawable.setBounds(getPadding(), getPadding(), getWidth() - getPadding(), getHeight() - getPadding());
            Drawable drawable2 = this.f0;
            Intrinsics.checkNotNull(drawable2);
            drawable2.setColorFilter(this.g0, PorterDuff.Mode.SRC_IN);
            Drawable drawable3 = this.f0;
            Intrinsics.checkNotNull(drawable3);
            drawable3.draw(createBackgroundBitmapCanvas);
            Drawable drawable4 = this.f0;
            Intrinsics.checkNotNull(drawable4);
            drawable4.setColorFilter(null);
            Drawable drawable5 = this.f0;
            Intrinsics.checkNotNull(drawable5);
            drawable5.draw(createForegroundBitmapCanvas);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ImageLinearGauge(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.g0 = -2697257;
        l(context, attributeSet);
    }
}
