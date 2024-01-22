package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.List;
/* loaded from: classes9.dex */
public class PieChartRenderer extends DataRenderer {

    /* renamed from: a  reason: collision with root package name */
    public TextPaint f7960a;
    public Paint b;
    public StaticLayout c;
    public CharSequence d;
    public RectF e;
    public RectF[] f;
    public Path g;
    public RectF h;
    public Path i;
    public Canvas mBitmapCanvas;
    public PieChart mChart;
    public WeakReference<Bitmap> mDrawBitmap;
    public Path mDrawCenterTextPathBuffer;
    public RectF mDrawHighlightedRectF;
    public Paint mHolePaint;
    public Paint mTransparentCirclePaint;
    public Paint mValueLinePaint;

    public PieChartRenderer(PieChart pieChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.e = new RectF();
        this.f = new RectF[]{new RectF(), new RectF(), new RectF()};
        this.g = new Path();
        this.h = new RectF();
        this.i = new Path();
        this.mDrawCenterTextPathBuffer = new Path();
        this.mDrawHighlightedRectF = new RectF();
        this.mChart = pieChart;
        Paint paint = new Paint(1);
        this.mHolePaint = paint;
        paint.setColor(-1);
        this.mHolePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.mTransparentCirclePaint = paint2;
        paint2.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint.setAlpha(105);
        TextPaint textPaint = new TextPaint(1);
        this.f7960a = textPaint;
        textPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f7960a.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        Paint paint3 = new Paint(1);
        this.b = paint3;
        paint3.setColor(-1);
        this.b.setTextAlign(Paint.Align.CENTER);
        this.b.setTextSize(Utils.convertDpToPixel(13.0f));
        Paint paint4 = new Paint(1);
        this.mValueLinePaint = paint4;
        paint4.setStyle(Paint.Style.STROKE);
    }

    public float calculateMinimumRadiusForSpacedSlice(MPPointF mPPointF, float f, float f2, float f3, float f4, float f5, float f6) {
        double d = (f5 + f6) * 0.017453292f;
        float cos = mPPointF.x + (((float) Math.cos(d)) * f);
        float sin = mPPointF.y + (((float) Math.sin(d)) * f);
        double d2 = (f5 + (f6 / 2.0f)) * 0.017453292f;
        return (float) ((f - ((float) ((Math.sqrt(Math.pow(cos - f3, 2.0d) + Math.pow(sin - f4, 2.0d)) / 2.0d) * Math.tan(((180.0d - f2) / 2.0d) * 0.017453292519943295d)))) - Math.sqrt(Math.pow((mPPointF.x + (((float) Math.cos(d2)) * f)) - ((cos + f3) / 2.0f), 2.0d) + Math.pow((mPPointF.y + (((float) Math.sin(d2)) * f)) - ((sin + f4) / 2.0f), 2.0d)));
    }

