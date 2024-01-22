package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import com.clevertap.android.sdk.Constants;
import com.github.anastr.speedviewlib.components.Section;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0014J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014R$\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lcom/github/anastr/speedviewlib/TubeSpeedometer;", "Lcom/github/anastr/speedviewlib/Speedometer;", "", "defaultGaugeValues", "defaultSpeedometerValues", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "Landroid/graphics/Canvas;", "canvas", "onDraw", "updateBackgroundBitmap", "speedometerBackColor", "getSpeedometerBackColor", "()I", "setSpeedometerBackColor", "(I)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class TubeSpeedometer extends Speedometer {
    @NotNull
    public final Paint A0;
    @NotNull
    public final RectF B0;
    @NotNull
    public final Paint z0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TubeSpeedometer(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TubeSpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ TubeSpeedometer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void k() {
        this.z0.setStyle(Paint.Style.STROKE);
        this.A0.setStyle(Paint.Style.STROKE);
        this.A0.setColor(-9079435);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TubeSpeedometer, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…le.TubeSpeedometer, 0, 0)");
        Paint paint = this.A0;
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.TubeSpeedometer_sv_speedometerBackColor, paint.getColor()));
        obtainStyledAttributes.recycle();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
        super.setSpeedometerWidth(dpTOpx(40.0f));
        getSections().get(0).setColor(-16728876);
        getSections().get(1).setColor(-16121);
        getSections().get(2).setColor(-769226);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void defaultSpeedometerValues() {
        super.setBackgroundCircleColor(0);
    }

    public final int getSpeedometerBackColor() {
        return this.A0.getColor();
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        x();
        canvas.drawArc(this.B0, getStartDegree(), (getEndDegree() - getStartDegree()) * getOffsetSpeed(), false, this.z0);
        drawSpeedUnitText(canvas);
        drawIndicator(canvas);
        drawNotes(canvas);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateBackgroundBitmap();
    }

    public final void setSpeedometerBackColor(int i) {
        this.A0.setColor(i);
        invalidateGauge();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void updateBackgroundBitmap() {
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        this.A0.setStrokeWidth(getSpeedometerWidth());
        float speedometerWidth = (getSpeedometerWidth() * 0.5f) + getPadding();
        this.B0.set(speedometerWidth, speedometerWidth, getSize() - speedometerWidth, getSize() - speedometerWidth);
        createBackgroundBitmapCanvas.drawArc(this.B0, getStartDegree(), getEndDegree() - getStartDegree(), false, this.A0);
        drawMarks(createBackgroundBitmapCanvas);
        if (getTickNumber() > 0) {
            drawTicks(createBackgroundBitmapCanvas);
        } else {
            drawDefMinMaxSpeedPosition(createBackgroundBitmapCanvas);
        }
    }

    public final void x() {
        this.z0.setStrokeWidth(getSpeedometerWidth());
        if (getCurrentSection() != null) {
            Paint paint = this.z0;
            Section currentSection = getCurrentSection();
            Intrinsics.checkNotNull(currentSection);
            paint.setColor(currentSection.getColor());
            return;
        }
        this.z0.setColor(0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TubeSpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.z0 = new Paint(1);
        this.A0 = new Paint(1);
        this.B0 = new RectF();
        k();
        l(context, attributeSet);
    }
}
