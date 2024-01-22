package com.google.android.libraries.places.api.model;

import androidx.annotation.NonNull;
import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class zzb extends AddressComponents {
    private final List<AddressComponent> zza;

    public zzb(List<AddressComponent> list) {
        Objects.requireNonNull(list, "Null asList");
        this.zza = list;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponents
    @NonNull
    public List<AddressComponent> asList() {
        return this.zza;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddressComponents) {
            return this.zza.equals(((AddressComponents) obj).asList());
        }
        return false;
    }

    public int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(valueOf.length() + 26);
        sb.append("AddressComponents{asList=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
