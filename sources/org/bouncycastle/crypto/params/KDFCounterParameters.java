package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public final class KDFCounterParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14802a;
    public byte[] b;
    public byte[] c;
    public int d;

    public KDFCounterParameters(byte[] bArr, byte[] bArr2, int i) {
        this(bArr, null, bArr2, i);
    }

    public KDFCounterParameters(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (bArr == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f14802a = Arrays.clone(bArr);
        if (bArr2 == null) {
            this.b = new byte[0];
        } else {
            this.b = Arrays.clone(bArr2);
        }
        if (bArr3 == null) {
            this.c = new byte[0];
        } else {
            this.c = Arrays.clone(bArr3);
        }
        if (i != 8 && i != 16 && i != 24 && i != 32) {
            throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
        }
        this.d = i;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.c);
    }

    public byte[] getFixedInputDataCounterPrefix() {
        return Arrays.clone(this.b);
    }

    public byte[] getFixedInputDataCounterSuffix() {
        return Arrays.clone(this.c);
    }

    public byte[] getKI() {
        return this.f14802a;
    }

    public int getR() {
        return this.d;
    }
}
