package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes6.dex */
public final class zzkh {

    /* renamed from: a  reason: collision with root package name */
    public final zzkf f9937a;
    public final Integer b;
    public final Integer c;
    public final Boolean d;

    public /* synthetic */ zzkh(zzke zzkeVar, zzkg zzkgVar) {
        zzkf zzkfVar;
        Integer num;
        zzkfVar = zzkeVar.f9936a;
        this.f9937a = zzkfVar;
        num = zzkeVar.b;
        this.b = num;
        this.c = null;
        this.d = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzkh) {
            zzkh zzkhVar = (zzkh) obj;
            return Objects.equal(this.f9937a, zzkhVar.f9937a) && Objects.equal(this.b, zzkhVar.b) && Objects.equal(null, null) && Objects.equal(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9937a, this.b, null, null);
    }

    @Nullable
    @zzcz(zza = 1)
    public final zzkf zza() {
        return this.f9937a;
    }

    @Nullable
    @zzcz(zza = 2)
    public final Integer zzb() {
        return this.b;
    }
}
