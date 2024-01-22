package com.mappls.sdk.navigation.ui;

import android.os.Parcel;
/* loaded from: classes11.dex */
public final class b extends a {
    public b(Integer num, Integer num2, String str, String str2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Boolean bool11, Integer num3) {
        super(num, num2, str, str2, bool, bool2, bool3, bool4, bool5, bool6, bool7, bool8, bool9, bool10, bool11, num3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(navigationLightTheme().intValue());
        parcel.writeInt(navigationDarkTheme().intValue());
        parcel.writeString(mapplsMapLightStyle());
        parcel.writeString(mapplsMapDarkStyle());
        parcel.writeInt(isUsingInternalMap().booleanValue() ? 1 : 0);
        parcel.writeInt(showDayNightOption().booleanValue() ? 1 : 0);
        parcel.writeInt(showTrafficOption().booleanValue() ? 1 : 0);
        parcel.writeInt(showNavigationSettingsOption().booleanValue() ? 1 : 0);
        parcel.writeInt(showSearchDuringNavigationOption().booleanValue() ? 1 : 0);
        parcel.writeInt(showNextInstructionBanner().booleanValue() ? 1 : 0);
        parcel.writeInt(showCurrentSpeed().booleanValue() ? 1 : 0);
        parcel.writeInt(showSpeedWarning().booleanValue() ? 1 : 0);
        parcel.writeInt(showBottomInfoBar().booleanValue() ? 1 : 0);
        parcel.writeInt(showInstructionBanner().booleanValue() ? 1 : 0);
        parcel.writeInt(showWarningMessage().booleanValue() ? 1 : 0);
        parcel.writeInt(navigationTheme().intValue());
    }
}
