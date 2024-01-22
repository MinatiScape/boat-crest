package com.google.android.gms.location;

import android.content.Intent;
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
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "SleepSegmentEventCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public class SleepSegmentEvent extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SleepSegmentEvent> CREATOR = new zzag();
    public static final int STATUS_MISSING_DATA = 1;
    public static final int STATUS_NOT_DETECTED = 2;
    public static final int STATUS_SUCCESSFUL = 0;
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getStatus", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getMissingDataDurationMinutes", id = 4)
    public final int k;
    @SafeParcelable.Field(getter = "getNinetiethPctConfidence", id = 5)
    public final int l;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepSegmentEvent(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) int i3) {
        Preconditions.checkArgument(j <= j2, "endTimeMillis must be greater than or equal to startTimeMillis");
        this.h = j;
        this.i = j2;
        this.j = i;
        this.k = i2;
        this.l = i3;
    }

    @NonNull
    public static List<SleepSegmentEvent> extractEvents(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        if (!hasEvents(intent)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.internal.EXTRA_SLEEP_SEGMENT_RESULT");
        if (arrayList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            byte[] bArr = (byte[]) arrayList.get(i);
            Preconditions.checkNotNull(bArr);
            arrayList2.add((SleepSegmentEvent) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR));
        }
        return Collections.unmodifiableList(arrayList2);
    }

    public static boolean hasEvents(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_SLEEP_SEGMENT_RESULT");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SleepSegmentEvent) {
            SleepSegmentEvent sleepSegmentEvent = (SleepSegmentEvent) obj;
            if (this.h == sleepSegmentEvent.getStartTimeMillis() && this.i == sleepSegmentEvent.getEndTimeMillis() && this.j == sleepSegmentEvent.getStatus() && this.k == sleepSegmentEvent.k && this.l == sleepSegmentEvent.l) {
                return true;
            }
        }
        return false;
    }

    public long getEndTimeMillis() {
        return this.i;
    }

    public long getSegmentDurationMillis() {
        return this.i - this.h;
    }

    public long getStartTimeMillis() {
        return this.h;
    }

    public int getStatus() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Long.valueOf(this.i), Integer.valueOf(this.j));
    }

    @NonNull
    public String toString() {
        long j = this.h;
        long j2 = this.i;
        int i = this.j;
        return "startMillis=" + j + ", endMillis=" + j2 + ", status=" + i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getStartTimeMillis());
        SafeParcelWriter.writeLong(parcel, 2, getEndTimeMillis());
        SafeParcelWriter.writeInt(parcel, 3, getStatus());
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
