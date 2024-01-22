package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SignInPasswordCreator")
/* loaded from: classes6.dex */
public class SignInPassword extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SignInPassword> CREATOR = new zbq();
    @SafeParcelable.Field(getter = "getId", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getPassword", id = 2)
    public final String i;

    @SafeParcelable.Constructor
    public SignInPassword(@NonNull @SafeParcelable.Param(id = 1) String str, @NonNull @SafeParcelable.Param(id = 2) String str2) {
        this.h = Preconditions.checkNotEmpty(((String) Preconditions.checkNotNull(str, "Account identifier cannot be null")).trim(), "Account identifier cannot be empty");
        this.i = Preconditions.checkNotEmpty(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SignInPassword) {
            SignInPassword signInPassword = (SignInPassword) obj;
            return Objects.equal(this.h, signInPassword.h) && Objects.equal(this.i, signInPassword.i);
        }
        return false;
    }

    @NonNull
    public String getId() {
        return this.h;
    }

    @NonNull
    public String getPassword() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getPassword(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
