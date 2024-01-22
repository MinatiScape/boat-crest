package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes8.dex */
public final class zzmd {

    /* renamed from: a  reason: collision with root package name */
    public final zzlz f9352a;
    public final zzmb b;
    public final zzmb c;
    public final Boolean d;

    public /* synthetic */ zzmd(zzma zzmaVar, zzmc zzmcVar) {
        zzlz zzlzVar;
        zzlzVar = zzmaVar.f9351a;
        this.f9352a = zzlzVar;
        this.b = null;
        this.c = null;
        this.d = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzmd) && Objects.equal(this.f9352a, ((zzmd) obj).f9352a) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9352a, null, null, null);
    }

    @Nullable
    @zzbo(zza = 1)
    public final zzlz zza() {
        return this.f9352a;
    }
}
