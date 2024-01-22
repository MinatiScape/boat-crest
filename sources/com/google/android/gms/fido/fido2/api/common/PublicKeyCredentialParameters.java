package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;
@SafeParcelable.Class(creator = "PublicKeyCredentialParametersCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class PublicKeyCredentialParameters extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialParameters> CREATOR = new zzan();
    @NonNull
    @SafeParcelable.Field(getter = "getTypeAsString", id = 2, type = "java.lang.String")
    public final PublicKeyCredentialType h;
    @NonNull
    @SafeParcelable.Field(getter = "getAlgorithmIdAsInteger", id = 3, type = "java.lang.Integer")
    public final COSEAlgorithmIdentifier i;

    @SafeParcelable.Constructor
    public PublicKeyCredentialParameters(@NonNull @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i) {
        Preconditions.checkNotNull(str);
        try {
            this.h = PublicKeyCredentialType.fromString(str);
            Preconditions.checkNotNull(Integer.valueOf(i));
            try {
                this.i = COSEAlgorithmIdentifier.fromCoseValue(i);
            } catch (COSEAlgorithmIdentifier.UnsupportedAlgorithmIdentifierException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof PublicKeyCredentialParameters) {
            PublicKeyCredentialParameters publicKeyCredentialParameters = (PublicKeyCredentialParameters) obj;
            return this.h.equals(publicKeyCredentialParameters.h) && this.i.equals(publicKeyCredentialParameters.i);
        }
        return false;
    }

    @NonNull
    public COSEAlgorithmIdentifier getAlgorithm() {
        return this.i;
    }

    public int getAlgorithmIdAsInteger() {
        return this.i.toCoseValue();
    }

    @NonNull
    public PublicKeyCredentialType getType() {
        return this.h;
    }

    @NonNull
    public String getTypeAsString() {
        return this.h.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getTypeAsString(), false);
        SafeParcelWriter.writeIntegerObject(parcel, 3, Integer.valueOf(getAlgorithmIdAsInteger()), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
