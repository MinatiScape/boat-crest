package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "BleDevicesResultCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* loaded from: classes6.dex */
public class BleDevicesResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<BleDevicesResult> CREATOR = new zza();
    @SafeParcelable.Field(getter = "getClaimedBleDevices", id = 1)
    public final List<BleDevice> h;
    @SafeParcelable.Field(getter = "getStatus", id = 2)
    public final Status i;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public BleDevicesResult(@NonNull @SafeParcelable.Param(id = 1) List<BleDevice> list, @NonNull @SafeParcelable.Param(id = 2) Status status) {
        this.h = Collections.unmodifiableList(list);
        this.i = status;
    }

    @NonNull
    @ShowFirstParty
    public static BleDevicesResult zzb(@NonNull Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleDevicesResult) {
            BleDevicesResult bleDevicesResult = (BleDevicesResult) obj;
            return this.i.equals(bleDevicesResult.i) && Objects.equal(this.h, bleDevicesResult.h);
        }
        return false;
    }

    @NonNull
    public List<BleDevice> getClaimedBleDevices() {
        return this.h;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.i, this.h);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.i).add("bleDevices", this.h).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getClaimedBleDevices(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public List<BleDevice> getClaimedBleDevices(@NonNull DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : this.h) {
            if (bleDevice.getDataTypes().contains(dataType)) {
                arrayList.add(bleDevice);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }
}
