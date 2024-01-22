package org.bouncycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class IESParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f15123a;
    public byte[] b;
    public int c;
    public int d;
    public byte[] e;
    public boolean f;

    public IESParameterSpec(byte[] bArr, byte[] bArr2, int i) {
        this(bArr, bArr2, i, -1, null, false);
    }

    public IESParameterSpec(byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3) {
        this(bArr, bArr2, i, i2, bArr3, false);
    }

    public IESParameterSpec(byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3, boolean z) {
        if (bArr != null) {
            byte[] bArr4 = new byte[bArr.length];
            this.f15123a = bArr4;
            System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        } else {
            this.f15123a = null;
        }
        if (bArr2 != null) {
            byte[] bArr5 = new byte[bArr2.length];
            this.b = bArr5;
            System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
        } else {
            this.b = null;
        }
        this.c = i;
        this.d = i2;
        this.e = Arrays.clone(bArr3);
        this.f = z;
    }

    public int getCipherKeySize() {
        return this.d;
    }

    public byte[] getDerivationV() {
        return Arrays.clone(this.f15123a);
    }

    public byte[] getEncodingV() {
        return Arrays.clone(this.b);
    }

    public int getMacKeySize() {
        return this.c;
    }

    public byte[] getNonce() {
        return Arrays.clone(this.e);
    }

    public boolean getPointCompression() {
        return this.f;
    }

    public void setPointCompression(boolean z) {
        this.f = z;
    }
}
