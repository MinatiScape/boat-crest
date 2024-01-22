package com.google.android.recaptcha.internal;

import android.net.TrafficStats;
import android.webkit.URLUtil;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzaq implements zzap {
    @NotNull
    private final String zza;

    public zzaq(@NotNull String str) {
        this.zza = str;
    }

    private static final void zzb(byte[] bArr) {
        for (zzlo zzloVar : zzlr.zzk(bArr).zzG()) {
            if (CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"INIT_TOTAL", "EXECUTE_TOTAL"}).contains(zzloVar.zzj().name()) && zzloVar.zzS()) {
                zzloVar.zzH();
                zzloVar.zzI();
                zzloVar.zzj().name();
                zzloVar.zzg().zzk();
                zzloVar.zzg().zzf();
                zzloVar.zzT();
            } else {
                zzloVar.zzH();
                zzloVar.zzI();
                zzloVar.zzj().name();
                zzloVar.zzT();
            }
        }
    }

    @Override // com.google.android.recaptcha.internal.zzap
    public final boolean zza(@NotNull byte[] bArr) {
        HttpURLConnection httpURLConnection;
        try {
            TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());
            zzb(bArr);
            if (URLUtil.isHttpUrl(this.zza)) {
                URLConnection openConnection = new URL(this.zza).openConnection();
                Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                httpURLConnection = (HttpURLConnection) openConnection;
            } else if (URLUtil.isHttpsUrl(this.zza)) {
                URLConnection openConnection2 = new URL(this.zza).openConnection();
                Intrinsics.checkNotNull(openConnection2, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
                httpURLConnection = (HttpsURLConnection) openConnection2;
            } else {
                throw new MalformedURLException("Recaptcha server url only allows using Http or Https.");
            }
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-protobuffer");
            httpURLConnection.connect();
            httpURLConnection.getOutputStream().write(bArr);
            return httpURLConnection.getResponseCode() == 200;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
}
