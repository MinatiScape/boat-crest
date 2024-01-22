package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class AddressComponents implements Parcelable {
    @NonNull
    public static AddressComponents newInstance(@NonNull List<AddressComponent> list) {
        return new zzab(list);
    }

    @NonNull
    public abstract List<AddressComponent> asList();
}
