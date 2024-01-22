package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.clevertap.android.sdk.Constants;
import com.github.anastr.speedviewlib.components.Section;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.components.indicators.NormalIndicator;
import com.github.anastr.speedviewlib.util.UtilsKt;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u0005¢\u0006\u0004\b!\u0010\"J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0014J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014R*\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006#"}, d2 = {"Lcom/github/anastr/speedviewlib/SpeedView;", "Lcom/github/anastr/speedviewlib/Speedometer;", "", "defaultGaugeValues", "defaultSpeedometerValues", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "Landroid/graphics/Canvas;", "canvas", "onDraw", "updateBackgroundBitmap", "", "centerCircleRadius", "C0", WeatherCriteria.UNIT_FARENHEIT, "getCenterCircleRadius", "()F", "setCenterCircleRadius", "(F)V", "centerCircleColor", "getCenterCircleColor", "()I", "setCenterCircleColor", "(I)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class SpeedView extends Speedometer {
    @NotNull
    public final Paint A0;
    @NotNull
    public final RectF B0;
    public float C0;
    @NotNull
    public final Paint z0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SpeedView(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SpeedView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ SpeedView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void k() {
        this.A0.setStyle(Paint.Style.STROKE);
        this.z0.setColor(-12303292);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SpeedView, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…tyleable.SpeedView, 0, 0)");
        Paint paint = this.z0;
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.SpeedView_sv_centerCircleColor, paint.getColor()));
        setCenterCircleRadius(obtainStyledAttributes.getDimension(R.styleable.SpeedView_sv_centerCircleRadius, this.C0));
        int i = obtainStyledAttributes.getInt(R.styleable.SpeedView_sv_sectionStyle, -1);
        if (i != -1) {
            for (Section section : getSections()) {
                section.setStyle(Style.values()[i]);
            }
        }
        obtainStyledAttributes.recycle();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void defaultSpeedometerValues() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setIndicator(new NormalIndicator(context));
        super.setBackgroundCircleColor(0);
        super.setMarksNumber(8);
    }

    public final int getCenterCircleColor() {
        return this.z0.getColor();
    }

    public final float getCenterCircleRadius() {
        return this.C0;
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        drawSpeedUnitText(canvas);
        drawIndicator(canvas);
        canvas.drawCircle(getSize() * 0.5f, getSize() * 0.5f, this.C0, this.z0);
        drawNotes(canvas);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateBackgroundBitmap();
    }

    public final void setCenterCircleColor(int i) {
        this.z0.setColor(i);
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    public final void setCenterCircleRadius(float f) {
        this.C0 = f;
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void updateBackgroundBitmap() {
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        for (Section section : getSections()) {
            float width = (section.getWidth() * 0.5f) + getPadding() + section.getPadding();
            this.B0.set(width, width, getSize() - width, getSize() - width);
            this.A0.setStrokeWidth(section.getWidth());
            this.A0.setColor(section.getColor());
            float startDegree = getStartDegree() + ((getEndDegree() - getStartDegree()) * section.getStartOffset());
            float endDegree = ((getEndDegree() - getStartDegree()) * section.getEndOffset()) - (startDegree - getStartDegree());
            if (section.getStyle() == Style.ROUND) {
                float roundAngle = UtilsKt.getRoundAngle(section.getWidth(), this.B0.width());
                this.A0.setStrokeCap(Paint.Cap.ROUND);
                createBackgroundBitmapCanvas.drawArc(this.B0, startDegree + roundAngle, endDegree - (roundAngle * 2.0f), false, this.A0);
            } else {
                this.A0.setStrokeCap(Paint.Cap.BUTT);
                createBackgroundBitmapCanvas.drawArc(this.B0, startDegree, endDegree, false, this.A0);
            }
        }
        drawMarks(createBackgroundBitmapCanvas);
        if (getTickNumber() > 0) {
            drawTicks(createBackgroundBitmapCanvas);
        } else {
            drawDefMinMaxSpeedPosition(createBackgroundBitmapCanvas);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SpeedView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.z0 = new Paint(1);
        this.A0 = new Paint(1);
        this.B0 = new RectF();
        this.C0 = dpTOpx(20.0f);
        k();
        l(context, attributeSet);
    }
}
