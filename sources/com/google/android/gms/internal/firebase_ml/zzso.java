package com.google.android.gms.internal.firebase_ml;

import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.vision.Frame;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.internal.modeldownload.zzn;
import com.google.firebase.ml.common.internal.modeldownload.zzt;
import com.google.firebase.ml.vision.automl.FirebaseAutoMLLocalModel;
import com.google.firebase.ml.vision.automl.FirebaseAutoMLRemoteModel;
import com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler;
import com.google.firebase.ml.vision.automl.internal.OnDeviceAutoMLImageLabelerOptionsParcel;
import com.google.firebase.ml.vision.automl.internal.zzg;
import com.google.firebase.ml.vision.automl.internal.zzj;
import com.google.firebase.ml.vision.automl.internal.zzl;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceAutoMLImageLabelerOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes7.dex */
public final class zzso implements zzpu<List<FirebaseVisionImageLabel>, zzsf>, zzqp {
    @VisibleForTesting
    public static final AtomicBoolean h = new AtomicBoolean(true);

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f8807a;
    public final FirebaseVisionOnDeviceAutoMLImageLabelerOptions b;
    public final zzqg c;
    public final FirebaseAutoMLRemoteModel d;
    public final FirebaseAutoMLLocalModel e;
    public IOnDeviceAutoMLImageLabeler f;
    public final AtomicBoolean g = new AtomicBoolean(false);

    public zzso(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionOnDeviceAutoMLImageLabelerOptions firebaseVisionOnDeviceAutoMLImageLabelerOptions) {
        this.f8807a = zzqfVar;
        this.b = firebaseVisionOnDeviceAutoMLImageLabelerOptions;
        this.c = zzqg.zza(zzqfVar, 5);
        this.d = firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqu();
        this.e = firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqt();
    }

    public final /* synthetic */ zzns.zzad.zza a(long j, zzoc zzocVar, zzsf zzsfVar, boolean z) {
        FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel;
        zzns.zzk.zza zzh = zzns.zzk.zzkx().zza(zzns.zzaf.zzme().zzk(j).zzk(zzocVar).zzah(h.get()).zzai(true).zzaj(true)).zzh(zzsa.zzc(zzsfVar));
        if (z && (firebaseAutoMLRemoteModel = this.d) != null) {
            zzh.zza(zzt.zza(firebaseAutoMLRemoteModel, zzn.AUTOML));
        } else {
            FirebaseAutoMLLocalModel firebaseAutoMLLocalModel = this.e;
            if (firebaseAutoMLLocalModel != null) {
                zzh.zza(firebaseAutoMLLocalModel.zza(zzn.AUTOML));
            }
        }
        return zzns.zzad.zzma().zza(zzh);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @WorkerThread
    /* renamed from: b */
    public final synchronized List<FirebaseVisionImageLabel> zza(zzsf zzsfVar) throws FirebaseMLException {
        Preconditions.checkNotNull(zzsfVar, "Mobile vision input can not be null");
        Preconditions.checkNotNull(zzsfVar.zzbrv, "Input frame can not be null");
        boolean z = this.g.get();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f != null) {
            if (zzsfVar.zzbrv.getBitmap() != null) {
                try {
                    IObjectWrapper wrap = ObjectWrapper.wrap(zzsfVar.zzbrv.getBitmap());
                    IOnDeviceAutoMLImageLabeler iOnDeviceAutoMLImageLabeler = this.f;
                    Frame.Metadata metadata = zzsfVar.zzbrv.getMetadata();
                    zzl[] zza = iOnDeviceAutoMLImageLabeler.zza(wrap, new zzsb(metadata.getWidth(), metadata.getHeight(), metadata.getId(), metadata.getTimestampMillis(), metadata.getRotation()));
                    c(zzoc.NO_ERROR, elapsedRealtime, z, zzsfVar);
                    if (zza == null) {
                        return new ArrayList();
                    }
                    ArrayList arrayList = new ArrayList();
                    for (zzl zzlVar : zza) {
                        arrayList.add(FirebaseVisionImageLabel.zza(zzlVar));
                    }
                    h.set(false);
                    return arrayList;
                } catch (RemoteException e) {
                    c(zzoc.UNKNOWN_ERROR, elapsedRealtime, z, zzsfVar);
                    Log.e("ODAutoMLImgLabelerTask", "Error calling on device auto ml inference.", e);
                    throw new FirebaseMLException("Cannot run on device automl image labeler.", 13, e);
                }
            }
            Log.e("ODAutoMLImgLabelerTask", "No image data found.");
            throw new FirebaseMLException("No image data found.", 3);
        }
        c(zzoc.UNKNOWN_ERROR, elapsedRealtime, z, zzsfVar);
        Log.e("ODAutoMLImgLabelerTask", "On device AutoML Image Labeler is not initialized.");
        throw new FirebaseMLException("Image labeler not initialized.", 13);
    }

