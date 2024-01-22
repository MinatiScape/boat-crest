package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class a2 implements x4 {

    /* renamed from: a  reason: collision with root package name */
    public final zzga f9961a;

    public a2(zzga zzgaVar) {
        zzga zzgaVar2 = (zzga) zzgt.c(zzgaVar, "output");
        this.f9961a = zzgaVar2;
        zzgaVar2.f10019a = this;
    }

    public static a2 p(zzga zzgaVar) {
        a2 a2Var = zzgaVar.f10019a;
        return a2Var != null ? a2Var : new a2(zzgaVar);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void a(int i, long j) throws IOException {
        this.f9961a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void b(int i, long j) throws IOException {
        this.f9961a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void c(int i, int i2) throws IOException {
        this.f9961a.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void d(int i, int i2) throws IOException {
        this.f9961a.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void e(int i, int i2) throws IOException {
        this.f9961a.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void f(int i, Object obj, o3 o3Var) throws IOException {
        this.f9961a.b(i, (zzic) obj, o3Var);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void g(int i, int i2) throws IOException {
        this.f9961a.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final <K, V> void h(int i, y2<K, V> y2Var, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.f9961a.writeTag(i, 2);
            this.f9961a.zzay(zzhu.a(y2Var, entry.getKey(), entry.getValue()));
            zzhu.b(this.f9961a, y2Var, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void i(int i, List<?> list, o3 o3Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            n(i, list.get(i2), o3Var);
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final int j() {
        return zzgs.zzf.zzxc;
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void k(int i) throws IOException {
        this.f9961a.writeTag(i, 3);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void l(int i, List<?> list, o3 o3Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            f(i, list.get(i2), o3Var);
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void m(int i) throws IOException {
        this.f9961a.writeTag(i, 4);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void n(int i, Object obj, o3 o3Var) throws IOException {
        zzga zzgaVar = this.f9961a;
        zzgaVar.writeTag(i, 3);
        o3Var.h((zzic) obj, zzgaVar.f10019a);
        zzgaVar.writeTag(i, 4);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void o(int i, zzfh zzfhVar) throws IOException {
        this.f9961a.zza(i, zzfhVar);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, float f) throws IOException {
        this.f9961a.zza(i, f);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzb(int i, long j) throws IOException {
        this.f9961a.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzc(int i, long j) throws IOException {
        this.f9961a.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzw(list.get(i4).longValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzs(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzy(list.get(i4).longValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzu(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzt(list.get(i4).floatValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzs(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzb(list.get(i4).doubleValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zza(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbh(list.get(i4).intValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzax(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzi(int i, int i2) throws IOException {
        this.f9961a.zzi(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbd(list.get(i4).intValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzay(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbg(list.get(i4).intValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzba(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzj(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzz(list.get(i4).longValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzu(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbe(list.get(i4).intValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzaz(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzx(list.get(i4).longValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzt(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzr(int i, int i2) throws IOException {
        this.f9961a.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, double d) throws IOException {
        this.f9961a.zza(i, d);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbf(list.get(i4).intValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzba(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzj(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzv(list.get(i4).longValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzs(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzl(list.get(i4).booleanValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzk(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zza(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, long j) throws IOException {
        this.f9961a.zza(i, j);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, boolean z) throws IOException {
        this.f9961a.zza(i, z);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, String str) throws IOException {
        this.f9961a.zza(i, str);
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzfh) {
            this.f9961a.zzb(i, (zzfh) obj);
        } else {
            this.f9961a.zza(i, (zzic) obj);
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9961a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbc(list.get(i4).intValue());
            }
            this.f9961a.zzay(i3);
            while (i2 < list.size()) {
                this.f9961a.zzax(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zzb(int i, List<zzfh> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f9961a.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.vision.x4
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzhj) {
            zzhj zzhjVar = (zzhj) list;
            while (i2 < list.size()) {
                Object raw = zzhjVar.getRaw(i2);
                if (raw instanceof String) {
                    this.f9961a.zza(i, (String) raw);
                } else {
                    this.f9961a.zza(i, (zzfh) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9961a.zza(i, list.get(i2));
            i2++;
        }
    }
}
