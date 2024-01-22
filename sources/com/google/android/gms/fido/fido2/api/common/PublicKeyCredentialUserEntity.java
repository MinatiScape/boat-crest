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
import java.util.Arrays;
@SafeParcelable.Class(creator = "PublicKeyCredentialUserEntityCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class PublicKeyCredentialUserEntity extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialUserEntity> CREATOR = new zzar();
    @NonNull
    @SafeParcelable.Field(getter = "getId", id = 2)
    public final byte[] h;
    @NonNull
    @SafeParcelable.Field(getter = "getName", id = 3)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getIcon", id = 4)
    public final String j;
    @NonNull
    @SafeParcelable.Field(getter = "getDisplayName", id = 5)
    public final String k;

    @SafeParcelable.Constructor
    public PublicKeyCredentialUserEntity(@NonNull @SafeParcelable.Param(id = 2) byte[] bArr, @NonNull @SafeParcelable.Param(id = 3) String str, @NonNull @SafeParcelable.Param(id = 4) String str2, @NonNull @SafeParcelable.Param(id = 5) String str3) {
        this.h = (byte[]) Preconditions.checkNotNull(bArr);
        this.i = (String) Preconditions.checkNotNull(str);
        this.j = str2;
        this.k = (String) Preconditions.checkNotNull(str3);
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof PublicKeyCredentialUserEntity) {
            PublicKeyCredentialUserEntity publicKeyCredentialUserEntity = (PublicKeyCredentialUserEntity) obj;
            return Arrays.equals(this.h, publicKeyCredentialUserEntity.h) && Objects.equal(this.i, publicKeyCredentialUserEntity.i) && Objects.equal(this.j, publicKeyCredentialUserEntity.j) && Objects.equal(this.k, publicKeyCredentialUserEntity.k);
        }
        return false;
    }

    @NonNull
    public String getDisplayName() {
        return this.k;
    }

    @Nullable
    public String getIcon() {
        return this.j;
    }

    @NonNull
    public byte[] getId() {
        return this.h;
    }

    @NonNull
    public String getName() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.k);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIcon(), false);
        SafeParcelWriter.writeString(parcel, 5, getDisplayName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
