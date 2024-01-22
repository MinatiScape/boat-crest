package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
@SafeParcelable.Class(creator = "LocationAvailabilityCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", getter = "getCellStatus", id = 1)
    public final int h;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", getter = "getWifiStatus", id = 2)
    public final int i;
    @SafeParcelable.Field(defaultValueUnchecked = BleConst.GetDeviceTime, getter = "getElapsedRealtimeNs", id = 3)
    public final long j;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNSUCCESSFUL", id = 4)
    public int k;
    @SafeParcelable.Field(getter = "getBatchedStatus", id = 5)
    public final zzac[] l;
    @NonNull
    public static final LocationAvailability zza = new LocationAvailability(0, 1, 1, 0, null, true);
    @NonNull
    public static final LocationAvailability zzb = new LocationAvailability(1000, 1, 1, 0, null, false);
    @NonNull
    public static final Parcelable.Creator<LocationAvailability> CREATOR = new zzw();

    @SafeParcelable.Constructor
    public LocationAvailability(@SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 5) zzac[] zzacVarArr, @SafeParcelable.Param(id = 6) boolean z) {
        this.k = i < 1000 ? 0 : 1000;
        this.h = i2;
        this.i = i3;
        this.j = j;
        this.l = zzacVarArr;
    }

    @Nullable
    public static LocationAvailability extractLocationAvailability(@NonNull Intent intent) {
        if (hasLocationAvailability(intent)) {
            try {
                return (LocationAvailability) intent.getParcelableExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public static boolean hasLocationAvailability(@Nullable Intent intent) {
        return intent != null && intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LocationAvailability) {
            LocationAvailability locationAvailability = (LocationAvailability) obj;
            if (this.h == locationAvailability.h && this.i == locationAvailability.i && this.j == locationAvailability.j && this.k == locationAvailability.k && Arrays.equals(this.l, locationAvailability.l)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.k));
    }

    public boolean isLocationAvailable() {
        return this.k < 1000;
    }

    @NonNull
    public String toString() {
        boolean isLocationAvailable = isLocationAvailable();
        return "LocationAvailability[" + isLocationAvailable + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeInt(parcel, 2, this.i);
        SafeParcelWriter.writeLong(parcel, 3, this.j);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.l, i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, isLocationAvailable());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
