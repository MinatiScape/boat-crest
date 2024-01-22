package com.google.android.recaptcha.internal;

import java.util.TimerTask;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.e;
/* loaded from: classes10.dex */
public final class zzal extends TimerTask {
    public final /* synthetic */ zzao zza;

    public zzal(zzao zzaoVar) {
        this.zza = zzaoVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        CoroutineScope coroutineScope;
        coroutineScope = this.zza.zzd;
        e.e(coroutineScope, null, null, new zzam(this.zza, null), 3, null);
    }
}
