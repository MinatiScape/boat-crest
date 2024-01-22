package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    public static final Result[] b = new Result[0];

    /* renamed from: a  reason: collision with root package name */
    public final Reader f11817a;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.f11817a = reader;
    }

    public static Result b(Result result, int i, int i2) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i3 = 0; i3 < resultPoints.length; i3++) {
            ResultPoint resultPoint = resultPoints[i3];
            if (resultPoint != null) {
                resultPointArr[i3] = new ResultPoint(resultPoint.getX() + i, resultPoint.getY() + i2);
            }
        }
        Result result2 = new Result(result.getText(), result.getRawBytes(), result.getNumBits(), resultPointArr, result.getBarcodeFormat(), result.getTimestamp());
        result2.putAllMetadata(result.getResultMetadata());
        return result2;
    }

    public final void a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, List<Result> list, int i, int i2, int i3) {
        boolean z;
        float f;
        float f2;
        int i4;
        int i5;
        if (i3 > 4) {
            return;
        }
        try {
            Result decode = this.f11817a.decode(binaryBitmap, map);
            Iterator<Result> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getText().equals(decode.getText())) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                list.add(b(decode, i, i2));
            }
            ResultPoint[] resultPoints = decode.getResultPoints();
            if (resultPoints == null || resultPoints.length == 0) {
                return;
            }
            int width = binaryBitmap.getWidth();
            int height = binaryBitmap.getHeight();
            float f3 = width;
            float f4 = 0.0f;
            float f5 = height;
            float f6 = 0.0f;
            for (ResultPoint resultPoint : resultPoints) {
                if (resultPoint != null) {
                    float x = resultPoint.getX();
                    float y = resultPoint.getY();
                    if (x < f3) {
                        f3 = x;
                    }
                    if (y < f5) {
                        f5 = y;
                    }
                    if (x > f6) {
                        f6 = x;
                    }
                    if (y > f4) {
                        f4 = y;
                    }
                }
            }
            if (f3 > 100.0f) {
                f = f6;
                f2 = f5;
                i4 = height;
                i5 = width;
                a(binaryBitmap.crop(0, 0, (int) f3, height), map, list, i, i2, i3 + 1);
            } else {
                f = f6;
                f2 = f5;
                i4 = height;
                i5 = width;
            }
            if (f2 > 100.0f) {
                a(binaryBitmap.crop(0, 0, i5, (int) f2), map, list, i, i2, i3 + 1);
            }
            float f7 = f;
            if (f7 < i5 - 100) {
                int i6 = (int) f7;
                a(binaryBitmap.crop(i6, 0, i5 - i6, i4), map, list, i + i6, i2, i3 + 1);
            }
            if (f4 < i4 - 100) {
                int i7 = (int) f4;
                a(binaryBitmap.crop(0, i7, i5, i4 - i7), map, list, i, i2 + i7, i3 + 1);
            }
        } catch (ReaderException unused) {
        }
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        a(binaryBitmap, map, arrayList, 0, 0, 0);
        if (!arrayList.isEmpty()) {
            return (Result[]) arrayList.toArray(b);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
