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
import com.clevertap.android.sdk.Constants;
import com.github.anastr.speedviewlib.components.Section;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.components.indicators.Indicator;
import com.github.anastr.speedviewlib.components.indicators.NormalSmallIndicator;
import com.github.anastr.speedviewlib.util.UtilsKt;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010)\u001a\u00020(\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\b\b\u0002\u0010,\u001a\u00020\u0005¢\u0006\u0004\b-\u0010.J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0014J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R*\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00128\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010%\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$¨\u0006/"}, d2 = {"Lcom/github/anastr/speedviewlib/DeluxeSpeedView;", "Lcom/github/anastr/speedviewlib/Speedometer;", "", "defaultGaugeValues", "defaultSpeedometerValues", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "Landroid/graphics/Canvas;", "canvas", "onDraw", "updateBackgroundBitmap", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator$Indicators;", "indicator", "setIndicator", "", "centerCircleRadius", "G0", WeatherCriteria.UNIT_FARENHEIT, "getCenterCircleRadius", "()F", "setCenterCircleRadius", "(F)V", "", "withEffects", "isWithEffects", "()Z", "setWithEffects", "(Z)V", "speedBackgroundColor", "getSpeedBackgroundColor", "()I", "setSpeedBackgroundColor", "(I)V", "centerCircleColor", "getCenterCircleColor", "setCenterCircleColor", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public class DeluxeSpeedView extends Speedometer {
    @NotNull
    public final Paint A0;
    @NotNull
    public final Paint B0;
    @NotNull
    public final Paint C0;
    @NotNull
    public final Paint D0;
    @NotNull
    public final RectF E0;
    public boolean F0;
    public float G0;
    @NotNull
    public final Path z0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public DeluxeSpeedView(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public DeluxeSpeedView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DeluxeSpeedView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void k() {
        this.B0.setStyle(Paint.Style.STROKE);
        this.C0.setStyle(Paint.Style.STROKE);
        this.D0.setColor(-1);
        this.A0.setColor(-2039584);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        setWithEffects(this.F0);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            v();
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.DeluxeSpeedView, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…le.DeluxeSpeedView, 0, 0)");
        Paint paint = this.D0;
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.DeluxeSpeedView_sv_speedBackgroundColor, paint.getColor()));
        this.F0 = obtainStyledAttributes.getBoolean(R.styleable.DeluxeSpeedView_sv_withEffects, this.F0);
        Paint paint2 = this.A0;
        paint2.setColor(obtainStyledAttributes.getColor(R.styleable.DeluxeSpeedView_sv_centerCircleColor, paint2.getColor()));
        setCenterCircleRadius(obtainStyledAttributes.getDimension(R.styleable.DeluxeSpeedView_sv_centerCircleRadius, this.G0));
        int i = obtainStyledAttributes.getInt(R.styleable.DeluxeSpeedView_sv_sectionStyle, -1);
        if (i != -1) {
            for (Section section : getSections()) {
                section.setStyle(Style.values()[i]);
            }
        }
        obtainStyledAttributes.recycle();
        setWithEffects(this.F0);
        v();
    }

    private final void v() {
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void defaultGaugeValues() {
        super.setTextColor(-1);
        getSections().get(0).setColor(-13138129);
        getSections().get(1).setColor(-6061516);
        getSections().get(2).setColor(-6610912);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void defaultSpeedometerValues() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setIndicator(new NormalSmallIndicator(context));
        getIndicator().setColor(-16711700);
        super.setBackgroundCircleColor(-14606047);
        super.setMarksNumber(8);
    }

    public final int getCenterCircleColor() {
        return this.A0.getColor();
    }

    public final float getCenterCircleRadius() {
        return this.G0;
    }

    public final int getSpeedBackgroundColor() {
        return this.D0.getColor();
    }

    public final boolean isWithEffects() {
        return this.F0;
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        RectF speedUnitTextBounds = getSpeedUnitTextBounds();
        speedUnitTextBounds.left -= 2.0f;
        speedUnitTextBounds.right += 2.0f;
        speedUnitTextBounds.bottom += 2.0f;
        canvas.drawRect(speedUnitTextBounds, this.D0);
        drawSpeedUnitText(canvas);
        drawIndicator(canvas);
        canvas.drawCircle(getSize() * 0.5f, getSize() * 0.5f, this.G0, this.A0);
        drawNotes(canvas);
    }

    @Override // com.github.anastr.speedviewlib.Speedometer, com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateBackgroundBitmap();
    }

    public final void setCenterCircleColor(int i) {
        this.A0.setColor(i);
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    public final void setCenterCircleRadius(float f) {
        this.G0 = f;
        if (isAttachedToWindow()) {
            invalidate();
        }
    }

    @Override // com.github.anastr.speedviewlib.Speedometer
    public void setIndicator(@NotNull Indicator.Indicators indicator) {
        Intrinsics.checkNotNullParameter(indicator, "indicator");
        super.setIndicator(indicator);
        getIndicator().withEffects(this.F0);
    }

    public final void setSpeedBackgroundColor(int i) {
        this.D0.setColor(i);
        invalidateGauge();
    }

    public final void setWithEffects(boolean z) {
        this.F0 = z;
        if (isInEditMode()) {
            return;
        }
        getIndicator().withEffects(z);
        if (z) {
            getMarkPaint().setMaskFilter(new BlurMaskFilter(5.0f, BlurMaskFilter.Blur.SOLID));
            this.D0.setMaskFilter(new BlurMaskFilter(8.0f, BlurMaskFilter.Blur.SOLID));
            this.A0.setMaskFilter(new BlurMaskFilter(10.0f, BlurMaskFilter.Blur.SOLID));
        } else {
            getMarkPaint().setMaskFilter(null);
            this.D0.setMaskFilter(null);
            this.A0.setMaskFilter(null);
        }
        invalidateGauge();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void updateBackgroundBitmap() {
        Canvas createBackgroundBitmapCanvas = createBackgroundBitmapCanvas();
        x();
        this.z0.reset();
        this.z0.moveTo(getSize() * 0.5f, getSpeedometerWidth() + getPadding());
        this.z0.lineTo(getSize() * 0.5f, getSpeedometerWidth() + getPadding() + (getViewSizePa() / 20.0f));
        this.C0.setStrokeWidth(3.0f);
        for (Section section : getSections()) {
            float width = (section.getWidth() * 0.5f) + getPadding() + section.getPadding();
            this.E0.set(width, width, getSize() - width, getSize() - width);
            this.B0.setStrokeWidth(section.getWidth());
            this.B0.setColor(section.getColor());
            float startDegree = getStartDegree() + ((getEndDegree() - getStartDegree()) * section.getStartOffset());
            float endDegree = ((getEndDegree() - getStartDegree()) * section.getEndOffset()) - (startDegree - getStartDegree());
            if (section.getStyle() == Style.ROUND) {
                float roundAngle = UtilsKt.getRoundAngle(section.getWidth(), this.E0.width());
                this.B0.setStrokeCap(Paint.Cap.ROUND);
                createBackgroundBitmapCanvas.drawArc(this.E0, startDegree + roundAngle, endDegree - (roundAngle * 2.0f), false, this.B0);
            } else {
                this.B0.setStrokeCap(Paint.Cap.BUTT);
                createBackgroundBitmapCanvas.drawArc(this.E0, startDegree, endDegree, false, this.B0);
            }
        }
        createBackgroundBitmapCanvas.save();
        createBackgroundBitmapCanvas.rotate(getStartDegree() + 90.0f, getSize() * 0.5f, getSize() * 0.5f);
        for (float startDegree2 = getStartDegree(); startDegree2 < getEndDegree() - 10.0f; startDegree2 += 10.0f) {
            createBackgroundBitmapCanvas.rotate(10.0f, getSize() * 0.5f, getSize() * 0.5f);
            createBackgroundBitmapCanvas.drawPath(this.z0, this.C0);
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
        this.B0.setStrokeWidth(getSpeedometerWidth());
        this.C0.setColor(getMarkColor());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public DeluxeSpeedView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.z0 = new Path();
        this.A0 = new Paint(1);
        this.B0 = new Paint(1);
        this.C0 = new Paint(1);
        this.D0 = new Paint(1);
        this.E0 = new RectF();
        this.F0 = true;
        this.G0 = dpTOpx(20.0f);
        k();
        l(context, attributeSet);
    }
}
