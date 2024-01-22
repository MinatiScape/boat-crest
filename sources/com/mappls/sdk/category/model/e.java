package com.mappls.sdk.category.model;

import android.os.Parcel;
/* loaded from: classes11.dex */
public final class e extends c {
    public e(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Float f, Integer num14) {
        super(num, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, f, num14);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(iconTintColor().intValue());
        parcel.writeInt(itemTextColor().intValue());
        parcel.writeInt(nextButtonTextColor().intValue());
        parcel.writeInt(nextButtonColor().intValue());
        parcel.writeInt(searchTextColor().intValue());
        parcel.writeInt(backgroundColor().intValue());
        parcel.writeInt(backIcon().intValue());
        parcel.writeInt(hintTextColor().intValue());
        parcel.writeInt(resultCountTextColor().intValue());
        parcel.writeInt(resultPlaceNameTextColor().intValue());
        parcel.writeInt(resultPlaceAddressTextColor().intValue());
        parcel.writeInt(resultPlaceDistanceTextColor().intValue());
        parcel.writeInt(resultMessageTextColor().intValue());
        parcel.writeFloat(polylineWidth().floatValue());
        parcel.writeInt(polylineColor().intValue());
    }
}
