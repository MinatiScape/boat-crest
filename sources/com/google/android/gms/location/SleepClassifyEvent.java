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
@SafeParcelable.Class(creator = "SleepClassifyEventCreator")
/* loaded from: classes10.dex */
public class SleepClassifyEvent extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SleepClassifyEvent> CREATOR = new zzaf();
    @SafeParcelable.Field(getter = "getTimestampSec", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getConfidence", id = 2)
    public final int i;
    @SafeParcelable.Field(getter = "getMotion", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getLight", id = 4)
    public final int k;
    @SafeParcelable.Field(getter = "getNoise", id = 5)
    public final int l;
    @SafeParcelable.Field(getter = "getLightDiff", id = 6)
    public final int m;
    @SafeParcelable.Field(getter = "getNightOrDay", id = 7)
    public final int n;
    @SafeParcelable.Field(getter = "getConfidenceOverwrittenByAlarmClockTrigger", id = 8)
    public final boolean o;
    @SafeParcelable.Field(getter = "getPresenceConfidence", id = 9)
    public final int p;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepClassifyEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) int i4, @SafeParcelable.Param(id = 5) int i5, @SafeParcelable.Param(id = 6) int i6, @SafeParcelable.Param(id = 7) int i7, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) int i8) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = i4;
        this.l = i5;
        this.m = i6;
        this.n = i7;
        this.o = z;
        this.p = i8;
    }

    @NonNull
    public static List<SleepClassifyEvent> extractEvents(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        if (!hasEvents(intent)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.internal.EXTRA_SLEEP_CLASSIFY_RESULT");
        if (arrayList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            byte[] bArr = (byte[]) arrayList.get(i);
            Preconditions.checkNotNull(bArr);
            arrayList2.add((SleepClassifyEvent) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR));
        }
        return Collections.unmodifiableList(arrayList2);
    }

    public static boolean hasEvents(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_SLEEP_CLASSIFY_RESULT");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SleepClassifyEvent) {
            SleepClassifyEvent sleepClassifyEvent = (SleepClassifyEvent) obj;
            return this.h == sleepClassifyEvent.h && this.i == sleepClassifyEvent.i;
        }
        return false;
    }

    public int getConfidence() {
        return this.i;
    }

    public int getLight() {
        return this.k;
    }

    public int getMotion() {
        return this.j;
    }

    public long getTimestampMillis() {
        return this.h * 1000;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), Integer.valueOf(this.i));
    }

    @NonNull
    public String toString() {
        int i = this.h;
        int i2 = this.i;
        int i3 = this.j;
        int i4 = this.k;
        return i + " Conf:" + i2 + " Motion:" + i3 + " Light:" + i4;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeInt(parcel, 2, getConfidence());
        SafeParcelWriter.writeInt(parcel, 3, getMotion());
        SafeParcelWriter.writeInt(parcel, 4, getLight());
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.writeInt(parcel, 6, this.m);
        SafeParcelWriter.writeInt(parcel, 7, this.n);
        SafeParcelWriter.writeBoolean(parcel, 8, this.o);
        SafeParcelWriter.writeInt(parcel, 9, this.p);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
