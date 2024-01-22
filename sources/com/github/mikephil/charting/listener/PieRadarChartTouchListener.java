package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class PieRadarChartTouchListener extends ChartTouchListener<PieRadarChartBase<?>> {
    public MPPointF h;
    public float i;
    public ArrayList<a> j;
    public long k;
    public float l;

    /* loaded from: classes9.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public long f7949a;
        public float b;

        public a(PieRadarChartTouchListener pieRadarChartTouchListener, long j, float f) {
            this.f7949a = j;
            this.b = f;
        }
    }

    public PieRadarChartTouchListener(PieRadarChartBase<?> pieRadarChartBase) {
        super(pieRadarChartBase);
        this.h = MPPointF.getInstance(0.0f, 0.0f);
        this.i = 0.0f;
        this.j = new ArrayList<>();
        this.k = 0L;
        this.l = 0.0f;
    }

    public final float a() {
        float f;
        float f2;
        if (this.j.isEmpty()) {
            return 0.0f;
        }
        a aVar = this.j.get(0);
        ArrayList<a> arrayList = this.j;
        a aVar2 = arrayList.get(arrayList.size() - 1);
        a aVar3 = aVar;
        for (int size = this.j.size() - 1; size >= 0; size--) {
            aVar3 = this.j.get(size);
            if (aVar3.b != aVar2.b) {
                break;
            }
        }
        float f3 = ((float) (aVar2.f7949a - aVar.f7949a)) / 1000.0f;
        if (f3 == 0.0f) {
            f3 = 0.1f;
        }
        boolean z = aVar2.b >= aVar3.b;
        if (Math.abs(f - f2) > 270.0d) {
            z = !z;
        }
        float f4 = aVar2.b;
        float f5 = aVar.b;
        if (f4 - f5 > 180.0d) {
            aVar.b = (float) (f5 + 360.0d);
        } else if (f5 - f4 > 180.0d) {
            aVar2.b = (float) (f4 + 360.0d);
        }
        float abs = Math.abs((aVar2.b - aVar.b) / f3);
        return !z ? -abs : abs;
    }

    public final void b() {
        this.j.clear();
    }

    public final void c(float f, float f2) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.j.add(new a(this, currentAnimationTimeMillis, ((PieRadarChartBase) this.mChart).getAngleForPoint(f, f2)));
        for (int size = this.j.size(); size - 2 > 0 && currentAnimationTimeMillis - this.j.get(0).f7949a > 1000; size--) {
            this.j.remove(0);
        }
    }

    public void computeScroll() {
        if (this.l == 0.0f) {
            return;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.l *= ((PieRadarChartBase) this.mChart).getDragDecelerationFrictionCoef();
        T t = this.mChart;
        ((PieRadarChartBase) t).setRotationAngle(((PieRadarChartBase) t).getRotationAngle() + (this.l * (((float) (currentAnimationTimeMillis - this.k)) / 1000.0f)));
        this.k = currentAnimationTimeMillis;
        if (Math.abs(this.l) >= 0.001d) {
            Utils.postInvalidateOnAnimation(this.mChart);
        } else {
            stopDeceleration();
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (((PieRadarChartBase) this.mChart).isHighlightPerTapEnabled()) {
            performHighlight(((PieRadarChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
            return true;
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && ((PieRadarChartBase) this.mChart).isRotationEnabled()) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                startAction(motionEvent);
                stopDeceleration();
                b();
                if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                    c(x, y);
                }
                setGestureStartAngle(x, y);
                MPPointF mPPointF = this.h;
                mPPointF.x = x;
                mPPointF.y = y;
            } else if (action == 1) {
                if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                    stopDeceleration();
                    c(x, y);
                    float a2 = a();
                    this.l = a2;
                    if (a2 != 0.0f) {
                        this.k = AnimationUtils.currentAnimationTimeMillis();
                        Utils.postInvalidateOnAnimation(this.mChart);
                    }
                }
                ((PieRadarChartBase) this.mChart).enableScroll();
                this.mTouchMode = 0;
                endAction(motionEvent);
            } else if (action == 2) {
                if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                    c(x, y);
                }
                if (this.mTouchMode == 0) {
                    MPPointF mPPointF2 = this.h;
                    if (ChartTouchListener.distance(x, mPPointF2.x, y, mPPointF2.y) > Utils.convertDpToPixel(8.0f)) {
                        this.mLastGesture = ChartTouchListener.ChartGesture.ROTATE;
                        this.mTouchMode = 6;
                        ((PieRadarChartBase) this.mChart).disableScroll();
                        endAction(motionEvent);
                    }
                }
                if (this.mTouchMode == 6) {
                    updateGestureRotation(x, y);
                    ((PieRadarChartBase) this.mChart).invalidate();
                }
                endAction(motionEvent);
            }
        }
        return true;
    }

    public void setGestureStartAngle(float f, float f2) {
        this.i = ((PieRadarChartBase) this.mChart).getAngleForPoint(f, f2) - ((PieRadarChartBase) this.mChart).getRawRotationAngle();
    }

    public void stopDeceleration() {
        this.l = 0.0f;
    }

    public void updateGestureRotation(float f, float f2) {
        T t = this.mChart;
        ((PieRadarChartBase) t).setRotationAngle(((PieRadarChartBase) t).getAngleForPoint(f, f2) - this.i);
    }
}
