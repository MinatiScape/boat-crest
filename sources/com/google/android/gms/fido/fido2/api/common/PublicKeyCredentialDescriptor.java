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
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;
import java.util.Arrays;
import java.util.List;
@SafeParcelable.Class(creator = "PublicKeyCredentialDescriptorCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class PublicKeyCredentialDescriptor extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialDescriptor> CREATOR;
    @NonNull
    @SafeParcelable.Field(getter = "getTypeAsString", id = 2, type = "java.lang.String")
    public final PublicKeyCredentialType h;
    @NonNull
    @SafeParcelable.Field(getter = "getId", id = 3)
    public final byte[] i;
    @Nullable
    @SafeParcelable.Field(getter = "getTransports", id = 4)
    public final List j;

    /* loaded from: classes6.dex */
    public static class UnsupportedPubKeyCredDescriptorException extends Exception {
        public UnsupportedPubKeyCredDescriptorException(@NonNull String str) {
            super(str);
        }

        public UnsupportedPubKeyCredDescriptorException(@NonNull String str, @NonNull Throwable th) {
            super(str, th);
        }
    }

    static {
        com.google.android.gms.internal.fido.zzau.zzi(com.google.android.gms.internal.fido.zzh.zza, com.google.android.gms.internal.fido.zzh.zzb);
        CREATOR = new zzam();
    }

    @SafeParcelable.Constructor
    public PublicKeyCredentialDescriptor(@NonNull @SafeParcelable.Param(id = 2) String str, @NonNull @SafeParcelable.Param(id = 3) byte[] bArr, @Nullable @SafeParcelable.Param(id = 4) List<Transport> list) {
        Preconditions.checkNotNull(str);
        try {
            this.h = PublicKeyCredentialType.fromString(str);
            this.i = (byte[]) Preconditions.checkNotNull(bArr);
            this.j = list;
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(@NonNull Object obj) {
        List list;
        if (obj instanceof PublicKeyCredentialDescriptor) {
            PublicKeyCredentialDescriptor publicKeyCredentialDescriptor = (PublicKeyCredentialDescriptor) obj;
            if (this.h.equals(publicKeyCredentialDescriptor.h) && Arrays.equals(this.i, publicKeyCredentialDescriptor.i)) {
                List list2 = this.j;
                if (list2 == null && publicKeyCredentialDescriptor.j == null) {
                    return true;
                }
                return list2 != null && (list = publicKeyCredentialDescriptor.j) != null && list2.containsAll(list) && publicKeyCredentialDescriptor.j.containsAll(this.j);
            }
            return false;
        }
        return false;
    }

    @NonNull
    public byte[] getId() {
        return this.i;
    }

    @Nullable
    public List<Transport> getTransports() {
        return this.j;
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
        return Objects.hashCode(this.h, Integer.valueOf(Arrays.hashCode(this.i)), this.j);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getTypeAsString(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getId(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
