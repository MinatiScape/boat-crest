package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
@SafeParcelable.Class(creator = "FidoCredentialDetailsCreator")
/* loaded from: classes6.dex */
public class FidoCredentialDetails extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<FidoCredentialDetails> CREATOR = new zzy();
    @Nullable
    @SafeParcelable.Field(getter = "getUserName", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getUserDisplayName", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getUserId", id = 3)
    public final byte[] j;
    @NonNull
    @SafeParcelable.Field(getter = "getCredentialId", id = 4)
    public final byte[] k;
    @SafeParcelable.Field(getter = "getIsDiscoverable", id = 5)
    public final boolean l;
    @SafeParcelable.Field(getter = "getIsPaymentCredential", id = 6)
    public final boolean m;

    @SafeParcelable.Constructor
    public FidoCredentialDetails(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) byte[] bArr, @NonNull @SafeParcelable.Param(id = 4) byte[] bArr2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) boolean z2) {
        this.h = str;
        this.i = str2;
        this.j = bArr;
        this.k = bArr2;
        this.l = z;
        this.m = z2;
    }

    @NonNull
    public static FidoCredentialDetails deserializeFromBytes(@NonNull byte[] bArr) {
        return (FidoCredentialDetails) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof FidoCredentialDetails) {
            FidoCredentialDetails fidoCredentialDetails = (FidoCredentialDetails) obj;
            return Objects.equal(this.h, fidoCredentialDetails.h) && Objects.equal(this.i, fidoCredentialDetails.i) && Arrays.equals(this.j, fidoCredentialDetails.j) && Arrays.equals(this.k, fidoCredentialDetails.k) && this.l == fidoCredentialDetails.l && this.m == fidoCredentialDetails.m;
        }
        return false;
    }

    @NonNull
    public byte[] getCredentialId() {
        return this.k;
    }

    public boolean getIsDiscoverable() {
        return this.l;
    }

    public boolean getIsPaymentCredential() {
        return this.m;
    }

    @Nullable
    public String getUserDisplayName() {
        return this.i;
    }

    @Nullable
    public byte[] getUserId() {
        return this.j;
    }

    @Nullable
    public String getUserName() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.k, Boolean.valueOf(this.l), Boolean.valueOf(this.m));
    }

    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUserName(), false);
        SafeParcelWriter.writeString(parcel, 2, getUserDisplayName(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getUserId(), false);
        SafeParcelWriter.writeByteArray(parcel, 4, getCredentialId(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, getIsDiscoverable());
        SafeParcelWriter.writeBoolean(parcel, 6, getIsPaymentCredential());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
