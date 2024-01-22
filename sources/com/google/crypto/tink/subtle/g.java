package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.f;
import java.lang.reflect.Array;
import java.math.BigInteger;
/* loaded from: classes10.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public static final long[] f11058a;
    public static final long[] b;
    public static final long[] c;
    public static final f.a[][] d;
    public static final f.a[] e;
    public static final BigInteger f;
    public static final BigInteger g;
    public static final BigInteger h;
    public static final BigInteger i;

    /* loaded from: classes10.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public BigInteger f11059a;
        public BigInteger b;

        public b() {
        }
    }

    static {
        BigInteger subtract = BigInteger.valueOf(2L).pow(255).subtract(BigInteger.valueOf(19L));
        f = subtract;
        BigInteger mod = BigInteger.valueOf(-121665L).multiply(BigInteger.valueOf(121666L).modInverse(subtract)).mod(subtract);
        g = mod;
        BigInteger mod2 = BigInteger.valueOf(2L).multiply(mod).mod(subtract);
        h = mod2;
        BigInteger modPow = BigInteger.valueOf(2L).modPow(subtract.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4L)), subtract);
        i = modPow;
        b bVar = new b();
        bVar.b = BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(subtract)).mod(subtract);
        bVar.f11059a = c(bVar.b);
        f11058a = h.c(d(mod));
        b = h.c(d(mod2));
        c = h.c(d(modPow));
        d = (f.a[][]) Array.newInstance(f.a.class, 32, 8);
        b bVar2 = bVar;
        for (int i2 = 0; i2 < 32; i2++) {
            b bVar3 = bVar2;
            for (int i3 = 0; i3 < 8; i3++) {
                d[i2][i3] = b(bVar3);
                bVar3 = a(bVar3, bVar2);
            }
            for (int i4 = 0; i4 < 8; i4++) {
                bVar2 = a(bVar2, bVar2);
            }
        }
        b a2 = a(bVar, bVar);
        e = new f.a[8];
        for (int i5 = 0; i5 < 8; i5++) {
            e[i5] = b(bVar);
            bVar = a(bVar, a2);
        }
    }

    public static b a(b bVar, b bVar2) {
        b bVar3 = new b();
        BigInteger multiply = g.multiply(bVar.f11059a.multiply(bVar2.f11059a).multiply(bVar.b).multiply(bVar2.b));
        BigInteger bigInteger = f;
        BigInteger mod = multiply.mod(bigInteger);
        BigInteger add = bVar.f11059a.multiply(bVar2.b).add(bVar2.f11059a.multiply(bVar.b));
        BigInteger bigInteger2 = BigInteger.ONE;
        bVar3.f11059a = add.multiply(bigInteger2.add(mod).modInverse(bigInteger)).mod(bigInteger);
        bVar3.b = bVar.b.multiply(bVar2.b).add(bVar.f11059a.multiply(bVar2.f11059a)).multiply(bigInteger2.subtract(mod).modInverse(bigInteger)).mod(bigInteger);
        return bVar3;
    }

    public static f.a b(b bVar) {
        BigInteger add = bVar.b.add(bVar.f11059a);
        BigInteger bigInteger = f;
        return new f.a(h.c(d(add.mod(bigInteger))), h.c(d(bVar.b.subtract(bVar.f11059a).mod(bigInteger))), h.c(d(h.multiply(bVar.f11059a).multiply(bVar.b).mod(bigInteger))));
    }

    public static BigInteger c(BigInteger bigInteger) {
        BigInteger pow = bigInteger.pow(2);
        BigInteger bigInteger2 = BigInteger.ONE;
        BigInteger subtract = pow.subtract(bigInteger2);
        BigInteger add = g.multiply(bigInteger.pow(2)).add(bigInteger2);
        BigInteger bigInteger3 = f;
        BigInteger multiply = subtract.multiply(add.modInverse(bigInteger3));
        BigInteger modPow = multiply.modPow(bigInteger3.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), bigInteger3);
        if (!modPow.pow(2).subtract(multiply).mod(bigInteger3).equals(BigInteger.ZERO)) {
            modPow = modPow.multiply(i).mod(bigInteger3);
        }
        return modPow.testBit(0) ? bigInteger3.subtract(modPow) : modPow;
    }

    public static byte[] d(BigInteger bigInteger) {
        byte[] bArr = new byte[32];
        byte[] byteArray = bigInteger.toByteArray();
        System.arraycopy(byteArray, 0, bArr, 32 - byteArray.length, byteArray.length);
        for (int i2 = 0; i2 < 16; i2++) {
            byte b2 = bArr[i2];
            int i3 = (32 - i2) - 1;
            bArr[i2] = bArr[i3];
            bArr[i3] = b2;
        }
        return bArr;
    }
}
