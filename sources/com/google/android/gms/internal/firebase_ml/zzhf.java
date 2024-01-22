package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class zzhf {

    /* renamed from: a  reason: collision with root package name */
    public int f8769a;
    public String b;
    public zzgx c;
    public String d;
    public String e;

    public zzhf(int i, String str, zzgx zzgxVar) {
        zzml.checkArgument(i >= 0);
        this.f8769a = i;
        this.b = str;
        this.c = (zzgx) zzml.checkNotNull(zzgxVar);
    }

    public final zzhf zzah(String str) {
        this.e = str;
        return this;
    }

    public final zzhf zzai(String str) {
        this.d = str;
        return this;
    }

    public zzhf(zzhd zzhdVar) {
        this(zzhdVar.getStatusCode(), zzhdVar.getStatusMessage(), zzhdVar.zzga());
        try {
            String zzgh = zzhdVar.zzgh();
            this.d = zzgh;
            if (zzgh.length() == 0) {
                this.d = null;
            }
        } catch (IOException e) {
            zzne.zzb(e);
        }
        StringBuilder zzc = zzhg.zzc(zzhdVar);
        if (this.d != null) {
            zzc.append(zzjt.zzaig);
            zzc.append(this.d);
        }
        this.e = zzc.toString();
    }
}
