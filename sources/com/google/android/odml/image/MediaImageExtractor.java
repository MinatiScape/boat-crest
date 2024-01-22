package com.google.android.odml.image;

import android.media.Image;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(19)
/* loaded from: classes10.dex */
public class MediaImageExtractor {
    @NonNull
    public static Image extract(@NonNull MlImage mlImage) {
        g a2 = mlImage.a();
        if (a2.zzb().getStorageType() == 3) {
            return ((i) a2).a();
        }
        throw new IllegalArgumentException("Extract Media Image from an MlImage created by objects other than Media Image is not supported");
    }
}
