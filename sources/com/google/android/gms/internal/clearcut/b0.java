package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public final class b0 implements z2 {

    /* renamed from: a  reason: collision with root package name */
    public final zzbn f8570a;

    public b0(zzbn zzbnVar) {
        zzbn zzbnVar2 = (zzbn) zzci.d(zzbnVar, "output");
        this.f8570a = zzbnVar2;
        zzbnVar2.f8614a = this;
    }

    public static b0 c(zzbn zzbnVar) {
        b0 b0Var = zzbnVar.f8614a;
        return b0Var != null ? b0Var : new b0(zzbnVar);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void a(int i, long j) throws IOException {
        this.f8570a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void b(int i, long j) throws IOException {
        this.f8570a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void d(int i) throws IOException {
        this.f8570a.zzb(i, 4);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void f(int i) throws IOException {
        this.f8570a.zzb(i, 3);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void g(int i, List<?> list, r1 r1Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            k(i, list.get(i2), r1Var);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void h(int i, List<?> list, r1 r1Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            j(i, list.get(i2), r1Var);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void i(int i, zzbb zzbbVar) throws IOException {
        this.f8570a.zza(i, zzbbVar);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void j(int i, Object obj, r1 r1Var) throws IOException {
        this.f8570a.a(i, (zzdo) obj, r1Var);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void k(int i, Object obj, r1 r1Var) throws IOException {
        zzbn zzbnVar = this.f8570a;
        zzbnVar.zzb(i, 3);
        r1Var.g((zzdo) obj, zzbnVar.f8614a);
        zzbnVar.zzb(i, 4);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final int l() {
        return zzcg.zzg.zzko;
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void m(int i, int i2) throws IOException {
        this.f8570a.zzc(i, i2);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final <K, V> void n(int i, a1<K, V> a1Var, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.f8570a.zzb(i, 2);
            this.f8570a.zzo(zzdg.a(a1Var, entry.getKey(), entry.getValue()));
            zzdg.b(this.f8570a, a1Var, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void o(int i, int i2) throws IOException {
        this.f8570a.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zza(int i, double d) throws IOException {
        this.f8570a.zza(i, d);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zza(int i, float f) throws IOException {
        this.f8570a.zza(i, f);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zza(int i, long j) throws IOException {
        this.f8570a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbb) {
            this.f8570a.zzb(i, (zzbb) obj);
        } else {
            this.f8570a.zzb(i, (zzdo) obj);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zza(int i, String str) throws IOException {
        this.f8570a.zza(i, str);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzcx)) {
            while (i2 < list.size()) {
                this.f8570a.zza(i, list.get(i2));
                i2++;
            }
            return;
        }
        zzcx zzcxVar = (zzcx) list;
        while (i2 < list.size()) {
            Object raw = zzcxVar.getRaw(i2);
            if (raw instanceof String) {
                this.f8570a.zza(i, (String) raw);
            } else {
                this.f8570a.zza(i, (zzbb) raw);
            }
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzc(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzs(list.get(i4).intValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzn(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzb(int i, long j) throws IOException {
        this.f8570a.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzb(int i, List<zzbb> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f8570a.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzf(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzv(list.get(i4).intValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzq(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzb(int i, boolean z) throws IOException {
        this.f8570a.zzb(i, z);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzc(int i, int i2) throws IOException {
        this.f8570a.zzc(i, i2);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzc(int i, long j) throws IOException {
        this.f8570a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zza(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zze(list.get(i4).longValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzb(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzd(int i, int i2) throws IOException {
        this.f8570a.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zza(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzf(list.get(i4).longValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzb(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zze(int i, int i2) throws IOException {
        this.f8570a.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzc(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzh(list.get(i4).longValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzd(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzf(int i, int i2) throws IOException {
        this.f8570a.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zza(i, list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzb(list.get(i4).floatValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zza(list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zza(i, list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzb(list.get(i4).doubleValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zza(list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzc(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzx(list.get(i4).intValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzn(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzb(i, list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzb(list.get(i4).booleanValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zza(list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzd(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzt(list.get(i4).intValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzo(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzf(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzw(list.get(i4).intValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzq(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzc(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzi(list.get(i4).longValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzd(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zze(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzu(list.get(i4).intValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzp(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.z2
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f8570a.zzb(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.f8570a.zzb(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzbn.zzg(list.get(i4).longValue());
        }
        this.f8570a.zzo(i3);
        while (i2 < list.size()) {
            this.f8570a.zzc(list.get(i2).longValue());
            i2++;
        }
    }
}
