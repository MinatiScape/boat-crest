package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes10.dex */
final class zzab extends zzb {
    public static final Parcelable.Creator<zzab> CREATOR = new zzaa();

    public zzab(List<AddressComponent> list) {
        super(list);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(asList());
    }
}
