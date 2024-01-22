package com.github.anastr.speedviewlib.components.indicators;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.Shader;
import com.github.anastr.speedviewlib.Speedometer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0014¨\u0006\u0011"}, d2 = {"Lcom/github/anastr/speedviewlib/components/indicators/TriangleIndicator;", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "", "getTop", "getBottom", "Landroid/graphics/Canvas;", "canvas", "", "draw", "updateIndicator", "", "withEffects", "setWithEffects", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class TriangleIndicator extends Indicator<TriangleIndicator> {
    @NotNull
    public Path f;
    public float g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriangleIndicator(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f = new Path();
        setWidth(dpTOpx(25.0f));
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawPath(this.f, getIndicatorPaint());
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public float getBottom() {
        return this.g + getWidth();
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public float getTop() {
        return this.g;
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
        this.f = new Path();
        Speedometer speedometer = getSpeedometer();
        Intrinsics.checkNotNull(speedometer);
        Speedometer speedometer2 = getSpeedometer();
        Intrinsics.checkNotNull(speedometer2);
        this.g = speedometer.getPadding() + speedometer2.getSpeedometerWidth() + dpTOpx(5.0f);
        this.f.moveTo(getCenterX(), this.g);
        this.f.lineTo(getCenterX() - getWidth(), this.g + getWidth());
        this.f.lineTo(getCenterX() + getWidth(), this.g + getWidth());
        this.f.moveTo(0.0f, 0.0f);
        getIndicatorPaint().setShader(new LinearGradient(getCenterX(), this.g, getCenterX(), this.g + getWidth(), getColor(), Color.argb(0, Color.red(getColor()), Color.green(getColor()), Color.blue(getColor())), Shader.TileMode.CLAMP));
    }
}
