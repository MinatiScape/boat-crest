package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "AuthenticationExtensionsCredPropsOutputsCreator")
/* loaded from: classes6.dex */
public class AuthenticationExtensionsCredPropsOutputs extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AuthenticationExtensionsCredPropsOutputs> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getIsDiscoverableCredential", id = 1)
    public final boolean h;

    @SafeParcelable.Constructor
    public AuthenticationExtensionsCredPropsOutputs(@SafeParcelable.Param(id = 1) boolean z) {
        this.h = z;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof AuthenticationExtensionsCredPropsOutputs) && this.h == ((AuthenticationExtensionsCredPropsOutputs) obj).h;
    }

    public boolean getIsDiscoverableCredential() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.h));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getIsDiscoverableCredential());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
