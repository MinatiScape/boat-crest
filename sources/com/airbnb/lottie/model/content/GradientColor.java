package com.airbnb.lottie.model.content;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.Arrays;
/* loaded from: classes.dex */
public class GradientColor {

    /* renamed from: a  reason: collision with root package name */
    public final float[] f2036a;
    public final int[] b;

    public GradientColor(float[] fArr, int[] iArr) {
        this.f2036a = fArr;
        this.b = iArr;
    }

    public final int a(float f) {
        int binarySearch = Arrays.binarySearch(this.f2036a, f);
        if (binarySearch >= 0) {
            return this.b[binarySearch];
        }
        int i = -(binarySearch + 1);
        if (i == 0) {
            return this.b[0];
        }
        int[] iArr = this.b;
        if (i == iArr.length - 1) {
            return iArr[iArr.length - 1];
        }
        float[] fArr = this.f2036a;
        int i2 = i - 1;
        float f2 = fArr[i2];
        return GammaEvaluator.evaluate((f - f2) / (fArr[i] - f2), iArr[i2], iArr[i]);
    }

    public GradientColor copyWithPositions(float[] fArr) {
        int[] iArr = new int[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            iArr[i] = a(fArr[i]);
        }
        return new GradientColor(fArr, iArr);
    }

    public int[] getColors() {
        return this.b;
    }

    public float[] getPositions() {
        return this.f2036a;
    }

    public int getSize() {
        return this.b.length;
    }

    public void lerp(GradientColor gradientColor, GradientColor gradientColor2, float f) {
        if (gradientColor.b.length == gradientColor2.b.length) {
            for (int i = 0; i < gradientColor.b.length; i++) {
                this.f2036a[i] = MiscUtils.lerp(gradientColor.f2036a[i], gradientColor2.f2036a[i], f);
                this.b[i] = GammaEvaluator.evaluate(f, gradientColor.b[i], gradientColor2.b[i]);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + gradientColor.b.length + " vs " + gradientColor2.b.length + ")");
    }
}