    @WorkerThread
    public final void c(final zzoc zzocVar, long j, final boolean z, final zzsf zzsfVar) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.c.zza(new zzqo(this, elapsedRealtime, zzocVar, zzsfVar, z) { // from class: com.google.android.gms.internal.firebase_ml.f5

            /* renamed from: a  reason: collision with root package name */
            public final zzso f8678a;
            public final long b;
            public final zzoc c;
            public final zzsf d;
            public final boolean e;

            {
                this.f8678a = this;
                this.b = elapsedRealtime;
                this.c = zzocVar;
                this.d = zzsfVar;
                this.e = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzqo
            public final zzns.zzad.zza zzok() {
                return this.f8678a.a(this.b, this.c, this.d, this.e);
            }
        }, zzod.AUTOML_IMAGE_LABELING_RUN);
        zzqm zzqmVar = e5.f8676a;
        this.c.zza((zzns.zza.zzb) ((zzwz) zzns.zza.zzb.zzjx().zza(zzocVar).zzm(h.get()).zza(zzsa.zzc(zzsfVar)).zzvb()), elapsedRealtime, zzod.AGGREGATED_AUTO_ML_IMAGE_LABELING_INFERENCE, zzqmVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    public final void release() {
        try {
            IOnDeviceAutoMLImageLabeler iOnDeviceAutoMLImageLabeler = this.f;
            if (iOnDeviceAutoMLImageLabeler != null) {
                iOnDeviceAutoMLImageLabeler.close();
            }
            h.set(true);
        } catch (RemoteException e) {
            Log.e("ODAutoMLImgLabelerTask", "Error closing on device AutoML Image Labeler", e);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    public final zzqp zzoc() {
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final synchronized void zzol() throws FirebaseMLException {
        String str;
        try {
            try {
                if (this.f == null) {
                    zzg asInterface = zzj.asInterface(DynamiteModule.load(this.f8807a.getApplicationContext(), DynamiteModule.PREFER_LOCAL, "com.google.firebase.ml.vision.dynamite.automl").instantiate("com.google.firebase.ml.vision.automl.OnDeviceAutoMLImageLabelerCreator"));
                    if (asInterface != null) {
                        IObjectWrapper wrap = ObjectWrapper.wrap(this.f8807a);
                        FirebaseVisionOnDeviceAutoMLImageLabelerOptions firebaseVisionOnDeviceAutoMLImageLabelerOptions = this.b;
                        String str2 = null;
                        String modelName = firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqu() != null ? firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqu().getModelName() : null;
                        if (firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqt() != null) {
                            if (firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqt().getAssetFilePath() != null) {
                                str = null;
                                str2 = firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqt().getAssetFilePath();
                            } else if (firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqt().getFilePath() != null) {
                                str = firebaseVisionOnDeviceAutoMLImageLabelerOptions.zzqt().getFilePath();
                            }
                            this.f = asInterface.newOnDeviceAutoMLImageLabeler(wrap, new OnDeviceAutoMLImageLabelerOptionsParcel(firebaseVisionOnDeviceAutoMLImageLabelerOptions.getConfidenceThreshold(), modelName, str2, str));
                        }
                        str = null;
                        this.f = asInterface.newOnDeviceAutoMLImageLabeler(wrap, new OnDeviceAutoMLImageLabelerOptionsParcel(firebaseVisionOnDeviceAutoMLImageLabelerOptions.getConfidenceThreshold(), modelName, str2, str));
                    } else {
                        Log.e("ODAutoMLImgLabelerTask", "Error when creating on device AutoML Image Labeler creator.");
                        throw new FirebaseMLException("Can not create on device AutoML Image Labeler.", 14);
                    }
                }
                try {
                    this.f.zzol();
                    this.g.set(this.f.zzou());
                } catch (RemoteException e) {
                    Log.e("ODAutoMLImgLabelerTask", "Error while loading the AutoML image labeling model.", e);
                    throw new FirebaseMLException("Cannot load the AutoML image labeling model.", 14, e);
                }
            } catch (RemoteException e2) {
                Log.e("ODAutoMLImgLabelerTask", "Error when creating on device AutoML Image Labeler.", e2);
                throw new FirebaseMLException("Can not create on device AutoML Image Labeler.", 14, e2);
            } catch (DynamiteModule.LoadingException e3) {
                Log.e("ODAutoMLImgLabelerTask", "Error when loading automl module.", e3);
                throw new FirebaseMLException("Cannot load automl module. Please add dependency firebase-ml-vision-automl.", 14);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
