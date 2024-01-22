package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.gtm.zzav;
import com.google.android.gms.internal.gtm.zzbe;
import com.google.android.gms.internal.gtm.zzbr;
import com.google.android.gms.internal.gtm.zzbt;
import com.google.android.gms.internal.gtm.zzbv;
import com.google.android.gms.internal.gtm.zzbx;
import com.google.android.gms.internal.gtm.zzex;
import com.google.android.gms.internal.gtm.zzfs;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public final class zzb extends zzbr implements zzt {
    public static DecimalFormat k;
    public final zzbv h;
    public final String i;
    public final Uri j;

    public zzb(zzbv zzbvVar, String str) {
        super(zzbvVar);
        Preconditions.checkNotEmpty(str);
        this.h = zzbvVar;
        this.i = str;
        this.j = a(str);
    }

    public static Uri a(String str) {
        Preconditions.checkNotEmpty(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(NotificationCompat.MessagingStyle.Message.KEY_DATA_URI);
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public static String b(double d) {
        if (k == null) {
            k = new DecimalFormat("0.######");
        }
        return k.format(d);
    }

    public static void c(Map<String, String> map, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        map.put(str, str2);
    }

    public static void d(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
        if (android.text.TextUtils.isEmpty(r6) != false) goto L8;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x001e A[SYNTHETIC] */
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.String> zzd(com.google.android.gms.analytics.zzh r12) {
        /*
            Method dump skipped, instructions count: 851
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.zzb.zzd(com.google.android.gms.analytics.zzh):java.util.Map");
    }

    @Override // com.google.android.gms.analytics.zzt
    public final Uri zzb() {
        return this.j;
    }

    @Override // com.google.android.gms.analytics.zzt
    public final void zze(zzh zzhVar) {
        Preconditions.checkNotNull(zzhVar);
        Preconditions.checkArgument(zzhVar.zzm(), "Can't deliver not submitted measurement");
        Preconditions.checkNotMainThread("deliver should be called on worker thread");
        zzh zzhVar2 = new zzh(zzhVar);
        zzbe zzbeVar = (zzbe) zzhVar2.zzb(zzbe.class);
        if (TextUtils.isEmpty(zzbeVar.zzf())) {
            zzz().zzc(zzd(zzhVar2), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzbeVar.zze())) {
            zzz().zzc(zzd(zzhVar2), "Ignoring measurement without client id");
        } else if (!this.h.zzc().getAppOptOut()) {
            if (zzfs.zzj(0.0d, zzbeVar.zze())) {
                zzG("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(0.0d));
                return;
            }
            Map<String, String> zzd = zzd(zzhVar2);
            zzd.put(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "1");
            zzd.put("_v", zzbt.zzb);
            zzd.put("tid", this.i);
            if (this.h.zzc().isDryRunEnabled()) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : zzd.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append(", ");
                    }
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                }
                zzN("Dry run is enabled. GoogleAnalytics would have sent", sb.toString());
                return;
            }
            HashMap hashMap = new HashMap();
            zzfs.zzg(hashMap, "uid", zzbeVar.zzg());
            zzav zzavVar = (zzav) zzhVar.zzc(zzav.class);
            if (zzavVar != null) {
                zzfs.zzg(hashMap, "an", zzavVar.zzf());
                zzfs.zzg(hashMap, "aid", zzavVar.zzd());
                zzfs.zzg(hashMap, "av", zzavVar.zzg());
                zzfs.zzg(hashMap, "aiid", zzavVar.zze());
            }
            zzd.put("_s", String.valueOf(zzs().zza(new zzbx(0L, zzbeVar.zze(), this.i, !TextUtils.isEmpty(zzbeVar.zzd()), 0L, hashMap))));
            zzs().zzh(new zzex(zzz(), zzd, zzhVar.zza(), true));
        }
    }
}
