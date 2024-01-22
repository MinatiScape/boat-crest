package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class y3 {

    /* renamed from: a  reason: collision with root package name */
    public zzfy f10136a;
    public List<Long> b;
    public List<zzfo> c;
    public long d;
    public final /* synthetic */ zzkn e;

    public /* synthetic */ y3(zzkn zzknVar, zzkl zzklVar) {
        this.e = zzknVar;
    }

    public static final long b(zzfo zzfoVar) {
        return ((zzfoVar.zzd() / 1000) / 60) / 60;
    }

    public final boolean a(long j, zzfo zzfoVar) {
        Preconditions.checkNotNull(zzfoVar);
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (this.b == null) {
            this.b = new ArrayList();
        }
        if (this.c.size() <= 0 || b(this.c.get(0)) == b(zzfoVar)) {
            long zzbt = this.d + zzfoVar.zzbt();
            this.e.zzg();
            if (zzbt >= Math.max(0, zzdw.zzh.zza(null).intValue())) {
                return false;
            }
            this.d = zzbt;
            this.c.add(zzfoVar);
            this.b.add(Long.valueOf(j));
            int size = this.c.size();
            this.e.zzg();
            return size < Math.max(1, zzdw.zzi.zza(null).intValue());
        }
        return false;
    }
}
