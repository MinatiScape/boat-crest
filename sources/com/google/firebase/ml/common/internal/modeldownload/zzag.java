package com.google.firebase.ml.common.internal.modeldownload;

import android.os.ParcelFileDescriptor;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqu;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzag {
    public static final GmsLogger i = new GmsLogger("RemoteModelLoader", "");
    @GuardedBy("RemoteModelLoader.class")
    public static final Map<String, zzag> j = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f11389a;
    public final FirebaseRemoteModel b;
    public final zzv c;
    public final zzz d;
    public final zzw e;
    public final zzaf f;
    public final zzn g;
    public boolean h = true;

    public zzag(@NonNull zzqf zzqfVar, @NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzp zzpVar, @NonNull zzaf zzafVar, @NonNull zzn zznVar) {
        this.d = new zzz(zzqfVar, firebaseRemoteModel, zzpVar, zznVar, new zzi(zzqfVar));
        zzw zzwVar = new zzw(zzqfVar, firebaseRemoteModel);
        this.e = zzwVar;
        this.c = zzv.zza(zzqfVar, firebaseRemoteModel, new zzg(zzqfVar), zzwVar);
        this.f = zzafVar;
        this.f11389a = zzqfVar;
        this.b = firebaseRemoteModel;
        this.g = zznVar;
    }

    public static synchronized zzag zza(@NonNull zzqf zzqfVar, @NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzp zzpVar, zzaf zzafVar, zzn zznVar) {
        zzag zzagVar;
        synchronized (zzag.class) {
            String uniqueModelNameForPersist = firebaseRemoteModel.getUniqueModelNameForPersist();
            Map<String, zzag> map = j;
            if (!map.containsKey(uniqueModelNameForPersist)) {
                map.put(uniqueModelNameForPersist, new zzag(zzqfVar, firebaseRemoteModel, zzpVar, zzafVar, zznVar));
            }
            zzagVar = map.get(uniqueModelNameForPersist);
        }
        return zzagVar;
    }

    @Nullable
    @WorkerThread
    public final MappedByteBuffer a(boolean z) throws FirebaseMLException {
        zzv zzvVar;
        Long m = this.c.m();
        String n = this.c.n();
        if (m != null && n != null) {
            Integer p = this.c.p();
            if (p == null) {
                return null;
            }
            GmsLogger gmsLogger = i;
            String valueOf = String.valueOf(p);
            StringBuilder sb = new StringBuilder(valueOf.length() + 22);
            sb.append("Download Status code: ");
            sb.append(valueOf);
            gmsLogger.d("RemoteModelLoader", sb.toString());
            if (p.intValue() == 8) {
                gmsLogger.d("RemoteModelLoader", "Model downloaded successfully");
                this.e.zza(zzoc.NO_ERROR, true, this.g, zzns.zzai.zza.SUCCEEDED);
                ParcelFileDescriptor q = this.c.q();
                if (q == null) {
                    return null;
                }
                gmsLogger.d("RemoteModelLoader", "moving downloaded model from external storage to private folder.");
                try {
                    File zza = this.d.zza(q, n, this.e);
                    if (zza == null) {
                        return null;
                    }
                    MappedByteBuffer c = c(zza);
                    String valueOf2 = String.valueOf(zza.getParent());
                    gmsLogger.d("RemoteModelLoader", valueOf2.length() != 0 ? "Moved the downloaded model to private folder successfully: ".concat(valueOf2) : new String("Moved the downloaded model to private folder successfully: "));
                    this.c.l(n, this.g);
                    if (z && this.d.zzd(zza)) {
                        gmsLogger.d("RemoteModelLoader", "All old models are deleted.");
                        return c(this.d.zzf(zza));
                    }
                    return c;
                } finally {
                    this.c.o();
                }
            }
            if (p.intValue() == 16) {
                this.e.zza(false, this.g, this.c.a(m));
            }
            return null;
        }
        i.d("RemoteModelLoader", "No new model is downloading.");
        return null;
    }

    @NonNull
    @WorkerThread
    public final MappedByteBuffer b(@NonNull String str) throws FirebaseMLException {
        return this.f.zzbz(str);
    }

    public final MappedByteBuffer c(File file) throws FirebaseMLException {
        try {
            return b(file.getAbsolutePath());
        } catch (Exception e) {
            this.d.zze(file);
            throw new FirebaseMLException("Failed to load newly downloaded model.", 14, e);
        }
    }

    @Nullable
    @WorkerThread
    public final MappedByteBuffer d() throws FirebaseMLException {
        String zzpk = this.d.zzpk();
        if (zzpk == null) {
            i.d("RemoteModelLoader", "No existing model file");
            return null;
        }
        try {
            return b(zzpk);
        } catch (Exception e) {
            this.d.zze(new File(zzpk));
            zzqu.zzb(this.f11389a).zzi(this.b);
            throw new FirebaseMLException("Failed to load an already downloaded model.", 14, e);
        }
    }

    @Nullable
    @WorkerThread
    public final synchronized MappedByteBuffer load() throws FirebaseMLException {
        MappedByteBuffer a2;
        GmsLogger gmsLogger = i;
        gmsLogger.d("RemoteModelLoader", "Try to load newly downloaded model file.");
        a2 = a(this.h);
        if (a2 == null) {
            gmsLogger.d("RemoteModelLoader", "Loading existing model file.");
            a2 = d();
        }
        return a2;
    }

    public final FirebaseRemoteModel zzpn() {
        return this.b;
    }
}
