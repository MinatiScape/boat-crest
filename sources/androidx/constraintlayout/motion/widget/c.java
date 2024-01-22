package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
/* loaded from: classes.dex */
public class c implements Comparable<c> {
    public static String[] y = {DeviceKey.position, "x", EllipticCurveJsonWebKey.Y_MEMBER_NAME, Property.ICON_TEXT_FIT_WIDTH, Property.ICON_TEXT_FIT_HEIGHT, "pathRotate"};
    public Easing h;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    public int q;
    public int r;
    public float s;
    public MotionController t;
    public LinkedHashMap<String, ConstraintAttribute> u;
    public int v;
    public double[] w;
    public double[] x;
    public int i = 0;
    public float p = Float.NaN;

    public c() {
        int i = Key.UNSET;
        this.q = i;
        this.r = i;
        this.s = Float.NaN;
        this.t = null;
        this.u = new LinkedHashMap<>();
        this.v = 0;
        this.w = new double[18];
        this.x = new double[18];
    }

    public void a(ConstraintSet.Constraint constraint) {
        this.h = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.q = motion.mPathMotionArc;
        this.r = motion.mAnimateRelativeTo;
        this.p = motion.mPathRotate;
        this.i = motion.mDrawPath;
        int i = motion.mAnimateCircleAngleTo;
        float f = constraint.propertySet.mProgress;
        this.s = constraint.layout.circleAngle;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute != null && constraintAttribute.isContinuous()) {
                this.u.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: b */
    public int compareTo(@NonNull c cVar) {
        return Float.compare(this.k, cVar.k);
    }

    public final boolean c(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void d(c cVar, boolean[] zArr, String[] strArr, boolean z) {
        boolean c = c(this.l, cVar.l);
        boolean c2 = c(this.m, cVar.m);
        zArr[0] = zArr[0] | c(this.k, cVar.k);
        boolean z2 = c | c2 | z;
        zArr[1] = zArr[1] | z2;
        zArr[2] = z2 | zArr[2];
        zArr[3] = zArr[3] | c(this.n, cVar.n);
        zArr[4] = c(this.o, cVar.o) | zArr[4];
    }

    public void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.k, this.l, this.m, this.n, this.o, this.p};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    public void f(double d, int[] iArr, double[] dArr, float[] fArr, int i) {
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
        MotionController motionController = this.t;
        if (motionController != null) {
            float[] fArr2 = new float[2];
            motionController.getCenter(d, fArr2, new float[2]);
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

    public void g(double d, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
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
        MotionController motionController = this.t;
        if (motionController != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motionController.getCenter(d, fArr3, fArr4);
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

    public int h(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = this.u.get(str);
        int i2 = 0;
        if (constraintAttribute == null) {
            return 0;
        }
        if (constraintAttribute.numberOfInterpolatedValues() == 1) {
            dArr[i] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int numberOfInterpolatedValues = constraintAttribute.numberOfInterpolatedValues();
        float[] fArr = new float[numberOfInterpolatedValues];
        constraintAttribute.getValuesToInterpolate(fArr);
        while (i2 < numberOfInterpolatedValues) {
            dArr[i] = fArr[i2];
            i2++;
            i++;
        }
        return numberOfInterpolatedValues;
    }

    public int i(String str) {
        ConstraintAttribute constraintAttribute = this.u.get(str);
        if (constraintAttribute == null) {
            return 0;
        }
        return constraintAttribute.numberOfInterpolatedValues();
    }

    public void j(int[] iArr, double[] dArr, float[] fArr, int i) {
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
        MotionController motionController = this.t;
        if (motionController != null) {
            float centerX = motionController.getCenterX();
            double d = f;
            double d2 = f2;
            f2 = (float) ((this.t.getCenterY() - (d * Math.cos(d2))) - (f4 / 2.0f));
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

    public boolean k(String str) {
        return this.u.containsKey(str);
    }

    public void l(KeyPosition keyPosition, c cVar, c cVar2) {
        float f = keyPosition.f939a / 100.0f;
        this.j = f;
        this.i = keyPosition.h;
        float f2 = Float.isNaN(keyPosition.i) ? f : keyPosition.i;
        float f3 = Float.isNaN(keyPosition.j) ? f : keyPosition.j;
        float f4 = cVar2.n;
        float f5 = cVar.n;
        float f6 = cVar2.o;
        float f7 = cVar.o;
        this.k = this.j;
        float f8 = cVar.l;
        float f9 = cVar.m;
        float f10 = (cVar2.l + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (cVar2.m + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = (f4 - f5) * f2;
        float f13 = f12 / 2.0f;
        this.l = (int) ((f8 + (f10 * f)) - f13);
        float f14 = (f6 - f7) * f3;
        float f15 = f14 / 2.0f;
        this.m = (int) ((f9 + (f11 * f)) - f15);
        this.n = (int) (f5 + f12);
        this.o = (int) (f7 + f14);
        float f16 = Float.isNaN(keyPosition.k) ? f : keyPosition.k;
        float f17 = Float.isNaN(keyPosition.n) ? 0.0f : keyPosition.n;
        if (!Float.isNaN(keyPosition.l)) {
            f = keyPosition.l;
        }
        float f18 = Float.isNaN(keyPosition.m) ? 0.0f : keyPosition.m;
        this.v = 0;
        this.l = (int) (((cVar.l + (f16 * f10)) + (f18 * f11)) - f13);
        this.m = (int) (((cVar.m + (f10 * f17)) + (f11 * f)) - f15);
        this.h = Easing.getInterpolator(keyPosition.f);
        this.q = keyPosition.g;
    }

    public void m(KeyPosition keyPosition, c cVar, c cVar2) {
        float f;
        float f2;
        float f3 = keyPosition.f939a / 100.0f;
        this.j = f3;
        this.i = keyPosition.h;
        float f4 = Float.isNaN(keyPosition.i) ? f3 : keyPosition.i;
        float f5 = Float.isNaN(keyPosition.j) ? f3 : keyPosition.j;
        float f6 = cVar2.n - cVar.n;
        float f7 = cVar2.o - cVar.o;
        this.k = this.j;
        if (!Float.isNaN(keyPosition.k)) {
            f3 = keyPosition.k;
        }
        float f8 = cVar.l;
        float f9 = cVar.n;
        float f10 = cVar.m;
        float f11 = cVar.o;
        float f12 = (cVar2.l + (cVar2.n / 2.0f)) - ((f9 / 2.0f) + f8);
        float f13 = (cVar2.m + (cVar2.o / 2.0f)) - ((f11 / 2.0f) + f10);
        float f14 = f12 * f3;
        float f15 = (f6 * f4) / 2.0f;
        this.l = (int) ((f8 + f14) - f15);
        float f16 = f3 * f13;
        float f17 = (f7 * f5) / 2.0f;
        this.m = (int) ((f10 + f16) - f17);
        this.n = (int) (f9 + f);
        this.o = (int) (f11 + f2);
        float f18 = Float.isNaN(keyPosition.l) ? 0.0f : keyPosition.l;
        this.v = 1;
        float f19 = (int) ((cVar.l + f14) - f15);
        this.l = f19;
        float f20 = (int) ((cVar.m + f16) - f17);
        this.m = f20;
        this.l = f19 + ((-f13) * f18);
        this.m = f20 + (f12 * f18);
        this.r = this.r;
        this.h = Easing.getInterpolator(keyPosition.f);
        this.q = keyPosition.g;
    }

    public void n(int i, int i2, KeyPosition keyPosition, c cVar, c cVar2) {
        float min;
        float f;
        float f2 = keyPosition.f939a / 100.0f;
        this.j = f2;
        this.i = keyPosition.h;
        this.v = keyPosition.o;
        float f3 = Float.isNaN(keyPosition.i) ? f2 : keyPosition.i;
        float f4 = Float.isNaN(keyPosition.j) ? f2 : keyPosition.j;
        float f5 = cVar2.n;
        float f6 = cVar.n;
        float f7 = cVar2.o;
        float f8 = cVar.o;
        this.k = this.j;
        this.n = (int) (f6 + ((f5 - f6) * f3));
        this.o = (int) (f8 + ((f7 - f8) * f4));
        int i3 = keyPosition.o;
        if (i3 == 1) {
            float f9 = Float.isNaN(keyPosition.k) ? f2 : keyPosition.k;
            float f10 = cVar2.l;
            float f11 = cVar.l;
            this.l = (f9 * (f10 - f11)) + f11;
            if (!Float.isNaN(keyPosition.l)) {
                f2 = keyPosition.l;
            }
            float f12 = cVar2.m;
            float f13 = cVar.m;
            this.m = (f2 * (f12 - f13)) + f13;
        } else if (i3 != 2) {
            float f14 = Float.isNaN(keyPosition.k) ? f2 : keyPosition.k;
            float f15 = cVar2.l;
            float f16 = cVar.l;
            this.l = (f14 * (f15 - f16)) + f16;
            if (!Float.isNaN(keyPosition.l)) {
                f2 = keyPosition.l;
            }
            float f17 = cVar2.m;
            float f18 = cVar.m;
            this.m = (f2 * (f17 - f18)) + f18;
        } else {
            if (Float.isNaN(keyPosition.k)) {
                float f19 = cVar2.l;
                float f20 = cVar.l;
                min = ((f19 - f20) * f2) + f20;
            } else {
                min = Math.min(f4, f3) * keyPosition.k;
            }
            this.l = min;
            if (Float.isNaN(keyPosition.l)) {
                float f21 = cVar2.m;
                float f22 = cVar.m;
                f = (f2 * (f21 - f22)) + f22;
            } else {
                f = keyPosition.l;
            }
            this.m = f;
        }
        this.r = cVar.r;
        this.h = Easing.getInterpolator(keyPosition.f);
        this.q = keyPosition.g;
    }

    public void o(int i, int i2, KeyPosition keyPosition, c cVar, c cVar2) {
        float f = keyPosition.f939a / 100.0f;
        this.j = f;
        this.i = keyPosition.h;
        float f2 = Float.isNaN(keyPosition.i) ? f : keyPosition.i;
        float f3 = Float.isNaN(keyPosition.j) ? f : keyPosition.j;
        float f4 = cVar2.n;
        float f5 = cVar.n;
        float f6 = cVar2.o;
        float f7 = cVar.o;
        this.k = this.j;
        float f8 = cVar.l;
        float f9 = cVar.m;
        float f10 = cVar2.l + (f4 / 2.0f);
        float f11 = cVar2.m + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.l = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.m = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.n = (int) (f5 + f12);
        this.o = (int) (f7 + f13);
        this.v = 2;
        if (!Float.isNaN(keyPosition.k)) {
            this.l = (int) (keyPosition.k * ((int) (i - this.n)));
        }
        if (!Float.isNaN(keyPosition.l)) {
            this.m = (int) (keyPosition.l * ((int) (i2 - this.o)));
        }
        this.r = this.r;
        this.h = Easing.getInterpolator(keyPosition.f);
        this.q = keyPosition.g;
    }

    public void p(float f, float f2, float f3, float f4) {
        this.l = f;
        this.m = f2;
        this.n = f3;
        this.o = f4;
    }

    public void q(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f3 = f7;
            } else if (i2 == 2) {
                f5 = f7;
            } else if (i2 == 3) {
                f4 = f7;
            } else if (i2 == 4) {
                f6 = f7;
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        fArr[0] = (f8 * (1.0f - f)) + (((f4 * 1.0f) + f8) * f) + 0.0f;
        fArr[1] = (f9 * (1.0f - f2)) + (((f6 * 1.0f) + f9) * f2) + 0.0f;
    }

    public void r(float f, View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3, boolean z) {
        float f2;
        boolean z2;
        boolean z3;
        float f3;
        float f4 = this.l;
        float f5 = this.m;
        float f6 = this.n;
        float f7 = this.o;
        if (iArr.length != 0 && this.w.length <= iArr[iArr.length - 1]) {
            int i = iArr[iArr.length - 1] + 1;
            this.w = new double[i];
            this.x = new double[i];
        }
        Arrays.fill(this.w, Double.NaN);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.w[iArr[i2]] = dArr[i2];
            this.x[iArr[i2]] = dArr2[i2];
        }
        float f8 = Float.NaN;
        int i3 = 0;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        while (true) {
            double[] dArr4 = this.w;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f3 = f8;
            } else {
                double d = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.w[i3])) {
                    d = this.w[i3] + d;
                }
                f3 = f8;
                float f13 = (float) d;
                float f14 = (float) this.x[i3];
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
        MotionController motionController = this.t;
        if (motionController != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motionController.getCenter(f, fArr, fArr2);
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
                z2 = false;
                dArr2[0] = sin2;
                z3 = true;
                dArr2[1] = cos2;
            } else {
                z2 = false;
                z3 = true;
            }
            if (!Float.isNaN(f15)) {
                view.setRotation((float) (f15 + Math.toDegrees(Math.atan2(cos2, sin2))));
            }
            f4 = sin;
            f5 = cos;
        } else {
            f2 = f7;
            z2 = false;
            z3 = true;
            if (!Float.isNaN(f15)) {
                view.setRotation((float) (0.0f + f15 + Math.toDegrees(Math.atan2(f10 + (f12 / 2.0f), f9 + (f11 / 2.0f)))));
            }
        }
        if (view instanceof FloatLayout) {
            ((FloatLayout) view).layout(f4, f5, f6 + f4, f5 + f2);
            return;
        }
        float f20 = f4 + 0.5f;
        int i4 = (int) f20;
        float f21 = f5 + 0.5f;
        int i5 = (int) f21;
        int i6 = (int) (f20 + f6);
        int i7 = (int) (f21 + f2);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if (i8 != view.getMeasuredWidth() || i9 != view.getMeasuredHeight()) {
            z2 = z3;
        }
        if (z2 || z) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(i9, 1073741824));
        }
        view.layout(i4, i5, i6, i7);
    }

    public void s(MotionController motionController, c cVar) {
        double d = ((this.l + (this.n / 2.0f)) - cVar.l) - (cVar.n / 2.0f);
        double d2 = ((this.m + (this.o / 2.0f)) - cVar.m) - (cVar.o / 2.0f);
        this.t = motionController;
        this.l = (float) Math.hypot(d2, d);
        if (Float.isNaN(this.s)) {
            this.m = (float) (Math.atan2(d2, d) + 1.5707963267948966d);
        } else {
            this.m = (float) Math.toRadians(this.s);
        }
    }

    public c(int i, int i2, KeyPosition keyPosition, c cVar, c cVar2) {
        int i3 = Key.UNSET;
        this.q = i3;
        this.r = i3;
        this.s = Float.NaN;
        this.t = null;
        this.u = new LinkedHashMap<>();
        this.v = 0;
        this.w = new double[18];
        this.x = new double[18];
        if (cVar.r != Key.UNSET) {
            n(i, i2, keyPosition, cVar, cVar2);
            return;
        }
        int i4 = keyPosition.o;
        if (i4 == 1) {
            m(keyPosition, cVar, cVar2);
        } else if (i4 != 2) {
            l(keyPosition, cVar, cVar2);
        } else {
            o(i, i2, keyPosition, cVar, cVar2);
        }
    }
}
