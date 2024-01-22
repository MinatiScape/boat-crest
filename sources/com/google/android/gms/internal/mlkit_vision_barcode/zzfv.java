package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes9.dex */
public final class zzfv {

    /* renamed from: a  reason: collision with root package name */
    public final zzpj f9552a;
    public final Boolean c;
    public final zzth e;
    public final zzcv f;
    public final zzcv g;
    public final Boolean b = null;
    public final zzos d = null;

    public /* synthetic */ zzfv(zzft zzftVar, zzfu zzfuVar) {
        this.f9552a = zzft.c(zzftVar);
        this.c = zzft.e(zzftVar);
        this.e = zzft.d(zzftVar);
        this.f = zzft.a(zzftVar);
        this.g = zzft.b(zzftVar);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfv) {
            zzfv zzfvVar = (zzfv) obj;
            return Objects.equal(this.f9552a, zzfvVar.f9552a) && Objects.equal(null, null) && Objects.equal(this.c, zzfvVar.c) && Objects.equal(null, null) && Objects.equal(this.e, zzfvVar.e) && Objects.equal(this.f, zzfvVar.f) && Objects.equal(this.g, zzfvVar.g);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9552a, null, this.c, null, this.e, this.f, this.g);
    }

    @Nullable
    @zzfg(zza = 6)
    public final zzcv zza() {
        return this.f;
    }

    @Nullable
    @zzfg(zza = 7)
    public final zzcv zzb() {
        return this.g;
    }

    @Nullable
    @zzfg(zza = 1)
    public final zzpj zzc() {
        return this.f9552a;
    }

    @Nullable
    @zzfg(zza = 5)
    public final zzth zzd() {
        return this.e;
    }

    @Nullable
    @zzfg(zza = 3)
    public final Boolean zze() {
        return this.c;
    }
}
