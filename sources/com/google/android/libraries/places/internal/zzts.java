package com.google.android.libraries.places.internal;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;
/* loaded from: classes10.dex */
final class zzts<T> implements zzue<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzvc.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzto zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zztx zzo;
    private final zzsy zzp;
    private final zzuw<?, ?> zzq;
    private final zzrw<?> zzr;
    private final zztl zzs;

    private zzts(int[] iArr, Object[] objArr, int i, int i2, zzto zztoVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zztx zztxVar, zzsy zzsyVar, zzuw<?, ?> zzuwVar, zzrw<?> zzrwVar, zztl zztlVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zztoVar instanceof zzsf;
        this.zzj = z;
        this.zzh = zzrwVar != null && zzrwVar.zza(zztoVar);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zztxVar;
        this.zzp = zzsyVar;
        this.zzq = zzuwVar;
        this.zzr = zzrwVar;
        this.zzg = zztoVar;
        this.zzs = zztlVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:163:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0397  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.google.android.libraries.places.internal.zzts<T> zza(java.lang.Class<T> r33, com.google.android.libraries.places.internal.zztm r34, com.google.android.libraries.places.internal.zztx r35, com.google.android.libraries.places.internal.zzsy r36, com.google.android.libraries.places.internal.zzuw<?, ?> r37, com.google.android.libraries.places.internal.zzrw<?> r38, com.google.android.libraries.places.internal.zztl r39) {
        /*
            Method dump skipped, instructions count: 1045
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzts.zza(java.lang.Class, com.google.android.libraries.places.internal.zztm, com.google.android.libraries.places.internal.zztx, com.google.android.libraries.places.internal.zzsy, com.google.android.libraries.places.internal.zzuw, com.google.android.libraries.places.internal.zzrw, com.google.android.libraries.places.internal.zztl):com.google.android.libraries.places.internal.zzts");
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzvc.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzvc.zzf(t, j)).booleanValue();
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final void zzb(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzc = zzc(i);
            long j = 1048575 & zzc;
            int i2 = this.zzc[i];
            switch ((zzc & 267386880) >>> 20) {
                case 0:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza(t, j, zzvc.zze(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zzd(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zzb(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zzb(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zza(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zzb(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zza(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza(t, j, zzvc.zzc(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza(t, j, zzvc.zzf(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza(t, j, zzvc.zzf(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zza(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zza(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zza(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zzb(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zza(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zza((zzts<T>) t2, i)) {
                        zzvc.zza((Object) t, j, zzvc.zzb(t2, j));
                        zzb((zzts<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzp.zza(t, t2, j);
                    break;
                case 50:
                    zzug.zza(this.zzs, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zza((zzts<T>) t2, i2, i)) {
                        zzvc.zza(t, j, zzvc.zzf(t2, j));
                        zzb((zzts<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zza((zzts<T>) t2, i2, i)) {
                        zzvc.zza(t, j, zzvc.zzf(t2, j));
                        zzb((zzts<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        zzug.zza(this.zzq, t, t2);
        if (this.zzh) {
            zzug.zza(this.zzr, t, t2);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final void zzc(T t) {
        int i;
        int i2 = this.zzm;
        while (true) {
            i = this.zzn;
            if (i2 >= i) {
                break;
            }
            long zzc = zzc(this.zzl[i2]) & ErrorCode.ERR_UNKNOWN;
            Object zzf = zzvc.zzf(t, zzc);
            if (zzf != null) {
                zzvc.zza(t, zzc, this.zzs.zzc(zzf));
            }
            i2++;
        }
        int length = this.zzl.length;
        while (i < length) {
            this.zzp.zza(t, this.zzl[i]);
            i++;
        }
        this.zzq.zzb(t);
        if (this.zzh) {
            this.zzr.zzc(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.android.libraries.places.internal.zzue] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.libraries.places.internal.zzue] */
    @Override // com.google.android.libraries.places.internal.zzue
    public final boolean zzd(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.zzm) {
                return !this.zzh || this.zzr.zza(t).zzf();
            }
            int i6 = this.zzl[i5];
            int i7 = this.zzc[i6];
            int zzc = zzc(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if (((268435456 & zzc) != 0) && !zza(t, i6, i, i2, i10)) {
                return false;
            }
            int i11 = (267386880 & zzc) >>> 20;
            if (i11 != 9 && i11 != 17) {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (zza((zzts<T>) t, i7, i6) && !zza(t, zzc, zza(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzb2 = this.zzs.zzb(zzvc.zzf(t, zzc & ErrorCode.ERR_UNKNOWN));
                            if (!zzb2.isEmpty()) {
                                if (this.zzs.zza(zzb(i6)).zzb.zza() == zzvr.MESSAGE) {
                                    zzue<T> zzueVar = 0;
                                    Iterator<?> it = zzb2.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzueVar == null) {
                                            zzueVar = zzud.zza().zza((Class) next.getClass());
                                        }
                                        boolean zzd = zzueVar.zzd(next);
                                        zzueVar = zzueVar;
                                        if (!zzd) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzvc.zzf(t, zzc & ErrorCode.ERR_UNKNOWN);
                if (!list.isEmpty()) {
                    ?? zza2 = zza(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!zza2.zzd(list.get(i12))) {
                            z = false;
                            break;
                        } else {
                            i12++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i6, i, i2, i10) && !zza(t, zzc, zza(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
    }

    private final int zzc(int i) {
        return this.zzc[i + 1];
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzvc.zzf(t, j)).floatValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((zzts<T>) t, i) == zza((zzts<T>) t2, i);
    }

    private final int zzd(int i) {
        return this.zzc[i + 2];
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzvc.zzf(t, j)).intValue();
    }

    private final void zzb(T t, T t2, int i) {
        int zzc = zzc(i);
        int i2 = this.zzc[i];
        long j = zzc & ErrorCode.ERR_UNKNOWN;
        if (zza((zzts<T>) t2, i2, i)) {
            Object zzf = zza((zzts<T>) t, i2, i) ? zzvc.zzf(t, j) : null;
            Object zzf2 = zzvc.zzf(t2, j);
            if (zzf != null && zzf2 != null) {
                zzvc.zza(t, j, zzsg.zza(zzf, zzf2));
                zzb((zzts<T>) t, i2, i);
            } else if (zzf2 != null) {
                zzvc.zza(t, j, zzf2);
                zzb((zzts<T>) t, i2, i);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.libraries.places.internal.zzue
    public final int zzb(T t) {
        int i;
        int i2;
        int i3;
        long j;
        int zzd;
        int zzb2;
        int zzj;
        int zzh;
        int zzi;
        int zze;
        int zzg;
        int zzb3;
        int zzi2;
        int zze2;
        int zzg2;
        boolean z = this.zzj;
        int i4 = 267386880;
        int i5 = ErrorCode.ERR_UNKNOWN;
        int i6 = 1;
        int i7 = 0;
        if (z) {
            Unsafe unsafe = zzb;
            int i8 = 0;
            int i9 = 0;
            while (i8 < this.zzc.length) {
                int zzc = zzc(i8);
                int i10 = (zzc & i4) >>> 20;
                int i11 = this.zzc[i8];
                long j2 = zzc & ErrorCode.ERR_UNKNOWN;
                if (i10 >= zzsc.zza.zza() && i10 <= zzsc.zzb.zza()) {
                    int i12 = this.zzc[i8 + 2];
                }
                switch (i10) {
                    case 0:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzb(i11, 0.0d);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 1:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzb(i11, 0.0f);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 2:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzd(i11, zzvc.zzb(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 3:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zze(i11, zzvc.zzb(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 4:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzf(i11, zzvc.zza(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 5:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzg(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 6:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzi(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 7:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzb(i11, true);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 8:
                        if (zza((zzts<T>) t, i8)) {
                            Object zzf = zzvc.zzf(t, j2);
                            if (zzf instanceof zzrb) {
                                zzb3 = zzrs.zzc(i11, (zzrb) zzf);
                                break;
                            } else {
                                zzb3 = zzrs.zzb(i11, (String) zzf);
                                break;
                            }
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 9:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzug.zza(i11, zzvc.zzf(t, j2), zza(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 10:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzc(i11, (zzrb) zzvc.zzf(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 11:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzg(i11, zzvc.zza(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 12:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzk(i11, zzvc.zza(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 13:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzj(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 14:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzh(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 15:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzh(i11, zzvc.zza(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 16:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzf(i11, zzvc.zzb(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 17:
                        if (zza((zzts<T>) t, i8)) {
                            zzb3 = zzrs.zzc(i11, (zzto) zzvc.zzf(t, j2), zza(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 18:
                        zzb3 = zzug.zzi(i11, zza(t, j2), false);
                        break;
                    case 19:
                        zzb3 = zzug.zzh(i11, zza(t, j2), false);
                        break;
                    case 20:
                        zzb3 = zzug.zza(i11, (List<Long>) zza(t, j2), false);
                        break;
                    case 21:
                        zzb3 = zzug.zzb(i11, (List<Long>) zza(t, j2), false);
                        break;
                    case 22:
                        zzb3 = zzug.zze(i11, zza(t, j2), false);
                        break;
                    case 23:
                        zzb3 = zzug.zzi(i11, zza(t, j2), false);
                        break;
                    case 24:
                        zzb3 = zzug.zzh(i11, zza(t, j2), false);
                        break;
                    case 25:
                        zzb3 = zzug.zzj(i11, zza(t, j2), false);
                        break;
                    case 26:
                        zzb3 = zzug.zza(i11, zza(t, j2));
                        break;
                    case 27:
                        zzb3 = zzug.zza(i11, zza(t, j2), zza(i8));
                        break;
                    case 28:
                        zzb3 = zzug.zzb(i11, zza(t, j2));
                        break;
                    case 29:
                        zzb3 = zzug.zzf(i11, zza(t, j2), false);
                        break;
                    case 30:
                        zzb3 = zzug.zzd(i11, zza(t, j2), false);
                        break;
                    case 31:
                        zzb3 = zzug.zzh(i11, zza(t, j2), false);
                        break;
                    case 32:
                        zzb3 = zzug.zzi(i11, zza(t, j2), false);
                        break;
                    case 33:
                        zzb3 = zzug.zzg(i11, zza(t, j2), false);
                        break;
                    case 34:
                        zzb3 = zzug.zzc(i11, zza(t, j2), false);
                        break;
                    case 35:
                        zzi2 = zzug.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 36:
                        zzi2 = zzug.zzh((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 37:
                        zzi2 = zzug.zza((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 38:
                        zzi2 = zzug.zzb((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 39:
                        zzi2 = zzug.zze((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 40:
                        zzi2 = zzug.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 41:
                        zzi2 = zzug.zzh((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 42:
                        zzi2 = zzug.zzj((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 43:
                        zzi2 = zzug.zzf((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 44:
                        zzi2 = zzug.zzd((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 45:
                        zzi2 = zzug.zzh((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 46:
                        zzi2 = zzug.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 47:
                        zzi2 = zzug.zzg((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 48:
                        zzi2 = zzug.zzc((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzrs.zze(i11);
                            zzg2 = zzrs.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 49:
                        zzb3 = zzug.zzb(i11, (List<zzto>) zza(t, j2), zza(i8));
                        break;
                    case 50:
                        zzb3 = this.zzs.zza(i11, zzvc.zzf(t, j2), zzb(i8));
                        break;
                    case 51:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzb(i11, 0.0d);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 52:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzb(i11, 0.0f);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 53:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzd(i11, zze(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 54:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zze(i11, zze(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 55:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzf(i11, zzd(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 56:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzg(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 57:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzi(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 58:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzb(i11, true);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 59:
                        if (zza((zzts<T>) t, i11, i8)) {
                            Object zzf2 = zzvc.zzf(t, j2);
                            if (zzf2 instanceof zzrb) {
                                zzb3 = zzrs.zzc(i11, (zzrb) zzf2);
                                break;
                            } else {
                                zzb3 = zzrs.zzb(i11, (String) zzf2);
                                break;
                            }
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 60:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzug.zza(i11, zzvc.zzf(t, j2), zza(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 61:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzc(i11, (zzrb) zzvc.zzf(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 62:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzg(i11, zzd(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 63:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzk(i11, zzd(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 64:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzj(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 65:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzh(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 66:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzh(i11, zzd(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 67:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzf(i11, zze(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 68:
                        if (zza((zzts<T>) t, i11, i8)) {
                            zzb3 = zzrs.zzc(i11, (zzto) zzvc.zzf(t, j2), zza(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    default:
                        i8 += 3;
                        i4 = 267386880;
                }
                i9 += zzb3;
                i8 += 3;
                i4 = 267386880;
            }
            return i9 + zza((zzuw) this.zzq, (Object) t);
        }
        Unsafe unsafe2 = zzb;
        int i13 = 1048575;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < this.zzc.length) {
            int zzc2 = zzc(i14);
            int[] iArr = this.zzc;
            int i17 = iArr[i14];
            int i18 = (zzc2 & 267386880) >>> 20;
            if (i18 <= 17) {
                int i19 = iArr[i14 + 2];
                int i20 = i19 & i5;
                i = i6 << (i19 >>> 20);
                if (i20 != i13) {
                    i16 = unsafe2.getInt(t, i20);
                    i13 = i20;
                }
            } else {
                i = 0;
            }
            long j3 = zzc2 & i5;
            switch (i18) {
                case 0:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzrs.zzb(i17, 0.0d);
                        continue;
                        i14 += 3;
                        i6 = i2;
                        i7 = i3;
                        i5 = ErrorCode.ERR_UNKNOWN;
                    }
                    break;
                case 1:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzrs.zzb(i17, 0.0f);
                        break;
                    }
                    break;
                case 2:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzrs.zzd(i17, unsafe2.getLong(t, j3));
                        i15 += zzd;
                        break;
                    }
                    break;
                case 3:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzrs.zze(i17, unsafe2.getLong(t, j3));
                        i15 += zzd;
                        break;
                    }
                    break;
                case 4:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzrs.zzf(i17, unsafe2.getInt(t, j3));
                        i15 += zzd;
                        break;
                    }
                    break;
                case 5:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        zzd = zzrs.zzg(i17, 0L);
                        i15 += zzd;
                        break;
                    }
                    break;
                case 6:
                    i2 = 1;
                    i3 = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzrs.zzi(i17, 0);
                    }
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 7:
                    if ((i16 & i) != 0) {
                        i2 = 1;
                        i15 += zzrs.zzb(i17, true);
                        i3 = 0;
                        j = 0;
                        i14 += 3;
                        i6 = i2;
                        i7 = i3;
                        i5 = ErrorCode.ERR_UNKNOWN;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 8:
                    if ((i16 & i) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzrb) {
                            zzb2 = zzrs.zzc(i17, (zzrb) object);
                        } else {
                            zzb2 = zzrs.zzb(i17, (String) object);
                        }
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 9:
                    if ((i16 & i) != 0) {
                        zzb2 = zzug.zza(i17, unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 10:
                    if ((i16 & i) != 0) {
                        zzb2 = zzrs.zzc(i17, (zzrb) unsafe2.getObject(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 11:
                    if ((i16 & i) != 0) {
                        zzb2 = zzrs.zzg(i17, unsafe2.getInt(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 12:
                    if ((i16 & i) != 0) {
                        zzb2 = zzrs.zzk(i17, unsafe2.getInt(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 13:
                    if ((i16 & i) != 0) {
                        zzj = zzrs.zzj(i17, 0);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 14:
                    if ((i16 & i) != 0) {
                        zzb2 = zzrs.zzh(i17, 0L);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 15:
                    if ((i16 & i) != 0) {
                        zzb2 = zzrs.zzh(i17, unsafe2.getInt(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 16:
                    if ((i16 & i) != 0) {
                        zzb2 = zzrs.zzf(i17, unsafe2.getLong(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 17:
                    if ((i16 & i) != 0) {
                        zzb2 = zzrs.zzc(i17, (zzto) unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 18:
                    zzb2 = zzug.zzi(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 19:
                    i3 = 0;
                    zzh = zzug.zzh(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 20:
                    i3 = 0;
                    zzh = zzug.zza(i17, (List<Long>) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 21:
                    i3 = 0;
                    zzh = zzug.zzb(i17, (List<Long>) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 22:
                    i3 = 0;
                    zzh = zzug.zze(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 23:
                    i3 = 0;
                    zzh = zzug.zzi(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 24:
                    i3 = 0;
                    zzh = zzug.zzh(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 25:
                    i3 = 0;
                    zzh = zzug.zzj(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 26:
                    zzb2 = zzug.zza(i17, (List) unsafe2.getObject(t, j3));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 27:
                    zzb2 = zzug.zza(i17, (List<?>) unsafe2.getObject(t, j3), zza(i14));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 28:
                    zzb2 = zzug.zzb(i17, (List) unsafe2.getObject(t, j3));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 29:
                    zzb2 = zzug.zzf(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 30:
                    i3 = 0;
                    zzh = zzug.zzd(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 31:
                    i3 = 0;
                    zzh = zzug.zzh(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 32:
                    i3 = 0;
                    zzh = zzug.zzi(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 33:
                    i3 = 0;
                    zzh = zzug.zzg(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 34:
                    i3 = 0;
                    zzh = zzug.zzc(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 35:
                    zzi = zzug.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 36:
                    zzi = zzug.zzh((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 37:
                    zzi = zzug.zza((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 38:
                    zzi = zzug.zzb((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 39:
                    zzi = zzug.zze((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 40:
                    zzi = zzug.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 41:
                    zzi = zzug.zzh((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 42:
                    zzi = zzug.zzj((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 43:
                    zzi = zzug.zzf((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 44:
                    zzi = zzug.zzd((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 45:
                    zzi = zzug.zzh((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 46:
                    zzi = zzug.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 47:
                    zzi = zzug.zzg((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 48:
                    zzi = zzug.zzc((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzrs.zze(i17);
                        zzg = zzrs.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 49:
                    zzb2 = zzug.zzb(i17, (List) unsafe2.getObject(t, j3), zza(i14));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 50:
                    zzb2 = this.zzs.zza(i17, unsafe2.getObject(t, j3), zzb(i14));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 51:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzb(i17, 0.0d);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 52:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzj = zzrs.zzb(i17, 0.0f);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 53:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzd(i17, zze(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 54:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zze(i17, zze(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 55:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzf(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 56:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzg(i17, 0L);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 57:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzj = zzrs.zzi(i17, 0);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 58:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzj = zzrs.zzb(i17, true);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 59:
                    if (zza((zzts<T>) t, i17, i14)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzrb) {
                            zzb2 = zzrs.zzc(i17, (zzrb) object2);
                        } else {
                            zzb2 = zzrs.zzb(i17, (String) object2);
                        }
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 60:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzug.zza(i17, unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 61:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzc(i17, (zzrb) unsafe2.getObject(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 62:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzg(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 63:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzk(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 64:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzj = zzrs.zzj(i17, 0);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 65:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzh(i17, 0L);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 66:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzh(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 67:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzf(i17, zze(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 68:
                    if (zza((zzts<T>) t, i17, i14)) {
                        zzb2 = zzrs.zzc(i17, (zzto) unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                default:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
            }
            i14 += 3;
            i6 = i2;
            i7 = i3;
            i5 = ErrorCode.ERR_UNKNOWN;
        }
        int i21 = i7;
        int zza2 = i15 + zza((zzuw) this.zzq, (Object) t);
        if (this.zzh) {
            zzrx<?> zza3 = this.zzr.zza(t);
            for (int i22 = i21; i22 < zza3.zza.zzc(); i22++) {
                Map.Entry<?, Object> zzb4 = zza3.zza.zzb(i22);
                i21 += zzrx.zza((zzrz) zzb4.getKey(), zzb4.getValue());
            }
            for (Map.Entry<?, Object> entry : zza3.zza.zzd()) {
                i21 += zzrx.zza((zzrz) entry.getKey(), entry.getValue());
            }
            return zza2 + i21;
        }
        return zza2;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + name.length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.libraries.places.internal.zzvc.zze(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.libraries.places.internal.zzvc.zze(r11, r6))) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.libraries.places.internal.zzug.zza(com.google.android.libraries.places.internal.zzvc.zzf(r10, r6), com.google.android.libraries.places.internal.zzvc.zzf(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (com.google.android.libraries.places.internal.zzug.zza(com.google.android.libraries.places.internal.zzvc.zzf(r10, r6), com.google.android.libraries.places.internal.zzvc.zzf(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zzb(r10, r6) == com.google.android.libraries.places.internal.zzvc.zzb(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zza(r10, r6) == com.google.android.libraries.places.internal.zzvc.zza(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a4, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zzb(r10, r6) == com.google.android.libraries.places.internal.zzvc.zzb(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zza(r10, r6) == com.google.android.libraries.places.internal.zzvc.zza(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zza(r10, r6) == com.google.android.libraries.places.internal.zzvc.zza(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zza(r10, r6) == com.google.android.libraries.places.internal.zzvc.zza(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
        if (com.google.android.libraries.places.internal.zzug.zza(com.google.android.libraries.places.internal.zzvc.zzf(r10, r6), com.google.android.libraries.places.internal.zzvc.zzf(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0106, code lost:
        if (com.google.android.libraries.places.internal.zzug.zza(com.google.android.libraries.places.internal.zzvc.zzf(r10, r6), com.google.android.libraries.places.internal.zzvc.zzf(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:
        if (com.google.android.libraries.places.internal.zzug.zza(com.google.android.libraries.places.internal.zzvc.zzf(r10, r6), com.google.android.libraries.places.internal.zzvc.zzf(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012e, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zzc(r10, r6) == com.google.android.libraries.places.internal.zzvc.zzc(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0140, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zza(r10, r6) == com.google.android.libraries.places.internal.zzvc.zza(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0154, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zzb(r10, r6) == com.google.android.libraries.places.internal.zzvc.zzb(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0165, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zza(r10, r6) == com.google.android.libraries.places.internal.zzvc.zza(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0178, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zzb(r10, r6) == com.google.android.libraries.places.internal.zzvc.zzb(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018b, code lost:
        if (com.google.android.libraries.places.internal.zzvc.zzb(r10, r6) == com.google.android.libraries.places.internal.zzvc.zzb(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.libraries.places.internal.zzvc.zzd(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.libraries.places.internal.zzvc.zzd(r11, r6))) goto L85;
     */
    @Override // com.google.android.libraries.places.internal.zzue
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zza(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzts.zza(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final int zza(T t) {
        int i;
        int zza2;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzc = zzc(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzc;
            int i5 = 37;
            switch ((zzc & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    zza2 = zzsg.zza(Double.doubleToLongBits(zzvc.zze(t, j)));
                    i2 = i + zza2;
                    break;
                case 1:
                    i = i2 * 53;
                    zza2 = Float.floatToIntBits(zzvc.zzd(t, j));
                    i2 = i + zza2;
                    break;
                case 2:
                    i = i2 * 53;
                    zza2 = zzsg.zza(zzvc.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 3:
                    i = i2 * 53;
                    zza2 = zzsg.zza(zzvc.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 4:
                    i = i2 * 53;
                    zza2 = zzvc.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 5:
                    i = i2 * 53;
                    zza2 = zzsg.zza(zzvc.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 6:
                    i = i2 * 53;
                    zza2 = zzvc.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 7:
                    i = i2 * 53;
                    zza2 = zzsg.zza(zzvc.zzc(t, j));
                    i2 = i + zza2;
                    break;
                case 8:
                    i = i2 * 53;
                    zza2 = ((String) zzvc.zzf(t, j)).hashCode();
                    i2 = i + zza2;
                    break;
                case 9:
                    Object zzf = zzvc.zzf(t, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zza2 = zzvc.zzf(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 11:
                    i = i2 * 53;
                    zza2 = zzvc.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 12:
                    i = i2 * 53;
                    zza2 = zzvc.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 13:
                    i = i2 * 53;
                    zza2 = zzvc.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 14:
                    i = i2 * 53;
                    zza2 = zzsg.zza(zzvc.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 15:
                    i = i2 * 53;
                    zza2 = zzvc.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 16:
                    i = i2 * 53;
                    zza2 = zzsg.zza(zzvc.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 17:
                    Object zzf2 = zzvc.zzf(t, j);
                    if (zzf2 != null) {
                        i5 = zzf2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    zza2 = zzvc.zzf(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 50:
                    i = i2 * 53;
                    zza2 = zzvc.zzf(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 51:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzsg.zza(Double.doubleToLongBits(zzb(t, j)));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = Float.floatToIntBits(zzc(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzsg.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzsg.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzsg.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzsg.zza(zzf(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = ((String) zzvc.zzf(t, j)).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzvc.zzf(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzvc.zzf(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzsg.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzsg.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzts<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzvc.zzf(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzq.zza(t).hashCode();
        return this.zzh ? (hashCode * 53) + this.zzr.zza(t).hashCode() : hashCode;
    }

    private final void zza(T t, T t2, int i) {
        long zzc = zzc(i) & ErrorCode.ERR_UNKNOWN;
        if (zza((zzts<T>) t2, i)) {
            Object zzf = zzvc.zzf(t, zzc);
            Object zzf2 = zzvc.zzf(t2, zzc);
            if (zzf != null && zzf2 != null) {
                zzvc.zza(t, zzc, zzsg.zza(zzf, zzf2));
                zzb((zzts<T>) t, i);
            } else if (zzf2 != null) {
                zzvc.zza(t, zzc, zzf2);
                zzb((zzts<T>) t, i);
            }
        }
    }

    private static <UT, UB> int zza(zzuw<UT, UB> zzuwVar, T t) {
        return zzuwVar.zzd(zzuwVar.zza(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzvc.zzf(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0a2a  */
    @Override // com.google.android.libraries.places.internal.zzue
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r14, com.google.android.libraries.places.internal.zzvq r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2916
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzts.zza(java.lang.Object, com.google.android.libraries.places.internal.zzvq):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x048f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void zzb(T r18, com.google.android.libraries.places.internal.zzvq r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzts.zzb(java.lang.Object, com.google.android.libraries.places.internal.zzvq):void");
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private static <T> double zzb(T t, long j) {
        return ((Double) zzvc.zzf(t, j)).doubleValue();
    }

    private final void zzb(T t, int i) {
        int zzd = zzd(i);
        long j = 1048575 & zzd;
        if (j == 1048575) {
            return;
        }
        zzvc.zza((Object) t, j, (1 << (zzd >>> 20)) | zzvc.zza(t, j));
    }

    private final void zzb(T t, int i, int i2) {
        zzvc.zza((Object) t, zzd(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    private final <K, V> void zza(zzvq zzvqVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzvqVar.zza(i, this.zzs.zza(zzb(i2)), this.zzs.zzb(obj));
        }
    }

    private static <UT, UB> void zza(zzuw<UT, UB> zzuwVar, T t, zzvq zzvqVar) throws IOException {
        zzuwVar.zza((zzuw<UT, UB>) zzuwVar.zza(t), zzvqVar);
    }

    private final zzue zza(int i) {
        int i2 = (i / 3) << 1;
        zzue zzueVar = (zzue) this.zzd[i2];
        if (zzueVar != null) {
            return zzueVar;
        }
        zzue<T> zza2 = zzud.zza().zza((Class) ((Class) this.zzd[i2 + 1]));
        this.zzd[i2] = zza2;
        return zza2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzue zzueVar) {
        return zzueVar.zzd(zzvc.zzf(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    private static void zza(int i, Object obj, zzvq zzvqVar) throws IOException {
        if (obj instanceof String) {
            zzvqVar.zza(i, (String) obj);
        } else {
            zzvqVar.zza(i, (zzrb) obj);
        }
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zza((zzts<T>) t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zza(T t, int i) {
        int zzd = zzd(i);
        long j = zzd & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (zzvc.zza(t, j) & (1 << (zzd >>> 20))) != 0;
        }
        int zzc = zzc(i);
        long j2 = zzc & ErrorCode.ERR_UNKNOWN;
        switch ((zzc & 267386880) >>> 20) {
            case 0:
                return zzvc.zze(t, j2) != 0.0d;
            case 1:
                return zzvc.zzd(t, j2) != 0.0f;
            case 2:
                return zzvc.zzb(t, j2) != 0;
            case 3:
                return zzvc.zzb(t, j2) != 0;
            case 4:
                return zzvc.zza(t, j2) != 0;
            case 5:
                return zzvc.zzb(t, j2) != 0;
            case 6:
                return zzvc.zza(t, j2) != 0;
            case 7:
                return zzvc.zzc(t, j2);
            case 8:
                Object zzf = zzvc.zzf(t, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzrb) {
                    return !zzrb.zza.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzvc.zzf(t, j2) != null;
            case 10:
                return !zzrb.zza.equals(zzvc.zzf(t, j2));
            case 11:
                return zzvc.zza(t, j2) != 0;
            case 12:
                return zzvc.zza(t, j2) != 0;
            case 13:
                return zzvc.zza(t, j2) != 0;
            case 14:
                return zzvc.zzb(t, j2) != 0;
            case 15:
                return zzvc.zza(t, j2) != 0;
            case 16:
                return zzvc.zzb(t, j2) != 0;
            case 17:
                return zzvc.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzvc.zza(t, (long) (zzd(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }
}
