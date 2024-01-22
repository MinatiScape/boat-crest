package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class HKDFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14800a;
    public final boolean b;
    public final byte[] c;
    public final byte[] d;

    public HKDFParameters(byte[] bArr, boolean z, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            throw new IllegalArgumentException("IKM (input keying material) should not be null");
        }
        this.f14800a = Arrays.clone(bArr);
        this.b = z;
        if (bArr2 == null || bArr2.length == 0) {
            this.c = null;
        } else {
            this.c = Arrays.clone(bArr2);
        }
        if (bArr3 == null) {
            this.d = new byte[0];
        } else {
            this.d = Arrays.clone(bArr3);
        }
    }

    public HKDFParameters(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this(bArr, false, bArr2, bArr3);
    }

    public static HKDFParameters defaultParameters(byte[] bArr) {
        return new HKDFParameters(bArr, false, null, null);
    }

    public static HKDFParameters skipExtractParameters(byte[] bArr, byte[] bArr2) {
        return new HKDFParameters(bArr, true, null, bArr2);
    }

    public byte[] getIKM() {
        return Arrays.clone(this.f14800a);
    }

    public byte[] getInfo() {
        return Arrays.clone(this.d);
    }

    public byte[] getSalt() {
        return Arrays.clone(this.c);
    }

    public boolean skipExtract() {
        return this.b;
    }
}
