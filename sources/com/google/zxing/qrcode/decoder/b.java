package com.google.zxing.qrcode.decoder;

import com.google.zxing.qrcode.decoder.Version;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final int f11875a;
    public final byte[] b;

    public b(int i, byte[] bArr) {
        this.f11875a = i;
        this.b = bArr;
    }

    public static b[] b(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length == version.getTotalCodewords()) {
            Version.ECBlocks eCBlocksForLevel = version.getECBlocksForLevel(errorCorrectionLevel);
            Version.ECB[] eCBlocks = eCBlocksForLevel.getECBlocks();
            int i = 0;
            for (Version.ECB ecb : eCBlocks) {
                i += ecb.getCount();
            }
            b[] bVarArr = new b[i];
            int i2 = 0;
            for (Version.ECB ecb2 : eCBlocks) {
                int i3 = 0;
                while (i3 < ecb2.getCount()) {
                    int dataCodewords = ecb2.getDataCodewords();
                    bVarArr[i2] = new b(dataCodewords, new byte[eCBlocksForLevel.getECCodewordsPerBlock() + dataCodewords]);
                    i3++;
                    i2++;
                }
            }
            int length = bVarArr[0].b.length;
            int i4 = i - 1;
            while (i4 >= 0 && bVarArr[i4].b.length != length) {
                i4--;
            }
            int i5 = i4 + 1;
            int eCCodewordsPerBlock = length - eCBlocksForLevel.getECCodewordsPerBlock();
            int i6 = 0;
            for (int i7 = 0; i7 < eCCodewordsPerBlock; i7++) {
                int i8 = 0;
                while (i8 < i2) {
                    bVarArr[i8].b[i7] = bArr[i6];
                    i8++;
                    i6++;
                }
            }
            int i9 = i5;
            while (i9 < i2) {
                bVarArr[i9].b[eCCodewordsPerBlock] = bArr[i6];
                i9++;
                i6++;
            }
            int length2 = bVarArr[0].b.length;
            while (eCCodewordsPerBlock < length2) {
                int i10 = 0;
                while (i10 < i2) {
                    bVarArr[i10].b[i10 < i5 ? eCCodewordsPerBlock : eCCodewordsPerBlock + 1] = bArr[i6];
                    i10++;
                    i6++;
                }
                eCCodewordsPerBlock++;
            }
            return bVarArr;
        }
        throw new IllegalArgumentException();
    }

    public byte[] a() {
        return this.b;
    }

    public int c() {
        return this.f11875a;
    }
}
