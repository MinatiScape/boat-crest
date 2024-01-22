package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class l2 implements d5 {

    /* renamed from: a  reason: collision with root package name */
    public final zzgk f8837a;

    public l2(zzgk zzgkVar) {
        zzgk zzgkVar2 = (zzgk) zzhc.c(zzgkVar, "output");
        this.f8837a = zzgkVar2;
        zzgkVar2.f8861a = this;
    }

    public static l2 o(zzgk zzgkVar) {
        l2 l2Var = zzgkVar.f8861a;
        return l2Var != null ? l2Var : new l2(zzgkVar);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void a(int i, long j) throws IOException {
        this.f8837a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void b(int i, long j) throws IOException {
        this.f8837a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void c(int i, int i2) throws IOException {
        this.f8837a.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void d(int i) throws IOException {
        this.f8837a.writeTag(i, 4);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void e(int i, int i2) throws IOException {
        this.f8837a.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void f(int i) throws IOException {
        this.f8837a.writeTag(i, 3);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void g(int i, List<?> list, x3 x3Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            i(i, list.get(i2), x3Var);
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void h(int i, Object obj, x3 x3Var) throws IOException {
        zzgk zzgkVar = this.f8837a;
        zzgkVar.writeTag(i, 3);
        x3Var.e((zzik) obj, zzgkVar.f8861a);
        zzgkVar.writeTag(i, 4);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void i(int i, Object obj, x3 x3Var) throws IOException {
        this.f8837a.b(i, (zzik) obj, x3Var);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void j(int i, zzfx zzfxVar) throws IOException {
        this.f8837a.zza(i, zzfxVar);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void k(int i, int i2) throws IOException {
        this.f8837a.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final <K, V> void l(int i, g3<K, V> g3Var, Map<K, V> map) throws IOException {
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            this.f8837a.writeTag(i, 2);
            next.getKey();
            next.getValue();
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void m(int i, int i2) throws IOException {
        this.f8837a.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void n(int i, List<?> list, x3 x3Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            h(i, list.get(i2), x3Var);
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, float f) throws IOException {
        this.f8837a.zza(i, f);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzb(int i, long j) throws IOException {
        this.f8837a.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final int zzbe() {
        return zzkl.zzaea;
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzc(int i, long j) throws IOException {
        this.f8837a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zze(list.get(i4).longValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zza(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zze(int i, int i2) throws IOException {
        this.f8837a.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzf(int i, int i2) throws IOException {
        this.f8837a.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzc(list.get(i4).doubleValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzx(list.get(i4).intValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzb(list.get(i4).booleanValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zza(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zza(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzt(list.get(i4).intValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzo(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzw(list.get(i4).intValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzh(list.get(i4).longValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzu(list.get(i4).intValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzp(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzf(list.get(i4).longValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, double d) throws IOException {
        this.f8837a.zza(i, d);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzv(list.get(i4).intValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzd(list.get(i4).longValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zza(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzg(list.get(i4).longValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzc(list.get(i4).floatValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzb(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, long j) throws IOException {
        this.f8837a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, boolean z) throws IOException {
        this.f8837a.zza(i, z);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, String str) throws IOException {
        this.f8837a.zza(i, str);
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzfx) {
            this.f8837a.zzb(i, (zzfx) obj);
        } else {
            this.f8837a.zza(i, (zzik) obj);
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8837a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzs(list.get(i4).intValue());
            }
            this.f8837a.zzo(i3);
            while (i2 < list.size()) {
                this.f8837a.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zzb(int i, List<zzfx> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f8837a.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.fitness.d5
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzhr) {
            zzhr zzhrVar = (zzhr) list;
            while (i2 < list.size()) {
                Object zzaf = zzhrVar.zzaf(i2);
                if (zzaf instanceof String) {
                    this.f8837a.zza(i, (String) zzaf);
                } else {
                    this.f8837a.zza(i, (zzfx) zzaf);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8837a.zza(i, list.get(i2));
            i2++;
        }
    }
}
