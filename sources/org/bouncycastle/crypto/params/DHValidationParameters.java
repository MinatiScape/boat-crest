package org.bouncycastle.crypto.params;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class DHValidationParameters {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14795a;
    public int b;

    public DHValidationParameters(byte[] bArr, int i) {
        this.f14795a = bArr;
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DHValidationParameters) {
            DHValidationParameters dHValidationParameters = (DHValidationParameters) obj;
            if (dHValidationParameters.b != this.b) {
                return false;
            }
            return Arrays.areEqual(this.f14795a, dHValidationParameters.f14795a);
        }
        return false;
    }

    public int getCounter() {
        return this.b;
    }

    public byte[] getSeed() {
        return this.f14795a;
    }

    public int hashCode() {
        return this.b ^ Arrays.hashCode(this.f14795a);
    }
}
