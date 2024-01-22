package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class MarkerImage implements IMarker {
    public Context h;
    public Drawable i;
    public WeakReference<Chart> l;
    public MPPointF j = new MPPointF();
    public MPPointF k = new MPPointF();
    public FSize m = new FSize();
    public Rect n = new Rect();

    public MarkerImage(Context context, int i) {
        this.h = context;
        if (Build.VERSION.SDK_INT >= 21) {
            this.i = context.getResources().getDrawable(i, null);
        } else {
            this.i = context.getResources().getDrawable(i);
        }
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public void draw(Canvas canvas, float f, float f2) {
        if (this.i == null) {
            return;
        }
        MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(f, f2);
        FSize fSize = this.m;
        float f3 = fSize.width;
        float f4 = fSize.height;
        if (f3 == 0.0f) {
            f3 = this.i.getIntrinsicWidth();
        }
        if (f4 == 0.0f) {
            f4 = this.i.getIntrinsicHeight();
        }
        this.i.copyBounds(this.n);
        Drawable drawable = this.i;
        Rect rect = this.n;
        int i = rect.left;
        int i2 = rect.top;
        drawable.setBounds(i, i2, ((int) f3) + i, ((int) f4) + i2);
        int save = canvas.save();
        canvas.translate(f + offsetForDrawingAtPoint.x, f2 + offsetForDrawingAtPoint.y);
        this.i.draw(canvas);
        canvas.restoreToCount(save);
        this.i.setBounds(this.n);
    }

    public Chart getChartView() {
        WeakReference<Chart> weakReference = this.l;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public MPPointF getOffset() {
        return this.j;
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public MPPointF getOffsetForDrawingAtPoint(float f, float f2) {
        Drawable drawable;
        Drawable drawable2;
        MPPointF offset = getOffset();
        MPPointF mPPointF = this.k;
        mPPointF.x = offset.x;
        mPPointF.y = offset.y;
        Chart chartView = getChartView();
        FSize fSize = this.m;
        float f3 = fSize.width;
        float f4 = fSize.height;
        if (f3 == 0.0f && (drawable2 = this.i) != null) {
            f3 = drawable2.getIntrinsicWidth();
        }
        if (f4 == 0.0f && (drawable = this.i) != null) {
            f4 = drawable.getIntrinsicHeight();
        }
        MPPointF mPPointF2 = this.k;
        float f5 = mPPointF2.x;
        if (f + f5 < 0.0f) {
            mPPointF2.x = -f;
        } else if (chartView != null && f + f3 + f5 > chartView.getWidth()) {
            this.k.x = (chartView.getWidth() - f) - f3;
        }
        MPPointF mPPointF3 = this.k;
        float f6 = mPPointF3.y;
        if (f2 + f6 < 0.0f) {
            mPPointF3.y = -f2;
        } else if (chartView != null && f2 + f4 + f6 > chartView.getHeight()) {
            this.k.y = (chartView.getHeight() - f2) - f4;
        }
        return this.k;
    }

    public FSize getSize() {
        return this.m;
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public void refreshContent(Entry entry, Highlight highlight) {
    }

    public void setChartView(Chart chart) {
        this.l = new WeakReference<>(chart);
    }

    public void setOffset(MPPointF mPPointF) {
        this.j = mPPointF;
        if (mPPointF == null) {
            this.j = new MPPointF();
        }
    }

    public void setSize(FSize fSize) {
        this.m = fSize;
        if (fSize == null) {
            this.m = new FSize();
        }
    }

    public void setOffset(float f, float f2) {
        MPPointF mPPointF = this.j;
        mPPointF.x = f;
        mPPointF.y = f2;
    }
}
