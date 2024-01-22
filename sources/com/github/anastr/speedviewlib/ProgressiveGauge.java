package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.LinearGauge;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0014J\b\u0010\b\u001a\u00020\u0002H\u0014J\b\u0010\t\u001a\u00020\u0002H\u0004J\b\u0010\n\u001a\u00020\u0002H\u0004R$\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/github/anastr/speedviewlib/ProgressiveGauge;", "Lcom/github/anastr/speedviewlib/LinearGauge;", "", "defaultGaugeValues", "", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "updateFrontAndBackBitmaps", "updateHorizontalPath", "updateVerticalPath", "speedometerColor", "getSpeedometerColor", "()I", "setSpeedometerColor", "(I)V", "speedometerBackColor", "getSpeedometerBackColor", "setSpeedometerBackColor", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class ProgressiveGauge extends LinearGauge {
    @NotNull
    public final Path f0;
    @NotNull
    public final Paint g0;
    @NotNull
    public final Paint h0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ProgressiveGauge(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ProgressiveGauge(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ ProgressiveGauge(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void k() {
        this.g0.setColor(-16711681);
        this.h0.setColor(-2697257);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.LinearGauge, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…leable.LinearGauge, 0, 0)");
        Paint paint = this.g0;
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.LinearGauge_sv_speedometerColor, paint.getColor()));
        Paint paint2 = this.h0;
        paint2.setColor(obtainStyledAttributes.getColor(R.styleable.LinearGauge_sv_speedometerBackColor, paint2.getColor()));
        obtainStyledAttributes.recycle();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
        super.setSpeedTextPosition(Gauge.Position.CENTER);
        super.setUnitUnderSpeedText(true);
    }

    public final int getSpeedometerBackColor() {
        return this.h0.getColor();
    }

    public final int getSpeedometerColor() {
        return this.g0.getColor();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getOrientation() == LinearGauge.Orientation.HORIZONTAL) {
            int i3 = measuredWidth / 2;
            if (measuredHeight > i3) {
                setMeasuredDimension(measuredWidth, i3);
                return;
            } else {
                setMeasuredDimension(measuredHeight * 2, measuredHeight);
                return;
            }
        }
        int i4 = measuredHeight / 2;
        if (measuredWidth > i4) {
            setMeasuredDimension(i4, measuredHeight);
        } else {
            setMeasuredDimension(measuredWidth, measuredWidth * 2);
        }
    }

    public final void r() {
        if (getOrientation() == LinearGauge.Orientation.HORIZONTAL) {
            updateHorizontalPath();
        } else {
            updateVerticalPath();
        }
    }

    public final void setSpeedometerBackColor(int i) {
        this.h0.setColor(i);
        invalidateGauge();
    }

    public final void setSpeedometerColor(int i) {
        this.g0.setColor(i);
        invalidateGauge();
    }

    @Override // com.github.anastr.speedviewlib.LinearGauge
    public void updateFrontAndBackBitmaps() {
        r();
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        Canvas createForegroundBitmapCanvas = createForegroundBitmapCanvas();
        createBackgroundBitmapCanvas.translate(getPadding(), getPadding());
        createBackgroundBitmapCanvas.drawPath(this.f0, this.h0);
        createForegroundBitmapCanvas.drawPath(this.f0, this.g0);
    }

    public final void updateHorizontalPath() {
        this.f0.reset();
        this.f0.moveTo(0.0f, getHeightPa());
        this.f0.lineTo(0.0f, getHeightPa() - (getHeightPa() * 0.1f));
        this.f0.quadTo(getWidthPa() * 0.75f, getHeightPa() * 0.75f, getWidthPa(), 0.0f);
        this.f0.lineTo(getWidthPa(), getHeightPa());
        this.f0.lineTo(0.0f, getHeightPa());
    }

    public final void updateVerticalPath() {
        this.f0.reset();
        this.f0.moveTo(0.0f, getHeightPa());
        this.f0.lineTo(getWidthPa() * 0.1f, getHeightPa());
        this.f0.quadTo(getWidthPa() * 0.25f, getHeightPa() * 0.25f, getWidthPa(), 0.0f);
        this.f0.lineTo(0.0f, 0.0f);
        this.f0.lineTo(0.0f, getHeightPa());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ProgressiveGauge(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f0 = new Path();
        this.g0 = new Paint(1);
        this.h0 = new Paint(1);
        k();
        l(context, attributeSet);
    }
}
