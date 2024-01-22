package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.clevertap.android.sdk.Constants;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.components.indicators.Indicator;
import com.github.anastr.speedviewlib.components.indicators.SpindleIndicator;
import com.github.anastr.speedviewlib.util.UtilsKt;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010)\u001a\u00020(\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\b\b\u0002\u0010,\u001a\u00020\u0005¢\u0006\u0004\b-\u0010.J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0014J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014J\u0006\u0010\u000f\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0005R*\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00158\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006/"}, d2 = {"Lcom/github/anastr/speedviewlib/PointerSpeedometer;", "Lcom/github/anastr/speedviewlib/Speedometer;", "", "defaultGaugeValues", "defaultSpeedometerValues", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "Landroid/graphics/Canvas;", "canvas", "onDraw", "updateBackgroundBitmap", "getSpeedometerColor", "speedometerColor", "setSpeedometerColor", "getPointerColor", "pointerColor", "setPointerColor", "", "centerCircleRadius", "H0", WeatherCriteria.UNIT_FARENHEIT, "getCenterCircleRadius", "()F", "setCenterCircleRadius", "(F)V", "centerCircleColor", "getCenterCircleColor", "()I", "setCenterCircleColor", "(I)V", "", "withPointer", "isWithPointer", "()Z", "setWithPointer", "(Z)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class PointerSpeedometer extends Speedometer {
    @NotNull
    public final Paint A0;
    @NotNull
    public final Paint B0;
    @NotNull
    public final Paint C0;
    @NotNull
    public final RectF D0;
    public int E0;
    public int F0;
    public boolean G0;
    public float H0;
    @NotNull
    public final Paint z0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PointerSpeedometer(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PointerSpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ PointerSpeedometer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void k() {
        this.z0.setStyle(Paint.Style.STROKE);
        this.z0.setStrokeCap(Paint.Cap.ROUND);
        this.C0.setColor(-1);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            v();
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PointerSpeedometer, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…PointerSpeedometer, 0, 0)");
        this.E0 = obtainStyledAttributes.getColor(R.styleable.PointerSpeedometer_sv_speedometerColor, this.E0);
        this.F0 = obtainStyledAttributes.getColor(R.styleable.PointerSpeedometer_sv_pointerColor, this.F0);
        Paint paint = this.C0;
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.PointerSpeedometer_sv_centerCircleColor, paint.getColor()));
        setCenterCircleRadius(obtainStyledAttributes.getDimension(R.styleable.SpeedView_sv_centerCircleRadius, this.H0));
        this.G0 = obtainStyledAttributes.getBoolean(R.styleable.PointerSpeedometer_sv_withPointer, this.G0);
        obtainStyledAttributes.recycle();
        v();
    }

    private final void v() {
        this.A0.setColor(this.F0);
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
        super.setSpeedometerWidth(dpTOpx(10.0f));
        super.setTextColor(-1);
        super.setSpeedTextColor(-1);
        super.setUnitTextColor(-1);
        super.setSpeedTextSize(dpTOpx(24.0f));
        super.setUnitTextSize(dpTOpx(11.0f));
        super.setSpeedTextTypeface(Typeface.create(Typeface.DEFAULT, 1));
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void defaultSpeedometerValues() {
        super.setMarksNumber(8);
        super.setMarksPadding(getSpeedometerWidth() + dpTOpx(12.0f));
        super.setTickPadding(getSpeedometerWidth() + dpTOpx(10.0f));
        super.setMarkStyle(Style.ROUND);
        super.setMarkHeight(dpTOpx(5.0f));
        super.setMarkWidth(dpTOpx(2.0f));
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setIndicator(new SpindleIndicator(context));
        Indicator<?> indicator = getIndicator();
        indicator.setWidth(indicator.dpTOpx(16.0f));
        indicator.setColor(-1);
        super.setBackgroundCircleColor(-12006167);
    }

    public final int getCenterCircleColor() {
        return this.C0.getColor();
    }

    public final float getCenterCircleRadius() {
        return this.H0;
    }

    public final int getPointerColor() {
        return this.F0;
    }

    public final int getSpeedometerColor() {
        return this.E0;
    }

    public final boolean isWithPointer() {
        return this.G0;
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        x();
        float roundAngle = UtilsKt.getRoundAngle(getSpeedometerWidth(), this.D0.width());
        canvas.drawArc(this.D0, getStartDegree() + roundAngle, (getEndDegree() - getStartDegree()) - (roundAngle * 2.0f), false, this.z0);
        if (this.G0) {
            canvas.save();
            canvas.rotate(90 + getDegree(), getSize() * 0.5f, getSize() * 0.5f);
            canvas.drawCircle(getSize() * 0.5f, (getSpeedometerWidth() * 0.5f) + dpTOpx(8.0f) + getPadding(), (getSpeedometerWidth() * 0.5f) + dpTOpx(8.0f), this.B0);
            canvas.drawCircle(getSize() * 0.5f, (getSpeedometerWidth() * 0.5f) + dpTOpx(8.0f) + getPadding(), (getSpeedometerWidth() * 0.5f) + dpTOpx(1.0f), this.A0);
            canvas.restore();
        }
        drawSpeedUnitText(canvas);
        drawIndicator(canvas);
        int centerCircleColor = getCenterCircleColor();
        this.C0.setColor(Color.argb((int) (Color.alpha(centerCircleColor) * 0.5f), Color.red(centerCircleColor), Color.green(centerCircleColor), Color.blue(centerCircleColor)));
        canvas.drawCircle(getSize() * 0.5f, getSize() * 0.5f, this.H0 + dpTOpx(6.0f), this.C0);
        this.C0.setColor(centerCircleColor);
        canvas.drawCircle(getSize() * 0.5f, getSize() * 0.5f, this.H0, this.C0);
        drawNotes(canvas);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float speedometerWidth = (getSpeedometerWidth() * 0.5f) + dpTOpx(8.0f) + getPadding();
        this.D0.set(speedometerWidth, speedometerWidth, getSize() - speedometerWidth, getSize() - speedometerWidth);
        y();
        updateBackgroundBitmap();
    }

    public final void setCenterCircleColor(int i) {
        this.C0.setColor(i);
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    public final void setCenterCircleRadius(float f) {
        this.H0 = f;
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    public final void setPointerColor(int i) {
        this.F0 = i;
        this.A0.setColor(i);
        y();
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    public final void setSpeedometerColor(int i) {
        this.E0 = i;
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    public final void setWithPointer(boolean z) {
        this.G0 = z;
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void updateBackgroundBitmap() {
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        x();
        drawMarks(createBackgroundBitmapCanvas);
        if (getTickNumber() > 0) {
            drawTicks(createBackgroundBitmapCanvas);
        } else {
            drawDefMinMaxSpeedPosition(createBackgroundBitmapCanvas);
        }
    }

    public final void x() {
        this.z0.setStrokeWidth(getSpeedometerWidth());
        this.z0.setShader(z());
    }

    public final void y() {
        this.B0.setShader(new RadialGradient(getSize() * 0.5f, getPadding() + (getSpeedometerWidth() * 0.5f) + dpTOpx(8.0f), (getSpeedometerWidth() * 0.5f) + dpTOpx(8.0f), new int[]{Color.argb(160, Color.red(this.F0), Color.green(this.F0), Color.blue(this.F0)), Color.argb(10, Color.red(this.F0), Color.green(this.F0), Color.blue(this.F0))}, new float[]{0.4f, 1.0f}, Shader.TileMode.CLAMP));
    }

    public final SweepGradient z() {
        int argb = Color.argb(150, Color.red(this.E0), Color.green(this.E0), Color.blue(this.E0));
        int argb2 = Color.argb(220, Color.red(this.E0), Color.green(this.E0), Color.blue(this.E0));
        int argb3 = Color.argb(70, Color.red(this.E0), Color.green(this.E0), Color.blue(this.E0));
        int argb4 = Color.argb(15, Color.red(this.E0), Color.green(this.E0), Color.blue(this.E0));
        float offsetSpeed = (getOffsetSpeed() * (getEndDegree() - getStartDegree())) / 360.0f;
        SweepGradient sweepGradient = new SweepGradient(getSize() * 0.5f, getSize() * 0.5f, new int[]{argb, argb2, this.E0, argb3, argb4, argb}, new float[]{0.0f, offsetSpeed * 0.5f, offsetSpeed, offsetSpeed, 0.99f, 1.0f});
        Matrix matrix = new Matrix();
        matrix.postRotate(getStartDegree(), getSize() * 0.5f, getSize() * 0.5f);
        sweepGradient.setLocalMatrix(matrix);
        return sweepGradient;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PointerSpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.z0 = new Paint(1);
        this.A0 = new Paint(1);
        this.B0 = new Paint(1);
        this.C0 = new Paint(1);
        this.D0 = new RectF();
        this.E0 = -1118482;
        this.F0 = -1;
        this.G0 = true;
        this.H0 = dpTOpx(12.0f);
        k();
        l(context, attributeSet);
    }
}
