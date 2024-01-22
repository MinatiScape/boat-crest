package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzad;
import com.google.android.gms.fitness.request.zze;
import com.google.android.gms.internal.fitness.zzbo;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;
/* loaded from: classes6.dex */
public final class b implements RemoteCall<zzk, TaskCompletionSource<Void>> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListenerHolder f8426a;
    public final /* synthetic */ List b;
    public final /* synthetic */ int c;

    public b(BleClient bleClient, ListenerHolder listenerHolder, List list, int i) {
        this.f8426a = listenerHolder;
        this.b = list;
        this.c = i;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(zzk zzkVar, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbo) zzkVar.getService()).zza(new StartBleScanRequest((List<DataType>) this.b, (zzad) zze.zzu().zza(this.f8426a), this.c, (zzcn) zzei.zza(taskCompletionSource)));
    }
}
