package com.google.android.gms.internal.gtm;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class zzsm {
    public static int zza(byte[] bArr, int i, zzsl zzslVar) throws zzvk {
        int zzj = zzj(bArr, i, zzslVar);
        int i2 = zzslVar.zza;
        if (i2 >= 0) {
            if (i2 <= bArr.length - zzj) {
                if (i2 == 0) {
                    zzslVar.zzc = zztd.zzb;
                    return zzj;
                }
                zzslVar.zzc = zztd.zzn(bArr, zzj, i2);
                return zzj + i2;
            }
            throw zzvk.zzj();
        }
        throw zzvk.zzf();
    }

    public static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int zzc(zzwx zzwxVar, byte[] bArr, int i, int i2, int i3, zzsl zzslVar) throws IOException {
        zzwn zzwnVar = (zzwn) zzwxVar;
        Object zze = zzwnVar.zze();
        int zzc = zzwnVar.zzc(zze, bArr, i, i2, i3, zzslVar);
        zzwnVar.zzf(zze);
        zzslVar.zzc = zze;
        return zzc;
    }

    public static int zzd(zzwx zzwxVar, byte[] bArr, int i, int i2, zzsl zzslVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzk(i4, bArr, i3, zzslVar);
            i4 = zzslVar.zza;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            Object zze = zzwxVar.zze();
            int i6 = i4 + i5;
            zzwxVar.zzi(zze, bArr, i5, i6, zzslVar);
            zzwxVar.zzf(zze);
            zzslVar.zzc = zze;
            return i6;
        }
        throw zzvk.zzj();
    }

    public static int zze(zzwx<?> zzwxVar, int i, byte[] bArr, int i2, int i3, zzvh<?> zzvhVar, zzsl zzslVar) throws IOException {
        int zzd = zzd(zzwxVar, bArr, i2, i3, zzslVar);
        zzvhVar.add(zzslVar.zzc);
        while (zzd < i3) {
            int zzj = zzj(bArr, zzd, zzslVar);
            if (i != zzslVar.zza) {
                break;
            }
            zzd = zzd(zzwxVar, bArr, zzj, i3, zzslVar);
            zzvhVar.add(zzslVar.zzc);
        }
        return zzd;
    }

    public static int zzf(byte[] bArr, int i, zzvh<?> zzvhVar, zzsl zzslVar) throws IOException {
        zzva zzvaVar = (zzva) zzvhVar;
        int zzj = zzj(bArr, i, zzslVar);
        int i2 = zzslVar.zza + zzj;
        while (zzj < i2) {
            zzj = zzj(bArr, zzj, zzslVar);
            zzvaVar.zzh(zzslVar.zza);
        }
        if (zzj == i2) {
            return zzj;
        }
        throw zzvk.zzj();
    }

    public static int zzg(byte[] bArr, int i, zzsl zzslVar) throws zzvk {
        int zzj = zzj(bArr, i, zzslVar);
        int i2 = zzslVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzslVar.zzc = "";
                return zzj;
            }
            zzslVar.zzc = new String(bArr, zzj, i2, zzvi.zza);
            return zzj + i2;
        }
        throw zzvk.zzf();
    }

    public static int zzh(byte[] bArr, int i, zzsl zzslVar) throws zzvk {
        int zzj = zzj(bArr, i, zzslVar);
        int i2 = zzslVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzslVar.zzc = "";
                return zzj;
            }
            zzslVar.zzc = zzyd.zzd(bArr, zzj, i2);
            return zzj + i2;
        }
        throw zzvk.zzf();
    }

    public static int zzi(int i, byte[] bArr, int i2, int i3, zzxp zzxpVar, zzsl zzslVar) throws zzvk {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzm = zzm(bArr, i2, zzslVar);
                zzxpVar.zzh(i, Long.valueOf(zzslVar.zzb));
                return zzm;
            } else if (i4 == 1) {
                zzxpVar.zzh(i, Long.valueOf(zzo(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzj = zzj(bArr, i2, zzslVar);
                int i5 = zzslVar.zza;
                if (i5 >= 0) {
                    if (i5 <= bArr.length - zzj) {
                        if (i5 == 0) {
                            zzxpVar.zzh(i, zztd.zzb);
                        } else {
                            zzxpVar.zzh(i, zztd.zzn(bArr, zzj, i5));
                        }
                        return zzj + i5;
                    }
                    throw zzvk.zzj();
                }
                throw zzvk.zzf();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzxpVar.zzh(i, Integer.valueOf(zzb(bArr, i2)));
                    return i2 + 4;
                }
                throw zzvk.zzc();
            } else {
                int i6 = (i & (-8)) | 4;
                zzxp zze = zzxp.zze();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzj2 = zzj(bArr, i2, zzslVar);
                    int i8 = zzslVar.zza;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = zzj2;
                        break;
                    }
                    i7 = i8;
                    i2 = zzi(i8, bArr, zzj2, i3, zze, zzslVar);
                }
                if (i2 <= i3 && i7 == i6) {
                    zzxpVar.zzh(i, zze);
                    return i2;
                }
                throw zzvk.zzg();
            }
        }
        throw zzvk.zzc();
    }

    public static int zzj(byte[] bArr, int i, zzsl zzslVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzslVar.zza = b;
            return i2;
        }
        return zzk(b, bArr, i2, zzslVar);
    }

    public static int zzk(int i, byte[] bArr, int i2, zzsl zzslVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzslVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzslVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzslVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzslVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzslVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int zzl(int i, byte[] bArr, int i2, int i3, zzvh<?> zzvhVar, zzsl zzslVar) {
        zzva zzvaVar = (zzva) zzvhVar;
        int zzj = zzj(bArr, i2, zzslVar);
        zzvaVar.zzh(zzslVar.zza);
        while (zzj < i3) {
            int zzj2 = zzj(bArr, zzj, zzslVar);
            if (i != zzslVar.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzslVar);
            zzvaVar.zzh(zzslVar.zza);
        }
        return zzj;
    }

    public static int zzm(byte[] bArr, int i, zzsl zzslVar) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzslVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (b & Byte.MAX_VALUE) << i4;
            b2 = bArr[i3];
            i3 = i5;
        }
        zzslVar.zzb = j2;
        return i3;
    }

    public static int zzn(int i, byte[] bArr, int i2, int i3, zzsl zzslVar) throws zzvk {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 5) {
                                return i2 + 4;
                            }
                            throw zzvk.zzc();
                        }
                        int i5 = (i & (-8)) | 4;
                        int i6 = 0;
                        while (i2 < i3) {
                            i2 = zzj(bArr, i2, zzslVar);
                            i6 = zzslVar.zza;
                            if (i6 == i5) {
                                break;
                            }
                            i2 = zzn(i6, bArr, i2, i3, zzslVar);
                        }
                        if (i2 > i3 || i6 != i5) {
                            throw zzvk.zzg();
                        }
                        return i2;
                    }
                    return zzj(bArr, i2, zzslVar) + zzslVar.zza;
                }
                return i2 + 8;
            }
            return zzm(bArr, i2, zzslVar);
        }
        throw zzvk.zzc();
    }

    public static long zzo(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }
}
