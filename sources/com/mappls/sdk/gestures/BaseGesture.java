package com.mappls.sdk.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.WindowManager;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import java.util.Set;
@Keep
@UiThread
/* loaded from: classes11.dex */
public abstract class BaseGesture<L> {
    public final Context context;
    private MotionEvent currentEvent;
    private long gestureDuration;
    private final AndroidGesturesManager gesturesManager;
    private boolean isEnabled = true;
    public L listener;
    private MotionEvent previousEvent;
    public final WindowManager windowManager;

    public BaseGesture(Context context, AndroidGesturesManager androidGesturesManager) {
        this.context = context;
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.gesturesManager = androidGesturesManager;
    }

    private boolean analyze(@Nullable MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        MotionEvent motionEvent2 = this.previousEvent;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
            this.previousEvent = null;
        }
        MotionEvent motionEvent3 = this.currentEvent;
        if (motionEvent3 != null) {
            this.previousEvent = MotionEvent.obtain(motionEvent3);
            this.currentEvent.recycle();
            this.currentEvent = null;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        this.currentEvent = obtain;
        this.gestureDuration = obtain.getEventTime() - this.currentEvent.getDownTime();
        return analyzeEvent(motionEvent);
    }

    public abstract boolean analyzeEvent(@NonNull MotionEvent motionEvent);

    public boolean canExecute(int i) {
        if (this.listener == null || !this.isEnabled) {
            return false;
        }
        for (Set<Integer> set : this.gesturesManager.getMutuallyExclusiveGestures()) {
            if (set.contains(Integer.valueOf(i))) {
                for (Integer num : set) {
                    int intValue = num.intValue();
                    for (BaseGesture baseGesture : this.gesturesManager.getDetectors()) {
                        if (baseGesture instanceof ProgressiveGesture) {
                            ProgressiveGesture progressiveGesture = (ProgressiveGesture) baseGesture;
                            if (progressiveGesture.getHandledTypes().contains(Integer.valueOf(intValue)) && progressiveGesture.isInProgress()) {
                                return false;
                            }
                        }
                    }
                }
                continue;
            }
        }
        return true;
    }

    public MotionEvent getCurrentEvent() {
        return this.currentEvent;
    }

    public long getGestureDuration() {
        return this.gestureDuration;
    }

    public MotionEvent getPreviousEvent() {
        return this.previousEvent;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return analyze(motionEvent);
    }

    public void removeListener() {
        this.listener = null;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void setListener(L l) {
        this.listener = l;
    }
}
