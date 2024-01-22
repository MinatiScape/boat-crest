package com.google.android.gms.tagmanager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes10.dex */
public final class zzbz extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.HASH.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.ALGORITHM.toString();
    public static final String zzd = com.google.android.gms.internal.gtm.zzb.INPUT_FORMAT.toString();

    public zzbz() {
        super(zza, zzb);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        byte[] zzb2;
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzb);
        if (zzakVar != null && zzakVar != zzfv.zzb()) {
            String zzn = zzfv.zzn(zzfv.zzl(zzakVar));
            com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzc);
            String zzn2 = zzakVar2 == null ? MessageDigestAlgorithms.MD5 : zzfv.zzn(zzfv.zzl(zzakVar2));
            com.google.android.gms.internal.gtm.zzak zzakVar3 = map.get(zzd);
            String zzn3 = zzakVar3 == null ? "text" : zzfv.zzn(zzfv.zzl(zzakVar3));
            if ("text".equals(zzn3)) {
                zzb2 = zzn.getBytes();
            } else if ("base16".equals(zzn3)) {
                zzb2 = zzp.zzb(zzn);
            } else {
                String valueOf = String.valueOf(zzn3);
                zzdh.zza(valueOf.length() != 0 ? "Hash: unknown input format: ".concat(valueOf) : new String("Hash: unknown input format: "));
                return zzfv.zzb();
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(zzn2);
                messageDigest.update(zzb2);
                return zzfv.zzc(zzp.zza(messageDigest.digest()));
            } catch (NoSuchAlgorithmException unused) {
                String valueOf2 = String.valueOf(zzn2);
                zzdh.zza(valueOf2.length() != 0 ? "Hash: unknown algorithm: ".concat(valueOf2) : new String("Hash: unknown algorithm: "));
                return zzfv.zzb();
            }
        }
        return zzfv.zzb();
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
