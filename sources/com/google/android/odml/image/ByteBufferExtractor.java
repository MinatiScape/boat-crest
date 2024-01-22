package com.google.android.odml.image;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public class ByteBufferExtractor {
    @NonNull
    public static ByteBuffer extract(@NonNull MlImage mlImage) {
        g a2 = mlImage.a();
        if (a2.zzb().getStorageType() == 2) {
            return ((f) a2).a().asReadOnlyBuffer();
        }
        throw new IllegalArgumentException("Extract ByteBuffer from an MlImage created by objects other than Bytebuffer is not supported");
    }
}
