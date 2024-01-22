package org.bouncycastle.crypto.params;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class DSAValidationParameters {

    /* renamed from: a  reason: collision with root package name */
    public int f14797a;
    public byte[] b;
    public int c;

    public DSAValidationParameters(byte[] bArr, int i) {
        this(bArr, i, -1);
    }

    public DSAValidationParameters(byte[] bArr, int i, int i2) {
        this.b = bArr;
        this.c = i;
        this.f14797a = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DSAValidationParameters) {
            DSAValidationParameters dSAValidationParameters = (DSAValidationParameters) obj;
            if (dSAValidationParameters.c != this.c) {
                return false;
            }
            return Arrays.areEqual(this.b, dSAValidationParameters.b);
        }
        return false;
    }

    public int getCounter() {
        return this.c;
    }

    public byte[] getSeed() {
        return this.b;
    }

    public int getUsageIndex() {
        return this.f14797a;
    }

    public int hashCode() {
        return this.c ^ Arrays.hashCode(this.b);
    }
}
