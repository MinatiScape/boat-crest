package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
@SafeParcelable.Class(creator = "LocationSettingsStatesCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public final class LocationSettingsStates extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzab();
    @SafeParcelable.Field(getter = "isGpsUsable", id = 1)
    public final boolean h;
    @SafeParcelable.Field(getter = "isNetworkLocationUsable", id = 2)
    public final boolean i;
    @SafeParcelable.Field(getter = "isBleUsable", id = 3)
    public final boolean j;
    @SafeParcelable.Field(getter = "isGpsPresent", id = 4)
    public final boolean k;
    @SafeParcelable.Field(getter = "isNetworkLocationPresent", id = 5)
    public final boolean l;
    @SafeParcelable.Field(getter = "isBlePresent", id = 6)
    public final boolean m;

    @SafeParcelable.Constructor
    public LocationSettingsStates(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) boolean z2, @SafeParcelable.Param(id = 3) boolean z3, @SafeParcelable.Param(id = 4) boolean z4, @SafeParcelable.Param(id = 5) boolean z5, @SafeParcelable.Param(id = 6) boolean z6) {
        this.h = z;
        this.i = z2;
        this.j = z3;
        this.k = z4;
        this.l = z5;
        this.m = z6;
    }

    @Nullable
    public static LocationSettingsStates fromIntent(@NonNull Intent intent) {
        return (LocationSettingsStates) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
    }

    public boolean isBlePresent() {
        return this.m;
    }

    public boolean isBleUsable() {
        return this.j;
    }

    public boolean isGpsPresent() {
        return this.k;
    }

    public boolean isGpsUsable() {
        return this.h;
    }

    public boolean isLocationPresent() {
        return this.k || this.l;
    }

    public boolean isLocationUsable() {
        return this.h || this.i;
    }

    public boolean isNetworkLocationPresent() {
        return this.l;
    }

    public boolean isNetworkLocationUsable() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, isGpsUsable());
        SafeParcelWriter.writeBoolean(parcel, 2, isNetworkLocationUsable());
        SafeParcelWriter.writeBoolean(parcel, 3, isBleUsable());
        SafeParcelWriter.writeBoolean(parcel, 4, isGpsPresent());
        SafeParcelWriter.writeBoolean(parcel, 5, isNetworkLocationPresent());
        SafeParcelWriter.writeBoolean(parcel, 6, isBlePresent());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
