package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class p2 extends f {
    public final /* synthetic */ zzjj e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p2(zzjj zzjjVar, y0 y0Var) {
        super(y0Var);
        this.e = zzjjVar;
    }

    @Override // com.google.android.gms.measurement.internal.f
    public final void c() {
        zzjj zzjjVar = this.e;
        zzjjVar.zzg();
        if (zzjjVar.zzL()) {
            zzjjVar.zzs.zzay().zzj().zza("Inactivity, disconnecting from the service");
            zzjjVar.zzs();
        }
    }
}
