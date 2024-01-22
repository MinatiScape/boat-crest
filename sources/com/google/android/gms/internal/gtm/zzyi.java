package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzyi extends zzuj {
    public /* synthetic */ zzyi(zzyg zzygVar) {
        super(true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.gtm.zzuj
    public final <CT extends zzwk> zzux<CT, ?> zzc(CT ct, int i) {
        char c;
        String name = ct.getClass().getName();
        switch (name.hashCode()) {
            case -1328301759:
                if (name.equals("com.google.android.gms.internal.gtm.zzak")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1328301158:
                if (name.equals("com.google.android.gms.internal.gtm.zztw")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1328301155:
                if (name.equals("com.google.android.gms.internal.gtm.zztz")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1328301148:
                if (name.equals("com.google.android.gms.internal.gtm.zzub")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            switch (i) {
                case 21596320:
                    return zzyq.zzd;
                case 21623477:
                    return zzyq.zzb;
                case 21713708:
                    return zzyq.zza;
                case 23459630:
                    return zzyq.zzc;
                case 26652850:
                    return zzyq.zze;
                case 53697879:
                    return zzyq.zzf;
                case 56871503:
                    return zzyq.zzg;
                default:
                    return null;
            }
        } else if (c == 1) {
            switch (i) {
                case 21467048:
                    return zzyq.zzk;
                case 21596320:
                    return zzyq.zzj;
                case 21623477:
                    return zzyq.zzi;
                case 21713708:
                    return zzyq.zzh;
                case 26652850:
                    return zzyq.zzl;
                default:
                    return null;
            }
        } else if (c == 2) {
            if (i != 21596320) {
                if (i != 28993747) {
                    return null;
                }
                return zzyq.zzn;
            }
            return zzyq.zzm;
        } else if (c != 3) {
            return null;
        } else {
            if (i != 101) {
                if (i != 47497405) {
                    return null;
                }
                return zze.zza;
            }
            return zzae.zza;
        }
    }
}
