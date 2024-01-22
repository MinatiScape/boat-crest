package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ActivityTransitionEventCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public class ActivityTransitionEvent extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityTransitionEvent> CREATOR = new zzf();
    @SafeParcelable.Field(getter = "getActivityType", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getTransitionType", id = 2)
    public final int i;
    @SafeParcelable.Field(getter = "getElapsedRealTimeNanos", id = 3)
    public final long j;

    @SafeParcelable.Constructor
    public ActivityTransitionEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) long j) {
        ActivityTransition.zza(i2);
        this.h = i;
        this.i = i2;
        this.j = j;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityTransitionEvent) {
            ActivityTransitionEvent activityTransitionEvent = (ActivityTransitionEvent) obj;
            return this.h == activityTransitionEvent.h && this.i == activityTransitionEvent.i && this.j == activityTransitionEvent.j;
        }
        return false;
    }

    public int getActivityType() {
        return this.h;
    }

    public long getElapsedRealTimeNanos() {
        return this.j;
    }

    public int getTransitionType() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), Integer.valueOf(this.i), Long.valueOf(this.j));
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.h;
        sb.append("ActivityType " + i);
        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        int i2 = this.i;
        sb.append("TransitionType " + i2);
        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        long j = this.j;
        sb.append("ElapsedRealTimeNanos " + j);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, getTransitionType());
        SafeParcelWriter.writeLong(parcel, 3, getElapsedRealTimeNanos());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
