package com.mappls.sdk.gestures;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import java.util.HashSet;
import java.util.Set;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class RotateGestureDetector extends ProgressiveGesture<OnRotateGestureListener> {
    private static final Set<Integer> handledTypes;
    private float angleThreshold;
    public float deltaSinceLast;
    public float deltaSinceStart;

    /* loaded from: classes11.dex */
    public interface OnRotateGestureListener {
        boolean onRotate(@NonNull RotateGestureDetector rotateGestureDetector, float f, float f2);

        boolean onRotateBegin(@NonNull RotateGestureDetector rotateGestureDetector);

        void onRotateEnd(@NonNull RotateGestureDetector rotateGestureDetector, float f, float f2, float f3);
    }

    /* loaded from: classes11.dex */
    public static class SimpleOnRotateGestureListener implements OnRotateGestureListener {
        @Override // com.mappls.sdk.gestures.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotate(@NonNull RotateGestureDetector rotateGestureDetector, float f, float f2) {
            return true;
        }

        @Override // com.mappls.sdk.gestures.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotateBegin(@NonNull RotateGestureDetector rotateGestureDetector) {
            return true;
        }

        @Override // com.mappls.sdk.gestures.RotateGestureDetector.OnRotateGestureListener
        public void onRotateEnd(@NonNull RotateGestureDetector rotateGestureDetector, float f, float f2, float f3) {
        }
    }

    static {
        HashSet hashSet = new HashSet();
        handledTypes = hashSet;
        hashSet.add(2);
    }

    public RotateGestureDetector(Context context, AndroidGesturesManager androidGesturesManager) {
        super(context, androidGesturesManager);
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public boolean analyzeMovement() {
        super.analyzeMovement();
        float rotationDegreesSinceLast = getRotationDegreesSinceLast();
        this.deltaSinceLast = rotationDegreesSinceLast;
        this.deltaSinceStart += rotationDegreesSinceLast;
        if (isInProgress()) {
            float f = this.deltaSinceLast;
            if (f != 0.0f) {
                return ((OnRotateGestureListener) this.listener).onRotate(this, f, this.deltaSinceStart);
            }
        }
        if (canExecute(2) && ((OnRotateGestureListener) this.listener).onRotateBegin(this)) {
            gestureStarted();
            return true;
        }
        return false;
    }

    public float calculateAngularVelocityVector(float f, float f2) {
        float abs = Math.abs((float) (((getFocalPoint().x * f2) + (getFocalPoint().y * f)) / (Math.pow(getFocalPoint().x, 2.0d) + Math.pow(getFocalPoint().y, 2.0d))));
        return this.deltaSinceLast < 0.0f ? -abs : abs;
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture, com.mappls.sdk.gestures.BaseGesture
    public boolean canExecute(int i) {
        return Math.abs(this.deltaSinceStart) >= this.angleThreshold && super.canExecute(i);
    }

    @Override // com.mappls.sdk.gestures.ProgressiveGesture
    public void gestureStopped() {
        super.gestureStopped();
        if (this.deltaSinceLast == 0.0f) {
            this.velocityX = 0.0f;
            this.velocityY = 0.0f;
        }
        ((OnRotateGestureListener) this.listener).onRotateEnd(this, this.velocityX, this.velocityY, calculateAngularVelocityVector(this.velocityX, this.velocityY));
    }

    public float getAngleThreshold() {
        return this.angleThreshold;
    }

    public float getDeltaSinceLast() {
        return this.deltaSinceLast;
    }

    public float getDeltaSinceStart() {
        return this.deltaSinceStart;
    }

    public float getRotationDegreesSinceLast() {
        MultiFingerDistancesObject multiFingerDistancesObject = this.pointersDistanceMap.get(new PointerDistancePair(this.pointerIdList.get(0), this.pointerIdList.get(1)));
        return (float) Math.toDegrees(Math.atan2(multiFingerDistancesObject.getPrevFingersDiffY(), multiFingerDistancesObject.getPrevFingersDiffX()) - Math.atan2(multiFingerDistancesObject.getCurrFingersDiffY(), multiFingerDistancesObject.getCurrFingersDiffX()));
    }

    @Override // com.mappls.sdk.gestures.ProgressiveGesture
    @NonNull
    public Set<Integer> provideHandledTypes() {
        return handledTypes;
    }

    @Override // com.mappls.sdk.gestures.MultiFingerGesture
    public void reset() {
        super.reset();
        this.deltaSinceStart = 0.0f;
    }

    public void setAngleThreshold(float f) {
        this.angleThreshold = f;
    }
}
