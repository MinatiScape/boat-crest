package com.google.android.recaptcha.internal;

import android.content.Context;
import android.webkit.WebView;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzdi {
    @NotNull
    public static final zzdb zza = new zzdb(null);
    public CompletableDeferred zzb;
    @NotNull
    private final WebView zzc;
    @NotNull
    private final String zzd;
    @NotNull
    private final Context zze;
    @NotNull
    private final zzq zzf;
    @NotNull
    private final String zzg;
    @NotNull
    private final String zzh;
    @NotNull
    private final zzaj zzi;
    @NotNull
    private final Map zzj = zzdj.zza();
    @NotNull
    private final Map zzk;
    @NotNull
    private final Map zzl;
    @NotNull
    private final zzas zzm;
    @NotNull
    private final zzdt zzn;
    @NotNull
    private final Mutex zzo;
    @NotNull
    private final zzdc zzp;

    public zzdi(@NotNull WebView webView, @NotNull String str, @NotNull Context context, @NotNull zzq zzqVar, @NotNull String str2, @NotNull String str3, @NotNull zzaj zzajVar, @NotNull CoroutineScope coroutineScope) {
        this.zzc = webView;
        this.zzd = str;
        this.zze = context;
        this.zzf = zzqVar;
        this.zzg = str2;
        this.zzh = str3;
        this.zzi = zzajVar;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zzk = linkedHashMap;
        this.zzl = linkedHashMap;
        zzbb zzbbVar = new zzbb(webView, coroutineScope);
        zzp zzpVar = zzp.zza;
        this.zzm = new zzay(zzbbVar, zzp.zzc(), context, new zzn());
        this.zzn = zzdt.zzc();
        this.zzo = MutexKt.Mutex$default(false, 1, null);
        zzdc zzdcVar = new zzdc(this);
        this.zzp = zzdcVar;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(zzdcVar, "RN");
        webView.setWebViewClient(new zzda(this));
    }

    private final zzh zzp(Exception exc) {
        if (exc instanceof TimeoutCancellationException) {
            return new zzh(zzf.zzc, zzd.zzj);
        }
        if (exc instanceof zzh) {
            return (zzh) exc;
        }
        return new zzh(zzf.zzc, zzd.zzu);
    }

    private final void zzq(List list, zzh zzhVar) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzln zzlnVar = (zzln) it.next();
            zzai zzaiVar = zzai.zza;
            String str = this.zzg;
            String str2 = this.zzh;
            zzai.zzd(new zzaf(zzlnVar, str, str2, str2, null), String.valueOf(zzhVar.zzb().zza()), zzhVar.zza().zza(), this.zze, this.zzf, null);
        }
    }

    @NotNull
    public final WebView zzb() {
        return this.zzc;
    }

    @NotNull
    public final zzas zzd() {
        return this.zzm;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c9  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object zzf(@org.jetbrains.annotations.NotNull com.google.android.recaptcha.RecaptchaAction r12, long r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r15) {
        /*
            Method dump skipped, instructions count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzdi.zzf(com.google.android.recaptcha.RecaptchaAction, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object zzg(long r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof com.google.android.recaptcha.internal.zzdf
            if (r0 == 0) goto L13
            r0 = r13
            com.google.android.recaptcha.internal.zzdf r0 = (com.google.android.recaptcha.internal.zzdf) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.zzd = r1
            goto L18
        L13:
            com.google.android.recaptcha.internal.zzdf r0 = new com.google.android.recaptcha.internal.zzdf
            r0.<init>(r10, r13)
        L18:
            java.lang.Object r13 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            long r11 = r0.zza
            com.google.android.recaptcha.internal.zzdi r0 = r0.zze
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Exception -> L2d
            goto L78
        L2d:
            r13 = move-exception
            goto L82
        L2f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L37:
            kotlin.ResultKt.throwOnFailure(r13)
            com.google.android.recaptcha.internal.zzai r13 = com.google.android.recaptcha.internal.zzai.zza
            com.google.android.recaptcha.internal.zzaf r13 = new com.google.android.recaptcha.internal.zzaf
            com.google.android.recaptcha.internal.zzln r5 = com.google.android.recaptcha.internal.zzln.INIT_NATIVE
            java.lang.String r6 = r10.zzg
            java.lang.String r8 = r10.zzh
            r9 = 0
            r4 = r13
            r7 = r8
            r4.<init>(r5, r6, r7, r8, r9)
            java.lang.String r2 = r10.zzd
            com.google.android.recaptcha.internal.zzr r4 = new com.google.android.recaptcha.internal.zzr
            r4.<init>()
            com.google.android.recaptcha.internal.zzai.zzb(r13, r2, r4)
            r13 = 0
            kotlinx.coroutines.CompletableDeferred r2 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r13, r3, r13)
            r10.zzb = r2
            kotlinx.coroutines.CompletableDeferred r2 = r10.zzm()
            int r2 = r2.hashCode()
            kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            com.google.android.recaptcha.internal.zzdh r2 = new com.google.android.recaptcha.internal.zzdh     // Catch: java.lang.Exception -> L80
            r2.<init>(r10, r13)     // Catch: java.lang.Exception -> L80
            r0.zze = r10     // Catch: java.lang.Exception -> L80
            r0.zza = r11     // Catch: java.lang.Exception -> L80
            r0.zzd = r3     // Catch: java.lang.Exception -> L80
            java.lang.Object r13 = kotlinx.coroutines.TimeoutKt.withTimeout(r11, r2, r0)     // Catch: java.lang.Exception -> L80
            if (r13 == r1) goto L7f
            r0 = r10
        L78:
            kotlin.Result r13 = (kotlin.Result) r13     // Catch: java.lang.Exception -> L2d
            java.lang.Object r11 = r13.m132unboximpl()     // Catch: java.lang.Exception -> L2d
            goto Ld6
        L7f:
            return r1
        L80:
            r13 = move-exception
            r0 = r10
        L82:
            r13.getMessage()
            boolean r1 = r13 instanceof kotlinx.coroutines.TimeoutCancellationException
            if (r1 == 0) goto L9a
            r2 = 2
            com.google.android.recaptcha.internal.zzln[] r2 = new com.google.android.recaptcha.internal.zzln[r2]
            r4 = 0
            com.google.android.recaptcha.internal.zzln r5 = com.google.android.recaptcha.internal.zzln.INIT_TOTAL
            r2[r4] = r5
            com.google.android.recaptcha.internal.zzln r4 = com.google.android.recaptcha.internal.zzln.INIT_NETWORK
            r2[r3] = r4
            java.util.List r2 = kotlin.collections.CollectionsKt__CollectionsKt.listOf(r2)
            goto La0
        L9a:
            com.google.android.recaptcha.internal.zzln r2 = com.google.android.recaptcha.internal.zzln.INIT_TOTAL
            java.util.List r2 = kotlin.collections.e.listOf(r2)
        La0:
            com.google.android.recaptcha.internal.zzdc r3 = r0.zzp
            java.lang.Long r3 = r3.zza()
            if (r1 != 0) goto La9
            goto Lc1
        La9:
            if (r3 != 0) goto Lac
            goto Lb7
        Lac:
            r4 = -2000(0xfffffffffffff830, double:NaN)
            long r11 = r11 + r4
            long r3 = r3.longValue()
            int r11 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r11 <= 0) goto Lc1
        Lb7:
            com.google.android.recaptcha.internal.zzh r11 = new com.google.android.recaptcha.internal.zzh
            com.google.android.recaptcha.internal.zzf r12 = com.google.android.recaptcha.internal.zzf.zze
            com.google.android.recaptcha.internal.zzd r13 = com.google.android.recaptcha.internal.zzd.zzT
            r11.<init>(r12, r13)
            goto Lc5
        Lc1:
            com.google.android.recaptcha.internal.zzh r11 = r0.zzp(r13)
        Lc5:
            r0.zzq(r2, r11)
            kotlin.Result$Companion r12 = kotlin.Result.Companion
            com.google.android.recaptcha.RecaptchaException r11 = r11.zzc()
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m123constructorimpl(r11)
        Ld6:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzdi.zzg(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final CompletableDeferred zzm() {
        CompletableDeferred completableDeferred = this.zzb;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }

    public final void zzo() {
        zzln zzlnVar = zzln.EXECUTE_TOTAL;
        String str = this.zzg;
        String str2 = this.zzh;
        zzaf zzafVar = new zzaf(zzlnVar, str, str2, str2, null);
        zzh zzhVar = new zzh(zzf.zzc, zzd.zzU);
        zzai zzaiVar = zzai.zza;
        zzai.zzb(zzafVar, this.zzd, new zzr());
        zzai.zzd(zzafVar, String.valueOf(zzhVar.zzb().zza()), zzhVar.zza().zza(), this.zze, this.zzf, null);
    }
}
