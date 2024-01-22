package com.google.android.gms.tagmanager;

import android.net.Uri;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
@ShowFirstParty
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzea {
    public static zzea zza;
    public volatile int zze = 1;
    public volatile String zzc = null;
    public volatile String zzb = null;
    public volatile String zzd = null;

    @ShowFirstParty
    public static zzea zza() {
        zzea zzeaVar;
        synchronized (zzea.class) {
            if (zza == null) {
                zza = new zzea();
            }
            zzeaVar = zza;
        }
        return zzeaVar;
    }

    public static final String zzf(String str) {
        return str.split("&")[0].split("=")[1];
    }

    public final String zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final synchronized boolean zzd(Uri uri) {
        try {
            String decode = URLDecoder.decode(uri.toString(), "UTF-8");
            if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                zzdh.zzb.zzd(decode.length() != 0 ? "Container preview url: ".concat(decode) : new String("Container preview url: "));
                if (decode.matches(".*?&gtm_debug=x$")) {
                    this.zze = 3;
                } else {
                    this.zze = 2;
                }
                this.zzd = uri.getQuery().replace("&gtm_debug=x", "");
                if (this.zze == 2 || this.zze == 3) {
                    String valueOf = String.valueOf(this.zzd);
                    this.zzc = valueOf.length() != 0 ? "/r?".concat(valueOf) : new String("/r?");
                }
                this.zzb = zzf(this.zzd);
                return true;
            } else if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                if (zzf(uri.getQuery()).equals(this.zzb)) {
                    String valueOf2 = String.valueOf(this.zzb);
                    zzdh.zzb.zzd(valueOf2.length() != 0 ? "Exit preview mode for container: ".concat(valueOf2) : new String("Exit preview mode for container: "));
                    this.zze = 1;
                    this.zzc = null;
                    return true;
                }
                return false;
            } else {
                zzdh.zzc(decode.length() != 0 ? "Invalid preview uri: ".concat(decode) : new String("Invalid preview uri: "));
                return false;
            }
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    public final int zze() {
        return this.zze;
    }
}
