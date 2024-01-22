package com.google.firebase.ml.common.internal.modeldownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzne;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.mlkit.common.sdkinternal.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
public final class zza implements zzk {
    public static final GmsLogger d = new GmsLogger("AutoMLModelFileManager", "");

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f11386a;
    public final String b;
    public final zzi c;

    public zza(@NonNull zzqf zzqfVar, @NonNull String str) {
        this.f11386a = zzqfVar;
        this.b = str;
        this.c = new zzi(zzqfVar);
    }

    public static void a(File file, g gVar) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
        try {
            gVar.a(bufferedWriter);
            bufferedWriter.close();
        } catch (Throwable th) {
            try {
                bufferedWriter.close();
            } catch (Throwable th2) {
                zzne.zza(th, th2);
            }
            throw th;
        }
    }

    public static final /* synthetic */ void c(List list, BufferedWriter bufferedWriter) throws IOException {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bufferedWriter.write((String) it.next());
            bufferedWriter.newLine();
        }
    }

    @Override // com.google.firebase.ml.common.internal.modeldownload.zzk
    @Nullable
    public final File zza(File file) throws FirebaseMLException {
        File c = this.c.c(this.b, zzn.AUTOML);
        File file2 = new File(new File(c, String.valueOf(zzi.b(c) + 1)), Constants.MODEL_FILE_NAME);
        File parentFile = file2.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        File zza = zza(this.f11386a, this.b);
        File file3 = new File(parentFile, Constants.AUTOML_IMAGE_LABELING_LABELS_FILE_NAME);
        if (file.renameTo(file2) && zza.renameTo(file3)) {
            d.d("AutoMLModelFileManager", "Rename to serving model successfully");
            file2.setExecutable(false);
            file2.setWritable(false);
            file3.setExecutable(false);
            file3.setWritable(false);
            File file4 = new File(parentFile, Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME);
            final String format = String.format("{\n\t\"modelType\": \"%s\",\n\t\"modelFile\": \"%s\",\n\t\"labelsFile\": \"%s\"\n}", Constants.AUTOML_IMAGE_LABELING_MODEL_TYPE, Constants.MODEL_FILE_NAME, Constants.AUTOML_IMAGE_LABELING_LABELS_FILE_NAME);
            try {
                a(file4, new g(format) { // from class: com.google.firebase.ml.common.internal.modeldownload.e

                    /* renamed from: a  reason: collision with root package name */
                    public final String f11380a;

                    {
                        this.f11380a = format;
                    }

                    @Override // com.google.firebase.ml.common.internal.modeldownload.g
                    public final void a(BufferedWriter bufferedWriter) {
                        bufferedWriter.write(this.f11380a);
                    }
                });
                return file2.getParentFile();
            } catch (IOException e) {
                String valueOf = String.valueOf(this.b);
                throw new FirebaseMLException(valueOf.length() != 0 ? "Failed to write manifest json for the AutoML model: ".concat(valueOf) : new String("Failed to write manifest json for the AutoML model: "), 13, e);
            }
        }
        GmsLogger gmsLogger = d;
        gmsLogger.d("AutoMLModelFileManager", "Rename to serving model failed, remove the temp file.");
        if (!file.delete()) {
            String valueOf2 = String.valueOf(file.getAbsolutePath());
            gmsLogger.d("AutoMLModelFileManager", valueOf2.length() != 0 ? "Failed to delete the temp model file: ".concat(valueOf2) : new String("Failed to delete the temp model file: "));
        }
        if (zza.delete()) {
            return null;
        }
        String valueOf3 = String.valueOf(zza.getAbsolutePath());
        gmsLogger.d("AutoMLModelFileManager", valueOf3.length() != 0 ? "Failed to delete the temp labels file: ".concat(valueOf3) : new String("Failed to delete the temp labels file: "));
        return null;
    }

    public static void zza(@NonNull zzqf zzqfVar, @NonNull String str, @NonNull final List<String> list) throws FirebaseMLException {
        try {
            a(zza(zzqfVar, str), new g(list) { // from class: com.google.firebase.ml.common.internal.modeldownload.d

                /* renamed from: a  reason: collision with root package name */
                public final List f11379a;

                {
                    this.f11379a = list;
                }

                @Override // com.google.firebase.ml.common.internal.modeldownload.g
                public final void a(BufferedWriter bufferedWriter) {
                    zza.c(this.f11379a, bufferedWriter);
                }
            });
        } catch (IOException e) {
            String valueOf = String.valueOf(str);
            throw new FirebaseMLException(valueOf.length() != 0 ? "Failed to write labels file for the AutoML model: ".concat(valueOf) : new String("Failed to write labels file for the AutoML model: "), 13, e);
        }
    }

    public static File zza(@NonNull zzqf zzqfVar, @NonNull String str) throws FirebaseMLException {
        File e = new zzi(zzqfVar).e(str, zzn.AUTOML);
        if (e.exists() && e.isFile() && !e.delete()) {
            String valueOf = String.valueOf(e.getAbsolutePath());
            throw new FirebaseMLException(valueOf.length() != 0 ? "Failed to delete the temp labels file: ".concat(valueOf) : new String("Failed to delete the temp labels file: "), 13);
        }
        if (!e.exists()) {
            GmsLogger gmsLogger = d;
            String valueOf2 = String.valueOf(e.getAbsolutePath());
            gmsLogger.d("AutoMLModelFileManager", valueOf2.length() != 0 ? "Temp labels folder does not exist, creating one: ".concat(valueOf2) : new String("Temp labels folder does not exist, creating one: "));
            if (!e.mkdirs()) {
                throw new FirebaseMLException("Failed to create a directory to hold the AutoML model's labels file.", 13);
            }
        }
        return new File(e, Constants.AUTOML_IMAGE_LABELING_LABELS_FILE_NAME);
    }
}
