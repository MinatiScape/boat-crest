package com.google.mlkit.common.sdkinternal.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
@KeepForSdk
/* loaded from: classes10.dex */
public class RemoteModelFileManager {
    public static final GmsLogger h = new GmsLogger("RemoteModelFileManager", "");

    /* renamed from: a  reason: collision with root package name */
    public final MlKitContext f11602a;
    public final String b;
    public final ModelType c;
    @Nullable
    public final ModelValidator d;
    public final RemoteModelFileMover e;
    public final SharedPrefManager f;
    public final ModelFileHelper g;

    @SuppressLint({"FirebaseLambdaLast"})
    public RemoteModelFileManager(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @Nullable ModelValidator modelValidator, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        String uniqueModelNameForPersist;
        this.f11602a = mlKitContext;
        ModelType modelType = remoteModel.getModelType();
        this.c = modelType;
        if (modelType == ModelType.TRANSLATE) {
            uniqueModelNameForPersist = remoteModel.getModelNameForBackend();
        } else {
            uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        }
        this.b = uniqueModelNameForPersist;
        this.d = modelValidator;
        this.f = SharedPrefManager.getInstance(mlKitContext);
        this.g = modelFileHelper;
        this.e = remoteModelFileMover;
    }

    @NonNull
    @KeepForSdk
    public File getModelDirUnsafe(boolean z) {
        return this.g.getModelDirUnsafe(this.b, this.c, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0098, code lost:
        com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager.h.d("RemoteModelFileManager", "Hash does not match with expected: ".concat(java.lang.String.valueOf(r12)));
        com.google.android.gms.internal.mlkit_common.zzqk.zzb("common").zzf(com.google.android.gms.internal.mlkit_common.zzqc.zzg(), r13, com.google.android.gms.internal.mlkit_common.zzlm.MODEL_HASH_MISMATCH, true, r10.c, com.google.android.gms.internal.mlkit_common.zzls.SUCCEEDED);
        r11 = new com.google.mlkit.common.MlKitException("Hash does not match with expected", 102);
     */
    @androidx.annotation.Nullable
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.io.File moveModelToPrivateFolder(@androidx.annotation.NonNull android.os.ParcelFileDescriptor r11, @androidx.annotation.NonNull java.lang.String r12, @androidx.annotation.NonNull com.google.mlkit.common.model.RemoteModel r13) throws com.google.mlkit.common.MlKitException {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager.moveModelToPrivateFolder(android.os.ParcelFileDescriptor, java.lang.String, com.google.mlkit.common.model.RemoteModel):java.io.File");
    }

    @NonNull
    @WorkerThread
    public final synchronized File zza(@NonNull File file) throws MlKitException {
        File file2 = new File(String.valueOf(this.g.getModelDir(this.b, this.c).getAbsolutePath()).concat("/0"));
        if (file2.exists()) {
            return file;
        }
        return file.renameTo(file2) ? file2 : file;
    }

    @Nullable
    @WorkerThread
    public final synchronized String zzb() throws MlKitException {
        return this.g.zzb(this.b, this.c);
    }

    @WorkerThread
    public final synchronized void zzc(@NonNull File file) {
        File modelDirUnsafe = getModelDirUnsafe(false);
        if (modelDirUnsafe.exists()) {
            File[] listFiles = modelDirUnsafe.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.equals(file)) {
                        this.g.deleteRecursively(file);
                        return;
                    }
                }
            }
        }
    }

    @WorkerThread
    public final synchronized boolean zzd(@NonNull File file) throws MlKitException {
        File modelDir = this.g.getModelDir(this.b, this.c);
        if (modelDir.exists()) {
            File[] listFiles = modelDir.listFiles();
            boolean z = true;
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.equals(file) && !this.g.deleteRecursively(file2)) {
                        z = false;
                    }
                }
                return z;
            }
            return true;
        }
        return false;
    }
}
