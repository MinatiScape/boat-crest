package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class zzhb {

    /* renamed from: a  reason: collision with root package name */
    public final zzhh f8766a;
    public final zzhe b;

    public zzhb(zzhh zzhhVar, zzhe zzheVar) {
        this.f8766a = zzhhVar;
        this.b = zzheVar;
    }

    public final zzhc zza(String str, zzgu zzguVar, zzgt zzgtVar) throws IOException {
        zzhc zzhcVar = new zzhc(this.f8766a, null);
        zzhe zzheVar = this.b;
        if (zzheVar != null) {
            zzheVar.zza(zzhcVar);
        }
        zzhcVar.zzaf(str);
        zzhcVar.zza(zzguVar);
        if (zzgtVar != null) {
            zzhcVar.zza(zzgtVar);
        }
        return zzhcVar;
    }
}
