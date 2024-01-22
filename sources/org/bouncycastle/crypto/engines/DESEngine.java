package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.realsil.sdk.dfu.DfuException;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class DESEngine implements BlockCipher {
    public static final int BLOCK_SIZE = 8;
    public static final short[] b = {128, 64, 32, 16, 8, 4, 2, 1};
    public static final int[] c = {8388608, 4194304, 2097152, 1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
    public static final byte[] d = {56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3};
    public static final byte[] e = {1, 2, 4, 6, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28};
    public static final byte[] f = {13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31};
    public static final int[] g = {16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, DfuException.ERROR_GATT_INVALID_PDU, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, InputDeviceCompat.SOURCE_TRACKBALL, 16777220, 16777220, InputDeviceCompat.SOURCE_TRACKBALL, 0, DfuException.ERROR_GATT_INVALID_PDU, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, InputDeviceCompat.SOURCE_TRACKBALL, 16842752, 16778244, 16777220, DfuException.ERROR_GATT_INVALID_PDU, 66564, 16843776, DfuException.ERROR_GATT_INVALID_PDU, 16778240, 16778240, 0, InputDeviceCompat.SOURCE_TRACKBALL, 66560, 0, 16842756};
    public static final int[] h = {-2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, Integer.MIN_VALUE, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, Integer.MIN_VALUE, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, Integer.MIN_VALUE, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, Integer.MIN_VALUE, -2146435040, -2146402272, 1081344};
    public static final int[] i = {520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584};
    public static final int[] j = {8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928};
    public static final int[] k = {256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080};
    public static final int[] l = {536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, PKIFailureInfo.duplicateCertReq, ErrorCode.SUB_ERR_UPGRADE_UNKNOWN, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, ErrorCode.SUB_ERR_UPGRADE_UNKNOWN, 4210688, 541081600, PKIFailureInfo.duplicateCertReq, 536887296, 16, 541065232, 4210688, 541081616, 4194304, ErrorCode.SUB_ERR_UPGRADE_UNKNOWN, 536870928, 4194304, 536887296, PKIFailureInfo.duplicateCertReq, ErrorCode.SUB_ERR_UPGRADE_UNKNOWN, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, PKIFailureInfo.duplicateCertReq, 4194320, 536887312};
    public static final int[] m = {2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154};
    public static final int[] n = {268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, SwipeableItemConstants.REACTION_CAN_NOT_SWIPE_BOTH_V_WITH_RUBBER_BAND_EFFECT, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, SwipeableItemConstants.REACTION_CAN_NOT_SWIPE_BOTH_V_WITH_RUBBER_BAND_EFFECT, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, SwipeableItemConstants.REACTION_CAN_NOT_SWIPE_BOTH_V_WITH_RUBBER_BAND_EFFECT, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, SwipeableItemConstants.REACTION_CAN_NOT_SWIPE_BOTH_V_WITH_RUBBER_BAND_EFFECT, 268435456, 268701696};

    /* renamed from: a  reason: collision with root package name */
    public int[] f14673a = null;

    public void desFunc(int[] iArr, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = ((bArr[i2 + 0] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8) | (bArr[i2 + 3] & 255);
        int i5 = ((bArr[i2 + 4] & 255) << 24) | ((bArr[i2 + 5] & 255) << 16) | ((bArr[i2 + 6] & 255) << 8) | (bArr[i2 + 7] & 255);
        int i6 = ((i4 >>> 4) ^ i5) & 252645135;
        int i7 = i5 ^ i6;
        int i8 = i4 ^ (i6 << 4);
        int i9 = ((i8 >>> 16) ^ i7) & 65535;
        int i10 = i7 ^ i9;
        int i11 = i8 ^ (i9 << 16);
        int i12 = ((i10 >>> 2) ^ i11) & 858993459;
        int i13 = i11 ^ i12;
        int i14 = i10 ^ (i12 << 2);
        int i15 = ((i14 >>> 8) ^ i13) & 16711935;
        int i16 = i13 ^ i15;
        int i17 = i14 ^ (i15 << 8);
        int i18 = (((i17 >>> 31) & 1) | (i17 << 1)) & (-1);
        int i19 = (i16 ^ i18) & (-1431655766);
        int i20 = i16 ^ i19;
        int i21 = i18 ^ i19;
        int i22 = (((i20 >>> 31) & 1) | (i20 << 1)) & (-1);
        for (int i23 = 0; i23 < 8; i23++) {
            int i24 = i23 * 4;
            int i25 = ((i21 << 28) | (i21 >>> 4)) ^ iArr[i24 + 0];
            int[] iArr2 = m;
            int i26 = iArr2[i25 & 63];
            int[] iArr3 = k;
            int i27 = i26 | iArr3[(i25 >>> 8) & 63];
            int[] iArr4 = i;
            int i28 = i27 | iArr4[(i25 >>> 16) & 63];
            int[] iArr5 = g;
            int i29 = iArr5[(i25 >>> 24) & 63] | i28;
            int i30 = iArr[i24 + 1] ^ i21;
            int[] iArr6 = n;
            int i31 = i29 | iArr6[i30 & 63];
            int[] iArr7 = l;
            int i32 = i31 | iArr7[(i30 >>> 8) & 63];
            int[] iArr8 = j;
            int i33 = i32 | iArr8[(i30 >>> 16) & 63];
            int[] iArr9 = h;
            i22 ^= i33 | iArr9[(i30 >>> 24) & 63];
            int i34 = ((i22 << 28) | (i22 >>> 4)) ^ iArr[i24 + 2];
            int i35 = iArr5[(i34 >>> 24) & 63];
            int i36 = iArr[i24 + 3] ^ i22;
            i21 ^= ((((i35 | ((iArr2[i34 & 63] | iArr3[(i34 >>> 8) & 63]) | iArr4[(i34 >>> 16) & 63])) | iArr6[i36 & 63]) | iArr7[(i36 >>> 8) & 63]) | iArr8[(i36 >>> 16) & 63]) | iArr9[(i36 >>> 24) & 63];
        }
        int i37 = (i21 >>> 1) | (i21 << 31);
        int i38 = (i22 ^ i37) & (-1431655766);
        int i39 = i22 ^ i38;
        int i40 = i37 ^ i38;
        int i41 = (i39 >>> 1) | (i39 << 31);
        int i42 = ((i41 >>> 8) ^ i40) & 16711935;
        int i43 = i40 ^ i42;
        int i44 = i41 ^ (i42 << 8);
        int i45 = ((i44 >>> 2) ^ i43) & 858993459;
        int i46 = i43 ^ i45;
        int i47 = i44 ^ (i45 << 2);
        int i48 = ((i46 >>> 16) ^ i47) & 65535;
        int i49 = i47 ^ i48;
        int i50 = i46 ^ (i48 << 16);
        int i51 = ((i50 >>> 4) ^ i49) & 252645135;
        int i52 = i49 ^ i51;
        int i53 = i50 ^ (i51 << 4);
        bArr2[i3 + 0] = (byte) ((i53 >>> 24) & 255);
        bArr2[i3 + 1] = (byte) ((i53 >>> 16) & 255);
        bArr2[i3 + 2] = (byte) ((i53 >>> 8) & 255);
        bArr2[i3 + 3] = (byte) (i53 & 255);
        bArr2[i3 + 4] = (byte) ((i52 >>> 24) & 255);
        bArr2[i3 + 5] = (byte) ((i52 >>> 16) & 255);
        bArr2[i3 + 6] = (byte) ((i52 >>> 8) & 255);
        bArr2[i3 + 7] = (byte) (i52 & 255);
    }

    public int[] generateWorkingKey(boolean z, byte[] bArr) {
        int i2;
        int[] iArr = new int[32];
        boolean[] zArr = new boolean[56];
        boolean[] zArr2 = new boolean[56];
        int i3 = 0;
        while (true) {
            boolean z2 = true;
            if (i3 >= 56) {
                break;
            }
            byte b2 = d[i3];
            if ((b[b2 & 7] & bArr[b2 >>> 3]) == 0) {
                z2 = false;
            }
            zArr[i3] = z2;
            i3++;
        }
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = z ? i4 << 1 : (15 - i4) << 1;
            int i6 = i5 + 1;
            iArr[i6] = 0;
            iArr[i5] = 0;
            int i7 = 0;
            while (true) {
                if (i7 >= 28) {
                    break;
                }
                int i8 = e[i4] + i7;
                if (i8 < 28) {
                    zArr2[i7] = zArr[i8];
                } else {
                    zArr2[i7] = zArr[i8 - 28];
                }
                i7++;
            }
            for (i2 = 28; i2 < 56; i2++) {
                int i9 = e[i4] + i2;
                if (i9 < 56) {
                    zArr2[i2] = zArr[i9];
                } else {
                    zArr2[i2] = zArr[i9 - 28];
                }
            }
            for (int i10 = 0; i10 < 24; i10++) {
                byte[] bArr2 = f;
                if (zArr2[bArr2[i10]]) {
                    iArr[i5] = iArr[i5] | c[i10];
                }
                if (zArr2[bArr2[i10 + 24]]) {
                    iArr[i6] = iArr[i6] | c[i10];
                }
            }
        }
        for (int i11 = 0; i11 != 32; i11 += 2) {
            int i12 = iArr[i11];
            int i13 = i11 + 1;
            int i14 = iArr[i13];
            iArr[i11] = ((16515072 & i14) >>> 10) | ((i12 & 16515072) << 6) | ((i12 & 4032) << 10) | ((i14 & 4032) >>> 6);
            iArr[i13] = ((i12 & 63) << 16) | ((i12 & 258048) << 12) | ((258048 & i14) >>> 4) | (i14 & 63);
        }
        return iArr;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to DES init - " + cipherParameters.getClass().getName());
        }
        KeyParameter keyParameter = (KeyParameter) cipherParameters;
        if (keyParameter.getKey().length > 8) {
            throw new IllegalArgumentException("DES key too long - should be 8 bytes");
        }
        this.f14673a = generateWorkingKey(z, keyParameter.getKey());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = this.f14673a;
        if (iArr != null) {
            if (i2 + 8 <= bArr.length) {
                if (i3 + 8 <= bArr2.length) {
                    desFunc(iArr, bArr, i2, bArr2, i3);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("DES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
