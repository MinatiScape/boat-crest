package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.Map;
/* loaded from: classes11.dex */
public final class ByQuadrantReader implements Reader {

    /* renamed from: a  reason: collision with root package name */
    public final Reader f11816a;

    public ByQuadrantReader(Reader reader) {
        this.f11816a = reader;
    }

    public static void a(ResultPoint[] resultPointArr, int i, int i2) {
        if (resultPointArr != null) {
            for (int i3 = 0; i3 < resultPointArr.length; i3++) {
                ResultPoint resultPoint = resultPointArr[i3];
                if (resultPoint != null) {
                    resultPointArr[i3] = new ResultPoint(resultPoint.getX() + i, resultPoint.getY() + i2);
                }
            }
        }
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public void reset() {
        this.f11816a.reset();
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int width = binaryBitmap.getWidth() / 2;
        int height = binaryBitmap.getHeight() / 2;
        try {
            try {
                try {
                    try {
                        return this.f11816a.decode(binaryBitmap.crop(0, 0, width, height), map);
                    } catch (NotFoundException unused) {
                        int i = width / 2;
                        int i2 = height / 2;
                        Result decode = this.f11816a.decode(binaryBitmap.crop(i, i2, width, height), map);
                        a(decode.getResultPoints(), i, i2);
                        return decode;
                    }
                } catch (NotFoundException unused2) {
                    Result decode2 = this.f11816a.decode(binaryBitmap.crop(width, height, width, height), map);
                    a(decode2.getResultPoints(), width, height);
                    return decode2;
                }
            } catch (NotFoundException unused3) {
                Result decode3 = this.f11816a.decode(binaryBitmap.crop(0, height, width, height), map);
                a(decode3.getResultPoints(), 0, height);
                return decode3;
            }
        } catch (NotFoundException unused4) {
            Result decode4 = this.f11816a.decode(binaryBitmap.crop(width, 0, width, height), map);
            a(decode4.getResultPoints(), width, 0);
            return decode4;
        }
    }
}
