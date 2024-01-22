package com.google.firebase.ml.vision.barcode.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzpu;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqm;
import com.google.android.gms.internal.firebase_ml.zzqo;
import com.google.android.gms.internal.firebase_ml.zzqp;
import com.google.android.gms.internal.firebase_ml.zzry;
import com.google.android.gms.internal.firebase_ml.zzsa;
import com.google.android.gms.internal.firebase_ml.zzsb;
import com.google.android.gms.internal.firebase_ml.zzsf;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzc implements zzpu<List<FirebaseVisionBarcode>, zzsf>, zzqp {
    @VisibleForTesting
    public static boolean g = true;

    /* renamed from: a  reason: collision with root package name */
    public final Context f11433a;
    public final FirebaseVisionBarcodeDetectorOptions b;
    public final zzqg c;
    public final zzry d = new zzry();
    @Nullable
    public IBarcodeDetector e;
    @Nullable
    public BarcodeDetector f;

    public zzc(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        Preconditions.checkNotNull(zzqfVar, "MlKitContext can not be null");
        Preconditions.checkNotNull(firebaseVisionBarcodeDetectorOptions, "FirebaseVisionBarcodeDetectorOptions can not be null");
        this.f11433a = zzqfVar.getApplicationContext();
        this.b = firebaseVisionBarcodeDetectorOptions;
        this.c = zzqg.zza(zzqfVar, 1);
    }

    public final /* synthetic */ zzns.zzad.zza a(long j, zzoc zzocVar, List list, List list2, zzsf zzsfVar) {
        return zzns.zzad.zzma().zzac(this.e != null).zza(zzns.zzam.zzmt().zzc(zzns.zzaf.zzme().zzk(j).zzk(zzocVar).zzah(g).zzai(true).zzaj(true)).zzc(this.b.zzqi()).zzs(list).zzt(list2).zzi(zzsa.zzc(zzsfVar)));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @WorkerThread
    /* renamed from: b */
    public final synchronized List<FirebaseVisionBarcode> zza(@NonNull zzsf zzsfVar) throws FirebaseMLException {
        ArrayList arrayList;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.d.zzb(zzsfVar);
        arrayList = new ArrayList();
        if (this.e != null) {
            try {
                IObjectWrapper wrap = ObjectWrapper.wrap(zzsfVar.zzbrv);
                Frame.Metadata metadata = zzsfVar.zzbrv.getMetadata();
                for (zzd zzdVar : (List) ObjectWrapper.unwrap(this.e.zzb(wrap, new zzsb(metadata.getWidth(), metadata.getHeight(), metadata.getId(), metadata.getTimestampMillis(), metadata.getRotation())))) {
                    arrayList.add(new FirebaseVisionBarcode(zzdVar));
                }
            } catch (RemoteException e) {
                throw new FirebaseMLException("Failed to run barcode detector.", 14, e);
            }
        } else {
            BarcodeDetector barcodeDetector = this.f;
            if (barcodeDetector != null) {
                if (barcodeDetector.isOperational()) {
                    SparseArray<Barcode> detect = this.f.detect(zzsfVar.zzbrv);
                    for (int i = 0; i < detect.size(); i++) {
                        arrayList.add(new FirebaseVisionBarcode(new zzf(detect.get(detect.keyAt(i)))));
                    }
                } else {
                    c(zzoc.MODEL_NOT_DOWNLOADED, elapsedRealtime, zzsfVar, null);
                    throw new FirebaseMLException("Waiting for the barcode detection model to be downloaded. Please wait.", 14);
                }
            } else {
                c(zzoc.UNKNOWN_ERROR, elapsedRealtime, zzsfVar, null);
                throw new FirebaseMLException("Model source is unavailable. Please load the model resource first.", 14);
            }
        }
        c(zzoc.NO_ERROR, elapsedRealtime, zzsfVar, arrayList);
        g = false;
        return arrayList;
    }

    @WorkerThread
    public final void c(final zzoc zzocVar, long j, @NonNull final zzsf zzsfVar, @Nullable List<FirebaseVisionBarcode> list) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        if (list != null) {
            for (FirebaseVisionBarcode firebaseVisionBarcode : list) {
                arrayList.add(firebaseVisionBarcode.zzqf());
                arrayList2.add(firebaseVisionBarcode.zzqg());
            }
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.c.zza(new zzqo(this, elapsedRealtime, zzocVar, arrayList, arrayList2, zzsfVar) { // from class: com.google.firebase.ml.vision.barcode.internal.a

            /* renamed from: a  reason: collision with root package name */
            public final zzc f11432a;
            public final long b;
            public final zzoc c;
            public final List d;
            public final List e;
            public final zzsf f;

            {
                this.f11432a = this;
                this.b = elapsedRealtime;
                this.c = zzocVar;
                this.d = arrayList;
                this.e = arrayList2;
                this.f = zzsfVar;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzqo
            public final zzns.zzad.zza zzok() {
                return this.f11432a.a(this.b, this.c, this.d, this.e, this.f);
            }
        }, zzod.ON_DEVICE_BARCODE_DETECT);
        zzqm zzqmVar = new zzqm(this) { // from class: com.google.firebase.ml.vision.barcode.internal.b
        };
        this.c.zza((zzns.zzc.zzb) ((zzwz) zzns.zzc.zzb.zzkd().zzd(zzocVar).zzp(g).zzc(zzsa.zzc(zzsfVar)).zzb(this.b.zzqi()).zzj(arrayList).zzk(arrayList2).zzvb()), elapsedRealtime, zzod.AGGREGATED_ON_DEVICE_BARCODE_DETECTION, zzqmVar);
    }

    @Nullable
    @VisibleForTesting
    public final IBarcodeDetector d() throws FirebaseMLException {
        if (DynamiteModule.getLocalVersion(this.f11433a, "com.google.firebase.ml.vision.dynamite.barcode") > 0) {
            try {
                return zzh.asInterface(DynamiteModule.load(this.f11433a, DynamiteModule.PREFER_LOCAL, "com.google.firebase.ml.vision.dynamite.barcode").instantiate("com.google.firebase.ml.vision.barcode.BarcodeDetectorCreator")).newBarcodeDetector(new BarcodeDetectorOptionsParcel(this.b.zzqh()));
            } catch (RemoteException | DynamiteModule.LoadingException e) {
                throw new FirebaseMLException("Failed to load barcode detector module.", 14, e);
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void release() {
        IBarcodeDetector iBarcodeDetector = this.e;
        if (iBarcodeDetector != null) {
            try {
                iBarcodeDetector.stop();
            } catch (RemoteException e) {
                Log.e("BarcodeDetectorTask", "Failed to stop barcode detector pipeline.", e);
            }
            this.e = null;
        }
        BarcodeDetector barcodeDetector = this.f;
        if (barcodeDetector != null) {
            barcodeDetector.release();
            this.f = null;
        }
        g = true;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    public final zzqp zzoc() {
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void zzol() throws FirebaseMLException {
        if (this.e == null) {
            this.e = d();
        }
        IBarcodeDetector iBarcodeDetector = this.e;
        if (iBarcodeDetector != null) {
            try {
                iBarcodeDetector.start();
                return;
            } catch (RemoteException e) {
                throw new FirebaseMLException("Failed to start barcode detector pipeline.", 14, e);
            }
        }
        if (this.f == null) {
            this.f = new BarcodeDetector.Builder(this.f11433a).setBarcodeFormats(this.b.zzqh()).build();
        }
    }
}
