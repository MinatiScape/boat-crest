package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class Encoder {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11884a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11885a;

        static {
            int[] iArr = new int[Mode.values().length];
            f11885a = iArr;
            try {
                iArr[Mode.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11885a[Mode.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11885a[Mode.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11885a[Mode.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void a(String str, BitArray bitArray, String str2) throws WriterException {
        try {
            for (byte b : str.getBytes(str2)) {
                bitArray.appendBits(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    public static void b(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int o = o(charSequence.charAt(i));
            if (o == -1) {
                throw new WriterException();
            }
            int i2 = i + 1;
            if (i2 < length) {
                int o2 = o(charSequence.charAt(i2));
                if (o2 != -1) {
                    bitArray.appendBits((o * 45) + o2, 11);
                    i += 2;
                } else {
                    throw new WriterException();
                }
            } else {
                bitArray.appendBits(o, 6);
                i = i2;
            }
        }
    }

    public static void c(String str, Mode mode, BitArray bitArray, String str2) throws WriterException {
        int i = a.f11885a[mode.ordinal()];
        if (i == 1) {
            h(str, bitArray);
        } else if (i == 2) {
            b(str, bitArray);
        } else if (i == 3) {
            a(str, bitArray, str2);
        } else if (i == 4) {
            e(str, bitArray);
        } else {
            throw new WriterException("Invalid mode: ".concat(String.valueOf(mode)));
        }
    }

    public static Mode chooseMode(String str) {
        return l(str, null);
    }

    public static void d(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003c A[LOOP:0: B:6:0x000f->B:19:0x003c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(java.lang.String r6, com.google.zxing.common.BitArray r7) throws com.google.zxing.WriterException {
        /*
            java.lang.String r0 = "Shift_JIS"
            byte[] r6 = r6.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L5c
            int r0 = r6.length
            int r0 = r0 % 2
            if (r0 != 0) goto L54
            int r0 = r6.length
            int r0 = r0 + (-1)
            r1 = 0
        Lf:
            if (r1 >= r0) goto L53
            r2 = r6[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r1 + 1
            r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r3
            r3 = 33088(0x8140, float:4.6366E-41)
            r4 = -1
            if (r2 < r3) goto L2b
            r5 = 40956(0x9ffc, float:5.7392E-41)
            if (r2 > r5) goto L2b
        L29:
            int r2 = r2 - r3
            goto L3a
        L2b:
            r3 = 57408(0xe040, float:8.0446E-41)
            if (r2 < r3) goto L39
            r3 = 60351(0xebbf, float:8.457E-41)
            if (r2 > r3) goto L39
            r3 = 49472(0xc140, float:6.9325E-41)
            goto L29
        L39:
            r2 = r4
        L3a:
            if (r2 == r4) goto L4b
            int r3 = r2 >> 8
            int r3 = r3 * 192
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r2
            r2 = 13
            r7.appendBits(r3, r2)
            int r1 = r1 + 2
            goto Lf
        L4b:
            com.google.zxing.WriterException r6 = new com.google.zxing.WriterException
            java.lang.String r7 = "Invalid byte sequence"
            r6.<init>(r7)
            throw r6
        L53:
            return
        L54:
            com.google.zxing.WriterException r6 = new com.google.zxing.WriterException
            java.lang.String r7 = "Kanji byte size not even"
            r6.<init>(r7)
            throw r6
        L5c:
            r6 = move-exception
            com.google.zxing.WriterException r7 = new com.google.zxing.WriterException
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.e(java.lang.String, com.google.zxing.common.BitArray):void");
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return encode(str, errorCorrectionLevel, null);
    }

    public static void f(int i, Version version, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(version);
        int i2 = 1 << characterCountBits;
        if (i < i2) {
            bitArray.appendBits(i, characterCountBits);
            return;
        }
        throw new WriterException(i + " is bigger than " + (i2 - 1));
    }

    public static void g(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    public static void h(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                bitArray.appendBits((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    bitArray.appendBits((charAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    bitArray.appendBits(charAt, 4);
                }
            }
        }
    }

    public static int i(Mode mode, BitArray bitArray, BitArray bitArray2, Version version) {
        return bitArray.getSize() + mode.getCharacterCountBits(version) + bitArray2.getSize();
    }

    public static int j(ByteMatrix byteMatrix) {
        return b.a(byteMatrix) + b.c(byteMatrix) + b.d(byteMatrix) + b.e(byteMatrix);
    }

    public static int k(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            c.a(bitArray, errorCorrectionLevel, version, i3, byteMatrix);
            int j = j(byteMatrix);
            if (j < i) {
                i2 = i3;
                i = j;
            }
        }
        return i2;
    }

    public static Mode l(String str, String str2) {
        if ("Shift_JIS".equals(str2) && r(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (o(charAt) == -1) {
                return Mode.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z2) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    public static Version m(int i, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            Version versionForNumber = Version.getVersionForNumber(i2);
            if (u(i, versionForNumber, errorCorrectionLevel)) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    public static byte[] n(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length + i];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    public static int o(int i) {
        int[] iArr = f11884a;
        if (i < iArr.length) {
            return iArr[i];
        }
        return -1;
    }

    public static void p(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 < i3) {
            int i5 = i % i3;
            int i6 = i3 - i5;
            int i7 = i / i3;
            int i8 = i7 + 1;
            int i9 = i2 / i3;
            int i10 = i9 + 1;
            int i11 = i7 - i9;
            int i12 = i8 - i10;
            if (i11 != i12) {
                throw new WriterException("EC bytes mismatch");
            }
            if (i3 != i6 + i5) {
                throw new WriterException("RS blocks mismatch");
            }
            if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
                throw new WriterException("Total bytes mismatch");
            }
            if (i4 < i6) {
                iArr[0] = i9;
                iArr2[0] = i11;
                return;
            }
            iArr[0] = i10;
            iArr2[0] = i12;
            return;
        }
        throw new WriterException("Block ID too large");
    }

    public static BitArray q(BitArray bitArray, int i, int i2, int i3) throws WriterException {
        if (bitArray.getSizeInBytes() == i2) {
            ArrayList<com.google.zxing.qrcode.encoder.a> arrayList = new ArrayList(i3);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                p(i, i2, i3, i7, iArr, iArr2);
                int i8 = iArr[0];
                byte[] bArr = new byte[i8];
                bitArray.toBytes(i4 << 3, bArr, 0, i8);
                byte[] n = n(bArr, iArr2[0]);
                arrayList.add(new com.google.zxing.qrcode.encoder.a(bArr, n));
                i5 = Math.max(i5, i8);
                i6 = Math.max(i6, n.length);
                i4 += iArr[0];
            }
            if (i2 == i4) {
                BitArray bitArray2 = new BitArray();
                for (int i9 = 0; i9 < i5; i9++) {
                    for (com.google.zxing.qrcode.encoder.a aVar : arrayList) {
                        byte[] a2 = aVar.a();
                        if (i9 < a2.length) {
                            bitArray2.appendBits(a2[i9], 8);
                        }
                    }
                }
                for (int i10 = 0; i10 < i6; i10++) {
                    for (com.google.zxing.qrcode.encoder.a aVar2 : arrayList) {
                        byte[] b = aVar2.b();
                        if (i10 < b.length) {
                            bitArray2.appendBits(b[i10], 8);
                        }
                    }
                }
                if (i == bitArray2.getSizeInBytes()) {
                    return bitArray2;
                }
                throw new WriterException("Interleaving error: " + i + " and " + bitArray2.getSizeInBytes() + " differ.");
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    public static boolean r(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    public static Version s(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, BitArray bitArray, BitArray bitArray2) throws WriterException {
        return m(i(mode, bitArray, bitArray2, m(i(mode, bitArray, bitArray2, Version.getVersionForNumber(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }

    public static void t(int i, BitArray bitArray) throws WriterException {
        int i2 = i << 3;
        if (bitArray.getSize() <= i2) {
            for (int i3 = 0; i3 < 4 && bitArray.getSize() < i2; i3++) {
                bitArray.appendBit(false);
            }
            int size = bitArray.getSize() & 7;
            if (size > 0) {
                while (size < 8) {
                    bitArray.appendBit(false);
                    size++;
                }
            }
            int sizeInBytes = i - bitArray.getSizeInBytes();
            for (int i4 = 0; i4 < sizeInBytes; i4++) {
                bitArray.appendBits((i4 & 1) == 0 ? 236 : 17, 8);
            }
            if (bitArray.getSize() != i2) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        throw new WriterException("data bits cannot fit in the QR Code" + bitArray.getSize() + " > " + i2);
    }

    public static boolean u(int i, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        return version.getTotalCodewords() - version.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (i + 7) / 8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00fc, code lost:
        if (com.google.zxing.qrcode.encoder.QRCode.isValidMaskPattern(r9) != false) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.zxing.qrcode.encoder.QRCode encode(java.lang.String r7, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r8, java.util.Map<com.google.zxing.EncodeHintType, ?> r9) throws com.google.zxing.WriterException {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.encode(java.lang.String, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.qrcode.encoder.QRCode");
    }
}
