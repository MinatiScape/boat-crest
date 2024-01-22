package com.github.mikephil.charting.animation;

import android.animation.TimeInterpolator;
import androidx.annotation.RequiresApi;
@RequiresApi(11)
/* loaded from: classes9.dex */
public class Easing {
    public static final EasingFunction Linear = new k();
    public static final EasingFunction EaseInQuad = new v();
    public static final EasingFunction EaseOutQuad = new w();
    public static final EasingFunction EaseInOutQuad = new x();
    public static final EasingFunction EaseInCubic = new y();
    public static final EasingFunction EaseOutCubic = new z();
    public static final EasingFunction EaseInOutCubic = new a0();
    public static final EasingFunction EaseInQuart = new b0();
    public static final EasingFunction EaseOutQuart = new c0();
    public static final EasingFunction EaseInOutQuart = new a();
    public static final EasingFunction EaseInSine = new b();
    public static final EasingFunction EaseOutSine = new c();
    public static final EasingFunction EaseInOutSine = new d();
    public static final EasingFunction EaseInExpo = new e();
    public static final EasingFunction EaseOutExpo = new f();
    public static final EasingFunction EaseInOutExpo = new g();
    public static final EasingFunction EaseInCirc = new h();
    public static final EasingFunction EaseOutCirc = new i();
    public static final EasingFunction EaseInOutCirc = new j();
    public static final EasingFunction EaseInElastic = new l();
    public static final EasingFunction EaseOutElastic = new m();
    public static final EasingFunction EaseInOutElastic = new n();
    public static final EasingFunction EaseInBack = new o();
    public static final EasingFunction EaseOutBack = new p();
    public static final EasingFunction EaseInOutBack = new q();
    public static final EasingFunction EaseInBounce = new r();
    public static final EasingFunction EaseOutBounce = new s();
    public static final EasingFunction EaseInOutBounce = new t();

    /* loaded from: classes9.dex */
    public interface EasingFunction extends TimeInterpolator {
        @Override // android.animation.TimeInterpolator
        float getInterpolation(float f);
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public enum EasingOption {
        Linear,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,
        EaseOutCirc,
        EaseInOutCirc,
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,
        EaseInBack,
        EaseOutBack,
        EaseInOutBack,
        EaseInBounce,
        EaseOutBounce,
        EaseInOutBounce
    }

