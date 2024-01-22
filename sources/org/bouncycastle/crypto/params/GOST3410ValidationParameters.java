package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class GOST3410ValidationParameters {

    /* renamed from: a  reason: collision with root package name */
    public int f14799a;
    public int b;
    public long c;
    public long d;

    public GOST3410ValidationParameters(int i, int i2) {
        this.f14799a = i;
        this.b = i2;
    }

    public GOST3410ValidationParameters(long j, long j2) {
        this.c = j;
        this.d = j2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410ValidationParameters) {
            GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
            return gOST3410ValidationParameters.b == this.b && gOST3410ValidationParameters.f14799a == this.f14799a && gOST3410ValidationParameters.d == this.d && gOST3410ValidationParameters.c == this.c;
        }
        return false;
    }

    public int getC() {
        return this.b;
    }

    public long getCL() {
        return this.d;
    }

    public int getX0() {
        return this.f14799a;
    }

    public long getX0L() {
        return this.c;
    }

    public int hashCode() {
        int i = this.f14799a ^ this.b;
        long j = this.c;
        int i2 = (i ^ ((int) j)) ^ ((int) (j >> 32));
        long j2 = this.d;
        return (i2 ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
