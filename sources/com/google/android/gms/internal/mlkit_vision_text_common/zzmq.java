package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes6.dex */
public final class zzmq {

    /* renamed from: a  reason: collision with root package name */
    public final zzmo f9947a;
    public final Boolean b;
    public final String c;

    public /* synthetic */ zzmq(zzmn zzmnVar, zzmp zzmpVar) {
        zzmo zzmoVar;
        zzmoVar = zzmnVar.f9946a;
        this.f9947a = zzmoVar;
        this.b = null;
        this.c = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzmq) && Objects.equal(this.f9947a, ((zzmq) obj).f9947a) && Objects.equal(null, null) && Objects.equal(null, null);
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9947a, null, null);
    }

    @Nullable
    @zzcz(zza = 3)
    public final zzmo zza() {
        return this.f9947a;
    }
}