    public void drawCenterText(Canvas canvas) {
        float radius;
        MPPointF mPPointF;
        CharSequence centerText = this.mChart.getCenterText();
        if (!this.mChart.isDrawCenterTextEnabled() || centerText == null) {
            return;
        }
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        MPPointF centerTextOffset = this.mChart.getCenterTextOffset();
        float f = centerCircleBox.x + centerTextOffset.x;
        float f2 = centerCircleBox.y + centerTextOffset.y;
        if (this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled()) {
            radius = this.mChart.getRadius() * (this.mChart.getHoleRadius() / 100.0f);
        } else {
            radius = this.mChart.getRadius();
        }
        RectF[] rectFArr = this.f;
        RectF rectF = rectFArr[0];
        rectF.left = f - radius;
        rectF.top = f2 - radius;
        rectF.right = f + radius;
        rectF.bottom = f2 + radius;
        RectF rectF2 = rectFArr[1];
        rectF2.set(rectF);
        float centerTextRadiusPercent = this.mChart.getCenterTextRadiusPercent() / 100.0f;
        if (centerTextRadiusPercent > 0.0d) {
            rectF2.inset((rectF2.width() - (rectF2.width() * centerTextRadiusPercent)) / 2.0f, (rectF2.height() - (rectF2.height() * centerTextRadiusPercent)) / 2.0f);
        }
        if (centerText.equals(this.d) && rectF2.equals(this.e)) {
            mPPointF = centerTextOffset;
        } else {
            this.e.set(rectF2);
            this.d = centerText;
            mPPointF = centerTextOffset;
            this.c = new StaticLayout(centerText, 0, centerText.length(), this.f7960a, (int) Math.max(Math.ceil(this.e.width()), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
        float height = this.c.getHeight();
        canvas.save();
        if (Build.VERSION.SDK_INT >= 18) {
            Path path = this.mDrawCenterTextPathBuffer;
            path.reset();
            path.addOval(rectF, Path.Direction.CW);
            canvas.clipPath(path);
        }
        canvas.translate(rectF2.left, rectF2.top + ((rectF2.height() - height) / 2.0f));
        this.c.draw(canvas);
        canvas.restore();
        MPPointF.recycleInstance(centerCircleBox);
        MPPointF.recycleInstance(mPPointF);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        Bitmap bitmap = weakReference == null ? null : weakReference.get();
        if (bitmap == null || bitmap.getWidth() != chartWidth || bitmap.getHeight() != chartHeight) {
            if (chartWidth <= 0 || chartHeight <= 0) {
                return;
            }
            bitmap = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_4444);
            this.mDrawBitmap = new WeakReference<>(bitmap);
            this.mBitmapCanvas = new Canvas(bitmap);
        }
        bitmap.eraseColor(0);
        for (IPieDataSet iPieDataSet : ((PieData) this.mChart.getData()).getDataSets()) {
            if (iPieDataSet.isVisible() && iPieDataSet.getEntryCount() > 0) {
                drawDataSet(canvas, iPieDataSet);
            }
        }
    }

    public void drawDataSet(Canvas canvas, IPieDataSet iPieDataSet) {
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        RectF rectF;
        int i3;
        float[] fArr;
        int i4;
        float f4;
        MPPointF mPPointF;
        float f5;
        float f6;
        MPPointF mPPointF2;
        float f7;
        int i5;
        PieChartRenderer pieChartRenderer = this;
        IPieDataSet iPieDataSet2 = iPieDataSet;
        float rotationAngle = pieChartRenderer.mChart.getRotationAngle();
        float phaseX = pieChartRenderer.mAnimator.getPhaseX();
        float phaseY = pieChartRenderer.mAnimator.getPhaseY();
        RectF circleBox = pieChartRenderer.mChart.getCircleBox();
        int entryCount = iPieDataSet.getEntryCount();
        float[] drawAngles = pieChartRenderer.mChart.getDrawAngles();
        MPPointF centerCircleBox = pieChartRenderer.mChart.getCenterCircleBox();
        float radius = pieChartRenderer.mChart.getRadius();
        int i6 = 1;
        boolean z = pieChartRenderer.mChart.isDrawHoleEnabled() && !pieChartRenderer.mChart.isDrawSlicesUnderHoleEnabled();
        float holeRadius = z ? (pieChartRenderer.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
        int i7 = 0;
        for (int i8 = 0; i8 < entryCount; i8++) {
            if (Math.abs(iPieDataSet2.getEntryForIndex(i8).getY()) > Utils.FLOAT_EPSILON) {
                i7++;
            }
        }
        float sliceSpace = i7 <= 1 ? 0.0f : pieChartRenderer.getSliceSpace(iPieDataSet2);
        int i9 = 0;
        float f8 = 0.0f;
        while (i9 < entryCount) {
            float f9 = drawAngles[i9];
            float abs = Math.abs(iPieDataSet2.getEntryForIndex(i9).getY());
            float f10 = Utils.FLOAT_EPSILON;
            if (abs <= f10 || pieChartRenderer.mChart.needsHighlight(i9)) {
                i = i9;
                i2 = i6;
                f = radius;
                f2 = rotationAngle;
                f3 = phaseX;
                rectF = circleBox;
                i3 = entryCount;
                fArr = drawAngles;
                i4 = i7;
                f4 = holeRadius;
                mPPointF = centerCircleBox;
            } else {
                int i10 = (sliceSpace <= 0.0f || f9 > 180.0f) ? 0 : i6;
                pieChartRenderer.mRenderPaint.setColor(iPieDataSet2.getColor(i9));
                float f11 = i7 == 1 ? 0.0f : sliceSpace / (radius * 0.017453292f);
                float f12 = rotationAngle + ((f8 + (f11 / 2.0f)) * phaseY);
                float f13 = (f9 - f11) * phaseY;
                if (f13 < 0.0f) {
                    f13 = 0.0f;
                }
                pieChartRenderer.g.reset();
                int i11 = i9;
                int i12 = i7;
                double d = f12 * 0.017453292f;
                i3 = entryCount;
                fArr = drawAngles;
                float cos = centerCircleBox.x + (((float) Math.cos(d)) * radius);
                float sin = centerCircleBox.y + (((float) Math.sin(d)) * radius);
                int i13 = (f13 > 360.0f ? 1 : (f13 == 360.0f ? 0 : -1));
                if (i13 >= 0 && f13 % 360.0f <= f10) {
                    f3 = phaseX;
                    pieChartRenderer.g.addCircle(centerCircleBox.x, centerCircleBox.y, radius, Path.Direction.CW);
                } else {
                    f3 = phaseX;
                    pieChartRenderer.g.moveTo(cos, sin);
                    pieChartRenderer.g.arcTo(circleBox, f12, f13);
                }
                RectF rectF2 = pieChartRenderer.h;
                float f14 = centerCircleBox.x;
                float f15 = centerCircleBox.y;
                float f16 = f13;
                rectF2.set(f14 - holeRadius, f15 - holeRadius, f14 + holeRadius, f15 + holeRadius);
                if (!z) {
                    f4 = holeRadius;
                    f2 = rotationAngle;
                    f5 = f16;
                    i2 = 1;
                    f = radius;
                    mPPointF = centerCircleBox;
                    rectF = circleBox;
                    i4 = i12;
                    i = i11;
                    f6 = 360.0f;
                } else if (holeRadius > 0.0f || i10 != 0) {
                    if (i10 != 0) {
                        f7 = f16;
                        rectF = circleBox;
                        i4 = i12;
                        i = i11;
                        f4 = holeRadius;
                        i5 = 1;
                        f = radius;
                        mPPointF2 = centerCircleBox;
                        float calculateMinimumRadiusForSpacedSlice = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f9 * phaseY, cos, sin, f12, f7);
                        if (calculateMinimumRadiusForSpacedSlice < 0.0f) {
                            calculateMinimumRadiusForSpacedSlice = -calculateMinimumRadiusForSpacedSlice;
                        }
                        holeRadius = Math.max(f4, calculateMinimumRadiusForSpacedSlice);
                    } else {
                        f4 = holeRadius;
                        mPPointF2 = centerCircleBox;
                        f7 = f16;
                        i5 = 1;
                        f = radius;
                        rectF = circleBox;
                        i4 = i12;
                        i = i11;
                    }
                    float f17 = (i4 == i5 || holeRadius == 0.0f) ? 0.0f : sliceSpace / (holeRadius * 0.017453292f);
                    float f18 = ((f8 + (f17 / 2.0f)) * phaseY) + rotationAngle;
                    float f19 = (f9 - f17) * phaseY;
                    if (f19 < 0.0f) {
                        f19 = 0.0f;
                    }
                    float f20 = f18 + f19;
                    if (i13 >= 0 && f7 % 360.0f <= f10) {
                        i2 = i5;
                        pieChartRenderer = this;
                        pieChartRenderer.g.addCircle(mPPointF2.x, mPPointF2.y, holeRadius, Path.Direction.CCW);
                        f2 = rotationAngle;
                    } else {
                        i2 = i5;
                        pieChartRenderer = this;
                        double d2 = f20 * 0.017453292f;
                        f2 = rotationAngle;
                        pieChartRenderer.g.lineTo(mPPointF2.x + (((float) Math.cos(d2)) * holeRadius), mPPointF2.y + (holeRadius * ((float) Math.sin(d2))));
                        pieChartRenderer.g.arcTo(pieChartRenderer.h, f20, -f19);
                    }
                    mPPointF = mPPointF2;
                    pieChartRenderer.g.close();
                    pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.g, pieChartRenderer.mRenderPaint);
                } else {
                    f4 = holeRadius;
                    f2 = rotationAngle;
                    f5 = f16;
                    f6 = 360.0f;
                    i2 = 1;
                    f = radius;
                    mPPointF = centerCircleBox;
                    rectF = circleBox;
                    i4 = i12;
                    i = i11;
                }
                if (f5 % f6 > f10) {
                    if (i10 != 0) {
                        float calculateMinimumRadiusForSpacedSlice2 = calculateMinimumRadiusForSpacedSlice(mPPointF, f, f9 * phaseY, cos, sin, f12, f5);
                        double d3 = (f12 + (f5 / 2.0f)) * 0.017453292f;
                        pieChartRenderer.g.lineTo(mPPointF.x + (((float) Math.cos(d3)) * calculateMinimumRadiusForSpacedSlice2), mPPointF.y + (calculateMinimumRadiusForSpacedSlice2 * ((float) Math.sin(d3))));
                    } else {
                        pieChartRenderer.g.lineTo(mPPointF.x, mPPointF.y);
                    }
                }
                pieChartRenderer.g.close();
                pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.g, pieChartRenderer.mRenderPaint);
            }
            f8 += f9 * f3;
            i9 = i + 1;
            iPieDataSet2 = iPieDataSet;
            centerCircleBox = mPPointF;
            i7 = i4;
            holeRadius = f4;
            circleBox = rectF;
            entryCount = i3;
            drawAngles = fArr;
            i6 = i2;
            phaseX = f3;
            radius = f;
            rotationAngle = f2;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    public void drawEntryLabel(Canvas canvas, String str, float f, float f2) {
        canvas.drawText(str, f, f2, this.b);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        drawHole(canvas);
        canvas.drawBitmap(this.mDrawBitmap.get(), 0.0f, 0.0f, (Paint) null);
        drawCenterText(canvas);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i;
        RectF rectF;
        float f;
        float f2;
        float f3;
        float[] fArr;
        float[] fArr2;
        IPieDataSet dataSetByIndex;
        float f4;
        int i2;
        float f5;
        float f6;
        int i3;
        int i4;
        float f7;
        float f8;
        Highlight[] highlightArr2 = highlightArr;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        boolean z = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        float f9 = 0.0f;
        float holeRadius = z ? (this.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
        RectF rectF2 = this.mDrawHighlightedRectF;
        rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
        int i5 = 0;
        while (i5 < highlightArr2.length) {
            int x = (int) highlightArr2[i5].getX();
            if (x < drawAngles.length && (dataSetByIndex = ((PieData) this.mChart.getData()).getDataSetByIndex(highlightArr2[i5].getDataSetIndex())) != null && dataSetByIndex.isHighlightEnabled()) {
                int entryCount = dataSetByIndex.getEntryCount();
                int i6 = 0;
                for (int i7 = 0; i7 < entryCount; i7++) {
                    if (Math.abs(dataSetByIndex.getEntryForIndex(i7).getY()) > Utils.FLOAT_EPSILON) {
                        i6++;
                    }
                }
                if (x == 0) {
                    i2 = 1;
                    f4 = 0.0f;
                } else {
                    f4 = absoluteAngles[x - 1] * phaseX;
                    i2 = 1;
                }
                float sliceSpace = i6 <= i2 ? 0.0f : dataSetByIndex.getSliceSpace();
                float f10 = drawAngles[x];
                float selectionShift = dataSetByIndex.getSelectionShift();
                float f11 = radius + selectionShift;
                int i8 = i5;
                rectF2.set(this.mChart.getCircleBox());
                float f12 = -selectionShift;
                rectF2.inset(f12, f12);
                boolean z2 = sliceSpace > 0.0f && f10 <= 180.0f;
                this.mRenderPaint.setColor(dataSetByIndex.getColor(x));
                float f13 = i6 == 1 ? 0.0f : sliceSpace / (radius * 0.017453292f);
                float f14 = i6 == 1 ? 0.0f : sliceSpace / (f11 * 0.017453292f);
                float f15 = rotationAngle + ((f4 + (f13 / 2.0f)) * phaseY);
                float f16 = (f10 - f13) * phaseY;
                float f17 = f16 < 0.0f ? 0.0f : f16;
                float f18 = ((f4 + (f14 / 2.0f)) * phaseY) + rotationAngle;
                float f19 = (f10 - f14) * phaseY;
                if (f19 < 0.0f) {
                    f19 = 0.0f;
                }
                this.g.reset();
                int i9 = (f17 > 360.0f ? 1 : (f17 == 360.0f ? 0 : -1));
                if (i9 >= 0 && f17 % 360.0f <= Utils.FLOAT_EPSILON) {
                    this.g.addCircle(centerCircleBox.x, centerCircleBox.y, f11, Path.Direction.CW);
                    f5 = holeRadius;
                    f3 = phaseX;
                    fArr = drawAngles;
                    fArr2 = absoluteAngles;
                } else {
                    f5 = holeRadius;
                    f3 = phaseX;
                    double d = f18 * 0.017453292f;
                    fArr = drawAngles;
                    fArr2 = absoluteAngles;
                    this.g.moveTo(centerCircleBox.x + (((float) Math.cos(d)) * f11), centerCircleBox.y + (f11 * ((float) Math.sin(d))));
                    this.g.arcTo(rectF2, f18, f19);
                }
                if (z2) {
                    double d2 = f15 * 0.017453292f;
                    i = i8;
                    f6 = f5;
                    f2 = 0.0f;
                    rectF = rectF2;
                    i4 = 1;
                    i3 = i6;
                    f7 = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f10 * phaseY, (((float) Math.cos(d2)) * radius) + centerCircleBox.x, centerCircleBox.y + (((float) Math.sin(d2)) * radius), f15, f17);
                } else {
                    f6 = f5;
                    rectF = rectF2;
                    i3 = i6;
                    i = i8;
                    f2 = 0.0f;
                    i4 = 1;
                    f7 = 0.0f;
                }
                RectF rectF3 = this.h;
                float f20 = centerCircleBox.x;
                float f21 = centerCircleBox.y;
                rectF3.set(f20 - f6, f21 - f6, f20 + f6, f21 + f6);
                if (z && (f6 > f2 || z2)) {
                    if (z2) {
                        if (f7 < f2) {
                            f7 = -f7;
                        }
                        f8 = Math.max(f6, f7);
                    } else {
                        f8 = f6;
                    }
                    float f22 = (i3 == i4 || f8 == f2) ? f2 : sliceSpace / (f8 * 0.017453292f);
                    float f23 = rotationAngle + ((f4 + (f22 / 2.0f)) * phaseY);
                    float f24 = (f10 - f22) * phaseY;
                    if (f24 < f2) {
                        f24 = f2;
                    }
                    float f25 = f23 + f24;
                    if (i9 >= 0 && f17 % 360.0f <= Utils.FLOAT_EPSILON) {
                        this.g.addCircle(centerCircleBox.x, centerCircleBox.y, f8, Path.Direction.CCW);
                        f = f6;
                    } else {
                        double d3 = f25 * 0.017453292f;
                        f = f6;
                        this.g.lineTo(centerCircleBox.x + (((float) Math.cos(d3)) * f8), centerCircleBox.y + (f8 * ((float) Math.sin(d3))));
                        this.g.arcTo(this.h, f25, -f24);
                    }
                } else {
                    f = f6;
                    if (f17 % 360.0f > Utils.FLOAT_EPSILON) {
                        if (z2) {
                            double d4 = (f15 + (f17 / 2.0f)) * 0.017453292f;
                            this.g.lineTo(centerCircleBox.x + (((float) Math.cos(d4)) * f7), centerCircleBox.y + (f7 * ((float) Math.sin(d4))));
                        } else {
                            this.g.lineTo(centerCircleBox.x, centerCircleBox.y);
                        }
                    }
                }
                this.g.close();
                this.mBitmapCanvas.drawPath(this.g, this.mRenderPaint);
            } else {
                i = i5;
                rectF = rectF2;
                f = holeRadius;
                f2 = f9;
                f3 = phaseX;
                fArr = drawAngles;
                fArr2 = absoluteAngles;
            }
            i5 = i + 1;
            rectF2 = rectF;
            highlightArr2 = highlightArr;
            holeRadius = f;
            f9 = f2;
            phaseX = f3;
            drawAngles = fArr;
            absoluteAngles = fArr2;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    public void drawHole(Canvas canvas) {
        if (!this.mChart.isDrawHoleEnabled() || this.mBitmapCanvas == null) {
            return;
        }
        float radius = this.mChart.getRadius();
        float holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        if (Color.alpha(this.mHolePaint.getColor()) > 0) {
            this.mBitmapCanvas.drawCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, this.mHolePaint);
        }
        if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
            int alpha = this.mTransparentCirclePaint.getAlpha();
            float transparentCircleRadius = radius * (this.mChart.getTransparentCircleRadius() / 100.0f);
            this.mTransparentCirclePaint.setAlpha((int) (alpha * this.mAnimator.getPhaseX() * this.mAnimator.getPhaseY()));
            this.i.reset();
            this.i.addCircle(centerCircleBox.x, centerCircleBox.y, transparentCircleRadius, Path.Direction.CW);
            this.i.addCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, Path.Direction.CCW);
            this.mBitmapCanvas.drawPath(this.i, this.mTransparentCirclePaint);
            this.mTransparentCirclePaint.setAlpha(alpha);
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    public void drawRoundedSlices(Canvas canvas) {
        float f;
        float[] fArr;
        float f2;
        if (this.mChart.isDrawRoundedSlicesEnabled()) {
            IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
            if (dataSet.isVisible()) {
                float phaseX = this.mAnimator.getPhaseX();
                float phaseY = this.mAnimator.getPhaseY();
                MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
                float radius = this.mChart.getRadius();
                float holeRadius = (radius - ((this.mChart.getHoleRadius() * radius) / 100.0f)) / 2.0f;
                float[] drawAngles = this.mChart.getDrawAngles();
                float rotationAngle = this.mChart.getRotationAngle();
                int i = 0;
                while (i < dataSet.getEntryCount()) {
                    float f3 = drawAngles[i];
                    if (Math.abs(dataSet.getEntryForIndex(i).getY()) > Utils.FLOAT_EPSILON) {
                        double d = radius - holeRadius;
                        double d2 = (rotationAngle + f3) * phaseY;
                        f = phaseY;
                        fArr = drawAngles;
                        f2 = rotationAngle;
                        float cos = (float) (centerCircleBox.x + (Math.cos(Math.toRadians(d2)) * d));
                        float sin = (float) ((d * Math.sin(Math.toRadians(d2))) + centerCircleBox.y);
                        this.mRenderPaint.setColor(dataSet.getColor(i));
                        this.mBitmapCanvas.drawCircle(cos, sin, holeRadius, this.mRenderPaint);
                    } else {
                        f = phaseY;
                        fArr = drawAngles;
                        f2 = rotationAngle;
                    }
                    rotationAngle = f2 + (f3 * phaseX);
                    i++;
                    phaseY = f;
                    drawAngles = fArr;
                }
                MPPointF.recycleInstance(centerCircleBox);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i;
        List<IPieDataSet> list;
        float f;
        float f2;
        float[] fArr;
        float[] fArr2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        PieDataSet.ValuePosition valuePosition;
        int i2;
        IPieDataSet iPieDataSet;
        List<IPieDataSet> list2;
        float f8;
        IPieDataSet iPieDataSet2;
        float f9;
        MPPointF mPPointF;
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float holeRadius = this.mChart.getHoleRadius() / 100.0f;
        float f10 = (radius / 10.0f) * 3.6f;
        if (this.mChart.isDrawHoleEnabled()) {
            f10 = (radius - (radius * holeRadius)) / 2.0f;
        }
        float f11 = radius - f10;
        PieData pieData = (PieData) this.mChart.getData();
        List<IPieDataSet> dataSets = pieData.getDataSets();
        float yValueSum = pieData.getYValueSum();
        boolean isDrawEntryLabelsEnabled = this.mChart.isDrawEntryLabelsEnabled();
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        int i3 = 0;
        int i4 = 0;
        while (i4 < dataSets.size()) {
            IPieDataSet iPieDataSet3 = dataSets.get(i4);
            boolean isDrawValuesEnabled = iPieDataSet3.isDrawValuesEnabled();
            if (isDrawValuesEnabled || isDrawEntryLabelsEnabled) {
                PieDataSet.ValuePosition xValuePosition = iPieDataSet3.getXValuePosition();
                PieDataSet.ValuePosition yValuePosition = iPieDataSet3.getYValuePosition();
                applyValueTextStyle(iPieDataSet3);
                float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "Q") + Utils.convertDpToPixel(4.0f);
                IValueFormatter valueFormatter = iPieDataSet3.getValueFormatter();
                int entryCount = iPieDataSet3.getEntryCount();
                this.mValueLinePaint.setColor(iPieDataSet3.getValueLineColor());
                this.mValueLinePaint.setStrokeWidth(Utils.convertDpToPixel(iPieDataSet3.getValueLineWidth()));
                float sliceSpace = getSliceSpace(iPieDataSet3);
                MPPointF mPPointF2 = MPPointF.getInstance(iPieDataSet3.getIconsOffset());
                mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                int i5 = i3;
                int i6 = 0;
                while (i6 < entryCount) {
                    PieEntry entryForIndex = iPieDataSet3.getEntryForIndex(i6);
                    float f12 = (((i5 == 0 ? 0.0f : absoluteAngles[i5 - 1] * phaseX) + ((drawAngles[i5] - ((sliceSpace / (f11 * 0.017453292f)) / 2.0f)) / 2.0f)) * phaseY) + rotationAngle;
                    MPPointF mPPointF3 = mPPointF2;
                    float y = this.mChart.isUsePercentValuesEnabled() ? (entryForIndex.getY() / yValueSum) * 100.0f : entryForIndex.getY();
                    int i7 = entryCount;
                    double d = f12 * 0.017453292f;
                    int i8 = i4;
                    List<IPieDataSet> list3 = dataSets;
                    float cos = (float) Math.cos(d);
                    float f13 = rotationAngle;
                    float[] fArr3 = drawAngles;
                    float sin = (float) Math.sin(d);
                    boolean z = isDrawEntryLabelsEnabled && xValuePosition == PieDataSet.ValuePosition.OUTSIDE_SLICE;
                    boolean z2 = isDrawValuesEnabled && yValuePosition == PieDataSet.ValuePosition.OUTSIDE_SLICE;
                    float[] fArr4 = absoluteAngles;
                    boolean z3 = isDrawEntryLabelsEnabled && xValuePosition == PieDataSet.ValuePosition.INSIDE_SLICE;
                    PieDataSet.ValuePosition valuePosition2 = xValuePosition;
                    boolean z4 = isDrawValuesEnabled && yValuePosition == PieDataSet.ValuePosition.INSIDE_SLICE;
                    if (z || z2) {
                        float valueLinePart1Length = iPieDataSet3.getValueLinePart1Length();
                        float valueLinePart2Length = iPieDataSet3.getValueLinePart2Length();
                        float valueLinePart1OffsetPercentage = iPieDataSet3.getValueLinePart1OffsetPercentage() / 100.0f;
                        PieDataSet.ValuePosition valuePosition3 = yValuePosition;
                        if (this.mChart.isDrawHoleEnabled()) {
                            float f14 = radius * holeRadius;
                            f3 = ((radius - f14) * valueLinePart1OffsetPercentage) + f14;
                        } else {
                            f3 = radius * valueLinePart1OffsetPercentage;
                        }
                        float abs = iPieDataSet3.isValueLineVariableLength() ? valueLinePart2Length * f11 * ((float) Math.abs(Math.sin(d))) : valueLinePart2Length * f11;
                        float f15 = centerCircleBox.x;
                        float f16 = (f3 * cos) + f15;
                        float f17 = centerCircleBox.y;
                        float f18 = (f3 * sin) + f17;
                        float f19 = (valueLinePart1Length + 1.0f) * f11;
                        float f20 = (f19 * cos) + f15;
                        float f21 = (f19 * sin) + f17;
                        double d2 = f12 % 360.0d;
                        if (d2 >= 90.0d && d2 <= 270.0d) {
                            float f22 = f20 - abs;
                            this.mValuePaint.setTextAlign(Paint.Align.RIGHT);
                            if (z) {
                                this.b.setTextAlign(Paint.Align.RIGHT);
                            }
                            f4 = f22;
                            f5 = f22 - convertDpToPixel;
                        } else {
                            f4 = f20 + abs;
                            this.mValuePaint.setTextAlign(Paint.Align.LEFT);
                            if (z) {
                                this.b.setTextAlign(Paint.Align.LEFT);
                            }
                            f5 = f4 + convertDpToPixel;
                        }
                        if (iPieDataSet3.getValueLineColor() != 1122867) {
                            if (iPieDataSet3.isUsingSliceColorAsValueLineColor()) {
                                this.mValueLinePaint.setColor(iPieDataSet3.getColor(i6));
                            }
                            f7 = radius;
                            i2 = i6;
                            valuePosition = valuePosition3;
                            f6 = f5;
                            canvas.drawLine(f16, f18, f20, f21, this.mValueLinePaint);
                            canvas.drawLine(f20, f21, f4, f21, this.mValueLinePaint);
                        } else {
                            f6 = f5;
                            f7 = radius;
                            valuePosition = valuePosition3;
                            i2 = i6;
                        }
                        if (z && z2) {
                            iPieDataSet = iPieDataSet3;
                            list2 = list3;
                            f8 = cos;
                            drawValue(canvas, valueFormatter, y, entryForIndex, 0, f6, f21, iPieDataSet3.getValueTextColor(i2));
                            if (i2 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                drawEntryLabel(canvas, entryForIndex.getLabel(), f6, f21 + calcTextHeight);
                            }
                        } else {
                            iPieDataSet = iPieDataSet3;
                            list2 = list3;
                            float f23 = f6;
                            f8 = cos;
                            if (z) {
                                if (i2 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                    drawEntryLabel(canvas, entryForIndex.getLabel(), f23, f21 + (calcTextHeight / 2.0f));
                                }
                            } else if (z2) {
                                iPieDataSet2 = iPieDataSet;
                                drawValue(canvas, valueFormatter, y, entryForIndex, 0, f23, f21 + (calcTextHeight / 2.0f), iPieDataSet2.getValueTextColor(i2));
                            }
                        }
                        iPieDataSet2 = iPieDataSet;
                    } else {
                        valuePosition = yValuePosition;
                        iPieDataSet2 = iPieDataSet3;
                        f7 = radius;
                        list2 = list3;
                        i2 = i6;
                        f8 = cos;
                    }
                    if (z3 || z4) {
                        float f24 = (f11 * f8) + centerCircleBox.x;
                        float f25 = (f11 * sin) + centerCircleBox.y;
                        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
                        if (z3 && z4) {
                            f9 = sin;
                            drawValue(canvas, valueFormatter, y, entryForIndex, 0, f24, f25, iPieDataSet2.getValueTextColor(i2));
                            if (i2 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                drawEntryLabel(canvas, entryForIndex.getLabel(), f24, f25 + calcTextHeight);
                            }
                        } else {
                            f9 = sin;
                            if (z3) {
                                if (i2 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                    drawEntryLabel(canvas, entryForIndex.getLabel(), f24, f25 + (calcTextHeight / 2.0f));
                                }
                            } else if (z4) {
                                drawValue(canvas, valueFormatter, y, entryForIndex, 0, f24, f25 + (calcTextHeight / 2.0f), iPieDataSet2.getValueTextColor(i2));
                            }
                        }
                    } else {
                        f9 = sin;
                    }
                    if (entryForIndex.getIcon() == null || !iPieDataSet2.isDrawIconsEnabled()) {
                        mPPointF = mPPointF3;
                    } else {
                        Drawable icon = entryForIndex.getIcon();
                        mPPointF = mPPointF3;
                        float f26 = mPPointF.y;
                        Utils.drawImage(canvas, icon, (int) (((f11 + f26) * f8) + centerCircleBox.x), (int) (((f11 + f26) * f9) + centerCircleBox.y + mPPointF.x), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    i5++;
                    i6 = i2 + 1;
                    mPPointF2 = mPPointF;
                    iPieDataSet3 = iPieDataSet2;
                    entryCount = i7;
                    i4 = i8;
                    rotationAngle = f13;
                    drawAngles = fArr3;
                    absoluteAngles = fArr4;
                    xValuePosition = valuePosition2;
                    dataSets = list2;
                    yValuePosition = valuePosition;
                    radius = f7;
                }
                i = i4;
                list = dataSets;
                f = radius;
                f2 = rotationAngle;
                fArr = drawAngles;
                fArr2 = absoluteAngles;
                MPPointF.recycleInstance(mPPointF2);
                i3 = i5;
            } else {
                i = i4;
                list = dataSets;
                f = radius;
                f2 = rotationAngle;
                fArr = drawAngles;
                fArr2 = absoluteAngles;
            }
            i4 = i + 1;
            rotationAngle = f2;
            drawAngles = fArr;
            absoluteAngles = fArr2;
            dataSets = list;
            radius = f;
        }
        MPPointF.recycleInstance(centerCircleBox);
        canvas.restore();
    }

    public TextPaint getPaintCenterText() {
        return this.f7960a;
    }

    public Paint getPaintEntryLabels() {
        return this.b;
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public float getSliceSpace(IPieDataSet iPieDataSet) {
        if (!iPieDataSet.isAutomaticallyDisableSliceSpacingEnabled()) {
            return iPieDataSet.getSliceSpace();
        }
        if (iPieDataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension() > (iPieDataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum()) * 2.0f) {
            return 0.0f;
        }
        return iPieDataSet.getSliceSpace();
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    public void releaseBitmap() {
        Canvas canvas = this.mBitmapCanvas;
        if (canvas != null) {
            canvas.setBitmap(null);
            this.mBitmapCanvas = null;
        }
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (weakReference != null) {
            Bitmap bitmap = weakReference.get();
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
