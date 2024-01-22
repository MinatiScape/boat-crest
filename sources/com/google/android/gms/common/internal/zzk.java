package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.jstyle.blesdk1860.constant.BleConst;
@SafeParcelable.Class(creator = "ConnectionInfoCreator")
/* loaded from: classes6.dex */
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();
    @SafeParcelable.Field(id = 1)
    public Bundle h;
    @SafeParcelable.Field(id = 2)
    public Feature[] i;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, id = 3)
    public int j;
    @Nullable
    @SafeParcelable.Field(id = 4)
    public ConnectionTelemetryConfiguration k;

    public zzk() {
    }

    @SafeParcelable.Constructor
    public zzk(@SafeParcelable.Param(id = 1) Bundle bundle, @SafeParcelable.Param(id = 2) Feature[] featureArr, @SafeParcelable.Param(id = 3) int i, @Nullable @SafeParcelable.Param(id = 4) ConnectionTelemetryConfiguration connectionTelemetryConfiguration) {
        this.h = bundle;
        this.i = featureArr;
        this.j = i;
        this.k = connectionTelemetryConfiguration;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.h, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.i, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.j);
        SafeParcelWriter.writeParcelable(parcel, 4, this.k, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
