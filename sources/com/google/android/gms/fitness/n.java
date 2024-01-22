package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.zzam;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzar;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzby;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class n implements RemoteCall<zzap, TaskCompletionSource<Boolean>> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListenerHolder f8445a;

    public n(SensorsClient sensorsClient, ListenerHolder listenerHolder) {
        this.f8445a = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(zzap zzapVar, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzap zzapVar2 = zzapVar;
        TaskCompletionSource<Boolean> taskCompletionSource2 = taskCompletionSource;
        zzam zzd = zzan.zzx().zzd(this.f8445a);
        if (zzd == null) {
            taskCompletionSource2.setResult(Boolean.FALSE);
            return;
        }
        ((zzby) zzapVar2.getService()).zza(new zzar((zzv) zzd, (PendingIntent) null, (zzcn) zzei.zzb(taskCompletionSource2)));
    }
}
