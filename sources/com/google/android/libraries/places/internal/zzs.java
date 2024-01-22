package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import androidx.annotation.Nullable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public final class zzs {
    private static final long zza = TimeUnit.MINUTES.toMicros(1);
    @Nullable
    private final WifiManager zzb;
    private final zzb zzc;

    public zzs(@Nullable WifiManager wifiManager, zzb zzbVar) {
        this.zzb = wifiManager;
        this.zzc = zzbVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0098, code lost:
        if (r10 == false) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0098  */
    @androidx.annotation.RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_FINE_LOCATION"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.libraries.places.internal.zzgi<com.google.android.libraries.places.internal.zzq> zza() {
        /*
            r14 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 17
            if (r0 >= r1) goto Lb
            com.google.android.libraries.places.internal.zzgi r0 = com.google.android.libraries.places.internal.zzgi.zza()
            return r0
        Lb:
            android.net.wifi.WifiManager r0 = r14.zzb
            if (r0 == 0) goto Lb9
            boolean r0 = r0.isWifiEnabled()
            if (r0 != 0) goto L17
            goto Lb9
        L17:
            android.net.wifi.WifiManager r0 = r14.zzb
            java.util.List r0 = r0.getScanResults()
            if (r0 == 0) goto Lb4
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L27
            goto Lb4
        L27:
            java.util.Comparator r2 = com.google.android.libraries.places.internal.zzr.zza
            com.google.android.libraries.places.internal.zzgo r2 = com.google.android.libraries.places.internal.zzgo.zza(r2)
            com.google.android.libraries.places.internal.zzgi r0 = com.google.android.libraries.places.internal.zzgi.zza(r2, r0)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.net.wifi.WifiManager r3 = r14.zzb
            android.net.wifi.WifiInfo r3 = r3.getConnectionInfo()
            int r4 = r0.size()
            r5 = 0
            r6 = r5
        L42:
            if (r6 >= r4) goto Laf
            java.lang.Object r7 = r0.get(r6)
            int r6 = r6 + 1
            android.net.wifi.ScanResult r7 = (android.net.wifi.ScanResult) r7
            int r8 = android.os.Build.VERSION.SDK_INT
            r9 = 1
            if (r8 < r1) goto La3
            if (r7 == 0) goto La3
            java.lang.String r8 = r7.SSID
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L5c
            goto La3
        L5c:
            r10 = 1000(0x3e8, double:4.94E-321)
            com.google.android.libraries.places.internal.zzb r8 = r14.zzc
            long r12 = r8.zza()
            long r12 = r12 * r10
            long r10 = r7.timestamp
            long r12 = r12 - r10
            long r10 = com.google.android.libraries.places.internal.zzs.zza
            int r8 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r8 <= 0) goto L70
            r8 = r9
            goto L71
        L70:
            r8 = r5
        L71:
            java.lang.String r10 = r7.SSID
            if (r10 == 0) goto L9b
            r11 = 95
            int r11 = r10.indexOf(r11)
            if (r11 < 0) goto L95
            java.util.Locale r11 = java.util.Locale.ENGLISH
            java.lang.String r10 = r10.toLowerCase(r11)
            java.lang.String r11 = "_nomap"
            boolean r11 = r10.contains(r11)
            if (r11 != 0) goto L93
            java.lang.String r11 = "_optout"
            boolean r10 = r10.contains(r11)
            if (r10 == 0) goto L95
        L93:
            r10 = r9
            goto L96
        L95:
            r10 = r5
        L96:
            if (r8 != 0) goto La3
            if (r10 != 0) goto La3
            goto La4
        L9b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Null SSID."
            r0.<init>(r1)
            throw r0
        La3:
            r9 = r5
        La4:
            if (r9 == 0) goto L42
            com.google.android.libraries.places.internal.zzq r8 = new com.google.android.libraries.places.internal.zzq
            r8.<init>(r3, r7)
            r2.add(r8)
            goto L42
        Laf:
            com.google.android.libraries.places.internal.zzgi r0 = com.google.android.libraries.places.internal.zzgi.zza(r2)
            return r0
        Lb4:
            com.google.android.libraries.places.internal.zzgi r0 = com.google.android.libraries.places.internal.zzgi.zza()
            return r0
        Lb9:
            com.google.android.libraries.places.internal.zzgi r0 = com.google.android.libraries.places.internal.zzgi.zza()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzs.zza():com.google.android.libraries.places.internal.zzgi");
    }

    public static final /* synthetic */ int zza(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult2.level - scanResult.level;
    }
}
