package com.google.firebase.ml.vision.objects.internal;

import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.firebase_ml.zzmw;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzpu;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqm;
import com.google.android.gms.internal.firebase_ml.zzqo;
import com.google.android.gms.internal.firebase_ml.zzqp;
import com.google.android.gms.internal.firebase_ml.zzsa;
import com.google.android.gms.internal.firebase_ml.zzsb;
import com.google.android.gms.internal.firebase_ml.zzsf;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.android.gms.vision.Frame;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.objects.FirebaseVisionObject;
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes10.dex */
public final class zze implements zzpu<List<FirebaseVisionObject>, zzsf>, zzqp {
    public static final AtomicBoolean g = new AtomicBoolean(true);

    /* renamed from: a  reason: collision with root package name */
    public final FirebaseVisionObjectDetectorOptions f11467a;
    public final zzqf b;
    public final zzqg c;
    public long d = -1;
    @VisibleForTesting
    public final zzns.zzas e;
    @GuardedBy("this")
    public IObjectDetector f;

    public zze(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionObjectDetectorOptions firebaseVisionObjectDetectorOptions) {
        zzns.zzas.zza zzaVar;
        Preconditions.checkNotNull(zzqfVar, "Context can not be null");
        Preconditions.checkNotNull(firebaseVisionObjectDetectorOptions, "FirebaseVisionObjectDetectorOptions can not be null");
        this.f11467a = firebaseVisionObjectDetectorOptions;
        this.b = zzqfVar;
        zzqg zza = zzqg.zza(zzqfVar, 1);
        this.c = zza;
        zzns.zzas.zzb zznh = zzns.zzas.zznh();
        int zzqw = firebaseVisionObjectDetectorOptions.zzqw();
        if (zzqw == 1) {
            zzaVar = zzns.zzas.zza.STREAM;
        } else if (zzqw != 2) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Unexpected detector mode: ");
            sb.append(zzqw);
            Log.e("ObjectDetectorTask", sb.toString());
            zzaVar = zzns.zzas.zza.MODE_UNSPECIFIED;
        } else {
            zzaVar = zzns.zzas.zza.SINGLE_IMAGE;
        }
        zzns.zzas zzasVar = (zzns.zzas) ((zzwz) zznh.zzb(zzaVar).zzao(firebaseVisionObjectDetectorOptions.zzqy()).zzap(firebaseVisionObjectDetectorOptions.zzqx()).zzvb());
        this.e = zzasVar;
        zza.zza(zzns.zzad.zzma().zza(zzns.zzar.zznf().zzc(zzasVar).zzm(zzoc.NO_ERROR)), zzod.ON_DEVICE_OBJECT_CREATE);
    }

    public final /* synthetic */ zzns.zzad.zza a(List list, long j, zzoc zzocVar, zzsf zzsfVar) {
        zzns.zzal.zzb zzbVar;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            FirebaseVisionObject firebaseVisionObject = (FirebaseVisionObject) it.next();
            zzns.zzal.zza zzmr = zzns.zzal.zzmr();
            int classificationCategory = firebaseVisionObject.getClassificationCategory();
            if (classificationCategory == 0) {
                zzbVar = zzns.zzal.zzb.CATEGORY_UNKNOWN;
            } else if (classificationCategory == 1) {
                zzbVar = zzns.zzal.zzb.CATEGORY_HOME_GOOD;
            } else if (classificationCategory == 2) {
                zzbVar = zzns.zzal.zzb.CATEGORY_FASHION_GOOD;
            } else if (classificationCategory == 3) {
                zzbVar = zzns.zzal.zzb.CATEGORY_FOOD;
            } else if (classificationCategory == 4) {
                zzbVar = zzns.zzal.zzb.CATEGORY_PLACE;
            } else if (classificationCategory != 5) {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Unexpected category: ");
                sb.append(classificationCategory);
                Log.e("ObjectDetectorTask", sb.toString());
                zzbVar = zzns.zzal.zzb.CATEGORY_UNKNOWN;
            } else {
                zzbVar = zzns.zzal.zzb.CATEGORY_PLANT;
            }
            zzns.zzal.zza zza = zzmr.zza(zzbVar);
            if (firebaseVisionObject.getClassificationConfidence() != null) {
                zza.zzm(firebaseVisionObject.getClassificationConfidence().floatValue());
            }
            if (firebaseVisionObject.getTrackingId() != null) {
                zza.zzbh(firebaseVisionObject.getTrackingId().intValue());
            }
            arrayList.add((zzns.zzal) ((zzwz) zza.zzvb()));
        }
        return zzns.zzad.zzma().zza(zzns.zzat.zznj().zzf(zzns.zzaf.zzme().zzk(j).zzk(zzocVar).zzah(g.get()).zzai(true).zzaj(true)).zzl(zzsa.zzc(zzsfVar)).zzd(this.e).zzu(arrayList));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @WorkerThread
    /* renamed from: b */
    public final synchronized List<FirebaseVisionObject> zza(zzsf zzsfVar) throws FirebaseMLException {
        Preconditions.checkNotNull(zzsfVar, "Mobile vision input can not bu null");
        Preconditions.checkNotNull(zzsfVar.zzbsk, "Input image can not be null");
        Preconditions.checkNotNull(zzsfVar.zzbrv, "Input frame can not be null");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            if (this.f == null) {
                Log.e("ObjectDetectorTask", "Object detector is not initialized.");
                return zzmw.zzji();
            }
            boolean z = true;
            if (this.f11467a.zzqw() == 1) {
                long j = this.d;
                if (j > 0) {
                    if (elapsedRealtime - j <= 300) {
                        z = false;
                    }
                    if (z) {
                        Log.w("ObjectDetectorTask", "Object detector pipeline is reset.");
                        f();
                    }
                }
            }
            this.d = elapsedRealtime;
            IObjectWrapper wrap = ObjectWrapper.wrap(zzsfVar.zzbrv);
            IObjectDetector iObjectDetector = this.f;
            Frame.Metadata metadata = zzsfVar.zzbrv.getMetadata();
            zzj[] zzc = iObjectDetector.zzc(wrap, new zzsb(metadata.getWidth(), metadata.getHeight(), metadata.getId(), metadata.getTimestampMillis(), metadata.getRotation()));
            ArrayList arrayList = new ArrayList();
            for (zzj zzjVar : zzc) {
                arrayList.add(new FirebaseVisionObject(zzjVar));
            }
            c(zzoc.NO_ERROR, zzsfVar, arrayList, elapsedRealtime);
            g.set(false);
            return arrayList;
        } catch (RemoteException e) {
            Log.e("ObjectDetectorTask", "Error calling object detector inference", e);
            c(zzoc.UNKNOWN_ERROR, zzsfVar, zzmw.zzji(), elapsedRealtime);
            throw new FirebaseMLException("Cannot run object detector.", 14);
        }
    }

    @WorkerThread
    public final void c(final zzoc zzocVar, final zzsf zzsfVar, final List<FirebaseVisionObject> list, long j) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.c.zza(new zzqo(this, list, elapsedRealtime, zzocVar, zzsfVar) { // from class: com.google.firebase.ml.vision.objects.internal.b

            /* renamed from: a  reason: collision with root package name */
            public final zze f11466a;
            public final List b;
            public final long c;
            public final zzoc d;
            public final zzsf e;

            {
                this.f11466a = this;
                this.b = list;
                this.c = elapsedRealtime;
                this.d = zzocVar;
                this.e = zzsfVar;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzqo
            public final zzns.zzad.zza zzok() {
                return this.f11466a.a(this.b, this.c, this.d, this.e);
            }
        }, zzod.ON_DEVICE_OBJECT_INFERENCE);
        zzqm zzqmVar = a.f11465a;
        this.c.zza((zzns.zzf.zzb) ((zzwz) zzns.zzf.zzb.zzkm().zza(this.e).zzg(zzocVar).zzt(g.get()).zzf(zzsa.zzc(zzsfVar)).zzs(!list.isEmpty()).zzvb()), elapsedRealtime, zzod.AGGREGATED_ON_DEVICE_OBJECT_INFERENCE, zzqmVar);
    }

    public final void d(zzoc zzocVar) {
        this.c.zza(zzns.zzad.zzma().zza(zzns.zzau.zznl().zze(this.e).zzn(zzocVar)), zzod.ON_DEVICE_OBJECT_LOAD);
    }

    @Nullable
    @VisibleForTesting
    public final zzc e() throws FirebaseMLException {
        try {
            return zzb.asInterface(DynamiteModule.load(this.b.getApplicationContext(), DynamiteModule.PREFER_LOCAL, "com.google.firebase.ml.vision.dynamite.objects").instantiate("com.google.firebase.ml.vision.objects.ObjectDetectorCreator"));
        } catch (DynamiteModule.LoadingException e) {
            Log.e("ObjectDetectorTask", "Error when loading object detector module");
            throw new FirebaseMLException("Cannot load object detector module.", 14, e);
        }
    }

    @VisibleForTesting
    @WorkerThread
    public final synchronized void f() {
        try {
            IObjectDetector iObjectDetector = this.f;
            if (iObjectDetector != null) {
                iObjectDetector.stop();
            }
        } catch (RemoteException e) {
            Log.e("ObjectDetectorTask", "Error calling object detector stop", e);
        }
        try {
            IObjectDetector iObjectDetector2 = this.f;
            if (iObjectDetector2 != null) {
                iObjectDetector2.start();
            }
        } catch (RemoteException e2) {
            Log.e("ObjectDetectorTask", "Error calling object detector restart", e2);
        }
        g.set(true);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void release() {
        try {
            IObjectDetector iObjectDetector = this.f;
            if (iObjectDetector != null) {
                iObjectDetector.stop();
            }
            g.set(true);
            this.c.zza(zzns.zzad.zzma(), zzod.ON_DEVICE_OBJECT_CLOSE);
        } catch (RemoteException e) {
            Log.e("ObjectDetectorTask", "Error calling object detector stop", e);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @NonNull
    public final zzqp zzoc() {
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void zzol() throws FirebaseMLException {
        try {
            if (this.f == null) {
                zzc e = e();
                if (e != null) {
                    IObjectWrapper wrap = ObjectWrapper.wrap(this.b.getApplicationContext());
                    FirebaseVisionObjectDetectorOptions firebaseVisionObjectDetectorOptions = this.f11467a;
                    this.f = e.newObjectDetector(wrap, new ObjectDetectorOptionsParcel(firebaseVisionObjectDetectorOptions.zzqw(), firebaseVisionObjectDetectorOptions.zzqy(), firebaseVisionObjectDetectorOptions.zzqx()));
                    d(zzoc.NO_ERROR);
                } else {
                    Log.e("ObjectDetectorTask", "Error when creating object detector creator");
                    d(zzoc.UNKNOWN_ERROR);
                    throw new FirebaseMLException("Cannot load object detector module.", 14);
                }
            }
            this.f.start();
        } catch (RemoteException e2) {
            Log.e("ObjectDetectorTask", "Error when creating object detector");
            d(zzoc.UNKNOWN_ERROR);
            throw new FirebaseMLException("Can not create ObjectDetector", 14, e2);
        }
    }
}
