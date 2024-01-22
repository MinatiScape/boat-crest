package com.google.firebase.ml.common.internal.modeldownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.firebase.ml.common.FirebaseMLException;
import java.io.File;
/* loaded from: classes10.dex */
public final class a implements zzk {

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f11376a;
    public final String b;

    public a(@NonNull zzqf zzqfVar, @NonNull String str) {
        this.f11376a = zzqfVar;
        this.b = str;
    }

    @Override // com.google.firebase.ml.common.internal.modeldownload.zzk
    @Nullable
    public final File zza(File file) throws FirebaseMLException {
        GmsLogger gmsLogger;
        GmsLogger gmsLogger2;
        GmsLogger gmsLogger3;
        File c = new zzi(this.f11376a).c(this.b, zzn.CUSTOM);
        File file2 = new File(c, String.valueOf(zzi.b(c) + 1));
        if (file.renameTo(file2)) {
            gmsLogger3 = zzz.i;
            gmsLogger3.d("RemoteModelFileManager", "Rename to serving model successfully");
            file2.setExecutable(false);
            file2.setWritable(false);
            return file2;
        }
        gmsLogger = zzz.i;
        gmsLogger.d("RemoteModelFileManager", "Rename to serving model failed, remove the temp file.");
        if (file.delete()) {
            return null;
        }
        gmsLogger2 = zzz.i;
        String valueOf = String.valueOf(file.getAbsolutePath());
        gmsLogger2.d("RemoteModelFileManager", valueOf.length() != 0 ? "Failed to delete the temp file: ".concat(valueOf) : new String("Failed to delete the temp file: "));
        return null;
    }
}
