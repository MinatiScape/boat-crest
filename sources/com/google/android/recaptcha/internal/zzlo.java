package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzlo extends zzhf implements zziq {
    private static final zzlo zzb;
    private Object zze;
    private int zzf;
    private long zzk;
    private zzgn zzl;
    private int zzm;
    private zzla zzn;
    private zzma zzo;
    private zzju zzq;
    private zzgn zzr;
    private int zzd = 0;
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzp = "";

    static {
        zzlo zzloVar = new zzlo();
        zzb = zzloVar;
        zzhf.zzC(zzlo.class, zzloVar);
    }

    private zzlo() {
    }

    public static zzlo zzG() {
        return zzb;
    }

    public static /* synthetic */ void zzJ(zzlo zzloVar, String str) {
        str.getClass();
        zzloVar.zzi = str;
    }

    public static /* synthetic */ void zzM(zzlo zzloVar, zzma zzmaVar) {
        zzmaVar.getClass();
        zzloVar.zzo = zzmaVar;
    }

    public static /* synthetic */ void zzP(zzlo zzloVar, zzku zzkuVar) {
        zzkuVar.getClass();
        zzloVar.zze = zzkuVar;
        zzloVar.zzd = 15;
    }

    public static /* synthetic */ void zzQ(zzlo zzloVar, String str) {
        str.getClass();
        zzloVar.zzg = str;
    }

    public static /* synthetic */ void zzR(zzlo zzloVar, String str) {
        str.getClass();
        zzloVar.zzh = str;
    }

    public static zzll zzi() {
        return (zzll) zzb.zzp();
    }

    public final String zzH() {
        return this.zzh;
    }

    public final String zzI() {
        return this.zzi;
    }

    public final boolean zzS() {
        return this.zzn != null;
    }

    public final int zzT() {
        int i = this.zzm;
        int i2 = i != 0 ? i != 1 ? i != 2 ? 0 : 4 : 3 : 2;
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    @Deprecated
    public final long zzf() {
        return this.zzk;
    }

    public final zzla zzg() {
        zzla zzlaVar = this.zzn;
        return zzlaVar == null ? zzla.zzj() : zzlaVar;
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
                    return new zzll(null);
                }
                return new zzlo();
            }
            return zzhf.zzz(zzb, "\u0000\u000e\u0001\u0000\u0001\u000f\u000e\u0000\u0000\u0000\u0001\f\u0002Ȉ\u0003\u0003\u0004\f\u0005\t\u0006\t\u0007Ȉ\bȈ\tȈ\n\t\u000b\t\r\t\u000eȈ\u000f<\u0000", new Object[]{"zze", "zzd", "zzf", "zzh", "zzk", "zzm", "zzn", "zzo", "zzp", "zzi", "zzj", "zzl", "zzq", "zzr", "zzg", zzku.class});
        }
        return (byte) 1;
    }

    public final zzln zzj() {
        int i = this.zzf;
        zzln zzlnVar = zzln.UNKNOWN;
        switch (i) {
            case 0:
                break;
            case 1:
                zzlnVar = zzln.INIT_NATIVE;
                break;
            case 2:
                zzlnVar = zzln.INIT_NETWORK;
                break;
            case 3:
                zzlnVar = zzln.INIT_JS;
                break;
            case 4:
                zzlnVar = zzln.INIT_TOTAL;
                break;
            case 5:
                zzlnVar = zzln.EXECUTE_NATIVE;
                break;
            case 6:
                zzlnVar = zzln.EXECUTE_JS;
                break;
            case 7:
                zzlnVar = zzln.EXECUTE_TOTAL;
                break;
            case 8:
                zzlnVar = zzln.CHALLENGE_ACCOUNT_NATIVE;
                break;
            case 9:
                zzlnVar = zzln.CHALLENGE_ACCOUNT_JS;
                break;
            case 10:
                zzlnVar = zzln.CHALLENGE_ACCOUNT_TOTAL;
                break;
            case 11:
                zzlnVar = zzln.VERIFY_PIN_NATIVE;
                break;
            case 12:
                zzlnVar = zzln.VERIFY_PIN_JS;
                break;
            case 13:
                zzlnVar = zzln.VERIFY_PIN_TOTAL;
                break;
            case 14:
                zzlnVar = zzln.RUN_PROGRAM;
                break;
            case 15:
                zzlnVar = zzln.FETCH_ALLOWLIST;
                break;
            case 16:
                zzlnVar = zzln.JS_LOAD;
                break;
            case 17:
                zzlnVar = zzln.WEB_VIEW_RELOAD_JS;
                break;
            default:
                zzlnVar = null;
                break;
        }
        return zzlnVar == null ? zzln.UNRECOGNIZED : zzlnVar;
    }
}