    /* loaded from: classes9.dex */
    public static class a implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return ((float) Math.pow(f2, 4.0d)) * 0.5f;
            }
            return (((float) Math.pow(f2 - 2.0f, 4.0d)) - 2.0f) * (-0.5f);
        }
    }

    /* loaded from: classes9.dex */
    public static class a0 implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float pow;
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                pow = (float) Math.pow(f2, 3.0d);
            } else {
                pow = ((float) Math.pow(f2 - 2.0f, 3.0d)) + 2.0f;
            }
            return pow * 0.5f;
        }
    }

    /* loaded from: classes9.dex */
    public static class b implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (-((float) Math.cos(f * 1.5707963267948966d))) + 1.0f;
        }
    }

    /* loaded from: classes9.dex */
    public static class b0 implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) Math.pow(f, 4.0d);
        }
    }

    /* loaded from: classes9.dex */
    public static class c implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) Math.sin(f * 1.5707963267948966d);
        }
    }

    /* loaded from: classes9.dex */
    public static class c0 implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return -(((float) Math.pow(f - 1.0f, 4.0d)) - 1.0f);
        }
    }

    /* loaded from: classes9.dex */
    public static class d implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (((float) Math.cos(f * 3.141592653589793d)) - 1.0f) * (-0.5f);
        }
    }

    /* loaded from: classes9.dex */
    public static class e implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            return (float) Math.pow(2.0d, (f - 1.0f) * 10.0f);
        }
    }

    /* loaded from: classes9.dex */
    public static class f implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f == 1.0f) {
                return 1.0f;
            }
            return -((float) Math.pow(2.0d, (f + 1.0f) * (-10.0f)));
        }
    }

    /* loaded from: classes9.dex */
    public static class g implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2;
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f == 1.0f) {
                return 1.0f;
            }
            float f3 = f * 2.0f;
            if (f3 < 1.0f) {
                f2 = (float) Math.pow(2.0d, (f3 - 1.0f) * 10.0f);
            } else {
                f2 = (-((float) Math.pow(2.0d, (f3 - 1.0f) * (-10.0f)))) + 2.0f;
            }
            return f2 * 0.5f;
        }
    }

    /* loaded from: classes9.dex */
    public static class h implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return -(((float) Math.sqrt(1.0f - (f * f))) - 1.0f);
        }
    }

    /* loaded from: classes9.dex */
    public static class i implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (float) Math.sqrt(1.0f - (f2 * f2));
        }
    }

    /* loaded from: classes9.dex */
    public static class j implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return (((float) Math.sqrt(1.0f - (f2 * f2))) - 1.0f) * (-0.5f);
            }
            float f3 = f2 - 2.0f;
            return (((float) Math.sqrt(1.0f - (f3 * f3))) + 1.0f) * 0.5f;
        }
    }

    /* loaded from: classes9.dex */
    public static class k implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return f;
        }
    }

    /* loaded from: classes9.dex */
    public static class l implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f == 1.0f) {
                return 1.0f;
            }
            float f2 = f - 1.0f;
            return -(((float) Math.pow(2.0d, 10.0f * f2)) * ((float) Math.sin(((f2 - (0.047746483f * ((float) Math.asin(1.0d)))) * 6.2831855f) / 0.3f)));
        }
    }

    /* loaded from: classes9.dex */
    public static class m implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f == 1.0f) {
                return 1.0f;
            }
            return (((float) Math.pow(2.0d, (-10.0f) * f)) * ((float) Math.sin(((f - (0.047746483f * ((float) Math.asin(1.0d)))) * 6.2831855f) / 0.3f))) + 1.0f;
        }
    }

    /* loaded from: classes9.dex */
    public static class n implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            float f2 = f * 2.0f;
            if (f2 == 2.0f) {
                return 1.0f;
            }
            float asin = ((float) Math.asin(1.0d)) * 0.07161972f;
            if (f2 < 1.0f) {
                float f3 = f2 - 1.0f;
                return ((float) Math.pow(2.0d, 10.0f * f3)) * ((float) Math.sin(((f3 * 1.0f) - asin) * 6.2831855f * 2.2222223f)) * (-0.5f);
            }
            float f4 = f2 - 1.0f;
            return (((float) Math.pow(2.0d, (-10.0f) * f4)) * 0.5f * ((float) Math.sin(((f4 * 1.0f) - asin) * 6.2831855f * 2.2222223f))) + 1.0f;
        }
    }

    /* loaded from: classes9.dex */
    public static class o implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return f * f * ((f * 2.70158f) - 1.70158f);
        }
    }

    /* loaded from: classes9.dex */
    public static class p implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * ((f2 * 2.70158f) + 1.70158f)) + 1.0f;
        }
    }

    /* loaded from: classes9.dex */
    public static class q implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return f2 * f2 * ((3.5949094f * f2) - 2.5949094f) * 0.5f;
            }
            float f3 = f2 - 2.0f;
            return ((f3 * f3 * ((3.5949094f * f3) + 2.5949094f)) + 2.0f) * 0.5f;
        }
    }

    /* loaded from: classes9.dex */
    public static class r implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return 1.0f - Easing.EaseOutBounce.getInterpolation(1.0f - f);
        }
    }

    /* loaded from: classes9.dex */
    public static class s implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f < 0.36363637f) {
                return 7.5625f * f * f;
            }
            if (f < 0.72727275f) {
                float f2 = f - 0.54545456f;
                return (7.5625f * f2 * f2) + 0.75f;
            } else if (f < 0.90909094f) {
                float f3 = f - 0.8181818f;
                return (7.5625f * f3 * f3) + 0.9375f;
            } else {
                float f4 = f - 0.95454544f;
                return (7.5625f * f4 * f4) + 0.984375f;
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class t implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f < 0.5f) {
                return Easing.EaseInBounce.getInterpolation(f * 2.0f) * 0.5f;
            }
            return (Easing.EaseOutBounce.getInterpolation((f * 2.0f) - 1.0f) * 0.5f) + 0.5f;
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class u {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7930a;

        static {
            int[] iArr = new int[EasingOption.values().length];
            f7930a = iArr;
            try {
                iArr[EasingOption.Linear.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7930a[EasingOption.EaseInQuad.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7930a[EasingOption.EaseOutQuad.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7930a[EasingOption.EaseInOutQuad.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7930a[EasingOption.EaseInCubic.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7930a[EasingOption.EaseOutCubic.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7930a[EasingOption.EaseInOutCubic.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7930a[EasingOption.EaseInQuart.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7930a[EasingOption.EaseOutQuart.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7930a[EasingOption.EaseInOutQuart.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f7930a[EasingOption.EaseInSine.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f7930a[EasingOption.EaseOutSine.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f7930a[EasingOption.EaseInOutSine.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f7930a[EasingOption.EaseInExpo.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f7930a[EasingOption.EaseOutExpo.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f7930a[EasingOption.EaseInOutExpo.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f7930a[EasingOption.EaseInCirc.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f7930a[EasingOption.EaseOutCirc.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f7930a[EasingOption.EaseInOutCirc.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f7930a[EasingOption.EaseInElastic.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f7930a[EasingOption.EaseOutElastic.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f7930a[EasingOption.EaseInOutElastic.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f7930a[EasingOption.EaseInBack.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f7930a[EasingOption.EaseOutBack.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f7930a[EasingOption.EaseInOutBack.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f7930a[EasingOption.EaseInBounce.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f7930a[EasingOption.EaseOutBounce.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f7930a[EasingOption.EaseInOutBounce.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class v implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return f * f;
        }
    }

    /* loaded from: classes9.dex */
    public static class w implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (-f) * (f - 2.0f);
        }
    }

    /* loaded from: classes9.dex */
    public static class x implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return 0.5f * f2 * f2;
            }
            float f3 = f2 - 1.0f;
            return ((f3 * (f3 - 2.0f)) - 1.0f) * (-0.5f);
        }
    }

    /* loaded from: classes9.dex */
    public static class y implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) Math.pow(f, 3.0d);
        }
    }

    /* loaded from: classes9.dex */
    public static class z implements EasingFunction {
        @Override // com.github.mikephil.charting.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return ((float) Math.pow(f - 1.0f, 3.0d)) + 1.0f;
        }
    }

    @Deprecated
    public static EasingFunction getEasingFunctionFromOption(EasingOption easingOption) {
        switch (u.f7930a[easingOption.ordinal()]) {
            case 2:
                return EaseInQuad;
            case 3:
                return EaseOutQuad;
            case 4:
                return EaseInOutQuad;
            case 5:
                return EaseInCubic;
            case 6:
                return EaseOutCubic;
            case 7:
                return EaseInOutCubic;
            case 8:
                return EaseInQuart;
            case 9:
                return EaseOutQuart;
            case 10:
                return EaseInOutQuart;
            case 11:
                return EaseInSine;
            case 12:
                return EaseOutSine;
            case 13:
                return EaseInOutSine;
            case 14:
                return EaseInExpo;
            case 15:
                return EaseOutExpo;
            case 16:
                return EaseInOutExpo;
            case 17:
                return EaseInCirc;
            case 18:
                return EaseOutCirc;
            case 19:
                return EaseInOutCirc;
            case 20:
                return EaseInElastic;
            case 21:
                return EaseOutElastic;
            case 22:
                return EaseInOutElastic;
            case 23:
                return EaseInBack;
            case 24:
                return EaseOutBack;
            case 25:
                return EaseInOutBack;
            case 26:
                return EaseInBounce;
            case 27:
                return EaseOutBounce;
            case 28:
                return EaseInOutBounce;
            default:
                return Linear;
        }
    }
}
