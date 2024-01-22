package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "LocationSettingsResultCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<LocationSettingsResult> CREATOR = new zzaa();
    @SafeParcelable.Field(getter = "getStatus", id = 1)
    public final Status h;
    @Nullable
    @SafeParcelable.Field(getter = "getLocationSettingsStates", id = 2)
    public final LocationSettingsStates i;

    @SafeParcelable.Constructor
    public LocationSettingsResult(@NonNull @SafeParcelable.Param(id = 1) Status status, @Nullable @SafeParcelable.Param(id = 2) LocationSettingsStates locationSettingsStates) {
        this.h = status;
        this.i = locationSettingsStates;
    }

    @Nullable
    public LocationSettingsStates getLocationSettingsStates() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getLocationSettingsStates(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
