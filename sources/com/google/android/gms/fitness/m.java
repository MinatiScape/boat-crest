package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzby;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class m implements RemoteCall<zzap, TaskCompletionSource<Void>> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListenerHolder f8444a;
    public final /* synthetic */ SensorRequest b;

    public m(SensorsClient sensorsClient, ListenerHolder listenerHolder, SensorRequest sensorRequest) {
        this.f8444a = listenerHolder;
        this.b = sensorRequest;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(zzap zzapVar, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzby) zzapVar.getService()).zza(new com.google.android.gms.fitness.request.zzap(this.b, zzan.zzx().zzc(this.f8444a), null, zzei.zza(taskCompletionSource)));
    }
}
