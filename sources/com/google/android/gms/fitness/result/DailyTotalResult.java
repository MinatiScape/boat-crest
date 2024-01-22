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
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
@SafeParcelable.Class(creator = "DailyTotalResultCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class DailyTotalResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<DailyTotalResult> CREATOR = new zzb();
    @SafeParcelable.Field(getter = "getStatus", id = 1)
    public final Status h;
    @Nullable
    @SafeParcelable.Field(getter = "getTotal", id = 2)
    public final DataSet i;

    @SafeParcelable.Constructor
    public DailyTotalResult(@NonNull @SafeParcelable.Param(id = 1) Status status, @Nullable @SafeParcelable.Param(id = 2) DataSet dataSet) {
        this.h = status;
        this.i = dataSet;
    }

    @NonNull
    @ShowFirstParty
    public static DailyTotalResult zza(@NonNull Status status, @NonNull DataType dataType) {
        return new DailyTotalResult(status, DataSet.builder(new DataSource.Builder().setType(1).setDataType(dataType).build()).build());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DailyTotalResult) {
            DailyTotalResult dailyTotalResult = (DailyTotalResult) obj;
            return this.h.equals(dailyTotalResult.h) && Objects.equal(this.i, dailyTotalResult.i);
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    @Nullable
    public DataSet getTotal() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.h).add("dataPoint", this.i).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getTotal(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
