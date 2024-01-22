package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;
/* loaded from: classes10.dex */
public final class zzfc implements Handler.Callback {
    public final /* synthetic */ zzfe zza;

    public zzfc(zzfe zzfeVar) {
        this.zza = zzfeVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Object obj;
        boolean zzm;
        if (message.what == 1) {
            obj = zzff.zza;
            if (obj.equals(message.obj)) {
                this.zza.zza.zza();
                zzm = this.zza.zza.zzm();
                if (!zzm) {
                    this.zza.zzc(1800000L);
                }
            }
        }
        return true;
    }
}
