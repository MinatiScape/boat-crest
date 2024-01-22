package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class zzcy extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.JOINER.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.ITEM_SEPARATOR.toString();
    public static final String zzd = com.google.android.gms.internal.gtm.zzb.KEY_VALUE_SEPARATOR.toString();
    public static final String zze = com.google.android.gms.internal.gtm.zzb.ESCAPE.toString();

    public zzcy() {
        super(zza, zzb);
    }

    public static final void zzc(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    public static final String zzd(String str, int i, Set<Character> set) {
        int i2 = i - 1;
        if (i2 == 1) {
            try {
                return zzfy.zza(str);
            } catch (UnsupportedEncodingException e) {
                zzdh.zzb("Joiner: unsupported encoding", e);
                return str;
            }
        } else if (i2 != 2) {
            return str;
        } else {
            String replace = str.replace("\\", "\\\\");
            for (Character ch : set) {
                String ch2 = ch.toString();
                String valueOf = String.valueOf(ch2);
                replace = replace.replace(ch2, valueOf.length() != 0 ? "\\".concat(valueOf) : new String("\\"));
            }
            return replace;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        int i;
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzb);
        if (zzakVar == null) {
            return zzfv.zzb();
        }
        com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzc);
        String zzn = zzakVar2 != null ? zzfv.zzn(zzfv.zzl(zzakVar2)) : "";
        com.google.android.gms.internal.gtm.zzak zzakVar3 = map.get(zzd);
        String zzn2 = zzakVar3 != null ? zzfv.zzn(zzfv.zzl(zzakVar3)) : "=";
        com.google.android.gms.internal.gtm.zzak zzakVar4 = map.get(zze);
        HashSet hashSet = null;
        boolean z = true;
        if (zzakVar4 != null) {
            String zzn3 = zzfv.zzn(zzfv.zzl(zzakVar4));
            if ("url".equals(zzn3)) {
                i = 2;
            } else if ("backslash".equals(zzn3)) {
                hashSet = new HashSet();
                zzc(hashSet, zzn);
                zzc(hashSet, zzn2);
                hashSet.remove('\\');
                i = 3;
            } else {
                String valueOf = String.valueOf(zzn3);
                zzdh.zza(valueOf.length() != 0 ? "Joiner: unsupported escape type: ".concat(valueOf) : new String("Joiner: unsupported escape type: "));
                return zzfv.zzb();
            }
        } else {
            i = 1;
        }
        StringBuilder sb = new StringBuilder();
        int zzO = zzakVar.zzO();
        if (zzO == 2) {
            for (com.google.android.gms.internal.gtm.zzak zzakVar5 : zzakVar.zzr()) {
                if (!z) {
                    sb.append(zzn);
                }
                sb.append(zzd(zzfv.zzn(zzfv.zzl(zzakVar5)), i, hashSet));
                z = false;
            }
        } else if (zzO != 3) {
            sb.append(zzd(zzfv.zzn(zzfv.zzl(zzakVar)), i, hashSet));
        } else {
            for (int i2 = 0; i2 < zzakVar.zzc(); i2++) {
                if (i2 > 0) {
                    sb.append(zzn);
                }
                String zzn4 = zzfv.zzn(zzfv.zzl(zzakVar.zzk(i2)));
                String zzn5 = zzfv.zzn(zzfv.zzl(zzakVar.zzl(i2)));
                sb.append(zzd(zzn4, i, hashSet));
                sb.append(zzn2);
                sb.append(zzd(zzn5, i, hashSet));
            }
        }
        return zzfv.zzc(sb.toString());
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
