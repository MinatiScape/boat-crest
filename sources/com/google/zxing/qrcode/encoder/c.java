package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;
import org.bouncycastle.crypto.tls.CipherSuite;
/* loaded from: classes11.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static final int[][] f11888a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    public static final int[][] b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    public static final int[][] c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    public static final int[][] d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    public static void a(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, int i, ByteMatrix byteMatrix) throws WriterException {
        c(byteMatrix);
        d(version, byteMatrix);
        l(errorCorrectionLevel, i, byteMatrix);
        s(version, byteMatrix);
        f(bitArray, i, byteMatrix);
    }

    public static int b(int i, int i2) {
        if (i2 != 0) {
            int n = n(i2);
            int i3 = i << (n - 1);
            while (n(i3) >= n) {
                i3 ^= i2 << (n(i3) - n);
            }
            return i3;
        }
        throw new IllegalArgumentException("0 polynomial");
    }

    public static void c(ByteMatrix byteMatrix) {
        byteMatrix.clear((byte) -1);
    }

    public static void d(Version version, ByteMatrix byteMatrix) throws WriterException {
        j(byteMatrix);
        e(byteMatrix);
        r(version, byteMatrix);
        k(byteMatrix);
    }

    public static void e(ByteMatrix byteMatrix) throws WriterException {
        if (byteMatrix.get(8, byteMatrix.getHeight() - 8) != 0) {
            byteMatrix.set(8, byteMatrix.getHeight() - 8, 1);
            return;
        }
        throw new WriterException();
    }

    public static void f(BitArray bitArray, int i, ByteMatrix byteMatrix) throws WriterException {
        boolean z;
        int width = byteMatrix.getWidth() - 1;
        int height = byteMatrix.getHeight() - 1;
        int i2 = -1;
        int i3 = 0;
        while (width > 0) {
            if (width == 6) {
                width--;
            }
            while (height >= 0 && height < byteMatrix.getHeight()) {
                for (int i4 = 0; i4 < 2; i4++) {
                    int i5 = width - i4;
                    if (o(byteMatrix.get(i5, height))) {
                        if (i3 < bitArray.getSize()) {
                            z = bitArray.get(i3);
                            i3++;
                        } else {
                            z = false;
                        }
                        if (i != -1 && b.f(i, i5, height)) {
                            z = !z;
                        }
                        byteMatrix.set(i5, height, z);
                    }
                }
                height += i2;
            }
            i2 = -i2;
            height += i2;
            width -= 2;
        }
        if (i3 == bitArray.getSize()) {
            return;
        }
        throw new WriterException("Not all bits consumed: " + i3 + '/' + bitArray.getSize());
    }

    public static void g(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        for (int i3 = 0; i3 < 8; i3++) {
            int i4 = i + i3;
            if (o(byteMatrix.get(i4, i2))) {
                byteMatrix.set(i4, i2, 0);
            } else {
                throw new WriterException();
            }
        }
    }

    public static void h(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 5; i3++) {
            int[] iArr = b[i3];
            for (int i4 = 0; i4 < 5; i4++) {
                byteMatrix.set(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    public static void i(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 7; i3++) {
            int[] iArr = f11888a[i3];
            for (int i4 = 0; i4 < 7; i4++) {
                byteMatrix.set(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    public static void j(ByteMatrix byteMatrix) throws WriterException {
        int length = f11888a[0].length;
        i(0, 0, byteMatrix);
        i(byteMatrix.getWidth() - length, 0, byteMatrix);
        i(0, byteMatrix.getWidth() - length, byteMatrix);
        g(0, 7, byteMatrix);
        g(byteMatrix.getWidth() - 8, 7, byteMatrix);
        g(0, byteMatrix.getWidth() - 8, byteMatrix);
        m(7, 0, byteMatrix);
        m((byteMatrix.getHeight() - 7) - 1, 0, byteMatrix);
        m(7, byteMatrix.getHeight() - 7, byteMatrix);
    }

    public static void k(ByteMatrix byteMatrix) {
        int i = 8;
        while (i < byteMatrix.getWidth() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (o(byteMatrix.get(i, 6))) {
                byteMatrix.set(i, 6, i3);
            }
            if (o(byteMatrix.get(6, i))) {
                byteMatrix.set(6, i, i3);
            }
            i = i2;
        }
    }

    public static void l(ErrorCorrectionLevel errorCorrectionLevel, int i, ByteMatrix byteMatrix) throws WriterException {
        int height;
        BitArray bitArray = new BitArray();
        p(errorCorrectionLevel, i, bitArray);
        for (int i2 = 0; i2 < bitArray.getSize(); i2++) {
            boolean z = bitArray.get((bitArray.getSize() - 1) - i2);
            int[] iArr = d[i2];
            byteMatrix.set(iArr[0], iArr[1], z);
            int i3 = 8;
            if (i2 < 8) {
                height = 8;
                i3 = (byteMatrix.getWidth() - i2) - 1;
            } else {
                height = (byteMatrix.getHeight() - 7) + (i2 - 8);
            }
            byteMatrix.set(i3, height, z);
        }
    }

    public static void m(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        for (int i3 = 0; i3 < 7; i3++) {
            int i4 = i2 + i3;
            if (o(byteMatrix.get(i, i4))) {
                byteMatrix.set(i, i4, 0);
            } else {
                throw new WriterException();
            }
        }
    }

    public static int n(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    public static boolean o(int i) {
        return i == -1;
    }

    public static void p(ErrorCorrectionLevel errorCorrectionLevel, int i, BitArray bitArray) throws WriterException {
        if (QRCode.isValidMaskPattern(i)) {
            int bits = (errorCorrectionLevel.getBits() << 3) | i;
            bitArray.appendBits(bits, 5);
            bitArray.appendBits(b(bits, 1335), 10);
            BitArray bitArray2 = new BitArray();
            bitArray2.appendBits(21522, 15);
            bitArray.xor(bitArray2);
            if (bitArray.getSize() == 15) {
                return;
            }
            throw new WriterException("should not happen but we got: " + bitArray.getSize());
        }
        throw new WriterException("Invalid mask pattern");
    }

    public static void q(Version version, BitArray bitArray) throws WriterException {
        bitArray.appendBits(version.getVersionNumber(), 6);
        bitArray.appendBits(b(version.getVersionNumber(), 7973), 12);
        if (bitArray.getSize() == 18) {
            return;
        }
        throw new WriterException("should not happen but we got: " + bitArray.getSize());
    }

    public static void r(Version version, ByteMatrix byteMatrix) {
        if (version.getVersionNumber() < 2) {
            return;
        }
        int[] iArr = c[version.getVersionNumber() - 1];
        for (int i : iArr) {
            if (i >= 0) {
                for (int i2 : iArr) {
                    if (i2 >= 0 && o(byteMatrix.get(i2, i))) {
                        h(i2 - 2, i - 2, byteMatrix);
                    }
                }
            }
        }
    }

    public static void s(Version version, ByteMatrix byteMatrix) throws WriterException {
        if (version.getVersionNumber() < 7) {
            return;
        }
        BitArray bitArray = new BitArray();
        q(version, bitArray);
        int i = 17;
        for (int i2 = 0; i2 < 6; i2++) {
            for (int i3 = 0; i3 < 3; i3++) {
                boolean z = bitArray.get(i);
                i--;
                byteMatrix.set(i2, (byteMatrix.getHeight() - 11) + i3, z);
                byteMatrix.set((byteMatrix.getHeight() - 11) + i3, i2, z);
            }
        }
    }
}
