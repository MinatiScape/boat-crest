package com.google.mlkit.common.sdkinternal.model;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzlm;
import com.google.android.gms.internal.mlkit_common.zzls;
import com.google.android.gms.internal.mlkit_common.zzpq;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
@KeepForSdk
/* loaded from: classes10.dex */
public class RemoteModelDownloadManager {
    public static final GmsLogger m = new GmsLogger("ModelDownloadManager", "");
    @GuardedBy("RemoteModelDownloadManager.class")
    public static final Map n = new HashMap();
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final LongSparseArray f11601a = new LongSparseArray();
    @GuardedBy("this")
    public final LongSparseArray b = new LongSparseArray();
    public final MlKitContext c;
    @Nullable
    public final DownloadManager d;
    public final RemoteModel e;
    public final ModelType f;
    public final zzpz g;
    public final SharedPrefManager h;
    public final ModelFileHelper i;
    @Nullable
    public final ModelInfoRetrieverInterop j;
    public final RemoteModelFileManager k;
    public DownloadConditions l;

    @VisibleForTesting
    public RemoteModelDownloadManager(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileManager remoteModelFileManager, @Nullable ModelInfoRetrieverInterop modelInfoRetrieverInterop, @NonNull zzpz zzpzVar) {
        this.c = mlKitContext;
        this.f = remoteModel.getModelType();
        this.e = remoteModel;
        DownloadManager downloadManager = (DownloadManager) mlKitContext.getApplicationContext().getSystemService("download");
        this.d = downloadManager;
        this.g = zzpzVar;
        if (downloadManager == null) {
            m.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.i = modelFileHelper;
        this.h = SharedPrefManager.getInstance(mlKitContext);
        this.j = modelInfoRetrieverInterop;
        this.k = remoteModelFileManager;
    }

    @NonNull
    @KeepForSdk
    public static synchronized RemoteModelDownloadManager getInstance(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileManager remoteModelFileManager, @Nullable ModelInfoRetrieverInterop modelInfoRetrieverInterop) {
        RemoteModelDownloadManager remoteModelDownloadManager;
        synchronized (RemoteModelDownloadManager.class) {
            Map map = n;
            if (!map.containsKey(remoteModel)) {
                map.put(remoteModel, new RemoteModelDownloadManager(mlKitContext, remoteModel, modelFileHelper, remoteModelFileManager, modelInfoRetrieverInterop, zzqk.zzb("common")));
            }
            remoteModelDownloadManager = (RemoteModelDownloadManager) map.get(remoteModel);
        }
        return remoteModelDownloadManager;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ac, code lost:
        r1 = n(r1, r13.l);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b2, code lost:
        if (r1 == null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00bc, code lost:
        return i(r1.longValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00bd, code lost:
        com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.m.i("ModelDownloadManager", "Didn't schedule download for the updated model");
     */
    @androidx.annotation.NonNull
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.gms.tasks.Task<java.lang.Void> ensureModelDownloaded() {
        /*
            r13 = this;
            com.google.android.gms.internal.mlkit_common.zzpz r0 = r13.g
            com.google.android.gms.internal.mlkit_common.zzpq r1 = com.google.android.gms.internal.mlkit_common.zzqc.zzg()
            com.google.mlkit.common.model.RemoteModel r2 = r13.e
            com.google.android.gms.internal.mlkit_common.zzlm r3 = com.google.android.gms.internal.mlkit_common.zzlm.NO_ERROR
            com.google.mlkit.common.sdkinternal.ModelType r5 = com.google.mlkit.common.sdkinternal.ModelType.UNKNOWN
            com.google.android.gms.internal.mlkit_common.zzls r6 = com.google.android.gms.internal.mlkit_common.zzls.EXPLICITLY_REQUESTED
            r4 = 0
            r0.zzf(r1, r2, r3, r4, r5, r6)
            r0 = 0
            com.google.mlkit.common.sdkinternal.ModelInfo r1 = r13.g()     // Catch: com.google.mlkit.common.MlKitException -> L19
            r2 = r0
            goto L1c
        L19:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L1c:
            r3 = 13
            java.lang.Integer r4 = r13.getDownloadingModelStatusCode()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            java.lang.Long r5 = r13.getDownloadingId()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            boolean r6 = r13.modelExistsLocally()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            if (r6 != 0) goto Laa
            if (r4 == 0) goto L38
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r7 = 8
            if (r6 != r7) goto L38
            goto Laa
        L38:
            if (r4 == 0) goto L4e
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r7 = 16
            if (r6 != r7) goto L4e
            com.google.mlkit.common.MlKitException r0 = r13.k(r5)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r13.removeOrCancelDownload()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            return r0
        L4e:
            if (r4 == 0) goto L8a
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r7 = 4
            if (r6 == r7) goto L65
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r7 = 2
            if (r6 == r7) goto L65
            int r4 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r6 = 1
            if (r4 != r6) goto L8a
        L65:
            if (r5 == 0) goto L8a
            java.lang.String r4 = r13.getDownloadingModelHash()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            if (r4 == 0) goto L8a
            com.google.android.gms.internal.mlkit_common.zzpz r6 = r13.g     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.internal.mlkit_common.zzpq r7 = com.google.android.gms.internal.mlkit_common.zzqc.zzg()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.mlkit.common.model.RemoteModel r8 = r13.e     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.internal.mlkit_common.zzlm r9 = com.google.android.gms.internal.mlkit_common.zzlm.NO_ERROR     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r10 = 0
            com.google.mlkit.common.sdkinternal.ModelType r11 = r8.getModelType()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.internal.mlkit_common.zzls r12 = com.google.android.gms.internal.mlkit_common.zzls.DOWNLOADING     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            r6.zzf(r7, r8, r9, r10, r11, r12)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            long r0 = r5.longValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.tasks.Task r0 = r13.i(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            return r0
        L8a:
            if (r1 != 0) goto L8d
            goto L93
        L8d:
            com.google.mlkit.common.model.DownloadConditions r0 = r13.l     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            java.lang.Long r0 = r13.n(r1, r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
        L93:
            if (r0 != 0) goto La1
            com.google.mlkit.common.MlKitException r0 = new com.google.mlkit.common.MlKitException     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            java.lang.String r1 = "Failed to schedule the download task"
            r0.<init>(r1, r3, r2)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            return r0
        La1:
            long r0 = r0.longValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.tasks.Task r0 = r13.i(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            return r0
        Laa:
            if (r1 == 0) goto Lc6
            com.google.mlkit.common.model.DownloadConditions r2 = r13.l     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            java.lang.Long r1 = r13.n(r1, r2)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            if (r1 == 0) goto Lbd
            long r0 = r1.longValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            com.google.android.gms.tasks.Task r0 = r13.i(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            return r0
        Lbd:
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.m     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            java.lang.String r2 = "ModelDownloadManager"
            java.lang.String r4 = "Didn't schedule download for the updated model"
            r1.i(r2, r4)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
        Lc6:
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forResult(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcb
            return r0
        Lcb:
            r0 = move-exception
            com.google.mlkit.common.MlKitException r1 = new com.google.mlkit.common.MlKitException
            java.lang.String r2 = "Failed to ensure the model is downloaded."
            r1.<init>(r2, r3, r0)
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.ensureModelDownloaded():com.google.android.gms.tasks.Task");
    }

    @Nullable
    @WorkerThread
    public final synchronized ModelInfo g() throws MlKitException {
        boolean z;
        boolean modelExistsLocally = modelExistsLocally();
        if (modelExistsLocally) {
            zzpz zzpzVar = this.g;
            zzpq zzg = zzqc.zzg();
            RemoteModel remoteModel = this.e;
            zzpzVar.zzf(zzg, remoteModel, zzlm.NO_ERROR, false, remoteModel.getModelType(), zzls.LIVE);
        }
        ModelInfoRetrieverInterop modelInfoRetrieverInterop = this.j;
        if (modelInfoRetrieverInterop != null) {
            ModelInfo retrieveRemoteModelInfo = modelInfoRetrieverInterop.retrieveRemoteModelInfo(this.e);
            if (retrieveRemoteModelInfo == null) {
                return null;
            }
            MlKitContext mlKitContext = this.c;
            RemoteModel remoteModel2 = this.e;
            String modelHash = retrieveRemoteModelInfo.getModelHash();
            SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(mlKitContext);
            boolean equals = modelHash.equals(sharedPrefManager.getIncompatibleModelHash(remoteModel2));
            boolean z2 = false;
            if (equals && CommonUtils.getAppVersion(mlKitContext.getApplicationContext()).equals(sharedPrefManager.getPreviousAppVersion())) {
                m.e("ModelDownloadManager", "The model is incompatible with TFLite and the app is not upgraded, do not download");
                z = false;
            } else {
                z = true;
            }
            if (!modelExistsLocally) {
                this.h.clearLatestModelHash(this.e);
            }
            boolean z3 = !retrieveRemoteModelInfo.getModelHash().equals(SharedPrefManager.getInstance(this.c).getLatestModelHash(this.e));
            if (!z) {
                z2 = z3;
            } else if (!modelExistsLocally || z3) {
                return retrieveRemoteModelInfo;
            }
            if (modelExistsLocally && (z2 ^ z)) {
                return null;
            }
            throw new MlKitException("The model " + this.e.getModelName() + " is incompatible with TFLite runtime", 100);
        }
        throw new MlKitException("Please include com.google.mlkit:linkfirebase sdk as your dependency when you try to download from Firebase.", 14);
    }

    @Nullable
    @KeepForSdk
    public synchronized ParcelFileDescriptor getDownloadedFile() {
        Long downloadingId = getDownloadingId();
        DownloadManager downloadManager = this.d;
        ParcelFileDescriptor parcelFileDescriptor = null;
        if (downloadManager == null || downloadingId == null) {
            return null;
        }
        try {
            parcelFileDescriptor = downloadManager.openDownloadedFile(downloadingId.longValue());
        } catch (FileNotFoundException unused) {
            m.e("ModelDownloadManager", "Downloaded file is not found");
        }
        return parcelFileDescriptor;
    }

    @Nullable
    @KeepForSdk
    public synchronized Long getDownloadingId() {
        return this.h.getDownloadingModelId(this.e);
    }

    @Nullable
    @KeepForSdk
    public synchronized String getDownloadingModelHash() {
        return this.h.getDownloadingModelHash(this.e);
    }

    @Nullable
    @KeepForSdk
    public synchronized Integer getDownloadingModelStatusCode() {
        Long downloadingId = getDownloadingId();
        DownloadManager downloadManager = this.d;
        Integer num = null;
        if (downloadManager != null && downloadingId != null) {
            Cursor query = downloadManager.query(new DownloadManager.Query().setFilterById(downloadingId.longValue()));
            Integer valueOf = (query == null || !query.moveToFirst()) ? null : Integer.valueOf(query.getInt(query.getColumnIndex(NotificationCompat.CATEGORY_STATUS)));
            if (valueOf == null) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            if (valueOf.intValue() == 2 || valueOf.intValue() == 4 || valueOf.intValue() == 1 || valueOf.intValue() == 8 || valueOf.intValue() == 16) {
                num = valueOf;
            }
            query.close();
            return num;
        }
        return null;
    }

    @KeepForSdk
    public int getFailureReason(@NonNull Long l) {
        int columnIndex;
        DownloadManager downloadManager = this.d;
        Cursor cursor = null;
        if (downloadManager != null && l != null) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(l.longValue()));
        }
        if (cursor == null || !cursor.moveToFirst() || (columnIndex = cursor.getColumnIndex("reason")) == -1) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }

    public final Task i(long j) {
        this.c.getApplicationContext().registerReceiver(l(j), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), null, MLTaskExecutor.getInstance().getHandler());
        return j(j).getTask();
    }

    @KeepForSdk
    @WorkerThread
    public boolean isModelDownloadedAndValid() throws MlKitException {
        try {
            if (modelExistsLocally()) {
                return true;
            }
        } catch (MlKitException unused) {
            m.d("ModelDownloadManager", "Failed to check if the model exist locally.");
        }
        Long downloadingId = getDownloadingId();
        String downloadingModelHash = getDownloadingModelHash();
        if (downloadingId != null && downloadingModelHash != null) {
            Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
            m.d("ModelDownloadManager", "Download Status code: ".concat(String.valueOf(downloadingModelStatusCode)));
            if (downloadingModelStatusCode != null) {
                return Objects.equal(downloadingModelStatusCode, 8) && zzi(downloadingModelHash) != null;
            }
            removeOrCancelDownload();
            return false;
        }
        m.d("ModelDownloadManager", "No new model is downloading.");
        removeOrCancelDownload();
        return false;
    }

    public final synchronized TaskCompletionSource j(long j) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.b.get(j);
        if (taskCompletionSource == null) {
            TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            this.b.put(j, taskCompletionSource2);
            return taskCompletionSource2;
        }
        return taskCompletionSource;
    }

    public final MlKitException k(@Nullable Long l) {
        DownloadManager downloadManager = this.d;
        Cursor cursor = null;
        if (downloadManager != null && l != null) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(l.longValue()));
        }
        int i = 13;
        String str = "Model downloading failed";
        if (cursor != null && cursor.moveToFirst()) {
            int i2 = cursor.getInt(cursor.getColumnIndex("reason"));
            if (i2 == 1006) {
                i = 101;
                str = "Model downloading failed due to insufficient space on the device.";
            } else {
                str = "Model downloading failed due to error code: " + i2 + " from Android DownloadManager";
            }
        }
        return new MlKitException(str, i);
    }

    public final synchronized a l(long j) {
        a aVar = (a) this.f11601a.get(j);
        if (aVar == null) {
            a aVar2 = new a(this, j, j(j), null);
            this.f11601a.put(j, aVar2);
            return aVar2;
        }
        return aVar;
    }

    @Nullable
    public final synchronized Long m(@NonNull DownloadManager.Request request, @NonNull ModelInfo modelInfo) {
        DownloadManager downloadManager = this.d;
        if (downloadManager == null) {
            return null;
        }
        long enqueue = downloadManager.enqueue(request);
        GmsLogger gmsLogger = m;
        gmsLogger.d("ModelDownloadManager", "Schedule a new downloading task: " + enqueue);
        this.h.setDownloadingModelInfo(enqueue, modelInfo);
        this.g.zzf(zzqc.zzg(), this.e, zzlm.NO_ERROR, false, modelInfo.getModelType(), zzls.SCHEDULED);
        return Long.valueOf(enqueue);
    }

    @KeepForSdk
    public boolean modelExistsLocally() throws MlKitException {
        return this.i.modelExistsLocally(this.e.getUniqueModelNameForPersist(), this.f);
    }

    @Nullable
    @WorkerThread
    public final synchronized Long n(@NonNull ModelInfo modelInfo, @NonNull DownloadConditions downloadConditions) throws MlKitException {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        String downloadingModelHash = this.h.getDownloadingModelHash(this.e);
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        if (downloadingModelHash != null && downloadingModelHash.equals(modelInfo.getModelHash()) && downloadingModelStatusCode != null) {
            Integer downloadingModelStatusCode2 = getDownloadingModelStatusCode();
            if (downloadingModelStatusCode2 == null || (downloadingModelStatusCode2.intValue() != 8 && downloadingModelStatusCode2.intValue() != 16)) {
                zzpz zzpzVar = this.g;
                zzpq zzg = zzqc.zzg();
                RemoteModel remoteModel = this.e;
                zzpzVar.zzf(zzg, remoteModel, zzlm.NO_ERROR, false, remoteModel.getModelType(), zzls.DOWNLOADING);
            }
            m.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
            return null;
        }
        GmsLogger gmsLogger = m;
        gmsLogger.d("ModelDownloadManager", "Need to download a new model.");
        removeOrCancelDownload();
        DownloadManager.Request request = new DownloadManager.Request(modelInfo.getModelUri());
        if (this.i.modelExistsLocally(modelInfo.getModelNameForPersist(), modelInfo.getModelType())) {
            gmsLogger.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
            this.g.zzf(zzqc.zzg(), this.e, zzlm.NO_ERROR, false, modelInfo.getModelType(), zzls.UPDATE_AVAILABLE);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            request.setRequiresCharging(downloadConditions.isChargingRequired());
        }
        if (downloadConditions.isWifiRequired()) {
            request.setAllowedNetworkTypes(2);
        }
        return m(request, modelInfo);
    }

    @KeepForSdk
    public synchronized void removeOrCancelDownload() throws MlKitException {
        Long downloadingId = getDownloadingId();
        if (this.d != null && downloadingId != null) {
            m.d("ModelDownloadManager", "Cancel or remove existing downloading task: ".concat(downloadingId.toString()));
            if (this.d.remove(downloadingId.longValue()) > 0 || getDownloadingModelStatusCode() == null) {
                this.i.deleteTempFilesInPrivateFolder(this.e.getUniqueModelNameForPersist(), this.e.getModelType());
                this.h.clearDownloadingModelInfo(this.e);
            }
        }
    }

    @KeepForSdk
    public void setDownloadConditions(@NonNull DownloadConditions downloadConditions) {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        this.l = downloadConditions;
    }

    @KeepForSdk
    public synchronized void updateLatestModelHashAndType(@NonNull String str) throws MlKitException {
        this.h.setLatestModelHash(this.e, str);
        removeOrCancelDownload();
    }

    @Nullable
    public final File zzi(@NonNull String str) throws MlKitException {
        GmsLogger gmsLogger = m;
        gmsLogger.d("ModelDownloadManager", "Model downloaded successfully");
        this.g.zzf(zzqc.zzg(), this.e, zzlm.NO_ERROR, true, this.f, zzls.SUCCEEDED);
        ParcelFileDescriptor downloadedFile = getDownloadedFile();
        if (downloadedFile == null) {
            removeOrCancelDownload();
            return null;
        }
        gmsLogger.d("ModelDownloadManager", "moving downloaded model from external storage to private folder.");
        try {
            return this.k.moveModelToPrivateFolder(downloadedFile, str, this.e);
        } finally {
            removeOrCancelDownload();
        }
    }
}
