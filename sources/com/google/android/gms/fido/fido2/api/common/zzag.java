package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "GoogleTunnelServerIdExtensionCreator")
/* loaded from: classes6.dex */
public final class zzag extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzag> CREATOR = new zzah();
    @NonNull
    @SafeParcelable.Field(getter = "getTunnelServerId", id = 1)
    public final String h;

    @SafeParcelable.Constructor
    public zzag(@NonNull @SafeParcelable.Param(id = 1) String str) {
        this.h = (String) Preconditions.checkNotNull(str);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzag) {
            return this.h.equals(((zzag) obj).h);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
