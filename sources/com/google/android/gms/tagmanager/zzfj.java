package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.util.VisibleForTesting;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
/* loaded from: classes10.dex */
public final class zzfj implements zzbk {
    public final String zza;
    public final Context zzb;
    public final zzfi zzc;
    public final zzfh zzd = new zzfh();

    @VisibleForTesting
    public zzfj(Context context, zzfi zzfiVar) {
        this.zzb = context.getApplicationContext();
        this.zzc = zzfiVar;
        String str = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        String str2 = null;
        if (locale != null && locale.getLanguage() != null && locale.getLanguage().length() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (locale.getCountry() != null && locale.getCountry().length() != 0) {
                sb.append("-");
                sb.append(locale.getCountry().toLowerCase());
            }
            str2 = sb.toString();
        }
        this.zza = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleTagManager", "4.00", str, str2, Build.MODEL, Build.ID);
    }

    @VisibleForTesting
    public static final URL zzc(zzca zzcaVar) {
        try {
            return new URL(zzcaVar.zzc());
        } catch (MalformedURLException unused) {
            zzdh.zza("Error trying to parse the GTM url.");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.google.android.gms.tagmanager.zzbk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(java.util.List<com.google.android.gms.tagmanager.zzca> r11) {
        /*
            r10 = this;
            int r0 = r11.size()
            r1 = 40
            int r0 = java.lang.Math.min(r0, r1)
            r1 = 1
            r2 = 0
            r4 = r1
            r3 = r2
        Le:
            if (r3 >= r0) goto Le5
            java.lang.Object r5 = r11.get(r3)
            com.google.android.gms.tagmanager.zzca r5 = (com.google.android.gms.tagmanager.zzca) r5
            java.net.URL r6 = zzc(r5)
            if (r6 != 0) goto L4c
            java.lang.String r6 = "No destination: discarding hit."
            com.google.android.gms.tagmanager.zzdh.zzc(r6)
            com.google.android.gms.tagmanager.zzfi r6 = r10.zzc
            com.google.android.gms.tagmanager.zzdu r6 = (com.google.android.gms.tagmanager.zzdu) r6
            com.google.android.gms.tagmanager.zzdw r6 = r6.zza
            long r7 = r5.zzb()
            com.google.android.gms.tagmanager.zzdw.zzh(r6, r7)
            long r5 = r5.zzb()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r8 = 57
            r7.<init>(r8)
            java.lang.String r8 = "Permanent failure dispatching hitId: "
            r7.append(r8)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.google.android.gms.tagmanager.zzbg r6 = com.google.android.gms.tagmanager.zzdh.zzb
            r6.zzd(r5)
            goto Le1
        L4c:
            java.net.URLConnection r6 = r6.openConnection()     // Catch: java.io.IOException -> Lb4
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch: java.io.IOException -> Lb4
            r7 = 0
            if (r4 == 0) goto L5e
            android.content.Context r4 = r10.zzb     // Catch: java.lang.Throwable -> L5b
            com.google.android.gms.tagmanager.zzdk.zza(r4)     // Catch: java.lang.Throwable -> L5b
            goto L5e
        L5b:
            r4 = move-exception
            r8 = r1
            goto La9
        L5e:
            java.lang.String r4 = "User-Agent"
            java.lang.String r8 = r10.zza     // Catch: java.lang.Throwable -> La7
            r6.setRequestProperty(r4, r8)     // Catch: java.lang.Throwable -> La7
            int r4 = r6.getResponseCode()     // Catch: java.lang.Throwable -> La7
            java.io.InputStream r7 = r6.getInputStream()     // Catch: java.lang.Throwable -> La7
            r8 = 200(0xc8, float:2.8E-43)
            if (r4 == r8) goto L8d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La7
            r9 = 25
            r8.<init>(r9)     // Catch: java.lang.Throwable -> La7
            java.lang.String r9 = "Bad response: "
            r8.append(r9)     // Catch: java.lang.Throwable -> La7
            r8.append(r4)     // Catch: java.lang.Throwable -> La7
            java.lang.String r4 = r8.toString()     // Catch: java.lang.Throwable -> La7
            com.google.android.gms.tagmanager.zzdh.zzc(r4)     // Catch: java.lang.Throwable -> La7
            com.google.android.gms.tagmanager.zzfi r4 = r10.zzc     // Catch: java.lang.Throwable -> La7
            r4.zza(r5)     // Catch: java.lang.Throwable -> La7
            goto L9a
        L8d:
            com.google.android.gms.tagmanager.zzfi r4 = r10.zzc     // Catch: java.lang.Throwable -> La7
            com.google.android.gms.tagmanager.zzdu r4 = (com.google.android.gms.tagmanager.zzdu) r4     // Catch: java.lang.Throwable -> La7
            com.google.android.gms.tagmanager.zzdw r4 = r4.zza     // Catch: java.lang.Throwable -> La7
            long r8 = r5.zzb()     // Catch: java.lang.Throwable -> La7
            com.google.android.gms.tagmanager.zzdw.zzh(r4, r8)     // Catch: java.lang.Throwable -> La7
        L9a:
            if (r7 == 0) goto L9f
            r7.close()     // Catch: java.io.IOException -> La4
        L9f:
            r6.disconnect()     // Catch: java.io.IOException -> La4
            r4 = r2
            goto Le1
        La4:
            r4 = move-exception
            r8 = r2
            goto Lb7
        La7:
            r4 = move-exception
            r8 = r2
        La9:
            if (r7 == 0) goto Lae
            r7.close()     // Catch: java.io.IOException -> Lb2
        Lae:
            r6.disconnect()     // Catch: java.io.IOException -> Lb2
            throw r4     // Catch: java.io.IOException -> Lb2
        Lb2:
            r4 = move-exception
            goto Lb7
        Lb4:
            r6 = move-exception
            r8 = r4
            r4 = r6
        Lb7:
            java.lang.Class r6 = r4.getClass()
            java.lang.String r6 = r6.getSimpleName()
            java.lang.String r7 = "Exception sending hit: "
            int r9 = r6.length()
            if (r9 == 0) goto Lcc
            java.lang.String r6 = r7.concat(r6)
            goto Ld1
        Lcc:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r7)
        Ld1:
            com.google.android.gms.tagmanager.zzdh.zzc(r6)
            java.lang.String r4 = r4.getMessage()
            com.google.android.gms.tagmanager.zzdh.zzc(r4)
            com.google.android.gms.tagmanager.zzfi r4 = r10.zzc
            r4.zza(r5)
            r4 = r8
        Le1:
            int r3 = r3 + 1
            goto Le
        Le5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfj.zza(java.util.List):void");
    }

    @Override // com.google.android.gms.tagmanager.zzbk
    public final boolean zzb() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzb.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzdh.zzb.zzd("...no network connectivity");
            return false;
        }
        return true;
    }
}
