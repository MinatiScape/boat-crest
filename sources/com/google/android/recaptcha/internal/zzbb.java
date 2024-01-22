package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbb {
    @NotNull
    private final WebView zza;
    @NotNull
    private final CoroutineScope zzb;

    public zzbb(@NotNull WebView webView, @NotNull CoroutineScope coroutineScope) {
        this.zza = webView;
        this.zzb = coroutineScope;
    }

    public final void zzb(@NotNull String str, @NotNull String... strArr) {
        BuildersKt.launch$default(this.zzb, null, null, new zzba(strArr, this, str, null), 3, null);
    }
}
