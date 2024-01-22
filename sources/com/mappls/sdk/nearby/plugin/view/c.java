package com.mappls.sdk.nearby.plugin.view;

import android.graphics.Bitmap;
import android.os.Parcel;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class c extends a {
    public c(Integer num, Integer num2, Integer num3, Integer num4, String str, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, @Nullable Bitmap bitmap, Integer num19, Float f, Boolean bool) {
        super(num, num2, num3, num4, str, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14, num15, num16, num17, num18, bitmap, num19, f, bool);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(backgroundColor().intValue());
        parcel.writeInt(paginationBackgroundColor().intValue());
        parcel.writeInt(toolbarBackgroundColor().intValue());
        parcel.writeInt(toolbarTintColor().intValue());
        parcel.writeString(toolbarText());
        parcel.writeInt(tabTextColor().intValue());
        parcel.writeInt(tabSelectedTextColor().intValue());
        parcel.writeInt(categoryFilterBackgroundColor().intValue());
        parcel.writeInt(tabIndicatorColor().intValue());
        parcel.writeInt(tabBackgroundColor().intValue());
        parcel.writeInt(tabIconTint().intValue());
        parcel.writeInt(placeNameTextColor().intValue());
        parcel.writeInt(distanceTextColor().intValue());
        parcel.writeInt(listBackgroundColor().intValue());
        parcel.writeInt(prevButtonBackgroundColor().intValue());
        parcel.writeInt(nextButtonBackgroundColor().intValue());
        parcel.writeInt(listSeperatorColor().intValue());
        parcel.writeInt(addressTextColor().intValue());
        parcel.writeInt(locationMarkerIcon().intValue());
        parcel.writeParcelable(locationMarkerBitmap(), i);
        parcel.writeInt(locationCircleColor().intValue());
        parcel.writeFloat(locationCircleAlpha().floatValue());
        parcel.writeInt(showDefaultMap().booleanValue() ? 1 : 0);
    }
}
