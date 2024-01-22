package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.Arrays;
import java.util.HashMap;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
/* loaded from: classes.dex */
public class MotionPaths implements Comparable<MotionPaths> {
    public static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    public static final boolean OLD_WAY = false;
    public static final int PERPENDICULAR = 1;
    public static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    public static String[] z = {DeviceKey.position, "x", EllipticCurveJsonWebKey.Y_MEMBER_NAME, Property.ICON_TEXT_FIT_WIDTH, Property.ICON_TEXT_FIT_HEIGHT, "pathRotate"};
    public Easing h;
    public int i;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    public int r;
    public int s;
    public float t;
    public Motion u;
    public HashMap<String, CustomVariable> v;
    public int w;
    public double[] x;
    public double[] y;

    public MotionPaths() {
        this.i = 0;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = -1;
        this.s = -1;
        this.t = Float.NaN;
        this.u = null;
        this.v = new HashMap<>();
        this.w = 0;
        this.x = new double[18];
        this.y = new double[18];
    }

    public final boolean a(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void applyParameters(MotionWidget motionWidget) {
        this.h = Easing.getInterpolator(motionWidget.b.mTransitionEasing);
        MotionWidget.Motion motion = motionWidget.b;
        this.r = motion.mPathMotionArc;
        this.s = motion.mAnimateRelativeTo;
        this.p = motion.mPathRotate;
        this.i = motion.mDrawPath;
        int i = motion.mAnimateCircleAngleTo;
        this.q = motionWidget.c.mProgress;
        this.t = 0.0f;
        for (String str : motionWidget.getCustomAttributeNames()) {
            CustomVariable customAttribute = motionWidget.getCustomAttribute(str);
            if (customAttribute != null && customAttribute.isContinuous()) {
                this.v.put(str, customAttribute);
            }
        }
    }

    public void b(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z2) {
        boolean a2 = a(this.l, motionPaths.l);
        boolean a3 = a(this.m, motionPaths.m);
        zArr[0] = zArr[0] | a(this.k, motionPaths.k);
        boolean z3 = a2 | a3 | z2;
        zArr[1] = zArr[1] | z3;
        zArr[2] = z3 | zArr[2];
        zArr[3] = zArr[3] | a(this.n, motionPaths.n);
        zArr[4] = a(this.o, motionPaths.o) | zArr[4];
    }

    public void c(double[] dArr, int[] iArr) {
        float[] fArr = {this.k, this.l, this.m, this.n, this.o, this.p};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    public void configureRelativeTo(Motion motion) {
        motion.c(this.q);
    }

    public void d(double d, int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.l;
        float f2 = this.m;
        float f3 = this.n;
        float f4 = this.o;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        Motion motion = this.u;
        if (motion != null) {
            float[] fArr2 = new float[2];
            motion.getCenter(d, fArr2, new float[2]);
            float f6 = fArr2[0];
            float f7 = fArr2[1];
            double d2 = f6;
            double d3 = f;
            double d4 = f2;
            f = (float) ((d2 + (Math.sin(d4) * d3)) - (f3 / 2.0f));
            f2 = (float) ((f7 - (d3 * Math.cos(d4))) - (f4 / 2.0f));
        }
        fArr[i] = f + (f3 / 2.0f) + 0.0f;
        fArr[i + 1] = f2 + (f4 / 2.0f) + 0.0f;
    }

    public void e(double d, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f;
        float f2 = this.l;
        float f3 = this.m;
        float f4 = this.n;
        float f5 = this.o;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f10 = (float) dArr[i];
            float f11 = (float) dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f2 = f10;
                f6 = f11;
            } else if (i2 == 2) {
                f3 = f10;
                f8 = f11;
            } else if (i2 == 3) {
                f4 = f10;
                f7 = f11;
            } else if (i2 == 4) {
                f5 = f10;
                f9 = f11;
            }
        }
        float f12 = 2.0f;
        float f13 = (f7 / 2.0f) + f6;
        float f14 = (f9 / 2.0f) + f8;
        Motion motion = this.u;
        if (motion != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motion.getCenter(d, fArr3, fArr4);
            float f15 = fArr3[0];
            float f16 = fArr3[1];
            float f17 = fArr4[0];
            float f18 = fArr4[1];
            double d2 = f2;
            double d3 = f3;
            f = f4;
            double d4 = f6;
            double d5 = f8;
            float sin = (float) (f17 + (Math.sin(d3) * d4) + (Math.cos(d3) * d5));
            f14 = (float) ((f18 - (d4 * Math.cos(d3))) + (Math.sin(d3) * d5));
            f13 = sin;
            f2 = (float) ((f15 + (Math.sin(d3) * d2)) - (f4 / 2.0f));
            f3 = (float) ((f16 - (d2 * Math.cos(d3))) - (f5 / 2.0f));
            f12 = 2.0f;
        } else {
            f = f4;
        }
        fArr[0] = f2 + (f / f12) + 0.0f;
        fArr[1] = f3 + (f5 / f12) + 0.0f;
        fArr2[0] = f13;
        fArr2[1] = f14;
    }

