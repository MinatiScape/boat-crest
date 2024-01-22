package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes10.dex */
public final class zzq {
    private final String zza;
    private final int zzb;
    private final zzp zzc;
    private final boolean zzd;
    private final int zze;

    public zzq(@Nullable WifiInfo wifiInfo, ScanResult scanResult) {
        this(wifiInfo, scanResult.BSSID, scanResult.capabilities, scanResult.level, scanResult.frequency);
    }

    @Nullable
    public final String zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final zzp zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzd;
    }

    public final int zze() {
        return this.zze;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private zzq(@androidx.annotation.Nullable android.net.wifi.WifiInfo r7, @androidx.annotation.Nullable java.lang.String r8, @androidx.annotation.Nullable java.lang.String r9, int r10, int r11) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto L34
            java.lang.String r9 = r9.toUpperCase()
            java.lang.String r0 = "[ESS]"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L31
            java.lang.String r0 = "[IBSS]"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L1b
            goto L31
        L1b:
            java.lang.String r0 = ".*WPA[0-9]*-PSK.*"
            boolean r0 = r9.matches(r0)
            if (r0 == 0) goto L26
            com.google.android.libraries.places.internal.zzp r9 = com.google.android.libraries.places.internal.zzp.PSK
            goto L36
        L26:
            java.lang.String r0 = ".*WPA[0-9]*-EAP.*"
            boolean r9 = r9.matches(r0)
            if (r9 == 0) goto L34
            com.google.android.libraries.places.internal.zzp r9 = com.google.android.libraries.places.internal.zzp.EAP
            goto L36
        L31:
            com.google.android.libraries.places.internal.zzp r9 = com.google.android.libraries.places.internal.zzp.NONE
            goto L36
        L34:
            com.google.android.libraries.places.internal.zzp r9 = com.google.android.libraries.places.internal.zzp.OTHER
        L36:
            r3 = r9
            if (r7 == 0) goto L4b
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 != 0) goto L4b
            java.lang.String r7 = r7.getBSSID()
            boolean r7 = r8.equalsIgnoreCase(r7)
            if (r7 == 0) goto L4b
            r7 = 1
            goto L4c
        L4b:
            r7 = 0
        L4c:
            r4 = r7
            r0 = r6
            r1 = r8
            r2 = r10
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzq.<init>(android.net.wifi.WifiInfo, java.lang.String, java.lang.String, int, int):void");
    }

    @VisibleForTesting
    private zzq(@Nullable String str, int i, zzp zzpVar, boolean z, int i2) {
        this.zza = str;
        this.zzb = i;
        this.zzc = zzpVar;
        this.zzd = z;
        this.zze = i2;
    }
}
