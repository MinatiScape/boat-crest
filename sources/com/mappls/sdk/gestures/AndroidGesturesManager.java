package com.mappls.sdk.gestures;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import com.mappls.sdk.gestures.MoveGestureDetector;
import com.mappls.sdk.gestures.MultiFingerTapGestureDetector;
import com.mappls.sdk.gestures.RotateGestureDetector;
import com.mappls.sdk.gestures.ShoveGestureDetector;
import com.mappls.sdk.gestures.SidewaysShoveGestureDetector;
import com.mappls.sdk.gestures.StandardGestureDetector;
import com.mappls.sdk.gestures.StandardScaleGestureDetector;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class AndroidGesturesManager {
    public static final int GESTURE_TYPE_DOUBLE_TAP = 10;
    public static final int GESTURE_TYPE_DOUBLE_TAP_EVENT = 11;
    public static final int GESTURE_TYPE_DOWN = 9;
    public static final int GESTURE_TYPE_FLING = 7;
    public static final int GESTURE_TYPE_LONG_PRESS = 6;
    public static final int GESTURE_TYPE_MOVE = 13;
    public static final int GESTURE_TYPE_MULTI_FINGER_TAP = 4;
    public static final int GESTURE_TYPE_QUICK_SCALE = 15;
    public static final int GESTURE_TYPE_ROTATE = 2;
    public static final int GESTURE_TYPE_SCALE = 1;
    public static final int GESTURE_TYPE_SCROLL = 0;
    public static final int GESTURE_TYPE_SHOVE = 3;
    public static final int GESTURE_TYPE_SHOW_PRESS = 8;
    public static final int GESTURE_TYPE_SIDEWAYS_SHOVE = 14;
    public static final int GESTURE_TYPE_SINGLE_TAP_CONFIRMED = 12;
    public static final int GESTURE_TYPE_SINGLE_TAP_UP = 5;
    private final List<BaseGesture> detectors;
    private final MoveGestureDetector moveGestureDetector;
    private final MultiFingerTapGestureDetector multiFingerTapGestureDetector;
    private final List<Set<Integer>> mutuallyExclusiveGestures;
    private final RotateGestureDetector rotateGestureDetector;
    private final ShoveGestureDetector shoveGestureDetector;
    private final SidewaysShoveGestureDetector sidewaysShoveGestureDetector;
    private final StandardGestureDetector standardGestureDetector;
    private final StandardScaleGestureDetector standardScaleGestureDetector;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface GestureType {
    }

    public AndroidGesturesManager(Context context) {
        this(context, true);
    }

    private void initDefaultThresholds() {
        for (BaseGesture baseGesture : this.detectors) {
            if (baseGesture instanceof MultiFingerGesture) {
                if (Build.VERSION.SDK_INT < 24) {
                    ((MultiFingerGesture) baseGesture).setSpanThresholdResource(R.dimen.mappls_internalMinSpan23);
                } else {
                    ((MultiFingerGesture) baseGesture).setSpanThresholdResource(R.dimen.mappls_internalMinSpan24);
                }
            }
            if (baseGesture instanceof StandardScaleGestureDetector) {
                ((StandardScaleGestureDetector) baseGesture).setSpanSinceStartThresholdResource(R.dimen.mappls_defaultScaleSpanSinceStartThreshold);
            }
            if (baseGesture instanceof ShoveGestureDetector) {
                ShoveGestureDetector shoveGestureDetector = (ShoveGestureDetector) baseGesture;
                shoveGestureDetector.setPixelDeltaThresholdResource(R.dimen.mappls_defaultShovePixelThreshold);
                shoveGestureDetector.setMaxShoveAngle(20.0f);
            }
            if (baseGesture instanceof SidewaysShoveGestureDetector) {
                SidewaysShoveGestureDetector sidewaysShoveGestureDetector = (SidewaysShoveGestureDetector) baseGesture;
                sidewaysShoveGestureDetector.setPixelDeltaThresholdResource(R.dimen.mappls_defaultShovePixelThreshold);
                sidewaysShoveGestureDetector.setMaxShoveAngle(20.0f);
            }
            if (baseGesture instanceof MultiFingerTapGestureDetector) {
                MultiFingerTapGestureDetector multiFingerTapGestureDetector = (MultiFingerTapGestureDetector) baseGesture;
                multiFingerTapGestureDetector.setMultiFingerTapMovementThresholdResource(R.dimen.mappls_defaultMultiTapMovementThreshold);
                multiFingerTapGestureDetector.setMultiFingerTapTimeThreshold(150L);
            }
            if (baseGesture instanceof RotateGestureDetector) {
                ((RotateGestureDetector) baseGesture).setAngleThreshold(15.3f);
            }
        }
    }

    public List<BaseGesture> getDetectors() {
        return this.detectors;
    }

    public MoveGestureDetector getMoveGestureDetector() {
        return this.moveGestureDetector;
    }

    public MultiFingerTapGestureDetector getMultiFingerTapGestureDetector() {
        return this.multiFingerTapGestureDetector;
    }

    public List<Set<Integer>> getMutuallyExclusiveGestures() {
        return this.mutuallyExclusiveGestures;
    }

    public RotateGestureDetector getRotateGestureDetector() {
        return this.rotateGestureDetector;
    }

    public ShoveGestureDetector getShoveGestureDetector() {
        return this.shoveGestureDetector;
    }

    public SidewaysShoveGestureDetector getSidewaysShoveGestureDetector() {
        return this.sidewaysShoveGestureDetector;
    }

    public StandardGestureDetector getStandardGestureDetector() {
        return this.standardGestureDetector;
    }

    public StandardScaleGestureDetector getStandardScaleGestureDetector() {
        return this.standardScaleGestureDetector;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        for (BaseGesture baseGesture : this.detectors) {
            if (baseGesture.onTouchEvent(motionEvent)) {
                z = true;
            }
        }
        return z;
    }

    public void removeMoveGestureListener() {
        this.moveGestureDetector.removeListener();
    }

    public void removeMultiFingerTapGestureListener() {
        this.multiFingerTapGestureDetector.removeListener();
    }

    public void removeRotateGestureListener() {
        this.rotateGestureDetector.removeListener();
    }

    public void removeShoveGestureListener() {
        this.shoveGestureDetector.removeListener();
    }

    public void removeSidewaysShoveGestureListener() {
        this.sidewaysShoveGestureDetector.removeListener();
    }

    public void removeStandardGestureListener() {
        this.standardGestureDetector.removeListener();
    }

    public void removeStandardScaleGestureListener() {
        this.standardScaleGestureDetector.removeListener();
    }

    public void setMoveGestureListener(MoveGestureDetector.OnMoveGestureListener onMoveGestureListener) {
        this.moveGestureDetector.setListener(onMoveGestureListener);
    }

    public void setMultiFingerTapGestureListener(MultiFingerTapGestureDetector.OnMultiFingerTapGestureListener onMultiFingerTapGestureListener) {
        this.multiFingerTapGestureDetector.setListener(onMultiFingerTapGestureListener);
    }

    @SafeVarargs
    public final void setMutuallyExclusiveGestures(Set<Integer>... setArr) {
        setMutuallyExclusiveGestures(Arrays.asList(setArr));
    }

    public void setRotateGestureListener(RotateGestureDetector.OnRotateGestureListener onRotateGestureListener) {
        this.rotateGestureDetector.setListener(onRotateGestureListener);
    }

    public void setShoveGestureListener(ShoveGestureDetector.OnShoveGestureListener onShoveGestureListener) {
        this.shoveGestureDetector.setListener(onShoveGestureListener);
    }

    public void setSidewaysShoveGestureListener(SidewaysShoveGestureDetector.OnSidewaysShoveGestureListener onSidewaysShoveGestureListener) {
        this.sidewaysShoveGestureDetector.setListener(onSidewaysShoveGestureListener);
    }

    public void setStandardGestureListener(StandardGestureDetector.StandardOnGestureListener standardOnGestureListener) {
        this.standardGestureDetector.setListener(standardOnGestureListener);
    }

    public void setStandardScaleGestureListener(StandardScaleGestureDetector.StandardOnScaleGestureListener standardOnScaleGestureListener) {
        this.standardScaleGestureDetector.setListener(standardOnScaleGestureListener);
    }

    public AndroidGesturesManager(Context context, boolean z) {
        this(context, new ArrayList(), z);
    }

    public void setMutuallyExclusiveGestures(List<Set<Integer>> list) {
        this.mutuallyExclusiveGestures.clear();
        this.mutuallyExclusiveGestures.addAll(list);
    }

    @SafeVarargs
    public AndroidGesturesManager(Context context, Set<Integer>... setArr) {
        this(context, Arrays.asList(setArr), true);
    }

    public AndroidGesturesManager(Context context, List<Set<Integer>> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        this.mutuallyExclusiveGestures = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.detectors = arrayList2;
        arrayList.addAll(list);
        RotateGestureDetector rotateGestureDetector = new RotateGestureDetector(context, this);
        this.rotateGestureDetector = rotateGestureDetector;
        StandardScaleGestureDetector standardScaleGestureDetector = new StandardScaleGestureDetector(context, this);
        this.standardScaleGestureDetector = standardScaleGestureDetector;
        ShoveGestureDetector shoveGestureDetector = new ShoveGestureDetector(context, this);
        this.shoveGestureDetector = shoveGestureDetector;
        SidewaysShoveGestureDetector sidewaysShoveGestureDetector = new SidewaysShoveGestureDetector(context, this);
        this.sidewaysShoveGestureDetector = sidewaysShoveGestureDetector;
        MultiFingerTapGestureDetector multiFingerTapGestureDetector = new MultiFingerTapGestureDetector(context, this);
        this.multiFingerTapGestureDetector = multiFingerTapGestureDetector;
        MoveGestureDetector moveGestureDetector = new MoveGestureDetector(context, this);
        this.moveGestureDetector = moveGestureDetector;
        StandardGestureDetector standardGestureDetector = new StandardGestureDetector(context, this);
        this.standardGestureDetector = standardGestureDetector;
        arrayList2.add(rotateGestureDetector);
        arrayList2.add(standardScaleGestureDetector);
        arrayList2.add(shoveGestureDetector);
        arrayList2.add(sidewaysShoveGestureDetector);
        arrayList2.add(multiFingerTapGestureDetector);
        arrayList2.add(moveGestureDetector);
        arrayList2.add(standardGestureDetector);
        if (z) {
            initDefaultThresholds();
        }
    }
}
