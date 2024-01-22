package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "DeviceMetaDataCreator")
/* loaded from: classes6.dex */
public class DeviceMetaData extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DeviceMetaData> CREATOR = new zzy();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "isLockScreenSolved", id = 2)
    public boolean i;
    @SafeParcelable.Field(getter = "getMinAgeOfLockScreen", id = 3)
    public long j;
    @SafeParcelable.Field(getter = "isChallengeAllowed", id = 4)
    public final boolean k;

    @SafeParcelable.Constructor
    public DeviceMetaData(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) boolean z2) {
        this.h = i;
        this.i = z;
        this.j = j;
        this.k = z2;
    }

    public long getMinAgeOfLockScreen() {
        return this.j;
    }

    public boolean isChallengeAllowed() {
        return this.k;
    }

    public boolean isLockScreenSolved() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeBoolean(parcel, 2, isLockScreenSolved());
        SafeParcelWriter.writeLong(parcel, 3, getMinAgeOfLockScreen());
        SafeParcelWriter.writeBoolean(parcel, 4, isChallengeAllowed());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
