package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;
/* loaded from: classes6.dex */
public final class p implements Handler.Callback {
    public final /* synthetic */ q h;

    public /* synthetic */ p(q qVar, zzq zzqVar) {
        this.h = qVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        HashMap hashMap4;
        HashMap hashMap5;
        int i = message.what;
        if (i == 0) {
            hashMap = this.h.f;
            synchronized (hashMap) {
                zzo zzoVar = (zzo) message.obj;
                hashMap2 = this.h.f;
                o oVar = (o) hashMap2.get(zzoVar);
                if (oVar != null && oVar.i()) {
                    if (oVar.j()) {
                        oVar.g("GmsClientSupervisor");
                    }
                    hashMap3 = this.h.f;
                    hashMap3.remove(zzoVar);
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            hashMap4 = this.h.f;
            synchronized (hashMap4) {
                zzo zzoVar2 = (zzo) message.obj;
                hashMap5 = this.h.f;
                o oVar2 = (o) hashMap5.get(zzoVar2);
                if (oVar2 != null && oVar2.a() == 3) {
                    String valueOf = String.valueOf(zzoVar2);
                    Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + valueOf, new Exception());
                    ComponentName b = oVar2.b();
                    if (b == null) {
                        b = zzoVar2.zza();
                    }
                    if (b == null) {
                        String zzc = zzoVar2.zzc();
                        Preconditions.checkNotNull(zzc);
                        b = new ComponentName(zzc, "unknown");
                    }
                    oVar2.onServiceDisconnected(b);
                }
            }
            return true;
        }
    }
}
