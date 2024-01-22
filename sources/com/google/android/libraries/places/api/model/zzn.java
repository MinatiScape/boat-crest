package com.google.android.libraries.places.api.model;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes10.dex */
abstract class zzn extends PhotoMetadata {
    private final String zza;
    private final int zzb;
    private final int zzc;
    private final String zzd;

    public zzn(String str, int i, int i2, String str2) {
        Objects.requireNonNull(str, "Null attributions");
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
        Objects.requireNonNull(str2, "Null photoReference");
        this.zzd = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PhotoMetadata) {
            PhotoMetadata photoMetadata = (PhotoMetadata) obj;
            if (this.zza.equals(photoMetadata.getAttributions()) && this.zzb == photoMetadata.getHeight() && this.zzc == photoMetadata.getWidth() && this.zzd.equals(photoMetadata.zza())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    @NonNull
    public String getAttributions() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    @IntRange(from = 0)
    public int getHeight() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    @IntRange(from = 0)
    public int getWidth() {
        return this.zzc;
    }

    public int hashCode() {
        return ((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc) * 1000003) ^ this.zzd.hashCode();
    }

    public String toString() {
        String str = this.zza;
        int i = this.zzb;
        int i2 = this.zzc;
        String str2 = this.zzd;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 84 + String.valueOf(str2).length());
        sb.append("PhotoMetadata{attributions=");
        sb.append(str);
        sb.append(", height=");
        sb.append(i);
        sb.append(", width=");
        sb.append(i2);
        sb.append(", photoReference=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    @NonNull
    public final String zza() {
        return this.zzd;
    }
}
