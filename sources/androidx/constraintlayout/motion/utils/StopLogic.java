package androidx.constraintlayout.motion.utils;

import androidx.constraintlayout.core.motion.utils.SpringStopEngine;
import androidx.constraintlayout.core.motion.utils.StopEngine;
import androidx.constraintlayout.core.motion.utils.StopLogicEngine;
import androidx.constraintlayout.motion.widget.MotionInterpolator;
/* loaded from: classes.dex */
public class StopLogic extends MotionInterpolator {

    /* renamed from: a  reason: collision with root package name */
    public StopLogicEngine f935a;
    public SpringStopEngine b;
    public StopEngine c;

    public StopLogic() {
        StopLogicEngine stopLogicEngine = new StopLogicEngine();
        this.f935a = stopLogicEngine;
        this.c = stopLogicEngine;
    }

    public void config(float f, float f2, float f3, float f4, float f5, float f6) {
        StopLogicEngine stopLogicEngine = this.f935a;
        this.c = stopLogicEngine;
        stopLogicEngine.config(f, f2, f3, f4, f5, f6);
    }

    public String debug(String str, float f) {
        return this.c.debug(str, f);
    }

    @Override // androidx.constraintlayout.motion.widget.MotionInterpolator, android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return this.c.getInterpolation(f);
    }

    public float getVelocity(float f) {
        return this.c.getVelocity(f);
    }

    public boolean isStopped() {
        return this.c.isStopped();
    }

    public void springConfig(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        if (this.b == null) {
            this.b = new SpringStopEngine();
        }
        SpringStopEngine springStopEngine = this.b;
        this.c = springStopEngine;
        springStopEngine.springConfig(f, f2, f3, f4, f5, f6, f7, i);
    }

    @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
    public float getVelocity() {
        return this.c.getVelocity();
    }
}
