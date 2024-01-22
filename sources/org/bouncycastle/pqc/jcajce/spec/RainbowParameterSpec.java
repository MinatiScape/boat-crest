package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class RainbowParameterSpec implements AlgorithmParameterSpec {
    public static final int[] b = {6, 12, 17, 22, 33};

    /* renamed from: a  reason: collision with root package name */
    public int[] f15358a;

    public RainbowParameterSpec() {
        this.f15358a = b;
    }

    public RainbowParameterSpec(int[] iArr) {
        this.f15358a = iArr;
        a();
    }

    public final void a() {
        int[] iArr;
        int i;
        int[] iArr2 = this.f15358a;
        if (iArr2 == null) {
            throw new IllegalArgumentException("no layers defined.");
        }
        if (iArr2.length <= 1) {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
        int i2 = 0;
        do {
            iArr = this.f15358a;
            if (i2 >= iArr.length - 1) {
                return;
            }
            i = iArr[i2];
            i2++;
        } while (i < iArr[i2]);
        throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
    }

    public int getDocumentLength() {
        int[] iArr = this.f15358a;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int getNumOfLayers() {
        return this.f15358a.length - 1;
    }

    public int[] getVi() {
        return Arrays.clone(this.f15358a);
    }
}
