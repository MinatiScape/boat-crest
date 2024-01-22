package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "DataUpdateNotificationCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class DataUpdateNotification extends AbstractSafeParcelable {
    @NonNull
    public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
    @NonNull
    public static final Parcelable.Creator<DataUpdateNotification> CREATOR = new zzn();
    @NonNull
    public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
    public static final int OPERATION_DELETE = 2;
    public static final int OPERATION_INSERT = 1;
    public static final int OPERATION_UPDATE = 3;
    @SafeParcelable.Field(getter = "getUpdateStartTimeNanos", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getUpdateEndTimeNanos", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getOperationType", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getDataSource", id = 4)
    public final DataSource k;
    @SafeParcelable.Field(getter = "getDataType", id = 5)
    public final DataType l;

    @SafeParcelable.Constructor
    public DataUpdateNotification(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) int i, @NonNull @SafeParcelable.Param(id = 4) DataSource dataSource, @NonNull @SafeParcelable.Param(id = 5) DataType dataType) {
        this.h = j;
        this.i = j2;
        this.j = i;
        this.k = dataSource;
        this.l = dataType;
    }

    @Nullable
    public static DataUpdateNotification getDataUpdateNotification(@NonNull Intent intent) {
        return (DataUpdateNotification) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_UPDATE_NOTIFICATION, CREATOR);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataUpdateNotification) {
            DataUpdateNotification dataUpdateNotification = (DataUpdateNotification) obj;
            return this.h == dataUpdateNotification.h && this.i == dataUpdateNotification.i && this.j == dataUpdateNotification.j && Objects.equal(this.k, dataUpdateNotification.k) && Objects.equal(this.l, dataUpdateNotification.l);
        }
        return false;
    }

    @NonNull
    public DataSource getDataSource() {
        return this.k;
    }

    @NonNull
    public DataType getDataType() {
        return this.l;
    }

    public int getOperationType() {
        return this.j;
    }

    public long getUpdateEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.i, TimeUnit.NANOSECONDS);
    }

    public long getUpdateStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.h, TimeUnit.NANOSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Long.valueOf(this.i), Integer.valueOf(this.j), this.k, this.l);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("updateStartTimeNanos", Long.valueOf(this.h)).add("updateEndTimeNanos", Long.valueOf(this.i)).add("operationType", Integer.valueOf(this.j)).add("dataSource", this.k).add(DeviceKey.DataType, this.l).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeInt(parcel, 3, getOperationType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
