package com.google.firebase.ml.common.internal.modeldownload;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.firebase.ml.common.FirebaseMLException;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes10.dex */
public final class zzi {
    public static final GmsLogger b = new GmsLogger("ModelFileHelper", "");
    @VisibleForTesting
    public static final String c = String.format("com.google.firebase.ml.%s.models", "custom");
    @VisibleForTesting
    public static final String d = String.format("com.google.firebase.ml.%s.models", "automl");
    @VisibleForTesting
    public static final String e = String.format("com.google.firebase.ml.%s.models", "base");
    @VisibleForTesting
    public static final String f = String.format("com.google.firebase.ml.%s.models", "translate");

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f11394a;

    public zzi(zzqf zzqfVar) {
        this.f11394a = zzqfVar;
    }

    @WorkerThread
    public static int b(@NonNull File file) {
        File[] listFiles = file.listFiles();
        int i = -1;
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                try {
                    i = Math.max(i, Integer.parseInt(file2.getName()));
                } catch (NumberFormatException unused) {
                    GmsLogger gmsLogger = b;
                    String valueOf = String.valueOf(file2.getName());
                    gmsLogger.d("ModelFileHelper", valueOf.length() != 0 ? "Contains non-integer file name ".concat(valueOf) : new String("Contains non-integer file name "));
                }
            }
        }
        return i;
    }

    @WorkerThread
    public final File a(@NonNull String str, @NonNull zzn zznVar, boolean z) throws FirebaseMLException {
        File zzb = zzb(str, zznVar, z);
        if (!zzb.exists()) {
            GmsLogger gmsLogger = b;
            String valueOf = String.valueOf(zzb.getAbsolutePath());
            gmsLogger.d("ModelFileHelper", valueOf.length() != 0 ? "model folder does not exist, creating one: ".concat(valueOf) : new String("model folder does not exist, creating one: "));
            if (!zzb.mkdirs()) {
                String valueOf2 = String.valueOf(zzb);
                StringBuilder sb = new StringBuilder(valueOf2.length() + 31);
                sb.append("Failed to create model folder: ");
                sb.append(valueOf2);
                throw new FirebaseMLException(sb.toString(), 13);
            }
        } else if (!zzb.isDirectory()) {
            String valueOf3 = String.valueOf(zzb);
            StringBuilder sb2 = new StringBuilder(valueOf3.length() + 71);
            sb2.append("Can not create model folder, since an existing file has the same name: ");
            sb2.append(valueOf3);
            throw new FirebaseMLException(sb2.toString(), 6);
        }
        return zzb;
    }

    @WorkerThread
    public final File c(@NonNull String str, zzn zznVar) throws FirebaseMLException {
        return a(str, zznVar, false);
    }

    public final boolean d(@Nullable File file) {
        boolean z;
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            z = true;
            for (File file2 : (File[]) Preconditions.checkNotNull(file.listFiles())) {
                z = z && d(file2);
            }
        } else {
            z = true;
        }
        return z && file.delete();
    }

    @WorkerThread
    public final File e(@NonNull String str, @NonNull zzn zznVar) throws FirebaseMLException {
        return a(str, zznVar, true);
    }

    public final void zza(String str, zzn zznVar) throws FirebaseMLException {
        if (zznVar != zzn.AUTOML) {
            return;
        }
        File parentFile = zza.zza(this.f11394a, str).getParentFile();
        if (d(parentFile)) {
            return;
        }
        GmsLogger gmsLogger = b;
        String valueOf = String.valueOf(parentFile != null ? parentFile.getAbsolutePath() : null);
        gmsLogger.e("ModelFileHelper", valueOf.length() != 0 ? "Failed to delete the temp labels file directory: ".concat(valueOf) : new String("Failed to delete the temp labels file directory: "));
    }

    public final boolean zzb(String str, zzn zznVar) throws FirebaseMLException {
        String sb;
        if (zznVar == zzn.UNKNOWN) {
            return false;
        }
        File a2 = a(str, zznVar, false);
        int b2 = b(a2);
        if (b2 == -1) {
            sb = null;
        } else {
            String absolutePath = a2.getAbsolutePath();
            StringBuilder sb2 = new StringBuilder(String.valueOf(absolutePath).length() + 12);
            sb2.append(absolutePath);
            sb2.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb2.append(b2);
            sb = sb2.toString();
        }
        return sb != null;
    }

    public final File zzb(@NonNull String str, @NonNull zzn zznVar, boolean z) {
        String str2;
        File dir;
        int i = h.f11382a[zznVar.ordinal()];
        if (i == 1) {
            str2 = c;
        } else if (i == 2) {
            str2 = e;
        } else if (i == 3) {
            str2 = d;
        } else if (i == 4) {
            str2 = f;
        } else {
            String name = zznVar.name();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 69);
            sb.append("Unknown model type ");
            sb.append(name);
            sb.append(". Cannot find a dir to store the downloaded model.");
            throw new IllegalArgumentException(sb.toString());
        }
        if (Build.VERSION.SDK_INT >= 21) {
            dir = new File(this.f11394a.getApplicationContext().getNoBackupFilesDir(), str2);
        } else {
            dir = this.f11394a.getApplicationContext().getDir(str2, 0);
        }
        if (z) {
            dir = new File(dir, "temp");
        }
        return new File(new File(dir, this.f11394a.getPersistenceKey()), str);
    }

    @WorkerThread
    public final synchronized void zza(zzn zznVar, String str) {
        d(zzb(str, zznVar, false));
        d(zzb(str, zznVar, true));
    }
}
