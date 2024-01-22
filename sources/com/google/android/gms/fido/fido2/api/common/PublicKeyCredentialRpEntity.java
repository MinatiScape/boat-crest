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
@SafeParcelable.Class(creator = "PublicKeyCredentialRpEntityCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class PublicKeyCredentialRpEntity extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialRpEntity> CREATOR = new zzap();
    @NonNull
    @SafeParcelable.Field(getter = "getId", id = 2)
    public final String h;
    @NonNull
    @SafeParcelable.Field(getter = "getName", id = 3)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getIcon", id = 4)
    public final String j;

    @SafeParcelable.Constructor
    public PublicKeyCredentialRpEntity(@NonNull @SafeParcelable.Param(id = 2) String str, @NonNull @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) String str3) {
        this.h = (String) Preconditions.checkNotNull(str);
        this.i = (String) Preconditions.checkNotNull(str2);
        this.j = str3;
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof PublicKeyCredentialRpEntity) {
            PublicKeyCredentialRpEntity publicKeyCredentialRpEntity = (PublicKeyCredentialRpEntity) obj;
            return Objects.equal(this.h, publicKeyCredentialRpEntity.h) && Objects.equal(this.i, publicKeyCredentialRpEntity.i) && Objects.equal(this.j, publicKeyCredentialRpEntity.j);
        }
        return false;
    }

    @Nullable
    public String getIcon() {
        return this.j;
    }

    @NonNull
    public String getId() {
        return this.h;
    }

    @NonNull
    public String getName() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIcon(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
