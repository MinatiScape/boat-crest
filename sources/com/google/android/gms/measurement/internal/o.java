package com.google.android.gms.measurement.internal;

import android.util.Log;
import com.clevertap.android.sdk.Constants;
/* loaded from: classes10.dex */
public final class o implements Runnable {
    public final /* synthetic */ int h;
    public final /* synthetic */ String i;
    public final /* synthetic */ Object j;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;
    public final /* synthetic */ zzei m;

    public o(zzei zzeiVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.m = zzeiVar;
        this.h = i;
        this.i = str;
        this.j = obj;
        this.k = obj2;
        this.l = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        char c;
        long j;
        char c2;
        long j2;
        v zzm = this.m.zzs.zzm();
        if (zzm.a()) {
            c = this.m.b;
            if (c == 0) {
                if (this.m.zzs.zzf().zzy()) {
                    zzei zzeiVar = this.m;
                    zzeiVar.zzs.zzaw();
                    zzeiVar.b = 'C';
                } else {
                    zzei zzeiVar2 = this.m;
                    zzeiVar2.zzs.zzaw();
                    zzeiVar2.b = Constants.INAPP_POSITION_CENTER;
                }
            }
            j = this.m.c;
            if (j < 0) {
                zzei zzeiVar3 = this.m;
                zzeiVar3.zzs.zzf().zzh();
                zzeiVar3.c = 42097L;
            }
            char charAt = "01VDIWEA?".charAt(this.h);
            c2 = this.m.b;
            j2 = this.m.c;
            String d = zzei.d(true, this.i, this.j, this.k, this.l);
            StringBuilder sb = new StringBuilder(String.valueOf(d).length() + 24);
            sb.append("2");
            sb.append(charAt);
            sb.append(c2);
            sb.append(j2);
            sb.append(":");
            sb.append(d);
            String sb2 = sb.toString();
            if (sb2.length() > 1024) {
                sb2 = this.i.substring(0, 1024);
            }
            zzev zzevVar = zzm.c;
            if (zzevVar != null) {
                zzevVar.zzb(sb2, 1L);
                return;
            }
            return;
        }
        Log.println(6, this.m.zzq(), "Persisted config not initialized. Not logging error/warn");
    }
}
