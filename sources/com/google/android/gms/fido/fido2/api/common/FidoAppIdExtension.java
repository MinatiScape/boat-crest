package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "FidoAppIdExtensionCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class FidoAppIdExtension extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<FidoAppIdExtension> CREATOR = new zzx();
    @NonNull
    @SafeParcelable.Field(getter = "getAppId", id = 2)
    public final String h;

    @SafeParcelable.Constructor
    public FidoAppIdExtension(@NonNull @SafeParcelable.Param(id = 2) String str) {
        this.h = (String) Preconditions.checkNotNull(str);
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof FidoAppIdExtension) {
            return this.h.equals(((FidoAppIdExtension) obj).h);
        }
        return false;
    }

    @NonNull
    public String getAppId() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAppId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
