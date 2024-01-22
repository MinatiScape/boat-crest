package com.google.firebase.ml.common.internal.modeldownload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.LongSparseArray;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.ml.common.FirebaseMLException;
@WorkerThread
/* loaded from: classes10.dex */
public final class k extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final long f11385a;
    public final TaskCompletionSource<Void> b;
    public final /* synthetic */ zzv c;

    public k(zzv zzvVar, long j, TaskCompletionSource<Void> taskCompletionSource) {
        this.c = zzvVar;
        this.f11385a = j;
        this.b = taskCompletionSource;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        GmsLogger gmsLogger;
        LongSparseArray longSparseArray;
        LongSparseArray longSparseArray2;
        zzw zzwVar;
        zzn t;
        zzw zzwVar2;
        zzn t2;
        zzw zzwVar3;
        zzn t3;
        FirebaseMLException g;
        zzqf zzqfVar;
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        if (longExtra != this.f11385a) {
            return;
        }
        Integer p = this.c.p();
        synchronized (this.c) {
            try {
                zzqfVar = this.c.c;
                zzqfVar.getApplicationContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                gmsLogger = zzv.j;
                gmsLogger.w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e);
            }
            longSparseArray = this.c.f11398a;
            longSparseArray.remove(this.f11385a);
            longSparseArray2 = this.c.b;
            longSparseArray2.remove(this.f11385a);
        }
        if (p != null) {
            if (p.intValue() == 16) {
                zzwVar3 = this.c.f;
                t3 = this.c.t();
                zzwVar3.zza(false, t3, this.c.a(Long.valueOf(longExtra)));
                TaskCompletionSource<Void> taskCompletionSource = this.b;
                g = this.c.g(Long.valueOf(longExtra));
                taskCompletionSource.setException(g);
                return;
            } else if (p.intValue() == 8) {
                zzwVar2 = this.c.f;
                zzoc zzocVar = zzoc.NO_ERROR;
                t2 = this.c.t();
                zzwVar2.zza(zzocVar, t2, zzns.zzai.zza.SUCCEEDED);
                this.b.setResult(null);
                return;
            }
        }
        zzwVar = this.c.f;
        t = this.c.t();
        zzwVar.zza(false, t, 0);
        this.b.setException(new FirebaseMLException("Model downloading failed", 13));
    }
}
