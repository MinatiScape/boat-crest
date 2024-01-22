package com.google.android.gms.internal.location;

import android.os.RemoteException;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class q implements RemoteCall, zzcs {

    /* renamed from: a  reason: collision with root package name */
    public final p f8894a;
    @GuardedBy("this")
    public ListenerHolder b;
    @GuardedBy("this")
    public boolean c = true;
    public final /* synthetic */ zzbp d;

    public q(zzbp zzbpVar, ListenerHolder listenerHolder, p pVar) {
        this.d = zzbpVar;
        this.b = listenerHolder;
        this.f8894a = pVar;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        ListenerHolder.ListenerKey listenerKey;
        boolean z;
        zzda zzdaVar = (zzda) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        synchronized (this) {
            listenerKey = this.b.getListenerKey();
            z = this.c;
            this.b.clear();
        }
        if (listenerKey == null) {
            taskCompletionSource.setResult(Boolean.FALSE);
        } else {
            this.f8894a.zza(zzdaVar, listenerKey, z, taskCompletionSource);
        }
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final synchronized ListenerHolder zza() {
        return this.b;
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final void zzb() {
        ListenerHolder.ListenerKey<?> listenerKey;
        synchronized (this) {
            this.c = false;
            listenerKey = this.b.getListenerKey();
        }
        if (listenerKey != null) {
            this.d.doUnregisterEventListener(listenerKey, 2441);
        }
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final synchronized void zzc(ListenerHolder listenerHolder) {
        ListenerHolder listenerHolder2 = this.b;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            this.b = listenerHolder;
        }
    }
}
