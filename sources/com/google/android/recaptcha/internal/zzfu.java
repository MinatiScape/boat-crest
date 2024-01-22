package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.List;
/* loaded from: classes10.dex */
final class zzfu implements zzko {
    private final zzft zza;

    private zzfu(zzft zzftVar) {
        byte[] bArr = zzhn.zzd;
        this.zza = zzftVar;
        zzftVar.zza = this;
    }

    public static zzfu zza(zzft zzftVar) {
        zzfu zzfuVar = zzftVar.zza;
        return zzfuVar != null ? zzfuVar : new zzfu(zzftVar);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzp(i, (i2 >> 31) ^ (i2 + i2));
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                int intValue = ((Integer) list.get(i4)).intValue();
                i3 += zzft.zzy((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                zzft zzftVar = this.zza;
                int intValue2 = ((Integer) list.get(i2)).intValue();
                zzftVar.zzq((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            zzft zzftVar2 = this.zza;
            int intValue3 = ((Integer) list.get(i2)).intValue();
            zzftVar2.zzp(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzD(int i, long j) throws IOException {
        this.zza.zzr(i, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                long longValue = ((Long) list.get(i4)).longValue();
                i3 += zzft.zzz((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                zzft zzftVar = this.zza;
                long longValue2 = ((Long) list.get(i2)).longValue();
                zzftVar.zzs((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            zzft zzftVar2 = this.zza;
            long longValue3 = ((Long) list.get(i2)).longValue();
            zzftVar2.zzr(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    @Deprecated
    public final void zzF(int i) throws IOException {
        this.zza.zzo(i, 3);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzG(int i, String str) throws IOException {
        this.zza.zzm(i, str);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (list instanceof zzhx) {
            zzhx zzhxVar = (zzhx) list;
            while (i2 < list.size()) {
                Object zzf = zzhxVar.zzf(i2);
                if (zzf instanceof String) {
                    this.zza.zzm(i, (String) zzf);
                } else {
                    this.zza.zze(i, (zzfi) zzf);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzm(i, (String) list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzp(i, i2);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzft.zzy(((Integer) list.get(i4)).intValue());
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzp(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzK(int i, long j) throws IOException {
        this.zza.zzr(i, j);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzft.zzz(((Long) list.get(i4)).longValue());
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzr(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Boolean) list.get(i4)).booleanValue();
                i3++;
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzd(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzd(int i, zzfi zzfiVar) throws IOException {
        this.zza.zze(i, zzfiVar);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzfi) list.get(i2));
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzf(int i, double d) throws IOException {
        this.zza.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Double) list.get(i4)).doubleValue();
                i3 += 8;
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    @Deprecated
    public final void zzh(int i) throws IOException {
        this.zza.zzo(i, 4);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzi(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzft.zzu(((Integer) list.get(i4)).intValue());
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzk(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzm(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzn(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzo(int i, float f) throws IOException {
        this.zza.zzf(i, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzp(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Float) list.get(i4)).floatValue();
                i3 += 4;
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzf(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzq(int i, Object obj, zzjc zzjcVar) throws IOException {
        zzft zzftVar = this.zza;
        zzftVar.zzo(i, 3);
        zzjcVar.zzj((zzip) obj, zzftVar.zza);
        zzftVar.zzo(i, 4);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzs(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzft.zzu(((Integer) list.get(i4)).intValue());
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzt(int i, long j) throws IOException {
        this.zza.zzr(i, j);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzu(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzft.zzz(((Long) list.get(i4)).longValue());
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzr(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzv(int i, Object obj, zzjc zzjcVar) throws IOException {
        zzip zzipVar = (zzip) obj;
        zzfq zzfqVar = (zzfq) this.zza;
        zzfqVar.zzq((i << 3) | 2);
        zzfqVar.zzq(((zzer) zzipVar).zza(zzjcVar));
        zzjcVar.zzj(zzipVar, zzfqVar.zza);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzfi) {
            zzfq zzfqVar = (zzfq) this.zza;
            zzfqVar.zzq(11);
            zzfqVar.zzp(2, i);
            zzfqVar.zze(3, (zzfi) obj);
            zzfqVar.zzq(12);
            return;
        }
        zzft zzftVar = this.zza;
        zzip zzipVar = (zzip) obj;
        zzfq zzfqVar2 = (zzfq) zzftVar;
        zzfqVar2.zzq(11);
        zzfqVar2.zzp(2, i);
        zzfqVar2.zzq(26);
        zzfqVar2.zzq(zzipVar.zzn());
        zzipVar.zze(zzftVar);
        zzfqVar2.zzq(12);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            this.zza.zzq(i3);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzko
    public final void zzz(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }
}
