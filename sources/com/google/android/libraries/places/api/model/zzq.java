package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzq extends PhotoMetadata.Builder {
    private String zza;
    private Integer zzb;
    private Integer zzc;
    private String zzd;

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final String getAttributions() {
        String str = this.zza;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Property \"attributions\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final int getHeight() {
        Integer num = this.zzb;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Property \"height\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final int getWidth() {
        Integer num = this.zzc;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Property \"width\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata.Builder setAttributions(String str) {
        Objects.requireNonNull(str, "Null attributions");
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata.Builder setHeight(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata.Builder setWidth(int i) {
        this.zzc = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata.Builder zza(String str) {
        Objects.requireNonNull(str, "Null photoReference");
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata zza() {
        String concat = this.zza == null ? "".concat(" attributions") : "";
        if (this.zzb == null) {
            concat = String.valueOf(concat).concat(" height");
        }
        if (this.zzc == null) {
            concat = String.valueOf(concat).concat(" width");
        }
        if (this.zzd == null) {
            concat = String.valueOf(concat).concat(" photoReference");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzap(this.zza, this.zzb.intValue(), this.zzc.intValue(), this.zzd);
    }
}
