package com.google.android.gms.internal.gtm;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzsn extends zzsp {
    public final byte[] zza;
    public int zzb;
    public int zzc;
    public int zzd;
    public int zze;

    public zzsn(ByteBuffer byteBuffer, boolean z) {
        super(null);
        this.zza = byteBuffer.array();
        this.zzb = byteBuffer.arrayOffset() + byteBuffer.position();
        this.zzc = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzA(List<Double> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzug) {
            zzug zzugVar = (zzug) list;
            int i3 = this.zzd & 7;
            if (i3 == 1) {
                do {
                    zzugVar.zze(zza());
                    if (zzaj()) {
                        return;
                    }
                    i2 = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i2;
                return;
            } else if (i3 == 2) {
                int zzX = zzX();
                zzai(zzX);
                int i4 = this.zzb + zzX;
                while (this.zzb < i4) {
                    zzugVar.zze(Double.longBitsToDouble(zzZ()));
                }
                return;
            } else {
                throw zzvk.zza();
            }
        }
        int i5 = this.zzd & 7;
        if (i5 == 1) {
            do {
                list.add(Double.valueOf(zza()));
                if (zzaj()) {
                    return;
                }
                i = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i;
        } else if (i5 == 2) {
            int zzX2 = zzX();
            zzai(zzX2);
            int i6 = this.zzb + zzX2;
            while (this.zzb < i6) {
                list.add(Double.valueOf(Double.longBitsToDouble(zzZ())));
            }
        } else {
            throw zzvk.zza();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzB(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            int i3 = this.zzd & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int zzX = this.zzb + zzX();
                    while (this.zzb < zzX) {
                        zzvaVar.zzh(zzX());
                    }
                    return;
                }
                throw zzvk.zza();
            }
            do {
                zzvaVar.zzh(zze());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
            return;
        }
        int i4 = this.zzd & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int zzX2 = this.zzb + zzX();
                while (this.zzb < zzX2) {
                    list.add(Integer.valueOf(zzX()));
                }
                return;
            }
            throw zzvk.zza();
        }
        do {
            list.add(Integer.valueOf(zze()));
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == this.zzd);
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzC(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (!(list instanceof zzva)) {
            int i3 = this.zzd & 7;
            if (i3 == 2) {
                int zzX = zzX();
                zzah(zzX);
                int i4 = this.zzb + zzX;
                while (this.zzb < i4) {
                    list.add(Integer.valueOf(zzW()));
                }
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(zzf()));
                    if (zzaj()) {
                        return;
                    }
                    i = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i;
                return;
            } else {
                throw zzvk.zza();
            }
        }
        zzva zzvaVar = (zzva) list;
        int i5 = this.zzd & 7;
        if (i5 == 2) {
            int zzX2 = zzX();
            zzah(zzX2);
            int i6 = this.zzb + zzX2;
            while (this.zzb < i6) {
                zzvaVar.zzh(zzW());
            }
        } else if (i5 == 5) {
            do {
                zzvaVar.zzh(zzf());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
        } else {
            throw zzvk.zza();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzD(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            int i3 = this.zzd & 7;
            if (i3 == 1) {
                do {
                    zzvzVar.zzf(zzk());
                    if (zzaj()) {
                        return;
                    }
                    i2 = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i2;
                return;
            } else if (i3 == 2) {
                int zzX = zzX();
                zzai(zzX);
                int i4 = this.zzb + zzX;
                while (this.zzb < i4) {
                    zzvzVar.zzf(zzZ());
                }
                return;
            } else {
                throw zzvk.zza();
            }
        }
        int i5 = this.zzd & 7;
        if (i5 == 1) {
            do {
                list.add(Long.valueOf(zzk()));
                if (zzaj()) {
                    return;
                }
                i = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i;
        } else if (i5 == 2) {
            int zzX2 = zzX();
            zzai(zzX2);
            int i6 = this.zzb + zzX2;
            while (this.zzb < i6) {
                list.add(Long.valueOf(zzZ()));
            }
        } else {
            throw zzvk.zza();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzE(List<Float> list) throws IOException {
        int i;
        int i2;
        if (!(list instanceof zzuq)) {
            int i3 = this.zzd & 7;
            if (i3 == 2) {
                int zzX = zzX();
                zzah(zzX);
                int i4 = this.zzb + zzX;
                while (this.zzb < i4) {
                    list.add(Float.valueOf(Float.intBitsToFloat(zzW())));
                }
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Float.valueOf(zzb()));
                    if (zzaj()) {
                        return;
                    }
                    i = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i;
                return;
            } else {
                throw zzvk.zza();
            }
        }
        zzuq zzuqVar = (zzuq) list;
        int i5 = this.zzd & 7;
        if (i5 == 2) {
            int zzX2 = zzX();
            zzah(zzX2);
            int i6 = this.zzb + zzX2;
            while (this.zzb < i6) {
                zzuqVar.zze(Float.intBitsToFloat(zzW()));
            }
        } else if (i5 == 5) {
            do {
                zzuqVar.zze(zzb());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
        } else {
            throw zzvk.zza();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.gtm.zzww
    public final <T> void zzF(List<T> list, zzwx<T> zzwxVar, zzuj zzujVar) throws IOException {
        int i;
        int i2 = this.zzd;
        if ((i2 & 7) != 3) {
            throw zzvk.zza();
        }
        do {
            list.add(zzab(zzwxVar, zzujVar));
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == i2);
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzG(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            int i3 = this.zzd & 7;
            if (i3 == 0) {
                do {
                    zzvaVar.zzh(zzg());
                    if (zzaj()) {
                        return;
                    }
                    i2 = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i2;
                return;
            } else if (i3 == 2) {
                int zzX = this.zzb + zzX();
                while (this.zzb < zzX) {
                    zzvaVar.zzh(zzX());
                }
                zzae(zzX);
                return;
            } else {
                throw zzvk.zza();
            }
        }
        int i4 = this.zzd & 7;
        if (i4 == 0) {
            do {
                list.add(Integer.valueOf(zzg()));
                if (zzaj()) {
                    return;
                }
                i = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i;
        } else if (i4 == 2) {
            int zzX2 = this.zzb + zzX();
            while (this.zzb < zzX2) {
                list.add(Integer.valueOf(zzX()));
            }
            zzae(zzX2);
        } else {
            throw zzvk.zza();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzH(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            int i3 = this.zzd & 7;
            if (i3 == 0) {
                do {
                    zzvzVar.zzf(zzl());
                    if (zzaj()) {
                        return;
                    }
                    i2 = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i2;
                return;
            } else if (i3 == 2) {
                int zzX = this.zzb + zzX();
                while (this.zzb < zzX) {
                    zzvzVar.zzf(zzp());
                }
                zzae(zzX);
                return;
            } else {
                throw zzvk.zza();
            }
        }
        int i4 = this.zzd & 7;
        if (i4 == 0) {
            do {
                list.add(Long.valueOf(zzl()));
                if (zzaj()) {
                    return;
                }
                i = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i;
        } else if (i4 == 2) {
            int zzX2 = this.zzb + zzX();
            while (this.zzb < zzX2) {
                list.add(Long.valueOf(zzp()));
            }
            zzae(zzX2);
        } else {
            throw zzvk.zza();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.gtm.zzww
    public final <T> void zzI(List<T> list, zzwx<T> zzwxVar, zzuj zzujVar) throws IOException {
        int i;
        int i2 = this.zzd;
        if ((i2 & 7) != 2) {
            throw zzvk.zza();
        }
        do {
            list.add(zzac(zzwxVar, zzujVar));
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == i2);
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzJ(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (!(list instanceof zzva)) {
            int i3 = this.zzd & 7;
            if (i3 == 2) {
                int zzX = zzX();
                zzah(zzX);
                int i4 = this.zzb + zzX;
                while (this.zzb < i4) {
                    list.add(Integer.valueOf(zzW()));
                }
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(zzh()));
                    if (zzaj()) {
                        return;
                    }
                    i = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i;
                return;
            } else {
                throw zzvk.zza();
            }
        }
        zzva zzvaVar = (zzva) list;
        int i5 = this.zzd & 7;
        if (i5 == 2) {
            int zzX2 = zzX();
            zzah(zzX2);
            int i6 = this.zzb + zzX2;
            while (this.zzb < i6) {
                zzvaVar.zzh(zzW());
            }
        } else if (i5 == 5) {
            do {
                zzvaVar.zzh(zzh());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
        } else {
            throw zzvk.zza();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzK(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            int i3 = this.zzd & 7;
            if (i3 == 1) {
                do {
                    zzvzVar.zzf(zzm());
                    if (zzaj()) {
                        return;
                    }
                    i2 = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i2;
                return;
            } else if (i3 == 2) {
                int zzX = zzX();
                zzai(zzX);
                int i4 = this.zzb + zzX;
                while (this.zzb < i4) {
                    zzvzVar.zzf(zzZ());
                }
                return;
            } else {
                throw zzvk.zza();
            }
        }
        int i5 = this.zzd & 7;
        if (i5 == 1) {
            do {
                list.add(Long.valueOf(zzm()));
                if (zzaj()) {
                    return;
                }
                i = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i;
        } else if (i5 == 2) {
            int zzX2 = zzX();
            zzai(zzX2);
            int i6 = this.zzb + zzX2;
            while (this.zzb < i6) {
                list.add(Long.valueOf(zzZ()));
            }
        } else {
            throw zzvk.zza();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzL(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            int i3 = this.zzd & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int zzX = this.zzb + zzX();
                    while (this.zzb < zzX) {
                        zzvaVar.zzh(zztj.zzs(zzX()));
                    }
                    return;
                }
                throw zzvk.zza();
            }
            do {
                zzvaVar.zzh(zzi());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
            return;
        }
        int i4 = this.zzd & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int zzX2 = this.zzb + zzX();
                while (this.zzb < zzX2) {
                    list.add(Integer.valueOf(zztj.zzs(zzX())));
                }
                return;
            }
            throw zzvk.zza();
        }
        do {
            list.add(Integer.valueOf(zzi()));
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == this.zzd);
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzM(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            int i3 = this.zzd & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int zzX = this.zzb + zzX();
                    while (this.zzb < zzX) {
                        zzvzVar.zzf(zztj.zzt(zzp()));
                    }
                    return;
                }
                throw zzvk.zza();
            }
            do {
                zzvzVar.zzf(zzn());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
            return;
        }
        int i4 = this.zzd & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int zzX2 = this.zzb + zzX();
                while (this.zzb < zzX2) {
                    list.add(Long.valueOf(zztj.zzt(zzp())));
                }
                return;
            }
            throw zzvk.zza();
        }
        do {
            list.add(Long.valueOf(zzn()));
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == this.zzd);
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzN(List<String> list) throws IOException {
        zzO(list, false);
    }

    public final void zzO(List<String> list, boolean z) throws IOException {
        int i;
        int i2;
        if ((this.zzd & 7) == 2) {
            if ((list instanceof zzvs) && !z) {
                zzvs zzvsVar = (zzvs) list;
                do {
                    zzvsVar.zzi(zzq());
                    if (zzaj()) {
                        return;
                    }
                    i2 = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i2;
                return;
            }
            do {
                list.add(zzw(z));
                if (zzaj()) {
                    return;
                }
                i = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i;
            return;
        }
        throw zzvk.zza();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzP(List<String> list) throws IOException {
        zzO(list, true);
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzQ(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            int i3 = this.zzd & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int zzX = this.zzb + zzX();
                    while (this.zzb < zzX) {
                        zzvaVar.zzh(zzX());
                    }
                    return;
                }
                throw zzvk.zza();
            }
            do {
                zzvaVar.zzh(zzj());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
            return;
        }
        int i4 = this.zzd & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int zzX2 = this.zzb + zzX();
                while (this.zzb < zzX2) {
                    list.add(Integer.valueOf(zzX()));
                }
                return;
            }
            throw zzvk.zza();
        }
        do {
            list.add(Integer.valueOf(zzj()));
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == this.zzd);
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzR(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            int i3 = this.zzd & 7;
            if (i3 == 0) {
                do {
                    zzvzVar.zzf(zzo());
                    if (zzaj()) {
                        return;
                    }
                    i2 = this.zzb;
                } while (zzX() == this.zzd);
                this.zzb = i2;
                return;
            } else if (i3 == 2) {
                int zzX = this.zzb + zzX();
                while (this.zzb < zzX) {
                    zzvzVar.zzf(zzp());
                }
                zzae(zzX);
                return;
            } else {
                throw zzvk.zza();
            }
        }
        int i4 = this.zzd & 7;
        if (i4 == 0) {
            do {
                list.add(Long.valueOf(zzo()));
                if (zzaj()) {
                    return;
                }
                i = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i;
        } else if (i4 == 2) {
            int zzX2 = this.zzb + zzX();
            while (this.zzb < zzX2) {
                list.add(Long.valueOf(zzp()));
            }
            zzae(zzX2);
        } else {
            throw zzvk.zza();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final boolean zzS() throws IOException {
        zzaf(0);
        return zzX() != 0;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final boolean zzT() throws IOException {
        int i;
        int i2;
        if (zzaj() || (i = this.zzd) == (i2 = this.zze)) {
            return false;
        }
        int i3 = i & 7;
        if (i3 == 0) {
            int i4 = this.zzc;
            int i5 = this.zzb;
            if (i4 - i5 >= 10) {
                byte[] bArr = this.zza;
                int i6 = 0;
                while (i6 < 10) {
                    int i7 = i5 + 1;
                    if (bArr[i5] >= 0) {
                        this.zzb = i7;
                        break;
                    }
                    i6++;
                    i5 = i7;
                }
            }
            for (int i8 = 0; i8 < 10; i8++) {
                if (zzU() >= 0) {
                    return true;
                }
            }
            throw zzvk.zze();
        } else if (i3 == 1) {
            zzag(8);
            return true;
        } else if (i3 == 2) {
            zzag(zzX());
            return true;
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzag(4);
                return true;
            }
            throw zzvk.zza();
        } else {
            this.zze = ((i >>> 3) << 3) | 4;
            while (zzc() != Integer.MAX_VALUE && zzT()) {
            }
            if (this.zzd == this.zze) {
                this.zze = i2;
                return true;
            }
            throw zzvk.zzg();
        }
    }

    public final byte zzU() throws IOException {
        int i = this.zzb;
        if (i != this.zzc) {
            byte[] bArr = this.zza;
            this.zzb = i + 1;
            return bArr[i];
        }
        throw zzvk.zzj();
    }

    public final int zzV() throws IOException {
        zzad(4);
        return zzW();
    }

    public final int zzW() {
        int i = this.zzb;
        byte[] bArr = this.zza;
        this.zzb = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public final int zzX() throws IOException {
        int i;
        int i2 = this.zzb;
        int i3 = this.zzc;
        if (i3 != i2) {
            byte[] bArr = this.zza;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.zzb = i4;
                return b;
            } else if (i3 - i4 < 9) {
                return (int) zzaa();
            } else {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 < 0) {
                    i = i6 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                } else {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        i = i8 ^ 16256;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ (-2080896);
                        } else {
                            i7 = i5 + 1;
                            byte b2 = bArr[i5];
                            i = (i9 ^ (b2 << 28)) ^ 266354560;
                            if (b2 < 0) {
                                i5 = i7 + 1;
                                if (bArr[i7] < 0) {
                                    i7 = i5 + 1;
                                    if (bArr[i5] < 0) {
                                        i5 = i7 + 1;
                                        if (bArr[i7] < 0) {
                                            i7 = i5 + 1;
                                            if (bArr[i5] < 0) {
                                                i5 = i7 + 1;
                                                if (bArr[i7] < 0) {
                                                    throw zzvk.zze();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i5 = i7;
                }
                this.zzb = i5;
                return i;
            }
        }
        throw zzvk.zzj();
    }

    public final long zzY() throws IOException {
        zzad(8);
        return zzZ();
    }

    public final long zzZ() {
        int i = this.zzb;
        byte[] bArr = this.zza;
        this.zzb = i + 8;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final double zza() throws IOException {
        zzaf(1);
        return Double.longBitsToDouble(zzY());
    }

    public final long zzaa() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzU = zzU();
            j |= (zzU & Byte.MAX_VALUE) << i;
            if ((zzU & 128) == 0) {
                return j;
            }
        }
        throw zzvk.zze();
    }

    public final <T> T zzab(zzwx<T> zzwxVar, zzuj zzujVar) throws IOException {
        int i = this.zze;
        this.zze = ((this.zzd >>> 3) << 3) | 4;
        try {
            T zze = zzwxVar.zze();
            zzwxVar.zzh(zze, this, zzujVar);
            zzwxVar.zzf(zze);
            if (this.zzd == this.zze) {
                return zze;
            }
            throw zzvk.zzg();
        } finally {
            this.zze = i;
        }
    }

    public final <T> T zzac(zzwx<T> zzwxVar, zzuj zzujVar) throws IOException {
        int zzX = zzX();
        zzad(zzX);
        int i = this.zzc;
        int i2 = this.zzb + zzX;
        this.zzc = i2;
        try {
            T zze = zzwxVar.zze();
            zzwxVar.zzh(zze, this, zzujVar);
            zzwxVar.zzf(zze);
            if (this.zzb == i2) {
                return zze;
            }
            throw zzvk.zzg();
        } finally {
            this.zzc = i;
        }
    }

    public final void zzad(int i) throws IOException {
        if (i < 0 || i > this.zzc - this.zzb) {
            throw zzvk.zzj();
        }
    }

    public final void zzae(int i) throws IOException {
        if (this.zzb != i) {
            throw zzvk.zzj();
        }
    }

    public final void zzaf(int i) throws IOException {
        if ((this.zzd & 7) != i) {
            throw zzvk.zza();
        }
    }

    public final void zzag(int i) throws IOException {
        zzad(i);
        this.zzb += i;
    }

    public final void zzah(int i) throws IOException {
        zzad(i);
        if ((i & 3) != 0) {
            throw zzvk.zzg();
        }
    }

    public final void zzai(int i) throws IOException {
        zzad(i);
        if ((i & 7) != 0) {
            throw zzvk.zzg();
        }
    }

    public final boolean zzaj() {
        return this.zzb == this.zzc;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final float zzb() throws IOException {
        zzaf(5);
        return Float.intBitsToFloat(zzV());
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zzc() throws IOException {
        if (zzaj()) {
            return Integer.MAX_VALUE;
        }
        int zzX = zzX();
        this.zzd = zzX;
        if (zzX == this.zze) {
            return Integer.MAX_VALUE;
        }
        return zzX >>> 3;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zze() throws IOException {
        zzaf(0);
        return zzX();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zzf() throws IOException {
        zzaf(5);
        return zzV();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zzg() throws IOException {
        zzaf(0);
        return zzX();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zzh() throws IOException {
        zzaf(5);
        return zzV();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zzi() throws IOException {
        zzaf(0);
        return zztj.zzs(zzX());
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final int zzj() throws IOException {
        zzaf(0);
        return zzX();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final long zzk() throws IOException {
        zzaf(1);
        return zzY();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final long zzl() throws IOException {
        zzaf(0);
        return zzp();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final long zzm() throws IOException {
        zzaf(1);
        return zzY();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final long zzn() throws IOException {
        zzaf(0);
        return zztj.zzt(zzp());
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final long zzo() throws IOException {
        zzaf(0);
        return zzp();
    }

    public final long zzp() throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        int i;
        int i2 = this.zzb;
        int i3 = this.zzc;
        if (i3 != i2) {
            byte[] bArr = this.zza;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.zzb = i4;
                return b;
            } else if (i3 - i4 < 9) {
                return zzaa();
            } else {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 >= 0) {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        j = i8 ^ 16256;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ (-2080896);
                        } else {
                            i7 = i5 + 1;
                            long j5 = (bArr[i5] << 28) ^ i9;
                            if (j5 < 0) {
                                int i10 = i7 + 1;
                                long j6 = j5 ^ (bArr[i7] << 35);
                                if (j6 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    i7 = i10 + 1;
                                    j5 = j6 ^ (bArr[i10] << 42);
                                    if (j5 >= 0) {
                                        j4 = 4363953127296L;
                                    } else {
                                        i10 = i7 + 1;
                                        j6 = j5 ^ (bArr[i7] << 49);
                                        if (j6 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            i7 = i10 + 1;
                                            j = (j6 ^ (bArr[i10] << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                i10 = i7 + 1;
                                                if (bArr[i7] < 0) {
                                                    throw zzvk.zze();
                                                }
                                                j2 = j;
                                                i5 = i10;
                                                this.zzb = i5;
                                                return j2;
                                            }
                                        }
                                    }
                                }
                                j2 = j3 ^ j6;
                                i5 = i10;
                                this.zzb = i5;
                                return j2;
                            }
                            j4 = 266354560;
                            j = j5 ^ j4;
                        }
                    }
                    i5 = i7;
                    j2 = j;
                    this.zzb = i5;
                    return j2;
                }
                i = i6 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                j2 = i;
                this.zzb = i5;
                return j2;
            }
        }
        throw zzvk.zzj();
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final zztd zzq() throws IOException {
        zzaf(2);
        int zzX = zzX();
        if (zzX == 0) {
            return zztd.zzb;
        }
        zzad(zzX);
        zztd zzq = zztd.zzq(this.zza, this.zzb, zzX);
        this.zzb += zzX;
        return zzq;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final <T> T zzr(Class<T> cls, zzuj zzujVar) throws IOException {
        zzaf(3);
        return (T) zzab(zzwt.zza().zzb(cls), zzujVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final <T> T zzs(zzwx<T> zzwxVar, zzuj zzujVar) throws IOException {
        zzaf(3);
        return (T) zzab(zzwxVar, zzujVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final <T> T zzt(Class<T> cls, zzuj zzujVar) throws IOException {
        zzaf(2);
        return (T) zzac(zzwt.zza().zzb(cls), zzujVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final <T> T zzu(zzwx<T> zzwxVar, zzuj zzujVar) throws IOException {
        zzaf(2);
        return (T) zzac(zzwxVar, zzujVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final String zzv() throws IOException {
        return zzw(false);
    }

    public final String zzw(boolean z) throws IOException {
        zzaf(2);
        int zzX = zzX();
        if (zzX == 0) {
            return "";
        }
        zzad(zzX);
        if (z) {
            byte[] bArr = this.zza;
            int i = this.zzb;
            if (!zzyd.zzf(bArr, i, i + zzX)) {
                throw zzvk.zzd();
            }
        }
        String str = new String(this.zza, this.zzb, zzX, zzvi.zza);
        this.zzb += zzX;
        return str;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final String zzx() throws IOException {
        return zzw(true);
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzy(List<Boolean> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzsr) {
            zzsr zzsrVar = (zzsr) list;
            int i3 = this.zzd & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int zzX = this.zzb + zzX();
                    while (this.zzb < zzX) {
                        zzsrVar.zze(zzX() != 0);
                    }
                    zzae(zzX);
                    return;
                }
                throw zzvk.zza();
            }
            do {
                zzsrVar.zze(zzS());
                if (zzaj()) {
                    return;
                }
                i2 = this.zzb;
            } while (zzX() == this.zzd);
            this.zzb = i2;
            return;
        }
        int i4 = this.zzd & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int zzX2 = this.zzb + zzX();
                while (this.zzb < zzX2) {
                    list.add(Boolean.valueOf(zzX() != 0));
                }
                zzae(zzX2);
                return;
            }
            throw zzvk.zza();
        }
        do {
            list.add(Boolean.valueOf(zzS()));
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == this.zzd);
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzww
    public final void zzz(List<zztd> list) throws IOException {
        int i;
        if ((this.zzd & 7) != 2) {
            throw zzvk.zza();
        }
        do {
            list.add(zzq());
            if (zzaj()) {
                return;
            }
            i = this.zzb;
        } while (zzX() == this.zzd);
        this.zzb = i;
    }
}
