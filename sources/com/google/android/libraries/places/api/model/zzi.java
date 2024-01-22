package com.google.android.libraries.places.api.model;

import android.os.ParcelUuid;
import androidx.annotation.NonNull;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class zzi extends AutocompleteSessionToken {
    private final ParcelUuid zza;

    public zzi(ParcelUuid parcelUuid) {
        Objects.requireNonNull(parcelUuid, "Null UUID");
        this.zza = parcelUuid;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AutocompleteSessionToken) {
            return this.zza.equals(((AutocompleteSessionToken) obj).zza());
        }
        return false;
    }

    public int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompleteSessionToken
    @NonNull
    public final ParcelUuid zza() {
        return this.zza;
    }
}
