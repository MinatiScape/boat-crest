package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zznc extends zzhf implements zziq {
    private static final zznc zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private zzmo zzh;
    private int zzi;
    private zzml zzj;

    static {
        zznc zzncVar = new zznc();
        zzb = zzncVar;
        zzhf.zzC(zznc.class, zzncVar);
    }

    private zznc() {
    }

    public static /* synthetic */ void zzG(zznc zzncVar, int i) {
        if (i == 1) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        zzncVar.zzd = i - 2;
    }

    public static /* synthetic */ void zzH(zznc zzncVar, int i) {
        if (i == 1) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        zzncVar.zzf = i - 2;
    }

    public static zznb zzf() {
        return (zznb) zzb.zzp();
    }

    @Override // com.google.android.recaptcha.internal.zzhf
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zznb(null);
                }
                return new zznc();
            }
            return zzhf.zzz(zzb, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\f\u0002\u000b\u0003\f\u0004\f\u0005\t\u0006\u000b\u0007\t", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        return (byte) 1;
    }

    public final int zzj() {
        int i;
        switch (this.zzf) {
            case 0:
                i = 2;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            case 4:
                i = 6;
                break;
            case 5:
                i = 7;
                break;
            case 6:
                i = 8;
                break;
            case 7:
                i = 9;
                break;
            case 8:
                i = 10;
                break;
            case 9:
                i = 11;
                break;
            case 10:
                i = 12;
                break;
            case 11:
                i = 13;
                break;
            case 12:
                i = 14;
                break;
            case 13:
                i = 15;
                break;
            case 14:
                i = 16;
                break;
            case 15:
                i = 17;
                break;
            case 16:
                i = 18;
                break;
            case 17:
                i = 19;
                break;
            case 18:
                i = 20;
                break;
            case 19:
                i = 21;
                break;
            case 20:
                i = 22;
                break;
            case 21:
                i = 23;
                break;
            case 22:
                i = 24;
                break;
            case 23:
                i = 25;
                break;
            case 24:
                i = 26;
                break;
            case 25:
                i = 27;
                break;
            case 26:
                i = 28;
                break;
            case 27:
                i = 29;
                break;
            case 28:
                i = 30;
                break;
            case 29:
                i = 31;
                break;
            case 30:
                i = 32;
                break;
            case 31:
                i = 33;
                break;
            case 32:
                i = 34;
                break;
            case 33:
                i = 35;
                break;
            case 34:
                i = 36;
                break;
            case 35:
                i = 37;
                break;
            case 36:
                i = 38;
                break;
            case 37:
                i = 39;
                break;
            case 38:
                i = 40;
                break;
            case 39:
                i = 41;
                break;
            case 40:
                i = 42;
                break;
            case 41:
                i = 43;
                break;
            case 42:
                i = 44;
                break;
            case 43:
                i = 45;
                break;
            case 44:
                i = 46;
                break;
            case 45:
                i = 47;
                break;
            case 46:
                i = 48;
                break;
            case 47:
                i = 49;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    public final int zzk() {
        int i;
        switch (this.zzd) {
            case 0:
                i = 2;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            case 4:
                i = 6;
                break;
            case 5:
                i = 7;
                break;
            case 6:
                i = 8;
                break;
            case 7:
                i = 9;
                break;
            case 8:
                i = 10;
                break;
            case 9:
                i = 11;
                break;
            case 10:
                i = 12;
                break;
            case 11:
                i = 13;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }
}
