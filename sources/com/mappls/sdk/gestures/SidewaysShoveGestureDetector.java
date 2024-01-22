package com.mappls.sdk.gestures;

import android.content.Context;
import androidx.annotation.DimenRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import java.util.HashSet;
import java.util.Set;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class SidewaysShoveGestureDetector extends ProgressiveGesture<OnSidewaysShoveGestureListener> {
    private static final Set<Integer> handledTypes;
    public float deltaPixelSinceLast;
    public float deltaPixelsSinceStart;
    private float maxShoveAngle;
    private float pixelDeltaThreshold;

    /* loaded from: classes11.dex */
    public interface OnSidewaysShoveGestureListener {
        boolean onSidewaysShove(@NonNull SidewaysShoveGestureDetector sidewaysShoveGestureDetector, float f, float f2);

        boolean onSidewaysShoveBegin(@NonNull SidewaysShoveGestureDetector sidewaysShoveGestureDetector);

        void onSidewaysShoveEnd(@NonNull SidewaysShoveGestureDetector sidewaysShoveGestureDetector, float f, float f2);
    }

    /* loaded from: classes11.dex */
    public static class SimpleOnSidewaysShoveGestureListener implements OnSidewaysShoveGestureListener {
        @Override // com.mappls.sdk.gestures.SidewaysShoveGestureDetector.OnSidewaysShoveGestureListener
        public boolean onSidewaysShove(@NonNull SidewaysShoveGestureDetector sidewaysShoveGestureDetector, float f, float f2) {
            return false;
        }

        @Override // com.mappls.sdk.gestures.SidewaysShoveGestureDetector.OnSidewaysShoveGestureListener
        public boolean onSidewaysShoveBegin(@NonNull SidewaysShoveGestureDetector sidewaysShoveGestureDetector) {
            return true;
        }

        @Override // com.mappls.sdk.gestures.SidewaysShoveGestureDetector.OnSidewaysShoveGestureListener
        public void onSidewaysShoveEnd(@NonNull SidewaysShoveGestureDetector sidewaysShoveGestureDetector, float f, float f2) {
        }
    }

    static {
        HashSet hashSet = new HashSet();
        handledTypes = hashSet;
        hashSet.add(14);
    }

    public SidewaysShoveGestureDetector(Context context, AndroidGesturesManager androidGesturesManager) {
        super(context, androidGesturesManager);
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public boolean analyzeMovement() {
        super.analyzeMovement();
        float calculateDeltaPixelsSinceLast = calculateDeltaPixelsSinceLast();
        this.deltaPixelSinceLast = calculateDeltaPixelsSinceLast;
        this.deltaPixelsSinceStart += calculateDeltaPixelsSinceLast;
        if (isInProgress()) {
            float f = this.deltaPixelSinceLast;
            if (f != 0.0f) {
                return ((OnSidewaysShoveGestureListener) this.listener).onSidewaysShove(this, f, this.deltaPixelsSinceStart);
            }
        }
        if (canExecute(14) && ((OnSidewaysShoveGestureListener) this.listener).onSidewaysShoveBegin(this)) {
            gestureStarted();
            return true;
        }
        return false;
    }

    public float calculateDeltaPixelsSinceLast() {
        return ((getCurrentEvent().getX(getCurrentEvent().findPointerIndex(this.pointerIdList.get(0).intValue())) + getCurrentEvent().getX(getCurrentEvent().findPointerIndex(this.pointerIdList.get(1).intValue()))) / 2.0f) - ((getPreviousEvent().getX(getPreviousEvent().findPointerIndex(this.pointerIdList.get(0).intValue())) + getPreviousEvent().getX(getPreviousEvent().findPointerIndex(this.pointerIdList.get(1).intValue()))) / 2.0f);
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture, com.mappls.sdk.gestures.BaseGesture
    public boolean canExecute(int i) {
        return Math.abs(this.deltaPixelsSinceStart) >= this.pixelDeltaThreshold && super.canExecute(i);
    }

    @Override // com.mappls.sdk.gestures.ProgressiveGesture
    public void gestureStopped() {
        super.gestureStopped();
        ((OnSidewaysShoveGestureListener) this.listener).onSidewaysShoveEnd(this, this.velocityX, this.velocityY);
    }

    public float getDeltaPixelSinceLast() {
        return this.deltaPixelSinceLast;
    }

    public float getDeltaPixelsSinceStart() {
        return this.deltaPixelsSinceStart;
    }

    public float getMaxShoveAngle() {
        return this.maxShoveAngle;
    }

    public float getPixelDeltaThreshold() {
        return this.pixelDeltaThreshold;
    }

    public boolean isAngleAcceptable() {
        MultiFingerDistancesObject multiFingerDistancesObject = this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(0), this.pointerIdList.get(1)));
        return Math.abs(Math.toDegrees(Math.abs(Math.atan2((double) multiFingerDistancesObject.getCurrFingersDiffY(), (double) multiFingerDistancesObject.getCurrFingersDiffX()))) - 90.0d) <= ((double) this.maxShoveAngle);
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public boolean isSloppyGesture() {
        return super.isSloppyGesture() || !isAngleAcceptable();
    }

    @Override // com.mappls.sdk.gestures.ProgressiveGesture
    @NonNull
    public Set<Integer> provideHandledTypes() {
        return handledTypes;
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public void reset() {
        super.reset();
        this.deltaPixelsSinceStart = 0.0f;
    }

    public void setMaxShoveAngle(float f) {
        this.maxShoveAngle = f;
    }

    public void setPixelDeltaThreshold(float f) {
        this.pixelDeltaThreshold = f;
    }

    public void setPixelDeltaThresholdResource(@DimenRes int i) {
        setPixelDeltaThreshold(this.context.getResources().getDimension(i));
    }
}
