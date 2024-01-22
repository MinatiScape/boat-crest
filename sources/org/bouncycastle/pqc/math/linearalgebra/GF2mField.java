package org.bouncycastle.pqc.math.linearalgebra;

import com.jstyle.blesdk1860.constant.BleConst;
import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class GF2mField {

    /* renamed from: a  reason: collision with root package name */
    public int f15366a;
    public int b;

    public GF2mField(int i) {
        this.f15366a = 0;
        if (i >= 32) {
            throw new IllegalArgumentException(" Error: the degree of field is too large ");
        }
        if (i < 1) {
            throw new IllegalArgumentException(" Error: the degree of field is non-positive ");
        }
        this.f15366a = i;
        this.b = PolynomialRingGF2.getIrreduciblePolynomial(i);
    }

    public GF2mField(int i, int i2) {
        this.f15366a = 0;
        if (i != PolynomialRingGF2.degree(i2)) {
            throw new IllegalArgumentException(" Error: the degree is not correct");
        }
        if (!PolynomialRingGF2.isIrreducible(i2)) {
            throw new IllegalArgumentException(" Error: given polynomial is reducible");
        }
        this.f15366a = i;
        this.b = i2;
    }

    public GF2mField(GF2mField gF2mField) {
        this.f15366a = 0;
        this.f15366a = gF2mField.f15366a;
        this.b = gF2mField.b;
    }

    public GF2mField(byte[] bArr) {
        this.f15366a = 0;
        if (bArr.length != 4) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        int OS2IP = LittleEndianConversions.OS2IP(bArr);
        this.b = OS2IP;
        if (!PolynomialRingGF2.isIrreducible(OS2IP)) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        this.f15366a = PolynomialRingGF2.degree(this.b);
    }

    public static String a(int i) {
        if (i == 0) {
            return BleConst.GetDeviceTime;
        }
        String str = ((byte) (i & 1)) == 1 ? "1" : "";
        int i2 = i >>> 1;
        int i3 = 1;
        while (i2 != 0) {
            if (((byte) (i2 & 1)) == 1) {
                str = str + "+x^" + i3;
            }
            i2 >>>= 1;
            i3++;
        }
        return str;
    }

    public int add(int i, int i2) {
        return i ^ i2;
    }

    public String elementToStr(int i) {
        StringBuilder sb;
        String str;
        String str2 = "";
        for (int i2 = 0; i2 < this.f15366a; i2++) {
            if ((((byte) i) & 1) == 0) {
                sb = new StringBuilder();
                str = BleConst.GetDeviceTime;
            } else {
                sb = new StringBuilder();
                str = "1";
            }
            sb.append(str);
            sb.append(str2);
            str2 = sb.toString();
            i >>>= 1;
        }
        return str2;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GF2mField)) {
            GF2mField gF2mField = (GF2mField) obj;
            if (this.f15366a == gF2mField.f15366a && this.b == gF2mField.b) {
                return true;
            }
        }
        return false;
    }

    public int exp(int i, int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i2 < 0) {
            i = inverse(i);
            i2 = -i2;
        }
        int i3 = 1;
        while (i2 != 0) {
            if ((i2 & 1) == 1) {
                i3 = mult(i3, i);
            }
            i = mult(i, i);
            i2 >>>= 1;
        }
        return i3;
    }

    public int getDegree() {
        return this.f15366a;
    }

    public byte[] getEncoded() {
        return LittleEndianConversions.I2OSP(this.b);
    }

    public int getPolynomial() {
        return this.b;
    }

    public int getRandomElement(SecureRandom secureRandom) {
        return RandUtils.a(secureRandom, 1 << this.f15366a);
    }

    public int getRandomNonZeroElement() {
        return getRandomNonZeroElement(new SecureRandom());
    }

    public int getRandomNonZeroElement(SecureRandom secureRandom) {
        int a2 = RandUtils.a(secureRandom, 1 << this.f15366a);
        int i = 0;
        while (a2 == 0 && i < 1048576) {
            a2 = RandUtils.a(secureRandom, 1 << this.f15366a);
            i++;
        }
        if (i == 1048576) {
            return 1;
        }
        return a2;
    }

    public int hashCode() {
        return this.b;
    }

    public int inverse(int i) {
        return exp(i, (1 << this.f15366a) - 2);
    }

    public boolean isElementOfThisField(int i) {
        int i2 = this.f15366a;
        return i2 == 31 ? i >= 0 : i >= 0 && i < (1 << i2);
    }

    public int mult(int i, int i2) {
        return PolynomialRingGF2.modMultiply(i, i2, this.b);
    }

    public int sqRoot(int i) {
        for (int i2 = 1; i2 < this.f15366a; i2++) {
            i = mult(i, i);
        }
        return i;
    }

    public String toString() {
        return "Finite Field GF(2^" + this.f15366a + ") = GF(2)[X]/<" + a(this.b) + "> ";
    }
}
