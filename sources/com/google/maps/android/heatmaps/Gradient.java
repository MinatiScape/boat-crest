package com.google.maps.android.heatmaps;

import android.graphics.Color;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class Gradient {
    public final int mColorMapSize;
    public int[] mColors;
    public float[] mStartPoints;

    /* loaded from: classes10.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f11561a;
        public final int b;
        public final float c;

        public b(Gradient gradient, int i, int i2, float f) {
            this.f11561a = i;
            this.b = i2;
            this.c = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    public static int c(int i, int i2, float f) {
        int alpha = (int) (((Color.alpha(i2) - Color.alpha(i)) * f) + Color.alpha(i));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f) + fArr[i3];
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    public final HashMap<Integer, b> a() {
        HashMap<Integer, b> hashMap = new HashMap<>();
        if (this.mStartPoints[0] != 0.0f) {
            hashMap.put(0, new b(Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0])), this.mColors[0], this.mColorMapSize * this.mStartPoints[0]));
        }
        for (int i = 1; i < this.mColors.length; i++) {
            int i2 = i - 1;
            Integer valueOf = Integer.valueOf((int) (this.mColorMapSize * this.mStartPoints[i2]));
            int[] iArr = this.mColors;
            int i3 = iArr[i2];
            int i4 = iArr[i];
            float[] fArr = this.mStartPoints;
            hashMap.put(valueOf, new b(i3, i4, (fArr[i] - fArr[i2]) * this.mColorMapSize));
        }
        float[] fArr2 = this.mStartPoints;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer valueOf2 = Integer.valueOf((int) (this.mColorMapSize * fArr2[length]));
            int[] iArr2 = this.mColors;
            hashMap.put(valueOf2, new b(iArr2[length], iArr2[length], this.mColorMapSize * (1.0f - this.mStartPoints[length])));
        }
        return hashMap;
    }

    public int[] b(double d) {
        HashMap<Integer, b> a2 = a();
        int[] iArr = new int[this.mColorMapSize];
        b bVar = a2.get(0);
        int i = 0;
        for (int i2 = 0; i2 < this.mColorMapSize; i2++) {
            if (a2.containsKey(Integer.valueOf(i2))) {
                bVar = a2.get(Integer.valueOf(i2));
                i = i2;
            }
            iArr[i2] = c(bVar.f11561a, bVar.b, (i2 - i) / bVar.c);
        }
        if (d != 1.0d) {
            for (int i3 = 0; i3 < this.mColorMapSize; i3++) {
                int i4 = iArr[i3];
                iArr[i3] = Color.argb((int) (Color.alpha(i4) * d), Color.red(i4), Color.green(i4), Color.blue(i4));
            }
        }
        return iArr;
    }

    public Gradient(int[] iArr, float[] fArr, int i) {
        if (iArr.length == fArr.length) {
            if (iArr.length != 0) {
                for (int i2 = 1; i2 < fArr.length; i2++) {
                    if (fArr[i2] <= fArr[i2 - 1]) {
                        throw new IllegalArgumentException("startPoints should be in increasing order");
                    }
                }
                this.mColorMapSize = i;
                int[] iArr2 = new int[iArr.length];
                this.mColors = iArr2;
                this.mStartPoints = new float[fArr.length];
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(fArr, 0, this.mStartPoints, 0, fArr.length);
                return;
            }
            throw new IllegalArgumentException("No colors have been defined");
        }
        throw new IllegalArgumentException("colors and startPoints should be same length");
    }
}
