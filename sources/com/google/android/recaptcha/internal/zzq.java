package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzq {
    @NotNull
    private final String zza;
    @NotNull
    private final String zzb;
    @NotNull
    private final String zzc;
    @NotNull
    private final String zzd;

    public zzq() {
        this("https://www.recaptcha.net/recaptcha/api3");
    }

    public zzq(@NotNull String str) {
        this.zza = "https://www.recaptcha.net/recaptcha/api3";
        this.zzb = "https://www.recaptcha.net/recaptcha/api3".concat("/mwv");
        this.zzc = "https://www.recaptcha.net/recaptcha/api3".concat("/mlg");
        this.zzd = "https://www.recaptcha.net/recaptcha/api3".concat("/mal");
    }

    @NotNull
    public final String zza() {
        return this.zzb;
    }

    @NotNull
    public final String zzb() {
        return this.zzd;
    }

    @NotNull
    public final String zzc() {
        return this.zzc;
    }
}
