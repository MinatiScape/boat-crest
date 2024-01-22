package com.google.zxing.datamatrix.encoder;

import com.goodix.ble.libcomx.logger.RingLogger;
import com.google.mlkit.common.MlKitException;
import com.ido.ble.protocol.model.PressureParam;
import com.jieli.jl_rcsp.constant.Command;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;
/* loaded from: classes11.dex */
public final class ErrorCorrection {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11809a = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    public static final int[][] b = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, 240, 92, 254}, new int[]{28, 24, 185, 166, 223, RingLogger.EVT_UPDATE, 116, 255, 110, 61}, new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, Command.CMD_GET_LOW_LATENCY_SETTINGS, 97, 178, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, Command.CMD_PHONE_NUMBER_PLAY_MODE, Command.CMD_GET_LOW_LATENCY_SETTINGS, 109, 129, 94, 254, 225, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, 190, 88, 205, 109, 39, 176, 21, 155, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 251, 223, 155, 21, 5, 172, 254, 124, 12, 181, 184, 96, 50, 193}, new int[]{Primes.SMALL_FACTOR_LIMIT, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, Command.CMD_GET_LOW_LATENCY_SETTINGS, 141, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, 127, 242, 218, 130, 250, 162, 181, 102, 120, 84, 179, 220, 251, 80, 182, 229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, 225, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, Command.CMD_GET_EXTERNAL_FLASH_MSG, 111, 57, 121, 21, 1, 253, 57, 54, 101, RingLogger.EVT_UPDATE, 202, 69, 50, 150, 177, 226, 5, 9, 5}, new int[]{245, 132, 172, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, Command.CMD_GET_LOW_LATENCY_SETTINGS, 92, 253, 225, 19}, new int[]{175, 9, 223, 238, 12, 17, 220, Command.CMD_NOTIFY_DEVICE_APP_INFO, 100, 29, 175, 170, 230, 192, 215, 235, 150, 159, 36, 223, 38, 200, 132, 54, 228, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 218, 234, 117, 203, 29, 232, 144, 238, 22, 150, 201, 117, 62, MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, Command.CMD_RECEIVE_SPEECH_CANCEL, 39, 118, 202, 188, 201, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 143, 108, 196, 37, 185, 112, 134, 230, 245, 63, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 190, 250, 106, 185, PressureParam.STATE_ALL_DAY, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, 204}, new int[]{220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, Command.CMD_GET_LOW_LATENCY_SETTINGS, 136, RingLogger.EVT_UPDATE, 180, 234, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 158, 177, 68, 122, 93, Command.CMD_GET_LOW_LATENCY_SETTINGS, 15, 160, 227, 236, 66, 139, 153, 185, 202, 167, 179, 25, 220, 232, 96, Command.CMD_RECEIVE_SPEECH_CANCEL, 231, 136, 223, 239, 181, Command.CMD_PHONE_NUMBER_PLAY_MODE, 59, 52, 172, 25, 49, 232, Primes.SMALL_FACTOR_LIMIT, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186}};
    public static final int[] c = new int[256];
    public static final int[] d = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            d[i2] = i;
            c[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    public static String a(CharSequence charSequence, int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = f11809a;
            if (i2 >= iArr.length) {
                i2 = -1;
                break;
            } else if (iArr[i2] == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 >= 0) {
            int[] iArr2 = b[i2];
            char[] cArr = new char[i];
            for (int i3 = 0; i3 < i; i3++) {
                cArr[i3] = 0;
            }
            for (int i4 = 0; i4 < charSequence.length(); i4++) {
                int i5 = i - 1;
                int charAt = cArr[i5] ^ charSequence.charAt(i4);
                while (i5 > 0) {
                    if (charAt != 0 && iArr2[i5] != 0) {
                        char c2 = cArr[i5 - 1];
                        int[] iArr3 = d;
                        int[] iArr4 = c;
                        cArr[i5] = (char) (c2 ^ iArr3[(iArr4[charAt] + iArr4[iArr2[i5]]) % 255]);
                    } else {
                        cArr[i5] = cArr[i5 - 1];
                    }
                    i5--;
                }
                if (charAt != 0 && iArr2[0] != 0) {
                    int[] iArr5 = d;
                    int[] iArr6 = c;
                    cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[iArr2[0]]) % 255];
                } else {
                    cArr[0] = 0;
                }
            }
            char[] cArr2 = new char[i];
            for (int i6 = 0; i6 < i; i6++) {
                cArr2[i6] = cArr[(i - i6) - 1];
            }
            return String.valueOf(cArr2);
        }
        throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(i)));
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() == symbolInfo.getDataCapacity()) {
            StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
            sb.append(str);
            int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
            if (interleavedBlockCount == 1) {
                sb.append(a(str, symbolInfo.getErrorCodewords()));
            } else {
                sb.setLength(sb.capacity());
                int[] iArr = new int[interleavedBlockCount];
                int[] iArr2 = new int[interleavedBlockCount];
                int i = 0;
                while (i < interleavedBlockCount) {
                    int i2 = i + 1;
                    iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i2);
                    iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i2);
                    i = i2;
                }
                for (int i3 = 0; i3 < interleavedBlockCount; i3++) {
                    StringBuilder sb2 = new StringBuilder(iArr[i3]);
                    for (int i4 = i3; i4 < symbolInfo.getDataCapacity(); i4 += interleavedBlockCount) {
                        sb2.append(str.charAt(i4));
                    }
                    String a2 = a(sb2.toString(), iArr2[i3]);
                    int i5 = 0;
                    int i6 = i3;
                    while (i6 < iArr2[i3] * interleavedBlockCount) {
                        sb.setCharAt(symbolInfo.getDataCapacity() + i6, a2.charAt(i5));
                        i6 += interleavedBlockCount;
                        i5++;
                    }
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    }
}
