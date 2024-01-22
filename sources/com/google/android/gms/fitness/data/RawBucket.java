package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@KeepName
@SafeParcelable.Class(creator = "RawBucketCreator")
@SafeParcelable.Reserved({7, 1000})
/* loaded from: classes6.dex */
public final class RawBucket extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<RawBucket> CREATOR = new zzz();
    @SafeParcelable.Field(id = 1)
    public final long zzkr;
    @SafeParcelable.Field(id = 2)
    public final long zzks;
    @Nullable
    @SafeParcelable.Field(id = 3)
    public final Session zzky;
    @NonNull
    @SafeParcelable.Field(id = 5)
    public final List<RawDataSet> zzlh;
    @SafeParcelable.Field(id = 6)
    public final int zzli;
    @SafeParcelable.Field(id = 4)
    public final int zzny;

    @SafeParcelable.Constructor
    public RawBucket(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @Nullable @SafeParcelable.Param(id = 3) Session session, @SafeParcelable.Param(id = 4) int i, @NonNull @SafeParcelable.Param(id = 5) List<RawDataSet> list, @SafeParcelable.Param(id = 6) int i2) {
        this.zzkr = j;
        this.zzks = j2;
        this.zzky = session;
        this.zzny = i;
        this.zzlh = list;
        this.zzli = i2;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RawBucket) {
            RawBucket rawBucket = (RawBucket) obj;
            return this.zzkr == rawBucket.zzkr && this.zzks == rawBucket.zzks && this.zzny == rawBucket.zzny && Objects.equal(this.zzlh, rawBucket.zzlh) && this.zzli == rawBucket.zzli;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzkr), Long.valueOf(this.zzks), Integer.valueOf(this.zzli));
    }

    @NonNull
    public final String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzkr)).add("endTime", Long.valueOf(this.zzks)).add("activity", Integer.valueOf(this.zzny)).add("dataSets", this.zzlh).add("bucketType", Integer.valueOf(this.zzli)).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 2, this.zzks);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzky, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzny);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zzlh, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzli);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public RawBucket(@NonNull Bucket bucket, @NonNull List<DataSource> list) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.zzkr = bucket.getStartTime(timeUnit);
        this.zzks = bucket.getEndTime(timeUnit);
        this.zzky = bucket.getSession();
        this.zzny = bucket.zzd();
        this.zzli = bucket.getBucketType();
        List<DataSet> dataSets = bucket.getDataSets();
        this.zzlh = new ArrayList(dataSets.size());
        for (DataSet dataSet : dataSets) {
            this.zzlh.add(new RawDataSet(dataSet, list));
        }
    }
}
