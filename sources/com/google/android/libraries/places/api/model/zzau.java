package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes10.dex */
final class zzau implements Parcelable.Creator<zzav> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzav createFromParcel(Parcel parcel) {
        return new zzav(parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzav[] newArray(int i) {
        return new zzav[i];
    }
}
