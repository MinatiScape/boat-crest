package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes10.dex */
final class zzal extends zzj {
    public static final Parcelable.Creator<zzal> CREATOR = new zzak();

    public zzal(List<Period> list, List<String> list2) {
        super(list, list2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(getPeriods());
        parcel.writeList(getWeekdayText());
    }
}
