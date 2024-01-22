package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.vision.Frame;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.io.IOException;
/* loaded from: classes7.dex */
public class zzrz<TDetectionResult> implements Closeable {
    public final zzpu<TDetectionResult, zzsf> h;
    public final zzqb i;

    public zzrz(@NonNull zzqf zzqfVar, zzpu<TDetectionResult, zzsf> zzpuVar) {
        Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
        Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
        this.h = zzpuVar;
        zzqb zza = zzqb.zza(zzqfVar);
        this.i = zza;
        zza.zza(zzpuVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i.zzb(this.h);
    }

    public final Task<TDetectionResult> zza(@NonNull FirebaseVisionImage firebaseVisionImage, boolean z, boolean z2) {
        Preconditions.checkNotNull(firebaseVisionImage, "FirebaseVisionImage can not be null");
        Frame zza = firebaseVisionImage.zza(z, z2);
        if (zza.getMetadata().getWidth() >= 32 && zza.getMetadata().getHeight() >= 32) {
            return this.i.zza((zzpu<T, zzpu<TDetectionResult, zzsf>>) this.h, (zzpu<TDetectionResult, zzsf>) new zzsf(firebaseVisionImage, zza));
        }
        return Tasks.forException(new FirebaseMLException("Image width and height should be at least 32!", 3));
    }
}
