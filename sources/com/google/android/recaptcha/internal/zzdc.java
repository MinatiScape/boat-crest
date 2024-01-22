package com.google.android.recaptcha.internal;

import android.webkit.JavascriptInterface;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzdc {
    public final /* synthetic */ zzdi zza;
    @Nullable
    private Long zzb;
    @NotNull
    private final zzdt zzc = zzdt.zzb();

    public zzdc(zzdi zzdiVar) {
        this.zza = zzdiVar;
    }

    private final void zzb() {
        if (this.zzb == null) {
            this.zzc.zzf();
            this.zzb = Long.valueOf(this.zzc.zza(TimeUnit.MILLISECONDS));
        }
    }

    @Nullable
    public final Long zza() {
        return this.zzb;
    }

    @JavascriptInterface
    public final void zzoed(@NotNull String str) {
        Map map;
        zzb();
        zzmq zzg = zzmq.zzg(zzek.zzh().zzj(str));
        zzg.zzi().name();
        zzg.zzk();
        map = this.zza.zzk;
        CancellableContinuation cancellableContinuation = (CancellableContinuation) map.remove(zzg.zzj());
        String zzk = zzg.zzk();
        if (zzk != null && zzk.length() != 0) {
            if (cancellableContinuation != null) {
                cancellableContinuation.resumeWith(Result.m123constructorimpl(zzg.zzk()));
                return;
            }
            return;
        }
        zzg.zzi().name();
        zzg zzgVar = zzh.zza;
        zzh zza = zzg.zza(zzg.zzi());
        zzg.zzj();
        if (cancellableContinuation != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(zza)));
        }
    }

    @JavascriptInterface
    public final void zzoid(@NotNull String str) {
        zzb();
        zzmu zzg = zzmu.zzg(zzek.zzh().zzj(str));
        zzg.zzi().name();
        if (zzg.zzi() == zzmw.JS_CODE_SUCCESS) {
            this.zza.zzm().hashCode();
            if (this.zza.zzm().complete(Unit.INSTANCE)) {
                return;
            }
            this.zza.zzm().hashCode();
            return;
        }
        zzg.zzi().name();
        zzg zzgVar = zzh.zza;
        zzh zza = zzg.zza(zzg.zzi());
        this.zza.zzm().hashCode();
        this.zza.zzm().completeExceptionally(zza);
    }

    @JavascriptInterface
    public final void zzrp(@NotNull String str) {
        zzb();
        this.zza.zzd().zza(str);
    }
}
