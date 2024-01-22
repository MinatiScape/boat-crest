package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.List;
/* loaded from: classes8.dex */
public final class z implements m2 {

    /* renamed from: a  reason: collision with root package name */
    public final zzdj f9625a;

    public z(zzdj zzdjVar) {
        byte[] bArr = zzem.zzd;
        this.f9625a = zzdjVar;
        zzdjVar.f9629a = this;
    }

    public static z d(zzdj zzdjVar) {
        z zVar = zzdjVar.f9629a;
        return zVar != null ? zVar : new z(zzdjVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void a(int i, Object obj, l1 l1Var) throws IOException {
        zzdj zzdjVar = this.f9625a;
        zzdjVar.zzo(i, 3);
        l1Var.b((zzfo) obj, zzdjVar.f9629a);
        zzdjVar.zzo(i, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void b(int i, Object obj, l1 l1Var) throws IOException {
        zzfo zzfoVar = (zzfo) obj;
        y yVar = (y) this.f9625a;
        yVar.zzq((i << 3) | 2);
        yVar.zzq(((zzck) zzfoVar).a(l1Var));
        l1Var.b(zzfoVar, yVar.f9629a);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void c(int i, zzdb zzdbVar) throws IOException {
        this.f9625a.zze(i, zzdbVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzh(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzB(int i, int i2) throws IOException {
        this.f9625a.zzp(i, (i2 >> 31) ^ (i2 + i2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                int intValue = ((Integer) list.get(i4)).intValue();
                i3 += zzdj.zzy((intValue >> 31) ^ (intValue + intValue));
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                zzdj zzdjVar = this.f9625a;
                int intValue2 = ((Integer) list.get(i2)).intValue();
                zzdjVar.zzq((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            zzdj zzdjVar2 = this.f9625a;
            int intValue3 = ((Integer) list.get(i2)).intValue();
            zzdjVar2.zzp(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzD(int i, long j) throws IOException {
        this.f9625a.zzr(i, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                long longValue = ((Long) list.get(i4)).longValue();
                i3 += zzdj.zzz((longValue >> 63) ^ (longValue + longValue));
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                zzdj zzdjVar = this.f9625a;
                long longValue2 = ((Long) list.get(i2)).longValue();
                zzdjVar.zzs((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            zzdj zzdjVar2 = this.f9625a;
            long longValue3 = ((Long) list.get(i2)).longValue();
            zzdjVar2.zzr(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    @Deprecated
    public final void zzF(int i) throws IOException {
        this.f9625a.zzo(i, 3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzG(int i, String str) throws IOException {
        this.f9625a.zzm(i, str);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (list instanceof zzew) {
            zzew zzewVar = (zzew) list;
            while (i2 < list.size()) {
                Object zzf = zzewVar.zzf(i2);
                if (zzf instanceof String) {
                    this.f9625a.zzm(i, (String) zzf);
                } else {
                    this.f9625a.zze(i, (zzdb) zzf);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzm(i, (String) list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzI(int i, int i2) throws IOException {
        this.f9625a.zzp(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdj.zzy(((Integer) list.get(i4)).intValue());
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzp(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzK(int i, long j) throws IOException {
        this.f9625a.zzr(i, j);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdj.zzz(((Long) list.get(i4)).longValue());
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzr(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzb(int i, boolean z) throws IOException {
        this.f9625a.zzd(i, z);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Boolean) list.get(i4)).booleanValue();
                i3++;
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzd(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f9625a.zze(i, (zzdb) list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzf(int i, double d) throws IOException {
        this.f9625a.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Double) list.get(i4)).doubleValue();
                i3 += 8;
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzh(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    @Deprecated
    public final void zzh(int i) throws IOException {
        this.f9625a.zzo(i, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzi(int i, int i2) throws IOException {
        this.f9625a.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdj.zzu(((Integer) list.get(i4)).intValue());
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzj(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzk(int i, int i2) throws IOException {
        this.f9625a.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzm(int i, long j) throws IOException {
        this.f9625a.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzn(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzh(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzo(int i, float f) throws IOException {
        this.f9625a.zzf(i, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzp(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Float) list.get(i4)).floatValue();
                i3 += 4;
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzf(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzr(int i, int i2) throws IOException {
        this.f9625a.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzs(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdj.zzu(((Integer) list.get(i4)).intValue());
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzj(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzt(int i, long j) throws IOException {
        this.f9625a.zzr(i, j);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzu(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdj.zzz(((Long) list.get(i4)).longValue());
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzr(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzdb) {
            y yVar = (y) this.f9625a;
            yVar.zzq(11);
            yVar.zzp(2, i);
            yVar.zze(3, (zzdb) obj);
            yVar.zzq(12);
            return;
        }
        zzdj zzdjVar = this.f9625a;
        zzfo zzfoVar = (zzfo) obj;
        y yVar2 = (y) zzdjVar;
        yVar2.zzq(11);
        yVar2.zzp(2, i);
        yVar2.zzq(26);
        yVar2.zzq(zzfoVar.zzE());
        zzfoVar.zzaa(zzdjVar);
        yVar2.zzq(12);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzx(int i, int i2) throws IOException {
        this.f9625a.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9625a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            this.f9625a.zzq(i3);
            while (i2 < list.size()) {
                this.f9625a.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9625a.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2
    public final void zzz(int i, long j) throws IOException {
        this.f9625a.zzh(i, j);
    }
}
