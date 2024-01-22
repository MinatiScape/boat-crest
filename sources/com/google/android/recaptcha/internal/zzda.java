package com.google.android.recaptcha.internal;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzda extends WebViewClient {
    public final /* synthetic */ zzdi zza;

    public zzda(zzdi zzdiVar) {
        this.zza = zzdiVar;
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(@NotNull WebView webView, @NotNull String str) {
        System.currentTimeMillis();
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(@NotNull WebView webView, @NotNull String str) {
        String str2;
        String str3;
        String str4;
        Context context;
        zzq zzqVar;
        zzdt zzdtVar;
        zzai zzaiVar = zzai.zza;
        zzln zzlnVar = zzln.INIT_NETWORK;
        str2 = this.zza.zzg;
        str3 = this.zza.zzh;
        str4 = this.zza.zzh;
        zzaf zzafVar = new zzaf(zzlnVar, str2, str3, str4, null);
        context = this.zza.zze;
        zzqVar = this.zza.zzf;
        zzai.zzc(zzafVar, context, zzqVar);
        zzdtVar = this.zza.zzn;
        long zza = zzdtVar.zza(TimeUnit.MICROSECONDS);
        zzj zzjVar = zzj.zza;
        zzj.zza(zzl.zzl.zza(), zza);
    }

    @Override // android.webkit.WebViewClient
    @Deprecated(message = "Use onReceivedError(WebView,request,error) instead")
    public final void onReceivedError(@NotNull WebView webView, int i, @NotNull String str, @NotNull String str2) {
        Map map;
        super.onReceivedError(webView, i, str, str2);
        zzf zzfVar = zzf.zze;
        map = this.zza.zzj;
        zzd zzdVar = (zzd) map.get(Integer.valueOf(i));
        if (zzdVar == null) {
            zzdVar = zzd.zzb;
        }
        zzh zzhVar = new zzh(zzfVar, zzdVar);
        this.zza.zzm().hashCode();
        zzhVar.getMessage();
        this.zza.zzm().completeExceptionally(zzhVar);
    }

    @Override // android.webkit.WebViewClient
    @Deprecated(message = "Use shouldInterceptRequest(WebView,WebResourceRequest) instead")
    @Nullable
    public final WebResourceResponse shouldInterceptRequest(@NotNull WebView webView, @NotNull String str) {
        zzdk zzdkVar = zzdk.zza;
        if (!zzdk.zza(Uri.parse(str))) {
            Uri parse = Uri.parse(str);
            zzh zzhVar = new zzh(zzf.zzc, zzd.zzu);
            this.zza.zzm().hashCode();
            parse.toString();
            this.zza.zzm().completeExceptionally(zzhVar);
            return new WebResourceResponse("text/plain", "UTF-8", new ByteArrayInputStream(new byte[0]));
        }
        return super.shouldInterceptRequest(webView, str);
    }
}
