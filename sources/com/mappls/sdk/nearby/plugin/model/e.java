package com.mappls.sdk.nearby.plugin.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class e extends c {
    public e(Integer num, Integer num2, Integer num3, Integer num4, @Nullable Bitmap bitmap, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, String str, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, @Nullable Integer num17, Integer num18, Integer num19, String str2, Integer num20, Integer num21, Integer num22, Integer num23, Integer num24, Integer num25, Integer num26, Integer num27, Integer num28, Integer num29, Integer num30, Integer num31, Integer num32, Integer num33, Integer num34, Integer num35, @Nullable Bitmap bitmap2, Integer num36, Float f, Boolean bool) {
        super(num, num2, num3, num4, bitmap, num5, num6, num7, num8, num9, num10, num11, str, num12, num13, num14, num15, num16, num17, num18, num19, str2, num20, num21, num22, num23, num24, num25, num26, num27, num28, num29, num30, num31, num32, num33, num34, num35, bitmap2, num36, f, bool);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int intValue;
        parcel.writeInt(backgroundColor().intValue());
        parcel.writeInt(toolbarColor().intValue());
        parcel.writeInt(nearbyToolbarColor().intValue());
        parcel.writeInt(nearbyToolbarIcon().intValue());
        parcel.writeParcelable(nearbyToolbarBitmap(), i);
        parcel.writeInt(toolbarTintColor().intValue());
        parcel.writeInt(nearbyToolbarTintColor().intValue());
        parcel.writeInt(locationDetailsBackgroundColor().intValue());
        parcel.writeInt(detailListBackgroundColor().intValue());
        parcel.writeInt(categoryFilterBackgroundColor().intValue());
        parcel.writeInt(detailListSeperatorBackgroundColor().intValue());
        parcel.writeInt(locationDetailInfoTextColor().intValue());
        parcel.writeString(locationDetailsInfoLabelText());
        parcel.writeInt(locationDetailFormattedAddressTextColor().intValue());
        parcel.writeInt(changeLocationButtonTextColor().intValue());
        parcel.writeInt(prevButtonBackgroundColor().intValue());
        parcel.writeInt(nextButtonBackgroundColor().intValue());
        parcel.writeInt(useCurrentLocationButtonTextColor().intValue());
        if (submitButtonColor() == null) {
            intValue = 1;
        } else {
            parcel.writeInt(0);
            intValue = submitButtonColor().intValue();
        }
        parcel.writeInt(intValue);
        parcel.writeInt(submitButtonResource().intValue());
        parcel.writeInt(submitButtonTextColor().intValue());
        parcel.writeString(submitButtonText());
        parcel.writeInt(selectedCategoryBackgroundColor().intValue());
        parcel.writeInt(selectedCategoryTextColor().intValue());
        parcel.writeInt(selectedCategoryTintColor().intValue());
        parcel.writeInt(categoryBackgroundColor().intValue());
        parcel.writeInt(categoryTextColor().intValue());
        parcel.writeInt(categoryTintColor().intValue());
        parcel.writeInt(paginationBackgroundColor().intValue());
        parcel.writeInt(tabTextColor().intValue());
        parcel.writeInt(selectedTabTextColor().intValue());
        parcel.writeInt(tabIndicatorColor().intValue());
        parcel.writeInt(tabBackgroundColor().intValue());
        parcel.writeInt(tabIconTint().intValue());
        parcel.writeInt(placeNameTextColor().intValue());
        parcel.writeInt(distanceTextColor().intValue());
        parcel.writeInt(placeAddressTextColor().intValue());
        parcel.writeInt(refLocationIcon().intValue());
        parcel.writeParcelable(refLocationBitmap(), i);
        parcel.writeInt(refLocationCircleColor().intValue());
        parcel.writeFloat(refLocationCircleAlpha().floatValue());
        parcel.writeInt(showDefaultMap().booleanValue() ? 1 : 0);
    }
}
