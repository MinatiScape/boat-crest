package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
/* loaded from: classes11.dex */
public final class Decoder {

    /* renamed from: a  reason: collision with root package name */
    public final ReedSolomonDecoder f11799a = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);

    public final void a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f11799a.decode(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    public DecoderResult decode(boolean[][] zArr) throws FormatException, ChecksumException {
        return decode(BitMatrix.parse(zArr));
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws FormatException, ChecksumException {
        a aVar = new a(bitMatrix);
        b[] b = b.b(aVar.c(), aVar.b());
        int i = 0;
        for (b bVar : b) {
            i += bVar.c();
        }
        byte[] bArr = new byte[i];
        int length = b.length;
        for (int i2 = 0; i2 < length; i2++) {
            b bVar2 = b[i2];
            byte[] a2 = bVar2.a();
            int c = bVar2.c();
            a(a2, c);
            for (int i3 = 0; i3 < c; i3++) {
                bArr[(i3 * length) + i2] = a2[i3];
            }
        }
        return c.a(bArr);
    }
}
