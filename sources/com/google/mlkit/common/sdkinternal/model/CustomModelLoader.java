package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
@KeepForSdk
/* loaded from: classes10.dex */
public class CustomModelLoader {
    public static final GmsLogger h = new GmsLogger("CustomModelLoader", "");
    @GuardedBy("CustomModelLoader.class")
    public static final Map i = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final MlKitContext f11595a;
    @Nullable
    public final LocalModel b;
    @Nullable
    public final CustomRemoteModel c;
    @Nullable
    public final RemoteModelDownloadManager d;
    @Nullable
    public final RemoteModelFileManager e;
    public final zzpz f;
    public boolean g;

    @KeepForSdk
    /* loaded from: classes10.dex */
    public interface CustomModelLoaderHelper {
        @KeepForSdk
        void logLoad() throws MlKitException;

        @KeepForSdk
        boolean tryLoad(@NonNull LocalModel localModel) throws MlKitException;
    }

    public CustomModelLoader(@NonNull MlKitContext mlKitContext, @Nullable LocalModel localModel, @Nullable CustomRemoteModel customRemoteModel) {
        if (customRemoteModel != null) {
            RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, customRemoteModel, null, new ModelFileHelper(mlKitContext), new com.google.mlkit.common.internal.model.zza(mlKitContext, customRemoteModel.getUniqueModelNameForPersist()));
            this.e = remoteModelFileManager;
            this.d = RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
            this.g = true;
        } else {
            this.e = null;
            this.d = null;
        }
        this.f11595a = mlKitContext;
        this.b = localModel;
        this.c = customRemoteModel;
        this.f = zzqk.zzb("common");
    }

    @WorkerThread
    public static final LocalModel c(File file) {
        if (file.isDirectory()) {
            LocalModel.Builder builder = new LocalModel.Builder();
            builder.setAbsoluteManifestFilePath(new File(file.getAbsolutePath(), Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME).toString());
            return builder.build();
        }
        LocalModel.Builder builder2 = new LocalModel.Builder();
        builder2.setAbsoluteFilePath(file.getAbsolutePath());
        return builder2.build();
    }

    @NonNull
    @KeepForSdk
    public static synchronized CustomModelLoader getInstance(@NonNull MlKitContext mlKitContext, @Nullable LocalModel localModel, @Nullable CustomRemoteModel customRemoteModel) {
        String uniqueModelNameForPersist;
        CustomModelLoader customModelLoader;
        synchronized (CustomModelLoader.class) {
            if (customRemoteModel == null) {
                uniqueModelNameForPersist = ((LocalModel) Preconditions.checkNotNull(localModel)).toString();
            } else {
                uniqueModelNameForPersist = customRemoteModel.getUniqueModelNameForPersist();
            }
            Map map = i;
            if (!map.containsKey(uniqueModelNameForPersist)) {
                map.put(uniqueModelNameForPersist, new CustomModelLoader(mlKitContext, localModel, customRemoteModel));
            }
            customModelLoader = (CustomModelLoader) map.get(uniqueModelNameForPersist);
        }
        return customModelLoader;
    }

    @Nullable
    @WorkerThread
    public final File a() throws MlKitException {
        String zzb = ((RemoteModelFileManager) Preconditions.checkNotNull(this.e)).zzb();
        if (zzb == null) {
            h.d("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(zzb);
        File[] listFiles = file.listFiles();
        return ((File[]) Preconditions.checkNotNull(listFiles)).length == 1 ? listFiles[0] : file;
    }

    @WorkerThread
    public final void b() throws MlKitException {
        ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.d)).removeOrCancelDownload();
    }

    @VisibleForTesting
    @Nullable
    @KeepForSdk
    @WorkerThread
    public synchronized LocalModel createLocalModelByLatestExistingModel() throws MlKitException {
        h.d("CustomModelLoader", "Try to get the latest existing model file.");
        File a2 = a();
        if (a2 == null) {
            return null;
        }
        return c(a2);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0098 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009a A[Catch: all -> 0x00a0, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0022, B:9:0x002a, B:24:0x009a, B:10:0x002e, B:12:0x0045, B:15:0x004e, B:16:0x0067, B:18:0x006f, B:19:0x008b), top: B:30:0x0001 }] */
    @androidx.annotation.VisibleForTesting
    @androidx.annotation.Nullable
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.google.mlkit.common.model.LocalModel createLocalModelByNewlyDownloadedModel() throws com.google.mlkit.common.MlKitException {
        /*
            r7 = this;
            monitor-enter(r7)
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.mlkit.common.sdkinternal.model.CustomModelLoader.h     // Catch: java.lang.Throwable -> La0
            java.lang.String r1 = "CustomModelLoader"
            java.lang.String r2 = "Try to get newly downloaded model file."
            r0.d(r1, r2)     // Catch: java.lang.Throwable -> La0
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r7.d     // Catch: java.lang.Throwable -> La0
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)     // Catch: java.lang.Throwable -> La0
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = (com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager) r1     // Catch: java.lang.Throwable -> La0
            java.lang.Long r1 = r1.getDownloadingId()     // Catch: java.lang.Throwable -> La0
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r2 = r7.d     // Catch: java.lang.Throwable -> La0
            java.lang.String r2 = r2.getDownloadingModelHash()     // Catch: java.lang.Throwable -> La0
            r3 = 0
            if (r1 == 0) goto L8b
            if (r2 != 0) goto L22
            goto L8b
        L22:
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r4 = r7.d     // Catch: java.lang.Throwable -> La0
            java.lang.Integer r4 = r4.getDownloadingModelStatusCode()     // Catch: java.lang.Throwable -> La0
            if (r4 != 0) goto L2e
            r7.b()     // Catch: java.lang.Throwable -> La0
            goto L95
        L2e:
            java.lang.String r5 = "Download Status code: "
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> La0
            java.lang.String r5 = r5.concat(r6)     // Catch: java.lang.Throwable -> La0
            java.lang.String r6 = "CustomModelLoader"
            r0.d(r6, r5)     // Catch: java.lang.Throwable -> La0
            int r5 = r4.intValue()     // Catch: java.lang.Throwable -> La0
            r6 = 8
            if (r5 != r6) goto L67
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r7.d     // Catch: java.lang.Throwable -> La0
            java.io.File r1 = r1.zzi(r2)     // Catch: java.lang.Throwable -> La0
            if (r1 != 0) goto L4e
            goto L95
        L4e:
            java.lang.String r4 = r1.getParent()     // Catch: java.lang.Throwable -> La0
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> La0
            java.lang.String r5 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r6 = "CustomModelLoader"
            java.lang.String r4 = r5.concat(r4)     // Catch: java.lang.Throwable -> La0
            r0.d(r6, r4)     // Catch: java.lang.Throwable -> La0
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r7.d     // Catch: java.lang.Throwable -> La0
            r0.updateLatestModelHashAndType(r2)     // Catch: java.lang.Throwable -> La0
            goto L96
        L67:
            int r0 = r4.intValue()     // Catch: java.lang.Throwable -> La0
            r2 = 16
            if (r0 != r2) goto L95
            com.google.android.gms.internal.mlkit_common.zzpz r0 = r7.f     // Catch: java.lang.Throwable -> La0
            com.google.android.gms.internal.mlkit_common.zzpq r2 = com.google.android.gms.internal.mlkit_common.zzqc.zzg()     // Catch: java.lang.Throwable -> La0
            com.google.mlkit.common.model.CustomRemoteModel r4 = r7.c     // Catch: java.lang.Throwable -> La0
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch: java.lang.Throwable -> La0
            com.google.mlkit.common.model.RemoteModel r4 = (com.google.mlkit.common.model.RemoteModel) r4     // Catch: java.lang.Throwable -> La0
            r5 = 0
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r6 = r7.d     // Catch: java.lang.Throwable -> La0
            int r1 = r6.getFailureReason(r1)     // Catch: java.lang.Throwable -> La0
            r0.zze(r2, r4, r5, r1)     // Catch: java.lang.Throwable -> La0
            r7.b()     // Catch: java.lang.Throwable -> La0
            goto L95
        L8b:
            java.lang.String r1 = "CustomModelLoader"
            java.lang.String r2 = "No new model is downloading."
            r0.d(r1, r2)     // Catch: java.lang.Throwable -> La0
            r7.b()     // Catch: java.lang.Throwable -> La0
        L95:
            r1 = r3
        L96:
            if (r1 != 0) goto L9a
            monitor-exit(r7)
            return r3
        L9a:
            com.google.mlkit.common.model.LocalModel r0 = c(r1)     // Catch: java.lang.Throwable -> La0
            monitor-exit(r7)
            return r0
        La0:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.CustomModelLoader.createLocalModelByNewlyDownloadedModel():com.google.mlkit.common.model.LocalModel");
    }

    @KeepForSdk
    @VisibleForTesting
    @WorkerThread
    public void deleteLatestExistingModel() throws MlKitException {
        File a2 = a();
        if (a2 != null) {
            ((RemoteModelFileManager) Preconditions.checkNotNull(this.e)).zzc(a2);
            SharedPrefManager.getInstance(this.f11595a).clearLatestModelHash((RemoteModel) Preconditions.checkNotNull(this.c));
        }
    }

    @KeepForSdk
    @VisibleForTesting
    @WorkerThread
    public void deleteOldModels(@NonNull LocalModel localModel) throws MlKitException {
        File parentFile = new File((String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath())).getParentFile();
        if (((RemoteModelFileManager) Preconditions.checkNotNull(this.e)).zzd((File) Preconditions.checkNotNull(parentFile))) {
            h.d("CustomModelLoader", "All old models are deleted.");
            this.e.zza(parentFile);
            return;
        }
        h.e("CustomModelLoader", "Failed to delete old models");
    }

    @KeepForSdk
    @WorkerThread
    public synchronized void load(@NonNull CustomModelLoaderHelper customModelLoaderHelper) throws MlKitException {
        LocalModel localModel = this.b;
        if (localModel == null) {
            localModel = createLocalModelByNewlyDownloadedModel();
        }
        if (localModel == null) {
            localModel = createLocalModelByLatestExistingModel();
        }
        if (localModel != null) {
            while (!customModelLoaderHelper.tryLoad(localModel)) {
                if (this.c != null) {
                    deleteLatestExistingModel();
                    localModel = createLocalModelByLatestExistingModel();
                    continue;
                } else {
                    localModel = null;
                    continue;
                }
                if (localModel == null) {
                    customModelLoaderHelper.logLoad();
                    return;
                }
            }
            if (this.c != null && this.g) {
                deleteOldModels((LocalModel) Preconditions.checkNotNull(localModel));
                this.g = false;
            }
            customModelLoaderHelper.logLoad();
            return;
        }
        throw new MlKitException("Model is not available.", 14);
    }
}
