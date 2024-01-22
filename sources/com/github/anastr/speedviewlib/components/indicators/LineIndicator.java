package com.github.anastr.speedviewlib.components.indicators;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.anastr.speedviewlib.Speedometer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0014¨\u0006\u0011"}, d2 = {"Lcom/github/anastr/speedviewlib/components/indicators/LineIndicator;", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "", "getBottom", "Landroid/graphics/Canvas;", "canvas", "", "draw", "updateIndicator", "", "withEffects", "setWithEffects", "Landroid/content/Context;", "context", "length", "<init>", "(Landroid/content/Context;F)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class LineIndicator extends Indicator<LineIndicator> {
    public final float f;
    @NotNull
    public final Path g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineIndicator(@NotNull Context context, float f) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f = f;
        this.g = new Path();
        boolean z = false;
        if (0.0f <= f && f <= 1.0f) {
            z = true;
        }
        if (z) {
            setWidth(dpTOpx(8.0f));
            return;
        }
        throw new IllegalArgumentException("Length must be between [0,1].".toString());
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawPath(this.g, getIndicatorPaint());
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public float getBottom() {
        return getCenterY() * this.f;
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void setWithEffects(boolean z) {
        if (z) {
            Speedometer speedometer = getSpeedometer();
            Intrinsics.checkNotNull(speedometer);
            if (!speedometer.isInEditMode()) {
                getIndicatorPaint().setMaskFilter(new BlurMaskFilter(15.0f, BlurMaskFilter.Blur.SOLID));
                return;
            }
        }
        getIndicatorPaint().setMaskFilter(null);
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void updateIndicator() {
        this.g.reset();
        Path path = this.g;
        float centerX = getCenterX();
        Speedometer speedometer = getSpeedometer();
        Intrinsics.checkNotNull(speedometer);
        path.moveTo(centerX, speedometer.getPadding());
        this.g.lineTo(getCenterX(), getCenterY() * this.f);
        getIndicatorPaint().setStyle(Paint.Style.STROKE);
        getIndicatorPaint().setStrokeWidth(getWidth());
        getIndicatorPaint().setColor(getColor());
    }
}
