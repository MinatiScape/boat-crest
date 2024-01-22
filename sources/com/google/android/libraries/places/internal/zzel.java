package com.google.android.libraries.places.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.CancellationTokenSource;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzel extends zzeq {
    private final CancellationTokenSource zza;
    private final String zzb;

    public zzel(CancellationTokenSource cancellationTokenSource, String str) {
        this.zza = cancellationTokenSource;
        Objects.requireNonNull(str, "Null placeId");
        this.zzb = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzeq) {
            zzeq zzeqVar = (zzeq) obj;
            if (this.zza.equals(zzeqVar.zza()) && this.zzb.equals(zzeqVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(valueOf.length() + 31 + String.valueOf(str).length());
        sb.append("PlaceRequest{source=");
        sb.append(valueOf);
        sb.append(", placeId=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzet
    @NonNull
    public final CancellationTokenSource zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzeq
    @NonNull
    public final String zzb() {
        return this.zzb;
    }
}
