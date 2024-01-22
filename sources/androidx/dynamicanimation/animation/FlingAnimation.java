package androidx.dynamicanimation.animation;

import androidx.annotation.FloatRange;
import androidx.dynamicanimation.animation.DynamicAnimation;
/* loaded from: classes.dex */
public final class FlingAnimation extends DynamicAnimation<FlingAnimation> {
    public final a m;

    /* loaded from: classes.dex */
    public static final class a {
        public float b;

        /* renamed from: a  reason: collision with root package name */
        public float f1243a = -4.2f;
        public final DynamicAnimation.p c = new DynamicAnimation.p();

        public float a() {
            return this.f1243a / (-4.2f);
        }

        public boolean b(float f, float f2) {
            return Math.abs(f2) < this.b;
        }

        public void c(float f) {
            this.f1243a = f * (-4.2f);
        }

        public void d(float f) {
            this.b = f * 62.5f;
        }

        public DynamicAnimation.p e(float f, float f2, long j) {
            float f3 = (float) j;
            this.c.b = (float) (f2 * Math.exp((f3 / 1000.0f) * this.f1243a));
            DynamicAnimation.p pVar = this.c;
            float f4 = this.f1243a;
            pVar.f1242a = (float) ((f - (f2 / f4)) + ((f2 / f4) * Math.exp((f4 * f3) / 1000.0f)));
            DynamicAnimation.p pVar2 = this.c;
            if (b(pVar2.f1242a, pVar2.b)) {
                this.c.b = 0.0f;
            }
            return this.c;
        }
    }

    public FlingAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        a aVar = new a();
        this.m = aVar;
        aVar.d(c());
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void g(float f) {
        this.m.d(f);
    }

    public float getFriction() {
        return this.m.a();
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public boolean i(long j) {
        DynamicAnimation.p e = this.m.e(this.b, this.f1240a, j);
        float f = e.f1242a;
        this.b = f;
        float f2 = e.b;
        this.f1240a = f2;
        float f3 = this.h;
        if (f < f3) {
            this.b = f3;
            return true;
        }
        float f4 = this.g;
        if (f <= f4) {
            return j(f, f2);
        }
        this.b = f4;
        return true;
    }

    public boolean j(float f, float f2) {
        return f >= this.g || f <= this.h || this.m.b(f, f2);
    }

    public FlingAnimation setFriction(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f > 0.0f) {
            this.m.c(f);
            return this;
        }
        throw new IllegalArgumentException("Friction must be positive");
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMaxValue(float f) {
        super.setMaxValue(f);
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMinValue(float f) {
        super.setMinValue(f);
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setStartVelocity(float f) {
        super.setStartVelocity(f);
        return this;
    }

    public <K> FlingAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
        a aVar = new a();
        this.m = aVar;
        aVar.d(c());
    }
}
