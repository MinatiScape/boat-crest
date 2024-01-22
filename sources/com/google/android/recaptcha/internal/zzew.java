package com.google.android.recaptcha.internal;

import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzew {
    public static int zza(byte[] bArr, int i, zzev zzevVar) throws zzhp {
        int zzj = zzj(bArr, i, zzevVar);
        int i2 = zzevVar.zza;
        if (i2 >= 0) {
            if (i2 <= bArr.length - zzj) {
                if (i2 == 0) {
                    zzevVar.zzc = zzfi.zzb;
                    return zzj;
                }
                zzevVar.zzc = zzfi.zzm(bArr, zzj, i2);
                return zzj + i2;
            }
            throw zzhp.zzj();
        }
        throw zzhp.zzf();
    }

    public static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << 24) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    public static int zzc(zzjc zzjcVar, byte[] bArr, int i, int i2, int i3, zzev zzevVar) throws IOException {
        Object zze = zzjcVar.zze();
        int zzn = zzn(zze, zzjcVar, bArr, i, i2, i3, zzevVar);
        zzjcVar.zzf(zze);
        zzevVar.zzc = zze;
        return zzn;
    }

    public static int zzd(zzjc zzjcVar, byte[] bArr, int i, int i2, zzev zzevVar) throws IOException {
        Object zze = zzjcVar.zze();
        int zzo = zzo(zze, zzjcVar, bArr, i, i2, zzevVar);
        zzjcVar.zzf(zze);
        zzevVar.zzc = zze;
        return zzo;
    }

    public static int zze(zzjc zzjcVar, int i, byte[] bArr, int i2, int i3, zzhm zzhmVar, zzev zzevVar) throws IOException {
        int zzd = zzd(zzjcVar, bArr, i2, i3, zzevVar);
        zzhmVar.add(zzevVar.zzc);
        while (zzd < i3) {
            int zzj = zzj(bArr, zzd, zzevVar);
            if (i != zzevVar.zza) {
                break;
            }
            zzd = zzd(zzjcVar, bArr, zzj, i3, zzevVar);
            zzhmVar.add(zzevVar.zzc);
        }
        return zzd;
    }

    public static int zzf(byte[] bArr, int i, zzhm zzhmVar, zzev zzevVar) throws IOException {
        zzhg zzhgVar = (zzhg) zzhmVar;
        int zzj = zzj(bArr, i, zzevVar);
        int i2 = zzevVar.zza + zzj;
        while (zzj < i2) {
            zzj = zzj(bArr, zzj, zzevVar);
            zzhgVar.zzg(zzevVar.zza);
        }
        if (zzj == i2) {
            return zzj;
        }
        throw zzhp.zzj();
    }

    public static int zzg(byte[] bArr, int i, zzev zzevVar) throws zzhp {
        int zzj = zzj(bArr, i, zzevVar);
        int i2 = zzevVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzevVar.zzc = "";
                return zzj;
            }
            zzevVar.zzc = new String(bArr, zzj, i2, zzhn.zzb);
            return zzj + i2;
        }
        throw zzhp.zzf();
    }

    public static int zzh(byte[] bArr, int i, zzev zzevVar) throws zzhp {
        int zzj = zzj(bArr, i, zzevVar);
        int i2 = zzevVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzevVar.zzc = "";
                return zzj;
            }
            zzevVar.zzc = zzkl.zzd(bArr, zzj, i2);
            return zzj + i2;
        }
        throw zzhp.zzf();
    }

    public static int zzi(int i, byte[] bArr, int i2, int i3, zzjx zzjxVar, zzev zzevVar) throws zzhp {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzm = zzm(bArr, i2, zzevVar);
                zzjxVar.zzj(i, Long.valueOf(zzevVar.zzb));
                return zzm;
            } else if (i4 == 1) {
                zzjxVar.zzj(i, Long.valueOf(zzq(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzj = zzj(bArr, i2, zzevVar);
                int i5 = zzevVar.zza;
                if (i5 >= 0) {
                    if (i5 <= bArr.length - zzj) {
                        if (i5 == 0) {
                            zzjxVar.zzj(i, zzfi.zzb);
                        } else {
                            zzjxVar.zzj(i, zzfi.zzm(bArr, zzj, i5));
                        }
                        return zzj + i5;
                    }
                    throw zzhp.zzj();
                }
                throw zzhp.zzf();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzjxVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                    return i2 + 4;
                }
                throw zzhp.zzc();
            } else {
                int i6 = (i & (-8)) | 4;
                zzjx zzf = zzjx.zzf();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzj2 = zzj(bArr, i2, zzevVar);
                    int i8 = zzevVar.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzj2;
                        break;
                    }
                    int zzi = zzi(i7, bArr, zzj2, i3, zzf, zzevVar);
                    i7 = i8;
                    i2 = zzi;
                }
                if (i2 <= i3 && i7 == i6) {
                    zzjxVar.zzj(i, zzf);
                    return i2;
                }
                throw zzhp.zzg();
            }
        }
        throw zzhp.zzc();
    }

    public static int zzj(byte[] bArr, int i, zzev zzevVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzevVar.zza = b;
            return i2;
        }
        return zzk(b, bArr, i2, zzevVar);
    }

    public static int zzk(int i, byte[] bArr, int i2, zzev zzevVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzevVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzevVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzevVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzevVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzevVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int zzl(int i, byte[] bArr, int i2, int i3, zzhm zzhmVar, zzev zzevVar) {
        zzhg zzhgVar = (zzhg) zzhmVar;
        int zzj = zzj(bArr, i2, zzevVar);
        zzhgVar.zzg(zzevVar.zza);
        while (zzj < i3) {
            int zzj2 = zzj(bArr, zzj, zzevVar);
            if (i != zzevVar.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzevVar);
            zzhgVar.zzg(zzevVar.zza);
        }
        return zzj;
    }

    public static int zzm(byte[] bArr, int i, zzev zzevVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzevVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= (b2 & Byte.MAX_VALUE) << i4;
            i3 = i5;
            b = b2;
        }
        zzevVar.zzb = j2;
        return i3;
    }

    public static int zzn(Object obj, zzjc zzjcVar, byte[] bArr, int i, int i2, int i3, zzev zzevVar) throws IOException {
        int zzc = ((zzis) zzjcVar).zzc(obj, bArr, i, i2, i3, zzevVar);
        zzevVar.zzc = obj;
        return zzc;
    }

    public static int zzo(Object obj, zzjc zzjcVar, byte[] bArr, int i, int i2, zzev zzevVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzk(i4, bArr, i3, zzevVar);
            i4 = zzevVar.zza;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            int i6 = i4 + i5;
            zzjcVar.zzi(obj, bArr, i5, i6, zzevVar);
            zzevVar.zzc = obj;
            return i6;
        }
        throw zzhp.zzj();
    }

    public static int zzp(int i, byte[] bArr, int i2, int i3, zzev zzevVar) throws zzhp {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 5) {
                                return i2 + 4;
                            }
                            throw zzhp.zzc();
                        }
                        int i5 = (i & (-8)) | 4;
                        int i6 = 0;
                        while (i2 < i3) {
                            i2 = zzj(bArr, i2, zzevVar);
                            i6 = zzevVar.zza;
                            if (i6 == i5) {
                                break;
                            }
                            i2 = zzp(i6, bArr, i2, i3, zzevVar);
                        }
                        if (i2 > i3 || i6 != i5) {
                            throw zzhp.zzg();
                        }
                        return i2;
                    }
                    return zzj(bArr, i2, zzevVar) + zzevVar.zza;
                }
                return i2 + 8;
            }
            return zzm(bArr, i2, zzevVar);
        }
        throw zzhp.zzc();
    }

    public static long zzq(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 7] & 255) << 56);
    }
}
