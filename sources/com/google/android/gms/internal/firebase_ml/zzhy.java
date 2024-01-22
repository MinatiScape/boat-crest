package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public class zzhy extends zzjf {
    public zzhx j;

    @Override // java.util.AbstractMap
    public String toString() {
        zzhx zzhxVar = this.j;
        if (zzhxVar != null) {
            try {
                return zzhxVar.toString(this);
            } catch (IOException e) {
                throw zzmv.zza(e);
            }
        }
        return super.toString();
    }

    public final void zza(zzhx zzhxVar) {
        this.j = zzhxVar;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    /* renamed from: zzfc */
    public zzhy clone() {
        return (zzhy) super.clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf
    public /* synthetic */ zzjf zzfd() {
        return (zzhy) clone();
    }

    public final String zzgt() throws IOException {
        zzhx zzhxVar = this.j;
        if (zzhxVar != null) {
            return zzhxVar.zzc(this);
        }
        return super.toString();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf
    /* renamed from: zza */
    public zzhy zzb(String str, Object obj) {
        return (zzhy) super.zzb(str, obj);
    }
}
