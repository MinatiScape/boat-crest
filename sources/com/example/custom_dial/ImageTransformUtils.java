package com.example.custom_dial;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import androidx.annotation.NonNull;
import kotlin.UShort;
/* loaded from: classes9.dex */
public class ImageTransformUtils {

    /* renamed from: a  reason: collision with root package name */
    public final String f7895a = toString().toString();

    public final byte[] a(int i, int i2, boolean z) {
        byte[] bArr = new byte[4];
        int i3 = ((i | 0) << 10) | (i2 << 21);
        if (z) {
            bArr[0] = 5;
        } else {
            bArr[0] = 4;
        }
        bArr[1] = (byte) (i3 >> 8);
        bArr[2] = (byte) (i3 >> 16);
        bArr[3] = (byte) (i3 >> 24);
        return bArr;
    }

    public final byte[] b(@NonNull Bitmap bitmap, int i, boolean z) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int i2 = height << 21;
        int i3 = width << 10;
        int i4 = z ? i2 | i3 | 0 | 4 : i2 | i3 | 0 | 21;
        byte[] bArr = {(byte) i4, (byte) (i4 >> 8), (byte) (i4 >> 16), (byte) (i4 >> 24)};
        byte[] bArr2 = new byte[4];
        int i5 = z ? height * width * 4 : height * width * 2;
        bArr2[0] = (byte) i5;
        bArr2[1] = (byte) (i5 >> 8);
        bArr2[2] = (byte) (i5 >> 16);
        bArr2[3] = (byte) (i5 >> 24);
        int i6 = i + 20;
        byte[] bArr3 = {(byte) i6, (byte) (i6 >> 8), (byte) (i6 >> 16), (byte) (i6 >> 24)};
        byte[] bArr4 = new byte[20];
        System.arraycopy(bArr, 0, bArr4, 0, 4);
        System.arraycopy(bArr2, 0, bArr4, 4, 4);
        System.arraycopy(bArr3, 0, bArr4, 8, 4);
        byte[] d = new ImageTransformUtils().d(bitmap, z);
        if (d != null) {
            byte[] bArr5 = new byte[d.length + 20];
            System.arraycopy(bArr4, 0, bArr5, 0, 20);
            System.arraycopy(d, 0, bArr5, 20, d.length);
            return bArr5;
        }
        return bArr4;
    }

    public final byte[] c(@NonNull Bitmap bitmap, boolean z) {
        byte[] bArr;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        byte[] a2 = a(width, height, z);
        int i = width * height;
        int[] iArr = new int[i];
        if (!z) {
            bArr = new byte[i * 2];
        } else {
            bArr = new byte[i * 3];
        }
        byte[] bArr2 = bArr;
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            int alpha = Color.alpha(i4);
            int red = ((short) ((((Color.red(i4) >> 3) << 11) & 63488) | (((Color.green(i4) >> 2) << 5) & 2016) | ((Color.blue(i4) >> 3) & 31))) & UShort.MAX_VALUE;
            bArr2[i2] = (byte) (red >> 8);
            bArr2[i2 + 1] = (byte) red;
            if (z) {
                bArr2[i2 + 2] = (byte) (alpha & (-1));
                i2 += 3;
            } else {
                i2 += 2;
            }
        }
        byte[] bArr3 = new byte[a2.length + bArr2.length];
        System.arraycopy(a2, 0, bArr3, 0, a2.length);
        System.arraycopy(bArr2, 0, bArr3, a2.length, bArr2.length);
        return bArr3;
    }

    public final byte[] d(@NonNull Bitmap bitmap, boolean z) {
        byte[] bArr;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int i = width * height;
        int[] iArr = new int[i];
        if (!z) {
            bArr = new byte[i * 2];
        } else {
            bArr = new byte[i * 4];
        }
        byte[] bArr2 = bArr;
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            if (z) {
                bArr2[i2] = (byte) (Color.red(i4) & 255);
                bArr2[i2 + 1] = (byte) (Color.green(i4) & 255);
                bArr2[i2 + 2] = (byte) (Color.blue(i4) & 255);
                bArr2[i2 + 3] = (byte) (Color.alpha(i4) & 255);
                i2 += 4;
            } else {
                int blue = ((short) (((Color.blue(i4) >> 3) & 31) | (((Color.red(i4) >> 3) << 11) & 63488) | (((Color.green(i4) >> 2) << 5) & 2016))) & UShort.MAX_VALUE;
                bArr2[i2] = (byte) blue;
                bArr2[i2 + 1] = (byte) (blue >> 8);
                i2 += 2;
            }
        }
        return bArr2;
    }

    public byte[] imageConvertRgb565(@NonNull Bitmap bitmap, boolean z, boolean z2, int i) {
        if (z2) {
            Log.e(this.f7895a, "新的平台");
            return b(bitmap, i, z);
        }
        Log.e(this.f7895a, "旧的平台");
        return c(bitmap, z);
    }
}
