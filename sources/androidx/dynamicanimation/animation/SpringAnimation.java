package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import androidx.dynamicanimation.animation.DynamicAnimation;
/* loaded from: classes.dex */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    public SpringForce m;
    public float n;
    public boolean o;

    public SpringAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
    }

    public void animateToFinalPosition(float f) {
        if (isRunning()) {
            this.n = f;
            return;
        }
        if (this.m == null) {
            this.m = new SpringForce(f);
        }
        this.m.setFinalPosition(f);
        start();
    }

    public boolean canSkipToEnd() {
        return this.m.b > 0.0d;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void g(float f) {
    }

    public SpringForce getSpring() {
        return this.m;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public boolean i(long j) {
        if (this.o) {
            float f = this.n;
            if (f != Float.MAX_VALUE) {
                this.m.setFinalPosition(f);
                this.n = Float.MAX_VALUE;
            }
            this.b = this.m.getFinalPosition();
            this.f1240a = 0.0f;
            this.o = false;
            return true;
        }
        if (this.n != Float.MAX_VALUE) {
            this.m.getFinalPosition();
            long j2 = j / 2;
            DynamicAnimation.p c = this.m.c(this.b, this.f1240a, j2);
            this.m.setFinalPosition(this.n);
            this.n = Float.MAX_VALUE;
            DynamicAnimation.p c2 = this.m.c(c.f1242a, c.b, j2);
            this.b = c2.f1242a;
            this.f1240a = c2.b;
        } else {
            DynamicAnimation.p c3 = this.m.c(this.b, this.f1240a, j);
            this.b = c3.f1242a;
            this.f1240a = c3.b;
        }
        float max = Math.max(this.b, this.h);
        this.b = max;
        float min = Math.min(max, this.g);
        this.b = min;
        if (j(min, this.f1240a)) {
            this.b = this.m.getFinalPosition();
            this.f1240a = 0.0f;
            return true;
        }
        return false;
    }

    public boolean j(float f, float f2) {
        return this.m.isAtEquilibrium(f, f2);
    }

    public final void k() {
        SpringForce springForce = this.m;
        if (springForce != null) {
            double finalPosition = springForce.getFinalPosition();
            if (finalPosition <= this.g) {
                if (finalPosition < this.h) {
                    throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
                }
                return;
            }
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
    }

    public SpringAnimation setSpring(SpringForce springForce) {
        this.m = springForce;
        return this;
    }

    public void skipToEnd() {
        if (canSkipToEnd()) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (this.f) {
                    this.o = true;
                    return;
                }
                return;
            }
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void start() {
        k();
        this.m.b(c());
        super.start();
    }

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
    }

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat, float f) {
        super(k, floatPropertyCompat);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
        this.m = new SpringForce(f);
    }
}
