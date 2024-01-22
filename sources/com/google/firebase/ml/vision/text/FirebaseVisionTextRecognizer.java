package com.google.firebase.ml.vision.text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzss;
import com.google.android.gms.internal.firebase_ml.zzsu;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public class FirebaseVisionTextRecognizer implements Closeable {
    public static final int CLOUD = 2;
    public static final int ON_DEVICE = 1;
    @GuardedBy("FirebaseVisionTextRecognizer.class")
    public static final Map<zzsu, FirebaseVisionTextRecognizer> k = new HashMap();
    @GuardedBy("FirebaseVisionTextRecognizer.class")
    public static final Map<zzss, FirebaseVisionTextRecognizer> l = new HashMap();
    public final zzsu h;
    public final zzss i;
    @RecognizerType
    public final int j;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface RecognizerType {
    }

    public FirebaseVisionTextRecognizer(@Nullable zzsu zzsuVar, @Nullable zzss zzssVar, @RecognizerType int i) {
        this.j = i;
        this.h = zzsuVar;
        this.i = zzssVar;
    }

    public static synchronized FirebaseVisionTextRecognizer zza(@NonNull zzqf zzqfVar, @Nullable FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions, boolean z) {
        synchronized (FirebaseVisionTextRecognizer.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            if (!z) {
                Preconditions.checkNotNull(firebaseVisionCloudTextRecognizerOptions, "Options must not be null");
            }
            if (z) {
                zzsu zzc = zzsu.zzc(zzqfVar);
                Map<zzsu, FirebaseVisionTextRecognizer> map = k;
                FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = map.get(zzc);
                if (firebaseVisionTextRecognizer == null) {
                    firebaseVisionTextRecognizer = new FirebaseVisionTextRecognizer(zzc, null, 1);
                    map.put(zzc, firebaseVisionTextRecognizer);
                }
                return firebaseVisionTextRecognizer;
            }
            zzss zza = zzss.zza(zzqfVar, firebaseVisionCloudTextRecognizerOptions);
            Map<zzss, FirebaseVisionTextRecognizer> map2 = l;
            FirebaseVisionTextRecognizer firebaseVisionTextRecognizer2 = map2.get(zza);
            if (firebaseVisionTextRecognizer2 == null) {
                firebaseVisionTextRecognizer2 = new FirebaseVisionTextRecognizer(null, zza, 2);
                map2.put(zza, firebaseVisionTextRecognizer2);
            }
            return firebaseVisionTextRecognizer2;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        zzsu zzsuVar = this.h;
        if (zzsuVar != null) {
            zzsuVar.close();
        }
        zzss zzssVar = this.i;
        if (zzssVar != null) {
            zzssVar.close();
        }
    }

    @RecognizerType
    public int getRecognizerType() {
        return this.j;
    }

    @NonNull
    public Task<FirebaseVisionText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkArgument((this.h == null && this.i == null) ? false : true, "Either on-device or cloud text recognizer should be enabled.");
        zzsu zzsuVar = this.h;
        if (zzsuVar != null) {
            return zzsuVar.processImage(firebaseVisionImage);
        }
        return this.i.processImage(firebaseVisionImage);
    }
}
