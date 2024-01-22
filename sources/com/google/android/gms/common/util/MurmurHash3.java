package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes6.dex */
public class MurmurHash3 {
    @KeepForSdk
    public static int murmurhash3_x86_32(@NonNull byte[] bArr, int i, int i2, int i3) {
        int i4;
        int i5 = i;
        while (true) {
            i4 = (i2 & (-4)) + i;
            if (i5 >= i4) {
                break;
            }
            int i6 = ((bArr[i5] & 255) | ((bArr[i5 + 1] & 255) << 8) | ((bArr[i5 + 2] & 255) << 16) | (bArr[i5 + 3] << 24)) * (-862048943);
            int i7 = i3 ^ (((i6 >>> 17) | (i6 << 15)) * 461845907);
            i3 = (((i7 >>> 19) | (i7 << 13)) * 5) - 430675100;
            i5 += 4;
        }
        int i8 = i2 & 3;
        if (i8 != 1) {
            if (i8 != 2) {
                r0 = i8 == 3 ? (bArr[i4 + 2] & 255) << 16 : 0;
                int i9 = i3 ^ i2;
                int i10 = (i9 ^ (i9 >>> 16)) * (-2048144789);
                int i11 = (i10 ^ (i10 >>> 13)) * (-1028477387);
                return i11 ^ (i11 >>> 16);
            }
            r0 |= (bArr[i4 + 1] & 255) << 8;
        }
        int i12 = ((bArr[i4] & 255) | r0) * (-862048943);
        i3 ^= ((i12 >>> 17) | (i12 << 15)) * 461845907;
        int i92 = i3 ^ i2;
        int i102 = (i92 ^ (i92 >>> 16)) * (-2048144789);
        int i112 = (i102 ^ (i102 >>> 13)) * (-1028477387);
        return i112 ^ (i112 >>> 16);
    }
}
