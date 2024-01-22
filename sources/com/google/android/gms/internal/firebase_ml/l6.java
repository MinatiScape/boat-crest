package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public final class l6 implements p {

    /* renamed from: a  reason: collision with root package name */
    public final zzwi f8701a;

    public l6(zzwi zzwiVar) {
        zzwi zzwiVar2 = (zzwi) zzxd.b(zzwiVar, "output");
        this.f8701a = zzwiVar2;
        zzwiVar2.f8810a = this;
    }

    public static l6 p(zzwi zzwiVar) {
        l6 l6Var = zzwiVar.f8810a;
        return l6Var != null ? l6Var : new l6(zzwiVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void a(int i, long j) throws IOException {
        this.f8701a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void b(int i, long j) throws IOException {
        this.f8701a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void c(int i, int i2) throws IOException {
        this.f8701a.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void d(int i, int i2) throws IOException {
        this.f8701a.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final int e() {
        return zzwz.zzg.zzcme;
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void f(int i, int i2) throws IOException {
        this.f8701a.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void g(int i, List<?> list, c8 c8Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            h(i, list.get(i2), c8Var);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void h(int i, Object obj, c8 c8Var) throws IOException {
        zzwi zzwiVar = this.f8701a;
        zzwiVar.writeTag(i, 3);
        c8Var.g((zzyk) obj, zzwiVar.f8810a);
        zzwiVar.writeTag(i, 4);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final <K, V> void i(int i, i7<K, V> i7Var, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.f8701a.writeTag(i, 2);
            this.f8701a.zzdc(zzyc.a(i7Var, entry.getKey(), entry.getValue()));
            zzyc.b(this.f8701a, i7Var, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void j(int i, List<?> list, c8 c8Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            l(i, list.get(i2), c8Var);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void k(int i) throws IOException {
        this.f8701a.writeTag(i, 4);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void l(int i, Object obj, c8 c8Var) throws IOException {
        this.f8701a.b(i, (zzyk) obj, c8Var);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void m(int i) throws IOException {
        this.f8701a.writeTag(i, 3);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void n(int i, zzvv zzvvVar) throws IOException {
        this.f8701a.zza(i, zzvvVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void o(int i, String str) throws IOException {
        this.f8701a.zzb(i, str);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zza(int i, float f) throws IOException {
        this.f8701a.zza(i, f);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzb(int i, long j) throws IOException {
        this.f8701a.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzc(int i, long j) throws IOException {
        this.f8701a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzaa(list.get(i4).longValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzw(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzac(list.get(i4).longValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzy(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzr(list.get(i4).floatValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzq(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzd(list.get(i4).doubleValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzc(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzdl(list.get(i4).intValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzdb(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzi(int i, int i2) throws IOException {
        this.f8701a.zzi(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzdh(list.get(i4).intValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzdc(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzk(int i, int i2) throws IOException {
        this.f8701a.zzk(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzad(list.get(i4).longValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzy(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzdi(list.get(i4).intValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzdd(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzj(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzab(list.get(i4).longValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzx(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzr(int i, int i2) throws IOException {
        this.f8701a.zzk(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zza(int i, double d) throws IOException {
        this.f8701a.zza(i, d);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzdj(list.get(i4).intValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzde(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzk(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzz(list.get(i4).longValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzw(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzay(list.get(i4).booleanValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzax(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zza(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzdk(list.get(i4).intValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzde(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzk(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zza(int i, long j) throws IOException {
        this.f8701a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zza(int i, boolean z) throws IOException {
        this.f8701a.zza(i, z);
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzvv) {
            this.f8701a.zzb(i, (zzvv) obj);
        } else {
            this.f8701a.zza(i, (zzyk) obj);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8701a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzwi.zzdg(list.get(i4).intValue());
            }
            this.f8701a.zzdc(i3);
            while (i2 < list.size()) {
                this.f8701a.zzdb(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zzb(int i, List<zzvv> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f8701a.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.p
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzxv) {
            zzxv zzxvVar = (zzxv) list;
            while (i2 < list.size()) {
                Object raw = zzxvVar.getRaw(i2);
                if (raw instanceof String) {
                    this.f8701a.zzb(i, (String) raw);
                } else {
                    this.f8701a.zza(i, (zzvv) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8701a.zzb(i, list.get(i2));
            i2++;
        }
    }
}
