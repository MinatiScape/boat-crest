package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes6.dex */
public final class zzee {

    /* renamed from: a  reason: collision with root package name */
    public final zzks f9932a;
    public final Boolean b;
    public final Boolean c;
    public final zzkh d;
    public final zzmq e;

    public /* synthetic */ zzee(zzec zzecVar, zzed zzedVar) {
        zzks zzksVar;
        Boolean bool;
        zzmq zzmqVar;
        zzksVar = zzecVar.f9931a;
        this.f9932a = zzksVar;
        this.b = null;
        bool = zzecVar.b;
        this.c = bool;
        this.d = null;
        zzmqVar = zzecVar.c;
        this.e = zzmqVar;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzee) {
            zzee zzeeVar = (zzee) obj;
            return Objects.equal(this.f9932a, zzeeVar.f9932a) && Objects.equal(null, null) && Objects.equal(this.c, zzeeVar.c) && Objects.equal(null, null) && Objects.equal(this.e, zzeeVar.e);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9932a, null, this.c, null, this.e);
    }

    @Nullable
    @zzcz(zza = 1)
    public final zzks zza() {
        return this.f9932a;
    }

    @Nullable
    @zzcz(zza = 5)
    public final zzmq zzb() {
        return this.e;
    }

    @Nullable
    @zzcz(zza = 3)
    public final Boolean zzc() {
        return this.c;
    }
}
