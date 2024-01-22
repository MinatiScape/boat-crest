package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.KeyFrameArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class SplineSet {

    /* renamed from: a  reason: collision with root package name */
    public int f883a;
    public String b;
    public CurveFit mCurveFit;
    public int[] mTimePoints = new int[10];
    public float[] mValues = new float[10];

    /* loaded from: classes.dex */
    public static class CustomSet extends SplineSet {
        public KeyFrameArray.CustomArray c;
        public float[] d;

        public CustomSet(String str, KeyFrameArray.CustomArray customArray) {
            String str2 = str.split(Constants.SEPARATOR_COMMA)[1];
            this.c = customArray;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int i, float f) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void setProperty(WidgetFrame widgetFrame, float f) {
            this.mCurveFit.getPos(f, this.d);
            widgetFrame.setCustomValue(this.c.valueAt(0), this.d);
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int i) {
            float[] fArr;
            int size = this.c.size();
            int numberOfInterpolatedValues = this.c.valueAt(0).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            this.d = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, numberOfInterpolatedValues);
            for (int i2 = 0; i2 < size; i2++) {
                int keyAt = this.c.keyAt(i2);
                CustomAttribute valueAt = this.c.valueAt(i2);
                dArr[i2] = keyAt * 0.01d;
                valueAt.getValuesToInterpolate(this.d);
                int i3 = 0;
                while (true) {
                    if (i3 < this.d.length) {
                        dArr2[i2][i3] = fArr[i3];
                        i3++;
                    }
                }
            }
            this.mCurveFit = CurveFit.get(i, dArr, dArr2);
        }

        public void setPoint(int i, CustomAttribute customAttribute) {
            this.c.append(i, customAttribute);
        }
    }

    /* loaded from: classes.dex */
    public static class CustomSpline extends SplineSet {
        public KeyFrameArray.CustomVar c;
        public float[] d;

        public CustomSpline(String str, KeyFrameArray.CustomVar customVar) {
            String str2 = str.split(Constants.SEPARATOR_COMMA)[1];
            this.c = customVar;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int i, float f) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setProperty(TypedValues typedValues, float f) {
            setProperty((MotionWidget) typedValues, f);
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int i) {
            float[] fArr;
            int size = this.c.size();
            int numberOfInterpolatedValues = this.c.valueAt(0).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            this.d = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, numberOfInterpolatedValues);
            for (int i2 = 0; i2 < size; i2++) {
                int keyAt = this.c.keyAt(i2);
                CustomVariable valueAt = this.c.valueAt(i2);
                dArr[i2] = keyAt * 0.01d;
                valueAt.getValuesToInterpolate(this.d);
                int i3 = 0;
                while (true) {
                    if (i3 < this.d.length) {
                        dArr2[i2][i3] = fArr[i3];
                        i3++;
                    }
                }
            }
            this.mCurveFit = CurveFit.get(i, dArr, dArr2);
        }

        public void setPoint(int i, CustomVariable customVariable) {
            this.c.append(i, customVariable);
        }

        public void setProperty(MotionWidget motionWidget, float f) {
            this.mCurveFit.getPos(f, this.d);
            this.c.valueAt(0).setInterpolatedValue(motionWidget, this.d);
        }
    }

    /* loaded from: classes.dex */
    public static class a extends SplineSet {
        public String c;

        public a(String str, long j) {
            this.c = str;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setProperty(TypedValues typedValues, float f) {
            typedValues.setValue(typedValues.getId(this.c), get(f));
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public static void a(int[] iArr, float[] fArr, int i, int i2) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i2;
            iArr2[1] = i;
            int i3 = 2;
            while (i3 > 0) {
                int i4 = i3 - 1;
                int i5 = iArr2[i4];
                i3 = i4 - 1;
                int i6 = iArr2[i3];
                if (i5 < i6) {
                    int b = b(iArr, fArr, i5, i6);
                    int i7 = i3 + 1;
                    iArr2[i3] = b - 1;
                    int i8 = i7 + 1;
                    iArr2[i7] = i5;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    i3 = i9 + 1;
                    iArr2[i9] = b + 1;
                }
            }
        }

        public static int b(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i2];
            int i4 = i;
            while (i < i2) {
                if (iArr[i] <= i3) {
                    c(iArr, fArr, i4, i);
                    i4++;
                }
                i++;
            }
            c(iArr, fArr, i4, i2);
            return i4;
        }

        public static void c(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
            float f = fArr[i];
            fArr[i] = fArr[i2];
            fArr[i2] = f;
        }
    }

    public static SplineSet makeCustomSpline(String str, KeyFrameArray.CustomArray customArray) {
        return new CustomSet(str, customArray);
    }

    public static SplineSet makeCustomSplineSet(String str, KeyFrameArray.CustomVar customVar) {
        return new CustomSpline(str, customVar);
    }

    public static SplineSet makeSpline(String str, long j) {
        return new a(str, j);
    }

    public float get(float f) {
        return (float) this.mCurveFit.getPos(f, 0);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public float getSlope(float f) {
        return (float) this.mCurveFit.getSlope(f, 0);
    }

    public void setPoint(int i, float f) {
        int[] iArr = this.mTimePoints;
        if (iArr.length < this.f883a + 1) {
            this.mTimePoints = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.mValues;
            this.mValues = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.mTimePoints;
        int i2 = this.f883a;
        iArr2[i2] = i;
        this.mValues[i2] = f;
        this.f883a = i2 + 1;
    }

    public void setProperty(TypedValues typedValues, float f) {
        typedValues.setValue(TypedValues.AttributesType.getId(this.b), get(f));
    }

    public void setType(String str) {
        this.b = str;
    }

    public void setup(int i) {
        int i2;
        int i3 = this.f883a;
        if (i3 == 0) {
            return;
        }
        b.a(this.mTimePoints, this.mValues, 0, i3 - 1);
        int i4 = 1;
        for (int i5 = 1; i5 < this.f883a; i5++) {
            int[] iArr = this.mTimePoints;
            if (iArr[i5 - 1] != iArr[i5]) {
                i4++;
            }
        }
        double[] dArr = new double[i4];
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, i4, 1);
        int i6 = 0;
        for (i2 = 0; i2 < this.f883a; i2 = i2 + 1) {
            if (i2 > 0) {
                int[] iArr2 = this.mTimePoints;
                i2 = iArr2[i2] == iArr2[i2 - 1] ? i2 + 1 : 0;
            }
            dArr[i6] = this.mTimePoints[i2] * 0.01d;
            dArr2[i6][0] = this.mValues[i2];
            i6++;
        }
        this.mCurveFit = CurveFit.get(i, dArr, dArr2);
    }

    public String toString() {
        String str = this.b;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i = 0; i < this.f883a; i++) {
            str = str + "[" + this.mTimePoints[i] + " , " + decimalFormat.format(this.mValues[i]) + "] ";
        }
        return str;
    }
}
