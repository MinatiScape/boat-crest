package com.google.android.recaptcha.internal;

import kotlin.comparisons.f;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzi implements Comparable {
    private int zza;
    private long zzb;
    private long zzc;

    @NotNull
    public final String toString() {
        String padEnd$default = StringsKt__StringsKt.padEnd$default(String.valueOf(this.zzb / this.zza), 10, (char) 0, 2, (Object) null);
        String padEnd$default2 = StringsKt__StringsKt.padEnd$default(String.valueOf(this.zzc), 10, (char) 0, 2, (Object) null);
        String padEnd$default3 = StringsKt__StringsKt.padEnd$default(String.valueOf(this.zzb), 10, (char) 0, 2, (Object) null);
        String padEnd$default4 = StringsKt__StringsKt.padEnd$default(String.valueOf(this.zza), 5, (char) 0, 2, (Object) null);
        return "avgExecutionTime: " + padEnd$default + " us| maxExecutionTime: " + padEnd$default2 + " us| totalTime: " + padEnd$default3 + " us| #Usages: " + padEnd$default4;
    }

    @Override // java.lang.Comparable
    /* renamed from: zza */
    public final int compareTo(@NotNull zzi zziVar) {
        return f.compareValues(Long.valueOf(this.zzb), Long.valueOf(zziVar.zzb));
    }

    public final int zzb() {
        return this.zza;
    }

    public final long zzc() {
        return this.zzc;
    }

    public final long zzd() {
        return this.zzb;
    }

    public final void zze(long j) {
        this.zzc = j;
    }

    public final void zzf(long j) {
        this.zzb = j;
    }

    public final void zzg(int i) {
        this.zza = i;
    }
}
