package com.google.android.libraries.places.api.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
abstract class zza extends AddressComponent {
    private final String zza;
    private final String zzb;
    private final List<String> zzc;

    public zza(String str, @Nullable String str2, List<String> list) {
        Objects.requireNonNull(str, "Null name");
        this.zza = str;
        this.zzb = str2;
        Objects.requireNonNull(list, "Null types");
        this.zzc = list;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddressComponent) {
            AddressComponent addressComponent = (AddressComponent) obj;
            if (this.zza.equals(addressComponent.getName()) && ((str = this.zzb) != null ? str.equals(addressComponent.getShortName()) : addressComponent.getShortName() == null) && this.zzc.equals(addressComponent.getTypes())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent
    @NonNull
    public String getName() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent
    @Nullable
    public String getShortName() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent
    @NonNull
    public List<String> getTypes() {
        return this.zzc;
    }

    public int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        String str = this.zzb;
        return ((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.zzc.hashCode();
    }

    public String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String valueOf = String.valueOf(this.zzc);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str2).length() + valueOf.length());
        sb.append("AddressComponent{name=");
        sb.append(str);
        sb.append(", shortName=");
        sb.append(str2);
        sb.append(", types=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
