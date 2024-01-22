package com.google.mlkit.vision.common.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.internal.model.ModelUtils;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.sdkinternal.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@KeepForSdk
@WorkerThread
/* loaded from: classes10.dex */
public class AutoMLModelUtils {
    @NonNull
    @KeepForSdk
    public static String[] getModelAndLabelFilePaths(@NonNull Context context, @NonNull LocalModel localModel, boolean z) throws IOException {
        String str;
        String str2;
        if (z) {
            str = (String) Preconditions.checkNotNull(localModel.getAssetFilePath());
        } else {
            str = (String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath());
        }
        if (localModel.isManifestFile()) {
            ModelUtils.AutoMLManifest parseManifestFile = ModelUtils.parseManifestFile(str, z, context);
            if (parseManifestFile != null) {
                Preconditions.checkState(Constants.AUTOML_IMAGE_LABELING_MODEL_TYPE.equals(parseManifestFile.getModelType()), "Model type should be: %s.", Constants.AUTOML_IMAGE_LABELING_MODEL_TYPE);
                str = new File(new File(str).getParent(), parseManifestFile.getModelFile()).toString();
                str2 = new File(new File(str).getParent(), parseManifestFile.getLabelsFile()).toString();
            } else {
                throw new IOException("Failed to parse manifest file.");
            }
        } else {
            str2 = "";
        }
        return new String[]{str, str2};
    }

    @NonNull
    @KeepForSdk
    public static List<String> readLabelsFile(@NonNull Context context, @NonNull String str, boolean z) throws IOException {
        InputStream fileInputStream;
        ArrayList arrayList = new ArrayList();
        if (z) {
            fileInputStream = context.getAssets().open(str);
        } else {
            fileInputStream = new FileInputStream(new File(str));
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                arrayList.add(readLine);
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    try {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    } catch (Exception unused) {
                    }
                }
            }
            throw th;
        }
    }
}
