package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes7.dex */
public final class zzsh implements zzpu<List<FirebaseVisionFace>, zzsf>, zzqp {
    public static volatile Boolean g;
    @VisibleForTesting
    public static AtomicBoolean h = new AtomicBoolean(true);

    /* renamed from: a  reason: collision with root package name */
    public final Context f8805a;
    public final FirebaseVisionFaceDetectorOptions b;
    public final zzqg c;
    @GuardedBy("this")
    public FaceDetector d;
    @GuardedBy("this")
    public FaceDetector e;
    public final zzry f = new zzry();

    public zzsh(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        Preconditions.checkNotNull(zzqfVar, "MlKitContext can not be null");
        Preconditions.checkNotNull(firebaseVisionFaceDetectorOptions, "FirebaseVisionFaceDetectorOptions can not be null");
        this.f8805a = zzqfVar.getApplicationContext();
        this.b = firebaseVisionFaceDetectorOptions;
        this.c = zzqg.zza(zzqfVar, 1);
    }

    public static void e(@NonNull List<FirebaseVisionFace> list) {
        for (FirebaseVisionFace firebaseVisionFace : list) {
            firebaseVisionFace.zzbi(-1);
        }
    }

    public final /* synthetic */ zzns.zzad.zza a(long j, zzoc zzocVar, int i, int i2, zzsf zzsfVar) {
        return zzns.zzad.zzma().zza((zzns.zzan) ((zzwz) zzns.zzan.zzmv().zzd(zzns.zzaf.zzme().zzk(j).zzk(zzocVar).zzah(h.get()).zzai(true).zzaj(true)).zzc(this.b.zzqs()).zzbm(i).zzbn(i2).zzj(zzsa.zzc(zzsfVar)).zzvb()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
        r0 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0100 A[Catch: all -> 0x0153, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000f, B:9:0x0014, B:10:0x0026, B:11:0x0027, B:13:0x0031, B:15:0x003d, B:17:0x0042, B:19:0x0046, B:23:0x0052, B:24:0x0059, B:56:0x012d, B:63:0x0141, B:62:0x013c, B:59:0x0133, B:30:0x0065, B:31:0x006e, B:33:0x0074, B:34:0x007f, B:36:0x0085, B:38:0x0091, B:41:0x0098, B:43:0x00a6, B:49:0x0100, B:50:0x0108, B:53:0x011b, B:55:0x0126), top: B:69:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0133 A[Catch: all -> 0x0153, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000f, B:9:0x0014, B:10:0x0026, B:11:0x0027, B:13:0x0031, B:15:0x003d, B:17:0x0042, B:19:0x0046, B:23:0x0052, B:24:0x0059, B:56:0x012d, B:63:0x0141, B:62:0x013c, B:59:0x0133, B:30:0x0065, B:31:0x006e, B:33:0x0074, B:34:0x007f, B:36:0x0085, B:38:0x0091, B:41:0x0098, B:43:0x00a6, B:49:0x0100, B:50:0x0108, B:53:0x011b, B:55:0x0126), top: B:69:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013c A[Catch: all -> 0x0153, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000f, B:9:0x0014, B:10:0x0026, B:11:0x0027, B:13:0x0031, B:15:0x003d, B:17:0x0042, B:19:0x0046, B:23:0x0052, B:24:0x0059, B:56:0x012d, B:63:0x0141, B:62:0x013c, B:59:0x0133, B:30:0x0065, B:31:0x006e, B:33:0x0074, B:34:0x007f, B:36:0x0085, B:38:0x0091, B:41:0x0098, B:43:0x00a6, B:49:0x0100, B:50:0x0108, B:53:0x011b, B:55:0x0126), top: B:69:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0108 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @androidx.annotation.WorkerThread
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.google.firebase.ml.vision.face.FirebaseVisionFace> zza(@androidx.annotation.NonNull com.google.android.gms.internal.firebase_ml.zzsf r22) throws com.google.firebase.ml.common.FirebaseMLException {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsh.zza(com.google.android.gms.internal.firebase_ml.zzsf):java.util.List");
    }

    @VisibleForTesting
    @WorkerThread
    public final synchronized List<FirebaseVisionFace> c(@NonNull FaceDetector faceDetector, @NonNull zzsf zzsfVar, long j) throws FirebaseMLException {
        ArrayList arrayList;
        if (this.e != null) {
            if (g == null) {
                g = Boolean.valueOf(DynamiteModule.getLocalVersion(this.f8805a, "com.google.android.gms.vision.dynamite.face") > 0);
            }
            if (!g.booleanValue()) {
                throw new FirebaseMLException("No Face Contour model is bundled. Please check your app setup to include firebase-ml-vision-face-model dependency.", 14);
            }
        }
        if (faceDetector.isOperational()) {
            SparseArray<Face> detect = faceDetector.detect(zzsfVar.zzbrv);
            arrayList = new ArrayList();
            for (int i = 0; i < detect.size(); i++) {
                arrayList.add(new FirebaseVisionFace(detect.get(detect.keyAt(i))));
            }
        } else {
            d(zzoc.MODEL_NOT_DOWNLOADED, j, zzsfVar, 0, 0);
            throw new FirebaseMLException("Waiting for the face detection model to be downloaded. Please wait.", 14);
        }
        return arrayList;
    }

    @WorkerThread
    public final synchronized void d(final zzoc zzocVar, long j, final zzsf zzsfVar, final int i, final int i2) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.c.zza(new zzqo(this, elapsedRealtime, zzocVar, i, i2, zzsfVar) { // from class: com.google.android.gms.internal.firebase_ml.a5

            /* renamed from: a  reason: collision with root package name */
            public final zzsh f8662a;
            public final long b;
            public final zzoc c;
            public final int d;
            public final int e;
            public final zzsf f;

            {
                this.f8662a = this;
                this.b = elapsedRealtime;
                this.c = zzocVar;
                this.d = i;
                this.e = i2;
                this.f = zzsfVar;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzqo
            public final zzns.zzad.zza zzok() {
                return this.f8662a.a(this.b, this.c, this.d, this.e, this.f);
            }
        }, zzod.ON_DEVICE_FACE_DETECT);
        zzqm zzqmVar = b5.f8667a;
        this.c.zza((zzns.zzd.zzb) ((zzwz) zzns.zzd.zzb.zzkg().zze(zzocVar).zzq(h.get()).zzd(zzsa.zzc(zzsfVar)).zzap(i).zzaq(i2).zza(this.b.zzqs()).zzvb()), elapsedRealtime, zzod.AGGREGATED_ON_DEVICE_FACE_DETECTION, zzqmVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void release() {
        FaceDetector faceDetector = this.d;
        if (faceDetector != null) {
            faceDetector.release();
            this.d = null;
        }
        FaceDetector faceDetector2 = this.e;
        if (faceDetector2 != null) {
            faceDetector2.release();
            this.e = null;
        }
        h.set(true);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    public final zzqp zzoc() {
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void zzol() {
        if (this.b.getContourMode() == 2) {
            if (this.e == null) {
                this.e = new FaceDetector.Builder(this.f8805a).setLandmarkType(2).setMode(2).setTrackingEnabled(false).setProminentFaceOnly(true).build();
            }
            if ((this.b.getLandmarkMode() == 2 || this.b.getClassificationMode() == 2 || this.b.getPerformanceMode() == 2) && this.d == null) {
                this.d = new FaceDetector.Builder(this.f8805a).setLandmarkType(zzsa.zzbw(this.b.getLandmarkMode())).setClassificationType(zzsa.zzby(this.b.getClassificationMode())).setMode(zzsa.zzbx(this.b.getPerformanceMode())).setMinFaceSize(this.b.getMinFaceSize()).setTrackingEnabled(this.b.isTrackingEnabled()).build();
            }
        } else if (this.d == null) {
            this.d = new FaceDetector.Builder(this.f8805a).setLandmarkType(zzsa.zzbw(this.b.getLandmarkMode())).setClassificationType(zzsa.zzby(this.b.getClassificationMode())).setMode(zzsa.zzbx(this.b.getPerformanceMode())).setMinFaceSize(this.b.getMinFaceSize()).setTrackingEnabled(this.b.isTrackingEnabled()).build();
        }
    }
}
