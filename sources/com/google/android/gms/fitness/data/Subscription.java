package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.Locale;
@SafeParcelable.Class(creator = "SubscriptionCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class Subscription extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Subscription> CREATOR = new zzai();
    @SafeParcelable.Field(getter = "getDataSource", id = 1)
    public final DataSource h;
    @SafeParcelable.Field(getter = "getDataType", id = 2)
    public final DataType i;
    @SafeParcelable.Field(getter = "getSamplingRateMicros", id = 3)
    public final long j;
    @SafeParcelable.Field(getter = "getAccuracyMode", id = 4)
    public final int k;
    @SafeParcelable.Field(getter = "getSubscriptionType", id = 5)
    public final int l;

    @ShowFirstParty
    /* loaded from: classes6.dex */
    public static class zza {

        /* renamed from: a  reason: collision with root package name */
        public DataSource f8433a;
        public DataType b;
        public long c = -1;
        public int d = 2;

        public final zza zza(DataSource dataSource) {
            this.f8433a = dataSource;
            return this;
        }

        public final Subscription zzr() {
            DataSource dataSource;
            boolean z = false;
            Preconditions.checkState((this.f8433a == null && this.b == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.b;
            if (dataType == null || (dataSource = this.f8433a) == null || dataType.equals(dataSource.getDataType())) {
                z = true;
            }
            Preconditions.checkState(z, "Specified data type is incompatible with specified data source");
            return new Subscription(this.f8433a, this.b, this.c, this.d, 0);
        }

        public final zza zza(DataType dataType) {
            this.b = dataType;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public Subscription(@SafeParcelable.Param(id = 1) DataSource dataSource, @SafeParcelable.Param(id = 2) DataType dataType, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) int i2) {
        this.h = dataSource;
        this.i = dataType;
        this.j = j;
        this.k = i;
        this.l = i2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Subscription) {
            Subscription subscription = (Subscription) obj;
            return Objects.equal(this.h, subscription.h) && Objects.equal(this.i, subscription.i) && this.j == subscription.j && this.k == subscription.k && this.l == subscription.l;
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

    public int hashCode() {
        DataSource dataSource = this.h;
        return Objects.hashCode(dataSource, dataSource, Long.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
    }

    @NonNull
    public String toDebugString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        DataSource dataSource = this.h;
        objArr[0] = dataSource == null ? this.i.getName() : dataSource.toDebugString();
        objArr[1] = Integer.valueOf(this.l);
        return String.format(locale, "Subscription{%s}, subscriptionType{%d}", objArr);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.h).add(DeviceKey.DataType, this.i).add("samplingIntervalMicros", Long.valueOf(this.j)).add("accuracyMode", Integer.valueOf(this.k)).add("subscriptionType", Integer.valueOf(this.l)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.j);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    @ShowFirstParty
    public final DataType zzs() {
        DataType dataType = this.i;
        return dataType == null ? this.h.getDataType() : dataType;
    }
}
