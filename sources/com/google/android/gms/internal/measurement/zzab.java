package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzab {

    /* renamed from: a  reason: collision with root package name */
    public zzaa f8940a;
    public zzaa b;
    public final List<zzaa> c;

    public zzab() {
        this.f8940a = new zzaa("", 0L, null);
        this.b = new zzaa("", 0L, null);
        this.c = new ArrayList();
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzab zzabVar = new zzab(this.f8940a.clone());
        for (zzaa zzaaVar : this.c) {
            zzabVar.c.add(zzaaVar.clone());
        }
        return zzabVar;
    }

    public final zzaa zza() {
        return this.f8940a;
    }

    public final zzaa zzb() {
        return this.b;
    }

    public final List<zzaa> zzc() {
        return this.c;
    }

    public final void zzd(zzaa zzaaVar) {
        this.f8940a = zzaaVar;
        this.b = zzaaVar.clone();
        this.c.clear();
    }

    public final void zze(String str, long j, Map<String, Object> map) {
        this.c.add(new zzaa(str, j, map));
    }

    public final void zzf(zzaa zzaaVar) {
        this.b = zzaaVar;
    }

    public zzab(zzaa zzaaVar) {
        this.f8940a = zzaaVar;
        this.b = zzaaVar.clone();
        this.c = new ArrayList();
    }
}
