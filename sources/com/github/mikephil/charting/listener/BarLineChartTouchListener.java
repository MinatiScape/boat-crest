package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes9.dex */
public class BarLineChartTouchListener extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {
    public Matrix h;
    public Matrix i;
    public MPPointF j;
    public MPPointF k;
    public float l;
    public float m;
    public float n;
    public IDataSet o;
    public VelocityTracker p;
    public long q;
    public MPPointF r;
    public MPPointF s;
    public float t;
    public float u;

    public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> barLineChartBase, Matrix matrix, float f) {
        super(barLineChartBase);
        this.h = new Matrix();
        this.i = new Matrix();
        this.j = MPPointF.getInstance(0.0f, 0.0f);
        this.k = MPPointF.getInstance(0.0f, 0.0f);
        this.l = 1.0f;
        this.m = 1.0f;
        this.n = 1.0f;
        this.q = 0L;
        this.r = MPPointF.getInstance(0.0f, 0.0f);
        this.s = MPPointF.getInstance(0.0f, 0.0f);
        this.h = matrix;
        this.t = Utils.convertDpToPixel(f);
        this.u = Utils.convertDpToPixel(3.5f);
    }

    public static float a(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    public static float b(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    public static void d(MPPointF mPPointF, MotionEvent motionEvent) {
        mPPointF.x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
        mPPointF.y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
    }

    public static float i(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public final boolean c() {
        IDataSet iDataSet;
        return (this.o == null && ((BarLineChartBase) this.mChart).isAnyAxisInverted()) || ((iDataSet = this.o) != null && ((BarLineChartBase) this.mChart).isInverted(iDataSet.getAxisDependency()));
    }

    public void computeScroll() {
        MPPointF mPPointF = this.s;
        if (mPPointF.x == 0.0f && mPPointF.y == 0.0f) {
            return;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.s.x *= ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef();
        this.s.y *= ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef();
        float f = ((float) (currentAnimationTimeMillis - this.q)) / 1000.0f;
        MPPointF mPPointF2 = this.s;
        float f2 = mPPointF2.x * f;
        float f3 = mPPointF2.y * f;
        MPPointF mPPointF3 = this.r;
        float f4 = mPPointF3.x + f2;
        mPPointF3.x = f4;
        float f5 = mPPointF3.y + f3;
        mPPointF3.y = f5;
        MotionEvent obtain = MotionEvent.obtain(currentAnimationTimeMillis, currentAnimationTimeMillis, 2, f4, f5, 0);
        e(obtain, ((BarLineChartBase) this.mChart).isDragXEnabled() ? this.r.x - this.j.x : 0.0f, ((BarLineChartBase) this.mChart).isDragYEnabled() ? this.r.y - this.j.y : 0.0f);
        obtain.recycle();
        this.h = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.h, this.mChart, false);
        this.q = currentAnimationTimeMillis;
        if (Math.abs(this.s.x) < 0.01d && Math.abs(this.s.y) < 0.01d) {
            ((BarLineChartBase) this.mChart).calculateOffsets();
            ((BarLineChartBase) this.mChart).postInvalidate();
            stopDeceleration();
            return;
        }
        Utils.postInvalidateOnAnimation(this.mChart);
    }

    public final void e(MotionEvent motionEvent, float f, float f2) {
        this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
        this.h.set(this.i);
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (c()) {
            if (this.mChart instanceof HorizontalBarChart) {
                f = -f;
            } else {
                f2 = -f2;
            }
        }
        this.h.postTranslate(f, f2);
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartTranslate(motionEvent, f, f2);
        }
    }

    public final void f(MotionEvent motionEvent) {
        Highlight highlightByTouchPoint = ((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY());
        if (highlightByTouchPoint == null || highlightByTouchPoint.equalTo(this.mLastHighlighted)) {
            return;
        }
        this.mLastHighlighted = highlightByTouchPoint;
        ((BarLineChartBase) this.mChart).highlightValue(highlightByTouchPoint, true);
    }

    public final void g(MotionEvent motionEvent) {
        boolean canZoomInMoreY;
        boolean canZoomInMoreX;
        boolean canZoomInMoreX2;
        boolean canZoomInMoreY2;
        if (motionEvent.getPointerCount() >= 2) {
            OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
            float i = i(motionEvent);
            if (i > this.u) {
                MPPointF mPPointF = this.k;
                MPPointF trans = getTrans(mPPointF.x, mPPointF.y);
                ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
                int i2 = this.mTouchMode;
                if (i2 == 4) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.PINCH_ZOOM;
                    float f = i / this.n;
                    boolean z = f < 1.0f;
                    if (z) {
                        canZoomInMoreX2 = viewPortHandler.canZoomOutMoreX();
                    } else {
                        canZoomInMoreX2 = viewPortHandler.canZoomInMoreX();
                    }
                    if (z) {
                        canZoomInMoreY2 = viewPortHandler.canZoomOutMoreY();
                    } else {
                        canZoomInMoreY2 = viewPortHandler.canZoomInMoreY();
                    }
                    float f2 = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? f : 1.0f;
                    float f3 = ((BarLineChartBase) this.mChart).isScaleYEnabled() ? f : 1.0f;
                    if (canZoomInMoreY2 || canZoomInMoreX2) {
                        this.h.set(this.i);
                        this.h.postScale(f2, f3, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, f2, f3);
                        }
                    }
                } else if (i2 == 2 && ((BarLineChartBase) this.mChart).isScaleXEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.X_ZOOM;
                    float a2 = a(motionEvent) / this.l;
                    if (a2 < 1.0f) {
                        canZoomInMoreX = viewPortHandler.canZoomOutMoreX();
                    } else {
                        canZoomInMoreX = viewPortHandler.canZoomInMoreX();
                    }
                    if (canZoomInMoreX) {
                        this.h.set(this.i);
                        this.h.postScale(a2, 1.0f, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, a2, 1.0f);
                        }
                    }
                } else if (this.mTouchMode == 3 && ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.Y_ZOOM;
                    float b = b(motionEvent) / this.m;
                    if (b < 1.0f) {
                        canZoomInMoreY = viewPortHandler.canZoomOutMoreY();
                    } else {
                        canZoomInMoreY = viewPortHandler.canZoomInMoreY();
                    }
                    if (canZoomInMoreY) {
                        this.h.set(this.i);
                        this.h.postScale(1.0f, b, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, 1.0f, b);
                        }
                    }
                }
                MPPointF.recycleInstance(trans);
            }
        }
    }

    public Matrix getMatrix() {
        return this.h;
    }

    public MPPointF getTrans(float f, float f2) {
        float f3;
        ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
        float offsetLeft = f - viewPortHandler.offsetLeft();
        if (c()) {
            f3 = -(f2 - viewPortHandler.offsetTop());
        } else {
            f3 = -((((BarLineChartBase) this.mChart).getMeasuredHeight() - f2) - viewPortHandler.offsetBottom());
        }
        return MPPointF.getInstance(offsetLeft, f3);
    }

    public final void h(MotionEvent motionEvent) {
        this.i.set(this.h);
        this.j.x = motionEvent.getX();
        this.j.y = motionEvent.getY();
        this.o = ((BarLineChartBase) this.mChart).getDataSetByTouchPoint(motionEvent.getX(), motionEvent.getY());
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.DOUBLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartDoubleTapped(motionEvent);
        }
        if (((BarLineChartBase) this.mChart).isDoubleTapToZoomEnabled() && ((BarLineScatterCandleBubbleData) ((BarLineChartBase) this.mChart).getData()).getEntryCount() > 0) {
            MPPointF trans = getTrans(motionEvent.getX(), motionEvent.getY());
            T t = this.mChart;
            ((BarLineChartBase) t).zoom(((BarLineChartBase) t).isScaleXEnabled() ? 1.4f : 1.0f, ((BarLineChartBase) this.mChart).isScaleYEnabled() ? 1.4f : 1.0f, trans.x, trans.y);
            if (((BarLineChartBase) this.mChart).isLogEnabled()) {
                Log.i("BarlineChartTouch", "Double-Tap, Zooming In, x: " + trans.x + ", y: " + trans.y);
            }
            MPPointF.recycleInstance(trans);
        }
        return super.onDoubleTap(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.mLastGesture = ChartTouchListener.ChartGesture.FLING;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartFling(motionEvent, motionEvent2, f, f2);
        }
        return super.onFling(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (((BarLineChartBase) this.mChart).isHighlightPerTapEnabled()) {
            performHighlight(((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
            return super.onSingleTapUp(motionEvent);
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (this.p == null) {
            this.p = VelocityTracker.obtain();
        }
        this.p.addMovement(motionEvent);
        if (motionEvent.getActionMasked() == 3 && (velocityTracker = this.p) != null) {
            velocityTracker.recycle();
            this.p = null;
        }
        if (this.mTouchMode == 0) {
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        if (((BarLineChartBase) this.mChart).isDragEnabled() || ((BarLineChartBase) this.mChart).isScaleXEnabled() || ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
            int action = motionEvent.getAction() & 255;
            if (action != 0) {
                boolean z = false;
                if (action == 1) {
                    VelocityTracker velocityTracker2 = this.p;
                    int pointerId = motionEvent.getPointerId(0);
                    velocityTracker2.computeCurrentVelocity(1000, Utils.getMaximumFlingVelocity());
                    float yVelocity = velocityTracker2.getYVelocity(pointerId);
                    float xVelocity = velocityTracker2.getXVelocity(pointerId);
                    if ((Math.abs(xVelocity) > Utils.getMinimumFlingVelocity() || Math.abs(yVelocity) > Utils.getMinimumFlingVelocity()) && this.mTouchMode == 1 && ((BarLineChartBase) this.mChart).isDragDecelerationEnabled()) {
                        stopDeceleration();
                        this.q = AnimationUtils.currentAnimationTimeMillis();
                        this.r.x = motionEvent.getX();
                        this.r.y = motionEvent.getY();
                        MPPointF mPPointF = this.s;
                        mPPointF.x = xVelocity;
                        mPPointF.y = yVelocity;
                        Utils.postInvalidateOnAnimation(this.mChart);
                    }
                    int i = this.mTouchMode;
                    if (i == 2 || i == 3 || i == 4 || i == 5) {
                        ((BarLineChartBase) this.mChart).calculateOffsets();
                        ((BarLineChartBase) this.mChart).postInvalidate();
                    }
                    this.mTouchMode = 0;
                    ((BarLineChartBase) this.mChart).enableScroll();
                    VelocityTracker velocityTracker3 = this.p;
                    if (velocityTracker3 != null) {
                        velocityTracker3.recycle();
                        this.p = null;
                    }
                    endAction(motionEvent);
                } else if (action == 2) {
                    int i2 = this.mTouchMode;
                    if (i2 == 1) {
                        ((BarLineChartBase) this.mChart).disableScroll();
                        e(motionEvent, ((BarLineChartBase) this.mChart).isDragXEnabled() ? motionEvent.getX() - this.j.x : 0.0f, ((BarLineChartBase) this.mChart).isDragYEnabled() ? motionEvent.getY() - this.j.y : 0.0f);
                    } else if (i2 != 2 && i2 != 3 && i2 != 4) {
                        if (i2 == 0 && Math.abs(ChartTouchListener.distance(motionEvent.getX(), this.j.x, motionEvent.getY(), this.j.y)) > this.t && ((BarLineChartBase) this.mChart).isDragEnabled()) {
                            if (!((BarLineChartBase) this.mChart).isFullyZoomedOut() || !((BarLineChartBase) this.mChart).hasNoDragOffset()) {
                                z = true;
                            }
                            if (z) {
                                float abs = Math.abs(motionEvent.getX() - this.j.x);
                                float abs2 = Math.abs(motionEvent.getY() - this.j.y);
                                if ((((BarLineChartBase) this.mChart).isDragXEnabled() || abs2 >= abs) && (((BarLineChartBase) this.mChart).isDragYEnabled() || abs2 <= abs)) {
                                    this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                                    this.mTouchMode = 1;
                                }
                            } else if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                                this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                                if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                                    f(motionEvent);
                                }
                            }
                        }
                    } else {
                        ((BarLineChartBase) this.mChart).disableScroll();
                        if (((BarLineChartBase) this.mChart).isScaleXEnabled() || ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                            g(motionEvent);
                        }
                    }
                } else if (action == 3) {
                    this.mTouchMode = 0;
                    endAction(motionEvent);
                } else if (action != 5) {
                    if (action == 6) {
                        Utils.velocityTrackerPointerUpCleanUpIfNecessary(motionEvent, this.p);
                        this.mTouchMode = 5;
                    }
                } else if (motionEvent.getPointerCount() >= 2) {
                    ((BarLineChartBase) this.mChart).disableScroll();
                    h(motionEvent);
                    this.l = a(motionEvent);
                    this.m = b(motionEvent);
                    float i3 = i(motionEvent);
                    this.n = i3;
                    if (i3 > 10.0f) {
                        if (((BarLineChartBase) this.mChart).isPinchZoomEnabled()) {
                            this.mTouchMode = 4;
                        } else if (((BarLineChartBase) this.mChart).isScaleXEnabled() != ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                            this.mTouchMode = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? 2 : 3;
                        } else {
                            this.mTouchMode = this.l > this.m ? 2 : 3;
                        }
                    }
                    d(this.k, motionEvent);
                }
            } else {
                startAction(motionEvent);
                stopDeceleration();
                h(motionEvent);
            }
            this.h = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.h, this.mChart, true);
            return true;
        }
        return true;
    }

    public void setDragTriggerDist(float f) {
        this.t = Utils.convertDpToPixel(f);
    }

    public void stopDeceleration() {
        MPPointF mPPointF = this.s;
        mPPointF.x = 0.0f;
        mPPointF.y = 0.0f;
    }
}
