package com.coveiot.android.leonardo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes5.dex */
public class ImageBarChartRenderer extends BarChartRenderer {
    public final Bitmap b;

    public ImageBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Bitmap bitmap) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.b = bitmap;
    }

    public final Bitmap a(BarBuffer barBuffer) {
        float[] fArr = barBuffer.buffer;
        int ceil = (int) Math.ceil(fArr[2] - fArr[0]);
        return Bitmap.createScaledBitmap(this.b, ceil, ceil, false);
    }

    public void drawBarImages(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        BarBuffer barBuffer = this.mBarBuffers[i];
        Bitmap a2 = a(barBuffer);
        int width = a2.getWidth() / 2;
        for (int i2 = 0; i2 < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i2 += 4) {
            float[] fArr = barBuffer.buffer;
            float f = fArr[i2];
            float f2 = fArr[i2 + 2];
            float f3 = fArr[i2 + 1];
            float f4 = fArr[i2 + 3];
            float f5 = (f + f2) / 2.0f;
            if (!this.mViewPortHandler.isInBoundsRight(f5)) {
                return;
            }
            if (this.mViewPortHandler.isInBoundsY(f3) && this.mViewPortHandler.isInBoundsLeft(f5) && ((BarEntry) iBarDataSet.getEntryForIndex(i2 / 4)).getY() > 50.0f) {
                drawImage(canvas, a2, f5 - width, 10.0f);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        super.drawData(canvas);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        super.drawDataSet(canvas, iBarDataSet, i);
        drawBarImages(canvas, iBarDataSet, i);
    }

    public void drawImage(Canvas canvas, Bitmap bitmap, float f, float f2) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, f, f2, (Paint) null);
        }
    }
}
