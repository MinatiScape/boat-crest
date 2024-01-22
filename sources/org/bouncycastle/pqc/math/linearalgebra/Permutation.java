package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class Permutation {

    /* renamed from: a  reason: collision with root package name */
    public int[] f15376a;

    public Permutation(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.f15376a = new int[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            this.f15376a[i2] = i2;
        }
    }

    public Permutation(int i, SecureRandom secureRandom) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.f15376a = new int[i];
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = i2;
        }
        int i3 = i;
        for (int i4 = 0; i4 < i; i4++) {
            int a2 = RandUtils.a(secureRandom, i3);
            i3--;
            this.f15376a[i4] = iArr[a2];
            iArr[a2] = iArr[i3];
        }
    }

    public Permutation(byte[] bArr) {
        if (bArr.length <= 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        int OS2IP = LittleEndianConversions.OS2IP(bArr, 0);
        int ceilLog256 = IntegerFunctions.ceilLog256(OS2IP - 1);
        if (bArr.length != (OS2IP * ceilLog256) + 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        this.f15376a = new int[OS2IP];
        for (int i = 0; i < OS2IP; i++) {
            this.f15376a[i] = LittleEndianConversions.OS2IP(bArr, (i * ceilLog256) + 4, ceilLog256);
        }
        if (!a(this.f15376a)) {
            throw new IllegalArgumentException("invalid encoding");
        }
    }

    public Permutation(int[] iArr) {
        if (!a(iArr)) {
            throw new IllegalArgumentException("array is not a permutation vector");
        }
        this.f15376a = IntUtils.clone(iArr);
    }

    public final boolean a(int[] iArr) {
        int length = iArr.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (iArr[i] < 0 || iArr[i] >= length || zArr[iArr[i]]) {
                return false;
            }
            zArr[iArr[i]] = true;
        }
        return true;
    }

    public Permutation computeInverse() {
        Permutation permutation = new Permutation(this.f15376a.length);
        for (int length = this.f15376a.length - 1; length >= 0; length--) {
            permutation.f15376a[this.f15376a[length]] = length;
        }
        return permutation;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Permutation) {
            return IntUtils.equals(this.f15376a, ((Permutation) obj).f15376a);
        }
        return false;
    }

    public byte[] getEncoded() {
        int length = this.f15376a.length;
        int ceilLog256 = IntegerFunctions.ceilLog256(length - 1);
        byte[] bArr = new byte[(length * ceilLog256) + 4];
        LittleEndianConversions.I2OSP(length, bArr, 0);
        for (int i = 0; i < length; i++) {
            LittleEndianConversions.I2OSP(this.f15376a[i], bArr, (i * ceilLog256) + 4, ceilLog256);
        }
        return bArr;
    }

    public int[] getVector() {
        return IntUtils.clone(this.f15376a);
    }

    public int hashCode() {
        return this.f15376a.hashCode();
    }

    public Permutation rightMultiply(Permutation permutation) {
        int length = permutation.f15376a.length;
        int[] iArr = this.f15376a;
        if (length == iArr.length) {
            Permutation permutation2 = new Permutation(iArr.length);
            for (int length2 = this.f15376a.length - 1; length2 >= 0; length2--) {
                permutation2.f15376a[length2] = this.f15376a[permutation.f15376a[length2]];
            }
            return permutation2;
        }
        throw new IllegalArgumentException("length mismatch");
    }

    public String toString() {
        String str = "[" + this.f15376a[0];
        for (int i = 1; i < this.f15376a.length; i++) {
            str = str + ", " + this.f15376a[i];
        }
        return str + "]";
    }
}
