package com.mappls.sdk.gestures;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import androidx.annotation.DimenRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class MoveGestureDetector extends ProgressiveGesture<OnMoveGestureListener> {
    private static final int MOVE_REQUIRED_POINTERS_COUNT = 1;
    private static final Set<Integer> handledTypes;
    public float lastDistanceX;
    public float lastDistanceY;
    private final Map<Integer, MoveDistancesObject> moveDistancesObjectMap;
    private float moveThreshold;
    @Nullable
    private RectF moveThresholdRect;
    private PointF previousFocalPoint;
    private boolean resetFocal;

    /* loaded from: classes11.dex */
    public interface OnMoveGestureListener {
        boolean onMove(@NonNull MoveGestureDetector moveGestureDetector, float f, float f2);

        boolean onMoveBegin(@NonNull MoveGestureDetector moveGestureDetector);

        void onMoveEnd(@NonNull MoveGestureDetector moveGestureDetector, float f, float f2);
    }

    /* loaded from: classes11.dex */
    public static class SimpleOnMoveGestureListener implements OnMoveGestureListener {
        @Override // com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMove(@NonNull MoveGestureDetector moveGestureDetector, float f, float f2) {
            return false;
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMoveBegin(@NonNull MoveGestureDetector moveGestureDetector) {
            return true;
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public void onMoveEnd(@NonNull MoveGestureDetector moveGestureDetector, float f, float f2) {
        }
    }

    static {
        HashSet hashSet = new HashSet();
        handledTypes = hashSet;
        hashSet.add(13);
    }

    public MoveGestureDetector(Context context, AndroidGesturesManager androidGesturesManager) {
        super(context, androidGesturesManager);
        this.moveDistancesObjectMap = new HashMap();
    }

    private void updateMoveDistancesObjects() {
        for (Integer num : this.pointerIdList) {
            int intValue = num.intValue();
            this.moveDistancesObjectMap.get(Integer.valueOf(intValue)).addNewPosition(getCurrentEvent().getX(getCurrentEvent().findPointerIndex(intValue)), getCurrentEvent().getY(getCurrentEvent().findPointerIndex(intValue)));
        }
    }

    @Override // com.mappls.sdk.gestures.ProgressiveGesture, com.mappls.sdk.gestures.MultiFingerGesture, com.mappls.sdk.gestures.BaseGesture
    public boolean analyzeEvent(@NonNull MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                this.moveDistancesObjectMap.clear();
            } else if (actionMasked == 3) {
                this.moveDistancesObjectMap.clear();
            } else if (actionMasked != 5) {
                if (actionMasked == 6) {
                    this.resetFocal = true;
                    this.moveDistancesObjectMap.remove(Integer.valueOf(motionEvent.getPointerId(motionEvent.getActionIndex())));
                }
            }
            return super.analyzeEvent(motionEvent);
        }
        this.resetFocal = true;
        this.moveDistancesObjectMap.put(Integer.valueOf(motionEvent.getPointerId(motionEvent.getActionIndex())), new MoveDistancesObject(motionEvent.getX(motionEvent.getActionIndex()), motionEvent.getY(motionEvent.getActionIndex())));
        return super.analyzeEvent(motionEvent);
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public boolean analyzeMovement() {
        super.analyzeMovement();
        updateMoveDistancesObjects();
        if (isInProgress()) {
            PointF focalPoint = getFocalPoint();
            PointF pointF = this.previousFocalPoint;
            float f = pointF.x - focalPoint.x;
            this.lastDistanceX = f;
            float f2 = pointF.y - focalPoint.y;
            this.lastDistanceY = f2;
            this.previousFocalPoint = focalPoint;
            if (this.resetFocal) {
                this.resetFocal = false;
                return ((OnMoveGestureListener) this.listener).onMove(this, 0.0f, 0.0f);
            }
            return ((OnMoveGestureListener) this.listener).onMove(this, f, f2);
        } else if (canExecute(13) && ((OnMoveGestureListener) this.listener).onMoveBegin(this)) {
            gestureStarted();
            this.previousFocalPoint = getFocalPoint();
            this.resetFocal = false;
            return true;
        } else {
            return false;
        }
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture, com.mappls.sdk.gestures.BaseGesture
    public boolean canExecute(int i) {
        return super.canExecute(i) && checkAnyMoveAboveThreshold();
    }

    public boolean checkAnyMoveAboveThreshold() {
        Iterator<MoveDistancesObject> it = this.moveDistancesObjectMap.values().iterator();
        if (it.hasNext()) {
            MoveDistancesObject next = it.next();
            boolean z = Math.abs(next.getDistanceXSinceStart()) >= this.moveThreshold || Math.abs(next.getDistanceYSinceStart()) >= this.moveThreshold;
            RectF rectF = this.moveThresholdRect;
            return !(rectF != null && rectF.contains(getFocalPoint().x, getFocalPoint().y)) && z;
        }
        return false;
    }

    @Override // com.mappls.sdk.gestures.ProgressiveGesture
    public void gestureStopped() {
        super.gestureStopped();
        ((OnMoveGestureListener) this.listener).onMoveEnd(this, this.velocityX, this.velocityY);
    }

    public float getLastDistanceX() {
        return this.lastDistanceX;
    }

    public float getLastDistanceY() {
        return this.lastDistanceY;
    }

    public MoveDistancesObject getMoveObject(int i) {
        if (!isInProgress() || i < 0 || i >= getPointersCount()) {
            return null;
        }
        return this.moveDistancesObjectMap.get(this.pointerIdList.get(i));
    }

    public float getMoveThreshold() {
        return this.moveThreshold;
    }

    @Nullable
    public RectF getMoveThresholdRect() {
        return this.moveThresholdRect;
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public int getRequiredPointersCount() {
        return 1;
    }

    @Override // com.mappls.sdk.gestures.ProgressiveGesture
    @NonNull
    public Set<Integer> provideHandledTypes() {
        return handledTypes;
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public void reset() {
        super.reset();
    }

    public void setMoveThreshold(float f) {
        this.moveThreshold = f;
    }

    public void setMoveThresholdRect(@Nullable RectF rectF) {
        this.moveThresholdRect = rectF;
    }

    public void setMoveThresholdResource(@DimenRes int i) {
        setMoveThreshold(this.context.getResources().getDimension(i));
    }
}
