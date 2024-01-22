package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqh;
import com.google.android.gms.internal.firebase_ml.zzsj;
import com.google.android.gms.internal.firebase_ml.zzsl;
import com.google.android.gms.internal.firebase_ml.zzsp;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public class FirebaseVisionImageLabeler implements Closeable {
    public static final int CLOUD = 2;
    public static final int ON_DEVICE = 1;
    public static final int ON_DEVICE_AUTOML = 3;
    @GuardedBy("FirebaseVisionImageLabeler.class")
    public static final Map<zzqh<FirebaseVisionOnDeviceImageLabelerOptions>, FirebaseVisionImageLabeler> m = new HashMap();
    @GuardedBy("FirebaseVisionImageLabeler.class")
    public static final Map<zzqh<FirebaseVisionCloudImageLabelerOptions>, FirebaseVisionImageLabeler> n = new HashMap();
    @GuardedBy("FirebaseVisionImageLabeler.class")
    public static final Map<zzqh<FirebaseVisionOnDeviceAutoMLImageLabelerOptions>, FirebaseVisionImageLabeler> o = new HashMap();
    public final zzsj h;
    public final zzsl i;
    public final zzsp j;
    public final FirebaseVisionCloudImageLabelerOptions k;
    @ImageLabelerType
    public final int l;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface ImageLabelerType {
    }

    public FirebaseVisionImageLabeler(@Nullable zzsl zzslVar) {
        this(zzslVar, null, null, null);
    }

    public static synchronized FirebaseVisionImageLabeler zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        FirebaseVisionImageLabeler firebaseVisionImageLabeler;
        synchronized (FirebaseVisionImageLabeler.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            zzqh<FirebaseVisionOnDeviceImageLabelerOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionOnDeviceImageLabelerOptions);
            Map<zzqh<FirebaseVisionOnDeviceImageLabelerOptions>, FirebaseVisionImageLabeler> map = m;
            firebaseVisionImageLabeler = map.get(zzj);
            if (firebaseVisionImageLabeler == null) {
                FirebaseVisionImageLabeler firebaseVisionImageLabeler2 = new FirebaseVisionImageLabeler(new zzsl(zzqfVar, firebaseVisionOnDeviceImageLabelerOptions));
                map.put(zzj, firebaseVisionImageLabeler2);
                firebaseVisionImageLabeler = firebaseVisionImageLabeler2;
            }
        }
        return firebaseVisionImageLabeler;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        zzsl zzslVar = this.i;
        if (zzslVar != null) {
            zzslVar.close();
        }
        zzsj zzsjVar = this.h;
        if (zzsjVar != null) {
            zzsjVar.close();
        }
        zzsp zzspVar = this.j;
        if (zzspVar != null) {
            zzspVar.close();
        }
    }

    @ImageLabelerType
    public int getImageLabelerType() {
        return this.l;
    }

    @NonNull
    public Task<List<FirebaseVisionImageLabel>> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkState((this.i == null && this.h == null && this.j == null) ? false : true, "One of on-device, cloud, or on-device AutoML image labeler should be set.");
        zzsl zzslVar = this.i;
        if (zzslVar != null) {
            return zzslVar.detectInImage(firebaseVisionImage);
        }
        zzsp zzspVar = this.j;
        if (zzspVar != null) {
            return zzspVar.detectInImage(firebaseVisionImage);
        }
        return this.h.detectInImage(firebaseVisionImage).continueWith(new b(this));
    }

    public FirebaseVisionImageLabeler(@Nullable zzsj zzsjVar, @Nullable FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions) {
        this(null, zzsjVar, null, firebaseVisionCloudImageLabelerOptions);
    }

    public FirebaseVisionImageLabeler(@NonNull zzsp zzspVar) {
        this(null, null, zzspVar, null);
    }

    public FirebaseVisionImageLabeler(@Nullable zzsl zzslVar, @Nullable zzsj zzsjVar, @Nullable zzsp zzspVar, @Nullable FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions) {
        Preconditions.checkArgument((zzslVar == null && zzsjVar == null && zzspVar == null) ? false : true, "One of on-device, cloud or on-device AutoML image labeler should be set.");
        this.i = zzslVar;
        this.h = zzsjVar;
        this.k = firebaseVisionCloudImageLabelerOptions;
        this.j = zzspVar;
        if (zzsjVar != null) {
            this.l = 2;
        } else if (zzslVar != null) {
            this.l = 1;
        } else {
            this.l = 3;
        }
    }

    public static synchronized FirebaseVisionImageLabeler zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionOnDeviceAutoMLImageLabelerOptions firebaseVisionOnDeviceAutoMLImageLabelerOptions) throws FirebaseMLException {
        FirebaseVisionImageLabeler firebaseVisionImageLabeler;
        synchronized (FirebaseVisionImageLabeler.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            zzqh<FirebaseVisionOnDeviceAutoMLImageLabelerOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionOnDeviceAutoMLImageLabelerOptions);
            Map<zzqh<FirebaseVisionOnDeviceAutoMLImageLabelerOptions>, FirebaseVisionImageLabeler> map = o;
            firebaseVisionImageLabeler = map.get(zzj);
            if (firebaseVisionImageLabeler == null) {
                FirebaseVisionImageLabeler firebaseVisionImageLabeler2 = new FirebaseVisionImageLabeler(new zzsp(zzqfVar, firebaseVisionOnDeviceAutoMLImageLabelerOptions));
                map.put(zzj, firebaseVisionImageLabeler2);
                firebaseVisionImageLabeler = firebaseVisionImageLabeler2;
            }
        }
        return firebaseVisionImageLabeler;
    }

    public static synchronized FirebaseVisionImageLabeler zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions) {
        FirebaseVisionImageLabeler firebaseVisionImageLabeler;
        synchronized (FirebaseVisionImageLabeler.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            zzqh<FirebaseVisionCloudImageLabelerOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionCloudImageLabelerOptions);
            Map<zzqh<FirebaseVisionCloudImageLabelerOptions>, FirebaseVisionImageLabeler> map = n;
            firebaseVisionImageLabeler = map.get(zzj);
            if (firebaseVisionImageLabeler == null) {
                FirebaseVisionCloudDetectorOptions.Builder maxResults = new FirebaseVisionCloudDetectorOptions.Builder().setMaxResults(20);
                if (firebaseVisionCloudImageLabelerOptions.isEnforceCertFingerprintMatch()) {
                    maxResults.enforceCertFingerprintMatch();
                }
                firebaseVisionImageLabeler = new FirebaseVisionImageLabeler(new zzsj(zzqfVar, maxResults.build()), firebaseVisionCloudImageLabelerOptions);
                map.put(zzj, firebaseVisionImageLabeler);
            }
        }
        return firebaseVisionImageLabeler;
    }
}
