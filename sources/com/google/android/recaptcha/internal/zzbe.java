package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbe {
    @NotNull
    private final zzdx zza;

    public zzbe() {
        this(1);
    }

    @NotNull
    public final List zwk() {
        return zza();
    }

    @NotNull
    public final List zza() {
        return CollectionsKt___CollectionsKt.toList(this.zza);
    }

    public final boolean zzb(@NotNull List list) {
        this.zza.add(list);
        return true;
    }

    public zzbe(int i) {
        this.zza = zzdx.zza(i);
    }
}
