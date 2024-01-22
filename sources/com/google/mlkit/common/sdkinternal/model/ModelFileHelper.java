package com.google.mlkit.common.sdkinternal.model;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
@KeepForSdk
/* loaded from: classes10.dex */
public class ModelFileHelper {
    @KeepForSdk
    public static final int INVALID_INDEX = -1;

    /* renamed from: a  reason: collision with root package name */
    public final MlKitContext f11598a;
    public static final GmsLogger b = new GmsLogger("ModelFileHelper", "");
    @NonNull
    @VisibleForTesting
    public static final String zza = String.format("com.google.mlkit.%s.models", "translate");
    @NonNull
    @VisibleForTesting
    public static final String zzb = String.format("com.google.mlkit.%s.models", "custom");
    @VisibleForTesting
    public static final String c = String.format("com.google.mlkit.%s.models", "base");

    public ModelFileHelper(@NonNull MlKitContext mlKitContext) {
        this.f11598a = mlKitContext;
    }

    @WorkerThread
    public final File a(@NonNull String str, @NonNull ModelType modelType, boolean z) throws MlKitException {
        File modelDirUnsafe = getModelDirUnsafe(str, modelType, z);
        if (!modelDirUnsafe.exists()) {
            b.d("ModelFileHelper", "model folder does not exist, creating one: ".concat(String.valueOf(modelDirUnsafe.getAbsolutePath())));
            if (!modelDirUnsafe.mkdirs()) {
                throw new MlKitException("Failed to create model folder: ".concat(String.valueOf(modelDirUnsafe)), 13);
            }
        } else if (!modelDirUnsafe.isDirectory()) {
            throw new MlKitException("Can not create model folder, since an existing file has the same name: ".concat(String.valueOf(modelDirUnsafe)), 6);
        }
        return modelDirUnsafe;
    }

    @KeepForSdk
    @WorkerThread
    public synchronized void deleteAllModels(@NonNull ModelType modelType, @NonNull String str) {
        deleteRecursively(getModelDirUnsafe(str, modelType, false));
        deleteRecursively(getModelDirUnsafe(str, modelType, true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002a, code lost:
        if (r5 != false) goto L21;
     */
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean deleteRecursively(@androidx.annotation.Nullable java.io.File r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            boolean r1 = r8.isDirectory()
            r2 = 1
            if (r1 == 0) goto L2c
            java.io.File[] r1 = r8.listFiles()
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)
            java.io.File[] r1 = (java.io.File[]) r1
            int r3 = r1.length
            r4 = r0
            r5 = r2
        L18:
            if (r4 >= r3) goto L2a
            r6 = r1[r4]
            if (r5 == 0) goto L26
            boolean r5 = r7.deleteRecursively(r6)
            if (r5 == 0) goto L26
            r5 = r2
            goto L27
        L26:
            r5 = r0
        L27:
            int r4 = r4 + 1
            goto L18
        L2a:
            if (r5 == 0) goto L33
        L2c:
            boolean r8 = r8.delete()
            if (r8 == 0) goto L33
            return r2
        L33:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelFileHelper.deleteRecursively(java.io.File):boolean");
    }

    @KeepForSdk
    @WorkerThread
    public void deleteTempFilesInPrivateFolder(@NonNull String str, @NonNull ModelType modelType) throws MlKitException {
        File a2 = a(str, modelType, true);
        if (deleteRecursively(a2)) {
            return;
        }
        b.e("ModelFileHelper", "Failed to delete the temp labels file directory: ".concat(String.valueOf(a2 != null ? a2.getAbsolutePath() : null)));
    }

    @KeepForSdk
    @WorkerThread
    public int getLatestCachedModelVersion(@NonNull File file) {
        File[] listFiles = file.listFiles();
        int i = -1;
        if (listFiles != null && (r1 = listFiles.length) != 0) {
            for (File file2 : listFiles) {
                try {
                    i = Math.max(i, Integer.parseInt(file2.getName()));
                } catch (NumberFormatException unused) {
                    b.d("ModelFileHelper", "Contains non-integer file name ".concat(String.valueOf(file2.getName())));
                }
            }
        }
        return i;
    }

    @NonNull
    @KeepForSdk
    @WorkerThread
    public File getModelDir(@NonNull String str, @NonNull ModelType modelType) throws MlKitException {
        return a(str, modelType, false);
    }

    @NonNull
    @KeepForSdk
    @WorkerThread
    public File getModelDirUnsafe(@NonNull String str, @NonNull ModelType modelType, boolean z) {
        String str2;
        File dir;
        ModelType modelType2 = ModelType.UNKNOWN;
        int ordinal = modelType.ordinal();
        if (ordinal == 1) {
            str2 = c;
        } else if (ordinal == 2) {
            str2 = zza;
        } else if (ordinal == 4) {
            str2 = zzb;
        } else {
            String name = modelType.name();
            throw new IllegalArgumentException("Unknown model type " + name + ". Cannot find a dir to store the downloaded model.");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            dir = new File(this.f11598a.getApplicationContext().getNoBackupFilesDir(), str2);
        } else {
            dir = this.f11598a.getApplicationContext().getDir(str2, 0);
        }
        if (z) {
            dir = new File(dir, "temp");
        }
        return new File(dir, str);
    }

    @NonNull
    @KeepForSdk
    @WorkerThread
    public File getModelTempDir(@NonNull String str, @NonNull ModelType modelType) throws MlKitException {
        return a(str, modelType, true);
    }

    @NonNull
    @KeepForSdk
    @WorkerThread
    public File getTempFileInPrivateFolder(@NonNull String str, @NonNull ModelType modelType, @NonNull String str2) throws MlKitException {
        File a2 = a(str, modelType, true);
        if (a2.exists() && a2.isFile() && !a2.delete()) {
            throw new MlKitException("Failed to delete the temp labels file: ".concat(String.valueOf(a2.getAbsolutePath())), 13);
        }
        if (!a2.exists()) {
            b.d("ModelFileHelper", "Temp labels folder does not exist, creating one: ".concat(String.valueOf(a2.getAbsolutePath())));
            if (!a2.mkdirs()) {
                throw new MlKitException("Failed to create a directory to hold the AutoML model's labels file.", 13);
            }
        }
        return new File(a2, str2);
    }

    @KeepForSdk
    @WorkerThread
    public boolean modelExistsLocally(@NonNull String str, @NonNull ModelType modelType) throws MlKitException {
        String zzb2;
        if (modelType == ModelType.UNKNOWN || (zzb2 = zzb(str, modelType)) == null) {
            return false;
        }
        File file = new File(zzb2);
        if (file.exists()) {
            File file2 = new File(file, Constants.MODEL_FILE_NAME);
            b.i("ModelFileHelper", "Model file path: ".concat(String.valueOf(file2.getAbsolutePath())));
            return file2.exists();
        }
        return false;
    }

    @NonNull
    @WorkerThread
    public final File zza(@NonNull String str, @NonNull ModelType modelType) throws MlKitException {
        return a(str, modelType, true);
    }

    @Nullable
    @WorkerThread
    public final String zzb(@NonNull String str, @NonNull ModelType modelType) throws MlKitException {
        File modelDir = getModelDir(str, modelType);
        int latestCachedModelVersion = getLatestCachedModelVersion(modelDir);
        if (latestCachedModelVersion == -1) {
            return null;
        }
        String absolutePath = modelDir.getAbsolutePath();
        return absolutePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + latestCachedModelVersion;
    }
}
