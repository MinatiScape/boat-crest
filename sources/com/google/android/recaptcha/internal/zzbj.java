package com.google.android.recaptcha.internal;

import java.util.HashMap;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbj {
    @NotNull
    private final zzbb zza;
    @NotNull
    private final zzn zzb;
    @NotNull
    private final zzbi zzc;
    private byte zzd;
    @NotNull
    private final HashMap zze;

    public zzbj(@NotNull zzbb zzbbVar, @NotNull zzn zznVar) {
        this.zza = zzbbVar;
        this.zzb = zznVar;
        zzbi zzbiVar = new zzbi();
        this.zzc = zzbiVar;
        this.zzd = (byte) h.random(new IntRange(1, 127), Random.Default);
        HashMap hashMap = new HashMap();
        this.zze = hashMap;
        zzbiVar.zze(173, hashMap);
    }

    public final byte zza() {
        return this.zzd;
    }

    @NotNull
    public final zzn zzb() {
        return this.zzb;
    }

    @NotNull
    public final zzbb zzc() {
        return this.zza;
    }

    @NotNull
    public final zzbi zzd() {
        return this.zzc;
    }

    public final void zze() {
        this.zzc.zzd();
        this.zzc.zze(173, this.zze);
    }

    public final void zzf(byte b) {
        this.zzd = b;
    }

    public final void zzg(@NotNull int i, @NotNull Object obj) {
        this.zze.put(Integer.valueOf(i - 2), obj);
    }
}
