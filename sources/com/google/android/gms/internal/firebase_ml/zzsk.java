package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.vision.label.ImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import java.util.List;
/* loaded from: classes7.dex */
public final class zzsk implements zzpu<List<FirebaseVisionImageLabel>, zzsf>, zzqp {
    @VisibleForTesting
    public static boolean e = true;
    public static volatile Boolean f;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8806a;
    public final FirebaseVisionOnDeviceImageLabelerOptions b;
    public final zzqg c;
    @GuardedBy("this")
    public ImageLabeler d;

    public zzsk(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        Preconditions.checkNotNull(zzqfVar, "Context can not be null");
        Preconditions.checkNotNull(firebaseVisionOnDeviceImageLabelerOptions, "FirebaseVisionOnDeviceImageLabelerOptions can not be null");
        this.f8806a = zzqfVar.getApplicationContext();
        this.b = firebaseVisionOnDeviceImageLabelerOptions;
        this.c = zzqg.zza(zzqfVar, 1);
    }

    public final /* synthetic */ zzns.zzad.zza a(long j, zzoc zzocVar, zzsf zzsfVar) {
        return zzns.zzad.zzma().zza((zzns.zzao) ((zzwz) zzns.zzao.zzmx().zze(zzns.zzaf.zzme().zzk(j).zzk(zzocVar).zzah(e).zzai(true).zzaj(true)).zzd(this.b.zzqv()).zzk(zzsa.zzc(zzsfVar)).zzvb()));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @androidx.annotation.WorkerThread
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.google.firebase.ml.vision.label.FirebaseVisionImageLabel> zza(com.google.android.gms.internal.firebase_ml.zzsf r9) throws com.google.firebase.ml.common.FirebaseMLException {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.Boolean r0 = com.google.android.gms.internal.firebase_ml.zzsk.f     // Catch: java.lang.Throwable -> L97
            r1 = 0
            if (r0 != 0) goto L27
            android.content.Context r0 = r8.f8806a     // Catch: java.lang.Throwable -> L97
            java.lang.String r2 = "com.google.android.gms.vision.dynamite.ica"
            int r2 = com.google.android.gms.dynamite.DynamiteModule.getLocalVersion(r0, r2)     // Catch: java.lang.Throwable -> L97
            r3 = 1
            if (r2 > 0) goto L1c
            java.lang.String r2 = "com.google.android.gms.vision.dynamite.imagelabel"
            int r0 = com.google.android.gms.dynamite.DynamiteModule.getLocalVersion(r0, r2)     // Catch: java.lang.Throwable -> L97
            if (r0 <= 0) goto L1a
            goto L1c
        L1a:
            r0 = r1
            goto L1d
        L1c:
            r0 = r3
        L1d:
            if (r0 != 0) goto L20
            goto L21
        L20:
            r3 = r1
        L21:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.Throwable -> L97
            com.google.android.gms.internal.firebase_ml.zzsk.f = r0     // Catch: java.lang.Throwable -> L97
        L27:
            java.lang.Boolean r0 = com.google.android.gms.internal.firebase_ml.zzsk.f     // Catch: java.lang.Throwable -> L97
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L97
            r2 = 14
            if (r0 != 0) goto L8f
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L97
            com.google.android.gms.vision.label.ImageLabeler r0 = r8.d     // Catch: java.lang.Throwable -> L97
            if (r0 == 0) goto L80
            boolean r0 = r0.isOperational()     // Catch: java.lang.Throwable -> L97
            if (r0 == 0) goto L73
            com.google.android.gms.vision.label.ImageLabeler r0 = r8.d     // Catch: java.lang.Throwable -> L97
            com.google.android.gms.vision.Frame r2 = r9.zzbrv     // Catch: java.lang.Throwable -> L97
            android.util.SparseArray r0 = r0.detect(r2)     // Catch: java.lang.Throwable -> L97
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L97
            r2.<init>()     // Catch: java.lang.Throwable -> L97
            if (r0 == 0) goto L6a
            r5 = r1
        L4f:
            int r6 = r0.size()     // Catch: java.lang.Throwable -> L97
            if (r5 >= r6) goto L6a
            int r6 = r0.keyAt(r5)     // Catch: java.lang.Throwable -> L97
            com.google.firebase.ml.vision.label.FirebaseVisionImageLabel r7 = new com.google.firebase.ml.vision.label.FirebaseVisionImageLabel     // Catch: java.lang.Throwable -> L97
            java.lang.Object r6 = r0.get(r6)     // Catch: java.lang.Throwable -> L97
            com.google.android.gms.vision.label.ImageLabel r6 = (com.google.android.gms.vision.label.ImageLabel) r6     // Catch: java.lang.Throwable -> L97
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L97
            r2.add(r7)     // Catch: java.lang.Throwable -> L97
            int r5 = r5 + 1
            goto L4f
        L6a:
            com.google.android.gms.internal.firebase_ml.zzoc r0 = com.google.android.gms.internal.firebase_ml.zzoc.NO_ERROR     // Catch: java.lang.Throwable -> L97
            r8.c(r0, r3, r9)     // Catch: java.lang.Throwable -> L97
            com.google.android.gms.internal.firebase_ml.zzsk.e = r1     // Catch: java.lang.Throwable -> L97
            monitor-exit(r8)
            return r2
        L73:
            com.google.android.gms.internal.firebase_ml.zzoc r0 = com.google.android.gms.internal.firebase_ml.zzoc.MODEL_NOT_DOWNLOADED     // Catch: java.lang.Throwable -> L97
            r8.c(r0, r3, r9)     // Catch: java.lang.Throwable -> L97
            com.google.firebase.ml.common.FirebaseMLException r9 = new com.google.firebase.ml.common.FirebaseMLException     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = "Waiting for the label detection model to be downloaded. Please wait."
            r9.<init>(r0, r2)     // Catch: java.lang.Throwable -> L97
            throw r9     // Catch: java.lang.Throwable -> L97
        L80:
            com.google.android.gms.internal.firebase_ml.zzoc r0 = com.google.android.gms.internal.firebase_ml.zzoc.UNKNOWN_ERROR     // Catch: java.lang.Throwable -> L97
            r8.c(r0, r3, r9)     // Catch: java.lang.Throwable -> L97
            com.google.firebase.ml.common.FirebaseMLException r9 = new com.google.firebase.ml.common.FirebaseMLException     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = "Model source is unavailable. Please load the model resource first."
            r1 = 13
            r9.<init>(r0, r1)     // Catch: java.lang.Throwable -> L97
            throw r9     // Catch: java.lang.Throwable -> L97
        L8f:
            com.google.firebase.ml.common.FirebaseMLException r9 = new com.google.firebase.ml.common.FirebaseMLException     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = "No model is bundled. Please check your app setup to includefirebase-ml-vision-image-label-model dependency."
            r9.<init>(r0, r2)     // Catch: java.lang.Throwable -> L97
            throw r9     // Catch: java.lang.Throwable -> L97
        L97:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsk.zza(com.google.android.gms.internal.firebase_ml.zzsf):java.util.List");
    }

    public final void c(final zzoc zzocVar, long j, final zzsf zzsfVar) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.c.zza(new zzqo(this, elapsedRealtime, zzocVar, zzsfVar) { // from class: com.google.android.gms.internal.firebase_ml.d5

            /* renamed from: a  reason: collision with root package name */
            public final zzsk f8672a;
            public final long b;
            public final zzoc c;
            public final zzsf d;

            {
                this.f8672a = this;
                this.b = elapsedRealtime;
                this.c = zzocVar;
                this.d = zzsfVar;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzqo
            public final zzns.zzad.zza zzok() {
                return this.f8672a.a(this.b, this.c, this.d);
            }
        }, zzod.ON_DEVICE_IMAGE_LABEL_DETECT);
        zzqm zzqmVar = c5.f8669a;
        this.c.zza((zzns.zze.zzb) ((zzwz) zzns.zze.zzb.zzkj().zzf(zzocVar).zzr(e).zze(zzsa.zzc(zzsfVar)).zza(this.b.zzqv()).zzvb()), elapsedRealtime, zzod.AGGREGATED_ON_DEVICE_IMAGE_LABEL_DETECTION, zzqmVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void release() {
        ImageLabeler imageLabeler = this.d;
        if (imageLabeler != null) {
            imageLabeler.release();
            this.d = null;
        }
        e = true;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    public final zzqp zzoc() {
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void zzol() {
        if (this.d == null) {
            this.d = new ImageLabeler.Builder(this.f8806a).setScoreThreshold(this.b.getConfidenceThreshold()).build();
        }
    }
}
