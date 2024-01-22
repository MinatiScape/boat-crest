package com.google.mlkit.common.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover;
import java.io.File;
/* loaded from: classes10.dex */
public final class zza implements RemoteModelFileMover {
    public static final GmsLogger c = new GmsLogger("CustomModelFileMover", "");

    /* renamed from: a  reason: collision with root package name */
    public final String f11572a;
    public final ModelFileHelper b;

    public zza(@NonNull MlKitContext mlKitContext, @NonNull String str) {
        this.f11572a = str;
        this.b = new ModelFileHelper(mlKitContext);
    }

    public static boolean a(File file, File file2) {
        String absolutePath = file.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        if (file.renameTo(file2)) {
            c.d("CustomModelFileMover", String.format("Moved file from %s to %s successfully", absolutePath, absolutePath2));
            file2.setExecutable(false);
            file2.setWritable(false);
            return true;
        }
        GmsLogger gmsLogger = c;
        gmsLogger.d("CustomModelFileMover", String.format("Move file to %s failed, remove the temp file %s.", absolutePath2, absolutePath));
        if (!file.delete()) {
            gmsLogger.d("CustomModelFileMover", "Failed to delete the temp file: ".concat(String.valueOf(absolutePath)));
        }
        return false;
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover
    public final File getModelFileDestination() throws MlKitException {
        File modelDir = this.b.getModelDir(this.f11572a, ModelType.CUSTOM);
        return new File(new File(modelDir, String.valueOf(this.b.getLatestCachedModelVersion(modelDir) + 1)), Constants.MODEL_FILE_NAME);
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover
    @Nullable
    public final File moveAllFilesFromPrivateTempToPrivateDestination(File file) throws MlKitException {
        File file2;
        ModelFileHelper modelFileHelper = this.b;
        String str = this.f11572a;
        ModelType modelType = ModelType.CUSTOM;
        File modelDir = modelFileHelper.getModelDir(str, modelType);
        File file3 = new File(new File(modelDir, String.valueOf(this.b.getLatestCachedModelVersion(modelDir) + 1)), Constants.MODEL_FILE_NAME);
        File parentFile = file3.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            ((File) Preconditions.checkNotNull(parentFile)).mkdirs();
        }
        File file4 = null;
        if (a(file, file3)) {
            File tempFileInPrivateFolder = this.b.getTempFileInPrivateFolder(this.f11572a, modelType, Constants.AUTOML_IMAGE_LABELING_LABELS_FILE_NAME);
            if (tempFileInPrivateFolder.exists()) {
                file2 = new File(parentFile, Constants.AUTOML_IMAGE_LABELING_LABELS_FILE_NAME);
                if (!a(tempFileInPrivateFolder, file2)) {
                    return null;
                }
            } else {
                file2 = null;
            }
            File tempFileInPrivateFolder2 = this.b.getTempFileInPrivateFolder(this.f11572a, modelType, Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME);
            if (tempFileInPrivateFolder2.exists()) {
                File file5 = new File(parentFile, Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME);
                if (!a(tempFileInPrivateFolder2, file5)) {
                    return null;
                }
                file4 = file5;
            }
            return (file2 == null && file4 == null) ? file3 : parentFile;
        }
        return null;
    }
}
