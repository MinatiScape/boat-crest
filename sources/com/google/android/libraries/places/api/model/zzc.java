package com.google.android.libraries.places.api.model;

import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.AddressComponent;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzc extends AddressComponent.Builder {
    private String zza;
    private String zzb;
    private List<String> zzc;

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    @Nullable
    public final String getShortName() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    public final AddressComponent.Builder setShortName(@Nullable String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    public final AddressComponent.Builder zza(String str) {
        Objects.requireNonNull(str, "Null name");
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    public final AddressComponent.Builder zza(List<String> list) {
        Objects.requireNonNull(list, "Null types");
        this.zzc = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    public final AddressComponent zza() {
        String concat = this.zza == null ? "".concat(" name") : "";
        if (this.zzc == null) {
            concat = String.valueOf(concat).concat(" types");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzz(this.zza, this.zzb, this.zzc);
    }
}
