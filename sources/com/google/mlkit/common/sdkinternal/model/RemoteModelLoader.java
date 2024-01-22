package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;
@KeepForSdk
/* loaded from: classes10.dex */
public class RemoteModelLoader {
    public static final GmsLogger h = new GmsLogger("RemoteModelLoader", "");
    @GuardedBy("RemoteModelLoader.class")
    public static final Map i = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final MlKitContext f11603a;
    public final RemoteModel b;
    public final RemoteModelDownloadManager c;
    public final RemoteModelFileManager d;
    public final RemoteModelLoaderHelper e;
    public final zzpz f;
    public boolean g;

    public RemoteModelLoader(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelValidator modelValidator, @NonNull RemoteModelLoaderHelper remoteModelLoaderHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, remoteModel, modelValidator, new ModelFileHelper(mlKitContext), remoteModelFileMover);
        this.d = remoteModelFileManager;
        this.g = true;
        this.c = RemoteModelDownloadManager.getInstance(mlKitContext, remoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
        this.e = remoteModelLoaderHelper;
        this.f11603a = mlKitContext;
        this.b = remoteModel;
        this.f = zzqk.zzb("common");
    }

    @NonNull
    @KeepForSdk
    public static synchronized RemoteModelLoader getInstance(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelValidator modelValidator, @NonNull RemoteModelLoaderHelper remoteModelLoaderHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        RemoteModelLoader remoteModelLoader;
        synchronized (RemoteModelLoader.class) {
            String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
            Map map = i;
            if (!map.containsKey(uniqueModelNameForPersist)) {
                map.put(uniqueModelNameForPersist, new RemoteModelLoader(mlKitContext, remoteModel, modelValidator, remoteModelLoaderHelper, remoteModelFileMover));
            }
            remoteModelLoader = (RemoteModelLoader) map.get(uniqueModelNameForPersist);
        }
        return remoteModelLoader;
    }

    @NonNull
    @WorkerThread
    public final MappedByteBuffer a(@NonNull String str) throws MlKitException {
        return this.e.loadModelAtPath(str);
    }

    public final MappedByteBuffer b(File file) throws MlKitException {
        try {
            return a(file.getAbsolutePath());
        } catch (Exception e) {
            this.d.zzc(file);
            throw new MlKitException("Failed to load newly downloaded model.", 14, e);
        }
    }

    @NonNull
    @KeepForSdk
    public RemoteModel getRemoteModel() {
        return this.b;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00b5 A[Catch: all -> 0x00f6, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0020, B:9:0x0028, B:26:0x00b5, B:28:0x00c4, B:30:0x00cc, B:33:0x00d2, B:34:0x00f0, B:35:0x00f1, B:10:0x002f, B:12:0x0046, B:15:0x004f, B:17:0x006d, B:19:0x0075, B:20:0x0087, B:22:0x008f, B:23:0x00a6), top: B:42:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f1 A[Catch: all -> 0x00f6, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0020, B:9:0x0028, B:26:0x00b5, B:28:0x00c4, B:30:0x00cc, B:33:0x00d2, B:34:0x00f0, B:35:0x00f1, B:10:0x002f, B:12:0x0046, B:15:0x004f, B:17:0x006d, B:19:0x0075, B:20:0x0087, B:22:0x008f, B:23:0x00a6), top: B:42:0x0001, inners: #1 }] */
    @androidx.annotation.Nullable
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.nio.MappedByteBuffer load() throws com.google.mlkit.common.MlKitException {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelLoader.load():java.nio.MappedByteBuffer");
    }
}
