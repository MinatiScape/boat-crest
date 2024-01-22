package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbh {
    public zzm zza;
    @NotNull
    private final zzbj zzb;
    private int zzd;
    @NotNull
    private final zzbi zze;
    @NotNull
    private final zzbb zzf;
    @NotNull
    private final zzn zzg;
    @NotNull
    private String zzc = "recaptcha.m.Main.rge";
    @NotNull
    private final zzm zzh = new zzm();

    public zzbh(@NotNull zzbj zzbjVar) {
        this.zzb = zzbjVar;
        this.zze = zzbjVar.zzd();
        this.zzf = zzbjVar.zzc();
        this.zzg = zzbjVar.zzb();
    }

    public final byte zza() {
        return this.zzb.zza();
    }

    public final int zzb() {
        return this.zzd;
    }

    @NotNull
    public final zzm zzc() {
        return this.zzh;
    }

    @NotNull
    public final zzbb zzd() {
        return this.zzf;
    }

    @NotNull
    public final zzbi zze() {
        return this.zze;
    }

    @NotNull
    public final String zzf() {
        return this.zzc;
    }

    public final void zzg() {
        this.zzb.zze();
    }

    public final void zzh(@NotNull String str) {
        this.zzc = str;
    }

    public final void zzi(int i) {
        this.zzd = i;
    }

    public final void zzj(byte b) {
        this.zzb.zzf(b);
    }
}
