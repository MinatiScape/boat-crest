package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.github.anastr.speedviewlib.components.Section;
import com.github.anastr.speedviewlib.components.indicators.Indicator;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010+\u001a\u00020*\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010,\u0012\b\b\u0002\u0010.\u001a\u00020\u0005¢\u0006\u0004\b/\u00100J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0014J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0005R$\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010&\u001a\u00020 2\u0006\u0010!\u001a\u00020 8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010'\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010\u001d\"\u0004\b)\u0010\u001f¨\u00061"}, d2 = {"Lcom/github/anastr/speedviewlib/RaySpeedometer;", "Lcom/github/anastr/speedviewlib/Speedometer;", "", "defaultGaugeValues", "defaultSpeedometerValues", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "Landroid/graphics/Canvas;", "canvas", "onDraw", "updateBackgroundBitmap", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator$Indicators;", "indicator", "setIndicator", "getDegreeBetweenMark", "degreeBetweenMark", "setDegreeBetweenMark", "", "withEffects", "isWithEffects", "()Z", "setWithEffects", "(Z)V", "speedBackgroundColor", "getSpeedBackgroundColor", "()I", "setSpeedBackgroundColor", "(I)V", "", "markRayWidth", "getRayMarkWidth", "()F", "setRayMarkWidth", "(F)V", "rayMarkWidth", "rayColor", "getRayColor", "setRayColor", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class RaySpeedometer extends Speedometer {
    @NotNull
    public final Path A0;
    @NotNull
    public final Path B0;
    @NotNull
    public final Paint C0;
    @NotNull
    public final Paint D0;
    @NotNull
    public final Paint E0;
    @NotNull
    public final Paint F0;
    public boolean G0;
    public int H0;
    @NotNull
    public final Path z0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RaySpeedometer(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RaySpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ RaySpeedometer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void k() {
        this.C0.setStyle(Paint.Style.STROKE);
        this.C0.setStrokeWidth(dpTOpx(3.0f));
        this.D0.setStyle(Paint.Style.STROKE);
        this.D0.setStrokeWidth(dpTOpx(3.0f));
        this.F0.setStyle(Paint.Style.STROKE);
        this.F0.setStrokeWidth(dpTOpx(1.8f));
        this.F0.setColor(-1);
        this.E0.setColor(-1);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        setWithEffects(this.G0);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        boolean z = false;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.RaySpeedometer, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…ble.RaySpeedometer, 0, 0)");
        Paint paint = this.F0;
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.RaySpeedometer_sv_rayColor, paint.getColor()));
        int i = obtainStyledAttributes.getInt(R.styleable.RaySpeedometer_sv_degreeBetweenMark, this.H0);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.RaySpeedometer_sv_rayMarkWidth, this.C0.getStrokeWidth());
        this.C0.setStrokeWidth(dimension);
        this.D0.setStrokeWidth(dimension);
        Paint paint2 = this.E0;
        paint2.setColor(obtainStyledAttributes.getColor(R.styleable.RaySpeedometer_sv_speedBackgroundColor, paint2.getColor()));
        this.G0 = obtainStyledAttributes.getBoolean(R.styleable.RaySpeedometer_sv_withEffects, this.G0);
        obtainStyledAttributes.recycle();
        setWithEffects(this.G0);
        if (1 <= i && i <= 20) {
            z = true;
        }
        if (z) {
            this.H0 = i;
        }
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
        super.setTextColor(-1);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void defaultSpeedometerValues() {
        super.setBackgroundCircleColor(-14606047);
        super.setMarkColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public final int getDegreeBetweenMark() {
        return this.H0;
    }

    public final int getRayColor() {
        return this.F0.getColor();
    }

    public final float getRayMarkWidth() {
        return this.C0.getStrokeWidth();
    }

    public final int getSpeedBackgroundColor() {
        return this.E0.getColor();
    }

    public final boolean isWithEffects() {
        return this.G0;
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        int i;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        canvas.save();
        canvas.rotate(getStartDegree() + 90.0f, getSize() * 0.5f, getSize() * 0.5f);
        int startDegree = getStartDegree();
        while (startDegree < getEndDegree()) {
            if (getDegree() <= startDegree) {
                this.C0.setColor(getMarkColor());
                canvas.drawPath(this.z0, this.C0);
                canvas.rotate(this.H0, getSize() * 0.5f, getSize() * 0.5f);
                i = this.H0;
            } else {
                if (getCurrentSection() != null) {
                    Paint paint = this.D0;
                    Section currentSection = getCurrentSection();
                    Intrinsics.checkNotNull(currentSection);
                    paint.setColor(currentSection.getColor());
                } else {
                    this.D0.setColor(0);
                }
                canvas.drawPath(this.z0, this.D0);
                canvas.rotate(this.H0, getSize() * 0.5f, getSize() / 2.0f);
                i = this.H0;
            }
            startDegree += i;
        }
        canvas.restore();
        RectF speedUnitTextBounds = getSpeedUnitTextBounds();
        speedUnitTextBounds.left -= 2.0f;
        speedUnitTextBounds.right += 2.0f;
        speedUnitTextBounds.bottom += 2.0f;
        canvas.drawRect(speedUnitTextBounds, this.E0);
        drawSpeedUnitText(canvas);
        drawIndicator(canvas);
        drawNotes(canvas);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        x();
        updateBackgroundBitmap();
    }

    public final void setDegreeBetweenMark(int i) {
        if (i <= 0 || i > 20) {
            return;
        }
        this.H0 = i;
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void setIndicator(@NotNull Indicator.Indicators indicator) {
        Intrinsics.checkNotNullParameter(indicator, "indicator");
        super.setIndicator(indicator);
        getIndicator().withEffects(this.G0);
    }

    public final void setRayColor(int i) {
        this.F0.setColor(i);
        invalidateGauge();
    }

    public final void setRayMarkWidth(float f) {
        this.C0.setStrokeWidth(f);
        this.D0.setStrokeWidth(f);
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    public final void setSpeedBackgroundColor(int i) {
        this.E0.setColor(i);
        invalidateGauge();
    }

    public final void setWithEffects(boolean z) {
        this.G0 = z;
        if (isInEditMode()) {
            return;
        }
        getIndicator().withEffects(z);
        if (z) {
            this.F0.setMaskFilter(new BlurMaskFilter(3.0f, BlurMaskFilter.Blur.SOLID));
            this.D0.setMaskFilter(new BlurMaskFilter(5.0f, BlurMaskFilter.Blur.SOLID));
            this.E0.setMaskFilter(new BlurMaskFilter(8.0f, BlurMaskFilter.Blur.SOLID));
        } else {
            this.F0.setMaskFilter(null);
            this.D0.setMaskFilter(null);
            this.E0.setMaskFilter(null);
        }
        invalidateGauge();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void updateBackgroundBitmap() {
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        x();
        this.A0.reset();
        this.A0.moveTo(getSize() / 2.0f, getSize() / 2.0f);
        this.A0.lineTo(getSize() / 2.0f, (getSizePa() / 3.2f) + getPadding());
        this.A0.moveTo(getSize() / 2.0f, (getSizePa() / 3.2f) + getPadding());
        this.A0.lineTo(getSize() / 2.2f, (getSizePa() / 3.0f) + getPadding());
        this.A0.moveTo(getSize() / 2.2f, (getSizePa() / 3.0f) + getPadding());
        this.A0.lineTo(getSize() / 2.1f, (getSizePa() / 4.5f) + getPadding());
        this.B0.reset();
        this.B0.moveTo(getSize() / 2.0f, getSize() / 2.0f);
        this.B0.lineTo(getSize() / 2.0f, (getSizePa() / 3.2f) + getPadding());
        this.B0.moveTo(getSize() / 2.0f, (getSizePa() / 3.2f) + getPadding());
        this.B0.lineTo(getSize() / 2.2f, (getSizePa() / 3.8f) + getPadding());
        this.B0.moveTo(getSize() / 2.0f, (getSizePa() / 3.2f) + getPadding());
        this.B0.lineTo(getSize() / 1.8f, (getSizePa() / 3.8f) + getPadding());
        createBackgroundBitmapCanvas.save();
        int i = 0;
        while (true) {
            int i2 = i + 1;
            createBackgroundBitmapCanvas.rotate(58.0f, getSize() * 0.5f, getSize() * 0.5f);
            if (i % 2 == 0) {
                createBackgroundBitmapCanvas.drawPath(this.A0, this.F0);
            } else {
                createBackgroundBitmapCanvas.drawPath(this.B0, this.F0);
            }
            if (i2 > 5) {
                break;
            }
            i = i2;
        }
        createBackgroundBitmapCanvas.restore();
        drawMarks(createBackgroundBitmapCanvas);
        if (getTickNumber() > 0) {
            drawTicks(createBackgroundBitmapCanvas);
        } else {
            drawDefMinMaxSpeedPosition(createBackgroundBitmapCanvas);
        }
    }

    public final void x() {
        this.z0.reset();
        this.z0.moveTo(getSize() * 0.5f, getPadding());
        this.z0.lineTo(getSize() * 0.5f, getSpeedometerWidth() + getPadding());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RaySpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.z0 = new Path();
        this.A0 = new Path();
        this.B0 = new Path();
        this.C0 = new Paint(1);
        this.D0 = new Paint(1);
        this.E0 = new Paint(1);
        this.F0 = new Paint(1);
        this.G0 = true;
        this.H0 = 5;
        k();
        l(context, attributeSet);
    }
}
