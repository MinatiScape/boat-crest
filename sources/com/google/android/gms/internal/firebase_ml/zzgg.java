package com.google.android.gms.internal.firebase_ml;

import com.android.volley.toolbox.HttpClientStack;
import java.io.IOException;
/* loaded from: classes7.dex */
public class zzgg<T> extends zzjf {
    public final zzge j;
    public final String k;
    public final String l;
    public final zzgt m;
    public zzgx n = new zzgx();
    public Class<T> o;

    public zzgg(zzge zzgeVar, String str, String str2, zzgt zzgtVar, Class<T> cls) {
        k0 a2;
        this.o = (Class) zzml.checkNotNull(cls);
        this.j = (zzge) zzml.checkNotNull(zzgeVar);
        this.k = (String) zzml.checkNotNull(str);
        this.l = (String) zzml.checkNotNull(str2);
        this.m = zzgtVar;
        String zzfg = zzgeVar.zzfg();
        if (zzfg != null) {
            zzgx zzgxVar = this.n;
            StringBuilder sb = new StringBuilder(zzfg.length() + 23);
            sb.append(zzfg);
            sb.append(" Google-API-Java-Client");
            zzgxVar.zzad(sb.toString());
        } else {
            this.n.zzad("Google-API-Java-Client");
        }
        zzgx zzgxVar2 = this.n;
        a2 = k0.a();
        zzgx zzgxVar3 = (zzgx) zzgxVar2.zzb("X-Goog-Api-Client", a2.c(zzgeVar.getClass().getSimpleName()));
    }

    public IOException zza(zzhd zzhdVar) {
        return new zzhg(zzhdVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf
    /* renamed from: zzc */
    public zzgg<T> zzb(String str, Object obj) {
        return (zzgg) super.zzb(str, obj);
    }

    public zzge zzfk() {
        return this.j;
    }

    public final zzgx zzfl() {
        return this.n;
    }

    public final T zzfm() throws IOException {
        zzml.checkArgument(true);
        zzml.checkArgument(true);
        zzhc zza = zzfk().zzfh().zza(this.k, new zzgu(zzhm.zza(this.j.zzff(), this.l, this, true)), this.m);
        new zzgb().zzb(zza);
        zza.zza(zzfk().zzfi());
        if (this.m == null && (this.k.equals("POST") || this.k.equals("PUT") || this.k.equals(HttpClientStack.HttpPatch.METHOD_NAME))) {
            zza.zza(new zzgp());
        }
        zza.zzga().putAll(this.n);
        zza.zza(new zzgs());
        zza.zza(new l0(this, zza.zzgc(), zza));
        zzhd zzgf = zza.zzgf();
        zzgf.zzga();
        zzgf.getStatusCode();
        zzgf.getStatusMessage();
        return (T) zzgf.zza(this.o);
    }
}
