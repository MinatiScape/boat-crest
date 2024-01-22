package com.github.anastr.speedviewlib.components.indicators;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Path;
import com.github.anastr.speedviewlib.Speedometer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0014¨\u0006\u0010"}, d2 = {"Lcom/github/anastr/speedviewlib/components/indicators/SpindleIndicator;", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "", "getTop", "Landroid/graphics/Canvas;", "canvas", "", "draw", "updateIndicator", "", "withEffects", "setWithEffects", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class SpindleIndicator extends Indicator<SpindleIndicator> {
    @NotNull
    public final Path f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpindleIndicator(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f = new Path();
        setWidth(dpTOpx(16.0f));
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawPath(this.f, getIndicatorPaint());
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public float getTop() {
        Speedometer speedometer = getSpeedometer();
        Intrinsics.checkNotNull(speedometer);
        return (getViewSize() * 0.18f) + speedometer.getPadding();
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
        this.f.reset();
        this.f.moveTo(getCenterX(), getCenterY());
        Path path = this.f;
        float centerX = getCenterX() - getWidth();
        Speedometer speedometer = getSpeedometer();
        Intrinsics.checkNotNull(speedometer);
        float viewSize = (getViewSize() * 0.34f) + speedometer.getPadding();
        float centerX2 = getCenterX();
        Speedometer speedometer2 = getSpeedometer();
        Intrinsics.checkNotNull(speedometer2);
        path.quadTo(centerX, viewSize, centerX2, (getViewSize() * 0.18f) + speedometer2.getPadding());
        Speedometer speedometer3 = getSpeedometer();
        Intrinsics.checkNotNull(speedometer3);
        this.f.quadTo(getCenterX() + getWidth(), (getViewSize() * 0.34f) + speedometer3.getPadding(), getCenterX(), getCenterY());
        getIndicatorPaint().setColor(getColor());
    }
}
