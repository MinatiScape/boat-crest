package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzki;
import com.google.android.gms.internal.mlkit_common.zzkz;
import com.google.android.gms.internal.mlkit_common.zzln;
import com.google.android.gms.internal.mlkit_common.zzlo;
import com.google.android.gms.internal.mlkit_common.zzlw;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class zzg implements RemoteModelManagerInterface {

    /* renamed from: a  reason: collision with root package name */
    public final MlKitContext f11573a;
    public final zzpz b;

    public zzg(MlKitContext mlKitContext) {
        zzpz zzb = zzqk.zzb("common");
        this.f11573a = mlKitContext;
        this.b = zzb;
    }

    public final /* synthetic */ Boolean a(CustomRemoteModel customRemoteModel) throws Exception {
        return Boolean.valueOf(e(customRemoteModel).isModelDownloadedAndValid());
    }

    public final /* synthetic */ void b(CustomRemoteModel customRemoteModel, TaskCompletionSource taskCompletionSource) {
        try {
            new ModelFileHelper(this.f11573a).deleteAllModels(ModelType.CUSTOM, (String) Preconditions.checkNotNull(customRemoteModel.getModelName()));
            taskCompletionSource.setResult(null);
        } catch (RuntimeException e) {
            taskCompletionSource.setException(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e));
        }
    }

    public final /* synthetic */ void c(Task task) {
        boolean isSuccessful = task.isSuccessful();
        zzpz zzpzVar = this.b;
        zzlo zzloVar = new zzlo();
        zzki zzkiVar = new zzki();
        zzkiVar.zzb(zzlw.CUSTOM);
        zzkiVar.zza(Boolean.valueOf(isSuccessful));
        zzloVar.zze(zzkiVar.zzc());
        zzpzVar.zzd(zzqc.zzf(zzloVar), zzln.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    public final /* synthetic */ void d(Task task) {
        boolean booleanValue = ((Boolean) task.getResult()).booleanValue();
        zzpz zzpzVar = this.b;
        zzlo zzloVar = new zzlo();
        zzkz zzkzVar = new zzkz();
        zzkzVar.zzb(zzlw.CUSTOM);
        zzkzVar.zza(Boolean.valueOf(booleanValue));
        zzloVar.zzg(zzkzVar.zzc());
        zzpzVar.zzd(zzqc.zzf(zzloVar), zzln.REMOTE_MODEL_IS_DOWNLOADED);
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task deleteDownloadedModel(RemoteModel remoteModel) {
        final CustomRemoteModel customRemoteModel = (CustomRemoteModel) remoteModel;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.mlkit.common.internal.model.zzc
            @Override // java.lang.Runnable
            public final void run() {
                zzg.this.b(customRemoteModel, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.common.internal.model.zzd
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzg.this.c(task);
            }
        });
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task download(RemoteModel remoteModel, DownloadConditions downloadConditions) {
        final RemoteModelDownloadManager e = e((CustomRemoteModel) remoteModel);
        e.setDownloadConditions(downloadConditions);
        return Tasks.forResult(null).onSuccessTask(MLTaskExecutor.workerThreadExecutor(), new SuccessContinuation() { // from class: com.google.mlkit.common.internal.model.zzb
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return RemoteModelDownloadManager.this.ensureModelDownloaded();
            }
        });
    }

    public final RemoteModelDownloadManager e(CustomRemoteModel customRemoteModel) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(this.f11573a, customRemoteModel, null, new ModelFileHelper(this.f11573a), new zza(this.f11573a, customRemoteModel.getUniqueModelNameForPersist()));
        MlKitContext mlKitContext = this.f11573a;
        return RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final Task<Set<CustomRemoteModel>> getDownloadedModels() {
        return Tasks.forException(new MlKitException("Custom Remote model does not support listing downloaded models", 12));
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task isModelDownloaded(RemoteModel remoteModel) {
        final CustomRemoteModel customRemoteModel = (CustomRemoteModel) remoteModel;
        return MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.mlkit.common.internal.model.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzg.this.a(customRemoteModel);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.common.internal.model.zzf
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzg.this.d(task);
            }
        });
    }
}
