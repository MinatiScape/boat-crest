package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
/* loaded from: classes7.dex */
public final class zzst implements zzpu<FirebaseVisionText, zzsf>, zzqp {
    @VisibleForTesting
    public static boolean e = true;
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public TextRecognizer f8808a;
    public final zzry b = new zzry();
    public final Context c;
    public final zzqg d;

    public zzst(@NonNull zzqf zzqfVar) {
        Preconditions.checkNotNull(zzqfVar, "MlKitContext can not be null");
        this.c = zzqfVar.getApplicationContext();
        this.d = zzqg.zza(zzqfVar, 1);
    }

    @WorkerThread
    public final void a(final zzoc zzocVar, long j, final zzsf zzsfVar) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.d.zza(new zzqo(elapsedRealtime, zzocVar, zzsfVar) { // from class: com.google.android.gms.internal.firebase_ml.h5

            /* renamed from: a  reason: collision with root package name */
            public final long f8685a;
            public final zzoc b;
            public final zzsf c;

            {
                this.f8685a = elapsedRealtime;
                this.b = zzocVar;
                this.c = zzsfVar;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzqo
            public final zzns.zzad.zza zzok() {
                long j2 = this.f8685a;
                zzoc zzocVar2 = this.b;
                zzsf zzsfVar2 = this.c;
                return zzns.zzad.zzma().zza((zzns.zzaz) ((zzwz) zzns.zzaz.zzns().zzg(zzns.zzaf.zzme().zzk(j2).zzk(zzocVar2).zzah(zzst.e).zzai(true).zzaj(true)).zzm(zzsa.zzc(zzsfVar2)).zzvb()));
            }
        }, zzod.ON_DEVICE_TEXT_DETECT);
        zzqm zzqmVar = g5.f8681a;
        this.d.zza((zzns.zzi.zzb) ((zzwz) zzns.zzi.zzb.zzkt().zzh(zzocVar).zzv(e).zzg(zzsa.zzc(zzsfVar)).zzvb()), elapsedRealtime, zzod.AGGREGATED_ON_DEVICE_TEXT_DETECTION, zzqmVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @WorkerThread
    /* renamed from: b */
    public final synchronized FirebaseVisionText zza(@NonNull zzsf zzsfVar) throws FirebaseMLException {
        SparseArray<TextBlock> detect;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        TextRecognizer textRecognizer = this.f8808a;
        if (textRecognizer != null) {
            if (textRecognizer.isOperational()) {
                this.b.zzb(zzsfVar);
                detect = this.f8808a.detect(zzsfVar.zzbrv);
                a(zzoc.NO_ERROR, elapsedRealtime, zzsfVar);
                e = false;
            } else {
                a(zzoc.MODEL_NOT_DOWNLOADED, elapsedRealtime, zzsfVar);
                throw new FirebaseMLException("Waiting for the text recognition model to be downloaded. Please wait.", 14);
            }
        } else {
            a(zzoc.UNKNOWN_ERROR, elapsedRealtime, zzsfVar);
            throw new FirebaseMLException("Model source is unavailable. Please load the model resource first.", 13);
        }
        return new FirebaseVisionText(detect);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void release() {
        TextRecognizer textRecognizer = this.f8808a;
        if (textRecognizer != null) {
            textRecognizer.release();
            this.f8808a = null;
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
        if (this.f8808a == null) {
            this.f8808a = new TextRecognizer.Builder(this.c).build();
        }
    }
}
