package org.bouncycastle.math.ec.rfc7748;
/* loaded from: classes13.dex */
public abstract class X25519 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f15189a = {64258704, 46628941, 18905110, 42949224, 8920788, 10663709, 35115447, 21804323, 8973338, 4366948};
    public static int[] b = null;

    public static int a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }

    public static void b(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = a(bArr, (i2 * 4) + i);
        }
        iArr[0] = iArr[0] & (-8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        iArr[7] = iArr[7] | 1073741824;
    }

    public static void c(int[] iArr, int[] iArr2) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        X25519Field.apm(iArr, iArr2, create, create2);
        X25519Field.sqr(create, create);
        X25519Field.sqr(create2, create2);
        X25519Field.mul(create, create2, iArr);
        X25519Field.sub(create, create2, create);
        X25519Field.mul(create, 121666, iArr2);
        X25519Field.add(iArr2, create2, iArr2);
        X25519Field.mul(iArr2, create, iArr2);
    }

    public static synchronized void precompute() {
        synchronized (X25519.class) {
            if (b != null) {
                return;
            }
            int[] iArr = new int[2520];
            b = iArr;
            int[] iArr2 = new int[2510];
            int[] create = X25519Field.create();
            create[0] = 9;
            int[] create2 = X25519Field.create();
            create2[0] = 1;
            int[] create3 = X25519Field.create();
            int[] create4 = X25519Field.create();
            X25519Field.apm(create, create2, create3, create4);
            int[] create5 = X25519Field.create();
            X25519Field.copy(create4, 0, create5, 0);
            int i = 0;
            while (true) {
                X25519Field.copy(create3, 0, iArr, i);
                if (i == 2510) {
                    break;
                }
                c(create, create2);
                X25519Field.apm(create, create2, create3, create4);
                X25519Field.mul(create3, create5, create3);
                X25519Field.mul(create5, create4, create5);
                X25519Field.copy(create4, 0, iArr2, i);
                i += 10;
            }
            int[] create6 = X25519Field.create();
            X25519Field.inv(create5, create6);
            while (true) {
                X25519Field.copy(iArr, i, create, 0);
                X25519Field.mul(create, create6, create);
                X25519Field.copy(create, 0, b, i);
                if (i == 0) {
                    return;
                }
                i -= 10;
                X25519Field.copy(iArr2, i, create2, 0);
                X25519Field.mul(create6, create2, create6);
            }
        }
    }

    public static void scalarMult(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int[] iArr = new int[8];
        b(bArr, i, iArr);
        int[] create = X25519Field.create();
        X25519Field.decode(bArr2, i2, create);
        int[] create2 = X25519Field.create();
        X25519Field.copy(create, 0, create2, 0);
        int[] create3 = X25519Field.create();
        create3[0] = 1;
        int[] create4 = X25519Field.create();
        create4[0] = 1;
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] create7 = X25519Field.create();
        int i4 = 254;
        int i5 = 1;
        while (true) {
            X25519Field.apm(create4, create5, create6, create4);
            X25519Field.apm(create2, create3, create5, create2);
            X25519Field.mul(create6, create2, create6);
            X25519Field.mul(create4, create5, create4);
            X25519Field.sqr(create5, create5);
            X25519Field.sqr(create2, create2);
            X25519Field.sub(create5, create2, create7);
            X25519Field.mul(create7, 121666, create3);
            X25519Field.add(create3, create2, create3);
            X25519Field.mul(create3, create7, create3);
            X25519Field.mul(create2, create5, create2);
            X25519Field.apm(create6, create4, create4, create5);
            X25519Field.sqr(create4, create4);
            X25519Field.sqr(create5, create5);
            X25519Field.mul(create5, create, create5);
            i4--;
            int i6 = (iArr[i4 >>> 5] >>> (i4 & 31)) & 1;
            int i7 = i5 ^ i6;
            X25519Field.cswap(i7, create2, create4);
            X25519Field.cswap(i7, create3, create5);
            if (i4 < 3) {
                break;
            }
            i5 = i6;
        }
        for (int i8 = 0; i8 < 3; i8++) {
            c(create2, create3);
        }
        X25519Field.inv(create3, create3);
        X25519Field.mul(create2, create3, create2);
        X25519Field.normalize(create2);
        X25519Field.encode(create2, bArr3, i3);
    }

    public static void scalarMultBase(byte[] bArr, int i, byte[] bArr2, int i2) {
        precompute();
        int[] iArr = new int[8];
        b(bArr, i, iArr);
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        create2[0] = 1;
        int[] create3 = X25519Field.create();
        create3[0] = 1;
        int[] create4 = X25519Field.create();
        X25519Field.copy(f15189a, 0, create4, 0);
        int[] create5 = X25519Field.create();
        create5[0] = 1;
        int i3 = 0;
        int i4 = 1;
        int i5 = 3;
        while (true) {
            X25519Field.copy(b, i3, create, 0);
            i3 += 10;
            int i6 = (iArr[i5 >>> 5] >>> (i5 & 31)) & 1;
            int i7 = i4 ^ i6;
            X25519Field.cswap(i7, create2, create4);
            X25519Field.cswap(i7, create3, create5);
            X25519Field.apm(create2, create3, create2, create3);
            X25519Field.mul(create, create3, create);
            X25519Field.carry(create2);
            X25519Field.apm(create2, create, create2, create3);
            X25519Field.sqr(create2, create2);
            X25519Field.sqr(create3, create3);
            X25519Field.mul(create5, create2, create2);
            X25519Field.mul(create4, create3, create3);
            i5++;
            if (i5 >= 255) {
                break;
            }
            i4 = i6;
        }
        for (int i8 = 0; i8 < 3; i8++) {
            c(create2, create3);
        }
        X25519Field.inv(create3, create3);
        X25519Field.mul(create2, create3, create2);
        X25519Field.normalize(create2);
        X25519Field.encode(create2, bArr2, i2);
    }
}
