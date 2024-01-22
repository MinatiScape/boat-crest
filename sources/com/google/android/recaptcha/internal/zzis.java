package com.google.android.recaptcha.internal;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzis<T> implements zzjc<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzkg.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzip zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzid zzm;
    private final zzjw zzn;
    private final zzgr zzo;
    private final int zzp;
    private final zziv zzq;
    private final zzik zzr;

    private zzis(int[] iArr, Object[] objArr, int i, int i2, zzip zzipVar, int i3, boolean z, int[] iArr2, int i4, int i5, zziv zzivVar, zzid zzidVar, zzjw zzjwVar, zzgr zzgrVar, zzik zzikVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzipVar instanceof zzhf;
        this.zzp = i3;
        boolean z2 = false;
        if (zzgrVar != null && zzgrVar.zzj(zzipVar)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzj = iArr2;
        this.zzk = i4;
        this.zzl = i5;
        this.zzq = zzivVar;
        this.zzm = zzidVar;
        this.zzn = zzjwVar;
        this.zzo = zzgrVar;
        this.zzg = zzipVar;
        this.zzr = zzikVar;
    }

    private static long zzA(Object obj, long j) {
        return ((Long) zzkg.zzf(obj, j)).longValue();
    }

    private final zzhj zzB(int i) {
        int i2 = i / 3;
        return (zzhj) this.zzd[i2 + i2 + 1];
    }

    private final zzjc zzC(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzjc zzjcVar = (zzjc) this.zzd[i3];
        if (zzjcVar != null) {
            return zzjcVar;
        }
        zzjc zzb2 = zziy.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzD(Object obj, int i, Object obj2, zzjw zzjwVar, Object obj3) {
        int i2 = this.zzc[i];
        Object zzf = zzkg.zzf(obj, zzz(i) & ErrorCode.ERR_UNKNOWN);
        if (zzf == null || zzB(i) == null) {
            return obj2;
        }
        zzij zzijVar = (zzij) zzf;
        zzii zziiVar = (zzii) zzE(i);
        throw null;
    }

    private final Object zzE(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzF(Object obj, int i) {
        zzjc zzC = zzC(i);
        int zzz = zzz(i) & ErrorCode.ERR_UNKNOWN;
        if (!zzT(obj, i)) {
            return zzC.zze();
        }
        Object object = zzb.getObject(obj, zzz);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzC.zze();
        if (object != null) {
            zzC.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzG(Object obj, int i, int i2) {
        zzjc zzC = zzC(i2);
        if (!zzX(obj, i, i2)) {
            return zzC.zze();
        }
        Object object = zzb.getObject(obj, zzz(i2) & ErrorCode.ERR_UNKNOWN);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzC.zze();
        if (object != null) {
            zzC.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzH(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzI(Object obj) {
        if (!zzW(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    private final void zzJ(Object obj, Object obj2, int i) {
        if (zzT(obj2, i)) {
            int zzz = zzz(i) & ErrorCode.ERR_UNKNOWN;
            Unsafe unsafe = zzb;
            long j = zzz;
            Object object = unsafe.getObject(obj2, j);
            if (object != null) {
                zzjc zzC = zzC(i);
                if (!zzT(obj, i)) {
                    if (!zzW(object)) {
                        unsafe.putObject(obj, j, object);
                    } else {
                        Object zze = zzC.zze();
                        zzC.zzg(zze, object);
                        unsafe.putObject(obj, j, zze);
                    }
                    zzM(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, j);
                if (!zzW(object2)) {
                    Object zze2 = zzC.zze();
                    zzC.zzg(zze2, object2);
                    unsafe.putObject(obj, j, zze2);
                    object2 = zze2;
                }
                zzC.zzg(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
        }
    }

    private final void zzK(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzX(obj2, i2, i)) {
            int zzz = zzz(i) & ErrorCode.ERR_UNKNOWN;
            Unsafe unsafe = zzb;
            long j = zzz;
            Object object = unsafe.getObject(obj2, j);
            if (object != null) {
                zzjc zzC = zzC(i);
                if (!zzX(obj, i2, i)) {
                    if (!zzW(object)) {
                        unsafe.putObject(obj, j, object);
                    } else {
                        Object zze = zzC.zze();
                        zzC.zzg(zze, object);
                        unsafe.putObject(obj, j, zze);
                    }
                    zzN(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, j);
                if (!zzW(object2)) {
                    Object zze2 = zzC.zze();
                    zzC.zzg(zze2, object2);
                    unsafe.putObject(obj, j, zze2);
                    object2 = zze2;
                }
                zzC.zzg(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
        }
    }

    private final void zzL(Object obj, int i, zzjb zzjbVar) throws IOException {
        if (zzS(i)) {
            zzkg.zzs(obj, i & ErrorCode.ERR_UNKNOWN, zzjbVar.zzs());
        } else if (this.zzi) {
            zzkg.zzs(obj, i & ErrorCode.ERR_UNKNOWN, zzjbVar.zzr());
        } else {
            zzkg.zzs(obj, i & ErrorCode.ERR_UNKNOWN, zzjbVar.zzp());
        }
    }

    private final void zzM(Object obj, int i) {
        int zzw = zzw(i);
        long j = 1048575 & zzw;
        if (j == 1048575) {
            return;
        }
        zzkg.zzq(obj, j, (1 << (zzw >>> 20)) | zzkg.zzc(obj, j));
    }

    private final void zzN(Object obj, int i, int i2) {
        zzkg.zzq(obj, zzw(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    private final void zzO(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzz(i) & ErrorCode.ERR_UNKNOWN, obj2);
        zzM(obj, i);
    }

    private final void zzP(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzz(i2) & ErrorCode.ERR_UNKNOWN, obj2);
        zzN(obj, i, i2);
    }

    private final void zzQ(zzko zzkoVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        zzii zziiVar = (zzii) zzE(i2);
        throw null;
    }

    private final boolean zzR(Object obj, Object obj2, int i) {
        return zzT(obj, i) == zzT(obj2, i);
    }

    private static boolean zzS(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    private final boolean zzT(Object obj, int i) {
        int zzw = zzw(i);
        long j = zzw & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (zzkg.zzc(obj, j) & (1 << (zzw >>> 20))) != 0;
        }
        int zzz = zzz(i);
        long j2 = zzz & ErrorCode.ERR_UNKNOWN;
        switch (zzy(zzz)) {
            case 0:
                return Double.doubleToRawLongBits(zzkg.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzkg.zzb(obj, j2)) != 0;
            case 2:
                return zzkg.zzd(obj, j2) != 0;
            case 3:
                return zzkg.zzd(obj, j2) != 0;
            case 4:
                return zzkg.zzc(obj, j2) != 0;
            case 5:
                return zzkg.zzd(obj, j2) != 0;
            case 6:
                return zzkg.zzc(obj, j2) != 0;
            case 7:
                return zzkg.zzw(obj, j2);
            case 8:
                Object zzf = zzkg.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzfi) {
                    return !zzfi.zzb.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzkg.zzf(obj, j2) != null;
            case 10:
                return !zzfi.zzb.equals(zzkg.zzf(obj, j2));
            case 11:
                return zzkg.zzc(obj, j2) != 0;
            case 12:
                return zzkg.zzc(obj, j2) != 0;
            case 13:
                return zzkg.zzc(obj, j2) != 0;
            case 14:
                return zzkg.zzd(obj, j2) != 0;
            case 15:
                return zzkg.zzc(obj, j2) != 0;
            case 16:
                return zzkg.zzd(obj, j2) != 0;
            case 17:
                return zzkg.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzU(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzT(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzV(Object obj, int i, zzjc zzjcVar) {
        return zzjcVar.zzl(zzkg.zzf(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    private static boolean zzW(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzhf) {
            return ((zzhf) obj).zzF();
        }
        return true;
    }

    private final boolean zzX(Object obj, int i, int i2) {
        return zzkg.zzc(obj, (long) (zzw(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    private static boolean zzY(Object obj, long j) {
        return ((Boolean) zzkg.zzf(obj, j)).booleanValue();
    }

    private static final void zzZ(int i, Object obj, zzko zzkoVar) throws IOException {
        if (obj instanceof String) {
            zzkoVar.zzG(i, (String) obj);
        } else {
            zzkoVar.zzd(i, (zzfi) obj);
        }
    }

    public static zzjx zzd(Object obj) {
        zzhf zzhfVar = (zzhf) obj;
        zzjx zzjxVar = zzhfVar.zzc;
        if (zzjxVar == zzjx.zzc()) {
            zzjx zzf = zzjx.zzf();
            zzhfVar.zzc = zzf;
            return zzf;
        }
        return zzjxVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x026d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.recaptcha.internal.zzis zzm(java.lang.Class r30, com.google.android.recaptcha.internal.zzim r31, com.google.android.recaptcha.internal.zziv r32, com.google.android.recaptcha.internal.zzid r33, com.google.android.recaptcha.internal.zzjw r34, com.google.android.recaptcha.internal.zzgr r35, com.google.android.recaptcha.internal.zzik r36) {
        /*
            Method dump skipped, instructions count: 996
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzis.zzm(java.lang.Class, com.google.android.recaptcha.internal.zzim, com.google.android.recaptcha.internal.zziv, com.google.android.recaptcha.internal.zzid, com.google.android.recaptcha.internal.zzjw, com.google.android.recaptcha.internal.zzgr, com.google.android.recaptcha.internal.zzik):com.google.android.recaptcha.internal.zzis");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzkg.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzkg.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzp(Object obj) {
        int i;
        int zzy;
        int zzy2;
        int zzz;
        int zzy3;
        int zzy4;
        int zzy5;
        int zzy6;
        int zzt;
        boolean z;
        int zzc;
        int zzh;
        int zzy7;
        int zzy8;
        int i2;
        int zzy9;
        int zzy10;
        int zzy11;
        int zzy12;
        Unsafe unsafe = zzb;
        int i3 = ErrorCode.ERR_UNKNOWN;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < this.zzc.length) {
            int zzz2 = zzz(i5);
            int[] iArr = this.zzc;
            int i8 = iArr[i5];
            int zzy13 = zzy(zzz2);
            if (zzy13 <= 17) {
                int i9 = iArr[i5 + 2];
                int i10 = i9 & i3;
                int i11 = i9 >>> 20;
                if (i10 != i4) {
                    i7 = unsafe.getInt(obj, i10);
                    i4 = i10;
                }
                i = 1 << i11;
            } else {
                i = 0;
            }
            long j = zzz2 & i3;
            switch (zzy13) {
                case 0:
                    if ((i7 & i) != 0) {
                        zzy = zzft.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if ((i7 & i) != 0) {
                        zzy2 = zzft.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if ((i7 & i) != 0) {
                        zzz = zzft.zzz(unsafe.getLong(obj, j));
                        zzy3 = zzft.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ((i7 & i) != 0) {
                        zzz = zzft.zzz(unsafe.getLong(obj, j));
                        zzy3 = zzft.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if ((i7 & i) != 0) {
                        zzz = zzft.zzu(unsafe.getInt(obj, j));
                        zzy3 = zzft.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i7 & i) != 0) {
                        zzy = zzft.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if ((i7 & i) != 0) {
                        zzy2 = zzft.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if ((i7 & i) != 0) {
                        zzy4 = zzft.zzy(i8 << 3) + 1;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i7 & i) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzfi) {
                            int i12 = zzft.zzb;
                            int zzd = ((zzfi) object).zzd();
                            zzy5 = zzft.zzy(zzd) + zzd;
                            zzy6 = zzft.zzy(i8 << 3);
                            zzy4 = zzy6 + zzy5;
                            i6 += zzy4;
                            break;
                        } else {
                            zzz = zzft.zzx((String) object);
                            zzy3 = zzft.zzy(i8 << 3);
                            i6 += zzy3 + zzz;
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if ((i7 & i) != 0) {
                        zzy4 = zzje.zzn(i8, unsafe.getObject(obj, j), zzC(i5));
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i7 & i) != 0) {
                        int i13 = zzft.zzb;
                        int zzd2 = ((zzfi) unsafe.getObject(obj, j)).zzd();
                        zzy5 = zzft.zzy(zzd2) + zzd2;
                        zzy6 = zzft.zzy(i8 << 3);
                        zzy4 = zzy6 + zzy5;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i7 & i) != 0) {
                        zzz = zzft.zzy(unsafe.getInt(obj, j));
                        zzy3 = zzft.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i7 & i) != 0) {
                        zzz = zzft.zzu(unsafe.getInt(obj, j));
                        zzy3 = zzft.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if ((i7 & i) != 0) {
                        zzy2 = zzft.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if ((i7 & i) != 0) {
                        zzy = zzft.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if ((i7 & i) != 0) {
                        int i14 = unsafe.getInt(obj, j);
                        zzy3 = zzft.zzy(i8 << 3);
                        zzz = zzft.zzy((i14 >> 31) ^ (i14 + i14));
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i & i7) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        i6 += zzft.zzy(i8 << 3) + zzft.zzz((j2 >> 63) ^ (j2 + j2));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i7 & i) != 0) {
                        zzy4 = zzft.zzt(i8, (zzip) unsafe.getObject(obj, j), zzC(i5));
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzy4 = zzje.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 19:
                    zzy4 = zzje.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 20:
                    zzy4 = zzje.zzl(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 21:
                    zzy4 = zzje.zzw(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 22:
                    zzy4 = zzje.zzj(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 23:
                    zzy4 = zzje.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 24:
                    zzy4 = zzje.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 25:
                    zzy4 = zzje.zza(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 26:
                    zzt = zzje.zzt(i8, (List) unsafe.getObject(obj, j));
                    i6 += zzt;
                    break;
                case 27:
                    zzt = zzje.zzo(i8, (List) unsafe.getObject(obj, j), zzC(i5));
                    i6 += zzt;
                    break;
                case 28:
                    zzt = zzje.zzb(i8, (List) unsafe.getObject(obj, j));
                    i6 += zzt;
                    break;
                case 29:
                    zzt = zzje.zzu(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzt;
                    break;
                case 30:
                    z = false;
                    zzc = zzje.zzc(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 31:
                    z = false;
                    zzc = zzje.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 32:
                    z = false;
                    zzc = zzje.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 33:
                    z = false;
                    zzc = zzje.zzp(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 34:
                    z = false;
                    zzc = zzje.zzr(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 35:
                    zzh = zzje.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 36:
                    zzh = zzje.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 37:
                    zzh = zzje.zzm((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 38:
                    zzh = zzje.zzx((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 39:
                    zzh = zzje.zzk((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 40:
                    zzh = zzje.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 41:
                    zzh = zzje.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 42:
                    int i15 = zzje.zza;
                    zzh = ((List) unsafe.getObject(obj, j)).size();
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 43:
                    zzh = zzje.zzv((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 44:
                    zzh = zzje.zzd((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 45:
                    zzh = zzje.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 46:
                    zzh = zzje.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 47:
                    zzh = zzje.zzq((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 48:
                    zzh = zzje.zzs((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzft.zzy(zzh);
                        zzy8 = zzft.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 49:
                    zzt = zzje.zzi(i8, (List) unsafe.getObject(obj, j), zzC(i5));
                    i6 += zzt;
                    break;
                case 50:
                    zzik.zza(i8, unsafe.getObject(obj, j), zzE(i5));
                    break;
                case 51:
                    if (zzX(obj, i8, i5)) {
                        zzy9 = zzft.zzy(i8 << 3);
                        zzt = zzy9 + 8;
                        i6 += zzt;
                    }
                    break;
                case 52:
                    if (zzX(obj, i8, i5)) {
                        zzy10 = zzft.zzy(i8 << 3);
                        zzt = zzy10 + 4;
                        i6 += zzt;
                    }
                    break;
                case 53:
                    if (zzX(obj, i8, i5)) {
                        zzh = zzft.zzz(zzA(obj, j));
                        i2 = zzft.zzy(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 54:
                    if (zzX(obj, i8, i5)) {
                        zzh = zzft.zzz(zzA(obj, j));
                        i2 = zzft.zzy(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 55:
                    if (zzX(obj, i8, i5)) {
                        zzh = zzft.zzu(zzq(obj, j));
                        i2 = zzft.zzy(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 56:
                    if (zzX(obj, i8, i5)) {
                        zzy9 = zzft.zzy(i8 << 3);
                        zzt = zzy9 + 8;
                        i6 += zzt;
                    }
                    break;
                case 57:
                    if (zzX(obj, i8, i5)) {
                        zzy10 = zzft.zzy(i8 << 3);
                        zzt = zzy10 + 4;
                        i6 += zzt;
                    }
                    break;
                case 58:
                    if (zzX(obj, i8, i5)) {
                        zzt = zzft.zzy(i8 << 3) + 1;
                        i6 += zzt;
                    }
                    break;
                case 59:
                    if (zzX(obj, i8, i5)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzfi) {
                            int i16 = zzft.zzb;
                            int zzd3 = ((zzfi) object2).zzd();
                            zzy11 = zzft.zzy(zzd3) + zzd3;
                            zzy12 = zzft.zzy(i8 << 3);
                            zzt = zzy12 + zzy11;
                            i6 += zzt;
                        } else {
                            zzh = zzft.zzx((String) object2);
                            i2 = zzft.zzy(i8 << 3);
                            i6 += i2 + zzh;
                        }
                    }
                    break;
                case 60:
                    if (zzX(obj, i8, i5)) {
                        zzt = zzje.zzn(i8, unsafe.getObject(obj, j), zzC(i5));
                        i6 += zzt;
                    }
                    break;
                case 61:
                    if (zzX(obj, i8, i5)) {
                        int i17 = zzft.zzb;
                        int zzd4 = ((zzfi) unsafe.getObject(obj, j)).zzd();
                        zzy11 = zzft.zzy(zzd4) + zzd4;
                        zzy12 = zzft.zzy(i8 << 3);
                        zzt = zzy12 + zzy11;
                        i6 += zzt;
                    }
                    break;
                case 62:
                    if (zzX(obj, i8, i5)) {
                        zzh = zzft.zzy(zzq(obj, j));
                        i2 = zzft.zzy(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 63:
                    if (zzX(obj, i8, i5)) {
                        zzh = zzft.zzu(zzq(obj, j));
                        i2 = zzft.zzy(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 64:
                    if (zzX(obj, i8, i5)) {
                        zzy10 = zzft.zzy(i8 << 3);
                        zzt = zzy10 + 4;
                        i6 += zzt;
                    }
                    break;
                case 65:
                    if (zzX(obj, i8, i5)) {
                        zzy9 = zzft.zzy(i8 << 3);
                        zzt = zzy9 + 8;
                        i6 += zzt;
                    }
                    break;
                case 66:
                    if (zzX(obj, i8, i5)) {
                        int zzq = zzq(obj, j);
                        i2 = zzft.zzy(i8 << 3);
                        zzh = zzft.zzy((zzq >> 31) ^ (zzq + zzq));
                        i6 += i2 + zzh;
                    }
                    break;
                case 67:
                    if (zzX(obj, i8, i5)) {
                        long zzA = zzA(obj, j);
                        i6 += zzft.zzy(i8 << 3) + zzft.zzz((zzA >> 63) ^ (zzA + zzA));
                    }
                    break;
                case 68:
                    if (zzX(obj, i8, i5)) {
                        zzt = zzft.zzt(i8, (zzip) unsafe.getObject(obj, j), zzC(i5));
                        i6 += zzt;
                    }
                    break;
            }
            i5 += 3;
            i3 = ErrorCode.ERR_UNKNOWN;
        }
        int i18 = 0;
        zzjw zzjwVar = this.zzn;
        int zza2 = i6 + zzjwVar.zza(zzjwVar.zzd(obj));
        if (this.zzh) {
            zzgv zzb2 = this.zzo.zzb(obj);
            for (int i19 = 0; i19 < zzb2.zza.zzb(); i19++) {
                Map.Entry zzg = zzb2.zza.zzg(i19);
                i18 += zzgv.zza((zzgu) zzg.getKey(), zzg.getValue());
            }
            for (Map.Entry entry : zzb2.zza.zzc()) {
                i18 += zzgv.zza((zzgu) entry.getKey(), entry.getValue());
            }
            return zza2 + i18;
        }
        return zza2;
    }

    private static int zzq(Object obj, long j) {
        return ((Integer) zzkg.zzf(obj, j)).intValue();
    }

    private final int zzr(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzev zzevVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzE = zzE(i3);
        Object object = unsafe.getObject(obj, j);
        if (zzik.zzb(object)) {
            zzij zzb2 = zzij.zza().zzb();
            zzik.zzc(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzii zziiVar = (zzii) zzE;
        throw null;
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzev zzevVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzew.zzq(bArr, i))));
                    int i9 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i9;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzew.zzb(bArr, i))));
                    int i10 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i10;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int zzm = zzew.zzm(bArr, i, zzevVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzevVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int zzj = zzew.zzj(bArr, i, zzevVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzevVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzew.zzq(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i11;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzew.zzb(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i12;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzew.zzm(bArr, i, zzevVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzevVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzew.zzj(bArr, i, zzevVar);
                    int i13 = zzevVar.zza;
                    if (i13 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !zzkl.zzf(bArr, zzj2, zzj2 + i13)) {
                        throw zzhp.zzd();
                    } else {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i13, zzhn.zzb));
                        zzj2 += i13;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object zzG = zzG(obj, i4, i8);
                    int zzo = zzew.zzo(zzG, zzC(i8), bArr, i, i2, zzevVar);
                    zzP(obj, i4, i8, zzG);
                    return zzo;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int zza2 = zzew.zza(bArr, i, zzevVar);
                    unsafe.putObject(obj, j, zzevVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int zzj3 = zzew.zzj(bArr, i, zzevVar);
                    int i14 = zzevVar.zza;
                    zzhj zzB = zzB(i8);
                    if (zzB != null && !zzB.zza(i14)) {
                        zzd(obj).zzj(i3, Long.valueOf(i14));
                    } else {
                        unsafe.putObject(obj, j, Integer.valueOf(i14));
                        unsafe.putInt(obj, j2, i4);
                    }
                    return zzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int zzj4 = zzew.zzj(bArr, i, zzevVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzfo.zzF(zzevVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int zzm3 = zzew.zzm(bArr, i, zzevVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzfo.zzG(zzevVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object zzG2 = zzG(obj, i4, i8);
                    int zzn = zzew.zzn(zzG2, zzC(i8), bArr, i, i2, (i3 & (-8)) | 4, zzevVar);
                    zzP(obj, i4, i8, zzG2);
                    return zzn;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01c6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:112:0x0211 -> B:113:0x0212). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0148 -> B:61:0x0149). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:92:0x01c3 -> B:93:0x01c4). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzt(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.recaptcha.internal.zzev r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1160
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzis.zzt(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.recaptcha.internal.zzev):int");
    }

    private final int zzu(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzx(i, 0);
    }

    private final int zzv(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzx(i, i2);
    }

    private final int zzw(int i) {
        return this.zzc[i + 2];
    }

    private final int zzx(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzy(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzz(int i) {
        return this.zzc[i + 1];
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final int zza(Object obj) {
        int zzy;
        int zzy2;
        int zzz;
        int zzy3;
        int zzy4;
        int zzy5;
        int zzy6;
        int zzn;
        int zzy7;
        int zzz2;
        int zzy8;
        int zzy9;
        zzkm zzkmVar = zzkm.zza;
        if (this.zzp - 1 != 0) {
            Unsafe unsafe = zzb;
            int i = 0;
            for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
                int zzz3 = zzz(i2);
                int zzy10 = zzy(zzz3);
                int i3 = this.zzc[i2];
                int i4 = zzz3 & ErrorCode.ERR_UNKNOWN;
                if (zzy10 >= zzgw.zzJ.zza() && zzy10 <= zzgw.zzW.zza()) {
                    int i5 = this.zzc[i2 + 2];
                }
                long j = i4;
                switch (zzy10) {
                    case 0:
                        if (zzT(obj, i2)) {
                            zzy = zzft.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzT(obj, i2)) {
                            zzy2 = zzft.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzT(obj, i2)) {
                            zzz = zzft.zzz(zzkg.zzd(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzT(obj, i2)) {
                            zzz = zzft.zzz(zzkg.zzd(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzT(obj, i2)) {
                            zzz = zzft.zzu(zzkg.zzc(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzT(obj, i2)) {
                            zzy = zzft.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzT(obj, i2)) {
                            zzy2 = zzft.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzT(obj, i2)) {
                            zzy4 = zzft.zzy(i3 << 3);
                            zzn = zzy4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzT(obj, i2)) {
                            Object zzf = zzkg.zzf(obj, j);
                            if (zzf instanceof zzfi) {
                                int i6 = i3 << 3;
                                int i7 = zzft.zzb;
                                int zzd = ((zzfi) zzf).zzd();
                                zzy5 = zzft.zzy(zzd) + zzd;
                                zzy6 = zzft.zzy(i6);
                                zzn = zzy6 + zzy5;
                                i += zzn;
                                break;
                            } else {
                                zzz = zzft.zzx((String) zzf);
                                zzy3 = zzft.zzy(i3 << 3);
                                i += zzy3 + zzz;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zzT(obj, i2)) {
                            zzn = zzje.zzn(i3, zzkg.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzT(obj, i2)) {
                            int i8 = i3 << 3;
                            int i9 = zzft.zzb;
                            int zzd2 = ((zzfi) zzkg.zzf(obj, j)).zzd();
                            zzy5 = zzft.zzy(zzd2) + zzd2;
                            zzy6 = zzft.zzy(i8);
                            zzn = zzy6 + zzy5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzT(obj, i2)) {
                            zzz = zzft.zzy(zzkg.zzc(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzT(obj, i2)) {
                            zzz = zzft.zzu(zzkg.zzc(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzT(obj, i2)) {
                            zzy2 = zzft.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzT(obj, i2)) {
                            zzy = zzft.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzT(obj, i2)) {
                            int zzc = zzkg.zzc(obj, j);
                            zzy3 = zzft.zzy(i3 << 3);
                            zzz = zzft.zzy((zzc >> 31) ^ (zzc + zzc));
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzT(obj, i2)) {
                            long zzd3 = zzkg.zzd(obj, j);
                            zzy7 = zzft.zzy(i3 << 3);
                            zzz2 = zzft.zzz((zzd3 + zzd3) ^ (zzd3 >> 63));
                            zzn = zzy7 + zzz2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzT(obj, i2)) {
                            zzn = zzft.zzt(i3, (zzip) zzkg.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzn = zzje.zzg(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 19:
                        zzn = zzje.zze(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 20:
                        zzn = zzje.zzl(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 21:
                        zzn = zzje.zzw(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 22:
                        zzn = zzje.zzj(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 23:
                        zzn = zzje.zzg(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 24:
                        zzn = zzje.zze(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 25:
                        zzn = zzje.zza(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 26:
                        zzn = zzje.zzt(i3, (List) zzkg.zzf(obj, j));
                        i += zzn;
                        break;
                    case 27:
                        zzn = zzje.zzo(i3, (List) zzkg.zzf(obj, j), zzC(i2));
                        i += zzn;
                        break;
                    case 28:
                        zzn = zzje.zzb(i3, (List) zzkg.zzf(obj, j));
                        i += zzn;
                        break;
                    case 29:
                        zzn = zzje.zzu(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 30:
                        zzn = zzje.zzc(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 31:
                        zzn = zzje.zze(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 32:
                        zzn = zzje.zzg(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 33:
                        zzn = zzje.zzp(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 34:
                        zzn = zzje.zzr(i3, (List) zzkg.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 35:
                        zzz = zzje.zzh((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i10 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i10);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        zzz = zzje.zzf((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i11 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i11);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        zzz = zzje.zzm((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i12 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i12);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        zzz = zzje.zzx((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i13 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i13);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        zzz = zzje.zzk((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i14 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i14);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        zzz = zzje.zzh((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i15 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i15);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        zzz = zzje.zzf((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i16 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i16);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int i17 = zzje.zza;
                        zzz = ((List) unsafe.getObject(obj, j)).size();
                        if (zzz > 0) {
                            int i18 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i18);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        zzz = zzje.zzv((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i19 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i19);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        zzz = zzje.zzd((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i20 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i20);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        zzz = zzje.zzf((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i21 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i21);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        zzz = zzje.zzh((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i22 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i22);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        zzz = zzje.zzq((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i23 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i23);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        zzz = zzje.zzs((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i24 = i3 << 3;
                            zzy8 = zzft.zzy(zzz);
                            zzy9 = zzft.zzy(i24);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        zzn = zzje.zzi(i3, (List) zzkg.zzf(obj, j), zzC(i2));
                        i += zzn;
                        break;
                    case 50:
                        zzik.zza(i3, zzkg.zzf(obj, j), zzE(i2));
                        break;
                    case 51:
                        if (zzX(obj, i3, i2)) {
                            zzy = zzft.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzX(obj, i3, i2)) {
                            zzy2 = zzft.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzft.zzz(zzA(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzft.zzz(zzA(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzft.zzu(zzq(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzX(obj, i3, i2)) {
                            zzy = zzft.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzX(obj, i3, i2)) {
                            zzy2 = zzft.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzX(obj, i3, i2)) {
                            zzy4 = zzft.zzy(i3 << 3);
                            zzn = zzy4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzX(obj, i3, i2)) {
                            Object zzf2 = zzkg.zzf(obj, j);
                            if (zzf2 instanceof zzfi) {
                                int i25 = i3 << 3;
                                int i26 = zzft.zzb;
                                int zzd4 = ((zzfi) zzf2).zzd();
                                zzy5 = zzft.zzy(zzd4) + zzd4;
                                zzy6 = zzft.zzy(i25);
                                zzn = zzy6 + zzy5;
                                i += zzn;
                                break;
                            } else {
                                zzz = zzft.zzx((String) zzf2);
                                zzy3 = zzft.zzy(i3 << 3);
                                i += zzy3 + zzz;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zzX(obj, i3, i2)) {
                            zzn = zzje.zzn(i3, zzkg.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzX(obj, i3, i2)) {
                            int i27 = i3 << 3;
                            int i28 = zzft.zzb;
                            int zzd5 = ((zzfi) zzkg.zzf(obj, j)).zzd();
                            zzy5 = zzft.zzy(zzd5) + zzd5;
                            zzy6 = zzft.zzy(i27);
                            zzn = zzy6 + zzy5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzft.zzy(zzq(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzft.zzu(zzq(obj, j));
                            zzy3 = zzft.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzX(obj, i3, i2)) {
                            zzy2 = zzft.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzX(obj, i3, i2)) {
                            zzy = zzft.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzX(obj, i3, i2)) {
                            int zzq = zzq(obj, j);
                            zzy3 = zzft.zzy(i3 << 3);
                            zzz = zzft.zzy((zzq >> 31) ^ (zzq + zzq));
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzX(obj, i3, i2)) {
                            long zzA = zzA(obj, j);
                            zzy7 = zzft.zzy(i3 << 3);
                            zzz2 = zzft.zzz((zzA + zzA) ^ (zzA >> 63));
                            zzn = zzy7 + zzz2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzX(obj, i3, i2)) {
                            zzn = zzft.zzt(i3, (zzip) zzkg.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzjw zzjwVar = this.zzn;
            return i + zzjwVar.zza(zzjwVar.zzd(obj));
        }
        return zzp(obj);
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final int zzb(Object obj) {
        int i;
        long doubleToLongBits;
        int floatToIntBits;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzz = zzz(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzz;
            int i5 = 37;
            switch (zzy(zzz)) {
                case 0:
                    i = i2 * 53;
                    doubleToLongBits = Double.doubleToLongBits(zzkg.zza(obj, j));
                    byte[] bArr = zzhn.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 1:
                    i = i2 * 53;
                    floatToIntBits = Float.floatToIntBits(zzkg.zzb(obj, j));
                    i2 = i + floatToIntBits;
                    break;
                case 2:
                    i = i2 * 53;
                    doubleToLongBits = zzkg.zzd(obj, j);
                    byte[] bArr2 = zzhn.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 3:
                    i = i2 * 53;
                    doubleToLongBits = zzkg.zzd(obj, j);
                    byte[] bArr3 = zzhn.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 4:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 5:
                    i = i2 * 53;
                    doubleToLongBits = zzkg.zzd(obj, j);
                    byte[] bArr4 = zzhn.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 6:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 7:
                    i = i2 * 53;
                    floatToIntBits = zzhn.zza(zzkg.zzw(obj, j));
                    i2 = i + floatToIntBits;
                    break;
                case 8:
                    i = i2 * 53;
                    floatToIntBits = ((String) zzkg.zzf(obj, j)).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 9:
                    Object zzf = zzkg.zzf(obj, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzf(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 11:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 12:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 13:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 14:
                    i = i2 * 53;
                    doubleToLongBits = zzkg.zzd(obj, j);
                    byte[] bArr5 = zzhn.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 15:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 16:
                    i = i2 * 53;
                    doubleToLongBits = zzkg.zzd(obj, j);
                    byte[] bArr6 = zzhn.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 17:
                    Object zzf2 = zzkg.zzf(obj, j);
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
                    floatToIntBits = zzkg.zzf(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 50:
                    i = i2 * 53;
                    floatToIntBits = zzkg.zzf(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 51:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = Double.doubleToLongBits(zzn(obj, j));
                        byte[] bArr7 = zzhn.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = Float.floatToIntBits(zzo(obj, j));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr8 = zzhn.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr9 = zzhn.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzq(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr10 = zzhn.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzq(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzhn.zza(zzY(obj, j));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = ((String) zzkg.zzf(obj, j)).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzkg.zzf(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzkg.zzf(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzq(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzq(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzq(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr11 = zzhn.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzq(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr12 = zzhn.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzkg.zzf(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzn.zzd(obj).hashCode();
        return this.zzh ? (hashCode * 53) + this.zzo.zzb(obj).zza.hashCode() : hashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:171:0x04ef, code lost:
        if (r0 == r1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x04f1, code lost:
        r31.putInt(r12, r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x04f7, code lost:
        r10 = r9.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x04fc, code lost:
        if (r10 >= r9.zzl) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x04fe, code lost:
        zzD(r34, r9.zzj[r10], null, r9.zzn, r34);
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0511, code lost:
        if (r7 != 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0515, code lost:
        if (r6 != r37) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x051c, code lost:
        throw com.google.android.recaptcha.internal.zzhp.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x051f, code lost:
        if (r6 > r37) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0521, code lost:
        if (r8 != r7) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0523, code lost:
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0528, code lost:
        throw com.google.android.recaptcha.internal.zzhp.zzg();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzc(java.lang.Object r34, byte[] r35, int r36, int r37, int r38, com.google.android.recaptcha.internal.zzev r39) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzis.zzc(java.lang.Object, byte[], int, int, int, com.google.android.recaptcha.internal.zzev):int");
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final Object zze() {
        return ((zzhf) this.zzg).zzs();
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final void zzf(Object obj) {
        if (zzW(obj)) {
            if (obj instanceof zzhf) {
                zzhf zzhfVar = (zzhf) obj;
                zzhfVar.zzD(Integer.MAX_VALUE);
                zzhfVar.zza = 0;
                zzhfVar.zzB();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzz = zzz(i);
                int i2 = 1048575 & zzz;
                int zzy = zzy(zzz);
                long j = i2;
                if (zzy != 9) {
                    if (zzy != 60 && zzy != 68) {
                        switch (zzy) {
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
                                this.zzm.zzb(obj, j);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzij) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    } else if (zzX(obj, this.zzc[i], i)) {
                        zzC(i).zzf(zzb.getObject(obj, j));
                    }
                }
                if (zzT(obj, i)) {
                    zzC(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzn.zzm(obj);
            if (this.zzh) {
                this.zzo.zzf(obj);
            }
        }
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final void zzg(Object obj, Object obj2) {
        zzI(obj);
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzz = zzz(i);
            int i2 = this.zzc[i];
            long j = 1048575 & zzz;
            switch (zzy(zzz)) {
                case 0:
                    if (zzT(obj2, i)) {
                        zzkg.zzo(obj, j, zzkg.zza(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzT(obj2, i)) {
                        zzkg.zzp(obj, j, zzkg.zzb(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzT(obj2, i)) {
                        zzkg.zzr(obj, j, zzkg.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzT(obj2, i)) {
                        zzkg.zzr(obj, j, zzkg.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzT(obj2, i)) {
                        zzkg.zzq(obj, j, zzkg.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzT(obj2, i)) {
                        zzkg.zzr(obj, j, zzkg.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzT(obj2, i)) {
                        zzkg.zzq(obj, j, zzkg.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzT(obj2, i)) {
                        zzkg.zzm(obj, j, zzkg.zzw(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzT(obj2, i)) {
                        zzkg.zzs(obj, j, zzkg.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzJ(obj, obj2, i);
                    break;
                case 10:
                    if (zzT(obj2, i)) {
                        zzkg.zzs(obj, j, zzkg.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzT(obj2, i)) {
                        zzkg.zzq(obj, j, zzkg.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzT(obj2, i)) {
                        zzkg.zzq(obj, j, zzkg.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzT(obj2, i)) {
                        zzkg.zzq(obj, j, zzkg.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzT(obj2, i)) {
                        zzkg.zzr(obj, j, zzkg.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzT(obj2, i)) {
                        zzkg.zzq(obj, j, zzkg.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzT(obj2, i)) {
                        zzkg.zzr(obj, j, zzkg.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzJ(obj, obj2, i);
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
                    this.zzm.zzc(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzje.zza;
                    zzkg.zzs(obj, j, zzik.zzc(zzkg.zzf(obj, j), zzkg.zzf(obj2, j)));
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
                    if (zzX(obj2, i2, i)) {
                        zzkg.zzs(obj, j, zzkg.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzK(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzX(obj2, i2, i)) {
                        zzkg.zzs(obj, j, zzkg.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzK(obj, obj2, i);
                    break;
            }
        }
        zzje.zzD(this.zzn, obj, obj2);
        if (this.zzh) {
            zzje.zzC(this.zzo, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:182:0x0651 A[LOOP:2: B:180:0x064d->B:182:0x0651, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0665  */
    @Override // com.google.android.recaptcha.internal.zzjc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzh(java.lang.Object r18, com.google.android.recaptcha.internal.zzjb r19, com.google.android.recaptcha.internal.zzgq r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzis.zzh(java.lang.Object, com.google.android.recaptcha.internal.zzjb, com.google.android.recaptcha.internal.zzgq):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x02d9, code lost:
        if (r0 != r15) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02fd, code lost:
        if (r0 != r15) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0294, code lost:
        if (r0 != r5) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0296, code lost:
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r9 = r18;
        r1 = r19;
        r2 = r22;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02ac, code lost:
        r2 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    @Override // com.google.android.recaptcha.internal.zzjc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.recaptcha.internal.zzev r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 892
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzis.zzi(java.lang.Object, byte[], int, int, com.google.android.recaptcha.internal.zzev):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x09e9  */
    @Override // com.google.android.recaptcha.internal.zzjc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzj(java.lang.Object r18, com.google.android.recaptcha.internal.zzko r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2852
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzis.zzj(java.lang.Object, com.google.android.recaptcha.internal.zzko):void");
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final boolean zzk(Object obj, Object obj2) {
        boolean zzX;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzz = zzz(i);
            long j = zzz & ErrorCode.ERR_UNKNOWN;
            switch (zzy(zzz)) {
                case 0:
                    if (zzR(obj, obj2, i) && Double.doubleToLongBits(zzkg.zza(obj, j)) == Double.doubleToLongBits(zzkg.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzR(obj, obj2, i) && Float.floatToIntBits(zzkg.zzb(obj, j)) == Float.floatToIntBits(zzkg.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzR(obj, obj2, i) && zzkg.zzd(obj, j) == zzkg.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzR(obj, obj2, i) && zzkg.zzd(obj, j) == zzkg.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzR(obj, obj2, i) && zzkg.zzc(obj, j) == zzkg.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzR(obj, obj2, i) && zzkg.zzd(obj, j) == zzkg.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzR(obj, obj2, i) && zzkg.zzc(obj, j) == zzkg.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzR(obj, obj2, i) && zzkg.zzw(obj, j) == zzkg.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzR(obj, obj2, i) && zzje.zzX(zzkg.zzf(obj, j), zzkg.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzR(obj, obj2, i) && zzje.zzX(zzkg.zzf(obj, j), zzkg.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzR(obj, obj2, i) && zzje.zzX(zzkg.zzf(obj, j), zzkg.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzR(obj, obj2, i) && zzkg.zzc(obj, j) == zzkg.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzR(obj, obj2, i) && zzkg.zzc(obj, j) == zzkg.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzR(obj, obj2, i) && zzkg.zzc(obj, j) == zzkg.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzR(obj, obj2, i) && zzkg.zzd(obj, j) == zzkg.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzR(obj, obj2, i) && zzkg.zzc(obj, j) == zzkg.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzR(obj, obj2, i) && zzkg.zzd(obj, j) == zzkg.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzR(obj, obj2, i) && zzje.zzX(zzkg.zzf(obj, j), zzkg.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
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
                    zzX = zzje.zzX(zzkg.zzf(obj, j), zzkg.zzf(obj2, j));
                    break;
                case 50:
                    zzX = zzje.zzX(zzkg.zzf(obj, j), zzkg.zzf(obj2, j));
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
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzw = zzw(i) & ErrorCode.ERR_UNKNOWN;
                    if (zzkg.zzc(obj, zzw) == zzkg.zzc(obj2, zzw) && zzje.zzX(zzkg.zzf(obj, j), zzkg.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzX) {
                return false;
            }
        }
        if (this.zzn.zzd(obj).equals(this.zzn.zzd(obj2))) {
            if (this.zzh) {
                return this.zzo.zzb(obj).equals(this.zzo.zzb(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final boolean zzl(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzk) {
            int i6 = this.zzj[i5];
            int i7 = this.zzc[i6];
            int zzz = zzz(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzz) != 0 && !zzU(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzy = zzy(zzz);
            if (zzy != 9 && zzy != 17) {
                if (zzy != 27) {
                    if (zzy == 60 || zzy == 68) {
                        if (zzX(obj, i7, i6) && !zzV(obj, zzz, zzC(i6))) {
                            return false;
                        }
                    } else if (zzy != 49) {
                        if (zzy == 50 && !((zzij) zzkg.zzf(obj, zzz & ErrorCode.ERR_UNKNOWN)).isEmpty()) {
                            zzii zziiVar = (zzii) zzE(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzkg.zzf(obj, zzz & ErrorCode.ERR_UNKNOWN);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzjc zzC = zzC(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzC.zzl(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzU(obj, i6, i, i2, i10) && !zzV(obj, zzz, zzC(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.zzh || this.zzo.zzb(obj).zzk();
    }
}
