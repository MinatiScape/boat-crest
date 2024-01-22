package org.bouncycastle.pqc.crypto.sphincs;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
/* loaded from: classes13.dex */
public class b {
    public static void a(byte[] bArr, byte[] bArr2) {
        d.b(bArr, 0, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, bArr2, 0);
    }

    public static int b(a aVar, byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        byte[] bArr6 = new byte[2097152];
        byte[] bArr7 = new byte[4194272];
        a(bArr6, bArr3);
        for (int i2 = 0; i2 < 65536; i2++) {
            aVar.d(bArr7, (65535 + i2) * 32, bArr6, i2 * 32);
        }
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = 16 - i3;
            long j = (1 << i4) - 1;
            int i5 = 1 << (i4 - 1);
            long j2 = i5 - 1;
            int i6 = 0;
            while (i6 < i5) {
                aVar.c(bArr7, (int) ((i6 + j2) * 32), bArr7, (int) (((i6 * 2) + j) * 32), bArr4, i3 * 2 * 32);
                i6++;
                i5 = i5;
                j2 = j2;
            }
        }
        int i7 = 2016;
        int i8 = i;
        while (i7 < 4064) {
            bArr[i8] = bArr7[i7];
            i7++;
            i8++;
        }
        for (int i9 = 0; i9 < 32; i9++) {
            int i10 = i9 * 2;
            int i11 = (bArr5[i10] & 255) + ((bArr5[i10 + 1] & 255) << 8);
            int i12 = 0;
            while (i12 < 32) {
                bArr[i8] = bArr6[(i11 * 32) + i12];
                i12++;
                i8++;
            }
            int i13 = i11 + 65535;
            for (int i14 = 0; i14 < 10; i14++) {
                int i15 = (i13 & 1) != 0 ? i13 + 1 : i13 - 1;
                int i16 = 0;
                while (i16 < 32) {
                    bArr[i8] = bArr7[(i15 * 32) + i16];
                    i16++;
                    i8++;
                }
                i13 = (i15 - 1) / 2;
            }
        }
        for (int i17 = 0; i17 < 32; i17++) {
            bArr2[i17] = bArr7[i17];
        }
        return 13312;
    }

    public static int c(a aVar, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        int i2;
        byte[] bArr5 = new byte[1024];
        int i3 = i + 2048;
        int i4 = 0;
        while (i4 < 32) {
            int i5 = i4 * 2;
            int i6 = (bArr4[i5] & 255) + ((bArr4[i5 + 1] & 255) << 8);
            if ((i6 & 1) == 0) {
                aVar.d(bArr5, 0, bArr2, i3);
                for (int i7 = 0; i7 < 32; i7++) {
                    bArr5[i7 + 32] = bArr2[i3 + 32 + i7];
                }
            } else {
                aVar.d(bArr5, 32, bArr2, i3);
                for (int i8 = 0; i8 < 32; i8++) {
                    bArr5[i8] = bArr2[i3 + 32 + i8];
                }
            }
            int i9 = i3 + 64;
            int i10 = 1;
            while (i10 < 10) {
                int i11 = i6 >>> 1;
                if ((i11 & 1) == 0) {
                    i2 = i10;
                    aVar.c(bArr5, 0, bArr5, 0, bArr3, (i10 - 1) * 2 * 32);
                    for (int i12 = 0; i12 < 32; i12++) {
                        bArr5[i12 + 32] = bArr2[i9 + i12];
                    }
                } else {
                    i2 = i10;
                    aVar.c(bArr5, 32, bArr5, 0, bArr3, (i2 - 1) * 2 * 32);
                    for (int i13 = 0; i13 < 32; i13++) {
                        bArr5[i13] = bArr2[i9 + i13];
                    }
                }
                i9 += 32;
                i10 = i2 + 1;
                i6 = i11;
            }
            int i14 = i6 >>> 1;
            aVar.c(bArr5, 0, bArr5, 0, bArr3, com.veryfit.multi.nativeprotocol.b.q2);
            for (int i15 = 0; i15 < 32; i15++) {
                if (bArr2[(i14 * 32) + i + i15] != bArr5[i15]) {
                    for (int i16 = 0; i16 < 32; i16++) {
                        bArr[i16] = 0;
                    }
                    return -1;
                }
            }
            i4++;
            i3 = i9;
        }
        for (int i17 = 0; i17 < 32; i17++) {
            aVar.c(bArr5, i17 * 32, bArr2, i + (i17 * 2 * 32), bArr3, 640);
        }
        for (int i18 = 0; i18 < 16; i18++) {
            aVar.c(bArr5, i18 * 32, bArr5, i18 * 2 * 32, bArr3, TypedValues.TransitionType.TYPE_AUTO_TRANSITION);
        }
        for (int i19 = 0; i19 < 8; i19++) {
            aVar.c(bArr5, i19 * 32, bArr5, i19 * 2 * 32, bArr3, 768);
        }
        for (int i20 = 0; i20 < 4; i20++) {
            aVar.c(bArr5, i20 * 32, bArr5, i20 * 2 * 32, bArr3, 832);
        }
        for (int i21 = 0; i21 < 2; i21++) {
            aVar.c(bArr5, i21 * 32, bArr5, i21 * 2 * 32, bArr3, 896);
        }
        aVar.c(bArr, 0, bArr5, 0, bArr3, 960);
        return 0;
    }
}
