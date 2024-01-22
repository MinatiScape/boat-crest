package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import com.jstyle.blesdk1860.constant.DeviceKey;
@SafeParcelable.Class(creator = "DataUpdateListenerRegistrationRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class DataUpdateListenerRegistrationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DataUpdateListenerRegistrationRequest> CREATOR = new zzw();
    @Nullable
    @SafeParcelable.Field(getter = "getDataSource", id = 1)
    public final DataSource h;
    @Nullable
    @SafeParcelable.Field(getter = "getDataType", id = 2)
    public final DataType i;
    @Nullable
    @SafeParcelable.Field(getter = "getIntent", id = 3)
    public final PendingIntent j;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    public final zzcn k;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public DataSource f8451a;
        public DataType b;
        public PendingIntent c;

        @NonNull
        public DataUpdateListenerRegistrationRequest build() {
            Preconditions.checkState((this.f8451a == null && this.b == null) ? false : true, "Set either dataSource or dataTYpe");
            Preconditions.checkNotNull(this.c, "pendingIntent must be set");
            return new DataUpdateListenerRegistrationRequest(this);
        }

        @NonNull
        public Builder setDataSource(@NonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource);
            this.f8451a = dataSource;
            return this;
        }

        @NonNull
        public Builder setDataType(@NonNull DataType dataType) {
            Preconditions.checkNotNull(dataType);
            this.b = dataType;
            return this;
        }

        @NonNull
        public Builder setPendingIntent(@NonNull PendingIntent pendingIntent) {
            Preconditions.checkNotNull(pendingIntent);
            this.c = pendingIntent;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public DataUpdateListenerRegistrationRequest(@Nullable @SafeParcelable.Param(id = 1) DataSource dataSource, @Nullable @SafeParcelable.Param(id = 2) DataType dataType, @Nullable @SafeParcelable.Param(id = 3) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 4) IBinder iBinder) {
        this.h = dataSource;
        this.i = dataType;
        this.j = pendingIntent;
        this.k = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DataUpdateListenerRegistrationRequest) {
            DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest = (DataUpdateListenerRegistrationRequest) obj;
            return Objects.equal(this.h, dataUpdateListenerRegistrationRequest.h) && Objects.equal(this.i, dataUpdateListenerRegistrationRequest.i) && Objects.equal(this.j, dataUpdateListenerRegistrationRequest.j);
        }
        return false;
    }

    @Nullable
    public DataSource getDataSource() {
        return this.h;
    }

    @Nullable
    public DataType getDataType() {
        return this.i;
    }

    @Nullable
    public PendingIntent getIntent() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.h).add(DeviceKey.DataType, this.i).add(BaseGmsClient.KEY_PENDING_INTENT, this.j).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIntent(), i, false);
        zzcn zzcnVar = this.k;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public DataUpdateListenerRegistrationRequest(@NonNull DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest, @Nullable IBinder iBinder) {
        this(dataUpdateListenerRegistrationRequest.h, dataUpdateListenerRegistrationRequest.i, dataUpdateListenerRegistrationRequest.j, iBinder);
    }

    public DataUpdateListenerRegistrationRequest(Builder builder) {
        this(builder.f8451a, builder.b, builder.c, null);
    }
}
