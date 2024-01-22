package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class Layer {

    /* renamed from: a  reason: collision with root package name */
    public int f15312a;
    public int b;
    public int c;
    public short[][][] d;
    public short[][][] e;
    public short[][] f;
    public short[] g;

    public Layer(byte b, byte b2, short[][][] sArr, short[][][] sArr2, short[][] sArr3, short[] sArr4) {
        int i = b & 255;
        this.f15312a = i;
        int i2 = b2 & 255;
        this.b = i2;
        this.c = i2 - i;
        this.d = sArr;
        this.e = sArr2;
        this.f = sArr3;
        this.g = sArr4;
    }

    public Layer(int i, int i2, SecureRandom secureRandom) {
        this.f15312a = i;
        this.b = i2;
        int i3 = i2 - i;
        this.c = i3;
        this.d = (short[][][]) Array.newInstance(short.class, i3, i3, i);
        int i4 = this.c;
        int i5 = this.f15312a;
        this.e = (short[][][]) Array.newInstance(short.class, i4, i5, i5);
        this.f = (short[][]) Array.newInstance(short.class, this.c, this.b);
        int i6 = this.c;
        this.g = new short[i6];
        for (int i7 = 0; i7 < i6; i7++) {
            for (int i8 = 0; i8 < this.c; i8++) {
                for (int i9 = 0; i9 < this.f15312a; i9++) {
                    this.d[i7][i8][i9] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int i10 = 0; i10 < i6; i10++) {
            for (int i11 = 0; i11 < this.f15312a; i11++) {
                for (int i12 = 0; i12 < this.f15312a; i12++) {
                    this.e[i10][i11][i12] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int i13 = 0; i13 < i6; i13++) {
            for (int i14 = 0; i14 < this.b; i14++) {
                this.f[i13][i14] = (short) (secureRandom.nextInt() & 255);
            }
        }
        for (int i15 = 0; i15 < i6; i15++) {
            this.g[i15] = (short) (secureRandom.nextInt() & 255);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Layer)) {
            return false;
        }
        Layer layer = (Layer) obj;
        return this.f15312a == layer.getVi() && this.b == layer.getViNext() && this.c == layer.getOi() && RainbowUtil.equals(this.d, layer.getCoeffAlpha()) && RainbowUtil.equals(this.e, layer.getCoeffBeta()) && RainbowUtil.equals(this.f, layer.getCoeffGamma()) && RainbowUtil.equals(this.g, layer.getCoeffEta());
    }

    public short[][][] getCoeffAlpha() {
        return this.d;
    }

    public short[][][] getCoeffBeta() {
        return this.e;
    }

    public short[] getCoeffEta() {
        return this.g;
    }

    public short[][] getCoeffGamma() {
        return this.f;
    }

    public int getOi() {
        return this.c;
    }

    public int getVi() {
        return this.f15312a;
    }

    public int getViNext() {
        return this.b;
    }

    public int hashCode() {
        return (((((((((((this.f15312a * 37) + this.b) * 37) + this.c) * 37) + Arrays.hashCode(this.d)) * 37) + Arrays.hashCode(this.e)) * 37) + Arrays.hashCode(this.f)) * 37) + Arrays.hashCode(this.g);
    }

    public short[][] plugInVinegars(short[] sArr) {
        int i = this.c;
        int i2 = 0;
        short[][] sArr2 = (short[][]) Array.newInstance(short.class, i, i + 1);
        short[] sArr3 = new short[this.c];
        for (int i3 = 0; i3 < this.c; i3++) {
            for (int i4 = 0; i4 < this.f15312a; i4++) {
                for (int i5 = 0; i5 < this.f15312a; i5++) {
                    sArr3[i3] = GF2Field.addElem(sArr3[i3], GF2Field.multElem(GF2Field.multElem(this.e[i3][i4][i5], sArr[i4]), sArr[i5]));
                }
            }
        }
        for (int i6 = 0; i6 < this.c; i6++) {
            for (int i7 = 0; i7 < this.c; i7++) {
                for (int i8 = 0; i8 < this.f15312a; i8++) {
                    sArr2[i6][i7] = GF2Field.addElem(sArr2[i6][i7], GF2Field.multElem(this.d[i6][i7][i8], sArr[i8]));
                }
            }
        }
        for (int i9 = 0; i9 < this.c; i9++) {
            for (int i10 = 0; i10 < this.f15312a; i10++) {
                sArr3[i9] = GF2Field.addElem(sArr3[i9], GF2Field.multElem(this.f[i9][i10], sArr[i10]));
            }
        }
        for (int i11 = 0; i11 < this.c; i11++) {
            for (int i12 = this.f15312a; i12 < this.b; i12++) {
                short[] sArr4 = sArr2[i11];
                int i13 = this.f15312a;
                sArr4[i12 - i13] = GF2Field.addElem(this.f[i11][i12], sArr2[i11][i12 - i13]);
            }
        }
        for (int i14 = 0; i14 < this.c; i14++) {
            sArr3[i14] = GF2Field.addElem(sArr3[i14], this.g[i14]);
        }
        while (true) {
            int i15 = this.c;
            if (i2 >= i15) {
                return sArr2;
            }
            sArr2[i2][i15] = sArr3[i2];
            i2++;
        }
    }
}