    public int f(String str, double[] dArr, int i) {
        CustomVariable customVariable = this.v.get(str);
        int i2 = 0;
        if (customVariable == null) {
            return 0;
        }
        if (customVariable.numberOfInterpolatedValues() == 1) {
            dArr[i] = customVariable.getValueToInterpolate();
            return 1;
        }
        int numberOfInterpolatedValues = customVariable.numberOfInterpolatedValues();
        float[] fArr = new float[numberOfInterpolatedValues];
        customVariable.getValuesToInterpolate(fArr);
        while (i2 < numberOfInterpolatedValues) {
            dArr[i] = fArr[i2];
            i2++;
            i++;
        }
        return numberOfInterpolatedValues;
    }

    public int g(String str) {
        CustomVariable customVariable = this.v.get(str);
        if (customVariable == null) {
            return 0;
        }
        return customVariable.numberOfInterpolatedValues();
    }

    public void h(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.l;
        float f2 = this.m;
        float f3 = this.n;
        float f4 = this.o;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        Motion motion = this.u;
        if (motion != null) {
            float centerX = motion.getCenterX();
            double d = f;
            double d2 = f2;
            f2 = (float) ((this.u.getCenterY() - (d * Math.cos(d2))) - (f4 / 2.0f));
            f = (float) ((centerX + (Math.sin(d2) * d)) - (f3 / 2.0f));
        }
        float f6 = f3 + f;
        float f7 = f4 + f2;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = f2 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f6 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f2 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f6 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f7 + 0.0f;
        fArr[i9] = f + 0.0f;
        fArr[i9 + 1] = f7 + 0.0f;
    }

    public boolean i(String str) {
        return this.v.containsKey(str);
    }

