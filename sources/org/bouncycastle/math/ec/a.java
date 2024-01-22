package org.bouncycastle.math.ec;

import com.google.common.primitives.Shorts;
import com.jstyle.blesdk1860.constant.BleConst;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class a implements Cloneable {
    public static final short[] i = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, 1024, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, Shorts.MAX_POWER_OF_TWO, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    public static final byte[] j = {0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
    public long[] h;

    public a(int i2) {
        this.h = new long[i2];
    }

    public a(BigInteger bigInteger) {
        int i2;
        if (bigInteger == null || bigInteger.signum() < 0) {
            throw new IllegalArgumentException("invalid F2m field value");
        }
        if (bigInteger.signum() == 0) {
            this.h = new long[]{0};
            return;
        }
        byte[] byteArray = bigInteger.toByteArray();
        int length = byteArray.length;
        if (byteArray[0] == 0) {
            length--;
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i3 = (length + 7) / 8;
        this.h = new long[i3];
        int i4 = i3 - 1;
        int i5 = (length % 8) + i2;
        if (i2 < i5) {
            long j2 = 0;
            while (i2 < i5) {
                j2 = (j2 << 8) | (byteArray[i2] & 255);
                i2++;
            }
            this.h[i4] = j2;
            i4--;
        }
        while (i4 >= 0) {
            long j3 = 0;
            int i6 = 0;
            while (i6 < 8) {
                j3 = (j3 << 8) | (byteArray[i2] & 255);
                i6++;
                i2++;
            }
            this.h[i4] = j3;
            i4--;
        }
    }

    public a(long[] jArr) {
        this.h = jArr;
    }

    public a(long[] jArr, int i2, int i3) {
        if (i2 == 0 && i3 == jArr.length) {
            this.h = jArr;
            return;
        }
        long[] jArr2 = new long[i3];
        this.h = jArr2;
        System.arraycopy(jArr, i2, jArr2, 0, i3);
    }

    public static void B(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        m(jArr, i2, i3);
        int i5 = i3 - i4;
        int length = iArr.length;
        while (true) {
            length--;
            if (length < 0) {
                m(jArr, i2, i5);
                return;
            }
            m(jArr, i2, iArr[length] + i5);
        }
    }

    public static void C(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        while (true) {
            i3--;
            if (i3 < i4) {
                return;
            }
            if (M(jArr, i2, i3)) {
                B(jArr, i2, i3, i4, iArr);
            }
        }
    }

    public static int D(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        int i5 = (i4 + 63) >>> 6;
        if (i3 < i5) {
            return i3;
        }
        int i6 = i3 << 6;
        int min = Math.min(i6, (i4 << 1) - 1);
        int i7 = i6 - min;
        int i8 = i3;
        while (i7 >= 64) {
            i8--;
            i7 -= 64;
        }
        int length = iArr.length;
        int i9 = iArr[length - 1];
        int i10 = length > 1 ? iArr[length - 2] : 0;
        int max = Math.max(i4, i9 + 64);
        int min2 = (i7 + Math.min(min - max, i4 - i10)) >> 6;
        if (min2 > 1) {
            int i11 = i8 - min2;
            F(jArr, i2, i8, i11, i4, iArr);
            while (i8 > i11) {
                i8--;
                jArr[i2 + i8] = 0;
            }
            min = i11 << 6;
        }
        if (min > max) {
            H(jArr, i2, i8, max, i4, iArr);
        } else {
            max = min;
        }
        if (max > i4) {
            C(jArr, i2, max, i4, iArr);
        }
        return i5;
    }

    public static a E(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        return new a(jArr, i2, D(jArr, i2, i3, i4, iArr));
    }

    public static void F(long[] jArr, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6 = (i4 << 6) - i5;
        int length = iArr.length;
        while (true) {
            length--;
            if (length < 0) {
                n(jArr, i2, jArr, i2 + i4, i3 - i4, i6);
                return;
            }
            n(jArr, i2, jArr, i2 + i4, i3 - i4, i6 + iArr[length]);
        }
    }

    public static void G(long[] jArr, int i2, int i3, long j2, int i4, int[] iArr) {
        int i5 = i3 - i4;
        int length = iArr.length;
        while (true) {
            length--;
            if (length < 0) {
                o(jArr, i2, i5, j2);
                return;
            }
            o(jArr, i2, iArr[length] + i5, j2);
        }
    }

    public static void H(long[] jArr, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6 = i4 >>> 6;
        int i7 = i3;
        while (true) {
            int i8 = i7 - 1;
            if (i8 <= i6) {
                break;
            }
            int i9 = i2 + i8;
            long j2 = jArr[i9];
            if (j2 != 0) {
                jArr[i9] = 0;
                G(jArr, i2, i8 << 6, j2, i5, iArr);
            }
            i7 = i8;
        }
        int i10 = i4 & 63;
        int i11 = i2 + i6;
        long j3 = jArr[i11] >>> i10;
        if (j3 != 0) {
            jArr[i11] = jArr[i11] ^ (j3 << i10);
            G(jArr, i2, i4, j3, i5, iArr);
        }
    }

    public static long J(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = 64 - i5;
        long j2 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            long j3 = jArr[i2 + i7];
            jArr2[i3 + i7] = j2 | (j3 << i5);
            j2 = j3 >>> i6;
        }
        return j2;
    }

    public static void L(long[] jArr, int i2, int i3, int[] iArr) {
        int i4 = i2 << 1;
        while (true) {
            i2--;
            if (i2 < 0) {
                return;
            }
            long j2 = jArr[i2];
            int i5 = i4 - 1;
            jArr[i5] = r((int) (j2 >>> 32));
            i4 = i5 - 1;
            jArr[i4] = r((int) j2);
        }
    }

    public static boolean M(long[] jArr, int i2, int i3) {
        return (jArr[i2 + (i3 >>> 6)] & (1 << (i3 & 63))) != 0;
    }

    public static void a(long[] jArr, int i2, long[] jArr2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i2 + i5;
            jArr[i6] = jArr[i6] ^ jArr2[i3 + i5];
        }
    }

    public static void b(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4, int i5) {
        for (int i6 = 0; i6 < i5; i6++) {
            jArr3[i4 + i6] = jArr[i2 + i6] ^ jArr2[i3 + i6];
        }
    }

    public static void c(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4, int i5) {
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = i2 + i6;
            jArr[i7] = jArr[i7] ^ (jArr2[i3 + i6] ^ jArr3[i4 + i6]);
        }
    }

    public static long g(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = 64 - i5;
        long j2 = 0;
        while (true) {
            i4--;
            if (i4 < 0) {
                return j2;
            }
            long j3 = jArr2[i3 + i4];
            int i7 = i2 + i4;
            jArr[i7] = (j2 | (j3 >>> i5)) ^ jArr[i7];
            j2 = j3 << i6;
        }
    }

    public static long h(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = 64 - i5;
        long j2 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            long j3 = jArr2[i3 + i7];
            int i8 = i2 + i7;
            jArr[i8] = (j2 | (j3 << i5)) ^ jArr[i8];
            j2 = j3 >>> i6;
        }
        return j2;
    }

    public static int i(long j2) {
        int i2;
        int i3 = 32;
        int i4 = (int) (j2 >>> 32);
        if (i4 == 0) {
            i4 = (int) j2;
            i3 = 0;
        }
        int i5 = i4 >>> 16;
        if (i5 == 0) {
            int i6 = i4 >>> 8;
            i2 = i6 == 0 ? j[i4] : j[i6] + 8;
        } else {
            int i7 = i5 >>> 8;
            i2 = i7 == 0 ? j[i5] + 16 : j[i7] + 24;
        }
        return i3 + i2;
    }

    public static void m(long[] jArr, int i2, int i3) {
        int i4 = i2 + (i3 >>> 6);
        jArr[i4] = jArr[i4] ^ (1 << (i3 & 63));
    }

    public static void n(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = i2 + (i5 >>> 6);
        int i7 = i5 & 63;
        if (i7 == 0) {
            a(jArr, i6, jArr2, i3, i4);
        } else {
            jArr[i6] = g(jArr, i6 + 1, jArr2, i3, i4, 64 - i7) ^ jArr[i6];
        }
    }

    public static void o(long[] jArr, int i2, int i3, long j2) {
        int i4 = i2 + (i3 >>> 6);
        int i5 = i3 & 63;
        if (i5 == 0) {
            jArr[i4] = jArr[i4] ^ j2;
            return;
        }
        jArr[i4] = jArr[i4] ^ (j2 << i5);
        long j3 = j2 >>> (64 - i5);
        if (j3 != 0) {
            int i6 = i4 + 1;
            jArr[i6] = j3 ^ jArr[i6];
        }
    }

    public static long r(int i2) {
        short[] sArr = i;
        int i3 = sArr[i2 & 255] | (sArr[(i2 >>> 8) & 255] << 16);
        short s = sArr[(i2 >>> 16) & 255];
        return (i3 & 4294967295L) | ((((sArr[i2 >>> 24] << 16) | s) & 4294967295L) << 32);
    }

    public static void z(long j2, long[] jArr, int i2, long[] jArr2, int i3) {
        if ((j2 & 1) != 0) {
            a(jArr2, i3, jArr, 0, i2);
        }
        long j3 = j2;
        int i4 = 1;
        while (true) {
            long j4 = j3 >>> 1;
            if (j4 == 0) {
                return;
            }
            if ((j4 & 1) != 0) {
                long h = h(jArr2, i3, jArr, 0, i2, i4);
                if (h != 0) {
                    int i5 = i3 + i2;
                    jArr2[i5] = h ^ jArr2[i5];
                }
            }
            i4++;
            j3 = j4;
        }
    }

    public void A(int i2, int[] iArr) {
        long[] jArr = this.h;
        int D = D(jArr, 0, jArr.length, i2, iArr);
        if (D < jArr.length) {
            long[] jArr2 = new long[D];
            this.h = jArr2;
            System.arraycopy(jArr, 0, jArr2, 0, D);
        }
    }

    public final long[] I(int i2) {
        long[] jArr = new long[i2];
        long[] jArr2 = this.h;
        System.arraycopy(jArr2, 0, jArr, 0, Math.min(jArr2.length, i2));
        return jArr;
    }

    public a K(int i2, int[] iArr) {
        int p = p();
        if (p == 0) {
            return this;
        }
        int i3 = p << 1;
        long[] jArr = new long[i3];
        int i4 = 0;
        while (i4 < i3) {
            long j2 = this.h[i4 >>> 1];
            int i5 = i4 + 1;
            jArr[i4] = r((int) j2);
            i4 = i5 + 1;
            jArr[i5] = r((int) (j2 >>> 32));
        }
        return new a(jArr, 0, i3);
    }

    public boolean N() {
        long[] jArr = this.h;
        return jArr.length > 0 && (1 & jArr[0]) != 0;
    }

    public BigInteger O() {
        int p = p();
        if (p == 0) {
            return ECConstants.ZERO;
        }
        int i2 = p - 1;
        long j2 = this.h[i2];
        byte[] bArr = new byte[8];
        int i3 = 0;
        boolean z = false;
        for (int i4 = 7; i4 >= 0; i4--) {
            byte b = (byte) (j2 >>> (i4 * 8));
            if (z || b != 0) {
                bArr[i3] = b;
                i3++;
                z = true;
            }
        }
        byte[] bArr2 = new byte[(i2 * 8) + i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i5] = bArr[i5];
        }
        for (int i6 = p - 2; i6 >= 0; i6--) {
            long j3 = this.h[i6];
            int i7 = 7;
            while (i7 >= 0) {
                bArr2[i3] = (byte) (j3 >>> (i7 * 8));
                i7--;
                i3++;
            }
        }
        return new BigInteger(1, bArr2);
    }

    public Object clone() {
        return new a(Arrays.clone(this.h));
    }

    public a d() {
        if (this.h.length == 0) {
            return new a(new long[]{1});
        }
        long[] I = I(Math.max(1, p()));
        I[0] = 1 ^ I[0];
        return new a(I);
    }

    public final void e(a aVar, int i2, int i3) {
        int i4 = (i2 + 63) >>> 6;
        int i5 = i3 >>> 6;
        int i6 = i3 & 63;
        if (i6 == 0) {
            a(this.h, i5, aVar.h, 0, i4);
            return;
        }
        long h = h(this.h, i5, aVar.h, 0, i4, i6);
        if (h != 0) {
            long[] jArr = this.h;
            int i7 = i4 + i5;
            jArr[i7] = h ^ jArr[i7];
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            int p = p();
            if (aVar.p() != p) {
                return false;
            }
            for (int i2 = 0; i2 < p; i2++) {
                if (this.h[i2] != aVar.h[i2]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void f(a aVar, int i2) {
        int p = aVar.p();
        if (p == 0) {
            return;
        }
        int i3 = p + i2;
        if (i3 > this.h.length) {
            this.h = I(i3);
        }
        a(this.h, i2, aVar.h, 0, p);
    }

    public int hashCode() {
        int p = p();
        int i2 = 1;
        for (int i3 = 0; i3 < p; i3++) {
            long j2 = this.h[i3];
            i2 = (((i2 * 31) ^ ((int) j2)) * 31) ^ ((int) (j2 >>> 32));
        }
        return i2;
    }

    public void j(long[] jArr, int i2) {
        long[] jArr2 = this.h;
        System.arraycopy(jArr2, 0, jArr, i2, jArr2.length);
    }

    public int k() {
        int length = this.h.length;
        while (length != 0) {
            length--;
            long j2 = this.h[length];
            if (j2 != 0) {
                return (length << 6) + i(j2);
            }
        }
        return 0;
    }

    public final int l(int i2) {
        int i3 = (i2 + 62) >>> 6;
        while (i3 != 0) {
            i3--;
            long j2 = this.h[i3];
            if (j2 != 0) {
                return (i3 << 6) + i(j2);
            }
        }
        return 0;
    }

    public int p() {
        return q(this.h.length);
    }

    public int q(int i2) {
        long[] jArr = this.h;
        int min = Math.min(i2, jArr.length);
        if (min < 1) {
            return 0;
        }
        if (jArr[0] != 0) {
            do {
                min--;
            } while (jArr[min] == 0);
            return min + 1;
        }
        do {
            min--;
            if (jArr[min] != 0) {
                return min + 1;
            }
        } while (min > 0);
        return 0;
    }

    public boolean s() {
        long[] jArr = this.h;
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < jArr.length; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean t() {
        for (long j2 : this.h) {
            if (j2 != 0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        int p = p();
        if (p == 0) {
            return BleConst.GetDeviceTime;
        }
        int i2 = p - 1;
        StringBuffer stringBuffer = new StringBuffer(Long.toBinaryString(this.h[i2]));
        while (true) {
            i2--;
            if (i2 < 0) {
                return stringBuffer.toString();
            }
            String binaryString = Long.toBinaryString(this.h[i2]);
            int length = binaryString.length();
            if (length < 64) {
                stringBuffer.append("0000000000000000000000000000000000000000000000000000000000000000".substring(length));
            }
            stringBuffer.append(binaryString);
        }
    }

    public a u(int i2, int[] iArr) {
        int k = k();
        if (k == 0) {
            throw new IllegalStateException();
        }
        int i3 = 1;
        if (k == 1) {
            return this;
        }
        int i4 = (i2 + 63) >>> 6;
        a aVar = new a(i4);
        B(aVar.h, 0, i2, i2, iArr);
        a aVar2 = new a(i4);
        aVar2.h[0] = 1;
        a aVar3 = new a(i4);
        int[] iArr2 = new int[2];
        iArr2[0] = k;
        iArr2[1] = i2 + 1;
        a[] aVarArr = {(a) clone(), aVar};
        int[] iArr3 = {1, 0};
        a[] aVarArr2 = {aVar2, aVar3};
        int i5 = iArr2[1];
        int i6 = iArr3[1];
        int i7 = i5 - iArr2[0];
        while (true) {
            if (i7 < 0) {
                i7 = -i7;
                iArr2[i3] = i5;
                iArr3[i3] = i6;
                int i8 = 1 - i3;
                int i9 = iArr2[i8];
                i6 = iArr3[i8];
                i3 = i8;
                i5 = i9;
            }
            int i10 = 1 - i3;
            aVarArr[i3].e(aVarArr[i10], iArr2[i10], i7);
            int l = aVarArr[i3].l(i5);
            if (l == 0) {
                return aVarArr2[i10];
            }
            int i11 = iArr3[i10];
            aVarArr2[i3].e(aVarArr2[i10], i11, i7);
            int i12 = i11 + i7;
            if (i12 > i6) {
                i6 = i12;
            } else if (i12 == i6) {
                i6 = aVarArr2[i3].l(i6);
            }
            i7 += l - i5;
            i5 = l;
        }
    }

    public a v(a aVar, int i2, int[] iArr) {
        int i3;
        int i4;
        a aVar2;
        a aVar3;
        int i5;
        long[] jArr;
        int i6;
        int k = k();
        if (k == 0) {
            return this;
        }
        int k2 = aVar.k();
        if (k2 == 0) {
            return aVar;
        }
        if (k > k2) {
            i4 = k;
            i3 = k2;
            aVar3 = this;
            aVar2 = aVar;
        } else {
            i3 = k;
            i4 = k2;
            aVar2 = this;
            aVar3 = aVar;
        }
        int i7 = (i3 + 63) >>> 6;
        int i8 = (i4 + 63) >>> 6;
        int i9 = ((i3 + i4) + 62) >>> 6;
        if (i7 == 1) {
            long j2 = aVar2.h[0];
            if (j2 == 1) {
                return aVar3;
            }
            long[] jArr2 = new long[i9];
            z(j2, aVar3.h, i8, jArr2, 0);
            return E(jArr2, 0, i9, i2, iArr);
        }
        int i10 = ((i4 + 7) + 63) >>> 6;
        int[] iArr2 = new int[16];
        int i11 = i10 << 4;
        long[] jArr3 = new long[i11];
        iArr2[1] = i10;
        System.arraycopy(aVar3.h, 0, jArr3, i10, i8);
        int i12 = 2;
        int i13 = i10;
        for (int i14 = 16; i12 < i14; i14 = 16) {
            i13 += i10;
            iArr2[i12] = i13;
            if ((i12 & 1) == 0) {
                jArr = jArr3;
                i6 = i11;
                J(jArr3, i13 >>> 1, jArr3, i13, i10, 1);
            } else {
                jArr = jArr3;
                i6 = i11;
                b(jArr, i10, jArr3, i13 - i10, jArr, i13, i10);
            }
            i12++;
            i11 = i6;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int i15 = i11;
        long[] jArr5 = new long[i15];
        J(jArr4, 0, jArr5, 0, i15, 4);
        long[] jArr6 = aVar2.h;
        int i16 = i9 << 3;
        long[] jArr7 = new long[i16];
        int i17 = 0;
        while (i17 < i7) {
            long j3 = jArr6[i17];
            int i18 = i17;
            while (true) {
                long j4 = j3 >>> 4;
                i5 = i17;
                c(jArr7, i18, jArr4, iArr2[((int) j3) & 15], jArr5, iArr2[((int) j4) & 15], i10);
                j3 = j4 >>> 4;
                if (j3 == 0) {
                    break;
                }
                i18 += i9;
                i17 = i5;
            }
            i17 = i5 + 1;
        }
        while (true) {
            i16 -= i9;
            if (i16 == 0) {
                return E(jArr7, 0, i9, i2, iArr);
            }
            h(jArr7, i16 - i9, jArr7, i16, i9, 8);
        }
    }

    public a w(int i2, int[] iArr) {
        int p = p();
        if (p == 0) {
            return this;
        }
        int i3 = p << 1;
        long[] jArr = new long[i3];
        int i4 = 0;
        while (i4 < i3) {
            long j2 = this.h[i4 >>> 1];
            int i5 = i4 + 1;
            jArr[i4] = r((int) j2);
            i4 = i5 + 1;
            jArr[i5] = r((int) (j2 >>> 32));
        }
        return new a(jArr, 0, D(jArr, 0, i3, i2, iArr));
    }

    public a x(int i2, int i3, int[] iArr) {
        int p = p();
        if (p == 0) {
            return this;
        }
        int i4 = ((i3 + 63) >>> 6) << 1;
        long[] jArr = new long[i4];
        System.arraycopy(this.h, 0, jArr, 0, p);
        while (true) {
            i2--;
            if (i2 < 0) {
                return new a(jArr, 0, p);
            }
            L(jArr, p, i3, iArr);
            p = D(jArr, 0, i4, i3, iArr);
        }
    }

    public a y(a aVar, int i2, int[] iArr) {
        int i3;
        int i4;
        a aVar2;
        a aVar3;
        long[] jArr;
        int i5;
        int k = k();
        if (k == 0) {
            return this;
        }
        int k2 = aVar.k();
        if (k2 == 0) {
            return aVar;
        }
        if (k > k2) {
            i4 = k;
            i3 = k2;
            aVar3 = this;
            aVar2 = aVar;
        } else {
            i3 = k;
            i4 = k2;
            aVar2 = this;
            aVar3 = aVar;
        }
        int i6 = (i3 + 63) >>> 6;
        int i7 = (i4 + 63) >>> 6;
        int i8 = ((i3 + i4) + 62) >>> 6;
        if (i6 == 1) {
            long j2 = aVar2.h[0];
            if (j2 == 1) {
                return aVar3;
            }
            long[] jArr2 = new long[i8];
            z(j2, aVar3.h, i7, jArr2, 0);
            return new a(jArr2, 0, i8);
        }
        int i9 = ((i4 + 7) + 63) >>> 6;
        int[] iArr2 = new int[16];
        int i10 = i9 << 4;
        long[] jArr3 = new long[i10];
        iArr2[1] = i9;
        System.arraycopy(aVar3.h, 0, jArr3, i9, i7);
        int i11 = 2;
        int i12 = i9;
        for (int i13 = 16; i11 < i13; i13 = 16) {
            i12 += i9;
            iArr2[i11] = i12;
            if ((i11 & 1) == 0) {
                jArr = jArr3;
                i5 = i10;
                J(jArr3, i12 >>> 1, jArr3, i12, i9, 1);
            } else {
                jArr = jArr3;
                i5 = i10;
                b(jArr, i9, jArr, i12 - i9, jArr3, i12, i9);
            }
            i11++;
            i10 = i5;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int i14 = i10;
        long[] jArr5 = new long[i14];
        J(jArr4, 0, jArr5, 0, i14, 4);
        long[] jArr6 = aVar2.h;
        int i15 = i8 << 3;
        long[] jArr7 = new long[i15];
        for (int i16 = 0; i16 < i6; i16++) {
            long j3 = jArr6[i16];
            int i17 = i16;
            while (true) {
                long j4 = j3 >>> 4;
                c(jArr7, i17, jArr4, iArr2[((int) j3) & 15], jArr5, iArr2[((int) j4) & 15], i9);
                j3 = j4 >>> 4;
                if (j3 == 0) {
                    break;
                }
                i17 += i8;
            }
        }
        while (true) {
            i15 -= i8;
            if (i15 == 0) {
                return new a(jArr7, 0, i8);
            }
            h(jArr7, i15 - i8, jArr7, i15, i8, 8);
        }
    }
}
