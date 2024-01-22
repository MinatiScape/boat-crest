package com.google.android.gms.tagmanager;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.gtm.zzga;
import com.google.android.gms.tagmanager.ContainerHolder;
/* loaded from: classes10.dex */
public final class zzz extends zzga {
    public final /* synthetic */ zzaa zza;
    public final ContainerHolder.ContainerAvailableListener zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzz(zzaa zzaaVar, ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
        super(looper);
        this.zza = zzaaVar;
        this.zzb = containerAvailableListener;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.what != 1) {
            zzdh.zza("Don't know how to handle this message.");
            return;
        }
        this.zzb.onContainerAvailable(this.zza, (String) message.obj);
    }
}
