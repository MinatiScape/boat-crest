package com.google.android.libraries.places.api.model;

import android.os.ParcelUuid;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import java.util.UUID;
@AutoValue
/* loaded from: classes10.dex */
public abstract class AutocompleteSessionToken implements Parcelable {
    @NonNull
    public static AutocompleteSessionToken newInstance() {
        return new zzah(new ParcelUuid(UUID.randomUUID()));
    }

    @NonNull
    public final String toString() {
        return zza().toString();
    }

    @NonNull
    public abstract ParcelUuid zza();
}