    public void j(MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f = motionKeyPosition.mFramePosition / 100.0f;
        this.j = f;
        this.i = motionKeyPosition.mDrawPath;
        float f2 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f : motionKeyPosition.mPercentWidth;
        float f3 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f : motionKeyPosition.mPercentHeight;
        float f4 = motionPaths2.n;
        float f5 = motionPaths.n;
        float f6 = motionPaths2.o;
        float f7 = motionPaths.o;
        this.k = this.j;
        float f8 = motionPaths.l;
        float f9 = motionPaths.m;
        float f10 = (motionPaths2.l + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (motionPaths2.m + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = (f4 - f5) * f2;
        float f13 = f12 / 2.0f;
        this.l = (int) ((f8 + (f10 * f)) - f13);
        float f14 = (f6 - f7) * f3;
        float f15 = f14 / 2.0f;
        this.m = (int) ((f9 + (f11 * f)) - f15);
        this.n = (int) (f5 + f12);
        this.o = (int) (f7 + f14);
        float f16 = Float.isNaN(motionKeyPosition.mPercentX) ? f : motionKeyPosition.mPercentX;
        float f17 = Float.isNaN(motionKeyPosition.mAltPercentY) ? 0.0f : motionKeyPosition.mAltPercentY;
        if (!Float.isNaN(motionKeyPosition.mPercentY)) {
            f = motionKeyPosition.mPercentY;
        }
        float f18 = Float.isNaN(motionKeyPosition.mAltPercentX) ? 0.0f : motionKeyPosition.mAltPercentX;
        this.w = 0;
        this.l = (int) (((motionPaths.l + (f16 * f10)) + (f18 * f11)) - f13);
        this.m = (int) (((motionPaths.m + (f10 * f17)) + (f11 * f)) - f15);
        this.h = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.r = motionKeyPosition.mPathMotionArc;
    }

    public void k(MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f;
        float f2;
        float f3 = motionKeyPosition.mFramePosition / 100.0f;
        this.j = f3;
        this.i = motionKeyPosition.mDrawPath;
        float f4 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f3 : motionKeyPosition.mPercentWidth;
        float f5 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f3 : motionKeyPosition.mPercentHeight;
        float f6 = motionPaths2.n - motionPaths.n;
        float f7 = motionPaths2.o - motionPaths.o;
        this.k = this.j;
        if (!Float.isNaN(motionKeyPosition.mPercentX)) {
            f3 = motionKeyPosition.mPercentX;
        }
        float f8 = motionPaths.l;
        float f9 = motionPaths.n;
        float f10 = motionPaths.m;
        float f11 = motionPaths.o;
        float f12 = (motionPaths2.l + (motionPaths2.n / 2.0f)) - ((f9 / 2.0f) + f8);
        float f13 = (motionPaths2.m + (motionPaths2.o / 2.0f)) - ((f11 / 2.0f) + f10);
        float f14 = f12 * f3;
        float f15 = (f6 * f4) / 2.0f;
        this.l = (int) ((f8 + f14) - f15);
        float f16 = f3 * f13;
        float f17 = (f7 * f5) / 2.0f;
        this.m = (int) ((f10 + f16) - f17);
        this.n = (int) (f9 + f);
        this.o = (int) (f11 + f2);
        float f18 = Float.isNaN(motionKeyPosition.mPercentY) ? 0.0f : motionKeyPosition.mPercentY;
        this.w = 1;
        float f19 = (int) ((motionPaths.l + f14) - f15);
        this.l = f19;
        float f20 = (int) ((motionPaths.m + f16) - f17);
        this.m = f20;
        this.l = f19 + ((-f13) * f18);
        this.m = f20 + (f12 * f18);
        this.s = this.s;
        this.h = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.r = motionKeyPosition.mPathMotionArc;
    }

    public void l(int i, int i2, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float min;
        float f;
        float f2 = motionKeyPosition.mFramePosition / 100.0f;
        this.j = f2;
        this.i = motionKeyPosition.mDrawPath;
        this.w = motionKeyPosition.mPositionType;
        float f3 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f2 : motionKeyPosition.mPercentWidth;
        float f4 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f2 : motionKeyPosition.mPercentHeight;
        float f5 = motionPaths2.n;
        float f6 = motionPaths.n;
        float f7 = motionPaths2.o;
        float f8 = motionPaths.o;
        this.k = this.j;
        this.n = (int) (f6 + ((f5 - f6) * f3));
        this.o = (int) (f8 + ((f7 - f8) * f4));
        int i3 = motionKeyPosition.mPositionType;
        if (i3 == 1) {
            float f9 = Float.isNaN(motionKeyPosition.mPercentX) ? f2 : motionKeyPosition.mPercentX;
            float f10 = motionPaths2.l;
            float f11 = motionPaths.l;
            this.l = (f9 * (f10 - f11)) + f11;
            if (!Float.isNaN(motionKeyPosition.mPercentY)) {
                f2 = motionKeyPosition.mPercentY;
            }
            float f12 = motionPaths2.m;
            float f13 = motionPaths.m;
            this.m = (f2 * (f12 - f13)) + f13;
        } else if (i3 != 2) {
            float f14 = Float.isNaN(motionKeyPosition.mPercentX) ? f2 : motionKeyPosition.mPercentX;
            float f15 = motionPaths2.l;
            float f16 = motionPaths.l;
            this.l = (f14 * (f15 - f16)) + f16;
            if (!Float.isNaN(motionKeyPosition.mPercentY)) {
                f2 = motionKeyPosition.mPercentY;
            }
            float f17 = motionPaths2.m;
            float f18 = motionPaths.m;
            this.m = (f2 * (f17 - f18)) + f18;
        } else {
            if (Float.isNaN(motionKeyPosition.mPercentX)) {
                float f19 = motionPaths2.l;
                float f20 = motionPaths.l;
                min = ((f19 - f20) * f2) + f20;
            } else {
                min = Math.min(f4, f3) * motionKeyPosition.mPercentX;
            }
            this.l = min;
            if (Float.isNaN(motionKeyPosition.mPercentY)) {
                float f21 = motionPaths2.m;
                float f22 = motionPaths.m;
                f = (f2 * (f21 - f22)) + f22;
            } else {
                f = motionKeyPosition.mPercentY;
            }
            this.m = f;
        }
        this.s = motionPaths.s;
        this.h = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.r = motionKeyPosition.mPathMotionArc;
    }

    public void m(int i, int i2, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f = motionKeyPosition.mFramePosition / 100.0f;
        this.j = f;
        this.i = motionKeyPosition.mDrawPath;
        float f2 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f : motionKeyPosition.mPercentWidth;
        float f3 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f : motionKeyPosition.mPercentHeight;
        float f4 = motionPaths2.n;
        float f5 = motionPaths.n;
        float f6 = motionPaths2.o;
        float f7 = motionPaths.o;
        this.k = this.j;
        float f8 = motionPaths.l;
        float f9 = motionPaths.m;
        float f10 = motionPaths2.l + (f4 / 2.0f);
        float f11 = motionPaths2.m + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.l = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.m = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.n = (int) (f5 + f12);
        this.o = (int) (f7 + f13);
        this.w = 2;
        if (!Float.isNaN(motionKeyPosition.mPercentX)) {
            this.l = (int) (motionKeyPosition.mPercentX * ((int) (i - this.n)));
        }
        if (!Float.isNaN(motionKeyPosition.mPercentY)) {
            this.m = (int) (motionKeyPosition.mPercentY * ((int) (i2 - this.o)));
        }
        this.s = this.s;
        this.h = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.r = motionKeyPosition.mPathMotionArc;
    }

    public void n(float f, float f2, float f3, float f4) {
        this.l = f;
        this.m = f2;
        this.n = f3;
        this.o = f4;
    }

    public void o(float f, MotionWidget motionWidget, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f2;
        float f3;
        float f4 = this.l;
        float f5 = this.m;
        float f6 = this.n;
        float f7 = this.o;
        if (iArr.length != 0 && this.x.length <= iArr[iArr.length - 1]) {
            int i = iArr[iArr.length - 1] + 1;
            this.x = new double[i];
            this.y = new double[i];
        }
        Arrays.fill(this.x, Double.NaN);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.x[iArr[i2]] = dArr[i2];
            this.y[iArr[i2]] = dArr2[i2];
        }
        float f8 = Float.NaN;
        int i3 = 0;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        while (true) {
            double[] dArr4 = this.x;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f3 = f8;
            } else {
                double d = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.x[i3])) {
                    d = this.x[i3] + d;
                }
                f3 = f8;
                float f13 = (float) d;
                float f14 = (float) this.y[i3];
                if (i3 == 1) {
                    f8 = f3;
                    f9 = f14;
                    f4 = f13;
                } else if (i3 == 2) {
                    f8 = f3;
                    f10 = f14;
                    f5 = f13;
                } else if (i3 == 3) {
                    f8 = f3;
                    f11 = f14;
                    f6 = f13;
                } else if (i3 == 4) {
                    f8 = f3;
                    f12 = f14;
                    f7 = f13;
                } else if (i3 == 5) {
                    f8 = f13;
                }
                i3++;
            }
            f8 = f3;
            i3++;
        }
        float f15 = f8;
        Motion motion = this.u;
        if (motion != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motion.getCenter(f, fArr, fArr2);
            float f16 = fArr[0];
            float f17 = fArr[1];
            float f18 = fArr2[0];
            float f19 = fArr2[1];
            double d2 = f4;
            double d3 = f5;
            float sin = (float) ((f16 + (Math.sin(d3) * d2)) - (f6 / 2.0f));
            f2 = f7;
            float cos = (float) ((f17 - (Math.cos(d3) * d2)) - (f7 / 2.0f));
            double d4 = f9;
            double d5 = f10;
            float sin2 = (float) (f18 + (Math.sin(d3) * d4) + (Math.cos(d3) * d2 * d5));
            float cos2 = (float) ((f19 - (d4 * Math.cos(d3))) + (d2 * Math.sin(d3) * d5));
            if (dArr2.length >= 2) {
                dArr2[0] = sin2;
                dArr2[1] = cos2;
            }
            if (!Float.isNaN(f15)) {
                motionWidget.setRotationZ((float) (f15 + Math.toDegrees(Math.atan2(cos2, sin2))));
            }
            f4 = sin;
            f5 = cos;
        } else {
            f2 = f7;
            if (!Float.isNaN(f15)) {
                motionWidget.setRotationZ((float) (0.0f + f15 + Math.toDegrees(Math.atan2(f10 + (f12 / 2.0f), f9 + (f11 / 2.0f)))));
            }
        }
        float f20 = f4 + 0.5f;
        float f21 = f5 + 0.5f;
        motionWidget.layout((int) f20, (int) f21, (int) (f20 + f6), (int) (f21 + f2));
    }

    public void setupRelative(Motion motion, MotionPaths motionPaths) {
        double d = ((this.l + (this.n / 2.0f)) - motionPaths.l) - (motionPaths.n / 2.0f);
        double d2 = ((this.m + (this.o / 2.0f)) - motionPaths.m) - (motionPaths.o / 2.0f);
        this.u = motion;
        this.l = (float) Math.hypot(d2, d);
        if (Float.isNaN(this.t)) {
            this.m = (float) (Math.atan2(d2, d) + 1.5707963267948966d);
        } else {
            this.m = (float) Math.toRadians(this.t);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionPaths motionPaths) {
        return Float.compare(this.k, motionPaths.k);
    }

    public MotionPaths(int i, int i2, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        this.i = 0;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = -1;
        this.s = -1;
        this.t = Float.NaN;
        this.u = null;
        this.v = new HashMap<>();
        this.w = 0;
        this.x = new double[18];
        this.y = new double[18];
        if (motionPaths.s != -1) {
            l(i, i2, motionKeyPosition, motionPaths, motionPaths2);
            return;
        }
        int i3 = motionKeyPosition.mPositionType;
        if (i3 == 1) {
            k(motionKeyPosition, motionPaths, motionPaths2);
        } else if (i3 != 2) {
            j(motionKeyPosition, motionPaths, motionPaths2);
        } else {
            m(i, i2, motionKeyPosition, motionPaths, motionPaths2);
        }
    }
}
