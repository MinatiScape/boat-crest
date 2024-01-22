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
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
@SafeParcelable.Class(creator = "DataReadResultCreator")
@SafeParcelable.Reserved({7, 1000})
/* loaded from: classes6.dex */
public class DataReadResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<DataReadResult> CREATOR = new zzc();
    @SafeParcelable.Field(getter = "getRawDataSets", id = 1, type = "java.util.List")
    public final List<DataSet> h;
    @SafeParcelable.Field(getter = "getStatus", id = 2)
    public final Status i;
    @SafeParcelable.Field(getter = "getRawBuckets", id = 3, type = "java.util.List")
    public final List<Bucket> j;
    @SafeParcelable.Field(getter = "getBatchCount", id = 5)
    public int k;
    @SafeParcelable.Field(getter = "getUniqueDataSources", id = 6)
    public final List<DataSource> l;

    @SafeParcelable.Constructor
    public DataReadResult(@SafeParcelable.Param(id = 1) List<RawDataSet> list, @SafeParcelable.Param(id = 2) Status status, @SafeParcelable.Param(id = 3) List<RawBucket> list2, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) List<DataSource> list3) {
        this.i = status;
        this.k = i;
        this.l = list3;
        this.h = new ArrayList(list.size());
        for (RawDataSet rawDataSet : list) {
            this.h.add(new DataSet(rawDataSet, list3));
        }
        this.j = new ArrayList(list2.size());
        for (RawBucket rawBucket : list2) {
            this.j.add(new Bucket(rawBucket, list3));
        }
    }

    public static void a(DataSet dataSet, List<DataSet> list) {
        for (DataSet dataSet2 : list) {
            if (dataSet2.getDataSource().equals(dataSet.getDataSource())) {
                dataSet2.zza(dataSet.getDataPoints());
                return;
            }
        }
        list.add(dataSet);
    }

    @NonNull
    @ShowFirstParty
    public static DataReadResult zza(@NonNull Status status, @NonNull List<DataType> list, @NonNull List<DataSource> list2) {
        ArrayList arrayList = new ArrayList();
        for (DataSource dataSource : list2) {
            arrayList.add(DataSet.builder(dataSource).build());
        }
        for (DataType dataType : list) {
            arrayList.add(DataSet.builder(new DataSource.Builder().setType(1).setDataType(dataType).setStreamName("Default").build()).build());
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DataReadResult) {
            DataReadResult dataReadResult = (DataReadResult) obj;
            return this.i.equals(dataReadResult.i) && Objects.equal(this.h, dataReadResult.h) && Objects.equal(this.j, dataReadResult.j);
        }
        return false;
    }

    @NonNull
    public List<Bucket> getBuckets() {
        return this.j;
    }

    @NonNull
    public DataSet getDataSet(@NonNull DataType dataType) {
        for (DataSet dataSet : this.h) {
            if (dataType.equals(dataSet.getDataType())) {
                return dataSet;
            }
        }
        return DataSet.builder(new DataSource.Builder().setType(1).setDataType(dataType).build()).build();
    }

    @NonNull
    public List<DataSet> getDataSets() {
        return this.h;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.i, this.h, this.j);
    }

    @NonNull
    public String toString() {
        Object obj;
        Object obj2;
        Objects.ToStringHelper add = Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.i);
        if (this.h.size() > 5) {
            int size = this.h.size();
            StringBuilder sb = new StringBuilder(21);
            sb.append(size);
            sb.append(" data sets");
            obj = sb.toString();
        } else {
            obj = this.h;
        }
        Objects.ToStringHelper add2 = add.add("dataSets", obj);
        if (this.j.size() > 5) {
            int size2 = this.j.size();
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append(size2);
            sb2.append(" buckets");
            obj2 = sb2.toString();
        } else {
            obj2 = this.j;
        }
        return add2.add("buckets", obj2).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        ArrayList arrayList = new ArrayList(this.h.size());
        for (DataSet dataSet : this.h) {
            arrayList.add(new RawDataSet(dataSet, this.l));
        }
        SafeParcelWriter.writeList(parcel, 1, arrayList, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        ArrayList arrayList2 = new ArrayList(this.j.size());
        for (Bucket bucket : this.j) {
            arrayList2.add(new RawBucket(bucket, this.l));
        }
        SafeParcelWriter.writeList(parcel, 3, arrayList2, false);
        SafeParcelWriter.writeInt(parcel, 5, this.k);
        SafeParcelWriter.writeTypedList(parcel, 6, this.l, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zzab() {
        return this.k;
    }

    public final void zzb(@NonNull DataReadResult dataReadResult) {
        for (DataSet dataSet : dataReadResult.getDataSets()) {
            a(dataSet, this.h);
        }
        for (Bucket bucket : dataReadResult.getBuckets()) {
            Iterator<Bucket> it = this.j.iterator();
            while (true) {
                if (it.hasNext()) {
                    Bucket next = it.next();
                    if (next.zza(bucket)) {
                        for (DataSet dataSet2 : bucket.getDataSets()) {
                            a(dataSet2, next.getDataSets());
                        }
                    }
                } else {
                    this.j.add(bucket);
                    break;
                }
            }
        }
    }

    @NonNull
    public DataSet getDataSet(@NonNull DataSource dataSource) {
        for (DataSet dataSet : this.h) {
            if (dataSource.equals(dataSet.getDataSource())) {
                return dataSet;
            }
        }
        return DataSet.builder(dataSource).build();
    }

    @ShowFirstParty
    public DataReadResult(List<DataSet> list, List<Bucket> list2, Status status) {
        this.h = list;
        this.i = status;
        this.j = list2;
        this.k = 1;
        this.l = new ArrayList();
    }
}
