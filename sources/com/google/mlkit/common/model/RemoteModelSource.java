package com.google.mlkit.common.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_common.zzaa;
import com.google.android.gms.internal.mlkit_common.zzz;
/* loaded from: classes10.dex */
public abstract class RemoteModelSource {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f11582a;

    @KeepForSdk
    public RemoteModelSource(@Nullable String str) {
        this.f11582a = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null) {
            if (obj.getClass().equals(getClass())) {
                return Objects.equal(this.f11582a, ((RemoteModelSource) obj).f11582a);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11582a);
    }

    @NonNull
    public String toString() {
        zzz zzb = zzaa.zzb("RemoteModelSource");
        zzb.zza("firebaseModelName", this.f11582a);
        return zzb.toString();
    }

    @Nullable
    public final String zza() {
        return this.f11582a;
    }
}
