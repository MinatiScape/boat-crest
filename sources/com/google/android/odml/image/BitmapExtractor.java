package com.google.android.odml.image;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public final class BitmapExtractor {
    @NonNull
    public static Bitmap extract(@NonNull MlImage mlImage) {
        g a2 = mlImage.a();
        if (a2.zzb().getStorageType() == 1) {
            return ((e) a2).a();
        }
        throw new IllegalArgumentException("Extracting Bitmap from an MlImage created by objects other than Bitmap is not supported");
    }
}
