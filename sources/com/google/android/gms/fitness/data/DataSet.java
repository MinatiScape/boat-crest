package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
@SafeParcelable.Class(creator = "DataSetCreator")
@SafeParcelable.Reserved({2, 5})
/* loaded from: classes6.dex */
public final class DataSet extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<DataSet> CREATOR = new zzi();
    @SafeParcelable.VersionField(getter = "getVersionCode", id = 1000)
    public final int h;
    @SafeParcelable.Field(getter = "getDataSource", id = 1)
    public final DataSource i;
    @SafeParcelable.Field(getter = "getRawDataPoints", id = 3, type = "java.util.List")
    public final List<DataPoint> j;
    @SafeParcelable.Field(getter = "getUniqueDataSources", id = 4)
    public final List<DataSource> k;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final DataSet f8430a;
        public boolean b;

        public Builder(DataSource dataSource) {
            this.b = false;
            this.f8430a = DataSet.create(dataSource);
        }

        @NonNull
        public Builder add(@NonNull DataPoint dataPoint) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8430a.add(dataPoint);
            return this;
        }

        @NonNull
        public Builder addAll(@NonNull Iterable<DataPoint> iterable) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8430a.addAll(iterable);
            return this;
        }

        @NonNull
        public DataSet build() {
            Preconditions.checkState(!this.b, "DataSet#build() should only be called once.");
            this.b = true;
            return this.f8430a;
        }
    }

    @SafeParcelable.Constructor
    public DataSet(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) DataSource dataSource, @SafeParcelable.Param(id = 3) List<RawDataPoint> list, @SafeParcelable.Param(id = 4) List<DataSource> list2) {
        this.h = i;
        this.i = dataSource;
        this.j = new ArrayList(list.size());
        this.k = i < 2 ? Collections.singletonList(dataSource) : list2;
        for (RawDataPoint rawDataPoint : list) {
            this.j.add(new DataPoint(this.k, rawDataPoint));
        }
    }

    @NonNull
    public static Builder builder(@NonNull DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new Builder(dataSource);
    }

    @NonNull
    public static DataSet create(@NonNull DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new DataSet(dataSource);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c5, code lost:
        if (r4 != 0.0d) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void zzb(@androidx.annotation.NonNull com.google.android.gms.fitness.data.DataPoint r11) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.DataSet.zzb(com.google.android.gms.fitness.data.DataPoint):void");
    }

    public final List<RawDataPoint> a(List<DataSource> list) {
        ArrayList arrayList = new ArrayList(this.j.size());
        for (DataPoint dataPoint : this.j) {
            arrayList.add(new RawDataPoint(dataPoint, list));
        }
        return arrayList;
    }

    @Deprecated
    public final void add(@NonNull DataPoint dataPoint) {
        DataSource dataSource = dataPoint.getDataSource();
        Preconditions.checkArgument(dataSource.getStreamIdentifier().equals(this.i.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.i);
        dataPoint.zzh();
        zzb(dataPoint);
        b(dataPoint);
    }

    @Deprecated
    public final void addAll(@NonNull Iterable<DataPoint> iterable) {
        for (DataPoint dataPoint : iterable) {
            add(dataPoint);
        }
    }

    @ShowFirstParty
    @Deprecated
    public final void b(DataPoint dataPoint) {
        this.j.add(dataPoint);
        DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource == null || this.k.contains(originalDataSource)) {
            return;
        }
        this.k.add(originalDataSource);
    }

    public final List<RawDataPoint> c() {
        return a(this.k);
    }

    @NonNull
    public final DataPoint createDataPoint() {
        return DataPoint.create(this.i);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataSet) {
            DataSet dataSet = (DataSet) obj;
            return Objects.equal(this.i, dataSet.i) && Objects.equal(this.j, dataSet.j);
        }
        return false;
    }

    @NonNull
    public final List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList(this.j);
    }

    @NonNull
    public final DataSource getDataSource() {
        return this.i;
    }

    @NonNull
    public final DataType getDataType() {
        return this.i.getDataType();
    }

    public final int hashCode() {
        return Objects.hashCode(this.i);
    }

    public final boolean isEmpty() {
        return this.j.isEmpty();
    }

    @NonNull
    public final String toString() {
        List<RawDataPoint> c = c();
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = this.i.toDebugString();
        Object obj = c;
        if (this.j.size() >= 10) {
            obj = String.format(locale, "%d data points, first 5: %s", Integer.valueOf(this.j.size()), c.subList(0, 5));
        }
        objArr[1] = obj;
        return String.format(locale, "DataSet{%s %s}", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeList(parcel, 3, c(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.k, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.h);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @Deprecated
    public final void zza(@NonNull Iterable<DataPoint> iterable) {
        for (DataPoint dataPoint : iterable) {
            b(dataPoint);
        }
    }

    @ShowFirstParty
    public DataSet(DataSource dataSource) {
        this.h = 3;
        DataSource dataSource2 = (DataSource) Preconditions.checkNotNull(dataSource);
        this.i = dataSource2;
        this.j = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.k = arrayList;
        arrayList.add(dataSource2);
    }

    public DataSet(@NonNull RawDataSet rawDataSet, @NonNull List<DataSource> list) {
        this.h = 3;
        this.i = list.get(rawDataSet.zzoa);
        this.k = list;
        List<RawDataPoint> list2 = rawDataSet.zzoc;
        this.j = new ArrayList(list2.size());
        for (RawDataPoint rawDataPoint : list2) {
            this.j.add(new DataPoint(this.k, rawDataPoint));
        }
    }
}
