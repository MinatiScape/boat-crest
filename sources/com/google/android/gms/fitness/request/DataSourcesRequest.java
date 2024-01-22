package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SafeParcelable.Class(creator = "DataSourcesRequestCreator")
@SafeParcelable.Reserved({5, 1000})
/* loaded from: classes6.dex */
public class DataSourcesRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new zzp();
    @SafeParcelable.Field(getter = "getDataTypes", id = 1)
    public final List<DataType> h;
    @SafeParcelable.Field(getter = "getDataSourceTypes", id = 2)
    public final List<Integer> i;
    @SafeParcelable.Field(getter = "includeDbOnlySources", id = 3)
    public final boolean j;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    public final com.google.android.gms.internal.fitness.zzbh k;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<DataType> f8449a = new ArrayList();
        public List<Integer> b = Arrays.asList(0, 1);

        @NonNull
        public DataSourcesRequest build() {
            Preconditions.checkState(!this.f8449a.isEmpty(), "Must add at least one data type");
            Preconditions.checkState(!this.b.isEmpty(), "Must add at least one data source type");
            return new DataSourcesRequest(this);
        }

        @NonNull
        public Builder setDataSourceTypes(@NonNull int... iArr) {
            this.b = new ArrayList();
            for (int i : iArr) {
                this.b.add(Integer.valueOf(i));
            }
            return this;
        }

        @NonNull
        public Builder setDataTypes(@NonNull DataType... dataTypeArr) {
            this.f8449a = Arrays.asList(dataTypeArr);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public DataSourcesRequest(@SafeParcelable.Param(id = 1) List<DataType> list, @SafeParcelable.Param(id = 2) List<Integer> list2, @SafeParcelable.Param(id = 3) boolean z, @Nullable @SafeParcelable.Param(id = 4) IBinder iBinder) {
        this.h = list;
        this.i = list2;
        this.j = z;
        this.k = iBinder == null ? null : com.google.android.gms.internal.fitness.zzbg.zzd(iBinder);
    }

    @NonNull
    public List<DataType> getDataTypes() {
        return this.h;
    }

    @NonNull
    public String toString() {
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("dataTypes", this.h).add("sourceTypes", this.i);
        if (this.j) {
            add.add("includeDbOnlySources", "true");
        }
        return add.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.i, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.j);
        com.google.android.gms.internal.fitness.zzbh zzbhVar = this.k;
        SafeParcelWriter.writeIBinder(parcel, 4, zzbhVar == null ? null : zzbhVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public DataSourcesRequest(Builder builder) {
        this((List<DataType>) builder.f8449a, (List<Integer>) builder.b, false, (com.google.android.gms.internal.fitness.zzbh) null);
    }

    public DataSourcesRequest(DataSourcesRequest dataSourcesRequest, com.google.android.gms.internal.fitness.zzbh zzbhVar) {
        this(dataSourcesRequest.h, dataSourcesRequest.i, dataSourcesRequest.j, zzbhVar);
    }

    public DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, @Nullable com.google.android.gms.internal.fitness.zzbh zzbhVar) {
        this.h = list;
        this.i = list2;
        this.j = z;
        this.k = zzbhVar;
    }
}
