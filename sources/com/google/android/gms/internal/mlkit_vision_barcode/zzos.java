package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes9.dex */
public final class zzos {

    /* renamed from: a  reason: collision with root package name */
    public final zzoq f9558a;
    public final Integer b;
    public final Integer c;
    public final Boolean d;

    public /* synthetic */ zzos(zzop zzopVar, zzor zzorVar) {
        zzoq zzoqVar;
        Integer num;
        zzoqVar = zzopVar.f9557a;
        this.f9558a = zzoqVar;
        num = zzopVar.b;
        this.b = num;
        this.c = null;
        this.d = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzos) {
            zzos zzosVar = (zzos) obj;
            return Objects.equal(this.f9558a, zzosVar.f9558a) && Objects.equal(this.b, zzosVar.b) && Objects.equal(null, null) && Objects.equal(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9558a, this.b, null, null);
    }

    @Nullable
    @zzfg(zza = 1)
    public final zzoq zza() {
        return this.f9558a;
    }

    @Nullable
    @zzfg(zza = 2)
    public final Integer zzb() {
        return this.b;
    }
}
