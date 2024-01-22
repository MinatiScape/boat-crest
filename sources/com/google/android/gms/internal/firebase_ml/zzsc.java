package com.google.android.gms.internal.firebase_ml;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.util.Log;
import androidx.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes7.dex */
public final class zzsc {

    /* renamed from: a  reason: collision with root package name */
    public static final zzsc f8803a = new zzsc();

    @TargetApi(19)
    public static void a(Image.Plane plane, int i, int i2, byte[] bArr, int i3, int i4) {
        ByteBuffer buffer = plane.getBuffer();
        int position = buffer.position();
        int remaining = ((buffer.remaining() + plane.getRowStride()) - 1) / plane.getRowStride();
        int i5 = i / (i2 / remaining);
        int i6 = 0;
        for (int i7 = 0; i7 < remaining; i7++) {
            int i8 = i6;
            for (int i9 = 0; i9 < i5; i9++) {
                bArr[i3] = buffer.get(i8);
                i3 += i4;
                i8 += plane.getPixelStride();
            }
            i6 += plane.getRowStride();
        }
        buffer.position(position);
    }

    public static byte[] zza(@NonNull ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        byte[] bArr = new byte[limit];
        byteBuffer.get(bArr, 0, limit);
        return bArr;
    }

    public static byte[] zzf(byte[] bArr) {
        int length = bArr.length;
        int i = length / 6;
        byte[] bArr2 = new byte[length];
        int i2 = i << 2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        for (int i3 = 0; i3 < (i << 1); i3++) {
            bArr2[i2 + i3] = bArr[((i3 % 2) * i) + i2 + (i3 / 2)];
        }
        return bArr2;
    }

    public static zzsc zzqp() {
        return f8803a;
    }

    public static byte[] zza(@NonNull byte[] bArr, int i, int i2) {
        byte[] bArr2;
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
            bArr2 = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
                Log.w("ImageConvertUtils", "Error closing ByteArrayOutputStream");
                return bArr2;
            }
        } catch (IOException unused2) {
            bArr2 = null;
        }
        return bArr2;
    }

    public static byte[] zza(@NonNull Bitmap bitmap) {
        byte[] bArr;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
                Log.w("ImageConvertUtils", "Error closing ByteArrayOutputStream");
                return bArr;
            }
        } catch (IOException unused2) {
            bArr = null;
        }
        return bArr;
    }

    @TargetApi(19)
    public static ByteBuffer zza(Image.Plane[] planeArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr = new byte[((i3 / 4) * 2) + i3];
        ByteBuffer buffer = planeArr[1].getBuffer();
        ByteBuffer buffer2 = planeArr[2].getBuffer();
        int position = buffer2.position();
        int limit = buffer.limit();
        buffer2.position(position + 1);
        buffer.limit(limit - 1);
        int i4 = (i3 * 2) / 4;
        boolean z = buffer2.remaining() == i4 + (-2) && buffer2.compareTo(buffer) == 0;
        buffer2.position(position);
        buffer.limit(limit);
        if (z) {
            planeArr[0].getBuffer().get(bArr, 0, i3);
            ByteBuffer buffer3 = planeArr[1].getBuffer();
            planeArr[2].getBuffer().get(bArr, i3, 1);
            buffer3.get(bArr, i3 + 1, i4 - 1);
        } else {
            a(planeArr[0], i, i2, bArr, 0, 1);
            a(planeArr[1], i, i2, bArr, i3 + 1, 2);
            a(planeArr[2], i, i2, bArr, i3, 2);
        }
        return ByteBuffer.wrap(bArr);
    }
}
