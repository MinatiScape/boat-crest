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
import com.google.android.gms.fitness.data.DataType;
import com.jstyle.blesdk1860.constant.DeviceKey;
@SafeParcelable.Class(creator = "DataTypeResultCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* loaded from: classes6.dex */
public class DataTypeResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<DataTypeResult> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getStatus", id = 1)
    public final Status h;
    @Nullable
    @SafeParcelable.Field(getter = "getDataType", id = 3)
    public final DataType i;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public DataTypeResult(@NonNull @SafeParcelable.Param(id = 1) Status status, @Nullable @SafeParcelable.Param(id = 3) DataType dataType) {
        this.h = status;
        this.i = dataType;
    }

    @NonNull
    @ShowFirstParty
    public static DataTypeResult zzc(@NonNull Status status) {
        return new DataTypeResult(status, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DataTypeResult) {
            DataTypeResult dataTypeResult = (DataTypeResult) obj;
            return this.h.equals(dataTypeResult.h) && Objects.equal(this.i, dataTypeResult.i);
        }
        return false;
    }

    @Nullable
    public DataType getDataType() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.h).add(DeviceKey.DataType, this.i).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
