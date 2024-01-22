package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.request.zza;
import com.google.android.gms.fitness.request.zzad;
import com.google.android.gms.fitness.request.zzbg;
import com.google.android.gms.fitness.request.zze;
import com.google.android.gms.internal.fitness.zzbo;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class c implements RemoteCall<zzk, TaskCompletionSource<Boolean>> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListenerHolder f8427a;

    public c(BleClient bleClient, ListenerHolder listenerHolder) {
        this.f8427a = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(zzk zzkVar, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzk zzkVar2 = zzkVar;
        TaskCompletionSource<Boolean> taskCompletionSource2 = taskCompletionSource;
        zza zzb = zze.zzu().zzb(this.f8427a);
        if (zzb == null) {
            taskCompletionSource2.setResult(Boolean.FALSE);
            return;
        }
        ((zzbo) zzkVar2.getService()).zza(new zzbg((zzad) zzb, (zzcn) zzei.zzb(taskCompletionSource2)));
    }
}
