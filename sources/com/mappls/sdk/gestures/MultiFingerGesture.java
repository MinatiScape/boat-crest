package com.mappls.sdk.gestures;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.annotation.DimenRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
@Keep
@UiThread
/* loaded from: classes11.dex */
public abstract class MultiFingerGesture<L> extends BaseGesture<L> {
    private static final int DEFAULT_REQUIRED_FINGERS_COUNT = 2;
    private static final float PRESSURE_THRESHOLD = 0.67f;
    private DisplayMetrics displayMetrics;
    private final float edgeSlop;
    private PointF focalPoint;
    private final a permittedActionsGuard;
    public final List<Integer> pointerIdList;
    public final HashMap<PointerDistancePair, MultiFingerDistancesObject> pointersDistanceMap;
    private float spanThreshold;

    public MultiFingerGesture(Context context, AndroidGesturesManager androidGesturesManager) {
        super(context, androidGesturesManager);
        this.permittedActionsGuard = new a();
        this.pointerIdList = new ArrayList();
        this.pointersDistanceMap = new HashMap<>();
        this.focalPoint = new PointF();
        this.edgeSlop = ViewConfiguration.get(context).getScaledEdgeSlop();
        queryDisplayMetrics();
    }

    private void calculateDistances() {
        this.pointersDistanceMap.clear();
        int i = 0;
        while (i < this.pointerIdList.size() - 1) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.pointerIdList.size(); i3++) {
                int intValue = this.pointerIdList.get(i).intValue();
                int intValue2 = this.pointerIdList.get(i3).intValue();
                float x = getPreviousEvent().getX(getPreviousEvent().findPointerIndex(intValue));
                float y = getPreviousEvent().getY(getPreviousEvent().findPointerIndex(intValue));
                float x2 = getPreviousEvent().getX(getPreviousEvent().findPointerIndex(intValue2)) - x;
                float y2 = getPreviousEvent().getY(getPreviousEvent().findPointerIndex(intValue2)) - y;
                float x3 = getCurrentEvent().getX(getCurrentEvent().findPointerIndex(intValue));
                float y3 = getCurrentEvent().getY(getCurrentEvent().findPointerIndex(intValue));
                this.pointersDistanceMap.put(new PointerDistancePair(Integer.valueOf(intValue), Integer.valueOf(intValue2)), new MultiFingerDistancesObject(x2, y2, getCurrentEvent().getX(getCurrentEvent().findPointerIndex(intValue2)) - x3, getCurrentEvent().getY(getCurrentEvent().findPointerIndex(intValue2)) - y3));
            }
            i = i2;
        }
    }

    private boolean checkSpanBelowThreshold() {
        Iterator<MultiFingerDistancesObject> it = this.pointersDistanceMap.values().iterator();
        while (it.hasNext()) {
            if (it.next().getCurrFingersDiffXY() < this.spanThreshold) {
                return true;
            }
        }
        return false;
    }

    private boolean isMissingPointers(MotionEvent motionEvent) {
        boolean z;
        Iterator<Integer> it = this.pointerIdList.iterator();
        do {
            z = false;
            if (!it.hasNext()) {
                return false;
            }
            if (motionEvent.findPointerIndex(it.next().intValue()) != -1) {
                z = true;
                continue;
            }
        } while (z);
        return true;
    }

    private void queryDisplayMetrics() {
        if (this.windowManager != null) {
            this.displayMetrics = new DisplayMetrics();
            Display defaultDisplay = this.windowManager.getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealMetrics(this.displayMetrics);
                return;
            } else {
                defaultDisplay.getMetrics(this.displayMetrics);
                return;
            }
        }
        this.displayMetrics = this.context.getResources().getDisplayMetrics();
    }

    private void updatePointerList(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            this.pointerIdList.add(Integer.valueOf(motionEvent.getPointerId(motionEvent.getActionIndex())));
        } else if (actionMasked == 1 || actionMasked == 6) {
            this.pointerIdList.remove(Integer.valueOf(motionEvent.getPointerId(motionEvent.getActionIndex())));
        }
    }

    private boolean verifyPointers(int i, int i2) {
        return i != i2 && i >= 0 && i2 >= 0 && i < getPointersCount() && i2 < getPointersCount();
    }

    @Override // com.mappls.sdk.gestures.BaseGesture
    public boolean analyzeEvent(@NonNull MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            queryDisplayMetrics();
        }
        boolean z = this.permittedActionsGuard.a(actionMasked, motionEvent.getPointerCount(), this.pointerIdList.size()) || (actionMasked == 2 && isMissingPointers(motionEvent));
        if (z) {
            if (this instanceof ProgressiveGesture) {
                ProgressiveGesture progressiveGesture = (ProgressiveGesture) this;
                if (progressiveGesture.isInProgress()) {
                    progressiveGesture.gestureStopped();
                }
            }
            this.pointerIdList.clear();
            this.pointersDistanceMap.clear();
        }
        if (!z || actionMasked == 0) {
            updatePointerList(motionEvent);
        }
        this.focalPoint = Utils.determineFocalPoint(motionEvent);
        if (z) {
            Log.w("MultiFingerGesture", "Some MotionEvents were not passed to the library or events from different view trees are merged.");
            return false;
        }
        if (actionMasked == 2 && this.pointerIdList.size() >= getRequiredPointersCount() && checkPressure()) {
            calculateDistances();
            if (!isSloppyGesture()) {
                return analyzeMovement();
            }
        }
        return false;
    }

    public boolean analyzeMovement() {
        return false;
    }

    @Override // com.mappls.sdk.gestures.BaseGesture
    public boolean canExecute(int i) {
        return super.canExecute(i) && !isSloppyGesture();
    }

    public boolean checkPressure() {
        return getCurrentEvent().getPressure() / getPreviousEvent().getPressure() > PRESSURE_THRESHOLD;
    }

    public float getCurrentSpan(int i, int i2) {
        if (verifyPointers(i, i2)) {
            return this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(i), this.pointerIdList.get(i2))).getCurrFingersDiffXY();
        }
        throw new NoSuchElementException("There is no such pair of pointers!");
    }

    public float getCurrentSpanX(int i, int i2) {
        if (verifyPointers(i, i2)) {
            return Math.abs(this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(i), this.pointerIdList.get(i2))).getCurrFingersDiffX());
        }
        throw new NoSuchElementException("There is no such pair of pointers!");
    }

    public float getCurrentSpanY(int i, int i2) {
        if (verifyPointers(i, i2)) {
            return Math.abs(this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(i), this.pointerIdList.get(i2))).getCurrFingersDiffY());
        }
        throw new NoSuchElementException("There is no such pair of pointers!");
    }

    public PointF getFocalPoint() {
        return this.focalPoint;
    }

    public int getPointersCount() {
        return this.pointerIdList.size();
    }

    public float getPreviousSpan(int i, int i2) {
        if (verifyPointers(i, i2)) {
            return this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(i), this.pointerIdList.get(i2))).getPrevFingersDiffXY();
        }
        throw new NoSuchElementException("There is no such pair of pointers!");
    }

    public float getPreviousSpanX(int i, int i2) {
        if (verifyPointers(i, i2)) {
            return Math.abs(this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(i), this.pointerIdList.get(i2))).getPrevFingersDiffX());
        }
        throw new NoSuchElementException("There is no such pair of pointers!");
    }

    public float getPreviousSpanY(int i, int i2) {
        if (verifyPointers(i, i2)) {
            return Math.abs(this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(i), this.pointerIdList.get(i2))).getPrevFingersDiffY());
        }
        throw new NoSuchElementException("There is no such pair of pointers!");
    }

    public int getRequiredPointersCount() {
        return 2;
    }

    public float getSpanThreshold() {
        return this.spanThreshold;
    }

    public boolean isSloppyGesture() {
        boolean z;
        DisplayMetrics displayMetrics = this.displayMetrics;
        float f = this.edgeSlop;
        float f2 = displayMetrics.widthPixels - f;
        float f3 = displayMetrics.heightPixels - f;
        for (Integer num : this.pointerIdList) {
            int findPointerIndex = getCurrentEvent().findPointerIndex(num.intValue());
            float rawX = Utils.getRawX(getCurrentEvent(), findPointerIndex);
            float rawY = Utils.getRawY(getCurrentEvent(), findPointerIndex);
            if (rawX < f || rawY < f || rawX > f2 || rawY > f3) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                return true;
            }
        }
        return checkSpanBelowThreshold();
    }

    public void reset() {
    }

    public void setSpanThreshold(float f) {
        this.spanThreshold = f;
    }

    public void setSpanThresholdResource(@DimenRes int i) {
        setSpanThreshold(this.context.getResources().getDimension(i));
    }
}
