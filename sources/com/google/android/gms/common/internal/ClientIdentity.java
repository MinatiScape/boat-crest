package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.jstyle.blesdk1860.constant.BleConst;
@KeepForSdk
@SafeParcelable.Class(creator = "ClientIdentityCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class ClientIdentity extends AbstractSafeParcelable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<ClientIdentity> CREATOR = new zaa();
    @Nullable
    @KeepForSdk
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 2)
    public final String packageName;
    @KeepForSdk
    @SafeParcelable.Field(defaultValueUnchecked = BleConst.GetDeviceTime, id = 1)
    public final int uid;

    @SafeParcelable.Constructor
    public ClientIdentity(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str) {
        this.uid = i;
        this.packageName = str;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ClientIdentity) {
            ClientIdentity clientIdentity = (ClientIdentity) obj;
            return clientIdentity.uid == this.uid && Objects.equal(clientIdentity.packageName, this.packageName);
        }
        return false;
    }

    public final int hashCode() {
        return this.uid;
    }

    @NonNull
    public final String toString() {
        int i = this.uid;
        String str = this.packageName;
        return i + ":" + str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.uid);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
