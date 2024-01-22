package org.bouncycastle.math.ec;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final BigInteger f15135a;
    public final int b;

    public b(BigInteger bigInteger, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        }
        this.f15135a = bigInteger;
        this.b = i;
    }

    public b a(b bVar) {
        c(bVar);
        return new b(this.f15135a.add(bVar.f15135a), this.b);
    }

    public b b(int i) {
        if (i >= 0) {
            int i2 = this.b;
            return i == i2 ? this : new b(this.f15135a.shiftLeft(i - i2), i);
        }
        throw new IllegalArgumentException("scale may not be negative");
    }

    public final void c(b bVar) {
        if (this.b != bVar.b) {
            throw new IllegalArgumentException("Only SimpleBigDecimal of same scale allowed in arithmetic operations");
        }
    }

    public int d(BigInteger bigInteger) {
        return this.f15135a.compareTo(bigInteger.shiftLeft(this.b));
    }

    public BigInteger e() {
        return this.f15135a.shiftRight(this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            return this.f15135a.equals(bVar.f15135a) && this.b == bVar.b;
        }
        return false;
    }

    public int f() {
        return this.b;
    }

    public b g() {
        return new b(this.f15135a.negate(), this.b);
    }

    public BigInteger h() {
        return a(new b(ECConstants.ONE, 1).b(this.b)).e();
    }

    public int hashCode() {
        return this.f15135a.hashCode() ^ this.b;
    }

    public b i(BigInteger bigInteger) {
        return new b(this.f15135a.subtract(bigInteger.shiftLeft(this.b)), this.b);
    }

    public b j(b bVar) {
        return a(bVar.g());
    }

    public String toString() {
        if (this.b == 0) {
            return this.f15135a.toString();
        }
        BigInteger e = e();
        BigInteger subtract = this.f15135a.subtract(e.shiftLeft(this.b));
        if (this.f15135a.signum() == -1) {
            subtract = ECConstants.ONE.shiftLeft(this.b).subtract(subtract);
        }
        if (e.signum() == -1 && !subtract.equals(ECConstants.ZERO)) {
            e = e.add(ECConstants.ONE);
        }
        String bigInteger = e.toString();
        char[] cArr = new char[this.b];
        String bigInteger2 = subtract.toString(2);
        int length = bigInteger2.length();
        int i = this.b - length;
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = '0';
        }
        for (int i3 = 0; i3 < length; i3++) {
            cArr[i + i3] = bigInteger2.charAt(i3);
        }
        String str = new String(cArr);
        StringBuffer stringBuffer = new StringBuffer(bigInteger);
        stringBuffer.append(".");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
