package com.google.android.gms.location;

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
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.List;
@SafeParcelable.Class(creator = "SleepSegmentRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public class SleepSegmentRequest extends AbstractSafeParcelable {
    public static final int CLASSIFY_EVENTS_ONLY = 2;
    @NonNull
    public static final Parcelable.Creator<SleepSegmentRequest> CREATOR = new zzah();
    public static final int SEGMENT_AND_CLASSIFY_EVENTS = 0;
    public static final int SEGMENT_EVENTS_ONLY = 1;
    @Nullable
    @SafeParcelable.Field(getter = "getUserPreferredSleepWindow", id = 1)
    public final List h;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, getter = "getRequestedDataType", id = 2)
    public final int i;

    public SleepSegmentRequest(int i) {
        this(null, i);
    }

    @NonNull
    public static SleepSegmentRequest getDefaultSleepSegmentRequest() {
        return new SleepSegmentRequest(null, 0);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SleepSegmentRequest) {
            SleepSegmentRequest sleepSegmentRequest = (SleepSegmentRequest) obj;
            return Objects.equal(this.h, sleepSegmentRequest.h) && this.i == sleepSegmentRequest.i;
        }
        return false;
    }

    public int getRequestedDataType() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, Integer.valueOf(this.i));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.h, false);
        SafeParcelWriter.writeInt(parcel, 2, getRequestedDataType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepSegmentRequest(@Nullable @SafeParcelable.Param(id = 1) List list, @SafeParcelable.Param(id = 2) int i) {
        this.h = list;
        this.i = i;
    }
}
