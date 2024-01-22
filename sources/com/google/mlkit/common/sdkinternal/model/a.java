package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.LongSparseArray;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzlm;
import com.google.android.gms.internal.mlkit_common.zzls;
import com.google.android.gms.internal.mlkit_common.zzpq;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqa;
import com.google.android.gms.internal.mlkit_common.zzqb;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
@WorkerThread
/* loaded from: classes10.dex */
public final class a extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final long f11605a;
    public final TaskCompletionSource b;
    public final /* synthetic */ RemoteModelDownloadManager c;

    public /* synthetic */ a(RemoteModelDownloadManager remoteModelDownloadManager, long j, TaskCompletionSource taskCompletionSource, zzc zzcVar) {
        this.c = remoteModelDownloadManager;
        this.f11605a = j;
        this.b = taskCompletionSource;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        GmsLogger gmsLogger;
        LongSparseArray longSparseArray;
        LongSparseArray longSparseArray2;
        zzpz zzpzVar;
        RemoteModel remoteModel;
        zzpz zzpzVar2;
        RemoteModel remoteModel2;
        RemoteModel remoteModel3;
        zzpz zzpzVar3;
        RemoteModel remoteModel4;
        MlKitException k;
        MlKitContext mlKitContext;
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        if (longExtra != this.f11605a) {
            return;
        }
        Integer downloadingModelStatusCode = this.c.getDownloadingModelStatusCode();
        synchronized (this.c) {
            try {
                mlKitContext = this.c.c;
                mlKitContext.getApplicationContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                gmsLogger = RemoteModelDownloadManager.m;
                gmsLogger.w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e);
            }
            longSparseArray = this.c.f11601a;
            longSparseArray.remove(this.f11605a);
            longSparseArray2 = this.c.b;
            longSparseArray2.remove(this.f11605a);
        }
        if (downloadingModelStatusCode != null) {
            if (downloadingModelStatusCode.intValue() == 16) {
                zzpzVar3 = this.c.g;
                zzpq zzg = zzqc.zzg();
                RemoteModelDownloadManager remoteModelDownloadManager = this.c;
                remoteModel4 = remoteModelDownloadManager.e;
                Long valueOf = Long.valueOf(longExtra);
                zzpzVar3.zze(zzg, remoteModel4, false, remoteModelDownloadManager.getFailureReason(valueOf));
                TaskCompletionSource taskCompletionSource = this.b;
                k = this.c.k(valueOf);
                taskCompletionSource.setException(k);
                return;
            } else if (downloadingModelStatusCode.intValue() == 8) {
                zzpzVar2 = this.c.g;
                zzpq zzg2 = zzqc.zzg();
                remoteModel2 = this.c.e;
                zzqa zzh = zzqb.zzh();
                zzh.zzb(zzlm.NO_ERROR);
                zzh.zze(true);
                remoteModel3 = this.c.e;
                zzh.zzd(remoteModel3.getModelType());
                zzh.zza(zzls.SUCCEEDED);
                zzpzVar2.zzg(zzg2, remoteModel2, zzh.zzh());
                this.b.setResult(null);
                return;
            }
        }
        zzpzVar = this.c.g;
        zzpq zzg3 = zzqc.zzg();
        remoteModel = this.c.e;
        zzpzVar.zze(zzg3, remoteModel, false, 0);
        this.b.setException(new MlKitException("Model downloading failed", 13));
    }
}
