package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
@SafeParcelable.Class(creator = "BleDeviceCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* loaded from: classes6.dex */
public class BleDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<BleDevice> CREATOR = new zzd();
    @SafeParcelable.Field(getter = "getAddress", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getName", id = 2)
    public final String i;
    @SafeParcelable.Field(getter = "getSupportedProfiles", id = 3)
    public final List<String> j;
    @SafeParcelable.Field(getter = "getDataTypes", id = 4)
    public final List<DataType> k;

    @SafeParcelable.Constructor
    public BleDevice(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) List<String> list, @SafeParcelable.Param(id = 4) List<DataType> list2) {
        this.h = str;
        this.i = str2;
        this.j = Collections.unmodifiableList(list);
        this.k = Collections.unmodifiableList(list2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BleDevice) {
            BleDevice bleDevice = (BleDevice) obj;
            return this.i.equals(bleDevice.i) && this.h.equals(bleDevice.h) && new HashSet(this.j).equals(new HashSet(bleDevice.j)) && new HashSet(this.k).equals(new HashSet(bleDevice.k));
        }
        return false;
    }

    @NonNull
    public String getAddress() {
        return this.h;
    }

    @NonNull
    public List<DataType> getDataTypes() {
        return this.k;
    }

    @NonNull
    public String getName() {
        return this.i;
    }

    @NonNull
    public List<String> getSupportedProfiles() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(this.i, this.h, this.j, this.k);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.i).add("address", this.h).add("dataTypes", this.k).add("supportedProfiles", this.j).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAddress(), false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getSupportedProfiles(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
