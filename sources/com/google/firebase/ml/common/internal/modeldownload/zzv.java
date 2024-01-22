package com.google.firebase.ml.common.internal.modeldownload;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzpt;
import com.google.android.gms.internal.firebase_ml.zzpx;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqu;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzv {
    public static final GmsLogger j = new GmsLogger("ModelDownloadManager", "");
    @GuardedBy("RemoteModelDownloadManager.class")
    public static final Map<String, zzv> k = new HashMap();
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final LongSparseArray<k> f11398a = new LongSparseArray<>();
    @GuardedBy("this")
    public final LongSparseArray<TaskCompletionSource<Void>> b = new LongSparseArray<>();
    public final zzqf c;
    public final DownloadManager d;
    public final FirebaseRemoteModel e;
    public final zzw f;
    public final zzqu g;
    public final zzg h;
    public FirebaseModelDownloadConditions i;

    public zzv(@NonNull zzqf zzqfVar, @NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzg zzgVar, @NonNull zzw zzwVar) {
        this.c = zzqfVar;
        this.e = firebaseRemoteModel;
        DownloadManager downloadManager = (DownloadManager) zzqfVar.getApplicationContext().getSystemService("download");
        this.d = downloadManager;
        if (downloadManager == null) {
            j.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.h = zzgVar;
        this.f = zzwVar;
        this.g = zzqu.zzb(zzqfVar);
    }

    public static boolean h(Integer num) {
        if (num != null) {
            return num.intValue() == 8 || num.intValue() == 16;
        }
        return false;
    }

    public static synchronized zzv zza(@NonNull zzqf zzqfVar, @NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzg zzgVar, @NonNull zzw zzwVar) {
        zzv zzvVar;
        synchronized (zzv.class) {
            String uniqueModelNameForPersist = firebaseRemoteModel.getUniqueModelNameForPersist();
            Map<String, zzv> map = k;
            if (!map.containsKey(uniqueModelNameForPersist)) {
                map.put(uniqueModelNameForPersist, new zzv(zzqfVar, firebaseRemoteModel, zzgVar, zzwVar));
            }
            zzvVar = map.get(uniqueModelNameForPersist);
        }
        return zzvVar;
    }

    public final int a(Long l) {
        int columnIndex;
        DownloadManager downloadManager = this.d;
        Cursor query = (downloadManager == null || l == null) ? null : downloadManager.query(new DownloadManager.Query().setFilterById(l.longValue()));
        if (query == null || !query.moveToFirst() || (columnIndex = query.getColumnIndex("reason")) == -1) {
            return 0;
        }
        return query.getInt(columnIndex);
    }

    public final synchronized Long d(@NonNull DownloadManager.Request request, @NonNull zzaa zzaaVar) {
        DownloadManager downloadManager = this.d;
        if (downloadManager == null) {
            return null;
        }
        long enqueue = downloadManager.enqueue(request);
        GmsLogger gmsLogger = j;
        StringBuilder sb = new StringBuilder(53);
        sb.append("Schedule a new downloading task: ");
        sb.append(enqueue);
        gmsLogger.d("ModelDownloadManager", sb.toString());
        this.g.zza(enqueue, zzaaVar);
        this.f.zza(zzoc.NO_ERROR, false, zzaaVar.zzpm(), zzns.zzai.zza.SCHEDULED);
        return Long.valueOf(enqueue);
    }

    @Nullable
    @WorkerThread
    public final synchronized Long e(@NonNull zzaa zzaaVar, @NonNull FirebaseModelDownloadConditions firebaseModelDownloadConditions) throws FirebaseMLException {
        Uri uri;
        String str;
        Preconditions.checkNotNull(firebaseModelDownloadConditions, "DownloadConditions can not be null");
        String zzb = this.g.zzb(this.e);
        Integer p = p();
        if (zzb != null) {
            str = zzaaVar.c;
            if (zzb.equals(str) && p != null) {
                if (!h(p())) {
                    this.f.zza(zzoc.NO_ERROR, false, t(), zzns.zzai.zza.DOWNLOADING);
                }
                j.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
                return null;
            }
        }
        GmsLogger gmsLogger = j;
        gmsLogger.d("ModelDownloadManager", "Need to download a new model.");
        o();
        uri = zzaaVar.b;
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationUri(null);
        if (this.h.zza(zzaaVar)) {
            gmsLogger.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
            this.f.zza(zzoc.NO_ERROR, false, zzaaVar.zzpm(), zzns.zzai.zza.UPDATE_AVAILABLE);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            request.setRequiresCharging(firebaseModelDownloadConditions.isChargingRequired());
            request.setRequiresDeviceIdle(firebaseModelDownloadConditions.isDeviceIdleRequired());
        }
        if (firebaseModelDownloadConditions.isWifiRequired()) {
            request.setAllowedNetworkTypes(2);
        }
        return d(request, zzaaVar);
    }

    public final FirebaseMLException g(Long l) {
        String str;
        DownloadManager downloadManager = this.d;
        Cursor query = (downloadManager == null || l == null) ? null : downloadManager.query(new DownloadManager.Query().setFilterById(l.longValue()));
        int i = 13;
        if (query == null || !query.moveToFirst()) {
            str = "Model downloading failed";
        } else {
            int i2 = query.getInt(query.getColumnIndex("reason"));
            if (i2 == 1006) {
                i = 101;
                str = "Model downloading failed due to insufficient space on the device.";
            } else {
                StringBuilder sb = new StringBuilder(84);
                sb.append("Model downloading failed due to error code: ");
                sb.append(i2);
                sb.append(" from Android DownloadManager");
                str = sb.toString();
            }
        }
        return new FirebaseMLException(str, i);
    }

    public final synchronized void l(@NonNull String str, @NonNull zzn zznVar) throws FirebaseMLException {
        this.g.zza(this.e, str, zznVar);
        o();
    }

    @Nullable
    public final synchronized Long m() {
        return this.g.zza(this.e);
    }

    @Nullable
    public final synchronized String n() {
        return this.g.zzb(this.e);
    }

    public final synchronized void o() throws FirebaseMLException {
        Long m = m();
        if (this.d != null && m != null) {
            GmsLogger gmsLogger = j;
            String valueOf = String.valueOf(m);
            StringBuilder sb = new StringBuilder(valueOf.length() + 44);
            sb.append("Cancel or remove existing downloading task: ");
            sb.append(valueOf);
            gmsLogger.d("ModelDownloadManager", sb.toString());
            if (this.d.remove(m.longValue()) > 0 || p() == null) {
                this.h.zza(this.e.getUniqueModelNameForPersist(), t());
                this.g.zzh(this.e);
            }
        }
    }

    @Nullable
    public final synchronized Integer p() {
        Long m = m();
        DownloadManager downloadManager = this.d;
        Integer num = null;
        if (downloadManager != null && m != null) {
            Cursor query = downloadManager.query(new DownloadManager.Query().setFilterById(m.longValue()));
            Integer valueOf = (query == null || !query.moveToFirst()) ? null : Integer.valueOf(query.getInt(query.getColumnIndex(NotificationCompat.CATEGORY_STATUS)));
            if (valueOf == null) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            if (valueOf.intValue() == 2 || valueOf.intValue() == 4 || valueOf.intValue() == 1 || valueOf.intValue() == 8 || valueOf.intValue() == 16) {
                num = valueOf;
            }
            if (query != null) {
                query.close();
            }
            return num;
        }
        return null;
    }

    @Nullable
    public final synchronized ParcelFileDescriptor q() {
        Long m = m();
        DownloadManager downloadManager = this.d;
        ParcelFileDescriptor parcelFileDescriptor = null;
        if (downloadManager == null || m == null) {
            return null;
        }
        try {
            parcelFileDescriptor = downloadManager.openDownloadedFile(m.longValue());
        } catch (FileNotFoundException unused) {
            j.e("ModelDownloadManager", "Downloaded file is not found");
        }
        return parcelFileDescriptor;
    }

    @Nullable
    @WorkerThread
    public final synchronized zzaa r() throws FirebaseMLException {
        String str;
        String str2;
        boolean s = s();
        boolean z = false;
        if (s) {
            this.f.zza(zzoc.NO_ERROR, false, this.g.zzd(this.e), zzns.zzai.zza.LIVE);
        }
        zzaa a2 = zzad.a(this.c, this.e, this.f);
        if (a2 == null) {
            return null;
        }
        zzqf zzqfVar = this.c;
        FirebaseRemoteModel firebaseRemoteModel = this.e;
        str = a2.c;
        zzqu zzb = zzqu.zzb(zzqfVar);
        if (str.equals(zzb.zze(firebaseRemoteModel)) && zzpt.zzb(zzqfVar.getApplicationContext()).equals(zzb.zzor())) {
            j.e("ModelDownloadManager", "The model is incompatible with TFLite and the app is not upgraded, do not download");
        } else {
            z = true;
        }
        if (!s) {
            this.g.zzi(this.e);
        }
        zzqf zzqfVar2 = this.c;
        FirebaseRemoteModel firebaseRemoteModel2 = this.e;
        str2 = a2.c;
        boolean z2 = !str2.equals(zzqu.zzb(zzqfVar2).zzc(firebaseRemoteModel2));
        if (!z || (s && !z2)) {
            if (s && (z2 ^ z)) {
                return null;
            }
            String modelName = this.e.getModelName();
            StringBuilder sb = new StringBuilder(String.valueOf(modelName).length() + 46);
            sb.append("The model ");
            sb.append(modelName);
            sb.append(" is incompatible with TFLite runtime");
            throw new FirebaseMLException(sb.toString(), 100);
        }
        return a2;
    }

    public final boolean s() throws FirebaseMLException {
        return this.h.zzb(this.e.getUniqueModelNameForPersist(), this.g.zzd(this.e));
    }

    public final zzn t() {
        String zzb = this.g.zzb(this.e);
        if (zzb == null) {
            return zzn.UNKNOWN;
        }
        return this.g.zzbw(zzb);
    }

    public final synchronized k v(long j2) {
        k kVar;
        kVar = this.f11398a.get(j2);
        if (kVar == null) {
            kVar = new k(this, j2, w(j2));
            this.f11398a.put(j2, kVar);
        }
        return kVar;
    }

    public final synchronized TaskCompletionSource<Void> w(long j2) {
        TaskCompletionSource<Void> taskCompletionSource;
        taskCompletionSource = this.b.get(j2);
        if (taskCompletionSource == null) {
            taskCompletionSource = new TaskCompletionSource<>();
            this.b.put(j2, taskCompletionSource);
        }
        return taskCompletionSource;
    }

    public final Task<Void> x(long j2) {
        this.c.getApplicationContext().registerReceiver(v(j2), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), null, zzpx.zzof().getHandler());
        return w(j2).getTask();
    }

    @WorkerThread
    public final boolean zzpf() throws FirebaseMLException {
        try {
            if (s()) {
                return true;
            }
            return Objects.equal(p(), 8);
        } catch (FirebaseMLException e) {
            throw new FirebaseMLException("Failed to check if the model is downloaded.", 13, e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
        if (r6 == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
        if (n() == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0050, code lost:
        r10.f.zza(com.google.android.gms.internal.firebase_ml.zzoc.NO_ERROR, false, t(), com.google.android.gms.internal.firebase_ml.zzns.zzai.zza.DOWNLOADING);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0065, code lost:
        return x(r6.longValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0088, code lost:
        r1 = e(r1, r10.i);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008e, code lost:
        if (r1 == null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0098, code lost:
        return x(r1.longValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0099, code lost:
        com.google.firebase.ml.common.internal.modeldownload.zzv.j.i("ModelDownloadManager", "Didn't schedule download for the updated model");
     */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.tasks.Task<java.lang.Void> zzpg() {
        /*
            r10 = this;
            com.google.firebase.ml.common.internal.modeldownload.zzw r0 = r10.f
            com.google.android.gms.internal.firebase_ml.zzoc r1 = com.google.android.gms.internal.firebase_ml.zzoc.NO_ERROR
            com.google.firebase.ml.common.internal.modeldownload.zzn r2 = com.google.firebase.ml.common.internal.modeldownload.zzn.UNKNOWN
            com.google.android.gms.internal.firebase_ml.zzns$zzai$zza r3 = com.google.android.gms.internal.firebase_ml.zzns.zzai.zza.EXPLICITLY_REQUESTED
            r4 = 0
            r0.zza(r1, r4, r2, r3)
            r0 = 0
            com.google.firebase.ml.common.internal.modeldownload.zzaa r1 = r10.r()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> L13
            r2 = r0
            goto L16
        L13:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L16:
            r3 = 13
            java.lang.Integer r5 = r10.p()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            java.lang.Long r6 = r10.m()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            boolean r7 = r10.s()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            if (r7 != 0) goto L86
            boolean r7 = h(r5)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            if (r7 == 0) goto L2d
            goto L86
        L2d:
            r7 = 1
            if (r5 == 0) goto L45
            int r8 = r5.intValue()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            r9 = 4
            if (r8 == r9) goto L46
            int r8 = r5.intValue()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            r9 = 2
            if (r8 == r9) goto L46
            int r5 = r5.intValue()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            if (r5 != r7) goto L45
            goto L46
        L45:
            r7 = r4
        L46:
            if (r7 == 0) goto L66
            if (r6 == 0) goto L66
            java.lang.String r5 = r10.n()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            if (r5 == 0) goto L66
            com.google.firebase.ml.common.internal.modeldownload.zzw r0 = r10.f     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.android.gms.internal.firebase_ml.zzoc r1 = com.google.android.gms.internal.firebase_ml.zzoc.NO_ERROR     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.firebase.ml.common.internal.modeldownload.zzn r2 = r10.t()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.android.gms.internal.firebase_ml.zzns$zzai$zza r5 = com.google.android.gms.internal.firebase_ml.zzns.zzai.zza.DOWNLOADING     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            r0.zza(r1, r4, r2, r5)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            long r0 = r6.longValue()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.android.gms.tasks.Task r0 = r10.x(r0)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            return r0
        L66:
            if (r1 != 0) goto L69
            goto L6f
        L69:
            com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions r0 = r10.i     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            java.lang.Long r0 = r10.e(r1, r0)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
        L6f:
            if (r0 != 0) goto L7d
            com.google.firebase.ml.common.FirebaseMLException r0 = new com.google.firebase.ml.common.FirebaseMLException     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            java.lang.String r1 = "Failed to schedule the download task"
            r0.<init>(r1, r3, r2)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r0)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            return r0
        L7d:
            long r0 = r0.longValue()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.android.gms.tasks.Task r0 = r10.x(r0)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            return r0
        L86:
            if (r1 == 0) goto La2
            com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions r2 = r10.i     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            java.lang.Long r1 = r10.e(r1, r2)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            if (r1 == 0) goto L99
            long r0 = r1.longValue()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.android.gms.tasks.Task r0 = r10.x(r0)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            return r0
        L99:
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.firebase.ml.common.internal.modeldownload.zzv.j     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            java.lang.String r2 = "ModelDownloadManager"
            java.lang.String r4 = "Didn't schedule download for the updated model"
            r1.i(r2, r4)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
        La2:
            if (r5 == 0) goto Lb8
            int r1 = r5.intValue()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            r2 = 16
            if (r1 != r2) goto Lb8
            com.google.firebase.ml.common.FirebaseMLException r0 = r10.g(r6)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            r10.o()     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r0)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            return r0
        Lb8:
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forResult(r0)     // Catch: com.google.firebase.ml.common.FirebaseMLException -> Lbd
            return r0
        Lbd:
            r0 = move-exception
            com.google.firebase.ml.common.FirebaseMLException r1 = new com.google.firebase.ml.common.FirebaseMLException
            java.lang.String r2 = "Failed to ensure the model is downloaded."
            r1.<init>(r2, r3, r0)
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.common.internal.modeldownload.zzv.zzpg():com.google.android.gms.tasks.Task");
    }

    public final void zza(@NonNull FirebaseModelDownloadConditions firebaseModelDownloadConditions) {
        Preconditions.checkNotNull(firebaseModelDownloadConditions, "DownloadConditions can not be null");
        this.i = firebaseModelDownloadConditions;
    }
}
