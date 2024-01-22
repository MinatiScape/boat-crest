package com.google.firebase.ml.common.internal.modeldownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.firebase.ml.common.FirebaseMLException;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;
@WorkerThread
/* loaded from: classes10.dex */
public class zzj {
    public static final GmsLogger c = new GmsLogger("ModelLoader", "");
    @Nullable
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final zzag f11395a;
    @NonNull
    public final zzl b;
    @Nullable
    @VisibleForTesting
    public final zzf zzblb;
    public int zzbld = zzo.zzblk;

    public zzj(@Nullable zzag zzagVar, @Nullable zzf zzfVar, @NonNull zzl zzlVar) {
        Preconditions.checkArgument((zzagVar == null && zzfVar == null) ? false : true, "At least one of RemoteModelLoader or LocalModelLoader must be non-null.");
        Preconditions.checkNotNull(zzlVar);
        this.f11395a = zzagVar;
        this.zzblb = zzfVar;
        this.b = zzlVar;
    }

    public final synchronized boolean a(zzm zzmVar, List<zzoc> list) throws FirebaseMLException {
        zzag zzagVar = this.f11395a;
        if (zzagVar != null) {
            try {
                MappedByteBuffer load = zzagVar.load();
                if (load != null) {
                    try {
                        zzmVar.zza(load);
                        c.d("ModelLoader", "Remote model source is loaded successfully");
                        return true;
                    } catch (RuntimeException e) {
                        list.add(zzoc.REMOTE_MODEL_INVALID);
                        throw e;
                    }
                }
                c.d("ModelLoader", "Remote model source can NOT be loaded, try local model.");
                list.add(zzoc.REMOTE_MODEL_LOADER_LOADS_NO_MODEL);
            } catch (FirebaseMLException e2) {
                c.d("ModelLoader", "Remote model source can NOT be loaded, try local model.");
                list.add(zzoc.REMOTE_MODEL_LOADER_ERROR);
                throw e2;
            }
        }
        return false;
    }

    public final synchronized boolean b(zzm zzmVar, List<zzoc> list) throws FirebaseMLException {
        MappedByteBuffer load;
        zzf zzfVar = this.zzblb;
        if (zzfVar == null || (load = zzfVar.load()) == null) {
            return false;
        }
        try {
            zzmVar.zza(load);
            c.d("ModelLoader", "Local model source is loaded successfully");
            return true;
        } catch (RuntimeException e) {
            list.add(zzoc.LOCAL_MODEL_INVALID);
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String c() {
        /*
            r4 = this;
            com.google.firebase.ml.common.internal.modeldownload.zzf r0 = r4.zzblb
            if (r0 == 0) goto L30
            com.google.firebase.ml.common.modeldownload.FirebaseLocalModel r0 = r0.zzot()
            java.lang.String r0 = r0.getAssetFilePath()
            if (r0 == 0) goto L19
            com.google.firebase.ml.common.internal.modeldownload.zzf r0 = r4.zzblb
            com.google.firebase.ml.common.modeldownload.FirebaseLocalModel r0 = r0.zzot()
            java.lang.String r0 = r0.getAssetFilePath()
            goto L31
        L19:
            com.google.firebase.ml.common.internal.modeldownload.zzf r0 = r4.zzblb
            com.google.firebase.ml.common.modeldownload.FirebaseLocalModel r0 = r0.zzot()
            java.lang.String r0 = r0.getFilePath()
            if (r0 == 0) goto L30
            com.google.firebase.ml.common.internal.modeldownload.zzf r0 = r4.zzblb
            com.google.firebase.ml.common.modeldownload.FirebaseLocalModel r0 = r0.zzot()
            java.lang.String r0 = r0.getFilePath()
            goto L31
        L30:
            r0 = 0
        L31:
            com.google.firebase.ml.common.internal.modeldownload.zzag r1 = r4.f11395a
            if (r1 != 0) goto L38
            java.lang.String r1 = "unspecified"
            goto L40
        L38:
            com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel r1 = r1.zzpn()
            java.lang.String r1 = r1.getUniqueModelNameForPersist()
        L40:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r0
            r0 = 1
            r2[r0] = r1
            java.lang.String r0 = "Local model path: %s. Remote model name: %s. "
            java.lang.String r0 = java.lang.String.format(r0, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.common.internal.modeldownload.zzj.c():java.lang.String");
    }

    public final synchronized void zza(zzm zzmVar) throws FirebaseMLException {
        Exception exc;
        boolean z;
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        Exception e = null;
        try {
            z = a(zzmVar, arrayList);
            exc = null;
        } catch (Exception e2) {
            exc = e2;
            z = false;
        }
        if (z) {
            this.b.zze(arrayList);
            this.zzbld = zzo.zzbll;
            return;
        }
        try {
            z2 = b(zzmVar, arrayList);
        } catch (Exception e3) {
            e = e3;
        }
        if (z2) {
            this.b.zze(arrayList);
            this.zzbld = zzo.zzblm;
            return;
        }
        arrayList.add(zzoc.NO_VALID_MODEL);
        this.b.zze(arrayList);
        this.zzbld = zzo.zzblk;
        if (exc != null) {
            String valueOf = String.valueOf(c());
            throw new FirebaseMLException(valueOf.length() != 0 ? "Remote model load failed with the model options: ".concat(valueOf) : new String("Remote model load failed with the model options: "), 14, exc);
        } else if (e != null) {
            String valueOf2 = String.valueOf(c());
            throw new FirebaseMLException(valueOf2.length() != 0 ? "Local model load failed with the model options: ".concat(valueOf2) : new String("Local model load failed with the model options: "), 14, e);
        } else {
            String valueOf3 = String.valueOf(c());
            throw new FirebaseMLException(valueOf3.length() != 0 ? "Cannot load any model with the model options: ".concat(valueOf3) : new String("Cannot load any model with the model options: "), 14);
        }
    }

    public final synchronized boolean zzou() {
        return this.zzbld == zzo.zzbll;
    }
}
