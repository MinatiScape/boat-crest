package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.clevertap.android.sdk.Constants;
import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.components.indicators.Indicator;
import com.github.anastr.speedviewlib.components.indicators.TriangleIndicator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\b\b\u0002\u0010#\u001a\u00020\u0005¢\u0006\u0004\b$\u0010%J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0014J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000bH\u0004J\u0006\u0010\u0011\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0005R$\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00198V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006&"}, d2 = {"Lcom/github/anastr/speedviewlib/AwesomeSpeedometer;", "Lcom/github/anastr/speedviewlib/Speedometer;", "", "defaultGaugeValues", "defaultSpeedometerValues", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "Landroid/graphics/Canvas;", "canvas", "onDraw", "updateBackgroundBitmap", com.google.android.material.color.c.f10260a, "drawCustomMarks", "getSpeedometerColor", "speedometerColor", "setSpeedometerColor", "trianglesColor", "getTrianglesColor", "()I", "setTrianglesColor", "(I)V", "", "speedometerWidth", "getSpeedometerWidth", "()F", "setSpeedometerWidth", "(F)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class AwesomeSpeedometer extends Speedometer {
    @NotNull
    public final Path A0;
    @NotNull
    public final Paint B0;
    @NotNull
    public final Paint C0;
    @NotNull
    public final Paint D0;
    @NotNull
    public final RectF E0;
    public int F0;
    @NotNull
    public final Path z0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AwesomeSpeedometer(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AwesomeSpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ AwesomeSpeedometer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void k() {
        this.B0.setStyle(Paint.Style.STROKE);
        getTextPaint().setTextAlign(Paint.Align.CENTER);
        this.C0.setStyle(Paint.Style.STROKE);
        this.D0.setColor(-13022805);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.AwesomeSpeedometer, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…AwesomeSpeedometer, 0, 0)");
        this.F0 = obtainStyledAttributes.getColor(R.styleable.AwesomeSpeedometer_sv_speedometerColor, this.F0);
        Paint paint = this.D0;
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.AwesomeSpeedometer_sv_trianglesColor, paint.getColor()));
        obtainStyledAttributes.recycle();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
        super.setSpeedometerWidth(dpTOpx(60.0f));
        super.setTextColor(-15776);
        super.setSpeedTextColor(-1);
        super.setUnitTextColor(-1);
        super.setTextTypeface(Typeface.create(Typeface.DEFAULT, 1));
        super.setSpeedTextPosition(Gauge.Position.CENTER);
        super.setUnitUnderSpeedText(true);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void defaultSpeedometerValues() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setIndicator(new TriangleIndicator(context));
        Indicator<?> indicator = getIndicator();
        indicator.setWidth(indicator.dpTOpx(25.0f));
        indicator.setColor(-16718106);
        super.setStartEndDegree(135, 455);
        super.setBackgroundCircleColor(-14606047);
        super.setTickNumber(9);
        super.setTickPadding(0.0f);
    }

    public final void drawCustomMarks(@NotNull Canvas c) {
        Intrinsics.checkNotNullParameter(c, "c");
        int endDegree = getEndDegree() - getStartDegree();
        int i = 0;
        for (Object obj : getTicks()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            float f = endDegree;
            float startDegree = getStartDegree() + (((Number) obj).floatValue() * f);
            c.save();
            c.rotate(90.0f + startDegree, getSize() * 0.5f, getSize() * 0.5f);
            c.drawPath(this.A0, this.D0);
            if (i2 != getTickNumber()) {
                c.save();
                float startDegree2 = (getStartDegree() + (f * getTicks().get(i2).floatValue())) - startDegree;
                int i3 = 1;
                while (true) {
                    int i4 = i3 + 1;
                    c.rotate(0.1f * startDegree2, getSize() * 0.5f, getSize() * 0.5f);
                    if (i3 == 5) {
                        this.B0.setStrokeWidth((getSize() / 22.0f) / 5.0f);
                    } else {
                        this.B0.setStrokeWidth((getSize() / 22.0f) / 9.0f);
                    }
                    c.drawPath(this.z0, this.B0);
                    if (i4 > 9) {
                        break;
                    }
                    i3 = i4;
                }
                c.restore();
            }
            c.restore();
            i = i2;
        }
    }

    public final int getSpeedometerColor() {
        return this.F0;
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge
    public float getSpeedometerWidth() {
        return super.getSpeedometerWidth();
    }

    public final int getTrianglesColor() {
        return this.D0.getColor();
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        drawSpeedUnitText(canvas);
        drawIndicator(canvas);
        drawNotes(canvas);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        y();
        updateBackgroundBitmap();
    }

    public final void setSpeedometerColor(int i) {
        this.F0 = i;
        y();
        invalidateGauge();
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge
    public void setSpeedometerWidth(float f) {
        super.setSpeedometerWidth(f);
        RectF rectF = this.E0;
        if (rectF != null) {
            float f2 = f * 0.5f;
            rectF.set(f2, f2, getSize() - f2, getSize() - f2);
            y();
            invalidateGauge();
        }
    }

    public final void setTrianglesColor(int i) {
        this.D0.setColor(i);
        invalidateGauge();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void updateBackgroundBitmap() {
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        x();
        float viewSizePa = getViewSizePa() / 22.0f;
        this.z0.reset();
        this.z0.moveTo(getSize() * 0.5f, getPadding());
        this.z0.lineTo(getSize() * 0.5f, getPadding() + viewSizePa);
        this.B0.setStrokeWidth(viewSizePa / 5.0f);
        setInitTickPadding(getViewSizePa() / 20.0f);
        this.A0.reset();
        this.A0.moveTo(getSize() * 0.5f, getPadding() + (getViewSizePa() / 20.0f));
        float viewSize = (getViewSize() / 20.0f) / 2.0f;
        this.A0.lineTo((getSize() * 0.5f) - viewSize, getPadding());
        this.A0.lineTo((getSize() * 0.5f) + viewSize, getPadding());
        float speedometerWidth = (getSpeedometerWidth() * 0.5f) + getPadding();
        this.E0.set(speedometerWidth, speedometerWidth, getSize() - speedometerWidth, getSize() - speedometerWidth);
        createBackgroundBitmapCanvas.drawArc(this.E0, 0.0f, 360.0f, false, this.C0);
        drawCustomMarks(createBackgroundBitmapCanvas);
        drawMarks(createBackgroundBitmapCanvas);
        drawTicks(createBackgroundBitmapCanvas);
    }

    public final void x() {
        this.C0.setStrokeWidth(getSpeedometerWidth());
        this.B0.setColor(getMarkColor());
    }

    public final void y() {
        float sizePa = ((getSizePa() * 0.5f) - getSpeedometerWidth()) / (getSizePa() * 0.5f);
        float f = 1.0f - sizePa;
        int i = this.F0;
        this.C0.setShader(new RadialGradient(getSize() * 0.5f, getSize() * 0.5f, 0.5f * getSizePa(), new int[]{getBackgroundCircleColor(), this.F0, getBackgroundCircleColor(), getBackgroundCircleColor(), i, i}, new float[]{sizePa, (0.1f * f) + sizePa, (0.36f * f) + sizePa, (0.64f * f) + sizePa, (f * 0.9f) + sizePa, 1.0f}, Shader.TileMode.CLAMP));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AwesomeSpeedometer(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.z0 = new Path();
        this.A0 = new Path();
        this.B0 = new Paint(1);
        this.C0 = new Paint(1);
        this.D0 = new Paint(1);
        this.E0 = new RectF();
        this.F0 = -16718106;
        k();
        l(context, attributeSet);
    }
}
