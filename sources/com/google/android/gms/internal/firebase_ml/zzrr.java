package com.google.android.gms.internal.firebase_ml;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
/* loaded from: classes7.dex */
public abstract class zzrr<ResultType> implements Closeable {
    public final zzru h;
    public final zzku i;
    public final zzqb j;
    public final zzkz k;
    public final zzqf zzbkb;

    public zzrr(@NonNull zzqf zzqfVar, @NonNull String str, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        this(zzqfVar, new zzku().zza(Integer.valueOf(firebaseVisionCloudDetectorOptions.getMaxResults())).zzay(str).zzax(zzrq.zzbv(firebaseVisionCloudDetectorOptions.getModelType())), (zzkz) null, firebaseVisionCloudDetectorOptions.isEnforceCertFingerprintMatch());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public final Task<ResultType> zza(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkNotNull(firebaseVisionImage, "Input image can not be null");
        Pair<byte[], Float> zze = firebaseVisionImage.zze(zzqk(), zzql());
        if (zze.first == null) {
            return Tasks.forException(new FirebaseMLException("Can not convert the image format", 3));
        }
        return this.j.zza((zzpu<T, zzru>) this.h, (zzru) new zzrs((byte[]) zze.first, ((Float) zze.second).floatValue(), Collections.singletonList(this.i), this.k));
    }

    public abstract ResultType zza(@NonNull zzkl zzklVar, float f);

    public abstract int zzqk();

    public abstract int zzql();

    public zzrr(@NonNull zzqf zzqfVar, @NonNull String str, @NonNull zzkz zzkzVar, boolean z) {
        this(zzqfVar, new zzku().zzay(str).zzax(zzrq.zzbv(1)), (zzkz) Preconditions.checkNotNull(zzkzVar, "ImageContext must not be null"), z);
    }

    public zzrr(@NonNull zzqf zzqfVar, @NonNull zzku zzkuVar, @Nullable zzkz zzkzVar, boolean z) {
        Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
        Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Firebase app name must not be null");
        this.i = (zzku) Preconditions.checkNotNull(zzkuVar);
        this.j = zzqb.zza(zzqfVar);
        this.h = new zzru(this, zzqfVar.zzoh(), z);
        this.zzbkb = zzqfVar;
        this.k = zzkzVar;
    }
}
