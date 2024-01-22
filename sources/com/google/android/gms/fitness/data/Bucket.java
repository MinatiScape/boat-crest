package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzko;
import java.util.List;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "BucketCreator")
@SafeParcelable.Reserved({7, 1000})
/* loaded from: classes6.dex */
public class Bucket extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<Bucket> CREATOR = new zze();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 2)
    public final long i;
    @Nullable
    @SafeParcelable.Field(getter = "getSession", id = 3)
    public final Session j;
    @SafeParcelable.Field(getter = "getActivityType", id = 4)
    public final int k;
    @SafeParcelable.Field(getter = "getDataSets", id = 5)
    public final List<DataSet> l;
    @SafeParcelable.Field(getter = "getBucketType", id = 6)
    public final int m;

    @SafeParcelable.Constructor
    public Bucket(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @Nullable @SafeParcelable.Param(id = 3) Session session, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) List<DataSet> list, @SafeParcelable.Param(id = 6) int i2) {
        this.h = j;
        this.i = j2;
        this.j = session;
        this.k = i;
        this.l = list;
        this.m = i2;
    }

    @NonNull
    @ShowFirstParty
    public static String zza(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "bug" : "intervals" : "segment" : "type" : "session" : NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP : "none";
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Bucket) {
            Bucket bucket = (Bucket) obj;
            return this.h == bucket.h && this.i == bucket.i && this.k == bucket.k && Objects.equal(this.l, bucket.l) && this.m == bucket.m;
        }
        return false;
    }

    @NonNull
    public String getActivity() {
        return zzko.getName(this.k);
    }

    public int getBucketType() {
        return this.m;
    }

    @Nullable
    public DataSet getDataSet(@NonNull DataType dataType) {
        for (DataSet dataSet : this.l) {
            if (dataSet.getDataType().equals(dataType)) {
                return dataSet;
            }
        }
        return null;
    }

    @NonNull
    public List<DataSet> getDataSets() {
        return this.l;
    }

    public long getEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.i, TimeUnit.MILLISECONDS);
    }

    @Nullable
    public Session getSession() {
        return this.j;
    }

    public long getStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.h, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Long.valueOf(this.i), Integer.valueOf(this.k), Integer.valueOf(this.m));
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.h)).add("endTime", Long.valueOf(this.i)).add("activity", Integer.valueOf(this.k)).add("dataSets", this.l).add("bucketType", zza(this.m)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeParcelable(parcel, 3, getSession(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataSets(), false);
        SafeParcelWriter.writeInt(parcel, 6, getBucketType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zza(@NonNull Bucket bucket) {
        return this.h == bucket.h && this.i == bucket.i && this.k == bucket.k && this.m == bucket.m;
    }

    @ShowFirstParty
    public final int zzd() {
        return this.k;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Bucket(@androidx.annotation.NonNull com.google.android.gms.fitness.data.RawBucket r11, @androidx.annotation.NonNull java.util.List<com.google.android.gms.fitness.data.DataSource> r12) {
        /*
            r10 = this;
            long r1 = r11.zzkr
            long r3 = r11.zzks
            com.google.android.gms.fitness.data.Session r5 = r11.zzky
            int r6 = r11.zzny
            java.util.List<com.google.android.gms.fitness.data.RawDataSet> r0 = r11.zzlh
            java.util.ArrayList r7 = new java.util.ArrayList
            int r8 = r0.size()
            r7.<init>(r8)
            java.util.Iterator r0 = r0.iterator()
        L17:
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L2c
            java.lang.Object r8 = r0.next()
            com.google.android.gms.fitness.data.RawDataSet r8 = (com.google.android.gms.fitness.data.RawDataSet) r8
            com.google.android.gms.fitness.data.DataSet r9 = new com.google.android.gms.fitness.data.DataSet
            r9.<init>(r8, r12)
            r7.add(r9)
            goto L17
        L2c:
            int r8 = r11.zzli
            r0 = r10
            r0.<init>(r1, r3, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.Bucket.<init>(com.google.android.gms.fitness.data.RawBucket, java.util.List):void");
    }
}
