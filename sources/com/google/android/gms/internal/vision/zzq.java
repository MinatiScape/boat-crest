package com.google.android.gms.internal.vision;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes10.dex */
public final class zzq {
    public static Bitmap zzb(Bitmap bitmap, zzp zzpVar) {
        int i;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (zzpVar.rotation != 0) {
            Matrix matrix = new Matrix();
            int i2 = zzpVar.rotation;
            if (i2 == 0) {
                i = 0;
            } else if (i2 == 1) {
                i = 90;
            } else if (i2 == 2) {
                i = 180;
            } else if (i2 != 3) {
                throw new IllegalArgumentException("Unsupported rotation degree.");
            } else {
                i = DfuException.ERROR_READ_DEVICE_INFO_ERROR;
            }
            matrix.postRotate(i);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }
        int i3 = zzpVar.rotation;
        if (i3 == 1 || i3 == 3) {
            zzpVar.width = height;
            zzpVar.height = width;
        }
        return bitmap;
    }
}
