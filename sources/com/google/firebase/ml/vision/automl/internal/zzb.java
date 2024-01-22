package com.google.firebase.ml.vision.automl.internal;

import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzpx;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.internal.modeldownload.RemoteModelManagerInterface;
import com.google.firebase.ml.common.internal.modeldownload.zzn;
import com.google.firebase.ml.common.internal.modeldownload.zzv;
import com.google.firebase.ml.common.internal.modeldownload.zzw;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.vision.automl.FirebaseAutoMLRemoteModel;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public class zzb implements RemoteModelManagerInterface<FirebaseAutoMLRemoteModel> {

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f11415a;
    public final zzqg b;

    public zzb(zzqf zzqfVar, zzqg zzqgVar) {
        this.f11415a = zzqfVar;
        this.b = zzqgVar;
    }

    public final /* synthetic */ Boolean a(FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel) throws Exception {
        zzqf zzqfVar = this.f11415a;
        return Boolean.valueOf(zzv.zza(zzqfVar, firebaseAutoMLRemoteModel, new com.google.firebase.ml.common.internal.modeldownload.zzg(zzqfVar), new zzw(this.f11415a, firebaseAutoMLRemoteModel)).zzpf());
    }

    public final /* synthetic */ Void b(FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel) throws Exception {
        new com.google.firebase.ml.common.internal.modeldownload.zzi(this.f11415a).zza(zzn.AUTOML, firebaseAutoMLRemoteModel.getModelName());
        return null;
    }

    public final /* synthetic */ void c(Task task) {
        this.b.zza(zzns.zzad.zzma().zza((zzns.zzag) ((zzwz) zzns.zzag.zzmg().zzc(zzns.zzaj.zzb.AUTOML_IMAGE_LABELING).zzal(((Boolean) task.getResult()).booleanValue()).zzvb())), zzod.REMOTE_MODEL_IS_DOWNLOADED);
    }

    public final /* synthetic */ void d(Task task) {
        this.b.zza(zzns.zzad.zzma().zza((zzns.zzaa) ((zzwz) zzns.zzaa.zzlu().zza(zzns.zzaj.zzb.AUTOML_IMAGE_LABELING).zzy(task.isSuccessful()).zzvb())), zzod.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    @Override // com.google.firebase.ml.common.internal.modeldownload.RemoteModelManagerInterface
    public /* synthetic */ Task deleteDownloadedModel(FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel) {
        final FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel2 = firebaseAutoMLRemoteModel;
        return Tasks.call(zzpx.zzoe(), new Callable(this, firebaseAutoMLRemoteModel2) { // from class: com.google.firebase.ml.vision.automl.internal.a
            public final zzb h;
            public final FirebaseAutoMLRemoteModel i;

            {
                this.h = this;
                this.i = firebaseAutoMLRemoteModel2;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.h.b(this.i);
            }
        }).addOnCompleteListener(new OnCompleteListener(this) { // from class: com.google.firebase.ml.vision.automl.internal.c

            /* renamed from: a  reason: collision with root package name */
            public final zzb f11412a;

            {
                this.f11412a = this;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f11412a.d(task);
            }
        });
    }

    @Override // com.google.firebase.ml.common.internal.modeldownload.RemoteModelManagerInterface
    public /* synthetic */ Task download(FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel, FirebaseModelDownloadConditions firebaseModelDownloadConditions) {
        FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel2 = firebaseAutoMLRemoteModel;
        zzqf zzqfVar = this.f11415a;
        final zzv zza = zzv.zza(zzqfVar, firebaseAutoMLRemoteModel2, new com.google.firebase.ml.common.internal.modeldownload.zzg(zzqfVar), new zzw(this.f11415a, firebaseAutoMLRemoteModel2));
        zza.zza(firebaseModelDownloadConditions);
        return Tasks.forResult(null).onSuccessTask(zzpx.zzoe(), new SuccessContinuation(zza) { // from class: com.google.firebase.ml.vision.automl.internal.d

            /* renamed from: a  reason: collision with root package name */
            public final zzv f11413a;

            {
                this.f11413a = zza;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return this.f11413a.zzpg();
            }
        });
    }

    @Override // com.google.firebase.ml.common.internal.modeldownload.RemoteModelManagerInterface
    public Task<Set<FirebaseAutoMLRemoteModel>> getDownloadedModels() {
        return Tasks.forException(new FirebaseMLException("AutoML Remote model does not support listing downloaded models", 12));
    }

    @Override // com.google.firebase.ml.common.internal.modeldownload.RemoteModelManagerInterface
    public /* synthetic */ Task getLatestModelFile(FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel) {
        return Tasks.forException(new FirebaseMLException("Getting latest model file not supported for AutoML models.", 12));
    }

    @Override // com.google.firebase.ml.common.internal.modeldownload.RemoteModelManagerInterface
    public /* synthetic */ Task isModelDownloaded(FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel) {
        final FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel2 = firebaseAutoMLRemoteModel;
        return zzpx.zzof().zza(new Callable(this, firebaseAutoMLRemoteModel2) { // from class: com.google.firebase.ml.vision.automl.internal.b
            public final zzb h;
            public final FirebaseAutoMLRemoteModel i;

            {
                this.h = this;
                this.i = firebaseAutoMLRemoteModel2;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.h.a(this.i);
            }
        }).addOnCompleteListener(new OnCompleteListener(this) { // from class: com.google.firebase.ml.vision.automl.internal.e

            /* renamed from: a  reason: collision with root package name */
            public final zzb f11414a;

            {
                this.f11414a = this;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f11414a.c(task);
            }
        });
    }
}
