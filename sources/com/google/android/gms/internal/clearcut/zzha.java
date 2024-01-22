package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzge;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes7.dex */
public final class zzha extends zzfu<zzha> implements Cloneable {
    public byte[] k;
    public zzge.zzd l;
    public String m;
    public String n;
    public zzgy o;
    public String p;
    public zzgz q;
    public String r;
    public int[] s;
    public zzge.zzs t;
    public byte[] zzbjp;
    public long zzbju;
    public byte[] zzbjw;
    public boolean zzbkc;
    public long zzbjf = 0;
    public long zzbjg = 0;
    public String h = "";
    public int zzbji = 0;
    public String i = "";
    public zzhb[] j = zzhb.zzge();

    public zzha() {
        byte[] bArr = zzgb.zzse;
        this.k = bArr;
        this.l = null;
        this.zzbjp = bArr;
        this.m = "";
        this.n = "";
        this.o = null;
        this.p = "";
        this.zzbju = 180000L;
        this.q = null;
        this.zzbjw = bArr;
        this.r = "";
        this.s = zzgb.zzrx;
        this.t = null;
        this.zzbkc = false;
        this.zzrj = null;
        this.zzrs = -1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: a */
    public final zzha clone() {
        try {
            zzha zzhaVar = (zzha) super.clone();
            zzhb[] zzhbVarArr = this.j;
            if (zzhbVarArr != null && zzhbVarArr.length > 0) {
                zzhaVar.j = new zzhb[zzhbVarArr.length];
                int i = 0;
                while (true) {
                    zzhb[] zzhbVarArr2 = this.j;
                    if (i >= zzhbVarArr2.length) {
                        break;
                    }
                    if (zzhbVarArr2[i] != null) {
                        zzhaVar.j[i] = (zzhb) zzhbVarArr2[i].clone();
                    }
                    i++;
                }
            }
            zzge.zzd zzdVar = this.l;
            if (zzdVar != null) {
                zzhaVar.l = zzdVar;
            }
            zzgy zzgyVar = this.o;
            if (zzgyVar != null) {
                zzhaVar.o = (zzgy) zzgyVar.clone();
            }
            zzgz zzgzVar = this.q;
            if (zzgzVar != null) {
                zzhaVar.q = (zzgz) zzgzVar.clone();
            }
            int[] iArr = this.s;
            if (iArr != null && iArr.length > 0) {
                zzhaVar.s = (int[]) iArr.clone();
            }
            zzge.zzs zzsVar = this.t;
            if (zzsVar != null) {
                zzhaVar.t = zzsVar;
            }
            return zzhaVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzha) {
            zzha zzhaVar = (zzha) obj;
            if (this.zzbjf == zzhaVar.zzbjf && this.zzbjg == zzhaVar.zzbjg) {
                String str = this.h;
                if (str == null) {
                    if (zzhaVar.h != null) {
                        return false;
                    }
                } else if (!str.equals(zzhaVar.h)) {
                    return false;
                }
                if (this.zzbji != zzhaVar.zzbji) {
                    return false;
                }
                String str2 = this.i;
                if (str2 == null) {
                    if (zzhaVar.i != null) {
                        return false;
                    }
                } else if (!str2.equals(zzhaVar.i)) {
                    return false;
                }
                if (zzfy.equals(this.j, zzhaVar.j) && Arrays.equals(this.k, zzhaVar.k)) {
                    zzge.zzd zzdVar = this.l;
                    if (zzdVar == null) {
                        if (zzhaVar.l != null) {
                            return false;
                        }
                    } else if (!zzdVar.equals(zzhaVar.l)) {
                        return false;
                    }
                    if (Arrays.equals(this.zzbjp, zzhaVar.zzbjp)) {
                        String str3 = this.m;
                        if (str3 == null) {
                            if (zzhaVar.m != null) {
                                return false;
                            }
                        } else if (!str3.equals(zzhaVar.m)) {
                            return false;
                        }
                        String str4 = this.n;
                        if (str4 == null) {
                            if (zzhaVar.n != null) {
                                return false;
                            }
                        } else if (!str4.equals(zzhaVar.n)) {
                            return false;
                        }
                        zzgy zzgyVar = this.o;
                        if (zzgyVar == null) {
                            if (zzhaVar.o != null) {
                                return false;
                            }
                        } else if (!zzgyVar.equals(zzhaVar.o)) {
                            return false;
                        }
                        String str5 = this.p;
                        if (str5 == null) {
                            if (zzhaVar.p != null) {
                                return false;
                            }
                        } else if (!str5.equals(zzhaVar.p)) {
                            return false;
                        }
                        if (this.zzbju != zzhaVar.zzbju) {
                            return false;
                        }
                        zzgz zzgzVar = this.q;
                        if (zzgzVar == null) {
                            if (zzhaVar.q != null) {
                                return false;
                            }
                        } else if (!zzgzVar.equals(zzhaVar.q)) {
                            return false;
                        }
                        if (Arrays.equals(this.zzbjw, zzhaVar.zzbjw)) {
                            String str6 = this.r;
                            if (str6 == null) {
                                if (zzhaVar.r != null) {
                                    return false;
                                }
                            } else if (!str6.equals(zzhaVar.r)) {
                                return false;
                            }
                            if (zzfy.equals(this.s, zzhaVar.s)) {
                                zzge.zzs zzsVar = this.t;
                                if (zzsVar == null) {
                                    if (zzhaVar.t != null) {
                                        return false;
                                    }
                                } else if (!zzsVar.equals(zzhaVar.t)) {
                                    return false;
                                }
                                if (this.zzbkc != zzhaVar.zzbkc) {
                                    return false;
                                }
                                zzfw zzfwVar = this.zzrj;
                                if (zzfwVar == null || zzfwVar.isEmpty()) {
                                    zzfw zzfwVar2 = zzhaVar.zzrj;
                                    return zzfwVar2 == null || zzfwVar2.isEmpty();
                                }
                                return this.zzrj.equals(zzhaVar.zzrj);
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzbjf;
        long j2 = this.zzbjg;
        int hashCode = (((((zzha.class.getName().hashCode() + 527) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31 * 31;
        String str = this.h;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.zzbji) * 31;
        String str2 = this.i;
        int hashCode3 = ((((((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31 * 31) + 1237) * 31) + zzfy.hashCode(this.j)) * 31) + Arrays.hashCode(this.k);
        zzge.zzd zzdVar = this.l;
        int hashCode4 = ((((hashCode3 * 31) + (zzdVar == null ? 0 : zzdVar.hashCode())) * 31) + Arrays.hashCode(this.zzbjp)) * 31;
        String str3 = this.m;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.n;
        int hashCode6 = hashCode5 + (str4 == null ? 0 : str4.hashCode());
        zzgy zzgyVar = this.o;
        int hashCode7 = ((hashCode6 * 31) + (zzgyVar == null ? 0 : zzgyVar.hashCode())) * 31;
        String str5 = this.p;
        int hashCode8 = str5 == null ? 0 : str5.hashCode();
        long j3 = this.zzbju;
        zzgz zzgzVar = this.q;
        int hashCode9 = (((((((hashCode7 + hashCode8) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (zzgzVar == null ? 0 : zzgzVar.hashCode())) * 31) + Arrays.hashCode(this.zzbjw)) * 31;
        String str6 = this.r;
        int hashCode10 = str6 == null ? 0 : str6.hashCode();
        zzge.zzs zzsVar = this.t;
        int hashCode11 = (((((((hashCode9 + hashCode10) * 31 * 31) + zzfy.hashCode(this.s)) * 31 * 31) + (zzsVar == null ? 0 : zzsVar.hashCode())) * 31) + (this.zzbkc ? 1231 : 1237)) * 31;
        zzfw zzfwVar = this.zzrj;
        if (zzfwVar != null && !zzfwVar.isEmpty()) {
            i = this.zzrj.hashCode();
        }
        return hashCode11 + i;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final void zza(zzfs zzfsVar) throws IOException {
        long j = this.zzbjf;
        if (j != 0) {
            zzfsVar.zzi(1, j);
        }
        String str = this.h;
        if (str != null && !str.equals("")) {
            zzfsVar.zza(2, this.h);
        }
        zzhb[] zzhbVarArr = this.j;
        int i = 0;
        if (zzhbVarArr != null && zzhbVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzhb[] zzhbVarArr2 = this.j;
                if (i2 >= zzhbVarArr2.length) {
                    break;
                }
                zzhb zzhbVar = zzhbVarArr2[i2];
                if (zzhbVar != null) {
                    zzfsVar.zza(3, zzhbVar);
                }
                i2++;
            }
        }
        byte[] bArr = this.k;
        byte[] bArr2 = zzgb.zzse;
        if (!Arrays.equals(bArr, bArr2)) {
            zzfsVar.zza(4, this.k);
        }
        if (!Arrays.equals(this.zzbjp, bArr2)) {
            zzfsVar.zza(6, this.zzbjp);
        }
        zzgy zzgyVar = this.o;
        if (zzgyVar != null) {
            zzfsVar.zza(7, zzgyVar);
        }
        String str2 = this.m;
        if (str2 != null && !str2.equals("")) {
            zzfsVar.zza(8, this.m);
        }
        zzge.zzd zzdVar = this.l;
        if (zzdVar != null) {
            zzfsVar.zze(9, zzdVar);
        }
        int i3 = this.zzbji;
        if (i3 != 0) {
            zzfsVar.zzc(11, i3);
        }
        String str3 = this.n;
        if (str3 != null && !str3.equals("")) {
            zzfsVar.zza(13, this.n);
        }
        String str4 = this.p;
        if (str4 != null && !str4.equals("")) {
            zzfsVar.zza(14, this.p);
        }
        long j2 = this.zzbju;
        if (j2 != 180000) {
            zzfsVar.zzb(15, 0);
            zzfsVar.zzn(zzfs.zzj(j2));
        }
        zzgz zzgzVar = this.q;
        if (zzgzVar != null) {
            zzfsVar.zza(16, zzgzVar);
        }
        long j3 = this.zzbjg;
        if (j3 != 0) {
            zzfsVar.zzi(17, j3);
        }
        if (!Arrays.equals(this.zzbjw, bArr2)) {
            zzfsVar.zza(18, this.zzbjw);
        }
        int[] iArr = this.s;
        if (iArr != null && iArr.length > 0) {
            while (true) {
                int[] iArr2 = this.s;
                if (i >= iArr2.length) {
                    break;
                }
                zzfsVar.zzc(20, iArr2[i]);
                i++;
            }
        }
        zzge.zzs zzsVar = this.t;
        if (zzsVar != null) {
            zzfsVar.zze(23, zzsVar);
        }
        String str5 = this.r;
        if (str5 != null && !str5.equals("")) {
            zzfsVar.zza(24, this.r);
        }
        boolean z = this.zzbkc;
        if (z) {
            zzfsVar.zzb(25, z);
        }
        String str6 = this.i;
        if (str6 != null && !str6.equals("")) {
            zzfsVar.zza(26, this.i);
        }
        super.zza(zzfsVar);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final int zzen() {
        int[] iArr;
        int zzen = super.zzen();
        long j = this.zzbjf;
        if (j != 0) {
            zzen += zzfs.zzd(1, j);
        }
        String str = this.h;
        if (str != null && !str.equals("")) {
            zzen += zzfs.zzb(2, this.h);
        }
        zzhb[] zzhbVarArr = this.j;
        int i = 0;
        if (zzhbVarArr != null && zzhbVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzhb[] zzhbVarArr2 = this.j;
                if (i2 >= zzhbVarArr2.length) {
                    break;
                }
                zzhb zzhbVar = zzhbVarArr2[i2];
                if (zzhbVar != null) {
                    zzen += zzfs.zzb(3, zzhbVar);
                }
                i2++;
            }
        }
        byte[] bArr = this.k;
        byte[] bArr2 = zzgb.zzse;
        if (!Arrays.equals(bArr, bArr2)) {
            zzen += zzfs.zzb(4, this.k);
        }
        if (!Arrays.equals(this.zzbjp, bArr2)) {
            zzen += zzfs.zzb(6, this.zzbjp);
        }
        zzgy zzgyVar = this.o;
        if (zzgyVar != null) {
            zzen += zzfs.zzb(7, zzgyVar);
        }
        String str2 = this.m;
        if (str2 != null && !str2.equals("")) {
            zzen += zzfs.zzb(8, this.m);
        }
        zzge.zzd zzdVar = this.l;
        if (zzdVar != null) {
            zzen += zzbn.zzc(9, zzdVar);
        }
        int i3 = this.zzbji;
        if (i3 != 0) {
            zzen += zzfs.zzr(11) + zzfs.zzs(i3);
        }
        String str3 = this.n;
        if (str3 != null && !str3.equals("")) {
            zzen += zzfs.zzb(13, this.n);
        }
        String str4 = this.p;
        if (str4 != null && !str4.equals("")) {
            zzen += zzfs.zzb(14, this.p);
        }
        long j2 = this.zzbju;
        if (j2 != 180000) {
            zzen += zzfs.zzr(15) + zzfs.zzo(zzfs.zzj(j2));
        }
        zzgz zzgzVar = this.q;
        if (zzgzVar != null) {
            zzen += zzfs.zzb(16, zzgzVar);
        }
        long j3 = this.zzbjg;
        if (j3 != 0) {
            zzen += zzfs.zzd(17, j3);
        }
        if (!Arrays.equals(this.zzbjw, bArr2)) {
            zzen += zzfs.zzb(18, this.zzbjw);
        }
        int[] iArr2 = this.s;
        if (iArr2 != null && iArr2.length > 0) {
            int i4 = 0;
            while (true) {
                iArr = this.s;
                if (i >= iArr.length) {
                    break;
                }
                i4 += zzfs.zzs(iArr[i]);
                i++;
            }
            zzen = zzen + i4 + (iArr.length * 2);
        }
        zzge.zzs zzsVar = this.t;
        if (zzsVar != null) {
            zzen += zzbn.zzc(23, zzsVar);
        }
        String str5 = this.r;
        if (str5 != null && !str5.equals("")) {
            zzen += zzfs.zzb(24, this.r);
        }
        if (this.zzbkc) {
            zzen += zzfs.zzr(25) + 1;
        }
        String str6 = this.i;
        return (str6 == null || str6.equals("")) ? zzen : zzen + zzfs.zzb(26, this.i);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu
    public final /* synthetic */ zzha zzeo() throws CloneNotSupportedException {
        return (zzha) clone();
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzha) clone();
    }
}
