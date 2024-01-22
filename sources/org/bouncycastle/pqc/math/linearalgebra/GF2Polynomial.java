package org.bouncycastle.pqc.math.linearalgebra;

import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.primitives.Shorts;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemViewTypeComposer;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.math.BigInteger;
import java.util.Random;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes13.dex */
public class GF2Polynomial {
    public static Random d = new Random();
    public static final boolean[] e = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    public static final short[] f = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, 1024, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, Shorts.MAX_POWER_OF_TWO, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    public static final int[] g = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, PKIFailureInfo.duplicateCertReq, 1073741824, Integer.MIN_VALUE, 0};
    public static final int[] h = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, ErrorCode.ERR_UNKNOWN, 2097151, 4194303, ItemViewTypeComposer.MAX_WRAPPED_VIEW_TYPE, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, LockFreeTaskQueueCore.MAX_CAPACITY_MASK, Integer.MAX_VALUE, -1};

    /* renamed from: a  reason: collision with root package name */
    public int f15364a;
    public int b;
    public int[] c;

    public GF2Polynomial(int i) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.b = i2;
        this.c = new int[i2];
        this.f15364a = i;
    }

    public GF2Polynomial(int i, String str) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.b = i2;
        this.c = new int[i2];
        this.f15364a = i;
        if (str.equalsIgnoreCase("ZERO")) {
            assignZero();
        } else if (str.equalsIgnoreCase("ONE")) {
            assignOne();
        } else if (str.equalsIgnoreCase("RANDOM")) {
            randomize();
        } else if (str.equalsIgnoreCase("X")) {
            assignX();
        } else if (str.equalsIgnoreCase("ALL")) {
            assignAll();
        } else {
            throw new IllegalArgumentException("Error: GF2Polynomial was called using " + str + " as value!");
        }
    }

    public GF2Polynomial(int i, BigInteger bigInteger) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.b = i2;
        this.c = new int[i2];
        this.f15364a = i;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            byteArray = bArr;
        }
        int length2 = byteArray.length & 3;
        int length3 = ((byteArray.length - 1) >> 2) + 1;
        for (int i3 = 0; i3 < length2; i3++) {
            int[] iArr = this.c;
            int i4 = length3 - 1;
            iArr[i4] = iArr[i4] | ((byteArray[i3] & 255) << (((length2 - 1) - i3) << 3));
        }
        for (int i5 = 0; i5 <= ((byteArray.length - 4) >> 2); i5++) {
            int length4 = (byteArray.length - 1) - (i5 << 2);
            int[] iArr2 = this.c;
            iArr2[i5] = byteArray[length4] & 255;
            iArr2[i5] = iArr2[i5] | ((byteArray[length4 - 1] << 8) & 65280);
            iArr2[i5] = iArr2[i5] | ((byteArray[length4 - 2] << 16) & 16711680);
            iArr2[i5] = ((byteArray[length4 - 3] << 24) & ViewCompat.MEASURED_STATE_MASK) | iArr2[i5];
        }
        int i6 = this.f15364a;
        if ((i6 & 31) != 0) {
            int[] iArr3 = this.c;
            int i7 = this.b - 1;
            iArr3[i7] = h[i6 & 31] & iArr3[i7];
        }
        reduceN();
    }

    public GF2Polynomial(int i, Random random) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.b = i2;
        this.c = new int[i2];
        this.f15364a = i;
        randomize(random);
    }

    public GF2Polynomial(int i, byte[] bArr) {
        int i2;
        i = i < 1 ? 1 : i;
        int i3 = ((i - 1) >> 5) + 1;
        this.b = i3;
        this.c = new int[i3];
        this.f15364a = i;
        int min = Math.min(((bArr.length - 1) >> 2) + 1, i3);
        int i4 = 0;
        while (true) {
            i2 = min - 1;
            if (i4 >= i2) {
                break;
            }
            int length = (bArr.length - (i4 << 2)) - 1;
            int[] iArr = this.c;
            iArr[i4] = bArr[length] & 255;
            iArr[i4] = (65280 & (bArr[length - 1] << 8)) | iArr[i4];
            iArr[i4] = (16711680 & (bArr[length - 2] << 16)) | iArr[i4];
            iArr[i4] = ((bArr[length - 3] << 24) & ViewCompat.MEASURED_STATE_MASK) | iArr[i4];
            i4++;
        }
        int length2 = (bArr.length - (i2 << 2)) - 1;
        int[] iArr2 = this.c;
        iArr2[i2] = bArr[length2] & 255;
        if (length2 > 0) {
            iArr2[i2] = (65280 & (bArr[length2 - 1] << 8)) | iArr2[i2];
        }
        if (length2 > 1) {
            iArr2[i2] = iArr2[i2] | (16711680 & (bArr[length2 - 2] << 16));
        }
        if (length2 > 2) {
            iArr2[i2] = ((bArr[length2 - 3] << 24) & ViewCompat.MEASURED_STATE_MASK) | iArr2[i2];
        }
        m();
        reduceN();
    }

    public GF2Polynomial(int i, int[] iArr) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.b = i2;
        this.c = new int[i2];
        this.f15364a = i;
        System.arraycopy(iArr, 0, this.c, 0, Math.min(i2, iArr.length));
        m();
    }

    public GF2Polynomial(GF2Polynomial gF2Polynomial) {
        this.f15364a = gF2Polynomial.f15364a;
        this.b = gF2Polynomial.b;
        this.c = IntUtils.clone(gF2Polynomial.c);
    }

    public static int[] d(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[8];
        int[] iArr4 = new int[2];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(2, iArr.length));
        int[] iArr5 = new int[2];
        if (iArr.length > 2) {
            System.arraycopy(iArr, 2, iArr5, 0, Math.min(2, iArr.length - 2));
        }
        int[] iArr6 = new int[2];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(2, iArr2.length));
        int[] iArr7 = new int[2];
        if (iArr2.length > 2) {
            System.arraycopy(iArr2, 2, iArr7, 0, Math.min(2, iArr2.length - 2));
        }
        if (iArr5[1] != 0 || iArr7[1] != 0) {
            int[] h2 = h(iArr5, iArr7);
            iArr3[7] = iArr3[7] ^ h2[3];
            iArr3[6] = iArr3[6] ^ h2[2];
            iArr3[5] = iArr3[5] ^ (h2[1] ^ h2[3]);
            iArr3[4] = iArr3[4] ^ (h2[0] ^ h2[2]);
            iArr3[3] = iArr3[3] ^ h2[1];
            iArr3[2] = h2[0] ^ iArr3[2];
        } else if (iArr5[0] != 0 || iArr7[0] != 0) {
            int[] f2 = f(iArr5[0], iArr7[0]);
            iArr3[5] = iArr3[5] ^ f2[1];
            iArr3[4] = iArr3[4] ^ f2[0];
            iArr3[3] = iArr3[3] ^ f2[1];
            iArr3[2] = f2[0] ^ iArr3[2];
        }
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        if (iArr5[1] == 0 && iArr7[1] == 0) {
            int[] f3 = f(iArr5[0], iArr7[0]);
            iArr3[3] = iArr3[3] ^ f3[1];
            iArr3[2] = f3[0] ^ iArr3[2];
        } else {
            int[] h3 = h(iArr5, iArr7);
            iArr3[5] = iArr3[5] ^ h3[3];
            iArr3[4] = iArr3[4] ^ h3[2];
            iArr3[3] = iArr3[3] ^ h3[1];
            iArr3[2] = h3[0] ^ iArr3[2];
        }
        if (iArr4[1] == 0 && iArr6[1] == 0) {
            int[] f4 = f(iArr4[0], iArr6[0]);
            iArr3[3] = iArr3[3] ^ f4[1];
            iArr3[2] = iArr3[2] ^ f4[0];
            iArr3[1] = iArr3[1] ^ f4[1];
            iArr3[0] = f4[0] ^ iArr3[0];
        } else {
            int[] h4 = h(iArr4, iArr6);
            iArr3[5] = iArr3[5] ^ h4[3];
            iArr3[4] = iArr3[4] ^ h4[2];
            iArr3[3] = iArr3[3] ^ (h4[1] ^ h4[3]);
            iArr3[2] = iArr3[2] ^ (h4[0] ^ h4[2]);
            iArr3[1] = iArr3[1] ^ h4[1];
            iArr3[0] = h4[0] ^ iArr3[0];
        }
        return iArr3;
    }

    public static int[] e(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[4];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(4, iArr.length));
        int[] iArr5 = new int[4];
        if (iArr.length > 4) {
            System.arraycopy(iArr, 4, iArr5, 0, Math.min(4, iArr.length - 4));
        }
        int[] iArr6 = new int[4];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(4, iArr2.length));
        int[] iArr7 = new int[4];
        if (iArr2.length > 4) {
            System.arraycopy(iArr2, 4, iArr7, 0, Math.min(4, iArr2.length - 4));
        }
        if (iArr5[3] != 0 || iArr5[2] != 0 || iArr7[3] != 0 || iArr7[2] != 0) {
            int[] d2 = d(iArr5, iArr7);
            iArr3[15] = iArr3[15] ^ d2[7];
            iArr3[14] = iArr3[14] ^ d2[6];
            iArr3[13] = iArr3[13] ^ d2[5];
            iArr3[12] = iArr3[12] ^ d2[4];
            iArr3[11] = iArr3[11] ^ (d2[3] ^ d2[7]);
            iArr3[10] = iArr3[10] ^ (d2[2] ^ d2[6]);
            iArr3[9] = iArr3[9] ^ (d2[1] ^ d2[5]);
            iArr3[8] = iArr3[8] ^ (d2[0] ^ d2[4]);
            iArr3[7] = iArr3[7] ^ d2[3];
            iArr3[6] = iArr3[6] ^ d2[2];
            iArr3[5] = iArr3[5] ^ d2[1];
            iArr3[4] = d2[0] ^ iArr3[4];
        } else if (iArr5[1] != 0 || iArr7[1] != 0) {
            int[] h2 = h(iArr5, iArr7);
            iArr3[11] = iArr3[11] ^ h2[3];
            iArr3[10] = iArr3[10] ^ h2[2];
            iArr3[9] = iArr3[9] ^ h2[1];
            iArr3[8] = iArr3[8] ^ h2[0];
            iArr3[7] = iArr3[7] ^ h2[3];
            iArr3[6] = iArr3[6] ^ h2[2];
            iArr3[5] = iArr3[5] ^ h2[1];
            iArr3[4] = h2[0] ^ iArr3[4];
        } else if (iArr5[0] != 0 || iArr7[0] != 0) {
            int[] f2 = f(iArr5[0], iArr7[0]);
            iArr3[9] = iArr3[9] ^ f2[1];
            iArr3[8] = iArr3[8] ^ f2[0];
            iArr3[5] = iArr3[5] ^ f2[1];
            iArr3[4] = f2[0] ^ iArr3[4];
        }
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr5[2] = iArr5[2] ^ iArr4[2];
        iArr5[3] = iArr5[3] ^ iArr4[3];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        iArr7[2] = iArr7[2] ^ iArr6[2];
        iArr7[3] = iArr7[3] ^ iArr6[3];
        int[] d3 = d(iArr5, iArr7);
        iArr3[11] = iArr3[11] ^ d3[7];
        iArr3[10] = iArr3[10] ^ d3[6];
        iArr3[9] = iArr3[9] ^ d3[5];
        iArr3[8] = iArr3[8] ^ d3[4];
        iArr3[7] = iArr3[7] ^ d3[3];
        iArr3[6] = iArr3[6] ^ d3[2];
        iArr3[5] = iArr3[5] ^ d3[1];
        iArr3[4] = d3[0] ^ iArr3[4];
        int[] d4 = d(iArr4, iArr6);
        iArr3[11] = iArr3[11] ^ d4[7];
        iArr3[10] = iArr3[10] ^ d4[6];
        iArr3[9] = iArr3[9] ^ d4[5];
        iArr3[8] = iArr3[8] ^ d4[4];
        iArr3[7] = iArr3[7] ^ (d4[3] ^ d4[7]);
        iArr3[6] = iArr3[6] ^ (d4[2] ^ d4[6]);
        iArr3[5] = iArr3[5] ^ (d4[1] ^ d4[5]);
        iArr3[4] = iArr3[4] ^ (d4[0] ^ d4[4]);
        iArr3[3] = iArr3[3] ^ d4[3];
        iArr3[2] = iArr3[2] ^ d4[2];
        iArr3[1] = iArr3[1] ^ d4[1];
        iArr3[0] = d4[0] ^ iArr3[0];
        return iArr3;
    }

    public static int[] f(int i, int i2) {
        int[] iArr = new int[2];
        if (i != 0 && i2 != 0) {
            long j = i2 & 4294967295L;
            long j2 = 0;
            for (int i3 = 1; i3 <= 32; i3++) {
                if ((g[i3 - 1] & i) != 0) {
                    j2 ^= j;
                }
                j <<= 1;
            }
            iArr[1] = (int) (j2 >>> 32);
            iArr[0] = (int) (j2 & 4294967295L);
        }
        return iArr;
    }

    public static int[] g(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[32];
        int[] iArr4 = new int[8];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(8, iArr.length));
        int[] iArr5 = new int[8];
        if (iArr.length > 8) {
            System.arraycopy(iArr, 8, iArr5, 0, Math.min(8, iArr.length - 8));
        }
        int[] iArr6 = new int[8];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(8, iArr2.length));
        int[] iArr7 = new int[8];
        if (iArr2.length > 8) {
            System.arraycopy(iArr2, 8, iArr7, 0, Math.min(8, iArr2.length - 8));
        }
        int[] e2 = e(iArr5, iArr7);
        iArr3[31] = iArr3[31] ^ e2[15];
        iArr3[30] = iArr3[30] ^ e2[14];
        iArr3[29] = iArr3[29] ^ e2[13];
        iArr3[28] = iArr3[28] ^ e2[12];
        iArr3[27] = iArr3[27] ^ e2[11];
        iArr3[26] = iArr3[26] ^ e2[10];
        iArr3[25] = iArr3[25] ^ e2[9];
        iArr3[24] = iArr3[24] ^ e2[8];
        iArr3[23] = iArr3[23] ^ (e2[7] ^ e2[15]);
        iArr3[22] = iArr3[22] ^ (e2[6] ^ e2[14]);
        iArr3[21] = iArr3[21] ^ (e2[5] ^ e2[13]);
        iArr3[20] = iArr3[20] ^ (e2[4] ^ e2[12]);
        iArr3[19] = iArr3[19] ^ (e2[3] ^ e2[11]);
        iArr3[18] = iArr3[18] ^ (e2[2] ^ e2[10]);
        iArr3[17] = iArr3[17] ^ (e2[1] ^ e2[9]);
        iArr3[16] = iArr3[16] ^ (e2[0] ^ e2[8]);
        iArr3[15] = iArr3[15] ^ e2[7];
        iArr3[14] = iArr3[14] ^ e2[6];
        iArr3[13] = iArr3[13] ^ e2[5];
        iArr3[12] = iArr3[12] ^ e2[4];
        iArr3[11] = iArr3[11] ^ e2[3];
        iArr3[10] = iArr3[10] ^ e2[2];
        iArr3[9] = iArr3[9] ^ e2[1];
        iArr3[8] = iArr3[8] ^ e2[0];
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr5[2] = iArr5[2] ^ iArr4[2];
        iArr5[3] = iArr5[3] ^ iArr4[3];
        iArr5[4] = iArr5[4] ^ iArr4[4];
        iArr5[5] = iArr5[5] ^ iArr4[5];
        iArr5[6] = iArr5[6] ^ iArr4[6];
        iArr5[7] = iArr5[7] ^ iArr4[7];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        iArr7[2] = iArr7[2] ^ iArr6[2];
        iArr7[3] = iArr7[3] ^ iArr6[3];
        iArr7[4] = iArr7[4] ^ iArr6[4];
        iArr7[5] = iArr7[5] ^ iArr6[5];
        iArr7[6] = iArr7[6] ^ iArr6[6];
        iArr7[7] = iArr7[7] ^ iArr6[7];
        int[] e3 = e(iArr5, iArr7);
        iArr3[23] = iArr3[23] ^ e3[15];
        iArr3[22] = iArr3[22] ^ e3[14];
        iArr3[21] = iArr3[21] ^ e3[13];
        iArr3[20] = iArr3[20] ^ e3[12];
        iArr3[19] = iArr3[19] ^ e3[11];
        iArr3[18] = iArr3[18] ^ e3[10];
        iArr3[17] = iArr3[17] ^ e3[9];
        iArr3[16] = iArr3[16] ^ e3[8];
        iArr3[15] = iArr3[15] ^ e3[7];
        iArr3[14] = iArr3[14] ^ e3[6];
        iArr3[13] = iArr3[13] ^ e3[5];
        iArr3[12] = iArr3[12] ^ e3[4];
        iArr3[11] = iArr3[11] ^ e3[3];
        iArr3[10] = iArr3[10] ^ e3[2];
        iArr3[9] = iArr3[9] ^ e3[1];
        iArr3[8] = e3[0] ^ iArr3[8];
        int[] e4 = e(iArr4, iArr6);
        iArr3[23] = iArr3[23] ^ e4[15];
        iArr3[22] = iArr3[22] ^ e4[14];
        iArr3[21] = iArr3[21] ^ e4[13];
        iArr3[20] = iArr3[20] ^ e4[12];
        iArr3[19] = iArr3[19] ^ e4[11];
        iArr3[18] = iArr3[18] ^ e4[10];
        iArr3[17] = iArr3[17] ^ e4[9];
        iArr3[16] = iArr3[16] ^ e4[8];
        iArr3[15] = iArr3[15] ^ (e4[7] ^ e4[15]);
        iArr3[14] = iArr3[14] ^ (e4[6] ^ e4[14]);
        iArr3[13] = iArr3[13] ^ (e4[5] ^ e4[13]);
        iArr3[12] = iArr3[12] ^ (e4[4] ^ e4[12]);
        iArr3[11] = iArr3[11] ^ (e4[3] ^ e4[11]);
        iArr3[10] = iArr3[10] ^ (e4[2] ^ e4[10]);
        iArr3[9] = iArr3[9] ^ (e4[1] ^ e4[9]);
        iArr3[8] = iArr3[8] ^ (e4[0] ^ e4[8]);
        iArr3[7] = iArr3[7] ^ e4[7];
        iArr3[6] = iArr3[6] ^ e4[6];
        iArr3[5] = iArr3[5] ^ e4[5];
        iArr3[4] = iArr3[4] ^ e4[4];
        iArr3[3] = iArr3[3] ^ e4[3];
        iArr3[2] = iArr3[2] ^ e4[2];
        iArr3[1] = iArr3[1] ^ e4[1];
        iArr3[0] = e4[0] ^ iArr3[0];
        return iArr3;
    }

    public static int[] h(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[4];
        int i = iArr[0];
        int i2 = iArr.length > 1 ? iArr[1] : 0;
        int i3 = iArr2[0];
        int i4 = iArr2.length > 1 ? iArr2[1] : 0;
        if (i2 != 0 || i4 != 0) {
            int[] f2 = f(i2, i4);
            iArr3[3] = iArr3[3] ^ f2[1];
            iArr3[2] = iArr3[2] ^ (f2[0] ^ f2[1]);
            iArr3[1] = f2[0] ^ iArr3[1];
        }
        int[] f3 = f(i2 ^ i, i4 ^ i3);
        iArr3[2] = iArr3[2] ^ f3[1];
        iArr3[1] = f3[0] ^ iArr3[1];
        int[] f4 = f(i, i3);
        iArr3[2] = iArr3[2] ^ f4[1];
        iArr3[1] = iArr3[1] ^ (f4[0] ^ f4[1]);
        iArr3[0] = f4[0] ^ iArr3[0];
        return iArr3;
    }

    public final void a(int i) {
        int i2 = this.b;
        int[] iArr = this.c;
        if (i2 > iArr.length) {
            int[] iArr2 = new int[i2];
            System.arraycopy(iArr, 0, iArr2, i, i2 - i);
            this.c = null;
            this.c = iArr2;
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            int[] iArr3 = this.c;
            iArr3[i3] = iArr3[i3 - i];
        }
        for (int i4 = 0; i4 < i; i4++) {
            this.c[i4] = 0;
        }
    }

    public GF2Polynomial add(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void addToThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.f15364a);
        xorThisBy(gF2Polynomial);
    }

    public void assignAll() {
        for (int i = 0; i < this.b; i++) {
            this.c[i] = -1;
        }
        m();
    }

    public void assignOne() {
        for (int i = 1; i < this.b; i++) {
            this.c[i] = 0;
        }
        this.c[0] = 1;
    }

    public void assignX() {
        for (int i = 1; i < this.b; i++) {
            this.c[i] = 0;
        }
        this.c[0] = 2;
    }

    public void assignZero() {
        for (int i = 0; i < this.b; i++) {
            this.c[i] = 0;
        }
    }

    public final GF2Polynomial b(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.f15364a << 1);
        int i = this.f15364a;
        if (i <= 32) {
            gF2Polynomial2.c = f(this.c[0], gF2Polynomial.c[0]);
            return gF2Polynomial2;
        } else if (i <= 64) {
            gF2Polynomial2.c = h(this.c, gF2Polynomial.c);
            return gF2Polynomial2;
        } else if (i <= 128) {
            gF2Polynomial2.c = d(this.c, gF2Polynomial.c);
            return gF2Polynomial2;
        } else if (i <= 256) {
            gF2Polynomial2.c = e(this.c, gF2Polynomial.c);
            return gF2Polynomial2;
        } else if (i <= 512) {
            gF2Polynomial2.c = g(this.c, gF2Polynomial.c);
            return gF2Polynomial2;
        } else {
            int i2 = g[IntegerFunctions.floorLog(i - 1)];
            int i3 = ((i2 - 1) >> 5) + 1;
            GF2Polynomial c = c(i3);
            GF2Polynomial l = l(i3);
            GF2Polynomial c2 = gF2Polynomial.c(i3);
            GF2Polynomial l2 = gF2Polynomial.l(i3);
            GF2Polynomial b = l.b(l2);
            GF2Polynomial b2 = c.b(c2);
            c.addToThis(l);
            c2.addToThis(l2);
            GF2Polynomial b3 = c.b(c2);
            gF2Polynomial2.shiftLeftAddThis(b, i2 << 1);
            gF2Polynomial2.shiftLeftAddThis(b, i2);
            gF2Polynomial2.shiftLeftAddThis(b3, i2);
            gF2Polynomial2.shiftLeftAddThis(b2, i2);
            gF2Polynomial2.addToThis(b2);
            return gF2Polynomial2;
        }
    }

    public final GF2Polynomial c(int i) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(i << 5);
        System.arraycopy(this.c, 0, gF2Polynomial.c, 0, Math.min(i, this.b));
        return gF2Polynomial;
    }

    public Object clone() {
        return new GF2Polynomial(this);
    }

    public GF2Polynomial[] divide(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[2];
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.f15364a);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int i = gF2Polynomial3.f15364a;
        int i2 = gF2Polynomial4.f15364a;
        if (i < i2) {
            gF2PolynomialArr[0] = new GF2Polynomial(0);
            gF2PolynomialArr[1] = gF2Polynomial3;
            return gF2PolynomialArr;
        }
        int i3 = i - i2;
        gF2Polynomial2.expandN(i3 + 1);
        while (i3 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(i3));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(i3);
            i3 = gF2Polynomial3.f15364a - gF2Polynomial4.f15364a;
        }
        gF2PolynomialArr[0] = gF2Polynomial2;
        gF2PolynomialArr[1] = gF2Polynomial3;
        return gF2PolynomialArr;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2Polynomial)) {
            return false;
        }
        GF2Polynomial gF2Polynomial = (GF2Polynomial) obj;
        if (this.f15364a != gF2Polynomial.f15364a) {
            return false;
        }
        for (int i = 0; i < this.b; i++) {
            if (this.c[i] != gF2Polynomial.c[i]) {
                return false;
            }
        }
        return true;
    }

    public void expandN(int i) {
        if (this.f15364a >= i) {
            return;
        }
        this.f15364a = i;
        int i2 = ((i - 1) >>> 5) + 1;
        int i3 = this.b;
        if (i3 >= i2) {
            return;
        }
        int[] iArr = this.c;
        if (iArr.length >= i2) {
            while (i3 < i2) {
                this.c[i3] = 0;
                i3++;
            }
            this.b = i2;
            return;
        }
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i3);
        this.b = i2;
        this.c = null;
        this.c = iArr2;
    }

    public GF2Polynomial gcd(GF2Polynomial gF2Polynomial) throws RuntimeException {
        if (isZero() && gF2Polynomial.isZero()) {
            throw new ArithmeticException("Both operands of gcd equal zero.");
        }
        if (isZero()) {
            return new GF2Polynomial(gF2Polynomial);
        }
        if (gF2Polynomial.isZero()) {
            return new GF2Polynomial(this);
        }
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
        GF2Polynomial gF2Polynomial5 = gF2Polynomial3;
        while (!gF2Polynomial5.isZero()) {
            GF2Polynomial gF2Polynomial6 = gF2Polynomial5;
            gF2Polynomial5 = gF2Polynomial4.remainder(gF2Polynomial5);
            gF2Polynomial4 = gF2Polynomial6;
        }
        return gF2Polynomial4;
    }

    public int getBit(int i) {
        if (i >= 0) {
            if (i > this.f15364a - 1) {
                return 0;
            }
            return (g[i & 31] & this.c[i >>> 5]) != 0 ? 1 : 0;
        }
        throw new RuntimeException();
    }

    public int getLength() {
        return this.f15364a;
    }

    public int hashCode() {
        return this.f15364a + this.c.hashCode();
    }

    public void i(int i, int[] iArr) {
        GF2Polynomial gF2Polynomial = this;
        int i2 = i >>> 5;
        int i3 = i & 31;
        int i4 = 32 - i3;
        boolean z = false;
        int i5 = (i - iArr[0]) >>> 5;
        int i6 = 32 - ((i - iArr[0]) & 31);
        int i7 = (i - iArr[1]) >>> 5;
        int i8 = 32 - ((i - iArr[1]) & 31);
        int i9 = (i - iArr[2]) >>> 5;
        int i10 = 32 - ((i - iArr[2]) & 31);
        int i11 = ((i << 1) - 2) >>> 5;
        while (i11 > i2) {
            int[] iArr2 = gF2Polynomial.c;
            long j = iArr2[i11] & 4294967295L;
            int i12 = i11 - i2;
            int i13 = i12 - 1;
            int i14 = i2;
            int i15 = i3;
            iArr2[i13] = ((int) (j << i4)) ^ iArr2[i13];
            iArr2[i12] = (int) (iArr2[i12] ^ (j >>> (32 - i4)));
            int i16 = i11 - i5;
            int i17 = i16 - 1;
            iArr2[i17] = iArr2[i17] ^ ((int) (j << i6));
            iArr2[i16] = (int) (iArr2[i16] ^ (j >>> (32 - i6)));
            int i18 = i11 - i7;
            int i19 = i18 - 1;
            iArr2[i19] = iArr2[i19] ^ ((int) (j << i8));
            iArr2[i18] = (int) (iArr2[i18] ^ (j >>> (32 - i8)));
            int i20 = i11 - i9;
            int i21 = i20 - 1;
            iArr2[i21] = iArr2[i21] ^ ((int) (j << i10));
            iArr2[i20] = (int) ((j >>> (32 - i10)) ^ iArr2[i20]);
            iArr2[i11] = 0;
            i11--;
            z = false;
            i2 = i14;
            i3 = i15;
            gF2Polynomial = this;
        }
        int i22 = i2;
        int i23 = i3;
        int[] iArr3 = gF2Polynomial.c;
        long j2 = iArr3[i22] & 4294967295L & (4294967295 << i23);
        iArr3[0] = (int) ((j2 >>> (32 - i4)) ^ iArr3[0]);
        int i24 = i22 - i5;
        int i25 = i24 - 1;
        if (i25 >= 0) {
            iArr3[i25] = iArr3[i25] ^ ((int) (j2 << i6));
        }
        iArr3[i24] = (int) (iArr3[i24] ^ (j2 >>> (32 - i6)));
        int i26 = i22 - i7;
        int i27 = i26 - 1;
        if (i27 >= 0) {
            iArr3[i27] = iArr3[i27] ^ ((int) (j2 << i8));
        }
        iArr3[i26] = (int) (iArr3[i26] ^ (j2 >>> (32 - i8)));
        int i28 = i22 - i9;
        int i29 = i28 - 1;
        if (i29 >= 0) {
            iArr3[i29] = iArr3[i29] ^ ((int) (j2 << i10));
        }
        iArr3[i28] = (int) ((j2 >>> (32 - i10)) ^ iArr3[i28]);
        iArr3[i22] = iArr3[i22] & h[i23];
        this.b = ((i - 1) >>> 5) + 1;
        this.f15364a = i;
    }

    public GF2Polynomial increase() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.increaseThis();
        return gF2Polynomial;
    }

    public void increaseThis() {
        xorBit(0);
    }

    public boolean isIrreducible() {
        if (isZero()) {
            return false;
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.reduceN();
        int i = gF2Polynomial.f15364a;
        int i2 = i - 1;
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(i, "X");
        for (int i3 = 1; i3 <= (i2 >> 1); i3++) {
            gF2Polynomial2.squareThisPreCalc();
            gF2Polynomial2 = gF2Polynomial2.remainder(gF2Polynomial);
            GF2Polynomial add = gF2Polynomial2.add(new GF2Polynomial(32, "X"));
            if (add.isZero() || !gF2Polynomial.gcd(add).isOne()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOne() {
        for (int i = 1; i < this.b; i++) {
            if (this.c[i] != 0) {
                return false;
            }
        }
        return this.c[0] == 1;
    }

    public boolean isZero() {
        if (this.f15364a == 0) {
            return true;
        }
        for (int i = 0; i < this.b; i++) {
            if (this.c[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public void j(int i, int i2) {
        int i3 = i >>> 5;
        int i4 = i & 31;
        int i5 = 32 - i4;
        int i6 = i - i2;
        int i7 = i6 >>> 5;
        int i8 = 32 - (i6 & 31);
        int i9 = ((i << 1) - 2) >>> 5;
        while (i9 > i3) {
            int[] iArr = this.c;
            long j = 4294967295L & iArr[i9];
            int i10 = i9 - i3;
            int i11 = i10 - 1;
            int i12 = i3;
            iArr[i11] = ((int) (j << i5)) ^ iArr[i11];
            iArr[i10] = (int) (iArr[i10] ^ (j >>> (32 - i5)));
            int i13 = i9 - i7;
            int i14 = i13 - 1;
            iArr[i14] = iArr[i14] ^ ((int) (j << i8));
            iArr[i13] = (int) ((j >>> (32 - i8)) ^ iArr[i13]);
            iArr[i9] = 0;
            i9--;
            i3 = i12;
        }
        int i15 = i3;
        int[] iArr2 = this.c;
        long j2 = (4294967295 << i4) & iArr2[i15] & 4294967295L;
        iArr2[0] = (int) (iArr2[0] ^ (j2 >>> (32 - i5)));
        int i16 = i15 - i7;
        int i17 = i16 - 1;
        if (i17 >= 0) {
            iArr2[i17] = iArr2[i17] ^ ((int) (j2 << i8));
        }
        iArr2[i16] = (int) ((j2 >>> (32 - i8)) ^ iArr2[i16]);
        iArr2[i15] = iArr2[i15] & h[i4];
        this.b = ((i - 1) >>> 5) + 1;
        this.f15364a = i;
    }

    public void k() {
        int i = this.b + 1;
        this.b = i;
        this.f15364a += 32;
        int[] iArr = this.c;
        if (i > iArr.length) {
            int[] iArr2 = new int[i];
            System.arraycopy(iArr, 0, iArr2, 1, i - 1);
            this.c = null;
            this.c = iArr2;
            return;
        }
        for (int i2 = i - 1; i2 >= 1; i2--) {
            int[] iArr3 = this.c;
            iArr3[i2] = iArr3[i2 - 1];
        }
        this.c[0] = 0;
    }

    public final GF2Polynomial l(int i) {
        int min = Math.min(i, this.b - i);
        GF2Polynomial gF2Polynomial = new GF2Polynomial(min << 5);
        if (this.b >= i) {
            System.arraycopy(this.c, i, gF2Polynomial.c, 0, min);
        }
        return gF2Polynomial;
    }

    public final void m() {
        int i = this.f15364a;
        if ((i & 31) != 0) {
            int[] iArr = this.c;
            int i2 = this.b - 1;
            iArr[i2] = h[i & 31] & iArr[i2];
        }
    }

    public GF2Polynomial multiply(GF2Polynomial gF2Polynomial) {
        int max = Math.max(this.f15364a, gF2Polynomial.f15364a);
        expandN(max);
        gF2Polynomial.expandN(max);
        return b(gF2Polynomial);
    }

    public GF2Polynomial multiplyClassic(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(Math.max(this.f15364a, gF2Polynomial.f15364a) << 1);
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[32];
        gF2PolynomialArr[0] = new GF2Polynomial(this);
        for (int i = 1; i <= 31; i++) {
            gF2PolynomialArr[i] = gF2PolynomialArr[i - 1].shiftLeft();
        }
        for (int i2 = 0; i2 < gF2Polynomial.b; i2++) {
            for (int i3 = 0; i3 <= 31; i3++) {
                if ((gF2Polynomial.c[i2] & g[i3]) != 0) {
                    gF2Polynomial2.xorThisBy(gF2PolynomialArr[i3]);
                }
            }
            for (int i4 = 0; i4 <= 31; i4++) {
                gF2PolynomialArr[i4].k();
            }
        }
        return gF2Polynomial2;
    }

    public GF2Polynomial quotient(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.f15364a);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int i = gF2Polynomial3.f15364a;
        int i2 = gF2Polynomial4.f15364a;
        if (i < i2) {
            return new GF2Polynomial(0);
        }
        int i3 = i - i2;
        gF2Polynomial2.expandN(i3 + 1);
        while (i3 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(i3));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(i3);
            i3 = gF2Polynomial3.f15364a - gF2Polynomial4.f15364a;
        }
        return gF2Polynomial2;
    }

    public void randomize() {
        for (int i = 0; i < this.b; i++) {
            this.c[i] = d.nextInt();
        }
        m();
    }

    public void randomize(Random random) {
        for (int i = 0; i < this.b; i++) {
            this.c[i] = random.nextInt();
        }
        m();
    }

    public void reduceN() {
        int[] iArr;
        int i = this.b;
        while (true) {
            i--;
            iArr = this.c;
            if (iArr[i] != 0 || i <= 0) {
                break;
            }
        }
        int i2 = iArr[i];
        int i3 = 0;
        while (i2 != 0) {
            i2 >>>= 1;
            i3++;
        }
        this.f15364a = (i << 5) + i3;
        this.b = i + 1;
    }

    public GF2Polynomial remainder(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial3.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial2.reduceN();
        gF2Polynomial3.reduceN();
        int i = gF2Polynomial2.f15364a;
        int i2 = gF2Polynomial3.f15364a;
        if (i < i2) {
            return gF2Polynomial2;
        }
        while (true) {
            int i3 = i - i2;
            if (i3 < 0) {
                return gF2Polynomial2;
            }
            gF2Polynomial2.subtractFromThis(gF2Polynomial3.shiftLeft(i3));
            gF2Polynomial2.reduceN();
            i = gF2Polynomial2.f15364a;
            i2 = gF2Polynomial3.f15364a;
        }
    }

    public void resetBit(int i) throws RuntimeException {
        if (i < 0) {
            throw new RuntimeException();
        }
        if (i > this.f15364a - 1) {
            return;
        }
        int[] iArr = this.c;
        int i2 = i >>> 5;
        iArr[i2] = (~g[i & 31]) & iArr[i2];
    }

    public void setBit(int i) throws RuntimeException {
        if (i < 0 || i > this.f15364a - 1) {
            throw new RuntimeException();
        }
        int[] iArr = this.c;
        int i2 = i >>> 5;
        iArr[i2] = g[i & 31] | iArr[i2];
    }

    public GF2Polynomial shiftLeft() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.f15364a + 1, this.c);
        for (int i = gF2Polynomial.b - 1; i >= 1; i--) {
            int[] iArr = gF2Polynomial.c;
            iArr[i] = iArr[i] << 1;
            iArr[i] = iArr[i] | (iArr[i - 1] >>> 31);
        }
        int[] iArr2 = gF2Polynomial.c;
        iArr2[0] = iArr2[0] << 1;
        return gF2Polynomial;
    }

    public GF2Polynomial shiftLeft(int i) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.f15364a + i, this.c);
        if (i >= 32) {
            gF2Polynomial.a(i >>> 5);
        }
        int i2 = i & 31;
        if (i2 != 0) {
            for (int i3 = gF2Polynomial.b - 1; i3 >= 1; i3--) {
                int[] iArr = gF2Polynomial.c;
                iArr[i3] = iArr[i3] << i2;
                iArr[i3] = iArr[i3] | (iArr[i3 - 1] >>> (32 - i2));
            }
            int[] iArr2 = gF2Polynomial.c;
            iArr2[0] = iArr2[0] << i2;
        }
        return gF2Polynomial;
    }

    public void shiftLeftAddThis(GF2Polynomial gF2Polynomial, int i) {
        int i2;
        if (i == 0) {
            addToThis(gF2Polynomial);
            return;
        }
        expandN(gF2Polynomial.f15364a + i);
        int i3 = i >>> 5;
        for (int i4 = gF2Polynomial.b - 1; i4 >= 0; i4--) {
            int i5 = i4 + i3;
            int i6 = i5 + 1;
            if (i6 < this.b && (i2 = i & 31) != 0) {
                int[] iArr = this.c;
                iArr[i6] = (gF2Polynomial.c[i4] >>> (32 - i2)) ^ iArr[i6];
            }
            int[] iArr2 = this.c;
            iArr2[i5] = iArr2[i5] ^ (gF2Polynomial.c[i4] << (i & 31));
        }
    }

    public void shiftLeftThis() {
        int i = this.f15364a;
        int i2 = i & 31;
        this.f15364a = i + 1;
        int i3 = this.b;
        if (i2 != 0) {
            for (int i4 = i3 - 1; i4 >= 1; i4--) {
                int[] iArr = this.c;
                iArr[i4] = iArr[i4] << 1;
                iArr[i4] = iArr[i4] | (iArr[i4 - 1] >>> 31);
            }
            int[] iArr2 = this.c;
            iArr2[0] = iArr2[0] << 1;
            return;
        }
        int i5 = i3 + 1;
        this.b = i5;
        int[] iArr3 = this.c;
        if (i5 > iArr3.length) {
            int[] iArr4 = new int[i5];
            System.arraycopy(iArr3, 0, iArr4, 0, iArr3.length);
            this.c = null;
            this.c = iArr4;
        }
        for (int i6 = this.b - 1; i6 >= 1; i6--) {
            int[] iArr5 = this.c;
            int i7 = i6 - 1;
            iArr5[i6] = iArr5[i6] | (iArr5[i7] >>> 31);
            iArr5[i7] = iArr5[i7] << 1;
        }
    }

    public GF2Polynomial shiftRight() {
        int i;
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.f15364a - 1);
        int i2 = 0;
        System.arraycopy(this.c, 0, gF2Polynomial.c, 0, gF2Polynomial.b);
        while (true) {
            i = gF2Polynomial.b;
            if (i2 > i - 2) {
                break;
            }
            int[] iArr = gF2Polynomial.c;
            iArr[i2] = iArr[i2] >>> 1;
            int i3 = i2 + 1;
            iArr[i2] = iArr[i2] | (iArr[i3] << 31);
            i2 = i3;
        }
        int[] iArr2 = gF2Polynomial.c;
        int i4 = i - 1;
        iArr2[i4] = iArr2[i4] >>> 1;
        if (i < this.b) {
            int i5 = i - 1;
            iArr2[i5] = (this.c[i] << 31) | iArr2[i5];
        }
        return gF2Polynomial;
    }

    public void shiftRightThis() {
        int i;
        int i2 = this.f15364a - 1;
        this.f15364a = i2;
        this.b = ((i2 - 1) >>> 5) + 1;
        int i3 = 0;
        while (true) {
            i = this.b;
            if (i3 > i - 2) {
                break;
            }
            int[] iArr = this.c;
            iArr[i3] = iArr[i3] >>> 1;
            int i4 = i3 + 1;
            iArr[i3] = iArr[i3] | (iArr[i4] << 31);
            i3 = i4;
        }
        int[] iArr2 = this.c;
        int i5 = i - 1;
        iArr2[i5] = iArr2[i5] >>> 1;
        if ((this.f15364a & 31) == 0) {
            int i6 = i - 1;
            iArr2[i6] = (iArr2[i] << 31) | iArr2[i6];
        }
    }

    public void squareThisBitwise() {
        if (isZero()) {
            return;
        }
        int i = this.b;
        int i2 = i << 1;
        int[] iArr = new int[i2];
        for (int i3 = i - 1; i3 >= 0; i3--) {
            int i4 = this.c[i3];
            int i5 = 1;
            for (int i6 = 0; i6 < 16; i6++) {
                if ((i4 & 1) != 0) {
                    int i7 = i3 << 1;
                    iArr[i7] = iArr[i7] | i5;
                }
                if ((65536 & i4) != 0) {
                    int i8 = (i3 << 1) + 1;
                    iArr[i8] = iArr[i8] | i5;
                }
                i5 <<= 2;
                i4 >>>= 1;
            }
        }
        this.c = null;
        this.c = iArr;
        this.b = i2;
        this.f15364a = (this.f15364a << 1) - 1;
    }

    public void squareThisPreCalc() {
        int i;
        int i2;
        if (isZero()) {
            return;
        }
        int length = this.c.length;
        int i3 = this.b;
        if (length >= (i3 << 1)) {
            for (int i4 = i3 - 1; i4 >= 0; i4--) {
                int[] iArr = this.c;
                int i5 = i4 << 1;
                short[] sArr = f;
                iArr[i5 + 1] = sArr[(iArr[i4] & 16711680) >>> 16] | (sArr[(iArr[i4] & ViewCompat.MEASURED_STATE_MASK) >>> 24] << 16);
                iArr[i5] = sArr[iArr[i4] & 255] | (sArr[(iArr[i4] & 65280) >>> 8] << 16);
            }
            i2 = this.b << 1;
        } else {
            int[] iArr2 = new int[i3 << 1];
            int i6 = 0;
            while (true) {
                i = this.b;
                if (i6 >= i) {
                    break;
                }
                int i7 = i6 << 1;
                short[] sArr2 = f;
                int[] iArr3 = this.c;
                iArr2[i7] = sArr2[iArr3[i6] & 255] | (sArr2[(iArr3[i6] & 65280) >>> 8] << 16);
                iArr2[i7 + 1] = (sArr2[(iArr3[i6] & ViewCompat.MEASURED_STATE_MASK) >>> 24] << 16) | sArr2[(iArr3[i6] & 16711680) >>> 16];
                i6++;
            }
            this.c = null;
            this.c = iArr2;
            i2 = i << 1;
        }
        this.b = i2;
        this.f15364a = (this.f15364a << 1) - 1;
    }

    public GF2Polynomial subtract(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void subtractFromThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.f15364a);
        xorThisBy(gF2Polynomial);
    }

    public boolean testBit(int i) {
        if (i >= 0) {
            if (i > this.f15364a - 1) {
                return false;
            }
            return (g[i & 31] & this.c[i >>> 5]) != 0;
        }
        throw new RuntimeException();
    }

    public byte[] toByteArray() {
        int i = ((this.f15364a - 1) >> 3) + 1;
        int i2 = i & 3;
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < (i >> 2); i3++) {
            int i4 = (i - (i3 << 2)) - 1;
            int[] iArr = this.c;
            bArr[i4] = (byte) (255 & iArr[i3]);
            bArr[i4 - 1] = (byte) ((iArr[i3] & 65280) >>> 8);
            bArr[i4 - 2] = (byte) ((iArr[i3] & 16711680) >>> 16);
            bArr[i4 - 3] = (byte) ((iArr[i3] & ViewCompat.MEASURED_STATE_MASK) >>> 24);
        }
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i2 - i5) - 1) << 3;
            bArr[i5] = (byte) ((this.c[this.b - 1] & (255 << i6)) >>> i6);
        }
        return bArr;
    }

    public BigInteger toFlexiBigInt() {
        return (this.f15364a == 0 || isZero()) ? new BigInteger(0, new byte[0]) : new BigInteger(1, toByteArray());
    }

    public int[] toIntegerArray() {
        int i = this.b;
        int[] iArr = new int[i];
        System.arraycopy(this.c, 0, iArr, 0, i);
        return iArr;
    }

    public String toString(int i) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', Constants.INAPP_POSITION_BOTTOM, Constants.INAPP_POSITION_CENTER, 'd', 'e', 'f'};
        String[] strArr = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        String str = new String();
        if (i == 16) {
            for (int i2 = this.b - 1; i2 >= 0; i2--) {
                str = ((((((((str + cArr[(this.c[i2] >>> 28) & 15]) + cArr[(this.c[i2] >>> 24) & 15]) + cArr[(this.c[i2] >>> 20) & 15]) + cArr[(this.c[i2] >>> 16) & 15]) + cArr[(this.c[i2] >>> 12) & 15]) + cArr[(this.c[i2] >>> 8) & 15]) + cArr[(this.c[i2] >>> 4) & 15]) + cArr[this.c[i2] & 15]) + HexStringBuilder.DEFAULT_SEPARATOR;
            }
        } else {
            for (int i3 = this.b - 1; i3 >= 0; i3--) {
                str = ((((((((str + strArr[(this.c[i3] >>> 28) & 15]) + strArr[(this.c[i3] >>> 24) & 15]) + strArr[(this.c[i3] >>> 20) & 15]) + strArr[(this.c[i3] >>> 16) & 15]) + strArr[(this.c[i3] >>> 12) & 15]) + strArr[(this.c[i3] >>> 8) & 15]) + strArr[(this.c[i3] >>> 4) & 15]) + strArr[this.c[i3] & 15]) + HexStringBuilder.DEFAULT_SEPARATOR;
            }
        }
        return str;
    }

    public boolean vectorMult(GF2Polynomial gF2Polynomial) throws RuntimeException {
        if (this.f15364a == gF2Polynomial.f15364a) {
            boolean z = false;
            for (int i = 0; i < this.b; i++) {
                int i2 = this.c[i] & gF2Polynomial.c[i];
                boolean[] zArr = e;
                z = (((z ^ zArr[i2 & 255]) ^ zArr[(i2 >>> 8) & 255]) ^ zArr[(i2 >>> 16) & 255]) ^ zArr[(i2 >>> 24) & 255];
            }
            return z;
        }
        throw new RuntimeException();
    }

    public GF2Polynomial xor(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2;
        int min = Math.min(this.b, gF2Polynomial.b);
        int i = 0;
        if (this.f15364a >= gF2Polynomial.f15364a) {
            gF2Polynomial2 = new GF2Polynomial(this);
            while (i < min) {
                int[] iArr = gF2Polynomial2.c;
                iArr[i] = iArr[i] ^ gF2Polynomial.c[i];
                i++;
            }
        } else {
            gF2Polynomial2 = new GF2Polynomial(gF2Polynomial);
            while (i < min) {
                int[] iArr2 = gF2Polynomial2.c;
                iArr2[i] = iArr2[i] ^ this.c[i];
                i++;
            }
        }
        gF2Polynomial2.m();
        return gF2Polynomial2;
    }

    public void xorBit(int i) throws RuntimeException {
        if (i < 0 || i > this.f15364a - 1) {
            throw new RuntimeException();
        }
        int[] iArr = this.c;
        int i2 = i >>> 5;
        iArr[i2] = g[i & 31] ^ iArr[i2];
    }

    public void xorThisBy(GF2Polynomial gF2Polynomial) {
        for (int i = 0; i < Math.min(this.b, gF2Polynomial.b); i++) {
            int[] iArr = this.c;
            iArr[i] = iArr[i] ^ gF2Polynomial.c[i];
        }
        m();
    }
}
