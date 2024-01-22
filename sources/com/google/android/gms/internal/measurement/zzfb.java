package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzfb extends zzjv<zzfc, zzfb> implements zzlh {
    public zzfb() {
        super(zzfc.e());
    }

    public final int zza() {
        return ((zzfc) this.zza).zzb();
    }

    public final zzfa zzb(int i) {
        return ((zzfc) this.zza).zzd(i);
    }

    public final zzfb zzc() {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfc.g((zzfc) this.zza);
        return this;
    }

    public final zzfb zzd(int i, zzez zzezVar) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfc.f((zzfc) this.zza, i, zzezVar.zzaA());
        return this;
    }

    public final List<zzeh> zze() {
        return Collections.unmodifiableList(((zzfc) this.zza).zzi());
    }

    public /* synthetic */ zzfb(h1 h1Var) {
        super(zzfc.e());
    }
}
