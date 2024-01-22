package com.airbnb.lottie.utils;

import android.view.Choreographer;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
/* loaded from: classes.dex */
public class LottieValueAnimator extends BaseLottieAnimator implements Choreographer.FrameCallback {
    @Nullable
    public LottieComposition s;
    public float k = 1.0f;
    public boolean l = false;
    public long m = 0;
    public float n = 0.0f;
    public float o = 0.0f;
    public int p = 0;
    public float q = -2.14748365E9f;
    public float r = 2.14748365E9f;
    @VisibleForTesting
    public boolean running = false;
    public boolean t = false;

    @Override // com.airbnb.lottie.utils.BaseLottieAnimator
    public void a() {
        super.a();
        b(i());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    @MainThread
    public void cancel() {
        a();
        removeFrameCallback();
    }

    public void clearComposition() {
        this.s = null;
        this.q = -2.14748365E9f;
        this.r = 2.14748365E9f;
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        postFrameCallback();
        if (this.s == null || !isRunning()) {
            return;
        }
        L.beginSection("LottieValueAnimator#doFrame");
        long j2 = this.m;
        float h = ((float) (j2 != 0 ? j - j2 : 0L)) / h();
        float f = this.n;
        if (i()) {
            h = -h;
        }
        float f2 = f + h;
        boolean z = !MiscUtils.contains(f2, getMinFrame(), getMaxFrame());
        float f3 = this.n;
        float clamp = MiscUtils.clamp(f2, getMinFrame(), getMaxFrame());
        this.n = clamp;
        if (this.t) {
            clamp = (float) Math.floor(clamp);
        }
        this.o = clamp;
        this.m = j;
        if (!this.t || this.n != f3) {
            g();
        }
        if (z) {
            if (getRepeatCount() != -1 && this.p >= getRepeatCount()) {
                float minFrame = this.k < 0.0f ? getMinFrame() : getMaxFrame();
                this.n = minFrame;
                this.o = minFrame;
                removeFrameCallback();
                b(i());
            } else {
                d();
                this.p++;
                if (getRepeatMode() == 2) {
                    this.l = !this.l;
                    reverseAnimationSpeed();
                } else {
                    float maxFrame = i() ? getMaxFrame() : getMinFrame();
                    this.n = maxFrame;
                    this.o = maxFrame;
                }
                this.m = j;
            }
        }
        j();
        L.endSection("LottieValueAnimator#doFrame");
    }

    @MainThread
    public void endAnimation() {
        removeFrameCallback();
        b(i());
    }

    @Override // android.animation.ValueAnimator
    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAnimatedFraction() {
        float minFrame;
        float maxFrame;
        float minFrame2;
        if (this.s == null) {
            return 0.0f;
        }
        if (i()) {
            minFrame = getMaxFrame() - this.o;
            maxFrame = getMaxFrame();
            minFrame2 = getMinFrame();
        } else {
            minFrame = this.o - getMinFrame();
            maxFrame = getMaxFrame();
            minFrame2 = getMinFrame();
        }
        return minFrame / (maxFrame - minFrame2);
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(getAnimatedValueAbsolute());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAnimatedValueAbsolute() {
        LottieComposition lottieComposition = this.s;
        if (lottieComposition == null) {
            return 0.0f;
        }
        return (this.o - lottieComposition.getStartFrame()) / (this.s.getEndFrame() - this.s.getStartFrame());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getDuration() {
        LottieComposition lottieComposition = this.s;
        if (lottieComposition == null) {
            return 0L;
        }
        return lottieComposition.getDuration();
    }

    public float getFrame() {
        return this.o;
    }

    public float getMaxFrame() {
        LottieComposition lottieComposition = this.s;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f = this.r;
        return f == 2.14748365E9f ? lottieComposition.getEndFrame() : f;
    }

    public float getMinFrame() {
        LottieComposition lottieComposition = this.s;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f = this.q;
        return f == -2.14748365E9f ? lottieComposition.getStartFrame() : f;
    }

    public float getSpeed() {
        return this.k;
    }

    public final float h() {
        LottieComposition lottieComposition = this.s;
        if (lottieComposition == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / lottieComposition.getFrameRate()) / Math.abs(this.k);
    }

    public final boolean i() {
        return getSpeed() < 0.0f;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.running;
    }

    public final void j() {
        if (this.s == null) {
            return;
        }
        float f = this.o;
        if (f < this.q || f > this.r) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.q), Float.valueOf(this.r), Float.valueOf(this.o)));
        }
    }

    @MainThread
    public void pauseAnimation() {
        removeFrameCallback();
        c();
    }

    @MainThread
    public void playAnimation() {
        this.running = true;
        f(i());
        setFrame((int) (i() ? getMaxFrame() : getMinFrame()));
        this.m = 0L;
        this.p = 0;
        postFrameCallback();
    }

    public void postFrameCallback() {
        if (isRunning()) {
            removeFrameCallback(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @MainThread
    public void removeFrameCallback() {
        removeFrameCallback(true);
    }

    @MainThread
    public void resumeAnimation() {
        this.running = true;
        postFrameCallback();
        this.m = 0L;
        if (i() && getFrame() == getMinFrame()) {
            setFrame(getMaxFrame());
        } else if (!i() && getFrame() == getMaxFrame()) {
            setFrame(getMinFrame());
        }
        e();
    }

    public void reverseAnimationSpeed() {
        setSpeed(-getSpeed());
    }

    public void setComposition(LottieComposition lottieComposition) {
        boolean z = this.s == null;
        this.s = lottieComposition;
        if (z) {
            setMinAndMaxFrames(Math.max(this.q, lottieComposition.getStartFrame()), Math.min(this.r, lottieComposition.getEndFrame()));
        } else {
            setMinAndMaxFrames((int) lottieComposition.getStartFrame(), (int) lottieComposition.getEndFrame());
        }
        float f = this.o;
        this.o = 0.0f;
        this.n = 0.0f;
        setFrame((int) f);
        g();
    }

    public void setFrame(float f) {
        if (this.n == f) {
            return;
        }
        float clamp = MiscUtils.clamp(f, getMinFrame(), getMaxFrame());
        this.n = clamp;
        if (this.t) {
            clamp = (float) Math.floor(clamp);
        }
        this.o = clamp;
        this.m = 0L;
        g();
    }

    public void setMaxFrame(float f) {
        setMinAndMaxFrames(this.q, f);
    }

    public void setMinAndMaxFrames(float f, float f2) {
        if (f <= f2) {
            LottieComposition lottieComposition = this.s;
            float startFrame = lottieComposition == null ? -3.4028235E38f : lottieComposition.getStartFrame();
            LottieComposition lottieComposition2 = this.s;
            float endFrame = lottieComposition2 == null ? Float.MAX_VALUE : lottieComposition2.getEndFrame();
            float clamp = MiscUtils.clamp(f, startFrame, endFrame);
            float clamp2 = MiscUtils.clamp(f2, startFrame, endFrame);
            if (clamp == this.q && clamp2 == this.r) {
                return;
            }
            this.q = clamp;
            this.r = clamp2;
            setFrame((int) MiscUtils.clamp(this.o, clamp, clamp2));
            return;
        }
        throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(f), Float.valueOf(f2)));
    }

    public void setMinFrame(int i) {
        setMinAndMaxFrames(i, (int) this.r);
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int i) {
        super.setRepeatMode(i);
        if (i == 2 || !this.l) {
            return;
        }
        this.l = false;
        reverseAnimationSpeed();
    }

    public void setSpeed(float f) {
        this.k = f;
    }

    public void setUseCompositionFrameRate(boolean z) {
        this.t = z;
    }

    @MainThread
    public void removeFrameCallback(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z) {
            this.running = false;
        }
    }
}
