package com.google.firebase.ml.common.internal.modeldownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqu;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes10.dex */
public final class zzz {
    public static final GmsLogger i = new GmsLogger("RemoteModelFileManager", "");

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f11400a;
    public final String b;
    public final FirebaseRemoteModel c;
    public final zzn d;
    public final zzah e;
    public final zzk f;
    public final zzqu g;
    public final zzi h;

    public zzz(@NonNull zzqf zzqfVar, @NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzp zzpVar, @NonNull zzn zznVar, @NonNull zzi zziVar) {
        String uniqueModelNameForPersist;
        this.f11400a = zzqfVar;
        this.c = firebaseRemoteModel;
        if (zznVar == zzn.TRANSLATE) {
            uniqueModelNameForPersist = firebaseRemoteModel.getModelNameForBackend();
        } else {
            uniqueModelNameForPersist = firebaseRemoteModel.getUniqueModelNameForPersist();
        }
        this.b = uniqueModelNameForPersist;
        this.d = zznVar;
        this.e = new zzah(zzpVar);
        this.g = zzqu.zzb(zzqfVar);
        this.h = zziVar;
        int i2 = b.f11377a[zznVar.ordinal()];
        if (i2 == 1) {
            this.f = new zza(zzqfVar, uniqueModelNameForPersist);
        } else if (i2 == 2) {
            this.f = new c(zzqfVar, uniqueModelNameForPersist);
        } else if (i2 != 3 && i2 != 4) {
            throw new IllegalArgumentException("Unexpected model type");
        } else {
            this.f = new a(zzqfVar, uniqueModelNameForPersist);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b6, code lost:
        r9 = com.google.firebase.ml.common.internal.modeldownload.zzz.i;
        r10 = java.lang.String.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c4, code lost:
        if (r10.length() == 0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c6, code lost:
        r10 = "Hash does not match with expected: ".concat(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00cb, code lost:
        r10 = new java.lang.String("Hash does not match with expected: ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d0, code lost:
        r9.d("RemoteModelFileManager", r10);
        r11.zza(com.google.android.gms.internal.firebase_ml.zzoc.MODEL_HASH_MISMATCH, true, r8.d, com.google.android.gms.internal.firebase_ml.zzns.zzai.zza.SUCCEEDED);
        r9 = new com.google.firebase.ml.common.FirebaseMLException("Hash does not match with expected", 102);
     */
    @androidx.annotation.Nullable
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.io.File zza(@androidx.annotation.NonNull android.os.ParcelFileDescriptor r9, @androidx.annotation.NonNull java.lang.String r10, @androidx.annotation.NonNull com.google.firebase.ml.common.internal.modeldownload.zzw r11) throws com.google.firebase.ml.common.FirebaseMLException {
        /*
            Method dump skipped, instructions count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.common.internal.modeldownload.zzz.zza(android.os.ParcelFileDescriptor, java.lang.String, com.google.firebase.ml.common.internal.modeldownload.zzw):java.io.File");
    }

    @WorkerThread
    public final synchronized boolean zzd(@NonNull File file) throws FirebaseMLException {
        File[] listFiles;
        File c = this.h.c(this.b, this.d);
        if (c.exists()) {
            boolean z = true;
            for (File file2 : c.listFiles()) {
                if (!file2.equals(file) && !this.h.d(file2)) {
                    z = false;
                }
            }
            return z;
        }
        return false;
    }

    @WorkerThread
    public final synchronized void zze(@NonNull File file) {
        File zzb = this.h.zzb(this.b, this.d, false);
        if (zzb.exists()) {
            for (File file2 : zzb.listFiles()) {
                if (file2.equals(file)) {
                    this.h.d(file);
                    return;
                }
            }
        }
    }

    @WorkerThread
    public final synchronized File zzf(@NonNull File file) throws FirebaseMLException {
        File file2 = new File(String.valueOf(this.h.c(this.b, this.d).getAbsolutePath()).concat("/0"));
        return file2.exists() ? file : file.renameTo(file2) ? file2 : file;
    }

    @Nullable
    @WorkerThread
    public final synchronized String zzpk() throws FirebaseMLException {
        File c = this.h.c(this.b, this.d);
        int b = zzi.b(c);
        if (b < 0) {
            return null;
        }
        String absolutePath = c.getAbsolutePath();
        StringBuilder sb = new StringBuilder(String.valueOf(absolutePath).length() + 12);
        sb.append(absolutePath);
        sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        sb.append(b);
        return sb.toString();
    }
}
