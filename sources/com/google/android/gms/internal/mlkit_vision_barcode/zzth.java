package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes9.dex */
public final class zzth {

    /* renamed from: a  reason: collision with root package name */
    public final zzcv f9574a;

    public /* synthetic */ zzth(zztf zztfVar, zztg zztgVar) {
        zzcv zzcvVar;
        zzcvVar = zztfVar.f9573a;
        this.f9574a = zzcvVar;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzth) {
            return Objects.equal(this.f9574a, ((zzth) obj).f9574a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9574a);
    }

    @Nullable
    @zzfg(zza = 1)
    public final zzcv zza() {
        return this.f9574a;
    }
}
