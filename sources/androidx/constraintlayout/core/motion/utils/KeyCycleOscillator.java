package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class KeyCycleOscillator {

    /* renamed from: a  reason: collision with root package name */
    public CurveFit f874a;
    public c b;
    public String c;
    public int d = 0;
    public String e = null;
    public int mVariesBy = 0;
    public ArrayList<d> f = new ArrayList<>();

    /* loaded from: classes.dex */
    public static class PathRotateSet extends KeyCycleOscillator {
        public String g;
        public int h;

        public PathRotateSet(String str) {
            this.g = str;
            this.h = TypedValues.CycleType.getId(str);
        }

        public void setPathRotate(MotionWidget motionWidget, float f, double d, double d2) {
            motionWidget.setRotationZ(get(f) + ((float) Math.toDegrees(Math.atan2(d2, d))));
        }

        @Override // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget, float f) {
            motionWidget.setValue(this.h, get(f));
        }
    }

    /* loaded from: classes.dex */
    public class a implements Comparator<d> {
        public a(KeyCycleOscillator keyCycleOscillator) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(d dVar, d dVar2) {
            return Integer.compare(dVar.f876a, dVar2.f876a);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends KeyCycleOscillator {
        public String g;
        public int h;

        public b(String str) {
            this.g = str;
            this.h = TypedValues.CycleType.getId(str);
        }

        @Override // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget, float f) {
            motionWidget.setValue(this.h, get(f));
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public Oscillator f875a;
        public float[] b;
        public double[] c;
        public float[] d;
        public float[] e;
        public float[] f;
        public CurveFit g;
        public double[] h;
        public double[] i;

        public c(int i, String str, int i2, int i3) {
            Oscillator oscillator = new Oscillator();
            this.f875a = oscillator;
            oscillator.setType(i, str);
            this.b = new float[i3];
            this.c = new double[i3];
            this.d = new float[i3];
            this.e = new float[i3];
            this.f = new float[i3];
            float[] fArr = new float[i3];
        }

        public double a(float f) {
            CurveFit curveFit = this.g;
            if (curveFit != null) {
                double d = f;
                curveFit.getSlope(d, this.i);
                this.g.getPos(d, this.h);
            } else {
                double[] dArr = this.i;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[2] = 0.0d;
            }
            double d2 = f;
            double value = this.f875a.getValue(d2, this.h[1]);
            double slope = this.f875a.getSlope(d2, this.h[1], this.i[1]);
            double[] dArr2 = this.i;
            return dArr2[0] + (value * dArr2[2]) + (slope * this.h[2]);
        }

        public double b(float f) {
            CurveFit curveFit = this.g;
            if (curveFit != null) {
                curveFit.getPos(f, this.h);
            } else {
                double[] dArr = this.h;
                dArr[0] = this.e[0];
                dArr[1] = this.f[0];
                dArr[2] = this.b[0];
            }
            double[] dArr2 = this.h;
            return dArr2[0] + (this.f875a.getValue(f, dArr2[1]) * this.h[2]);
        }

        public void c(int i, int i2, float f, float f2, float f3, float f4) {
            this.c[i] = i2 / 100.0d;
            this.d[i] = f;
            this.e[i] = f2;
            this.f[i] = f3;
            this.b[i] = f4;
        }

        public void d(float f) {
            double[][] dArr = (double[][]) Array.newInstance(double.class, this.c.length, 3);
            float[] fArr = this.b;
            this.h = new double[fArr.length + 2];
            this.i = new double[fArr.length + 2];
            if (this.c[0] > 0.0d) {
                this.f875a.addPoint(0.0d, this.d[0]);
            }
            double[] dArr2 = this.c;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f875a.addPoint(1.0d, this.d[length]);
            }
            for (int i = 0; i < dArr.length; i++) {
                dArr[i][0] = this.e[i];
                dArr[i][1] = this.f[i];
                dArr[i][2] = this.b[i];
                this.f875a.addPoint(this.c[i], this.d[i]);
            }
            this.f875a.normalize();
            double[] dArr3 = this.c;
            if (dArr3.length > 1) {
                this.g = CurveFit.get(0, dArr3, dArr);
            } else {
                this.g = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public int f876a;
        public float b;
        public float c;
        public float d;
        public float e;

        public d(int i, float f, float f2, float f3, float f4) {
            this.f876a = i;
            this.b = f4;
            this.c = f2;
            this.d = f;
            this.e = f3;
        }
    }

    public static KeyCycleOscillator makeWidgetCycle(String str) {
        if (str.equals("pathRotate")) {
            return new PathRotateSet(str);
        }
        return new b(str);
    }

    public float get(float f) {
        return (float) this.b.b(f);
    }

    public CurveFit getCurveFit() {
        return this.f874a;
    }

    public float getSlope(float f) {
        return (float) this.b.a(f);
    }

    public void setCustom(Object obj) {
    }

    public void setPoint(int i, int i2, String str, int i3, float f, float f2, float f3, float f4, Object obj) {
        this.f.add(new d(i, f, f2, f3, f4));
        if (i3 != -1) {
            this.mVariesBy = i3;
        }
        this.d = i2;
        setCustom(obj);
        this.e = str;
    }

    public void setProperty(MotionWidget motionWidget, float f) {
    }

    public void setType(String str) {
        this.c = str;
    }

    public void setup(float f) {
        int size = this.f.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f, new a(this));
        double[] dArr = new double[size];
        char c2 = 0;
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, 3);
        this.b = new c(this.d, this.e, this.mVariesBy, size);
        Iterator<d> it = this.f.iterator();
        int i = 0;
        while (it.hasNext()) {
            d next = it.next();
            float f2 = next.d;
            dArr[i] = f2 * 0.01d;
            double[] dArr3 = dArr2[i];
            float f3 = next.b;
            dArr3[c2] = f3;
            double[] dArr4 = dArr2[i];
            float f4 = next.c;
            dArr4[1] = f4;
            double[] dArr5 = dArr2[i];
            float f5 = next.e;
            dArr5[2] = f5;
            this.b.c(i, next.f876a, f2, f4, f5, f3);
            i++;
            c2 = 0;
        }
        this.b.d(f);
        this.f874a = CurveFit.get(0, dArr, dArr2);
    }

    public String toString() {
        String str = this.c;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<d> it = this.f.iterator();
        while (it.hasNext()) {
            d next = it.next();
            str = str + "[" + next.f876a + " , " + decimalFormat.format(next.b) + "] ";
        }
        return str;
    }

    public boolean variesByPath() {
        return this.mVariesBy == 1;
    }

    public void setPoint(int i, int i2, String str, int i3, float f, float f2, float f3, float f4) {
        this.f.add(new d(i, f, f2, f3, f4));
        if (i3 != -1) {
            this.mVariesBy = i3;
        }
        this.d = i2;
        this.e = str;
    }
}
