package com.touchgui.sdk.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.touchgui.sdk.bean.TGScanDevice;
/* loaded from: classes12.dex */
public final class fa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new TGScanDevice(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new TGScanDevice[i];
    }
}
