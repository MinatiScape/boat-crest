package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;
/* loaded from: classes11.dex */
public final class Decoder {

    /* renamed from: a  reason: collision with root package name */
    public final ReedSolomonDecoder f11869a = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);

    public final void a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f11869a.decode(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    public final DecoderResult b(a aVar, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        Version e = aVar.e();
        ErrorCorrectionLevel d = aVar.d().d();
        b[] b = b.b(aVar.c(), e, d);
        int i = 0;
        for (b bVar : b) {
            i += bVar.c();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (b bVar2 : b) {
            byte[] a2 = bVar2.a();
            int c = bVar2.c();
            a(a2, c);
            int i3 = 0;
            while (i3 < c) {
                bArr[i2] = a2[i3];
                i3++;
                i2++;
            }
        }
        return d.a(bArr, e, d, map);
    }

    public DecoderResult decode(boolean[][] zArr) throws ChecksumException, FormatException {
        return decode(zArr, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(boolean[][] zArr, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        return decode(BitMatrix.parse(zArr), map);
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return decode(bitMatrix, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        ChecksumException e;
        a aVar = new a(bitMatrix);
        FormatException formatException = null;
        try {
            return b(aVar, map);
        } catch (ChecksumException e2) {
            e = e2;
            try {
                aVar.f();
                aVar.g(true);
                aVar.e();
                aVar.d();
                aVar.b();
                DecoderResult b = b(aVar, map);
                b.setOther(new QRCodeDecoderMetaData(true));
                return b;
            } catch (ChecksumException | FormatException unused) {
                if (formatException != null) {
                    throw formatException;
                }
                throw e;
            }
        } catch (FormatException e3) {
            e = null;
            formatException = e3;
            aVar.f();
            aVar.g(true);
            aVar.e();
            aVar.d();
            aVar.b();
            DecoderResult b2 = b(aVar, map);
            b2.setOther(new QRCodeDecoderMetaData(true));
            return b2;
        }
    }
}
