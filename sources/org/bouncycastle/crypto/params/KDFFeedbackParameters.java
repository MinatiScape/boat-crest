package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public final class KDFFeedbackParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14804a;
    public final byte[] b;
    public final boolean c;
    public final int d;
    public final byte[] e;

    public KDFFeedbackParameters(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f14804a = Arrays.clone(bArr);
        if (bArr3 == null) {
            this.e = new byte[0];
        } else {
            this.e = Arrays.clone(bArr3);
        }
        this.d = i;
        if (bArr2 == null) {
            this.b = new byte[0];
        } else {
            this.b = Arrays.clone(bArr2);
        }
        this.c = z;
    }

    public static KDFFeedbackParameters createWithCounter(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (i == 8 || i == 16 || i == 24 || i == 32) {
            return new KDFFeedbackParameters(bArr, bArr2, bArr3, i, true);
        }
        throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
    }

    public static KDFFeedbackParameters createWithoutCounter(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return new KDFFeedbackParameters(bArr, bArr2, bArr3, -1, false);
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.e);
    }

    public byte[] getIV() {
        return this.b;
    }

    public byte[] getKI() {
        return this.f14804a;
    }

    public int getR() {
        return this.d;
    }

    public boolean useCounter() {
        return this.c;
    }
}
