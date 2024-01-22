package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public final class KDFDoublePipelineIterationParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14803a;
    public final boolean b;
    public final int c;
    public final byte[] d;

    public KDFDoublePipelineIterationParameters(byte[] bArr, byte[] bArr2, int i, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f14803a = Arrays.clone(bArr);
        if (bArr2 == null) {
            this.d = new byte[0];
        } else {
            this.d = Arrays.clone(bArr2);
        }
        if (i != 8 && i != 16 && i != 24 && i != 32) {
            throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
        }
        this.c = i;
        this.b = z;
    }

    public static KDFDoublePipelineIterationParameters createWithCounter(byte[] bArr, byte[] bArr2, int i) {
        return new KDFDoublePipelineIterationParameters(bArr, bArr2, i, true);
    }

    public static KDFDoublePipelineIterationParameters createWithoutCounter(byte[] bArr, byte[] bArr2) {
        return new KDFDoublePipelineIterationParameters(bArr, bArr2, 32, false);
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.d);
    }

    public byte[] getKI() {
        return this.f14803a;
    }

    public int getR() {
        return this.c;
    }

    public boolean useCounter() {
        return this.b;
    }
}
