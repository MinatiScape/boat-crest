package com.github.mikephil.charting.data.filter;

import android.annotation.TargetApi;
import java.util.Arrays;
/* loaded from: classes9.dex */
public class Approximator {

    /* loaded from: classes9.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public float[] f7944a;
        public float b;
        public float c;
        public float d;
        public float e;
        public float f;

        public a(Approximator approximator, float f, float f2, float f3, float f4) {
            float f5 = f - f3;
            this.d = f5;
            float f6 = f2 - f4;
            this.e = f6;
            this.b = f * f4;
            this.c = f3 * f2;
            this.f = (float) Math.sqrt((f5 * f5) + (f6 * f6));
            this.f7944a = new float[]{f, f2, f3, f4};
        }

        public float a(float f, float f2) {
            return Math.abs((((this.e * f) - (this.d * f2)) + this.b) - this.c) / this.f;
        }

        public float[] b() {
            return this.f7944a;
        }
    }

    public float[] a(float[]... fArr) {
        int i = 0;
        for (float[] fArr2 : fArr) {
            i += fArr2.length;
        }
        float[] fArr3 = new float[i];
        int i2 = 0;
        for (float[] fArr4 : fArr) {
            for (float f : fArr4) {
                fArr3[i2] = f;
                i2++;
            }
        }
        return fArr3;
    }

    @TargetApi(9)
    public float[] reduceWithDouglasPeucker(float[] fArr, float f) {
        a aVar = new a(this, fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1]);
        float f2 = 0.0f;
        int i = 0;
        for (int i2 = 2; i2 < fArr.length - 2; i2 += 2) {
            float a2 = aVar.a(fArr[i2], fArr[i2 + 1]);
            if (a2 > f2) {
                i = i2;
                f2 = a2;
            }
        }
        if (f2 > f) {
            float[] reduceWithDouglasPeucker = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, 0, i + 2), f);
            float[] reduceWithDouglasPeucker2 = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, i, fArr.length), f);
            return a(reduceWithDouglasPeucker, Arrays.copyOfRange(reduceWithDouglasPeucker2, 2, reduceWithDouglasPeucker2.length));
        }
        return aVar.b();
    }
}
