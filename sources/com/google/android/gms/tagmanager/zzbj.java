package com.google.android.gms.tagmanager;

import android.os.Build;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzbj extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.DEVICE_NAME.toString();

    public zzbj() {
        super(zza, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (!str2.startsWith(str) && !str.equals("unknown")) {
            StringBuilder sb = new StringBuilder(str.length() + 1 + str2.length());
            sb.append(str);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(str2);
            str2 = sb.toString();
        }
        return zzfv.zzc(str2);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
